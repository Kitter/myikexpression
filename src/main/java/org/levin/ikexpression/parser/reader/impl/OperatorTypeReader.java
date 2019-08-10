/**
 *
 */
package org.levin.ikexpression.parser.reader.impl;

import org.levin.ikexpression.exception.ExpressionParseException;
import org.levin.ikexpression.parser.Element;
import org.levin.ikexpression.parser.ElementType;
import org.levin.ikexpression.parser.reader.ExpressionReader;
import org.levin.ikexpression.utils.Sets;

import java.io.IOException;
import java.util.Set;

import static org.levin.ikexpression.utils.Preconditions.checkState;

/**
 * 读取运算符类型
 * @author 林良益，卓诗垚
 * @version 2.0
 * Sep 21, 2008
 */
public class OperatorTypeReader extends AbstractElementReader {
    private static final Set<String> OPERATOR_WORDS = Sets.newHashSet();

    static {
        OPERATOR_WORDS.add("+");
        OPERATOR_WORDS.add("-");
        OPERATOR_WORDS.add(">");
        OPERATOR_WORDS.add("<");
        OPERATOR_WORDS.add(">=");
        OPERATOR_WORDS.add("<=");
        OPERATOR_WORDS.add("==");
        OPERATOR_WORDS.add("!=");
        OPERATOR_WORDS.add("*");
        OPERATOR_WORDS.add("/");
        OPERATOR_WORDS.add("%");
        OPERATOR_WORDS.add("&&");
        OPERATOR_WORDS.add("||");
        OPERATOR_WORDS.add("!");
        OPERATOR_WORDS.add("#");
        OPERATOR_WORDS.add("?:");
        OPERATOR_WORDS.add("?");
        OPERATOR_WORDS.add(":");
    }

    /**
     * 从流中读取运算符类型的ExpressionToken
     * @param sr
     * @return
     * @throws IOException
     */
    protected Element doRead(ExpressionReader sr) throws IOException {
        int index = sr.getCurrentIndex();
        StringBuilder sb = new StringBuilder();
        int b = sr.read();
        checkState(b != -1, "表达式已结束");

        char c = (char) b;
        sb.append(c);
        if (isOperatorWord(sb.toString())) {
            if (sb.length() == 1) {//两个符号的运算符优先，如<=，不应该认为是<运算符
                sr.mark(0);
                b = sr.read();
                if (b != -1) {
                    if (isOperatorWord(sb.toString() + (char) b)) {
                        return Element.create(sb.toString() + (char) b, index, ElementType.OPERATOR);
                    }
                }
                sr.reset();
            }
            return Element.create(sb.toString(), index, ElementType.OPERATOR);
        }

        while ((b = sr.read()) != -1) {
            c = (char) b;
            sb.append(c);
            if (isOperatorWord(sb.toString())) {
                return Element.create(sb.toString(), index, ElementType.OPERATOR);
            }
            if (VariableTypeReader.STOP_CHAR.indexOf(c) >= 0) {//单词停止符
                throw new ExpressionParseException("不是有效的运算符：" + sb.toString());
            }
        }
        throw new ExpressionParseException("不是有效的运算符结束");
    }

    /**
     * 测试是否为运算符
     * @param sr
     * @return
     * @throws IOException
     */
    public static boolean isOperatorStart(ExpressionReader sr) throws IOException {
        sr.mark(0);
        try {
            StringBuilder sb = new StringBuilder();
            int b = sr.read();
            if (b == -1) {
                return false;
            }
            char c = (char) b;
            sb.append(c);
            if (isOperatorWord(sb.toString())) {
                return true;
            }
            while ((b = sr.read()) != -1) {
                c = (char) b;
                sb.append(c);
                if (isOperatorWord(sb.toString())) {
                    return true;
                }
                if (VariableTypeReader.STOP_CHAR.indexOf(c) >= 0) {//单词停止符
                    return false;
                }
            }
            return false;
        } finally {
            sr.reset();
        }
    }

    /**
     * 判断字符串是否是合法的操作符
     * @param tokenText
     * @return
     */
    private static boolean isOperatorWord(String tokenText) {
        return OPERATOR_WORDS.contains(tokenText);
    }
}
