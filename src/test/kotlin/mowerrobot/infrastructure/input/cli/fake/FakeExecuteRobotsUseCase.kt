package mowerrobot.infrastructure.input.cli.fake

import org.francescfe.mowerrobot.application.dto.ExecuteRobotsCommand
import org.francescfe.mowerrobot.application.dto.RobotResult
import org.francescfe.mowerrobot.application.port.input.ExecuteRobotsUseCase

class FakeExecuteRobotsUseCase : ExecuteRobotsUseCase {
    var wasCalled = false
        private set

    var lastCommand: ExecuteRobotsCommand? = null
        private set

    var resultsToReturn: List<RobotResult> = emptyList()

    override fun execute(command: ExecuteRobotsCommand): List<RobotResult> {
        wasCalled = true
        lastCommand = command
        return resultsToReturn
    }
}
