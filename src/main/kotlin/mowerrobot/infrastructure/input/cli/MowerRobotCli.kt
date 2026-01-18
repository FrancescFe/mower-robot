package org.francescfe.mowerrobot.infrastructure.input.cli

import org.francescfe.mowerrobot.application.port.input.ExecuteRobotsUseCase

class MowerRobotCli(
    private val parser: InputParser,
    private val useCase: ExecuteRobotsUseCase,
) {
    fun run(input: List<String>) {
        val command = parser.parse(input)
        useCase.execute(command)
    }
}
