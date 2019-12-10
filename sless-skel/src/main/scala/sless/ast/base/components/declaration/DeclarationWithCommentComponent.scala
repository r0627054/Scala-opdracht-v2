package sless.ast.base.components.declaration


import sless.ast.base.CommentFormat
import sless.ast.base.components.{CssComponent, PropertyComponent, ValueComponent}

class DeclarationWithCommentComponent(override val prop: PropertyComponent, override val value: ValueComponent, val comment : String)
  extends DeclarationComponent(prop,value) with CommentFormat {

  def this(declarationComponent: DeclarationComponent, comment: String) {
    this(declarationComponent.prop,declarationComponent.value,comment)
  }

  override def compile(sheet: CssComponent): String = {
     super.compile(sheet) + format(comment)
  }

  override def pretty(sheet: CssComponent, spaces: Int): String = {
    super.pretty(sheet,spaces) + " " + format(comment)
  }



}
