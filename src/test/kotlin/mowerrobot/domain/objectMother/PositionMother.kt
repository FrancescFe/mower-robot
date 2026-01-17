package mowerrobot.domain.objectMother

import org.francescfe.mowerrobot.domain.spatial.Position

object PositionMother {
    fun origin() = Position(x = 0, y = 0)

    fun at(
        x: Int,
        y: Int,
    ) = Position(x = x, y = y)
}
