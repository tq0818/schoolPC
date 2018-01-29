<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>课程设置</title>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/company.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/splitscreen.css"/>
    <link rel="stylesheet" href="<%=rootPath %>/stylesheets/look.css"/>
</head>
<body>
<!-- header start -->
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
<%@include file="/WEB-INF/jsp/company/commonDomain.jsp"%>
<!-- header end -->
<!-- 二级导航 -->
<div class="u-wrap company overflow">
<jsp:include page="/WEB-INF/jsp/menu/menu_newsystem.jsp"></jsp:include>
    <div class="right-side">
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">试听视频设置</span>
                </div>
            </div>
            <input type="hidden" value="${cv.id }" id="id"/>
            <p class="prompt-font">说明：用户可根据需求设置试听时长</p>
            <div class="block clear">
                <div class="label itm">试听时长设置：</div>
                <div class="itm">
                    <input type="text" value="${cv.overFlowTime }" id="overFlowTime" maxlength="3" onkeydown="onlyNum();"/>
                    <span>分钟</span>
                </div>
            </div>
            <div class="block clear">
                <div class="label itm">试听到时提示：</div>
                <div class="itm">
                    <textarea name="" id="overFlowInfo" placeholder="该视频属于收费视频，如想继续收看请您购买课程" maxlength="80" onkeydown="kedown();">${cv.overFlowInfo }</textarea>
                    <p class="prompt-font">注：最多可输入80个字</p>
                </div>
            </div>
            <div class="block clear">
                <div class="label itm">试听视频权限：</div>
                <div class="itm">
                <c:if test="${empty cv || cv.testListenAuth == 'ALL_USERS' }">
                    <input type="radio" value="ALL_USERS" name="testListenAuth" checked>
                    <i>所有用户</i>
                    <input type="radio" value="LAND_USERS" name="testListenAuth">
                    <i>注册用户</i>
                </c:if>
                <c:if test="${!empty cv && cv.testListenAuth != 'ALL_USERS' }">
                    <input type="radio" value="ALL_USERS" name="testListenAuth">
                    <i>所有用户</i>
                    <input type="radio" value="LAND_USERS" name="testListenAuths" checked>
                    <i>注册用户</i>
                </c:if>
                </div>
            </div>
        </div>
        <div>
            <div class="title-box">
                <div class="tit-font">
                    <span class="t">免费视频/资源观看权限</span>
                </div>
            </div>
            <p class="prompt-font">说明：对学员观看权限进行设定。当课程允许免费观看时系统会对学员权限进行判定</p>
            <div class="block clear">
                <div class="label itm">免费视频/资源观看权限：</div>
                <div class="itm">
                <c:if test="${empty cv || cv.userSeeAuth == 'ALL_USERS' }">
                    <input type="radio" value="ALL_USERS" name="userSeeAuth" checked>
                    <i>所有用户</i>
                    <input type="radio" value="LAND_USERS" name="userSeeAuth">
                    <i>注册用户</i>
                </c:if>
                <c:if test="${!empty cv && cv.userSeeAuth == 'LAND_USERS' }">
                    <input type="radio" value="ALL_USERS" name="userSeeAuth">
                    <i>所有用户</i>
                    <input type="radio" value="LAND_USERS" name="userSeeAuth" checked>
                    <i>注册用户</i>
                </c:if>
                </div>
            </div>
        </div>
        <%-- <div class="check-name">
            <div class="title-box ">
                <div class="tit-font">
                    <span class="t">随堂点名设置</span>
                </div>
            </div>
            <p class="prompt-font">说明：观看录播课时，系统将根据用户设置的时间或次数。对观看视频课的学员进行听课确认。学员只有确认后才可继续观看课程</p>
            <div class="block clear">
                <div class="label itm">点名规则：</div>
                <div class="itm">
                <c:if test="${empty cv || cv.setPointName == 'LOOK_VIDEO_NOT_NAMED' }">
                    <span>
                        <input type="radio" value="LOOK_VIDEO_NOT_NAMED" name="setPointName" checked>
                        <i>不点名</i>
                    </span>
                    <span>
                        <input type="radio" value="LOOK_VIDEO_BY_TIME" name="setPointName">
                        <i>按时间间隔（单位：分钟）</i>
                        <input type="text" value="${cv.namedTime }" id="namedTime" onkeydown="onlyNum();" maxlength="3"/>
                    </span>
                    <span>
                        <input type="radio" value="LOOK_VIDEO_BY_NUM" name="setPointName">
                        <i>按次数</i>
                        <input type="text" value="${cv.namedNum }" id="namedNum" onkeydown="onlyNum();" max="3"/>
                    </span>
				</c:if>
                <c:if test="${!empty cv && cv.setPointName == 'LOOK_VIDEO_BY_TIME' }">
                    <span>
                        <input type="radio" value="LOOK_VIDEO_NOT_NAMED" name="setPointName">
                        <i>不点名</i>
                    </span>
                    <span>
                        <input type="radio" value="LOOK_VIDEO_BY_TIME" name="setPointName" checked>
                        <i>按时间间隔（单位：分钟）</i>
                        <input type="text" value="${cv.namedTime }" id="namedTime" onkeydown="onlyNum();" maxlength="3"/>
                    </span>
                    <span>
                        <input type="radio" value="LOOK_VIDEO_BY_NUM" name="setPointName">
                        <i>按次数</i>
                        <input type="text" value="${cv.namedNum }" id="namedNum" maxlength="3"  onkeydown="onlyNum();"/>
                    </span>
				</c:if>
                <c:if test="${!empty cv && cv.setPointName == 'LOOK_VIDEO_BY_NUM' }">
                    <span>
                        <input type="radio" value="LOOK_VIDEO_NOT_NAMED" name="setPointName">
                        <i>不点名</i>
                    </span>
                    <span>
                        <input type="radio" value="LOOK_VIDEO_BY_TIME" name="setPointName">
                        <i>按时间间隔（单位：分钟）</i>
                        <input type="text" value="${cv.namedTime }" id="namedTime" maxlength="3" onkeydown="onlyNum();"/>
                    </span>
                    <span>
                        <input type="radio" value="LOOK_VIDEO_BY_NUM" name="setPointName" checked>
                        <i>按次数</i>
                        <input type="text" value="${cv.namedNum }" id="namedNum" onkeydown="onlyNum();" maxlength="3"/>
                    </span>
				</c:if>
                </div>
            </div>
        </div> --%>
        <%--<div class="block-btn is-ok" style="display:none;">--%>
        <div class="block-btn is-ok">
            <button class="ok">保存</button>
            <button class="cancel">取消</button>
        </div>
    </div>
</div>

<script type="text/javascript">
	$(function(){
		$selectSubMenu('org_service');
		$selectSubMenus('course_video_auth');
	});
</script>
<script src="<%=rootPath %>/javascripts/plus/jquery.cookie.js"></script>
<script src="<%=rootPath %>/javascripts/common/common.js"></script>
<script src="<%=rootPath %>/javascripts/class/look.js"></script>
</body>
</html>