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
 * 读取数字类型
 * @author 林良益，卓诗垚
 * @version 2.0
 * Sep 21, 2008
 */
public class NumberTypeReader extends AbstractElementReader {
    public static final String NUMBER_CHARS = "01234567890.";//表示数值的字符

    private static final String LONG_MARKS = "lL";//long的结尾标志
    private static final String FLOAT_MARKS = "fF";//float的结尾标志
    private static final String DOUBLE_MARKS = "dD";//double的结尾标志

    /**
     * 从流中读取数字类型的ExpressionToken
     * @param expressionReader
     * @return
     */
    protected Element doRead(ExpressionReader expressionReader) throws IOException {
        int index = expressionReader.getCurrentIndex();
        StringBuilder sb = new StringBuilder();
        int b = -1;
        while ((b = expressionReader.read()) != -1) {
            char c = (char) b;
            if (NUMBER_CHARS.indexOf(c) == -1) {
                if (LONG_MARKS.indexOf(c) >= 0) {
                    if (sb.indexOf(".") >= 0) {//有小数点
                        throw new ExpressionParseException("long类型不能有小数点");
                    }
                    return Element.create(sb.toString(), index, ElementType.LONG);
                } else if (FLOAT_MARKS.indexOf(c) >= 0) {
                    checkDecimal(sb);
                    return Element.create(sb.toString(), index, ElementType.FLOAT);
                } else if (DOUBLE_MARKS.indexOf(c) >= 0) {
                    checkDecimal(sb);
                    return Element.create(sb.toString(), index, ElementType.DOUBLE);
                } else {
                    expressionReader.reset();
                    return createDefaultElement(sb, index);
                }
            }
            sb.append(c);
            expressionReader.mark(0);
        }
        //读到结未
        return createDefaultElement(sb, index);
    }

    private static Element createDefaultElement(StringBuilder sb, int index) {
        if (sb.indexOf(".") >= 0) {//没有结束标志，有小数点，为double
            checkDecimal(sb);
            return Element.create(sb.toString(), index, ElementType.DOUBLE);
        } else {//没有结束标志，无小数点，为int
            return Element.create(sb.toString(), index, ElementType.INT);
        }
    }

    private static void checkDecimal(StringBuilder sb) {
        if (sb.indexOf(".") != sb.lastIndexOf(".")) {
            throw new ExpressionParseException("数字最多只能有一个小数点");
        }
    }
}
