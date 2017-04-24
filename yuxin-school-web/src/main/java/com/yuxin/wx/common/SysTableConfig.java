package com.yuxin.wx.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yuxin.wx.api.system.ISysConfigTableService;
import com.yuxin.wx.model.system.SysConfigTable;
import com.yuxin.wx.vo.system.ConfigTableVo;

/**
 * @ClassName: SysTableConfig
 * @Description: (该类主要用于存放在新增表数据的时候，判断表字段是否为必填字段)
 * @author wang.zx 
 * @date 2014-12-9 下午3:26:16
 * @version 1.0
 */
public class SysTableConfig {
	
	protected static final Log log = LogFactory.getLog("log");
	
	@Autowired
	private ISysConfigTableService sysConfigTableServiceImpl;
	public static Map<String,Map<Integer,Integer>> map = new HashMap<String, Map<Integer,Integer>>();
	
	/**
	 * @Description:(启动项目初始化表结构必填字段)
	 * @author wang.zx 
	 * @date 2014-12-9 下午3:34:06
	 * @version 1.0
	 */
	public void init(){
		List<SysConfigTable> tableList = sysConfigTableServiceImpl.queryAll();
		
		try {
			if(tableList != null && tableList.size() > 0){
				for(SysConfigTable config : tableList){
					Map<Integer,Integer> configMap = new HashMap<Integer, Integer>();
					configMap.put(config.getIsRequired(), config.getIsSystem());
					map.put(config.getTableName()+config.getColumnName(), configMap);
				}
			}
		} catch (Exception e) {
			log.error("初始化表结构必填字段失败",e);
			e.printStackTrace();
		}
	}
	
	/**
	 * @Description:(根据key获取对应的是否必填项的值)
	 * @author wang.zx 
	 * @date 2014-12-9 下午4:45:33
	 * @version 1.0
	 * @param tableName
	 * @param columnName
	 * @return
	 */
	public static ConfigTableVo getConfig(String tableName, String columnName){
		ConfigTableVo configVo = new ConfigTableVo();
		String key = tableName + columnName;
		if(key != null && key.length() > 0){
			Map<Integer,Integer> configMap = map.get(key);
			if(configMap != null && configMap.size() > 0){
				for (Entry<Integer, Integer> entry : configMap.entrySet()) {
					configVo.setRequired(entry.getKey() == 1 ? true : false);
					configVo.setSystem(entry.getValue() == 1 ? true : false);
				}
			}
		}
		return configVo;
	}
	
	/**
	 * @Description:(添加表字段是否为必填)
	 * @author wang.zx 
	 * @date 2014-12-9 下午4:01:12
	 * @version 1.0
	 * @param tableName
	 * @param columnName
	 * @param required
	 * @param system
	 */
	public String addConfig(SysConfigTable config){
		try {
			Map<Integer,Integer> configMap = new HashMap<Integer, Integer>();
			configMap.put(config.getIsRequired(), config.getIsSystem());
			map.put(config.getTableName()+config.getColumnName(),configMap);
			sysConfigTableServiceImpl.insert(config);
			return "succ";
		} catch (Exception e) {
			e.printStackTrace();
			log.error("添加表结构必填字段失败,表名："+ config.getTableName()+ "字段名："+ config.getColumnName());
			return "fail";
		}
	}
	
	/**
	 * @Description:(修改表字段是否为必填)
	 * @author wang.zx 
	 * @date 2014-12-9 下午4:03:04
	 * @version 1.0
	 * @param tableName
	 * @param columnName
	 * @param required
	 * @param system
	 */
	public String updateConfig(SysConfigTable config){
		String mapKey = config.getTableName()+config.getColumnName();
		try {
			Map<Integer,Integer> configMap = map.get(mapKey);
			if(configMap != null && configMap.size() > 0){
				map.remove(mapKey);
				sysConfigTableServiceImpl.deleteSysConfigTableByTableNameAndColumn(config.getTableName(), config.getColumnName());
				addConfig(config);
			}
			return "succ";
		} catch (Exception e) {
			e.printStackTrace();
			log.error("更新表结构必填字段失败，表名："+config.getTableName() + "字段名：" + config.getColumnName());
			return "fail";
		}
	}
	
	/**
	 * 
	 * @Description:(删除表字段是否为必填)
	 * @author wang.zx 
	 * @date 2014-12-9 下午4:26:17
	 * @version 1.0
	 * @param tableName
	 * @param columnName
	 * @param required
	 * @param system
	 * @return
	 */
	public String delConfig(SysConfigTable config){
		String mapKey = config.getTableName()+config.getColumnName();
		try {
			Map<Integer,Integer> configMap = map.get(mapKey);
			if(configMap != null && configMap.size() > 0){
				map.remove(mapKey);
				sysConfigTableServiceImpl.deleteSysConfigTableByTableNameAndColumn(config.getTableName(), config.getColumnName());
			}
			return "succ";
		} catch (Exception e) {
			e.printStackTrace();
			log.error("删除表结构必填字段失败，表名："+config.getTableName() + "字段名：" + config.getColumnName());
			return "fail";
		}
	}
	
}
