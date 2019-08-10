/*
 * Copyright 2019-2019 [Levin]
 */
package org.levin.ikexpression.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 类Lists描述：
 *
 * @author dinglevin
 * @date 2019-08-10 14:16
 */
public class Lists {
    private Lists() { }

    public static <T> List<T> newArrayList() {
        return new ArrayList<>();
    }
}
