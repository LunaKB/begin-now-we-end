package com.cmoncrieffe.gundalisgame.game.engine.manager

import com.cmoncrieffe.gundalisgame.game.engine.PlayerResult
import com.cmoncrieffe.gundalisgame.game.entity.Player

class PlayerManager(
    input: List<Player>
) {
    val players: List<Player> = input.sortedByDescending { player -> player.initiative }

    fun getValidPlayer(counter: Int): Player? {
        val player = players[counter % players.size]
        return if (player.result == PlayerResult.PLAYING)
            player
        else null
    }
}