package filli.softview.core.reader

class SourceCodeReaderException extends RuntimeException {

    SourceCodeReaderException(String message) {
        super(message)
    }

    SourceCodeReaderException(String message, Throwable t) {
        super(message, t)
    }

}
