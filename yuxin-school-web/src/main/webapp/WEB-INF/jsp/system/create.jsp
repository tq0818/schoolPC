<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
<script type="text/javascript" src="<%=rootPath %>/javascripts/company/jquery.cityselect.js"></script>
<div class="screen-right-cont">
    <div class="screen-right-title">
        <h3> <i> 添加分校</i></h3>
    </div>
    <div class="create-school">
        <div class="school-block clear">
            <div class="t-label fl">
                分校名称
            </div>
            <div class="fl">
                <input type="text" value="${school.schoolName }" class="school-branch" placeholder="" id="schoolname"/>
            </div>
        </div>
        <%-- <div class="school-block clear">
            <div class="t-label fl">
                分校网址
            </div>
            <div class="fl">
                <input type="text" value="${school.suffix }" 
                class="school-branch" placeholder="" id="schoolsuffix" 
                maxlength="20" onkeyup="javascript:suffixChange();"/>
            </div>
        </div>
        <div class="school-block clear">
            <div class="t-label fl">
            &nbsp;
            </div>
            <div class="fl">
            	<span style="color:#aaa;">TIP:当前分校域名   http://${domain }/<span class="suffix">${!empty school.suffix?'schools/':''}${school.suffix }</span></span>
            </div>
        </div> --%>
        <c:if test="${!empty cfs && cfs.status == 1 }">
        <div class="school-block clear">
            <div class="t-label fl">
                所在地址
            </div>
            <div class="fl choose-address" id="pcdAddress">
                <select id="prov"></select>
                <select id="city"></select>
                <select id="dist"></select>
            </div>
        </div>
        <%-- <div class="school-block clear">
            <div class="t-label fl">
                分校域名
            </div>
            <div class="fl">
                <input type="text" value="${school.indexDomain }" class="school-branch" placeholder="" id="domain"/>
            </div>
        </div> --%>
        <%-- <div class="school-block clear">
            <div class="t-label fl">
                分校类型
            </div>
            <div class="fl">
                <select id="category">
                	<option value="0">学校</option>
                	<option value="1">机构</option>
                </select>
            </div>
        </div> --%>
        <div class="school-block clear">
            <div class="t-label fl">
                学校封面
            </div>
            <div class="fl">
                <div class="school-img">
                	<c:if test="${!empty school.overview }">
                    	<img src="http://${imgpath }/${school.overview }" width="100%" height="100%" id="commdotityPic"/>
                	</c:if>
                	<c:if test="${empty school.overview }">
                    	<img src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image" width="100%" height="100%" id="commdotityPic"/>
                	</c:if>
                </div>
                <div class="change-img">
                	<a href="javascript:;"class="btn btn-sm btn-default">更换封面</a>
                    <span class="manage-button-mask"></span>
                </div>

            </div>
        </div>
        </c:if>
        <div class="school-block clear">
            <div class="t-label fl">
                学校简介
            </div>
            <div class="fl">
                <textarea id="schoolDesc" maxlength="100" cols="30" rows="10" class="school-msg">${school.schoolDesc }</textarea>
            </div>
        </div>
        <div class="bnts-wrap">
            <button class="manage-btn manage-btn-cancel manage-button">取消 <span class="manage-button-mask"></span></button>
            <button class="manage-btn manage-btn-success mr20 manage-button">保存 <span class="manage-button-mask"></span></button>
        </div>
    </div>
</div>
<input type="hidden" value="${province }" id="province"/>
<input type="hidden" value="${city }" id="citys"/>
<input type="hidden" value="${address }" id="county"/>
<input type="hidden" value="${imgpath }" id="imgpath"/>
<%-- <input type="hidden" value="${!empty school.category ? school.category : 0 }" id="cate"/> --%>
<input type="hidden" value="${school.id }" id="schoolId"/>
<script src="<%=rootPath%>/javascripts/company/create.js"></script>