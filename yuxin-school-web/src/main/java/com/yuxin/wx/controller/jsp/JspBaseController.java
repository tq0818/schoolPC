package com.yuxin.wx.controller.jsp;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassTypeOfBranchSchoolService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.api.user.IUsersService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.user.UsersAreaRelation;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 本controller只供前端人员写页面，仅开发阶段用
 * @author zengdeqiang
 *
 */
@Controller
@RequestMapping("/jsp")
public class JspBaseController {

    @Autowired
    private IClassTypeOfBranchSchoolService classTypeOfBranchSchoolService;
    @Autowired
    private ISysConfigDictService sysConfigDictServiceImpl;
    @Autowired
	private IUsersService usersServiceImpl;
    /**
     * 分校开放课程管理
     * @return
     */
    @RequestMapping(value = "/OpenCourse")
    public String OpenCourse(){
        return "schoolResources/openCourse";
    }

    /**
     *课程基本信息
     * @return
     */
    @RequestMapping(value = "/basicInformationCourse")
    public String basicInformationCourse(){
        return "schoolResources/basicInformationCourse";
    }

    /**
     *财务-订单
     * @return
     */
    @RequestMapping(value = "/allOrder")
    public String allOrder(){
        return "system/allOrder";
    }

    /**
     *学校行政管理
     * @return
     */
    @RequestMapping(value = "/AdministrativeManagement")
    public String AdministrativeManagement(HttpServletRequest request,Model model){
    	//查询学校所在区域
        SysConfigDict areaDict = new SysConfigDict();
        areaDict.setDictCode("EDU_SCHOOL_AREA");
        List<SysConfigDict> area = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);

        //查询学校性质
        areaDict.setDictCode("EDU_STEP_NEW");
        List<SysConfigDict> school = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        model.addAttribute("school", school);
        areaDict.setDictCode("EDU_SCHOOL");
        //查询学校
        areaDict.setPageSize(10);
        List<SysConfigDict> schools = sysConfigDictServiceImpl.queryAllSchools(areaDict);

        List<SysConfigDict> areas =new ArrayList<SysConfigDict>();
        Integer userId =(Integer) request.getSession().getAttribute("loginUserIdNow");
        UsersAreaRelation loginArea= sysConfigDictServiceImpl.selectUserByUserId(userId);
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("教科院")){
            model.addAttribute("areas", area);
        }else if(subject.hasRole("区县负责人")){
            for (SysConfigDict areas1:area) {
                if(areas1.getItemCode().equals(loginArea.getEduArea())){
                    areas.add(areas1);
                }
            }
            model.addAttribute("areas", areas);
        }else if(subject.hasRole("学校负责人")||subject.hasRole("任课老师")||subject.hasRole("班主任")){
            for (SysConfigDict areas1:area) {
                if(areas1.getItemCode().equals(loginArea.getEduSchool())){
                    areas.add(areas1);
                }
            }
            model.addAttribute("areas", area);
        }else{
            model.addAttribute("areas", area);
        }
        //查询学校总数
//        Integer rowCount = sysConfigDictServiceImpl.queryAllSchoolsCount(areaDict);
//        PageFinder<SysConfigDict> pageFinder = new PageFinder<SysConfigDict>(areaDict.getPage(), areaDict.getPageSize(), rowCount, schools);
//        model.addAttribute("pageFinder", pageFinder);
        return "query/administrativeManagement";
    }
    /***
     * 学校行政管理搜索
     * @param request
     * @param model
     * @param searchName
     * @param eduArea
     * @param eduSchool
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/searchAdministrativeManagement")
    public PageFinder<SysConfigDict> searchAdministrativeManagement(HttpServletRequest request,Model model,
    		String searchName,String eduArea,String eduSchool,Integer page){
    	//查询学校所在区域
    	SysConfigDict areaDict = new SysConfigDict();
    	areaDict.setDictCode("EDU_SCHOOL_AREA");
    	List<SysConfigDict> area = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
    	/*model.addAttribute("areas", area);*/
    	//查询学校性质
    	areaDict.setDictCode("EDU_STEP_NEW");
    	List<SysConfigDict> school = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
    	model.addAttribute("school", school);
    	areaDict.setDictCode("EDU_SCHOOL");
    	//查询学校
    	areaDict.setItemValue(searchName);
    	areaDict.setFirstLetter(searchName);

        List<SysConfigDict> areas =new ArrayList<SysConfigDict>();
        Integer userId =(Integer) request.getSession().getAttribute("loginUserIdNow");
        UsersAreaRelation loginArea= sysConfigDictServiceImpl.selectUserByUserId(userId);
        //记录区县id 在字典表中筛选
        String eduID=null;
        Subject subject = SecurityUtils.getSubject();
        if(subject.hasRole("教科院")){
            model.addAttribute("areas", area);
            areaDict.setIsArea(eduArea);
        }else if(subject.hasRole("区县负责人")){
            for (SysConfigDict areas1:area) {
                if(areas1.getItemCode().equals(loginArea.getEduArea())){
                    areas.add(areas1);
                }
                if(areas1.getItemCode().equals(loginArea.getEduArea())){
                    eduID=String.valueOf(areas1.getId());
                }
            }
            model.addAttribute("areas", areas);
            areaDict.setIsArea(eduID);
        }
        else if(subject.hasRole("学校负责人")||subject.hasRole("任课老师")||subject.hasRole("班主任")){
            model.addAttribute("areas", area);
        }else{
            model.addAttribute("areas", area);
            areaDict.setIsArea(eduArea);
        }
    	areaDict.setGroupCode(eduSchool);
    	areaDict.setPageSize(10);
    	areaDict.setPage(page);
    	List<SysConfigDict> schools = sysConfigDictServiceImpl.queryAllSchools(areaDict);
    	model.addAttribute("schools", schools);
    	//查询学校总数
        Integer rowCount = sysConfigDictServiceImpl.queryAllSchoolsCount(areaDict);
        PageFinder<SysConfigDict> pageFinder = new PageFinder<SysConfigDict>(areaDict.getPage(), areaDict.getPageSize(), rowCount, schools);
        //model.addAttribute("pageFinder", pageFinder);
    	return pageFinder;
//    	return "query/administrativeManagement";
    }
    /**
     * 学校行政管理修改
     */
    @ResponseBody
    @RequestMapping(value = "/updateManagement")
    public String queryManagement(HttpServletRequest request,SysConfigDict areaDict){
    	//学校名称
    	String itemValue = areaDict.getItemValue();
    	//获取名称首字母
    	String firstLetter = getPYIndexStr(itemValue,true);
    	firstLetter = firstLetter.substring(0, 5);
    	areaDict.setFirstLetter(firstLetter);
    	//组织机构代码
    	String itemCode = areaDict.getItemCode();
    	//区域
    	String dictCode = areaDict.getDictCode();
    	//学校性质
    	String dictName = areaDict.getDictName();
    	try {
    		//根据组织机构代码找出对应的id
    		SysConfigDict areaDictNew= new SysConfigDict();
    		areaDictNew.setItemCode(dictCode);
    		Integer parentItemId = sysConfigDictServiceImpl.findId(areaDictNew);
    			//如果学校性质有修改，则改变
    		if(dictName != null && dictName != ""){
    			sysConfigDictServiceImpl.updateSchoolProperty(areaDict);
    		}
    		//改变其他三个属性
    		areaDict.setParentItemId(parentItemId);
    		sysConfigDictServiceImpl.updateOthserSchoolProperty(areaDict);
    		return "success";
			
		} catch (Exception e) {
			return "false";
		}
    }
    /***
     * 修改密码
     * @param request
     * @param areaDict
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/updatePassword")
    public String updatePassword(HttpServletRequest request,String password,String schoolCode){
    	try {
    	Integer userId = usersServiceImpl.findUserByRealName(schoolCode);
    	Users findUsers = usersServiceImpl.findUsersById(userId);
    	findUsers.setUsername(schoolCode);
    	findUsers.setPassword(new Md5Hash(password,ByteSource.Util.bytes(findUsers.getUsername()+"salt")).toHex());
    	usersServiceImpl.updateSchoolPassword(findUsers);
    	return "success";
    	} catch (Exception e) {
    		return "false";
    	}
    
    }
    /**
     * 重置密码
     * @param request
     * @param areaDict
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/resetPassword")
    public String resetPassword(HttpServletRequest request,String schoolCode){
    	try {
    		Integer userId = usersServiceImpl.findUserByRealName(schoolCode);
        	Users findUsers = usersServiceImpl.findUsersById(userId);
        	findUsers.setUsername(schoolCode);
        	findUsers.setPassword(new Md5Hash("111111",ByteSource.Util.bytes(findUsers.getUsername()+"salt")).toHex());
    		usersServiceImpl.updateSchoolPassword(findUsers);
    		return "success";
		} catch (Exception e) {
			return "false";
		}
    }
    /**
     * 添加分校
     * @param request
     * @param areaDict
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/addSchool")
    public String addSchool(HttpServletRequest request,
    		String schoolName,String schoolCode,String countyCode,String schoolPro){
    	SysConfigDict areaDict = new SysConfigDict();
    	areaDict.setCompanyId(1);
    	areaDict.setDictCode("EDU_SCHOOL");
    	areaDict.setDictName("学校");
    	areaDict.setItemStatusCode("1");
    	areaDict.setDisplaySeq(1);
    	//根据组织机构代码找出对应的id
    	try {
    		//组织机构代码或者学校名重复就不添加
    		SysConfigDict areaCode = new SysConfigDict();
    		areaCode.setItemCode(schoolCode);
    		Integer checkCode = sysConfigDictServiceImpl.checkCodeAndName(areaCode);
    		SysConfigDict areaSchoolName = new SysConfigDict();
    		areaSchoolName.setItemValue(schoolName);
    		Integer checkName = sysConfigDictServiceImpl.checkCodeAndName(areaSchoolName);
    		if(checkCode > 0 || checkName >0){
				return "false";
			}
    		
    		SysConfigDict areaDictNew= new SysConfigDict();
			areaDictNew.setItemCode(countyCode);
	    	Integer parentItemId = sysConfigDictServiceImpl.findId(areaDictNew);
	    	areaDict.setParentItemId(parentItemId);
	    	//获取名称首字母
	    	String firstLetter = getPYIndexStr(schoolName,true);
	    	firstLetter = firstLetter.substring(0, 5);
	    	areaDict.setFirstLetter(firstLetter);
	    	areaDict.setItemCode(schoolCode);
	    	areaDict.setItemValue(schoolName);
	    	//保存sys_config_dict表数据
	    	sysConfigDictServiceImpl.insert(areaDict);
	    	Integer eduStepId = 0;
	    	if(schoolPro.equals("EDU_STEP_NEW_01")){
	    		eduStepId = 2358;
	    	}
	    	if(schoolPro.equals("EDU_STEP_NEW_02")){
	    		eduStepId = 2359;
	    	}
	    	if(schoolPro.equals("EDU_STEP_NEW_03")){
	    		eduStepId = 2360;
	    	}
	    	if(schoolPro.equals("EDU_STEP_NEW_04")){
	    		eduStepId = 2361;
	    	}
	    	if(schoolPro.equals("EDU_STEP_NEW_05")){
	    		eduStepId = 2362;
	    	}
	    	if(schoolPro.equals("EDU_STEP_NEW_06")){
	    		eduStepId = 2363;
	    	}
	    	SysConfigDict area = new SysConfigDict();
	    	
	    	//edu_step_school_relation表中的edu_step_new_id字段
	    	area.setStepId(eduStepId);
	    	
	    	//edu_step_school_relation表中的edu_school_id字段
	    	//根据组织机构代码找出对应的id
    		SysConfigDict ereaFindId= new SysConfigDict();
    		ereaFindId.setItemCode(schoolCode);
    		Integer eduSchoolId = sysConfigDictServiceImpl.findId(ereaFindId);
	    	area.setCompanyId(eduSchoolId);
	    	
	    	//edu_step_school_relation表中的edu_step_new_code字段
	    	area.setGroupCode(schoolPro);
	    	
	    	//edu_step_school_relation表中的edu_school_code字段
	    	area.setItemStatusCode(schoolCode);
	    	//保存edu_step_school_relation表数据
	    	sysConfigDictServiceImpl.addEduStepSchool(area);
	    	//在users表中插入数据
	    	Users user = new Users();
	    	//组织机构代码为username
	    	user.setUsername(schoolCode);
	    	//默认密码111111
	    	user.setPassword(new Md5Hash("111111",ByteSource.Util.bytes(user.getUsername()+"salt")).toHex());
	    	//学校名称作为realname
	    	user.setRealName(schoolName);
	    	usersServiceImpl.addNewSchool(user);
	    	//保存users_area_relation表数据
	    	Integer userId = usersServiceImpl.findUserByRealName(schoolCode);
	    	Users srelation = new Users();
//	    	SysConfigDict configDict = sysConfigDictServiceImpl.findSysConfigDictById(Integer.valueOf(countyCode));
	    	srelation.setUserCity(countyCode);
	    	srelation.setId(userId);
	    	srelation.setSchoolName(schoolCode);
	    	usersServiceImpl.addUsersAreaRelation(srelation);
	    	//保存users_comany_relation表数据
	    	Integer currentCompanyId = WebUtils.getCurrentCompanyId();
	    	Users s = new Users();
	    	s.setId(userId);
	    	s.setCompanyId(currentCompanyId);
	    	usersServiceImpl.addUsersComanyRelation(s);
	    	//当前登录用户
	    	Users currentUser = WebUtils.getCurrentUser();
	    	//保存auth_user_role表数据
	    	Users authUser = new Users();
	    	authUser.setId(userId);
	    	authUser.setCompanyId(currentUser.getId());
	    	Integer findRoleUid = usersServiceImpl.findRoleUid(currentCompanyId);
	    	authUser.setStatus(findRoleUid);
	    	usersServiceImpl.addAuthUserRole(authUser);
	    	return "success";
    	} catch (Exception e) {
    		return "false";
    	}
    }
    /**
     * 检查组织机构代码是否重复
     */
    @ResponseBody
    @RequestMapping(value = "/checkCode")
    public String checkCode(HttpServletRequest request,String schoolCode){
    	SysConfigDict areaDict= new SysConfigDict();
    	areaDict.setItemCode(schoolCode);
    	try {
    		Integer checkCodeAndName = sysConfigDictServiceImpl.checkCodeAndName(areaDict);
			if(checkCodeAndName > 0){
				return "false";
			}
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			return "success";
		}
    	
    }
    
    /**
     * 检查学校名称是否重复
     */
    @ResponseBody
    @RequestMapping(value = "/checkName")
    public String checkName(HttpServletRequest request,String schoolName){
    	SysConfigDict areaDict= new SysConfigDict();
    	areaDict.setItemValue(schoolName);
    	try {
    		Integer checkCodeAndName = sysConfigDictServiceImpl.checkCodeAndName(areaDict);
			if(checkCodeAndName > 0){
				return "false";
			}
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			return "success";
		}
    	
    }
    /**
     *财务-分校收入查询
     * @return
     */
    @RequestMapping(value = "/incomeQuery")
    public String incomeQuery(Model model){
        String isArea = WebUtils.getCurrentIsArea();
        if("0".equals(isArea)){
            List<SysConfigDict> areaList=classTypeOfBranchSchoolService.findAreaIds();
            model.addAttribute("firstItems", areaList);
        }
        model.addAttribute("isArea",isArea);
        return "system/incomeQuery";
    }

    /**
     * 查询分校信息
     * @param request
     * @param areaId
     * @return
     */
    @ResponseBody
    @RequestMapping("/selSchool")
    public JSONObject selSchool(HttpServletRequest request, String areaId){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("parentId",areaId);
        List<SysConfigDict> schoolList = classTypeOfBranchSchoolService.findSchoolByCondition(paramMap);
        JSONObject json = new JSONObject();
        json.put("school", schoolList);
        return json;
    }

    /**
     *财务-老师收入查询
     * @return
     */
    @RequestMapping(value = "/teacherIncome")
    public String teacherIncome(Model model){
        String isArea = WebUtils.getCurrentIsArea();
        if("0".equals(isArea)){
            List<SysConfigDict> schoolList = classTypeOfBranchSchoolService.queryAllSchool();
            model.addAttribute("schoolList", schoolList);
        }
        model.addAttribute("isArea",isArea);
        return "system/teacherIncome";
    }
	public static String getPYIndexStr(String strChinese, boolean bUpCase){

       try{

           StringBuffer buffer = new StringBuffer();

           byte b[] = strChinese.getBytes("GBK");//把中文转化成byte数组

           for(int i = 0; i < b.length; i++){

               if((b[i] & 255) > 128){

                   int char1 = b[i++] & 255;

                   char1 <<= 8;//左移运算符用“<<”表示，是将运算符左边的对象，向左移动运算符右边指定的位数，并且在低位补零。其实，向左移n位，就相当于乘上2的n次方

                   int chart = char1 + (b[i] & 255);

                   buffer.append(getPYIndexChar((char)chart, bUpCase));

                   continue;

               }

               char c = (char)b[i];

               if(!Character.isJavaIdentifierPart(c))//确定指定字符是否可以是 Java 标识符中首字符以外的部分。

                   c = 'A';

               buffer.append(c);

           }

           return buffer.toString();

       }catch(Exception e){

           System.out.println((new StringBuilder()).append("\u53D6\u4E2D\u6587\u62FC\u97F3\u6709\u9519").append(e.getMessage()).toString());

       }

       return null;

   }

   /**

    * 得到首字母

    * @param strChinese

    * @param bUpCase

    * @return

    */

   private static char getPYIndexChar(char strChinese, boolean bUpCase){

       int charGBK = strChinese;

       char result;

       if(charGBK >= 45217 && charGBK <= 45252)

           result = 'A';

       else

       if(charGBK >= 45253 && charGBK <= 45760)

           result = 'B';

       else

       if(charGBK >= 45761 && charGBK <= 46317)

           result = 'C';

       else

       if(charGBK >= 46318 && charGBK <= 46825)

           result = 'D';

       else

       if(charGBK >= 46826 && charGBK <= 47009)

           result = 'E';

       else

       if(charGBK >= 47010 && charGBK <= 47296)

           result = 'F';

       else

       if(charGBK >= 47297 && charGBK <= 47613)

           result = 'G';

       else

       if(charGBK >= 47614 && charGBK <= 48118)

           result = 'H';

       else

       if(charGBK >= 48119 && charGBK <= 49061)

           result = 'J';

       else

       if(charGBK >= 49062 && charGBK <= 49323)

           result = 'K';

       else

       if(charGBK >= 49324 && charGBK <= 49895)

           result = 'L';

       else

       if(charGBK >= 49896 && charGBK <= 50370)

           result = 'M';

       else

       if(charGBK >= 50371 && charGBK <= 50613)

           result = 'N';

       else

       if(charGBK >= 50614 && charGBK <= 50621)

           result = 'O';

       else

       if(charGBK >= 50622 && charGBK <= 50905)

           result = 'P';

       else

       if(charGBK >= 50906 && charGBK <= 51386)

           result = 'Q';

       else

       if(charGBK >= 51387 && charGBK <= 51445)

           result = 'R';

       else

       if(charGBK >= 51446 && charGBK <= 52217)

           result = 'S';

       else

       if(charGBK >= 52218 && charGBK <= 52697)

           result = 'T';

       else

       if(charGBK >= 52698 && charGBK <= 52979)

           result = 'W';

       else

       if(charGBK >= 52980 && charGBK <= 53688)

           result = 'X';

       else

       if(charGBK >= 53689 && charGBK <= 54480)

           result = 'Y';

       else

       if(charGBK >= 54481 && charGBK <= 55289)

           result = 'Z';

       else

           result = (char)(65 + (new Random()).nextInt(25));

       if(!bUpCase)

           result = Character.toLowerCase(result);

       return result;

   }

}
