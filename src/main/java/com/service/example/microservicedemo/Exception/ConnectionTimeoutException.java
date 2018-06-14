package com.service.example.microservicedemo.Exception;

import com.service.example.microservicedemo.Constants.StatusConstants;
import lombok.Getter;

@Getter
public class ConnectionTimeoutException extends Exception {

    private StatusConstants.HttpConstants status;

    public ConnectionTimeoutException(StatusConstants.HttpConstants status) {
        super(status.getDesc(), null);
        this.status = status;
    }
}
