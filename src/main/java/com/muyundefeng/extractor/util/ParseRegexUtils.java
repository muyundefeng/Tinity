package com.muyundefeng.extractor.util;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式解析器，解析其中的捕获分组
 * Created by lisheng on 17-5-15.
 */
public class ParseRegexUtils {
    /**
     * 解析其中有多少个捕获分组
     *
     * @param regx
     * @return
     */
    public static int analyseNumberGroup(String regx) {
        int count = 0;
        String str = "\\.\\*";
        Pattern pattern = Pattern.compile(str);
        Matcher matcher = pattern.matcher(regx);
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    /**
     * @param regex 正则表达式
     * @param group 捕获分组数量
     * @return 返回要提取的相关的数据信息
     * @Param html 要抽取的html文本信息
     */
    public static List<String> extraText(String regex, int group, String html) {

        List<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            list.add(matcher.group(group));
        }
        return list;
    }

    /**
     * 对正则表达式进行规范处理
     *
     * @param str 要处理的正则表达式
     * @return 返回正常的正则表达式
     */
    public static String getNormalRegex(String str) {
        str = str.replaceAll("\\.\\*", "(.*)");
        return str;
    }

    /**
     * 正则表达式截取，截取合适的长度
     *
     * @param str
     * @return
     */
    public static String cutRegex(String str) {
        char[] chars = str.toCharArray();
        int endIndex = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            if (chars[i] == '.' && chars[i + 1] == '*') {
                endIndex = i;
            }
        }
        endIndex++;
        int end = 0;
        //为了能够保证唯一性，需要在加几个标签
        for (int i = endIndex; i < str.length(); i++) {
            String str1 = str.substring(endIndex, i);
            if (StringUtils.countMatches(str1, "<") == 2 && StringUtils.countMatches(str1, ">") == 2) {
                end = i;
                break;
            }
        }
        return str.substring(0, end);

    }
}
