package sless.ast.base.components.value.prop.specific

import sless.ast.base.components.value.ValueComponent
import sless.ast.base.components.value.basic.{AutoValueComponent, EmptyValueComponent, LengthValueComponent}

/**
  * The margin value component is a property specific value component.
  * It represents the possible values that a margin property can have.
  * It requires the 4 margin values to be of a valid type.
  *
  * @param margin1 The first margin value.
  * @param margin2 The second margin value.
  * @param margin3 The third margin value.
  * @param margin4 The fourth margin value.
  */
class MarginValueComponent(val margin1: ValueComponent, val margin2: ValueComponent, val margin3: ValueComponent, val margin4: ValueComponent) extends ValueComponent {
  require(isValidMarginValue(margin1), "Invalid value of first margin value")
  require(isValidMarginValue(margin2), "Invalid value of second margin value")
  require(isValidMarginValue(margin3), "Invalid value of third margin value")
  require(isValidMarginValue(margin4), "Invalid value of fourth margin value")

  /**
    * Creates a margin value component with 3 values and fills the third value with an empty value.
    *
    * @param margin1 The first margin value.
    * @param margin2 The second margin value.
    * @param margin3 The third margin value.
    */
  def this(margin1: ValueComponent, margin2: ValueComponent, margin3: ValueComponent) {
    this(margin1, margin2, margin3, new EmptyValueComponent())
  }

  /**
    * Creates a margin value component with 2 values and fills the third and fourth value with an empty value.
    *
    * @param margin1 The first margin value.
    * @param margin2 The second margin value.
    */
  def this(margin1: ValueComponent, margin2: ValueComponent) {
    this(margin1, margin2, new EmptyValueComponent())
  }

  /**
    * Creates a margin value component with one value and fills the second, third and fourth value with an empty value.
    *
    * @param margin1 The first margin value.
    */
  def this(margin1: ValueComponent) {
    this(margin1, new EmptyValueComponent())
  }

  /**
    * Returns the basic string format of all the value separated with a space.
    *
    * @return The string representation of the value.
    */
  override def getStringValue: String = {
    margin1.basic() + possibleMarginFormat(margin2) + possibleMarginFormat(margin3) + possibleMarginFormat(margin4)
  }

  /**
    * Checks whether one value is of a valid type.
    * A margin value can be a length value or an auto value or an empty value.
    *
    * @param value The value which will be checked on type.
    * @return True when it is of one of the above types; otherwise false.
    */
  def isValidMarginValue(value: ValueComponent): Boolean = {
    value.isInstanceOf[LengthValueComponent] || value.isInstanceOf[EmptyValueComponent] || value.isInstanceOf[AutoValueComponent]
  }

  /**
    * Helper function for printing a single margin value. It checks whether or not it has to print a space.
    *
    * @param margin The value which needs to be printed.
    * @return The string representation preceded with a space or not.
    */
  def possibleMarginFormat(margin: ValueComponent): String = {
    if (margin.isInstanceOf[EmptyValueComponent]) {
      ""
    } else {
      " " + margin.basic()
    }
  }
}
