/*
 * Copyright 2019-2019 [Levin]
 */
package org.levin.ikexpression.experiment;

import org.junit.Test;
import org.levin.ikexpression.ExpressionEvaluator;

/**
 * 类ExpressionEvaluatorTest描述：
 *
 * @author dinglevin
 * @date 2019-08-09 23:10
 */
public class ExpressionEvaluatorTest {
    @Test
    public void testCompile() {
        String expr = "-(10 + (23 - 3) * (4 / 5)) % 6";

        System.out.println(ExpressionEvaluator.compile(expr));

        System.out.println(ExpressionEvaluator.evaluate(expr));
    }

    @Test
    public void testSimpleRPN() {
        String expression = "1 + 2 * 3";
        System.out.println(ExpressionEvaluator.evaluate(expression));

        expression = "1 + 2 + 3";
        System.out.println(ExpressionEvaluator.compile(expression));
    }
}
