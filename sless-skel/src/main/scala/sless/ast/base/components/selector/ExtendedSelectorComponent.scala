package sless.ast.base.components.selector
import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.constructors.GroupSelectorComponent

class ExtendedSelectorComponent(val selector1 : SelectorComponent, val selector2: SelectorComponent) extends SelectorComponent {

  /**
    * First creates the new group selector.
    * Than replaces selector2 with the newly created group selector.
    * @param css The old CSS component where the selector isn't replaced
    * @return The new CSS component where the selector is replaced
    */
  override def extendSelectorReplacement(css: CssComponent): CssComponent = {
    val extendedSelector: SelectorComponent = new GroupSelectorComponent(Seq(selector1,selector2))
    css.replaceGivenSelectorWith(selector2,extendedSelector)
  }

  override def basic(): String = throw new IllegalAccessError("This method cannot be called, it has to be replaced with non-extended selectors")

  override def pretty(spaces: Int): String = throw new IllegalAccessError("This method cannot be called, it has to be replaced with non-extended selectors")
}
