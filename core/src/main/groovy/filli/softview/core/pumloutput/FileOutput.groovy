package filli.softview.core.pumloutput

import java.nio.file.Path

class FileOutput implements ConverterOutput {

    private Path outputDir

    FileOutput(Path outputDir) {
        this.outputDir = outputDir
    }

    def savePumlSyntaxAndGraph(String syntax) {
        return null
    }
}
