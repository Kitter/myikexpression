/**
 *
 */
package org.levin.ikexpression;

import org.levin.ikexpression.exception.IllegalExpressionException;
import org.levin.ikexpression.datameta.Constant;
import org.levin.ikexpression.datameta.Variable;
import org.levin.ikexpression.utils.CollectionUtils;

import java.util.Collection;
import java.util.List;

import static org.levin.ikexpression.utils.Preconditions.checkNotNull;

/**
 * IK-Expression 表达式解析器（主入口程序）
 * ExpressionEvaluator 是多线程安全的
 *
 * @author 林良益，卓诗垚
 * @version 2.0
 * Sep 25, 2008
 */
public class ExpressionEvaluator {

    /**
     * 验证表达式
     *
     * @param expression
     * @return
     */
    public static String compile(String expression) {
        return compile(expression, null);
    }

    /**
     * 验证表达式
     * @param expression
     * @param variables
     * @return
     * @throws IllegalExpressionException
     */
    public static String compile(String expression, Collection<Variable> variables) {
        checkNotNull(expression, "expression is null");

        ExpressionExecutor expressionExecutor = new ExpressionExecutor();
        try {
            //获取上下文的变量，设置到脚本执行器中
            addVariables(variables);
            //解析表达式词元
            List<ExpressionToken> expTokens = expressionExecutor.analyze(expression);
            //转化RPN，并验证
            expTokens = expressionExecutor.compile(expTokens);
            //以字符串形式输出RPN
            return expressionExecutor.tokensToString(expTokens);
        } catch (IllegalExpressionException e) {
            throw new RuntimeException("表达式：\"" + expression + "\" 编译期检查异常", e);
        } finally {
            //释放脚本变量容器
            VariableContainer.removeVariableMap();
        }
    }

    /**
     * 获取预编译的表达式对象
     * @param expression 表达式的字符串表示
     * @param variables 表达式的参数集合
     * @return PreparedExpression 编译的表达式对象
     * @throws IllegalExpressionException
     */
    public static PreparedExpression preparedCompile(String expression, Collection<Variable> variables) {
        checkNotNull(expression, "expression is null");

        ExpressionExecutor ee = new ExpressionExecutor();
        try {
            //获取上下文的变量，设置到脚本执行器中
            addVariables(variables);
            //解析表达式词元
            List<ExpressionToken> expTokens = ee.analyze(expression);
            //转化RPN，并验证
            expTokens = ee.compile(expTokens);
            //生成预编译表达式
            PreparedExpression pe = new PreparedExpression(expression, expTokens, VariableContainer.getVariableMap());
            return pe;
        } finally {
            //释放脚本变量容器
            VariableContainer.removeVariableMap();
        }
    }


    /**
     * 执行无变量表达式
     * @param expression
     * @return
     */
    public static Object evaluate(String expression) {
        return evaluate(expression, null);
    }

    /**
     * 根据流程上下文，执行公式语言
     * @param expression
     * @param variables
     * @return
     */
    public static Object evaluate(String expression, Collection<Variable> variables) {
        checkNotNull(expression, "expression is null");

        ExpressionExecutor expressionExecutor = new ExpressionExecutor();
        try {
            addVariables(variables);
            //解析表达式词元
            List<ExpressionToken> expTokens = expressionExecutor.analyze(expression);
            //转化RPN，并验证
            expTokens = expressionExecutor.compile(expTokens);
            //执行RPN
            Constant constant = expressionExecutor.execute(expTokens);

            return constant.toJavaObject();
        } finally {
            //释放脚本变量容器
            VariableContainer.removeVariableMap();
        }
    }

    /**
     * 逐个添加表达式上下文变量
     * @param variable
     */
    public static void addVariable(Variable variable) {
        //添加变来到脚本变量容器
        VariableContainer.addVariable(variable);
    }

    /**
     * 批量添加表达式上下文变量
     * @param variables
     */
    public static void addVariables(Collection<Variable> variables) {
        //获取上下文的变量，设置到脚本执行器中
        if (CollectionUtils.isNotEmpty(variables)) {
            for (Variable var : variables) {
                //添加变来到脚本变量容器
                addVariable(var);
            }
        }
    }
}
