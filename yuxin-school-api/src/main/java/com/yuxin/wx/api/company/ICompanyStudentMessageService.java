package com.yuxin.wx.api.company;

import java.util.List;
import java.util.Map;

import com.yuxin.wx.model.company.CompanyStudentMessage;
import com.yuxin.wx.model.user.UserMessage;
import com.yuxin.wx.vo.student.StuMessageVo;
/**
 * Service Interface:CompanyStudentMessage
 * @author wang.zx
 * @date 2015-6-4
 */
public interface ICompanyStudentMessageService  {
	/**
	 * 
	* @Title: saveCompanyStudentMessage
	* @Description: 新增CompanyStudentMessage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-4
	* @user by wangzx
	 */
	void insert(CompanyStudentMessage entity);
	
	/**
	 * 
	* @Title: batchSaveCompanyStudentMessage 
	* @Description: 批量新增CompanyStudentMessage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-4
	* @user by wangzx
	 */
	void batchInsert(List<CompanyStudentMessage> list);
	
	/**
	 * 
	* @Title: updateCompanyStudentMessage 
	* @Description: 编辑CompanyStudentMessage
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-4
	* @user by wangzx
	 */
	void update(CompanyStudentMessage entity);
	
	/**
	 * 
	* @Title: deleteCompanyStudentMessageById 
	* @Description: 根据id删除CompanyStudentMessage
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-4
	* @user by wangzx
	 */
	void deleteCompanyStudentMessageById(Integer id);
	
	/**
	 * 
	* @Title: deleteCompanyStudentMessageByIds 
	* @Description: 根据id批量删除CompanyStudentMessage
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-4
	* @user by wangzx
	 */
	void deleteCompanyStudentMessageByIds(Integer[] ids);
	
	/**
	 * 
	* @Title: findCompanyStudentMessageById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-4
	* @user by wangzx
	 */
	CompanyStudentMessage findCompanyStudentMessageById(Integer id);
	
	/**
	 * 
	* @Title: findCompanyStudentMessageByPage 
	* @Description: 分页查询
	* @return
	* @return List<CompanyStudentMessage>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-6-4
	* @user by wangzx
	 */
	List<CompanyStudentMessage> findCompanyStudentMessageByPage(CompanyStudentMessage search);
	
	/**
	 * 
	 * Class Name: ICompanyStudentMessageService.java
	 * @Description: 查询操作人 姓名
	 * @author 周文斌
	 * @date 2015-6-4 上午11:46:53
	 * @version 1.0
	 * @param id
	 * @return
	 */
	String findCreatorName(Integer id);
	
	/**
	 * 
	 * Class Name: ICompanyStudentMessageService.java
	 * @Description: 查询 通知信息 列表
	 * @author 周文斌
	 * @date 2015-6-4 上午11:51:59
	 * @version 1.0
	 * @param companyStudentMessage
	 * @return
	 */
	List<CompanyStudentMessage> selMsgByCond(CompanyStudentMessage companyStudentMessage);
	
	/**
	 * 
	 * Class Name: ICompanyStudentMessageService.java
	 * @Description: 总数
	 * @author 周文斌
	 * @date 2015-6-4 上午11:53:32
	 * @version 1.0
	 * @param companyStudentMessage
	 * @return
	 */
	Integer selMsgCount(CompanyStudentMessage companyStudentMessage);
	/**
	 * 
	 * Class Name: ICompanyStudentMessageService.java
	 * @Description: 根据班型id查询学员通知情况
	 * @author 周文斌
	 * @date 2015-6-3 下午6:29:29
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<StuMessageVo> findStudentMsgByClassId(StuMessageVo stuMessageVo);
	
	/**
	 * 
	 * Class Name: ICompanyStudentMessageService.java
	 * @Description: 根据班号id查询学员通知情况
	 * @author 周文斌
	 * @date 2015-6-3 下午6:42:40
	 * @version 1.0
	 * @param param
	 * @return
	 */
	List<StuMessageVo> findStudentMsgByModuleId(StuMessageVo stuMessageVo);
	
	/**
	 * 
	 * Class Name: ICompanyStudentMessageService.java
	 * @Description: 查询 总数
	 * @author 周文斌
	 * @date 2015-6-3 下午7:03:07
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Integer findStudentMsgCountByClassId(StuMessageVo stuMessageVo);

	/**
	 * 
	 * Class Name: ICompanyStudentMessageService.java
	 * @Description: 查询 总数
	 * @author 周文斌
	 * @date 2015-6-3 下午7:03:07
	 * @version 1.0
	 * @param param
	 * @return
	 */
	Integer findStudentMsgCountByModuleId(StuMessageVo stuMessageVo);
	
	/**
	 * 
	 * Class Name: ICompanyStudentMessageService.java
	 * @Description: 插入站内通知
	 * @author 周文斌
	 * @date 2015-6-9 下午12:29:11
	 * @version 1.0
	 * @param um
	 */
	void insertUserMessage(UserMessage um);
	
	/**
	 * 
	 * Class Name: ICompanyStudentMessageService.java
	 * @Description: 插线指定通知的学员
	 * @author 周文斌
	 * @date 2015-6-9 下午5:14:58
	 * @version 1.0
	 * @param MessageId
	 * @return
	 */
	List<StuMessageVo> findNoticeList(StuMessageVo stuMessageVo);
	
	/**
	 * 
	 * Class Name: ICompanyStudentMessageService.java
	 * @Description: 查询指定通知总数
	 * @author 周文斌
	 * @date 2015-6-9 下午5:16:34
	 * @version 1.0
	 * @param messageId
	 * @return
	 */
	Integer findNoticeListCount(StuMessageVo stuMessageVo);
	
	/**
	 * @Description: 查看当前站内信是否已经发送过
	 * @author zx
	 * @date 2015-8-20 下午3:54:28
	 * @version 1.0
	 * @param message
	 * @return
	 */
	Integer findSameCount(UserMessage message);

	List<StuMessageVo> findStudentMsgByGroup(StuMessageVo stuMessageVo);

	Integer findStudentMsgByGroupCount(StuMessageVo stuMessageVo);
}