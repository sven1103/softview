package filli.softview.core.sourcecodereader

import filli.softview.core.pumlelements.PumlObject

abstract class SourceCodeReader {

    abstract PumlObject parseFileContent(String fileContent, String fileName)

}
