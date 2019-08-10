/**
 *
 */
package org.levin.ikexpression.parser.reader.impl;

import org.levin.ikexpression.exception.ExpressionParseException;
import org.levin.ikexpression.parser.Element;
import org.levin.ikexpression.parser.ElementType;
import org.levin.ikexpression.parser.reader.ExpressionReader;

import java.io.IOException;

/**
 * 读取字符窜类型
 * @author 林良益，卓诗垚
 * @version 2.0
 * Sep 21, 2008
 */
public class StringTypeReader extends AbstractElementReader {
    public static final char START_MARK = '"';//字符窜开始标志
    public static final char END_MARK = '"';//字符窜结束标志

    public static final char ESCAPE_MARK = '\\';//转义符号

    /**
     * 从流中读取字符窜类型的ExpressionToken
     * @param sr
     * @return ExpressionToken
     * @throws IOException
     */
    protected Element doRead(ExpressionReader sr) throws IOException {
        int index = sr.getCurrentIndex();
        StringBuilder builder = new StringBuilder();
        int b = sr.read();
        if (b == -1 || b != START_MARK) {
            throw new ExpressionParseException("不是有效的字符窜开始");
        }

        while ((b = sr.read()) != -1) {
            char c = (char) b;
            if (c == ESCAPE_MARK) {//遇到转义字符
                c = getEscapeValue((char) sr.read());
            } else if (c == END_MARK) {//遇到非转义的引号
                return Element.create(builder.toString(), index, ElementType.STRING);
            }
            builder.append(c);
        }
        throw new ExpressionParseException("不是有效的字符串结束");
    }

    /**
     * 可转义字符有\"nt
     * @param c
     * @return
     * @throws ExpressionParseException
     */
    private static char getEscapeValue(char c) throws ExpressionParseException {
        if (c == '\\' || c == '\"') {
            return c;
        } else if (c == 'n') {
            return '\n';
        } else if (c == 'r') {
            return '\r';
        } else if (c == 't') {
            return '\t';
        }
        throw new ExpressionParseException("字符转义出错");
    }
}
