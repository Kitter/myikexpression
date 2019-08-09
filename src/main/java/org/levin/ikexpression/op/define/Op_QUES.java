/**
 * 
 */
package org.levin.ikexpression.op.define;

import org.levin.ikexpression.IllegalExpressionException;
import org.levin.ikexpression.datameta.BaseDataMeta;
import org.levin.ikexpression.datameta.Constant;
import org.levin.ikexpression.op.IOperatorExecution;
import org.levin.ikexpression.op.Operator;

/**
 * @author 林良益，卓诗垚
 * @version 2.0 
 * 2009-02-06 
 */
public class Op_QUES implements IOperatorExecution {
	
	public static final Operator THIS_OPERATOR = Operator.QUES;
	/* (non-Javadoc)
	 * @see org.levin.ikexpression.op.IOperatorExecution#execute(org.levin.ikexpression.datameta.Constant[])
	 */
	public Constant execute(Constant[] args) {
		throw new UnsupportedOperationException("操作符\"" + THIS_OPERATOR.getToken() + "不支持该方法");
	}

	/* (non-Javadoc)
	 * @see org.levin.ikexpression.op.IOperatorExecution#verify(int, org.levin.ikexpression.datameta.BaseDataMeta[])
	 */
	public Constant verify(int opPositin, BaseDataMeta[] args)
			throws IllegalExpressionException {
		throw new UnsupportedOperationException("操作符\"" + THIS_OPERATOR.getToken() + "不支持该方法");
	}

}
