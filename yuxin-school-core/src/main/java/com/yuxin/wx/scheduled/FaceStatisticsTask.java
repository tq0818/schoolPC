package com.yuxin.wx.scheduled;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.resource.IResourceListService;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.system.ISysConfigSchoolService;
import com.yuxin.wx.model.company.CompanyServiceStatic;

/**
 * 
 * @ClassName: FaceStatisticsTask
 * @Description: TODO(统计招生人数，分校数量)
 * @author 1
 * @date 2015-6-25 上午10:31:37
 * @version 1.0
 */
@Component
public class FaceStatisticsTask {
	
	@Autowired
	private ISysConfigSchoolService sysConfigSchoolServiceImpl;
	
	@Autowired
	private IStudentPayMasterService studentPayMasterServiceImpl;
	
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;

	@Autowired
	private ICompanyService companyServiceImpl;
	
	@Autowired
	private IResourceListService resourceListImpl;
	
	public void faceStatistics(){
		//获得公司id
		List<Integer> companyIds = companyServiceImpl.findCompanyId(null);
		for (Integer i : companyIds) {
			//统计已招生人数
			Integer stuCount = studentPayMasterServiceImpl.findStuCountByCompanyId(i);
			//统计已开分校数
			Integer schoolCount = sysConfigSchoolServiceImpl.findSchoolCountByCompanyId(i);
			//根据公司id 查服务
			CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(i);
			if(css == null){
				css = new CompanyServiceStatic();
				css.setFaceStudent(stuCount);
				css.setSchoolNum(schoolCount);
				css.setEmailSend(0);
				css.setCompanyId(i);
				css.setLiveConcurrent(0);
				css.setMessageSend(0);
				css.setVideoFlow(0.0);
				css.setVideoStorage(0.0);
				companyServiceStaticServiceImpl.insert(css);
			}else{
				css.setFaceStudent(stuCount);
				css.setSchoolNum(schoolCount);
				
				if(css.getLiveConcurrent() == null){
					css.setLiveConcurrent(0);
				}
				if(css.getVideoFlow() == null){
					css.setVideoFlow(0.0);
				}
				if(css.getVideoStorage() == null){
					css.setVideoStorage(0.0);
				}
				if(css.getFaceStudent() == null){
					css.setEmailSend(0);
				}
				if(css.getMessageSend() == null){
					css.setMessageSend(0);
				}
				companyServiceStaticServiceImpl.update(css);
			}
		}
	}
}
