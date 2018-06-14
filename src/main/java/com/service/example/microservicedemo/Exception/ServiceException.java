package com.service.example.microservicedemo.Exception;

import com.service.example.microservicedemo.Constants.StatusConstants;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    private StatusConstants.HttpConstants status;

    public ServiceException(StatusConstants.HttpConstants status) {
        super(status.getDesc(), null);
        this.status = status;
    }
}
