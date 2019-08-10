/**
 *
 */
package org.levin.ikexpression.parser.reader;

import org.levin.ikexpression.exception.ExpressionParseException;
import org.levin.ikexpression.parser.reader.impl.*;
import org.levin.ikexpression.utils.ClassUtils;

import java.io.IOException;

import static org.levin.ikexpression.utils.Preconditions.checkState;

/**
 * 词元读取器工厂
 * @author 林良益，卓诗垚
 * @version 2.0
 * Oct 9, 2008
 */
public class ElementReaderFactory {

    /**
     * 根据流开头构造不同的词元读取
     * 流应该非空格开头
     * @param reader
     * @return ElementReader
     * @throws IOException
     * @throws ExpressionParseException
     */
    public static ElementReader createElementReader(ExpressionReader reader) throws IOException {
        //读一个char
        reader.mark(0);
        int b = reader.read();
        reader.reset();

        checkState(b != -1, "流已结束");

        char c = (char) b;
        if (c == StringTypeReader.START_MARK) {//"开头，构造字符串读取器
            return ClassUtils.newInstance(StringTypeReader.class);
        } else if (c == DateTypeReader.START_MARK) {//[开头，构造日期读取器
            return ClassUtils.newInstance(DateTypeReader.class);
        } else if (c == FunctionTypeReader.START_MARK) {//$开头，构造函数读取器
            return ClassUtils.newInstance(FunctionTypeReader.class);
        } else if (SplitorTypeReader.SPLITOR_CHAR.indexOf(c) >= 0) {//如果是分隔符，构造分隔符读取器
            return ClassUtils.newInstance(SplitorTypeReader.class);
        } else if (NumberTypeReader.NUMBER_CHARS.indexOf(c) >= 0) {//以数字开头，构造数字类型读取器
            return ClassUtils.newInstance(NumberTypeReader.class);
        } else if (OperatorTypeReader.isOperatorStart(reader)) {//如果前缀是运算符，构造运算符读取器
            return ClassUtils.newInstance(OperatorTypeReader.class);
        } else {
            return ClassUtils.newInstance(VariableTypeReader.class);//否则构造一个变量读取器
        }
    }
}
