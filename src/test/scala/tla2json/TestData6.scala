package tla2json

object TestData6Test extends TestData.PropTest {
  override val testData = TestData6
}

object TestData6 extends TestData {

  val output =
    """
TLC2 Version 2.17 of 02 February 2022 (rev: 3c7caa5)
Running breadth-first search Model-Checking with fp 39 and seed 2335506240322380849 with 1 worker on 8 cores with 3641MB heap and 64MB offheap memory [pid: 96871] (Mac OS X 12.4 x86_64, Homebrew 18.0.1.1 x86_64, MSBDiskFPSet, DiskStateQueue).
Parsing file /Users/darius/tla/Counter.tla
Labels added.
Parsing file /private/var/folders/l6/290h50ys7t995mrks71zd9zc0000gn/T/TLC.tla
Parsing file /private/var/folders/l6/290h50ys7t995mrks71zd9zc0000gn/T/Integers.tla
Parsing file /private/var/folders/l6/290h50ys7t995mrks71zd9zc0000gn/T/Naturals.tla
Parsing file /private/var/folders/l6/290h50ys7t995mrks71zd9zc0000gn/T/Sequences.tla
Parsing file /private/var/folders/l6/290h50ys7t995mrks71zd9zc0000gn/T/FiniteSets.tla
Semantic processing of module Naturals
Semantic processing of module Sequences
Semantic processing of module FiniteSets
Semantic processing of module TLC
Semantic processing of module Integers
Semantic processing of module Counter
Starting... (2022-07-26 15:55:28)
Computing initial states...
Error: Invariant Inv is violated by the initial state:
/\ x = 0
/\ pc = "Lbl_1"

Finished in 00s at (2022-07-26 15:55:29)
    """.stripMargin

}
