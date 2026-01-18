package org.francescfe.mowerrobot.application.usecase

import org.francescfe.mowerrobot.application.dto.ExecuteRobotsCommand
import org.francescfe.mowerrobot.application.dto.RobotCommand
import org.francescfe.mowerrobot.application.dto.RobotResult
import org.francescfe.mowerrobot.application.mapper.toDomain
import org.francescfe.mowerrobot.application.mapper.toResult
import org.francescfe.mowerrobot.application.port.input.ExecuteRobotsUseCase
import org.francescfe.mowerrobot.application.port.output.ResultPrinter
import org.francescfe.mowerrobot.domain.grid.Grid
import org.francescfe.mowerrobot.domain.mower.Mower
import org.francescfe.mowerrobot.domain.spatial.Position

class ExecuteRobotsInteractor(
    private val resultPrinter: ResultPrinter,
) : ExecuteRobotsUseCase {
    override fun execute(command: ExecuteRobotsCommand): List<RobotResult> {
        val grid =
            Grid(
                upperRightX = command.gridUpperRightX,
                upperRightY = command.gridUpperRightY,
            )

        val results =
            command.robots.map { robotCommand ->
                executeRobot(robotCommand, grid)
            }

        resultPrinter.print(results)

        return results
    }

    private fun executeRobot(
        robotCommand: RobotCommand,
        grid: Grid,
    ): RobotResult {
        val initialMower =
            Mower(
                position = Position(x = robotCommand.startX, y = robotCommand.startY),
                orientation = robotCommand.orientation.toDomain(),
            )

        val instructions = robotCommand.instructions.map { it.toDomain() }

        val finalMower = initialMower.executeAll(instructions, grid)

        return finalMower.toResult()
    }
}
