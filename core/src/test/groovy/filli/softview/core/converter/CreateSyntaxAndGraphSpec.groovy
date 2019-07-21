package filli.softview.core.converter


import spock.lang.Shared
import spock.lang.Specification


class CreateSyntaxAndGraphSpec extends Specification {

    @Shared
    private List<filli.softview.core.pumlelements.PumlObject> pumlObjectsList = createTestPumlObjects()


    def 'pass pumlobjects source code representations'() {

        given:
        def convertCodeToPumlElements = new CreateSyntaxAndGraph()

        when:
        convertCodeToPumlElements.passPumlObjects(pumlObjectsList)

        then:
        assert convertCodeToPumlElements.pumlObjectList.size() == 2
        assert convertCodeToPumlElements.pumlObjectList.find { it.class == filli.softview.core.pumlelements.PumlClass }
        assert convertCodeToPumlElements.pumlObjectList.find { it.class == filli.softview.core.pumlelements.PumlInterface }
    }


    def 'save puml syntax and create graph, pass both to the converter output'() {

        given:
        def convertCodeToPumlElements = new CreateSyntaxAndGraph()
        filli.softview.core.pumloutput.ConverterOutput simpleOutput = Mock()

        when:
        convertCodeToPumlElements.passPumlObjects(pumlObjectsList)
        convertCodeToPumlElements.saveSyntaxAndGraph(simpleOutput)

        then:
        1 * simpleOutput.savePumlSyntaxAndGraph(_ as String)
    }


    private static List<filli.softview.core.pumlelements.PumlObject> createTestPumlObjects() {
        def pumlClass = new filli.softview.core.pumlelements.PumlClass()
        def pumlInterface = new filli.softview.core.pumlelements.PumlInterface()
        return [pumlClass, pumlInterface]
    }


}
