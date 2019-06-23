package filli.softview.converter

import filli.softview.elements.PumlObject

interface ConvertSourceCode {

    def passPumlObjects(List<PumlObject> pumlObjectList)

}