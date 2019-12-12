package sless.ast.base.components.value.prop.specific

import sless.ast.base.components.value.ValueComponent
import sless.ast.base.components.value.basic.{AutoValueComponent, EmptyValueComponent, InheritValueComponent, LengthValueComponent}

class MarginValueComponent(val margin1: ValueComponent, val margin2: ValueComponent, val margin3: ValueComponent, val margin4: ValueComponent) extends ValueComponent{
  require(isValidMarginValue(margin1),"Invalid value of first margin value")
  require(isValidMarginValue(margin2),"Invalid value of second margin value")
  require(isValidMarginValue(margin3),"Invalid value of third margin value")
  require(isValidMarginValue(margin4),"Invalid value of fourth margin value")

  def this(margin1: ValueComponent, margin2: ValueComponent, margin3: ValueComponent){
    this(margin1,margin2,margin3,new EmptyValueComponent())
  }

  def this(margin1: ValueComponent, margin2: ValueComponent){
    this(margin1,margin2,new EmptyValueComponent())
  }

  def this(margin1: ValueComponent){
    this(margin1,new EmptyValueComponent())
  }

  override def getStringValue(): String = {
    margin1.basic() + possibleMarginFormat(margin2) + possibleMarginFormat(margin3) + possibleMarginFormat(margin4)
  }

  def isValidMarginValue(value: ValueComponent) : Boolean = {
    value.isInstanceOf[LengthValueComponent] || value.isInstanceOf[EmptyValueComponent] ||  value.isInstanceOf[AutoValueComponent]
  }

  /**
    * Helper function for printing a single margin value. It checks whether or not it has to print spaces
    * @param margin
    * @return
    */
  def possibleMarginFormat(margin: ValueComponent) : String = {
    if(margin.isInstanceOf[EmptyValueComponent]){
      ""
    }else {
      " " + margin.basic()
    }
  }
}
