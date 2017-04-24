<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>添加考试内容</title>
    <%@include file="/decorators/import.jsp" %>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/classes.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/classedit.css"/>

	<script src="<%=rootPath %>/javascripts/tiku/exam/classedit.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
	<script src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/tiku/exam/addExamTwo.js"></script>
</head>
<body>
<%@include file="/WEB-INF/jsp/menu/menu_tiku.jsp"%>
<div class="u-wrap company clear">
        <div class="mainbackground nopadding paddno">
            <div class="heading-box">
                <div class="heading">
                    <h2 class="h5">考试内容</h2>
                    <span class="line"></span>
                </div>
            </div>
            <input type="hidden" value="${examId }" id="examId"/>
            <div class="class-steps clear">
                <div class="class-step active">01 考试信息<div class="blue-horn"></div></div>
                <div class="class-step active">02 考试内容</div>
            </div>
            <div class="select-paper">
                <p class="clear addpaper">
                    <span>选择试卷：</span>
                    <em class="active pop-layer-btn">添加试卷</em>
                </p>
                <c:if test="${!empty paperList }">
		        	<c:forEach var="p" items="${paperList }" varStatus="status">
		        	<c:if test="${status.index == 0 }">
		                <div class="clear">
		                    <span></span>
		                    <div class="exampaper active" data-id="${p.tikuPaperId }" style="cursor: pointer;">
		                        <c:if test="${fn:length(p.paperName) > 24 }">
		                        	<s title="${p.paperName }">${fn:substring(p.paperName,0,24) }...</s>
		                        </c:if>
		                        <c:if test="${fn:length(p.paperName) <= 24 }">
		                        	<s>${p.paperName }</s>
		                        </c:if>
		                        <s>【来源：${p.cateName }->${p.subName }】</s>
		                        <i class="iconfont del-paper showes" data-id="${p.id }">&#xe610;</i>
		                    </div>
		                </div>
		        	</c:if>
		            <c:if test="${status.index > 0 }">
		                <div class="clear">
		                    <span></span>
		                    <div class="exampaper" data-id="${p.tikuPaperId }" style="cursor: pointer;">
		                        <c:if test="${fn:length(p.paperName) > 24 }">
		                        	<s title="${p.paperName }">${fn:substring(p.paperName,0,24) }...</s>
		                        </c:if>
		                        <c:if test="${fn:length(p.paperName) <= 24 }">
		                        	<s>${p.paperName }</s>
		                        </c:if>
		                        <s>【来源：${p.cateName }->${p.subName }】</s>
		                        <i class="iconfont del-paper showes" data-id="${p.id }">&#xe610;</i>
		                    </div>
		                </div>
		        	</c:if>
                	</c:forEach>
                </c:if>
                <div class="clear detail">
                    <span>试卷详情：</span>
                    <table>
                        <tr>
                            <td>题型</td>
                            <td>数量</td>
                            <td>单个分值</td>
                            <td>总分</td>
                        </tr>
                        <tr>
                            <td>单选题</td>
                            <td class="radio_count"></td>
                            <td class="radio_score"></td>
                            <td class="radio_sum"></td>
                        </tr>
                        <tr>
                            <td>多选题</td>
                            <td class="multiple_count"></td>
                            <td class="multiple_score"></td>
                            <td class="multiple_sum"></td>
                        </tr>
                        <tr>
                            <td>不定项</td>
                            <td class="undefined_count"></td>
                            <td class="undefined_score"></td>
                            <td class="undefined_sum"></td>
                        </tr>
                        <tr>
                            <td>判断题</td>
                            <td class="truefalse_count"></td>
                            <td class="truefalse_score"></td>
                            <td class="truefalse_sum"></td>
                        </tr>
                        <tr>
                            <td>填空题</td>
                            <td class="filling_count"></td>
                            <td class="filling_score"></td>
                            <td class="filling_sum"></td>
                        </tr>
                        <tr>
                            <td>简答题</td>
                            <td class="answer_count"></td>
                            <td class="answer_score"></td>
                            <td class="answer_sum"></td>
                        </tr>
                        <tr>
                            <td>材料题</td>
                            <td class="case_count"></td>
                            <td class="case_score"></td>
                            <td class="case_sum"></td>
                        </tr>
                        <tr class="blue-bg">
                            <td>汇总</td>
                            <td class="sum_count"></td>
                            <td class="sum_score"></td>
                            <td class="sum_sum"></td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="exam-save-box clear">
                <div class="btn btn-primary save" data-status="${types }">保存</div>
                <div class="btn btn-primary save" data-status="2">保存并发布考试</div>
                <div class="btn btn-cancel cancel">取消</div>
            </div>
        </div>
</div>
<div class="pop-fixed">
    <div class="select-board">
        <div class="board-title">
            <span>选择试卷</span>
            <em class="iconfont pop-close-btn">&#xe610;</em>
        </div>
        <div class="select-content">
            <div class="selects">
                <select name="xk" id="cate" style="width:196px">
                </select>
                <select name="km" id="sub" style="width:196px">
                </select>
                <input type="text" id="paperName" placeholder="输入试卷名称模糊查询" class="input-name"/>
                <span class="selPaperClick">查询</span>
				<span class="btn-create-paper" style="width:85px">
					<em class="iconfont" style="top:0px;">&#xe606;</em> 创建试卷
				</span>
            </div>
            <div>
                <table class="paper-dt">
                    <tr class="top-tr">
                        <td>试卷名称</td>
                        <td>题量</td>
                        <td>分数</td>
                        <td>创建时间</td>
                        <td>创建人</td>
                        <td></td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="pages page-btn-box">
			<ul class="pagination">
				
			</ul>
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
        <p><i></i>加载中,请稍后...</p>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none;opacity:125;"></div>
<!--  ajax加载中div结束 -->
</body>
</html>