<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<link  type="text/css" href="<%=request.getContextPath() %>/stylesheets/student.css"/>
<div class="mark-more">
            <div class="main-content">
                <div class="m-title">
                    <h2 class="h6">课程</h2>
                </div>
                <ul class="list-infos clear">
                    <li>
                        <p class='c'>
                            <span class="c-title">学科</span>
                            <span class="c-content">${oneItem.itemName }</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">学科小类</span>
                            <span class="c-content">${secondItem.itemName }</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">学习课程</span>
                            <span class="c-content">${classType.name }</span>
                        </p>
                    </li>
                    <li class="">
                        <p class='c'>
                            <span class="c-title">总 课 时</span>
                            <span class="c-content">${payMaster.classTypeHour }</span>
                        </p>
                    </li>
                    <li class="none no">
                        <p class='c'>
                            <span class="c-title">运营分校</span>
                            <span class="c-content">
                            <c:forEach items="${schoolList }" var="school" varStatus="v">
                            		&nbsp;${school.schoolName }
                            </c:forEach>
                            </span>
                        </p>
                    </li>
                </ul>
            </div>
            <div class="main-content">
                <div class="m-title">
                    <h2 class="h6">课程</h2>
                </div>
                <ul class="list-infos clear">
                    <li>
                        <p class='c'>
                            <span class="c-title">优先上课校区</span>
                            <span class="c-content">${schoo.schoolName }</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">学员计划考期</span>
                            <span class="c-content" id="pExamTermName">${payMaster.examTermName }</span>
                        </p>
                    </li>
                </ul>
                <div class="c-list">
                	<c:forEach items="${paySlaveList }" var="paySlave">
                		<c:if test="${paySlave.resourceType=='TEACH_METHOD_FACE'||paySlave.resourceType=='TEACH_METHOD_LIVE' }">
                			  <p class="public clear">
			                        <span class="left clear">
			                            <span class="c-title">课程单元</span>
			                            <span class="c-content">${paySlave.name }</span>
			                            <span class="c-title">课程单元类型</span>
			                            <c:if test="${paySlave.resourceType=='TEACH_METHOD_FACE'}">
				                            <span class="c-content">面授</span>
			                            </c:if>
			                            <c:if test="${paySlave.resourceType=='TEACH_METHOD_LIVE'}">
				                            <span class="c-content">直播</span>
			                            </c:if>
			                        </span>
			                        <span class="right clear">
			                            <span class="c-title">班号</span>
			                            <span class="c-content"><b>${paySlave.moduleNo }</b></span>
			                        </span>
			                   </p>
                		</c:if>
                		<c:if test="${paySlave.resourceType=='TEACH_METHOD_VIDEO'}">
                 		    <p class="long">
		                        <span class="c-title">课程单元</span>
		                        <span class="c-content">${paySlave.name }</span>
		                        <span class="c-title">课程单元类型</span>
		                        <span class="c-content">录播</span>
		                        <span class="c-title">总课时</span>
		                        <span class="c-content">${paySlave.totalClassHour }课时</span>
		                    </p>
                		</c:if>
                	</c:forEach>
                </div>
            </div>
        </div>
