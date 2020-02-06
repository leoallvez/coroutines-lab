import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


fun foo(): Flow<Int> = flow { // flow builder
    for (i in 1..3) {
        delay(100) // pretend we are doing something useful here
        emit(i) // emit next value
    }
}

fun main() = runBlocking<Unit> {
    // Collect the flow
    foo().collect { value -> println(value) }
}
