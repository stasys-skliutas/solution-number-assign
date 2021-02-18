package dev.stasys.numberassigner

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

import static org.hamcrest.Matchers.is
import static org.hamcrest.Matchers.notNullValue
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@Stepwise
class AppSpec extends Specification {
    @Autowired
    private MockMvc mockMvc

    @Shared
    def url = "http://localhost:8080?s=S4"

    def 'assign value to input string'() {
        expect:
            executeAndVerifyAssignValueCall()
    }

    def 'return the same result for the same assignment'() {
        expect:
            executeAndVerifyAssignValueCall()
    }

    def executeAndVerifyAssignValueCall() {
        mockMvc.perform(put(url))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("\$.inputString", is('S4')))
            .andExpect(MockMvcResultMatchers.jsonPath("\$.value", is(notNullValue())))
            .andReturn()
            .response
    }

    def toAssignResult(String source) {
        return new ObjectMapper().readValue(source, AssignResult)
    }
}
