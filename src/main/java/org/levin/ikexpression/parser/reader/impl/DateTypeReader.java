/**
 *
 */
package org.levin.ikexpression.parser.reader.impl;

import org.levin.ikexpression.exception.ExpressionParseException;
import org.levin.ikexpression.parser.Element;
import org.levin.ikexpression.parser.ElementType;
import org.levin.ikexpression.parser.reader.ExpressionReader;

import java.io.IOException;
import java.io.StringReader;

/**
 * 读取时间类型
 * @author 林良益，卓诗垚
 * @version 2.0
 * Sep 21, 2008
 */
public class DateTypeReader extends AbstractElementReader {
    public static final char START_MARK = '[';//时间开始标志
    public static final char END_MARK = ']';//时间结束标志

    public static final String DATE_CHARS = "0123456789-:. ";

    /**
     * 从流中读取时间类型的ExpressionToken
     * @param sr
     * @return
     * @throws IOException
     */
    protected Element doRead(ExpressionReader sr) throws IOException {
        int index = sr.getCurrentIndex();
        StringBuilder builder = new StringBuilder();
        int b = sr.read();
        if (b == -1 || b != START_MARK) {
            throw new ExpressionParseException("不是有效的时间开始");
        }

        while ((b = sr.read()) != -1) {
            char c = (char) b;
            if (c == END_MARK) {
                return Element.create(formatTime(builder.toString()), index, ElementType.DATE);
            }
            if (DATE_CHARS.indexOf(c) == -1) {
                throw new ExpressionParseException("时间类型不能包函非法字符：" + c);
            }
            builder.append(c);
        }
        throw new ExpressionParseException("不是有效的时间结束");
    }

    /**
     * 格式化时间字符窜
     * 如2007-12-1 12:2会被格式化成2007-12-01 12:02:00
     * 转化后的格式支持Timestamp.valueOf(String value)
     * @param time 字符窜表示的时间
     * @return 格式代的结果
     */
    public static String formatTime(String time) {
        if (time == null) {
            throw new ExpressionParseException("不是有效的时间表达式");
        }
        StringReader sr = new StringReader(time.trim());
        StringBuffer sb = new StringBuffer();
        int b = -1;
        try {
            while ((b = sr.read()) != -1) {
                char c = (char) b;
                if (sb.length() < 4) {//年
                    int find = DATE_CHARS.indexOf(c);
                    if (find == -1 || find > 9) {
                        throw new ExpressionParseException("年份必需为4位数字");
                    }
                    sb.append(c);
                } else if (sb.length() == 4) {//
                    if (c != '-') {
                        throw new ExpressionParseException("日期分割符必需为“－”");
                    }
                    sb.append(c);
                } else if (sb.length() == 5) {//月
                    int find = DATE_CHARS.indexOf(c);
                    if (find == -1 || find > 9) {
                        throw new ExpressionParseException("月份必需为2位以内的数字");
                    }
                    sb.append(c);
                    sr.mark(0);
                    c = (char) sr.read();
                    find = DATE_CHARS.indexOf(c);
                    if (find == -1 || find > 9) {
                        sb.insert(5, '0');
                        sr.reset();
                    } else {
                        sb.append(c);
                    }
                } else if (sb.length() == 7) {//
                    if (c != '-') {
                        throw new ExpressionParseException("日期分割符必需为“－”");
                    }
                    sb.append(c);
                } else if (sb.length() == 8) {//日
                    int find = DATE_CHARS.indexOf(c);
                    if (find == -1 || find > 9) {
                        throw new ExpressionParseException("日必需为2位以内的数字");
                    }
                    sb.append(c);
                    sr.mark(0);
                    c = (char) sr.read();
                    find = DATE_CHARS.indexOf(c);
                    if (find == -1 || find > 9) {
                        sb.insert(8, '0');
                        sr.reset();
                    } else {
                        sb.append(c);
                    }
                } else if (sb.length() == 10) {//
                    if (c != ' ') {
                        throw new ExpressionParseException("日期后分割符必需为“ ”");
                    }
                    sb.append(c);
                } else if (sb.length() == 11) {//小时
                    int find = DATE_CHARS.indexOf(c);
                    if (find == -1 || find > 9) {
                        throw new ExpressionParseException("小时必需为2位以内的数字");
                    }
                    sb.append(c);
                    sr.mark(0);
                    c = (char) sr.read();
                    find = DATE_CHARS.indexOf(c);
                    if (find == -1 || find > 9) {
                        sb.insert(11, '0');
                        sr.reset();
                    } else {
                        sb.append(c);
                    }
                } else if (sb.length() == 13) {//
                    if (c != ':') {
                        throw new ExpressionParseException("时间分割符必需为“:”");
                    }
                    sb.append(c);
                } else if (sb.length() == 14) {//分
                    int find = DATE_CHARS.indexOf(c);
                    if (find == -1 || find > 9) {
                        throw new ExpressionParseException("分钟必需为2位以内的数字");
                    }
                    sb.append(c);
                    sr.mark(0);
                    c = (char) sr.read();
                    find = DATE_CHARS.indexOf(c);
                    if (find == -1 || find > 9) {
                        sb.insert(14, '0');
                        sr.reset();
                    } else {
                        sb.append(c);
                    }
                } else if (sb.length() == 16) {//
                    if (c != ':') {
                        throw new ExpressionParseException("时间分割符必需为“:”");
                    }
                    sb.append(c);
                } else if (sb.length() == 17) {//秒
                    int find = DATE_CHARS.indexOf(c);
                    if (find == -1 || find > 9) {
                        throw new ExpressionParseException("秒必需为2位以内的数字");
                    }
                    sb.append(c);
                    sr.mark(0);
                    c = (char) sr.read();
                    find = DATE_CHARS.indexOf(c);
                    if (find == -1 || find > 9) {
                        sb.insert(17, '0');
                        sr.reset();
                    } else {
                        sb.append(c);
                    }
                } else {
                    throw new ExpressionParseException("不是有效的时间表达式");
                }
            }
        } catch (IOException e) {
            throw new ExpressionParseException("不是有效的时间表达式");
        }

        if (sb.length() == 10) {//补时间
            sb.append(" 00:00:00");
        } else if (sb.length() == 16) {//补秒
            sb.append(":00");
        }
        if (sb.length() != 19) {
            throw new ExpressionParseException("不是有效的时间表达式");
        }
        return sb.toString();
    }
}
