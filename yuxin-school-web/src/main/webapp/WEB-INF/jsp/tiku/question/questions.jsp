<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>试题</title>
<script type="text/javascript" src="<%=rootPath %>/javascripts/tiku/question/question.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
<style>
    .iconHide{display: none;}
</style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/tiku/tikuHeader.jsp"></jsp:include>
<%-- 控制  分页 查询 条数 --%>
<input type="hidden" value="${cate.id }" id="tikuId"/>
<input type="hidden" value="5" id="pageSize"/>
<input type="hidden" value="${cate.id }" id="categoryId"/>
<input type="hidden" value="${subId }" id="subId"/>
<input type="hidden" value="${status }" id="status"/>
<input type="hidden" value="${pageNo }" id="pageNO"/>
<c:if test="${types != null }">
	<input type="hidden" value="${types }" id="types"/>
</c:if>
<div class="u-wrap tiku">
    <div class="mainbackground nopadding">
        <div class="classes-type">
            <p class="c title-p clear">
                <span class="t-title fl-left margin-t3">科目</span>
                <span class="t-content fl-left iconSubject">
                	<span class="subList" style="width: auto;">
	                   	<c:forEach var="s" items="${subList }">
	                          <a class="btn btn-mini btn-default subject" href="javascript:;" data-id="${s.id }">${s.subjectName }</a>
	                      <!-- <input type="text" class="iconHide">
	                        <i class="icon iconfont iconHide">&#xe625;</i>
	                        <i class="icon iconfont iconHide">&#xe626;</i> -->
	                   	</c:forEach>
                   	</span>
                   	
	                <span  class="editorbtn "style="width: auto;margin-left: 10px;">
	                 	<i class="icon iconfont icon-edit">&#xe625;</i>
	                    <i class="icon iconfont icon-del">&#xe626;</i>
	                	<a href="javascript:;" class="btn btn-sm btn-success btn-add">添加科目</a>
	                </span>
	                <span  class="editorContent" style="width: auto;display: none;margin-left: 10px;">
	                	<input type="text" maxlength="22" id="subName" style="width: 153px;"/>
	                    <a href="javascript:;" class="btn btn-mini btn-success btn-save" data-id="">保存</a>
	                    <a href="javascript:;" class="btn btn-mini btn-default btn-cancle">取消</a>
	                </span>
	                
                </span>
                
            </p>
            <p class="c clear">
                <span class="t-title">试题状态</span>
                <span class="t-content">
                    <c:if test="${tikuSet.topicAuditYes == 1 && tikuSet.topicAuditNo == 0 }">
                   	<a class="btn btn-mini btn-success questionStatus" href="javascript:;" data-status="topicYes">全部</a>
                    	<a class="btn btn-mini btn-default questionStatus" href="javascript:;" data-status="PAPER_STATUS_EDIT">正在编辑</a>
                    	<a class="btn btn-mini btn-default questionStatus" href="javascript:;" data-status="PAPER_STATUS_WAIT_AUDIT">等待审核</a>
                    	<a class="btn btn-mini btn-default questionStatus" href="javascript:;" data-status="PAPER_STATUS_PUBLISH">发布成功</a>
                    	<a class="btn btn-mini btn-default questionStatus" href="javascript:;" data-status="PAPER_STATUS_AUDIT_FAIL">审核不通过</a>
                    </c:if>
                    <c:if test="${tikuSet.topicAuditYes == 0 && tikuSet.topicAuditNo == 1 }">
                   	<a class="btn btn-mini btn-success questionStatus" href="javascript:;" data-status="topicNo">全部</a>
                    	<a class="btn btn-mini btn-default questionStatus" href="javascript:;" data-status="PAPER_STATUS_EDIT">正在编辑</a>
                    	<a class="btn btn-mini btn-default questionStatus" href="javascript:;" data-status="PAPER_STATUS_PUBLISH">发布成功</a>
                    </c:if>
                </span>
            </p>

            <div class="search">
                <input type="text" class="input-ctrl" placeholder="试题名称" id="topicName"/>
                <input type="button" value="搜索" class="btn btn-sm btn-sel-no"  />
            </div>
        </div>
    </div>
</div>

<div class="u-wrap tiku">
    <div class="mainbackground nopadding">
        <p>
            <a class="btn btn-mini btn-primary types" href="javascript:;" data-type="TOPIC_TYPE_RADIO">单选题</a>
            <a class="btn btn-mini btn-default types" href="javascript:;" data-type="TOPIC_TYPE_MULTIPLE">多选题</a>
            <a class="btn btn-mini btn-default types" href="javascript:;" data-type="TOPIC_TYPE_TRUE_FALSE">判断题</a>
            <a class="btn btn-mini btn-default types" href="javascript:;" data-type="TOPIC_TYPE_ANSWER">简答题</a>
            <a class="btn btn-mini btn-default types" href="javascript:;" data-type="TOPIC_TYPE_UNDEFINED">不定项</a>
            <a class="btn btn-mini btn-default types" href="javascript:;" data-type="TOPIC_TYPE_FILLING">填空题</a>
            <a class="btn btn-mini btn-default types" href="javascript:;" data-type="TOPIC_TYPE_CASE">材料题</a>
        </p>
        <div style="float: right;margin-top:-24px">
            <a href="javascript:;" class="btn btn-mini btn-primary btn-topic" data-btn="create" data-qid="0"><em class="iconfont" style="top:0px;">&#xe606;</em> 添加试题</a>
        </div>
        <div class="table-list">
        </div>
        <div class="pages">
            <ul class="pagination">
	
			</ul>
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<script>
//    科目编辑和删除
   /*  $('.iconSubject').children('a.subject').hover(function(){
        $(this).siblings('.iconHide').show();
    }); */

</script>
</body>
</html>