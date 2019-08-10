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
 * 读取函数类型
 * @author 林良益，卓诗垚
 * @version 2.0
 * Sep 21, 2008
 */
public class FunctionTypeReader extends AbstractElementReader {
    public static final char START_MARK = '$';//函数开始
    public static final char END_MARK = '(';//函数结束

    /**
     * 从流中读取函数类型的ExpressionToken
     * @param sr
     * @return
     * @throws ExpressionParseException
     * @throws IOException
     */
    protected Element doRead(ExpressionReader sr) throws ExpressionParseException, IOException {
        int index = sr.getCurrentIndex();
        StringBuffer sb = new StringBuffer();
        int b = sr.read();
        if (b == -1 || b != FunctionTypeReader.START_MARK) {
            throw new ExpressionParseException("不是有效的函数开始");
        }

        boolean readStart = true;
        while ((b = sr.read()) != -1) {
            char c = (char) b;
            if (c == FunctionTypeReader.END_MARK) {
                if (sb.length() == 0) {
                    throw new ExpressionParseException("函数名称不能为空");
                }
                sr.reset();
                return Element.create(sb.toString(), index, ElementType.FUNCTION);
            }

            validateJavaIdentifier(readStart, c);
            readStart = false;

            sb.append(c);
            sr.mark(0);
        }

        throw new ExpressionParseException("不是有效的函数结束");
    }
}
