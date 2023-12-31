package com.clawson.restful.exceptions;

public class EventNotFoundException extends RuntimeException{
    
    public EventNotFoundException(Long id) {
        super("Could not find event " + id);
    }
}
