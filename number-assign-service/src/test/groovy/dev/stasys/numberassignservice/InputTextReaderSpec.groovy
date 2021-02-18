package dev.stasys.numberassignservice

import spock.lang.Specification

class InputTextReaderSpec extends Specification {
    def 'return null when numbers read from empty file'() {
        given:
            def reader = new InputTextReader(TestEnv.EMPTY_NUMBERS_FILE_PATH.toString())
        expect:
            !reader.hasNextTextLine()
            !reader.nextTextLine()
    }

    def 'return numbers'() {
        given:
            def reader = new InputTextReader(TestEnv.NUMBER_FILE_PATH.toString())
        expect:
            reader.hasNextTextLine()
            reader.nextTextLine() == 'S1'
        and:
            reader.hasNextTextLine()
            reader.nextTextLine() == 'S2'
        and:
            !reader.hasNextTextLine()
    }
}
