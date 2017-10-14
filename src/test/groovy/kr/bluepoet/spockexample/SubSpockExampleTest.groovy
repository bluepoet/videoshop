package kr.bluepoet.spockexample
/**
 * Created by bluepoet on 2017. 10. 11..
 */
class SubSpockExampleTest extends SpockExampleTest {
    void setupSpec() {
        println 'SubSpockExampleTest setupSpec()'
    }

    void setup() {
        println 'SubSpockExampleTest setup()'
    }

    def "fixture method 실행순서를 확인한다."() {
        expect:
        println 'confirm fixture method execution order'
    }

    void cleanupSpec() {
        println 'SubSpockExampleTest cleanupSpec'
    }

    void cleanup() {
        println 'SubSpockExampleTest cleanup()'
    }
}