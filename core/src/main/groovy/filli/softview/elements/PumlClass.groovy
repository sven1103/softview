package filli.softview.elements

import groovy.transform.CompileStatic

@CompileStatic
class PumlClass extends PumlAbstractClass {

    PumlClass(String name) {
        super(name)
        this.type = "class"
    }
}
