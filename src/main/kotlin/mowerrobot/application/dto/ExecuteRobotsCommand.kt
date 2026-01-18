package org.francescfe.mowerrobot.application.dto

data class ExecuteRobotsCommand(
    val gridUpperRightX: Int,
    val gridUpperRightY: Int,
    val robots: List<RobotCommand>,
)
