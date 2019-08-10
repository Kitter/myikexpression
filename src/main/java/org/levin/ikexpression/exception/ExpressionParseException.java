/**
 *
 */
package org.levin.ikexpression.exception;

/**
 * 解析ExpressionToken出错时抛出
 * @author 林良益，卓诗垚
 * @version 2.0
 * Sep 21, 2008
 */
public class ExpressionParseException extends ExpressionException {
    private static final long serialVersionUID = 2156367068320450613L;

    public ExpressionParseException() {
        super();
    }

    public ExpressionParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExpressionParseException(String message) {
        super(message);
    }

    public ExpressionParseException(Throwable cause) {
        super(cause);
    }
}
