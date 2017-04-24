package com.yuxin.wx.vo.system;

import java.util.Date;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.yuxin.wx.util.ShortDateSerializer;

import com.yuxin.wx.common.BaseEntity;

/**
 * POJO:SysLogStudentOperation
 * 
 * @author wang.zx
 * @date 2017-2-14
 */
@SuppressWarnings("serial")
public class SysLogStudentOperationVo extends BaseEntity {
	
	
	private Integer	userId;		 /* 用户ID */ 
	private String	userName;		 /* 用户姓名 */ 
	private Date	operaTime;		 /* 操作时间 */ 
	private String	operation;		 /* 操作 */ 
	private Double	cost;		 /* 花费 */ 
	private Integer	operationType;		 /* 操作分类 动作 0:购买,1:上直播,2:公开课,3:上录播,4:提问,5:回答,6:评论 */ 
	private Integer sourceId;
	private Integer companyId;
	private long operaTimemis;
	
	private String startTime;	/* 开始时间*/
	private String endTime;		/* 结束时间*/
	
	private String headImg;
	
	private String cuslimit;
	private String cusorder;

	// Constructor
	public SysLogStudentOperationVo() {
	}
	
	/**
	 * full Constructor
	 */
	public SysLogStudentOperationVo(Integer id, Integer userId, String userName
			, Date operaTime, String operation, Double cost, Integer operationType
			,Integer companyId) {
		setId(id);
		this.userId = userId;
		this.userName = userName;
		this.operaTime = operaTime;
		this.operation = operation;
		this.cost = cost;
		this.operationType = operationType;
		this.companyId = companyId;
	}

	// getter && setter
	// 在setter方法最后加上"return this;"并把返回参数改为SysLogStudentOperation可以实现连缀设置属性
	
	public long getOperaTimemis() {
		return operaTimemis;
	}

	public void setOperaTimemis(long operaTimemis) {
		this.operaTimemis = operaTimemis;
	}

	public String getCuslimit() {
		return cuslimit;
	}

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
	}

	public void setCuslimit(String cuslimit) {
		this.cuslimit = cuslimit;
	}

	public String getCusorder() {
		return cusorder;
	}

	public void setCusorder(String cusorder) {
		this.cusorder = cusorder;
	}

	public Integer getUserId() {
		return userId;
	}

	public SysLogStudentOperationVo setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public String getUserName() {
		return userName;
	}

	public SysLogStudentOperationVo setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	
	public Date getOperaTime() {
		return operaTime;
	}

	public SysLogStudentOperationVo setOperaTime(Date operaTime) {
		this.operaTime = operaTime;
		return this;
	}
	
	
	public String getOperation() {
		return operation;
	}

	public SysLogStudentOperationVo setOperation(String operation) {
		this.operation = operation;
		return this;
	}
	
	
	public Double getCost() {
		return cost;
	}

	public SysLogStudentOperationVo setCost(Double cost) {
		this.cost = cost;
		return this;
	}
	
	
	public Integer getOperationType() {
		return operationType;
	}

	public SysLogStudentOperationVo setOperationType(Integer operationType) {
		this.operationType = operationType;
		return this;
	}
	
	@Override
	public String toString() {
		return "SysLogStudentOperation [" + "id=" + getId() + ", userId=" + userId + ", userName=" + userName + ", operaTime=" + operaTime + ", operation=" + operation + ", cost=" + cost + ", operationType=" + operationType +  "]";
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
}
