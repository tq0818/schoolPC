package com.yuxin.wx.system.impl;

import com.yuxin.wx.api.classes.IClassModuleLessonService;
import com.yuxin.wx.api.query.ISysPlayLogsService;
import com.yuxin.wx.api.system.ISysKnowledgeTreeService;
import com.yuxin.wx.api.tiku.ITikuUserExerciseService;
import com.yuxin.wx.api.watchInfo.IWatchInfoService;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.model.classes.ClassModuleLesson;
import com.yuxin.wx.model.system.SysKnowledgeTree;
import com.yuxin.wx.model.system.SysKnowledgeTreeStatistics;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.system.mapper.SysKnowledgeTreeMapper;
import com.yuxin.wx.system.mapper.SysKnowledgeTreeStatisticsMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by Administrator on 2017/7/31.
 */
@Service
@Transactional
public class SysKnowledgeTreeServiceImpl extends BaseServiceImpl implements ISysKnowledgeTreeService {
    @Autowired
    private SysKnowledgeTreeMapper sysKnowledgeTreeMapper;

    @Autowired
    private SysKnowledgeTreeStatisticsMapper sysKnowledgeTreeStatisticsMapper;

    @Autowired
    private IClassModuleLessonService classModuleLessonServiceImpl;

    @Autowired
    private ISysPlayLogsService sysPlayLogsServiceImpl;

    @Autowired
    private IWatchInfoService watchInfoServiceImpl;

    @Autowired
    private ITikuUserExerciseService tikuUserExerciseServiceImpl;


    @Override
    public void insertKnowledgeTree(SysKnowledgeTree sysKnowledgeTree) {
        sysKnowledgeTreeMapper.insert(sysKnowledgeTree);
    }

    @Override
    public void updateKnowledgeTree(SysKnowledgeTree sysKnowledgeTree) {
        sysKnowledgeTreeMapper.update(sysKnowledgeTree);
    }

    @Override
    public List<SysKnowledgeTree> findKnoledgeTreeByPapam(SysKnowledgeTree sysKnowledgeTree) {
        return sysKnowledgeTreeMapper.findKnoledgeTreeByPapam(sysKnowledgeTree);
    }

    @Override
    public void removeKnowledge(SysKnowledgeTree sysKnowledgeTree) {
        List<SysKnowledgeTree> sysKnowledgeTreeList = findKnoledgeTreeByPapam(sysKnowledgeTree);
        if(sysKnowledgeTreeList!=null && sysKnowledgeTreeList.size()>0){
            sysKnowledgeTreeStatisticsMapper.removeKnowledgeStatistics(sysKnowledgeTreeList);
        }
        sysKnowledgeTreeMapper.removeKnowledge(sysKnowledgeTree);
    }

    @Override
    public void addKnowledgeTree(String idstr, SysKnowledgeTree sysKnowledgeTree, Users user) {
        //清空知识树节点
        removeKnowledge(sysKnowledgeTree);
        //生成新的知识树节点
        long startTime = new Date().getTime();
        if(StringUtils.isNotBlank(idstr)){
            String[] ids = idstr.split(",");
            List<ClassModuleLesson> classModuleLessonList = classModuleLessonServiceImpl.findLessonByCommodityIds(ids);
            List<SysKnowledgeTree> sysKnowledgeTreeList = new ArrayList<SysKnowledgeTree>();
            for(int i=0; i<classModuleLessonList.size(); i++){
                ClassModuleLesson classModuleLesson = classModuleLessonList.get(i);
                sysKnowledgeTree.setCommodityId(classModuleLesson.getCommodityId());
                sysKnowledgeTree.setClasstypeId(classModuleLesson.getClassTypeId());
                sysKnowledgeTree.setLessonId(classModuleLesson.getId());
                if(StringUtils.isNotBlank(classModuleLesson.getAfterStudyUrl())){
                    String afterId = classModuleLesson.getAfterStudyUrl().substring(classModuleLesson.getAfterStudyUrl().lastIndexOf("/")+1);
                    sysKnowledgeTree.setCommodityIdHuikan(Integer.valueOf(afterId));
                }
                if(StringUtils.isNotBlank(classModuleLesson.getBeforeStudyUrl())){
                    String beforeId = classModuleLesson.getBeforeStudyUrl().substring(classModuleLesson.getBeforeStudyUrl().lastIndexOf("/")+1);
                    sysKnowledgeTree.setCommodityIdWeike(Integer.valueOf(beforeId));
                }
                sysKnowledgeTree.setCreateTime(new Date());
                sysKnowledgeTree.setPaperId(classModuleLesson.getTikuPaper()!=null ? classModuleLesson.getTikuPaper().getId():null);
                sysKnowledgeTree.setCreatorId(user.getId());
                sysKnowledgeTree.setCreator(user.getRealName());
                sysKnowledgeTree.setId(null);

                sysKnowledgeTreeList.add(sysKnowledgeTree);
                sysKnowledgeTreeMapper.insert(sysKnowledgeTree);

                insertSysKnowledgeTree(sysKnowledgeTree);
            }
        }
        System.out.println("执行时间：" + (new Date().getTime() - startTime));
    }

    //数据库分开统计后插入
    private void insertSysKnowledgeTree(SysKnowledgeTree sysKnowledgeTree){
        Map<String, SysKnowledgeTreeStatistics> allMap = new HashMap<String, SysKnowledgeTreeStatistics>();
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> resultMap;
        //查询直播
        param.put("lessonId", sysKnowledgeTree.getLessonId());
        List<Map<String, Object>> liveList = watchInfoServiceImpl.queryWatchInfoByParam(param);
        for(int i=0; i<liveList.size(); i++){
            resultMap = liveList.get(i);
            SysKnowledgeTreeStatistics sysKnowledgeTreeStatistics = new SysKnowledgeTreeStatistics();
            sysKnowledgeTreeStatistics.setLessonId(sysKnowledgeTree.getLessonId());
            sysKnowledgeTreeStatistics.setLiveFlag(resultMap.get("liveFlag")!=null ? Integer.valueOf(resultMap.get("liveFlag").toString()):0);
            sysKnowledgeTreeStatistics.setClasstypeId(sysKnowledgeTree.getClasstypeId());
            sysKnowledgeTreeStatistics.setCommodityId(sysKnowledgeTree.getCommodityId());
            sysKnowledgeTreeStatistics.setItemSecondCode(sysKnowledgeTree.getItemSecondCode());
            sysKnowledgeTreeStatistics.setItemThreeCode(sysKnowledgeTree.getItemThreeCode());
            sysKnowledgeTreeStatistics.setUserId(resultMap.get("userId")!=null ? Integer.valueOf(resultMap.get("userId").toString()):0);
            sysKnowledgeTreeStatistics.setKnowledgeTreeId(sysKnowledgeTree.getId());
            allMap.put(resultMap.get("userId").toString(), sysKnowledgeTreeStatistics);
        }
        //查询微课
        if(sysKnowledgeTree.getCommodityIdWeike() != null){
            param.put("commodityId", sysKnowledgeTree.getCommodityIdWeike());
            List<Map<String, Object>> weikeList = sysPlayLogsServiceImpl.queryPlayLogsByParam(param);
            for(int i=0; i<weikeList.size(); i++){
                resultMap = weikeList.get(i);
                if(allMap.containsKey(resultMap.get("userId").toString())){
                    SysKnowledgeTreeStatistics sysKnowledgeTreeStatistics = allMap.get(resultMap.get("userId").toString());
                    sysKnowledgeTreeStatistics.setVideoLectrueWeikeId(resultMap.get("lectureId")!=null ? Integer.valueOf(resultMap.get("lectureId").toString()):0);
                    sysKnowledgeTreeStatistics.setVideoWeikeFlag(resultMap.get("videoFlag")!=null ? Integer.valueOf(resultMap.get("videoFlag").toString()):0);
                }else{
                    SysKnowledgeTreeStatistics sysKnowledgeTreeStatistics = new SysKnowledgeTreeStatistics();
                    sysKnowledgeTreeStatistics.setVideoLectrueWeikeId(resultMap.get("lectureId")!=null ? Integer.valueOf(resultMap.get("lectureId").toString()):0);
                    sysKnowledgeTreeStatistics.setVideoWeikeFlag(resultMap.get("videoFlag")!=null ? Integer.valueOf(resultMap.get("videoFlag").toString()):0);
                    sysKnowledgeTreeStatistics.setClasstypeId(sysKnowledgeTree.getClasstypeId());
                    sysKnowledgeTreeStatistics.setCommodityId(sysKnowledgeTree.getCommodityId());
                    sysKnowledgeTreeStatistics.setItemSecondCode(sysKnowledgeTree.getItemSecondCode());
                    sysKnowledgeTreeStatistics.setItemThreeCode(sysKnowledgeTree.getItemThreeCode());
                    sysKnowledgeTreeStatistics.setUserId(resultMap.get("userId")!=null ? Integer.valueOf(resultMap.get("userId").toString()):0);
                    sysKnowledgeTreeStatistics.setKnowledgeTreeId(sysKnowledgeTree.getId());

                    allMap.put(resultMap.get("userId").toString(), sysKnowledgeTreeStatistics);
                }
            }
        }
        //查询回放
        if(sysKnowledgeTree.getCommodityIdHuikan() != null){
            param.put("commodityId", sysKnowledgeTree.getCommodityIdHuikan());
            List<Map<String, Object>> huifangList = sysPlayLogsServiceImpl.queryPlayLogsByParam(param);
            for(int i=0; i<huifangList.size(); i++){
                resultMap = huifangList.get(i);
                if(allMap.containsKey(resultMap.get("userId").toString())){
                    SysKnowledgeTreeStatistics sysKnowledgeTreeStatistics = allMap.get(resultMap.get("userId").toString());
                    sysKnowledgeTreeStatistics.setVideoLectrueId(resultMap.get("lectureId")!=null ? Integer.valueOf(resultMap.get("lectureId").toString()):0);
                    sysKnowledgeTreeStatistics.setVideoFlag(resultMap.get("videoFlag")!=null ? Integer.valueOf(resultMap.get("videoFlag").toString()):0);
                }else{
                    SysKnowledgeTreeStatistics sysKnowledgeTreeStatistics = new SysKnowledgeTreeStatistics();
                    sysKnowledgeTreeStatistics.setVideoLectrueId(resultMap.get("lectureId")!=null ? Integer.valueOf(resultMap.get("lectureId").toString()):0);
                    sysKnowledgeTreeStatistics.setVideoFlag(resultMap.get("videoFlag")!=null ? Integer.valueOf(resultMap.get("videoFlag").toString()):0);
                    sysKnowledgeTreeStatistics.setClasstypeId(sysKnowledgeTree.getClasstypeId());
                    sysKnowledgeTreeStatistics.setCommodityId(sysKnowledgeTree.getCommodityId());
                    sysKnowledgeTreeStatistics.setItemSecondCode(sysKnowledgeTree.getItemSecondCode());
                    sysKnowledgeTreeStatistics.setItemThreeCode(sysKnowledgeTree.getItemThreeCode());
                    sysKnowledgeTreeStatistics.setUserId(resultMap.get("userId")!=null ? Integer.valueOf(resultMap.get("userId").toString()):0);
                    sysKnowledgeTreeStatistics.setKnowledgeTreeId(sysKnowledgeTree.getId());

                    allMap.put(resultMap.get("userId").toString(), sysKnowledgeTreeStatistics);
                }
            }
        }

        //统计做题记录
        if(sysKnowledgeTree.getPaperId() != null){
            param.put("paperId", sysKnowledgeTree.getPaperId());
            List<Map<String, Object>> paperList = tikuUserExerciseServiceImpl.findTikuPaperByParam(param);
            for(int i=0; i<paperList.size(); i++){
                resultMap = paperList.get(i);
                if(allMap.containsKey(resultMap.get("userId").toString())){
                    SysKnowledgeTreeStatistics sysKnowledgeTreeStatistics = allMap.get(resultMap.get("userId").toString());
                    sysKnowledgeTreeStatistics.setPaperId(sysKnowledgeTree.getPaperId());
                    sysKnowledgeTreeStatistics.setPaperFlag(resultMap.get("paperFlag")!=null ? Integer.valueOf(resultMap.get("paperFlag").toString()):0);
                }else{
                    SysKnowledgeTreeStatistics sysKnowledgeTreeStatistics = new SysKnowledgeTreeStatistics();
                    sysKnowledgeTreeStatistics.setPaperId(sysKnowledgeTree.getPaperId());
                    sysKnowledgeTreeStatistics.setPaperFlag(resultMap.get("paperFlag")!=null ? Integer.valueOf(resultMap.get("paperFlag").toString()):0);
                    sysKnowledgeTreeStatistics.setClasstypeId(sysKnowledgeTree.getClasstypeId());
                    sysKnowledgeTreeStatistics.setCommodityId(sysKnowledgeTree.getCommodityId());
                    sysKnowledgeTreeStatistics.setItemSecondCode(sysKnowledgeTree.getItemSecondCode());
                    sysKnowledgeTreeStatistics.setItemThreeCode(sysKnowledgeTree.getItemThreeCode());
                    sysKnowledgeTreeStatistics.setUserId(resultMap.get("userId")!=null ? Integer.valueOf(resultMap.get("userId").toString()):0);
                    sysKnowledgeTreeStatistics.setKnowledgeTreeId(sysKnowledgeTree.getId());

                    allMap.put(resultMap.get("userId").toString(), sysKnowledgeTreeStatistics);
                }
            }
        }

        if(allMap != null && allMap.size()>0){
            List<SysKnowledgeTreeStatistics> klts = new ArrayList<SysKnowledgeTreeStatistics>();
            Iterator<String> it = allMap.keySet().iterator();
            while(it.hasNext()){
                klts.add(allMap.get(it.next()));
            }
            sysKnowledgeTreeStatisticsMapper.batchInsert(klts);
        }
    }
}
