package filli

abstract class PumlObject implements Relatable{

    protected final String name

    PumlObject(String name) {
        this.name = name
    }

    abstract String getUmlSyntax()
}
