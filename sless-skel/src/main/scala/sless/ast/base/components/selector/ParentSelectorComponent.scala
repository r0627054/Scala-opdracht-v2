package sless.ast.base.components.selector

import sless.ast.base.components.CssComponent

/**
  * The parent selector is represented by the parent keyword.
  * The parent selector cannot be printed as basic or pretty or extend by another selector.
  * This parent selector must be first transformed to basic representation before it can be printed.
  */
case class ParentSelectorComponent() extends SelectorComponent {

  /**
    * A parent selector cannot be extended. This is because it first needs to be resolved before it can be extended.
    *
    * @throws IllegalAccessError in every case.
    * @param css The old CSS component where the selector isn't replaced
    * @return The new CSS component where the selector is replaced
    */
  override def extendSelectorReplacement(css: CssComponent): CssComponent = throw new IllegalAccessError("A Parent selector component cannot be extended. It should be resolved first.")

  /**
    * A parent selector cannot be printed as basic. This is because it first needs to be resolved before it can be printed.
    *
    * @throws IllegalAccessError in every case.
    * @return The basic print string of the component.
    */
  override def basic(): String = throw new IllegalAccessError("A Parent selector component cannot be printed by basic. It should be resolved first")

  /**
    * A parent selector cannot be printed as pretty. This is because it first needs to be resolved before it can be printed.
    *
    * @throws IllegalAccessError in every case.
    * @param spaces The number of spaces placed before a declaration.
    * @return The pretty print string of the component
    */
  override def pretty(spaces: Int): String = throw new IllegalAccessError("A Parent selector component cannot be extended. It should be resolved first.")

  /**
    * This selector is replaced with the given selector.
    *
    * @param parentSelector The selector which will replace the parent selector.
    * @return The given parent selector.
    */
  override def replaceParentWithSelectorComponent(parentSelector: SelectorComponent): SelectorComponent = parentSelector

  /**
    * This method returns true because this selector is a parent selector.
    *
    * @return True when because this is a parent selector.
    */
  override def hasParentSelectorComponent: Boolean = true
}
