<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <c:forEach items="${sysCyclePicList }" var="cyclePicList" varStatus="status">
                <li id="liStatus${cyclePicList.picSequence }" id1="${cyclePicList.id }" _sort="${cyclePicList.picSequence }">
                    <div class="focus-title">
                    	第${status.count }张
                    	<%-- <c:if test="${status.count == 1}">
                    		<span style="color:red;font-size:1.2rem;margin-left:10px;">由于不同分辨率下轮播图课显示有效区域不同，此处建议将图片内主要内容设计在中间1200像素内。</span>
                    	</c:if> --%>
                    </div>
                    <div class="focus-content clear" data-provides="fileupload">
                        <div class="left hb" id="imgOne${cyclePicList.id }" ids="" style="background-image:url(${cyclePicUrl }${cyclePicList.picUrl })">
<%-- 							<c:choose> --%>
<%-- 								<c:when test="${empty cyclePicList.picUrl}"> --%>
<%-- 									<img id="imgOne${cyclePicList.id }" ids="" src="" alt="" /> --%>
<%-- 								</c:when> --%>
<%-- 								<c:otherwise> --%>
<%-- 									 <img id="imgOne${cyclePicList.id }" ids="" src="${cyclePicUrl }${cyclePicList.picUrl }" > --%>
<%-- 								</c:otherwise> --%>
<%-- 							</c:choose> --%>
                        </div>
                        <div class="right">
                            <div class="title">配置信息</div>
                            <c:if test="${status.first }">
	                            <div class="direction">
	                                <i class="iconfont up" onclick="changeStatus(${cyclePicList.id},${cyclePicList.picSequence })" title="向下移动">&#xe607;</i>
	                            </div>
	                        </c:if>
	                         <c:if test="${status.first==false && status.last==false }">
	                            <div class="direction">
	                                <i class="iconfont up" onclick="changeStatus(${cyclePicList.id},${cyclePicList.picSequence })" title="向下移动">&#xe607;</i>
	                                <i class="iconfont down" onclick="upStatus(${cyclePicList.id},${cyclePicList.picSequence })" title="向上移动" >&#xe608;</i>
	                            </div>
	                        </c:if>
	                         <c:if test="${status.last }">
	                            <div class="direction">
	                                <i class="iconfont down" onclick="upStatus(${cyclePicList.id},${cyclePicList.picSequence })" title="向上移动" >&#xe608;</i>
	                            </div>
	                        </c:if>
                            <div class="set-infos is-save${cyclePicList.id }">
                                <p>
                                    <span class="t">上传图片：</span>
                                     <span class="btns">
                                     <input type="file" class="u-f" id="imgData${cyclePicList.id }" name="imgData" onchange="updateCyclePic(${cyclePicList.id },this);" accept="image/*">
                                      <a href="javascript:;" class="btn btn-default btn-mini c-uf">更新图片</a>
                                    </span>
                                    <c:choose>
                                    	<c:when test="${picType == 'openClass' }">
		                                     <span style="color:red;font-size:12px;">建议上传图片尺寸为1600*350</span>
                                    	</c:when>
                                    	<c:when test="${picType == 'wap' }">
		                                     <span style="color:red;font-size:12px;">建议上传图片尺寸为750*375</span>
                                    	</c:when>
                                    	<c:otherwise>
                                    		<span style="color:red;font-size:12px;" class="img-tip">建议上传图片尺寸为1600*535</span>
                                    	</c:otherwise>
                                    </c:choose>
                                </p>
                                 <c:choose>
                                	<c:when test="${picType=='wap' }">
                                <p><span class="t">链接地址：</span><label class="inputPosi"><span>http://</span><input type="text"  id="cycleUrl${cyclePicList.id }" value="${fn:substring(cyclePicList.clickUrl, 7,fn:length(cyclePicList.clickUrl))}" class="url" ></label></p>
                                	</c:when>
                                	<c:otherwise>
                                <p><span class="t">链接地址：</span><span>http://</span><input type="text" style="width: 185px;" id="cycleUrl${cyclePicList.id }" value="${fn:substring(cyclePicList.clickUrl, 7,fn:length(cyclePicList.clickUrl))}" class="url" ></p>
                                	</c:otherwise>
                                </c:choose>
                                <p><span class="t">是否启用：</span>
                                <c:if test="${cyclePicList.validFlag==1 }">
	                                <i class="iconfont normal open">&#xe603;</i>
	                                <span id="addStatus${cyclePicList.id }" class="i open">已启用</span>
                                </c:if>
                                 <c:if test="${cyclePicList.validFlag!=1 }">
                                 	<i class="iconfont normal close">&#xe604;</i>
                                	<span id="addStatus${cyclePicList.id }" class="i close">已禁用</span>
                                </c:if>
                                </p>
                                <p>
                                
                                <c:choose>
                                	<c:when test="${picType=='wap' }">
                                		<span class="actionBut">
	                                	<a href="javascript:deleteSysCyclePic('${cyclePicList.id }','${cyclePicList.picSequence }');" class="btn btn-default ">删除该轮播</a>
	                                	<a href="javascript:updateSysCyclePic(${cyclePicList.id });" class="btn btn-gray btn-submit btn-prevent">保存设置</a>
	                                	</span>
                                	</c:when>
                                	<c:otherwise>
                                		<span class='t'></span>
                                		<a href="javascript:updateSysCyclePic(${cyclePicList.id });" class="btn btn-gray btn-submit">保存设置</a>
                             		    <a href="javascript:deleteSysCyclePic('${cyclePicList.id }','${cyclePicList.picSequence }');" class="btn btn-default">删除该轮播</a>
                                	</c:otherwise>
                                </c:choose>
                                
                                </p>
                            </div>
                        </div>
                    </div>
                </li>
          </c:forEach>       
          <style>
         
          </style>         		                           