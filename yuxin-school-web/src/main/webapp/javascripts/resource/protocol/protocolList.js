/**
 * author zhang.zx
 * 用户权限
 * 页面js封装
 */
(function($){
	var Form={
			init : function(){
				var $this = this;
				$("#categoryList").on('click','a.btn',function(){
					var _this=$(this),status= _this.hasClass('btn-success');
					if(!status){
						 _this.addClass('btn-success').siblings('a').removeClass('btn-success');
					}
					$this.searchProtocol();
				});
				var courseStatus1 = "";
				var packageStatus1 = "";
				$.ajax({
					 url:rootPath+"/companyFunctionSet/queryCompanyProtocolExist",
					 type:"post",
					 async: false,
					 data:{"functionCode":"CLASS_POTOCOL_SET"},
					 dataType:"json",
					 success:function(jsonData){
						 if(jsonData && jsonData == "success"){
							 courseStatus1 = "success";
						 }
					 }
				 })
				 
				 $.ajax({
					 url:rootPath+"/companyFunctionSet/queryCompanyProtocolExist",
					 type:"post",
					 async: false,
					 data:{"functionCode":"CLASSPACKAGE_POTOCOL_SET"},
					 dataType:"json",
					 success:function(jsonData){
						 if(jsonData && jsonData == "success"){
							 packageStatus1 = "success";
						 }
					 }
				 })
				 
				 if(packageStatus1 == "success" || courseStatus1 == "success"){
					 //$('#resourceProtocol').show();
					 $this.searchProtocol();
				 }
				$this.searchProtocol();
				$('.addProtocol').on('click',function(){
					window.location.href=rootPath+"/courseProtocolConfig/addProtocol";
				})
			},
			searchProtocol:function(page){
				var type="";
				$("#categoryList").find("a").each(function(i){
					if($(this).hasClass("btn-success")){
						type=$(this).attr("mark");
						if(type == 'all')
							type = null;
						else if(type == 'course')
							type = '0';
						else if(type == 'coursePackage')
							type = '1';
					}
				})
				var condition = {};
				condition.type = type;
				condition.page = page?page:1;
				$.ajax({
					url:rootPath+"/courseProtocolConfig/queryProtocolList",
					type:"post",
					data:condition,
					/*beforeSend:function(XMLHttpRequest){
			            $(".loading").show();
			            $(".loading-bg").show();
			        },*/
					success:function(jsonData){
						$('#loadData').html(jsonData);
						$("td.status").each(function(i){
							if($(this).text()=="停用"){
								$(this).css("color","red");
							}
						});
					},
					/*complete:function(XMLHttpRequest,textStatus){
						$(".loading").hide();
			            $(".loading-bg").hide();
			     }*/
				})
				
			}
		}
	$(document).ready(function(){
		Form.init();
		$('.btn-default').on('click',function(){
			$(this).addClass('btn-success').siblings().removeClass('btn-success');
		})
		window.Form=Form;
		$('#loadData').on('click','.changeStatus',function(){
			var protocolId = $(this).attr('marks');
			var status = '';
			if($(this).text()=='停用'){
				status = 0;
				$.confirm("是否确定停用此协议",function(a){
					if(a==true){
				$.ajax({
				url:rootPath+"/courseProtocolConfig/saveOrUpdateProtocol",
				type:"post",
				async:false,
				data:{'id':protocolId,'status':status},
				dataType:'json',
				beforeSend:function(XMLHttpRequest){
					$(this).attr('disabled','disabled');
				},
				success:function(msg){
					if(msg=="success"){
						$.msg('修改成功');
						setTimeout(function(){
							$(this).attr('disabled',false);
							//window.location.href=rootPath+"/courseProtocolConfig/toProtocolList";
							Form.searchProtocol();
						},500);
					}
				}
			})
					}
				})
			}else{
				status = 1;
				$.confirm("是否确定启用此协议",function(a){
					if(a==true){
				$.ajax({
				url:rootPath+"/courseProtocolConfig/saveOrUpdateProtocol",
				type:"post",
				async:false,
				data:{'id':protocolId,'status':status},
				dataType:'json',
				beforeSend:function(XMLHttpRequest){
					$(this).attr('disabled','disabled');
				},
				success:function(msg){
					if(msg=="success"){
						$.msg('修改成功');
						setTimeout(function(){
							$(this).attr('disabled',false);
							//window.location.href=rootPath+"/courseProtocolConfig/toProtocolList";
							Form.searchProtocol();
						},500);
					}
				}
			})
					}
				})
			}
			
		})
	})
	
})(jQuery)