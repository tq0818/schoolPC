<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<c:if test="${topicType != 'TOPIC_TYPE_ANSWER' }">
<p class="c">
	<span class="c-title">每题得分</span>
	<span class="c-content">
		<c:if test="${score == null }">
			<input type="text" value="0" id="score" size="4" maxlength="4"/>
		</c:if>
		<c:if test="${score != null }">
			<input type="text" value="${score }" id="score" size="4" maxlength="4" disabled="disabled"/>
		</c:if>
	</span>
	<c:if test="${score == null }">
		<a href="javascript:;" class="btn btn-mini btn-primary btn-score">保存</a>
		<a href="javascript:;" class="btn btn-mini btn-primary btn-score-edit" style="display: none;">编辑</a>
	</c:if>
	<c:if test="${score != null }">
		<a href="javascript:;" class="btn btn-mini btn-primary btn-score" style="display: none;">保存</a>
		<a href="javascript:;" class="btn btn-mini btn-primary btn-score-edit">编辑</a>
	</c:if>
</p><br>
</c:if>
<input type="hidden" value="${topicType }" id="ttyp"/>
<ul>
	<c:forEach var="t" items="${topic }">
    <li>
        <table class="table table-border-title topic-iconfont">
        	<tbody>
	            <tr> 
	                <td width="3%">
		    			<span class="status" style="float:right; margin-top:2px;"></span>
	                </td>
	                <td width="87%" class="noimg">
	                    	<%-- <c:if test="${fn:length(t.topicName) > 45}">
	                    		${fn:substring(t.topicName,0,45)}......
	                    	</c:if>
	                    	<c:if test="${fn:length(t.topicName) <= 45}">
	                    		${t.topicName }
	                    	</c:if> --%>
	                    	${t.topicName }
	                	<%-- <i class="iconfont btn-iconfont" data-btn="audite" data-id="${t.id }">&#xe628;</i> --%>
	                </td>
	                <td>
	                	<i class="iconfont btn-iconfont" data-btn="edit" data-id="${t.id }">&#xe625;</i>
	                	<i class="iconfont btn-iconfont" data-btn="del" data-id="${t.id }">&#xe626;</i>
	                </td>
	            </tr>
            </tbody>
        </table>
        <div  class="questionedit"></div>
    </li>
   	</c:forEach>
</ul>
<div id="questionEdit" class="questionedit">

</div>
<c:if test="${score == null && topicType != 'TOPIC_TYPE_ANSWER' && topicType != 'TOPIC_TYPE_CASE' }">
	<a href="javascript:;" class="btn btn-mini btn-success btn-topic" data-qid="0" style="display: none;">新增试题</a>
	<a href="javascript:;" class="btn btn-mini btn-success btn-exist" style="display: none;">新增已有试题</a>
</c:if>
<c:if test="${score != null || topicType == 'TOPIC_TYPE_ANSWER' || topicType == 'TOPIC_TYPE_CASE' }">
	<a href="javascript:;" class="btn btn-mini btn-success btn-topic" data-qid="0">新增试题</a>
	<a href="javascript:;" class="btn btn-mini btn-success btn-exist">新增已有试题</a>
</c:if>

<div class="topicDetail">
	<p>
		<span class="loadDetail">
		
		</span>
	</p>
	<p style="width:100%;margin-top:50px" align="center">
		<a href="javascript:;" class="btn btn-primary btn-closeDetail">返回</a>
	</p>
</div>
<div class="add-classes">
</div>
<div class="add-classes-bg"></div>
<style>
	.btn-iconfont{display:none;float:right;margin-left: 10px;margin-right: 10px;cursor:pointer;}
	.add-classes{display:none;position:fixed;top:50%;left:50%;width:760px;height:545px;margin-left:-400px;margin-top:-280px;padding:10px 20px;background-color:#fafafa;z-index:99}
	.add-classes .close{position:absolute;top:5px;right:0;font-size:16px;font-size:1.6rem;cursor:pointer}
	.add-classes-bg{display:none;position:fixed;top:0;right:0;bottom:0;left:0;background-color:rgba(0,0,0,0.4);z-index:98}
	.topicDetail{display:none;position:fixed;top:50%;left:50%;width:760px;margin-left:-400px;margin-top:-280px;padding:10px 20px;background-color:#fafafa;z-index:1000}
</style>
<script type="text/javascript" src="<%=rootPath%>/javascripts/tiku/paper/paperTopic.js"></script>
<script type="text/javascript">
$(function(){
	$(".noimg").find("img").css({"width":"40px","height":"40px"});
});
</script>