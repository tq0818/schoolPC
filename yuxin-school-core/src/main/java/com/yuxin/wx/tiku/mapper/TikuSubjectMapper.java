package com.yuxin.wx.tiku.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.tiku.TikuSubject;

/**
 * Service Interface:Users
 * @author chopin
 * @date 2015-3-12
 */
public interface TikuSubjectMapper extends BaseMapper<TikuSubject> {
	List<TikuSubject> findTikuSubject(TikuSubject search);

	/**
	 * 
	 * Class Name: ITikuSubjectService.java
	 * @Description: 查询科目
	 * @author 周文斌
	 * @date 2015-7-9 下午2:35:46
	 * @version 1.0
	 * @param categoryId
	 * @return
	 */
	List<TikuSubject> findSubByCategoryId(Integer categoryId);
	
	/**
	 * 
	 * Class Name: TikuSubjectMapper.java
	 * @Description: 根据题库类别id删除
	 * @author yuchanglong
	 * @date 2015年9月10日 下午12:43:41
	 * @version 1.0
	 * @param tikuId
	 */
	void delByTikuId(Integer tikuId);
	
	Integer findCountBySubName(TikuSubject subject);
}