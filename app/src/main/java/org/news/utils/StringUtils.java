package org.news.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by virgil on 2016/5/31.
 */
public class StringUtils {
    /**
     * 利用正则表达式提取字符串中的数字
     *
     * @param str 被提取的字符串
     * @return 提取的数字
     */
    public static int getIntFromString(String str) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return Integer.parseInt(m.replaceAll("").trim());
    }

    /**
     * 利用正则表达式提取字符串中的日期
     *
     * @param str 被提取的字符串
     * @return 提取的日期，格式为YYYY-MM-DD
     */
    public static String getDateFromString(String str) {
        String regEx = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        if (m.find()) {
            return m.group();
        }
        return null;
    }

}
