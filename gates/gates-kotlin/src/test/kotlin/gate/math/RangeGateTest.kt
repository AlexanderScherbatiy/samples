package gate.math

import org.junit.Test
import kotlin.test.assertEquals
import gate.DirectGateConnection

class RangeGateTest {

    @Test
    fun testRangeGate() {

        val connection = DirectGateConnection(-1)
        val input = connection.input
        val output = connection.output
        val rangeGate = RangeGate(3, 4, output)

        fun tick() {
            rangeGate.tick()
            connection.tick()
        }

        assertEquals(-1, input.get())

        tick()
        assertEquals(3, input.get())

        tick()
        assertEquals(4, input.get())

        tick()
        assertEquals(-1, input.get())
    }

    @Test
    fun testChangeRangeGateBody() {

        val connection = DirectGateConnection<Int>(-1)
        val input = connection.input
        val output = connection.output
        val rangeGate = RangeGate(3, 4, output)

        fun tick() {
            rangeGate.tick()
            connection.tick()
        }

        tick()
        assertEquals(3, input.get())

        rangeGate.f = { output.set(17) }
        tick()
        assertEquals(17, input.get())
    }
}