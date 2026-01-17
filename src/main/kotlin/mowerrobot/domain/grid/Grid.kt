package org.francescfe.mowerrobot.domain.grid

import org.francescfe.mowerrobot.domain.spatial.Position

data class Grid(
    val upperRightX: Int,
    val upperRightY: Int,
) {
    init {
        require(upperRightX >= 0) { "Grid upperRightX must be >= 0" }
        require(upperRightY >= 0) { "Grid upperRightY must be >= 0" }
    }

    fun isWithinBounds(position: Position): Boolean =
        position.x in 0..upperRightX &&
            position.y in 0..upperRightY
}
