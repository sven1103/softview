package filli.softview.converter

import filli.softview.io.ConverterOutput

import java.nio.file.Path

class ConvertCodeToPumlElements implements ConvertSourceCode {


    def parseSourceCodeFilesFromDir(Path path) {
        return null
    }

    def saveSyntaxAndGraph(ConverterOutput converterOutput) {
        converterOutput.savePumlSyntaxAndGraph(syntax)
    }
}
