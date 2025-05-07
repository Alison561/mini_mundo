package com.example.mini_mundo.Exceptions;

public class NaoEcontradoException extends RuntimeException{

    public NaoEcontradoException(String mensagem){
        super(mensagem);
    }
}
