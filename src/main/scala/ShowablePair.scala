abstract class Show {
  def show: String
}

class ShowablePair[T1 <: Show, T2 <: Show](val t1: T1, val t2: T2) extends Show {
  override def show: String = s"(${t1.show}, ${t2.show})"
}

//
//// 型注釈を与えることで抽象的に扱えるようになる
//// search(Seq("a", "b", "c", "d"), { x: String => x == "a" })
//def search[A](seq: Seq[A], f: A => Boolean): Boolean = {
//  def searchRec(i: Int): Boolean = {
//    if (seq.length == i) false
//    else if (f(seq(i))) true
//    else searchRec(i + 1)
//  }
//
//  searchRec(0)
//}
//
//// カリー化することで型注釈が不要になり可読性も向上する
//// search(Seq("a", "b", "c", "d")){ x => x == "a" }
//def search[A](seq: Seq[A])(f: A => Boolean): Boolean = {
//  def searchRec(i: Int): Boolean = {
//    if (seq.length == i) false
//    else if (f(seq(i))) true
//    else searchRec(i + 1)
//  }
//
//  searchRec(0)
//}
