package sless.ast.base.components.selector.constructors

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.SelectorComponent

/**
  * The all Selector component is the most general selector component.
  * It is represented by the "*" sign.
  */
case class AllSelectorComponent() extends SelectorComponent {
  val allStringSelectorSymbol: String = "*"

  /**
    * Returns the all string selector symbol.
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = allStringSelectorSymbol

  /**
    * Returns the all string selector symbol.
    *
    * @param spaces The number of spaces placed before a declaration.
    * @return The pretty print string of the component
    */
  override def pretty(spaces: Int): String = allStringSelectorSymbol

  /**
    * In case of an all selector component, the input css in just returned.
    * No replacements has to be made.
    *
    * @param css The old CSS component where the selector isn't replaced
    * @return The new CSS component where the selector is replaced
    */
  override def extendSelectorReplacement(css: CssComponent): CssComponent = css

  /**
    * In case of an all selector component, the component it just returned.
    * It cannot contain a parent selector.
    *
    * @param parentSelector The selector which will replace the parent selector.
    * @return The Selector in which the parent selector is replaced with the given selector.
    */
  override def replaceParentWithSelectorComponent(parentSelector: SelectorComponent): SelectorComponent = this

  /**
    * Returns false because it cannot contain a parent component.
    *
    * @return True when the selector contains the parents selector.
    */
  override def hasParentSelectorComponent: Boolean = false
}
