<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <html lang="zh-cn">

    <head>
        <%@include file="/decorators/import.jsp" %>
            <title>
                <c:if test="${isEdit == 0}">添加</c:if>
                <c:if test="${isEdit != 0}">编辑</c:if>
			            直播公开课
            </title>

            <link href="<%=rootPath%>/stylesheets/classes.css" rel="stylesheet" type="text/css" />
            <link href="<%=rootPath%>/stylesheets/manage.css" rel="stylesheet" type="text/css" />
            <link href="<%=rootPath%>/stylesheets/openClass/openClass.css" rel="stylesheet" type="text/css" />
            <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
            <%-- <link href="<%=rootPath%>/stylesheets/jquery.datetimepicker.css" rel="stylesheet" type="text/css" />--%>
            <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/jcrop/css/jquery.Jcrop.css" />
            <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-fileupload/bootstrap-fileupload.css" />
            <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap/css/bootstrap.css">
            <link rel="stylesheet" type="text/css" href="<%=rootPath%>/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css">

            <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/select2/select2.css" />

            <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
            <%-- <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.datetimepicker.js"></script>--%>
            <script type="text/javascript" src="<%=rootPath %>/javascripts/class/addClassTypeOnsale.js"></script>
            <script type="text/javascript" src="<%=rootPath%>/javascripts/ajaxfileupload.js"></script>
            <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-fileupload/bootstrap-fileupload.js"></script>
            <script type="text/javascript" src="<%=rootPath%>/plugins/jcrop/js/jquery.Jcrop.js"></script>
            <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap/js/bootstrap.min.js"></script>
            <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js" charset="UTF-8"></script>
            <script type="text/javascript" src="<%=rootPath%>/plugins/bootstrap/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
            <script type="text/javascript" src="<%=rootPath%>/plugins/jquery-validation/jquery.validate.js"></script>
            <script type="text/javascript" src="<%=rootPath%>/javascripts/class/addTeacher.js"></script>
            <script type="text/javascript" src="<%=rootPath %>/javascripts/class/addClassTypePic.js"></script>
            <script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
            <script type="text/javascript" src="<%=rootPath %>/plugins/select2/select2.js"></script>
            <script type="text/javascript" src="<%=rootPath%>/javascripts/openClass/openClass.js"></script>
            <style type="text/css">
                .p1 {
                    display: block;
                    position: absolute;
                    z-index: 2000;
                    top: 10px;
                    right: -280px;
                }
                .p2 {
                    display: block;
                    position: absolute;
                    z-index: 2000;
                    top: 10px;
                    right: -280px;
                }
                .p3 {
                    display: block;
                    position: absolute;
                    z-index: 2000;
                    top: 10px;
                    right: -280px;
                }
                .p1 .preview-container {
                    width: 446px;
                    height: 241px;
                    overflow: hidden;
                }
                .p2 .preview-container {
                    width: 255px;
                    height: 138px;
                    overflow: hidden;
                }
                .p3 .preview-container {
                    width: 181px;
                    height: 96px;
                    overflow: hidden;
                }
                input+span {
                    color: red;
                }
            </style>
    </head>

    <body>
        <!-- header start -->
        <!-- header end -->
        <!-- 二级导航 -->
        <jsp:include page="/WEB-INF/jsp/menu/menu_class.jsp"></jsp:include>
        <div class="u-wrap resource">
            <div class="mainbackground">
                <div class="heading">
                    <h2 class="h5">
        			<c:if test="${isEdit eq 0}">
        			添加
        			</c:if>
        			<c:if test="${isEdit ne 0}">
        			编辑
        			</c:if>
        			直播公开课
                </h2>
                    <span class="line"></span>
                </div>
                <form id="conForm">
                    <input type="hidden" value="${course.id}" name="id" id="couId" />
                    <div class="mark-more">
                        <div class="main-content">
                            <ul class="list-infos clear">
                                <li style="height: 58px; margin-left: -80px;">
                                    <p class='c'>
                                        <span class="c-title">学科</span>  <span class="c-content">
									<select id="oneItem" name="itemOneId" style="width: 170px;">
										<c:forEach items="${oneItems}" var="one">
											<c:if test="${one.id == course.itemOneId }">
												<option selected="selected" value="${one.id}">${one.itemName}</option>
											</c:if>
											<c:if test="${one.id != course.itemOneId }">
												<option value="${one.id}">${one.itemName}</option>
											</c:if>
										</c:forEach>
								</select>
								</span>
                                    </p>
                                </li>
                                <li style="height: 58px; margin-left: 70px;">
                                    <p class='c'>
                                        <span class="c-title">学科小类</span>  <span class="c-content">
									<select id="twoItem" name="itemSecondId">

								</select> <input type="hidden" value="${course.itemSecondId}" id="twoId" />
								</span>
                                    </p>
                                </li>
                                <li style="height: 58px; margin-left: -80px;">
                                    <p class='c'>
                                        <span class="c-title">课程名称</span>  <span class="c-content">
									<input type="text" value="${course.openCourseName}" name="openCourseName" id="openCourseName" maxlength="62">
								</span>
                                    </p>
                                </li>
                                <li style="height: 58px; margin-left: 70px;">
                                    <p class='c'>
                                        <span class="c-title">课程封面</span>  <span class="c-content poAbso">
                                    <c:if test="${empty course.cover}">
										<img class="img" id="commdotityPic" src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image">
									</c:if>
                                    <c:if test="${!empty course.cover}">
										<img class="img" id="commdotityPic" src="http://${imgUrl}/${course.cover}">
									</c:if>
                                    <input type="hidden" id="claPicUrl" name="cover" value="${course.cover}" />
                                    <a href="javascript:void(0)" class="btn btn-sm btn-default" id="upPic">上传图片</a>
								</span>
                                    </p>
                                </li>
                                <li style="height: 58px; margin-left: -80px;">
                                    <p class='c'>
                                        <span class="c-title">名师</span> 
                                        <span class="c-content">
    									<select id="teacherList" name="teacherId" style="width: 170px;"></select>
                                        <input type="hidden" value="${course.teacherId}" id="teacherId" />
                                        <a href="javascript:void(0)" class="box-select" style="color: #03f;cursor: pointer;">点击添加老师</a>
								    </span>
                                    </p>
                                </li>
                                <li style="height: 58px; margin-left: 70px;"></li>
                                <li style="height: 58px; margin-left: -80px;">
                                    <p class='c'>
                                        <span class="c-title">教务</span>  <span class="c-content">
    									<select id="assistantList" name="assistantId" style="width: 170px;">
        									<option value="-1">无</option>
        								</select>
                                        <input type="hidden" value="${course.assistantId}" id="assistantId" /> 
    								</span>
                                    </p>
                                </li>
                                <li style="height: 58px; margin-left: 70px;"></li>
                                <li id="time1" style="height: 58px; margin-left: -80px;">
                                    <p class='c'>
                                        <span class="c-title">开始时间</span>
                                        <span>
                                            <span class="input-append date form_time" data-date="" data-date-format="yyyy-mm-dd hh:ii">
                                                <input class="span2" size="16" type="text" value="" id="statTime" readonly="readonly" style="width: 150px;cursor: default;" />
    											<span class="add-on"><i class="icon-th"></i></span>
                                            </span>
                                        </span>
                                        <span class="c-title">结束时间</span> 
                                        <span>
                                            <span class="input-append date form_time" data-date="" data-date-format="yyyy-mm-dd hh:ii">
                                                <input class="span2" size="16" type="text" value="" id="endTime" readonly="readonly" style="width: 150px;cursor: default;position: absolute;margin-top: -6px;">
    											<span class="add-on"><i class="icon-th"></i></span>
                                            </span>
                                        </span>
                                        <input type="hidden" value="${course.startTime}" name="startTime" id="startTimes" />
                                        <input type="hidden" value="${course.endTime}" name="endTime" id="endTimes" />
                                        <input type="hidden" value='<fmt:formatDate value="${course.startOpenData}" pattern="yyyy-MM-dd"/>' name="startOpenData" id="startDate" />
                                        <input type="hidden" value='<fmt:formatDate value="${course.endOpenData}" pattern="yyyy-MM-dd"/>' name="endOpenData" id="endDate" />
                                    </p>
                                </li>
                                <li id="time2" style="height: 58px; margin-left: 70px;"></li>
                                <c:if test="${tg ne 'zs' and tg ne 'ht' and tg ne 'cc'}">
                                    <li id="time3" style="height: 58px; margin-left: -80px;" id="suportM">
                                        <p class='c'>
                                            <span class="c-title">是否支持手机端</span>
                                            <span class="c-content">
            									<c:if test="${course.supportMobile == 1}">
            										<input type="radio" name="supportMobile" value="1" checked="checked"/>支持
            										<input type="radio" name="supportMobile" value="0" />不支持
            									</c:if>
            									<c:if test="${course.supportMobile != 1}">
            										<input type="radio" name="supportMobile" value="1" />支持
            										<input type="radio" name="supportMobile" value="0" checked="checked"/>不支持
            									</c:if>	
        									</span>
                                        </p>
                                    </li>
                                    <li id="time4" style="height: 58px; margin-left: 70px;" id="removeLi"></li>
                                </c:if>
                                <c:if test="${tg eq 'ht'}">
                                    <li id="time3" style="height: 58px; margin-left: -80px;" id="barrageM">
                                        <p class='c'>
                                            <span class="c-title">弹幕</span>
                                            <span class="c-content">
            									<c:if test="${course.barrage == 1}">
            										<input type="radio" name="barrage" value="1" checked="checked"/>支持
            										<input type="radio" name="barrage" value="0" />不支持
            									</c:if>
            									<c:if test="${course.barrage != 1}">
            										<input type="radio" name="barrage" value="1" />支持
            										<input type="radio" name="barrage" value="0" checked="checked"/>不支持
            									</c:if>	
        									</span>
                                        </p>
                                    </li>
                                    <li id="time3" style="height: 58px; margin-left: -80px;" id="modetypeM">
                                        <p class='c'>
                                            <span class="c-title">模式</span>
                                            <span class="c-content">
                                            	<select id="modetype" name="modetype">
                                            	<c:choose>
                                            		<c:when test="${course.modetype == 1 }">
                                            			<option value="3">大班课</option>
                                            			<option value="1" selected="selected">语音互动课堂</option>
                                            		</c:when>
                                            		<c:otherwise>
                                            			<option value="3" selected="selected">大班课</option>
                                            			<option value="1">语音互动课堂</option>
                                            		</c:otherwise>
                                            	</c:choose>
                                            	</select>
        									</span>
                                        </p> 
                                    </li>
                                </c:if>
                                <c:if test="${tg eq 'cc' }">
                                    <li id="time3" style="height: 58px; margin-left: -80px;" id="barrageM">
                                        <p class='c'>
                                            <span class="c-title">弹幕</span>
                                            <span class="c-content">
            									<c:if test="${course.barrage == 1}">
            										<input type="radio" name="barrage" value="1" checked="checked" <c:if test="${isEdit ne 0 }">disabled="disabled"</c:if>/>支持
            										<input type="radio" name="barrage" value="0" <c:if test="${isEdit ne 0 }">disabled="disabled"</c:if> />不支持
            									</c:if>
            									<c:if test="${course.barrage != 1}">
            										<input type="radio" name="barrage" value="1" <c:if test="${isEdit ne 0 }">disabled="disabled"</c:if> />支持
            										<input type="radio" name="barrage" value="0" checked="checked" <c:if test="${isEdit ne 0 }">disabled="disabled"</c:if>/>不支持
            									</c:if>	
        									</span>
                                        </p>
                                    </li>
                                    <li id="time3" style="height: 58px; margin-left: -80px;" id="modetypeM">
                                        <p class='c'>
                                            <span class="c-title">直播间模板</span>
                                            <span class="c-content">
                                            	<select id="modetype" name="modetype" <c:if test="${isEdit ne 0 }">disabled="disabled"</c:if>>
                                            			<option value="1" <c:if test="${course.modetype == 1}">selected</c:if>>模板1</option>
                                            			<option value="2" <c:if test="${course.modetype == 2}">selected</c:if>>模板2</option>
                                            			<option value="3" <c:if test="${course.modetype == 3}">selected</c:if>>模板3</option>
                                            			<option value="4" <c:if test="${course.modetype == 4}">selected</c:if>>模板4</option>
                                            			<option value="5" <c:if test="${course.modetype == 5}">selected</c:if>>模板5</option>
                                            			<option value="6" <c:if test="${course.modetype == 6}">selected</c:if>>模板6</option>
                                            	</select>
        									</span>
                                        </p> 
                                    </li>
                                </c:if>
                                <li style="height: 100px; margin-left: -80px;">
                                    <p class='c'>
                                        <span class="c-title">课程详情</span>
                                        <span class="c-content poAbso">
                                            <textarea rows="5" cols="60" id="detailDesc" placeholder="字数限制在0～120范围内" name="detailDesc">${course.detailDesc}</textarea>
        								</span>
                                    </p>
                                </li>
                            </ul>

                        </div>
                        <div class="m-bo text-center" style="margin-top: 80px;">
                            <a href="javascript:void(0)" class="btn btn-sm btn-primary saveInfo">保存并退出</a>
                            <a href="javascript:void(0)" class="btn btn-sm btn-primary saveInfo">保存并发布</a>
                            <a href="<%=rootPath%>/liveOpenCourse/toLiveShow" class="btn btn-sm btn-default">取消</a> 
                            <input type="hidden" id="publishStatus" value="${course.publishStatus}" />
                        </div>
                    </div>
                </form>
                <input type="hidden" value="${imgUrl}" id="imgUrl" />
				<input type="hidden" id="ysTime" value='<fmt:formatDate value="${course.startOpenData}"/>' />
                <!-- ajax加载中div开始 -->
                <div class="loading lp-units-loading" style="display: none">
                    <p>
                        <i></i>加载中,请稍后...
                    </p>
                </div>
                <div class="loading-bg lp-units-loading-bg" style="display: none"></div>

                <!--  ajax加载中div结束 -->
                <div class="upload-layer none" id="chooseDiv" style="width: 1080px;">
                    <div class="upload-title">
                        <h2 class="h5">上传课程封面</h2>
                        <i class="iconfont close">&#xe610;</i>
                    </div>
                    <div class="types">
                        <a href="javascript:queryConditionPics('');" marks="privatePic" class="btn btn-default btn-success">私有云</a>
                        <a href="javascript:queryConditionPics('');" marks="publicPic" class="btn btn-default">公有云</a>
                    </div>
                    <div class="types-list">
                        <a href="javascript:queryAll(1,'');" class="btn btn-mini btn-link active">全部</a>
                    </div>
                    <div id="tlist">

                    </div>
                    <div class="pic-upload none">
                        <p class="tips">
                            <input type="file" class="btn btn-mini btn-primary" name="imgData" accept=".jpg,.png,.gif" id="imgData" onchange="Forms.savePic()" value="重新选择文件" />
                            <span>建议上传的图片尺寸为：516*282 </span>
                        </p>
                        <div class="upload-content" style="padding: 10px;">
                            <div class="attributes none">
                                <input type="hidden" id="x" name="x" value="0" />
                                <input type="hidden" id="y" name="y" value="0" />
                                <input type="hidden" id="w" name="w" value="0" />
                                <input type="hidden" id="h" name="h" value="0" />
                                <input type="hidden" id="x2" name="x2" value="0" />
                                <input type="hidden" id="y2" name="y2" value="0" />
                            </div>
                            <div class="pic" style="width: 516px; height: 282px; background-color: #f2f2f2;">
                                <img id="target" src="" />
                            </div>
                            <div class="upload-big p1" style="width: 456px;">
                                <div class="preview-container" style="margin: 0 auto;">
                                    <img src="">
                                </div>
                            </div>
                            <div class="upload-sm p2" style="left: 540px;">
                                <div class="preview-container">
                                    <img src="">
                                </div>
                            </div>
                            <div class="upload-mini p3" style="left: 805px;">
                                <div class="preview-container">
                                    <img src="">
                                </div>
                            </div>
                        </div>
                        <p class="text-center">
                            <a href="javascript:Forms.saveCutPic();" class="btn btn-primary">确定</a>
                            <a href="javascript:;" class="btn btn-default btn-cancel">取消</a>
                        </p>
                    </div>
                </div>
                <div class="add-layer-bg none" id="stopDiv"></div>
            </div>
        </div>
        <!-- 添加教师弹窗 -->
        <div class="popupwin teacherpopuwin" data-pupwin="modal" style="top: 150px;position:fixed;">
            <form id="addTeachersForm">
                <div class="popupwin-title">
                    <h2 class="h5">新增老师</h2>
                    <i class="close iconfont"></i>
                </div>
                <div class="main form-horizontal" id="lsOne">
                    <div class="form-body">
                        <div class="form-group">
                            <label class="col-md-3 control-label"><em style="color: red;">*</em>用户名</label>
                            <div class="col-md-4">
                                <input type="text" id="tuserName" name="tuserName" class="form-control" placeholder="">
                                <span class="help-block" style="color: red;"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label"><em style="color: red;">*</em>密码</label>
                            <div class="col-md-4">
                                <input class="form-control" id="password" name="password" type="password" />
                                <span class="tips" style="color: red;"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label"><em style="color: red;">*</em>确认密码</label>
                            <div class="col-md-4">
                                <input class="form-control" id="confirmpwd" name="confirmpwd" type="password" />
                                <span class="tips" style="color: red;"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label"><em style="color: red;">*</em>姓名</label>
                            <div class="col-md-4">
                                <input class="form-control" id="realName" name="realName" type="text" />
                                <span class="tips" style="color: red;"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-3 control-label"><em style="color: red;">*</em>手机号</label>
                            <div class="col-md-4">
                                <input class="form-control" id="tMobile" name="tMobile" type="text" />
                                <span class="tips" style="color: red;"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-3 col-md-offset-4" style="width: 26%;">
                                <a class="m-btn-red" href="javascript:Form.addTeacher();">确&nbsp;&nbsp;定</a>
                                <a class="m-btn-default" data-pupwin-btn="cancle" style="position: absolute;" href="javascript:;">取&nbsp;&nbsp;消</a>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <input type="hidden" id="isCopy" value="${isCopy}" />
            <input type="hidden" id="isEdit" value="${isEdit}" />
        </div>
        <script>
            $(function () {
                $selectSubMenu('open_class_set');
            });
        </script>
        <!-- footer start -->
        <!-- footer end -->
    </body>

									</select> <input type="hidden" value="${course.itemSecondId}" id="twoId" />
									</span>
								</p>
							</li>
							<li style="height: 58px; margin-left: -80px;">
								<p class='c'>
									<span class="c-title">课程名称</span> <span class="c-content">
										<input type="text" value="${course.openCourseName}"
										name="openCourseName" id="openCourseName" maxlength="62">
									</span>
								</p>
							</li>
							<li style="height: 58px; margin-left: 70px;">
								<p class='c'>
									<span class="c-title">课程封面</span> <span
										class="c-content poAbso"> <c:if
											test="${empty course.cover}">
											<img class="img" id="commdotityPic"
												src="http://www.placehold.it/200x150/EFEFEF/AAAAAA&amp;text=no+image">
										</c:if> <c:if test="${!empty course.cover}">
											<img class="img" id="commdotityPic"
												src="http://${imgUrl}/${course.cover}">
										</c:if> <input type="hidden" id="claPicUrl" name="cover"
										value="${course.cover}" /> <a href="javascript:void(0)"
										class="btn btn-sm btn-default" id="upPic">上传图片</a>
									</span>
								</p>
							</li>
							<li style="height: 58px; margin-left: -80px;">
								<p class='c'>
									<span class="c-title">名师</span> <span class="c-content">
										<select id="teacherList" name="teacherId" style="width: 170px;">

									</select> <input type="hidden" value="${course.teacherId}"
										id="teacherId" /> <a class="box-select" style="color: #03f;">点击我添加老师</a>
									</span>
								</p>
							</li>
							<li style="height: 58px; margin-left: 70px;"></li>
							<li style="height: 58px; margin-left: -80px;">
								<p class='c'>
									<span class="c-title">教务</span> <span class="c-content">
										<select id="assistantList" name="assistantId" style="width: 170px;">
										<option value="-1">无</option>
									</select> <input type="hidden" value="${course.assistantId}" id="assistantId" /> 
									</span>
								</p>
							</li>
							<li style="height: 58px; margin-left: 70px;"></li>
							<li id="time1" style="height: 58px; margin-left: -80px;">
								<p class='c'>
									<span class="c-title">开始时间</span>
									<span> <span class="input-append date form_time"
											data-date="" data-date-format="yyyy-mm-dd hh:ii"> <input
												class="span2" size="16" type="text" value="" id="statTime"
												 readonly="readonly" style="width: 130px;cursor: default;">
												<span class="add-on"><i class="icon-th"></i></span>
										</span> 
									</span>
									<span class="c-title">结束时间</span> 
									<span> <span class="input-append date form_time"
											data-date="" data-date-format="yyyy-mm-dd hh:ii"> <input
												class="span2" size="16" type="text" value="" id="endTime"
												 readonly="readonly" style="width: 130px;cursor: default;position: absolute;margin-top: -6px;">
												<span class="add-on"><i class="icon-th"></i></span>
										</span> 
									</span> 
									<input type="hidden" value="${course.startTime}" name="startTime" id="startTimes" /> 
									<input type="hidden" value="${course.endTime}" name="endTime" id="endTimes" />
									<input type="hidden" value='<fmt:formatDate value="${course.startOpenData}" pattern="yyyy-MM-dd"/>' name="startOpenData" id="startDate" />
									<input type="hidden" value='<fmt:formatDate value="${course.endOpenData}" pattern="yyyy-MM-dd"/>' name="endOpenData" id="endDate" />
									</span>
								</p>
							</li>
							<li id="time2" style="height: 58px; margin-left: 70px;"></li>
							<c:if test="${tg != 'zs' }">
								<li id="time3" style="height: 58px; margin-left: -80px;" id="suportM">
									<p class='c'>
										<span class="c-title">是否支持手机端</span> <span class="c-content">
										<c:if test="${course.supportMobile == 1}">
											<input type="radio" name="supportMobile" value="1" checked="checked"/>支持
											<input type="radio" name="supportMobile" value="0" />不支持
										</c:if>
										<c:if test="${course.supportMobile != 1}">
											<input type="radio" name="supportMobile" value="1" />支持
											<input type="radio" name="supportMobile" value="0" checked="checked"/>不支持
										</c:if>	
										</span>
									</p>
								</li>
								<li id="time4" style="height: 58px; margin-left: 70px;" id="removeLi"></li>
							</c:if>
							<li style="height: 100px; margin-left: -80px;">
								<p class='c'>
									<span class="c-title">课程详情</span> <span
										class="c-content poAbso"> <textarea rows="5" cols="60" id="detailDesc" placeholder="字数限制在0～120范围内"
											name="detailDesc">${course.detailDesc}</textarea>
									</span>
								</p>
							</li>
						</ul>

					</div>
					<div class="m-bo text-center" style="margin-top: 80px;">
						<a href="javascript:void(0)"
							class="btn btn-sm btn-primary saveInfo">保存并退出</a> <a
							href="javascript:void(0)" class="btn btn-sm btn-primary saveInfo">保存并发布</a>
						<a href="<%=rootPath%>/liveOpenCourse/toLiveShow"
							class="btn btn-sm btn-default">取消</a> <input type="hidden"
							id="publishStatus" value="${course.publishStatus}" />
					</div>
				</div>
			</form>
			<input type="hidden" value="${imgUrl}" id="imgUrl" />
			<input type="hidden" id="ysTime" value="<fmt:formatDate value="${course.startOpenData}"/>" />
			<!-- ajax加载中div开始 -->
			<div class="loading lp-units-loading" style="display: none">
				<p>
					<i></i>加载中,请稍后...
				</p>
			</div>
			<div class="loading-bg lp-units-loading-bg" style="display: none"></div>

			<!--  ajax加载中div结束 -->
			<div class="upload-layer none" id="chooseDiv" style="width: 1080px;">
				<div class="upload-title">
					<h2 class="h5">上传课程封面</h2>
					<i class="iconfont close">&#xe610;</i>
				</div>
				<div class="types">
			        <a href="javascript:queryConditionPics('');" marks="privatePic" class="btn btn-default btn-success">私有云</a>
			        <a href="javascript:queryConditionPics('');" marks="publicPic" class="btn btn-default">公有云</a>
			    </div>
			    <div class="types-list">
			        <a href="javascript:queryAll(1,'');" class="btn btn-mini btn-link active">全部</a>
			    </div>
			    <div id="tlist">
				   
			    </div>
				<div class="pic-upload none">
					<p class="tips">
						<input type="file" class="btn btn-mini btn-primary" name="imgData" accept=".jpg,.png,.gif"
							id="imgData" onchange="Forms.savePic()" value="重新选择文件" />
						<!--<a href="javascript:;" class="btn btn-mini btn-primary">重新选择文件</a>-->
						<span>建议上传的图片尺寸为：516*282 </span>
					</p>
					<div class="upload-content" style="padding: 10px;">
						<div class="attributes none">
							<input type="hidden" id="x" name="x" value="0" /> <input
								type="hidden" id="y" name="y" value="0" /> <input type="hidden"
								id="w" name="w" value="0" /> <input type="hidden" id="h"
								name="h" value="0" /> <input type="hidden" id="x2" name="x2"
								value="0" /> <input type="hidden" id="y2" name="y2" value="0" />
						</div>
						<div class="pic"
							style="width: 516px; height: 282px; background-color: #f2f2f2;">
							<img id="target" src="" />
						</div>
						<div class="upload-big p1" style="width: 456px;">
							<div class="preview-container" style="margin: 0 auto;">
								<img src="">
							</div>
						</div>
						<div class="upload-sm p2" style="left: 540px;">
							<div class="preview-container">
								<img src="">
							</div>
						</div>
						<div class="upload-mini p3" style="left: 805px;">
							<div class="preview-container">
								<img src="">
							</div>
						</div>
					</div>
					<p class="text-center">
						<a href="javascript:Forms.saveCutPic();" class="btn btn-primary">确定</a>
						<a href="javascript:;" class="btn btn-default btn-cancel close">取消</a>
					</p>
				</div>
			</div>
			<div class="add-layer-bg none" id="stopDiv"></div>
		</div>
	</div>
	<!-- 添加教师弹窗 -->
	<div class="popupwin teacherpopuwin" data-pupwin="modal" style="top:150px;position:fixed">
		<form id="addTeachersForm">
			<div class="popupwin-title">
				<h2 class="h5">新增老师</h2>
				<i class="close iconfont"></i>
			</div>
			<div class="main form-horizontal" id="lsOne">
				<div class="form-body">
					<div class="form-group">
						<label class="col-md-3 control-label"><em style="color: red;">*</em>用户名</label>
						<div class="col-md-4">
							<input type="text" id="tuserName" name="tuserName"
								class="form-control" placeholder=""> <span
								class="help-block" style="color: red;"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><em style="color: red;">*</em>密码</label>
						<div class="col-md-4">
							<input class="form-control" id="password" name="password"
								type="password" /> <span class="tips" style="color: red;"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><em style="color: red;">*</em>确认密码</label>
						<div class="col-md-4">
							<input class="form-control" id="confirmpwd" name="confirmpwd"
								type="password" /> <span class="tips" style="color: red;"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><em style="color: red;">*</em>姓名</label>
						<div class="col-md-4">
							<input class="form-control" id="realName" name="realName"
								type="text" /> <span class="tips" style="color: red;"></span>
						</div>
					</div>
					<div class="form-group">
						<label class="col-md-3 control-label"><em style="color: red;">*</em>手机号</label>
						<div class="col-md-4">
							<input class="form-control" id="tMobile" name="tMobile"
								type="text" /> <span class="tips" style="color: red;"></span>
						</div>
					</div>
					<div class="form-group">
						<div class="col-md-3 col-md-offset-4" style="width: 26%;">
							<a class="m-btn-red" href="javascript:Form.addTeacher();">确&nbsp;&nbsp;定</a>
							<a class="m-btn-default" data-pupwin-btn="cancle" style="position: absolute;"
								href="javascript:;">取&nbsp;&nbsp;消</a>
						</div>
					</div>
				</div>
			</div>
		</form>
		<input type="hidden" id="isCopy" value="${isCopy}"/>
		<input type="hidden" id="isEdit" value="${isEdit}"/>
	</div>
	<script>
		$(function() {
			$selectSubMenu('open_class_set');
		});
	</script>
	<!-- footer start -->
	<!-- footer end -->
</body>
</html>
