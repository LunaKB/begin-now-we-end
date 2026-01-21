package com.cmoncrieffe.gundalisgame

import com.cmoncrieffe.gundalisgame.game.engine.PlayerResult
import com.cmoncrieffe.gundalisgame.game.engine.manager.GameStateManager
import com.cmoncrieffe.gundalisgame.game.entity.Player
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(MockitoExtension::class)
class GameStateManagerTests {
    private lateinit var player1: Player
    private lateinit var player2: Player
    private lateinit var player3: Player
    private lateinit var players: List<Player>
    private lateinit var gameStateManager: GameStateManager

    @BeforeEach
    fun setUp() {
        player1 = Player("Player1", 5)
        player2 = Player("Player2", 15)
        player3 = Player("Player3", 10)
        players = listOf(player1, player2, player3)
        gameStateManager = GameStateManager()
    }

    @Test
    fun verifyGameState() {
        gameStateManager.addGameState(player1, 20)
        assertEquals(20, gameStateManager.latestGameState().number)

        gameStateManager.addGameState(player2, 40)
        assertEquals(40, gameStateManager.latestGameState().number)
    }

    @Test
    fun verifyContinue() {
        assertTrue(gameStateManager.shouldContinue(players))

        player2.result = PlayerResult.WINNER
        assertFalse(gameStateManager.shouldContinue(players))
    }

    @Test
    fun verifyWinner() {
        gameStateManager.determineWinner(players)
        assertEquals("invalid", gameStateManager.latestGameState().playerName)

        player2.result = PlayerResult.ELIMINATED
        gameStateManager.determineWinner(players)
        assertEquals("invalid", gameStateManager.latestGameState().playerName)

        player1.result = PlayerResult.ELIMINATED
        gameStateManager.determineWinner(players)
        assertEquals(player3.name, gameStateManager.latestGameState().playerName)
    }
}