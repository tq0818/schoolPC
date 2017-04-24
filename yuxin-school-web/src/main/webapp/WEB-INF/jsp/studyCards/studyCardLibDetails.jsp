<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<%@include file="/decorators/import.jsp"%>
	<title>学习卡</title>
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/fatstyle.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/manage.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/splitscreen.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/studyCard/stuCard.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/resource.css">
	<link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/css/utils.css">
	<style type="text/css">
		.searchCon input[type="text"] {
   			 height: 14px;
   		}
	</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
	<div class="u-wrap company overflow">
        <jsp:include page="/WEB-INF/jsp/menu/menu_studycard.jsp"></jsp:include>
        <div class="right-side proxyManage">
            <div>
                <div class="title-box">
                    <div class="tit-font clear">
                        <span class="t">学习卡详情</span>
                        <a href="<%=rootPath%>/companyStudyCard/gotoStudyCardsManage" class="btn proxyBtn" >返回</a>
                    </div>
                </div>
                <div class="proxyBox studyCardlibs-list">
                    <div class="publicTit">
                        <h4>基本信息</h4>
                        <hr/>
                    </div>
                    <div class="baseMan ">
                        <ul>
                            <li class="clear">
                                <div class="stuName fl">
                                    <b class="stuNameb">学习卡名称 :</b>
                                    <span>${studyCard.name }</span>
                                </div>
                                <div class="stuName fl">
                                    <b class="stuNameb">使用期限 :</b>
                                    <c:choose>
                                    	<c:when test="${!empty studyCard.useDateBeginString  }">
                                   			<span>从 ${studyCard.useDateBeginString }到 ${studyCard.useDateEndString }</span>
                                    	</c:when>
                                    	<c:otherwise>
                                    		<span>永久有效</span>
                                    	</c:otherwise>
                                    </c:choose>
                                </div>
                            </li>
                            <li class="clear">
                                <div class="stuName fl">
                                    <b class="stuNameb">创建日期 :</b>
                                    <span>${studyCard.createTimeString }</span>
                                </div>
                                <div class="stuName fl">
                                    <b class="stuNameb">代理机构 :</b>
                                    <span>${studyCard.proxyOrgName }</span>
                                </div>
                            </li>
                            <li class="clear">
                                <div class="stuName fl">
                                    <b class="stuNameb">发放数量 :</b>
                                    <span>${studyCard.totalNum }</span>
                                </div>
                                <div class="stuName fl">
                                    <b class="stuNameb">已领取数量 :</b>
                                    <span>${studyCard.usedNum }</span>
                                </div>
                            </li>
                            <li class="clear contCourse">
                                <b class="stuNameb fl">包含课程 :</b>
                                <c:choose>
                                	<c:when test="${fn:length(courseNames)>4}">
                               		<div class="courseMing fl" style="padding-bottom: 20px; ">
                                    <div class="manycourse">
                                        <c:forEach items="${courseNames }" var="course">
                                        	<span>${course }</span>
                                   		</c:forEach>
                                    </div>
                                   	<p class="cardPull">
                                      		 <i class="arrow "></i>
                                   	</p>
                               		</div>
                                	</c:when>
                                	<c:otherwise>
                                	<div class="courseMing fl">
                                    <div class="manycourse">
                                        <c:forEach items="${courseNames }" var="course">
                                        	<span>${course }</span>
                                   		</c:forEach>
                                    </div>
                               		</div>
                                	</c:otherwise>
                                </c:choose>
                            </li>
                            <li class="clear cardExpl">
                                <b class="stuNameb fl">学习卡说明 :</b>
                                <div class="courseExpl fl" style="word-wrap:break-word">
                                    ${studyCard.description }
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div class="publicTit">
                        <h4>使用详情</h4>
                        <hr/>
                    </div>
                    <div class="formtable">
                        <div class="searchCon">
                            <input type="text" id="username" name="username" value="" placeholder="用户名" />
                            <input type="text" id="mobile" name="mobile" value="" placeholder="手机号" />
                            <input type="text" id="name" name="name" value="" placeholder="学员姓名" />
                            <input type="text" id="code" name="code" value="" placeholder="学习码" />
                            <a href="javascript:;" class="btn searBtn libs-search">搜索</a>
                        </div>
                    </div>
                    <table class="table table-center cashrecord-tab">
                        <colgroup>
                            <col width="16.6%" />
                            <col width="16.6%" />
                            <col width="16.6%" />
                            <col width="16.6%" />
                            <col width="16.6%" />
                            <col width="16.6%" />
                        </colgroup>
                        <tbody>
                            <tr class="tab-htr">
                                <th>学习码</th>
                                <th>状态</th>
                                <th>用户名</th>
                                <th>手机号</th>
                                <th>姓名</th>
                                <th>使用日期</th>
                            </tr>
                            <tr><td colspan="6">暂无数据</td></tr>
                        </tbody>
                    </table>
                    <div class="pages pages-find pages-card">
                        <ul class="pagination">
                        </ul>
                    </div>
                    <input type="hidden" value="${studyCard.id }" id="cardId"> 
                    <input type="hidden" value="${errorMsg }" id="errorMsg">
                </div>
            </div>
        </div>
    </div>
    <div class="loading-bg lp-units-loading-bg" style="display:none"></div>  
    <div class="loading lp-units-loading " style="z-index: 99; display: none;">
		<p><i></i>加载中,请稍后...</p>
	</div>  
	<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/plugins/jquery-pagination/js/jquery.pagination.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/studyCards/studyCardLibDetails.js"></script>
	<script type="text/javascript">
		$(function(){
			var flag = false;
		    $('.cardPull .arrow').on('click', function() {
		        if (!flag) {
		            $(this).parents('.courseMing').css({
		                'max-height': "none"
		            });
		            $(this).addClass('arrowup');
		            flag = true;
		        } else {
		            $(this).parents('.courseMing').css({
		                'max-height': "67px"               
		            });
		            $(this).removeClass('arrowup');
		            flag = false;
		        }
		    })
		    
	        var manyH = $('.manycourse').outerHeight();
		    if (manyH > 67) {
		        $('.courseMing').find('.cardPull').show();
		    } else {
		        $('.courseMing').find('.cardPull').hide();
		    }
		})
	</script>
</body>
</html>