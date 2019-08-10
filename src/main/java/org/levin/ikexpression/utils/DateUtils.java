/*
 * Copyright 2019-2019 [Levin]
 */
package org.levin.ikexpression.utils;

import org.levin.ikexpression.exception.ExpressionParseException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类DateUtils描述：
 *
 * @author dinglevin
 * @date 2019-08-10 14:06
 */
public class DateUtils {
    private DateUtils() { }

    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final ThreadLocal<DateFormat> DATE_FORMAT_THREAD_LOCAL = ThreadLocal.withInitial(()
            -> new SimpleDateFormat(DATE_FORMAT_PATTERN));

    public static Date quietParse(String dateStr) {
        try {
            return DATE_FORMAT_THREAD_LOCAL.get().parse(dateStr);
        } catch (ParseException ex) {
            throw new ExpressionParseException("Failed to parse date string: " + dateStr, ex);
        }
    }

    public static String format(Date date) {
        return DATE_FORMAT_THREAD_LOCAL.get().format(date);
    }
}
