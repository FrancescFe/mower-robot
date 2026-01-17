package mowerrobot.domain.objectMother

import org.francescfe.mowerrobot.domain.grid.Grid

object GridMother {
    fun default() = Grid(upperRightX = 5, upperRightY = 5)

    fun of(
        upperRightX: Int,
        upperRightY: Int,
    ) = Grid(upperRightX, upperRightY)
}
