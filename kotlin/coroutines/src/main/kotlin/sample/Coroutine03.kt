package sample

import kotlinx.coroutines.*
import kotlin.random.Random

suspend fun funC(): Int {
    delay(Random.nextLong(delay))
    return 10;
}

suspend fun funD(): Int {
    delay(Random.nextLong(delay))
    return 20;
}

suspend fun funE(): Int {
    delay(Random.nextLong(delay))
    return 30;
}

suspend fun <T> runTask(timeMillis: Long, defaultValue: T, block: suspend () -> T): T =
        try {
            withTimeout(timeMillis) {
                block()
            }
        } catch (e: TimeoutCancellationException) {
            defaultValue
        }

class Scheduler<T>(val timeout: Long, val defaultValue: T, vararg val blocks: suspend () -> T) {

    private var finished = false

    fun run() {
        runBlocking {
            while (!finished) {
                blocks.forEachIndexed() { index, block ->
                    val res = runTask(timeout, defaultValue, block)
                    println("task: $index, result: $res")
                }
            }
        }
    }

    fun finish() {
        finished = true
    }
}

fun main() {

    val timeout = 3 * delay / 4
    val scheduler = Scheduler<Int>(timeout, -1, ::funC, ::funD, ::funE)

    GlobalScope.launch {
        scheduler.run()
    }

    Thread.sleep(6000)
    scheduler.finish()
}