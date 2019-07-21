package filli.softview.core.elements

abstract class PumlObject {

    protected String type

    protected String name

    PumlObject(String name) {
        this.name = name
        this.type = ""
    }
    
    abstract String getUmlSyntax()
}
