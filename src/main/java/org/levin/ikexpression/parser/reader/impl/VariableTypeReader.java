
package org.levin.ikexpression.parser.reader.impl;

import org.levin.ikexpression.exception.ExpressionParseException;
import org.levin.ikexpression.parser.Element;
import org.levin.ikexpression.parser.ElementType;
import org.levin.ikexpression.parser.reader.ExpressionReader;

import java.io.IOException;

/**
 * 读取一个词段
 *
 * @author 林良益，卓诗垚
 * @version 2.0
 * Sep 21, 2008
 */
public class VariableTypeReader extends AbstractElementReader {
    public static final String STOP_CHAR = "+-*/%^<>=&|!?:#$(),[]'\" \r\n\t";//词段的结束符

    public static final String TRUE_WORD = "true";
    public static final String FALSE_WORD = "false";

    public static final String NULL_WORD = "null";

    protected Element doRead(ExpressionReader sr) throws ExpressionParseException, IOException {
        int index = sr.getCurrentIndex();
        String word = readWord(sr);

        if (TRUE_WORD.equals(word) || FALSE_WORD.equals(word)) {
            return Element.create(word, index, ElementType.BOOLEAN);
        } else if (NULL_WORD.equals(word)) {
            return Element.create(word, index, ElementType.NULL);
        } else {
            return Element.create(word, index, ElementType.VARIABLE);
        }
    }

    private String readWord(ExpressionReader sr) throws IOException {
        StringBuffer sb = new StringBuffer();
        boolean readStart = true;
        int b = -1;
        while ((b = sr.read()) != -1) {
            char c = (char) b;
            if (STOP_CHAR.indexOf(c) >= 0 && !readStart) {//单词停止符,并且忽略第一个字符
                sr.reset();
                return sb.toString();
            }

            validateJavaIdentifier(readStart, c);
            readStart = false;

            sb.append(c);
            sr.mark(0);
        }
        return sb.toString();
    }
}
