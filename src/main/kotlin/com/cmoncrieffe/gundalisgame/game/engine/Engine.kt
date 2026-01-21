package com.cmoncrieffe.gundalisgame.game.engine

import com.cmoncrieffe.dice.DiceRoller
import com.cmoncrieffe.dice.DiceSize
import com.cmoncrieffe.gundalisgame.game.engine.manager.GameStateManager
import com.cmoncrieffe.gundalisgame.game.engine.manager.NumberManager
import com.cmoncrieffe.gundalisgame.game.engine.manager.PlayerManager
import com.cmoncrieffe.gundalisgame.game.entity.Player
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope("prototype")
class Engine() {
    private val gameStateManager = GameStateManager()
    private val numberManager = NumberManager()
    private val playerManager = PlayerManager(listOf(
        Player("Ellen1", DiceRoller.roll1Based(DiceSize.D20)),
        Player("Jack1", DiceRoller.roll1Based(DiceSize.D20)),
        Player("Anne1", DiceRoller.roll1Based(DiceSize.D20)),
        Player("Gary1", DiceRoller.roll1Based(DiceSize.D20))
    ))
    private var counter = -1 // Mod of this will be used for player index

    fun run() : Boolean {
        while (gameStateManager.shouldContinue(playerManager.players)) {
            counter++
            val player = playerManager.getValidPlayer(counter)
            player?.let {
                val rolledNumber = DiceRoller.roll1Based(DiceSize.D100)
                player.result = if (numberManager.checkNumber(rolledNumber))
                    PlayerResult.PLAYING
                else
                    PlayerResult.ELIMINATED

                gameStateManager.addGameState(player, rolledNumber)
                gameStateManager.determineWinner(playerManager.players)
                return true
            }
        }
        return false
    }

    fun getPlayers() : List<Player> {
        return playerManager.players
    }

    fun getLatestGameState() : GameState {
        return gameStateManager.latestGameState()
    }
}