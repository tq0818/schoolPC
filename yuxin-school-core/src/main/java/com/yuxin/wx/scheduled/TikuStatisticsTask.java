package com.yuxin.wx.scheduled;

import com.yuxin.wx.api.company.ICompanyEmailHistoryService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticDayService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.tiku.*;
import com.yuxin.wx.model.company.CompanyEmailHistory;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.company.CompanyServiceStaticDay;
import com.yuxin.wx.model.tiku.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 
 * @ClassName: EmailStatisticsTask
 * @Description: 邮件定时任务
 * @author 周文斌
 * @date 2015-5-21 下午12:44:43
 * @version 1.0
 */
@Component
public class TikuStatisticsTask{
	@Autowired
	private ITikuUserExerciseAnswerService tikuUserExerciseAnswerServiceImpl;
	@Autowired
	private ITikuPaperTopicService tikuPaperTopicServiceImpl;
	@Autowired
	private ITikuPaperService tikuPaperServiceImpl;
	@Autowired
	private ITikuTopicOptionService tikuTopicOptionService;
	@Autowired
	private ITikuUserExerciseAnswerStatisticsService tikuUserExerciseAnswerStatisticsServiceImpl;
	@Autowired
	private ITikuUserExerciseAnswerAccuracyService tikuUserExerciseAnswerAccuracyServiceImpl;

	private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

	private static String exerciseType = "EXERCISE_TYPE_PAPER";

	/**
	 * 试卷使用情况统计
	 * @throws Exception
	 */
	public void tikuStatistics() throws Exception{
        List<TikuPaper> tikuPaperList = tikuPaperServiceImpl.queryAll();
        //查询原始做备份
        List<Integer> statisticsList = tikuUserExerciseAnswerStatisticsServiceImpl.queryAllData();
        List<Integer> accuracyList = tikuUserExerciseAnswerAccuracyServiceImpl.queryAllData();

        for(TikuPaper tikuPaper:tikuPaperList){
            //统计单选题
            Map<String, Object> papmMap = new HashMap<String, Object>();
            papmMap.put("paperId", tikuPaper.getId());
            List<String> topicType = new ArrayList<String>();
            papmMap.put("topicType", topicType);
            List<TikuPaperTopic> tikuPaperTopicList = tikuPaperTopicServiceImpl.findTikuPaperByType(papmMap);
            long start = System.currentTimeMillis();
			for(TikuPaperTopic tikuPaperTopic:tikuPaperTopicList){
				fixedThreadPool.submit(new RuntimeStatistics(tikuPaperTopic));
			}
			long end = System.currentTimeMillis();
			System.out.println("查询时间:"+(end-start)+"；查询个数:"+tikuPaperTopicList.size());
		}
		//所有任务处理完成后，清空之前数据
		while(true){
			System.out.println(getExcutorPoolActiveCount(fixedThreadPool));
			if(getExcutorPoolActiveCount(fixedThreadPool) == 0){
				if(statisticsList.size()>0){
					Integer[] a = new Integer[statisticsList.size()];
					tikuUserExerciseAnswerStatisticsServiceImpl.deleteByIds(statisticsList.toArray(a));
				}
				if(statisticsList.size()>0){
					Integer[] b = new Integer[accuracyList.size()];
					tikuUserExerciseAnswerAccuracyServiceImpl.deleteByIds(accuracyList.toArray(b));
				}
				break;
			}
		}
	}

	private Integer getExcutorPoolActiveCount(ExecutorService GET_TASK_POOL) {
		Integer runnerNum = 0;
		if (GET_TASK_POOL != null) {
			runnerNum = ((ThreadPoolExecutor) GET_TASK_POOL).getActiveCount();
		}
		return runnerNum;
	}

	public class RuntimeStatistics implements Runnable{
		private TikuPaperTopic tikuPaperTopic = null;
		public RuntimeStatistics(TikuPaperTopic tikuPaperTopic){
			this.tikuPaperTopic = tikuPaperTopic;
		}
		@Override
		public void run(){
			Map<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("exerciseType", exerciseType);
			parameterMap.put("topicType", tikuPaperTopic.getTopicType());
			parameterMap.put("topicId", tikuPaperTopic.getTopicId());
			parameterMap.put("paperId", tikuPaperTopic.getPaperId());
			//查询答案
			List<TikuTopicOption> tikuTopicOptionList = tikuTopicOptionService.findOptionByTopicId(tikuPaperTopic.getTopicId());
			Map<String, Integer> map = new HashMap<String, Integer>();
			Map<String, Integer> tikuTopicOptionMap = new HashMap<String, Integer>();
			for(TikuTopicOption tikuTopicOption:tikuTopicOptionList){
				map.put(tikuTopicOption.getOptionNo(), 0);
				tikuTopicOptionMap.put(tikuTopicOption.getOptionNo(), tikuTopicOption.getId());
			}
			//查询用户答题结果
			List<TikuUserExerciseAnswer> tikuUserExerciseAnswerList = tikuUserExerciseAnswerServiceImpl.findTikuUserExerciseAnswer(parameterMap);

			int accuracyNum = 0;
			if("TOPIC_TYPE_RADIO".equals(tikuPaperTopic.getTopicType())){//单选
				for(TikuUserExerciseAnswer tikuUserExerciseAnswer:tikuUserExerciseAnswerList){
					if(map.containsKey(String.valueOf(tikuUserExerciseAnswer.getUserAnswer()))){
						map.put(String.valueOf(tikuUserExerciseAnswer.getUserAnswer()), map.get(tikuUserExerciseAnswer.getUserAnswer())+1);
					}
					if(Integer.valueOf(1).equals(tikuUserExerciseAnswer.getCorrectFlag())){
						accuracyNum += 1;
					}
				}
				//存储统计选择题的数据表
				Set<String> set = map.keySet();
				for(String s:set){
					statisticsModel(tikuPaperTopic.getPaperId(),exerciseType,tikuPaperTopic.getTopicId(), tikuPaperTopic.getTopicType(), tikuTopicOptionMap.get(s), s, map.get(s));
				}
			}else if("TOPIC_TYPE_MULTIPLE".equals(tikuPaperTopic.getTopicType()) || "TOPIC_TYPE_UNDEFINED".equals(tikuPaperTopic.getTopicType())){//多选或不定项
				for(TikuUserExerciseAnswer tikuUserExerciseAnswer:tikuUserExerciseAnswerList){
					char[] uc = tikuUserExerciseAnswer.getUserAnswer().toCharArray();
					for(int i=0; i<uc.length; i++){
						if(map.containsKey(String.valueOf(uc[i]))){
							map.put(String.valueOf(uc[i]), map.get(String.valueOf(uc[i]))+1);
						}
					}
					if(Integer.valueOf(1).equals(tikuUserExerciseAnswer.getCorrectFlag())){
						accuracyNum += 1;
					}
				}
				//存储统计选择题的数据表
				Set<String> set = map.keySet();
				for(String s:set){
					statisticsModel(tikuPaperTopic.getPaperId(),exerciseType,tikuPaperTopic.getTopicId(), tikuPaperTopic.getTopicType(), tikuTopicOptionMap.get(s), s, map.get(s));
				}
			}else{
				for(TikuUserExerciseAnswer tikuUserExerciseAnswer:tikuUserExerciseAnswerList){
					if(Integer.valueOf(1).equals(tikuUserExerciseAnswer.getCorrectFlag())){
						accuracyNum += 1;
					}
				}
			}
			statisticsAccuracy(tikuPaperTopic.getPaperId(),exerciseType,tikuPaperTopic.getTopicId(), tikuPaperTopic.getTopicType(), tikuUserExerciseAnswerList.size(), accuracyNum);
		}


		private void statisticsModel(Integer paperId, String exerciseType, Integer topicId,
									 String topicType, Integer topicOptionId, String topicOptionAnswer, Integer topicOptionAnswerNum){
			//组装
			TikuUserExerciseAnswerStatistics statistics = new TikuUserExerciseAnswerStatistics();
			statistics.setPaperId(paperId);
			statistics.setExerciseType(exerciseType);
			statistics.setTopicId(topicId);
			statistics.setTopicType(topicType);
			statistics.setTopicOptionId(topicOptionId);
			statistics.setTopicOptionAnswer(topicOptionAnswer);
			statistics.setTopicOptionAnswerNum(topicOptionAnswerNum);
            statistics.setCreateDate(new Date());
			tikuUserExerciseAnswerStatisticsServiceImpl.insert(statistics);
		}

		private void statisticsAccuracy(Integer paperId, String exerciseType, Integer topicId, String topicType,
										Integer answerNum, Integer answerAccuracyNum){
			//组装
			TikuUserExerciseAnswerAccuracy accuracy = new TikuUserExerciseAnswerAccuracy();
			accuracy.setPaperId(paperId);
			accuracy.setExerciseType(exerciseType);
			accuracy.setTopicId(topicId);
			accuracy.setTopicType(topicType);
			accuracy.setAnswerNum(answerNum);
			accuracy.setAnswerAccuracyNum(answerAccuracyNum);
			accuracy.setCreateDate(new Date());
			tikuUserExerciseAnswerAccuracyServiceImpl.insert(accuracy);
		}
	}
}
