package filli.softview.types

interface Relatable {

    void uses(PumlObject pumlObject)

    void uses(List<PumlObject> pumlObjectList)

    void realizes(PumlInterface pumlInterface)

    void realizes(List<PumlInterface> pumlInterfaceList)

    void specializes(PumlAbstractClass abstractClass)

}