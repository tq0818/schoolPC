package com.yuxin.wx.common;

/**
 * 
 * 分页封装类
 * @author wang.zx
 * @version 2014-11-10
 */
public class BaseModel {

	/**
	 * 分页号
	 */
	private Integer page=0;
	/**
	 * 每页记录数
	 */
	private Integer pageSize=12;
	
	/**
	 * 共有多少条
	 */
	private int totalRecords;
	
	/**
	 * 起始位置
	 */
	public int getFirstIndex() {
		return getStart();
	}


	/**
	 * 共有多少页
	 * @return
	 */
	public int getTotalPages() {
		return (this.totalRecords + this.pageSize - 1) / this.pageSize;
	}
	
	/**
	 * 获取最后一页
	 * @return
	 */
	public int getLastPageNo() {
		return this.getTotalPages();
	}
	
	/**
	 * 获取上一页
	 * @return
	 */
	public int getPreviousPageNo() {
		if (this.page <= 1) {
			return 1;
		}
		return this.page - 1;
	}

	/**
	 * 获取下一页
	 * @return
	 */
	public int getNextPageNo() {
		if (this.page >= this.getLastPageNo()) {
			return this.getLastPageNo();
		}
		return this.page + 1;
	}
	
	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	/**
	 * 获取列表的开始位置
	 * 
	 * @return the start
	 */
	public Integer getStart() {
		if(page == null || page < 1){
			page = 1;
		}
		return (page - 1) * pageSize;
	}
	/**
	 * 获取列表的结束位置
	 * 
	 * @return the limit
	 */
	public Integer getLimit() {
		return pageSize;
	}
}