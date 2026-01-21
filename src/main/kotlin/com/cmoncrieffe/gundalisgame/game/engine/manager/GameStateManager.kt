package com.cmoncrieffe.gundalisgame.game.engine.manager

import com.cmoncrieffe.gundalisgame.game.engine.GameState
import com.cmoncrieffe.gundalisgame.game.engine.PlayerResult
import com.cmoncrieffe.gundalisgame.game.entity.Player

class GameStateManager {
    private val gameStates = mutableListOf<GameState>()

    fun addGameState(player: Player, rolledNumber: Int) {
        gameStates.add(
            GameState(
                gameStates.size,
                player.name,
                rolledNumber,
                player.result
            )
        )
    }

    fun latestGameState() : GameState {
        return try {
            gameStates.last()
        } catch (e: Exception) {
            GameState(
                -1,
                "invalid",
                -1,
                PlayerResult.PLAYING)
        }
    }

    fun shouldContinue(players: Collection<Player>): Boolean {
        val winner = players.find { player -> player.result == PlayerResult.WINNER }
        return winner == null
    }

    fun determineWinner(players: Collection<Player>) {
        val filteredPlayers = players.filter { player -> player.result != PlayerResult.ELIMINATED }
        if (filteredPlayers.size == 1) {
            val winner = filteredPlayers[0]
            winner.result = PlayerResult.WINNER
            gameStates.add(
                GameState(
                    gameStates.size,
                    winner.name,
                    -1,
                    winner.result
                )
            )
        }
    }
}