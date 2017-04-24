<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    <%@include file="/decorators/import.jsp" %>
    
    <div class="add-teacher-room-content">
        <p class="c">
            <span class="sc-c-title">所属校区</span>
            <span class="sc-c-content">
                <select id="campus">
                	<c:forEach var="c" items="${sccList }">
                		<c:if test="${scr.campusId == c.id }">
                			 <option value="${c.id }" selected="selected">${c.campusName }</option>
                		</c:if>
                		<c:if test="${scr.campusId != c.id }">
                			<option value="${c.id }">${c.campusName }</option>
                		</c:if>
                	</c:forEach>
                </select>
            </span>
        </p>
        <p class="c">
            <span class="sc-c-title">教室名称</span>
            <span class="sc-c-content">
            	<c:if test="${scr.roomName != null && scr.roomName != '' }">
            		<input id="roomName" type="text" value="${scr.roomName }" disabled="disabled" >
            	</c:if>
            	<c:if test="${scr.roomName == null }">
            		<input id="roomName" type="text" value="" placeholder="教室名称">
            	</c:if>
            </span>
        </p>
        <p class="c">
            <span class="sc-c-title">租用时段</span>
            <span class="sc-c-content">
                <select id="time">
                	<c:forEach var="c" items="${roomTime }">
                		<c:if test="${c.itemCode == scr.rentScope }">
                			 <option value="${c.itemCode }" selected="selected">${c.itemValue }</option>
                		</c:if>
                		<c:if test="${c.itemCode != scr.rentScope }">
                			<option value="${c.itemCode }">${c.itemValue }</option>
                		</c:if>
                	</c:forEach>
                </select>
            </span>
        </p>
        <p class="c">
            <span class="sc-c-title">属性</span>
            <span class="sc-c-content">
                <select id="attr">
                	<c:forEach var="c" items="${roomAttr }">
                		<c:if test="${c.itemCode == scr.roomAttrCode }">
                			 <option value="${c.itemCode }" selected="selected">${c.itemValue }</option>
                		</c:if>
                		<c:if test="${c.itemCode != scr.roomAttrCode }">
                			<option value="${c.itemCode }">${c.itemValue }</option>
                		</c:if>
                	</c:forEach>
                </select>
            </span>
        </p>
        <p class="c">
            <span class="sc-c-title">类别</span>
            <span class="sc-c-content">
                <select id="type">
                	<c:forEach var="c" items="${roomType }">
                		<c:if test="${c.itemCode == scr.roomTypeCode }">
                			 <option value="${c.itemCode }" selected="selected">${c.itemValue }</option>
                		</c:if>
                		<c:if test="${c.itemCode != scr.roomTypeCode }">
                			<option value="${c.itemCode }">${c.itemValue }</option>
                		</c:if>
                	</c:forEach>
                </select>
            </span>
        </p>
        <p class="c">
            <span class="sc-c-title">容量</span>
            <span class="sc-c-content">
                <select id="kind">
                	<c:forEach var="c" items="${roomKind }">
                		<c:if test="${c.itemCode == scr.seatNumCode }">
                			 <option value="${c.itemCode }" selected="selected">${c.itemValue }</option>
                		</c:if>
                		<c:if test="${c.itemCode != scr.seatNumCode }">
                			<option value="${c.itemCode }">${c.itemValue }</option>
                		</c:if>
                	</c:forEach>
                </select>
            </span>
        </p>
        <p class="c">
            <span class="sc-c-title">教室级别</span>
            <span class="sc-c-content">
                <select id="level">
                	<c:forEach var="c" items="${roomLevel }">
                		<c:if test="${c.itemCode == scr.roomLevelCode }">
                			 <option value="${c.itemCode }" selected="selected">${c.itemValue }</option>
                		</c:if>
                		<c:if test="${c.itemCode != scr.roomLevelCode }">
                			<option value="${c.itemCode }">${c.itemValue }</option>
                		</c:if>
                	</c:forEach>
                </select>
            </span>
        </p>
        <p class="c">
            <span class="sc-c-title">教室状态</span>
            <span class="sc-c-content">
            	<c:if test="${scr.status == null }">
	                <label><input type="radio" name="status" checked="checked" value="1"> 启用</label>
	                <label><input type="radio" name="status" value="0"> 不启用</label>
            	</c:if>
            	<c:if test="${scr.status == 1 }">
	                <label><input type="radio" name="status" checked="checked" value="1" disabled="disabled"> 启用</label>
	                <label><input type="radio" name="status" value="0" disabled="disabled"> 不启用</label>
            	</c:if>
            	<c:if test="${scr.status == 0 }">
	                <label><input type="radio" name="status" value="1"> 启用</label>
	                <label><input type="radio" name="status" checked="checked" value="0"> 不启用</label>
            	</c:if>
            </span>
        </p>
        <p class="f">
            <span class="sc-c-title">地址</span>
            <span class="sc-c-content">
                <input id = "address" type="text" class="full-long" value="${scr.address }">
            </span>
        </p>
        <p class="f">
            <span class="sc-c-title">公交线路</span>
            <span class="sc-c-content">
                <input id="busLine" type="text" value="${scr.busLine }">
            </span>
        </p>
        <p class="f">
            <span class="sc-c-title">备注</span>
            <span class="sc-c-content top">
                <textarea id="remark" class="textarea" >${scr.remark }</textarea>
            </span>
        </p>
        <p class="f">
            <span class="sc-c-title">&nbsp;</span>
            <span class="sc-c-content sc-c-btn">
                <a href="javascript:;" class="btn btn-primary add-room">确定添加</a>
                <a href="javascript:;" class="btn btn-default btn-close">取消</a>
                <input type="hidden" id="roomId" value="${scr.id }"/>
            </span>
        </p>
    </div>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/resource/classroom/dict.js"></script>