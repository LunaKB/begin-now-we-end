package com.cmoncrieffe.gundalisgame.game.service

import com.cmoncrieffe.gundalisgame.game.engine.Engine
import com.cmoncrieffe.gundalisgame.game.state_machine.StateMachineFactory
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Service

@Service
class GameService(
    private val stateMachineFactory: StateMachineFactory,
    private val engineProvider: ObjectProvider<Engine>
) {
    fun newGame() : Session {
        val engine = engineProvider.getObject()
        val tinderStateMachine = stateMachineFactory.create(engine)
        return Session(tinderStateMachine, engine)
    }
}