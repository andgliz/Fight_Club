package com.example.fightclub

interface RandomProvider {
    fun nextInt(range: IntRange): Int
}

object DefaultRandomProvider : RandomProvider {
    override fun nextInt(range: IntRange): Int = range.random()
}