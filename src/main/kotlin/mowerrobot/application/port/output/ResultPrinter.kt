package org.francescfe.mowerrobot.application.port.output

import org.francescfe.mowerrobot.application.dto.RobotResult

interface ResultPrinter {
    fun print(results: List<RobotResult>)
}
