
import kotlinx.coroutines.*

fun main() {

    runBlocking {
        test()
        repeat(times = 1_000_000) {
            launch {
                println(".")
            }
        }
    }
}

suspend fun test() = coroutineScope {
    delay(100L)
    print("Hi!")
}
