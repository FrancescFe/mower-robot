package org.francescfe.mowerrobot.infrastructure.output.cli

import org.francescfe.mowerrobot.application.dto.RobotResult
import org.francescfe.mowerrobot.application.port.output.ResultPrinter

class ConsoleResultPrinter : ResultPrinter {
    override fun print(results: List<RobotResult>) {
        results.forEach { result ->
            println("${result.x} ${result.y} ${result.orientation}")
        }
    }
}
