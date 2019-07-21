package filli.softview.cli

import filli.softview.core.converter.CreateSyntaxAndGraph
import filli.softview.core.pumlelements.PumlObject
import filli.softview.core.pumloutput.ConverterOutput
import filli.softview.core.pumloutput.FileOutput
import groovyjarjarpicocli.CommandLine

import java.nio.file.Path
import java.nio.file.Paths
import java.util.concurrent.Callable

@CommandLine.Command(description = "Creates a visual representation of source code dependencies.",
    name = "softview runner", mixinStandardHelpOptions = true, version = "softview cli 1.0.0")
class SoftviewRunner implements Callable<Void> {

    @CommandLine.Parameters(index = "0", description = "A directory path with the source code to analyse.")
    private Path dir

    @CommandLine.Option(names = ["-o", "--pumloutput"], description = "An pumloutput path where softview creates the figure.")
    private Path outputDir = Paths.get(System.getProperty("user.dir"))

    @Override
    Void call() throws Exception {
        println "Parsing source code from directory: $dir ... "
        List<PumlObject> pumlObjectList = PumlParser.parseFromDirectory(dir)
        println("done")

        print "Creating pumloutput..."
        ConverterOutput converterOutput = new FileOutput(outputDir)
        println("done")

        print "Creating visual representation..."
        CreateSyntaxAndGraph createSyntaxAndGraph = new CreateSyntaxAndGraph()
        createSyntaxAndGraph.passPumlObjects(pumlObjectList)
        createSyntaxAndGraph.saveSyntaxAndGraph(converterOutput)
        println("done")
    }
}
