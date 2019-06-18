package filli.softview.parsers

class SourceCodeParserException extends RuntimeException {

    SourceCodeParserException(String message) {
        super(message)
    }

    SourceCodeParserException(String message, Throwable t) {
        super(message, t)
    }

}
