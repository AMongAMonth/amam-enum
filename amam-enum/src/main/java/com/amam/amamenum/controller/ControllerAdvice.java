package com.amam.amamenum.controller;

import com.amam.amamenum.exception.CustomException;
import com.amam.amamenum.exception.ErrorCode;
import com.amam.amamenum.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.security.sasl.AuthenticationException;
import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.util.Enumeration;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> exceptionHandler(CustomException e, HttpServletRequest req) {
        String resquestUrl = req.getRequestURI();
        ErrorResponse errorResponse = new ErrorResponse(e, req.getRequestURI());
        return new ResponseEntity<>(errorResponse, e.getCodable().getStatus());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> exceptionTest(Exception e, HttpServletRequest req) throws Exception {
        e.printStackTrace();
        if (e instanceof AccessDeniedException || e instanceof AuthenticationException) {
            throw e;
        }
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> bindingTest(MethodArgumentNotValidException e, HttpServletRequest req) {
        log.info("되는가요");
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private String getParams(HttpServletRequest req) {
        StringBuilder params = new StringBuilder();
        Enumeration<String> keys = req.getParameterNames();
        while(keys.hasMoreElements()) {
            String key = keys.nextElement();
            params.append("- ").append(key).append(" : ").append(req.getParameter(key)).append('\n');
        }
        return  params.toString();
    }
}
