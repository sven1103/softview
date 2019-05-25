package filli

abstract class PumlObject {

    private final String name

    PumlObject(String name) {
        this.name = name
    }

    abstract String getUmlSyntax()
}
