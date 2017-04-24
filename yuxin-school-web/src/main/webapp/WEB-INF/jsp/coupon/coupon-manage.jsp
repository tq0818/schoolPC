<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="zh-cn">
<head>
	<%@include file="/decorators/import.jsp" %>
    <title>促销</title> 
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/fatstyle.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/system.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/company.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/splitscreen.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/classedit.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/coupon.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/conpon-alert.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/manage-screen.css" />
    <link rel="stylesheet" href="<%=rootPath%>/stylesheets/fonts/iconfont.css"/>
    <style type="text/css">
	.coupon-use-detail table th a{height: 100%;display: inline-block}
	.coupon-use-detail table th a:link,.coupon-use-detail table th a:visited{color: #474747; }
	.coupon-use-detail table th a:hover,.coupon-use-detail table th a:active{ color:#474747; }
    .coupon-use-detail .sort-btn .chang_one{color:dodgerblue;}
    li.subentry span {left:82px;}
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_operate.jsp"></jsp:include>
<input id="exportExcel_PatchId" value="0" type="hidden">
<div class="u-wrap company overflow coupon-page">
  
  <jsp:include page="/WEB-INF/jsp/menu/menu_coupon.jsp"></jsp:include>
    <div class="right-side">
     <c:if test="${cfs!=null && cfs.status==1 }">
        <div class="u-wrap classes set-system">
            <div class="screen-right-cont set-system">
               <div class="screen-right-title">
                <h3>
                    <i> 优惠码管理</i>
                </h3>

                <div class="set-new manage-button couponCreate"> 创建优惠码<span class="manage-button-mask"></span></div>
            </div>
                <div class="search-cont">
<!--                     <select name="" id="startStatus"> -->
<!--                         <option value="-1">状态</option> -->
<!--                         <option value="begin">未开始</option> -->
<!--                         <option value="ing">进行中</option> -->
<!--                         <option value="old">已结束</option> -->
<!--                     </select> -->
                    <select name="" id="sendStatus">
                        <option value="">状态</option>
                        <option value="0">未发放</option>
                        <option value="1">已发放</option>
                    </select>
                    <span class="use-time">
                        <em>使用期限</em>
                         <input type="text" name="startTime" id="startDate" class="date-picker from">
                        <em>——</em>
                        <input type="text" name="endTime" id="endDate" class="date-picker to">
                    </span>
                    <input type="text" id="patchName" placeholder="优惠名称"/>
                    <button class="btn btn-sm btn-primary btn-searh manage-button" style="height: 26px;padding-bottom: 19px;width:auto;padding:5px 10px;line-height:12px;">搜索</button>
                </div>
                <div class="user-list">
                    <table class="table table-center couponsList">
                        <colgroup>
                            <col width="10%">
                            <col width="10%">
                            <col width="8%">
                            <col width="8%">
                            <col width="17%">
                            <col width="6%">
                            <col width="10%">
                            <col width="8%">
                            <col width="8%">
                            <col width="15%">
                        </colgroup>
                        <tbody>
                            <tr>
                                <th>优惠名称</th>
                                <th>优惠对象</th>
                                <th>优惠方式</th>
                                <th>产品类型</th>
                                <th>优惠范围</th>
                                <th>发放量</th>
                                <th>使用期限</th>
                                <th>发放状态</th>
                                <th>发放方式</th>
                                <th>操作</th>
                            </tr>
                            <tr><td colspan="10">暂无数据</td></tr>
                        </tbody>
                    </table>
                </div>
                <div class="pages pagination"></div>
                <input type="hidden" id="page" value="1">
            </div>
        </div>
        </c:if>
    </div>
</div>
<div class="layer-tips check-coupon-code checkCouponsCode" style="margin-left: -470px;"> 
    <div class="layer-tips-title">查看优惠码 <i class="close iconfont confirm_close">&#xe610;</i></div>
    <div class="check-cont">
        <table class="table table-center coupons_libs_List">
            <colgroup>
                <col width="20%">
                <col width="30%">
                <col width="30%">
                <col width="20%">
            </colgroup>
            <tbody>
                <tr>
                    <th>优惠码</th>
                    <th>优惠范围</th>
                    <th>优惠对象</th>
                    <th>使用期限</th>
                </tr>
                <tr><td colspan="4">暂无数据</td></tr>
            </tbody>
        </table>
        <div class="pages pagination_libs"></div>
        <input type="hidden" id="page_libs" value="1">
    </div>
</div>
<div class="layer-tips coupon-code-detail couponCodeDetail" style="margin-left: -470px;">
    <div class="layer-tips-title">优惠码详情 <i class="close iconfont confirm_close">&#xe610;</i></div>
    <div class="coupon-basic-info">
        <h3>基本信息</h3>
        <div  class="info-cont">
            <ul class="clear">
                <li><span class="itm-tilte">优惠名称</span><span id="patch_name">未取得数据</span></li>
                <li><span class="itm-tilte">优惠方式</span><span id="patch_useWay">未取得数据</span></li>
                <li><span class="itm-tilte">发放量</span><span id="patch_totalNum">未取得数据</span></li>
                <li><span class="itm-tilte">优惠对象</span><span id="patch_forCrowd">未取得数据</span></li>
                <li><span class="itm-tilte">使用期限</span><span id="patch_time">未取得数据</span></li>
                <li><span class="itm-tilte">使用范围</span><span id="patch_useRange">未取得数据</span></li>
                <li><span class="itm-tilte">优惠说明</span><span id="patch_description">未取得数据</span></li>
            </ul>
        </div>
    </div>
    <h3>使用详情</h3>
    <div class="coupon-use-detail">
        <table class="table table-center coupons_libs_order_List">
            <colgroup>
                <col width="16%">
                <col width="6%">
                <col width="11%">
                <col width="10%">
                <col width="18%">
                <col width="8%">
                <col width="11%">
                <col width="10%">
                <col width="10%">
            </colgroup>
            <tbody>
                <tr>
                    <th>优惠码</th>
                    <th style="padding:0;" class="status_th"><a href="javascript:void(0);" style="padding:8px 5px;">状态<span class="sort-btn"><i class="iconfont iconfont_status iconfont_s">&#xe622;</i><i class="iconfont iconfont_status iconfont_s">&#xe623;</i></span></a>
                    <th>使用者账号<span class="sort-btn"></span></th>
                    <th>所购商品<span class="sort-btn"></span></th>
                    <th>优惠内容<span class="sort-btn"></span></th>
                    <th>原价<span class="sort-btn"></span></th>
                    <th>实付<span class="sort-btn"></span></th>
                    <th>发放日期<span class="sort-btn"></span></th>
                    <th style="padding:0;" class="usetime_th"><a href="javascript:void(0);" style="padding:8px 5px;">使用日期<span class="sort-btn"><i class="iconfont iconfont_status iconfont_u">&#xe622;</i><i class="iconfont iconfont_status iconfont_u">&#xe623;</i></span></a>
                </tr>
                <tr><td colspan="9">暂无数据</td></tr>
            </tbody>
        </table>
     	<div class="pages pagination_libs_order"></div>
     	<input type="hidden" id="page_libs_order" value="1">
     	<input type="hidden" id="statusOrder" value="-">
     	<input type="hidden" id="usetimeOrder" value="-">
     	<input type="hidden" id="order_patchid" value="0">
    </div>
</div>
<!-- <div class="exportExcel" style="display: none;"> -->
<!-- 	  <div class="s_box"> -->
<!--             <div class="top"> -->
<!--                 <h3>导出优惠码</h3> -->
<!--                 <div class="right"> -->
<!--                     <span>说明：点击导出直接从浏览器下载</span> -->
<!--                     <a href="javascript:;">导出Excel文件</a> -->
<!--                 </div> -->
<!--             </div> -->
<!--             <div class="cont"> -->
<!--                 <div class="tabCont"> -->
<!--                		    <form id="searchForm"> -->
<!--                         <table class="table table-center coupons_libs_List"> -->
<!--                             <colgroup> -->
<!--                                 <col width="20%"> -->
<!--                                 <col width="30%"> -->
<!--                                 <col width="30%"> -->
<!--                                 <col width="20%"> -->
<!--                             </colgroup> -->
<!--                             <tbody> -->
<!--                                 <tr class="tr1"> -->
<!--                                    <td>优惠码</td> -->
<!--                                    <td>优惠范围</td> -->
<!--                                    <td>优惠对象</td> -->
<!--                                    <td>使用期限</td> -->
<!--                                 </tr> -->
<!--                                 <tr><td colspan="4">暂无数据</td></tr> -->
<!--                             </tbody> -->
<!--                         </table> -->
<!--                        </form> -->
<!--                         <div class="pages pagination_libs"></div> -->
<!--                 </div> -->
<!--             </div> -->
<!--            <button class="btn btn-sm btn-primary  manage-button closeExport" >关闭</button> -->
<!--         </div> -->
<!-- </div> -->



<div class="add-alert-content lead-out-code exportExcel">
    <div class="s_box">
        <div class="top">
            <h3>导出优惠码</h3>
            <i class="s_right iconfont closeExport">&#xe610;</i>
        </div>
        <div class="cont">
            <div class="tabCont">
                 <form id="searchForm">
                        <table class="table table-center coupons_libs_List">
                            <colgroup>
                                <col width="30%">
                                <col width="35%">
                                <col width="35%">
                            </colgroup>
                            <tbody>
                                <tr class="tr1">
                                   <td>优惠码</td>
                                   <td>优惠范围</td>
                                   <td>使用期限</td>
                                </tr>
                                <tr><td colspan="4">暂无数据</td></tr>
                            </tbody>
                        </table>
                       </form>
                        <div class="pages pagination_libs"></div>
            </div>
        </div>
        <div class="right">
            <span>说明：点击导出直接从浏览器下载</span>
            <a href="javascript:;" class="excelExport">导出Excel文件</a>
        </div>
    </div>
</div>
<!-- ajax加载中div开始 -->
<div class="loading lp-units-loading" style="display:none">
	<p><i></i>加载中,请稍后...</p>
</div>
<div class="loading lp-units-loading send" style="display:none">
	<p><i></i>发送中,请稍后...</p>
</div>
<div class="loading-bg lp-units-loading-bg" style="display:none"></div>
<!--  ajax加载中div结束 -->
<div class="layer-tips-bg"></div>

<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/splitmarket.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/service/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/service/bootstrap-datepicker.zh-CN.min.js"></script>
<script type="text/javascript" src="<%=rootPath %>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/coupon/coupon-manage.js"></script>
<script src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>

<%-- <script type="text/javascript" src="<%=rootPath%>/javascripts/common/common.js"></script> --%>

</body>

</html>