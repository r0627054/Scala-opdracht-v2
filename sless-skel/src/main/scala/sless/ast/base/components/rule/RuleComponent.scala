package sless.ast.base.components.rule

import sless.ast.base.components.declaration.DeclarationComponent
import sless.ast.base.components.selector.SelectorComponent
import sless.ast.base.components.selector.combinators.DescendantSelectorComponent
import sless.ast.base.components.selector.constructors.GroupSelectorComponent
import sless.ast.base.components.value.ValueComponent
import sless.ast.base.components.value.basic.BasicValueComponent
import sless.ast.base.components.{ CssComponent, PropertyComponent, RuleOrDeclarationComponent}
import sless.ast.base.enumeration.MarginType

/**
  * A rule component contains a selector and a sequence of RuleOrDeclarationComponents. This is because a rule component
  * can contain nested rules.
  * @param selector The selector of the rule.
  * @param declarations The declarations of the rule.
  */
class RuleComponent(val selector: SelectorComponent, var declarations: Seq[RuleOrDeclarationComponent]) extends RuleOrDeclarationComponent {

  /**
    * The basic string is returned. This string contains the selector with all the declaration between curly brackets.
    * @return The basic print string of the component.
    */
  override def basic(): String = {
    var result: String = selector.basic() + "{"
    declarations.foreach(declaration => result += declaration.basic() )
    result + "}"
  }

  /**
    * The pretty string is returned. The string contains the selector with all the declaration on a new line and preceded
    * by the given number of spaces.
    * @param spaces The number of spaces placed before a declaration.
    * @return The pretty print string of the component
    */
  override def pretty(spaces: Int): String = {
    var result: String = selector.pretty(spaces) + " {\n"
    declarations.foreach(declaration => result += " "*spaces + declaration.pretty(spaces) + "\n" )
    result + "}"
  }

  /**
    * Returns whether or not the rule component contains declarations.
    * @return True when the sequence is empty; otherwise false.
    */
  def hasNoDeclarations: Boolean = {
    declarations.isEmpty
  }

  /**
    * Returns whether or not the rule component contains declarations.
    * @return True when the sequence is non empty; otherwise false.
    */
  def hasDeclarations: Boolean = {
    declarations.nonEmpty
  }


  //------------------------------------
  //--------- MARGINS METHODS ----------
  //------------------------------------

  /**
    * Returns whether the rule declarations can be aggregated to one declaration.
    * The aggregated rule is also returned in the rule.
    * @return The aggregated rule and whether the rule could be aggregated.
    */
  def aggregateMargins(): (Boolean,RuleComponent) = {
     val (hasAll,margins) : (Boolean,Array[ValueComponent])  = hasAllMarginPositions
     if(hasAll){
       (true,replaceMargins(margins))
     } else {
       (false,this)
     }
  }

  /**
    * Checks whether the rule contains all the margin declarations.
    * The first element of the tuple returns whether all the margin declarations where present.
    * The second element is are the value of the margin the following order: (top,right,bottom,left).
    * @return The first and second element as described above.
    */
  private def hasAllMarginPositions: (Boolean,Array[ValueComponent]) = {
    //(top,right,bottom,left)

    val boolResult : Array [Boolean] =   new Array[Boolean](4)
    val marginValues : Array[ValueComponent] = new Array[ValueComponent](4)
    //must consist of basic components
    val basicDeclarations : Seq[DeclarationComponent] = getBasicDeclarations

      for(declaration <- basicDeclarations){
        if(declaration.containsMarginProperty()){
          var index : Int = 0
            declaration.getMarginPosition match {
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

  /**
    * Replaces the margin declarations with the one rule margin declaration with the given margin values.
    * @param margins The array of margin declarations.
    * @return The new rule which contains the aggregated margin declaration.
    */
  private def replaceMargins(margins: Array[ValueComponent]): RuleComponent = {
    var isFirst : Boolean= true
    val marginAggregate : String = margins.map(valCom => valCom.getStringValue).mkString(" ")
    var newDeclarations: Seq[DeclarationComponent] = Seq()
    for(declaration <- declarations.asInstanceOf[Seq[DeclarationComponent]]){
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

  /**
    * Returns the number of declarations the rule has of a property with the given name.
    * @param propertyName The name of the property of which the occurrence has to be counted.
    * @return The number of declarations the rule has of a property with the given name.
    */
  def numberOfDeclarationsOfPropertyWithName(propertyName: String) : Int = {
    //in case a rule has multiple declarations of a certain property
    declarations.asInstanceOf[Seq[DeclarationComponent]].count(dec => dec.hasPropertyName(propertyName))
  }

  /**
    * Checks whether the rule has at least one declaration with the given property name.
    * @param propertyName  The name of the property of which the existence has to be checked.
    * @return True when a declaration exists; otherwise false.
    */
  def hasDeclarationWithPropertyName(propertyName: String) : Boolean = numberOfDeclarationsOfPropertyWithName(propertyName) != 0

  /**
    * Checks whether the selector is a group selector.
    * @return True when the selector is a group selector; otherwise false.
    */
  def hasGroupSelectorComponent: Boolean = selector.isInstanceOf[GroupSelectorComponent]

  //-----------------------
  //----- EXTEND ----------
  //-----------------------

  /**
    * Replaces the given selector with the new selector.
    * The selector is only replaced if the given selector equals the current selector of the rule.
    * @param oldSelector The old selector which needs to be replaced.
    * @param newSelector The new selector, to which the old selector will be set.
    * @return The new rule where the selector is replaced if the old selector equals the current selector.
    */
  def replaceGivenSelectorWith(oldSelector: SelectorComponent, newSelector: SelectorComponent): RuleComponent ={
    // This can be compared because selectorComponent leaf classes are case classes
    if(selector == oldSelector){
      new Rule(newSelector, declarations)
    }else {
      this
    }
  }

  /**
    * This method will replace all extend selectors.
    * @param css The css file in which the extend selector needs to be replaced.
    * @return The css file in which the extend selector is replaced.
    */
  def extendSelectorReplacement(css: CssComponent): CssComponent = selector.extendSelectorReplacement(css)

  //----------------------
  //------- MERGE --------
  //----------------------

  /**
    * Checks whether the selector of this rule equals the given selector.
    * @param sel the selector to which it will be compared.
    * @return True when both selectors are equal; otherwise false.
    */
  def hasSameSelector(sel: Selector) : Boolean = sel == this.selector


  /**
    * This will will merge this rule component in the given css sheet. A sequence of rule components which are not merged in
    * already existing rule components of the second sheet are returned.
    *
    * @param secondSheet The sheet in which this component needs to be merged.
    * @return A sequence of rule components which are not merged in already existing rule components of the second sheet are returned.
    */
  def mergeInSheet(secondSheet: CssComponent): Seq[RuleComponent] = {
        if(secondSheet.hasSameSelector(this.selector)){
          //has identical selector
            val matchingRightRule : RuleComponent = secondSheet.getRuleOfSelector(this.selector)
            matchingRightRule.mergeInDeclarations(this.declarations.asInstanceOf[Seq[DeclarationComponent]])
          Seq()
        }else if(this.hasGroupSelectorComponent){
          //When this is a group selector component
          val castedGroup : GroupSelectorComponent = selector.asInstanceOf[GroupSelectorComponent]
          val allSelectors : Seq[SelectorComponent] = castedGroup.selectors
          var newSelectors : Seq[SelectorComponent] = Seq()
          for(currentSelector <- allSelectors) {
            //iterate over all selectors in group
             if(secondSheet.hasSameSelector(currentSelector)){
               //second sheet has the same selector as the current selector in the group
               val matchingRightRule : RuleComponent = secondSheet.getRuleOfSelector(currentSelector)
               matchingRightRule.mergeInDeclarations(this.declarations.asInstanceOf[Seq[DeclarationComponent]])
             } else {
               newSelectors = newSelectors :+ currentSelector
             }
          }
          if(newSelectors.nonEmpty){
            Seq(new RuleComponent( GroupSelectorComponent(newSelectors),this.declarations))
          }else {
            Seq()
          }
        } else {
          Seq(this)
        }

  }

  def addDeclaration(declaration: DeclarationComponent) : Unit = {
     this.declarations =  this.declarations ++ Seq(declaration)
  }

  def mergeInDeclarations(newDeclarations: Seq[DeclarationComponent]) : Seq[DeclarationComponent] = {
    var uniqueDeclarations : Seq[DeclarationComponent] = Seq()
    for(declaration <- newDeclarations){
      if(!this.hasDeclarationWithPropertyName(declaration.getPropertyName)){
        //if it has not a declaration, just add the declaration to it
        this.addDeclaration(declaration)
      }else {
        uniqueDeclarations = uniqueDeclarations ++ Seq(declaration)
      }
    }
    uniqueDeclarations
  }

  //---------------------------------------
  //--------- NESTED STYLE RULES ----------
  //---------------------------------------

  def replaceParentWithSelectorComponent(parentSelector: SelectorComponent) : SelectorComponent = {
    if(this.hasParentSelectorComponent){
      selector.replaceParentWithSelectorComponent(parentSelector)
    }else{
      DescendantSelectorComponent(parentSelector,selector)
    }
  }

  /**
    * One rule component with nested selectors can contain multiple "basic" rule components.
    * This method transforms the rule component to a Seq of "basic" rule components.
    * This method is called on the most outer rule component(s) because it currently cannot have a parent component.
    * On the more inner components the "toBasicComponents(parent)" is called.
    * @return The Seq of basic RuleComponents with their basic DeclarationComponents.
    */
  def toBasicComponents: Seq[RuleComponent] = {
    //this Seq contains the basic rules and the declarations of the current rule
    toBasicComponentsHelper(selector)
  }

  /**
    * Returns a sequence of rule components only existing of basic components.
    * A basic rule component only has a sequence of declaration components and no other nested rule components.
    * @param currentParentSelector the currentParentSelector of the component.
    * @return The Seq of basic RuleComponents with their basic DeclarationComponents.
    */
  def toBasicComponents(currentParentSelector: SelectorComponent) : Seq[RuleComponent] = {
    val nextParentValue = replaceParentWithSelectorComponent(currentParentSelector)
    toBasicComponentsHelper(nextParentValue)
  }

  /**
    * This function is a helper function which contains the main logic of the both toBasicComponents methods.
    * @param sel The selector component which will be resolved as a parent.
    * @return The Seq of basic RuleComponents with their basic DeclarationComponents.
    */
  protected def toBasicComponentsHelper(sel: SelectorComponent) : Seq[RuleComponent] = {
    val plainDeclarationsOfCurrentRule : Seq[RuleOrDeclarationComponent] = getPlainDeclarationOfCurrentRule(sel)
    if(plainDeclarationsOfCurrentRule.isEmpty)  getInnerRules(sel) else new RuleComponent(sel,plainDeclarationsOfCurrentRule) +: getInnerRules(sel)
  }

  /**
    * Returns only a sequence of Declaration components of the rule.
    * This sequence does not contain any nested rule component.
    *
    * @param sel The selector component which will be resolved as a parent.
    * @return The Seq of basic Declaration components.
    */
  protected def getPlainDeclarationOfCurrentRule(sel: SelectorComponent): Seq[RuleOrDeclarationComponent] = {
    basicComponentWhereParentsRemoved(sel).filter(rd => rd.isDeclarationComponent)
  }

  protected def getInnerRules(sel: SelectorComponent) : Seq[RuleComponent] = {
    basicComponentWhereParentsRemoved(sel).filter(rd => rd.isRuleComponent).asInstanceOf[Seq[RuleComponent]]
  }

  private def basicComponentWhereParentsRemoved(sel: SelectorComponent) : Seq[RuleOrDeclarationComponent] = {
    declarations.flatMap(declaration => declaration.toBasicComponents(sel))
  }


  private def getBasicDeclarations: Seq[DeclarationComponent] = {
    toBasicComponents.head.declarations.asInstanceOf[Seq[DeclarationComponent]]
  }

  override def containsMarginProperty(): Boolean = {
    declarations.exists(dec => dec.containsMarginProperty())
  }

  def hasParentSelectorComponent: Boolean = {
    selector.hasParentSelectorComponent
  }





}
