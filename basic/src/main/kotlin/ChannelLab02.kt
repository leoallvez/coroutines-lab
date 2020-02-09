import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

val channel = Channel<String>()

fun main() {
    runBlocking {
        launch {
            channel.send("A1")
            channel.send("A2")
        }

        launch {
            channel.send("B1")
            channel.send("B2")
        }

        launch {
            repeat(4) {
                println(channel.receive())
            }
        }
    }
}