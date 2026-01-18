package org.francescfe.mowerrobot.infrastructure.output.cli

import org.francescfe.mowerrobot.application.dto.RobotResult

class ConsoleResultPrinter {
    fun print(results: List<RobotResult>) {
        results.forEach { result ->
            println("${result.x}${result.y} ${result.orientation}")
        }
    }
}
