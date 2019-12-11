package sless.dsl

import org.scalatest.FunSuite
import sless.dsl.MixinImplementation.dsl

class MixinTest extends FunSuite {
  import MixinImplementation.dsl._
  import MixinImplementation._
  //---------------------------
  //------- PROVIDED TESTS ----
  //---------------------------

  test("Simple mixin") {
    val asgn = prop("property-1") := value("value-1")
    val mixin = List(asgn,asgn)
    val propertiesPre = List(prop("property-pre") := value("value-pre"))
    val propertiesPost = List(prop("property-post") := value("value-post"))
    val rule = All.mixin(propertiesPre, mixin, propertiesPost)
    val ex = css(rule)

    assert(
      MixinImplementation.dsl.compile(ex) ===
        """*{property-pre:value-pre;property-1:value-1;property-1:value-1;property-post:value-post;}""")
  }

  test("Parametric mixin") {
    val asgnPar : String => Declaration = x  => prop(x) := value("value-1")
    val mixin : String => Seq[Declaration] = x  =>  List(asgnPar(x))
    val propertiesPre = List(prop("property-pre") := value("value-pre"))
    val propertiesPost = List(prop("property-post") := value("value-post"))
    val rule = All.mixin(propertiesPre,mixin("Hey-Im-a-mixin"), propertiesPost)
    val ex = css(rule)

    assert(
      MixinImplementation.dsl.compile(ex) ===
        """*{property-pre:value-pre;Hey-Im-a-mixin:value-1;property-post:value-post;}""")
  }

  //--------------------------------
  //----- MY OWN TESTS -------------
  //--------------------------------

  test("simpleColorMixin Test") {
    val paddingMixin = createPaddingMixin()
    val rule = All.mixin(simpleColorMixin,paddingMixin)
    val ex = css(rule)
    assert(
      MixinImplementation.dsl.compile(ex) ===
        """*{color:red;background:white;padding-left:0.5em;padding-Top:0.5em;}""")
 }

  test("nestedMixin Test"){
    val propertiesPre = List(getPreDeclaration)
    val propertiesPost = List(getPostDeclaration)
    val rule = All.mixin(propertiesPre,nestedMixin(createPaddingMixin(),"3em"),propertiesPost)
    val ex = css(rule)
    assert(
      MixinImplementation.dsl.compile(ex) ===
        """*{property-pre:value-pre;padding-left:0.5em;padding-Top:0.5em;margin:3em;property-post:value-post;}""")
  }

  test("doubledWidthMixin Test"){
    val propertiesPre = List(getPreDeclaration)
    val propertiesPost = List(getPostDeclaration)
    val rule = All.mixin(propertiesPre,doubledWidthMixin(5),propertiesPost)
    val ex = css(rule)
    assert(
      MixinImplementation.dsl.compile(ex) ===
        """*{property-pre:value-pre;height:5em;width:10em;property-post:value-post;}""")
  }

  test("all Mixin implementation components test"){
    val propertiesPre = List(getPreDeclaration)
    val propertiesPost = List(getPostDeclaration)
    val rule = All.mixin(propertiesPre,doubledWidthMixin(5),nestedMixin(createPaddingMixin(),"3em"),simpleColorMixin,propertiesPost)
    val ex = css(rule)
    assert(MixinImplementation.dsl.compile(ex) === """*{property-pre:value-pre;height:5em;width:10em;padding-left:0.5em;padding-Top:0.5em;margin:3em;color:red;background:white;property-post:value-post;}""")
  }

  private def getPreDeclaration: Declaration = {
    prop("property-pre") := value("value-pre")
  }

  private def getPostDeclaration: Declaration = {
    prop("property-post") := value("value-post")
  }

  private def createPaddingMixin() : Seq[dsl.Declaration] = {
    val paddingLeft = prop("padding-left") := value("0.5em")
    val paddingTop = prop("padding-Top") := value("0.5em")
    Seq(paddingLeft,paddingTop)
  }

}
