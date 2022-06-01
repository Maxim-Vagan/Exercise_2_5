package ru.maxvagan.exceptions;

public class WrongLoginException extends RuntimeException{
    public WrongLoginException(String message) {
        super(message);
        System.out.println("=========   Wrong Login! Try next time   =========");
    }
}

