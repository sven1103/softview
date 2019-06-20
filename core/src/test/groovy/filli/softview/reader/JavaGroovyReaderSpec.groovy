package filli.softview.reader

import filli.softview.types.PumlClass
import filli.softview.types.PumlObject
import spock.lang.Shared
import spock.lang.Specification

class JavaGroovyReaderSpec extends Specification{

    @Shared
    File testClass = new File('src/test/resources/TestClass.java')

    def 'parse test class and return PumlClass object with 1 association, 1 specialisation and 1 realisation'() {
        given:
        SourceCodeReader parser = new JavaGroovyReader()

        when:
        PumlObject object =  parser.parseFileContent(testClass.text)

        then:
        assert object.class == PumlClass.class
        assert object.associations.size() == 1
        assert object.specialization
        assert object.realizations.size() == 2

    }

}