package com.yuxin.wx.vo.privilege;


public class TreeNode{
    private String id;
    private Integer pId;
    private String name;
    private String url;
    private String click;
    private String target;
    private Boolean checked;
    private Boolean chkDisabled;
    private String halfCheck;
    private Boolean isParent;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getPId() {
        return pId;
    }
    public void setPId(Integer pId) {
        this.pId = pId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getClick() {
        return click;
    }
    public void setClick(String click) {
        this.click = click;
    }
    public String getTarget() {
        return target;
    }
    public void setTarget(String target) {
        this.target = target;
    }
    public Boolean getChecked() {
        return checked;
    }
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
    public Boolean getChkDisabled() {
        return chkDisabled;
    }
    public void setChkDisabled(Boolean chkDisabled) {
        this.chkDisabled = chkDisabled;
    }
    public String getHalfCheck() {
        return halfCheck;
    }
    public void setHalfCheck(String halfCheck) {
        this.halfCheck = halfCheck;
    }
    public Boolean getIsParent() {
        return isParent;
    }
    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    } 
    

}
