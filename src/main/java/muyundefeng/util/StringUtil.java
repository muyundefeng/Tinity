package muyundefeng.util;

import muyundefeng.trinity.Text;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 自定义String工具类
 *
 * @author lisheng
 */
public class StringUtil {

    /**
     * 获取Text文本长度,将一个标签视为一个token，计算token的个数
     *
     * @param text
     * @return
     */
    public static int length(Text text) {
        return count(text.getText()); //+ rawText.length();
    }

    /**
     * 获取String中的标签个数
     *
     * @param str
     * @return
     */
    public static int count(String str) {
        int count = 0;
        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            count++;
        }
        return count;
    }

    /**
     * 截取子字符串，截取字符串的时候只关心字符串中所包含的标签
     *
     * @param text  　函数作用的目标
     * @param start 　子字符串开始的位置
     * @param end   　子字符串结束的位置
     * @return　返回子字符串
     */
    public static String subString(Text text, int start, int end) {
        int realStart = realStartIndex(text, start);
        int realEnd = realEndIndex(text, end);
        return text.getText().substring(realStart, realEnd);
    }

    /**
     * 查找字符串真正开始的位置
     *
     * @param text
     * @param start
     * @return
     */
    public static int realStartIndex(Text text, int start) {
        String raw = text.getText();
        int length = 0;
        if (raw != null) {
            if (raw.startsWith("<")) {

            } else {
                length = raw.indexOf("<");
                raw = raw.substring(length);
            }
        }
        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(raw);
        int realStartIndex = 0;
        int count = 0;
        String rawTemp = "";
        String temp = "";
        String match = "";
        String endMatch = "";
        while (matcher.find()) {
            if (count != (start + 1)) {
                temp = match;
                match = matcher.group();
                endMatch = match;
                count++;
                realStartIndex += match.length();
                String betweenTokenStr = StringUtils.substringBetween(rawTemp, temp, match);
                if (betweenTokenStr != null && betweenTokenStr.length() > 0) {

                    realStartIndex += betweenTokenStr.length();
                }
                rawTemp = raw;
                raw = raw.replaceFirst("<[^>]*>", "");
            } else
                break;
        }
        return realStartIndex + length - endMatch.length();
    }

    /**
     * 查找字符串真正结束的位置
     *
     * @param text
     * @return
     */
    @SuppressWarnings("Duplicates")
    public static int realEndIndex(Text text, int end) {
        String raw = text.getText();
        int length = 0;
        if (raw != null) {
            if (raw.startsWith("<")) {

            } else {
                length = raw.indexOf("<");
                //System.out.println("length="+length);
                raw = raw.substring(length);
                //System.out.println(raw);
            }
        }
        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(raw);
        int realEndIndex = length;
        int count = 0;
        String rawTemp = raw;
        String temp = "";
        String match = "";
        //String endMacth = "";
        while (matcher.find()) {
            if (count != end) {
                temp = match;
                match = matcher.group();
                //endMacth = match;
                count++;
                realEndIndex += match.length();
                //System.out.println(realEndIndex);
                //System.out.println("matches="+match+","+"temp="+temp);
                String betweenTokenStr = StringUtils.substringBetween(rawTemp, temp, match);
                //System.out.println(betweenTokenStr);
                if (betweenTokenStr != null && betweenTokenStr.length() > 0) {
                    //System.out.println(betweenTokenStr.length());
                    realEndIndex += betweenTokenStr.length();
                }
                rawTemp = raw;
                raw = raw.replaceFirst("<[^>]*>", "");
            } else
                break;
        }
        //System.out.println("realEndIndex="+realEndIndex);
        return realEndIndex;
    }

    public static void main(String[] args) {
        String str = "<div class=\"soso\" id=\"soso\">\n" +
                "\t\t<div class=\"logo\" id=\"tencentlogo\" bossZone=\"logo\">\n" +
                "\t\t\t<h1>\n" +
                "\t\t\t\t<a href=\"http://www.qq.com\" class=\"qqlogo\" target=\"_blank\">\n" +
                "\t\t\t\t\t<span class=\"undis\">腾讯网</span>\n" +
                "\t\t\t\t</a>\n" +
                "\t\t\t</h1>\n" +
                "\t\t</div>\n";
        System.out.println(extaLabel(str));
    }

    public static boolean equals(String src, String target) {
        String srclabel = extaLabel(src);
        String targetlabel = extaLabel(target);
        return srclabel.equals(targetlabel) ? true : false;
    }

    public static String extaLabel(String str) {
        String labelRegex = "(<[^>]*>)";
        Pattern pattern = Pattern.compile(labelRegex);
        Matcher matcher = pattern.matcher(str);
        String re = "";
        while (matcher.find()) {
            re += matcher.group(1);
        }
        return re;
    }

}
