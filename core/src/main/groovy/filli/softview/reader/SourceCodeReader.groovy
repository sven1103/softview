package filli.softview.reader

import filli.softview.elements.PumlObject

abstract class SourceCodeReader {

    abstract PumlObject parseFileContent(String fileContent)

}
