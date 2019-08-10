/**
 *
 */
package org.levin.ikexpression.operators.define;

import org.levin.ikexpression.exception.IllegalExpressionException;
import org.levin.ikexpression.datameta.BaseDataMeta;
import org.levin.ikexpression.datameta.Constant;
import org.levin.ikexpression.datameta.Reference;
import org.levin.ikexpression.datameta.BaseDataMeta.DataType;
import org.levin.ikexpression.operators.OperatorExecutor;
import org.levin.ikexpression.operators.Operator;

/**
 * 逻辑与操作
 * @author 林良益，卓诗垚
 * @version 2.0
 * Sep 27, 2008
 */
public class Op_AND implements OperatorExecutor {
    public static final Operator THIS_OPERATOR = Operator.AND;

    public Constant execute(Constant[] args) {
        //参数校验
        if (args == null || args.length != 2) {
            throw new IllegalArgumentException("操作符\"" + THIS_OPERATOR.getToken() + "操作缺少参数");
        }
        Constant first = args[1];
        if (null == first || null == first.getDataValue()) {
            //抛NULL异常
            throw new NullPointerException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数为空");
        }
        Constant second = args[0];
        if (null == second || null == second.getDataValue()) {
            //抛NULL异常
            throw new NullPointerException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数为空");
        }
        //运算：
        //如果第一参数为引用，则执行引用
        if (first.isReference()) {
            Reference firstRef = (Reference) first.getDataValue();
            first = firstRef.execute();
        }
        if (DataType.DATATYPE_BOOLEAN == first.getDataType()) {
            //对AND操作的优化处理，first为false，则忽略计算第二参数
            if (first.getBooleanValue()) {
                //如果第二参数为引用，则执行引用
                if (second.isReference()) {
                    Reference secondRef = (Reference) second.getDataValue();
                    second = secondRef.execute();
                }
                if (DataType.DATATYPE_BOOLEAN == second.getDataType()) {
                    return second;
                } else {
                    //抛异常
                    throw new IllegalArgumentException("操作符\"" + THIS_OPERATOR.getToken() + "\"第二参数类型错误");
                }

            } else {
                return first;
            }
        } else {
            //抛异常
            throw new IllegalArgumentException("操作符\"" + THIS_OPERATOR.getToken() + "\"第一参数类型错误");
        }
    }

    public Constant verify(int position, BaseDataMeta[] args) {
        if (args == null) {
            throw new IllegalArgumentException("运算操作符参数为空");
        }
        if (args.length != 2) {
            //抛异常
            throw new IllegalExpressionException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数丢失"
                    , THIS_OPERATOR.getToken()
                    , position
            );
        }

        BaseDataMeta first = args[1];
        BaseDataMeta second = args[0];
        if (first == null || second == null) {
            throw new NullPointerException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数为空");
        }

        if (DataType.DATATYPE_BOOLEAN == first.getDataType() && DataType.DATATYPE_BOOLEAN == second.getDataType()) {
            return new Constant(DataType.DATATYPE_BOOLEAN, Boolean.FALSE);
        } else {
            //抛异常
            throw new IllegalExpressionException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数类型错误"
                    , THIS_OPERATOR.getToken()
                    , position
            );
        }
    }
}
