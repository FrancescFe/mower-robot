package org.francescfe.mowerrobot.application.dto

data class RobotCommand(
    val startX: Int,
    val startY: Int,
    val orientation: OrientationDto,
    val instructions: List<InstructionDto>,
)
