<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
  <c:forEach items="${sysCyclePicList }" var="cyclePicList" varStatus="status">
                <li id="liStatus${cyclePicList.picSequence }" id1="${cyclePicList.id }" _sort="${cyclePicList.picSequence }">
                    <div class="focus-title">第${status.count }张</div>
                    <div class="focus-content clear" data-provides="fileupload">
                        <div class="left hb" id="imgOne${cyclePicList.id }" ids="" style="background-image:url(${cyclePicUrl }${cyclePicList.picUrl })">
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
		                                     <span style="color:red;font-size:12px;">建议上传图片尺寸为640*330</span>
                                    	</c:when>
                                    	<c:otherwise>
                                    		<span style="color:red;font-size:12px;">建议上传图片尺寸为640*330</span>
                                    	</c:otherwise>
                                    </c:choose>
                                </p>
                                <p>
                                <span class="t">链接地址：</span>
                                <span>
                                	<span style="width: 150px;" class="linNames">${cyclePicList.typeName }</span>
	                                <input type="text" style="display: none;" id="cycleUrl${cyclePicList.id }" class="url"  value="${fn:substring(cyclePicList.clickUrl, 7,fn:length(cyclePicList.clickUrl))}" placeholder="请输入完整的网页地址">
	                                <a href="javascript:;" class="bt btn-ico">...</a>
                                </span>
                                </p>
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
                                <span class='t'></span>
                                <a href="javascript:updateSysCyclePic(${cyclePicList.id });" class="btn btn-gray btn-submit">保存设置</a>
                                <a href="javascript:deleteSysCyclePic('${cyclePicList.id }','${cyclePicList.picSequence }');" class="btn btn-default">删除该轮播</a>
                                </p>
                            </div>
                        </div>
                    </div>
                </li>
          </c:forEach>                		                           