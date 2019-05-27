package filli

import groovy.transform.CompileStatic

@CompileStatic
class PumlClass extends PumlObject{

    private final List<PumlObject> associations = []

    PumlClass(String name) {
        super(name)
    }

    String getUmlSyntax() {
        final StringBuilder builder = new StringBuilder()
        associations.each { builder.append("${it.name} <-- $name\n") }
        builder.toString()
    }

    void addAssociationWith(PumlObject object) {
        associations << object
    }
}
