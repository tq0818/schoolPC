<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!doctype html>
<html lang="zh-cn">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>模板设置</title>
<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/operate.css">
<link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/system/schoolwork.css"/>

</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>
	<div class="u-wrap operate">
	    <div class="operate_live_heading" style="display:none">
	    </div>
	    <div class="mainbackground schWorkT">
	        <div class="heading" data-school-id="${schoolId}" data-new-e-c-flag="${newECFlag}">
	            <h2 class="h5">模板设置</h2>
	            <span class="line"></span>
	            <span class="rb">
	                <a href="javascript:;" class="btn btn-mini btn-primary save" style="background-color:#1ebb90;border-color:#1ebb90;">发布生效</a>
	                <%--<a href="javascript:;" class="btn btn-mini btn-primary reset">重置模板</a>
	                <a href="javascript:;" class="btn btn-mini btn-primary preview">预览</a>--%>
	            	<a href="javascript:;" class="btn btn-mini btn-primary goback">返回</a>
	            </span>
	        </div>
	        <div class="schWork-option clear">
	            <div  class="schList fl">
	                <dl>
	                    <dt>
	                        <span class="w32">模块名称</span>
	                        <span class="w33">自定义名称</span>
	                        <span class="w9">状态</span>
	                        <span class="w17">操作</span>
	                        <span class="w9">拖动</span>
	                    </dt>
	                </dl>
	                <div class="addAdverBut" style="display:none">
	                    <button><i class="iconfont">&#xe652;</i>添加广告</button>
	                </div>
	            </div>
	            <div class="eaditArea fr">
	                <h2>编辑区域</h2>
	                <div class="eaditCon">
	                    <!--今日公开课-->
	                    <div class="swConText nowClass" style="display: none;" data-id="0">
	                        <h3>公开课</h3>
	                        <form action="">
	                            <ul class="swFormList">
	                                <li class="clear">
	                                    <h4 class="fl">自定义名称</h4>
	                                    <p class="fr">
	                                        <input type="text" name="" id="" class="swText" placeholder="最多输入六个字">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <!--<h4 class="fl">上传图片</h4>-->
	                                    <p class="fr">
	                                        <input type="button" value="保存" class="swSaveBut">
	                                    </p>
	                                </li>
	                            </ul>
	                        </form>
	                    </div>
	                    <!--推荐课程-->
	                    <div class="swConText nowClass" style="display: none;" data-id="1">
	                        <h3>推荐课程</h3>
	                        <form action="">
	                            <ul class="swFormList">
	                                <li class="clear">
	                                    <h4 class="fl">自定义名称</h4>
	                                    <p class="fr">
	                                        <input type="text" name="" class="swText" placeholder="最多输入六个字">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <h4 class="fl">课程排序</h4>
	                                    <p class="fr">
	                                        <input type="button" data-order="time" value="最新" class="swChioceBut active">
	                                        <input type="button" data-order="hot" value="最热" class="swChioceBut">
	                                        <input type="button" data-order="zh" value="综合" class="swChioceBut">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <h4 class="fl">课程数量</h4>
	                                    <p class="fr">
	                                        <input type="button" data-limit="4" value="4" class="swChioceBut active">
	                                        <input type="button" data-limit="8" value="8" class="swChioceBut">
	                                        <input type="button" data-limit="12" value="12" class="swChioceBut">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <!--<h4 class="fl">上传图片</h4>-->
	                                    <p class="fr">
	                                        <input type="button" value="保存" class="swSaveBut">
	                                    </p>
	                                </li>
	                            </ul>
	                        </form>
	                    </div>
	                    <!--课程-->
	                    <div class="swConText nowClass" style="display: none;" data-id="2">
	                        <h3>课程</h3>
	                        <form action="">
	                            <ul class="swFormList">
	                                <li class="clear">
	                                    <h4 class="fl">展示学科</h4>
	                                    <p class="fr showCheck itemOneList">
	                                        <label for=""><input type="checkbox" class="swCheck">学科名称最多可以放14个字的啊</label>
	                                        <label for=""><input type="checkbox" class="swCheck"> 学科2</label>
	                                        <label for=""><input type="checkbox" class="swCheck"> 学科3</label>
	                                        <label for=""><input type="checkbox" class="swCheck"> 学科4</label>
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <h4 class="fl">课程排序</h4>
	                                    <p class="fr">
	                                        <input type="button" data-order="time" value="最新" class="swChioceBut active">
	                                        <input type="button" data-order="hot" value="最热" class="swChioceBut">
	                                        <input type="button" data-order="zh" value="综合" class="swChioceBut">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <h4 class="fl">课程数量</h4>
	                                    <p class="fr">
	                                        <input type="button" data-limit="4" value="4" class="swChioceBut active">
	                                        <input type="button" data-limit="8" value="8" class="swChioceBut">
	                                        <input type="button" data-limit="12" value="12" class="swChioceBut">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <!--<h4 class="fl">上传图片</h4>-->
	                                    <p class="fr">
	                                        <input type="button" value="保存" class="swSaveBut">
	                                    </p>
	                                </li>
	                            </ul>
	                        </form>
	                    </div>
	                    <!--通屏-->
	                    <div class="swConText" style="display: none"  data-id="3">
	                        <h3>广告</h3>
	                        <form action="">
	                            <ul class="swFormList">
	                                <li class="clear">
	                                    <h4 class="fl">广告名称</h4>
	                                    <p class="fr">
	                                        <input type="text" name="" id="" class="swText">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <h4 class="fl">跳转链接</h4>
	                                    <p class="fr">
	                                        <input type="text" name="" id="" class="swText">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <h4 class="fl">通屏显示</h4>
	                                    <p class="fr">
	                                    <p class="fr showCheck isWidth">
	                                        <label for=""><input type="checkbox" class="swCheck" data-width="0" checked="checked"> 否</label>
	                                        <label for=""><input type="checkbox" class="swCheck" data-width="1"> 是</label>
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <h4 class="fl">上传图片</h4>
	                                    <p class="fr">
	                                        <label for="" class="swFileBut"><input type="file" name="imgData" id="imgData">上传</label>
	                                        <!-- <span class="prompt red">！上传失败</span>
	                                        <span class="prompt complete">已上传</span> -->
	                                        <span class="prompt uploading">未上传</span>
	                                        <!-- <span class="prompt uploading">上传中</span> -->
	                                    </p>
	                                    <p class="fr reMark" style="display:none;">
	                                        <span>建议上传图片宽度：1200px</span>
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <!--<h4 class="fl">上传图片</h4>-->
	                                    <p class="fr">
	                                        <input type="button" value="保存" class="swSaveBut">
	                                    </p>
	                                </li>
	                            </ul>
	                        </form>
	                    </div>
	                    <!--推荐课程包-->
	                    <div class="swConText nowClass" style="display: none;" data-id="5">
	                        <h3>推荐课程包</h3>
	                        <form action="">
	                            <ul class="swFormList">
	                                <li class="clear">
	                                    <h4 class="fl">自定义名称</h4>
	                                    <p class="fr">
	                                        <input type="text" name="" id="" class="swText">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <h4 class="fl">课程包排序</h4>
	                                    <p class="fr">
	                                        <input type="button" data-order="time" value="最新" class="swChioceBut active">
	                                        <input type="button" data-order="hot" value="最热" class="swChioceBut">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <h4 class="fl">课程包数量</h4>
	                                    <p class="fr">
	                                        <input type="button" data-limit="3" value="3" class="swChioceBut active">
	                                        <input type="button" data-limit="6" value="6" class="swChioceBut">
	                                        <input type="button" data-limit="9" value="9" class="swChioceBut">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <!--<h4 class="fl">上传图片</h4>-->
	                                    <p class="fr">
	                                        <input type="button" value="保存" class="swSaveBut">
	                                    </p>
	                                </li>
	                            </ul>
	                        </form>
	                    </div>
	                    <!--名师专区-->
	                    <div class="swConText nowClass" style="display: none;"  data-id="6">
	                        <h3>名师专区</h3>
	                        <form action="">
	                            <ul class="swFormList">
	                                <li class="clear">
	                                    <h4 class="fl">自定义名称</h4>
	                                    <p class="fr">
	                                        <input type="text" name="" id="" class="swText">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <h4 class="fl">名师排序</h4>
	                                    <p class="fr">
	                                        <input type="button" data-order="zh" value="综合" class="swChioceBut active">
	                                        <input type="button" data-order="hot" value="人气" class="swChioceBut">
	                                        <input type="button" data-order="good" value="好评" class="swChioceBut">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <p class="fr">
	                                        <input type="button" value="保存" class="swSaveBut">
	                                    </p>
	                                </li>
	                            </ul>
	                        </form>
	                    </div>
	                    <!--会员专区-->
	                    <div class="swConText nowClass" style="display: none;"  data-id="7">
	                        <h3>会员专区</h3>
	                        <form action="">
	                            <ul class="swFormList">
	                                <li class="clear">
	                                    <h4 class="fl">自定义名称</h4>
	                                    <p class="fr">
	                                        <input type="text" name="" class="swText" placeholder="">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <h4 class="fl">会员排序</h4>
	                                    <p class="fr">
	                                        <input type="button" data-order="asc" value="递增" class="swChioceBut active">
	                                        <input type="button" data-order="desc" value="递减" class="swChioceBut">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <!--<h4 class="fl">上传图片</h4>-->
	                                    <p class="fr">
	                                        <input type="button" value="保存" class="swSaveBut">
	                                    </p>
	                                </li>
	                            </ul>
	                        </form>
	                    </div>
	                    <!--学员动态-->
	                    <div class="swConText nowClass" style="display: none;" data-id="8">
	                        <h3>学员动态</h3>
	                        <form action="">
	                            <ul class="swFormList">
	                                <li class="clear">
	                                    <h4 class="fl">自定义名称</h4>
	                                    <p class="fr">
	                                        <input type="text" name="" class="swText" placeholder="">
	                                    </p>
	                                    <p class="fr reMark">
	                                        <span class="red">注：学员动态包括：注册账号、登录网校、购买课程、购买课程包、购买积分、购买会员、学习课程、点评课程、参加公开课、发布问题、回答问题、题库答题、参加考试、完成课后测验及完成课后作业。</span>
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <p class="fr">
	                                        <input type="button" value="保存" class="swSaveBut">
	                                    </p>
	                                </li>
	                            </ul>
	                        </form>
	                    </div>
	                    <!--问答社区-->
	                    <div class="swConText nowClass" style="display: none;"  data-id="9">
	                        <h3>问答社区</h3>
	                        <form action="">
	                            <ul class="swFormList">
	                                <li class="clear">
	                                    <h4 class="fl">自定义名称</h4>
	                                    <p class="fr">
	                                        <input type="text" name="" class="swText" placeholder="">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <h4 class="fl">问答排序</h4>
	                                    <p class="fr">
	                                        <input type="button" data-order="zh" value="综合" class="swChioceBut active">
	                                        <input type="button" data-order="hot" value="热门" class="swChioceBut">
	                                        <input type="button" data-order="essence" value="精华" class="swChioceBut">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <!--<h4 class="fl">上传图片</h4>-->
	                                    <p class="fr">
	                                        <input type="button" value="保存" class="swSaveBut">
	                                    </p>
	                                </li>
	                            </ul>
	                        </form>
	                    </div>
	                    <!--新闻资讯-->
	                    <div class="swConText nowClass" style="display: none;" data-id="10">
	                        <h3>新闻资讯</h3>
	                        <form action="">
	                            <ul class="swFormList">
	                                <li class="clear">
	                                    <h4 class="fl">自定义名称</h4>
	                                    <p class="fr">
	                                        <input type="text" name="" class="swText" placeholder="">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <h4 class="fl">新闻排序</h4>
	                                    <p class="fr">
	                                        <input type="button" data-order="time" value="最新" class="swChioceBut active">
	                                        <input type="button" data-order="hot" value="最热" class="swChioceBut">
	                                    </p>
	                                    <p class="fr reMark">
	                                        <span class="red">注：网校中至少包含一个推荐新闻，四个普通新闻</span>
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <!--<h4 class="fl">上传图片</h4>-->
	                                    <p class="fr">
	                                        <input type="button" value="保存" class="swSaveBut">
	                                    </p>
	                                </li>
	                            </ul>
	                        </form>
	                    </div>
	                    <!--学员心声-->
	                    <div class="swConText nowClass" style="display: none;" data-id="4">
	                        <h3>学员心声</h3>
	                        <form action="">
	                            <ul class="swFormList">
	                                <li class="clear">
	                                    <h4 class="fl">自定义名称</h4>
	                                    <p class="fr">
	                                        <input type="text" name="" class="swText" placeholder="">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <h4 class="fl">学员数量</h4>
	                                    <p class="fr">
	                                        <input type="button" data-limit="4" value="4" class="swChioceBut active">
	                                        <input type="button" data-limit="8" value="8" class="swChioceBut">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <!--<h4 class="fl">上传图片</h4>-->
	                                    <p class="fr">
	                                        <input type="button" value="保存" class="swSaveBut">
	                                    </p>
	                                </li>
	                            </ul>
	                        </form>
	                    </div>
	                     <!--最近直播-->
	                    <div class="swConText nowClass" style="display: none;" data-id="11">
	                        <h3>最近直播</h3>
	                        <form action="">
	                            <ul class="swFormList">
	                                <li class="clear">
	                                    <h4 class="fl">自定义名称</h4>
	                                    <p class="fr">
	                                        <input type="text" name="" class="swText" placeholder="">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <h4 class="fl">直播数量</h4>
	                                    <p class="fr">
	                                        <input type="button" data-limit="3" value="3" class="swChioceBut active">
	                                        <input type="button" data-limit="6" value="6" class="swChioceBut">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <!--<h4 class="fl">上传图片</h4>-->
	                                    <p class="fr">
	                                        <input type="button" value="保存" class="swSaveBut">
	                                    </p>
	                                </li>
	                            </ul>
	                        </form>
	                    </div>
	                    
	                     <!--精品微课-->
	                    <div class="swConText nowClass" style="display: none;" data-id="12">
	                        <h3>精品微课</h3>
	                        <form action="">
	                            <ul class="swFormList">
	                                <li class="clear">
	                                    <h4 class="fl">自定义名称</h4>
	                                    <p class="fr">
	                                        <input type="text" name="" class="swText" placeholder="">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <h4 class="fl">微课数量</h4>
	                                    <p class="fr">
	                                        <input type="button" data-limit="3" value="3" class="swChioceBut active">
	                                        <input type="button" data-limit="6" value="6" class="swChioceBut">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <!--<h4 class="fl">上传图片</h4>-->
	                                    <p class="fr">
	                                        <input type="button" value="保存" class="swSaveBut">
	                                    </p>
	                                </li>
	                            </ul>
	                        </form>
	                    </div>
	                    
	                      <!--专题课堂-->
	                    <div class="swConText nowClass" style="display: none;" data-id="13">
	                        <h3>专题课堂</h3>
	                        <form action="">
	                            <ul class="swFormList">
	                                <li class="clear">
	                                    <h4 class="fl">自定义名称</h4>
	                                    <p class="fr">
	                                        <input type="text" name="" class="swText" placeholder="">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <h4 class="fl">专题数量</h4>
	                                    <p class="fr">
	                                        <input type="button" data-limit="2" value="2" class="swChioceBut active">
	                                        <input type="button" data-limit="4" value="4" class="swChioceBut">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <!--<h4 class="fl">上传图片</h4>-->
	                                    <p class="fr">
	                                        <input type="button" value="保存" class="swSaveBut">
	                                    </p>
	                                </li>
	                            </ul>
	                        </form>
	                    </div>
	                    
	                      <!--名师推荐-->
	                    <div class="swConText nowClass" style="display: none;" data-id="14">
	                        <h3>名师推荐</h3>
	                        <form action="">
	                            <ul class="swFormList">
	                                <li class="clear">
	                                    <h4 class="fl">自定义名称</h4>
	                                    <p class="fr">
	                                        <input type="text" name="" class="swText" placeholder="">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <h4 class="fl">名师数量</h4>
	                                    <p class="fr">
	                                        <input type="button" data-limit="4" value="4" class="swChioceBut active">
	                                        <input type="button" data-limit="8" value="8" class="swChioceBut">
	                                    </p>
	                                </li>
	                                <li class="clear">
	                                    <!--<h4 class="fl">上传图片</h4>-->
	                                    <p class="fr">
	                                        <input type="button" value="保存" class="swSaveBut">
	                                    </p>
	                                </li>
	                            </ul>
	                        </form>
	                    </div>
	                    
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	<!--课后作业-->
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display:none">
	    <p><i></i>加载中,请稍后...</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
	<!--  ajax加载中div结束 -->
	<form action="/sysConfigIndexPageTemplate/indexPageConfig">
		<input type="hidden" value="${schoolId}"/>
	</form>
<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/system/indexPageConfig.js"></script>
</body>
</html>