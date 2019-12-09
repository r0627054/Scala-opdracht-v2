package sless.ast.base.components

class CssComponent(val rules: Seq[RuleComponent]) extends BaseComponent {

  override def compile(sheet: CssComponent): String = {
    var result : String = ""
    rules.foreach(r => (result +=  r.compile(sheet)))
    result
  }

  override def pretty(sheet: CssComponent, spaces: Int): String = ???
}
