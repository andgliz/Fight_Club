package com.example.fightclub.model

abstract class Creature(
    val name: String,
    val attack: Int,
    val defense: Int,
    val maxHealth: Double,
    val possibleDamageRange: IntRange,
) {
    init {
        require(name.isNotBlank())
        require(attack in 0..30)
        require(defense in 0..30)
        require(maxHealth > 0.0)
        require(possibleDamageRange.min() in 1..10)
        require(possibleDamageRange.max() in 1..10)
    }

    var currentHealth: Double = maxHealth
        protected set

    val isAlive: Boolean
        get() = currentHealth > 0

    abstract fun death()

    fun takeDamage(enemyDamage: Int) {
        currentHealth -= enemyDamage
        if (currentHealth <= 0) {
            death()
        }
    }
}
