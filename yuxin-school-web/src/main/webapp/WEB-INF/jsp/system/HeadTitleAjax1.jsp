<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 <div class="menu-nav-title">
    <ul class="row clear" style="background-color: #F6F6F6;">
        <li class="with20">一级导航名称</li>
        <li class="with50">链接地址</li>
        <li class="with10">状态</li>
        <li class="with20">操作</li>
    </ul>
</div>
<c:forEach items="${IndexheadTitleList }" var="list" varStatus="status">
	<c:if test="${status.first }">
		 <div class="section n true ui-state-disabled" ids="${list.id }" sort='${list.sequence }'>
                    <div class="block l-menu">
                        <div class="title-wrap" data-name="${list.name }">
                            <ul class="row clear">
                                <li class="with20">
                                    <span class="title-info in${list.id }" data-name="${list.name }">${list.name }</span>
                                </li>
                                <li class="with50" class="link-address">该导航打开的地址为网校首页，只能修改导航名称，不能停用禁用</li>
                                <li class="with10"><span class="open">已启用</span></li>
                                <li class="with20">
                                    <i class="iconfont edit-icon mar-lr5">&#xe628;</i>
                                </li>
                            </ul>
                            <div class="field">
                                <div class="content">
                                    <p>
                                        <span class="name">显示名称：</span>
                                        <input type="text" class="title-content titleName" value="${list.name }">
                                    </p>
                                    <p>
                                        <input type="button"  value="保存" class="btn btn-primary save_headtitle" ids="${list.id }" mark="no"/>
                                        <input type="button"  value="取消" class="btn btn-primary"/>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="block s-menu">
                    </div>
                </div>
	</c:if>
	<c:if test="${status.count==2 }">
	 <div class="section n true ui-state-disabled" ids="${list.id }" sort='${list.sequence }'>
                    <div class="block l-menu">
                        <div class="title-wrap" data-name="${list.name }">
                            <ul class="row clear">
                                <li class="with20">
                                    <span class="title-info in${list.id }" data-name="${list.name }">${list.name }</span>
                                </li>
                                <li class="with50" class="link-address"></li>
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
                                <li class="with20"><i class="iconfont edit-icon mar-lr5">&#xe628;</i></li>
                            </ul>
                            <div class="field">
                                <div class="content">
                                    <p>
                                        <span class="name">显示名称：</span>
                                        <input type="text" value="${list.name }" class="title-content titleName">
                                    </p>
                                    <p>
                                        <input type="button"  value="保存" class="btn btn-primary save_headtitle" ids="${list.id }" mark="no">
                                        <input type="button"  value="取消" class="btn btn-primary">
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="block s-menu">
                    </div>
                </div>
	</c:if>
	<c:if test="${status.count>=3 }">
		<c:choose>
			<c:when test="${list.pageHeadType=='SYS_PAGE_HEAD_NEWS'}">
			 <div class="section n true" ids="${list.id }" sort='${list.sequence }'>
                    <div class="block l-menu">
                        <div class="title-wrap" data-name="${list.name }">
                            <ul class="row clear">
                                <li class="with20">
                                	<i class="iconfont nav-name-icon" style="cursor: move;"> &#xe630;</i>
                                    <span class="title-info in${list.id }" data-name="${list.name }">${list.name }</span>
                                </li>
                                <li class="with50 link-address"></li>
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
                                <li class="with20"><i class="iconfont edit-icon mar-lr5">&#xe628;</i></li>
                            </ul>
                            <div class="field">
                                <div class="content">
                                    <p>
                                        <span class="name">显示名称：</span>
                                        <input type="text" value="${list.name }" class="title-content titleName">
                                    </p>
                                    <p>
                                        <input type="button"  value="保存" class="btn btn-primary save_headtitle" ids="${list.id }" mark="no"/>
                                        <input type="button"  value="取消" class="btn btn-primary"/>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="block s-menu">
                    </div>
                </div>
			</c:when>
			<c:when test="${list.pageHeadType=='SYS_PAGE_HEAD_TIKU'}">
				<div class="section n true" ids="${list.id }" sort='${list.sequence }'>
                    <div class="block l-menu">
                        <div class="title-wrap" data-name="${list.name }">
                            <ul class="row clear">
                                <li class="with20">
                                	<i class="iconfont nav-name-icon" style="cursor: move;"> &#xe630;</i>
                                    <span class="title-info in${list.id }" data-name="${list.name }">${list.name }</span>
                                </li>
                                <li class="with50 link-address"></li>
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
                                <li class="with20"><i class="iconfont edit-icon mar-lr5">&#xe628;</i></li>
                            </ul>
                            <div class="field">
                                <div class="content">
                                    <p>
                                        <span class="name">显示名称：</span>
                                        <input type="text" value="${list.name }" class="title-content titleName">
                                    </p>
                                    <p>
                                        <input type="button"  value="保存" class="btn btn-primary save_headtitle" ids="${list.id }" mark="no">
                                        <input type="button"  value="取消" class="btn btn-primary">
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="block s-menu">
                    </div>
                </div>
			</c:when>
			<c:when test="${list.pageHeadType=='SYS_PAGE_HEAD_OPENCLASS'}">
				<div class="section n true" ids="${list.id }" sort='${list.sequence }'>
                    <div class="block l-menu">
                        <div class="title-wrap" data-name="${list.name }">
                            <ul class="row clear">
                                <li class="with20">
                                	<i class="iconfont nav-name-icon" style="cursor: move;"> &#xe630;</i>
                                    <span class="title-info in${list.id }" data-name="${list.name }">${list.name }</span>
                                </li>
                                <li class="with50 link-address"></li>
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
                                <li class="with20"><i class="iconfont edit-icon mar-lr5">&#xe628;</i></li>
                            </ul>
                            <div class="field">
                                <div class="content">
                                    <p>
                                        <span class="name">显示名称：</span>
                                        <input type="text" value="${list.name }" class="title-content titleName">
                                    </p>
                                    <p>
                                        <input type="button"  value="保存" class="btn btn-primary save_headtitle" ids="${list.id }" mark="no">
                                        <input type="button"  value="取消" class="btn btn-primary">
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="block s-menu">
                    </div>
                </div>
			</c:when>
			<c:when test="${list.pageHeadType=='SYS_PAGE_HEAD_TEACHER'}">
				<div class="section n true" ids="${list.id }" sort='${list.sequence }'>
                    <div class="block l-menu">
                        <div class="title-wrap" data-name="${list.name }">
                            <ul class="row clear">
                                <li class="with20">
                                	<i class="iconfont nav-name-icon" style="cursor: move;"> &#xe630;</i>
                                    <span class="title-info in${list.id }" data-name="${list.name }">${list.name }</span>
                                </li>
                                <li class="with50 link-address"></li>
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
                                <li class="with20"><i class="iconfont edit-icon mar-lr5">&#xe628;</i></li>
                            </ul>
                            <div class="field">
                                <div class="content">
                                    <p>
                                        <span class="name">显示名称：</span>
                                        <input type="text" value="${list.name }" class="title-content titleName">
                                    </p>
                                    <p>
                                        <input type="button"  value="保存" class="btn btn-primary save_headtitle" ids="${list.id }" mark="no">
                                        <input type="button"  value="取消" class="btn btn-primary">
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="block s-menu">
                    </div>
                </div>
			</c:when>
			<c:when test="${list.pageHeadType=='SYS_PAGE_HEAD_QUESTION'}">
				<div class="section n true" ids="${list.id }" sort='${list.sequence }'>
                    <div class="block l-menu">
                        <div class="title-wrap" data-name="${list.name }">
                            <ul class="row clear">
                                <li class="with20">
                                	<i class="iconfont nav-name-icon" style="cursor: move;"> &#xe630;</i>
                                    <span class="title-info in${list.id }" data-name="${list.name }">${list.name }</span>
                                </li>
                                <li class="with50 link-address"></li>
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
                                <li class="with20"><i class="iconfont edit-icon mar-lr5">&#xe628;</i></li>
                            </ul>
                            <div class="field">
                                <div class="content">
                                    <p>
                                        <span class="name">显示名称：</span>
                                        <input type="text" value="${list.name }" class="title-content titleName">
                                    </p>
                                    <p>
                                        <input type="button"  value="保存" class="btn btn-primary save_headtitle" ids="${list.id }" mark="no">
                                        <input type="button"  value="取消" class="btn btn-primary">
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="block s-menu">
                    </div>
                </div>
			</c:when>
			<c:when test="${list.pageHeadType=='SYS_PAGE_HEAD_CLASS_PACKAGE'}">
				<div class="section n true" ids="${list.id }" sort='${list.sequence }'>
                    <div class="block l-menu">
                        <div class="title-wrap" data-name="${list.name }">
                            <ul class="row clear">
                                <li class="with20">
                                	<i class="iconfont nav-name-icon" style="cursor: move;"> &#xe630;</i>
                                    <span class="title-info in${list.id }" data-name="${list.name }">${list.name }</span>
                                </li>
                                <li class="with50 link-address"></li>
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
                                <li class="with20"><i class="iconfont edit-icon mar-lr5">&#xe628;</i></li>
                            </ul>
                            <div class="field">
                                <div class="content">
                                    <p>
                                        <span class="name">显示名称：</span>
                                        <input type="text" value="${list.name }" class="title-content titleName">
                                    </p>
                                    <p>
                                        <input type="button"  value="保存" class="btn btn-primary save_headtitle" ids="${list.id }" mark="no">
                                        <input type="button"  value="取消" class="btn btn-primary">
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="block s-menu">
                    </div>
                </div>
			</c:when>
			<c:when test="${list.pageHeadType=='SERVICE_STUDENT_ASPIRATIONS'}">
				<div class="section n true" ids="${list.id }" sort='${list.sequence }'>
                    <div class="block l-menu">
                        <div class="title-wrap" data-name="${list.name }">
                            <ul class="row clear">
                                <li class="with20">
                                	<i class="iconfont nav-name-icon" style="cursor: move;"> &#xe630;</i>
                                    <span class="title-info in${list.id }" data-name="${list.name }">${list.name }</span>
                                </li>
                                <li class="with50 link-address"></li>
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
                                <li class="with20"><i class="iconfont edit-icon mar-lr5">&#xe628;</i></li>
                            </ul>
                            <div class="field">
                                <div class="content">
                                    <p>
                                        <span class="name">显示名称：</span>
                                        <input type="text" value="${list.name }" class="title-content titleName">
                                    </p>
                                    <p>
                                        <input type="button"  value="保存" class="btn btn-primary save_headtitle" ids="${list.id }" mark="no">
                                        <input type="button"  value="取消" class="btn btn-primary">
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="block s-menu">
                    </div>
                </div>
			</c:when>
			<c:when test="${list.pageHeadType=='SYS_PAGE_HEAD_MEMBER'}">
				<div class="section n true" ids="${list.id }" sort='${list.sequence }'>
                    <div class="block l-menu">
                        <div class="title-wrap" data-name="${list.name }">
                            <ul class="row clear">
                                <li class="with20">
                                	<i class="iconfont nav-name-icon" style="cursor: move;"> &#xe630;</i>
                                    <span class="title-info in${list.id }" data-name="${list.name }">${list.name }</span>
                                </li>
                                <li class="with50" class="link-address"></li>
                                <c:if test="${list.validFlag==1 }">
                              		<li class="with10"><span class="open">已启用</span></li>
								</c:if> 
								<c:if test="${list.validFlag!=1 }">
									<li class="with10"><span class="close">已禁用</span></li>
								</c:if>
                                <li class="with20">
                                    <i class="iconfont edit-icon mar-lr5">&#xe628;</i>
                                </li>
                            </ul>
                            <div class="field">
                                <div class="content">
                                    <p>
                                        <span class="name">显示名称：</span>
                                        <input type="text" class="title-content titleName" value="${list.name }">
                                    </p>
                                    <p>
                                        <input type="button"  value="保存" class="btn btn-primary save_headtitle" ids="${list.id }" mark="no"/>
                                        <input type="button"  value="取消" class="btn btn-primary"/>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="block s-menu">
                    </div>
                </div>
			</c:when>
			<c:otherwise>
				<div class="section n true" ids="${list.id }" sort="${list.sequence }">
                    <div class="block l-menu">
                        <div class="title-wrap" data-name="${list.name }">
                            <ul class="row clear">
                                <li class="with20">
                                    <i class="iconfont nav-name-icon" style="cursor: move;"> &#xe630;</i>
                                    <span class="title-info" data-name="${list.name }">${list.name }</span>
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
                                        <input type="text" value="${list.name }" class="title-content titleName" >
                                    </p>
                                    <p>
                                        <span class="name">链接地址：</span>
                                        <!--  <em>http://</em>  ${fn:substring(list.url, 7,fn:length(list.url))}-->
			                            <span>
			                                <input type="text" class="address weburl" value="${list.url }" placeholder="">
			                                <a href="javascript:;" class="bt btn-ico">...</a>
			                            </span>&nbsp;&nbsp;示例:http://yunduoketang.com/course
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
                        <div class="title-wrap" data-name="${pageChildList.name }">
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
                                        <input type="text" value="${pageChildList.name }" class="title-content titleName" >
                                    </p>
                                    <p>
                                        <span class="name">链接地址：</span>
                                       <!--  <em>http://</em>  ${fn:substring(pageChildList.url, 7,fn:length(pageChildList.url))}--> 
                                        <span>
                                            <input type="text" value="${pageChildList.url }" class="address weburl" placeholder="">
                                            <a href="javascript:;" class="bt btn-ico">...</a>
                                        </span>&nbsp;&nbsp;示例:http://yunduoketang.com/course
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
			</c:otherwise>
		</c:choose>
	</c:if>
</c:forEach>
 <div class="new-add-btn">
      <i class="iconfont new-add-icon">&#xe61c;</i>
      <span>新增</span>
  </div>
<script type="text/javascript">
	$(function(){
		 // 配置信息事件
        $('#navbarconfigs').find("div.section").on('click','a.headStatus',function(){
         	var $this=$(this);
         	var id=$this.attr("ids");
         	var sta=$this.attr("sta");
        	var status=0;
        	if(sta==1){
        		status=0;
        	}else{
        		status=1;
        	}
        	 $.ajax({ 
         		  type: "post", 
         		  url: rootPath+"/sysPageHeadFoot/updateStatus", 
         		  data: { "id" : id, "validFlag" : status },
         		  success: function(result){
         			 queryDataTitle();
         			 queryOneHeadTitleDatas();
         		  }
         	  });
         })
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
