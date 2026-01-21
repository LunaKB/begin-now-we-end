package com.cmoncrieffe.gundalisgame.game.state_machine

sealed class State {
    object Idle: State()
    object SentPlayers: State()
    object Running: State()
    object Finished: State()
}