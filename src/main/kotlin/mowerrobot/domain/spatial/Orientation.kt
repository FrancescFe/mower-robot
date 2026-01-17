package org.francescfe.mowerrobot.domain.spatial

enum class Orientation {
    N,
    E,
    S,
    W,
    ;

    fun turnLeft(): Orientation =
        when (this) {
            N -> W
            W -> S
            S -> E
            E -> N
        }

    fun turnRight(): Orientation =
        when (this) {
            N -> E
            E -> S
            S -> W
            W -> N
        }
}
