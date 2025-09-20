package com.example.fightclub

import com.example.fightclub.model.Creature

class FightingRing {
    fun fighting(
        attacker: Creature,
        defender: Creature,
        ioProvider: IOProvider,
        randomProvider: RandomProvider
    ) {
        ioProvider.write("${attacker.name} turn.")
        val attackModifier = calculateAttackModifier(attacker.attack, defender.defense)
        val rollDiceSucceeded = rollDice(attackModifier, ioProvider, randomProvider)
        if (rollDiceSucceeded) {
            val attackerDamage = randomProvider.nextInt(attacker.possibleDamageRange)
            ioProvider.write("You've done $attackerDamage damage\n")
            defender.takeDamage(attackerDamage)
        } else {
            ioProvider.write("The attempts are over. The attack failed\n")
        }

    }

    private fun calculateAttackModifier(attack: Int, defense: Int): Int {
        val attackModifier = attack - defense + 1
        return if (attackModifier > 0) attackModifier else 1
    }

    private fun rollDice(
        attackModifier: Int,
        ioProvider: IOProvider,
        randomProvider: RandomProvider
    ): Boolean {
        var attempts = attackModifier
        ioProvider.write("You roll $attempts dice.")
        while (attempts != 0) {
            val result = randomProvider.nextInt(1..6)
            if (result >= 5) {
                ioProvider.write("You roll $result. Success!")
                return true
            } else {
                ioProvider.write("You roll $result. Fail:(")
                attempts -= 1
            }
        }
        return false
    }

    fun showCharacterCharacteristics(character: Creature, ioProvider: IOProvider) {
        ioProvider.write(
            "${character.name} characteristics\n" +
                    "Attack: ${character.attack}\n" +
                    "Defense: ${character.defense}\n" +
                    "Health: ${character.currentHealth}\n"
        )
    }
}
