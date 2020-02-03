import kotlinx.coroutines.*

fun main() {
    println("Program execution will now in block")
    runBlocking {
        launch {
            delay(1000L)
            println("Task from RumBlocking")
        }

        GlobalScope.launch {
            delay(500L)
            println("Task from GlobalScope")
        }

        coroutineScope {
            launch {
                delay(1500L)
                println("Task from coroutineScope")
            }
        }
    }
    println("Program execution will continue")
}