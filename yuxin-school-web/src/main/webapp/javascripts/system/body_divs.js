/**
 * 约定的class名称
 * .name  名称
 * .text  描述
 * .pic   图片
 * .url   链接
 * .btns  按钮
 * .itemOne 学科
 * .itemSecond 学科小类
 * .classType 课程
 * .showItem 是否显示学科小类
 * .price  按价格排序
 * .time 按时间排序
 * .num  按学员数量排序
 * .sortBy  排序
 * .recommend 推荐优先
 * 
 */
//=============================================================
var $divs=[];//存储所有div对象

//===============单图课程================
var $div1=
//	'<div class="model div1" modelId="1">'+
//	'<div class="wrap contents single gap">'+
//'<div class="title">'+
//    '<h2 class="h3 _title">关于在线教育课程开课的通知</h2>            '+
//'</div>'+
//'<div class="g _item">'+
//    '<div class="picture">'+
//        '<img class="_img" src="../images/overview_demo.jpg" alt="" style="width:100%;">'+
//    '</div>'+
//    '<div class="text">        '+
//        '<p class="c _caption">汇集全国多家知名大学里广受学生好评的计算机老师，每一门课程都由他们亲自制作，权威、专业！人人都可以在这里体验到时下最流行的 MOOC ，系统地掌握计算机专业知识。</p>'+
//        '<p><a href="javascript:;" class="btn btn-primary btn-bigger">查看详情</a></p>'+
//    '</div>'+
//'</div>'+
//'</div>'+
//'<div class="box-config none">'+
//'<div class="title">配置信息</div>'+
//'<div class="main-config">'+
//    '<p class="name"><span class="pro-name">名称</span><input type="text" class="in-name" value=""></p>'+
//    '<p class="itemOne"><span class="pro-name">学科</span></p>'+
//    '<p class="itemSecond"><span class="pro-name">学科小类</span></p>'+
//    '<p class="classType"><span class="pro-name">课程</span></p>'+
//    '<p class="btns">'+
//        '<span class="pro-name"></span>'+
//        '<a href="javascript:;" class="btn btn-primary">保存配置</a>'+
//    '</p>'+
//'</div>'+
//'</div>'+
//'</div>';

//'<div class="wrap contents picturemsg single singleImg gap">'+
//	'<div class="t1 g _body">'+
//		'<ul>'+
//		    '<li class="_item clear">'+
//		        '<div class="picture _pic">'+
//		            '<img class="_img" src="../images/overview_demo.jpg" alt="" style="cursor: pointer;">'+
//		        '</div>'+
//		        '<div class="singleText">'+
//		            '<h2 class="textTitle">关于在线教育课程开课的通知</h2>'+
//		            '<p class="t1_Price">'+
//		                '<em>￥123</em>'+
//		                '<del>￥123</del>'+
//		            '</p>'+
//		            '<p class="t1_Con">汇集全国多家知名大学里广受学生好评的计算机老师，每一门课程都由他们亲自制作，权威、专业！人人都可以在这里体验到时下最流行的 MOOC ，系统地掌握计算机专业知识。</p>'+
//		            '<div class="clear">'+
//		                '<h4 class="fl">最新加入学习</h4>'+
//		                '<p class="fl">'+
//		                    '<span class="userTitle userTitle1"></span>'+
//		                    '<b>+40</b>'+
//		                '</p>'+
//		                '<button class="fr">开始学习</button>'+
//		            '</div>'+
//		        '</div>'+
//		        '<strong class="vipImg none"></strong>'+
//		    '</li>'+
//		'</ul>'+
//	'</div>'+
//'</div>';
'<div class="model div1" modelId="1">'+
	'<div class="wrap contents picturemsg single singleImg gap">'+
	    '<div class="t1 g _body">'+
	        '<ul>'+
	            '<li class="_item clear">'+
	                '<div class="picture _pic">'+
	                    '<img class="_img" src="../images/overview_demo.jpg" alt="" style="cursor: pointer;">'+
	                '</div>'+
	                '<div class="singleText">'+
	                    '<h2 class="textTitle">'+
	'                            大学英语六级基础全项突破班'+
	                        '</h2>'+
	                    '<p class="t1_Price">'+
	                        '<em>￥120.00</em>'+
	                        '<del>￥120.00</del>'+
	                    '</p>'+
	                    '<p class="t1_Con">'+
	                        '初三电学是重点也是难点 '+'怎么能学好电学，关系到整个初三的成绩和中考成绩。这里控制60个字符...初三电学是重点也是难点 '+'怎么能学好电学，关系到整个初三的成绩和中考成绩。'+
	                    '</p>'+
	                    '<div class="clear">'+
	                        '<h4 class="fl">最新加入学习</h4>'+
	                        '<p class="fl">'+
	                            '<span class="userTitle userTitle1">'+
	                                '<strong style="background-position: -268.667px -43px;"></strong><strong style="background-position: -155.667px -81px;"></strong><strong style="background-position: -5px -43px;"></strong><strong style="background-position: -344px -81px;"></strong></span>'+
	                            '<b>+40</b>'+
	                        '</p>'+
	                        '<button class="fr">开始学习</button>'+
	                    '</div>'+
	                '</div>'+
	                '<strong class="vipImg none"></strong>'+
	            '</li>'+
	        '</ul>'+
	    '</div>'+
	'</div>'+
	'<div class="box-config none">'+
		'<div class="title">配置信息</div>'+
		'<div class="main-config">'+
		    '<p class="name"><span class="pro-name">名称</span><input type="text" class="in-name" value=""></p>'+
		    '<p class="itemOne"><span class="pro-name">学科</span></p>'+
		    '<p class="itemSecond"><span class="pro-name">学科小类</span></p>'+
		    '<p class="classType"><span class="pro-name">课程</span></p>'+
		    '<p class="btns">'+
		        '<span class="pro-name"></span>'+
		        '<a href="javascript:;" class="btn btn-primary">保存配置</a>'+
		    '</p>'+
		'</div>'+
	'</div>'+
'</div>';
//===============两图课程================
var $div2=
//	'<div class="model div2" modelId="2">'+
//	'<div class="wrap contents picturemsg gap">'+
//    '<div class="rows">'+
//'<div class="title">'+
//    '<h2 class="h5 _title"><i></i>热门课程'+ 
//    '<span class="more"><a href="active">更多</a></span>'+
//    '</h2>'+
//'</div>'+
//'<div class="t2">'+
//    '<ul>'+
//       ' <li class="_item">'+
//            '<p><img class="_img" src="../images/overview_demo.jpg" alt=""></p>'+
//           '<h2 class="h6">'+
//               ' <a href="javascript:;" class="_caption">会计证财经法规与职业道德考点全解</a>'+
//               '<span class="_time"><i></i>今天 18:00-20:00</span>'+
//            '</h2>'+
//        '</li>'+
//        '<li class="_item">'+
//            '<p><img class="_img" src="../images/overview_demo.jpg" alt=""></p>'+
//            '<h2 class="h6">'+
//                '<a href="javascript:;" class="_caption">会计证财经法规与职业道德考点全解</a>'+
//                '<span class="_time"><i></i>今天 18:00-20:00</span>'+
//            '</h2>'+
//        '</li>'+
//    '</ul>'+
//'</div>'+
//'</div>'+
//'</div>'+
//'<div class="box-config none">'+
//'<div class="title">配置信息</div>'+
//'<div class="main-config">'+
//    '<p class="name"><span class="pro-name">名称</span><input type="text" class="in-name" value="热门课程"></p>'+
//    '<p class="itemOne"><span class="pro-name">学科</span></p>'+
//    '<p class="itemSecond"><span class="pro-name">学科小类</span></p>'+
//    '<p class="classType"><span class="pro-name">课程</span></p>'+
//    '<p class="btns">'+
//        '<span class="pro-name"></span>'+
//        '<a href="javascript:;" class="btn btn-primary">保存配置</a>'+
//    '</p>            '+
//'</div>'+
//'</div>'+
//'</div>';
'<div class="model div2" modelId="2">'+
	'<div class="wrap contents picturemsg gap">'+
	    '<div class="rows">'+
	        '<div class="homeListTitle clear">'+
	            '<h5>二图课程</h5>'+
	            '<ul>'+
	            '</ul>'+
	            '<a href="javascript:;" class="homeMoreBtn">'+
	                '<span>更多</span>&gt;'+
	            '</a>'+
	        '</div>'+
	        '<div class="t2 _body">'+
	            '<ul _class="clear">'+
	                '<li class="_item fl">'+
	                    '<div class="_itemT2Box">'+
	                        '<p class="pic">'+
	                            '<img class="_img" src="../images/overview_demo.jpg" alt="" id="img_0_0" style="cursor: pointer;">'+
	                        '</p>'+
	                        '<div class="picContent">'+
	                            '<h4>PPT从菜鸟到高手实用教程</h4>'+
	                            '<p class="picDet">学习过此课程的同学最多显示四个，人数大于或者等于4个时，人的头像后面不显示人数，人数超出4个后在头像后面显示人数</p>'+
	                            '<p class="clear">'+
	                                '<span class="fl">'+
	                                    '<em>￥199.00</em>'+
	                                    '<del>￥199.00</del>'+
	                                '</span>'+
	                                '<span class="fr">'+
	                                    '<b>最新加入学习</b>'+
	                                    '<span class="userTitle userTitle2">'+
	                                    '<strong style="background-position: -118px -81px;"></strong><strong style="background-position: -457px -43px;"></strong><strong style="background-position: -457px -5px;"></strong></span>'+
	                                '</span>'+
	                            '</p>'+
	                        '</div>'+
	                        '<strong class="vipImg none"></strong>'+
	                    '</div>'+
	                '</li>'+
	                '<li class="_item fr">'+
	                    '<div class="_itemT2Box">'+
	                        '<p class="pic">'+
	                            '<img class="_img" src="../images/overview_demo.jpg" alt="" id="img_0_0" style="cursor: pointer;">'+
	                        '</p>'+
	                        '<div class="picContent">'+
	                            '<h4>PPT从菜鸟到高手实用教程</h4>'+
	                            '<p class="picDet">学习过此课程的同学最多显示四个，人数大于或者等于4个时，人的头像后面不显示人数，人数超出4个后在头像后面显示人数</p>'+
	                            '<p class="clear">'+
	                                '<span class="deFree fl">免费</span>'+
	                                '<span class="fr">'+
	                                    '<b>最新加入学习</b>'+
	                                    '<span class="userTitle userTitle3">'+
	                                    '<strong style="background-position: -80.3333px -5px;"></strong><strong style="background-position: -155.667px -43px;"></strong><strong style="background-position: -118px -5px;"></strong></span>'+
	                                '</span>'+
	                            '</p>'+
	                        '</div>'+
	                        '<strong class="vipImg none"></strong>'+
	                    '</div>'+
	                '</li>'+
	
	            '</ul>'+
	        '</div>'+
	    '</div>'+
	'</div>'+
	'<div class="box-config none">'+
		'<div class="title">配置信息</div>'+
		'<div class="main-config">'+
		    '<p class="name"><span class="pro-name">名称</span><input type="text" class="in-name" value="热门课程"></p>'+
		    '<p class="itemOne"><span class="pro-name">学科</span></p>'+
		    '<p class="itemSecond"><span class="pro-name">学科小类</span></p>'+
		    '<p class="classType"><span class="pro-name">课程</span></p>'+
		    '<p class="btns">'+
		        '<span class="pro-name"></span>'+
		        '<a href="javascript:;" class="btn btn-primary">保存配置</a>'+
		    '</p>            '+
		'</div>'+
	'</div>'+
'</div>';




//==================三图课程==================
var $div3=
//	'<div class="model div3" modelId="3">'+
//	'<div class="wrap contents picturemsg gap">'+
//'<div class="rows">'+
//'<div class="title">'+
//    '<h2 class="h5 _title"><i></i>热门课程 '+
//    '<span class="more"><a href="active">更多</a></span>'+
//    '</h2>'+
//    '<div class="class-menu">'+
//        '<a href="javascript:;" class="active">会计</a>'+
//        '<a href="javascript:;">人力</a>'+
//    '</div>'+
//'</div>'+
//'<div class="t3">'+
//    '<div class="first _item">'+
//        '<div class="pic">'+
//            '<img class="_img" src="../images/overview_demo.jpg" alt="">'+
//        '</div>'+
//        '<div class="text">'+
//            '<h2 class="h4">'+
//                '<a href="javascript:;" class="_caption">超级人像摄影——挽留美的一瞬间</a>'+
//            '</h2>'+
//            '<p class="_overview">很多朋友听到快门，光圈，白平衡，就觉得摄影很难学，很神秘！如果你正在寻找一个有趣有效的入门教程，那么梁山老师这个摄影课程节目就是你要的～赶紧加入我们吧！</p>'+
//        '</div>'+
//    '</div>'+
//    '<div class="sm-list">'+
//    '<ul>'+
//    '<li class="_item">'+
//       ' <p class="pic">'+
//           ' <img class="_img" src="../images/overview_demo.jpg" alt="">'+
//        '</p>'+
//       ' <div class="infos _text">'+
//          '  <p ><a class="_caption" href="javascript:;"></a></p>'+
//           ' <p class="_overview"></p>'+
//        '</div>'+
//    '</li>'+
//    '<li class="_item">'+
//       ' <p class="pic">'+
//           ' <img class="_img" src="../images/overview_demo.jpg" alt="">'+
//        '</p>'+
//      '  <div class="infos _text">'+
//         '   <p><a  class="_caption" href="javascript:;"></a></p>'+
//           ' <p class="_overview"></p>'+
//       ' </div>'+
//    '</li>'+
//'</ul>'+
//    '</div>'+
//'</div>'+
//'</div>'+
//'</div>'+
//'<div class="box-config none">'+
//'<div class="title">配置信息</div>'+
//'<div class="main-config">'+
//    '<p class="name"><span class="pro-name">名称</span><input type="text" class="in-name" value="热门课程"></p>'+
//    '<p class="itemOne"><span class="pro-name">学科</span></p>'+
//    '<p class="showItem">'+
//        '<span class="pro-name">是否显示学科小类</span>'+
//        '<span class="switch open"><i class="iconfont normal"></i><em>显示</em></span>'+
//    '</p>'+
//    '<p>'+
//	'<span class="pro-name">授课类型</span>'+
//	'<span class="teachType"><a href="javascript:;" class="btn btn-mini btn-default all">全部</a><a href="javascript:;" class="btn btn-mini btn-default live">直播</a><a href="javascript:;" class="btn btn-mini btn-default video">录播</a><a href="javascript:;" class="btn btn-mini btn-default face">面授</a></span>'+
//	'</p>'+
//    '<p class="sortBy">'+
//        '<span class="pro-name">展示顺序</span>'+
//        '<span class="rank">'+
//            '<span class="checkbox num"><i class="iconfont"></i><em>学员数量</em></span>'+
//            '<span class="checkbox time"><i class="iconfont"></i><em>时间</em></span>'+
//            '<span class="checkbox price"><i class="iconfont"></i><em>价格</em></span>'+
//        '</span>'+
//        '<span class="recommend radio open"><i class="iconfont">&#xe60a;</i><em>推荐优先</em></span>'+
//    '</p>'+
//    '<p class="btns">'+
//        '<span class="pro-name"></span>'+
//        '<a href="javascript:;" class="btn btn-primary">保存配置</a>'+
//    '</p> '+
//'</div>'+
//'</div>'+
//'</div>';
'<div class="model div3" modelId="3">'+	
	'<div class="wrap contents picturemsg gap">'+
	    '<div class="rows">'+
	        '<div class="homeListTitle clear">'+
	            '<h5>三图课程</h5>'+
	            '<ul>'+
	            '</ul>'+
	            '<a href="javascript:;" class="homeMoreBtn">'+
	                '<span>更多</span>&gt;'+
	            '</a>'+
	        '</div>'+
	        '<div class="t3 _body">'+
	            '<ul style="width: auto;">'+
	                '<li class="_item">'+
	                    '<p class="pic">'+
	                        '<span class="tips mf"></span>'+
	                        '<img class="_img" src="../images/overview_demo.jpg" alt="">'+
	                    '</p>'+
	                    '<div class="infos">'+
	                        '<h2 class="h6">'+
	                            '<a class="_caption" href="javascript:;" id="caption_1_0" title="如何创建直播课程">如何创建直播课程</a>'+
	                        '</h2>'+
	                        '<p class="t3C _overview">'+'当我们在谈论从0到1设计一款APP时，聊得最多的往往是平台规范和设计方法。师至今未还要和这些内容进行交互。</p>'+
	                        '<p class="d clear">'+
	                            '<span class="price _price free fl">'+
	                               '<em>￥199.00</em>'+
	                                '<del>￥199.00</del>'+
	                            '</span>'+
	                            '<span class="people _people fr">'+
	                                '<i>&#xe6e6;</i>200人学习过'+
	                            '</span>'+
	                        '</p>'+
	                    '</div>'+
	                    '<strong class="vipImg none"></strong>'+
	                '</li>'+
	                '<li class="_item">'+
	                    '<p class="pic">'+
	                        '<span class="tips mf"></span>'+
	                        '<img class="_img" src="../images/overview_demo.jpg" alt="">'+
	                    '</p>'+
	                    '<div class="infos">'+
	                        '<h2 class="h6">'+
	                            '<a class="_caption" href="javascript:;" id="caption_1_0" title="如何创建直播课程">如何创建直播课程</a>'+
	                        '</h2>'+
	                        '<p class="t3C _overview">'+'当我们在谈论从0到1设计一款APP时，聊得最多的往往是平台规范和设计方法。师至今未还要和这些内容进行交互。</p>'+
	                        '<p class="d clear">'+
	                            '<span class="price _price free fl">免费</span>'+
	                            '<span class="people _people fr">'+
	                                '<i class="iconfont">&#xe6e6;</i>200人学习过'+
	                            '</span>'+
	                        '</p>'+
	                    '</div>'+
	                    '<strong class="vipImg none"></strong>'+
	                '</li>'+
	                '<li class="_item last">'+
	                    '<p class="pic">'+
	                        '<span class="tips mf"></span>'+
	                        '<img class="_img" src="../images/overview_demo.jpg" alt="">'+
	                    '</p>'+
	                    '<div class="infos">'+
	                        '<h2 class="h6">'+
	                            '<a class="_caption" href="javascript:;" id="caption_1_0" title="如何创建直播课程">如何创建直播课程</a>'+
	                        '</h2>'+
	                        '<p class="t3C _overview">'+'当我们在谈论从0到1设计一款APP时，聊得最多的往往是平台规范和设计方法。师至今未还要和这些内容进行交互。</p>'+
	                        '<p class="d clear">'+
	                            '<span class="price _price free fl">免费</span>'+
	                            '<span class="people _people fr">'+
	                                '<i>&#xe6e6;</i>200人学习过'+
	                            '</span>'+
	                        '</p>'+
	                    '</div>'+
	                    '<strong class="vipImg none"></strong>'+
	                '</li>'+
	            '</ul>'+
	        '</div>'+
	    '</div>'+
	'</div>'+	
	'<div class="box-config none">'+
		'<div class="title">配置信息</div>'+
		'<div class="main-config">'+
		    '<p class="name"><span class="pro-name">名称</span><input type="text" class="in-name" value="热门课程"></p>'+
		    '<p class="itemOne"><span class="pro-name">学科</span></p>'+
		    '<p class="showItem">'+
		        '<span class="pro-name">是否显示学科小类</span>'+
		        '<span class="switch open"><i class="iconfont normal"></i><em>显示</em></span>'+
		    '</p>'+
		    '<p>'+
			'<span class="pro-name">授课类型</span>'+
			'<span class="teachType"><a href="javascript:;" class="btn btn-mini btn-default all">全部</a><a href="javascript:;" class="btn btn-mini btn-default live">直播</a><a href="javascript:;" class="btn btn-mini btn-default video">录播</a><a href="javascript:;" class="btn btn-mini btn-default face">面授</a></span>'+
			'</p>'+
		    '<p class="sortBy">'+
		        '<span class="pro-name">展示顺序</span>'+
		        '<span class="rank">'+
		            '<span class="checkbox num"><i class="iconfont"></i><em>学员数量</em></span>'+
		            '<span class="checkbox time"><i class="iconfont"></i><em>时间</em></span>'+
		            '<span class="checkbox price"><i class="iconfont"></i><em>价格</em></span>'+
		        '</span>'+
		        '<span class="recommend radio open"><i class="iconfont">&#xe60a;</i><em>推荐优先</em></span>'+
		    '</p>'+
		    '<p class="btns">'+
		        '<span class="pro-name"></span>'+
		        '<a href="javascript:;" class="btn btn-primary">保存配置</a>'+
		    '</p> '+
		'</div>'+
	'</div>'+
'</div>';	

//================四图课程==================
var $div4=
//	'<div class="model div4" modelId="4">'+
//	'<div class="wrap contents picturemsg gap">'+
//'<div class="rows">'+
//'<div class="title">'+
//    '<h2 class="h5 _title"><i></i>热门课程 '+
//    '<span class="more"><a href="active">更多</a></span>'+
//    '</h2>'+
//    '<div class="class-menu">'+
//        '<a href="javascript:;" class="active">会计</a>'+
//        '<a href="javascript:;">人力</a>'+
//    '</div>'+
//'</div>'+
//'<div class="t4">'+
//    '<ul>'+
//        '<li class="_item">'+
//            '<p><img class="_img" src="../images/overview_demo.jpg" alt=""></p>'+
//            '<div class="infos">'+
//                '<h2 class="h6">'+
//                    '<a class="_caption" href="javascript:;">会计证财经法规与职业道德考点全解</a>       '+
//                '</h2>'+
//                '<p class="d"><span class="price _price"><i></i></span><span class="people _people"><i></i></span></p>'+
//            '</div>'+
//        '</li>'+
//        '<li class="_item">'+
//            '<p><img class="_img" src="../images/overview_demo.jpg" alt=""></p>'+
//            '<div class="infos">'+
//                '<h2 class="h6">'+
//                    '<a class="_caption" href="javascript:;">会计证财经法规与职业道德考点全解</a>       '+
//                '</h2>'+
//                '<p class="d"><span class="price _price"><i></i></span><span class="people _people"><i></i></span></p>'+
//            '</div>'+
//        '</li>'+
//        '<li class="_item">'+
//            '<p><img class="_img" src="../images/overview_demo.jpg" alt=""></p>'+
//            '<div class="infos">'+
//                '<h2 class="h6">'+
//                    '<a  class="_caption" href="javascript:;">会计证财经法规与职业道德考点全解</a>       '+
//                '</h2>'+
//                '<p class="d"><span class="price _price"><i></i></span><span class="people _people"><i></i></span></p>'+
//            '</div>'+
//        '</li>'+
//        '<li class="_item">'+
//            '<p><img class="_img" src="../images/overview_demo.jpg" alt=""></p>'+
//            '<div class="infos">'+
//                '<h2 class="h6">'+
//                    '<a class="_caption" href="javascript:;">会计证财经法规与职业道德考点全解</a>       '+
//                '</h2>'+
//                '<p class="d"><span class="price _price"><i></i></span><span class="people _people"><i></i></span></p>'+
//            '</div>'+
//        '</li>'+
//    '</ul>'+
//'</div>'+
//'</div>'+
//'</div>'+
//'<div class="box-config none">'+
//'<div class="title">配置信息</div>'+
//'<div class="main-config">'+
//    '<p class="name"><span class="pro-name">名称</span><input type="text" class="in-name" value="热门课程"></p>'+
//    '<p class="itemOne">'+
//        '<span class="pro-name">学科</span>'+
//    '</p>'+
//    '<p class="showItem">'+
//        '<span class="pro-name">是否显示学科小类</span>'+
//        '<span class="switch open"><i class="iconfont normal"></i><em>显示</em></span>'+
//    '</p>'+
//    '<p>'+
//	'<span class="pro-name">授课类型</span>'+
//	'<span class="teachType"><a href="javascript:;" class="btn btn-mini btn-default all">全部</a><a href="javascript:;" class="btn btn-mini btn-default live">直播</a><a href="javascript:;" class="btn btn-mini btn-default video">录播</a><a href="javascript:;" class="btn btn-mini btn-default face">面授</a></span>'+
//	'</p>'+
//    '<p class="sortBy">'+
//        '<span class="pro-name">展示顺序</span>'+
//        '<span class="rank">'+
//            '<span class="checkbox num"><i class="iconfont"></i><em>学员数量</em></span>'+
//            '<span class="checkbox time"><i class="iconfont"></i><em>时间</em></span>'+
//            '<span class="checkbox price"><i class="iconfont"></i><em>价格</em></span>'+
//        '</span>'+
//        '<span class="radio recommend open"><i class="iconfont">&#xe60a;</i><em>推荐优先</em></span>'+
//    '</p>'+
//    '<p class="btns">'+
//        '<span class="pro-name"></span>'+
//        '<a href="javascript:;" class="btn btn-primary">保存配置</a>'+
//    '</p> '+
//'</div>'+
//'</div>'+
//'</div>';
'<div class="model div4" modelId="4">'+	
	'<div class="wrap contents picturemsg gap">'+
	    '<div class="rows">'+
	        '<div class="homeListTitle clear">'+
	            '<h5>四图课程</h5>'+
	            '<ul>'+
	            '</ul>'+
	            '<a href="javascript:;" class="homeMoreBtn">'+
	                '<span>更多</span>&gt;'+
	            '</a>'+
	        '</div>'+
	        '<div class="homet4">'+
	            '<ul class="clear T4List">'+
	                '<li class="_item">'+
	                    '<div class="borderCon">'+
	                        '<img  class="_img" src="../images/overview_demo.jpg" alt="">'+
	                    '</div>'+
	                    '<div class="t4Con">'+
	                        '<h4 class="_caption">这里是课程名称这里是课程名称这里可以显示两排文字，最多30个字符...</h4>'+
	                        '<p class="d clear">'+
	                            '<span class="price _price free fl">免费</span>'+
	                            '<span class="people _people fr">'+
                                    '<i class="iconfont">&#xe6e6;</i>'+
                                    '<em class="ml10">223</em>'+
                                '</span>'+
	                        '</p>'+
	                    '</div>'+
	                    '<strong class="vipImg none"></strong>'+
	                '</li>'+
	                '<li class="_item">'+
	                    '<div class="borderCon">'+
	                        '<img  class="_img" src="../images/overview_demo.jpg" alt="">'+
	                    '</div>'+
	                    '<div class="t4Con">'+
	                        '<h4 class="_caption">这里是课程名称这里是课程名称这里可以显示两排文字，最多30个字符...</h4>'+
	                        '<p class="d clear">'+
		                        '<span class="price _price free fl">'+
		                            '<em>￥199.00</em>'+
		                             '<del>￥199.00</del>'+
		                         '</span>'+
	                            '<span class="people _people fr">'+
	                                '<i class="iconfont">&#xe6e6;</i>'+
	                                '<em class="ml10">223</em>'+
	                            '</span>'+
	                        '</p>'+
	                    '</div>'+
	                    '<strong class="vipImg none"></strong>'+
	                '</li>'+
	                '<li class="_item">'+
	                    '<div class="borderCon">'+
	                        '<img  class="_img" src="../images/overview_demo.jpg" alt="">'+
	                    '</div>'+
	                    '<div class="t4Con">'+
	                        '<h4 class="_caption">这里是课程名称这里是课程名称这里可以显示两排文字，最多30个字符...</h4>'+
	                        '<p class="d clear">'+
		                        '<span class="price _price free fl">'+
		                            '<em>￥199.00</em>'+
		                             '<del>￥199.00</del>'+
		                         '</span>'+
	                            '<span class="people _people fr">'+
	                                '<i class="iconfont">&#xe6e6;</i>'+
	                                '<em class="ml10">223</em>'+
	                            '</span>'+
	                        '</p>'+
	                    '</div>'+
	                    '<strong class="vipImg none"></strong>'+
	                '</li>'+
	                '<li class="_item last">'+
		                '<div class="borderCon">'+
		                    '<img  class="_img" src="../images/overview_demo.jpg" alt="">'+
		                '</div>'+
		                '<div class="t4Con">'+
		                    '<h4 class="_caption">这里是课程名称这里是课程名称这里可以显示两排文字，最多30个字符...</h4>'+
		                    '<p class="d clear">'+
		                        '<span class="price _price free fl">'+
		                            '<em>￥199.00</em>'+
		                             '<del>￥199.00</del>'+
		                         '</span>'+
		                        '<span class="people _people fr">'+
		                            '<i class="iconfont">&#xe6e6;</i>'+
		                            '<em class="ml10">223</em>'+
		                        '</span>'+
		                    '</p>'+
		                '</div>'+
		                '<strong class="vipImg none"></strong>'+
	                '</li>'+
	            '</ul>'+
	        '</div>'+
	    '</div>'+
	'</div>'+
	'<div class="box-config none">'+
		'<div class="title">配置信息</div>'+
		'<div class="main-config">'+
		    '<p class="name"><span class="pro-name">名称</span><input type="text" class="in-name" value="热门课程"></p>'+
		    '<p class="itemOne"><span class="pro-name">学科</span></p>'+
		    '<p class="showItem">'+
		        '<span class="pro-name">是否显示学科小类</span>'+
		        '<span class="switch open"><i class="iconfont normal"></i><em>显示</em></span>'+
		    '</p>'+
		    '<p>'+
			'<span class="pro-name">授课类型</span>'+
			'<span class="teachType"><a href="javascript:;" class="btn btn-mini btn-default all">全部</a><a href="javascript:;" class="btn btn-mini btn-default live">直播</a><a href="javascript:;" class="btn btn-mini btn-default video">录播</a><a href="javascript:;" class="btn btn-mini btn-default face">面授</a></span>'+
			'</p>'+
		    '<p class="sortBy">'+
		        '<span class="pro-name">展示顺序</span>'+
		        '<span class="rank">'+
		            '<span class="checkbox num"><i class="iconfont"></i><em>学员数量</em></span>'+
		            '<span class="checkbox time"><i class="iconfont"></i><em>时间</em></span>'+
		            '<span class="checkbox price"><i class="iconfont"></i><em>价格</em></span>'+
		        '</span>'+
		        '<span class="recommend radio open"><i class="iconfont">&#xe60a;</i><em>推荐优先</em></span>'+
		    '</p>'+
		    '<p class="btns">'+
		        '<span class="pro-name"></span>'+
		        '<a href="javascript:;" class="btn btn-primary">保存配置</a>'+
		    '</p> '+
		'</div>'+
	'</div>'+
'</div>';	
	

//===============五图课程===================
var $div5=
//'<div class="model div5" modelId="5">'+
//'<div class="wrap contents picturemsg gap">'+
//'<div class="rows">'+
//'<div class="title">'+
//    '<h2 class="h5 _title"><i></i>热门课程 '+
//    '<span class="more"><a href="active">更多</a></span>'+
//    '</h2>'+
//    '<div class="class-menu">'+
//        '<a href="javascript:;" class="active">会计</a>'+
//        '<a href="javascript:;">人力</a>'+
//    '</div>'+
//'</div>'+
//'<div class="t5">'+
//    '<div class="first _item">'+
//        '<div class="pic">'+
//            '<img class="_img" src="../images/overview_demo.jpg" alt="">'+
//        '</div>'+
//        '<div class="text">'+
//            '<h2 class="h4 _caption">时间管理学——洞察隐藏在时间里的学问</h2>'+
//            '<p class="_overview">在这个高节奏的社会，事情越来越多，能自由支配的时间越来越少、越来越碎，想花个一大段时间做点自己的事情，却总是会被各种各样的琐事打断，想好要花一个小时写份文案，却在毫无头绪的情况下莫名其妙地上淘宝刷微博，结果三个小时过去了，可是文案却还没有开头。</p>'+
//        '</div>'+
//    '</div>'+
//    '<div class="sm-list">'+
//        '<ul>'+
//            '<li class="_item">'+
//                '<p>'+
//                    '<img class="_img" src="../images/overview_demo.jpg" alt="">'+
//                '</p>'+
//                '<p><a class="_caption" href="javascript:;">行为面试技巧——让你的工作不再难</a></p>'+
//            '</li>'+
//            '<li class="_item">'+
//                '<p>'+
//                    '<img class="_img" src="../images/overview_demo.jpg" alt="">'+
//                '</p>'+
//                '<p><a  class="_caption" href="javascript:;">行为面试技巧——让你的工作不再难</a></p>'+
//            '</li>'+
//            '<li class="_item">'+
//                '<p>'+
//                    '<img class="_img" src="../images/overview_demo.jpg" alt="">'+
//                '</p>'+
//                '<p><a class="_caption" href="javascript:;">行为面试技巧——让你的工作不再难</a></p>'+
//            '</li>'+
//            '<li class="_item">'+
//                '<p>'+
//                    '<img class="_img" src="../images/overview_demo.jpg" alt="">'+
//                '</p>'+
//                '<p><a class="_caption" href="javascript:;">行为面试技巧——让你的工作不再难</a></p>'+
//            '</li>'+
//        '</ul>'+
//    '</div>'+
//'</div>'+
//'</div>'+
//'</div>'+
//'<div class="box-config none">'+
//'<div class="title">配置信息</div>'+
//'<div class="main-config">'+
//    '<p class="name"><span class="pro-name">名称</span><input type="text" class="in-name" value="热门课程"></p>'+
//    '<p class="itemOne">'+
//        '<span class="pro-name">学科</span>'+
//    '</p>'+
//    '<p class="showItem">'+
//        '<span class="pro-name">是否显示学科小类</span>'+
//        '<span class="switch open"><i class="iconfont normal"></i><em>显示</em></span>'+
//    '</p>'+
//    '<p>'+
//	'<span class="pro-name">授课类型</span>'+
//	'<span class="teachType"><a href="javascript:;" class="btn btn-mini btn-default all">全部</a><a href="javascript:;" class="btn btn-mini btn-default live">直播</a><a href="javascript:;" class="btn btn-mini btn-default video">录播</a><a href="javascript:;" class="btn btn-mini btn-default face">面授</a></span>'+
//	'</p>'+
//    '<p class="sortBy">'+
//        '<span class="pro-name">展示顺序</span>'+
//        '<span class="rank">'+
//            '<span class="checkbox num"><i class="iconfont"></i><em>学员数量</em></span>'+
//            '<span class="checkbox time"><i class="iconfont"></i><em>时间</em></span>'+
//            '<span class="checkbox price"><i class="iconfont"></i><em>价格</em></span>'+
//        '</span>'+
//        '<span class="radio open recommend"><i class="iconfont">&#xe60a;</i><em>推荐优先</em></span>'+
//    '</p>'+
//    '<p>'+
//    '<p class="btns">'+
//        '<span class="pro-name"></span>'+
//        '<a href="javascript:;" class="btn btn-primary">保存配置</a>'+
//    '</p> '+
//'</div>'+
//'</div>'+
//'</div>';
'<div class="model div5" modelId="5">'+	
	'<div class="wrap contents picturemsg gap">'+
	    '<div class="rows">'+
	        '<div class="homeListTitle clear">'+
	            '<h5>五图课程</h5>'+
	            '<ul>'+
	            '</ul>'+
	            '<a href="javascript:;" class="homeMoreBtn">'+
	                '<span>更多</span>&gt;'+
	            '</a>'+
	        '</div>'+
	        '<div class="homet4 homet4-5">'+
	            '<ul class="clear T4List">'+
	                '<li class="_item">'+
	                    '<div class="borderCon">'+
	                        '<img class="_img" src="../images/overview_demo.jpg" alt="">'+
	                    '</div>'+
	                    '<div class="t4Con">'+
	                        '<h4 class="_caption">会计零基础到项目实战项目实战</h4>'+
	                        '<p class="d clear">'+
	                            '<span class="price _price free fl">免费</span>'+
	                            '<span class="people _people fr">'+
                                    '<i class="iconfont">&#xe6e6;</i>'+
                                    '<em class="ml10">223</em>'+
                                '</span>'+
	                        '</p>'+
	                    '</div>'+
	                '</li>'+
	                '<li class="_item">'+
	                    '<div class="borderCon">'+
	                        '<img class="_img" src="../images/overview_demo.jpg" alt="">'+
	                    '</div>'+
	                    '<div class="t4Con">'+
	                        '<h4 class="_caption">会计零基础到项目实战项目实战</h4>'+
	                        '<p class="d clear">'+
	                            '<span class="price _price free fl">'+
	                                   '<em>￥199.00</em>'+
	                                    '<del>￥199.00</del>'+
	                                '</span>'+
	                            '<span class="people _people fr">'+
	                                    '<i class="iconfont">&#xe6e6;</i>'+
	                                    '<em class="ml10">223</em>'+
	                                '</span>'+
	                        '</p>'+
	                    '</div>'+
	                '</li>'+
	                '<li class="_item">'+
	                    '<div class="borderCon">'+
	                        '<img class="_img" src="../images/overview_demo.jpg" alt="">'+
	                    '</div>'+
	                    '<div class="t4Con">'+
	                        '<h4 class="_caption">会计零基础到项目实战项目实战</h4>'+
	                        '<p class="d clear">'+
	                            '<span class="price _price free fl">'+
	                                   '<em>￥199.00</em>'+
	                                    '<del>￥199.00</del>'+
	                                '</span>'+
	                            '<span class="people _people fr">'+
	                                    '<i class="iconfont">&#xe6e6;</i>'+
	                                    '<em class="ml10">223</em>'+
	                                '</span>'+
	                        '</p>'+
	                    '</div>'+
	                '</li>'+
	                '<li class="_item">'+
	                    '<div class="borderCon">'+
	                        '<img class="_img" src="../images/overview_demo.jpg" alt="">'+
	                    '</div>'+
	                    '<div class="t4Con">'+
	                        '<h4 class="_caption">会计零基础到项目实战项目实战</h4>'+
	                        '<p class="d clear">'+
	                            '<span class="price _price free fl">'+
	                               '<em>￥199.00</em>'+
	                                '<del>￥199.00</del>'+
	                            '</span>'+
	                            '<span class="people _people fr">'+
	                                '<i class="iconfont">&#xe6e6;</i>'+
	                                '<em class="ml10">223</em>'+
	                            '</span>'+
	                        '</p>'+
	                    '</div>'+
	                '</li>'+
	                '<li class="_item last">'+
	                    '<div class="borderCon">'+
	                        '<img class="_img" src="../images/overview_demo.jpg" alt="">'+
	                    '</div>'+
	                    '<div class="t4Con">'+
	                        '<h4 class="_caption">会计零基础到项目实战项目实战</h4>'+
	                        '<p class="d clear">'+
	                            '<span class="price _price free fl">'+
	                                '<em>￥199.00</em>'+
	                                '<del>￥199.00</del>'+
	                            '</span>'+
	                            '<span class="people _people fr">'+
	                                '<i class="iconfont">&#xe6e6;</i>'+
	                                '<em class="ml10">223</em>'+
	                            '</span>'+
	                        '</p>'+
	                    '</div>'+
	                '</li>'+
	            '</ul>'+
	        '</div>'+
	    '</div>'+
	'</div>'+
	'<div class="box-config none">'+
		'<div class="title">配置信息</div>'+
		'<div class="main-config">'+
		    '<p class="name"><span class="pro-name">名称</span><input type="text" class="in-name" value="热门课程"></p>'+
		    '<p class="itemOne"><span class="pro-name">学科</span></p>'+
		    '<p class="showItem">'+
		        '<span class="pro-name">是否显示学科小类</span>'+
		        '<span class="switch open"><i class="iconfont normal"></i><em>显示</em></span>'+
		    '</p>'+
		    '<p>'+
			'<span class="pro-name">授课类型</span>'+
			'<span class="teachType"><a href="javascript:;" class="btn btn-mini btn-default all">全部</a><a href="javascript:;" class="btn btn-mini btn-default live">直播</a><a href="javascript:;" class="btn btn-mini btn-default video">录播</a><a href="javascript:;" class="btn btn-mini btn-default face">面授</a></span>'+
			'</p>'+
		    '<p class="sortBy">'+
		        '<span class="pro-name">展示顺序</span>'+
		        '<span class="rank">'+
		            '<span class="checkbox num"><i class="iconfont"></i><em>学员数量</em></span>'+
		            '<span class="checkbox time"><i class="iconfont"></i><em>时间</em></span>'+
		            '<span class="checkbox price"><i class="iconfont"></i><em>价格</em></span>'+
		        '</span>'+
		        '<span class="recommend radio open"><i class="iconfont">&#xe60a;</i><em>推荐优先</em></span>'+
		    '</p>'+
		    '<p class="btns">'+
		        '<span class="pro-name"></span>'+
		        '<a href="javascript:;" class="btn btn-primary">保存配置</a>'+
		    '</p> '+
		'</div>'+
	'</div>'+
'</div>';	
	

//==================八图课程=====================
var $div7=
//	'<div class="model div7" modelId="7">'+
//	'<div class="wrap contents picturemsg gap">'+
//'<div class="rows">'+
//'<div class="title">'+
//    '<h2 class="h5 _title"><i></i>热门课程 '+
//    '<span class="more"><a href="active">更多</a></span>'+
//    '</h2>'+
//    '<div class="class-menu">'+
//        '<a href="javascript:;" class="active">会计</a>'+
//        '<a href="javascript:;">人力</a>'+
//    '</div>'+
//'</div>'+
//'<div class="t4">'+
//    '<ul>'+
//        '<li class="_item">'+
//            '<p><img class="_img" src="../images/overview_demo.jpg" alt=""></p>'+
//            '<div class="infos">'+
//                '<h2 class="h6">'+
//                    '<a class="_caption" href="javascript:;">会计证财经法规与职业道德考点全解</a>       '+
//                '</h2>'+
//                '<p class="d"><span class="price _price"><i></i></span><span class="people _people"><i></i></span></p>'+
//            '</div>'+
//        '</li>'+
//        '<li class="_item">'+
//            '<p><img class="_img" src="../images/overview_demo.jpg" alt=""></p>'+
//            '<div class="infos">'+
//                '<h2 class="h6">'+
//                    '<a class="_caption" href="javascript:;">会计证财经法规与职业道德考点全解</a>       '+
//                '</h2>'+
//                '<p class="d"><span class="price _price"><i></i></span><span class="people _people"><i></i></span></p>'+
//            '</div>'+
//        '</li>'+
//        '<li class="_item">'+
//            '<p><img class="_img" src="../images/overview_demo.jpg" alt=""></p>'+
//            '<div class="infos">'+
//                '<h2 class="h6">'+
//                    '<a  class="_caption" href="javascript:;">会计证财经法规与职业道德考点全解</a>       '+
//                '</h2>'+
//                '<p class="d"><span class="price _price"><i></i></span><span class="people"><i></i></span></p>'+
//            '</div>'+ 
//        '</li>'+
//        '<li class="_item">'+
//            '<p><img class="_img" src="../images/overview_demo.jpg" alt=""></p>'+
//            '<div class="infos">'+
//                '<h2 class="h6">'+
//                    '<a class="_caption" href="javascript:;">会计证财经法规与职业道德考点全解</a>       '+
//                '</h2>'+
//                '<p class="d"><span class="price _price"><i></i></span><span class="people _people"><i></i></span></p>'+
//            '</div>'+
//        '</li>'+
//        '</ul><ul style="margin-top:20px;">'+
//        '<li class="_item">'+
//        '<p><img class="_img" src="../images/overview_demo.jpg" alt=""></p>'+
//        '<div class="infos">'+
//            '<h2 class="h6">'+
//                '<a class="_caption" href="javascript:;">会计证财经法规与职业道德考点全解</a>       '+
//            '</h2>'+
//            '<p class="d"><span class="price _price"><i></i></span><span class="people _people"><i></i></span></p>'+
//        '</div>'+
//    '</li>'+
//    '<li class="_item">'+
//        '<p><img class="_img" src="../images/overview_demo.jpg" alt=""></p>'+
//        '<div class="infos">'+
//            '<h2 class="h6">'+
//                '<a class="_caption" href="javascript:;">会计证财经法规与职业道德考点全解</a>       '+
//            '</h2>'+
//            '<p class="d"><span class="price _price"><i></i></span><span class="people _people"><i></i></span></p>'+
//        '</div>'+
//    '</li>'+
//    '<li class="_item">'+
//        '<p><img class="_img" src="../images/overview_demo.jpg" alt=""></p>'+
//        '<div class="infos">'+
//            '<h2 class="h6">'+
//                '<a  class="_caption" href="javascript:;">会计证财经法规与职业道德考点全解</a>       '+
//            '</h2>'+
//            '<p class="d"><span class="price _price"><i></i></span><span class="people"><i></i></span></p>'+
//        '</div>'+
//    '</li>'+
//    '<li class="_item">'+
//        '<p><img class="_img" src="../images/overview_demo.jpg" alt=""></p>'+
//        '<div class="infos">'+
//            '<h2 class="h6">'+
//                '<a class="_caption" href="javascript:;">会计证财经法规与职业道德考点全解</a>       '+
//            '</h2>'+
//            '<p class="d"><span class="price _price"><i></i></span><span class="people _people"><i></i></span></p>'+
//        '</div>'+
//    '</li>'+
//    '</ul>'+
//'</div>'+
//'</div>'+
//'</div>'+
//'<div class="box-config none">'+
//'<div class="title">配置信息</div>'+
//'<div class="main-config">'+
//    '<p class="name"><span class="pro-name">名称</span><input type="text" class="in-name" value="热门课程"></p>'+
//    '<p class="itemOne">'+
//        '<span class="pro-name">学科</span>'+
//    '</p>'+
//    '<p class="showItem">'+
//        '<span class="pro-name">是否显示学科小类</span>'+
//        '<span class="switch open"><i class="iconfont normal"></i><em>显示</em></span>'+
//    '</p>'+
//    '<p>'+
//	'<span class="pro-name">授课类型</span>'+
//	'<span class="teachType"><a href="javascript:;" class="btn btn-mini btn-default all">全部</a><a href="javascript:;" class="btn btn-mini btn-default live">直播</a><a href="javascript:;" class="btn btn-mini btn-default video">录播</a><a href="javascript:;" class="btn btn-mini btn-default face">面授</a></span>'+
//	'</p>'+
//    '<p class="sortBy">'+
//        '<span class="pro-name">展示顺序</span>'+
//        '<span class="rank">'+
//            '<span class="checkbox num"><i class="iconfont"></i><em>学员数量</em></span>'+
//            '<span class="checkbox time"><i class="iconfont"></i><em>时间</em></span>'+
//            '<span class="checkbox price"><i class="iconfont"></i><em>价格</em></span>'+
//        '</span>'+
//        '<span class="radio recommend open"><i class="iconfont">&#xe60a;</i><em>推荐优先</em></span>'+
//    '</p>'+
//    '<p class="btns">'+
//        '<span class="pro-name"></span>'+
//        '<a href="javascript:;" class="btn btn-primary">保存配置</a>'+
//    '</p> '+
//'</div>'+
//'</div>'+
//'</div>';
'<div class="model div7" modelId="7">'+	
	'<div class="wrap contents picturemsg gap">'+
	    '<div class="rows">'+
	        '<div class="homeListTitle clear">'+
	            '<h5>八图课程</h5>'+
	            '<ul>'+
	            '</ul>'+
	            '<a href="javascript:;" class="homeMoreBtn">'+
	                '<span>更多</span>&gt;'+
	            '</a>'+
	        '</div>'+
	        '<div class="homet4">'+
		        '<ul class="clear T4List">'+
		            '<li class="_item">'+
		                '<div class="borderCon">'+
		                    '<img  class="_img" src="../images/overview_demo.jpg" alt="">'+
		                '</div>'+
		                '<div class="t4Con">'+
		                    '<h4 class="_caption">这里是课程名称这里是课程名称这里可以显示两排文字，最多30个字符...</h4>'+
		                    '<p class="d clear">'+
		                        '<span class="price _price free fl">免费</span>'+
		                        '<span class="people _people fr">'+
		                            '<i class="iconfont">&#xe6e6;</i>'+
		                            '<em class="ml10">223</em>'+
		                        '</span>'+
		                    '</p>'+
		                '</div>'+
		                '<strong class="vipImg none"></strong>'+
		            '</li>'+
		            '<li class="_item">'+
		                '<div class="borderCon">'+
		                    '<img  class="_img" src="../images/overview_demo.jpg" alt="">'+
		                '</div>'+
		                '<div class="t4Con">'+
		                    '<h4 class="_caption">这里是课程名称这里是课程名称这里可以显示两排文字，最多30个字符...</h4>'+
		                    '<p class="d clear">'+
		                        '<span class="price _price free fl">'+
		                            '<em>￥199.00</em>'+
		                             '<del>￥199.00</del>'+
		                         '</span>'+
		                        '<span class="people _people fr">'+
		                            '<i class="iconfont">&#xe6e6;</i>'+
		                            '<em class="ml10">223</em>'+
		                        '</span>'+
		                    '</p>'+
		                '</div>'+
		                '<strong class="vipImg none"></strong>'+
		            '</li>'+
		            '<li class="_item">'+
		                '<div class="borderCon">'+
		                    '<img  class="_img" src="../images/overview_demo.jpg" alt="">'+
		                '</div>'+
		                '<div class="t4Con">'+
		                    '<h4 class="_caption">这里是课程名称这里是课程名称这里可以显示两排文字，最多30个字符...</h4>'+
		                    '<p class="d clear">'+
		                        '<span class="price _price free fl">'+
		                            '<em>￥199.00</em>'+
		                             '<del>￥199.00</del>'+
		                         '</span>'+
		                        '<span class="people _people fr">'+
		                            '<i class="iconfont">&#xe6e6;</i>'+
		                            '<em class="ml10">223</em>'+
		                        '</span>'+
		                    '</p>'+
		                '</div>'+
		                '<strong class="vipImg none"></strong>'+
		            '</li>'+
		            '<li class="_item last">'+
		                '<div class="borderCon">'+
		                    '<img  class="_img" src="../images/overview_demo.jpg" alt="">'+
		                '</div>'+
		                '<div class="t4Con">'+
		                    '<h4 class="_caption">这里是课程名称这里是课程名称这里可以显示两排文字，最多30个字符...</h4>'+
		                    '<p class="d clear">'+
		                        '<span class="price _price free fl">'+
		                            '<em>￥199.00</em>'+
		                             '<del>￥199.00</del>'+
		                         '</span>'+
		                        '<span class="people _people fr">'+
		                            '<i class="iconfont">&#xe6e6;</i>'+
		                            '<em class="ml10">223</em>'+
		                        '</span>'+
		                    '</p>'+
		                '</div>'+
		                '<strong class="vipImg none"></strong>'+
		            '</li>'+
		        '</ul>'+
		        '<ul class="clear T4List">'+
	                '<li class="_item">'+
	                    '<div class="borderCon">'+
	                        '<img  class="_img" src="../images/overview_demo.jpg" alt="">'+
	                    '</div>'+
	                    '<div class="t4Con">'+
	                        '<h4 class="_caption">这里是课程名称这里是课程名称这里可以显示两排文字，最多30个字符...</h4>'+
	                        '<p class="d clear">'+
	                            '<span class="price _price free fl">免费</span>'+
	                            '<span class="people _people fr">'+
	                                '<i class="iconfont">&#xe6e6;</i>'+
	                                '<em class="ml10">223</em>'+
	                            '</span>'+
	                        '</p>'+
	                    '</div>'+
	                    '<strong class="vipImg none"></strong>'+
	                '</li>'+
	                '<li class="_item">'+
	                    '<div class="borderCon">'+
	                        '<img  class="_img" src="../images/overview_demo.jpg" alt="">'+
	                    '</div>'+
	                    '<div class="t4Con">'+
	                        '<h4 class="_caption">这里是课程名称这里是课程名称这里可以显示两排文字，最多30个字符...</h4>'+
	                        '<p class="d clear">'+
		                        '<span class="price _price free fl">'+
		                            '<em>￥199.00</em>'+
		                             '<del>￥199.00</del>'+
		                         '</span>'+
	                            '<span class="people _people fr">'+
	                                '<i class="iconfont">&#xe6e6;</i>'+
	                                '<em class="ml10">223</em>'+
	                            '</span>'+
	                        '</p>'+
	                    '</div>'+
	                    '<strong class="vipImg none"></strong>'+
	                '</li>'+
	                '<li class="_item">'+
	                    '<div class="borderCon">'+
	                        '<img  class="_img" src="../images/overview_demo.jpg" alt="">'+
	                    '</div>'+
	                    '<div class="t4Con">'+
	                        '<h4 class="_caption">这里是课程名称这里是课程名称这里可以显示两排文字，最多30个字符...</h4>'+
	                        '<p class="d clear">'+
		                        '<span class="price _price free fl">'+
		                            '<em>￥199.00</em>'+
		                             '<del>￥199.00</del>'+
		                         '</span>'+
	                            '<span class="people _people fr">'+
	                                '<i class="iconfont">&#xe6e6;</i>'+
	                                '<em class="ml10">223</em>'+
	                            '</span>'+
	                        '</p>'+
	                    '</div>'+
	                    '<strong class="vipImg none"></strong>'+
	                '</li>'+
	                '<li class="_item last">'+
		                '<div class="borderCon">'+
		                    '<img  class="_img" src="../images/overview_demo.jpg" alt="">'+
		                '</div>'+
		                '<div class="t4Con">'+
		                    '<h4 class="_caption">这里是课程名称这里是课程名称这里可以显示两排文字，最多30个字符...</h4>'+
		                    '<p class="d clear">'+
		                        '<span class="price _price free fl">'+
		                            '<em>￥199.00</em>'+
		                             '<del>￥199.00</del>'+
		                         '</span>'+
		                        '<span class="people _people fr">'+
		                            '<i class="iconfont">&#xe6e6;</i>'+
		                            '<em class="ml10">223</em>'+
		                        '</span>'+
		                    '</p>'+
		                '</div>'+
		                '<strong class="vipImg none"></strong>'+
	                '</li>'+
	            '</ul>'+
	        '</div>'+
	    '</div>'+
	'</div>'+
	'<div class="box-config none">'+
		'<div class="title">配置信息</div>'+
		'<div class="main-config">'+
		    '<p class="name"><span class="pro-name">名称</span><input type="text" class="in-name" value="热门课程"></p>'+
		    '<p class="itemOne"><span class="pro-name">学科</span></p>'+
		    '<p class="showItem">'+
		        '<span class="pro-name">是否显示学科小类</span>'+
		        '<span class="switch open"><i class="iconfont normal"></i><em>显示</em></span>'+
		    '</p>'+
		    '<p>'+
			'<span class="pro-name">授课类型</span>'+
			'<span class="teachType"><a href="javascript:;" class="btn btn-mini btn-default all">全部</a><a href="javascript:;" class="btn btn-mini btn-default live">直播</a><a href="javascript:;" class="btn btn-mini btn-default video">录播</a><a href="javascript:;" class="btn btn-mini btn-default face">面授</a></span>'+
			'</p>'+
		    '<p class="sortBy">'+
		        '<span class="pro-name">展示顺序</span>'+
		        '<span class="rank">'+
		            '<span class="checkbox num"><i class="iconfont"></i><em>学员数量</em></span>'+
		            '<span class="checkbox time"><i class="iconfont"></i><em>时间</em></span>'+
		            '<span class="checkbox price"><i class="iconfont"></i><em>价格</em></span>'+
		        '</span>'+
		        '<span class="recommend radio open"><i class="iconfont">&#xe60a;</i><em>推荐优先</em></span>'+
		    '</p>'+
		    '<p class="btns">'+
		        '<span class="pro-name"></span>'+
		        '<a href="javascript:;" class="btn btn-primary">保存配置</a>'+
		    '</p> '+
		'</div>'+
	'</div>'+
'</div>';	

//========================新闻-列表===================
var $div8=
//	'<div class="model div8" modelId="8">'+
//	'<div class="wrap contents news gap">'+
//    '<div class="rows">'+
//        '<div class="title">'+
//            '<h2 class="h6 _title">新闻公告 <span class="more"><a href="javascript:;">更多</a></span></h2>'+
//        '</div>'+
//        '<div class="list">'+
//            '<ul>'+
//            '<li class="_item"><i></i><a href="javascript:;"></a></li>'+
//            '<li class="_item"><i></i><a href="javascript:;"></a></li>'+
//            '<li class="_item"><i></i><a href="javascript:;"></a></li>'+
//            '<li class="_item"><i></i><a href="javascript:;"></a></li>'+
//            '<li class="_item"><i></i><a href="javascript:;"></a></li>'+
//            '<li class="_item"><i></i><a href="javascript:;"></a></li>'+
//            '<li class="_item"><i></i><a href="javascript:;"></a></li>'+
//            '<li class="_item"><i></i><a href="javascript:;"></a></li>'+
//            '<li class="_item"><i></i><a href="javascript:;"></a></li>'+
//            '<li class="_item"><i></i><a href="javascript:;"></a></li>'+
//            '<li class="_item"><i></i><a href="javascript:;"></a></li>'+
//            '<li class="_item"><i></i><a href="javascript:;"></a></li>'+
//            '</ul>'+
//        '</div>'+
//    '</div>'+
//'</div>'+
//	'<div class="box-config none">'+
//    '<div class="title">配置信息</div>'+
//    '<div class="main-config">'+
//        '<p class="name"><span class="pro-name">名称</span><input type="text" class="in-name" value=""></p>'+
//        '<p class="sortBy">'+
//            '<span class="pro-name">展示顺序</span>'+
//            '<span class="recommend radio open"><i class="iconfont">&#xe60a;</i><em>推荐优先</em></span>'+
//        '</p>'+
//        '<p class="btns">'+
//            '<span class="pro-name"></span>'+
//            '<a href="javascript:;" class="btn btn-primary">保存配置</a>'+
//        '</p>'+ 
//    '</div>'+
//'</div>'+
//'</div>';
'<div class="model div8" modelId="8">'+	
	'<div class="wrap contents picturemsg gap">'+
	    '<div class="rows">'+
	        '<div class="homeListTitle clear">'+
	            '<h5>新闻资讯</h5>'+
	            '<a href="javascript:;" class="homeMoreBtn">'+
	                '<span>更多</span>&gt;'+
	            '</a>'+
	        '</div>'+
	        '<div class="clear newInfor">'+
	            '<ol class="fl">'+
	                '<li class="_olitem _item">'+
	                    '<h3></h3>'+
	                    '<p></p>'+
	                    '<p class="lastp">'+
	                        '<time></time>'+
	                        '<span>'+
	                            '<i class="iconfont iYan">&#xe6e7;</i>'+
	                            '<small></small>'+
	                        '</span>'+
	                        '<span>'+
	                            '<i class="iconfont">&#xe6e5;</i>'+
	                            '<small></small>'+
	                        '</span>'+
	                    '</p>'+
	                    '<b></b>'+
	                '</li>'+
	                '<li  class="_olitem _item">'+
	                    '<h3></h3>'+
	                    '<p></p>'+
	                    '<p class="lastp">'+
	                        '<time></time>'+
	                        '<span>'+
	                            '<i class="iconfont iYan">&#xe6e7;</i>'+
	                            '<small></small>'+
	                        '</span>'+
	                        '<span>'+
	                            '<i class="iconfont">&#xe6e5;</i>'+
	                            '<small></small>'+
	                        '</span>'+
	                    '</p>'+
	                    '<b></b>'+
	                '</li>'+
	                '<li class="_olitem _item last">'+
	                    '<h3></h3>'+
	                    '<p></p>'+
	                    '<p class="lastp">'+
	                        '<time></time>'+
	                        '<span>'+
	                            '<i class="iconfont iYan">&#xe6e7;</i>'+
	                            '<small></small>'+
	                        '</span>'+
	                        '<span>'+
	                            '<i class="iconfont">&#xe6e5;</i>'+
	                            '<small></small>'+
	                        '</span>'+
	                    '</p>'+
	                    '<b></b>'+
	                '</li>'+
	            '</ol>'+
	            '<ul class="fr">'+
	                '<li class="_ulitem _item clear">'+
	                    '<div class="borderCon fl">'+
	                        '<img src="" alt="">'+
	                    '</div>'+
	                    '<div class="inforList">'+
	                        '<h3></h3>'+
	                        '<p></p>'+
	                        '<time></time>'+
	                    '</div>'+
	                '</li>'+
	                '<li class="_ulitem _item clear">'+
	                    '<div class="borderCon fl">'+
	                        '<img src="" alt="">'+
	                    '</div>'+
	                    '<div class="inforList">'+
	                        '<h3></h3>'+
	                        '<p></p>'+
	                        '<time></time>'+
	                    '</div>'+
	                '</li>'+
	            '</ul>'+
	        '</div>'+
	    '</div>'+
	'</div>'+
	'<div class="box-config none">'+
	  '<div class="title">配置信息</div>'+
	  '<div class="main-config">'+
	      '<p class="name"><span class="pro-name">名称</span><input type="text" class="in-name" value=""></p>'+
	      '<p class="sortBy">'+
	          '<span class="pro-name">展示顺序</span>'+
	          '<span class="recommend radio open"><i class="iconfont">&#xe60a;</i><em>推荐优先</em></span>'+
	      '</p>'+
	      '<p class="btns">'+
	          '<span class="pro-name"></span>'+
	          '<a href="javascript:;" class="btn btn-primary">保存配置</a>'+
	      '</p>'+ 
	  '</div>'+
	'</div>'+
'</div>';
//=====================自定义=======================
//var $div6=
//	'<div class="model div6" modelId="6">'+
//	'<div class="wrap contents single gap">'+
//	'<div class="title">'+
//	    '<h2 class="h3 _title">关于在线教育课程开课的通知</h2>            '+
//	'</div>'+
//	'<div class="g _item">'+
//	    '<div class="picture">'+
//	        '<img class="_img" src="../images/overview_demo.jpg" alt="" style="width:100%;">'+
//	    '</div>'+
//	    '<div class="text">        '+
//	        '<p class="c _overview">汇集全国多家知名大学里广受学生好评的计算机老师，每一门课程都由他们亲自制作，权威、专业！人人都可以在这里体验到时下最流行的 MOOC ，系统地掌握计算机专业知识。</p>'+
//	        '<p><a href="javascript:;" class="btn btn-primary btn-bigger">阅读更多</a></p>'+
//	    '</div>'+
//	'</div>'+
//	'</div>'+
//	'<div class="box-config none">'+
//    '<div class="title">配置信息</div>'+
//    '<div class="main-config">'+
//        '<p class="name"><span class="pro-name">名称</span><input type="text" class="in-name" value="测试的名称"></p>'+
//        '<p class="text">'+
//            '<span class="pro-name t-top">文案</span>'+
//            '<span class="textarea">'+
//                '<textarea name=""></textarea>'+
//            '</span>'+
//        '</p>'+
//        '<p class="pic">'+
//            '<span class="pro-name">图片</span>'+
//            '<span class="up-pic">'+
//                '<img class="img-perview none" />'+
//                '<a href="javascript:;" class="btn btn-default btn-sm btn-up">上传图片</a>'+
//                '<input type="file" class="up-btn" accept="image/*">'+
//                '<input type="hidden" class="img-url"/>'+
//            '</span>'+
//        '</p>'+
//        '<p class="url">'+
//            '<span class="pro-name">链接地址</span>'+
//            '<span class="second">'+
//                '<input class="_url" type="text" value="http://www.baidu.com">'+
//            '</span>'+
//        '</p>'+
//        '<p class="btns">'+
//            '<span class="pro-name"></span>'+
//            '<a href="javascript:;" class="btn btn-primary">保存配置</a>'+
//        '</p>     '+
//    '</div>'+
//'</div>'+
//'</div>';

var $div6='<div class="model div6" modelId="6" id="" sort="" type=""><div class="wrap contents single gap"><img class="cusImg img-perview" style="width:100%" src="../images/overview-7.jpg"/></div>'+
'<div class="box-config none"><div class="title">配置信息</div><div class="main-config">'+
'<p class="cusImg"><span class="pro-name">上传图片</span><span class="btns" style="position:relative;"><input type="file" name="imgData" class="cusfile u-f" accept="image/*" id=""/><a class="btn btn-default  btn-mini c-uf" href="javascript:;">上传图片</a></span></p>'+
'<p class="url"><span class="pro-name">链接地址</span><span>http://<input type="text" value=""/></span></p>'+
'<p class="tile"><span class="pro-name">是否通屏</span><span><input type="radio" name="tile" class="tongping" id="need-tile" value="1" checked="checked"/><label for="need-tile" style="display:inline;">是</label><input type="radio" class="tongping" name="tile" class="tongping" id="unneed-tile" value="0"/><label for="unneed-tile" style="display:inline;">否</label></span>&nbsp;&nbsp;<span style="font-size:12px;color:red;" class="addsetHtml">注：设置为通屏时，建议上传图片宽度为1600px-1900px</span></p>'+
'<p class="btns"><span class="pro-name"></span><a href="javascript:;" class="btn btn-primary">保存配置</a></p>'+
'</div>'+
'</div>'+
'</div>';








//================================================================
var _types=["INDEX_CLASSTYPE","INDEX_CLASSTYPE","INDEX_ITEM","INDEX_ITEM","INDEX_ITEM","INDEX_CUSTOM","INDEX_ITEM","INDEX_NEWS"];
for(var i=1;i<=8;i++){
	$divs.push({"id":i,"text":eval('$div'+i),"type":_types[i-1]});
}