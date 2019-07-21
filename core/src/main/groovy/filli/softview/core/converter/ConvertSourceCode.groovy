package filli.softview.core.converter

import filli.softview.core.pumlelements.PumlObject

interface ConvertSourceCode {

    def passPumlObjects(List<PumlObject> pumlObjectList)

}