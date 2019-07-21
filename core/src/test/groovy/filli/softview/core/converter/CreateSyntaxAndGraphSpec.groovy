package filli.softview.core.converter


import spock.lang.Shared
import spock.lang.Specification


class CreateSyntaxAndGraphSpec extends Specification {

    @Shared
    private List<filli.softview.core.elements.PumlObject> pumlObjectsList = createTestPumlObjects()


    def 'pass pumlobjects source code representations'() {

        given:
        def convertCodeToPumlElements = new CreateSyntaxAndGraph()

        when:
        convertCodeToPumlElements.passPumlObjects(pumlObjectsList)

        then:
        assert convertCodeToPumlElements.pumlObjectList.size() == 2
        assert convertCodeToPumlElements.pumlObjectList.find { it.class == filli.softview.core.elements.PumlClass }
        assert convertCodeToPumlElements.pumlObjectList.find { it.class == filli.softview.core.elements.PumlInterface }
    }


    def 'save puml syntax and create graph, pass both to the converter output'() {

        given:
        def convertCodeToPumlElements = new CreateSyntaxAndGraph()
        filli.softview.core.output.ConverterOutput simpleOutput = Mock()

        when:
        convertCodeToPumlElements.passPumlObjects(pumlObjectsList)
        convertCodeToPumlElements.saveSyntaxAndGraph(simpleOutput)

        then:
        1 * simpleOutput.savePumlSyntaxAndGraph(_ as String)
    }


    private static List<filli.softview.core.elements.PumlObject> createTestPumlObjects() {
        def pumlClass = new filli.softview.core.elements.PumlClass()
        def pumlInterface = new filli.softview.core.elements.PumlInterface()
        return [pumlClass, pumlInterface]
    }


}
