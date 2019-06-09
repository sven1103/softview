package filli.softview.parsers

import filli.softview.types.PumlClass
import filli.softview.types.PumlObject

class JavaGroovyParser extends SourceCodeParser{

    private PumlObject pumlObject

    def parseFile(File file) {
        pumlObject = new PumlClass()
    }

    PumlObject getPumlObjectRepresentation() {
        pumlObject
    }
}
