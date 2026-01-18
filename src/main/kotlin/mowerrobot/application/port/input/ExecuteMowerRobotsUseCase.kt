package org.francescfe.mowerrobot.application.port.input

import org.francescfe.mowerrobot.application.dto.ExecuteRobotsCommand
import org.francescfe.mowerrobot.application.dto.RobotResult

interface ExecuteMowerRobotsUseCase {
    fun execute(command: ExecuteRobotsCommand): List<RobotResult>
}
