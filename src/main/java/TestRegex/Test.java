package TestRegex;

import com.muyundefeng.extractor.trinity.Text;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
	public static int realStartIndex(Text text, int start){
		String raw = text.getText();
		int length = 0;
		if(raw != null){
			if(raw.startsWith("<")){
				
			}
			else{
				length = raw.indexOf("<");
				System.out.println("length="+length);
				raw = raw.substring(length);
			}
		}
		Pattern pattern = Pattern.compile("<[^>]*>");  
		Matcher matcher = pattern.matcher(raw);  
		int realStartIndex = 0;
		int count = 0;
		//int realEndIndex = 0;
		String rawTemp = "";
		String temp = "";
		String match = "";
		String endMatch = "";
		while(matcher.find()){
			if(count != (start + 1) )
			{
				temp = match;
				match = matcher.group();
				endMatch = match;
				System.out.println("matches="+match+","+"temp="+temp);
				
				count ++;
				realStartIndex += match.length();
				String betweenTokenStr = StringUtils.substringBetween(rawTemp,temp, match);
				System.out.println("betweenTokenStr="+betweenTokenStr);
				if(betweenTokenStr!=null&&betweenTokenStr.length()>0){
					
					realStartIndex += betweenTokenStr.length();
				}
				rawTemp = raw;
				raw = raw.replaceFirst("<[^>]*>", "");
			}
			else
				break;
		}
		return realStartIndex + length -endMatch.length() ;
	}
	public static int realEndIndex(Text text, int end){
		String raw = text.getText();
		int length = 0;
		if(raw != null){
			if(raw.startsWith("<")){
				
			}
			else{
				length = raw.indexOf("<");
				System.out.println("length="+length);
				raw = raw.substring(length);
				System.out.println(raw);
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
		while(matcher.find()){
			if(count != end)
			{
				temp = match;
				match = matcher.group();
				//endMacth = match;
				count ++;
				realEndIndex += match.length();
				//System.out.println(realEndIndex);
				//System.out.println("matches="+match+","+"temp="+temp);				
				String betweenTokenStr = StringUtils.substringBetween(rawTemp,temp, match);
				//System.out.println(betweenTokenStr);
				if(betweenTokenStr!=null&&betweenTokenStr.length()>0){
					//System.out.println(betweenTokenStr.length());
					realEndIndex += betweenTokenStr.length();
				}
				rawTemp = raw;
				raw = raw.replaceFirst("<[^>]*>", "");
			}
			else
				break;
		}
		System.out.println("realEndIndex="+realEndIndex);
		return realEndIndex;
	}
	public static void test(List<String> list){
		if(list == null)
			System.out.println("yes");
		else
			System.out.println("no");
	}
	public static String strExitAndAfterPattern(Text text, String pattern, String sperator){
		String betweenTextSep = StringUtils.substringBetween(text.getText(),sperator,pattern );

		if(betweenTextSep.length()>0){
			return betweenTextSep;
		}
		else {
			return "";
		}
		
	}
	/**查看分隔符前面，如果存在则返回相关的字符串信息
	 * @param text　
	 * @param pattern
	 * @return
	 */
	public static String strExitAndBeforePattern(Text text, String pattern, String sperator){
		String betweenTextSep = StringUtils.substringBetween(text.getText(), pattern,sperator );
		if(betweenTextSep.length()>0){
			return betweenTextSep;
		}
		else {
			return "";
		}
		
	} 
	public static void main(String[] args) {
		String str = "<title>(.*)</title><ul>客户端搜索频道</ul><a>下一篇</a><input>正文<a></a>(.*)<em></em>关注新华网微信微博<a></a>(.*)<a></a><ul></ul>加载更多<ul><a>(.*)</a><a>(.*)</a></ul>炫图 视频<ul><a>(.*)</a><a>(.*)</a><a>(.*)</a><a>(.*)</a><a>(.*)</a><a>(.*)</a></ul>热词<a><a>  </a><a>  </a><a>  </a></a>(.*)</body>";
		String str1 = "<title>4月工业增加值增长6.5% 商品房待售面积进一步减少-新华网</title><ul>客户端搜索频道</ul><a>下一篇</a><input>正文<a></a>4月工业增加值增长6.5% 商品房待售面积进一步减少 2017-05-15 10:13:16来源：<em></em>关注新华网微信微博<a></a>图集    4月份，各地区各部门认真贯彻落实党中央国务院决策部署，国民经济继续保持总体平稳、稳中向好的发展态势。生产领域稳定增长，市场需求不断扩大，就业形势持续向好，消费物价温和上涨，供给侧结构性改革深入推进，新旧动能加快转换，经济运行质量效益进一步改善。    一、工业稳定增长，企业利润增长较快    4月份，全国规模以上工业增加值同比实际增长6.5%，增速比上月回落1.1个百分点，比上年同月加快0.5个百分点。分经济类型看，国有控股企业增加值同比增长5.6%，集体企业增长1.9%，股份制企业增长6.9%，外商及港澳台商投资企业增长5.5%。分三大门类看，采矿业增加值同比下降0.4%，制造业增长6.9%，电力、热力、燃气及水生产和供应业增长7.8%。工业结构继续优化，高技术产业和装备制造业增加值同比分别增长12.3%和10.3%，增速分别比规模以上工业快5.8和3.8个百分点。规模以上工业企业产销率达到97.6%。从环比看，4月份全国规模以上工业增加值比上月增长0.56%。1-4月份，全国规模以上工业增加值同比增长6.7%。    1-3月份，全国规模以上工业企业实现利润总额17043亿元，同比增长28.3%。规模以上工业企业每百元主营业务收入中的成本为85.25元，同比减少0.15元；主营业务收入利润率为6.13%，同比提高0.68个百分点。3月末，规模以上工业企业资产负债率为56.2%，同比下降0.7个百分点。    二、服务业保持较快增长，景气继续扩张    4月份，全国服务业生产指数同比增长8.1%，增速比上月回落0.2个百分点，今年以来一直保持在8%以上较高水平。1-4月份，全国服务业生产指数同比增长8.2%。其中，信息传输、软件和信息技术服务业，交通运输、仓储和邮政业，租赁和商务服务业保持较快增长态势。    4月份，服务业商务活动指数为52.6%，比上月回落1.6个百分点，比上年同月提高0.1个百分点，继续保持在50%的临界值以上。其中，零售业、铁路运输业、航空运输业、邮政业、住宿业、电信广播电视和卫星传输服务、互联网及软件信息技术服务等行业商务活动指数均位于55%及以上的较高景气区间。    三、固定资产投资增速平稳，基础设施投资增长较快    1-4月份，全国固定资产投资（不含农户）144327亿元，同比增长8.9%，增速比1-3月份回落0.3个百分点。其中，国有控股投资51476亿元，增长13.8%；民间投资88053亿元，增长6.9%，占全部投资的比重为61.0%。分产业看，第一产业投资3931亿元，同比增长19.1%；第二产业投资54596亿元，增长3.5%，其中制造业投资45379亿元，增长4.9%；第三产业投资85801亿元，增长12.1%。基础设施投资29789亿元，增长23.3%。高技术制造业投资增长22.6%，增速快于全部投资13.7个百分点。固定资产投资到位资金155793亿元，同比下降1.4%。新开工项目计划总投资107911亿元，同比下降5.9%。从环比看，4月份全国固定资产投资（不含农户）比上月增长0.71%。    四、房地产开发投资继续增加，商品房待售面积进一步减少    1-4月份，全国房地产开发投资27732亿元，同比增长9.3%，增速比1-3月份加快0.2个百分点，其中住宅投资增长10.6%。房屋新开工面积48240万平方米，同比增长11.1%，其中住宅新开工面积增长17.5%。全国商品房销售面积41655万平方米，同比增长15.7%，其中住宅销售面积增长13.0%。全国商品房销售额33223亿元，同比增长20.1%，其中住宅销售额增长16.1%。房地产开发企业土地购置面积5528万平方米，同比增长8.1%。4月末，全国商品房待售面积67469万平方米，比3月末减少1341万平方米。1-4月份，房地产开发企业到位资金47221亿元，同比增长11.4%。    五、市场销售稳定增长，网上零售比重提升    4月份，社会消费品零售总额27278亿元，同比增长10.7%，增速比上月回落0.2个百分点，比上年同月加快0.6个百分点。按经营单位所在地分，城镇消费品零售额23483亿元，同比增长10.4%；乡村消费品零售额3795亿元，增长12.6%。按消费类型分，餐饮收入2886亿元，同比增长11.1%；商品零售24393亿元，增长10.6%，其中限额以上单位商品零售11532亿元，增长9.3%。从环比看，4月份社会消费品零售总额比上月增长0.79%。1-4月份，社会消费品零售总额同比增长10.2%。    1-4月份，全国网上零售额19180亿元，同比增长32.0%。其中，实物商品网上零售额14617亿元，增长25.9%，占社会消费品零售总额的比重为12.9%，比上年同期提高1.8个百分点。    六、居民消费价格温和上涨，工业生产者价格涨势放缓    4月份，全国居民消费价格同比上涨1.2%，涨幅比上月扩大0.3个百分点。分类别看，食品烟酒价格同比下降1.8%，衣着上涨1.3%，居住上涨2.4%，生活用品及服务上涨0.8%，交通和通信上涨1.8%，教育文化和娱乐上涨2.6%，医疗保健上涨5.7%，其他用品和服务上涨3.4%。在食品烟酒价格中，粮食价格上涨1.5%，猪肉价格下降8.1%，鲜菜价格下降21.6%。4月份，居民消费价格环比上涨0.1%。1-4月份，全国居民消费价格同比上涨1.4%。    4月份，全国工业生产者出厂价格同比上涨6.4%，涨幅比上月回落1.2个百分点，环比下降0.4%。1-4月份，全国工业生产者出厂价格同比上涨7.2%。4月份，全国工业生产者购进价格同比上涨9.0%，环比下降0.3%。1-4月份，全国工业生产者购进价格同比上涨9.3%。    七、进出口较快增长，机电产品出口份额较高    4月份，进出口总额22205亿元，同比增长16.2%。其中，出口12414亿元，增长14.3%；进口9791亿元，增长18.6%。进出口相抵，贸易顺差2623亿元。1-4月份，进出口总额84205亿元，同比增长20.3%。其中，出口45678亿元，增长14.7%，机电产品出口占出口总额的57.7%；进口38527亿元，增长27.8%。    4月份，规模以上工业企业实现出口交货值10192亿元，同比增长10.8%。1-4月份，规模以上工业企业实现出口交货值38306亿元，同比增长10.3%。    总的来看，4月份经济运行延续了稳中向好的态势，积极因素继续增加。但也要看到，国内外环境依然复杂多变，结构性矛盾尚未根本缓解，一些新情况新问题需要密切关注。下阶段，要在以习近平同志为核心的党中央坚强领导下，坚持稳中求进工作总基调，坚持以供给侧结构性改革为主线，继续适度扩大总需求，大力深化创新驱动，注重加强预期引导，全面做好稳增长、促改革、调结构、惠民生、防风险各项工作，确保经济平稳健康发展。    附注    （1）规模以上工业增加值及其分类项目增长速度按可比价计算，为实际增长速度；其他指标除特殊说明外，均按现价计算，为名义增长速度。    （2）规模以上工业统计范围为年主营业务收入2000万元及以上的工业企业。    （3）为及时反映服务业经济活动的月度运行态势，国家统计局编制了服务业生产指数，并自2017年3月份起按月度发布。服务业生产指数是指剔除价格因素后，反映服务业报告期相对于基期的产出变化。    （4）社会消费品零售总额统计中限额以上单位是指年主营业务收入2000万元及以上的批发业企业（单位）、500万元及以上的零售业企业（单位）、200万元及以上的住宿和餐饮业企业（单位）。    网上零售额是指通过公共网络交易平台（包括自建网站和第三方平台）实现的商品和服务零售额之和。商品和服务包括实物商品和非实物商品（如虚拟商品、服务类商品等）。    社会消费品零售总额包括实物商品网上零售额，不包括非实物商品网上零售额。    （5）进出口数据来源于海关总署。    （6）部分数据因四舍五入的原因，存在总计与分项合计不等的情况。+1<a></a>责任编辑：刘绪尧新闻评论<ul></ul>加载更多<ul><a>【影巢周刊】春夏交替感受多彩生活</a><a>往期汇总</a></ul>炫图 视频<ul><a>   收割水稻</a><a>   中国陶瓷微书亮相日内瓦</a><a>   </a><a>   西双版纳现龙卷风天气</a></ul>热词<a><a>  </a><a>  </a><a>  </a></a>  4月工业增加值增长6.5% 商品房待售面积进一步减少  \uFEFF 010030090900000000000000011109231296048231   </body>";
		System.out.println(str1.matches(str));
	}
}
