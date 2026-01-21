package com.cmoncrieffe.gundalisgame.game.service

import com.cmoncrieffe.gundalisgame.game.engine.Engine
import com.cmoncrieffe.gundalisgame.game.engine.PlayerResult
import com.cmoncrieffe.gundalisgame.game.state_machine.Effect
import com.cmoncrieffe.gundalisgame.game.state_machine.Event
import com.cmoncrieffe.gundalisgame.game.state_machine.State
import com.tinder.StateMachine

class Session(
    private val stateMachine: StateMachine<State, Event, Effect>,
    private val engine: Engine
) {
    fun next() : SessionResult {
        val (event, function) = when (stateMachine.state) {
            State.Idle -> {
                Event.SendPlayerOrder to { SessionResult("player_order", engine.getPlayers()) }
            }
            State.SentPlayers -> {
                Event.SendGameState to { SessionResult("game_state", engine.getLatestGameState()) }
            }
            State.Running -> {
                val result = if (engine.getLatestGameState().playerResult == PlayerResult.WINNER) Event.SendGameFinished else Event.SendGameState
                result to { SessionResult("game_state", engine.getLatestGameState()) }
            }
            State.Finished -> null to { SessionResult("finished_game_state", engine.getLatestGameState()) }
        }

        event?.let { event -> stateMachine.transition(event) }
        return function()
    }

    fun isFinished(): Boolean {
        return stateMachine.state == State.Finished
    }
}