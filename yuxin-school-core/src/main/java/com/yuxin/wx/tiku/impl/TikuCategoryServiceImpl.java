package com.yuxin.wx.tiku.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.tiku.ITikuCategoryService;
import com.yuxin.wx.model.tiku.TikuCategory;
import com.yuxin.wx.model.tiku.TikuSubject;
import com.yuxin.wx.tiku.mapper.TikuCategoryMapper;
import com.yuxin.wx.tiku.mapper.TikuChapterMapper;
import com.yuxin.wx.tiku.mapper.TikuExampointMapper;
import com.yuxin.wx.tiku.mapper.TikuSectionMapper;
import com.yuxin.wx.tiku.mapper.TikuSetMapper;
import com.yuxin.wx.tiku.mapper.TikuSubjectMapper;
import com.yuxin.wx.tiku.mapper.TikuTopicMapper;
import com.yuxin.wx.vo.tiku.TikuCategoryVo;

/**
 * Service Implementation:TikuCategory
 * @author wang.zx
 * @date 2015-7-8
 */
@Service
@Transactional
public class TikuCategoryServiceImpl extends BaseServiceImpl implements ITikuCategoryService {

	@Autowired
	private TikuCategoryMapper tikuCategoryMapper;
	
	@Autowired
	private TikuSubjectMapper tikuSubjectMapper;
	
	@Autowired
	private TikuChapterMapper tikuChapterMapper;
	
	@Autowired
	private TikuExampointMapper tikuExampointMapper;
	
	@Autowired
	private TikuSectionMapper tikuSectionMapper;
	
	@Autowired
	private TikuTopicMapper tikuTopicMapper;
	
	@Autowired
	private TikuSetMapper tikuSetMapper;
	
	/**
	 * 
	* @Title: saveTikuCategory
	* @Description: 新增TikuCategory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void insert(TikuCategory entity){
		tikuCategoryMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveTikuCategory 
	* @Description: 批量新增TikuCategory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<TikuCategory> entity){
		tikuCategoryMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateTikuCategory 
	* @Description: 编辑TikuCategory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void update(TikuCategory entity){
		tikuCategoryMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteTikuCategoryById 
	* @Description: 根据id删除TikuCategory
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	 @Override
	public void deleteTikuCategoryById(Integer id){
		tikuCategoryMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteTikuCategoryByIds 
	* @Description: 根据id批量删除TikuCategory
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public void deleteTikuCategoryByIds(Integer[] ids){
		tikuCategoryMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findTikuCategoryById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public TikuCategory findTikuCategoryById(Integer id){
		return tikuCategoryMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findTikuCategoryByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuCategory>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by chopin
	 */
	@Override
	public List<TikuCategory> findTikuCategoryByPage(TikuCategory search){
		return tikuCategoryMapper.page(search);
	}

	@Override
	public List<TikuCategoryVo> findTikuCategoryVo(TikuCategory search) {
		// TODO Auto-generated method stub
		return tikuCategoryMapper.findTikuCategoryVo(search);
	}

	@Override
	public void insertTikuAndSub(TikuCategory category, String [] subNames) {
		// TODO Auto-generated method stub
		if(category.getId()!=null){
			tikuCategoryMapper.update(category);
		}else{
			tikuCategoryMapper.insert(category);
		}
		if(subNames.length>0){
			for (String subName : subNames) {
				if(subName!=""){
					TikuSubject subject = new TikuSubject();
					subject.setDelFlag(0);
					subject.setSubjectName(subName);
					subject.setCategoryId(category.getId());
					tikuSubjectMapper.insert(subject);
				}
				
			}
		}
	}

	@Override
	public void delAllById(Integer id) {
		// TODO Auto-generated method stub
		tikuTopicMapper.delByTikuId(id);
		tikuExampointMapper.deleteByTikuId(id);
		tikuSectionMapper.deleteByTikuId(id);
		tikuChapterMapper.deleteByTikuId(id);
		tikuSubjectMapper.delByTikuId(id);
		tikuCategoryMapper.deleteById(id);
		tikuSetMapper.delByCateId(id);
	}

	@Override
	public Integer findTopCountById(Integer id) {
		// TODO Auto-generated method stub
		return tikuCategoryMapper.findTopCountById(id);
	}

	@Override
	public Integer findTikuByComIdAndTName(TikuCategory category) {
		// TODO Auto-generated method stub
		return tikuCategoryMapper.findTikuByComIdAndTName(category);
	}

	@Override
	public List<TikuCategory> findCateList(Integer companyId) {
		// TODO Auto-generated method stub
		return tikuCategoryMapper.findCateList(companyId);
	}

	
}
