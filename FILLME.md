# Sless
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

* If new functionallity needs to be added, only the corresponding classed need to implement these functionallity. No other methods needs to change. For example, if a new print method such as "compile" or "pretty" need to be added, all the components will need to handle their own implementation, no other exisiting methods needs to be modified. 


## Extra

Write which files, if any, contain extra self-written tests. If you did something extra impressive let us know here as well!

## Better Values

Explain your implementation briefly, if you chose to implement this extension.
* All values representing a length (px, em, pt, mm) all extend the abstract LengthValueComponent class. If another length is introduced, a new class is created for it and it will extend the LengthValueComponent class.


## Improving original features

If you chose to extend some of the original features as an extensions, properly document what you did here.

## Custom Extension

If you came up with your own extension, properly document it here. Explain its
intended behavior and showcase by example.

## Feedback on the Project 

After working on this project for such a long time, you're allowed to vent in this
section. Tell us what you learned or what you would've liked to learn instead,
etc. This does not count toward your grades at all (neither positive nor negative).
