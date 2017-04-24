package com.yuxin.wx.api.tiku;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.tiku.TikuTopic;
import com.yuxin.wx.model.tiku.TikuTopicExampoint;
import com.yuxin.wx.model.tiku.TikuTopicOption;
import com.yuxin.wx.model.tiku.TikuUserExerciseAnswer;
import com.yuxin.wx.vo.tiku.TikuTopicVo;

/**
 * Service Interface:TikuTopic
 *
 * @author wang.zx
 * @date 2015-7-8
 */
public interface ITikuTopicService {
    /**
     *
     * @Title: saveTikuTopic
     * @Description: 新增TikuTopic
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by wangzx
     */
    void insert(TikuTopic entity);

    /**
     *
     * @Title: batchSaveTikuTopic
     * @Description: 批量新增TikuTopic
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by wangzx
     */
    void batchInsert(List<TikuTopic> list);

    /**
     *
     * @Title: updateTikuTopic
     * @Description: 编辑TikuTopic
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by wangzx
     */
    void update(TikuTopic entity);

    /**
     *
     * @Title: deleteTikuTopicById
     * @Description: 根据id删除TikuTopic
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by wangzx
     */
    void deleteTikuTopicById(Integer id);

    /**
     *
     * @Title: deleteTikuTopicByIds
     * @Description: 根据id批量删除TikuTopic
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by wangzx
     */
    void deleteTikuTopicByIds(Integer[] ids);

    /**
     *
     * @Title: findTikuTopicById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by wangzx
     */
    TikuTopic findTikuTopicById(Integer id);

    /**
     *
     * @Title: findTikuTopicByPage
     * @Description: 分页查询
     * @return
     * @return List<TikuTopic> 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by wangzx
     */
    List<TikuTopic> findTikuTopicByPage(TikuTopic search);

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
     * @Description: 查询试卷下的试题
     * @author 周文斌
     * @date 2015-8-25 下午4:59:16
     * @version 1.0
     * @param paperId
     * @return
     */
    List<TikuTopic> findByPaperId(Integer paperId, Integer userId, Integer exerId);

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

    /**
     * @Description: 根据材料题Id查询对应的子题
     * @author zx
     * @date 2015-8-6 下午9:04:20
     * @version 1.0
     * @param parentIds
     * @return
     */
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

    /**
     * @Description: 根据材料Id，查询该材料下所有的子题
     * @author zx
     * @date 2015-7-28 下午5:03:22
     * @version 1.0
     * @param param
     * @return
     */
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

    /**
     *
     * Class Name: ITikuTopicService.java
     *
     * @Description: 根据科目id删除试题
     * @author yuchanglong
     * @date 2015年9月9日 下午6:28:46
     * @version 1.0
     * @param subId
     */
    void delBySubId(Integer subId);

    /**
     *
     * Class Name: ITikuTopicService.java
     *
     * @Description: 查子题 及状态
     * @author 周文斌
     * @date 2015-9-6 下午7:43:57
     * @version 1.0
     * @param tuea
     * @return
     */
    List<TikuTopic> findListByParentId(TikuUserExerciseAnswer tuea);

    List<TikuTopic> findTikuTopicListByParentId(Integer parentId);

    TikuTopicVo findTopicById(Integer id);

    /**
     *
     * Class Name: ITikuTopicService.java
     *
     * @Description: 根据练习id 查询试题
     * @author 周文斌
     * @date 2015-8-27 上午11:56:46
     * @version 1.0
     * @param batchId
     * @return
     */
    List<TikuTopic> findTopicByBatchId(Integer batchId, Integer userId, Integer exerId);

    Integer insertTopic(TikuTopic topic, List<TikuTopicOption> optionList, TikuTopicExampoint point, Integer paperId, Float topicScore);
}