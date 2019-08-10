/**
 *
 */
package org.levin.ikexpression;

import org.levin.ikexpression.datameta.Constant;
import org.levin.ikexpression.datameta.Variable;
import org.levin.ikexpression.utils.Maps;

import java.util.List;
import java.util.Map;

import static org.levin.ikexpression.utils.Preconditions.checkNotNull;

/**
 * 预编译的表达式
 * 采用预编译策略，对表达式相同，但参数不同的计算，有效的优化执行效率
 * @author 林良益
 * @version 2.0
 * 2009-09-05
 */
public class PreparedExpression {

    //原始表达式字符
    private String orgExpression;

    //编译验证后生成的RPN
    private List<ExpressionToken> expTokens;

    //编译验证后生成的表达式的变量表
    private Map<String, Variable> variableMap;

    /**
     * 构造函数
     * @param expTokens
     */
    PreparedExpression(String orgExpression, List<ExpressionToken> expTokens, Map<String, Variable> variableMap) {
        this.orgExpression = orgExpression;
        this.expTokens = expTokens;
        this.variableMap = Maps.newHashMap(variableMap);
    }

    /**
     * 设置指定参数的值
     * 如果参数不存在，则抛出IllegalArgumentException运行时异常
     *
     * @param name 参数名
     * @param value 参数值
     */
    public synchronized void setArgument(String name, Object value) {
        Variable variable = variableMap.get(name);
        checkNotNull(variable, "无法识别的表达式参数：" + name);

        variable.setVariableValue(value);
    }

    /**
     * 执行当前的预编译表达式
     * @return
     */
    public Object execute() {
        ExpressionExecutor expressionExecutor = new ExpressionExecutor();
        //执行RPN
        try {
            //添加变来到脚本变量容器
            VariableContainer.setVariableMap(Maps.newHashMap(variableMap));
            Constant constant = expressionExecutor.execute(expTokens);
            return constant.toJavaObject();
        } finally {
            //释放脚本变量容器
            VariableContainer.removeVariableMap();
        }
    }
}
