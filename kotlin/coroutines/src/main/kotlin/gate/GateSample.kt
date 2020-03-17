package gate

import mu.KotlinLogging

class RangeGate(private val from: Int,
                private val to: Int,
                private val output: Wire<Int>.Output) : Gate {

    companion object {
        private val log = KotlinLogging.logger {}
    }

    var current = from

    override fun tick() {
        log.trace { "from: $from, to: $to, current: $current"}
        output.set(if (current <= to) current++ else -1)
    }
}

class SumGate(private val in1: Wire<Int>.Input,
              private val in2: Wire<Int>.Input,
              private val out: Wire<Int>.Output) : Gate {

    companion object {
        private val log = KotlinLogging.logger {}
    }

    override fun tick() {
        val v1 = in1.get()
        val v2 = in2.get()
        val sum = if (v1 == -1 || v2 == -1) -1 else v1 + v2
        log.trace { "($v1 + $v2 -> $sum)" }
        out.set(sum)
    }
}

class PrintGate(private val input: Wire<Int>.Input) : Gate {

    override fun tick() {
        println("result: ${input.get()}")
    }
}

class IntWire : Wire<Int>(-1)

fun main() {

    val wire1 = IntWire();
    val wire2 = IntWire();
    val wire3 = IntWire();

    val range1 = RangeGate(1, 5, wire1.output)
    val range2 = RangeGate(50, 55, wire2.output)
    val sum = SumGate(wire1.input, wire2.input, wire3.output)
    val print = PrintGate(wire3.input)

    val gateSystem = GateSystem(listOf(range1, range2, sum, print), listOf(wire1, wire2, wire3))

    for (i in 1..10) {
        gateSystem.tick()
    }
}