/**
 *
 */
package org.levin.ikexpression;

import org.levin.ikexpression.datameta.BaseDataMeta;
import org.levin.ikexpression.datameta.Constant;
import org.levin.ikexpression.datameta.Reference;
import org.levin.ikexpression.datameta.Variable;
import org.levin.ikexpression.operators.Operator;

import static org.levin.ikexpression.utils.Preconditions.checkNotNull;

/**
 * 表达式解析词元对象
 * @author 林良益，卓诗垚
 * @version 2.0
 * 2008-09-18
 */
public class ExpressionToken {
    //词元的语法类型
    public enum ETokenType {
        //常量
        ETOKEN_TYPE_CONSTANT,
        //变量
        ETOKEN_TYPE_VARIABLE,
        //操作符
        ETOKEN_TYPE_OPERATOR,
        //函数
        ETOKEN_TYPE_FUNCTION,
        //分隔符
        ETOKEN_TYPE_SPLITOR,
        ;
    }

    //Token的词元类型：常量，变量，操作符，函数，分割符
    private ETokenType tokenType;

    //当TokenType = ETOKEN_TYPE_CONSTANT 时,constant存储常量描述
    private Constant constant;

    //当TokenType = ETOKEN_TYPE_VARIABLE 时,variable存储变量描述
    private Variable variable;

    //当TokenType = ETOKEN_TYPE_OPERATOR 时, operator存储操做符描述
    private Operator operator;

    //存储字符描述
    private String tokenText;

    //词元在表达式中的起始位置
    private int startPosition = -1;

    public static ExpressionToken createConstantToken(BaseDataMeta.DataType dataType, Object dataValue) {
        ExpressionToken instance = new ExpressionToken();
        instance.tokenType = ETokenType.ETOKEN_TYPE_CONSTANT;
        instance.constant = new Constant(dataType, dataValue);
        if (dataValue != null) {
            instance.tokenText = instance.constant.getDataValueText();
        }
        return instance;
    }

    public static ExpressionToken createConstantToken(Constant constant) {
        checkNotNull(constant, "constant is null");

        ExpressionToken instance = new ExpressionToken();
        instance.tokenType = ETokenType.ETOKEN_TYPE_CONSTANT;
        instance.constant = constant;
        if (constant.getDataValue() != null) {
            instance.tokenText = constant.getDataValueText();
        }
        return instance;
    }

    public static ExpressionToken createVariableToken(String variableName) {
        ExpressionToken instance = new ExpressionToken();
        instance.tokenType = ETokenType.ETOKEN_TYPE_VARIABLE;
        instance.variable = new Variable(variableName);
        instance.tokenText = variableName;
        return instance;
    }

    public static ExpressionToken createReference(Reference ref) {
        ExpressionToken instance = new ExpressionToken();
        instance.tokenType = ETokenType.ETOKEN_TYPE_CONSTANT;
        instance.constant = new Constant(ref);
        if (ref != null) {
            instance.tokenText = instance.constant.getDataValueText();
        }
        return instance;
    }

    public static ExpressionToken createFunctionToken(String functionName) {
        checkNotNull(functionName, "functionName is null");

        ExpressionToken instance = new ExpressionToken();
        instance.tokenType = ETokenType.ETOKEN_TYPE_FUNCTION;
        instance.tokenText = functionName;
        return instance;
    }

    public static ExpressionToken createOperatorToken(Operator operator) {
        checkNotNull(operator, "operator is null");

        ExpressionToken instance = new ExpressionToken();
        instance.tokenType = ETokenType.ETOKEN_TYPE_OPERATOR;
        instance.operator = operator;
        instance.tokenText = operator.getToken();
        return instance;
    }

    public static ExpressionToken createSplitorToken(String splitorText) {
        checkNotNull(splitorText, "splitorText is null");

        ExpressionToken instance = new ExpressionToken();
        instance.tokenType = ETokenType.ETOKEN_TYPE_SPLITOR;
        instance.tokenText = splitorText;
        return instance;
    }

    private ExpressionToken() { }

    /**
     * 获取Token的词元类型
     * @return
     */
    public ETokenType getTokenType() {
        return tokenType;
    }

    /**
     * 获取Token的常量描述
     * @return
     */
    public Constant getConstant() {
        return this.constant;
    }

    /**
     * 获取Token的变量描述
     * @return
     */
    public Variable getVariable() {
        return this.variable;
    }

    /**
     * 获取Token的操作符类型值
     * @return
     */
    public Operator getOperator() {
        return this.operator;
    }

    /**
     * 获取Token的方法名类型值
     * @return
     */
    public String getFunctionName() {
        return this.tokenText;
    }

    /**
     * 获取Token的分隔符类型值
     * @return
     */
    public String getSplitor() {
        return this.tokenText;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    @Override
    public String toString() {
        return tokenText;
    }
}
