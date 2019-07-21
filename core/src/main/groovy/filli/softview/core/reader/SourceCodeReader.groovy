package filli.softview.core.reader

import filli.softview.core.elements.PumlObject

abstract class SourceCodeReader {

    abstract PumlObject parseFileContent(String fileContent)

}
