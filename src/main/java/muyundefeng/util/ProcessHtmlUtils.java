package muyundefeng.util;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对html文档进行预处理
 * Created by lisheng on 17-5-9.
 */
public class ProcessHtmlUtils {

    public static final String regMetaCha[] = {"?", "^", "[", "]", "{", "}", "(", ")", "$", "+", ".", "*"};

    public static String rmSomeScript(String html) {
        html = html.replaceAll("(?is)<!DOCTYPE.*?>", "");
        html = html.replaceAll("(?is)<!--.*?-->", "");
        html = html.replaceAll("(?is)<script.*?>.*?</script>", "");
        html = html.replaceAll("(?is)<style.*?>.*?</style>", "");
        html = html.replaceAll("&.{2,5};|&#.{2,5};", " ");
        String patternStr = "<(\\w+)?(\\s[^>]+)>";
        Pattern pattern = java.util.regex.Pattern.compile(patternStr);

        Matcher matcher = pattern.matcher(html);
        while (matcher.find()) {
            String str = matcher.group(2);
            String label = matcher.group(1);
            //将字符串str进行转义StringEscapeUtils
            if (!label.contains("div")) {
                for (String ch : regMetaCha) {
                    str = str.replace(ch, "\\" + ch);
                }
                html = html.replaceFirst(str, "");
            }
        }
        html = ExtraMainBodyUtils.extraMainBody(html, "<div>");
        System.out.println(html);
        html = html.replaceAll("\\s", "");//移除空格
        html = html.replaceAll("<meta>", "");//移除meta元素
        html = html.replaceAll("<head>", "");//移除head元素
        html = html.replaceAll("<p>", "");//移除p标签
        html = html.replaceAll("</p>", "");
        html = html.replaceAll("<span>", "");//移除span
        html = html.replaceAll("</span>", "");
        html = html.replaceAll("<strong>", "");//移除strong
        html = html.replaceAll("</strong>", "");
        html = html.replaceAll("<img>", "");//移除图片

        //提取出有元素的数据
        String extraEleData = "(?i)<[^>]+>[^<]+<[^>]+>";
        Pattern patternData = Pattern.compile(extraEleData);
        Matcher matcher1 = patternData.matcher(html);
        String afterProcessHtml = "";
        while (matcher1.find()) {
            String str = matcher1.group(0);
            afterProcessHtml += str + "\n";
        }
        return afterProcessHtml;
    }

    public static void main(String[] args) {
        String str = "<iframe src=\"http://v.qq.com/video/playview.html?vid=o0500gf4ps0\" width=\"0\" height=\"0\" style=\"display:none;\"></iframe>";
        System.out.println(rmSomeScript(str));
    }
}
