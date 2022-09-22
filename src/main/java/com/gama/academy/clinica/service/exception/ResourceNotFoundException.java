package com.gama.academy.clinica.service.exception;

public class ResourceNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public ResourceNotFoundException(String objectName){
            super("Objeto: " + objectName + ", Não foi encontrado!");
        }     
}
