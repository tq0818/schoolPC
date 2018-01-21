package com.yuxin.wx.controller.jsp;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.classes.IClassTypeOfBranchSchoolService;
import com.yuxin.wx.api.system.ISysConfigDictService;
import com.yuxin.wx.model.system.SysConfigDict;
import com.yuxin.wx.utils.WebUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
        model.addAttribute("areas", area);
        areaDict.setDictCode("EDU_STEP_NEW");
        List<SysConfigDict> school = sysConfigDictServiceImpl.queryConfigDictListByDictCode(areaDict);
        model.addAttribute("school", school);
        areaDict.setDictCode("EDU_SCHOOL");
        List<SysConfigDict> schools = sysConfigDictServiceImpl.queryAllSchools(areaDict);
        model.addAttribute("schools", schools);
        return "query/administrativeManagement";
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
    String u = "阿百川";
	String i = getPYIndexStr(u,true);
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
