package filli

import spock.lang.Specification

class PumlClassSpec extends Specification{

    def "a simple two classes association should look like: ClassA <-- ClassB"() {
        given:
        PumlClass classA = new PumlClass("ClassA")
        PumlClass classB = new PumlClass("ClassB")

        when:
        classB.uses(classA)

        then:
        assert classB.getUmlSyntax() == 'ClassA <-- ClassB\n'
        assert classA.getUmlSyntax() == ''
    }

    def "a simple class implements interface relation: InterfaceA <|.. ClassB"() {
        given:
        PumlInterface interfaceA = new PumlInterface("InterfaceA")
        PumlClass classB = new PumlClass("ClassB")

        when:
        classB.realizes(interfaceA)

        then:
        assert classB.getUmlSyntax() == 'InterfaceA <|.. ClassB\n'
        assert interfaceA.getUmlSyntax() == ''

    }

    def "a simple specialization relation: AbstractClassA <|-- ClassB"() {
        given:
        PumlAbstractClass classA = new PumlAbstractClass("AbstractClassA")
        PumlClass classB = new PumlClass("ClassB")

        when:
        classB.specializes(classA)

        then:
        assert classB.getUmlSyntax() == 'AbstractClassA <|-- ClassB\n'
        assert classA.getUmlSyntax() == ''

    }

}
