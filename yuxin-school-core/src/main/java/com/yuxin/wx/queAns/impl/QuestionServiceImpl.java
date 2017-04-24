package com.yuxin.wx.queAns.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;import com.yuxin.wx.common.BaseServiceImpl;

import com.yuxin.wx.api.queAns.IQuestionService;
import com.yuxin.wx.common.PageFinder;
import com.yuxin.wx.model.queAns.QueQuestion;
import com.yuxin.wx.model.user.Users;
import com.yuxin.wx.model.user.UsersFront;
import com.yuxin.wx.queAns.mapper.QuestionMapper;
import com.yuxin.wx.user.mapper.UsersFrontMapper;
import com.yuxin.wx.user.mapper.UsersMapper;
import com.yuxin.wx.vo.queAns.QuestionVo;


/**
 * Service Implementation:Question
 * @author wang.zx
 * @date 2015-12-9
 */
@Service
@Transactional
public class QuestionServiceImpl extends BaseServiceImpl implements IQuestionService {

	@Autowired
	private QuestionMapper questionMapper;
	
	@Autowired
	private UsersFrontMapper usersFrontMapper;
	
	@Autowired
	private UsersMapper usersMapper;
	
	/**
	 * 
	* @Title: saveQuestion
	* @Description: 新增Question
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by chopin
	 */
	@Override
	public void insert(QueQuestion entity){
		questionMapper.insert(entity);
	};
	
	/**
	 * 
	* @Title: batchSaveQuestion 
	* @Description: 批量新增Question
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by chopin
	 */
	@Override
	public void batchInsert(List<QueQuestion> T){
		questionMapper.batchInsert(T);
	};
	
	/**
	 * 
	* @Title: updateQuestion 
	* @Description: 编辑Question
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by chopin
	 */
	@Override
	public void update(QueQuestion T){
		questionMapper.update(T);
	};
	
	/**
	 * 
	* @Title: deleteQuestionById 
	* @Description: 根据id删除Question
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by chopin
	 */
	 @Override
	public void deleteQuestionById(Integer id){
		 questionMapper.deleteById(id);
	};
	
	/**
	 * 
	* @Title: deleteQuestionByIds 
	* @Description: 根据id批量删除Question
	* @param ids
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by chopin
	 */
	@Override
	public void deleteQuestionByIds(Integer[] ids){
		questionMapper.deleteByIds(ids);
	};
	
	/**
	 * 
	* @Title: findQuestionById 
	* @Description: 根据id查询
	* @param id
	* @return void    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by chopin
	 */
	@Override
	public QueQuestion findQuestionById(Integer id){
		return questionMapper.findById(id);
	};
	
	/**
	 * 
	* @Title: findQuestionByPage 
	* @Description: 分页查询
	* @return
	* @return List<Question>    返回类型 
	* @throws 
	* @exception 
	* @date 2015-12-9
	* @user by chopin
	 */
	@Override
	public List<QueQuestion> findQuestionByPage(QueQuestion search){
		return questionMapper.page(search);
	}

	@Override
	public PageFinder<QuestionVo> findVoByPage(QueQuestion search) {
		// TODO Auto-generated method stub
		List<QuestionVo> list = questionMapper.findQuestionVoByPage(search);
		for (QuestionVo qv : list) {
			Integer userId = qv.getUserId();
			String uType = qv.getQuestionType();
			if(uType.equals("QUESTION_STUDENT")){
				UsersFront front = usersFrontMapper.findById(userId);
				if(front == null){
					continue;
				}
				//获得提问者昵称
				if(front.getNickName() != null){
					qv.setUserName(front.getNickName());
				}else if(front.getMobile() != null && front.getMobile().length() > 7){
					qv.setUserName("******" + front.getMobile().substring(7));
				}else{
					qv.setUserName(front.getUsername());
				}
				//获得用户头像（提问者）
				String pic = front.getHeadPicMax();
				qv.setHeadPic(pic);
			}else{
				Users tiMana = usersMapper.findById(userId);
				String tiUserPic = tiMana.getHeadPicUrl();
				qv.setUserName(tiMana.getRealName() != null ? tiMana.getRealName() : ("*******" + tiMana.getMobile().substring(7)));
				qv.setHeadPic(tiUserPic);
			}
			long create = qv.getCreateTime().getTime();
			String diffTime = secToTime(create);
			qv.setTiwenTime(diffTime);
		}
		Integer count = questionMapper.pageCount(search);
		PageFinder<QuestionVo> pageFinder=new PageFinder<QuestionVo>(search.getPage(), search.getPageSize(), count, list);
		return pageFinder;
	}
	
	@Override
	public List<QuestionVo> findTeacherQuestion(Map<String,Object> search) {
		// TODO Auto-generated method stub
		List<QuestionVo> list = questionMapper.findTeacherQuestion(search);
		for (QuestionVo qv : list) {
			Integer userId = qv.getUserId();
			String uType = qv.getQuestionType();
			if(uType.equals("QUESTION_STUDENT")){
				UsersFront front = usersFrontMapper.findById(userId);
				if(front == null){
					continue;
				}
				//获得提问者昵称
				if(front.getNickName() != null){
					qv.setUserName(front.getNickName());
				}else if(front.getMobile() != null){
					qv.setUserName("******" + front.getMobile().substring(7));
				}else{
					qv.setUserName(front.getUsername());
				}
				//获得用户头像（提问者）
				String pic = front.getHeadPicMax();
				qv.setHeadPic(pic);
			}else{
				Users tiMana = usersMapper.findById(userId);
				String tiUserPic = tiMana.getHeadPicUrl();
				qv.setUserName(tiMana.getRealName() != null ? tiMana.getRealName() : ("*******" + tiMana.getMobile().substring(7)));
				qv.setHeadPic(tiUserPic);
			}
			long create = qv.getCreateTime().getTime();
			String diffTime = secToTime(create);
			qv.setTiwenTime(diffTime);
		}
		return list;
	}
	
	/**
	 * 
	 * Class Name: QuestionServiceImpl.java
	 * @Description: 计算多少秒，分，小时，天，时间之前
	 * @author yuchanglong
	 * @date 2015年12月9日 下午7:20:12
	 * @version 1.0
	 * @param time
	 * @return
	 */
	private static String secToTime(long time) { 
		
		long curtime = new Date().getTime();
		long cha = ((curtime - time) / 1000);  
		long m = 60;
		long h = 60 * 60;
		long d = 60 * 60 * 24;
		long yd = 60 *60 * 24 *3 ;
        if (cha < m) {
        	return cha + " 秒之前";
        }else if(cha >= m && cha < h){ 
        	return (cha / m) + " 分钟之前";
        }else if(cha >= h && cha < d){
        	return (cha / h) + " 小时之前";
        }else if(cha >= d && cha < yd){
        	return (cha / d) + " 天之前";
        }else{
        	String t = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date(time));
        	return t;
        }
    }
	
}
