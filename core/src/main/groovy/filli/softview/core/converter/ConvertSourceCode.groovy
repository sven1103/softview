package filli.softview.core.converter

import filli.softview.core.elements.PumlObject

interface ConvertSourceCode {

    def passPumlObjects(List<PumlObject> pumlObjectList)

}