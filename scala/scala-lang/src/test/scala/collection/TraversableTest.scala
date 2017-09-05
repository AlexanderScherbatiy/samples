package collection

import org.scalatest.FunSuite

class TraversableTest extends FunSuite {

  test("Test Tree Traversable") {

    val tree = TraversableNode(1, TraversableNode(2, TraversableLeaf, TraversableLeaf), TraversableNode(3, TraversableLeaf, TraversableLeaf))

    var n = 1;
    for (i <- tree) {
      assert(n === i)
      n += 1
    }
  }

  test("Test Traversable Operations") {
    val node1 = TraversableNode(1, TraversableLeaf, TraversableLeaf)
    val node2 = TraversableNode(2, TraversableLeaf, TraversableLeaf)
    val node3 = TraversableNode(3, TraversableLeaf, TraversableLeaf)

    val sum = node1 ++ node2 ++ node3

    var n = 1;
    for (i <- sum) {
      assert(n === i)
      n += 1
    }

    val leaf = TraversableLeaf
    assert(leaf.isEmpty)
    assert(leaf.size === 0)

    val tree = TraversableNode(1, TraversableNode(2, TraversableLeaf, TraversableLeaf), TraversableNode(3, TraversableLeaf, TraversableLeaf))
    assert(tree.nonEmpty)
    assert(tree.size === 3)
    assert(tree.head === 1)
    assert(tree.tail === List(2, 3))
    assert(tree.exists(x => x == 2))
    assert(!tree.exists(x => x == 5))
    assert(tree.forall(x => x > 0 && x < 4))
    assert(tree.reduceLeft((a, b) => a + b) === 6)
    assert(tree.foldLeft(1)((a, b) => a * b) === 6)
    assert(tree.min === 1)
    assert(tree.max === 3)
    assert(tree.mkString("|") === "1|2|3")

    n = 1;
    for (i <- tree) {
      assert(n === i)
      n += 1
    }

    val incTree = tree.map(a => a + 1)

    n = 2;
    for (i <- incTree) {
      assert(n === i)
      n += 1
    }

    assert(Array(2, 3, 4) === incTree.toArray)
  }
}
