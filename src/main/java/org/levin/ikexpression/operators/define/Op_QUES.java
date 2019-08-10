/**
 * 
 */
package org.levin.ikexpression.operators.define;

import org.levin.ikexpression.exception.IllegalExpressionException;
import org.levin.ikexpression.datameta.BaseDataMeta;
import org.levin.ikexpression.datameta.Constant;
import org.levin.ikexpression.operators.OperatorExecutor;
import org.levin.ikexpression.operators.Operator;

/**
 * @author 林良益，卓诗垚
 * @version 2.0 
 * 2009-02-06 
 */
public class Op_QUES implements OperatorExecutor {
	
	public static final Operator THIS_OPERATOR = Operator.QUES;
	/* (non-Javadoc)
	 * @see org.levin.ikexpression.op.OperatorExecutor#execute(org.levin.ikexpression.datameta.Constant[])
	 */
	public Constant execute(Constant[] args) {
		throw new UnsupportedOperationException("操作符\"" + THIS_OPERATOR.getToken() + "不支持该方法");
	}

	/* (non-Javadoc)
	 * @see org.levin.ikexpression.op.OperatorExecutor#verify(int, org.levin.ikexpression.datameta.BaseDataMeta[])
	 */
	public Constant verify(int position, BaseDataMeta[] args)
			throws IllegalExpressionException {
		throw new UnsupportedOperationException("操作符\"" + THIS_OPERATOR.getToken() + "不支持该方法");
	}

}
