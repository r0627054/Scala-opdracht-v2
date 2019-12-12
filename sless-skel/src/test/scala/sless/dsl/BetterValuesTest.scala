package sless.dsl
import org.scalatest.FunSuite
class BetterValuesTest extends FunSuite {
  import BetterValuesImplementation.dsl._

  //-------------------------------------------
  //--------- LENGTH VALUE TESTS --------------
  //-------------------------------------------

  test("Pixel Test") {
    val v : Value = 100.0.px
    assert(
      compile(lengthValueCssGeneratedHelper(v)) ===
        """*.class-name1,*.class-name2{width:100.0px;}""")
  }

  test("Picas Test") {
    val v : Value = 200.0.pc

    assert(
      compile(lengthValueCssGeneratedHelper(v)) ===        """*.class-name1,*.class-name2{width:200.0pc;}""")
  }

  test("Points Test") {
    val v : Value = 300.0.pt

    assert(
      compile(lengthValueCssGeneratedHelper(v)) ===        """*.class-name1,*.class-name2{width:300.0pt;}""")
  }

  test("MilliMeter Test") {
    val v : Value = 400.0.mm

    assert(
      compile(lengthValueCssGeneratedHelper(v)) ===        """*.class-name1,*.class-name2{width:400.0mm;}""")
  }

  test("CentiMeter Test") {
    val v : Value = 500.0.cm

    assert(
      compile(lengthValueCssGeneratedHelper(v)) ===        """*.class-name1,*.class-name2{width:500.0cm;}""")
  }

  test("Inches Test") {
    val v : Value = 3.0.in

    assert(
      compile(lengthValueCssGeneratedHelper(v)) ===        """*.class-name1,*.class-name2{width:3.0in;}""")
  }

  test("Em Test") {
    val v : Value = 4.0.em

    assert(
      compile(lengthValueCssGeneratedHelper(v)) ===        """*.class-name1,*.class-name2{width:4.0em;}""")
  }

  //-------------------------------------------
  //------ inhert/auto VALUE TESTS ------------
  //-------------------------------------------


  test("inherit Test") {
    val x = css(
      N(All.c("class-name1"), All.c("class-name2")) {
        prop("color") := inherit
      }
    )
    assert(
      compile(x) ===        """*.class-name1,*.class-name2{color:inherit;}""")
  }

  test("auto Test") {
    val x = css(
      N(All.c("class-name1"), All.c("class-name2")) {
        prop("color") := auto
      }
    )
    assert(
      compile(x) ===        """*.class-name1,*.class-name2{color:auto;}""")
  }

  //-------------------------------------------
  //-------- margin constructor TESTS ---------
  //-------------------------------------------

  test("Margin one argument constructor Test") {
    val margin : Value = Margin(5.px)
    assert(
      compile(marginConstructorCssGenerator(margin)) ===        """*.class-dries{margin:5.0px;}""")
  }

  test("Margin two argument constructor Test") {
    val margin : Value = Margin(5.px,auto)
    assert(
      compile(marginConstructorCssGenerator(margin)) ===        """*.class-dries{margin:5.0px auto;}""")
  }

  test("Margin three argument constructor Test") {
    val margin : Value = Margin(5.px,auto,3.em)
    assert(
      compile(marginConstructorCssGenerator(margin)) ===        """*.class-dries{margin:5.0px auto 3.0em;}""")
  }

  test("Margin four argument constructor Test") {
    val margin : Value = Margin(5.px,auto,3.em, 7.cm)
    assert(
      compile(marginConstructorCssGenerator(margin)) ===        """*.class-dries{margin:5.0px auto 3.0em 7.0cm;}""")
  }


  //--------------------------------------
  //------------- HELPER FUNCTIONS--------
  //--------------------------------------


  private def lengthValueCssGeneratedHelper(value: Value) : Css = {
    css(
      N(All.c("class-name1"), All.c("class-name2")) {
        prop("width") := value
      }
    )
  }

  private def marginConstructorCssGenerator(value: Value) : Css = {
    css(
      All.c("class-dries") {
        prop("margin") := value
      }
    )
  }

}
