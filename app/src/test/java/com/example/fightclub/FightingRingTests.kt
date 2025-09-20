package com.example.fightclub

import com.example.fightclub.model.Monster
import com.example.fightclub.model.Player
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertTrue

class FightingRingTests {
    private val fightingRing = FightingRing()

    class FakeRandomProvider(private val values: List<Int>) : RandomProvider {
        private var index = 0
        override fun nextInt(range: IntRange): Int = values[index++]
    }

    @Test
    fun fighting_successfulAttackReducesDefenderHealth() {
        val attacker =
            Player("Gelya", attack = 5, defense = 3, maxHealth = 40.0, possibleDamageRange = 3..3)
        val defender =
            Monster("Ilya", attack = 2, defense = 2, maxHealth = 30.0, possibleDamageRange = 1..1)

        val io = FakeIO(emptyList())
        val random = FakeRandomProvider(listOf(6, 3))

        fightingRing.fighting(attacker, defender, io, random)

        assertEquals(27.0, defender.currentHealth)
        assertTrue(io.getOutputs().any { it.contains("Success!") })
        assertTrue(io.getOutputs().any { it.contains("You've done 3 damage") })
    }

    @Test
    fun fighting_failedAttackDoesNotReduceHealth() {
        val attacker =
            Player("Gelya", attack = 5, defense = 3, maxHealth = 40.0, possibleDamageRange = 3..3)
        val defender =
            Monster("Ilya", attack = 2, defense = 2, maxHealth = 30.0, possibleDamageRange = 1..1)

        val io = FakeIO(emptyList())
        val random = FakeRandomProvider(listOf(2, 4, 2, 3))

        fightingRing.fighting(attacker, defender, io, random)

        assertEquals(30.0, defender.currentHealth)
        assertTrue(io.getOutputs().any { it.contains("You roll 4. Fail:(") })
        assertTrue(io.getOutputs().any { it.contains("attack failed") })
    }

    @Test
    fun showCharacterCharacteristics_outputsTheCorrectCharacterData() {
        val io = FakeIO(emptyList())
        val character = Player(
            name = "Gelya",
            attack = 5,
            defense = 10,
            maxHealth = 40.0,
            possibleDamageRange = 5..10
        )
        fightingRing.showCharacterCharacteristics(character, io)
        assertEquals(
            listOf(
                "${character.name} characteristics\n" +
                        "Attack: ${character.attack}\n" +
                        "Defense: ${character.defense}\n" +
                        "Health: ${character.currentHealth}\n"
            ),
            io.getOutputs()
        )
    }
}
