package com.example.fightclub

import com.example.fightclub.model.DifficultyLevel
import com.example.fightclub.model.Player

class UserInput {
    fun selectDifficultyLevel(ioProvider: IOProvider): DifficultyLevel {
        var difficultyLevel: DifficultyLevel? = null
        ioProvider.write("Choose the game level: EASY, MEDIUM, HARD.\nEnter your choice:")
        while (difficultyLevel == null) {
            val choice = ioProvider.read()
            when (choice.uppercase()) {
                "EASY" -> {
                    difficultyLevel = DifficultyLevel.Easy()
                }

                "MEDIUM" -> {
                    difficultyLevel = DifficultyLevel.Medium()
                }

                "HARD" -> {
                    difficultyLevel = DifficultyLevel.Hard()
                }

                else -> {
                    ioProvider.write("Error. Try again.")
                }
            }
        }
        return difficultyLevel
    }

    fun createName(ioProvider: IOProvider): String {
        ioProvider.write("Enter your name:")
        var name = ""
        while (name.isBlank()) {
            name = ioProvider.read()
            if (name.isBlank()) {
                ioProvider.write("Error. Try again.")
            }
        }
        return name
    }

    fun createPlayer(name: String, difficultyLevel: DifficultyLevel): Player {
        return Player(
            name = name,
            attack = difficultyLevel.attack,
            defense = difficultyLevel.defense,
            maxHealth = difficultyLevel.maxHealth,
            possibleDamageRange = difficultyLevel.possibleDamageRange
        )
    }

    fun continueGame(ioProvider: IOProvider): Boolean {
        var choice = ""
        var result = true
        ioProvider.write("Do you want to continue? (Y/N)")
        while (choice == "") {
            choice = ioProvider.read()
            when (choice.uppercase()) {
                "YES", "Y" -> {
                    result = true
                }

                "NO", "N" -> {
                    result = false
                }

                else -> {
                    choice = ""
                    ioProvider.write("Error. Try again.")
                }
            }
        }
        return result
    }
}
