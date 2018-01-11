<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/decorators/import.jsp" %>
    <p class="kg">
         <span class="sc-c-title">题库名称</span>
         <span class="sc-c-content">
			 <input id="tikuName" type="text" value="${tiku.tikuName}" maxlength="50" style="width: 176px;"/>
			 <span style="color: red;">最多50字符</span>
        </span>
    </p>
    <p class="kg">
        <span class="sc-c-title">包含科目</span>
        <span class="sc-c-content">
	<ul>
		<ul id="subInfo" style="margin-left: 20%; margin-top: -9%;">
			<c:forEach items="${subjects}" var="sub">
				<li style="height: 16px; padding: 5px 10px; font-size: 14px;">
					${sub.subjectName}</li>
			</c:forEach>
		</ul>
		<li class="addCBtn" style="margin-left: 20%">
		<a href="javascript:;" class="btn btn-sm btn-success" style="margin-left: -14px;">添加科目</a></li>
		<li class="addCConter none" style="margin-left: 20%">
			<input type="text" id="addSubName" style="width: 178px;margin-left: -15px;" maxlength="20" placeholder="最多20个字符"/>
			<a href="javascript:;" class="btn btn-sm btn-success">保存</a>
			<a href="javascript:;" class="btn btn-sm btn-default">取消</a>
		</li>
	</ul>
</span>
     </p>
        <p class="kg" style="margin-top: 12px;">
            <span class="sc-c-title">绑定学科</span>
            <span class="sc-c-content">
				<span style="width: 70px;">学科</span>
	            <select id="itemOneId" style="width: 120px;" onchange="javascript:Forms.queryItemSecond()">
	                <c:forEach items="${itemOneList}" var="itemOne">
	                    <c:if test="${itemOne.id == tiku.itemOneId}">
	                        <option value="${itemOne.id}" selected="selected">${itemOne.itemName}</option>
	                    </c:if>
	                    <c:if test="${itemOne.id != tiku.itemOneId}">
	                        <option value="${itemOne.id}">${itemOne.itemName}</option>
	                    </c:if>
	                </c:forEach>
	            </select>
            </span>
            <br>
            <span class="sc-c-content" style="position: relative; left: 84px;">
			<span style="width: 70px;display: none">学科小类</span>
            <select id="itemSecondId" style="width: 120px;display: none">
            </select>
            </span>
        </p>
        <p class="kg">
            <span class="sc-c-title">题库描述</span>
            <span class="sc-c-content">
				<textarea rows="3" cols="40" id="tikuDesc" maxlength="160">${tiku.tikuDesc}</textarea>
			</span>
        </p>
        <p class="kg">
            <span class="sc-c-title">学科图标</span>
            <span class="sc-c-content itemiconone">
			<c:forEach var="i" items="${iconList }" varStatus="status">
			<c:if test="${status.index <= 3 }">
			<span class="item-icons" style="width:60px;height:30px;">
				<img src="http://${ImagePath }/${i.iconBackUrl }" width='20px' picId="${i.id}" title="${i.iconName }" style="padding:5px;">
			</span>
            </c:if>
            </c:forEach>
            </span>
            <span style="width:60px;height:60px;" id="jia">
				<img src="<%=rootPath%>/images/jia.jpg" width='30px' style="padding:10px;">
			</span>
            <a href="javascript:;" class="btn-nexticon"><span style="font-size:14px;">换一批</span></a>
            <input type="hidden" value="${nowpage }" id="iconpage" />
            <input type="hidden" value="${pagecount }" id="pagecount" />
            <input type="hidden" id="imgurl" value="${ImagePath }" />
            <input type="hidden" id="picUrl" value="${tiku.iconUrl }" />
            <input type="hidden" id="picId" value="" />
        </p>
        <p>
            <span class="sc-c-title"></span>
            <span class="sc-c-content itemiconone">
		    <c:forEach var="i" items="${iconList }" varStatus="status">
			<c:if test="${status.index > 3 }">
			<span class="item-icons" style="width:60px;height:30px;">
				<img src="http://${ImagePath }/${i.iconBackUrl }" width='20px' picId="${i.id}" title="${i.iconName }" style="padding:5px;">
			</span>
            </c:if>
            </c:forEach>
            </span>
        </p>

        <div class="c text-center" style="margin-left: -16%; margin-top: 6%;">
            <a id="saveTikuBtn" href="javascript:;" class="btn btn-sm btn-default btn-primary">保存</a>
            <a href="javascript:;" class="btn btn-sm btn-default btn-cancel">取消</a>
        </div>
        <input type="hidden" id="twoSecItemId" value="${tiku.itemSecondId}" />
        <input id="tikuNameH" type="hidden" value="${tiku.tikuName}" />