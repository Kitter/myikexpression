/**
 *
 */
package org.levin.ikexpression.parser;

import org.levin.ikexpression.ExpressionToken;
import org.levin.ikexpression.exception.ExpressionParseException;
import org.levin.ikexpression.parser.reader.ExpressionReader;
import org.levin.ikexpression.utils.Lists;
import org.levin.ikexpression.utils.Stacks;

import java.util.List;
import java.util.Stack;

import static org.levin.ikexpression.utils.Preconditions.checkNotNull;

/**
 * @author 林良益，卓诗垚
 * @version 2.0
 * Sep 23, 2008
 */
public class ExpressionParser {
    private Stack<String> parenthesis = Stacks.newStack(); //匹配圆括号的栈

    public List<ExpressionToken> getExpressionTokens(String expression) {
        ExpressionReader reader = new ExpressionReader(expression);
        List<ExpressionToken> list = Lists.newArrayList();
        ExpressionToken expressionToken = null;//上一次读取的ExpressionToken
        Element element = null;

        while ((element = reader.readToken()) != null) {
            expressionToken = changeToToken(expressionToken, element);
            //如果是括号，则记录下来，最后进行最后进行匹配
            pushParenthesis(element);
            list.add(expressionToken);
        }

        if (!parenthesis.isEmpty()) {
            throw new ExpressionParseException("括号匹配出错");
        }

        return list;
    }

    /**
     * 如果是括号，则记录下来，最后进行最后进行匹配
     * @param element
     */
    public void pushParenthesis(Element element) {
        if (ElementType.SPLITOR == element.getType()) {
            if (element.getText().equals("(")) {
                parenthesis.push("(");
            } else if (element.getText().equals(")")) {
                if (parenthesis.isEmpty() || !parenthesis.peek().equals("(")) {
                    throw new ExpressionParseException("括号匹配出错");
                } else {
                    parenthesis.pop();
                }
            }
        }
    }

    /**
     * 将切分的元素转化成ExpressionToken，并验证减号还是负号
     * @param previousToken
     * @param element
     * @return
     */
    public ExpressionToken changeToToken(ExpressionToken previousToken, Element element) {
        checkNotNull(element, "element is null");

        ExpressionToken token = element.getType().createExprToken(element, previousToken);
        token.setStartPosition(element.getIndex());
        return token;
    }
}
