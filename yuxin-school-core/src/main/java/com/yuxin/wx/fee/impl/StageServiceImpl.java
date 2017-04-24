package com.yuxin.wx.fee.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.fee.IStageService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.student.StudentFeeStage;
import com.yuxin.wx.model.system.SysConfigSchool;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.student.mapper.StudentFeeStageMapper;
import com.yuxin.wx.system.mapper.SysConfigSchoolMapper;
import com.yuxin.wx.vo.fee.RemoteFeeVo;
import com.yuxin.wx.vo.fee.StagingVo;

@Service
@Transactional
public class StageServiceImpl extends BaseServiceImpl implements IStageService{
    @Autowired
    private StudentFeeStageMapper studentFeeStageMapper;
    @Autowired
	private SysConfigSchoolMapper sysConfigSchoolMapper;
    
    @Override
    public void batchInsert(List<StudentFeeStage> studentFeeStage) {
        // TODO Auto-generated method stub
        
    }
   
    @Override
    public StudentFeeStage findByOneId(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }
   
    @Override
    public void insert(StudentFeeStage studentFeeStage) {
        studentFeeStageMapper.insert(studentFeeStage);
    }
    
    @Override
    public PageFinder<StagingVo> queryList(StagingVo search) {
        List<StagingVo> al=studentFeeStageMapper.queryStageList(search);
        for(StagingVo st:al){
        	SysConfigSchool school=sysConfigSchoolMapper.findById(st.getSchoolId());
        	if(school!=null){
        		st.setSchoolName(school.getSchoolName());
        	}
        }
        int pageCount=studentFeeStageMapper.pageCount2(search);
        PageFinder<StagingVo> pf=new PageFinder<StagingVo>(search.getPage(),search.getPageSize(),pageCount,al);
        return pf;
    }
    @Override
    public PageFinder<StagingVo> queryList2(StagingVo search) {
    	List<StagingVo> al=studentFeeStageMapper.queryStageList2(search);
    	int pageCount=studentFeeStageMapper.pageCount4(search);
    	PageFinder<StagingVo> pf=new PageFinder<StagingVo>(search.getPage(),search.getPageSize(),pageCount,al);
    	return pf;
    }
    @Override
    public void update(StudentFeeStage studentFeeStage) {
        studentFeeStageMapper.update(studentFeeStage);
    }
    @Override
    public String fixStage(StudentFeeStage studentFeeStage,Users user){
        StudentFeeStage detail=studentFeeStageMapper.findById(studentFeeStage.getId());
        if(detail==null || detail.getId()==null){
            return "error";
        }
        //新增分期
        if(studentFeeStage.getStageFee()!=null && studentFeeStage.getStageFee()>0){
            StudentFeeStage newStage=new StudentFeeStage();
            newStage.setStageFee(studentFeeStage.getStageFee());
            newStage.setStageDate(studentFeeStage.getStageDate());
            newStage.setStuId(detail.getStuId());
            newStage.setPayMasterId(detail.getPayMasterId());
            newStage.setStageStatus("0");
            newStage.setCreateType("STAGE_TYPE_STAGE");
            newStage.setCreateTime(detail.getCreateTime());
            newStage.setCreator(detail.getCreator());
            newStage.setCompanyId(user.getCompanyId());
            studentFeeStageMapper.insert(newStage);
        }
        StudentFeeStage oldStage=new StudentFeeStage();
        detail.setPosReal(studentFeeStage.getPosReal());
        detail.setCashReal(studentFeeStage.getCashReal());
        detail.setCheckReal(studentFeeStage.getCheckReal());
        detail.setRemitReal(studentFeeStage.getRemitReal());
        detail.setPayDate(new Date());
        detail.setStageStatus("1");
        detail.setRemark(studentFeeStage.getRemark());
        detail.setUpdateTime(new Date());
        detail.setUpdator(user.getId());
        studentFeeStageMapper.update(detail);
        return "success";
    }
    @Override
    public String modifyNextStage(StudentFeeStage studentFeeStage,Integer userId){
        StudentFeeStage stage=studentFeeStageMapper.findNextById(studentFeeStage.getId());
        if(stage==null || stage.getId()==null){
            return "error";
        }
        stage.setStageFee(stage.getStageFee()+studentFeeStage.getStageFee());
        studentFeeStageMapper.update(stage);
        studentFeeStage.setStageFee(null);
        Users user=new Users();
        user.setId(userId);
        return fixStage(studentFeeStage,user);
    }
    
    @Override
    public PageFinder<StagingVo> queryListByStatus(StagingVo search){
        List<StagingVo> stages=new ArrayList<StagingVo>();
        List<StagingVo> vos=studentFeeStageMapper.queryStageGroup(search.getCompanyId());
        
        for(StagingVo vo: vos){
            StagingVo s=studentFeeStageMapper.findStageByGroup(vo);
            s.setCntFee(vo.getCntFee());
            s.setCntNum(vo.getCntNum());
            stages.add(s);
        }
        int pageCount=studentFeeStageMapper.pageCount3(search);
        PageFinder<StagingVo> pf=new PageFinder<StagingVo>(search.getPage(),search.getPageSize(),pageCount,stages);
        return pf;
    }

	@Override
	public PageFinder<RemoteFeeVo> queryRemoteList(RemoteFeeVo search) {
		List<RemoteFeeVo> data=studentFeeStageMapper.queryRemoteStageList(search);
		int count=studentFeeStageMapper.remoteCount(search);
		PageFinder<RemoteFeeVo> pageFinder=new PageFinder<RemoteFeeVo>(search.getPage(), search.getPageSize(), count, data);
		return pageFinder;
	}

	@Override
	public RemoteFeeVo queryRemoteById(RemoteFeeVo search) {
		// TODO Auto-generated method stub
		return studentFeeStageMapper.queryRemoteById(search);
	}

	@Override
	public void insertRemoteFee(RemoteFeeVo search) {
		// TODO Auto-generated method stub
		studentFeeStageMapper.insertRemoteFee(search);
	}

	@Override
	public List<RemoteFeeVo> queryFee(RemoteFeeVo search) {
		// TODO Auto-generated method stub
		return studentFeeStageMapper.queryFee(search);
	}

	@Override
	public void updateRemoteFee(RemoteFeeVo search) {
		// TODO Auto-generated method stub
		studentFeeStageMapper.updateRemoteFee(search);
	}

	@Override
	public void insertFee(RemoteFeeVo search) {
		// TODO Auto-generated method stub
		studentFeeStageMapper.insertFee(search);
	}

}
