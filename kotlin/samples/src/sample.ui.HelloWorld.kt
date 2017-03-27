/**
 * Created by alexsch on 3/27/2017.
 */

import javax.swing.JFrame
import javax.swing.JLabel


fun main(args: Array<String>) {
    println("Hello, World!")

    val frame = JFrame("Hello")
    frame.setSize(400, 300)
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

    val label = JLabel("Hello World!")
    frame.contentPane = label

    frame.isVisible = true
}