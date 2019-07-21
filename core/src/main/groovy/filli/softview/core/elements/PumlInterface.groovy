package filli.softview.core.elements

class PumlInterface extends PumlObject {

    PumlInterface(String name) {
        super(name)
        this.type = "interface"
    }

    @Override
    String getUmlSyntax() {
        return ""
    }
}
