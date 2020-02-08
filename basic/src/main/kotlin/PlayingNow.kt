import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

object PlayingNow {
    var song: Flow<People> = flow {}
    var isUpdated: Boolean = false
}

data class People(val name: String,val age: Int)

fun main() {
    runBlocking {
        withContext(Dispatchers.IO) {
            PlayingNow.song = flow {
                val people = People("Maria da Silva", 33)
                emit(people)
            }
        }
    }

    runBlocking {
        withContext(Dispatchers.Default) {
            PlayingNow.song.collect { people -> println(people) }
        }
    }
}