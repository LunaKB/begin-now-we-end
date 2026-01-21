package com.cmoncrieffe.gundalisgame.game.state_machine

import com.cmoncrieffe.gundalisgame.game.engine.Engine
import com.tinder.StateMachine
import org.springframework.stereotype.Component

@Component
class StateMachineFactory {
    fun create(engine: Engine): StateMachine<State, Event, Effect> {
        return StateMachine.create {
            initialState(State.Idle)
            state<State.Idle> {
                on<Event.SendPlayerOrder> { transitionTo(State.SentPlayers) }
            }
            state<State.SentPlayers> {
                on<Event.SendGameState> { transitionTo(State.Running, Effect.RunGame) }
            }
            state<State.Running> {
                on<Event.SendGameState> { transitionTo(State.Running, Effect.RunGame) }
                on<Event.SendGameFinished> { transitionTo(State.Finished)}
            }
            state<State.Finished> {}
            onTransition { transition ->
                val validTransition = transition as? StateMachine.Transition.Valid ?: return@onTransition
                when (validTransition.sideEffect) {
                    Effect.RunGame -> engine.run()
                    else -> {}
                }
            }
        }
    }
}