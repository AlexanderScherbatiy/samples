package gate.math

import gate.DirectGateConnection
import org.junit.Test
import kotlin.test.assertEquals

class IntFunctionGateTest {

    @Test
    fun test() {

        val cx = DirectGateConnection<Int>(-1)
        val cy = DirectGateConnection<Int>(-1)
        val cz = DirectGateConnection<Int>(-1)

        val funcGate = IntFunctionGate(cx.input, cy.input, cz.output)

        fun tick() {
            cx.output.set(2)
            cy.output.set(3)

            cx.tick()
            cy.tick()
            funcGate.tick()
            cz.tick()
        }


        tick()
        assertEquals(5, cz.input.get())

        funcGate.f = {z.set(x.get() * y.get())}
        tick()
        assertEquals(6, cz.input.get())
    }
}
