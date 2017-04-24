package com.yuxin.wx.tiku.impl;

import com.yuxin.wx.api.tiku.ITikuPaperService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.tiku.TikuPaper;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.tiku.mapper.TikuPaperMapper;
import com.yuxin.wx.user.mapper.UsersMapper;
import com.yuxin.wx.vo.tiku.TikuExamPaperVo;
import com.yuxin.wx.vo.tiku.TikuPaperVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import java.util.List;

/**
 * Service Implementation:TikuPaper
 * @author wang.zx
 * @date 2015-7-8
 */
@Service
@Transactional
public class TikuPaperServiceImpl extends BaseServiceImpl implements ITikuPaperService {

	@Autowired
	private TikuPaperMapper tikuPaperMapper;
	
	@Autowired
	private UsersMapper userMapper;
	
	/**
	 * 
	* @Title: saveTikuPaper
	* @Description: 新增TikuPaper
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void insert(TikuPaper entity){
		tikuPaperMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveTikuPaper 
	* @Description: 批量新增TikuPaper
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<TikuPaper> entity){
		tikuPaperMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateTikuPaper 
	* @Description: 编辑TikuPaper
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void update(TikuPaper entity){
		tikuPaperMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteTikuPaperById 
	* @Description: 根据id删除TikuPaper
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	 @Override
	public void deleteTikuPaperById(Integer id){
		tikuPaperMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteTikuPaperByIds 
	* @Description: 根据id批量删除TikuPaper
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void deleteTikuPaperByIds(Integer[] ids){
		tikuPaperMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findTikuPaperById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public TikuPaper findTikuPaperById(Integer id){
		return tikuPaperMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findTikuPaperByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuPaper>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public List<TikuPaper> findTikuPaperByPage(TikuPaper search){
		return tikuPaperMapper.page(search);
	}
	/**
	 * 
	* @Title: findTikuPaperByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuPaper>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public PageFinder<TikuPaperVo> findTikuPaperVoByPage(TikuPaper search){
		List<TikuPaperVo> list = tikuPaperMapper.pagevo(search);
		Integer count = tikuPaperMapper.pageCount(search);
		PageFinder<TikuPaperVo> pageFinder=new PageFinder<TikuPaperVo>(search.getPage(), search.getPageSize(), count, list);
		return pageFinder;
	}
	
	@Override
	public PageFinder<TikuPaperVo> containhw(TikuPaper search){
		List<TikuPaperVo> list = tikuPaperMapper.containhw(search);
		Integer count = tikuPaperMapper.containhwCount(search);
		PageFinder<TikuPaperVo> pageFinder=new PageFinder<TikuPaperVo>(search.getPage(), search.getPageSize(), count, list);
		return pageFinder;
	}

	@Override
	public PageFinder<TikuPaper> findTikuUserByPage(TikuPaper search) {
		// TODO Auto-generated method stub
		List<TikuPaper> list = tikuPaperMapper.page(search);
		Integer createrId = 0;
		Integer auditorId = 0;
		Users creater = null;
		Users auditor = null;
		for (TikuPaper tp : list) {
			createrId = 0;
			auditorId = 0;
			
			createrId = tp.getCreator();
			if(tp.getAuditor()!=null){
				auditorId = tp.getAuditor();
			}
			creater = userMapper.findById(createrId);
			if(creater!=null){
				tp.setCreatorName(creater.getUsername());
			}
			auditor = userMapper.findById(auditorId);
			if(auditor!=null){
				tp.setAuditorName(auditor.getUsername());
			}
		}
		Integer count = tikuPaperMapper.pageCount(search);
		PageFinder<TikuPaper> pageFinder=new PageFinder<TikuPaper>(search.getPage(), search.getPageSize(), count, list);
		return pageFinder;
	}

	@Override
	public List<TikuExamPaperVo> findPaperByExam(TikuPaper paper) {
		// TODO Auto-generated method stub
		return tikuPaperMapper.findPaperByExam(paper);
	}

	@Override
	public Integer findPaperByExamCount(TikuPaper paper) {
		// TODO Auto-generated method stub
		return tikuPaperMapper.findPaperByExamCount(paper);
	}
	
}
