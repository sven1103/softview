package filli.softview.core.sourcecodereader

import filli.softview.core.elements.PumlObject

abstract class SourceCodeReader {

    abstract PumlObject parseFileContent(String fileContent)

}
