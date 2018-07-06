trait Stack[+T] {
  def pop: (T, Stack[T])

  def push[E >: T](e: E): Stack[E] // [E >: T] = E は T の継承元

  def isEmpty: Boolean
}

class NonEmptyStack[+T](private val top: T, private val rest: Stack[T]) extends Stack[T] {
  override def pop: (T, Stack[T]) = (top, rest)

  override def push[E >: T](e: E): Stack[E] = new NonEmptyStack[E](e, this)

  override def isEmpty: Boolean = false
}

case object EmptyStack extends Stack[Nothing] {
  override def pop: Nothing = throw new IllegalArgumentException("empty stack")

  override def push[E >: Nothing](e: E): Stack[E] = new NonEmptyStack[E](e, this)

  override def isEmpty: Boolean = true
}

object Stack {
  def apply(): Stack[Nothing] = EmptyStack
}

