# Sless Dries Janse (r0627054)
## Implementation

The following Selss Extensions are made:
- [x] Comments
- [x] Merge CSS sheets (imports)
- [x] Mixins
- [x] Nested style rules
- [x] Better Values
- [x] Extend

As an extra, scala documentation is added to all own written classes. This scala documentation is exported to html files and can be found in the [doc folder](doc).


## Extensibility

The project is implemented as a focus on extensibility, each component is constructed out of other compontents. Given the following examples:

* A new css component can easily be added, it only needs the extend the right Base Component. For example, if more specific attribute selectors are required, subclasses can be made of the AttributeSelectorComponent class. These classes have to override the "basic" and "pretty" method, no other classes need to change.

* If new functionallity needs to be added, only the corresponding classes need to implement these functionallity. No other methods needs to change. For example, if a new print method, such as "compile" or "pretty", need to be added all the components will need to handle their own implementation, no other exisiting methods needs to be modified. 


## Extra

Some provided test files are extended with, own written tests. This tests the more in depth functionallity of the features. The following files are extended with self written tests:
* MixinTest
* MergeTest 
* LessNestingTest
* LessVariableTest
* BetterValuesTest

## Better Values
The Better Values extension is implemented as follow. A value can be a basic value or a property specific value.
* The following basic values are considered:
  * AutoValueComponent -> represents the 'auto' value
  * BasicValueComponent -> represents a normal string value
  * EmptyValueComponent -> represents the empty string
  * InheritValueComponent -> represents the 'inherit' value
  * LengthValueComponent -> represents all the values which can be expressed as a length. It contains the case classes for: pixels, points, mm, cm, em, %, inches, picas, ex, ...

* Currently only one property specific value is implemented, but this can be easily extended. Margin is a property specific value and is composed out of the above basic values. The same can be done for other property specific values such as: padding, border, etc.


## Improving original features

Non of the original features were extended.

## Custom Extension

The extension I added was the documentation of my own written classes. The extensive capabilities of Better Values such as pixels, points, mm, em, ... can be seen as an extra extension of the Better Values extension.

## Feedback on the Project 

The project is a nice touch to course because, it deepens your knowledge of the language.
This project consumes, in my opinion, way over the provided 40 hours of the course. Which is a disadvantage because of the amount of work in the Master of Toegepaste Informatica.
