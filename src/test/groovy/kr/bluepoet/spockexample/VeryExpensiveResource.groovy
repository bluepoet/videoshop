package kr.bluepoet.spockexample

/**
 * Created by bluepoet on 2017. 10. 11..
 */
class VeryExpensiveResource {
    def counter = 0

    def plusCounter() {
        counter++
    }

    def getCounter() {
        counter
    }
}
