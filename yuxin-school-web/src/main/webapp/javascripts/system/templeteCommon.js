/**
 * 在jQuery加载完成后执行
 */
(function($){
	var Model = {
			config : {
				companyFootInfo : {},//公司默认配置
				baseImgUrl    : 'http://image.yunduoketang.com/',
				companyEneter : false
			} 
	};
	Model.ajaxLoad = function(url,dataInfo,func){
  		 $.ajax({ 
  	 		  type: "post", 
  	 		  url : rootPath+url, 
  	 		  data: dataInfo,
  			  beforeSend:function(XMLHttpRequest){
  	            $(".loading").show();
  	            $(".loading-bg").show();
  	          },
  	 		  success: func,
  	 		  complete:function(XMLHttpRequest,textStatus){
  				  $(".loading").hide();
  		          $(".loading-bg").hide();
  		      }
  	 	  });
  	};
  	Model.ajax = function(){
		var arg = Array.prototype.slice.call(arguments),
			success = arg.length>2?arg[2]:function(){},
			error = arg.length>3?arg[3]:function(){};
		$.ajax({ 
	 		  type: "post", 
	 		  url : rootPath+arg[0], 
	 		  data: arg[1],
	 		  success: success,
	 		  error : error
	 	  });
	}
  	/**
  	 * 查询默认配置信息
  	 */
  	Model.queryCompanyInfo = function(){
  		var _this = this;
  		this.ajax('/companyFootInfo/queryCompanyId',{},function(result){
  			_this.config.companyFootInfo = result || {};
  			_this.queryCompanyLeve();
  		});
  	};
  	Model.queryCompanyLeve = function(){
  		var _this = this;
  		this.ajax('/companyFootInfo/isEnterootPathriseVersion',{},function(result){
  			_this.config.companyEneter = result;
  			_this.appendInfo();
  		})
  	};
  	Model.appendInfo = function(){
  		var templete    = this.getTemp(),
  			copyright   = this.config.companyFootInfo.copyright || '2014-2017.',
  			companyName = this.config.companyFootInfo.companyName || '云朵课堂',
  			icpNo		= this.config.companyFootInfo.icpNo || '京ICP备15011506号',
  			securityIco = this.config.companyFootInfo.securityIco?this.config.baseImgUrl+this.config.companyFootInfo.securityIco:'',
  			securityRegno = this.config.companyFootInfo.securityRegno?this.config.companyFootInfo.securityRegno:'',
  			securityLink = this.config.companyFootInfo.securityLink?this.config.companyFootInfo.securityLink:'';
  		
  		if(isNaN(templete)) return;
  		
  		$('#copyrightList').html('Copyright @ ' + copyright + companyName + ' 版权所有  ' + icpNo);
  		if(securityIco || securityRegno || securityLink){
  			if(templete == 1 ){
  	  			$('#copyrightList').after('<p class="record clear"><span class="picture fl" style="background:url('+securityIco+') no-repeat center;background-size:100% auto;"></span><a class="stan fl" href="#">' + securityRegno + '</a></p>');
  	  		}
  	  		if(templete == 2){
  	  			$('#copyrightList').after('<p class="record record3-bot clear"><span class="picture fl"  style="width:14px;height:16px;background:url('+securityIco+') no-repeat center;background-size:100% auto;"></span><a class="stan fl" href="#">' + securityRegno + '</a></p>');
  	  		}
  	  		if(templete == 3){
  	  			$('#copyrightList').after('<b class="picture2" style="background:url('+securityIco+') no-repeat center;background-size:100% auto;"></b><a class="stan2 dfs" href="#">' + securityRegno + '</a>');
  	  		}
  	  		if(templete == 4){
  	  			$('#copyrightList').after('<p class="record record4-copy clear"><span class="picture fl" style="background:url('+securityIco+') no-repeat center;background-size:100% auto;"></span><a class="stan stan4 fl" href="#">' + securityRegno + '</a></p>');
  	  		}
  		}
  	};
  	Model.getTemp = function(){
  		return parseInt(location.pathname.substring(location.pathname.lastIndexOf('/')+1));
  	};
  	 /**
	  * 上传备案图片
	  */
	Model.addCompanyIco = function(){
		var _this = this;
		
		$.ajaxFileUpload({
			url : rootPath+"/companyFootInfo/upload;"+ window["sessionName"] + "=" + window["sessionId"],
			secureuri : false,// 安全协议
			async : false,
			fileElementId : 'secico',
			dataType:'json',
			type : "POST",
			success : function(data) {
				if(data.imgPath){
					$('#security_ico').attr('src',data.path).data('path',data.imgPath);
//					_this.changeBeianVal();
				}else{
					$.msg('上传失败');
				}
			},
			error : function(resp,msg,err){
				console.log(resp);
				$.msg('上传失败');
			}
		});
	};
	//更改备案显示
//	Model.changeBeianVal = function(){
//		var imgUrl = $('#security_ico').attr('src'),
//			text   = $('#security_regno').val(),
//			href   = $('#security_link').val(),
//			temp   = this.getTemp();
//		
//		if(!$('.record').length){
//			
//		}
//		if(imgUrl || text || href){
//			$('#beianan img').attr('src',imgUrl || '');
//			$('#beianan a').html(text || '');
//			$('#beianan a').attr('href',href || 'javascript:;');
//			if(temp == 4){
//				$('.c-footer-4,.c-footer-4').css({'height':'70px'});
//				$('..f-4-left')
//			}
//		}else{
//			$('#beianan').remove();
//		}
//	};
	//更改copyright
	Model.changeCopyrightVal = function(){
		var copyright   = $('#copyright').val() || '2014-2017.',
			companyName = $('#companyName').val() || '云朵课堂',
			icpNos      = $('#icpNos').val() || '京ICP备15011506号';
		
		$('#copyrightList').html('Copyright @ ' + copyright + companyName + ' 版权所有  ' + icpNos);
	};
	
	
	
  	Model.event = function(){
  		var _this = this;
  		$selectMenu('org_service');
  		_this.queryCompanyInfo();
  		$("body").on("click",':text',function(){
  			$(this).focus();
  		})
  		//初始化弹出层中的学科
  		$.ajax({
  			url: rootPath+"/sysConfigItem/getJsons",
  			type: "post",
  			dataType: "json",
  			data: {"itemType":1},
  			success: function(jsonData){
  				$(".itemOne").find("p.cons_content").html('');

  				$.each(jsonData,function(i,data){
  					$(".itemOne").find("p.cons_content").append('<a href="javascript:;" value="'+data.id+'" mark='+data.itemName+' class="btn btn-mini btn-default">'+data.itemName+'</a>');
  				});
  			}
  		})
  		
  		$("#linkList_custom").find("tr:gt(0)").remove();
  		$.ajax({
  			url: rootPath+"/companyConfigCustompage/queryCustomPageListById",
  			type: "post",
  			dataType: "json",
  			success: function(jsonData){
  				$.each(jsonData,function(i,data){
  					var html='<tr>'+
  	                    '<td class="col-1">'+
  	                    	'<input type="radio" value="'+(data.url?data.url:"")+'" ids='+data.id+' name="title-name"/><span>'+data.title+'</span>'+
  	                    '</td>'+
  						'<td class="col-2">自定义域名：/custom/page/'+(data.url?data.url:"")+'</td>'+
  						'<td class="col-3">'+(data.updateTime)+'</td>'+
  						'<td class="col-4">'+(data.creaters)+'</td>'+
  		                '</tr>';
  					$("#linkList_custom").append(html);
  				});
  			}
  		})
  		//绑定事件
  		$(document).on("click.bt","p span a.btn-ico",function(){
  			var input=$(this).prev();
  			$.ajax({
  				url: rootPath+"/company/currtCompany",
  				type:"post",
  				dataType: "json",
  				success: function(jsonData){
  					var domain=jsonData.domain;
  					$(".chooseUrl").popup({
  						init: true,
  						show : true,
  						modal: false,
  						success: function(ele){
  							var url="";
  							var name="";
  							var mark=$(".link-tab-btn").find("p.active").attr("mark");
  							if(mark=="link"){
  								var customUrl=$("#linkList_custom").find("input[type=radio]:checked").val();
  								var id=$("#linkList_custom").find("input[type=radio]:checked").attr("ids");
  								url+=domain+"/custom/page/"+customUrl+"/"+id;
  							}else{
  								var itemOne=$(".chooseUrl").find(".itemOne").find(".btn-success").attr("value");
  								var itemTwo=$(".chooseUrl").find(".itemTwo").find(".btn-success").attr("value");
  								var classes=$(".chooseUrl").find(".classes").find(".btn-success").attr("value");
  								var classTypeId=$(".chooseUrl").find(".classes").find(".btn-success").attr("classtypeId");

  								var itemOneName=$(".chooseUrl").find(".itemOne").find(".btn-success").attr("mark");
  								var itemTwoName=$(".chooseUrl").find(".itemTwo").find(".btn-success").attr("mark");
  								var classesName=$(".chooseUrl").find(".classes").find(".btn-success").attr("mark");
  								if(itemOne && itemTwo && !classes){
  									url+=domain+"/sysConfigItem/getitemTwo/"+itemOne+"/"+itemTwo;
  									name+=itemTwoName;
  								}else if(itemOne && !itemTwo){
  									url+=domain+"/sysConfigItem/getitemOne/"+itemOne;
  									name+=itemOneName;
  								}else if(classes){
  									var htyp=$("#htmlType").val();
  									if(htyp && htyp=="app_cycle"){
  										url+=domain+"/sysConfigItem/selectDetail/"+classTypeId;
  										name+=classesName;
  									}else{
  										url+=domain+"/sysConfigItem/selectDetail/"+classes;
  										name+=classesName;
  									}
  								}
  							}
  							$(input).val('http://'+url);
  							$(input).prev().html(name);
  						}
  				})
  				}
  			})
  		});	
  		//学科
  		$(".itemOne").on("click","a.btn",function(){
  			$(this).siblings().removeClass("btn-success").addClass("btn-default");
  			$(this).addClass("btn-success").removeClass("btn-default");
  			queryItemSecond();
  			 queryClassType();
  		})
  		queryItemSecond();
  		//学科小类
  		$(".itemTwo").on('click','a.btn',function(){
  			$(this).siblings().removeClass("btn-success").addClass("btn-default");
  			$(this).addClass("btn-success").removeClass("btn-default");
  			  queryClassType();
  		})
  		//班型
  		$(".classes").on('click','a.btn',function(){
  			$(this).siblings().removeClass("btn-success").addClass("btn-default");
  			$(this).addClass("btn-success").removeClass("btn-default");
  		})

  		 $(".original").on("click",function(){
  		        $(this).addClass("active");
  		        $(this).next().removeClass("active");
  		        $(".top-box-padd2").hide();
  		        $(".top-box-padd1").show();
  	    });
  	    $(".custom").on("click",function(){
  	        $(this).addClass("active");
  	        $(this).prev().removeClass("active");
  	        $(".top-box-padd1").hide();
  	        $(".top-box-padd2").show();
  	    });
  	    $(document).on('change','#secico',_this.addCompanyIco);
  	    //保存备案html
  	    $('#saveh').on('click',function(){
  	    	var security_ico = $('#security_ico').data('path');
  	    	var security_regno = $('#security_regno').val();
  	    	var security_link = null;
  	    	if(security_regno){
  	    		for(var i=0; i<security_regno.length; i++){
  	    			if(!isNaN(parseInt(security_regno[i]))){
  	    				if(!security_link) security_link = 'http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=';
  	    				security_link += security_regno[i];
  	    			}
  	    		}
  	    	}
  	    	
  	    	$.ajax({
  	    		url: rootPath+"/companyFootInfo/editgongan",
  	    		type: "post",
  	    		dataType: "json",
  	    		data: {"securityIco":security_ico,'securityRegno':security_regno,'securityLink':security_link},
  	    		success: function(jsonData){
  	    			if(jsonData == 'success'){
  	    				$.msg('保存成功');
  	    				window.location.reload();
  	    			}else{
  	    				$.msg(jsonData.msg || '保存失败');
  	    			}
  	    		}
  	    	})
  	    });
  	    //删除图片
  	    $('.del-img').on('click',function(){
  	    	$('#security_ico').attr('src','').data('path',null);
  	    });
  	    $(document).on('keyup','#copyright,#companyName,#icpNos',_this.changeCopyrightVal);
//		$(document).on('keyup','#security_regno,#security_link',_this.changeBeianVal);
		$(document).on('change','#copyright,#companyName,#icpNos',_this.changeCopyrightVal);
//		$(document).on('change','#security_regno,#security_link',_this.changeBeianVal);
  	};
  	
  	Model.init = function(){
  		this.event();
  	};
  	
	
	/**
	 * 页面加载完成后执行
	 */
	$(function(){
		Model.init();
		
	});
})(jQuery);
	
	function queryItemSecond(){
		 var itemOneId=0;
		$(".itemOne").find("a.btn").each(function(){
			var st=$(this).hasClass("btn-success");
			if(st){
				itemOneId=$(this).attr("value");
			}
		});
		$.ajax({
			url: rootPath+"/sysConfigItem/getJsons",
			type: "post",
			dataType: "json",
			data: {"itemType":2,"parentId":itemOneId},
			success: function(jsonData){
				$(".itemTwo").find("p.cons_content").html('');
				$.each(jsonData,function(i,data){
					$(".itemTwo").find("p.cons_content").append('<a href="javascript:;" value="'+data.id+'" mark="'+data.itemName+'" class="btn btn-mini btn-default">'+data.itemName+'</a>');
				});
			}
		})
	}
	 function queryClassType(){
		var itemOneId=0,itemSecondId=0;
		itemOneId=$(".itemOne").find(".btn-success").attr("value");
		itemSecondId=$(".itemTwo").find(".btn-success").attr("value");
		$.ajax({
			url: rootPath+"/classType/queryList",
			type: "post",
			dataType: "json",
			data: {"itemOneId":itemOneId,"itemSecondId":itemSecondId},
			success: function(jsonData){
				$(".classes").find("p.cons_content").html('');
				$.each(jsonData,function(i,data){
					$(".classes").find("p.cons_content").append('<a href="javascript:;" value="'+data.commodityId+'" classtypeId="'+data.id+'" mark="'+data.name+'" class="btn btn-mini btn-default">'+data.name+'</a>');
				});
			}
		})
	}
