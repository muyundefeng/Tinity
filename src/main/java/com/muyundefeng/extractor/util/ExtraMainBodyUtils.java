package com.muyundefeng.extractor.util;


import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据文本的标签<p></p>提取新闻类主体
 * Created by lisheng on 17-5-10.
 */
public class ExtraMainBodyUtils {

    @SuppressWarnings("Duplicates")
    public static String extraMainBody(String html, String label) {
        List<String> lines;
        int blocksWidth = 2;//设置连续的文件块(这里每行代表一个文件块)
        int threshold = 1;//在连续文件块中出现label标签的最小数目
        int start;
        int end;
        StringBuilder text = new StringBuilder();
        ArrayList<Integer> indexDistribution = new ArrayList<Integer>();

        lines = new ArrayList<String>();
        String segs[] = html.split("\\n");
        for (String seg : segs) {
            if (!seg.matches("\\s*")) {
                lines.add(seg);
            }
        }
        for (int i = 0; i < lines.size() - blocksWidth; i++) {
            int lablesNum = 0;
            for (int j = i; j < i + blocksWidth; j++) {
                int count = StringUtils.countMatches(lines.get(j), label);
                lablesNum += count;
            }
            indexDistribution.add(lablesNum);
        }
        start = -1;
        end = -1;
        boolean boolstart = false, boolend = false;
        text.setLength(0);
        for (int i = 0; i < indexDistribution.size() - blocksWidth; i++) {
            if (indexDistribution.get(i) > threshold && !boolstart) {
                if (indexDistribution.get(i + 1).intValue() != 0
                        || indexDistribution.get(i + 2).intValue() != 0
                        || indexDistribution.get(i + 3).intValue() != 0) {
                    boolstart = true;
                    start = i;
                    continue;
                }
            }
            if (boolstart) {
                if (indexDistribution.get(i).intValue() == 0
                        || indexDistribution.get(i + 1).intValue() == 0) {
                    end = i;
                    boolend = true;
                }
            }
            StringBuilder tmp = new StringBuilder();
            if (boolend) {
                for (int ii = start; ii <= end; ii++) {
                    if (lines.get(ii).length() < 5) continue;
                    tmp.append(lines.get(ii) + "\n");
                }
                String str = tmp.toString();
                if (str.contains("Copyright")) continue;
                text.append(str);
                boolstart = boolend = false;
            }
        }
        return text.toString();
    }

    public static void main(String[] args) {
        String str = "<div>\n" +
                "  <ul>\n" +
                "<li><em></em><a>首页</a></li>\n" +
                "<li><a>新闻</a></li>\n" +
                "\n" +
                "<li><a>体育</a></li>\n" +
                "<li><a>娱乐</a></li>\n" +
                "<li><a>视频</a></li>\n" +
                "<li><a>财经</a></li>\n" +
                "<li><a>证券</a></li>\n" +
                "<li><a>汽车</a></li>\n" +
                "<li><a>房产</a></li>\n" +
                "<li><a>科技</a></li>\n" +
                "<li><a>数码</a></li>\n" +
                "<li><a>游戏</a></li>\n" +
                "<li><a>教育</a></li>\n" +
                "<li><a>时尚</a></li>\n" +
                "<li><a>文化</a></li>\n" +
                "<li>\n" +
                "  <a><span>更多</span></a>\n" +
                "    <div>\n" +
                "      <a>动漫</a>\n" +
                "      <a>读书</a>\n" +
                "      <a>儿童</a> \n" +
                "      <a>星座</a>\n" +
                "      <a>精品课</a> \n" +
                "      <a>全部频道</a>\n" +
                "    </div>\n" +
                "</li>\n" +
                "  </ul>\n" +
                "</div>";
        System.out.println(extraMainBody(str, "<a>"));
    }
}
