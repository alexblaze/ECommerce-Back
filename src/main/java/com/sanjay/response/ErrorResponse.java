package com.sanjay.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class ErrorResponse {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private LocalDateTime timestamp;
    private boolean error;
    private String message;
    private List<?> objects;

    public ErrorResponse() {

    }

    public ErrorResponse(String message) {
        this.timestamp = LocalDateTime.now();
        this.error = true;
        this.message = message;
    }


    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public ErrorResponse setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public List<?> getObjects() {
        return objects;
    }

    public ErrorResponse setObjects(List<Object> objects) {
        this.objects = objects;
        return this;
    }

    public boolean isError() {
        return error;
    }

    public ErrorResponse setError(boolean error) {
        this.error = error;
        return this;
    }

}

