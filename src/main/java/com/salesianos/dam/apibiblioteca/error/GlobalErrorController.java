package com.salesianos.dam.apibiblioteca.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class GlobalErrorController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LibraryNotFoundException.class)
    public ProblemDetail handleLibraryNotFound(LibraryNotFoundException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );

        problemDetail.setTitle("Biblioteca no encontrada");
        problemDetail.setType(URI.create("http://dam.salesianos-triana.com/library-not-found"));

        return problemDetail;
    }


    @ExceptionHandler(InvalidLibraryDataException.class)
    public ProblemDetail handleInvalidLibrary(InvalidLibraryDataException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );

        problemDetail.setTitle("Datos inválidos");
        problemDetail.setType(URI.create("http://dam.salesianos-triana.com/library-invalid-data"));

        return problemDetail;
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgument(IllegalArgumentException ex) {

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                ex.getMessage()
        );

        problemDetail.setTitle("Solicitud incorrecta");
        problemDetail.setType(URI.create("http://dam.salesianos-triana.com/library-illegal-argument"));

        return problemDetail;
    }

}
