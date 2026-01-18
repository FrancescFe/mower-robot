package mowerrobot.application

import io.kotest.matchers.shouldBe
import mowerrobot.application.fake.FakeResultPrinter
import org.francescfe.mowerrobot.application.dto.ExecuteRobotsCommand
import org.francescfe.mowerrobot.application.dto.InstructionDto
import org.francescfe.mowerrobot.application.dto.OrientationDto
import org.francescfe.mowerrobot.application.dto.RobotCommand
import org.francescfe.mowerrobot.application.dto.RobotResult
import org.francescfe.mowerrobot.application.usecase.ExecuteMowerRobotsService
import org.junit.jupiter.api.Test

class ExecuteMowerRobotsServiceIT {
    private val printer = FakeResultPrinter()
    private val useCase = ExecuteMowerRobotsService(printer)

    @Test
    fun `executes multiple robots sequentially within grid`() {
        val command =
            ExecuteRobotsCommand(
                gridUpperRightX = 5,
                gridUpperRightY = 5,
                robots =
                    listOf(
                        RobotCommand(
                            startX = 1,
                            startY = 2,
                            orientation = OrientationDto.N,
                            instructions =
                                listOf(
                                    InstructionDto.L,
                                    InstructionDto.M,
                                    InstructionDto.L,
                                    InstructionDto.M,
                                    InstructionDto.L,
                                    InstructionDto.M,
                                    InstructionDto.L,
                                    InstructionDto.M,
                                    InstructionDto.M,
                                ),
                        ),
                        RobotCommand(
                            startX = 3,
                            startY = 3,
                            orientation = OrientationDto.E,
                            instructions =
                                listOf(
                                    InstructionDto.M,
                                    InstructionDto.M,
                                    InstructionDto.R,
                                    InstructionDto.M,
                                    InstructionDto.M,
                                    InstructionDto.R,
                                    InstructionDto.M,
                                    InstructionDto.R,
                                    InstructionDto.R,
                                    InstructionDto.M,
                                ),
                        ),
                    ),
            )

        val results = useCase.execute(command)

        results shouldBe
            listOf(
                RobotResult(1, 3, OrientationDto.N),
                RobotResult(5, 1, OrientationDto.E),
            )
    }
}
