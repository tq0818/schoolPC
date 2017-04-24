package com.yuxin.wx.controller.fee;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.codehaus.jackson.map.util.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yuxin.wx.api.classes.IClassTypeService;
import com.yuxin.wx.api.fee.ICourseRemotePayoffService;
import com.yuxin.wx.api.fee.IStageService;
import com.yuxin.wx.api.student.IStudentPayMasterService;
import com.yuxin.wx.api.system.ISysConfigItemService;
import com.yuxin.wx.api.system.ISysConfigTermService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.common.SysConfigConstant;
import com.yuxin.wx.fee.impl.CourseRemotePayoffServiceImpl;
import com.yuxin.wx.model.classes.ClassType;
import com.yuxin.wx.model.course.CourseRemotePayoff;
import com.yuxin.wx.model.student.StudentFeeStage;
import com.yuxin.wx.model.student.StudentPayMaster;
import com.yuxin.wx.model.system.SysConfigItem;
import com.yuxin.wx.model.system.SysConfigTerm;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.fee.RemoteFeeVo;
import com.yuxin.wx.vo.fee.StagingVo;

@Controller
@RequestMapping("/fee")
public class FeeController {
    @Autowired
    private IStageService stageServiceImpl;
    @Autowired
    private ICourseRemotePayoffService courseRemotePayoffServiceImpl;
    @Autowired
	private ISysConfigItemService sysConfigItemServiceImpl;
    @Autowired
    private IClassTypeService classTypeServiceImpl;
    @Autowired
    private IStudentPayMasterService studentPayMasterServiceImpl;
    @Autowired
    private ISysConfigTermService sysConfigTermServiceImpl;
    @RequestMapping(value="/page/{page}", method = RequestMethod.GET)
    public String showPage(Model model,HttpServletRequest request, @PathVariable String page){
    	/*SysConfigItem search=new SysConfigItem();
    	search.setCompanyId(WebUtils.getCurrentCompanyId());
    	search.setDelFlag(0);	//未删除
    	search.setStatus("1");		//启用
    	search.setItemType("1");	//一级项目
*/		//查询出该公司的所有未删除的一级项目
    	List<SysConfigItem> itemOnes = sysConfigItemServiceImpl.findSysConfigItemByPid("1", null, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
    	model.addAttribute("itemOnes", itemOnes);
    	if(itemOnes!=null && !itemOnes.isEmpty()){
	    	//查出一级项目第一个所有一级项目考期
	    	List<SysConfigTerm> terms = sysConfigTermServiceImpl.getTermByItemOne(itemOnes.get(0).getId(),WebUtils.getCurrentCompanyId());
	    	model.addAttribute("terms", terms);
	    	
    		List<SysConfigItem> itemTwos = sysConfigItemServiceImpl.findTwoByOneId(itemOnes.get(0).getId());
        	model.addAttribute("itemTwos", itemTwos);
        	model.addAttribute("one", itemOnes.get(0).getId());
        	//查出第一个一级项目下的所有班型
        	List<ClassType> list = classTypeServiceImpl.findByItem(WebUtils.getCurrentCompanyId(),WebUtils.getCurrentSchoolId(),itemOnes.get(0).getId(),null);
//        	for (int i = 0; i < list.size(); i++) {
//    			String[] strs = list.get(i).getSchoolsId().split(",");
//    			if(!isContains(strs,WebUtils.getCurrentSchoolId().toString())){
//    				list.remove(i);
//    				i--;
//    			}
//    			
//    		}
        	model.addAttribute("classTypeList", list);
    	}
    	return "/student/urgeFee/"+page;
    }
    /**
     * 
     * Class Name: FeeController.java
     * @Description: 根据一级项目查询对应班型
     * @author 权飞虎
     * @date 2015年4月29日 下午4:32:14
     * @modifier
     * @modify-date 2015年4月29日 下午4:32:14
     * @version 1.0
     * @param itemOneId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="findTerm",method=RequestMethod.POST)
    public List<SysConfigTerm> findTerm(Integer itemOneId,Model model){
    	List<SysConfigTerm> terms = sysConfigTermServiceImpl.getTermByItemOne(itemOneId, WebUtils.getCurrentCompanyId());
    	model.addAttribute("terms", terms);
    	//return "/student/urgeFee/ajaxFindTerm";
    	return terms;
    }
    /**
     * 
     * Class Name: FeeController.java
     * @Description: 根据一级项目查询所对应二级项目
     * @author 权飞虎
     * @date 2015年4月29日 下午4:59:41
     * @modifier
     * @modify-date 2015年4月29日 下午4:59:41
     * @version 1.0
     * @param itemOneId
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping(value="findSecondItem",method=RequestMethod.POST)
    public List<SysConfigItem> findSecondItem(Integer itemOneId,Model model){
    	
    	List<SysConfigItem> itemTwos = sysConfigItemServiceImpl.findTwoByOneId(itemOneId);
    	model.addAttribute("itemTwos", itemTwos);
    	//return "/student/urgeFee/ajaxSecondItem";
    	return itemTwos;
    }
    @ResponseBody
    @RequestMapping(value="findClassType",method=RequestMethod.POST)
    public List<ClassType> findClassType(Model model,Integer itemOneId,Integer itemSecondId){
    	if("".equals(itemSecondId)){
    		itemSecondId=null;
    	}
    	List<ClassType> list = classTypeServiceImpl.findByItem(WebUtils.getCurrentCompanyId(),WebUtils.getCurrentSchoolId(),itemOneId,itemSecondId);
//    	for (int i = 0; i < list.size(); i++) {
//			String[] strs = list.get(i).getSchoolsId().split(",");
//			System.out.println(Arrays.asList(strs));
//			System.out.println(WebUtils.getCurrentSchoolId());
//			if(!isContains(strs,WebUtils.getCurrentSchoolId().toString())){
//				list.remove(i);
//				i--;
//			}
//			
//		}
    	model.addAttribute("classTypeList", list);
    	//return "student/urgeFee/ajaxClassType";
    	return list;
    }
  //判断数组中是否包含某元素
  	public Boolean isContains(String[] strs,String schoolId){
  		//int count=0;
  		for (String str : strs) {
  			if(schoolId.equals(str)){
  				return true;
  			}
  		}
  		  return false;
  	}
    //Integer itemOneId,Integer itemSecondId,Integer classTypeId,Integer examTermId,Model model
    @RequestMapping(value="search",method=RequestMethod.POST)
    public String search(Integer pageNo,Integer itemOneId,Integer itemSecondId,Integer classTypeId,Integer examTermId,String mobile,Model model,HttpServletRequest request){
    	StagingVo search=new StagingVo();
    	if(itemOneId!=null&&!"".equals(itemOneId)){
    		search.setItemOneId(itemOneId);
    	}
    	if(classTypeId!=null&&!"".equals(classTypeId)){
    		search.setClassTypeId(classTypeId);
    	}
    	if(itemSecondId!=null&&!"".equals(itemSecondId)){
    		search.setItemSecondId(itemSecondId);
    	}
    	if(examTermId!=null&&!"".equals(examTermId)){
    		search.setExamTermId(examTermId);
    	}
    	if(mobile!=null&&!"".equals(mobile)){
    		search.setMobile(mobile);
    	}
    	if(pageNo!=null&&!"".equals(pageNo)){
    		search.setPage(pageNo);
    	}else{
    		search.setPage(1);
    	}
    	search.setPageSize(5);
    	search.setCompanyId(WebUtils.getCurrentCompanyId());
    	search.setSchoolId(WebUtils.getCurrentSchoolId());
        PageFinder<StagingVo> pf=stageServiceImpl.queryList2(search);
        model.addAttribute("pf", pf);
        return "/student/urgeFee/ajaxMessage";
    }
    @ResponseBody
    @RequestMapping(value="/staging", method = RequestMethod.POST)
    public PageFinder<StagingVo> staging(StagingVo search){
    	search.setCompanyId(WebUtils.getCurrentCompanyId());
        PageFinder<StagingVo> pf=stageServiceImpl.queryList(search);
        return pf;
    }
    
    @ResponseBody
    @RequestMapping(value="/stagingNotice", method = RequestMethod.POST)
    public PageFinder<StagingVo> stagingNotice(StagingVo search){
    	search.setCompanyId(WebUtils.getCurrentCompanyId());
        PageFinder<StagingVo> pf=stageServiceImpl.queryListByStatus(search);
        return pf;
    }
    
    @ResponseBody
    @RequestMapping(value="/modifyNextStage", method = RequestMethod.POST)
    public String modifyNextStage(HttpServletRequest request,StudentFeeStage feeStage){
        return stageServiceImpl.modifyNextStage(feeStage,WebUtils.getCurrentUserId(request));
    }
    
    @ResponseBody
    @RequestMapping(value="/fixStage", method = RequestMethod.POST)
    public String fixStage(HttpServletRequest request, StudentFeeStage studentFeeStage){
        return stageServiceImpl.fixStage(studentFeeStage,WebUtils.getCurrentUser());
    }
    @ResponseBody
    @RequestMapping(value="/remote", method = RequestMethod.POST)
    public PageFinder<RemoteFeeVo> remoteList(HttpServletRequest request,RemoteFeeVo search){
    	search.setSchoolId(WebUtils.getCurrentSchoolId());
       return  courseRemotePayoffServiceImpl.queryList(search);
    }
    
    @ResponseBody
    @RequestMapping(value="/add", method = RequestMethod.POST)
    public String addRemotePay(HttpServletRequest request,CourseRemotePayoff search){
       courseRemotePayoffServiceImpl.addPayOff(search,WebUtils.getCurrentUserId(request));
       return "success";
    }
    
    @ResponseBody
    @RequestMapping(value="/modify", method = RequestMethod.POST)
    public String modifyRemotePay(HttpServletRequest request,CourseRemotePayoff search){
       courseRemotePayoffServiceImpl.modifyPayOff(search,WebUtils.getCurrentUserId(request));
       return "success";
    }
    
    /**
     * 远程节费
     */
    @RequestMapping(value="/stuLong",method=RequestMethod.GET)
    public String studentLong(Model model){
    	List<SysConfigItem> firstItems = sysConfigItemServiceImpl.findSysConfigItemByPid(SysConfigConstant.ITEMTYPE_FIRST, null, WebUtils.getCurrentCompanyId(), WebUtils.getCurrentSchoolId());
		model.addAttribute("firstItems", firstItems);
    	return "student/student-long";
    }
    
    /**
     * 
     * Class Name: FeeController.java
     * @Description: 远程节费信息
     * @author zhang.zx
     * @date 2015年6月23日 下午3:02:09
     * @modifier
     * @modify-date 2015年6月23日 下午3:02:09
     * @version 1.0
     * @param pageNo
     * @param itemOneId
     * @param itemSecondId
     * @param classTypeId
     * @param examTermId
     * @param mobile
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value="/queryLists")
    public String queryRemoteListDetail(Integer pageNo,Integer itemOneId,Integer itemSecondId,Integer classTypeId,Integer examTermId,String classTypeName,String payoffStatus,Model model,HttpServletRequest request){
    	RemoteFeeVo search=new RemoteFeeVo();
    	if(itemOneId!=null&&!"".equals(itemOneId)){
    		search.setItemOneId(itemOneId);
    	}
    	if(classTypeId!=null&&!"".equals(classTypeId)){
    		search.setClassTypeId(classTypeId);
    	}
    	if(itemSecondId!=null&&!"".equals(itemSecondId)){
    		search.setItemSecondId(itemSecondId);
    	}
    	if(examTermId!=null&&!"".equals(examTermId)){
    		search.setExamTermId(examTermId);
    	}
    	if(classTypeName!=null&&!"".equals(classTypeName)){
    		search.setClassTypeName(classTypeName);
    	}
    	if(pageNo!=null&&!"".equals(pageNo)){
    		search.setPage(pageNo);
    	}else{
    		search.setPage(1);
    	}
    	search.setPayoffStatus(payoffStatus);
    	search.setPageSize(5);
    	search.setCompanyId(WebUtils.getCurrentCompanyId());
    	search.setSchoolId(WebUtils.getCurrentSchoolId());
        PageFinder<RemoteFeeVo> pageFinder=stageServiceImpl.queryRemoteList(search);
        model.addAttribute("pageFinder", pageFinder);
    	return "/student/studentLongAjaxList";
    }
    
    /**
     * 
     * Class Name: FeeController.java
     * @Description: 查询单个远程结费信息
     * @author zhang.zx
     * @date 2015年6月23日 下午5:43:05
     * @modifier
     * @modify-date 2015年6月23日 下午5:43:05
     * @version 1.0
     * @param model
     * @return
     */
    @RequestMapping(value="/queryRemoteById/{id}",method=RequestMethod.GET)
    public String addRemote(@PathVariable Integer id,Model model){
    	RemoteFeeVo search=new RemoteFeeVo();
    	search.setPayId(id);
    	RemoteFeeVo remoteFee=stageServiceImpl.queryRemoteById(search);
    	model.addAttribute("remoteFee", remoteFee);
    	return "/student/addStudentLong";
    }
    
    /**
     * 
     * Class Name: FeeController.java
     * @Description: 添加远程节费
     * @author zhang.zx
     * @date 2015年6月23日 下午7:03:24
     * @modifier
     * @modify-date 2015年6月23日 下午7:03:24
     * @version 1.0
     * @param model
     * @param remoteFee
     * @return
     */
    @RequestMapping(value="/addRemoteFee",method=RequestMethod.POST)
    public String addRemotes(Integer id,Integer classTypeId,String classTypeName,Integer examTermId,String examTermName,
    		Double hasPayFee,Double payoffFee,String payoffPercent,String payoffStatus,Model model,HttpServletRequest request){
    	RemoteFeeVo re=new RemoteFeeVo();
    	re.setPayoffStatus(payoffStatus);
    	re.setPayoffPercent(payoffPercent);
        if(id==null||"".equals(id)){
        	re.setClassTypeId(classTypeId);
        	re.setPayoffFee(payoffFee);
        	re.setClassTypeName(classTypeName);
        	re.setExamTermId(examTermId);
        	re.setExamTermName(examTermName);
    	    re.setCompanyId(WebUtils.getCurrentCompanyId());
            re.setSchoolId(WebUtils.getCurrentSchoolId());
            stageServiceImpl.insertFee(re);
        }else{
        	if(hasPayFee!=null){
        		re.setPayoffFee(hasPayFee+payoffFee);
        	}else{
        		re.setPayoffFee(payoffFee);
        	}
        	 re.setId(id);
        	stageServiceImpl.updateRemoteFee(re);
        }
    	RemoteFeeVo remoteFee=new RemoteFeeVo();
    	remoteFee.setId(re.getId());
    	remoteFee.setPayoffFee(payoffFee);
    	remoteFee.setPayoffDate(new Date());
    	remoteFee.setCreator(WebUtils.getCurrentUserId(request));
    	remoteFee.setCreateTime(new Date());
    	
    	stageServiceImpl.insertRemoteFee(remoteFee);
    	return "redirect:/fee/stuLong";
    }
    
    /**
     * 
     * Class Name: FeeController.java
     * @Description:查询结费记录信息
     * @author zhang.zx
     * @date 2015年6月24日 下午3:27:22
     * @modifier
     * @modify-date 2015年6月24日 下午3:27:22
     * @version 1.0
     * @param feeId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/queryFeeList",method=RequestMethod.POST)
    public List<RemoteFeeVo> queryFeeList(Integer feeId){
    	RemoteFeeVo search=new RemoteFeeVo();
    	search.setId(feeId);
    	return stageServiceImpl.queryFee(search);
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

}
