package org.Java.Exception;

public class StorageException extends RuntimeException {
    private final String name;

    public StorageException(String message,String name) {
        super(message);
        this.name = name;
    }
    public StorageException(String message) {
        this(message,null);
    }
}
