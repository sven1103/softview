package filli.softview.types

class PumlAbstractClass extends PumlObject implements Relatable{

    private final List<PumlObject> associations = []

    private final List<PumlInterface> realizations = []

    private PumlAbstractClass specialization

    PumlAbstractClass(String name) {
        super(name)
        this.type == "abstract"
    }

    @Override
    String getUmlSyntax() {
        final StringBuilder builder = new StringBuilder()
        builder.append(getAllAssociationSyntax())
        builder.append(getAllRealizationSyntax())
        builder.append(getSpecializationSyntax())
        builder.toString()
    }

    private String getAllAssociationSyntax() {
        final StringBuilder builder = new StringBuilder()
        associations.each { builder.append("${it.name} <-- $name\n") }
        builder.toString()
    }

    private String getAllRealizationSyntax() {
        final StringBuilder builder = new StringBuilder()
        realizations.each { builder.append("${it.name} <|.. $name\n") }
        builder.toString()
    }

    private String getSpecializationSyntax() {
        final StringBuilder builder = new StringBuilder()
        if( specialization ) {
            builder.append("${specialization?.name} <|-- $name\n")
        }
        builder.toString()
    }

    void uses(PumlObject pumlObject) {
        this.associations << pumlObject
    }

    void realizes(PumlInterface pumlInterface) {
        this.realizations << pumlInterface
    }

    void specializes(PumlAbstractClass pumlAbstractClass) {
        this.specialization = pumlAbstractClass
    }
}
