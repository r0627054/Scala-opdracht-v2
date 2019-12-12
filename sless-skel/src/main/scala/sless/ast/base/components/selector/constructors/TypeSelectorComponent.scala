package sless.ast.base.components.selector.constructors

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.SelectorComponent

case class TypeSelectorComponent(val string:String) extends SelectorComponent{
  override def basic(): String = string

  override def pretty(spaces: Int): String = string

  /**
    * In case of a type selector the input css in just returned.
    * No replacements has to be made.
    *
    * @param css The old CSS component where the selector isn't replaced
    * @return The new CSS component where the selector is replaced
    */
  override def extendSelectorReplacement(css: CssComponent): CssComponent = css
}
