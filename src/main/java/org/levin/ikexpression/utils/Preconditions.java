/*
 * Copyright 2019-2019 [Levin]
 */
package org.levin.ikexpression.utils;

import org.levin.ikexpression.exception.ExpressionException;
import org.levin.ikexpression.exception.ExpressionParseException;

/**
 * 类Preconditions描述：
 *
 * @author dinglevin
 * @date 2019-08-09 23:08
 */
public class Preconditions {
    private Preconditions() { }

    public static void checkNotNull(Object value, String message) {
        if (value == null) {
            throw new ExpressionException(message);
        }
    }

    public static void checkState(boolean expression, String message) {
        if (!expression) {
            throw new ExpressionException(message);
        }
    }

    public static void checkArgument(boolean expression, String message) {
        if (!expression) {
            throw new ExpressionException(message);
        }
    }
}
