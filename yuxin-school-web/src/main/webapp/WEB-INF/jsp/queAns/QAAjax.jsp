<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%String rootPath = request.getContextPath(); %>
<style>
.onean{
	width: 98%;
}
.onean.t-t{
	margin-top: 5px;
	border: solid 1px #eee;
	border-top: none;
}
.onean .title-t{
	width:100px;
	text-align: center;
}
.onean .title-t .title-hip{
	width:100%;
	height: 60px;
}
.onean .title-t .title-name{
	width:100px;
	position:relative;
	font-size:12px;
}
.onean .content{
	padding-left:10px;
	word-wrap: break-word; 
	word-break: break all;
}
.onean .content-c .c-top{
	font-size:12px;
	min-height: 47px;
}
.onean .content-c .c-bottom{
	color:#999999;
	padding-top:10px;
	font-size:12px;
}
.xzi{
	margin-left:5px;
}
.twoans .c{
	padding-left:10px;
	padding-top:10px;
}
.h-text td{
	padding-top:20px;
}
.h-text .t{
	width:80%;
}
.h-text .t p{
	padding-top:10px;
}
.twoans{
	margin-top:10px;
}
.icons{
	cursor: pointer;;
}	
</style>
<script type="text/javascript" src="../javascripts/queAns/QAAjax.js"></script>
<c:forEach var="a" items="${anPage.data }">
<div class="oneanswer" data-id="${a.id }" data-queid="${a.questionId }">
<table class="onean t-t" cellpadding="0px" cellspacing="0px">
	<col width="100px">
	<col width="100%">
	<tr>
		<td class="title-t" valign="top">
			<div class="title-hip">
				<c:if test="${!empty a.imgurl }">
					<img class="yuanPic" src="${a.imgurl}"/>
				</c:if>
				<c:if test="${empty a.imgurl }">
					<img class="yuanPic" src="<%=rootPath %>/images/user/iconfont-danxiantouxiangline.png"/>
				</c:if>
			</div>
			<div class="title-name">
				${a.name }
			</div>
		</td>
		<td class="content-c" valign="top">
			<div class="c-top">
				${a.answerDesc }
			</div>
			<div class="c-bottom">
				<span>回复时间: ${a.times }</span>
				<span class="anss" style="margin-left:10px;color: #0099ff;cursor: pointer;" data-id="${a.id }"><span class="xzi">回答:(<span class="count">${a.commentCount }</span>)</span></span>
				<span class="ore" style="margin-left:30px;cursor: pointer;"><i class="iconfont">&#xe64f;</i><span class="xzi">回复</span></span>
				<c:if test="${types == 3 }">
					<span class="del" style="margin-left:10px;cursor: pointer;" data-id="${a.id }" data-types="1"><i class="iconfont">&#xe626;</i><span class="xzi">删除</span></span>
				</c:if>
			</div>
		</td>
	</tr>
	<tr class="twoans">
		<td></td>
		<td class="c">
			<div class="two-content" style="display:none">
				
			</div>
		</td>
	</tr>
	<tr class="h-text">
		<td></td>
		<td class="t">
			<div class="onetext" style="display: none;">
			<textarea rows="5" style="width: 95%;" placeholder="回复  @${a.name } " data-name="${a.name }" data-types="${a.answerType }"></textarea>
			<p style="float: right;margin-right: 3%;">
				<a class="btn btn-mini btn-primary btn-onere" style="cursor: pointer;" data-id="${a.id }" data-uid="${a.userId }">回复</a>
				<a class="btn btn-mini btn-default btn-oneca" style="cursor: pointer;">取消</a>
			</p>
			</div>
		</td>
	</tr>
</table>
</div>
</c:forEach>
<div class="pages">
	<div class="paginations"></div>
</div>
<script type="text/javascript">
$(function(){
	$(".paginations").html("");
	$(".paginations").pagination('${anPage.rowCount}', {
		next_text : "下一页",
		prev_text : "上一页",
		current_page : '${anPage.pageNo-1}',
		link_to : "javascript:void(0)",
		num_display_entries : 8,
		items_per_page : '${anPage.pageSize}',
		num_edge_entries : 1,
		callback : function(page, jq) {
			var pageNo = page + 1;
			selOneAns(pageNo);
		}
	});
});
</script>
<input type="hidden" value="${types }" id="types"/>