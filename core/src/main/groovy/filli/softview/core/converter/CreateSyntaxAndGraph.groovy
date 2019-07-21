package filli.softview.core.converter

import filli.softview.core.pumlelements.PumlObject
import filli.softview.core.pumloutput.ConverterOutput

class CreateSyntaxAndGraph implements ConvertSourceCode {

    private List<PumlObject> pumlObjectList

    void saveSyntaxAndGraph(ConverterOutput converterOutput) {
        StringBuilder builder = new StringBuilder()
        writePumlEntryLine(builder)
        pumlObjectList.each { PumlObject pumlObject ->
            builder.append(pumlObject.getUmlSyntax())
        }
        writePumlFinishLine(builder)
        converterOutput.savePumlSyntaxAndGraph(builder.toString())
    }

    private static void writePumlEntryLine(StringBuilder builder) {
        builder.append("@startuml\n")
    }

    private static void writePumlFinishLine(StringBuilder builder) {
        builder.append("@enduml")
    }


    def passPumlObjects(List<PumlObject> pumlObjectList) {
        this.pumlObjectList = pumlObjectList
    }
}
