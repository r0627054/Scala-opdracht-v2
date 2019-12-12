package sless.ast.base.components.selector
import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.constructors.GroupSelectorComponent

case class ExtendedSelectorComponent(val selector1 : SelectorComponent, val selector2: SelectorComponent) extends SelectorComponent {

  /**
    * First creates the new group selector.
    * Than replaces selector2 with the newly created group selector.
    * @param css The old CSS component where the selector isn't replaced
    * @return The new CSS component where the selector is replaced
    */
  override def extendSelectorReplacement(css: CssComponent): CssComponent = {
    //when extended selectors are chained, all the exteneded selectors have to be replaced
    //nested extend calls to appear in s1 (but not in s2, this is a regular selector): any outer
    //extend call extends its selector after erasing any more inward extend calls in s1
    val selector1Css : CssComponent = selector1.extendSelectorReplacement(css)

    val extendedSelector: SelectorComponent = new GroupSelectorComponent(Seq(selector2,selector1))
    selector1Css.replaceGivenSelectorWith(selector2,extendedSelector).replaceGivenSelectorWith(this,selector1)
  }

  override def basic(): String = selector1.basic()

  override def pretty(spaces: Int): String = selector1.pretty(spaces)


}
