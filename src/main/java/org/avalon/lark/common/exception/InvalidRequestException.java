package org.avalon.lark.common.exception;

public class InvalidRequestException extends RuntimeException {

    public InvalidRequestException() {
        super();
    }

    public InvalidRequestException(String msg) {
        super(msg);
    }
}
