package com.yuxin.wx.vo.system;

import com.yuxin.wx.model.system.SysConfigItem;

import java.util.List;

/**
 * Created by yangyanbo on 15/11/18.
 */
public class SysConfigItemVo {
    private SysConfigItem sysOneItem;
    private List<SysConfigItem> sysTwoItem;

    public SysConfigItem getSysOneItem() {
        return sysOneItem;
    }

    public void setSysOneItem(SysConfigItem sysOneItem) {
        this.sysOneItem = sysOneItem;
    }

    public List<SysConfigItem> getSysTwoItem() {
        return sysTwoItem;
    }

    public void setSysTwoItem(List<SysConfigItem> sysTwoItem) {
        this.sysTwoItem = sysTwoItem;
    }
}
