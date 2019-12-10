package sless.ast.base.creators

import sless.ast.base.BaseAST
import sless.ast.base.components.rule.RuleComponent
import sless.ast.base.components.selectors.combinators.{AdjacentSelectorComponent, ChildSelectorComponent, DescendantSelectorComponent, GeneralSelectorComponent}
import sless.ast.base.components.selectors.constructors.{AllSelectorComponent, GroupSelectorComponent, TypeSelectorComponent}
import sless.ast.base.components.selectors.modifiers.{AttributeSelectorComponent, ClassNameSelectorComponent, IdSelectorComponent, PseudoClassSelectorComponent, PseudoElementSelectorComponent}
import sless.dsl.SelectorDSL

trait SelectorCreator extends BaseAST with SelectorDSL {
  override protected def className(s: Selector, string: String): Selector = new ClassNameSelectorComponent(s,string)

  override protected def id(s: Selector, string: String): Selector = new IdSelectorComponent(s,string)

  override protected def attribute(s: Selector, attr: String, value: Value): Selector = new AttributeSelectorComponent(s,attr,value)

  override protected def pseudoClass(s: Selector, string: String): Selector = new PseudoClassSelectorComponent(s,string)

  override protected def pseudoElement(s: Selector, string: String): Selector = new PseudoElementSelectorComponent(s,string)



  /** -> s + selector { ... } */
  override protected def adjacent(s: Selector, selector: Selector): Selector = new AdjacentSelectorComponent(s,selector)

  /** -> s ~ selector { ... } */
  override protected def general(s: Selector, selector: Selector): Selector = new GeneralSelectorComponent(s,selector)

  /** -> s > selector { ... } */
  override protected def child(s: Selector, selector: Selector): Selector = new ChildSelectorComponent(s,selector)

  /** -> s selector { ... } */
  override protected def descendant(s: Selector, selector: Selector): Selector = new DescendantSelectorComponent(s,selector)




  override protected def group(selectors: Seq[Selector]): Selector = new GroupSelectorComponent(selectors)

  override def tipe(string: String): Selector = new TypeSelectorComponent(string)

  override val All: Selector = new AllSelectorComponent()

  override protected def bindTo(s: Selector, declarations: Seq[Declaration]): Rule = new RuleComponent(s,declarations)
}
