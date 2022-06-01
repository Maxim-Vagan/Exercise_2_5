package ru.maxvagan.exceptions;

public class WrongPasswordException extends RuntimeException{
    public WrongPasswordException(String message) {
        super(message);
        System.out.println("=========   Wrong Password! Be attentive   =========");
    }
}
