package mowerrobot.infrastructure.input.cli

import io.kotest.matchers.shouldBe
import mowerrobot.infrastructure.input.cli.fake.FakeExecuteRobotsUseCase
import org.francescfe.mowerrobot.infrastructure.input.cli.InputParser
import org.francescfe.mowerrobot.infrastructure.input.cli.MowerRobotCli
import kotlin.test.Test

class MowerRobotCliIT {
    private val fakeUseCase = FakeExecuteRobotsUseCase()
    private val cli = MowerRobotCli(InputParser(), fakeUseCase)

    @Test
    fun `cli parses input and calls use case`() {
        cli.run(
            listOf(
                "55",
                "12 N",
                "LMLMLMLMM",
            ),
        )

        fakeUseCase.wasCalled shouldBe true
    }
}
