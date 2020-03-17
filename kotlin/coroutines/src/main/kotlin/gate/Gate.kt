package gate

import mu.KotlinLogging

interface Tickable {
    fun tick()
}

interface Gate : Tickable

open class Wire<T>(private val defaultValue: T) : Tickable {

    companion object {
        private val log = KotlinLogging.logger {}
    }

    private var currentVal = defaultValue
    private var nextVal = defaultValue

    val input = Input()
    val output = Output()

    override fun tick() {
        log.trace { "next: $nextVal, current: $currentVal" }
        currentVal = nextVal
        nextVal = defaultValue
    }

    inner class Input() {
        fun get() = currentVal
    }

    inner class Output() {
        fun set(value: T) {
            nextVal = value
        }
    }
}

class GateSystem(private val gates: List<Gate>,
                 private val wires: List<Wire<*>> ) : Tickable {

    override fun tick() {

        for(gate in gates) {
            gate.tick()
        }

        for(wire in wires) {
            wire.tick()
        }
    }
}
