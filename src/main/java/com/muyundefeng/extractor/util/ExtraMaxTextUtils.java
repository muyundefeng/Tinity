//package com.muyundefeng.extractor.util;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Created by lisheng on 17-5-10.
// */
//public class ExtraMainBodyUtils {
//    public static String select(String html) {
//        List<String> lines;
//        int blocksWidth = 1;
//        int threshold = 30;
//        int start;
//        int end;
//        StringBuilder text = new StringBuilder();
//        ArrayList<Integer> indexDistribution = new ArrayList<Integer>();
//
//        lines = Arrays.asList(html.split("\n"));
//
//        for (int i = 0; i < lines.size() - blocksWidth; i++) {
//            int wordsNum = 0;
//            for (int j = i; j < i + blocksWidth; j++) {
//                lines.set(j, lines.get(j).replaceAll("\\s+", ""));
//                wordsNum += lines.get(j).length();
//            }
//            indexDistribution.add(wordsNum);
//        }
//
//        start = -1;
//        end = -1;
//        boolean boolstart = false, boolend = false;
//        text.setLength(0);
//
//        for (int i = 0; i < indexDistribution.size() - 1; i++) {
//            if (indexDistribution.get(i) > threshold && !boolstart) {
//                if (indexDistribution.get(i + 1).intValue() != 0
//                        || indexDistribution.get(i + 2).intValue() != 0
//                        || indexDistribution.get(i + 3).intValue() != 0) {
//                    boolstart = true;
//                    start = i;
//                    continue;
//                }
//            }
//            if (boolstart) {
//                if (indexDistribution.get(i).intValue() == 0
//                        || indexDistribution.get(i + 1).intValue() == 0) {
//                    end = i;
//                    boolend = true;
//                }
//            }
//            StringBuilder tmp = new StringBuilder();
//            if (boolend) {
//                for (int ii = start; ii <= end; ii++) {
//                    if (lines.get(ii).length() < 5) continue;
//                    tmp.append(lines.get(ii) + "\n");
//                }
//                String str = tmp.toString();
//                if (str.contains("Copyright")) continue;
//                text.append(str);
//                boolstart = boolend = false;
//            }
//        }
//        return text.toString();
//    }
//}