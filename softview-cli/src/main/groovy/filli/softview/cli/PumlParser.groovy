package filli.softview.cli

import filli.softview.core.pumlelements.PumlObject
import filli.softview.core.sourcecodereader.JavaGroovyReader
import filli.softview.core.sourcecodereader.SourceCodeReader
import groovy.io.FileType

import java.nio.file.Path

class PumlParser {

    static List<PumlObject> parseFromDirectory(Path dir) {
        List<Path> fileList = collectAllFilesFromDir(dir)
        List<PumlObject> pumlObjectList = []
        fileList.each { file ->
            SourceCodeReader reader = getReaderForFileType(file)
            println "Parsing file $file"
            pumlObjectList << reader.parseFileContent(file.text, getFileNameWithoutExtension(file))
        }
        return pumlObjectList
    }

    private static String getFileNameWithoutExtension(Path file) {
        file.fileName.toString().take(file.fileName.toString().lastIndexOf("."))
    }

    private static List<Path> collectAllFilesFromDir(Path dir) {
        def list = []
        dir.eachFileRecurse(FileType.FILES) { list << it }
        return list
    }

    private static SourceCodeReader getReaderForFileType(Path file) {
        SourceCodeReader reader
        switch(file.fileName.toString()) {
            case { it.endsWith('java') || it.endsWith('groovy') }:
                reader = new JavaGroovyReader()
                break
        }
        return reader
    }
}
