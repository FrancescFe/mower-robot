package mowerrobot.infrastructure.input.cli

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.francescfe.mowerrobot.infrastructure.input.cli.InputParser
import org.francescfe.mowerrobot.infrastructure.input.cli.exception.InputValidationException
import kotlin.test.Test

class InputParserTest {
    private val parser = InputParser()

    @Test
    fun `parses valid input into ExecuteRobotsCommand`() {
        val input =
            listOf(
                "55",
                "12 N",
                "LMLMLMLMM",
                "33 E",
                "MMRMMRMRRM",
            )

        val command = parser.parse(input)

        command.gridUpperRightX shouldBe 5
        command.gridUpperRightY shouldBe 5
        command.robots shouldHaveSize 2
    }

    @Test
    fun `throws exception on malformed grid`() {
        val input = listOf("5 5")

        shouldThrow<InputValidationException> {
            parser.parse(input)
        }
    }

    @Test
    fun `throws exception when robot definition is incomplete`() {
        val input =
            listOf(
                "5 5",
                "1 2 N",
            )

        shouldThrow<InputValidationException> {
            parser.parse(input)
        }
    }
}
