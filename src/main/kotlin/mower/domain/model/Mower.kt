package org.francescfe.mower.domain.model

class Mower(
    val position: Position,
    val orientation: Orientation,
) {
    fun execute(instruction: Instruction): Mower =
        when {
            instruction.isTurnLeft() -> rotateLeft()
            instruction.isTurnRight() -> rotateRight()
            instruction.isMove() -> moveForward()
            else -> this
        }

    fun executeAll(instructions: List<Instruction>): Mower =
        instructions.fold(this) { robot, instruction ->
            robot.execute(instruction)
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

    private fun moveForward(): Mower {
        val newPosition =
            when (orientation) {
                Orientation.N -> position.moveNorth()
                Orientation.S -> position.moveSouth()
                Orientation.E -> position.moveEast()
                Orientation.W -> position.moveWest()
            }

        return Mower(
            position = newPosition,
            orientation = orientation,
        )
    }
}
