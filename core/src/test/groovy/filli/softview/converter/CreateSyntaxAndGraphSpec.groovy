package filli.softview.converter

import filli.softview.elements.PumlClass
import filli.softview.elements.PumlInterface
import filli.softview.elements.PumlObject
import filli.softview.output.ConverterOutput
import spock.lang.Shared
import spock.lang.Specification


class CreateSyntaxAndGraphSpec extends Specification {

    @Shared
    private List<PumlObject> pumlObjectsList = createTestPumlObjects()


    def 'pass pumlobjects source code representations'() {

        given:
        def convertCodeToPumlElements = new CreateSyntaxAndGraph()

        when:
        convertCodeToPumlElements.passPumlObjects(pumlObjectsList)

        then:
        assert convertCodeToPumlElements.pumlObjectList.size() == 2
        assert convertCodeToPumlElements.pumlObjectList.find { it.class == PumlClass }
        assert convertCodeToPumlElements.pumlObjectList.find { it.class == PumlInterface }
    }


    def 'save puml syntax and create graph, pass both to the converter output'() {

        given:
        def convertCodeToPumlElements = new CreateSyntaxAndGraph()
        ConverterOutput simpleOutput = Mock()

        when:
        convertCodeToPumlElements.passPumlObjects(pumlObjectsList)
        convertCodeToPumlElements.saveSyntaxAndGraph(simpleOutput)

        then:
        1 * simpleOutput.savePumlSyntaxAndGraph(_ as String)
    }


    private static List<PumlObject> createTestPumlObjects() {
        def pumlClass = new PumlClass()
        def pumlInterface = new PumlInterface()
        return [pumlClass, pumlInterface]
    }


}
