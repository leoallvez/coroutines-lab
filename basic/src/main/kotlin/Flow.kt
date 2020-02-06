import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking

/**
    flow {...}    - build
    emit(value)   - transmit a value
    collect {...} - receive the values
 */

fun sendPrimes(): Flow<Int> = flow {

    val primesList = listOf(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
    primesList.forEach {
        delay(it * 1000L)
        emit(it)
    }
}

fun main() {
    runBlocking {
        println("Receiving prime numbers:")
        sendPrimes().collect {
            println("Received prime number: $it")
        }
        println("Finish receiving numbers")
    }
}

