package filli.softview.core.reader

import filli.softview.core.elements.PumlClass
import filli.softview.core.elements.PumlObject
import spock.lang.Shared
import spock.lang.Specification

class JavaGroovyReaderSpec extends Specification{

    @Shared
    String testClass = """
        import filli.tests.SimpleClass;
        import filli.tests.SimpleInterface;
        import filli.tests.AnotherClass;

        class TestClass extends SimpleClass implements SimpleInterface, AnotherInterface {

        }""".stripIndent()

    def 'parse test class and return PumlClass object with 1 association, 1 specialisation and 2 realisation'() {
        given:
        SourceCodeReader parser = new JavaGroovyReader()

        when:
        PumlObject object =  parser.parseFileContent(testClass)

        then:
        assert object.class == PumlClass.class
        assert object.associations.size() == 1
        assert object.specialization
        assert object.realizations.size() == 2

    }

}
