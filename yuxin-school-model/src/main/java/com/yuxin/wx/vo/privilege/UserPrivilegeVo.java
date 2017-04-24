package com.yuxin.wx.vo.privilege;

import com.yuxin.wx.common.BaseEntity;

public class UserPrivilegeVo extends BaseEntity{
    
    
    private Integer id;
    private String name;
    private Integer parentId;
    private Integer companyId;
    private Integer description;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getParentId() {
        return parentId;
    }
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    public Integer getCompanyId() {
        return companyId;
    }
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
    public Integer getDescription() {
        return description;
    }
    public void setDescription(Integer description) {
        this.description = description;
    }
    
    

}
