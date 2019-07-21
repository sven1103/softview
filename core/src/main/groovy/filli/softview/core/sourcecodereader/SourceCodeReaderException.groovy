package filli.softview.core.sourcecodereader

class SourceCodeReaderException extends RuntimeException {

    SourceCodeReaderException(String message) {
        super(message)
    }

    SourceCodeReaderException(String message, Throwable t) {
        super(message, t)
    }

}
