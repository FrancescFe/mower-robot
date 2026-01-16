package org.francescfe.mower.domain.model

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
