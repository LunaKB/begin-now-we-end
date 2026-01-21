package com.cmoncrieffe.gundalisgame

import com.cmoncrieffe.gundalisgame.game.engine.PlayerResult
import com.cmoncrieffe.gundalisgame.game.engine.manager.PlayerManager
import com.cmoncrieffe.gundalisgame.game.entity.Player
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertNull
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.Test
import kotlin.test.assertEquals

@ExtendWith(MockitoExtension::class)
class PlayerManagerTests {
    private lateinit var player1: Player
    private lateinit var player2: Player
    private lateinit var player3: Player
    private lateinit var playerManager: PlayerManager

    @BeforeEach
    fun setUp() {
        player1 = Player("Player1", 5)
        player2 = Player("Player2", 15)
        player3 = Player("Player3", 10)
        playerManager = PlayerManager(listOf(player1, player2, player3))
    }

    @Test
    fun verifyPlayerOrder() {
        assertEquals(player2.name,playerManager.players[0].name)
        assertEquals(player3.name, playerManager.players[1].name)
        assertEquals(player1.name, playerManager.players[2].name)
    }

    @Test
    fun verifyValidPlayer() {
        player2.result = PlayerResult.ELIMINATED
        player3.result = PlayerResult.WINNER

        assertNull(playerManager.getValidPlayer(0))
        assertNull(playerManager.getValidPlayer(1))
        assertEquals(player1, playerManager.getValidPlayer(2))
    }
}