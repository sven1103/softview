package filli

import spock.lang.Specification

class PumlClassSpec extends Specification{

    def "a simple two classes association should look like: ClassA <-- ClassB"() {
        given:
        PumlClass classA = new PumlClass("ClassA")
        PumlClass classB = new PumlClass("ClassB")

        when:
        classB.addAssociationWith(classA)

        then:
        assert classB.getUmlSyntax() == 'ClassA <-- ClassB'
        assert classA.getUmlSyntax() == ''
    }

}
