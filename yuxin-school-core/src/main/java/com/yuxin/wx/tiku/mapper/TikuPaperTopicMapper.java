package com.yuxin.wx.tiku.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuPaperTopic;
import com.yuxin.wx.model.tiku.TikuTopic;

/**
 * Service Interface:Users
 *
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuPaperTopicMapper extends BaseMapper<TikuPaperTopic> {

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
     * @Description: 根据父类Id获取当前材料的所有子题
     * @author zx
     * @date 2015-8-6 下午6:13:52
     * @version 1.0
     * @param parentId
     * @return
     */
    List<TikuTopic> findTopicPaperByParentIdAndPaperId(@Param("parentId") Integer parentId, @Param("paperId") Integer paperId);

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
     * 通过类型和试卷id查询对应的试卷和题的关系
     * @param param
     * @return
     */
    List<TikuPaperTopic> findTikuPaperByType(Map<String, Object> param);

}