package filli.softview.parsers

import filli.softview.types.PumlObject

abstract class SourceCodeParser {

    abstract PumlObject parseFileContent(String fileContent)

}
