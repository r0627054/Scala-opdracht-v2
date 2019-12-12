package sless.ast.base.creators

import sless.ast.base.BaseAST
import sless.ast.base.components.value.ValueComponent
import sless.ast.base.components.value.basic.{AutoValueComponent, CentiMeterValueComponent, EmValueComponent, ExValueComponent, InchesValueComponent, InheritValueComponent, MilliMeterValueComponent, PercentageValueComponent, PicasValueComponent, PixelUnitValueComponent, PointsValueComponent}
import sless.ast.base.components.value.prop.specific.MarginValueComponent
import sless.dsl.BetterValuesDSL

trait BetterValueCreator extends BaseAST with BetterValuesDSL  {
  override def Margin(value: ValueComponent) : Value = new MarginValueComponent(value)
  override def Margin(value1: Value,value2: Value) : Value = new MarginValueComponent(value1,value2)
  override def Margin(value1: Value,value2: Value,value3: Value) : Value = new MarginValueComponent(value1,value2,value3)
  override def Margin(value1: Value,value2: Value,value3: Value,value4: Value) : Value = new MarginValueComponent(value1,value2,value3,value4)

  def inherit: Value = new InheritValueComponent()
  def auto: Value    = new AutoValueComponent()

  override protected def createPixelValue(pixels: Double) : Value   =  PixelUnitValueComponent(pixels)
  override protected def createPicasValue(picas: Double) : Value    =  PicasValueComponent(picas)
  override protected def createPointsValue(points: Double) : Value  =  PointsValueComponent(points)
  override protected def createMilliMeterValue(mms: Double) : Value =  MilliMeterValueComponent(mms)
  override protected def createCentiMeterValue(cms: Double) : Value =  CentiMeterValueComponent(cms)
  override protected def createInchesValue(inches: Double) : Value  =  InchesValueComponent(inches)
  override protected def createEmValue(ems: Double) : Value         =  EmValueComponent(ems)
  override protected def createExValue(exs: Double) : Value         =  ExValueComponent(exs)
  override protected def createPercentageValue(pers: Double) : Value=  PercentageValueComponent(pers)

}
