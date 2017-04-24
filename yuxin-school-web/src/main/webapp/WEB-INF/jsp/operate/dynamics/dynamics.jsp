<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/decorators/import.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>动态</title>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/manage.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/teacher.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/lightbox.css">
    <link rel="stylesheet" type="text/css" href="<%=rootPath%>/stylesheets/popupwin.css">
    <style type="text/css">
        .num_140{
            font-weight: 700;
            font-size: 22px;
            font-style: italic;
            font-family: Constantia, Georgia;
            color: #808080;
        }
        .add-span{
            color: #808080;
            float: right;
            margin-right: 75px;
        }
        .add-textarea{
            width: 568px;
            height: 120px;
            margin-left: 66px;
            resize: none;
            font-size: 14px;
            word-wrap: break-word;
            line-height: 18px;
            overflow-y: auto;
            overflow-x: hidden;
            outline: none;
        }
        .add_tittle{
            color: #808080;
            float: left;
            margin-left: 71px;
            font-family: 'Microsoft Yahei';
            font-size: 16px;
        }
        .div_textarea{
            padding: 5px;
            border-color: #cccccc;
        }
        .clickUploadDiv{
            border: 2px dashed #ccc;
            width: 80px;
            height: 80px;
            margin-left: 10px;
            margin-top: 5px;
            cursor:pointer;
            float: left
        }
        .clickUploadDiv_plus{
            display: block;
            font-size: 40px;
            font-weight: bold;
            color: #ccc;
            margin-top: 5px;
            text-align: center;
        }
        .imgObject{
            width: 84px;
            height: 84px;
        }
        .img_bg{
            display: none;
            background-color: rgba(0,0,0,.3);
            z-index: 96;
            position: absolute;
            top: 0;
            right: 0;
            bottom: 0;
            left: 0;
        }
        .img_div{
            float: left;
            margin:5px;
            position:relative;
        }
        .delete_div{
            display: none;
            background-image: url(../../../images/ico_yybUp.png);
            background-position: -25px -25px;
            background-repeat: no-repeat;
            z-index: 97;
            position: absolute;
            top: 0;
            right: 0;
            height: 20px;
            width: 20px;
        }
        .gobal-progress{
            margin-top: -13px;
            height: 32px;
        }
        .iconfont{
            cursor: auto;
        }
    </style>
</head>
<body>
<%@include file="/WEB-INF/jsp/menu/menu_operate.jsp" %>
<script type="text/javascript">
    $selectSubMenu('teacher_person_status');
</script>
<div class="Y_wrap">
    <div class="Y_background Y_backtrend">
        <div class="Y_head">
            <h2 class="h5" style="width: 1000px">动态</h2>
            <a href="javascript:void(0);" class="btn btn-mini btn-primary Y_btn"><i class="iconfont"
                                                                                    style="font-size: 12px">&#xe652;</i>发布动态</a>
            <span class="line"></span>
        </div>
        <div class="dynamicsList">

        </div>
        <!--
        <div class="Y_trend Y_clear">
            <div class="headicon">
                <a href="" >
                    <img src="../../../temp/2.png" alt="" width="50" height="50"/>
                </a>
            </div>
            <div class="Y_content">
                <div class="content_title">
                    管理科学定义
                </div>
                <p>
                    我随便说说，你随便看看。 老家。 老家最喜欢的就是社戏每年过年时候都有。是最美好的童年回忆。幸好现在也有。 第一次出游是去洞头出海。 接下来是厦门由于人太多了拍我随便说说，欢的就是社戏..
                </p>
                <div class="trend_pic Y_clear">
                    <a href="../../../temp/2.png" class="Y_pic" data-lightbox="roadtrip">
                        <img src="../../../temp/2.png" alt=""/>
                    </a>
                    <a href="../../../temp/2.png" class="Y_pic" data-lightbox="roadtrip">
                        <img src="../../../temp/2.png" alt=""/>
                    </a>
                    <a href="../../../temp/2.png" class="Y_pic" data-lightbox="roadtrip">
                        <img src="../../../temp/2.png" alt=""/>
                    </a>
                    <a href="../../../temp/2.png" class="Y_pic" data-lightbox="roadtrip">
                        <img src="../../../temp/2.png" alt=""/>
                    </a>
                    <a href="../../../temp/2.png" class="Y_pic" data-lightbox="roadtrip">
                        <img src="../../../temp/2.png" alt=""/>
                    </a>
                </div>
                <p class="Y_time">
                    <span class="Y_mr20">2014-06-10</span>
                    <span>13:15:55</span>
                </p>
                <p class="Y_icon">
                       <span class="Praise Y_mr20">
                           <i class="iconfont fnt_color">&#xe64e;</i>
                           <em>赞 (29)</em>
                       </span>
                        <span class="comment">
                            <i class="iconfont fnt_color">&#xe64f;</i>
                            <em>评论 (11)</em>
                        </span>
                </p>
                <div class="Y_comment">
                    <div class="comment_content Y_clear">
                        <div class="headicon">
                            <a href=""><img src="../../../temp/2.png" alt="" width="50" height="50"/></a>
                        </div>
                        <div class="word">
                            <div class="word_content">
                                <a href="">还是那个少年</a>
                                <span>2015-9-31</span>
                                <span>23：44</span>
                        <span class="btns">
                            <button>回复</button>
                            <button class="delete delete2">
                                删除
                            </button>
                        </span>
                            </div>
                            <p>
                                可怜的娃
                            </p>
                        </div>
                    </div>
                    <div class="comment_content Y_clear">
                        <div class="headicon">
                            <a href=""><img src="../../../temp/2.png" alt="" width="50" height="50"/></a>
                        </div>
                        <div class="word">
                            <div class="word_content">
                                <a href="">还是那个少年</a>
                                <span>2015-9-31</span>
                                <span>23：44</span>
                        <span class="btns">
                            <button>回复</button>
                            <button class="delete delete2">
                                删除
                            </button>
                        </span>
                            </div>
                            <p>
                                可怜的娃
                            </p>
                        </div>
                    </div>
                    <div class="comment_answer Y_clear">
                        <div class="headicon">
                            <a href=""><img src="../../../temp/2.png" alt="" width="50" height="50"/></a>
                        </div>
                        <div class="word">
                            <div class="word_content">
                                <a href="">还是那个少年@那个</a>
                                <span>2015-9-31</span>
                                <span>23：44</span>
                        <span class="btns">
                            <button>回复</button>
                            <button class="delete delete2">
                                删除
                            </button>
                        </span>
                            </div>
                            <p>
                                可怜的娃
                            </p>
                        </div>
                    </div>
                    <div class="comment_answer Y_clear">
                        <div class="headicon">
                            <a href=""><img src="../../../temp/2.png" alt="" width="50" height="50"/></a>
                        </div>
                        <div class="word">
                            <div class="word_content">
                                <a href="">还是那个少年@加哦</a>
                                <span>2015-9-31</span>
                                <span>23：44</span>
                        <span class="btns">
                            <button>回复</button>
                            <button class="delete delete2">
                                删除
                            </button>
                        </span>
                            </div>
                            <p>
                                可怜的娃
                            </p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="btns">
                <button>取消置顶</button>
                <button>编辑</button>
                <button class="delete delete1">删除</button>
            </div>
            <div class="Y_cb"></div>
            <div class="slide slideup">
                <a href="">展示更多</a>
            </div>
            <hr/>
        </div>

        <div class="Y_pages">
            <a href="" class="up_page"><i class="iconfont">&#xe650;</i>前一页</a>
            <a href="" class="num">1</a>
            <a href=""class="num">2</a>
            <a href=""class="num">3</a>
            <a href=""class="num">4</a>
            <a href="" class="next_page"><i class="iconfont">&#xe651;</i>后一页</a>
        </div>
                     -->
        <!-- 分页插件开始 -->
        <div class="pages pagination"></div>
        <div class="mark mark1">
            <input type="hidden" class="deleteId" id=""/>

            <div class="alertbox">
                <p style="text-align: center"><i class="iconfont">&#xe653;</i>您是否确定删除该条动态及评论</p>

                <div class="button-box Y_clear">
                    <button class="yes">是</button>
                    <button class="no">否</button>
                </div>
            </div>
        </div>
        <div class="mark mark2">
            <div class="alertbox">
                <input type="hidden" class="deleteReplayId" id=""/>

                <p style="text-align: center"><i class="iconfont">&#xe653;</i>您是否确定删除该条评论</p>

                <div class="button-box Y_clear">
                    <button class="yes">是</button>
                    <button class="no">否</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!--
<br/><br/><br/><br/>
<div class="Y_wrap">
    <div class="Y_background Y_backtrend">
        <div class="Y_head">
            <h2 class="h5">动态</h2>
            <a href="javascript:;" class="Y_btn"><i class="iconfont">&#xe652;</i>发布动态</a>
            <span class="line"></span>
        </div>
        <div class="empty">
            暂时没有发布的状态
        </div>
    </div>
</div>
-->
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
    <p><i></i>加载中,请稍后...</p>
</div>
<!-- popupwin 界面 开始-->
<div class="popupwin addDynamics" style="width:700px; height:auto;top:10px" data-pupwin="modal">
    <form class="addForm">
        <div class="popupwin-title">
            <h2 class="h5">发布新动态</h2>
            <i class="close iconfont"></i>
        </div>
        <div class="main form-horizontal" id="lsOne">
            <div class="form-body">
                <div class="form-group">
                    <div>
                        <div class="add_tittle">文字内容</div>
                        <div class="add-span">您还可以输入<span class="num_140">140</span>字</div>
                        <div class="div_textarea"><textarea class="add-textarea" maxlength="140"></textarea></div>
                    </div>
                    <div class="left" style="width: 568px; margin-left: 66px;height: 100px" id="leftCont">
                        <p class='tips-msg'>
                            <input type="file" id="imgDatas" name="imgData" style="display: none;"
                                   onchange="changeCyclePic('so',this);" class="up shangchuan">

                        <div class="clickUploadDiv">
                            <p class="clickUploadDiv_plus">+</p>
                        </div>
                        </p>
                    </div>
                    <div style="width: 568px;margin-left: 66px;">
                        <button class=" btn btn-primary add-btn" type="button" style="margin-left: 214px;margin-right: 13px" disabled>发&nbsp;&nbsp;布
                        </button>
                        <a class="btn btn-default" data-pupwin-btn="cancle"
                           href="javascript:void(0);">取&nbsp;&nbsp;消</a>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script type="text/javascript" src="<%=rootPath%>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/operate/dynamics/dynamics.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/operate/dynamics/lightbox.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.pagination.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/popupwin.js"></script>
</body>
</html>