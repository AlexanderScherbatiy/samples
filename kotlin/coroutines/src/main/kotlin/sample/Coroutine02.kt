package sample

import kotlinx.coroutines.*
import kotlin.random.Random

const val delay = 500L

suspend fun funA() : Int {
    delay(Random.nextLong(delay))
    return 10;
}

suspend fun funB(): Int {
    delay(Random.nextLong(delay))
    return 20;
}

suspend fun <T> runWithTimeout(timeMillis: Long, defaultValue: T, block: suspend CoroutineScope.() -> T) : T =
     try {
        withTimeout(timeMillis) {
            block()
        }
    } catch (e: TimeoutCancellationException) {
        defaultValue
    }

fun main() {

    val waitTimeout = 3 * delay / 4

    runBlocking {

        repeat(10) {

            println("iteration: $it")

            val resA = runWithTimeout(waitTimeout, -1) {
                funA()
            }

            println("result A: $resA")

            val resB = runWithTimeout(waitTimeout, -2) {
                funB()
            }

            println("result B: $resB")
            println()
        }
    }
}