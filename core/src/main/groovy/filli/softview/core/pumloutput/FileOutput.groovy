package filli.softview.core.pumloutput

import java.nio.file.Path

class FileOutput implements ConverterOutput {

    private Path outputDir

    private final String syntaxFileName = "syntax.puml"

    private final String graphFileName = "graph.png"

    FileOutput(Path outputDir) {
        this.outputDir = outputDir
    }

    def savePumlSyntaxAndGraph(String syntax) {
        saveSyntax(syntax)
        saveGraph(syntax)
    }

    private void saveSyntax(String syntax) {
        File syntaxFile = new File(outputDir.toString(), syntaxFileName)
        syntaxFile.withWriter {
            it.write(syntax)
        }
    }

    private void saveGraph(String syntax) {
        // TODO Use the Puml library to create the graph figure
    }
}
