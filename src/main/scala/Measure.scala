object Measure extends App {
  println("Elementary level")

  def benchmark(f: => Unit) = {
    val begin = System.currentTimeMillis()
    f
    val end = System.currentTimeMillis()
    val formatter = java.text.NumberFormat.getNumberInstance()
    println(s"time: ${formatter.format(end - begin)} ミリ秒")
  }

  benchmark {
    var list = List[Int]()
    for (i <- 0 to 10000) {
      list = list :+ i
    }
  }
  benchmark {
    var list = Array[Int]()
    for (i <- 0 to 10000) {
      list = list :+ i
    }
  }
  benchmark {
    var list = List[Int]()
    for (i <- 0 to 10000) {
      list = i +: list
    }
  }
  benchmark {
    var list = Array[Int]()
    for (i <- 0 to 10000) {
      list = i +: list
    }
  }

  println("Intermediate level")

  def benchmarkNano(f: => Unit) = {
    val begin = System.nanoTime()
    f
    val end = System.nanoTime()
    val formatter = java.text.NumberFormat.getNumberInstance()
    println(s"time: ${formatter.format(end - begin)} ナノ秒")
  }

  benchmarkNano((1 to 10000000).toList(10000000 - 1))
  benchmarkNano((1 to 10000000).toList.last)
  benchmarkNano {
    val array = (1 to 10000000).toArray
    array(10000000 - 1)
  }
  benchmarkNano((1 to 10000000).toArray.last)


  println("Advanced level")
  var array: Array[Int] = _
  benchmarkNano {
    array = (1 to 100000).toArray
  }
  benchmarkNano(array.last)
  benchmarkNano(array(100000 - 1))
  var hm: scala.collection.immutable.HashMap[Int, Int] = _
  benchmarkNano {
    hm = scala.collection.immutable.HashMap((1 to 100000).map(i => i -> i): _*)
  }
  benchmarkNano(hm.last)
  benchmarkNano(hm(100000 - 1))
  var tm: scala.collection.immutable.TreeMap[Int, Int] = scala.collection.immutable.TreeMap((1 to 100000).map(i => i -> i): _*)
  benchmarkNano {
    tm = scala.collection.immutable.TreeMap((1 to 100000).map(i => i -> i): _*)
  }
  benchmarkNano(tm.last)
  benchmarkNano(tm(100000 - 1))
}
