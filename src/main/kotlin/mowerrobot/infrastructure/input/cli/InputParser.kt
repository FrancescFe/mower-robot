package org.francescfe.mowerrobot.infrastructure.input.cli

import org.francescfe.mowerrobot.application.dto.ExecuteRobotsCommand
import org.francescfe.mowerrobot.application.dto.InstructionDto
import org.francescfe.mowerrobot.application.dto.OrientationDto
import org.francescfe.mowerrobot.application.dto.RobotCommand
import org.francescfe.mowerrobot.infrastructure.input.cli.exception.InputValidationException

class InputParser {
    fun parse(lines: List<String>): ExecuteRobotsCommand {
        validateNotEmpty(lines)

        val (gridUpperRightX, gridUpperRightY) = parseGrid(lines.first())
        val robots = parseRobots(lines.drop(1))

        return ExecuteRobotsCommand(
            gridUpperRightX = gridUpperRightX,
            gridUpperRightY = gridUpperRightY,
            robots = robots,
        )
    }

    private fun validateNotEmpty(lines: List<String>) {
        if (lines.isEmpty()) {
            throw InputValidationException("Input cannot be empty")
        }
    }

    private fun parseGrid(line: String): GridCoordinates {
        validateGridFormat(line)
        return GridCoordinates(
            x = line[X_COORDINATE_INDEX].digitToInt(),
            y = line[Y_COORDINATE_INDEX].digitToInt(),
        )
    }

    private fun validateGridFormat(line: String) {
        if (line.length != GRID_LINE_LENGTH) {
            throw InputValidationException("Invalid grid format: '$line'. Expected exactly two digits (e.g., '55')")
        }
    }

    private fun parseRobots(lines: List<String>): List<RobotCommand> {
        validateRobotLinesArePaired(lines)
        return lines.chunked(LINES_PER_ROBOT).map { (positionLine, instructionsLine) ->
            parseRobot(positionLine, instructionsLine)
        }
    }

    private fun validateRobotLinesArePaired(lines: List<String>) {
        if (lines.size % LINES_PER_ROBOT != 0) {
            throw InputValidationException("Each robot must have exactly two lines: position and instructions")
        }
    }

    private fun parseRobot(
        positionLine: String,
        instructionsLine: String,
    ): RobotCommand {
        val (x, y, orientation) = parsePosition(positionLine)
        val instructions = parseInstructions(instructionsLine)

        return RobotCommand(
            startX = x,
            startY = y,
            orientation = orientation,
            instructions = instructions,
        )
    }

    private fun parsePosition(line: String): RobotPosition {
        val trimmed = line.trim()
        validatePositionFormat(trimmed, line)

        return RobotPosition(
            x = trimmed[X_COORDINATE_INDEX].digitToInt(),
            y = trimmed[Y_COORDINATE_INDEX].digitToInt(),
            orientation = parseOrientation(trimmed[ORIENTATION_INDEX]),
        )
    }

    private fun validatePositionFormat(
        trimmed: String,
        originalLine: String,
    ) {
        if (trimmed.length != POSITION_LINE_LENGTH || trimmed[SEPARATOR_INDEX] != ' ') {
            throw InputValidationException(
                "Invalid robot position: '$originalLine'. Expected format: 'XY O' (e.g., '12 N')",
            )
        }
    }

    private fun parseOrientation(char: Char): OrientationDto =
        runCatching { OrientationDto.valueOf(char.toString()) }
            .getOrElse { throw InputValidationException("Invalid orientation: '$char'. Expected: N, E, S, W") }

    private fun parseInstructions(line: String): List<InstructionDto> = line.trim().map { parseInstruction(it) }

    private fun parseInstruction(char: Char): InstructionDto =
        runCatching { InstructionDto.valueOf(char.toString()) }
            .getOrElse { throw InputValidationException("Invalid instruction: '$char'. Expected: L, R, M") }

    private data class GridCoordinates(val x: Int, val y: Int)

    private data class RobotPosition(val x: Int, val y: Int, val orientation: OrientationDto)

    companion object {
        private const val GRID_LINE_LENGTH = 2
        private const val X_COORDINATE_INDEX = 0
        private const val Y_COORDINATE_INDEX = 1
        private const val LINES_PER_ROBOT = 2
        private const val ORIENTATION_INDEX = 3
        private const val POSITION_LINE_LENGTH = 4
        private const val SEPARATOR_INDEX = 2
    }
}
