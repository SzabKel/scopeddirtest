package com.scopeddirtest;

public class WebDriverInitializerException extends RuntimeException {
    public WebDriverInitializerException() {
    }

    public WebDriverInitializerException(String message) {
        super(message);
    }

    public WebDriverInitializerException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebDriverInitializerException(Throwable cause) {
        super(cause);
    }

    public WebDriverInitializerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
