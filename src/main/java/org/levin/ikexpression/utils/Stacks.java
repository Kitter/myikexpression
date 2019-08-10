/*
 * Copyright 2019-2019 [Levin]
 */
package org.levin.ikexpression.utils;

import java.util.Stack;

/**
 * 类Stocks描述：
 *
 * @author dinglevin
 * @date 2019-08-10 15:39
 */
public class Stacks {
    private Stacks() { }

    public static <T> Stack<T> newStack() {
        return new Stack<>();
    }
}
