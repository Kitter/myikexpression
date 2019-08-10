/*
 * Copyright 2019-2019 [Levin]
 */
package org.levin.ikexpression.exception;

/**
 * 类ExpressionException描述：
 *
 * @author dinglevin
 * @date 2019-08-10 11:18
 */
public class ExpressionException extends RuntimeException {
    private static final long serialVersionUID = -4667205531162691462L;

    public ExpressionException() {
    }

    public ExpressionException(String message) {
        super(message);
    }

    public ExpressionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpressionException(Throwable cause) {
        super(cause);
    }
}
