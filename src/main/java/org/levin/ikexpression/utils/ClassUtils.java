/*
 * Copyright 2019-2019 [Levin]
 */
package org.levin.ikexpression.utils;

import org.levin.ikexpression.exception.ExpressionInternalException;

import static org.levin.ikexpression.utils.Preconditions.checkNotNull;

/**
 * 类ClassUtils描述：
 *
 * @author dinglevin
 * @date 2019-08-10 12:20
 */
public class ClassUtils {
    private ClassUtils() { }

    public static <T> T newInstance(Class<T> instanceType) {
        checkNotNull(instanceType, "instanceType is null");

        try {
            return instanceType.newInstance();
        } catch (Exception ex) {
            throw new ExpressionInternalException("Create instance of " + instanceType + " failed", ex);
        }
    }
}
