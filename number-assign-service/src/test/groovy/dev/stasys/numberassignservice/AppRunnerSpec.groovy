package dev.stasys.numberassignservice


import dev.stasys.numberassignservice.externalapi.AssignResultResponse
import dev.stasys.numberassignservice.externalapi.ValueAssignClient
import spock.lang.Specification

class AppRunnerSpec extends Specification {
    def 'assign value result in success'() {
        given:
            def valueAssignClient = Mock(ValueAssignClient)
            def appRunner = new AppRunner(valueAssignClient, TestEnv.NUMBER_FILE_PATH.toString())
            def buffer = new ByteArrayOutputStream()
            System.out = new PrintStream(buffer)
        when:
            appRunner.run()
            def output = buffer.toString()
        then:
            1 * valueAssignClient.assignValue('S1') >> new AssignResultResponse().tap {
                it.inputString = 'S2'
                it.value = 200
            }
            output.find("S1 -> AssignResult.*")
            !output.find("Failed to assign value for input text 'S1'. See logs for additional information")
        and:
            1 * valueAssignClient.assignValue('S2') >> new AssignResultResponse().tap {
                it.inputString = 'S2'
                it.value = 200
            }
            output.find("S2 -> AssignResult.*")
            !output.find("Failed to assign value for input text 'S2'. See logs for additional information")
    }

    def 'continue execution after client call fails'() {
        given:
            def valueAssignClient = Mock(ValueAssignClient)
            def appRunner = new AppRunner(valueAssignClient, TestEnv.NUMBER_FILE_PATH.toString())
            def buffer = new ByteArrayOutputStream()
            System.out = new PrintStream(buffer)
        when:
            appRunner.run()
            def output = buffer.toString()
        then:
            1 * valueAssignClient.assignValue('S1') >> {throw new Exception()}
            output.find("Failed to assign value for input text 'S1'. See logs for additional information")
            !output.find("Failed to assign value for input text 'S2'. See logs for additional information")
        and:
            1 * valueAssignClient.assignValue('S2') >> new AssignResultResponse().tap {
                it.inputString = 'S2'
                it.value = 200
            }
            output.find("S2 -> AssignResult.*")
    }
}
