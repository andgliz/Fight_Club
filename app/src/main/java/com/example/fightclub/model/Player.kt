package com.example.fightclub.model

open class Player(
    name: String,
    attack: Int,
    defense: Int,
    maxHealth: Double,
    possibleDamageRange: IntRange,
    private var respawn: Int = 4,
) : Creature(
    name = name,
    attack = attack,
    defense = defense,
    maxHealth = maxHealth,
    possibleDamageRange = possibleDamageRange
) {
    override fun death() {
        if (respawn != 0) {
            currentHealth = maxHealth * 0.3
            respawn -= 1
            if (respawn > 0) {
                println("Health restored by 30%. There are $respawn attempts left.")
            } else {
                println("Health restored by 30%. This is your last attempt.")
            }
        } else {
            println("WASTED:(")
        }
    }
}
