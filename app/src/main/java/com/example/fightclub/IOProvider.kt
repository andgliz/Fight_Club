package com.example.fightclub

interface IOProvider {
    fun read(): String
    fun write(message: String)
}

object ConsoleIO : IOProvider {
    override fun read(): String = readln()
    override fun write(message: String) = println(message)
}