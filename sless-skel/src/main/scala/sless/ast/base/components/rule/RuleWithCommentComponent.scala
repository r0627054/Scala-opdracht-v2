package sless.ast.base.components.rule

import sless.ast.base.CommentFormat
import sless.ast.base.components.CssComponent
import sless.ast.base.components.declaration.DeclarationComponent
import sless.ast.base.components.selectors.SelectorComponent

class RuleWithCommentComponent(override val s: SelectorComponent, override val declarations: Seq[DeclarationComponent], val comment: String)
  extends RuleComponent(s,declarations) with CommentFormat {

  def this(ruleComponent: RuleComponent, comment: String) {
    this(ruleComponent.s,ruleComponent.declarations,comment)
  }

  override def compile(sheet: CssComponent): String = {
    format(comment) + super.compile(sheet)
  }

  override def pretty(sheet: CssComponent, spaces: Int): String = {
    format(comment) + "\n" + super.pretty(sheet,spaces)
  }

}
