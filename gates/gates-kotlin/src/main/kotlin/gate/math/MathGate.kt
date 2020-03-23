package gate.math

import gate.*

class RangeGate(val from: Int,
                val to: Int,
                val output: GateOutput<Int>) : Gate<RangeGate> {

    override val receiver = this
    override var f = range()

    private fun range(): RangeGate.() -> Unit {
        var current = from
        return {
            output.set(if (current <= to) current++ else -1)
        }
    }
}

class IntFunctionGate(val x: GateInput<Int>,
                      val y: GateInput<Int>,
                      val z: GateOutput<Int>) : Gate<IntFunctionGate> {

    override val receiver = this

    override var f = plus()

    private fun plus(): IntFunctionGate.() -> Unit = {
        z.set(x.get() + y.get())
    }
}
