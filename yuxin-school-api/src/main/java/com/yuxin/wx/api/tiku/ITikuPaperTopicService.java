package com.yuxin.wx.api.tiku;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.tiku.TikuPaperTopic;
import com.yuxin.wx.model.tiku.TikuTopic;

/**
 * Service Interface:TikuPaperTopic
 *
 * @author wang.zx
 * @date 2015-7-8
 */
public interface ITikuPaperTopicService {
    /**
     *
     * @Title: saveTikuPaperTopic
     * @Description: 新增TikuPaperTopic
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by wangzx
     */
    void insert(TikuPaperTopic entity);

    /**
     *
     * @Title: batchSaveTikuPaperTopic
     * @Description: 批量新增TikuPaperTopic
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by wangzx
     */
    void batchInsert(List<TikuPaperTopic> list);

    /**
     *
     * @Title: updateTikuPaperTopic
     * @Description: 编辑TikuPaperTopic
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by wangzx
     */
    void update(TikuPaperTopic entity);

    /**
     *
     * @Title: deleteTikuPaperTopicById
     * @Description: 根据id删除TikuPaperTopic
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by wangzx
     */
    void deleteTikuPaperTopicById(Integer id);

    /**
     *
     * @Title: deleteTikuPaperTopicByIds
     * @Description: 根据id批量删除TikuPaperTopic
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by wangzx
     */
    void deleteTikuPaperTopicByIds(Integer[] ids);

    /**
     *
     * @Title: findTikuPaperTopicById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by wangzx
     */
    TikuPaperTopic findTikuPaperTopicById(Integer id);

    /**
     *
     * @Title: findTikuPaperTopicByPage
     * @Description: 分页查询
     * @return
     * @return List<TikuPaperTopic> 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by wangzx
     */
    List<TikuPaperTopic> findTikuPaperTopicByPage(TikuPaperTopic search);

    /**
     *
     * Class Name: ITikuPaperTopicService.java
     *
     * @Description: 查看有没有关系
     * @author 周文斌
     * @date 2015-7-24 下午1:00:04
     * @version 1.0
     * @return
     */
    TikuPaperTopic findTopicPaperById(Map<String, Object> param);

    /**
     * @Description: 通过父级Id查询当前，材料题下面所有的子题
     * @author zx
     * @date 2015-8-6 下午6:12:15
     * @version 1.0
     * @param parentId
     * @return
     */
    List<TikuTopic> findTopicPaperByParentIdAndPaperId(Integer parentId, Integer paperId);

    /**
     *
     * Class Name: ITikuPaperTopicService.java
     *
     * @Description: 删除试卷试题关系
     * @author 周文斌
     * @date 2015-7-24 下午3:12:17
     * @version 1.0
     * @param param
     */
    void delPaperTopic(Map<String, Object> param);

    /**
     *
     * Class Name: ITikuPaperTopicService.java
     *
     * @Description: 查询试卷总题数
     * @author 周文斌
     * @date 2015-7-24 下午5:52:10
     * @version 1.0
     * @param paperId
     * @return
     */
    Integer findPaperCountById(Integer paperId);

    /**
     *
     * Class Name: ITikuPaperTopicService.java
     *
     * @Description: 根据试题id 查询 试卷关系
     * @author 周文斌
     * @date 2015-8-7 下午4:27:07
     * @version 1.0
     * @param topicId
     * @return
     */
    List<TikuPaperTopic> findListByTopicId(Integer topicId);

    /**
     *
     * Class Name: ITikuPaperTopicService.java
     *
     * @Description: 查询其他试卷是否使用该试题
     * @author 周文斌
     * @date 2015-8-10 下午4:08:01
     * @version 1.0
     * @return
     */
    List<TikuPaperTopic> findOtherByPaperTopic(Map<String, Object> param);

    /**
     *
     * Class Name: ITikuPaperTopicService.java
     *
     * @Description: 查询试卷下的试题关系
     * @author 周文斌
     * @date 2015-8-10 下午6:03:52
     * @version 1.0
     * @param paperId
     * @return
     */
    List<TikuPaperTopic> findTopicByPaperId(Integer paperId);

    Float findTopicScore(Map<String, Object> param);

    /**
     *
     * Class Name: ITikuPaperTopicService.java
     *
     * @Description: 查询 ids
     * @author 周文斌
     * @date 2015-9-16 下午6:52:16
     * @version 1.0
     * @param param
     * @return
     */
    List<Integer> findIdByType(Map<String, Object> param);

    /**
     *
     * Class Name: ITikuPaperTopicService.java
     *
     * @Description: 更改分数 根据id
     * @author 周文斌
     * @date 2015-9-16 下午6:55:36
     * @version 1.0
     * @param param
     */
    void updateByIds(Map<String, Object> param);

    /**
     *
     * Class Name: ITikuPaperTopicService.java
     *
     * @Description: 查询试卷详情
     * @author 周文斌
     * @date 2016-2-18 下午7:59:28
     * @version 1.0
     * @param param
     * @return
     */
    Integer findCount(Map<String, Object> param);

    /**
     *
     * Class Name: ITikuPaperTopicService.java
     *
     * @Description: 查询简答题和材料题总分数
     * @author 周文斌
     * @date 2016-2-18 下午8:18:50
     * @version 1.0
     * @param param
     * @return
     */
    Double findSumScore(Map<String, Object> param);

    /**
     * 通过类型和试卷id查询出对应的题关系数据
     * @param param
     * @return
     */
    List<TikuPaperTopic> findTikuPaperByType(Map<String, Object> param);
}