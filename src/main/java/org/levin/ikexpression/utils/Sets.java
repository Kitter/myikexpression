/*
 * Copyright 2019-2019 [Levin]
 */
package org.levin.ikexpression.utils;

import java.util.HashSet;
import java.util.Set;

/**
 * 类Sets描述：
 *
 * @author dinglevin
 * @date 2019-08-10 12:27
 */
public class Sets {
    private Sets() { }

    public static <T> Set<T> newHashSet() {
        return new HashSet<>();
    }
}
