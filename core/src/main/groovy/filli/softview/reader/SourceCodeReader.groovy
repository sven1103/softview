package filli.softview.reader

import filli.softview.types.PumlObject

abstract class SourceCodeReader {

    abstract PumlObject parseFileContent(String fileContent)

}
