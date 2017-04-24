$(document).ready(function(){
	//加载数据
	loadClassPackageCategory();
	//点击返回
	$(".hcancle").on('click',function(){
		window.location.href=rootPath+"/company/companyService";
	});
	//点击列表
	$("#menu_list").on('click','li.subentry',function(){
		var url=$(this).attr("mark");
		window.location.href=rootPath+url;
	});
	//显示弹框
	 $(".right-side").on("click",".add-classify-btn,.edit-classify",function(){
		 clearData();
		 var code=$(this).attr("code");
		 var mark=$(this).attr("mark");
		 var type=$(this).attr("type");
		 var id=$(this).attr("ids");
		 if("edit"==type){
			 if("first"==mark){
				 $(".add-grade-title").text("编辑一级分类");
			 }else if("second"==mark){
				 $(".add-grade-title").text("编辑二级分类");
			 }else if("third"==mark){
				 $(".add-grade-title").text("编辑三级分类");
			 }
			 $.ajax({
				url : rootPath + "/classPackageCategory/"+id,
				type : "post",
				dataType : "json",
				success : function(result) {
					$("#cateGoryId").val(result.id);
					$("#categoryName").val(result.name?result.name:"");
				}
			});
		 }else{
			 if("one"==mark){
				 $(".add-grade-title").text("添加一级分类");
			 }else if("first"==mark){
				 $(".add-grade-title").text("添加二级分类");
			 }else if("second"==mark){
				 $(".add-grade-title").text("添加三级分类");
			 }
			 $("#cateGoryId").val(id);
		 }
		 $(".addCategorys").attr("type",type);
	     $(".addCategorys").attr("parentCode",code);
	     $(".addCategorys").attr("mark",mark);
         $(".add-grade-pop-box").fadeIn(300);
     });
	 //取消弹框
	 $(".add-cancel").on("click",function(){
		 clearData();
         $(".add-grade-pop-box").fadeOut(300);
     });
	//添加分类
	$(".addCategorys").on('click',function(){
		var $this=$(this);
		var type=$(this).attr("type");
		var name=$("#categoryName").val();
		var code=$(this).attr("parentCode");
		if(name==""||$.trim(name).length<=0){
			$.msg("分类名称不能为空");
			return;
		}
		if($.trim(name).length>10){
			$.msg("分类最多可输入10个字");
			return;
		}
		if("edit"==type){
			var id=$("#cateGoryId").val();
			$.ajax({
				url : rootPath + "/classPackageCategory/checkName",
				type : "post",
				dataType : "json",
				data : {"mark":"edit","name":name,"id":id,"code":code},
				success : function(result) {
					if(result){
						$.ajax({
							url : rootPath + "/classPackageCategory/updateClassPackage",
							type : "post",
							dataType : "json",
							data : {"id":id,"name":name,"status":1},
							success : function(result) {
								$(".add-grade-pop-box").fadeOut(300);
								loadClassPackageCategory();
							}
						});
					}else{
						$.msg("该分类名称已存在");
					}
				}
			});
		}else{
			if($this.hasClass("disabled")){
				return;
			}
			$this.addClass("disabled");
			var mark=$(this).attr("mark");
			var parentId,sort;
			if(mark=="one"){
				parentId=0;
				sort=$("#classPackageCategoryLists").find(".fir-grade").length+1;
				if(!sort){
					sort=1;
				}
			}else{
				parentId=$("#cateGoryId").val();
				sort=0;
			}
			$.ajax({
				url : rootPath + "/classPackageCategory/checkName",
				type : "post",
				dataType : "json",
				data : {"mark":"add","name":name,"code":code,"parentId":parentId},
				success : function(result) {
					if(result){
						$.ajax({
							url : rootPath + "/classPackageCategory/addClassPackage",
							type : "post",
							dataType : "json",
							data : {"name":name,"sort":sort,"status":1,"parentId":parentId,"code":code},
							success : function(item) {
								clearData();
								$(".add-grade-pop-box").fadeOut(300);
								$this.removeClass("disabled");
								if(parentId && parentId != ""){
									if(code.length==3){
										var secondHtml='<div class="sec-grade">'+
						                '<span class="lower-btn"></span>'+
						                '<span class="grade-name">'+item.name+'</span>'+
						                '<em class="grade-right-btn iconfont add-classify-btn" ids='+item.id+' code='+item.code+' mark="second">&#xe61c;</em>'+
						                '<em class="grade-right-btn iconfont deleteCategory" ids='+item.id+' code='+item.code+'>&#xe626;</em>'+
						                '<em class="grade-right-btn iconfont edit-classify" ids='+item.id+' code='+item.code+' type="edit" mark="second">&#xe628;</em>'+
				                        '</div>'+
						              '<div class="third-box thirdCategoryList thirdcategoryList'+item.id+'" ids='+item.id+' code='+item.code+'></div>';
									  $(".categoryList"+parentId).append(secondHtml);
									}else if(code.length==6){
										var thirdHtml='<div class="thr-grade">'+
						                '<span class="lower-btn"></span>'+
						                '<span class="grade-name">'+item.name+'</span>'+
						                '<em class="grade-right-btn iconfont deleteCategory" ids='+item.id+' mark="third" code='+item.code+'>&#xe626;</em>'+
						                '<em class="grade-right-btn iconfont edit-classify" ids='+item.id+' code='+item.code+' type="edit" mark="third">&#xe628;</em>'+
				                     '</div>';
									$(".thirdcategoryList"+parentId).append(thirdHtml);
									}
								}else{
									loadClassPackageCategory();
								}
							}
						});
					}else{
						$.msg("该分类名称已存在");
						$this.removeClass("disabled");
					}
				}
			});
		}
	});
	//管理列表
	//点击删除
	$(document).on('click.deleteCategory',".grade-box .deleteCategory",function(){
		var $this=$(this);
    	var id=$(this).attr("ids");
    	var code=$(this).attr("code");
    	$.confirm("您确定要删除该分类吗?",function(b){
    		if(b){
    			$.ajax({
    				url : rootPath + "/classPackageCategory/delCategory",
    				type : "post",
    				dataType : "json",
    				data : {"id":id,"code":code},
    				success : function(result) {
    					if("success"==result){
    						$.msg("删除成功");
    						$this.parent().remove();
    					}else if("existPackage"==result){
    						$.msg("此分类下存在课程包，请删除课程包后，再删除分类");
    					}else if("existChild"==result){
    						$.msg("请先删除该分类下子类");
    					}
    				}
    			});
    		}
    	});
    })
    .on('click.grade-pop-btn',".fir-grade .grade-pop-btn",function(){
    	if($(this).hasClass("hide")){
			 $(this).html("&#xe6a2;");
			 $(this).parents(".grade-box").find(".open-box").slideDown(200);
			 $(this).removeClass("hide");
			 $("#showLable").val("show");
    	}else{
    		$(this).html("&#xe6a1;");
   		    $(this).parents(".grade-box").find(".open-box").slideUp(200);
   		    $(this).addClass("hide");
   		    $("#showLable").val("hide");
    	}
    });
});

//加载课程分类
function loadClassPackageCategory(){
	$("#classPackageCategoryLists").html("");
	$.ajax({
		url : rootPath + "/classPackageCategory/queryCategoryList",
		type : "post",
		dataType : "json",
		data:{"leavl":"first","status":1},
		success : function(result) {
			var html="";
			var m=$("#showLable").val();
			$.each(result,function(i,item){
				var lablehtml='<span class="grade-pop-btn iconfont">&#xe6a2;</span>';
				var childhtml='<div class="open-box secodeCategoryList categoryList'+item.id+'" ids='+item.id+' code='+item.code+'></div>';
				if(m=="hide"){
					lablehtml='<span class="grade-pop-btn iconfont hide">&#xe6a1;</span>';
					childhtml='<div class="open-box secodeCategoryList categoryList'+item.id+'" ids='+item.id+' code='+item.code+' style="display:none;"></div>';
				}
				html+='<div class="grade-box">'+
						'<div class="fir-grade">'+
			                lablehtml+
			                '<span class="grade-name">'+item.name+'</span>'+
			                '<em class="grade-right-btn iconfont add-classify-btn" ids='+item.id+' code='+item.code+' mark="first">&#xe61c;</em>'+
			                '<em class="grade-right-btn iconfont deleteCategory" ids='+item.id+' mark="first" code='+item.code+'>&#xe626;</em>'+
			                '<em class="grade-right-btn iconfont edit-classify" ids='+item.id+' code='+item.code+' type="edit" mark="first">&#xe628;</em>'+
	                     '</div>'+
	                     childhtml+
                     '</div>';
			});
			$("#classPackageCategoryLists").append(html);
			
			//加载二级分类
			$("#classPackageCategoryLists").find(".secodeCategoryList").each(function(i){
				var code=$(this).attr("code");
				var id=$(this).attr("ids");
				$.ajax({
					url : rootPath + "/classPackageCategory/queryCategoryList",
					type : "post",
					data : {"leavl":"second","code":code,"status":1},
					dataType : "json",
					success : function(result) {
						var secondHtml="";
						$.each(result,function(i,item){
							secondHtml+='<div class="sec-grade">'+
			                '<span class="lower-btn"></span>'+
			                '<span class="grade-name">'+item.name+'</span>'+
			                '<em class="grade-right-btn iconfont add-classify-btn" ids='+item.id+' code='+item.code+' mark="second">&#xe61c;</em>'+
			                '<em class="grade-right-btn iconfont deleteCategory" ids='+item.id+' code='+item.code+'>&#xe626;</em>'+
			                '<em class="grade-right-btn iconfont edit-classify" ids='+item.id+' code='+item.code+' type="edit" mark="second">&#xe628;</em>'+
	                     '</div>'+
			              '<div class="third-box thirdCategoryList thirdcategoryList'+item.id+'" ids='+item.id+' code='+item.code+'></div>';
					    });
						$(".categoryList"+id).append(secondHtml);
						//加载三级分类
						$("#classPackageCategoryLists").find(".thirdCategoryList").each(function(i){
							var code=$(this).attr("code");
							var id=$(this).attr("ids");
							$.ajax({
								url : rootPath + "/classPackageCategory/queryCategoryList",
								type : "post",
								dataType : "json",
								data : {"leavl":"third","code":code,"status":1},
								success : function(result1) {
									var thirdHtml="";
									$.each(result1,function(i,item){
										thirdHtml+='<div class="thr-grade">'+
						                '<span class="lower-btn"></span>'+
						                '<span class="grade-name">'+item.name+'</span>'+
						                '<em class="grade-right-btn iconfont deleteCategory" ids='+item.id+' mark="third" code='+item.code+'>&#xe626;</em>'+
						                '<em class="grade-right-btn iconfont edit-classify" ids='+item.id+' code='+item.code+' type="edit" mark="third">&#xe628;</em>'+
				                     '</div>';
								    });
									$(".thirdcategoryList"+id).html(thirdHtml);
								}
							});
						});
					}
				});
				
			});
		}
	});
}

//清空数据
function clearData(){
	 $("#categoryName").val("");
	 $("#cateGoryId").val("");
	 $(".addCategorys").removeAttr("type");
	 $(".addCategorys").removeAttr("parentCode");
     $(".addCategorys").removeAttr("mark");
}
