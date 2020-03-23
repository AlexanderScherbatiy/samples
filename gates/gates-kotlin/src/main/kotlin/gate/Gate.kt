package gate

interface Tickable {
    fun tick()
}

interface Gate<GateReceiver> : Tickable {

    val receiver: GateReceiver
    var f: GateReceiver.() -> Unit

    override fun tick() {
        receiver.f()
    }
}

interface GateInput<T> {
    fun get(): T
}

interface GateOutput<T> {
    fun set(value: T)
}

interface GateConnection<T> : Tickable {
    val input: GateInput<T>
    val output: GateOutput<T>
}
