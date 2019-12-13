package sless.ast.base.components.rule

import sless.ast.base.components.declaration.DeclarationComponent
import sless.ast.base.components.selector.SelectorComponent
import sless.ast.base.components.selector.constructors.GroupSelectorComponent
import sless.ast.base.components.selector.modifiers.ModifierComponent
import sless.ast.base.components.value.ValueComponent
import sless.ast.base.components.value.basic.BasicValueComponent
import sless.ast.base.components.{BaseComponent, CssComponent, PropertyComponent}
import sless.ast.base.enumeration.MarginType

import scala.collection.GenTraversableOnce


class RuleComponent(val selector: SelectorComponent, var declarations: Seq[DeclarationComponent]) extends BaseComponent {

  override def basic(): String = {
    var result: String = selector.basic() + "{"
    declarations.foreach(declaration => result += declaration.basic() )
    result + "}"
  }

  override def pretty(spaces: Int): String = {
    var result: String = selector.pretty(spaces) + " {\n"
    declarations.foreach(declaration => result += " "*spaces + declaration.pretty(spaces) + "\n" )
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
    val marginAggregate : String = margins.map(valCom => valCom.getStringValue()).mkString(" ")
    var newDeclarations: Seq[DeclarationComponent] = Seq()
    for(declaration <- declarations){
      if(declaration.containsMarginProperty()){
        if(isFirst){
          //the first margin property is replaces with the full margin specification
          isFirst = false
          newDeclarations =  newDeclarations:+ new DeclarationComponent(new PropertyComponent("margin"),new BasicValueComponent(marginAggregate))
        }
      }else {
        newDeclarations = newDeclarations:+ declaration
      }
    }
    new RuleComponent(selector,newDeclarations)
  }

  def numberOfDeclarationsOfPropertyWithName(propertyName: String) : Int = {
    //in case a rule has multiple declarations of a certain property
    declarations.filter(dec => dec.hasPropertyName(propertyName)).length
  }

  def hasDeclarationWithPropertyName(propertyName: String) : Boolean = numberOfDeclarationsOfPropertyWithName(propertyName) != 0


  def hasGroupSelectorComponent() : Boolean = selector.isInstanceOf[GroupSelectorComponent]

  //def hasModifierComponent() : Boolean = s.isInstanceOf[ModifierComponent]

  //-----------------------
  //----- EXTEND ----------
  //-----------------------

  def replaceGivenSelectorWith(oldSelector: SelectorComponent, newSelector: SelectorComponent): Rule ={
    // This can be compared because selectorComponent leaf classes are case classes
    if(selector == oldSelector){
      new Rule(newSelector, declarations)
    }else {
      this
    }
  }

  def extendSelectorReplacement(css: CssComponent): CssComponent = selector.extendSelectorReplacement(css)


  //----------------------
  //------- MERGE --------
  //----------------------

  def hasSameSelector(sel: Selector) : Boolean = sel == this.selector

  def mergeInSheet(secondSheet: CssComponent): Seq[RuleComponent] = {
        if(secondSheet.hasSameSelector(this.selector)){
          //has identical group selector
            val matchingRightRule : RuleComponent = secondSheet.getRuleOfSelector(this.selector)
            val leftOverDeclarations : Seq[DeclarationComponent] = matchingRightRule.mergeInDeclarations(this.declarations)
            if(leftOverDeclarations.isEmpty){
              Seq()
            } else {
              Seq(new RuleComponent(this.selector,leftOverDeclarations))
            }
        }else if(this.hasGroupSelectorComponent()){
          //When this is a group selector component


        } else {
          //Has no other occurrences
          Seq(this)
        }






      //}
  }

  def addDeclaration(declaration: DeclarationComponent) : Unit = {
     this.declarations =  this.declarations ++ Seq(declaration)
  }

  def mergeInDeclarations(newDeclarations: Seq[DeclarationComponent]) : Seq[DeclarationComponent] = {
    var uniqueDeclarations : Seq[DeclarationComponent] = Seq()
    for(declaration <- newDeclarations){
      if(!this.hasDeclarationWithPropertyName(declaration.getStringValue())){
        //if it has not a declaration, just add the declaration to it
        this.addDeclaration(declaration)
      }else {
        uniqueDeclarations = uniqueDeclarations ++ Seq(declaration)
      }
    }
    uniqueDeclarations
  }


}
