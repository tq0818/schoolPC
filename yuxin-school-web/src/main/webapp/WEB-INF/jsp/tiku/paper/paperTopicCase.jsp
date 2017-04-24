<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<style>
	.borderDiv{border:2px solid #6DEAD7; padding:15px}
</style>
<input type="hidden" value="TOPIC_TYPE_CASE" id="topicTypeCase"/>
<ul>
   	<c:forEach var="t" items="${topic }">
    <li>
        <table class="table table-border-title files-list topic-iconfont">
			<tr>
                <td width="40%">
                    <span>编辑者<em><c:if test="${t.creatorName != null }">${t.creatorName }</c:if></em></span>
                </td>
                <td width="40%">
                    <span>审核者<em><c:if test="${t.updatorName != null }">${t.updatorName }</c:if></em></span>
                </td>
                <td class="text-right">
                   <%--  <c:if test="${t.status == 'PAPER_STATUS_EDIT' }"><span class="text-right">正在编辑</span></c:if>
	    			<c:if test="${t.status == 'PAPER_STATUS_WAIT_AUDIT' }"><span class="text-right info-color">等待审核</span></c:if>
	    			<c:if test="${t.status == 'PAPER_STATUS_PUBLISH' }"><span class="text-right success-color">发布成功</span></c:if>
	    			<c:if test="${t.status == 'PAPER_STATUS_AUDIT_FAIL' }"><span class="text-right danger-colo">审核不通过</span></c:if> --%>
                </td>
            </tr>
            <tr>
                <td colspan="3">${t.topicName }</td>
            </tr>
            <tr>
                <td colspan="3" class="text-right">
                    <a href="javascript:;" class="btn btn-mini btn-default editPaperCase" data-btn="edit" data-id="${t.id }" data-paperid=${paperId }>编辑材料</a>
                    <a href="javascript:;" class="btn btn-mini btn-default editPaperCaseChild" data-btn="edit" data-id="${t.id }" data-paperid=${paperId }>编辑子题</a>
                    <a href="javascript:;" onclick="delPaperCaseTopic(this)" class="btn btn-mini btn-default btn-iconfont" data-btn="del" data-id="${t.id }" data-paperid=${paperId }>删除</a>
                </td>
            </tr>
        </table>
        <!-- 编辑试卷材料题存放的为位置 -->
        <div class="questionedit"></div>
        <!-- 编辑试卷材料题的子题存放的位置 -->
	    <div class="paperCaseTopic none" >
	        <div class="borderDiv">
	        	<div class="qution-control" style="margin-bottom:15px;">
			        <p>
			            <a href="javascript:;" class="btn btn-mini btn-success addChildQuestion" data-qid="${t.id }" data-paperid=${paperId }>新增材料子题</a>
			        </p>
			    </div>
			    <!-- 存放试题列表 -->
		   		<div class="qustion-list">
		   			<!-- 已经存在的试题列表 -->
		   			<div class="existQuestion" data-parentId="">
		   			</div>
		   			<!-- 新增试题 -->
		   			<div class="addQuestionEdit">
		   			
		   			</div>
		   		</div>
	        </div>
        </div>
    </li>
   	</c:forEach>
</ul>	
<div id="questionEdit" class="questionedit">

</div>
<a href="javascript:;" class="btn btn-mini btn-success btn-topic" data-qid="0">新增材料</a>
<a href="javascript:;" class="btn btn-mini btn-success btn-exist">新增已有材料</a>

<div class="topicDetail">
	<p>
		<span class="loadDetail">
		
		</span>
	</p>
	<p style="width:100%;margin-top:50px" align="center">
		<a href="javascript:;" class="btn btn-primary btn-closeDetail">返回</a>
	</p>
</div>

<input type="hidden" value="${topicType }" id="ttyp"/>
<div class="add-classes ">
</div>
<div class="add-classes-bg "></div>
<style>
	.add-classes{display:none;position:fixed;top:50%;left:50%;width:760px;height:535px;margin-left:-400px;margin-top:-280px;padding:10px 20px;background-color:#fafafa;z-index:99}
	.add-classes .close{position:absolute;top:5px;right:0;font-size:16px;font-size:1.6rem;cursor:pointer}
	.add-classes-bg{display:none;position:fixed;top:0;right:0;bottom:0;left:0;background-color:rgba(0,0,0,0.4);z-index:98}
	.topicDetail{display:none;position:fixed;top:50%;left:50%;width:760px;margin-left:-400px;margin-top:-280px;padding:10px 20px;background-color:#fafafa;z-index:1000}
</style>
<script type="text/javascript" src="<%=rootPath%>/javascripts/tiku/paper/paperTopic.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/tiku/question/editCase.js"></script>