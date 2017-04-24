<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html lang="zh-cn">
<head>
<%@include file="/decorators/import.jsp" %>
    <title>营销设置</title>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/manage.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/system.css"/>
    <link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/marketing.css"/>
    <link rel="stylesheet" type="text/css" href="<%=rootPath %>/plugins/bootstrap-fileupload/bootstrap-fileupload.css" />
    
</head>
<body>
<jsp:include page="/WEB-INF/jsp/menu/menu_system.jsp"></jsp:include>

<input type="hidden" value="" id="markId"/>
<div class="u-wrap set-system">
    <div class="heading">
        <h2 class="h5">营销设置</h2>
    </div>

	<div class="u-wrap set-system">
		<div class="mainbackground space">
			<div class="set-focus">
				<ul>
					<li>
						<div class="focus-title">营销QQ<span class="markqq"></div>
						<div class="focus-content ">
							<div class="focus-left">
								<p id="qqTypeLists">
									<input type="radio" name="type" value="MARKET_QQ_PERSON"/>个人QQ
<!-- 								<input type="radio" name="type" value="company"/>企业QQ -->
									<input type="radio" name="type" value="MARKET_QQ_MARKET"/>营销QQ
								</p>
								<p id="qq">
<!-- 									<span class="t">QQ号码</span> -->
<!-- 									<input class="c" type="text" id="qq" value=""/> -->
								</p>
								<p id="qqkey">
<!-- 									<span class="t">营销QQ-KEY值</span> -->
<!-- 									<input class="c" type="text" id="qqkey" value=""/> -->
								</p>
								<p class="btns">
									<input type="button" marks="qqmark" class="btn btn-success" value="保存 "/>
<!-- 								<input type="button" class="btn btn-default" value="取消 "/> -->
								</p>
							</div>
							<div class="focus-right"></div>
						</div>
					</li>
					<li>
						<div class="focus-title">微信<span class="weixinmark"></span></div>
						<div class="focus-content ">
							<div class="focus-left">
								<p id="weixinhao">
<!-- 									<span class="t">公众号</span> -->
<!-- 									<input type="text" id="weixinNo" value=""/> -->
								</p>
								<p class="btns">
									<input type="button" marks="wxmark" class="btn btn-success" value="保存 "/>
								</p>
							</div>
							<div class="focus-right">
								<img class="qrcode wxewm" alt="上传二维码" id="imageObject" ids="" src=""/>
								<input type="file" name="imgData"id="imgData" onchange="savePic('wx')"/>
							</div>
						</div>
					</li>
					<li>
						<div class="focus-title">新浪微博<span class="xinlang"></span></div>
						<div class="focus-content ">
							<div class="focus-left">
								<p id="xinlangNo">
<!-- 									<span class="t">微博号</span> -->
<!-- 									<input class="c" type="text" id="weiboNo" value=""/> -->
								</p>
								<p id="xinlangUrl">
<!-- 									<span class="t">微博地址</span> -->
<!-- 									<input class="c" type="text" id="weiboUrl" value=""/> -->
								</p>
								<p class="btns">
									<input type="button" marks="xlmark" class="btn btn-success" value="保存 "/>
								</p>
							</div>
							<div class="focus-right">
								<img class="qrcode xlewm" alt="上传二维码" id="imageObject1" ids="" src=""/>
								<input type="file" name="imgDatas" id="imgDatas" onchange="savexlPic()"/>
							</div>
						</div>

					</li>
					<li style="display:none;">
						<div class="focus-title">乐语设置<span class="leyu"></span></div>
						<div class="focus-content ">
							<div class="focus-left">
								<p id="leyuUrl">
<!-- 									<span class="t">乐语js文件地址</span> -->
<!-- 									<span class="c">http://gate.looyu.com/</span> -->
<!-- 									<input class="c" type="text" value=""/> -->
								</p>
								<p id="leyuPhone">
<!-- 									<span class="t">客服组电话</span> -->
<!-- 									<input class="c" type="text"/> -->
								</p>
								<p class="btns">
									<input type="button" marks="lymark" class="btn btn-success" value="保存 "/>
								</p>
							</div>
							<div class="focus-right">
							
							</div>
						</div>

					</li>
					<li>
						<div class="focus-title">客服电话<span class="services"></span></div>
						<div class="focus-content ">
							<div class="focus-left">
							<p id="kfPhone">
							</p>
							<p id="fwTime">
							
							</p>
								<p class="btns">
									<input type="button" marks="kfmark" class="btn btn-success" value="保存 "/>
								</p>
							</div>
							<div class="focus-right"></div>
						</div>

					</li>
				</ul>
			</div>
		</div>
	</div>
	</div>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/system/markting.js"></script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/system.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/plugins/bootstrap-fileupload/bootstrap-fileupload.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
</body>
</html>