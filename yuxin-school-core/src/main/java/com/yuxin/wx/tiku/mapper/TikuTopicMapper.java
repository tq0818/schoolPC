package com.yuxin.wx.tiku.mapper;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuTopic;
import com.yuxin.wx.model.tiku.TikuTopicExampoint;
import com.yuxin.wx.model.tiku.TikuTopicOption;
import com.yuxin.wx.model.tiku.TikuUserExerciseAnswer;
import com.yuxin.wx.vo.tiku.TikuTopicVo;

/**
 * Service Interface:Users
 *
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuTopicMapper extends BaseMapper<TikuTopic> {

    /**
     *
     * Class Name: ITikuTopicService.java
     *
     * @Description: 根据条件分页
     * @author 周文斌
     * @date 2015-7-9 下午4:27:38
     * @version 1.0
     * @param topic
     * @return
     */
    List<TikuTopic> findTopicByEntity(TikuTopic topic);

    /**
     *
     * Class Name: ITikuTopicService.java
     *
     * @Description: 数量
     * @author 周文斌
     * @date 2015-7-9 下午4:27:52
     * @version 1.0
     * @param topic
     * @return
     */
    Integer fingCountByEntity(TikuTopic topic);

    /**
     *
     * Class Name: ITikuTopicService.java
     *
     * @Description: 查询试题及选项
     * @author 周文斌
     * @date 2015-7-10 上午10:33:47
     * @version 1.0
     * @param id
     * @return
     */
    TikuTopicVo findTopicAndOptionById(Integer id);

    /**
     *
     * Class Name: ITikuTopicService.java
     *
     * @Description: 查询下一个id
     * @author 周文斌
     * @date 2015-7-16 下午6:37:59
     * @version 1.0
     * @param param
     * @return
     */
    Integer findNextId(Map<String, Object> param);

    /**
     *
     * Class Name: ITikuTopicService.java
     *
     * @Description: 查询试卷下的试题
     * @author 周文斌
     * @date 2015-7-23 下午8:35:02
     * @version 1.0
     * @param param
     * @return
     */
    List<TikuTopic> findTopicByPaperId(Map<String, Object> param);

    List<TikuTopic> findCaseChildTopicByParentIds(String[] parentIds);

    /**
     *
     * Class Name: ITikuTopicService.java
     *
     * @Description: 查询已存在试题
     * @author 周文斌
     * @date 2015-7-29 下午6:11:01
     * @version 1.0
     * @param param
     * @return
     */
    List<TikuTopic> findExistTopic(Map<String, Object> param);

    /**
     *
     * Class Name: ITikuTopicService.java
     *
     * @Description: 查询已存在试题分页总数
     * @author 周文斌
     * @date 2015-7-29 下午6:19:03
     * @version 1.0
     * @return
     */
    Integer findCountExistTopic(Map<String, Object> param);

    List<TikuTopic> findChildTopicByParams(Map<String, Object> param);

    /**
     *
     * Class Name: ITikuTopicService.java
     *
     * @Description: 查询所有子题id
     * @author 周文斌
     * @date 2015-8-10 下午5:27:57
     * @version 1.0
     * @param parentId
     * @return
     */
    List<Integer> findSubjectByParentId(Integer parentId);

    /**
     *
     * Class Name: ITikuTopicService.java
     *
     * @Description: 组团删除
     * @author 周文斌
     * @date 2015-8-10 下午5:37:46
     * @version 1.0
     * @param list
     */
    void delByIds(List<Integer> list);

    void delBySubId(Integer subId);

    /**
     *
     * Class Name: TikuTopicMapper.java
     *
     * @Description:根据题库类别表删除试题
     * @author yuchanglong
     * @date 2015年9月10日 下午12:31:10
     * @version 1.0
     * @param tikuId
     */
    void delByTikuId(Integer tikuId);

    List<TikuTopic> findByPaperId(Integer paperId, Integer userId, Integer exerId);

    List<TikuTopic> findListByParentId(TikuUserExerciseAnswer tuea);

    List<TikuTopic> findTikuTopicListByParentId(Integer parentId);

    TikuTopicVo findTopicById(Integer id);

    List<TikuTopic> findTopicByBatchId(Integer batchId, Integer userId, Integer exerId);

    Integer insertTopic(TikuTopic topic, List<TikuTopicOption> optionList, TikuTopicExampoint point, Integer paperId, Float topicScore);

}