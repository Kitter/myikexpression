/*
 * Copyright 2019-2019 [Levin]
 */
package org.levin.ikexpression.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 类Maps描述：
 *
 * @author dinglevin
 * @date 2019-08-10 11:27
 */
public class Maps {
    private Maps() { }

    public static <K, V> Map<K, V> newHashMap() {
        return new HashMap<>();
    }

    public static <K, V> Map<K, V> newHashMap(Map<K, V> map) {
        return new HashMap<>(map);
    }
}
