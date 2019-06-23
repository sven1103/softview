package filli.softview.converter

import filli.softview.elements.PumlObject
import filli.softview.output.ConverterOutput


class CreateSyntaxAndGraph implements ConvertSourceCode {

    private List<PumlObject> pumlObjectList

    def saveSyntaxAndGraph(ConverterOutput converterOutput) {
        def syntax = ""
        converterOutput.savePumlSyntaxAndGraph(syntax)
    }

    def passPumlObjects(List<PumlObject> pumlObjectList) {
        this.pumlObjectList = pumlObjectList
    }
}
