package sless.ast.base.components.rule

import sless.ast.base.components.declaration.DeclarationComponent
import sless.ast.base.components.selector.SelectorComponent
import sless.ast.base.components.selector.combinators.DescendantSelectorComponent
import sless.ast.base.components.selector.constructors.GroupSelectorComponent
import sless.ast.base.components.selector.modifiers.ModifierComponent
import sless.ast.base.components.value.ValueComponent
import sless.ast.base.components.value.basic.BasicValueComponent
import sless.ast.base.components.{BaseComponent, CssComponent, PropertyComponent, RuleOrDeclarationComponent}
import sless.ast.base.enumeration.MarginType

class RuleComponent(val selector: SelectorComponent, var declarations: Seq[RuleOrDeclarationComponent]) extends RuleOrDeclarationComponent {

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
    //must consist of basic components
    val basicDeclarations : Seq[DeclarationComponent] = getBasicDeclarations()

      for(declaration <- basicDeclarations){
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

  def numberOfDeclarationsOfPropertyWithName(propertyName: String) : Int = {
    //in case a rule has multiple declarations of a certain property
    declarations.asInstanceOf[Seq[DeclarationComponent]].filter(dec => dec.hasPropertyName(propertyName)).length
  }

  def hasDeclarationWithPropertyName(propertyName: String) : Boolean = numberOfDeclarationsOfPropertyWithName(propertyName) != 0


  def hasGroupSelectorComponent() : Boolean = selector.isInstanceOf[GroupSelectorComponent]

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
          //has identical selector
            val matchingRightRule : RuleComponent = secondSheet.getRuleOfSelector(this.selector)
            val leftOverDeclarations : Seq[DeclarationComponent] = matchingRightRule.mergeInDeclarations(this.declarations.asInstanceOf[Seq[DeclarationComponent]])
          Seq()
        }else if(this.hasGroupSelectorComponent()){
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
            Seq(new RuleComponent(new GroupSelectorComponent(newSelectors),this.declarations))
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
      if(!this.hasDeclarationWithPropertyName(declaration.getPropertyName())){
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
    //TODO nakijken of dit klopt
    if(this.hasParentSelectorComponent()){
      selector.replaceParentWithSelectorComponent(parentSelector)
    }else{
      new DescendantSelectorComponent(parentSelector,selector)
    }
  }

  /**
    * One rule component with nested selectors can contain multiple "basic" rule components.
    * This method transforms the rule component to a Seq of "basic" rule components.
    * This method is called on the most outer rule component(s) because it currently cannot have a parent component.
    * On the more inner components the "toBasicComponents(parent)" is called.
    * @return
    */
  def toBasicComponents(): Seq[RuleComponent] = {
    //this Seq contains the basic rules and the declarations of the current rule
    toBasicComponentsHelper(selector)
  }

  def toBasicComponents(currentParentSelector: SelectorComponent) : Seq[RuleComponent] = {
    val nextParentValue = replaceParentWithSelectorComponent(currentParentSelector)
    toBasicComponentsHelper(nextParentValue)
  }

  protected def toBasicComponentsHelper(sel: SelectorComponent) : Seq[RuleComponent] = {
    val plainDeclarationsOfCurrentRule : Seq[RuleOrDeclarationComponent] = getPlainDeclarationOfCurrentRule(sel)
    if(plainDeclarationsOfCurrentRule.isEmpty)  getInnerRules(sel) else new RuleComponent(sel,plainDeclarationsOfCurrentRule) +: getInnerRules(sel)
  }

  protected def getPlainDeclarationOfCurrentRule(sel: SelectorComponent): Seq[RuleOrDeclarationComponent] = {
    basicComponentWhereParentsRemoved(sel).filter(rd => rd.isDeclarationComponent())
  }

  protected def getInnerRules(sel: SelectorComponent) : Seq[RuleComponent] = {
    basicComponentWhereParentsRemoved(sel).filter(rd => rd.isRuleComponent()).asInstanceOf[Seq[RuleComponent]]
  }

  private def basicComponentWhereParentsRemoved(sel: SelectorComponent) : Seq[RuleOrDeclarationComponent] = {
    declarations.map(declaration => declaration.toBasicComponents(sel)).flatten
  }


  private def getBasicDeclarations(): Seq[DeclarationComponent] = {
    toBasicComponents().head.declarations.asInstanceOf[Seq[DeclarationComponent]]
  }

  override def containsMarginProperty(): Boolean = {
    declarations.exists(dec => dec.containsMarginProperty())
  }

  def hasParentSelectorComponent() : Boolean = {
    selector.hasParentSelectorComponent()
  }





}
