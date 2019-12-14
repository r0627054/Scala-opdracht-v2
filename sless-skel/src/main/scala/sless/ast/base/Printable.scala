package sless.ast.base

/**
  * The printable trait contains all the methods which need to be implemented to print a component.
  * It contains a method to flat print a component or to print a component pretty.
  * It looks like the "Compilable" trait, the difference is that these methods don't need a CSS component as parameter.
  */
trait Printable {

  /**
    * Prints the component very basic. Without unnecessary spaces or newlines.
    *
    * @return The basic print string of the component.
    */
  def basic(): String

  /**
    * Prints the component pretty. The component contains spaces and newlines to pretty print.
    *
    * @param spaces The number of spaces places before a declaration.
    * @return The pretty print string of the component
    */
  def pretty(spaces: Int): String
}
