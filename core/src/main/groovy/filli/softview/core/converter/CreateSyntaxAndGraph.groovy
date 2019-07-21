package filli.softview.core.converter

import filli.softview.core.pumlelements.PumlObject
import filli.softview.core.pumloutput.ConverterOutput

class CreateSyntaxAndGraph implements ConvertSourceCode {

    private List<PumlObject> pumlObjectList

    void saveSyntaxAndGraph(ConverterOutput converterOutput) {
        StringBuilder builder = new StringBuilder()
        pumlObjectList.each { PumlObject pumlObject ->
            builder.append(pumlObject.getUmlSyntax())
        }
        converterOutput.savePumlSyntaxAndGraph(builder.toString())
    }

    def passPumlObjects(List<PumlObject> pumlObjectList) {
        this.pumlObjectList = pumlObjectList
    }
}
