package com.example.fightclub.model

sealed class DifficultyLevel(
    val attack: Int,
    val defense: Int,
    val maxHealth: Double,
    val possibleDamageRange: IntRange,
) {
    class Easy : DifficultyLevel(
        attack = (25..30).random(),
        defense = (25..30).random(),
        maxHealth = (40..60).random().toDouble(),
        possibleDamageRange = 8..10,
    )

    class Medium : DifficultyLevel(
        attack = (15..25).random(),
        defense = (15..25).random(),
        maxHealth = (25..40).random().toDouble(),
        possibleDamageRange = 4..10,
    )

    class Hard : DifficultyLevel(
        attack = (1..15).random(),
        defense = (1..15).random(),
        maxHealth = (10..25).random().toDouble(),
        possibleDamageRange = 1..10,
    )
}
