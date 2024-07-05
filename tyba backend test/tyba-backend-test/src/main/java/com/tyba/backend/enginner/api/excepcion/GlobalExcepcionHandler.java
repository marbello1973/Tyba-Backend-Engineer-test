package com.tyba.backend.enginner.api.excepcion;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExcepcionHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handlerSQLIntegrityConstraintViolationException(DataIntegrityViolationException ex, WebRequest request){
        Map<String, Object> body = new HashMap<>();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        body.put("timestamp", new Date());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Data Integrity Violation");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false));

        //Obtener la URI de la solicitud
        String uri = request.getDescription(false);
        if(uri != null && uri.contains("uri=")){
            uri = uri.substring(uri.lastIndexOf("uri=") + 4);
        }

        String errorUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(uri + "/" + "{status}")
                .buildAndExpand(status.value())
                .toUriString();
        body.put("url", errorUrl);

        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity tratamientoError500RuntimeException(RuntimeException exception, WebRequest request){
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", new Date());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", exception.getMessage());
        body.put("path", request.getDescription(false));

        //Obtener la URI de la solicitud
        String uri = request.getDescription(false);
        if(uri != null && uri.contains("uri=")){
            uri = uri.substring(uri.lastIndexOf("uri=") + 4);
        }

        //Construir la url dinamica
        String errorUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(uri + "/{status}")
                .buildAndExpand(HttpStatus.BAD_REQUEST.value())
                .toUriString();
        body.put("url", errorUrl);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

}
