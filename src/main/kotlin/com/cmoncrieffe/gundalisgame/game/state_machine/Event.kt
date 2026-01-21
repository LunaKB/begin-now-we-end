package com.cmoncrieffe.gundalisgame.game.state_machine

sealed class Event {
    object SendPlayerOrder: Event()
    object SendGameState: Event()
    object SendGameFinished : Event()
}