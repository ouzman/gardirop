package com.oguzhanuzman.github.gardirop.exception.aws;

import com.oguzhanuzman.github.gardirop.exception.base.RestException;

public class AWSException extends RestException {
    public AWSException(String message) {
        super(String.format("AWS Error: %s", message));
    }

    public AWSException(String message, Throwable cause) {
        super(message, cause);
    }
}
