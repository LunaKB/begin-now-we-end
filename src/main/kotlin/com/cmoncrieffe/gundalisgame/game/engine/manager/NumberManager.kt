package com.cmoncrieffe.gundalisgame.game.engine.manager

class NumberManager {
    private val numbers = mutableSetOf<Int>()

    fun checkNumber(number: Int) : Boolean {
        if (numbers.contains(number))
            return false
        else {
            numbers.add(number)
            return true
        }
    }
}