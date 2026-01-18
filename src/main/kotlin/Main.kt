package org.francescfe

import org.francescfe.mowerrobot.application.port.input.ExecuteRobotsUseCase
import org.francescfe.mowerrobot.application.usecase.ExecuteRobotsInteractor
import org.francescfe.mowerrobot.infrastructure.input.cli.InputParser
import org.francescfe.mowerrobot.infrastructure.output.cli.ConsoleResultPrinter

fun main() {
    try {
        val rawInput = generateSequence(::readLine).toList()

        val inputParser = InputParser()
        val resultPrinter = ConsoleResultPrinter()

        val useCase: ExecuteRobotsUseCase = ExecuteRobotsInteractor()

        val command = inputParser.parse(rawInput)

        val results = useCase.execute(command)

        resultPrinter.print(results)
    } catch (ex: Exception) {
        System.err.println("ERROR: ${ex.message}")
    }
}
