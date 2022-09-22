package com.gama.academy.clinica.service.exception;

public class ControllerNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public ControllerNotFoundException(String objectName){
            super("Objeto: " + objectName + ", Não foi encontrado!");
        }     
}
