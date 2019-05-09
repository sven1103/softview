# softview
Track your dependency flow.

## motivation

Motivated by Robert Cecil Martin's book _Clean Architecture_, I wanted to create visual representations of my projects' dependency flow. I wanted to see, where I created depencencies of unstable components, and where I need to apply _inversion of control_(IoC) to decouple core business logic from e.g. the persistance layer or controllers. 

I wanted to get rid of this:

<img src="http://theawkwardyeti.com/wp-content/uploads/2018/09/090718_Counterproductive.png" width=400>

Removing the "it kind of feels right" driven programming style. I started to search for tools out there and was surprised, that there is not much around to create a dependency flow graph in UML, given the source code of a project. 

Ok, challenge accepted: let's come up with a small piece of code, that helps out.

## idea

With **softview** I want to create a small command-line application written in [Groovy](http://groovy-lang.org/), that uses the [plantUML](http://plantuml.com) syntax for textual graph representation and its Java API to create visual class diagrams.

