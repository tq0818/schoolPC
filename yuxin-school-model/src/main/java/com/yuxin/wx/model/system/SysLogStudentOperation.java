package com.yuxin.wx.model.system;

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
public class SysLogStudentOperation extends BaseEntity {
	
	
	private Integer	userId;		 /* 用户ID */ 
	private String	userName;		 /* 用户姓名 */ 
	private Date	operaTime;		 /* 操作时间 */ 
	private String	operation;		 /* 操作 */ 
	private Double	cost;		 /* 花费 */ 
	private Integer	operationType;		 /* 0:购买,1:上直播,2:公开课,3:上录播,4:提问,5:回答,6:评论,7:做题,8:登录,9:注册 */ 
	private Integer companyId;
	private Integer sourceId;

	// Constructor
	public SysLogStudentOperation() {
	}
	
	/**
	 * full Constructor
	 */
	public SysLogStudentOperation(Integer id, Integer userId, String userName
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
	
	public Integer getUserId() {
		return userId;
	}

	public SysLogStudentOperation setUserId(Integer userId) {
		this.userId = userId;
		return this;
	}
	
	
	public String getUserName() {
		return userName;
	}

	public SysLogStudentOperation setUserName(String userName) {
		this.userName = userName;
		return this;
	}
	
	@JsonSerialize(using = ShortDateSerializer.class)
	public Date getOperaTime() {
		return operaTime;
	}

	public SysLogStudentOperation setOperaTime(Date operaTime) {
		this.operaTime = operaTime;
		return this;
	}
	
	
	public String getOperation() {
		return operation;
	}

	public SysLogStudentOperation setOperation(String operation) {
		this.operation = operation;
		return this;
	}
	
	
	public Double getCost() {
		return cost;
	}

	public SysLogStudentOperation setCost(Double cost) {
		this.cost = cost;
		return this;
	}
	
	
	public Integer getOperationType() {
		return operationType;
	}

	public SysLogStudentOperation setOperationType(Integer operationType) {
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

	public Integer getSourceId() {
		return sourceId;
	}

	public void setSourceId(Integer sourceId) {
		this.sourceId = sourceId;
	}
}
