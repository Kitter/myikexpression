/*
 * Copyright 2019-2019 [Levin]
 */
package org.levin.ikexpression.exception;

/**
 * 类ExpressionInternalException描述：
 *
 * @author dinglevin
 * @date 2019-08-10 12:22
 */
public class ExpressionInternalException extends ExpressionException {
    public ExpressionInternalException() {
    }

    public ExpressionInternalException(String message) {
        super(message);
    }

    public ExpressionInternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpressionInternalException(Throwable cause) {
        super(cause);
    }
}
