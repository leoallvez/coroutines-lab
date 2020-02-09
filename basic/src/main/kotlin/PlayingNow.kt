import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel

const val interval: Long = 5 * 1000L
object PlayingNow {

    var song = Channel<String>()
        private set

    suspend fun sendSong(value: String, interval: Long = 10 * 1000L) {
        println("sent....: $value")
        song.send(value)
        delay(interval)
    }
}

fun main() {
    runBlocking {
        launch {
            var count = 0
            while (true) {
                PlayingNow.sendSong((count++).toString(), interval)
            }
        }

        launch {
            for (song in PlayingNow.song) {
                println("received: $song")
            }
        }
    }
}
