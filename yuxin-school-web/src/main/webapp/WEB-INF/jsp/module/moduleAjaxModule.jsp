<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<ul>
	<c:choose>
		<c:when test="${liveModule != null }">
			<c:forEach items="${liveModule }" var="item">
				<li date-id="${item.id }">
					  <span class="p1" style="width:60%;">${item.name }</span>
			          <span class="p2" style="width:15%;">${wx:dictCode2Name(item.teachMethod)}</span>
			          <span class="p3" style="width:20%;">${item.totalClassHour }课时</span>
				</li>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p class="nomodule">该学科下还没有课程单元，<a href="javascript:;">点我去添加</a></p>
		</c:otherwise>
	</c:choose>
</ul>

<ul class="none">

	<c:choose>
		<c:when test="${faceModule != null }">
			<c:forEach items="${faceModule }" var="item">
				<li date-id="${item.id }">
					  <span class="p1">${item.name }</span>
			          <span class="p2">${wx:dictCode2Name(item.teachMethod)}</span>
			          <span class="p3">${item.totalClassHour }课时</span>
				</li>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<p class="nomodule">该学科下还没有课程单元，<a href="javascript:;">点我去添加</a></p>
		</c:otherwise>
	</c:choose>
</ul>

<!-- 添加模块弹窗 -->
<div class="upload-layer none" id="chooseDiv">
<input type="hidden" id="teachMethod" name="teachMethod" value="TEACH_METHOD_REMOTE"/>
<div class="u-wrap classes">
   	 <div class="mainbackground nopadding" style="height:400px">
        <div class="heading">
            <h2 class="h5">新增<span id="tits">直播</span>课程单元</h2>
            <span class="line"></span>
        </div>
        <div class="c-main">
            <div class="c-content add-classes">
                 <p class='h'>
                     <span class="c-title">学科</span>
                     <span class="c-content">
                     	<span ids="" id="xuekeOne"></span>
<%--                          <input type="text" name="itemOneId" value="${ct.itemOneName }" ids="${ct.itemOneId }" readonly="readonly" readonly/> --%>
                     </span>
                 </p>
                 <p class='h'>
                     <span class="c-title">学科小类</span>
                     <span class="c-content">
                        	<span ids="" id="xuekeTwo"></span>
                     </span>
                 </p>
                  <p class='h'>
                      <span class="c-title">课程单元名称</span>
                      <span class="c-content">
                          <input type="text" id="mouName" value=""/>
                      </span>
                       <span class="note1" style="display: none;color: red;font-size: 12px;margin-left: 124px;">请输入课程单元名称</span>
                  </p>
                  <p class='h'>
                      <span class="c-title">授课方式</span>
                      <span class="c-content" ids="" id="tMs">直播</span>
                  </p>
                  <p class='h'>
                      <span class="c-title">总课时</span>
                      <span class="c-content">
                          <input type="text" id="totalClassHour" value=""/>
                      </span>
                       <span class="note2" style="display: none;color: red;font-size: 12px;margin-left: 124px;">请输入总课时</span>
                  </p>
                  <p class="c">
	                  <span class="c-title">课程单元描述</span>
	                  <span class="c-content" >
	                      <textarea name="moduleDesc"  id="moduleDesc" style="width:435px;height:113px"></textarea>
	                  </span>
             	  </p>
            </div>
        </div>
        
    </div>
</div>
        <div class="m-bo text-center">
            <a href="javascript:void(0);" class="btn btn-big btn-default giveUp">取消</a>
            <a href="javascript:;"  class="btn btn-big btn-primary savemodule">保存</a>
        </div>

</div>
<div class="add-layer-bg none" id="stopDiv"></div>
<form method="post" id="editFroms">
	<input type="hidden" name="itemOneId" id="yjItemId"/>
	<input type="hidden" name="itemSecondId" id="ejItemId"/>
	<input type="hidden" name="name" id="mNames"/>
	<input type="hidden" name="totalClassHour" id="totalHours"/>
	<input type="hidden" name="teachMethod" id="methodTeach"/>
	<input type="hidden" name="moduleDesc" id="moduleMark"/>
</form>
