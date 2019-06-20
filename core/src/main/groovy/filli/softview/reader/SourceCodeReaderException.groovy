package filli.softview.reader

class SourceCodeReaderException extends RuntimeException {

    SourceCodeReaderException(String message) {
        super(message)
    }

    SourceCodeReaderException(String message, Throwable t) {
        super(message, t)
    }

}
