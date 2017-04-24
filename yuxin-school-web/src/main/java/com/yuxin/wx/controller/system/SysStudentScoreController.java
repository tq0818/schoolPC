package com.yuxin.wx.controller.system;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.alibaba.fastjson.JSONObject;
import com.yuxin.wx.api.system.ISysStudentScoreService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.system.SysStudentScore;
import com.yuxin.wx.utils.FileUploadUtil;
import com.yuxin.wx.utils.ImportExcl;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;

/**
 * Controller of SysStudentScore
 * @author chopin
 * @date 2016-11-16
 */
@Controller
@RequestMapping("/sysStudentScore")
public class SysStudentScoreController {
	
	Log log = LogFactory.getLog("log");
	
	@Autowired
	private ISysStudentScoreService sysStudentScoreServiceImpl;
	@Autowired
	private PropertiesUtil propertiesUtil;
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, SysStudentScore search){
		if (search == null) {
			search = new SysStudentScore();
			// search.setPageSize(20);
		}
		model.addAttribute("list", sysStudentScoreServiceImpl.findSysStudentScoreByPage(search));
		return "sysStudentScore/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(SysStudentScore SysStudentScore) {
		sysStudentScoreServiceImpl.insert(SysStudentScore);
		return "redirect:/sysStudentScore";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(SysStudentScore SysStudentScore) {
		sysStudentScoreServiceImpl.update(SysStudentScore);
		return "redirect:/sysStudentScore";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		sysStudentScoreServiceImpl.deleteSysStudentScoreById(id);
		return "redirect:/sysStudentScore";
	}
	
	@ResponseBody
	@RequestMapping(value="/{id:\\d+}", method = RequestMethod.GET)
	public SysStudentScore getJson(Model model, @PathVariable Integer id){
		return sysStudentScoreServiceImpl.findSysStudentScoreById(id);
	}
	
	/**
	 * 后台接收Date转换
	 */
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	/**
	 * 
	 * Class Name: SysStudentScoreController.java
	 * @Description: 进入竞赛成绩页面
	 * @author dongshuai
	 * @date 2016年11月16日 下午7:21:25
	 * @modifier
	 * @modify-date 2016年11月16日 下午7:21:25
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value="/competitionScore")
	public String enterCompetitionScore(){
		return "system/competitionScore";
	}
	
	/**
	 * 
	 * Class Name: SysStudentScoreController.java
	 * @Description: 导入竞赛成绩
	 * @author dongshuai
	 * @date 2016年11月17日 下午4:44:53
	 * @modifier
	 * @modify-date 2016年11月17日 下午4:44:53
	 * @version 1.0
	 * @param multiPartRquest
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/importCompetitionScore",method=RequestMethod.POST)
	public JSONObject importCompetitionScore(MultipartRequest multiPartRquest,HttpServletRequest request){
		JSONObject json = new JSONObject();
		Integer userId = WebUtils.getCurrentUserId(request);
		MultipartFile multipartFile = multiPartRquest.getFile("scoreFile");
		String scoreFileName = multipartFile.getOriginalFilename();
		if (null != scoreFileName && !"".equals(scoreFileName)) {
			String suff = scoreFileName.substring(scoreFileName.lastIndexOf("."));
			if (!".xls".equals(suff) && !".xlsx".equals(suff)) {
				json.put("result", "error");
				json.put("reason", "文件格式不正确");
				return json;
			}
			String newFileName = FileUploadUtil.getlnstance().getNewFileName(scoreFileName);
			File parentfile = new File(propertiesUtil.getExclePath() + userId + "/");
			File file = new File(propertiesUtil.getExclePath() + userId + "/" + newFileName);
			try {
				if (!parentfile.exists()) {
					file.mkdirs();
				}
				multipartFile.transferTo(file);
				List<List<String>> list = ImportExcl.read(file.getPath());
				if(null != list && list.size()>1){
					if(list.get(0).size() >= 7){
						//校验
						JSONObject checkResult = checkImportCompetitionScoreData(list);
						if(!(Boolean)checkResult.get("result")){
							json.put("result", "error");
							json.put("reason", checkResult.get("reason"));
							return json;
						}
						//存储
						boolean saveResult = this.sysStudentScoreServiceImpl.importCompetitionScore(list,WebUtils.getCurrentCompanyId());
						if(!saveResult){
							json.put("result", "error");
							json.put("reason", "竞赛成绩导入数据出错");
							return json;
						}
					}else{
						json.put("result", "error");
						json.put("reason", "文件内容不正确(请参照模板正确填写)");
						return json;
					}
				}else{
					json.put("result", "error");
					json.put("reason", "文件异常(无数据)，请检查文件内容");
					return json;
				}
			} catch (IOException e) {
				log.error("竞赛成绩：文件上传失败", e);
				e.printStackTrace();
				json.put("result", "error");
				json.put("reason", "文件上传失败");
				return json;
			}
		}else{
			json.put("result", "error");
			json.put("reason", "未选择文件");
			return json;
		}
		json.put("result", "success");
		return json;
	}
	
	/**
	 * 
	 * Class Name: SysStudentScoreController.java
	 * @Description: 导入成绩验证
	 * @author dongshuai
	 * @date 2016年11月23日 上午11:01:35
	 * @modifier
	 * @modify-date 2016年11月23日 上午11:01:35
	 * @version 1.0
	 * @param list
	 * @return
	 */
	public JSONObject checkImportCompetitionScoreData(List<List<String>> list){
		JSONObject json = new JSONObject();
		boolean result = true;
		Integer count = 1;
		String reason = "<p>验证异常：</p>";
		String[] fields = new String[]{"姓名","考号","证件号","参赛年级组","分数","奖项","成绩分析链接"};
		Integer[] fieldsSize = new Integer[]{16,32,32,16,16,64,500};
		for (int n = 1; n < list.size(); n++) {
			List<String> csList = list.get(n);
			for (int i = 0; i < 7; i++) {
				if(i<5 && csList.get(i).length()==0){
					result = false;
					reason +="<p>"+count+". 文件第"+(n+1)+"行："+fields[i]+"【"+csList.get(i)+"】为空（该字段不允许为空）,请修改后重新导入</p>";
					count++;
					continue;
				}
				if( csList.get(i).length()>fieldsSize[i] ){
					result = false;
					reason +="<p>"+count+". 文件第"+(n+1)+"行："+fields[i]+"【"+csList.get(i)+"】过长（最多允许"+fieldsSize[i]+"个字符）,请修改后重新导入</p>";
					count++;
				}
			}
		}
		if(!result){
			json.put("reason", reason);
		}
		json.put("result", result);
		return json;
	}
	
	/**
	 * 
	 * Class Name: SysStudentScoreController.java
	 * @Description: 竞赛成绩分页查询
	 * @author dongshuai
	 * @date 2016年11月17日 下午8:25:30
	 * @modifier
	 * @modify-date 2016年11月17日 下午8:25:30
	 * @version 1.0
	 * @param sss
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryCompetitionScore",method=RequestMethod.POST)
	public PageFinder<SysStudentScore> queryCompetitionScore(SysStudentScore sss,HttpServletRequest request){
		sss.setCompanyId(WebUtils.getCurrentCompanyId());
		PageFinder<SysStudentScore> pageFinder = sysStudentScoreServiceImpl.querySysStudentScoreData(sss);
		return pageFinder;
	}
	
	/**
	 * 
	 * Class Name: SysStudentScoreController.java
	 * @Description: 添加竞赛成绩
	 * @author dongshuai
	 * @date 2016年11月17日 下午9:36:30
	 * @modifier
	 * @modify-date 2016年11月17日 下午9:36:30
	 * @version 1.0
	 * @param sss
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/addCompetitionScore",method=RequestMethod.POST)
	public JSONObject addCompetitionScore(SysStudentScore sss){
		JSONObject json = new JSONObject();
		boolean result = true;
		try {
			sss.setCompanyId(WebUtils.getCurrentCompanyId());
			sysStudentScoreServiceImpl.insert(sss);
		} catch (Exception e) {
			log.error("竞赛成绩添加失败",e);
			e.printStackTrace();
			result = false;
		}
		if(!result){
			json.put("result", "error");
			json.put("reason", "添加失败");
			return json;
		}
		json.put("result", "success");
		return json;
	}
	
	/**
	 * 
	 * Class Name: SysStudentScoreController.java
	 * @Description: 修改前查询
	 * @author dongshuai
	 * @date 2016年11月17日 下午9:59:58
	 * @modifier
	 * @modify-date 2016年11月17日 下午9:59:58
	 * @version 1.0
	 * @param sss
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/editToQueryCompetitionScore",method=RequestMethod.POST)
	public JSONObject editToQueryCompetitionScore(SysStudentScore sss){
		JSONObject json = new JSONObject();
		boolean result = true;
		if(null != sss && null != sss.getId()){
			try {
				sss = sysStudentScoreServiceImpl.findSysStudentScoreById(sss.getId());
			} catch (Exception e) {
				log.error("竞赛成绩查询数据出错",e);
				e.printStackTrace();
				result = false;
			}
			if(!result){
				json.put("result", "error");
				json.put("reason", "查询数据出错,请刷新后再试");
			}else{
				json.put("sysStudentScore", sss);
				json.put("result", "success");
			}
		}else{
			json.put("result", "error");
			json.put("reason", "数据异常,请刷新后再试");
			return json;
		}
		return json;
	}
	
	/**
	 * 
	 * Class Name: SysStudentScoreController.java
	 * @Description: 修改竞赛成绩
	 * @author dongshuai
	 * @date 2016年11月17日 下午9:47:23
	 * @modifier
	 * @modify-date 2016年11月17日 下午9:47:23
	 * @version 1.0
	 * @param sss
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/editCompetitionScore",method=RequestMethod.POST)
	public JSONObject editCompetitionScore(SysStudentScore sss){
		JSONObject json = new JSONObject();
		boolean result = true;
		try {
			sysStudentScoreServiceImpl.update(sss);
		} catch (Exception e) {
			log.error("竞赛成绩修改失败",e);
			e.printStackTrace();
			result = false;
		}
		if(!result){
			json.put("result", "error");
			json.put("reason", "修改失败");
			return json;
		}
		json.put("result", "success");
		return json;
	}
	
	/**
	 * 
	 * Class Name: SysStudentScoreController.java
	 * @Description: 删除竞赛成绩
	 * @author dongshuai
	 * @date 2016年11月17日 下午11:02:45
	 * @modifier
	 * @modify-date 2016年11月17日 下午11:02:45
	 * @version 1.0
	 * @param sss
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/delCompetitionScore",method=RequestMethod.POST)
	public JSONObject delCompetitionScore(SysStudentScore sss){
		JSONObject json = new JSONObject();
		boolean result = true;
		if(null != sss && null != sss.getId()){
			try {
				sysStudentScoreServiceImpl.deleteSysStudentScoreById(sss.getId());
			} catch (Exception e) {
				log.error("竞赛成绩删除数据出错",e);
				e.printStackTrace();
				result = false;
			}
			if(!result){
				json.put("result", "error");
				json.put("reason", "删除数据出错,请刷新后再试");
			}else{
				json.put("result", "success");
			}
		}else{
			json.put("result", "error");
			json.put("reason", "数据异常,请刷新后再试");
			return json;
		}
		return json;
	}
	
	/**
	 * 
	 * Class Name: SysStudentScoreController.java
	 * @Description: 下载模板
	 * @author dongshuai
	 * @date 2016年11月23日 上午11:05:23
	 * @modifier
	 * @modify-date 2016年11月23日 上午11:05:23
	 * @version 1.0
	 * @param response
	 * @param request
	 */
	@RequestMapping(value="/downloadExcleTemplete",method=RequestMethod.POST)
	public void downloadExcleTemplete(HttpServletResponse response, HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("/") + "student_score.xls";
		File file = new File(path);
		response.setHeader("Content-Disposition", "attachment; filename=student_score.xls");
		response.addHeader("Content-Length", "" + file.length());
		response.setContentType("application/octet-stream;charset=UTF-8");
		InputStream in = null;
		OutputStream toClient = null;
		try {
			in = new BufferedInputStream(new FileInputStream(file));
			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			in.close();
			toClient = new BufferedOutputStream(response.getOutputStream());
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (Exception e) {
			log.error("竞赛成绩模板下载失败", e);
		}
	}
}
