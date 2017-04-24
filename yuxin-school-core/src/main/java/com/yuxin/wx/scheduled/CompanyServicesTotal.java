package com.yuxin.wx.scheduled;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.vo.company.CompanyMemberServiceTotalVo;
import com.yuxin.wx.vo.company.CompanyMemberServicesTotalVo;


/**
 * 
 * @ClassName: CompanyServicesTotal
 * @Description: 查询公司服务剩余数量
 * @author zhang.zx
 * @date 2015年7月21日 上午11:56:21
 * @modifier
 * @modify-date 2015年7月21日 上午11:56:21
 * @version 1.0
 */
@Component
public class CompanyServicesTotal {
	private Log log=LogFactory.getLog("log");
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	@Autowired
	private ICompanyService companyServiceImpl;
	
	/**
	 * 
	 * Class Name: CompanyServicesTotal.java
	 * @Description: 公司剩余服务数量
	 * @author zhang.zx
	 * @date 2015年7月21日 下午12:42:55
	 * @modifier
	 * @modify-date 2015年7月21日 下午12:42:55
	 * @version 1.0
	 * @return
	 */
	public List<CompanyMemberServiceTotalVo> taskCompanyServicesUsed(){
		List<CompanyMemberServiceTotalVo> arr=new ArrayList<CompanyMemberServiceTotalVo>();
		List<CompanyMemberServicesTotalVo> cms=companyMemberServiceServiceImpl.queryCopanyServices();
		List<CompanyServiceStatic> css=companyServiceStaticServiceImpl.queryCompanyServicesUsed();
		for (CompanyMemberServicesTotalVo cm:cms) {
			for(CompanyServiceStatic cs:css){
				if(cm.getCompanyId().equals(cs.getCompanyId())){
					CompanyMemberServiceTotalVo cmstv=new CompanyMemberServiceTotalVo();
					cmstv.setServiceVersion(cm.getServiceVersion());
					//剩余短信条数
					Integer messageTotal=0;
					if(cm.getMessageTotal()!=null){
						messageTotal=cm.getMessageTotal()+cm.getGiveMessage();
					}else{
						messageTotal=cm.getGiveMessage();
					}
					Integer messageSend=0;
					if(cs.getMessageSend()!=null){
						messageSend=cs.getMessageSend();
					}
					Integer messageLen=messageTotal-messageSend;
					log.info("------------------短信统计-----------------------");
					log.info(cm.getCompanyId()+"---总计:"+messageTotal+"--已用:"+messageSend+"--剩余:"+messageLen);
					//剩余邮件条数
					Integer emailTotal=0;
					if(cm.getEmailTotal()!=null){
						emailTotal=cm.getEmailTotal()+cm.getGiveEmail();
					}else{
						emailTotal=cm.getGiveEmail();
					}
					Integer emailSend=0;
					if(cs.getEmailSend()!=null){
						emailSend=cs.getEmailSend();
					}
					Integer emailLen=emailTotal-emailSend;
					log.info("------------------邮件统计-----------------------");
					log.info(cm.getCompanyId()+"---总计:"+emailTotal+"--已用:"+emailSend+"--剩余:"+emailLen);
					//剩余直播并发
					Integer LiveConcurrentMax=0;
					Integer giveLiveFree=0;
					if(cm.getGiveLive()!=null){
						giveLiveFree=cm.getGiveLive();
					}
					if(cm.getLiveConcurrentMax()!=null){
						LiveConcurrentMax=cm.getLiveConcurrentMax()+giveLiveFree;
					}else{
						LiveConcurrentMax=giveLiveFree;
					}
					Integer LiveConcurrent=0;
					if(cs.getLiveConcurrent()!=null){
						LiveConcurrent=cs.getLiveConcurrent();
					}
					Integer liveConcurrentMaxLen=LiveConcurrentMax-LiveConcurrent;
					log.info("------------------直播并发-----------------------");
					log.info(cm.getCompanyId()+"---总计:"+LiveConcurrentMax+"--已用:"+LiveConcurrent+"--剩余:"+liveConcurrentMaxLen);
					//剩余流量
					Double videoFlow=0.0; 
					if(cm.getVideoFlow()!=null){
						videoFlow=cm.getVideoFlow()+cm.getGiveVideoFlow();
					}else{
						videoFlow=cm.getGiveVideoFlow();
					}
				    Double videoFlowUsed=0.0;
					if(cs.getVideoFlow()!=null){
						videoFlowUsed=cs.getVideoFlow();
					}
					Double videoFlowLen=videoFlow-videoFlowUsed;
					log.info("------------------流量剩余-----------------------");
					log.info(cm.getCompanyId()+"---总计:"+videoFlow+"--已用:"+videoFlowUsed+"--剩余:"+videoFlowLen);
					//剩余视频空间
					Double videoStorage=0.0;
					Double giveVideoStorageFree=0.0;
					if(cm.getGiveVideoStorage()!=null){
						giveVideoStorageFree=cm.getGiveVideoStorage();
					}
					if(cm.getVideoStorage()!=null){
						videoStorage=cm.getVideoStorage()+giveVideoStorageFree;
					}else{
						videoStorage=giveVideoStorageFree;
					}
					Double videoStorageUsed=0.0;
					if(cs.getVideoStorage()!=null){
						videoStorageUsed=cs.getVideoStorage();
					}
					Double videoStorageLen=videoStorage-videoStorageUsed;
					log.info("------------------视频空间-----------------------");
					log.info(cm.getCompanyId()+"---总计:"+videoStorage+"--已用:"+videoStorageUsed+"--剩余:"+videoStorageLen);
					//剩余招生人数
					Integer faceStudentAll=0;
					if(cm.getFaceStudentAll()!=null){
						faceStudentAll=cm.getFaceStudentAll();
					}
					Integer faceStudent=0;
					if(cs.getFaceStudent()!=null){
						faceStudent=cs.getFaceStudent();
					}
					Integer faceStudentAllLen=faceStudentAll-faceStudent;
					log.info("------------------招生人数-----------------------");
					log.info(cm.getCompanyId()+"---总计:"+faceStudentAll+"--已用:"+faceStudent+"--剩余:"+faceStudentAllLen);
					
					cmstv.setCompanyId(cm.getCompanyId());
					cmstv.setCompanyName(cm.getCompanyName());
					cmstv.setMessageLen(messageLen);
					cmstv.setEmailLen(emailLen);
					cmstv.setLiveConcurrentMaxLen(liveConcurrentMaxLen);
					cmstv.setVideoFlowLen(videoFlowLen);
					cmstv.setVideoStorageLen(videoStorageLen);
					cmstv.setFaceStudentAllLen(faceStudentAllLen);
					//直播有效期
					cmstv.setLiveDateLen(cm.getLiveDateLen());//购买
					cmstv.setGiveDateLen(cm.getGiveLiveLen());//免费
//					if(cm.getGiveLiveLen()<=0){
//						cmstv.setLiveDateLen(cm.getLiveDateLen());
//					}else{
//						cmstv.setLiveDateLen(cm.getLiveDateLen()+cm.getGiveLiveLen());
//					}
					log.info("------------------直播有效期-----------------------");
					log.info(cm.getCompanyId()+"--购买剩余:"+cmstv.getLiveDateLen()+"--免费剩余:"+cmstv.getGiveDateLen());
//					if(cm.getGiveVideoLen()<=0){
//						cmstv.setVideoDateLen(cm.getVideoDateLen());
//					}else{
//						cmstv.setVideoDateLen(cm.getVideoDateLen()+cm.getGiveVideoLen());
//					}
					cmstv.setVideoDateLen(cm.getVideoDateLen());//购买
					cmstv.setGiveVideoDateLen(cm.getGiveVideoLen());//免费
					log.info("------------------视频有效期-----------------------");
					log.info(cm.getCompanyId()+"--购买剩余:"+cmstv.getVideoDateLen()+"---免费剩余:"+cmstv.getGiveVideoDateLen());
					//已发短信条数
					cmstv.setMessageLenUsed(messageSend);
					//已发邮件条数
					cmstv.setEmailLenUsed(emailSend);
					//最大并发数
					cmstv.setLiveConcurrentMax(LiveConcurrentMax);
					//已使用流量
					cmstv.setVideoFlowLenUsed(videoFlowUsed);
					//已使用视频空间
					cmstv.setVideoStorageLenUsed(videoStorageUsed);
					//已招学员数量
					cmstv.setFaceStudentAllLenUsed(faceStudent);
					//会员服务有效期剩余
					cmstv.setMemberService(cm.getMemberDateLen());
					log.info("------------------会员有效期-----------------------");
					log.info(cm.getCompanyId()+"--剩余:"+cmstv.getMemberService());
					arr.add(cmstv);
				}
			}
		}
		return arr;
	}

}
