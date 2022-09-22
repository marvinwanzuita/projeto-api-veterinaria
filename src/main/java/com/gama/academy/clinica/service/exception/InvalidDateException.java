package com.gama.academy.clinica.service.exception;

public class InvalidDateException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public InvalidDateException(String data){
            super("A data: " + data + ", é inferior a data atual !!!");
        }     
}
