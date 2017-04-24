package com.yuxin.wx.homework.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.mysql.fabric.xmlrpc.base.Data;
import com.yuxin.wx.api.homework.IHomeworkStudentCompleteService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.homework.mapper.HomeworkStudentCompleteMapper;
import com.yuxin.wx.model.homework.HomeworkStudentComplete;
import com.yuxin.wx.vo.homework.StudentHomeWorkVo;
import com.yuxin.wx.vo.homework.StudentHomeworkAttachmentVo;


/**
 * Service Implementation:HomeworkStudentComplete
 * @author chopin
 * @date 2016-12-15
 */
@Service
@Transactional
public class HomeworkStudentCompleteServiceImpl extends BaseServiceImpl implements IHomeworkStudentCompleteService {

	@Autowired
	private HomeworkStudentCompleteMapper homeworkStudentCompleteMapper;
	
	/**
	 * 
	* @Title: saveHomeworkStudentComplete
	* @Description: 新增HomeworkStudentComplete
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	@Override
	public void insert(HomeworkStudentComplete entity){
		homeworkStudentCompleteMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveHomeworkStudentComplete 
	* @Description: 批量新增HomeworkStudentComplete
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<HomeworkStudentComplete> entity){
		homeworkStudentCompleteMapper.batchInsert(entity);
	};
	
	/**
	 * 
	* @Title: updateHomeworkStudentComplete 
	* @Description: 编辑HomeworkStudentComplete
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	@Override
	public void update(HomeworkStudentComplete entity){
		homeworkStudentCompleteMapper.update(entity);
	};
	
	/**
	 * 
	* @Title: deleteHomeworkStudentCompleteById 
	* @Description: 根据id删除HomeworkStudentComplete
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	 @Override
	public void deleteHomeworkStudentCompleteById(Integer id){
		homeworkStudentCompleteMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteHomeworkStudentCompleteByIds 
	* @Description: 根据id批量删除HomeworkStudentComplete
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	@Override
	public void deleteHomeworkStudentCompleteByIds(Integer[] ids){
		homeworkStudentCompleteMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findHomeworkStudentCompleteById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	@Override
	public HomeworkStudentComplete findHomeworkStudentCompleteById(Integer id){
		return homeworkStudentCompleteMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findHomeworkStudentCompleteByPage 
	* @Description: 分页查询
	* @return
	* @return List<HomeworkStudentComplete>    返回类型 
	* @throws 
	* @exception 
	* @date 2016-12-15
	* @user by chopin
	 */
	@Override
	public List<HomeworkStudentComplete> findHomeworkStudentCompleteByPage(HomeworkStudentComplete search){
		return homeworkStudentCompleteMapper.page(search);
	}
	
	@Override
	public List<HomeworkStudentComplete> findByHomeworkId(StudentHomeWorkVo homeworkId) {
		return homeworkStudentCompleteMapper.findByHomeworkId(homeworkId);
	};

	@Override
	public PageFinder<StudentHomeWorkVo> queryStudentHomeworks(StudentHomeWorkVo shv) {
		List<StudentHomeWorkVo> data = homeworkStudentCompleteMapper.queryStudentHomeworkList(shv); 
		int count = homeworkStudentCompleteMapper.queryStudentHomeworkListCount(shv);
		PageFinder<StudentHomeWorkVo> pageFinder = new PageFinder<StudentHomeWorkVo>(shv.getPage(), shv.getPageSize(), count, data);
		return pageFinder;
	}

	@Override
	public StudentHomeWorkVo queryAllStudentHomework(StudentHomeWorkVo shv) {
		Integer alreadyCommit = 0;
		Integer alreadyRead   = 0;
		
		List<StudentHomeWorkVo> data = homeworkStudentCompleteMapper.queryAllStudentHomework(shv); 
		Integer studentNum = data.size();
		shv.setStudentNum(studentNum);
		
		for (StudentHomeWorkVo studentHomeWorkVo : data) {
			if(studentHomeWorkVo.getStatus() != null && 3 == studentHomeWorkVo.getStatus()){
				alreadyRead++;
			}
			if(studentHomeWorkVo.getStatus() != null && ( 1 == studentHomeWorkVo.getStatus() || 3 == studentHomeWorkVo.getStatus())){
				alreadyCommit++;
			}
		}
		
		shv.setAlreadyCommitNum(alreadyCommit);
		shv.setAlreadyReadNum(alreadyRead);
		
		if(alreadyRead == alreadyCommit){
			shv.setStudentHomeworkListStatus("<em class=\"colorBlu\">作业已完成批阅</em>");
		}else{
			shv.setStudentHomeworkListStatus("<em class=\"colorRed\">作业未完成批阅</em>");
		}
		if(studentNum == 0){
			shv.setStudentHomeworkListStatus("<em class=\"colorRed\">无学员</em>");
		}
		return shv;
	}

	@Override
	public List<StudentHomeworkAttachmentVo> queryStudentHomeworkAndReadList(StudentHomeworkAttachmentVo shav) {
		return homeworkStudentCompleteMapper.queryStudentHomeworkAndReadList(shav);
	};
}
