package muyundefeng.test;

import muyundefeng.input.InputDocument;
import muyundefeng.trinity.CreateTrinity;
import muyundefeng.trinity.LearnTemplate;
import muyundefeng.trinity.Node;
import muyundefeng.trinity.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 三叉树测试程序
 *
 * @author lisheng
 */
public class TestTrinity {

    private static Logger logger = LoggerFactory.getLogger(TestTrinity.class);

    public static boolean isVariable(Node node) {
        boolean isVariable = true;
        List<Text> texts = node.getTexts();
        for (Text text : texts) {
            if (text.getText().contains("<"))
                isVariable &= true;
            else {
                isVariable = false;
                break;
            }
        }
        return isVariable;
    }

    public static boolean isLeaf(Node node) {
        boolean isLeaf = false;
        if (node != null) {
            if (node.getPreffixNode() == null && node.getSeparatorNode() == null
                    && node.getSuffixNode() == null) {
                isLeaf = true;
            }
        }
        System.out.println(isLeaf);
        return isLeaf;
    }

    //遍历构建三叉树
    public static void preScanTrinity(Node node) {
        List<Text> texts = node.getTexts();
        System.out.println("-----------------");
        if (node.getPattern() != null)
            System.out.println(node.getPattern().getString());
        for (Text text : texts) {
            System.out.println(text.getText());
        }
        if (node.getPreffixNode() != null) {
            preScanTrinity(node.getPreffixNode());
        }
        if (node.getSeparatorNode() != null) {
            preScanTrinity(node.getSeparatorNode());
        }
        if (node.getSuffixNode() != null) {
            preScanTrinity(node.getSuffixNode());
        }
    }

    public static void main(String[] args) throws IOException {
        List<Text> texts = null;
        try {
            texts = InputDocument.getDefaultReadHtml();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Node node = new Node();
        String string = texts.get(1).getText();

        node.setTexts(texts);
        CreateTrinity trinity = new CreateTrinity();
        trinity.createTrinity(node);
        preScanTrinity(node);

//
//        LearnTemplate learnTemplate = new LearnTemplate();
//        String result = learnTemplate.learnTemplate(node, "");
//        logger.info("result=" + result);
//        System.out.println(result);
//        System.out.println("string=" + string);
//        Pattern pat = Pattern.compile("<html><head>	<title>results</title></head><body>	<h1>Result:</h1>.*<br/><b>");
//        Matcher mat = pat.matcher(string);
//        //boolean rs = mat.find();
//        while (mat.find()) {
//            System.out.println(mat.group());
//        }

    }
}
