package com.yuxin.wx.model.company;

import com.yuxin.wx.common.BaseEntity;

/**
 * Created by lym_gxm on 17/11/26.
 */
@SuppressWarnings("serial")
public class NoticeTemplatVo extends BaseEntity {
    //通知编码
    private String noticeCode;
    //通知描述标题
    private String noticeTopic;
    //发布时间
    private String publishTime;
    //通知内容
    private String noticeContent;
    //通知状态
    private String noticeStatus;
    //通知拦截url
    private String noticeUrl;

    public String getNoticeCode() {
        return noticeCode;
    }

    public void setNoticeCode(String noticeCode) {
        this.noticeCode = noticeCode;
    }

    public String getNoticeTopic() {
        return noticeTopic;
    }

    public void setNoticeTopic(String noticeTopic) {
        this.noticeTopic = noticeTopic;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getNoticeStatus() {
        return noticeStatus;
    }

    public void setNoticeStatus(String noticeStatus) {
        this.noticeStatus = noticeStatus;
    }

    public String getNoticeUrl() {
        return noticeUrl;
    }

    public void setNoticeUrl(String noticeUrl) {
        this.noticeUrl = noticeUrl;
    }
}
