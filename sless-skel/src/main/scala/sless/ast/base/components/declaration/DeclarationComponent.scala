package sless.ast.base.components.declaration

import sless.ast.base.components.selector.SelectorComponent
import sless.ast.base.components.value.ValueComponent
import sless.ast.base.components.{PropertyComponent, RuleOrDeclarationComponent}
import sless.ast.base.enumeration.MarginType

/**
  * This class represents a CSS Declaration. A CSS declaration contains a property and a value.
  * It is a subclass of the RuleOrDeclarationComponent used for the nested style rules extension.
  *
  * @param prop  The property of the declaration.
  * @param value The value of the declaration.
  */
class DeclarationComponent(val prop: PropertyComponent, val value: ValueComponent) extends RuleOrDeclarationComponent {

  /**
    * It returns the property component to basic followed by a colon and the value component to basic.
    *
    * @return The basic print string of the component.
    */
  override def basic(): String = {
    prop.basic() + ":" + value.basic() + ";"
  }

  /**
    * It returns the property component to pretty followed by a colon, space and the value component to pretty.
    *
    * @param spaces The number of spaces placed before a declaration.
    * @return The pretty print string of the component
    */
  override def pretty(spaces: Int): String = {
    prop.pretty(spaces) + ": " + value.pretty(spaces) + ";"
  }

  /**
    * Checks whether to property of the declaration is a margin property.
    *
    * @return true when the component contains a margin property; otherwise false.
    */
  def containsMarginProperty(): Boolean = {
    prop.containsMarginProperty()
  }

  /**
    * Return the margin position if this declaration is a margin property.
    *
    * @return The margin position if this declaration is a margin property.
    */
  def getMarginPosition: MarginType.Value = {
    prop.getMarginPosition
  }

  /**
    * Returns the string value of the value component.
    *
    * @return
    */
  def getStringValue: String = {
    value.getStringValue
  }

  /**
    * Checks whether the property has the given name.
    *
    * @param name The name to which the property needs to be checked.
    * @return true when the property has the given name; otherwise false.
    */
  def hasPropertyName(name: String): Boolean = {
    prop.equalsPropertyName(name)
  }

  /**
    * Returns the string name of the property.
    *
    * @return The string name of the property.
    */
  def getPropertyName: String = prop.getPropertyName

  /**
    * The declaration component to basic is just this component.
    * It returns this declaration component.
    *
    * @param currentParentSelector the currentParentSelector of the component.
    * @return The Seq of basic RuleComponents with their basic DeclarationComponents.
    */
  override def toBasicComponents(currentParentSelector: SelectorComponent): Seq[RuleOrDeclarationComponent] = Seq(this)
}
