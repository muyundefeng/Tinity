package muyundefeng.util;


import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 对html文档进行预处理
 * Created by lisheng on 17-5-9.
 */
public class ProcessHtmlUtils {

    public static final String regMetaCha[] = {"?", "^", "[", "]", "{", "}", "(", ")", "$", "+", ".", "*"};

    public static String rmSomeScript(String html) {
//        System.out.println(html);
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
//            System.out.println(str);
            //将字符串str进行转义StringEscapeUtils
//            str = StringEscapeUtils.escapeJava(str);
            for (String ch : regMetaCha) {
                str = str.replace(ch, "\\" + ch);
            }
            html = html.replaceFirst(str, "");
        }
        System.out.println("=====" + html);
        html = html.replaceAll("\\s", "");//移除空格
        html = html.replaceAll("<meta>", "");//移除meta元素
        html = html.replaceAll("<head>", "");//移除head元素

        //提取出有元素的数据
        String extraEleData = "(?i)<[^>]+>[^<]+<[^>]+>";
        Pattern patternData = Pattern.compile(extraEleData);
        Matcher matcher1 = patternData.matcher(html);
        String afterProcessHtml = "";
        while (matcher1.find()) {
            String str = matcher1.group(0);
            //afterProcessHtml += str + "\n";
            System.out.println(str);
            afterProcessHtml += str;
        }
        return afterProcessHtml;
    }

    public static void main(String[] args) {
//        String str = "<meta content=\"text/html; charset=gb2312\" http-equiv=\"Content-Type\"><html>";
//        String str ="                    <p>腾讯娱乐讯据台湾媒体《东森新闻》5月7日报道，女星欧阳娜娜专心投入演艺事业后，陆续在\n" +
//                        "                      <a>电影</a>、电视剧、综艺上皆有演出，人气节节攀升。她出道3年收入逾9千万台币（约合人民币2000万元），不过事业越旺，所承受的舆论压力也就越大，经常成为网友调侃、攻击的目标，但靠着高EQ，心态调适的还算不错，会安慰妈妈傅娟别太在意，特别是还很贴心，即便工作忙碌仍时常留意妈妈的身体状况，乖巧、孝顺的模样在妈妈傅娟眼里既心疼又自豪。</p>\n";
//        String str = "<div id=\"Cnt-Main-Article-QQ\" class=\"Cnt-Main-Article-QQ\" bossZone=\"content\"><p style=\"text-indent: 2em;\" class=\"text\"><p align=\"center\"><img style=\"display: block;\" src=\"http://inews.gtimg.com/newsapp_bt/0/1514813683/641\"></p><p class=\"text image_desc\" align=\"center\">欧阳娜娜资料图</p><p></p><p style=\"text-indent: 2em;\" class=\"text\"><p align=\"center\"><img style=\"display: block;\" src=\"http://inews.gtimg.com/newsapp_bt/0/1514813729/641\"></p><p class=\"text image_desc\" align=\"center\">欧阳娜娜为母亲傅娟庆生</p><p>";
        String str = "<iframe src=\"http://v.qq.com/video/playview.html?vid=o0500gf4ps0\" width=\"0\" height=\"0\" style=\"display:none;\"></iframe>";
//        str = str.replaceFirst(" src=\"http://v.qq.com/video/playview.html?vid=o0500gf4ps0\" width=\"0\" height=\"0\" style=\"display:none;\"", "");
//        System.out.println(str);
        System.out.println(rmSomeScript(str));
    }
}
