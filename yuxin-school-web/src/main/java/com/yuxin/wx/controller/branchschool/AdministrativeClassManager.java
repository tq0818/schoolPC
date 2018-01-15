package com.yuxin.wx.controller.branchschool;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.company.ICompanyManageService;
import com.yuxin.wx.common.JsonMsg;
import com.yuxin.wx.model.classes.EduMasterClass;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.utils.WebUtils;

@Controller
@RequestMapping("/administrativeClassManager")
public class AdministrativeClassManager {

	 private Log log=LogFactory.getLog("log");
	 
	 @Autowired
	 private ICompanyManageService companyManageServiceImpl;
	
    @RequestMapping(value = "/administrativeClass")
    public String gotoAdministrativeClassManager(HttpServletRequest request,Model model,EduMasterClass classe){
    	Company company=WebUtils.getCurrentCompany();
    	String  eduAreaSchool=company.getEduAreaSchool();
    	List<EduMasterClass> list=companyManageServiceImpl.findClassByEduAreaSchool(eduAreaSchool);
    	
    	if(null!=list && list.size()>0){
    		SimpleDateFormat df = new SimpleDateFormat("yyyy");//设置日期格式
    		SimpleDateFormat df1 = new SimpleDateFormat("MM");//设置日期格式
    		int date=Integer.valueOf(df.format(new Date()));
    		int month=Integer.valueOf(df1.format(new Date()));
    		if(month>=9){
    			boolean flag=true;
    			for(int i=0;i<list.size();i++){
    				if("STEP_01".equals(list.get(i).getEduStep())&&String.valueOf(date).equals(list.get(i).getEduYear())){
    					flag=false;
    				}
    			}
    			if(flag){
					EduMasterClass small=new EduMasterClass();
					EduMasterClass moddle=new EduMasterClass();
					EduMasterClass hihg=new EduMasterClass();
					small.setClassCount(0);
					small.setEduStep("STEP_01");
					for(int i=0;i<list.size();i++){
						if("STEP_02".equals(list.get(i).getEduStep())&&String.valueOf(date-3).equals(list.get(i).getEduYear())){
							list.remove(i);
	    				}
					}
					for(int i=0;i<list.size();i++){
						if("STEP_03".equals(list.get(i).getEduStep())&&String.valueOf(date-3).equals(list.get(i).getEduYear())){
							list.remove(i);
	    				}
					}
					small.setEduYear(String.valueOf(date));
					moddle.setClassCount(0);
					moddle.setEduStep("STEP_02");
					moddle.setEduYear(String.valueOf(date));
					hihg.setClassCount(0);
					hihg.setEduStep("STEP_03");
					hihg.setEduYear(String.valueOf(date));
					list.add(2,moddle);
					list.add(3,hihg);
					list.add(0,small);
				}
    		}
    		model.addAttribute("list",list);
    		return "company/editAdministrativeClass";
    	}else{
    		SimpleDateFormat df = new SimpleDateFormat("yyyy");//设置日期格式
    		SimpleDateFormat df1 = new SimpleDateFormat("MM");//设置日期格式
    		int date=Integer.valueOf(df.format(new Date()));
    		int month=Integer.valueOf(df1.format(new Date()));
    		if(month>=9){
    			for(int i=0;i<6 ;i++){
        			EduMasterClass classes=new EduMasterClass();
        			classes.setEduStep("STEP_01");
        			classes.setEduYear(String.valueOf(date-i));
        			classes.setClassCount(0);
        			list.add(classes);
        		}
        		for(int i=0;i<3 ;i++){
        			EduMasterClass classes=new EduMasterClass();
        			classes.setEduStep("STEP_02");
        			classes.setEduYear(String.valueOf(date-i));
        			classes.setClassCount(0);
        			list.add(classes);
        		}
        		for(int i=0;i<3 ;i++){
        			EduMasterClass classes=new EduMasterClass();
        			classes.setEduStep("STEP_03");
        			classes.setEduYear(String.valueOf(date-i));
        			classes.setClassCount(0);
        			list.add(classes);
        		}
    		}else{
    			for(int i=0;i<6 ;i++){
        			EduMasterClass classes=new EduMasterClass();
        			classes.setEduStep("STEP_01");
        			classes.setEduYear(String.valueOf(date-1-i));
        			classes.setClassCount(0);
        			list.add(classes);
        		}
        		for(int i=0;i<3 ;i++){
        			EduMasterClass classes=new EduMasterClass();
        			classes.setEduStep("STEP_02");
        			classes.setEduYear(String.valueOf(date-1-i));
        			classes.setClassCount(0);
        			list.add(classes);
        		}
        		for(int i=0;i<3 ;i++){
        			EduMasterClass classes=new EduMasterClass();
        			classes.setEduStep("STEP_03");
        			classes.setEduYear(String.valueOf(date-1-i));
        			classes.setClassCount(0);
        			list.add(classes);
        		}
    		}
    		model.addAttribute("list",list);
    		return "company/administrativeClass";
    	}
    }
    /**
     * 
     * @author jishangyang 2017年12月13日 下午3:01:38
     * @Method: addClass 
     * @Description: 保存班级信息
     * @param req
     * @return 
     * @throws
     */
    @ResponseBody
    @RequestMapping("/addClass" )
    public JSONObject addClass(HttpServletRequest req,HttpServletResponse response){
    	JSONObject js=new JSONObject();
    	Company company=WebUtils.getCurrentCompany();
    	
    	String  eduAreaSchool=company.getEduAreaSchool();
    	String eduArea=companyManageServiceImpl.findEduAreaByeduAreaSchool(eduAreaSchool);
    	try {
    	String ds = req.getParameter("postData");
    	JSONArray json=JSONArray.fromObject(ds); 
    	net.sf.json.JSONObject jsonOne;
    	List<EduMasterClass> list = new ArrayList<EduMasterClass>();
    	List<EduMasterClass> saveList = new ArrayList<EduMasterClass>();
    	for(int i=0;i<json.size();i++){
			 EduMasterClass cla= new EduMasterClass();
	         jsonOne = json.getJSONObject(i); 
	         cla.setClassCount(Integer.valueOf(jsonOne.get("Value").toString()));
	         String a =(String) jsonOne.get("Key");
	         String step =a.substring(0, a.lastIndexOf("_"));
	         cla.setEduStep(step);
	         String year =a.substring(a.lastIndexOf("_")+1, a.length());
	         cla.setEduYear(year);
	         list.add(cla);
		}
    	if(null!=list && list.size()>0){
    		for(int i=0;i<list.size();i++){
    			int classConut=Integer.valueOf(list.get(i).getClassCount());
    			if(classConut>1){
    				for(int k=0;k<classConut;k++){
    					EduMasterClass cla= new EduMasterClass();
    					cla.setEduArea(eduArea);
    					cla.setEduSchool(eduAreaSchool);
    					if("01".equals(list.get(i).getEduStep())){
    						cla.setEduStep("STEP_01");
    					}else if("02".equals(list.get(i).getEduStep())){
    						cla.setEduStep("STEP_02");
    					}else{
    						cla.setEduStep("STEP_03");
    					}
    					cla.setEduYear(list.get(i).getEduYear());
    					cla.setEduClass(String.valueOf(k+1));
    					cla.setIsUsed("1");
    					saveList.add(cla);
    				}
    			}else if(classConut==1){
    				EduMasterClass cla= new EduMasterClass();
    				cla.setEduArea(eduArea);
					cla.setEduSchool(eduAreaSchool);
					if("01".equals(list.get(i).getEduStep())){
						cla.setEduStep("STEP_01");
					}else if("02".equals(list.get(i).getEduStep())){
						cla.setEduStep("STEP_02");
					}else{
						cla.setEduStep("STEP_03");
					}
					cla.setEduYear(list.get(i).getEduYear());
					cla.setEduClass("1");
					cla.setIsUsed("1");
					saveList.add(cla);
    			}else{
    				EduMasterClass cla= new EduMasterClass();
    				cla.setEduArea(eduArea);
					cla.setEduSchool(eduAreaSchool);
					if("01".equals(list.get(i).getEduStep())){
						cla.setEduStep("STEP_01");
					}else if("02".equals(list.get(i).getEduStep())){
						cla.setEduStep("STEP_02");
					}else{
						cla.setEduStep("STEP_03");
					}
					cla.setEduYear(list.get(i).getEduYear());
					cla.setIsUsed("0");
					saveList.add(cla);
    			}
    		}
    	}
			companyManageServiceImpl.addClass(saveList);
    		js.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			js.put(JsonMsg.RESULT, JsonMsg.ERROR);
		}
    	 return js;
    }
  /**
   * 
   * @author jishangyang 2017年12月14日 上午11:52:29
   * @Method: editClass 
   * @Description: 修改班级
   * @param req
   * @param response
   * @return 
   * @throws
   */
    @ResponseBody
    @RequestMapping("/editClass" )
    public JSONObject editClass(HttpServletRequest req,HttpServletResponse response){
    	JSONObject js=new JSONObject();
    	Company company=WebUtils.getCurrentCompany();
    	
    	String  eduAreaSchool=company.getEduAreaSchool();
    	String eduArea=companyManageServiceImpl.findEduAreaByeduAreaSchool(eduAreaSchool);
    	try {
    		String ds = req.getParameter("postData");
    		JSONArray json=JSONArray.fromObject(ds); 
    		net.sf.json.JSONObject jsonOne;
    		List<EduMasterClass> list = new ArrayList<EduMasterClass>();
    		Map<Object,Integer> sMap=new HashMap<Object, Integer>();
    		Map<Object,Integer> mMap=new HashMap<Object, Integer>();
    		Map<Object,Integer> hMap=new HashMap<Object, Integer>();
    		List<EduMasterClass> saveList = new ArrayList<EduMasterClass>();
    		List<EduMasterClass> updateList = new ArrayList<EduMasterClass>();
    		for(int i=0;i<json.size();i++){
    			EduMasterClass cla= new EduMasterClass();
    			jsonOne = json.getJSONObject(i); 
    			int count=Integer.valueOf(jsonOne.get("Value").toString());
    			cla.setClassCount(count);
    			String a =(String) jsonOne.get("Key");
    			String step =a.substring(0, a.lastIndexOf("_"));
    			cla.setEduStep(step);
    			String year =a.substring(a.lastIndexOf("_")+1, a.length());
    			cla.setEduYear(year);
    			list.add(cla);
    		}
    		if(null!=list && list.size()>0){
    			for(int i=0;i<list.size();i++){
    				String edtStep=list.get(i).getEduStep();
    				if("01".equals(edtStep)){
    					//小学
						sMap.put(list.get(i).getEduYear(), list.get(i).getClassCount());
    				}else if("02".equals(edtStep)){
    					//初中
    					mMap.put(list.get(i).getEduYear(), list.get(i).getClassCount());
    				}else{
    					//高中
    					hMap.put(list.get(i).getEduYear(), list.get(i).getClassCount());
    				}
    			}
    		}
    		
    		List<EduMasterClass> localList=companyManageServiceImpl.findClassByEduAreaSchool(eduAreaSchool);
    		if(null!=localList && localList.size()>0){
    			for(EduMasterClass vo: localList){
    				if("STEP_01".equals(vo.getEduStep())){
    					if(sMap.containsKey(vo.getEduYear())){
    						if(vo.getClassCount()>Integer.valueOf(sMap.get(vo.getEduYear()))){
    							int k=vo.getClassCount()-Integer.valueOf(sMap.get(vo.getEduYear()));
    							for(int j=0;j<k;j++){
    								EduMasterClass cl=new EduMasterClass();
    								cl.setEduArea(eduArea);
    								cl.setEduSchool(eduAreaSchool);
    								cl.setEduStep(vo.getEduStep());
    								cl.setEduYear(vo.getEduYear());
    								cl.setEduClass(String.valueOf(vo.getClassCount()-j));
    								cl.setIsUsed("0");
    								updateList.add(cl);
    							}
    						}
    						if(vo.getClassCount()<Integer.valueOf(sMap.get(vo.getEduYear()))){
    							int k=Integer.valueOf(sMap.get(vo.getEduYear()))-vo.getClassCount();
    							for(int j=0; j<k;j++){
    								EduMasterClass cl=new EduMasterClass();
    								cl.setEduArea(eduArea);
    								cl.setEduSchool(eduAreaSchool);
    								cl.setEduStep(vo.getEduStep());
    								cl.setEduYear(vo.getEduYear());
    								cl.setEduClass(String.valueOf(vo.getClassCount()+j+1));
    								cl.setIsUsed("1");
    								saveList.add(cl);
    							}
    							
    						}
    					}
    				}
    				if("STEP_02".equals(vo.getEduStep())){
    					if(mMap.containsKey(vo.getEduYear())){
    						if(vo.getClassCount()>Integer.valueOf(mMap.get(vo.getEduYear()))){
    							int k=vo.getClassCount()-Integer.valueOf(mMap.get(vo.getEduYear()));
    							for(int j=0;j<k;j++){
    								EduMasterClass cl=new EduMasterClass();
    								cl.setEduArea(eduArea);
    								cl.setEduSchool(eduAreaSchool);
    								cl.setEduStep(vo.getEduStep());
    								cl.setEduYear(vo.getEduYear());
    								cl.setEduClass(String.valueOf(vo.getClassCount()-j));
    								cl.setIsUsed("0");
    								updateList.add(cl);
    							}
    						}
    						if(vo.getClassCount()<Integer.valueOf(mMap.get(vo.getEduYear()))){
    							int k=Integer.valueOf(mMap.get(vo.getEduYear()))-vo.getClassCount();
    							for(int j=0; j<k;j++){
    								EduMasterClass cl=new EduMasterClass();
    								cl.setEduArea(eduArea);
    								cl.setEduSchool(eduAreaSchool);
    								cl.setEduStep(vo.getEduStep());
    								cl.setEduYear(vo.getEduYear());
    								cl.setEduClass(String.valueOf(vo.getClassCount()+j+1));
    								cl.setIsUsed("1");
    								saveList.add(cl);
    							}
    							
    						}
    					}
    				}
    				if("STEP_03".equals(vo.getEduStep())){
    					if(hMap.containsKey(vo.getEduYear())){
    						if(vo.getClassCount()>Integer.valueOf(hMap.get(vo.getEduYear()))){
    							int k=vo.getClassCount()-Integer.valueOf(hMap.get(vo.getEduYear()));
    							for(int j=0;j<k;j++){
    								EduMasterClass cl=new EduMasterClass();
    								cl.setEduArea(eduArea);
    								cl.setEduSchool(eduAreaSchool);
    								cl.setEduStep(vo.getEduStep());
    								cl.setEduYear(vo.getEduYear());
    								cl.setEduClass(String.valueOf(vo.getClassCount()-j));
    								cl.setIsUsed("0");
    								updateList.add(cl);
    							}
    						}
    						if(vo.getClassCount()<Integer.valueOf(hMap.get(vo.getEduYear()))){
    							int k=Integer.valueOf(hMap.get(vo.getEduYear()))-vo.getClassCount();
    							for(int j=0; j<k;j++){
    								EduMasterClass cl=new EduMasterClass();
    								cl.setEduArea(eduArea);
    								cl.setEduSchool(eduAreaSchool);
    								cl.setEduStep(vo.getEduStep());
    								cl.setEduYear(vo.getEduYear());
    								cl.setEduClass(String.valueOf(vo.getClassCount()+j+1));
    								cl.setIsUsed("1");
    								saveList.add(cl);
    							}
    							
    						}
    					}
    				}
    			}
    		}
    		companyManageServiceImpl.editClass(saveList,updateList);
    		js.put(JsonMsg.RESULT, JsonMsg.SUCCESS);
    	} catch (Exception e) {
    		e.printStackTrace();
    		log.error(e.getMessage());
    		js.put(JsonMsg.RESULT, JsonMsg.ERROR);
    	}
    	return js;
    }
   
}
