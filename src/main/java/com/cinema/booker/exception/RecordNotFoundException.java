package com.cinema.booker.exception;

public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException(long id, String name) {
        super(name + " with id: " + id + " was not found");
    }
}
