package com.yuxin.wx.model.system;

import com.yuxin.wx.common.BaseEntity;

/**
 * Created by Administrator on 2017/7/31.
 */
@SuppressWarnings("serial")
public class SysConfigItemRelation extends BaseEntity {
    private String itemCode;
    private String parentCode;
    private Integer level;
    private Integer parentId;
    private String levelPath;
    private boolean isParent;
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getLevelPath() {
        return levelPath;
    }

    public void setLevelPath(String levelPath) {
        this.levelPath = levelPath;
    }

    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean parent) {
        isParent = parent;
    }
}
