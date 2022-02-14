package com.anath.usersignup.controller.exception;

import java.util.Map;


public class ApiError {
    private int status;
    private Map<String, String> errors;

    public ApiError(final int status, final  Map<String, String> errors) {
        this.status = status;
        this.errors = errors;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(final Map<String, String> errors) {
        this.errors = errors;
    }
}
