<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<p class="c d">说明：公司所有的课程有效期和观看次数按照统一的规则进行设置</p>
<p class="c">
	<span class="c-title w112">课程有效期:&nbsp;</span> 
	<span class="c-content">
		<input type="text" class="w25 com" style="height: 11px;" value="${comDay}" id="comDay" />&nbsp;天
	</span>
</p>
<p class="c">
	<span class="c-title w112">单视频观看次数:&nbsp;</span> 
	<span class="c-content">
		<input type="text" class="w25 com" style="height: 11px;" value="${comVideo}" id="comVideo" />&nbsp;次
	</span>
</p>
<p class="c">
	<span class="c-title w112">单直播回看次数:&nbsp;</span> 
	<span class="c-content">
		<input type="text" class="w25 com" style="height: 11px;" value="${comLive}" id="comLive" />&nbsp;次
	</span>
</p>
<p class="c">
	<a href="javascript:;" class="btn btn-mini btn-success comSave">保存</a>
</p>