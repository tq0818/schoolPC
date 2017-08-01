<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<ul class="subject-box">
    <li class="sub-tit">名称</li>
    <li class="sub-tit">编码</li>
</ul>
<span class="add-subs" style="display: none;">
    <input type="text" class="twoName sub-input" size="14" maxlength="10">
    <input type="text" class="itemCode sub-input" size="14" maxlength="10">
    <input type="button" class="btn btn-mini btn-default" name="twoAdd" value="保存" data-pid="${oneItemId }" data-pcode="${oneItemCode}">
    <input type="button" class="btn btn-mini btn-default" name="twoCancel" value="取消">
</span>
<ul class="eachTwoItem">
<c:forEach var="two" items="${twoProList }">
<li class="ui-state-default" data-id="${two.id}">
	<span class="r-subs-title">
        <em>
            <span class="sub-input itemname">
        	<c:if test="${fn:length(two.itemName) > 15}">
        		${fn:substring(two.itemName,0,14) }...
        	</c:if>
        	<c:if test="${fn:length(two.itemName) <= 15}">
        		${two.itemName }
        	</c:if>
            </span>
            <span class="sub-input itemcode">
        	<c:if test="${fn:length(two.itemCode) > 15}">
                ${fn:substring(two.itemCode,0,14) }...
            </c:if>
        	<c:if test="${fn:length(two.itemCode) <= 15}">
                ${two.itemCode }
            </c:if>
            </span>
        </em>
        <span class="b" style="margin-top:-5px">
        <c:if test="${two.relationStatus == 0 }">
        <i class="iconfont btn-edit-two">&#xe625;</i>
        <i class="iconfont btn-del-two">&#xe626;</i>
        </c:if>
        <c:if test="${(two.relationStatus == 1 || two.relationStatus == null) && two.status != 0}">
        	<i class="iconfont closed btn-switch btn-two" title="已停用，点击启用">&#xe641;</i>
        </c:if>
        <c:if test="${two.relationStatus == 0 }">
        	<i class="iconfont open btn-switch btn-two" style="height:24px;" title="已启用，点击停用">&#xe642;</i>
        </c:if>
        <input type="hidden" class="pid" value="${two.parentId }"/>
        <input type="hidden" class="pcode" value="${two.parentCode }"/>
        <input type="hidden" class="twoId" value="${two.id }"/>
        <input type="hidden" class="twoStatus" value="${two.status }"/>
        </span>
    </span>
    </li>
</c:forEach>
<c:forEach var="two" items="${newTowProList }">
<li class="ui-state-default">
	<span class="r-subs-title">
        <em>
        	<c:if test="${fn:length(two.itemName) > 15}">
        		${fn:substring(two.itemName,0,14) }...
        	</c:if>
        	<c:if test="${fn:length(two.itemName) <= 15}">
        		${two.itemName }
        	</c:if>
        </em>
        <span class="b" style="margin-top:-5px">
        <c:if test="${onerel != null && onerel.delFlag == 0 && onerel.trueDelFlag == 0}">
        	<i class="iconfont closed btn-switch btn-two" title="已停用，点击启用">&#xe641;</i>
       		<!-- <i class="iconfont normal closed btn-switch btn-two" title="已停用，点击启用">&#xe641;</i> -->
        </c:if>
        <input type="hidden" class="pid" value="${two.parentId }"/>
        <input type="hidden" class="twoId" value="${two.id }"/>
        <input type="hidden" class="twoStatus" value="${two.status }"/>
        </span>
    </span>
    </li>
</c:forEach>
</ul>
<%-- 
<c:forEach var="two" items="${newTowProList }">
	<span class="r-subs-title">
        <em>${two.itemName }</em>
        <a href="javascript:;" class="btn btn-mini btn-link btn-switch btn-two" style="text-decoration: none">
        	<c:if test="${(two.relationStatus == 1 || two.relationStatus == null) && two.status != 0}"><i class="iconfont normal close">&#xe604;</i>&nbsp;&nbsp;已停用</c:if>
        </a>
        <input type="hidden" class="pid" value="${two.parentId }"/>
        <input type="hidden" class="twoId" value="${two.id }"/>
        <input type="hidden" class="twoStatus" value="1"/>
    </span>
</c:forEach>  --%>
<script type="text/javascript" src="<%=rootPath%>/javascripts/resource/project/twoPro.js"></script>
<script> 
$(function(){
	//拖拽效果
	$.each($(".eachTwoItem"),function(i,obj){
		addMove($(obj));
	});
	
	function addMove(node){
		node.sortable({
            update:function(event,ui){
				var sortMap = [];
				node.find(".ui-state-default").each(function(i){
					var twoItem={};
					twoItem.id=$(this).data("id");
					twoItem.sort=$(this).index()+1;
					sortMap.push(twoItem);
				})
				myAjax("/sysConfigItem/orderOneItem","list="+JSON.stringify(sortMap),function(data){
					$.msg("修改成功");
				});
			},
			delay: 100,
      }).disableSelection();
	};
	
	var myAjax = function(url,dataInfo,func){
		 $.ajax({ 
	 		  type: "post", 
	 		  url : rootPath+url, 
	 		  data: dataInfo,
			  beforeSend:function(XMLHttpRequest){
	            $(".loading").show();
	            $(".loading-bg").show();
	          },
	 		  success: func,
	 		  error : function(){
	 			  $.msg("操作失败");
	 		  },
	 		  complete:function(XMLHttpRequest,textStatus){
				  $(".loading").hide();
		          $(".loading-bg").hide();
		      }
	 	  });
	};
});
</script>