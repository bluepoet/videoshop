package kr.bluepoet.spockexample

import spock.lang.Shared
import spock.lang.Specification


/**
 * Created by bluepoet on 2017. 10. 11..
 */
class SpockExampleTest extends Specification {
    @Shared def res = new VeryExpensiveResource()

    void setupSpec() {
        println 'SpockExampleTest setupSpec()'
    }

    void setup() {
        println 'SpockExampleTest setup()'
    }

    def "@Shared test : 첫번째 counter 증가 후 값 확인"() {
        when:
        res.plusCounter()

        then:
        res.getCounter() == 1
    }

    def "@Shared test : 두번째 counter 증가 후 값 확인"() {
        when:
        res.plusCounter()

        then:
        res.getCounter() == 2
    }

    void cleanupSpec() {
        println 'SpockExampleTest cleanupSpec'
    }

    void cleanup() {
        println 'SpockExampleTest cleanup()'
    }
}