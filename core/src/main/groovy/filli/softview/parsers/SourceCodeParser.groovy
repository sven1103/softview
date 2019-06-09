package filli.softview.parsers

import filli.softview.types.PumlObject

abstract class SourceCodeParser {

    abstract parseFile(File file)

    abstract PumlObject getPumlObjectRepresentation()

}
