/*
 * Copyright 2019-2019 [Levin]
 */
package org.levin.ikexpression.utils;

import java.util.Collection;

/**
 * 类CollectionUtils描述：
 *
 * @author dinglevin
 * @date 2019-08-09 23:00
 */
public class CollectionUtils {
    private CollectionUtils() { }

    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }
}
