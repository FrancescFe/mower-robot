package mowerrobot.domain.objectMother

import org.francescfe.mowerrobot.domain.mower.Mower
import org.francescfe.mowerrobot.domain.spatial.Orientation

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
