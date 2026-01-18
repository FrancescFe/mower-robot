package org.francescfe.mowerrobot.application.mapper

import org.francescfe.mowerrobot.application.dto.InstructionDto
import org.francescfe.mowerrobot.application.dto.OrientationDto
import org.francescfe.mowerrobot.application.dto.RobotResult
import org.francescfe.mowerrobot.domain.mower.Instruction
import org.francescfe.mowerrobot.domain.mower.Mower
import org.francescfe.mowerrobot.domain.spatial.Orientation

fun OrientationDto.toDomain(): Orientation = Orientation.valueOf(this.name)

fun InstructionDto.toDomain(): Instruction =
    when (this) {
        InstructionDto.L -> Instruction.TurnLeft
        InstructionDto.R -> Instruction.TurnRight
        InstructionDto.M -> Instruction.Move
    }

fun Mower.toResult(): RobotResult =
    RobotResult(
        x = position.x,
        y = position.y,
        orientation = OrientationDto.valueOf(orientation.name),
    )
