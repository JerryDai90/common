package com.wordty.common.assist.utils;

import com.wordty.common.assist.exception.TipException;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;


/**
 * ClassName: Assert <br/>
 * Function: 提供验证非空等验证，如果不满足规则则提示错误. <br/>
 * date: 2016-5-22 上午9:11:54 <br/>
 *
 * @author jerry
 * @since JDK 1.7
 */
public class Assert extends org.springframework.util.Assert {

    /**
     * 判断字符串是否不为空且不是空串. <br/>
     * date: 2016-5-22 上午9:12:42.<br/>
     *
     * @param value 待验证的字符串
     * @param msg   错误信息
     * @author jerry
     * @since JDK 1.7
     */
    public static void notNullAndNotEmpty(String value, String msg) {
        _verify(org.apache.commons.lang3.StringUtils.isNotBlank(value), null, msg);
    }

    /**
     * 自主提供条件验证. <br/>
     * date: 2016-5-22 上午9:13:24.<br/>
     *
     * @param condition 条件
     * @param msg       condition=true的时候抛出异常
     * @author jerry
     * @since JDK 1.7
     */
    public static void verify(boolean condition, String msg) {
        _verify(!condition, null, msg);
    }

    /**
     * 自主提供条件验证. <br/>
     * date: 2016-5-22 上午9:13:24.<br/>
     *
     * @param condition 条件
     * @param code      错误代码
     * @param msg       condition=true的时候抛出异常
     * @author jerry
     * @since JDK 1.7
     */
    public static void verify(boolean condition, String code, String msg) {
        _verify(!condition, code, msg);
    }

    /**
     * 自主提供条件验证. <br/>
     * date: 2016-5-22 上午9:13:24.<br/>
     *
     * @param condition 条件
     * @param msg       condition=true的时候抛出异常
     * @author jerry
     * @since JDK 1.7
     */
    public static void verify(boolean condition, String msg, Object... objects) {
        _verify(!condition, null, messageFormatter(msg, objects));
    }

    public static String messageFormatter(String message, Object... objects) {
        FormattingTuple ft = MessageFormatter.arrayFormat(message, objects);
        return ft.getMessage();
    }

    public static void _verify(boolean condition, String code, String message) {
        if (!condition) {
            throw new TipException(code, message);
        }
    }

}
