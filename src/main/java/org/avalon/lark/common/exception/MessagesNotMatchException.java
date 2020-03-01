package org.avalon.lark.common.exception;

public class MessagesNotMatchException extends RuntimeException {

    private static final long serialVersionUID = 2723478410410701149L;

    public MessagesNotMatchException() {
        super();
    }

    public MessagesNotMatchException(String message) {
        super(message);
    }
}
