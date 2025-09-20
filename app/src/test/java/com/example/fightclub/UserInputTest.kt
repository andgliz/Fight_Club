package com.example.fightclub

import com.example.fightclub.model.DifficultyLevel
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue

class UserInputTest {
    private val userInput = UserInput()

    @Test
    fun selectDifficultyLevel_returnsEasyDifficultyLevelWhenUserEntersEasy() {
        val io = FakeIO(listOf("easy"))
        val result = userInput.selectDifficultyLevel(io)
        assertTrue(result is DifficultyLevel.Easy)
        assertEquals(
            listOf("Choose the game level: EASY, MEDIUM, HARD.\nEnter your choice:"),
            io.getOutputs()
        )
    }

    @Test
    fun selectDifficultyLevel_returnsMediumDifficultyLevelWhenUserEntersMedium() {
        val io = FakeIO(listOf("medium"))
        val result = userInput.selectDifficultyLevel(io)
        assertTrue(result is DifficultyLevel.Medium)
        assertEquals(
            listOf("Choose the game level: EASY, MEDIUM, HARD.\nEnter your choice:"),
            io.getOutputs()
        )
    }

    @Test
    fun selectDifficultyLevel_returnsHardDifficultyLevelWhenUserEntersHard() {
        val io = FakeIO(listOf("hard"))
        val result = userInput.selectDifficultyLevel(io)
        assertTrue(result is DifficultyLevel.Hard)
        assertEquals(
            listOf("Choose the game level: EASY, MEDIUM, HARD.\nEnter your choice:"),
            io.getOutputs()
        )
    }

    @Test
    fun selectDifficultyLevel_asksAgainWhenUserEntersWrongInput() {
        val io = FakeIO(listOf("lol", "EASY"))
        val result = userInput.selectDifficultyLevel(io)
        assertTrue(result is DifficultyLevel.Easy)
        assertEquals(
            listOf(
                "Choose the game level: EASY, MEDIUM, HARD.\nEnter your choice:",
                "Error. Try again."
            ),
            io.getOutputs()
        )
    }

    @Test
    fun createName_returnsGelyaWhenUserEntersGelya() {
        val io = FakeIO(listOf("Gelya"))
        val result = userInput.createName(io)
        assertEquals("Gelya", result)
        assertEquals(listOf("Enter your name:"), io.getOutputs())
    }

    @Test
    fun createName_asksAgainWhenUserEntersEmptyInput() {
        val io = FakeIO(listOf("", "lol"))
        val result = userInput.createName(io)
        assertEquals("lol", result)
        assertEquals(listOf("Enter your name:", "Error. Try again."), io.getOutputs())
    }

    @Test
    fun createPlayer_returnsPlayerWithCorrectStatsForEasy() {
        val player = userInput.createPlayer(
            name = "Gelya",
            difficultyLevel = DifficultyLevel.Easy()
        )
        assertEquals("Gelya", player.name)
        assertTrue(player.attack in 25..30)
        assertTrue(player.defense in 25..30)
        assertTrue(player.maxHealth in 40.0..60.0)
        assertEquals(8..10,player.possibleDamageRange)
    }

    @Test
    fun continueGame_returnsTrueWhenUserEntersYesOrY() {
        val io = FakeIO(listOf("Y", "YES"))
        val result = userInput.continueGame(io)
        assertTrue(result)
        assertEquals(listOf("Do you want to continue? (Y/N)"), io.getOutputs())
    }

    @Test
    fun continueGame_returnsFalseWhenUserEntersNoOrN() {
        val io = FakeIO(listOf("N", "NO"))
        val result = userInput.continueGame(io)
        assertFalse(result)
        assertEquals(listOf("Do you want to continue? (Y/N)"), io.getOutputs())
    }

    @Test
    fun continueGame_asksAgainWhenUserEntersWrongInput() {
        val io = FakeIO(listOf("", "lol", "N"))
        val result = userInput.continueGame(io)
        assertFalse(result)
        assertEquals(
            listOf(
                "Do you want to continue? (Y/N)",
                "Error. Try again.",
                "Error. Try again."
            ),
            io.getOutputs()
        )
    }
}
