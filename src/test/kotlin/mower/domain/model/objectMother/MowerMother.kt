package mower.domain.model.objectMother

import org.francescfe.mower.domain.model.Mower
import org.francescfe.mower.domain.model.Orientation

object MowerMother {
    fun atOriginFacingNorth() =
        Mower(
            position = PositionMother.origin(),
            orientation = Orientation.N,
        )

    fun at(
        x: Int,
        y: Int,
        orientation: Orientation,
    ) = Mower(
        position = PositionMother.at(x, y),
        orientation = orientation,
    )
}
