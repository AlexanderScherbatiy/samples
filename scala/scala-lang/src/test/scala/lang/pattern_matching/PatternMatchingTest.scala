package lang.pattern_matching

import org.scalatest.FunSuite


class PatternMatchingTest extends FunSuite {

  trait Event

  test("Case Class Pattern Matching") {

    case class MouseClickEvent(x: Int, y: Int) extends Event
    case class KeyPressedEvent(c: Char) extends Event

    def handleEvents(event: Event) = event match {
      case MouseClickEvent(x, y) => assert(x === 2 && y === 3)
      case KeyPressedEvent(c) => assert(c === 'a')
      case _ => assert(false)
    }

    handleEvents(MouseClickEvent(2, 3))
    handleEvents(KeyPressedEvent('a'))
  }

  test("Extractor pattern matching") {

    class MouseClickEvent() extends Event {
      private var _x: Int = 0;
      private var _y: Int = 0;
    }

    object MouseClickEvent {
      def apply(x: Int, y: Int): MouseClickEvent = {
        val mouseClickEvent = new MouseClickEvent()
        mouseClickEvent._x = x
        mouseClickEvent._y = y
        mouseClickEvent
      }

      def unapply(mouseClickEvent: MouseClickEvent): Option[(Int, Int)] = Some((mouseClickEvent._x, mouseClickEvent._y))
    }

    class KeyPressedEvent(val char: Char) extends Event

    object KeyPressedEvent {
      def unapply(keyPressedEvent: KeyPressedEvent): Option[Char] = Some(keyPressedEvent.char)
    }

    def handleEvents(event: Event) = event match {
      case MouseClickEvent(x, y) => assert(x === 2 && y === 3)
      case KeyPressedEvent(c) => assert(c === 'a')
      case _ => assert(false)
    }

    handleEvents(MouseClickEvent(2, 3))
    handleEvents(new KeyPressedEvent('a'))
  }
}
