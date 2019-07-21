package filli.softview.core.elements

class PumlAbstractClass extends PumlObject implements Relatable {

    protected final List<PumlObject> associations = []

    protected final List<PumlInterface> realizations = []

    protected PumlAbstractClass specialization

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
        removeRedundancies()
    }

    void uses(List<PumlObject> pumlObjectList) {
        pumlObjectList.each { this.uses it }
        removeRedundancies()
    }

    void realizes(PumlInterface pumlInterface) {
        this.realizations << pumlInterface
        removeRedundancies()
    }

    void realizes(List<PumlInterface> pumlInterfaceList) {
        pumlInterfaceList.each { this.realizes it }
        removeRedundancies()
    }

    void specializes(PumlAbstractClass pumlAbstractClass) {
        this.specialization = pumlAbstractClass
        removeRedundancies()
    }

    private void removeRedundancies(){
        this.associations.removeAll { PumlObject pumlObject ->
            this.specialization.any { pumlObject.name =~ it.name }
        }
        this.associations.removeAll { PumlObject pumlObject ->
            this.realizations.any { pumlObject.name =~ it.name }
        }
    }
}
