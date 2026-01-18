package org.francescfe.mowerrobot.infrastructure.input.cli

import org.francescfe.mowerrobot.application.dto.ExecuteRobotsCommand
import org.francescfe.mowerrobot.application.dto.InstructionDto
import org.francescfe.mowerrobot.application.dto.OrientationDto
import org.francescfe.mowerrobot.application.dto.RobotCommand
import org.francescfe.mowerrobot.infrastructure.input.cli.exception.InputValidationException

class InputParser {
    fun parse(lines: List<String>): ExecuteRobotsCommand {
        if (lines.isEmpty()) {
            throw IllegalArgumentException("Input is empty")
        }

        val (gridUpperRightX, gridUpperRightY) = parseGrid(lines.first())

        val robotLines = lines.drop(1)
        if (robotLines.size % 2 != 0) {
            throw InputValidationException("Mower definition must have position and instructions")
        }

        val robots =
            robotLines.chunked(2).map { (positionLine, instructionLine) ->
                parseRobot(positionLine, instructionLine)
            }

        return ExecuteRobotsCommand(
            gridUpperRightX = gridUpperRightX,
            gridUpperRightY = gridUpperRightY,
            robots = robots,
        )
    }

    private fun parseGrid(line: String): Pair<Int, Int> {
        if (line.length != 2) {
            throw InputValidationException("Invalid grid format: $line")
        }
        val x = line[0].digitToInt()
        val y = line[1].digitToInt()
        return x to y
    }

    private fun parseRobot(
        positionLine: String,
        instructionLine: String,
    ): RobotCommand {
        val trimmed = positionLine.trim()
        if (trimmed.length != 4 || trimmed[2] != ' ') {
            throw InputValidationException("Invalid robot position: $positionLine")
        }

        val x = trimmed[0].digitToInt()
        val y = trimmed[1].digitToInt()
        val orientation = OrientationDto.valueOf(trimmed[3].toString())

        val instructions =
            instructionLine
                .trim()
                .map { char ->
                    InstructionDto.valueOf(char.toString())
                }

        return RobotCommand(
            startX = x,
            startY = y,
            orientation = orientation,
            instructions = instructions,
        )
    }
}
