package sless.ast.base.components.rule

import sless.ast.base.components.declaration.DeclarationComponent
import sless.ast.base.components.selectors.SelectorComponent
import sless.ast.base.components.{BaseComponent, CssComponent, PropertyComponent, ValueComponent}
import sless.ast.base.enumeration.MarginType


class RuleComponent(val s: SelectorComponent, val declarations: Seq[DeclarationComponent]) extends BaseComponent {




  override def compile(sheet: CssComponent): String = {
    var result: String = s.compile(sheet) + "{"
    declarations.foreach(declaration => result += declaration.compile(sheet) )
    result + "}"
  }

  override def pretty(sheet: CssComponent, spaces: Int): String = {
    var result: String = s.pretty(sheet,spaces) + " {\n"
    declarations.foreach(declaration => result += " "*spaces + declaration.pretty(sheet,spaces) + "\n" )
    result + "}"
  }

  def hasNoDeclarations: Boolean = {
    declarations.isEmpty
  }

  def hasDeclarations: Boolean = {
    declarations.nonEmpty
  }


  //------------------------------------
  //--------- MARGINS METHODS ----------
  //------------------------------------

  def aggregateMargins(): (Boolean,RuleComponent) = {
    val (hasAll,margins) : (Boolean,Array[ValueComponent])  = hasAllMarginPositions
    if(hasAll){
      (true,replaceMargins(margins))
    } else {
      (false,this)
    }
  }

  private def hasAllMarginPositions: (Boolean,Array[ValueComponent]) = {
    //(top,right,bottom,left)
    val boolResult : Array [Boolean] =   new Array[Boolean](4)
    val marginValues : Array[ValueComponent] = new Array[ValueComponent](4)
      for(declaration <- declarations){
        if(declaration.containsMarginProperty()){
          var index : Int = 0
            declaration.getMarginPosition() match {
              case MarginType.top    => index = 0
              case MarginType.right  => index = 1
              case MarginType.bottom => index = 2
              case MarginType.left   => index = 3
            }
          boolResult(index)   = true
          marginValues(index) = declaration.value
        }
      }
    (boolResult.forall(p => p), marginValues)
  }

  private def replaceMargins(margins: Array[ValueComponent]): RuleComponent = {
    var isFirst : Boolean= true
    val marginAggregate : String = margins.map(valCom => valCom.value).mkString(" ")
    var newDeclarations: Seq[DeclarationComponent] = Seq()
    for(declaration <- declarations){
      if(declaration.containsMarginProperty()){
        if(isFirst){
          //the first margin property is replaces with the full margin specification
          isFirst = false
          newDeclarations =  newDeclarations:+ new DeclarationComponent(new PropertyComponent("margin"),new ValueComponent(marginAggregate))
        }
      }else {
        newDeclarations = newDeclarations:+ declaration
      }
    }
    new RuleComponent(s,newDeclarations)
  }

  def numberOfDeclarationsOfPropertyWithName(propertyName: String) : Int = {
    //in case a rule has multiple declarations of a certain property
    declarations.filter(dec => dec.hasPropertyName(propertyName)).length
  }
}
