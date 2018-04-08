<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% String rootPath=request.getContextPath(); %>
<script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>

<form method="post" id="myForm">
	<input type="hidden" name="id" id="classTypeId"/>
	<input type="hidden" name="type" value="update"/>
	<input type="hidden" name="typeCode" id="typeCode"/>
	<input type="hidden" name="itemOneId" id="oneId">
	<input type="hidden" name="itemSecondId" id="twoId"/>
	<input type="hidden" name="lable" id="lab"/>
</form>
<div class="m-list clear">
	<ul class="clear" id="ulListss">
	<li class='add-class'> <a href='javascript:;' class="checkStudent operate_btn btn-sel-stu"><i class='iconfont icons'>&#xe61c;</i></a></li>
	  <c:forEach items="${pageFinder.data }" var="allCommdotity" varStatus="status">
		   <li id="commodityLi${allCommdotity.id }" onmouseover="Form.showSave(${allCommdotity.id})" onmouseout="Form.closeSave(${allCommdotity.id})" publishStatus="${allCommdotity.publishStatus}">
		   		<c:choose>
		   			<c:when test="${allCommdotity.publishStatus=='CLASS_STOP_SALE' }">
		   				<i class="tips tips_type" style="background-color: rgba(231,31,26,0.8);color: white;">
		   				<c:choose>
		   					<c:when test="${allCommdotity.publishStatus=='CLASS_UNPUBLISHED'}">未发布</c:when>
		   					<c:when test="${allCommdotity.publishStatus=='CLASS_ON_SALE'}">在售</c:when>
		   					<c:otherwise>停售</c:otherwise>
		   				</c:choose>
		   				</i>
		   			</c:when>
		   			<c:otherwise>
		   				<i class="tips tips_type"><c:choose>
		   					<c:when test="${allCommdotity.publishStatus=='CLASS_UNPUBLISHED'}">未发布</c:when>
		   					<c:when test="${allCommdotity.publishStatus=='CLASS_ON_SALE'}">在售</c:when>
		   					<c:otherwise>停售</c:otherwise>
		   				</c:choose></i>
		   			</c:otherwise>
		   		</c:choose>
			  <c:if test="${allCommdotity.iconLable !=null ||allCommdotity.iconLable.length>0}">
				  <i class="tips zb_date">
						  ${allCommdotity.iconLable}
				  </i>
			  </c:if>
			     <i class="add-com" id="com${allCommdotity.id}" marks="${allCommdotity.recommendFlag}" style="display: none;" onclick="Form.collectShop(${allCommdotity.id})">${allCommdotity.recommendFlag==1?'取消推荐':'加入推荐' }</i>
			    <div class="infos-pic">
			      <a href="javascript:Form.showClassTypeDetail(${allCommdotity.id },'${allCommdotity.typeCode }');">
			    	<c:if test="${allCommdotity.cover!=null }">
			    		<img src="${commodityPicUrl }${allCommdotity.cover}" alt="">
			    	</c:if>
			    	<c:if test="${empty allCommdotity.cover}">
			    		<img alt="" src="<%=rootPath %>/images/overview_demo.jpg">
			    	</c:if>
			      </a>
			    </div>

			    <div class="infos-title">
			        <h2 class="h5">
			        	 <a href="javascript:Form.showClassTypeDetail(${allCommdotity.id },'${allCommdotity.typeCode }');" title="${allCommdotity.name }">
				          <c:choose>
				          	<c:when test="${fn:length(allCommdotity.name)>15}">
				          		${fn:substring(allCommdotity.name, 0, 15)}...
				          	</c:when>
				          	<c:otherwise>
				          		 ${allCommdotity.name }
				          	</c:otherwise>
				          </c:choose>
				        </a>
			        </h2> 
			        <div class="type" id="lab${allCommdotity.id }">
			        	<c:if test="${allCommdotity.liveFlag==1 }"> 
			        		 <a href="javascript:;" mark="live" class="btn btn-mini btn-default">直播</a>
			        	</c:if>
			        	<c:if test="${allCommdotity.videoFlag==1 }"> 
			        		 <a href="javascript:;" mark="video" class="btn btn-mini btn-default">录播</a>
			        	</c:if>
			           <c:if test="${allCommdotity.faceFlag==1 }"> 
			        		 <a href="javascript:;" mark="face" class="btn btn-mini btn-default">面授</a>
			        	</c:if>
			        	<c:if test="${allCommdotity.liveFlag==0&&allCommdotity.videoFlag==0&&allCommdotity.faceFlag==0 }">
			        		<a href="javascript:;" mark="remote" class="btn btn-mini btn-default">其他</a>
			        	</c:if>
			        </div>
			        <p class="descript" title="${allCommdotity.description }">
			        	<c:if test="${fn:length(allCommdotity.description)>15}">
			        		${fn:substring(allCommdotity.description, 0, 15)}......
			        	</c:if>
			        	<c:if test="${fn:length(allCommdotity.description)<=15}">
			        		${allCommdotity.description }
			        	</c:if>
			        	<c:if test="${empty allCommdotity.description }">
			        		&nbsp;&nbsp;
			        	</c:if>
			        </p> 
			        <div class="btns list-btn">
				      <a href="javascript:Form.deleteClassType(${allCommdotity.id });" class="btn btn-sm btn-default deleteGoods">删除</a>
				    	<c:if test="${allCommdotity.publishStatus=='CLASS_ON_SALE'}">
				    	    <a href="javascript:Form.stopOnsale(${allCommdotity.id });" class="btn btn-sm btn-default downGoods">下架</a>
				    	    <a href="javascript:Form.editClassType(${allCommdotity.id });" class="btn btn-sm btn-primary">管理</a>
				    	    <a href="<%=rootPath %>/classModuleLesson/classesResource/${allCommdotity.id }/none" target="_blank" class="btn btn-sm btn-primary">资料</a>
				    	</c:if>
				    	<c:if test="${allCommdotity.publishStatus=='CLASS_STOP_SALE'}">
				    	    <a href="javascript:Form.classTypeOnsale(${allCommdotity.id });" class="btn btn-sm btn-default upSale">上架</a>
				    		<a href="javascript:Form.editClassType(${allCommdotity.id });" class="btn btn-sm btn-primary">管理</a>
				    	</c:if>
				    	<c:if test="${allCommdotity.publishStatus=='CLASS_UNPUBLISHED'}">
				    		<a href="javascript:Form.editClassType(${allCommdotity.id });" class="btn btn-sm btn-primary">管理</a>
				    	</c:if>
				    </div>
			    </div>
			    <div class="infos-tips clear">
			        <p><span class="price">￥ ${allCommdotity.strRealPrice }</span><del>${allCommdotity.strOriginalPrice }</del><span style="float:right; color: gray;font-size: 10px;">${allCommdotity.actualNum }人学习</span></p>
			    </div>
		        <div class="course-sort">
		        	<label for="" class="sort-txt">学科课程排序：</label>
		        	<input type="text" class="sort-input" maxLength="2" name="sortInput" <c:if test="${not empty allCommdotity.subjectClassOrder}"> value="${allCommdotity.subjectClassOrder }"  isOrder="1" </c:if> <c:if test="${empty allCommdotity.subjectClassOrder}"> placeholder ="未排序"</c:if> onfocus="this.placeholder=''" onblur="this.placeholder='未排序'">
		        	<!-- <div class="sortbtn"> -->
		        		<i class='iconfont icons sortbtn sortbtn-gou'>&#xe660;</i>
		        		<i class='iconfont icons sortbtn sortbtn-cha'>&#xe6bd;</i>
		        	<!-- </div> -->
		        	
		        </div>
			    
			</li>
		</c:forEach>
	</ul>
</div>
<div class="pages">
	<ul class="pagination"></ul>
 </div>
 <input type="hidden" id="itemOneId" name="itemOneId" value="${itemOneId }"/>
 <input type="hidden" id="searchName" value="${searchName }"/>
 <script type="text/javascript">
 var orderCount = ${orderCount};
 function resizeLayout(){
	 var w=$(".upload-layer").width();
	 var h=$(".upload-layer").height();
	 var ww=$(window).width();
	 var hh=$(window).height();
	 var left =(ww-w)/2;
	 var top =(hh-h)/2;
	 $(".upload-layer").css({"left":left+"px","top":top+"px"});
	 
 }
  $(document).ready(function(){
	  $(".pagination").pagination('${pageFinder.rowCount}', {
	    	 next_text : "下一页",
	    	 prev_text : "上一页",
	    	 current_page :'${pageFinder.pageNo-1}',
	    	 link_to : "javascript:void(0)",
	    	 num_display_entries : 8,
	    	 items_per_page : '${pageFinder.pageSize}',
	    	 num_edge_entries : 1,
	    	 callback:function(page,jq){
		    	 var pageNo = page + 1;
		    	 if($("#searchName").val()){
		    		 Form.queryCommodityByName(pageNo); 
		    	 }else{
		    		 Form.queryAllCommdityByItemNew(pageNo);
		    	 }
	    	 }
	   });
	  /*点击已招学员-弹出已招学员列表*/
		$(".checkStudent").click(function(){
			//判断是否是排课老师的权限
			$(".add-layer-bg").fadeIn(200,function(){
							$(".upload-layer").css({
								'width': '860px',
							    'height': '480px',
							    'left':'55%',
							    'top':'60%',
							    'background': '#fff',
							    'box-shadow': '0 0 6px #999',
							    'border': 'none',
							    'border-radius': '5px',
							    'display': 'none'
							    }).fadeIn(200);
							//resizeLayout();
						});
			/* $.ajax({
				type:'GET',
				data:{},
				url:rootPath+"/simpleClasses/checkIsFuShengPaikeOrCommonPaike",
				dataType : "json",
				success:function(data){
					if(data=='yes'){
						$.msg("当前是排课老师权限，不能添加！");
						return;
					}else{
						$(".add-layer-bg").fadeIn(200,function(){
							$(".upload-layer").css({
								'width': '860px',
							    'height': '400px',
							    'left':'55%',
							    'top':'60%',
							    'background': '#fff',
							    'box-shadow': '0 0 6px #999',
							    'border': 'none',
							    'border-radius': '5px',
							    'display': 'none'
							    }).fadeIn(200);
							//resizeLayout();
						});
					}
				}
			}) */
		});
	  $(document).on('click.close','.upload-layer .close',function(){
		  $('.upload-layer').fadeOut(200,function(){
			  $(".add-layer-bg").fadeOut(200);
		  })
	  });
		var oldVal = '';
	  	$("[name=sortInput]").focus(function(){
	  		var _this = $(this);
			console.log(_this.val());
			$("#ulListss .sort-input").removeClass("editing");
	  		_this.addClass("editing");
	  		_this.siblings('.sortbtn').show();
			_this.val()?oldVal=_this.val():oldVal='';
	  	});
	  	$(".course-sort").delegate(".sortbtn-gou","click",function(e){
	  		var isOrder = $(this).prev().attr("isOrder");
//	  		if(isOrder !=1 && orderCount >= 8 ){
//	  			$.msg("排序总数不能超过8个.");
//	  			return false;
//	  		}
	  		var publishStatus = $(this).parent().parent("li[id^='commodityLi']").attr("publishStatus"); 
	  		if(publishStatus !="CLASS_ON_SALE"){
	  			$.msg("课程未发布,不能排序.");
	  			return false;
	  		}
	  		
	  		var _input = $(this).prev().val();
	  		var reg =  /(^$)|(^[1-9]\d*$)/;
	 	  	if(!$.trim(_input) == '' && !reg.test(_input)) {
	  			$.msg("请输入正整数,并且序号范围1-99");
	  			return false;
	  		}

	  		$("[name=sortInput]").removeClass("editing");
	  		$(e.delegateTarget).find('.sortbtn').hide();
	  		var id;
//	        var itemOneId;
//	        $("#itemOneList").find("a").each(function(i){
//				if($(this).hasClass('btn-success')){
//					var cid=$(this).attr("ids");
//					itemOneId=cid;
//					return false;
//				}
//			});
	  	    id = $(this).parent().parent("li[id^='commodityLi']").attr("id"); 
	  		id = id.replace("commodityLi","");
		    $.ajax({
				type:'GET',
				data:{'id':id,'order':_input},
				url:"<%=rootPath%>/simpleClasses/updateSubjectClassOrder",
				dataType : "json",
				success:function(data){
					if(data=='success'){
						$.msg("修改排序成功");
						Form.queryAllCommdityByItemNew(1);
					}else{
						$.msg("修改排序失败");
					}
				}
			});

	  	}).delegate(".sortbtn-cha","click",function(e){
			var sortinput = $(this).closest(".course-sort").find("[name=sortInput]");
			if(oldVal){
				sortinput.val(oldVal);
			}else{
				sortinput.val("");
			}
			sortinput.removeClass("editing");
				$(e.delegateTarget).find('.sortbtn').hide();
	  	});
	  $(document).bind("click",function(e){
		  var _this = $(e.target);
		  if(_this.hasClass("sort-input")){
			  oldVal = _this.val();
		  }
		  var sortinput = $(".course-sort").find(".editing");
		  if(!_this.hasClass('sort-input')&& !_this.hasClass('sortbtn-gou')){
			  if(oldVal){
				  sortinput.val(oldVal);
			  }else{
				  sortinput.val("");
			  }
			  sortinput.removeClass("editing");
			  $(e.delegateTarget).find('.sortbtn').hide();
		  }
	  });
  });
</script>
<script type="text/javascript" src="<%=request.getContextPath() %>/javascripts/class/editClass/validatePrivilige.js"></script>
