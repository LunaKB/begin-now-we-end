package com.cmoncrieffe.gundalisgame.game.entity

import com.cmoncrieffe.gundalisgame.game.engine.PlayerResult

class Player (
    val name : String,
    val initiative: Int
) {
    var result: PlayerResult = PlayerResult.PLAYING
}