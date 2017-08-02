package com.yuxin.wx.scheduled;

import com.yuxin.wx.api.company.ICompanyEmailHistoryService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticDayService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.tiku.ITikuPaperService;
import com.yuxin.wx.api.tiku.ITikuPaperTopicService;
import com.yuxin.wx.api.tiku.ITikuTopicOptionService;
import com.yuxin.wx.api.tiku.ITikuUserExerciseAnswerService;
import com.yuxin.wx.model.company.CompanyEmailHistory;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.company.CompanyServiceStaticDay;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.model.tiku.TikuPaperTopic;
import com.yuxin.wx.model.tiku.TikuTopicOption;
import com.yuxin.wx.model.tiku.TikuUserExerciseAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 
 * @ClassName: EmailStatisticsTask
 * @Description: 邮件定时任务
 * @author 周文斌
 * @date 2015-5-21 下午12:44:43
 * @version 1.0
 */
@Component
public class TikuStatisticsTask {
	@Autowired
	private ITikuUserExerciseAnswerService tikuUserExerciseAnswerServiceImpl;
	@Autowired
	private ITikuPaperTopicService tikuPaperTopicServiceImpl;
	@Autowired
	private ITikuPaperService tikuPaperServiceImpl;
	@Autowired
	private ITikuTopicOptionService tikuTopicOptionService;

	/**
	 * 试卷使用情况统计
	 * @throws Exception
	 */
	public void tikuStatistics() throws Exception{
		List<TikuPaper> tikuPaperList = tikuPaperServiceImpl.queryAll();
		for(TikuPaper tikuPaper:tikuPaperList){
			//统计单选题
			Map<String, Object> papmMap = new HashMap<String, Object>();
			papmMap.put("paperId", tikuPaper.getId());
			List<String> topicType = new ArrayList<String>();
			topicType.add("TOPIC_TYPE_RADIO");
			topicType.add("TOPIC_TYPE_MULTIPLE");
			papmMap.put("topicType", topicType);
			List<TikuPaperTopic> tikuPaperTopicList = tikuPaperTopicServiceImpl.findTikuPaperByType(papmMap);
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			for(TikuPaperTopic tikuPaperTopic:tikuPaperTopicList){
				parameterMap.put("exerciseType", "EXERCISE_TYPE_PAPER");
				parameterMap.put("topicType", tikuPaperTopic.getTopicType());
				parameterMap.put("topicId", tikuPaperTopic.getTopicId());
				parameterMap.put("paperId", tikuPaper.getId());
				//查询答案
				List<TikuTopicOption> tikuTopicOptionList = tikuTopicOptionService.findOptionByTopicId(tikuPaperTopic.getTopicId());
				Map<String, Object> map = new HashMap<String, Object>();
				for(TikuTopicOption tikuTopicOption:tikuTopicOptionList){
					map.put(tikuTopicOption.getOptionNo(), 0);
				}
				long l = System.currentTimeMillis();
				//查询用户答题结果
				List<TikuUserExerciseAnswer> tikuUserExerciseAnswerList = tikuUserExerciseAnswerServiceImpl.findTikuUserExerciseAnswer(parameterMap);
				long l1 = System.currentTimeMillis();
				System.out.println("查询时间:"+(l1-l)+"；查询个数:"+tikuUserExerciseAnswerList.size());

				long ll = System.currentTimeMillis();
				for(TikuUserExerciseAnswer tikuUserExerciseAnswer:tikuUserExerciseAnswerList){
					char[] userAnswerChar = tikuUserExerciseAnswer.getUserAnswer().toCharArray();
					for(int i=0; i<userAnswerChar.length; i++){
						if(map.containsKey(String.valueOf(userAnswerChar[i]))){
							map.put(String.valueOf(userAnswerChar[i]), map.get(String.valueOf(userAnswerChar[i])));
						}
					}
				}
				long ll1 = System.currentTimeMillis();
				System.out.println("处理时间:"+(ll1-ll)+"；处理个数:"+tikuUserExerciseAnswerList.size());
			}
		}
	}
	
}
