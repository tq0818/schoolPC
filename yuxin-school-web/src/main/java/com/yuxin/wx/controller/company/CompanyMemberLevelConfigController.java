package com.yuxin.wx.controller.company;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.yuxin.wx.api.classes.IClassTypeMemberDiscountService;
import com.yuxin.wx.api.company.ICompanyMemberConfigService;
import com.yuxin.wx.api.company.ICompanyMemberLevelConfigService;
import com.yuxin.wx.api.company.ICompanyMemberLevelDetailService;
import com.yuxin.wx.api.company.ICompanyMemberServiceService;
import com.yuxin.wx.api.company.ICompanyPicsService;
import com.yuxin.wx.api.company.ICompanyService;
import com.yuxin.wx.api.company.ICompanyServiceStaticService;
import com.yuxin.wx.api.user.IUsersFrontService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.classes.ClassTypeMemberDiscount;
import com.yuxin.wx.model.company.Company;
import com.yuxin.wx.model.company.CompanyMemberConfig;
import com.yuxin.wx.model.company.CompanyMemberLevelConfig;
import com.yuxin.wx.model.company.CompanyMemberLevelConfigVo;
import com.yuxin.wx.model.company.CompanyMemberLevelDetail;
import com.yuxin.wx.model.company.CompanyMemberService;
import com.yuxin.wx.model.company.CompanyPics;
import com.yuxin.wx.model.company.CompanyServiceStatic;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.utils.EntityDtoConverter;
import com.yuxin.wx.utils.FileUtil;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.WebUtils;
import com.yuxin.wx.vo.company.CompanyPicsVo;

/**
 * Controller of CompanyMemberLevelConfig
 * 
 * @author chopin
 * @date 2016-5-17
 */
@Controller
@RequestMapping("/companyMemberLevelConfig")
public class CompanyMemberLevelConfigController {
	private static Log log = LogFactory.getLog("log");
	@Autowired
	private ICompanyMemberLevelConfigService companyMemberLevelConfigServiceImpl;
	@Autowired
	private IClassTypeMemberDiscountService classTypeMemberDiscountServiceImpl;
	@Autowired
	private ICompanyMemberLevelDetailService companyMemberLevelDetailServiceImpl;
	@Autowired
	private PropertiesUtil propertiesUtil;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private ICompanyPicsService companyPicsServiceImpl;
	@Autowired
	private ICompanyMemberConfigService companyMemberConfigServiceImpl;
	@Autowired
	private IUsersFrontService  usersFrontServiceImpl;
	@Autowired
	private ICompanyMemberServiceService companyMemberServiceServiceImpl;
	@Autowired
	private ICompanyServiceStaticService companyServiceStaticServiceImpl;
	/**
	 * Class Name: CompanyMemberLevelConfigController.java
	 * 
	 * @Description: 上传会员等级图标
	 * @author xukaiqiang
	 * @date 2016年5月22日 下午9:44:21
	 * @modifier
	 * @modify-date 2016年5月22日 下午9:44:21
	 * @version 1.0
	 * @param multiPartRquest
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public CompanyPicsVo upload(MultipartRequest multiPartRquest, HttpServletRequest req) {
		// Subject subject = SecurityUtils.getSubject();
		String realPath = null;
		String picPath = null;
		MultipartFile multipartFile = multiPartRquest.getFile("imgData");
		// subject.getSession().setAttribute("imgData", multipartFile);
		String name = multipartFile.getOriginalFilename();
		if (name != null && !"".equals(name)) {
			try {
				realPath = FileUtil.upload(multipartFile, "company", WebUtils.getCurrentCompanyId() + "");
			} catch (Exception e) {
				log.error("文件上传失败",e);
				e.printStackTrace();
			}
		}
		CompanyPics pic = new CompanyPics();
		pic.setCompanyId(WebUtils.getCurrentCompanyId());
		pic.setPicName("");
		pic.setPicType("memberlevel");
		pic.setPicOriginalUrl(realPath);
		// 存库
		companyPicsServiceImpl.insert(pic);

		String picUrl = "http://" + propertiesUtil.getProjectImageUrl() + "/" + realPath;
		log.info("图片回显路径===============" + picUrl);
		CompanyPicsVo pics = new CompanyPicsVo();
		pics.setPicOriginalUrl(picUrl);
		pics.setRealPath(realPath);
		picPath = "http://" + propertiesUtil.getProjectImageUrl() + "/" + realPath;
		return pics;
	}

	/**
	 * Class Name: CompanyMemberLevelConfigController.java
	 * 
	 * @Description: 会员等级排序
	 * @author xukaiqiang
	 * @date 2016年6月7日 下午4:15:49
	 * @modifier
	 * @modify-date 2016年6月7日 下午4:15:49
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/orderMemberLevelSequence")
	public boolean orderMemberLevelSequence(HttpServletRequest request) {
		List<CompanyMemberLevelConfig> items = JSONArray.parseArray(request.getParameter("list"),
				CompanyMemberLevelConfig.class);
		if (items == null || items.size() == 0)
			return true;
		for (CompanyMemberLevelConfig companyMemberLevelConfig : items) {
			companyMemberLevelConfigServiceImpl.update(companyMemberLevelConfig);
		}
		return true;
	}

	/**
	 * 
	 * Class Name: CompanyMemberLevelConfigController.java
	 * 
	 * @Description:会员等级名称是否重复
	 * @author xukaiqiang
	 * @date 2016年6月2日 上午11:54:13
	 * @modifier
	 * @modify-date 2016年6月2日 上午11:54:13
	 * @version 1.0
	 * @param companyMemberLevelConfigVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkMemberLevelName")
	public boolean checkMemberLevelName(CompanyMemberLevelConfigVo companyMemberLevelConfigVo) {
		companyMemberLevelConfigVo.setCompanyId(WebUtils.getCurrentCompanyId());
		boolean flag = companyMemberLevelConfigServiceImpl.checkMemberLevelName(companyMemberLevelConfigVo);
		return flag;
	}

	/**
	 * Class Name: CompanyMemberLevelConfigController.java
	 * 
	 * @Description:插入或修改会员等级
	 * @author xukaiqiang
	 * @date 2016年6月2日 下午9:17:01
	 * @modifier
	 * @modify-date 2016年6月2日 下午9:17:01
	 * @version 1.0
	 * @param configVo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/insertOrUpdateCompanyMemberLevelConfig")
	public int insertOrUpdateCompanyMemberLevelConfig(CompanyMemberLevelConfigVo configVo) {
		// 功能标记2-添加1-修改
		int functionFlag = 0;
		Integer companyId = WebUtils.getCurrentCompanyId();
		// 更新
		if (null != configVo.getId()) {
			// 插入
			functionFlag = 1;
			log.info("进入修改会员等级.........");
			// 更新会员等级
			CompanyMemberLevelConfig entity = companyMemberLevelConfigServiceImpl
					.findCompanyMemberLevelConfigById(configVo.getId());
			entity.setName(configVo.getName());
			entity.setDiscountType(configVo.getDiscountType());
			entity.setOpenWay(configVo.getOpenWay());
			entity.setDiscount(configVo.getDiscount());
			String picPath = configVo.getIco();
			entity.setIco(picPath.replaceAll("http://" + propertiesUtil.getProjectImageUrl() + "/", ""));
			entity.setDescription(configVo.getDescription());
			if(configVo.getOpenWay()!=1){
				entity.setConsumption(configVo.getConsumption());
			}
			companyMemberLevelConfigServiceImpl.update(entity);
			// 更新会员等级详情
			// 如果不是累计型
			if (configVo.getBecomeMember() == 0&&configVo.getOpenWay()!=1) {
				List<CompanyMemberLevelDetail> list = JSON.parseArray(configVo.getListCmld(),
						CompanyMemberLevelDetail.class);
				for (int i = 0; i < list.size(); i++) {
					CompanyMemberLevelDetail companyMemberLevelDetail = list.get(i);
					// 根据length和memberId在数据库中进行查询该条记录
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("memberId", entity.getId());
					map.put("memberBuyLength", companyMemberLevelDetail.getLength());
					CompanyMemberLevelDetail companyMemberLevelDetailDB = companyMemberLevelDetailServiceImpl
							.findCompanyMemberLevelDetailById(companyMemberLevelDetail.getId());
					if (companyMemberLevelDetailDB != null) {
						companyMemberLevelDetailDB.setLength(companyMemberLevelDetail.getLength());
						companyMemberLevelDetailDB.setPrice(companyMemberLevelDetail.getPrice());
						companyMemberLevelDetailDB.setName(companyMemberLevelDetail.getName());
						companyMemberLevelDetailDB.setUnit(companyMemberLevelDetail.getUnit());
						companyMemberLevelDetailServiceImpl.update(companyMemberLevelDetailDB);
					} else {
						companyMemberLevelDetail.setStatus(1);
						companyMemberLevelDetail.setMemberId(entity.getId());
						companyMemberLevelDetailServiceImpl.insert(companyMemberLevelDetail);
					}
				}
			}
			//############################
			//同步修改users_front表中的关于会员的内容
			List<UsersFront>  usersFrontList=usersFrontServiceImpl.findUsersFrontByMemberId(entity.getId());
			for (UsersFront usersFront : usersFrontList) {
				usersFront.setMemberLevel(entity.getName());
				usersFrontServiceImpl.update(usersFront);
			}
			
		} else {
			// 插入
			functionFlag = 2;
			log.info("进入添加会员等级.........");
			// 添加
			configVo.setCompanyId(companyId);
			configVo.setDelFlag(0);
			String picPath = configVo.getIco();
			configVo.setIco(picPath.replaceAll("http://" + propertiesUtil.getProjectImageUrl() + "/", ""));
			CompanyMemberLevelConfig companyMemberLevelConfig = new CompanyMemberLevelConfig();
			try {
				companyMemberLevelConfig = (CompanyMemberLevelConfig) EntityDtoConverter.dto2Bean(configVo,
						companyMemberLevelConfig);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("companyId", companyId);
			// 插入sequnce
			Integer maxSequence = companyMemberLevelConfigServiceImpl.findMaxSequence(map);
			if (maxSequence != null) {
				companyMemberLevelConfig.setSequence(maxSequence + 1);
			} else {
				maxSequence = 1;
				companyMemberLevelConfig.setSequence(maxSequence);
			}
			companyMemberLevelConfigServiceImpl.insert(companyMemberLevelConfig);
			log.info("插入会员等级.........companyMemberLevelConfig.getId()=" + companyMemberLevelConfig.getId());
			//如果是购买成为会员并且不是内部开通的
			if (configVo.getBecomeMember() == 0&&configVo.getOpenWay()!=1) {
				log.info("成为会员的方式为购买companyMemberLevelConfig.getConsumption()为..........."
						+ companyMemberLevelConfig.getConsumption());
				// 插入会员等级详情
				List<CompanyMemberLevelDetail> list = JSON.parseArray(configVo.getListCmld(),
						CompanyMemberLevelDetail.class);
				for (CompanyMemberLevelDetail companyMemberLevelDetail : list) {
					companyMemberLevelDetail.setStatus(1);
					companyMemberLevelDetail.setMemberId(companyMemberLevelConfig.getId());
					companyMemberLevelDetailServiceImpl.insert(companyMemberLevelDetail);
				}
			}
		}
		return functionFlag;
	}

	/**
	 * Class Name: CompanyMemberLevelConfigController.java
	 * 
	 * @Description: 删除会员等级详情
	 * @author xukaiqiang
	 * @date 2016年6月5日 上午10:32:52
	 * @modifier
	 * @modify-date 2016年6月5日 上午10:32:52
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteMemberDetail")
	public String deleteMemberDetail(CompanyMemberLevelDetail companyMemberLevelDetail) {
		companyMemberLevelDetailServiceImpl.deleteCompanyMemberLevelDetailById(companyMemberLevelDetail.getId());
		return "";
	}

	/**
	 * Class Name: CompanyMemberLevelConfigController.java
	 * 
	 * @Description: 跳转到会员等级添加页面
	 * @author xukaiqiang
	 * @date 2016年5月22日 下午9:34:54
	 * @modifier
	 * @modify-date 2016年5月22日 下午9:34:54
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addVipLevelUI")
	public String addVipLevelUI(Model model, HttpServletRequest request) {
		// commonDomain.jsp中的信息
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);
		model.addAttribute("company", company);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		// 查询会员设置中的值，如果是购买型会员，购买方式为购买，如果是累积型会员，购买方式显示累积消费
		// 通过公司的companyId查找公司的信息
		CompanyMemberConfig companyMemberConfig = companyMemberConfigServiceImpl
				.findCompanyMemberConfigByCompanyId(companyId);
		model.addAttribute("companyMemberConfig", companyMemberConfig);
		
		CompanyMemberLevelConfigVo companyMemberLevelConfigVo = new CompanyMemberLevelConfigVo();
		companyMemberLevelConfigVo.setMethod("add");
		model.addAttribute("companyMemberLevelConfigVo", companyMemberLevelConfigVo);
		return "company/vip/companyMemberLevelConfig";
	}

	/**
	 * Class Name: CompanyMemberLevelConfigController.java
	 * 
	 * @Description: 删除会员等级详情和会员等级
	 * @author xukaiqiang
	 * @date 2016年6月5日 上午10:54:02
	 * @modifier
	 * @modify-date 2016年6月5日 上午10:54:02
	 * @version 1.0
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteCompanyMemberConfigAndDetail/{id}")
	public String deleteCompanyMemberConfigAndDetail(@PathVariable Integer id) {
		companyMemberLevelConfigServiceImpl.deleteCompanyMemberLevelConfigById(id);
		companyMemberLevelDetailServiceImpl.deleteMemberLevelDetailByMemberId(id);
		
		//更新users_front中的数据，把是该等级的会员中的会员数据清空
		List<UsersFront> list=usersFrontServiceImpl.findUsersFrontByMemberId(id);
		for (UsersFront usersFront : list) {
			usersFront.setMemberId(null);
			usersFront.setMemberBuyLength(null);
			usersFront.setMemberEndTime(null);
			usersFront.setMemberStatus(null);
			usersFrontServiceImpl.updateNull(usersFront);
		}
		//删除class_type_member_discount表中的数据
		classTypeMemberDiscountServiceImpl.deleteClassTypeMemberDiscountByMemberId(id);
		return "company/vip/vipLevelList";
	}

	/**
	 * Class Name: CompanyMemberLevelConfigController.java
	 * 
	 * @Description: 跳转到会员等级设置界面
	 * @author xukaiqiang
	 * @date 2016年5月21日 上午11:27:19
	 * @modifier
	 * @modify-date 2016年5月21日 上午11:27:19
	 * @version 1.0
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/vipSet")
	public String show(Model model) {
		// 获取当前公司ID
		// commonDomain.jsp中的信息
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);
		model.addAttribute("company", company);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		// 查询会员设置中的值，如果是购买型会员，购买方式为购买，如果是累积型会员，购买方式显示累积消费
				// 通过公司的companyId查找公司的信息
				CompanyMemberConfig companyMemberConfig = companyMemberConfigServiceImpl
						.findCompanyMemberConfigByCompanyId(companyId);
				model.addAttribute("companyMemberConfig", companyMemberConfig);
				log.info("进入会员等级列表查询页面.........companyMemberLevelConfig/vipSet");
		// 查询会员等级列表,在service中进行处理，返回CompanyMemberLevelConfig
		CompanyMemberLevelConfig search = new CompanyMemberLevelConfig();
		search.setCompanyId(companyId);
		log.info("获取companyId........." + companyId);
		List<CompanyMemberLevelConfigVo> list = companyMemberLevelConfigServiceImpl
				.findCompanyMemberLevelConfigByCompanyId(search);
		log.info("获取会员等级列表总共........." + list.size() + "条。");
		// 查询会员等级详情
		for (CompanyMemberLevelConfigVo companyMemberLevelConfigVo : list) {
			List<CompanyMemberLevelDetail> companyMemberLevelDetails = companyMemberLevelConfigServiceImpl
					.findCompanyMemberLevelConfigDetailById(companyMemberLevelConfigVo);
			companyMemberLevelConfigVo.setList(companyMemberLevelDetails);
		}
		log.info("获取会员等级详情...................");
		model.addAttribute("list", list);
		log.info("离开......封装会员等级及详情跳转页面...................");
		return "company/vip/vipLevelList";
	}

	/**
	 * Class Name: CompanyMemberLevelConfigController.java
	 * 
	 * @Description: 查询所有的私有云图片
	 * @author xukaiqiang
	 * @date 2016年6月7日 下午6:38:27
	 * @modifier
	 * @modify-date 2016年6月7日 下午6:38:27
	 * @version 1.0
	 * @return
	 */
	@RequestMapping(value = "/queryPrivatePic", method = RequestMethod.POST)
	public String queryPrivatePic(CompanyPicsVo picVo, Model model) {
		String url = "http://" + propertiesUtil.getProjectImageUrl() + "/";
		Integer companyId = WebUtils.getCurrentCompanyId();
		picVo.setCompanyId(companyId);
		picVo.setPicType("memberlevel");
		picVo.setPageSize(44);
		PageFinder<CompanyPicsVo> pageFinder = companyService.queryPrivatePic(picVo);
		for (CompanyPicsVo c : pageFinder.getData()) {
			c.setRealPath(c.getPicOriginalUrl());
			c.setPicOriginalUrl(url + c.getPicOriginalUrl());
		}
		model.addAttribute("itemOneId", picVo.getItemOneId());
		model.addAttribute("pageFinder", pageFinder);
		return "company/vip/companyPicsAjaxList";
	}

	/**
	 * Class Name: CompanyMemberLevelConfigController.java
	 * 
	 * @Description: 查询公有云上面的图片
	 * @author xukaiqiang
	 * @date 2016年6月8日 上午10:57:11
	 * @modifier
	 * @modify-date 2016年6月8日 上午10:57:11
	 * @version 1.0
	 * @param picVo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryPublicPic", method = RequestMethod.POST)
	public String queryPublicPic(CompanyPicsVo picVo, Model model) {
		String url = "http://" + propertiesUtil.getProjectImageUrl() + "/";
		Integer companyId = WebUtils.getCurrentCompanyId();
		picVo.setCompanyId(companyId);
		picVo.setPicType("memberlevel");
		picVo.setPageSize(50);
		PageFinder<CompanyPicsVo> pageFinder = companyService.queryPublicPic(picVo);
		for (CompanyPicsVo c : pageFinder.getData()) {
			c.setRealPath(c.getPicOriginalUrl());
			c.setPicOriginalUrl(url + c.getPicOriginalUrl());
		}
		model.addAttribute("itemOneId", picVo.getItemOneId());
		model.addAttribute("pageFinder", pageFinder);
		return "company/vip/publicPicsAjaxList";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model model, CompanyMemberLevelConfig search) {
		if (search == null) {
			search = new CompanyMemberLevelConfig();
			// search.setPageSize(20);
		}
		model.addAttribute("list", companyMemberLevelConfigServiceImpl.findCompanyMemberLevelConfigByPage(search));
		return "companyMemberLevelConfig/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(CompanyMemberLevelConfig CompanyMemberLevelConfig) {
		companyMemberLevelConfigServiceImpl.insert(CompanyMemberLevelConfig);
		return "redirect:/companyMemberLevelConfig";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(CompanyMemberLevelConfig CompanyMemberLevelConfig) {
		companyMemberLevelConfigServiceImpl.update(CompanyMemberLevelConfig);
		return "redirect:/companyMemberLevelConfig";
	}

	@RequestMapping(value = "/del/{id}", method = RequestMethod.GET)
	public String del(Model model, @PathVariable Integer id) {
		companyMemberLevelConfigServiceImpl.deleteCompanyMemberLevelConfigById(id);
		return "redirect:/companyMemberLevelConfig";
	}

	@ResponseBody
	@RequestMapping(value = "/{id:\\d+}", method = RequestMethod.GET)
	public CompanyMemberLevelConfig getJson(Model model, @PathVariable Integer id) {
		return companyMemberLevelConfigServiceImpl.findCompanyMemberLevelConfigById(id);
	}

	/**
	 * 
	 * Class Name: CompanyMemberLevelConfigController.java
	 * 
	 * @Description: 查询会员等级列表
	 * @author zhang.zx
	 * @date 2016年5月20日 下午2:37:01
	 * @modifier
	 * @modify-date 2016年5月20日 下午2:37:01
	 * @version 1.0
	 * @param search
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/queryLevelList")
	public List<CompanyMemberLevelConfig> queryMemberLevelList(Integer classTypeId) {
		Integer companyId = WebUtils.getCurrentCompanyId();
		ClassTypeMemberDiscount search1 = new ClassTypeMemberDiscount();
		search1.setCompanyId(companyId);
		search1.setClassTypeId(classTypeId);
		List<ClassTypeMemberDiscount> classLevelList = classTypeMemberDiscountServiceImpl.findClassLevelList(search1);
		CompanyMemberLevelConfig search = new CompanyMemberLevelConfig();
		search.setCompanyId(companyId);
		List<CompanyMemberLevelConfig> companyLeveList = companyMemberLevelConfigServiceImpl
				.queryMemberLevelList(search);
		if (null != companyLeveList) {
			for (CompanyMemberLevelConfig companylevel : companyLeveList) {
				if (null != companylevel) {
					for (ClassTypeMemberDiscount classlevel : classLevelList) {
						if (null != classlevel && (companylevel.getId() == classlevel.getMemberId() || companylevel.getId().equals(classlevel.getMemberId()))) {
							companylevel.setDiscount(classlevel.getMemberDiscount());
							companylevel.setUseFlag(1);
							companylevel.setDiscountType(classlevel.getDiscountType());
						}
					}
				}
			}
		}
		return companyLeveList;
	}

	/**
	 * Class Name: CompanyMemberLevelConfigController.java
	 * 
	 * @Description: 禁用启用会员等级
	 * @author xukaiqiang
	 * @date 2016年5月23日 下午7:46:30
	 * @modifier
	 * @modify-date 2016年5月23日 下午7:46:30
	 * @version 1.0
	 * @param memberLevelConfigId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/forbiddenMemberLevel/{memberLevelConfigId}")
	public int forbiddenMemberLevel(@PathVariable Integer memberLevelConfigId) {
		int flag = 0;
		log.info("进入禁用或者启用会员方法中........memberLevelConfigId=" + memberLevelConfigId);
		CompanyMemberLevelDetail detail = companyMemberLevelDetailServiceImpl
				.findCompanyMemberLevelDetailById(memberLevelConfigId);
		if (detail.getStatus() == 1) {
			log.info("当前会员等级的状态为" + detail.getStatus());
			// 禁用
			detail.setStatus(0);
			flag = 0;
		} else if (detail.getStatus() == 0) {
			log.info("当前会员等级的状态为" + detail.getStatus());
			detail.setStatus(1);
			flag = 1;
		}
		log.info("更新会员等级详情状态............");
		companyMemberLevelDetailServiceImpl.update(detail);
		log.info("退出禁用或者启用会员方法中............");
		return flag;
	}

	/**
	 * Class Name: CompanyMemberLevelConfigController.java
	 * 
	 * @Description: 跳转到会员等级编辑页面
	 * @author xukaiqiang
	 * @date 2016年6月1日 上午11:20:01
	 * @modifier
	 * @modify-date 2016年6月1日 上午11:20:01
	 * @version 1.0
	 * @return
	 */
	@RequestMapping("/toUpdatePage/{id}")
	public String toUpdatePage(@PathVariable Integer id, Model model) {
		log.info("进入会员等级修改页面。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
		// commonDomain.jsp中的信息
		Integer companyId = WebUtils.getCurrentCompanyId();
		Company company = companyService.findCompanyById(companyId);
		model.addAttribute("company", company);
		CompanyMemberService cms = companyMemberServiceServiceImpl.findByCompanyId(companyId);
		CompanyServiceStatic css = companyServiceStaticServiceImpl.findByCompanyId(companyId);
		model.addAttribute("cms", cms);
		model.addAttribute("css", css);
		// 查询会员设置中的值，如果是购买型会员，购买方式为购买，如果是累积型会员，购买方式显示累积消费
		// 通过公司的companyId查找公司的信息
		CompanyMemberConfig companyMemberConfig = companyMemberConfigServiceImpl
				.findCompanyMemberConfigByCompanyId(companyId);
		model.addAttribute("companyMemberConfig", companyMemberConfig);
		log.info("获取commonDomain.jsp中的信息。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。公司名称：" + company.getCompanyName());
		CompanyMemberLevelConfigVo companyMemberLevelConfigVo = new CompanyMemberLevelConfigVo();
		try {
			// 根据编号获取数据
			log.info("根据编号获取数据会员等级id为。。。。。。。。。。。" + id);
			CompanyMemberLevelConfig companyMemberLevelConfig = companyMemberLevelConfigServiceImpl
					.findCompanyMemberLevelConfigById(id);
			// entity=>Vo
			companyMemberLevelConfigVo = (CompanyMemberLevelConfigVo) EntityDtoConverter
					.bean2Dto(companyMemberLevelConfig, companyMemberLevelConfigVo);
			log.info("bean2Vo完成。。。。。。。。。。。");
			companyMemberLevelConfigVo.setId(companyMemberLevelConfig.getId());
			// 根据Member_id获取会员等级详情
			List<CompanyMemberLevelDetail> levelList = companyMemberLevelConfigServiceImpl
					.findCompanyMemberLevelConfigDetailById(companyMemberLevelConfigVo);
			log.info(" 根据Member_id获取会员等级详情..........companyMemberLevelConfigVo.getId()......."
					+ companyMemberLevelConfigVo.getId());
			companyMemberLevelConfigVo.setList(levelList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		companyMemberLevelConfigVo.setMethod("update");
		companyMemberLevelConfigVo
				.setIco("http://" + propertiesUtil.getProjectImageUrl() + "/" + companyMemberLevelConfigVo.getIco());
		// 封装
		model.addAttribute("memberLevelAndDetail", companyMemberLevelConfigVo);
		log.info("封装memberLevelAndDetail。。。。。。。。。。。。等级名称。。。。" + companyMemberLevelConfigVo.getName());
		log.info("退出会员等级修改页面..........................");
		return "company/vip/companyMemberLevelConfig";
	}

	/**
	 * 
	 * Class Name: CompanyMemberLevelConfigController.java
	 * @Description: 查询公司会员等级
	 * @author zhang.zx
	 * @date 2016年6月20日 下午6:56:05
	 * @modifier
	 * @modify-date 2016年6月20日 下午6:56:05
	 * @version 1.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/queryMemberLists")
	public List<CompanyMemberLevelConfig> queryCompanyMemberLists(){
		Integer companyId = WebUtils.getCurrentCompanyId();
		CompanyMemberLevelConfig search = new CompanyMemberLevelConfig();
		search.setCompanyId(companyId);
		List<CompanyMemberLevelConfig> companyLeveList = companyMemberLevelConfigServiceImpl
				.queryMemberLevelList(search);
		return companyLeveList;
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
}
