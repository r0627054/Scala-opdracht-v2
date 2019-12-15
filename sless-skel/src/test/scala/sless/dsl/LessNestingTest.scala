package sless.dsl

import org.scalatest.FunSuite

class LessNestingTest extends FunSuite {
  import LessNestingImplementation.dsl._

  test("Nesting is possible") {
    val nestedExample = css(
      (All ## "header").nest(
        prop("color") := value("black"),
        All.c("logo")(
          prop("width") := value("300px")
        )
      )
    )

    assert(
      LessNestingImplementation.dsl.compile(nestedExample) ===
        """*#header{color:black;}*#header *.logo{width:300px;}""")
  }

  test("Parent support through 'Parent'") {
    val parentExample = css(
      (All ## "header").nest(
        (Parent |+ Parent)(
          prop("width") := value("300px")
        )
      )
    )

    assert(
      LessNestingImplementation.dsl.compile(parentExample) ===
        """*#header+*#header{width:300px;}""")
  }

  test("Parent scoping test for more difficult pattern") {
    val parentExample = css(
      (All ## "header").nest(
        (Parent |+ Parent).nest(
          (Parent |- Parent)(prop("width") := value("300px"))
        )
      )
    )

    assert(
      LessNestingImplementation.dsl.compile(parentExample) ===
        """*#header+*#header *#header+*#header{width:300px;}"""
    )
  }

  //----------------------
  //-------- OWN ---------
  //----------------------

  test("Parent scoping test for more difficult pattern can pretty print") {
    val parentExample = css(
      (All ## "header").nest(
        (Parent |+ Parent).nest(
          (Parent |- Parent)(prop("width") := value("300px"))
        )
      )
    )
    assert(
      LessNestingImplementation.dsl.pretty(parentExample,3) ===
        "*#header + *#header *#header + *#header {\n" +
          "   width: 300px;" +
          "\n}"
    )
  }

}
