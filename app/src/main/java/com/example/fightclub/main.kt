package com.example.fightclub

import com.example.fightclub.model.MonsterFactory

fun main() {
    println("Happy Hunger Games! And may the odds be ever in your favor.")
    val userInput = UserInput()
    val monsterFactory = MonsterFactory()
    val fightingRing = FightingRing()
    val difficultyLevel = userInput.selectDifficultyLevel(ConsoleIO)
    val name = userInput.createName(ConsoleIO)
    val player = userInput.createPlayer(name, difficultyLevel)

    var gameContinues = true
    val monstersList = monsterFactory.createList()
    for (monster in monstersList) {
        if (!gameContinues || !player.isAlive) {
            println("Game is over")
            break
        }
        while (player.isAlive && monster.isAlive) {
            gameContinues = userInput.continueGame(ConsoleIO)
            if (!gameContinues) {
                break
            }
            fightingRing.showCharacterCharacteristics(player, ConsoleIO)
            fightingRing.showCharacterCharacteristics(monster, ConsoleIO)
            fightingRing.fighting(player, monster, ConsoleIO, DefaultRandomProvider)
            if (monster.isAlive) {
                fightingRing.fighting(monster, player, ConsoleIO, DefaultRandomProvider)
            }
        }
    }
}
