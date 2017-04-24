package com.yuxin.wx.api.tiku;

import java.util.List;

import com.yuxin.wx.model.tiku.TikuCategory;
import com.yuxin.wx.vo.tiku.TikuCategoryVo;
/**
 * Service Interface:TikuCategory
 * @author wang.zx
 * @date 2015-7-8
 */
public interface ITikuCategoryService  {
	/**
	 * 
	* @Title: saveTikuCategory
	* @Description: 新增TikuCategory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void insert(TikuCategory entity);
	
	/**
	 * 
	* @Title: batchSaveTikuCategory 
	* @Description: 批量新增TikuCategory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void batchInsert(List<TikuCategory> list);
	
	/**
	 * 
	* @Title: updateTikuCategory 
	* @Description: 编辑TikuCategory
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void update(TikuCategory entity);
	
	/**
	 * 
	* @Title: deleteTikuCategoryById 
	* @Description: 根据id删除TikuCategory
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuCategoryById(Integer id);
	
	/**
	 * 
	* @Title: deleteTikuCategoryByIds 
	* @Description: 根据id批量删除TikuCategory
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	void deleteTikuCategoryByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findTikuCategoryById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	TikuCategory findTikuCategoryById(Integer id);
	
	/**
	 * 
	* @Title: findTikuCategoryByPage 
	* @Description: 分页查询
	* @return
	* @return List<TikuCategory>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-7-8
	* @user by wangzx
	 */
	List<TikuCategory> findTikuCategoryByPage(TikuCategory search);
	/**
	 * 
	 * Class Name: ITikuCategoryService.java
	 * @Description: 题库首页查询信息
	 * @author yuchanglong
	 * @date 2015年7月9日 下午2:27:47
	 * @version 1.0
	 * @param search
	 * @return
	 */
	List<TikuCategoryVo> findTikuCategoryVo(TikuCategory search);
	/**
	 * 
	 * Class Name: ITikuCategoryService.java
	 * @Description: 插入题库分类和科目
	 * @author yuchanglong
	 * @date 2015年7月10日 上午9:44:35
	 * @version 1.0
	 */
	void insertTikuAndSub(TikuCategory category,String [] subNames);
	/**
	 * 
	 * Class Name: ITikuCategoryService.java
	 * @Description:根据id删除题库类别下的所有信息
	 * @author yuchanglong
	 * @date 2015年9月10日 下午12:28:43
	 * @version 1.0
	 * @param id
	 */
	void delAllById(Integer id);
	
	Integer findTopCountById(Integer id);
	/**
	 * 
	 * Class Name: ITikuCategoryService.java
	 * @Description: 添加题库时名字去重
	 * @author yuchanglong
	 * @date 2015年9月24日 下午6:31:32
	 * @version 1.0
	 * @param category
	 * @return
	 */
	Integer findTikuByComIdAndTName(TikuCategory category);
	
	List<TikuCategory> findCateList(Integer companyId);
}