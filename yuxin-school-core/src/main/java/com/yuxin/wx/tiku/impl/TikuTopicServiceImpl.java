package com.yuxin.wx.tiku.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.ITikuTopicService;
import com.yuxin.wx.model.tiku.TikuPaperTopic;
import com.yuxin.wx.model.tiku.TikuTopic;
import com.yuxin.wx.model.tiku.TikuTopicExampoint;
import com.yuxin.wx.model.tiku.TikuTopicOption;
import com.yuxin.wx.model.tiku.TikuUserExerciseAnswer;
import com.yuxin.wx.tiku.mapper.TikuPaperTopicMapper;
import com.yuxin.wx.tiku.mapper.TikuTopicExampointMapper;
import com.yuxin.wx.tiku.mapper.TikuTopicMapper;
import com.yuxin.wx.tiku.mapper.TikuTopicOptionMapper;
import com.yuxin.wx.vo.tiku.TikuTopicVo;

/**
 * Service Implementation:TikuTopic
 *
 * @author wang.zx
 * @date 2015-7-8
 */
@Service
@Transactional
public class TikuTopicServiceImpl extends BaseServiceImpl implements ITikuTopicService {

    @Autowired
    private TikuTopicMapper tikuTopicMapper;

    @Autowired
    private TikuTopicOptionMapper tikuTopicOptionMapper;

    @Autowired
    private TikuTopicExampointMapper tikuTopicExampointMapper;

    @Autowired
    private TikuPaperTopicMapper tikuPaperTopicMapper;

    /**
     *
     * @Title: saveTikuTopic
     * @Description: 新增TikuTopic
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public void insert(TikuTopic entity) {
        this.tikuTopicMapper.insert(entity);
    };

    /**
     *
     * @Title: batchSaveTikuTopic
     * @Description: 批量新增TikuTopic
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public void batchInsert(List<TikuTopic> entity) {
        this.tikuTopicMapper.batchInsert(entity);
    };

    /**
     *
     * @Title: updateTikuTopic
     * @Description: 编辑TikuTopic
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public void update(TikuTopic entity) {
        this.tikuTopicMapper.update(entity);
    };

    /**
     *
     * @Title: deleteTikuTopicById
     * @Description: 根据id删除TikuTopic
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public void deleteTikuTopicById(Integer id) {
        this.tikuTopicMapper.deleteById(id);
    };

    /**
     *
     * @Title: deleteTikuTopicByIds
     * @Description: 根据id批量删除TikuTopic
     * @param ids
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public void deleteTikuTopicByIds(Integer[] ids) {
        this.tikuTopicMapper.deleteByIds(ids);
    };

    /**
     *
     * @Title: findTikuTopicById
     * @Description: 根据id查询
     * @param id
     * @return void 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public TikuTopic findTikuTopicById(Integer id) {
        return this.tikuTopicMapper.findById(id);
    };

    /**
     *
     * @Title: findTikuTopicByPage
     * @Description: 分页查询
     * @return
     * @return List<TikuTopic> 返回类型
     * @throws @exception
     * @date 2015-7-8
     * @user by chopin
     */
    @Override
    public List<TikuTopic> findTikuTopicByPage(TikuTopic search) {
        return this.tikuTopicMapper.page(search);
    }

    @Override
    public List<TikuTopic> findTopicByEntity(TikuTopic topic) {
        // TODO Auto-generated method stub
        return this.tikuTopicMapper.findTopicByEntity(topic);
    }

    @Override
    public Integer fingCountByEntity(TikuTopic topic) {
        // TODO Auto-generated method stub
        return this.tikuTopicMapper.fingCountByEntity(topic);
    }

    @Override
    public TikuTopicVo findTopicAndOptionById(Integer id) {
        // TODO Auto-generated method stub
        return this.tikuTopicMapper.findTopicAndOptionById(id);
    }

    @Override
    public Integer findNextId(Map<String, Object> param) {
        // TODO Auto-generated method stub
        return this.tikuTopicMapper.findNextId(param);
    }

    @Override
    public List<TikuTopic> findTopicByPaperId(Map<String, Object> param) {
        // TODO Auto-generated method stub
        return this.tikuTopicMapper.findTopicByPaperId(param);
    }

    @Override
    public List<TikuTopic> findCaseChildTopicByParentIds(String[] parentIds) {
        return this.tikuTopicMapper.findCaseChildTopicByParentIds(parentIds);
    }

    @Override
    public List<TikuTopic> findExistTopic(Map<String, Object> param) {
        // TODO Auto-generated method stub
        return this.tikuTopicMapper.findExistTopic(param);
    }

    @Override
    public Integer findCountExistTopic(Map<String, Object> param) {
        // TODO Auto-generated method stub
        return this.tikuTopicMapper.findCountExistTopic(param);
    };

    @Override
    public List<TikuTopic> findChildTopicByParams(Map<String, Object> param) {
        return this.tikuTopicMapper.findChildTopicByParams(param);
    }

    @Override
    public List<Integer> findSubjectByParentId(Integer parentId) {
        // TODO Auto-generated method stub
        return this.tikuTopicMapper.findSubjectByParentId(parentId);
    }

    @Override
    public void delByIds(List<Integer> list) {
        // TODO Auto-generated method stub
        this.tikuTopicMapper.delByIds(list);
    }

    @Override
    public void delBySubId(Integer subId) {
        // TODO Auto-generated method stub
        this.tikuTopicMapper.delBySubId(subId);
    }

    @Override
    public List<TikuTopic> findByPaperId(Integer paperId, Integer userId, Integer exerId) {
        return this.tikuTopicMapper.findByPaperId(paperId, userId, exerId);
    }

    @Override
    public List<TikuTopic> findListByParentId(TikuUserExerciseAnswer tuea) {
        return this.tikuTopicMapper.findListByParentId(tuea);
    }

    @Override
    public List<TikuTopic> findTikuTopicListByParentId(Integer parentId) {
        return this.tikuTopicMapper.findTikuTopicListByParentId(parentId);
    }

    @Override
    public TikuTopicVo findTopicById(Integer id) {
        return this.tikuTopicMapper.findTopicById(id);
    }

    @Override
    public List<TikuTopic> findTopicByBatchId(Integer batchId, Integer userId, Integer exerId) {
        // TODO Auto-generated method stub
        return this.tikuTopicMapper.findTopicByBatchId(batchId, userId, exerId);
    }

    @Override
    public Integer insertTopic(TikuTopic topic, List<TikuTopicOption> optionList, TikuTopicExampoint point, Integer paperId, Float topicScore) {
        // TODO Auto-generated method stub
        Integer topicid = null;
        if (topic.getId() == null) {
            this.tikuTopicMapper.insert(topic);
            if ("TOPIC_TYPE_CASE".equals(topic.getTopicType())) {
                topicid = topic.getId();
            }
            // 考题考点
            if (point != null) {
                point.setTopicId(topic.getId());
                this.tikuTopicExampointMapper.insert(point);
            }
            if (optionList != null && optionList.size() > 0) {
                // 选项
                for (TikuTopicOption o : optionList) {
                    o.setTopicId(topic.getId());
                    this.tikuTopicOptionMapper.insert(o);
                }
            }
        } else {
            this.tikuTopicMapper.update(topic);
            if ("TOPIC_TYPE_CASE".equals(topic.getTopicType())) {

                // 根据parentID查询出来所以的子题，然后修改对应的状态
                String[] arg = { topic.getId().toString() };
                List<TikuTopic> topics = this.tikuTopicMapper.findCaseChildTopicByParentIds(arg);
                // 修改所有的子题跟材料题的状态一样
                if (topics != null && topics.size() > 0) {
                    for (TikuTopic tp : topics) {
                        tp.setAuditor(topic.getAuditor());
                        tp.setAuditorName(topic.getAuditorName());
                        tp.setAuditTime(new Date());
                        tp.setStatus(topic.getStatus());
                        this.tikuTopicMapper.update(tp);

                    }
                }
            }
            // 查询试题考点id
            Integer examId = this.tikuTopicExampointMapper.findIdByTopicId(topic.getId());
            if (point != null && examId != null && examId > 0) {
                point.setId(examId);
                point.setTopicId(topic.getId());
                this.tikuTopicExampointMapper.update(point);
            }
            if (optionList != null && optionList.size() > 0) {
                // 选项
                for (TikuTopicOption o : optionList) {
                    System.out.println(o);
                    o.setTopicId(topic.getId());
                    if (o.getId() == null) {
                        this.tikuTopicOptionMapper.insert(o);
                    } else {
                        this.tikuTopicOptionMapper.update(o);
                    }
                }
            }
        }
        if (paperId != null) {
            // 插入试卷试题关系
            // 查询是否存在
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("topicId", topic.getId());
            param.put("paperId", paperId);
            TikuPaperTopic tpt = this.tikuPaperTopicMapper.findTopicPaperById(param);
            if (tpt == null) {
                tpt = new TikuPaperTopic();
                tpt.setPaperId(paperId);
                tpt.setTopicId(topic.getId());
                tpt.setTopicType(topic.getTopicType());
                tpt.setParentId(topic.getParentId());
                tpt.setTopicScore(topicScore);
                this.tikuPaperTopicMapper.insert(tpt);
            } else {
                tpt.setPaperId(paperId);
                tpt.setTopicId(topic.getId());
                tpt.setTopicType(topic.getTopicType());
                tpt.setParentId(topic.getParentId());
                tpt.setTopicScore(topicScore);
                this.tikuPaperTopicMapper.update(tpt);
            }
        }
        return topicid;
    }
}
