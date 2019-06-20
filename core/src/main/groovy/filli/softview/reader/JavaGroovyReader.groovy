package filli.softview.reader

import filli.softview.types.PumlAbstractClass
import filli.softview.types.PumlClass
import filli.softview.types.PumlInterface
import filli.softview.types.PumlObject

class JavaGroovyReader extends SourceCodeReader{

    private String fileContent

    PumlObject parseFileContent(String fileContent) {
        this.fileContent = fileContent
        buildPumlObjectFromFile()
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
        }
    }

    private PumlObject describeAbstractClassDependencies() {

    }

    private PumlObject describeClassDependencies() {
        PumlClass pumlClass = new PumlClass()
        pumlClass.uses(getClassUsages())
        pumlClass.specializes(getAbstractClassSpecialisation())
        pumlClass.realizes(getInterfaceImplementations())
        return pumlClass
    }

    private PumlAbstractClass getAbstractClassSpecialisation() {
        def findSpecialisations = ( fileContent =~ $/class.*extends.[^\{]\w*/$ )
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
        def findSpecialisations = ( fileContent =~ $/class.*implements.(\w+)(,\s*\w+)*/$ )
        def implementationPhrase = $/implements.*/$
        List <PumlInterface> pumlInterfaceList = []
        findSpecialisations.each { matches ->
            def implementations = ( matches[0] =~ implementationPhrase )
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
        def findImportStatements = ( fileContent =~ $/import.*/$)
        def usedClasses = []
        findImportStatements.each { importLine ->
            def listWithClassImport = importLine.split()
            if (listWithClassImport[1]) {
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
        def findDefinitions = (fileContent =~ classDefinitionRegex)
        if (!findDefinitions) {
            throw new SourceCodeReaderException("Could not find a class definition line. Please check the file content!")
        } else if (findDefinitions.size() > 1) {
            throw new SourceCodeReaderException("Found more than one class definition line. Please check the file content!")
        }
        return findDefinitions[0]
    }
}
