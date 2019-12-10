package sless.ast.base.creators

import sless.ast.base.BaseAST
import sless.ast.base.components.declaration.{DeclarationComponent, DeclarationWithCommentComponent}
import sless.ast.base.components.rule.{RuleComponent, RuleWithCommentComponent}
import sless.dsl.CommentDSL

trait CommentCreator extends BaseAST with CommentDSL{
  override protected def commentRule(rule: RuleComponent, str: String): RuleComponent = {
      new RuleWithCommentComponent(rule,str)
  }

  override protected def commentDeclaration(declaration: DeclarationComponent, str: String): DeclarationComponent = {
      new DeclarationWithCommentComponent(declaration,str)
  }
}
