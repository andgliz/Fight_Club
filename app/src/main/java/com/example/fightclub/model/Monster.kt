package com.example.fightclub.model

class Monster(
    name: String,
    attack: Int,
    defense: Int,
    maxHealth: Double,
    possibleDamageRange: IntRange,
) : Creature(
    name = name,
    attack = attack,
    defense = defense,
    maxHealth = maxHealth,
    possibleDamageRange = possibleDamageRange) {
    override fun death() {
        println("$name is defeated!\n")
    }
}

class MonsterFactory {
    fun createList(): List<Monster> {
        return listOf(
            Monster(
                name = "Tyler Durden",
                attack = (1..10).random(),
                defense = (1..10).random(),
                maxHealth = (10..25).random().toDouble(),
                possibleDamageRange = 1..5,
            ),
            Monster(
                name = "Darth Vader",
                attack = (10..20).random(),
                defense = (10..20).random(),
                maxHealth = (25..30).random().toDouble(),
                possibleDamageRange = 2..7,
            ),
            Monster(
                name = "Thanos",
                attack = (20..30).random(),
                defense = (20..30).random(),
                maxHealth = (30..40).random().toDouble(),
                possibleDamageRange = 5..8,
            ),
        )
    }
}
