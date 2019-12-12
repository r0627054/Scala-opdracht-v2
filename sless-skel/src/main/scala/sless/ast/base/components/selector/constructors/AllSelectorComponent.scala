package sless.ast.base.components.selector.constructors

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.SelectorComponent

case class AllSelectorComponent() extends SelectorComponent {
  val allStringSelectorSymbol : String = "*"

  override def basic(): String = allStringSelectorSymbol

  override def pretty(spaces: Int): String = allStringSelectorSymbol

  /**
    * In case of an All selector the input css in just returned.
    * No replacements has to be made.
    *
    * @param css The old CSS component where the selector isn't replaced
    * @return The new CSS component where the selector is replaced
    */
  override def extendSelectorReplacement(css: CssComponent): CssComponent = css
}
