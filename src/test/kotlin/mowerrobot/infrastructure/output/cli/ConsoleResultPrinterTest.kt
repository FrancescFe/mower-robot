package mowerrobot.infrastructure.output.cli

import io.kotest.matchers.shouldBe
import org.francescfe.mowerrobot.application.dto.OrientationDto
import org.francescfe.mowerrobot.application.dto.RobotResult
import org.francescfe.mowerrobot.infrastructure.output.cli.ConsoleResultPrinter
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test

class ConsoleResultPrinterTest {
    @Test
    fun `prints output in required format`() {
        val output =
            captureStdout {
                ConsoleResultPrinter().print(
                    listOf(
                        RobotResult(1, 3, OrientationDto.N),
                        RobotResult(5, 1, OrientationDto.E),
                    ),
                )
            }

        output shouldBe
            """
            1 3 N
            5 1 E
            """.trimIndent()
    }

    private fun captureStdout(block: () -> Unit): String {
        val originalOut = System.out
        val outputStream = ByteArrayOutputStream()

        try {
            System.setOut(PrintStream(outputStream))
            block()
        } finally {
            System.setOut(originalOut)
        }

        return outputStream.toString().trim()
    }
}
