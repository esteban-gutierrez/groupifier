package com.bestsecret.groupifier.populator;

public class PopulatorException extends Exception {
    public PopulatorException(String errorMessage) {
        super(errorMessage);
    }

    public PopulatorException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
