package com.rbkmoney.adapter.common.state.deserializer;

public class DeserializerException extends RuntimeException {
    public DeserializerException() {
        super();
    }

    public DeserializerException(String message) {
        super(message);
    }

    public DeserializerException(Throwable cause) {
        super(cause);
    }

    public DeserializerException(String message, Throwable cause) {
        super(message, cause);
    }
}
