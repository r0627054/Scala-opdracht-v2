package sless.ast.base.creators

import sless.ast.base.BaseAST
import sless.ast.base.components.rule.RuleComponent
import sless.ast.base.components.selector.combinators.{AdjacentSelectorComponent, ChildSelectorComponent, DescendantSelectorComponent, GeneralSelectorComponent}
import sless.ast.base.components.selector.constructors.{AllSelectorComponent, GroupSelectorComponent, TypeSelectorComponent}
import sless.ast.base.components.selector.modifiers.{AttributeSelectorComponent, ClassNameSelectorComponent, IdSelectorComponent, PseudoClassSelectorComponent, PseudoElementSelectorComponent}
import sless.dsl.SelectorDSL

/**
  * A selector creator is a creator class which implements the SelectorDSL methods.
  */
trait SelectorCreator extends BaseAST with SelectorDSL {
  override protected def className(s: Selector, string: String): Selector = ClassNameSelectorComponent(s, string)

  override protected def id(s: Selector, string: String): Selector = IdSelectorComponent(s, string)

  override protected def attribute(s: Selector, attr: String, value: Value): Selector = AttributeSelectorComponent(s, attr, value)

  override protected def pseudoClass(s: Selector, string: String): Selector = PseudoClassSelectorComponent(s, string)

  override protected def pseudoElement(s: Selector, string: String): Selector = PseudoElementSelectorComponent(s, string)


  /** -> s + selector { ... } */
  override protected def adjacent(s: Selector, selector: Selector): Selector = AdjacentSelectorComponent(s, selector)

  /** -> s ~ selector { ... } */
  override protected def general(s: Selector, selector: Selector): Selector = GeneralSelectorComponent(s, selector)

  /** -> s > selector { ... } */
  override protected def child(s: Selector, selector: Selector): Selector = ChildSelectorComponent(s, selector)

  /** -> s selector { ... } */
  override protected def descendant(s: Selector, selector: Selector): Selector = DescendantSelectorComponent(s, selector)


  override protected def group(selectors: Seq[Selector]): Selector = GroupSelectorComponent(selectors)

  override def tipe(string: String): Selector = TypeSelectorComponent(string)

  override val All: Selector = AllSelectorComponent()

  override protected def bindTo(s: Selector, declarations: Seq[Declaration]): Rule = new RuleComponent(s, declarations)
}
