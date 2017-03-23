package sample.ui.hello

/**
  * Created by alexsch on 3/5/2017.
  */

import scala.swing._
import scala.swing.event.ButtonClicked
import java.awt.Color

object HelloWorld extends SimpleSwingApplication {

  def top = new MainFrame {

    title = "Hello World!"

    contents = new BorderPanel {
      val label = new Label("Hello World!")
      val paint = new Component {
        override def paintComponent(g: Graphics2D): Unit = {
          super.paintComponent(g)
          g.setColor(Color.RED)
          g.drawOval(0, 0, size.width, size.height)
        }
      }
      val button = new Button("Press Me!") {

        reactions += {
          case ButtonClicked(_) => {
            println("Button Pressed!")
          }
        }
      }

      add(label, BorderPanel.Position.North)
      add(paint, BorderPanel.Position.Center)
      add(button, BorderPanel.Position.South)
    }

    size = new Dimension(400, 300)
  }
}
