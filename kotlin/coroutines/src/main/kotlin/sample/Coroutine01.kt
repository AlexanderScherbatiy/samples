package sample

val evenNumbers = sequence {

    var evenNumber = 0

    while (true) {
        yield(evenNumber)
        evenNumber += 2
    }
}

fun main() {
    println("first 5 even numbers: ${evenNumbers.take(5).toList()}")

    for (i in evenNumbers) {
        if (i > 10) {
            break
        }
        println("even number: $i")
    }
}