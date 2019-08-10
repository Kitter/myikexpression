/*
 * Copyright 2019-2019 [Levin]
 */
package org.levin.ikexpression.parser;

import org.levin.ikexpression.ExpressionToken;
import org.levin.ikexpression.datameta.BaseDataMeta;
import org.levin.ikexpression.operators.Operator;
import org.levin.ikexpression.utils.DateUtils;

/**
 * 类ElementType描述：
 *
 * @author dinglevin
 * @date 2019-08-10 11:46
 */
public enum ElementType {
    //NULL类型
    NULL {
        @Override
        public ExpressionToken createExprToken(Element element, ExpressionToken previousToken) {
            return ExpressionToken.createConstantToken(BaseDataMeta.DataType.DATATYPE_NULL, null);
        }
    },

    //字符窜
    STRING {
        @Override
        public ExpressionToken createExprToken(Element element, ExpressionToken previousToken) {
            return ExpressionToken.createConstantToken(BaseDataMeta.DataType.DATATYPE_STRING, element.getText());
        }
    },

    //布尔类
    BOOLEAN {
        @Override
        public ExpressionToken createExprToken(Element element, ExpressionToken previousToken) {
            return ExpressionToken.createConstantToken(BaseDataMeta.DataType.DATATYPE_BOOLEAN, Boolean.valueOf(element.getText()));
        }
    },

    //整数
    INT {
        @Override
        public ExpressionToken createExprToken(Element element, ExpressionToken previousToken) {
            return ExpressionToken.createConstantToken(BaseDataMeta.DataType.DATATYPE_INT, Integer.valueOf(element.getText()));
        }
    },

    //长整数
    LONG {
        @Override
        public ExpressionToken createExprToken(Element element, ExpressionToken previousToken) {
            return ExpressionToken.createConstantToken(BaseDataMeta.DataType.DATATYPE_LONG, Long.valueOf(element.getText()));
        }
    },

    //浮点数
    FLOAT {
        @Override
        public ExpressionToken createExprToken(Element element, ExpressionToken previousToken) {
            return ExpressionToken.createConstantToken(BaseDataMeta.DataType.DATATYPE_FLOAT, Float.valueOf(element.getText()));
        }
    },

    //双精度浮点
    DOUBLE {
        @Override
        public ExpressionToken createExprToken(Element element, ExpressionToken previousToken) {
            return ExpressionToken.createConstantToken(BaseDataMeta.DataType.DATATYPE_DOUBLE, Double.valueOf(element.getText()));
        }
    },

    //日期时间
    DATE {
        @Override
        public ExpressionToken createExprToken(Element element, ExpressionToken previousToken) {
            return ExpressionToken.createConstantToken(BaseDataMeta.DataType.DATATYPE_DATE, DateUtils.quietParse(element.getText()));
        }
    },

    //变量
    VARIABLE {
        @Override
        public ExpressionToken createExprToken(Element element, ExpressionToken previousToken) {
            return ExpressionToken.createVariableToken(element.getText());
        }
    },

    //操作符
    OPERATOR {
        @Override
        public ExpressionToken createExprToken(Element element, ExpressionToken previousToken) {
            //区分负号
            if (element.getText().equals("-") && (
                    //以“-”开头肯定是负号
                    previousToken == null ||
                    //运算符后面肯定是负号
                    previousToken.getTokenType() == ExpressionToken.ETokenType.ETOKEN_TYPE_OPERATOR ||
                    //“(”或“,”后面肯定是负号
                    previousToken.getTokenType() == ExpressionToken.ETokenType.ETOKEN_TYPE_SPLITOR &&
                    !")".equals(previousToken.getSplitor())
            )) {
                return ExpressionToken.createOperatorToken(Operator.NG);
            } else {
                return ExpressionToken.createOperatorToken(Operator.findByToken(element.getText()));
            }
        }
    },

    //函数
    FUNCTION {
        @Override
        public ExpressionToken createExprToken(Element element, ExpressionToken previousToken) {
            return ExpressionToken.createFunctionToken(element.getText());
        }
    },

    //分隔符
    SPLITOR {
        @Override
        public ExpressionToken createExprToken(Element element, ExpressionToken previousToken) {
            return ExpressionToken.createSplitorToken(element.getText());
        }
    };

    public abstract ExpressionToken createExprToken(Element element, ExpressionToken previousToken);
}
