<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="/WEB-INF/wx.tld" prefix="wx" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String rootPath=request.getContextPath(); %>
<style type="text/css">
    .register {
        position: fixed;
        left: 50%;
        top: 50%;
        width: 400px;
        height: 400px;
        margin-left: -200px;
        margin-top: -200px;
        padding-bottom: 15px;
        background-color: #fff;
        border: 1px solid #ddd;
        border-radius: 5px;
        box-shadow: 0 0 30px rgba(0, 0, 0, 0.2);
        z-index: 999
    }
    .none {
        display: none;
    }
    .register .reg-close {
        position: absolute;
        top: 12px;
        right: 12px;
        width: 12px;
        height: 12px;
        background-image: url('../images/index-icons.png');
        background-repeat: no-repeat;
        background-position: 0 0;
        cursor: pointer;
    }
    .register .reg-title {
        padding: 15px 30px;
        border-bottom: 1px solid #e5e5e5;
    }
    .register .reg-form {
        padding: 0 60px;
    }
    .register .reg-bottom {
        padding: 2px 52px;
        border-top: 1px solid #e5e5e5;
    }
    .mark-bg {
        position: fixed;
        top: 0;
        right: 0;
        bottom: 0;
        left: 0;
        background-color: #fff;
        background-color: rgba(255, 255, 255, 0.6);
        opacity: .6 \9;
        filter: alpha(opacity=60);
    }
</style>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/message.js"></script>

<div class="m-list clear">
    <ul class="clear" id="ulListss">
	    <c:if test="${empty pageFinder.data}">
	    	<li>没有发现您要找的内容</li>
	    </c:if>
        <c:forEach items="${pageFinder.data }" var="que" varStatus="status">
            
            <li class="wendaLi">
                <div>
                    <div class="headPic">
                        <c:if test="${que.headPic == null }">
                            <img alt="" src="<%=rootPath %>/images/user/iconfont-danxiantouxiangline.png" class="yuanPic" width="50px;" height="50px;">
                        </c:if>
                        <c:if test="${que.headPic != null }">
                            <img alt="" src="${que.headPic}" class="yuanPic" width="50px;" height="50px;">
                        </c:if>
                        <div class="plName">${que.userName }</div>
                    </div>
                    <div class="content">
                        <div class="title1">
                            <c:if test="${que.adoptFlag != 1}">
                                <i class="iconfont" style="color: #00b7ee;cursor: default;">&#xe666;</i>
                            </c:if>
                            <c:if test="${que.adoptFlag == 1}">
                                <i class="iconfont" style="color: #11cd6e;cursor: default;">&#xe660;</i>
                            </c:if>
                            <c:if test="${que.topFlag == 1}">
                                <i class="iconfont zdt" style="cursor: default;">&#xe663;</i>
                            </c:if>
                            <c:if test="${que.essenceFlag == 1}">
                                <i class="iconfont jht" style="cursor: default;">&#xe662;</i>
                            </c:if>
                            <span class="queTitle">${que.questionTitle}</span>
                        </div>
                        <div class="ques">
                            <span class="showCon${que.id }">${que.subQuestionDesc}</span>
                            <span class="showAllCon${que.id } none">${que.questionDesc}</span>
                        </div>
                        <div class="ans">
                            <span class="pr50">提问时间：${que.tiwenTime}</span>
                                     <span class="pr50">
										<i class="iconfont" style="font-size: 12px;cursor: default;" ids="${que.id}">&#xe669;</i>
										<span class="showAll showAlls${que.id}" ids="${que.id}" style="cursor: pointer;">显示全部</span>
                                     </span>
                                     <span class="pr50">
										<i class="iconfont" style="cursor: default;" ids="${que.id}" userT="${que.userId }">&#xe668;</i>
										<span class="showAns" ids="${que.id}" userT="${que.userId }" style="cursor: pointer;">回答</span>
		                            </span>
		                            <span class="pr50">
										<c:if test="${que.courseName != null}">
										源自：${que.courseName }
										</c:if>
										<c:if test="${que.courseLectureName != null}">
										，${que.courseLectureName }
										</c:if>
									</span>
                                  </div>
                              </div>
                              <div class="mana">
                                  <c:if test="${isMan == 'yes' }">
                            <i class="iconfont delIcon fr cfont" ids="${que.id}">&#xe626;</i>
                            <c:if test="${que.essenceFlag == 1}">
                                <i class="iconfont fr jhIcon cfont jht" title="取消精华" ids="${que.id}">&#xe662;</i>
                            </c:if>
                            <c:if test="${que.essenceFlag != 1}">
                                <i class="iconfont fr jhIcon cfont" title="加入精华" ids="${que.id}">&#xe662;</i>
                            </c:if>
                            <c:if test="${que.topFlag == 1}">
                                <i class="iconfont fr zdIcon cfont zdt" title="取消置顶" ids="${que.id}">&#xe663;</i>
                            </c:if>
                            <c:if test="${que.topFlag != 1}">
                                <i class="iconfont fr zdIcon cfont" title="加入置顶" ids="${que.id}">&#xe663;</i>
                            </c:if>
                        </c:if>
                    </div>
                    <div class="plContent none pl${que.id}" queId="${que.id}"></div>
                </div>
            </li>

        </c:forEach>
    </ul>
</div>
<div class="pages">
    <ul class="pagination"></ul>
</div>
<input type="hidden" id="isMan" value="${isMan}" />
<script type="text/javascript">
    $(document).ready(function () {
        $(".pagination").pagination('${pageFinder.rowCount}', {
            next_text: "下一页",
            prev_text: "上一页",
            current_page: '${pageFinder.pageNo-1}',
            link_to: "javascript:void(0)",
            num_display_entries: 8,
            items_per_page: '${pageFinder.pageSize}',
            num_edge_entries: 1,
            callback: function (page, jq) {
                var pageNo = page + 1;
                console.log("pageNo=" + pageNo + ",oneId=" + oneId + ",twoId=" + twoId + ",jz=" + jz, "questionName=" + questionName);
                Form.findQuestion(pageNo, oneId, twoId, jz, questionName);
            }
        });
    });
</script>