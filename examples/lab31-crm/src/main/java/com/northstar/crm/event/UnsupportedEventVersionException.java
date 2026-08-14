package com.northstar.crm.event;

public class UnsupportedEventVersionException extends RuntimeException {
    public UnsupportedEventVersionException() {
        super("Unsupported CustomerEvent version");
    }
}