package com.yuxin.wx.classes.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yuxin.wx.api.classes.IClassTypeOfBranchSchoolService;
import com.yuxin.wx.api.commodity.ICommodityProductRealtionService;
import com.yuxin.wx.classes.mapper.ClassTypeMapper;
import com.yuxin.wx.classes.mapper.ClassTypeOfBranchSchoolMapper;
import com.yuxin.wx.common.BaseServiceImpl;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.classes.ClassModule;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.classes.ClassTypeResource;
import com.yuxin.wx.model.commodity.Commodity;
import com.yuxin.wx.model.commodity.CommodityProductRealtion;
import com.yuxin.wx.model.course.CourseVideoChapter;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.system.SysConfigItemRelation;
import com.yuxin.wx.system.mapper.SysConfigDictMapper;
import com.yuxin.wx.vo.classes.ClassTypeResourceVo;
import com.yuxin.wx.vo.classes.ClassTypeVo;

@Service
@Transactional
public class ClassTypeOfBranchSchoolService extends BaseServiceImpl implements
		IClassTypeOfBranchSchoolService {
	Log log=LogFactory.getLog("log");
	
	@Autowired
	private SysConfigDictMapper sysConfigDictMapper;
	
	@Autowired
	private ClassTypeOfBranchSchoolMapper classTypeOfBranchSchoolMapper;
	
	@Autowired
	private ClassTypeMapper classTypeMapper;
	

	@Autowired
	private ICommodityProductRealtionService commodityProductRealtionServiceImpl;


	@Override
	public List<SysConfigDict> findByParentId(String parentId) {
		// TODO Auto-generated method stub
		return sysConfigDictMapper.findByParentId(parentId);
	}
	
	@Override
	public List<SysConfigDict> findByDicCode(String code) {
		return sysConfigDictMapper.findByDicCode(code);
	}

	@Override
	public List<ClassTypeVo> queryClassTypeOfBranchSchool(Map<String, Object> param) {
		return classTypeOfBranchSchoolMapper.queryClassTypeOfBranchSchool(param);
	}

	@Override
	public int queryCountClassTypeOfBranchSchool(Map<String, Object> param) {
		return classTypeOfBranchSchoolMapper.queryCountClassTypeOfBranchSchool(param);
	}


	@Override
	public List<SysConfigItemRelation> findItemFront(
			Map<String, Object> param) {
		return classTypeOfBranchSchoolMapper.findItemFront(param);
	}

	@Override
	public void battchUpOrDown(Map<String, Object> param) {
		// TODO Auto-generated method stub
		classTypeOfBranchSchoolMapper.battchUpOrDown(param);
	}

	public ClassTypeVo findClassTypeDetail(Map<String,String> map) {
		ClassTypeVo classvo=classTypeOfBranchSchoolMapper.findDetailById(map);
		List<ClassModule> classModules=classTypeOfBranchSchoolMapper.findListByClassId(map);
		List<CourseVideoChapter> chapters=classTypeOfBranchSchoolMapper.findByClassId(map);
		Integer totalClass=0;
		if(null!=classModules&&classModules.size()>0){
			for(ClassModule classModule: classModules){
				if(classModule.getTotalClassHour()!=null){
					totalClass+=classModule.getTotalClassHour();
				}
			}
		}
		classvo.setModules(classModules);
		classvo.setVideos(chapters);
		//classvo.setRemotes(classTypeOfBranchSchoolMapper.findRemotesByClassTypeId(map));
		classvo.setTotalClass(totalClass);
		return classvo;
	}

	@Override
	public void setCddsRecommendFlag(Map<String, Object> param) {
		// TODO Auto-generated method stub
		classTypeOfBranchSchoolMapper.setCddsRecommendFlag(param);
	}

	@Override
	public void setSaleOrNoSale(Map<String, Object> param) {
		// TODO Auto-generated method stub
		classTypeOfBranchSchoolMapper.setSaleOrNoSale(param);
	}

	@Override
	public List<ClassTypeResourceVo> findResBy(ClassTypeResource res) {
		// TODO Auto-generated method stub
		return classTypeOfBranchSchoolMapper.findResBy(res);
	}

	@Override
	public Integer findResCountBy(ClassTypeResource res) {
		// TODO Auto-generated method stub
		return classTypeOfBranchSchoolMapper.findResCountBy(res);
	}

	@Override
	public String copyClassTypeToTargetCompany(Map<String, String> param) {
		String result="";
		try {
			Integer sourceClassTypeId=Integer.parseInt(param.get("sourceClassTypeId"));
			Integer targetCompanyId=Integer.parseInt(param.get("targetCompanyId"));
			Integer targetSchoolId=Integer.parseInt(param.get("targetSchoolId"));
			//1.查询课程信息
			ClassType ct=classTypeMapper.findById(sourceClassTypeId);
			ClassType targetCt=this.getTargetClassType(ct);
			targetCt.setCreateTime(new Date());
			targetCt.setUpdateTime(new Date());
			targetCt.setSourceCompanyId(ct.getCompanyId());
			targetCt.setSourceClassTypeId(ct.getId());
			targetCt.setIsOutSource(1);
			targetCt.setCompanyId(targetCompanyId);
			targetCt.setCreateSchoolId(targetSchoolId);
			//a.替换封面
			classTypeOfBranchSchoolMapper.insertClassType(targetCt);
			//2.commodity商品表
			Commodity c =classTypeOfBranchSchoolMapper.findCommodityByClassTypeId(ct.getId());
			Commodity targetC =this.getTargetCommodity(c);
			//b.替换封面
			targetC.setCompanyId(targetCompanyId);
			targetC.setSchoolId(targetSchoolId);
			targetC.setCerateTime(new Date());
			targetC.setUpdateTime(new Date());
			targetC.setOriginType(0);
			targetC.setCddsStatus(0);
			targetC.setCddsRecommendFlag(0);
			classTypeOfBranchSchoolMapper.insertCommodity(targetC);
			//3.commodity_product_realtion商品课程关系表
			CommodityProductRealtion commodityProductRealtion = new CommodityProductRealtion();
			commodityProductRealtion.setProductId(targetCt.getId());
			commodityProductRealtion.setProductType("1");
			commodityProductRealtion.setComId(targetC.getId());
			commodityProductRealtionServiceImpl.insert(commodityProductRealtion);
			
			List<ClassModule> moduleList=classTypeOfBranchSchoolMapper.findListByClassId(param);
			if(null!=moduleList&&moduleList.size()>0){
				for(ClassModule module:moduleList){
					//4.class_module课程单元
					//ClassModule module=
					
					//5.class_type_module_relation模块班型关系表
					
					//6.class_module_no模块号
					
					//7.class_module_no_onsale在售模块号
					
					//8.class_module_lesson
					
				}
			}
			
			//9.课程资料,试卷
			
			
			result=JsonMsg.SUCCESS;
		} catch (Exception e) {
			log.error(e.getMessage());
			result=JsonMsg.ERROR;
		}
		return result;
	};
	
	public ClassType getTargetClassType(ClassType ct){
		ClassType targetCt=new ClassType();
		if(null==ct) return targetCt;
		targetCt.setCreator(ct.getCreator());
		targetCt.setUpdator(ct.getUpdator());
		targetCt.setName(ct.getName());
		targetCt.setTypeCode(ct.getTypeCode());
		targetCt.setOriginalPrice(ct.getOriginalPrice());
		targetCt.setRealPrice(ct.getRealPrice());
		targetCt.setDescription(ct.getDescription());
		targetCt.setPublishStatus(ct.getPublishStatus());
		targetCt.setPublishTime(ct.getPublishTime());
		targetCt.setIsSale(ct.getIsSale());
		targetCt.setCover(ct.getCover());
		targetCt.setSubTitle(ct.getSubTitle());
		targetCt.setDetailDesc(ct.getDetailDesc());
		targetCt.setDelFlag(ct.getDelFlag());
		targetCt.setBaseNum(ct.getBaseNum());
		targetCt.setLableType(ct.getLableType());
		targetCt.setTeacherId(ct.getTeacherId());
		targetCt.setFaceFlag(ct.getFaceFlag());
		targetCt.setLiveFlag(ct.getLiveFlag());
		targetCt.setVideoFlag(ct.getVideoFlag());
		targetCt.setRemoteFlag(ct.getRemoteFlag());
		targetCt.setRecommendFlag(ct.getRecommendFlag());
		targetCt.setValidityDay(ct.getValidityDay());
		targetCt.setValidityDate(ct.getValidityDate());
		targetCt.setIntegralFlag(ct.getIntegralFlag());
		targetCt.setMemberFlag(ct.getMemberFlag());
		targetCt.setBuyNumMax(ct.getBuyNumMax());
		targetCt.setSubjectClassOrder(ct.getSubjectClassOrder());
		targetCt.setIconLable(ct.getIconLable());
		targetCt.setItemOneCode(ct.getItemOneCode());
		targetCt.setItemSecondCode(ct.getItemSecondCode());
		targetCt.setItemThirdCode(ct.getItemThirdCode());
		targetCt.setItemFourthCode(ct.getItemFourthCode());
		targetCt.setIsMicroClass(ct.getIsMicroClass());
		targetCt.setPaperDescription(ct.getPaperDescription());
		targetCt.setIsPublic(ct.getIsPublic());
		targetCt.setPublicPrice(ct.getPublicPrice());
		return targetCt;
	}
	
	public Commodity getTargetCommodity(Commodity c){
		Commodity targetC=new Commodity();
		if(null==c) return targetC;
		targetC.setName(c.getName());
		targetC.setCoverUrl(c.getCoverUrl());
		targetC.setOverview(c.getOverview());
		targetC.setOriginalPrice(c.getOriginalPrice());
		targetC.setRealPrice(c.getRealPrice());
		targetC.setType(c.getType());
		targetC.setCreator(c.getCreator());
		targetC.setUpdator(c.getUpdator());
		targetC.setStatus(c.getStatus());
		targetC.setLableType(c.getLableType());
		targetC.setBaseNum(c.getBaseNum());
		targetC.setFaceFlag(c.getFaceFlag());
		targetC.setLiveFlag(c.getLiveFlag());
		targetC.setVideoFlag(c.getVideoFlag());
		targetC.setRemoteFlag(c.getRemoteFlag());
		targetC.setRecommendFlag(c.getRecommendFlag());
		targetC.setBuyNum(c.getBuyNum());
		targetC.setIntegralFlag(c.getIntegralFlag());
		targetC.setMemberFlag(c.getMemberFlag());
		targetC.setItemOneCode(c.getItemOneCode());
		targetC.setItemSecondCode(c.getItemSecondCode());
		targetC.setItemThirdCode(c.getItemThirdCode());
		targetC.setItemFourthCode(c.getItemFourthCode());
		targetC.setIsMicroClass(c.getIsMicroClass());

		return targetC;
	}
	
	public ClassModule getTargetClassModule(ClassModule module){
		ClassModule targetM=new ClassModule();
		
		return targetM;
	}
	
	public String replaceCoverPath(String coverUrl){
		
		return coverUrl;
	}
	
}
