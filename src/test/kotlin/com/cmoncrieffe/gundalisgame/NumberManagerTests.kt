package com.cmoncrieffe.gundalisgame

import com.cmoncrieffe.gundalisgame.game.engine.manager.NumberManager
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

@ExtendWith(MockitoExtension::class)
class NumberManagerTests {
    @Test
    fun checkRolledNumber() {
        val numberManager = NumberManager()
        val number1 = 20
        val number2 = 30
        val number3 = 20

        assertTrue(numberManager.checkNumber(number1))
        assertTrue(numberManager.checkNumber(number2))
        assertFalse(numberManager.checkNumber(number3))
    }
}