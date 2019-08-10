/**
 *
 */
package org.levin.ikexpression.operators.define;

import org.levin.ikexpression.datameta.BaseDataMeta;
import org.levin.ikexpression.datameta.BaseDataMeta.DataType;
import org.levin.ikexpression.datameta.Constant;
import org.levin.ikexpression.datameta.Reference;
import org.levin.ikexpression.exception.IllegalExpressionException;
import org.levin.ikexpression.operators.OperatorExecutor;
import org.levin.ikexpression.operators.Operator;

import java.util.ArrayList;
import java.util.List;

/**
 * 集合添加操作
 * @author 林良益，卓诗垚
 * @version 2.0
 * Oct 8, 2008
 */
public class Op_APPEND implements OperatorExecutor {

    public static final Operator THIS_OPERATOR = Operator.APPEND;

    /* (non-Javadoc)
     * @see org.levin.ikexpression.op.OperatorExecutor#execute(org.levin.ikexpression.ExpressionToken[])
     */
    public Constant execute(Constant[] args) throws IllegalExpressionException {

        if (args == null || args.length != 2) {
            throw new IllegalArgumentException("操作符\"" + THIS_OPERATOR.getToken() + "参数个数不匹配");
        }
        Constant first = args[1];
        Constant second = args[0];
        if (first == null || second == null) {
            throw new NullPointerException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数为空");
        }
        //如果第一参数为引用，则执行引用
        if (first.isReference()) {
            Reference firstRef = (Reference) first.getDataValue();
            first = firstRef.execute();
        }
        //如果第二参数为引用，则执行引用
        if (second.isReference()) {
            Reference secondRef = (Reference) second.getDataValue();
            second = secondRef.execute();
        }
        return append(first, second);
    }

    /* (non-Javadoc)
     * @see org.levin.ikexpression.op.OperatorExecutor#verify(int, org.levin.ikexpression.ExpressionToken[])
     */
    public Constant verify(int position, BaseDataMeta[] args)
            throws IllegalExpressionException {

        if (args == null) {
            throw new IllegalArgumentException("运算操作符参数为空");
        }
        if (args.length != 2) {
            //抛异常
            throw new IllegalExpressionException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数个数不匹配"
                    , THIS_OPERATOR.getToken()
                    , position
            );
        }
        BaseDataMeta first = args[1];
        BaseDataMeta second = args[0];

        if (first == null || second == null) {
            throw new NullPointerException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数为空");
        }
        //APPEND接受任何类型的参数，总是返回Collection类型的常量
        return new Constant(BaseDataMeta.DataType.DATATYPE_LIST, null);

    }

    /**
     * 合并两个常量对象
     */
    private Constant append(Constant arg1, Constant arg2) {
        if (arg1 == null || arg2 == null) {
            throw new IllegalArgumentException("操作符\"" + THIS_OPERATOR.getToken() + "\"参数丢失");
        }

        List<Object> resultCollection = new ArrayList<Object>();
        //合并参数一
        if (DataType.DATATYPE_LIST == arg1.getDataType()) {
            if (arg1.getCollection() != null) {
                resultCollection.addAll(arg1.getCollection());
            }
        } else {
            Object object = arg1.toJavaObject();
            resultCollection.add(object);
        }
        //合并参数二
        if (DataType.DATATYPE_LIST == arg2.getDataType()) {
            if (arg2.getCollection() != null) {
                resultCollection.addAll(arg2.getCollection());
            }
        } else {
            Object object = arg2.toJavaObject();
            resultCollection.add(object);
        }

        //构造新的collection 常量
        Constant result = new Constant(BaseDataMeta.DataType.DATATYPE_LIST, resultCollection);
        return result;
    }

}
