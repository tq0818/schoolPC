<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<!doctype html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页页面设置</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/system.css" />
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/web-index.css" />
    <script type="text/javascript">
	$(function(){
		$selectSubMenu('netschool_body');
	});
	</script>
</head>
<body>
<!-- 二级导航 -->
<jsp:include page="/WEB-INF/jsp/menu/menu_netschool.jsp"></jsp:include>
<!-- 二级导航结束 -->

<div class="u-wrap set-system none">
    <div class="mainbackground">
        <div class="title"><h2 class="h6">模板选择</h2></div>
        <div class="set-body select-themes clear">
            <div class="box b1" data-index="b1">
                <div class="c">
                    <h5>单图课程</h5>
                </div>                
            </div>
            <div class="box b2" data-index="b2">
                <div class="c">
                    <h5>双图课程</h5>
                </div>
            </div>
            <div class="box b3" data-index="b3">
                <div class="c">
                    <h5>三图课程</h5>
                </div>
            </div>
            <div class="box b4" data-index="b4">
                <div class="c">
                    <h5>四图课程</h5>
                </div>
            </div>
            <div class="box b5" data-index="b5">
                <div class="c">
                    <h5>五图课程</h5>
                </div>
            </div>
            <div class="box b6" data-index="b6">
                <div class="c">
                    <h5>自定义内容</h5>
                </div>
            </div>
            <div class="box b7" data-index="b7">
                <div class="c">
                    <h5>新闻单图</h5>
                </div>
            </div>
            <div class="box b8" data-index="b8">
                <div class="c">
                    <h5>新闻列表</h5>
                </div>
            </div>
            <div class="box b9">
                <div class="c">
                    <h5>更多模板陆续添加中...</h5>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="u-wrap set-system">
    <div class="mainbackground bodys">
        <div class="title"><h2 class="h6">模版名称：风林火山</h2></div>
        <div class="set-body" id="set-body">
        <!-- 单图 -->
        <div class="wrap contents single gap">
            <div class="m">
                <!-- 配置蒙板 -->
                <div class="config">
                    <a href="javascript:;" class="btn btn-default btn-config">配置内容</a>
                    <a href="javascript:;" class="btn btn-default btn-remove">删除该内容</a>
                    <a href="javascript:;" class="btn btn-default btn-rank">点击排序</a>
                </div>
                <div class="direction">
                    <span class="top"><i class="iconfont">&#xe618;</i></span>
                    <span class="up"><i class="iconfont">&#xe61a;</i></span>
                    <span class="down"><i class="iconfont">&#xe617;</i></span>
                    <span class="bottom"><i class="iconfont">&#xe619;</i></span>
                </div>
            </div>
            <div class="title">
                <h2 class="h3">关于在线教育课程开课的通知</h2>            
            </div>
            <div class="g">
                <div class="picture">
                    <img src="/temp/04.png" alt="">
                </div>
                <div class="text">        
                    <p class="c">汇集全国多家知名大学里广受学生好评的计算机老师，每一门课程都由他们亲自制作，权威、专业！人人都可以在这里体验到时下最流行的 MOOC ，系统地掌握计算机专业知识。</p>
                    <p><a href="javascript:;" class="btn btn-primary btn-bigger">阅读更多</a></p>
                </div>
            </div>
        </div>
        <!-- 单图结束 -->
        <!-- 双图 -->
        <div class="wrap contents picturemsg gap">
            <div class="rows">
                <div class="title">
                    <h2 class="h5"><i></i>热门课程直播 
                    <span class="more"><a href="active">更多</a></span>
                    </h2>
                    <div class="class-menu">
                        <a href="javascript:;" class="active">视频</a>
                        <a href="javascript:;">题库</a>
                    </div>
                </div>
                <div class="t2">
                    <ul>
                        <li>
                            <p><img src="/temp/07.png" alt=""></p>
                            <h2 class="h6">
                                <a href="javascript:;">会计证财经法规与职业道德考点全解</a>
                                <span class="time"><i></i>今天 18:00-20:00</span>
                            </h2>
                        </li>
                        <li>
                            <p><img src="/temp/07.png" alt=""></p>
                            <h2 class="h6">
                                <a href="javascript:;">会计证财经法规与职业道德考点全解</a>
                                <span class="time"><i></i>今天 18:00-20:00</span>
                            </h2>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!-- 双图结束 -->
<!-- 其他 -->
<div class="wrap contents picturemsg gap">
    <div class="rows">
        <div class="title">
            <h2 class="h5"><i></i>热门课程直播 
            <span class="more"><a href="active">更多</a></span>
            </h2>
            <div class="class-menu">
                <a href="javascript:;" class="active">视频</a>
                <a href="javascript:;">题库</a>
            </div>
        </div>
        <div class="t3">
            <div class="first">
                <div class="pic">
                    <img src="/temp/10.png" alt="">
                </div>
                <div class="text">
                    <h2 class="h4">
                        <a href="javascript:;">超级人像摄影——挽留美的一瞬间</a>
                    </h2>
                    <p>很多朋友听到快门，光圈，白平衡，就觉得摄影很难学，很神秘！如果你正在寻找一个有趣有效的入门教程，那么梁山老师这个摄影课程节目就是你要的～赶紧加入我们吧！</p>
                </div>
            </div>
            <div class="sm-list">
                <ul>
                    <li>
                        <p>
                            <img src="/temp/11.png" alt="">
                        </p>
                        <div class="infos">
                            <p><a href="javascript:;">天天美食课——教你留住男人的胃</a></p>
                            <p class="time"><i></i>今天 18:00-20:00</p>
                        </div>
                    </li>
                    <li>
                        <p>
                            <img src="/temp/11.png" alt="">
                        </p>
                        <div class="infos">
                            <p><a href="javascript:;">天天美食课——教你留住男人的胃</a></p>
                            <p class="time"><i></i>今天 18:00-20:00</p>
                        </div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<div class="wrap contents picturemsg gap">
    <div class="rows">
        <div class="title">
            <h2 class="h5"><i></i>热门课程直播 
            <span class="more"><a href="active">更多</a></span>
            </h2>
            <div class="class-menu">
                <a href="javascript:;" class="active">视频</a>
                <a href="javascript:;">题库</a>
            </div>
        </div>
        <div class="t4">
            <ul>
                <li>
                    <p><img src="/temp/07.png" alt=""></p>
                    <div class="infos">
                        <h2 class="h6">
                            <a href="javascript:;">会计证财经法规与职业道德考点全解</a>       
                        </h2>
                        <p class="d"><span class="price"><i></i>￥699</span><span class="people"><i></i>390人</span></p>
                    </div>

                </li>
                <li>
                    <p><img src="/temp/07.png" alt=""></p>
                    <div class="infos">
                        <h2 class="h6">
                            <a href="javascript:;">会计证财经法规与职业道德考点全解</a>       
                        </h2>
                        <p class="d"><span class="price"><i></i>￥699</span><span class="people"><i></i>390人</span></p>
                    </div>
                </li>
                <li>
                    <p><img src="/temp/07.png" alt=""></p>
                    <div class="infos">
                        <h2 class="h6">
                            <a href="javascript:;">会计证财经法规与职业道德考点全解</a>       
                        </h2>
                        <p class="d"><span class="price"><i></i>￥699</span><span class="people"><i></i>390人</span></p>
                    </div>
                </li>
                <li>
                    <p><img src="/temp/07.png" alt=""></p>
                    <div class="infos">
                        <h2 class="h6">
                            <a href="javascript:;">会计证财经法规与职业道德考点全解</a>       
                        </h2>
                        <p class="d"><span class="price"><i></i>￥699</span><span class="people"><i></i>390人</span></p>
                    </div>
                </li>
            </ul>
        </div>
    </div>
</div>

<div class="wrap contents picturemsg gap">
    <div class="rows">
        <div class="title">
            <h2 class="h5"><i></i>热门课程直播 
            <span class="more"><a href="active">更多</a></span>
            </h2>
            <div class="class-menu">
                <a href="javascript:;" class="active">视频</a>
                <a href="javascript:;">题库</a>
            </div>
        </div>
        <div class="t5">
            <div class="first">
                <div class="pic">
                    <img src="/temp/08.png" alt="">
                </div>
                <div class="text">
                    <h2 class="h4">时间管理学——洞察隐藏在时间里的学问</h2>
                    <p>在这个高节奏的社会，事情越来越多，能自由支配的时间越来越少、越来越碎，想花个一大段时间做点自己的事情，却总是会被各种各样的琐事打断，想好要花一个小时写份文案，却在毫无头绪的情况下莫名其妙地上淘宝刷微博，结果三个小时过去了，可是文案却还没有开头。</p>
                </div>
            </div>
            <div class="sm-list">
                <ul>
                    <li>
                        <p>
                            <img src="/temp/07.png" alt="">
                        </p>
                        <p><a href="javascript:;">行为面试技巧——让你的工作不再难</a></p>
                    </li>
                    <li>
                        <p>
                            <img src="/temp/07.png" alt="">
                        </p>
                        <p><a href="javascript:;">行为面试技巧——让你的工作不再难</a></p>
                    </li>
                    <li>
                        <p>
                            <img src="/temp/07.png" alt="">
                        </p>
                        <p><a href="javascript:;">行为面试技巧——让你的工作不再难</a></p>
                    </li>
                    <li>
                        <p>
                            <img src="/temp/07.png" alt="">
                        </p>
                        <p><a href="javascript:;">行为面试技巧——让你的工作不再难</a></p>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="wrap contents news gap">
    <div class="rows">
        <div class="title">
            <h2 class="h6">新闻公告 <span class="more"><a href="javascript:;">更多</a></span></h2>
        </div>
        <div class="list">
            <ul>
                <li><i></i><a href="javascript:;">行行为面试技巧——让你的工作不再为面试技巧——让你的工作不再难...</a></li>
                <li><i></i><a href="javascript:;">行行为面试技巧——让你的工作不再为面试技巧——让你的工作不再难...</a></li>
                <li><i></i><a href="javascript:;">行行为面试技巧——让你的工作不再为面试技巧——让你的工作不再难...</a></li>
                <li><i></i><a href="javascript:;">行行为面试技巧——让你的工作不再为面试技巧——让你的工作不再难...</a></li>
                <li><i></i><a href="javascript:;">行行为面试技巧——让你的工作不再为面试技巧——让你的工作不再难...</a></li>
                <li><i></i><a href="javascript:;">行行为面试技巧——让你的工作不再为面试技巧——让你的工作不再难...</a></li>
                <li><i></i><a href="javascript:;">行行为面试技巧——让你的工作不再为面试技巧——让你的工作不再难...</a></li>
                <li><i></i><a href="javascript:;">行行为面试技巧——让你的工作不再为面试技巧——让你的工作不再难...</a></li>
                <li><i></i><a href="javascript:;">行行为面试技巧——让你的工作不再为面试技巧——让你的工作不再难...</a></li>
                <li><i></i><a href="javascript:;">行行为面试技巧——让你的工作不再为面试技巧——让你的工作不再难...</a></li>
                <li><i></i><a href="javascript:;">行行为面试技巧——让你的工作不再为面试技巧——让你的工作不再难...</a></li>
                <li><i></i><a href="javascript:;">行行为面试技巧——让你的工作不再为面试技巧——让你的工作不再难...</a></li>
            </ul>
        </div>
    </div>
</div>
<!-- 其他结束 -->
        </div>
    </div>
</div>
<!-- 排序蒙板 -->
<div class="rank-mark">
    <div class="title" id="rank-title">
        <h2 class="h6">点击关闭</h2>
    </div>
    <ul class="rank-list" id="rank-list">
        <li>学科一</li>
        <li>学科二</li>
        <li>学科三</li>
        <li>学科四</li>
        <li>学科五</li>
    </ul>
</div>
<!-- 排序蒙板结束 -->
<!-- 配置模板 -->
<!-- 配置信息模板 -->
<div class="box-config none">
    <div class="title">配置信息</div>
    <div class="main-config">
        <p><span class="pro-name">名称</span><input type="text" class="in-name" value="测试的名称"></p>
        <p>
            <span class="pro-name">学科</span>
            <a href="javascript:;" class="btn btn-mini btn-primary btn-pro">人力</a>
            <a href="javascript:;" class="btn btn-mini btn-default btn-pro">心理</a>
            <a href="javascript:;" class="btn btn-mini btn-default btn-pro">自考</a>
        </p>
        <p>
            <span class="pro-name">是否显示学科小类</span>
            <span class="switch open"><i class="iconfont normal">&#xe603;</i><em>显示</em></span>
        </p>
        <p>
            <span class="pro-name">展示顺序</span>
            <span class="rank">
                <span class="checkbox open random"><i class="iconfont">&#xe60b;</i><em>随机</em></span>
                <span class="checkbox"><i class="iconfont">&#xe60c;</i><em>时间</em></span>
                <span class="checkbox"><i class="iconfont">&#xe60c;</i><em>价格</em></span>
            </span>
            <span class="rank none">
                <span class="checkbox"><i class="iconfont">&#xe60c;</i><em>正序</em></span>
                <span class="checkbox"><i class="iconfont">&#xe60c;</i><em>倒序</em></span>
            </span>
        </p>
        <p>
            <span class="pro-name"></span>
            <a href="javascript:;" class="btn btn-primary">保存配置</a>
        </p> 
    </div>
</div>
<!-- 配置信息结束 -->
<!-- 配置信息模板 -->
<div class="box-config none">
    <div class="title">配置信息</div>
    <div class="main-config">
        <p><span class="pro-name">名称</span><input type="text" class="in-name" value="测试的名称"></p>
        <p>
            <span class="pro-name">学科</span>
            <a href="javascript:;" class="btn btn-mini btn-primary btn-pro">人力</a>
            <a href="javascript:;" class="btn btn-mini btn-default btn-pro">心理</a>
            <a href="javascript:;" class="btn btn-mini btn-default btn-pro">自考</a>
        </p>
        <p>
            <span class="pro-name">学科小类</span>
            <span class="second">
                <a href="javascript:;" class="btn btn-mini btn-default btn-pro">人力</a>
                <a href="javascript:;" class="btn btn-mini btn-default btn-pro">心理</a>
                <a href="javascript:;" class="btn btn-mini btn-default btn-pro">自考</a>
            </span>
        </p>
        <p>
            <span class="pro-name">学科</span>
            <a href="javascript:;" class="btn btn-mini btn-primary btn-pro">人力</a>
            <a href="javascript:;" class="btn btn-mini btn-default btn-pro">心理</a>
            <a href="javascript:;" class="btn btn-mini btn-default btn-pro">自考</a>
        </p>
        <p>
            <span class="pro-name">学科小类</span>
            <span class="second">
                <a href="javascript:;" class="btn btn-mini btn-default btn-pro">人力</a>
                <a href="javascript:;" class="btn btn-mini btn-default btn-pro">心理</a>
                <a href="javascript:;" class="btn btn-mini btn-default btn-pro">自考</a>
            </span>
        </p>
        <p>
            <span class="pro-name"></span>
            <a href="javascript:;" class="btn btn-primary">保存配置</a>
        </p>            
    </div>
</div>
<!-- 配置信息结束 -->
<!-- 配置信息模板 -->
<div class="box-config none">
    <div class="title">配置信息</div>
    <div class="main-config">
        <p><span class="pro-name">名称</span><input type="text" class="in-name" value="测试的名称"></p>
        <p>
            <span class="pro-name t-top">文案</span>
            <span class="textarea">
                <textarea name="" ></textarea>
            </span>
        </p>
        <p>
            <span class="pro-name">图片</span>
            <span class="up-pic">
                <a href="javascript:;" class="btn btn-default btn-sm btn-up">上传图片</a>
                <input type="file" class="up-btn" accept="image/*">
            </span>
        </p>
        <p>
            <span class="pro-name">链接地址</span>
            <span class="second">
                <input type="text" value="http://www.baidu.com">
            </span>
        </p>
        <p>
            <span class="pro-name"></span>
            <a href="javascript:;" class="btn btn-primary">保存配置</a>
        </p>     
    </div>
</div>
<!-- 配置信息结束 -->
<!-- 配置信息模板 -->
<div class="box-config none">
    <div class="title">配置信息</div>
    <div class="main-config">
        <p><span class="pro-name">名称</span><input type="text" class="in-name" value="测试的名称"></p>
        <p>
            <span class="pro-name">展示顺序</span>
            <span class="rank">
                <span class="checkbox open random"><i class="iconfont">&#xe60b;</i><em>随机</em></span>
                <span class="checkbox"><i class="iconfont">&#xe60c;</i><em>时间</em></span>
                <span class="checkbox"><i class="iconfont">&#xe60c;</i><em>价格</em></span>
            </span>
            <span class="rank none">
                <span class="checkbox"><i class="iconfont">&#xe60c;</i><em>正序</em></span>
                <span class="checkbox"><i class="iconfont">&#xe60c;</i><em>倒序</em></span>
            </span>
        </p>
        <p>
            <span class="pro-name"></span>
            <a href="javascript:;" class="btn btn-primary">保存配置</a>
        </p> 
    </div>
</div>
<!-- 配置信息结束 -->
<script type="text/javascript" src="<%=rootPath%>/javascripts/sys-body.js" ></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/jquery.scrollTo-2.1.0/jquery.scrollTo.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/system/addSysBody.js?v=1.0"></script>
</body>
</html>