<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>
<% String rootPath=request.getContextPath(); %>

<script type="text/javascript" src="<%=rootPath%>/javascripts/resource.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/resource/project/onePro.js"></script>
<style>
	.save_{
		padding-left:7px;
	}
</style>	
<div class="main-content subject-main-content" >
<div id="sortTable">
<c:forEach var="one" items="${newOneList }">
<c:if test="${one.relationStatus == 1 && one.status != 0}">
	<div class="block ui-state-default" data-type="stop" style="background:#EEEEEE" data-id="${one.id}">
		<div class="hidden">
	<input type="hidden" class="oneId" value="${one.id }" />
			<input type="hidden" class="parentCode" value="${one.itemCode }" />
<input type="hidden" class="oneStatus" value="${one.relationStatus }" />
		<div class="b-title">
			<div class="icon"><i class="iconfont"><img src="http://${ImagePath }/${one.itemBackPic }"/></i></div>
			<div class="tt">
				<div class="h3"><b>
        	<c:if test="${fn:length(one.itemName) > 7}">
        		${fn:substring(one.itemName,0,7) }...
        	</c:if>
        	<c:if test="${fn:length(one.itemName) <= 7}">
        		${one.itemName }
        	</c:if>
				</b><i class="iconfont btn-del-pro">&#xe626;</i><i class="iconfont btn-edit-pro">&#xe625;</i></div>
				<div class="c">
					<c:if test="${fn:length(one.remark) > 21 }">
						<span title="${one.remark }">${fn:substring(one.remark,0,21) }......</span> 
					</c:if>
					<c:if test="${fn:length(one.remark) <= 21 }">
						<span>${one.remark }</span>
					</c:if>
    				</div>
    			</div>
    			<div class="line"></div>
    		</div>
    		<div class="b-content">
    		</div>
<div class="b-btns">
    			<i class="iconfont left btn-ok btn-switch" style="color:red;" title="已停用，点击启用" name="oneStatus">&#xe635;</i>
    			<i class="iconfont right add-sub" title="添加子类" name="twoToCreate">&#xe6c2;</i>
    			<%-- <c:if test="${tagShow==1 }">
    			<div class="tag-manage tagBtn" itemOneId="${one.id }">
			        <i class="iconfont">&#xe6c4;</i>
			    </div>
			    </c:if> --%>
			<!-- <div class="btn-add" name="twoToCreate">+</div> -->
		</div>
		</div>
	</div>
</c:if>
<c:if test="${one.relationStatus == 0 }">
	<div class="block active ui-state-default" data-type="start" data-id="${one.id}">
	<input type="hidden" class="oneCode" value="${one.itemCode}"/>
	<input type="hidden" class="oneId" value="${one.id }" />
<input type="hidden" class="oneStatus" value="${one.relationStatus }" />
		<div class="b-title">
			<div class="icon"><i class="iconfont"><img src="http://${ImagePath }/${one.itemBackPic }"/></i></div>
			<div class="tt">
				<div class="h3"><b>
        	<c:if test="${fn:length(one.itemName) > 7}">
        		${fn:substring(one.itemName,0,7) }...
        	</c:if>
        	<c:if test="${fn:length(one.itemName) <= 7}">
        		${one.itemName }
        	</c:if>
				</b><i class="iconfont btn-del-pro" data-id="${one.id }">&#xe626;</i><i class="iconfont btn-edit-pro" data-id="${one.id }">&#xe625;</i></div>
				<div class="c">
					<c:if test="${fn:length(one.remark) > 21 }">
						<span title="${one.remark }">${fn:substring(one.remark,0,21) }......</span>
					</c:if>
					<c:if test="${fn:length(one.remark) <= 21 }">
						<span>${one.remark }${one.itemCode}</span>
					</c:if>
    				</div>
    			</div>
    			<div class="line"></div>
    		</div>
    		<div class="b-content">
    		</div>
<div class="b-btns b-btns-new">
    			<i class="iconfont left btn-ok btn-switch" title="已启用，点击停用" name="oneStatus">&#xe636;</i>
    			<%-- <c:if test="${tagShow==1 }">
    			<c:if test="${tag3Show==1 || tag4Show==1 }">
    				<input type="button" class="btn btn-default btn-primary tagBtn" itemOneId="${one.id }"  value="标签管理"/> 
    			</c:if> --%>
    			<i class="iconfont right add-sub" title="添加子类" name="twoToCreate">&#xe6c2;</i>
    			<%--<c:if test="${tag3Show==1 || tag4Show==1 }">--%>
    			<%--<div class="tag-manage tagBtn" itemOneId="${one.id }">--%>
			        <%--<i class="iconfont" title="添加标签">&#xe6c4;</i>--%>
			    <%--</div>--%>
			    <%--</c:if>--%>
    			<!-- <div class="btn-add" name="twoToCreate" title="添加学科小类">+</div> -->
    		</div>
    		</input>
    	</div>
    </c:if>
</c:forEach>
<c:forEach var="one" items="${oneList }">
	<div class="block ui-state-default" data-type="stop" style="background:#EEEEEE" data-id="${one.id}">
	<input type="hidden" class="oneId" value="${one.id }" />
	<input type="hidden" class="oneStatus" value="${one.relationStatus }" />
		<div class="b-title">
			<div class="icon"><i class="iconfont"><img src="http://${ImagePath }/${one.itemBackPic }"/></i></div>
			<div class="tt">
				<div class="h3"><b>
        	<c:if test="${fn:length(one.itemName) > 7}">
        		${fn:substring(one.itemName,0,7) }...
        	</c:if>
        	<c:if test="${fn:length(one.itemName) <= 7}">
        		${one.itemName }
        	</c:if>
				</b><i class="iconfont btn-del-pro">&#xe626;</i><i class="iconfont btn-edit-pro">&#xe625;</i></div>
				<div class="c">
					<c:if test="${fn:length(one.remark) > 21 }">
						<span title="${one.remark }">${fn:substring(one.remark,0,21) }......</span>
					</c:if>
					<c:if test="${fn:length(one.remark) <= 21 }">
						<span>${one.remark }</span>
					</c:if>
    				</div>
    			</div>
    			<div class="line"></div>
    		</div>
    		<div class="b-content">
    		</div>
<div class="b-btns b-btns-new">
    			<i class="iconfont left btn-ok btn-switch" style="color:red;" title="已停用，点击启用" name="oneStatus">&#xe635;</i>
    			<i class="iconfont right add-sub" title="添加子类" name="twoToCreate">&#xe6c2;</i>
    			<%-- <c:if test="${tagShow==1 }">
    			<div class="tag-manage tagBtn" itemOneId="${one.id }">
			        <i class="iconfont">&#xe6c4;</i>
			    </div>
			    </c:if> --%>
			<!-- <div class="btn-add" name="twoToCreate">+</div> -->
		</div>
	</div>
</c:forEach>
</div>
	<div class="block active" id="addOneItem">
		<div class="b-btns" style="margin-left:-150px;margin-bottom:120px;">
			<div class="btn-addss btn-pro" id="createPro" title="添加分类"><i class="iconfont"  style="font-size:80px">&#xe61c;</i></div>
		</div>
	</div>
</div>


<!-- 添加级别 -->
<div class="add-subs-layer none" id="addPro" style="height:340px">
<input type="hidden" value="" id="addOrUpdateItemid"/>
<input type="hidden" id="status"/>
    <p class="c">
        <span class="l-title">分类名称：</span>
        <span class="l-content"><input type="text" id="oneItemName" size="30" maxlength="14"></span>
    </p>
    <p class="c">
        <span class="l-title">分类编码：</span>
        <span class="l-content">
        	  <span class="l-content"><input type="text" id="itemCode" size="30" maxlength="14"></span>
        </span>
        <span style="color:red;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;已输入<span id="remarkHint" >0</span>个字，不多于128个字</span>
    </p>
    <p class="c">
        <span class="l-title">分类图标：</span>
        <span class="l-content itemiconone">
			<c:forEach var="i" items="${iconList }" varStatus="status">
			<c:if test="${status.index <= 3 }">
			<span class="item-icons" style="width:60px;height:30px;">
				<img src="http://${ImagePath }/${i.iconBackUrl }" width='20px' data-url="${i.iconUrl }" title="${i.iconName }" style="padding:5px;">
			</span>
			</c:if>
			</c:forEach>
        </span>
        <span style="width:60px;height:300px;" id="jia">
			<img src="<%=rootPath%>/images/jia.jpg" width='40px'>
		</span>
		<a href="javascript:;" class="btn-nexticon"><span style="font-size:14px;">换一批</span></a>
		<input type="hidden" value="${nowpage }" id="iconpage"/>
		<input type="hidden" value="${pagecount }" id="pagecount"/>
    </p>
    <p class="c">
        <span class="l-title">&nbsp;</span>
        <span class="l-content itemiconone">
			<c:forEach var="i" items="${iconList }" varStatus="status">
			<c:if test="${status.index > 3 }">
			<span class="item-icons" style="width:60px;height:30px;">
				<img src="http://${ImagePath }/${i.iconBackUrl }" width='20px' data-url="${i.iconUrl }" title="${i.iconName }" style="padding:5px;">
			</span>
			</c:if>
			</c:forEach>
        </span>
    </p>
    <p class="c">
        <span class="l-title">&nbsp;</span>
        <span class="l-content">
            <a href="javascript:;" class="btn btn-sm btn-primary" id="addOne">确定</a>
            <a id="oneCancel" href="javascript:;" class="btn btn-sm btn-default btn-cancel">取消</a>
        </span>
    </p>
</div>

	<div class="add-subs-layer stop-subs" id="stopPanel">
		<p class="c">
			<span class="l-description"> <em class="alert-tips">该学科下有在售的课程，如果停用、删除学科，这些课程将不可见，确认停用该学科吗？</em>
			</span>
		</p>
		<p class="c">
			<table id="classHint">
			</table>
		</p>
		<p class="c text-center">
			<a href="javascript:;"
				class="btn btn-sm btn-primary btn-stop btn-primary">继续</a>
			<a id="cancel" href="javascript:;"
				class="btn btn-sm btn-default btn-cancel btn-primary">返回</a>
		</p>
	</div>
	<div class="add-subs-layer-bg"></div>

	
	<div class="q-box tagManage none" >
        <div class="title_q">
            <h3>标签管理</h3>
            <i class="iconfont closeBtn">&#xe610;</i>
        </div>
        <div class="content">
            <div class="trans">
               <i class="iconfont prev prev_tag" style=" height: 26px;">&#xe650;</i>
               <i class="iconfont next next_tag" style=" height: 26px;">&#xe651;</i>
               <div class="list-cont" style="margin-left: 20px;width: 675px;">
                <ul class="list clear _itemSecondId" style="width:20000px;margin-left:0px;">
                </ul>
                </div>
            </div>
            <c:if test="${tag3Show==1 }">
            <div class="main">
                <h3>所属标签</h3>
                <div style="border: 1px solid #CCCCCC;height: 135px;overflow:auto;">
	                <ul class="main_list clear tag_1">
	                </ul>
                </div>
            </div>
            </c:if>
            <c:if test="${tag4Show==1 }">
                 <div class="main">
                <h3>二级标签</h3>
                <div style="border: 1px solid #CCCCCC;height: 135px;overflow:auto;">
	                <ul class="main_list clear tag_2">
	                </ul>
                </div>
            </div>
			</c:if>
        </div>
    </div>

	
	<!-- ajax加载中div开始 -->
	<div class="loading lp-units-loading" style="display: none">
		<p>
			<i></i>加载中,请稍后...
		</p>
	</div>
	<div class="loading-bg lp-units-loading-bg" style="display: none"></div>
	<!--  ajax加载中div结束 -->
	<script>
		$(function() {
			$selectSubMenu('resource_item');
			$("#sortTable" ).sortable({
	            update:function(event,ui){
					var sortMap = [];
					$("#sortTable").find(".ui-state-default").each(function(i){
						var oneItem={};
						oneItem.id=$(this).data("id");
						oneItem.sort=$(this).index()+1;
						sortMap.push(oneItem);
					});
					 $.ajax({ 
				 		  type: "post", 
				 		  url : rootPath+"/sysConfigItem/orderOneItem", 
				 		  data: "list="+JSON.stringify(sortMap),
						  beforeSend:function(XMLHttpRequest){
				            $(".loading").show();
				            $(".loading-bg").show();
				          },
				 		  success: function(data){
								$.msg("修改成功");
						  },
				 		  error : function(){
				 			  $.msg("操作失败");
				 		  },
				 		  complete:function(XMLHttpRequest,textStatus){
							  $(".loading").hide();
					          $(".loading-bg").hide();
					      }
				 	 });
				},
				delay: 100,
	      }).disableSelection();
			$('body').on('focus','input',function(){
				
			});
		});
	</script>
	<script type="text/javascript" src="<%=rootPath%>/javascripts/tagmanagement.js"></script>
	<script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
<%-- <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery-ui.js"></script>
<script type="text/javascript" src="<%=rootPath%>/javascripts/resource/project/zeroPro.js"></script> --%>
