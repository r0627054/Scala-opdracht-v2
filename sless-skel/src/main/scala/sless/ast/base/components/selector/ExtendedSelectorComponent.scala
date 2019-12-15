package sless.ast.base.components.selector

import sless.ast.base.components.CssComponent
import sless.ast.base.components.selector.constructors.GroupSelectorComponent

/**
  * The extend selector is a subclass of a selector component. It contains the selector and to which it needs to be extended.
  *
  * @param selector1 The first selector.
  * @param selector2 The second selector.
  */
case class ExtendedSelectorComponent(selector1: SelectorComponent, selector2: SelectorComponent) extends SelectorComponent {

  /**
    * First creates the new group selector.
    * Than replaces selector2 with the newly created group selector.
    *
    * @param css The old CSS component where the selector isn't replaced
    * @return The new CSS component where the selector is replaced
    */
  override def extendSelectorReplacement(css: CssComponent): CssComponent = {
    //when extended selectors are chained, all the extended selectors have to be replaced
    //nested extend calls to appear in s1 (but not in s2, this is a regular selector), any outer
    //extend call extends its selector after erasing any more inward extend calls in s1
    val selector1Css: CssComponent = selector1.extendSelectorReplacement(css)

    val extendedSelector: SelectorComponent = GroupSelectorComponent(Seq(selector2, selector1))
    selector1Css.replaceGivenSelectorWith(selector2, extendedSelector).replaceGivenSelectorWith(this, selector1)
  }

  /**
    * The first selector is print basic.
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = selector1.basic()

  /**
    * The first selector is print pretty.
    *
    * @param spaces The number of spaces placed before a declaration.
    * @return The pretty print string of the component
    */
  override def pretty(spaces: Int): String = selector1.pretty(spaces)

  /**
    * On both selectors the replaceParentWithSelectorComponent is called. Both of these selectors can contain the parent keyword.
    *
    * @param parentSelector The selector which will replace the parent selector.
    * @return The Selector in which the parent selector is replaced with the given selector.
    */
  override def replaceParentWithSelectorComponent(parentSelector: SelectorComponent): SelectorComponent = ExtendedSelectorComponent(selector1.replaceParentWithSelectorComponent(parentSelector), selector2.replaceParentWithSelectorComponent(parentSelector))

  /**
    * Checks the first selector if it has a parent selector component.
    *
    * @return True when the selector contains the parents selector.
    */
  override def hasParentSelectorComponent: Boolean = selector1.hasParentSelectorComponent
}
