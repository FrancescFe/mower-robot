package mower.domain.model.objectMother

import org.francescfe.mower.domain.model.Position

object PositionMother {
    fun origin() = Position(x = 0, y = 0)

    fun at(
        x: Int,
        y: Int,
    ) = Position(x = x, y = y)
}
