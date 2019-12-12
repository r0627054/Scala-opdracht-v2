package sless.dsl
import org.scalatest.FunSuite
class BetterValuesTest extends FunSuite {
  import BetterValuesImplementation.dsl._
  test("Pixel Test") {
    val ex = css(
      N(All.c("class-name1"), All.c("class-name2")) {
        prop("width") := 100.px
      }
    )

    assert(
      BetterValuesImplementation.dsl.compile(ex) ===
        """*.class-name1,*.class-name2{width:100.0px;}""")
  }
}
