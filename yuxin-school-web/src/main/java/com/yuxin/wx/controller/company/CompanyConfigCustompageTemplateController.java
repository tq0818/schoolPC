package com.yuxin.wx.controller.company;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.tools.ant.filters.FixCrLfFilter.AddAsisRemove;
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

import com.yuxin.wx.api.company.ICompanyConfigCustompageTemplateService;
import com.yuxin.wx.model.company.CompanyConfigCustompageTemplate;


/**
 * Controller of CompanyConfigCustompageTemplate
 * @author chopin
 * @date 2016-4-25
 */
@Controller
@RequestMapping("/companyConfigCustompageTemplate")
public class CompanyConfigCustompageTemplateController {
	
	@Autowired
	private ICompanyConfigCustompageTemplateService companyConfigCustompageTemplateServiceImpl;
	
	/**
	 * 
	 * Class Name: CompanyConfigCustompageTemplateController.java
	 * @Description: 查询自定义类型列表
	 * @author zhang.zx
	 * @date 2016年4月25日 下午6:26:27
	 * @modifier
	 * @modify-date 2016年4月25日 下午6:26:27
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryCustomTypeList")
	public List<CompanyConfigCustompageTemplate> queryCustomType(CompanyConfigCustompageTemplate search){
		List<CompanyConfigCustompageTemplate> arr=companyConfigCustompageTemplateServiceImpl.queryCustomList(search);
		return arr;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyConfigCustompageTemplate search){
		if (search == null) {
			search = new CompanyConfigCustompageTemplate();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyConfigCustompageTemplateServiceImpl.findCompanyConfigCustompageTemplateByPage(search));
		return "companyConfigCustompageTemplate/list";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(CompanyConfigCustompageTemplate CompanyConfigCustompageTemplate) {
		companyConfigCustompageTemplateServiceImpl.insert(CompanyConfigCustompageTemplate);
		return "redirect:/companyConfigCustompageTemplate";
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(CompanyConfigCustompageTemplate CompanyConfigCustompageTemplate) {
		companyConfigCustompageTemplateServiceImpl.update(CompanyConfigCustompageTemplate);
		return "redirect:/companyConfigCustompageTemplate";
	}
	
	@RequestMapping(value="/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyConfigCustompageTemplateServiceImpl.deleteCompanyConfigCustompageTemplateById(id);
		return "redirect:/companyConfigCustompageTemplate";
	}
	
	@ResponseBody
	@RequestMapping(value="/queryTemplate/{id}")
	public CompanyConfigCustompageTemplate getJson(Model model, @PathVariable Integer id){
		return companyConfigCustompageTemplateServiceImpl.findCompanyConfigCustompageTemplateById(id);
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
	

	@ResponseBody
	@RequestMapping(value="/addTemplete")
	public String addTemplete(CompanyConfigCustompageTemplate con){
		con.setTitle("单元");
		con.setStatus(1);
		String s1="<div class='pop-textarea-box'>"+
				"<textarea class='ckeditor form-control editorContents' id='footContents'  rows='6'></textarea>"+
				 "</div>";
		String s2="<div class='mould-content'>"+
                        "<div class='textarea-left'>"+
                            "<div class='tl-title'>"+
//                               "<span>帮助页面</span>"+
//                                "<em class='iconfont'>&#xe626;</em>"+
//                                "<em class='iconfont'>&#xe628;</em>"+
                            "</div>"+
                            "<div class='tl-cont'>"+
                                "<ul class='sortable'>"+
//                                    "<li class='active'>"+
//                                        "<div><p>如何支付</p></div>"+
//                                    "</li>"+
                                "</ul>"+
                            "</div>"+
                        "</div>"+
//                        "<div class='textarea-left block'>"+
//                            "<div class='tl-title'>"+
//                                "<span>非帮助页面</span>"+
//                                "<em class='iconfont'>&#xe626;</em>"+
//                                "<em class='iconfont'>&#xe628;</em>"+
//                            "</div>"+
//                            "<div class='tl-cont'>"+
//                                "<ul class='sortable'>"+
//                                    "<li>"+
//                                        "<div><p>如何上直播课</p></div>"+
//                                    "</li>"+
//                                "</ul>"+
//                            "</div>"+
//                        "</div>"+
                        "<div class='pop-textarea-box textarea-right'>"+
                        	"<textarea class='ckeditor form-control editorContents' id='footContents'  rows='6'></textarea>"+
                        "</div>"+
                    "</div>";
		String s3="<div class='mould-content clear'>"+
                      "<div class='textarea-left block'>"+ 
                            "<div class='tl-title'>"+
                                "<span id='group_firstName' class='group_title_span'></span>"+
                                "<div class='add-temp-box'>"+
                                    "<em class='iconfont pop pop-btn'>&#xe6a5;</em>"+
                                    "<div class='temp-pop'>"+
                                        "<div class='temp-pop-title'>请选择模板</div>"+
                                        "<ul id='group_list'>"+
                                        "</ul>"+
                                        "<div class='add-btn-box'>"+
                                            "<div class='add-temp iconfont'>&#xe652;</div>"+
                                        "</div>"+
                                    "</div>"+
                                "</div>"+
                           "</div>"+
                           "<div class='tl-cont'>"+
                                "<ul class='sortable cusListPage'>"+
//                                    "<li class='active'>"+
//                                        "<div><p></p></div>"+
//                                    "</li>"+
                                "</ul>"+
                            "</div>"+
                        "</div>"+
                        "<div class='pop-textarea-box textarea-right'>"+
                           "<textarea class='ckeditor form-control editorContents' id='footContents'  rows='6'></textarea>"+
                        "</div>"+
                    "</div>";
		
		try {
			con.setContents(URLEncoder.encode(s3, "UTF-8"));
			companyConfigCustompageTemplateServiceImpl.insert(con);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}

}
