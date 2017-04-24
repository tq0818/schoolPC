package com.yuxin.wx.system.mapper;

import java.util.List;

import com.yuxin.wx.common.BaseMapper;
import com.yuxin.wx.model.system.LongitudinalTableData;
import com.yuxin.wx.model.system.SysBlackList;

public interface LongitudinalTableDataMapper extends BaseMapper<LongitudinalTableData>{
	List<LongitudinalTableData>  findByCompany(Integer companyId);
	List<LongitudinalTableData> query(LongitudinalTableData data);
	Integer getCurrtRow(LongitudinalTableData data);
	Integer getSequence();
}
