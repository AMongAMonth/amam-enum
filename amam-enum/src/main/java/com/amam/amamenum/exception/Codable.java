package com.amam.amamenum.exception;

import org.springframework.http.HttpStatus;

public interface Codable {

    String getMessage();
    HttpStatus getStatus();
    boolean getIsNotify();

}
