<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <div class="menu-nav-title">
    <ul class="row clear" style="background-color: #F6F6F6;">
        <li class="with20">内容名称</li>
        <li class="with50">链接</li>
        <li class="with10">方式</li>
        <li class="with20">操作</li>
    </ul>
</div>
<c:forEach items="${IndexheadTitleList }" var="list" varStatus="status">
	<div class="section n true" ids="${list.id }" sort="${list.sequence }">
                 <div class="block l-menu">
                     <div class="title-wrap">
                         <ul class="row clear">
                             <li class="with20">
                                 <i class="iconfont nav-name-icon" style="cursor: move;"> &#xe630;</i>
                                 <span class="title-info">${list.name }</span>
                             </li>
                             <li class="with50" class="link-address">${list.url }</li>
                             <li class="with10">
                             	<a href="javascript:void(0);" ids="${list.id }" sta="${list.validFlag}"
					class="headStatus" style="text-decoration: none;">
                             	<c:if test="${list.validFlag==1 }">
                            			<i class="iconfont normal open">&#xe603;</i>
                                 	<span class="open">已启用</span>
						</c:if> 
						<c:if test="${list.validFlag!=1 }">
							<i class="iconfont normal close" style="color: #999999;">&#xe604;</i>
							<span class="close">已禁用</span>
						</c:if>
						</a>
                             </li>
                             <li class="with20">
                             <div class="icon-wrap1">
                                 <i class="iconfont edit-icon mar-lr5">&#xe628;</i>
                                 <i class="iconfont mar-lr5 delete-icon deleteparents_icon" ids="${list.id }">&#xe67e;</i>
                                 <i class="iconfont mar-lr5 add-title" ids="${list.id }">&#xe67d;</i>
                             </div>
                             <div class="icon-wrap2">
                                 <c:if test="${list.count>0 }">
                                 	 <i class="iconfont mar-lr5 slide">&#xe67f;</i>
                                 </c:if>
                             </div>
                             </li>
                         </ul>
                         <div class="field">
                             <div class="content father_content">
                                 <p>
                                     <span class="name">显示名称：</span>
                                     <input type="text" value="${list.name }" class="title-content titleName">
                                 </p>
                                 <p>
                                     <span class="name">链接地址：</span>
                                     <em>http://</em>
                            <span>
                                <input type="text" class="address weburl" value="${fn:substring(list.url, 7,fn:length(list.url))}" placeholder="示例:http://yunduoketang.com/course">
                                <a href="javascript:;" class="bt btn-ico">...</a>
                            </span>
                                 </p>
                                 <p class="type_url">
                                     <span class="name">链接类型：</span>
                                     <c:choose>
								<c:when test="${list.openType=='self' }">
									<input type="radio" name="openType${list.id }" checked="checked" value="self" />本页打开
						            <input type="radio" name="openType${list.id }" value="blank" />弹出新窗口
					             </c:when>
								<c:otherwise>
									<input type="radio" name="openType${list.id }" value="self" />本页打开
					                <input type="radio" name="openType${list.id }" checked="checked" value="blank" />弹出新窗口
					            </c:otherwise>
							</c:choose>
                                 </p>
                                 <p>
                                     <input type="button"  value="保存" class="btn btn-primary save_headtitle" ids="${list.id }" mark="other"/>
                                     <input type="button"  value="取消" class="btn btn-primary">
                                 </p>
                             </div>
                         </div>
                     </div>
                 </div>
             <div class="block s-menu">
            <c:forEach items="${sysPageChildHead }" var="pageChildList">
            	   <c:if test="${pageChildList.parentId==list.id }">
                     <div class="title-wrap">
                         <ul class="row clear">
                             <li class="with16">
                                 <span class="title-info">${pageChildList.name }</span>
                             </li>
                             <li class="with55 link-address">${pageChildList.url }</li>
                             <li class="with12">
                                 
                             </li>
                             <li class="with17">
                                 <i class="iconfont edit-icon mar-lr5">&#xe628;</i>
                                 <i class="iconfont mar-lr5 delete-icon" onclick="deleteChild(${pageChildList.id})">&#xe67e;</i>
                             </li>
                         </ul>
                         <div class="field">
                             <div class="content father_content">
                                 <p>
                                     <span class="name">显示名称：</span>
                                     <input type="text" value="${pageChildList.name }" class="title-content titleName">
                                 </p>
                                 <p>
                                     <span class="name">链接地址：</span>
                                     <em>http://</em>
                                     <span>
                                         <input type="text" value="${fn:substring(pageChildList.url, 7,fn:length(pageChildList.url))}" class="address weburl" placeholder="示例:http://yunduoketang.com/course">
                                         <a href="javascript:;" class="bt btn-ico">...</a>
                                     </span>
                                 </p>
                                 <p class="type_url">
                                     <span class="name">链接类型：</span>
                                     <c:choose>
								<c:when test="${pageChildList.openType=='self' }">
									<input type="radio" name="openType${pageChildList.id }" checked="checked" value="self" />本页打开
				                    <input type="radio" name="openType${pageChildList.id }" value="blank" />弹出新窗口
		                        </c:when>
								<c:otherwise>
									<input type="radio" name="openType${pageChildList.id }" value="self" />本页打开
			                        <input type="radio" name="openType${pageChildList.id }" checked="checked" value="blank" />弹出新窗口
		                         </c:otherwise>
							</c:choose>
                                 </p>
                                 <p>
                                     <input type="button"  value="保存" class="btn btn-primary save_headtitle" ids="${pageChildList.id }" mark="other"/>
                                     <input type="button"  value="取消" class="btn btn-primary"/>
                                 </p>
                             </div>
                         </div>
                     </div>
               </c:if>
          </c:forEach> 
          </div>
<!--              	二级标题添加 -->
<!--                 结束    -->
     </div>
</c:forEach>
 <div class="new-add-btn">
      <i class="iconfont new-add-icon">&#xe61c;</i>
      <span>新增</span>
  </div>
<script type="text/javascript">
	$(function(){
         //排序
         $('#navbarconfigs').sortable({
				placeholder: "ui-state-highlight",
				items: "div.section:not(.ui-state-disabled)",
				update:function(event,ui){
					var sortMap=new Array();
					$("#navbarconfigs").find("div.section").each(function(i){
						var syspage={};
						syspage.id=$(this).attr("ids");
						syspage.sequence=$(this).index()+1;
						sortMap.push(syspage);
					})
					$.ajax({
					url: rootPath+"/sysPageHeadFoot/orderSortTitle",
					data: "list="+JSON.stringify(sortMap),
					type: "post",
					dataType: "json",
					success:function(jsonData){
						 queryDataTitle();
	         			 queryOneHeadTitleDatas();
					}
				})
				},
				delay: 200
			}).disableSelection();
	})
</script>
