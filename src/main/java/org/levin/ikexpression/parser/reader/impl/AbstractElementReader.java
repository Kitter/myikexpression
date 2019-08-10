/*
 * Copyright 2019-2019 [Levin]
 */
package org.levin.ikexpression.parser.reader.impl;

import org.levin.ikexpression.exception.ExpressionParseException;
import org.levin.ikexpression.parser.Element;
import org.levin.ikexpression.parser.reader.ElementReader;
import org.levin.ikexpression.parser.reader.ExpressionReader;

import java.io.IOException;

/**
 * 类AbstractElementReader描述：
 *
 * @author dinglevin
 * @date 2019-08-10 12:47
 */
public abstract class AbstractElementReader implements ElementReader {

    @Override
    public Element read(ExpressionReader reader) {
        try {
            return doRead(reader);
        } catch (IOException ex) {
            throw new ExpressionParseException("Failed to parse expression", ex);
        }
    }

    protected abstract Element doRead(ExpressionReader reader) throws IOException;

    protected static void validateJavaIdentifier(int c, String message) {
        if (!Character.isJavaIdentifierPart(c)) {
            throw new ExpressionParseException(message);
        }
    }

    protected static void validateJavaIdentifierStart(int c, String message) {
        if (!Character.isJavaIdentifierStart(c)) {
            throw new ExpressionParseException(message);
        }
    }

    protected static void validateJavaIdentifier(boolean readStart, int c) {
        if (readStart) {
            validateJavaIdentifierStart(c, "名称开头不能为字符：" + c);
        } else {
            validateJavaIdentifier(c, "名称不能为非法字符：" + c);
        }
    }
}
