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
 * 读取分割符类型
 * @author 林良益，卓诗垚
 * @version 2.0
 * Sep 21, 2008
 */
public class SplitorTypeReader extends AbstractElementReader {

    public static final String SPLITOR_CHAR = "(),";//所有分割符

    /**
     * 从流中读取分割符类型的ExpressionToken
     * @param sr
     * @return
     * @throws IOException
     */
    protected Element doRead(ExpressionReader sr) throws IOException {
        int index = sr.getCurrentIndex();
        int b = sr.read();
        char c = (char) b;
        if (b == -1 || SPLITOR_CHAR.indexOf(c) == -1) {
            throw new ExpressionParseException("不是有效的分割字符");
        }
        return Element.create(Character.toString(c), index, ElementType.SPLITOR);
    }
}
