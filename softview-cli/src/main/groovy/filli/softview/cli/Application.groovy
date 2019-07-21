package filli.softview.cli

import groovyjarjarpicocli.CommandLine

class Application {

    static void main(String[] args) throws Exception {
        CommandLine.call(new SoftviewRunner(), args)
    }

}
