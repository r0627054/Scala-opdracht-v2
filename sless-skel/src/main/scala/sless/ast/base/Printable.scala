package sless.ast.base

trait Printable {
  def basic(): String
  def pretty(spaces: Int): String
}
