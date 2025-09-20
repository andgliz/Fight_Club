package com.example.fightclub

class FakeIO(private val inputs: List<String>) : IOProvider {
    private val outputs = mutableListOf<String>()
    private var index = 0

    override fun read(): String = inputs[index++]
    override fun write(message: String) {
        outputs.add(message)
    }

    fun getOutputs() = outputs
}