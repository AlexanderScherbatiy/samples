package gate

class DirectGateConnection<T>(private val defaultValue: T) : GateConnection<T> {

    private var currentVal = defaultValue
    private var nextVal = defaultValue

    override val input = Input()
    override val output = Output()

    override fun tick() {
        currentVal = nextVal
        nextVal = defaultValue
    }

    inner class Input : GateInput<T> {
        override fun get() = currentVal
    }

    inner class Output : GateOutput<T> {
        override fun set(value: T) {
            nextVal = value
        }
    }
}
