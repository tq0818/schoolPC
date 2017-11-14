package com.yuxin.wx.model.watchInfo;

import java.util.List;

public class WatchInfoFromZSResult {
    private String code;
    private String message;
    private List<WatchInfoFromZSGet> list;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<WatchInfoFromZSGet> getList() {
        return list;
    }

    public void setList(List<WatchInfoFromZSGet> list) {
        this.list = list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
