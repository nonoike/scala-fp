import scala.annotation.tailrec

object Main extends App {

  @tailrec
  def series(n: Int, acc: Int = 0): Int = {
    if (n == 0) {
      acc
    } else {
      series(n - 1, acc + n)
    }
  }

}
