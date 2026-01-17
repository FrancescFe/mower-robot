package org.francescfe.mowerrobot.domain.mower

import org.francescfe.mowerrobot.domain.grid.Grid
import org.francescfe.mowerrobot.domain.spatial.Orientation
import org.francescfe.mowerrobot.domain.spatial.Position

class Mower(
    val position: Position,
    val orientation: Orientation,
) {
    fun execute(
        instruction: Instruction,
        grid: Grid,
    ): Mower =
        when (instruction) {
            is Instruction.TurnLeft -> rotateLeft()
            is Instruction.TurnRight -> rotateRight()
            is Instruction.Move -> moveForward(grid)
        }

    fun executeAll(
        instructions: List<Instruction>,
        grid: Grid,
    ): Mower =
        instructions.fold(this) { mower, instruction ->
            mower.execute(instruction, grid)
        }

    private fun rotateLeft(): Mower =
        Mower(
            position = position,
            orientation = orientation.turnLeft(),
        )

    private fun rotateRight(): Mower =
        Mower(
            position = position,
            orientation = orientation.turnRight(),
        )

    private fun moveForward(grid: Grid): Mower {
        val candidatePosition =
            when (orientation) {
                Orientation.N -> Position(position.x, position.y + 1)
                Orientation.S -> Position(position.x, position.y - 1)
                Orientation.E -> Position(position.x + 1, position.y)
                Orientation.W -> Position(position.x - 1, position.y)
            }

        return if (grid.isWithinBounds(candidatePosition)) {
            Mower(candidatePosition, orientation)
        } else {
            this
        }
    }
}
