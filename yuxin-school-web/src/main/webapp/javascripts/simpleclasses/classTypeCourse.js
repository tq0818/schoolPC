/**
 * author zhang.zx
 * 代报考
 * 页面js封装
 */
(function($){
	var Form={
			init : function(){
				$(".footer").addClass("footer-fixed");
				
				//新增班号
				$(".addbtn").on('click',function(){
					var id=$("#clasIds").val();
//					$(".layout-choose").removeClass("none");
//					$(".layout-mb").removeClass("none");
					location.href=rootPath+"/classModuleNo/addModuleNo/"+id+"/"+$(this).attr("ids")+"/0";
					//链接地址
					//window.open(rootPath+"/classModuleNo/addModuleNo/"+id+"/"+$(this).attr("ids")+"/0");
				})
				
				//修改班号
				$(".updatebtn").on('click',function(){
					var id=$("#clasIds").val();
//					$(".layout-choose").removeClass("none");
//					$(".layout-mb").removeClass("none");
					location.href=rootPath+"/classModuleNo/editModule/"+$(this).attr("ids")+"?classTypeId="+id+"&methed=0";
					//链接地址
					//window.open(rootPath+"/classModuleNo/editModule/"+$(this).attr("ids")+"?classTypeId="+id+"&methed=0");
				})
				
				//弹窗
//				$(".layout-choose").on('click','a.btn',function(){
//					//调用方法和刷新页面
//					var id=$("#clasIds").val();
//					location.href=rootPath+"/classType/"+id+"/showClassCourseDetail";
//					$(".layout-choose").addClass("none");
//					$(".layout-mb").addClass("none");
//				})

				//返回
				$(".back").on('click',function(){
					window.location.href=rootPath+"/simpleClasses/showClassTypePage";
				})
				
				//选中班号
				$.ajax({
					url : rootPath + "/classModule/queryOnsaleList",
					type : "post",
					data : {"classTypeId":$("#clasIds").val()},
					dataType : "json",
					success : function(result) {
						$.each(result,function(i,item){
							$("input[type=radio]").each(function(){
								var mouId=$(this).attr("ids");
								if(item.moduleNoId==mouId){
									$(this).attr("value",item.id);
								}
								if(item.moduleNoId==mouId&&item.defaultFlag==1){
									$(this).attr("checked","checked");
								}
							});
						});
					}
				})
		
				
				//确定
				$("#back").on('click',function(){
					var classNos={};
					var classModuleOnsale=new Array();
					var classtypeId=$("#clasIds").val();
					var id,moduleId,moduleNoId,defaultFlag;
					var result=true;
					$(".c-list").find("p.public").each(function(i){
						var count=false;
						$(this).find("span.right").each(function(){
							var $this=$(this);
								$this.find("span.block").find("span.c-content").find("input[type=radio]").each(function(){
									defaultFlag=0;
									if($(this).is(":checked")){
										count=true;
										defaultFlag=1;
									}
									classModuleOnsale.push({id:$(this).attr("value"),classtypeId:classtypeId,moduleId:$(this).attr("mark"),moduleNoId:$(this).attr("ids"),defaultFlag:defaultFlag});
								})
						})
						result=count && result;
					})
					if(result){
						classNos.classNosale=JSON.stringify(classModuleOnsale);
						$.ajax({
							url:rootPath+"/simpleClasses/insertClassNoSale",
							type:"post",
							data:classNos,
							dataType:"json",
							success:function(data){
								$("#myForm").attr("action",rootPath+"/simpleClasses/onSale").submit();
							}
						})
					}else{
						alert("您必须指定一个默认报名班号才可以开始招生");
						return;
					}
				})
			}
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)