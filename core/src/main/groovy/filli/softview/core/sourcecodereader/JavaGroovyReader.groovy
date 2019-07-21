package filli.softview.core.sourcecodereader

import filli.softview.core.pumlelements.PumlAbstractClass
import filli.softview.core.pumlelements.PumlClass
import filli.softview.core.pumlelements.PumlInterface
import filli.softview.core.pumlelements.PumlObject

import java.util.regex.Matcher

class JavaGroovyReader extends SourceCodeReader {

    private String fileContent

    private String fileName

    private String classPath

    PumlObject parseFileContent(String fileContent, String fileName) {
        this.fileContent = fileContent
        this.fileName = fileName
        this.classPath = determineClassPath(fileContent)
        buildPumlObjectFromFile()
    }

    private static String determineClassPath(fileContent) {
        def packageDefinitions = (fileContent =~ $/^package\s+[\w.]*/$)
        def packages = []

        packageDefinitions.each { line ->
            packages << line.split()[-1]
        }

        return packages[0]
    }

    private PumlObject buildPumlObjectFromFile() {
        PumlObject pumlObject = determineObjectType()
        switch (pumlObject.class) {
            case PumlClass.class:
                describeClassDependencies()
                break
            case PumlAbstractClass.class:
                describeAbstractClassDependencies()
                break
            case PumlInterface.class:
                describeInterfaceDependencies()
                break
        }
    }

    private PumlObject describeInterfaceDependencies() {
        PumlInterface interfaceClass = new PumlInterface("$classPath.$fileName")
        return interfaceClass
    }

    private PumlObject describeAbstractClassDependencies() {
        PumlAbstractClass abstractClass = new PumlAbstractClass("$classPath.$fileName")
        return abstractClass
    }

    private PumlObject describeClassDependencies() {
        PumlClass pumlClass = new PumlClass("$classPath.$fileName")
        pumlClass.uses(getClassUsages())
        pumlClass.specializes(getAbstractClassSpecialisation())
        pumlClass.realizes(getInterfaceImplementations())
        return pumlClass
    }

    private PumlAbstractClass getAbstractClassSpecialisation() {
        def findSpecialisations = (fileContent =~ $/class.*extends.[^\{]\w*/$)
        List<PumlAbstractClass> specialisations = []
        findSpecialisations.each { line ->
            def heritedClassName = line.split()[-1]
            def pumlAbstractClass = new PumlAbstractClass()
            pumlAbstractClass.name = heritedClassName
            specialisations << pumlAbstractClass
        }
        return specialisations[0]

    }

    private List<PumlInterface> getInterfaceImplementations() {
        def findSpecialisations = (fileContent =~ $/class.*implements.(\w+)(,\s*\w+)*/$)
        def implementationPhrase = $/implements.*/$
        List<PumlInterface> pumlInterfaceList = []
        findSpecialisations.each { matches ->
            def implementations = (matches[0] =~ implementationPhrase)
            implementations.each { String implMatches ->
                def interfaceNames = implMatches.replaceAll("implements", "")
                        .replaceAll(/\s/, "").split(",")
                interfaceNames.each {
                    def pumlInterface = new PumlInterface()
                    pumlInterface.name = it
                    pumlInterfaceList << pumlInterface
                }
            }

        }
        return pumlInterfaceList
    }

    private List<PumlObject> getClassUsages() {
        Matcher findImportStatements = (fileContent =~ $/import\s.*/$)
        def usedClasses = []
        findImportStatements.each { importLine ->
            def listWithClassImport = importLine.split()
            if (listWithClassImport.getAt(1)) {
                def otherPumlClass = new PumlClass()
                otherPumlClass.name = listWithClassImport[1]
                usedClasses << otherPumlClass
            }
        }
        return usedClasses
    }

    private PumlObject determineObjectType() {
        def classDefinitionLine = findClassDefinitionLine()
        switch (classDefinitionLine) {
            case { it.contains('abstract') }:
                new PumlAbstractClass()
                break
            case { it.contains('class') }:
                new PumlClass()
                break
            case { it.contains('interface') }:
                new PumlInterface()
                break
            default:
                throw new SourceCodeReaderException("Could not find any of the keywords ('abstract', 'class' or 'interface')" +
                        "in the class definition line.")
        }
    }

    private String findClassDefinitionLine() {
        final def classDefinitionRegex = /(class.*)|(interface.*)|(abstract.*)/
        Matcher findDefinitions = (fileContent =~ classDefinitionRegex)
        if (!findDefinitions) {
            throw new SourceCodeReaderException("Could not find a class definition line. Please check the file content!")
        }
        return findDefinitions[0][0]
    }
}
