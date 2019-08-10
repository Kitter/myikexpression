/**
 *
 */
package org.levin.ikexpression.operators;

import org.levin.ikexpression.datameta.BaseDataMeta;
import org.levin.ikexpression.datameta.Constant;
import org.levin.ikexpression.exception.ExpressionParseException;
import org.levin.ikexpression.exception.IllegalExpressionException;
import org.levin.ikexpression.operators.define.*;

import java.util.Objects;

/**
 * 表达式操作符接口
 * 操作符优先级数值越大，优先级越高
 * @author 林良益，卓诗垚
 * @version 2.0
 * 2008-09-17
 */
public enum Operator {
    //逻辑否
    NOT("!", 80, 1, new Op_NOT()),
    //取负
    NG("-", 80, 1, new Op_NG()),

    //算术乘
    MUTI("*", 70, 2, new Op_MUTI()),
    //算术除
    DIV("/", 70, 2, new Op_DIV()),
    //算术除
    MOD("%", 70, 2, new Op_MOD()),

    //算术加
    PLUS("+", 60, 2, new Op_PLUS()),
    //算术减
    MINUS("-", 60, 2, new Op_MINUS()),

    //逻辑小于
    LT("<", 50, 2, new Op_LT()),
    //逻辑小等于
    LE("<=", 50, 2, new Op_LE()),
    //逻辑大于
    GT(">", 50, 2, new Op_GT()),
    //逻辑大等于
    GE(">=", 50, 2, new Op_GE()),

    //逻辑等
    EQ("==", 40, 2, new Op_EQ()),
    //逻辑不等
    NEQ("!=", 40, 2, new Op_NEQ()),

    //逻辑与
    AND("&&", 30, 2, new Op_AND()),

    //逻辑或
    OR("||", 20, 2, new Op_OR()),

    //集合添加
    APPEND("#", 10, 2, new Op_APPEND()),


    //三元选择
    QUES("?", 0, 0, new Op_SELECT()),
    COLON(":", 0, 0, new Op_QUES()),
    SELECT("?:", 0, 3, new Op_COLON());

    private String token;
    private int priority;
    private int opType;
    private OperatorExecutor executor;

    Operator(String token, int priority, int opType, OperatorExecutor executor) {
        this.token = token;
        this.priority = priority;
        this.opType = opType;
        this.executor = executor;
    }

    /**
     * 获取操作符的字符表示
     * 如：+  - equals && ==
     * @return String 操作符的字符形态
     */
    public String getToken() {
        return this.token;
    }

    /**
     * 获取操作符的优先级
     * @return int 操作符优先级
     */
    public int getPriority() {
        return this.priority;
    }

    /**
     * 操作符类型
     * 一元 ！
     * 二元 && >=
     * @return int 操作符类型（几元操作）
     */
    public int getOpType() {
        return this.opType;
    }

    /**
     * 执行操作，并返回结果Token
     * @param args 注意args中的参数由于是从栈中按LIFO顺序弹出的，所以必须从尾部倒着取数
     * @return Constant 常量型的执行结果
     */
    public Constant execute(Constant[] args) {
        return executor.execute(args);
    }

    /**
     * 检查操作符和参数是否合法，是可执行的
     * 如果合法，则返回含有执行结果类型的Token
     * 如果不合法，则返回null
     * @param position 操作符位置
     * @param args 注意args中的参数由于是从栈中按LIFO顺序弹出的，所以必须从尾部倒着取数
     * @return Constant 常量型的执行结果
     */
    public Constant verify(int position, BaseDataMeta[] args) throws IllegalExpressionException {
        return executor.verify(position, args);
    }

    public static Operator findByToken(String operateToken) {
        for (Operator operator : Operator.values()) {
            if (Objects.equals(operateToken, operator.getToken())) {
                return operator;
            }
        }

        throw new ExpressionParseException("Operator not supported: " + operateToken);
    }
}
