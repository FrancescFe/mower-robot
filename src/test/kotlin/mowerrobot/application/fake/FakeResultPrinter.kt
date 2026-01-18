package mowerrobot.application.fake

import org.francescfe.mowerrobot.application.dto.RobotResult
import org.francescfe.mowerrobot.application.port.output.ResultPrinter

class FakeResultPrinter : ResultPrinter {
    val printedResults = mutableListOf<List<RobotResult>>()

    override fun print(results: List<RobotResult>) {
        printedResults.add(results)
    }
}
