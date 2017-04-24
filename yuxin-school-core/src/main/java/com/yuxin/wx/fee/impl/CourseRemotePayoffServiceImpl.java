package com.yuxin.wx.fee.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.fee.ICourseRemotePayoffService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.course.mapper.CourseRemotePayoffMapper;
import com.yuxin.wx.model.course.CourseRemotePayoff;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.student.mapper.StudentPayMasterMapper;
import com.yuxin.wx.vo.fee.RemoteFeeVo;
import com.yuxin.wx.vo.fee.StagingVo;

@Service
@Transactional
public class CourseRemotePayoffServiceImpl extends BaseServiceImpl implements ICourseRemotePayoffService{
    
    @Autowired
    private StudentPayMasterMapper studentPayMasterMapper;
    @Autowired
    private CourseRemotePayoffMapper courseRemotePayoffMapper;
    
    /**
     * 根据查询条件查询远程缴费信息
     * @param RemoteFeeVo
     * @return PageFinder<RemoteFeeVo>
     * @author: chopin 
     * @date : 2015/1/15
     */
    @Override
    public PageFinder<RemoteFeeVo> queryList(RemoteFeeVo search) {
        List<RemoteFeeVo> data=new ArrayList<RemoteFeeVo>();
        StudentPayMaster payMaster =new StudentPayMaster();
        payMaster.setCommodityId(search.getClassTypeId());
        payMaster.setCommodityType("COMMODITY_CLASS");
        payMaster.setExamTermId(search.getExamTermId());
        payMaster.setItemOneId(search.getItemOneId());
        payMaster.setItemSecondId(search.getItemSecondId());
        payMaster.setSchoolId(search.getSchoolId());
        List<StudentPayMaster> al=studentPayMasterMapper.queryListByClassType(payMaster);
        for(StudentPayMaster m: al){
            RemoteFeeVo vo=new RemoteFeeVo();
            CourseRemotePayoff c=new CourseRemotePayoff();
            c.setClassTypeId(m.getCommodityId());
            c.setExamTermId(m.getExamTermId());
            List<CourseRemotePayoff> pl=courseRemotePayoffMapper.findListByClassType(c);
            Date maxPayoffDate=null;
            Double sumPayoff=0.0;
            String payOffPercent="";
            String payoffStatus="REMOTE_PAYOFF_NO";
            if(pl!=null && !pl.isEmpty()){
                for(CourseRemotePayoff p : pl){
                    sumPayoff+=p.getPayoffFee();
                    if(maxPayoffDate==null || p.getPayoffDate().after(maxPayoffDate)){
                        maxPayoffDate=p.getPayoffDate();
                        payOffPercent=p.getPayoffPercent();
                        if(StringUtils.isNotBlank(p.getPayoffStatus())){
                            payoffStatus=p.getPayoffStatus();
                        }
                    }
                }
            }            
            vo.setClassTypeId(m.getCommodityId());
            vo.setClassTypeName(m.getClassTypeName());
            vo.setExamTermId(m.getExamTermId());
            vo.setExamTermName(m.getExamTermName());
            vo.setHasPayFee(sumPayoff);
            vo.setTotalFee(m.getTotalAmount());
            vo.setPayoffPercent(payOffPercent);
            vo.setPayoffStatus(payoffStatus);
            data.add(vo);
        }
        int pageCount=studentPayMasterMapper.pageCountClassType(payMaster);
        PageFinder<RemoteFeeVo> pf=new PageFinder<RemoteFeeVo>(search.getPage(),search.getPageSize(),pageCount,data);
        return pf;
    }
    @Override
    public void addPayOff(CourseRemotePayoff pay,Integer userid){
        pay.setCreateTime(new Date());
        pay.setCreator(userid);
        pay.setPayoffDate(new Date());
        courseRemotePayoffMapper.insert(pay);
    }
    @Override
    public void modifyPayOff(CourseRemotePayoff pay,Integer userid){
        pay.setPayoffDate(new Date());
        pay.setUpdateTime(new Date());
        pay.setUpdator(userid);
        courseRemotePayoffMapper.updateByClassType(pay);
    }

}
