package filli

interface Relatable {

    void uses(PumlObject pumlObject)

    void realizes(PumlInterface pumlInterface)

    void specializes(PumlAbstractClass abstractClass)

}