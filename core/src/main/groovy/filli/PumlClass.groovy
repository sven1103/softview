package filli

import groovy.transform.CompileStatic

@CompileStatic
class PumlClass extends PumlObject implements Relatable {

    private final List<PumlObject> associations = []

    private final List<PumlInterface> realizations = []

    private PumlClass specialization

    PumlClass(String name) {
        super(name)
    }

    String getUmlSyntax() {
        final StringBuilder builder = new StringBuilder()
        builder.append(getAllAssociationSyntax())
        builder.append(getAllRealizationSyntax())
        builder.toString()
    }

    private String getAllAssociationSyntax() {
        final StringBuilder builder = new StringBuilder()
        associations.each { builder.append("${it.name} <-- $name\n") }
        builder.toString()
    }

    private String getAllRealizationSyntax() {
        final StringBuilder builder = new StringBuilder()
        realizations.each { builder.append("${it.name} <.. $name\n") }
        builder.toString()
    }

    void uses(PumlObject pumlObject) {
        associations << pumlObject
    }

    void realizes(PumlInterface pumlInterface) {
        realizations << pumlInterface
    }

    void specializes(PumlClass pumlClass) {
        specialization = pumlClass
    }
}
