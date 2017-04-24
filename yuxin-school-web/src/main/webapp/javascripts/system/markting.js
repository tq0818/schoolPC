$(document).ready(function(){
	$selectMenu('netschool_markset');
	loadData();
	//保存数据
	 $('.set-focus')
     .on('click','i.iconfont',function(){
         var 
             // 开关图标  03为左侧块 04为右侧块
             s = ['&#xe603;','&#xe604;'],
             // 开关文字
             st = ['启用','禁用'],
             // 获得开关实体
             o = $(this),
             // 获得当前状态
             staus = o.hasClass('open');
         console.log(staus);

         o
             [staus?'removeClass':'addClass']('open')
             [staus?'addClass':'removeClass']('close')
             .html(s[staus?1:0])
             .next('span.i').text(st[staus?1:0])
             [staus?'removeClass':'addClass']('open')
             [staus?'addClass':'removeClass']('close');
         if(staus){
        	 $(this).parents("div.focus-title").next().find("div.focus-left").find("input[type=text]").attr("disabled","disabled");
        	 $(this).parents("div.focus-title").next().find("div.focus-left").find("input[type=button]").attr("disabled","disabled");
        	 var data={};
        	 var type=$(this).parents("span").attr("class");
        	 data.id=$("#markId").val();
        	 if(type=="markqq"){
        		 data.qqFlag=0;
        	 }else if(type=="weixinmark"){
        		 data.weixinFlag=0;
        	 }else if(type=="xinlang"){
        		 data.weiboFlag=0;
        	 }else if(type=="leyu"){
        		 data.leyuFlag=0;
        	 }else if(type=="services"){
        		data.serviceFlag=0;
        	 } 
        	
        	 $.ajax({
      			type: "post",
      			url:  rootPath+"/companyMarketSet/updateMarket",
      			dataType : "json",
      			data:data,
      			success: function(jsonData){
      				if(jsonData.id){
      					$("#markId").val(jsonData.id);
      				}
      				if(jsonData.qqNum){
      					$("#qqval").val(jsonData.qqNum);
      				}
      				if(jsonData.qqKey){
      					$("#qqkeyval").val(jsonData.qqKey);
      				}
      				if(jsonData.weixinNo){
      					$("#weixinNo").val(jsonData.weixinNo);
      				}
      				if(jsonData.weiboNo){
      					$("#weiboNo").val(jsonData.weiboNo);
      				}
      				if(jsonData.weiboUrl){
      					$("#xlNo").val(jsonData.weiboUrl);
      				}
      				if(jsonData.servicePhone){
      					$("#comPhone").val(jsonData.servicePhone);
      				}
      				if(jsonData.serviceTime){
      					$("#serviceTime").val(jsonData.serviceTime);
      				}
      			  // loadData();
      			}
      		});
         }else{
        	 $(this).parents("div.focus-title").next().find("div.focus-left").find("input[type=text]").attr("disabled",false);
        	 $(this).parents("div.focus-title").next().find("div.focus-left").find("input[type=button]").attr("disabled",false);
        	 var data={};
        	 var type=$(this).parents("span").attr("class");
        	 data.id=$("#markId").val();
        	 if(type=="markqq"){
        		 data.qqFlag=1;
        	 }else if(type=="weixinmark"){
        		 data.weixinFlag=1;
        	 }else if(type=="xinlang"){
        		 data.weiboFlag=1;
        	 }else if(type=="leyu"){
        		 data.leyuFlag=1;
        	 }else if(type=="services"){
        		data.serviceFlag=1;
        	 } 
        	 $.ajax({
      			type: "post",
      			url:  rootPath+"/companyMarketSet/updateMarket",
      			dataType : "json",
      			data:data,
      			success: function(jsonData){
      				if(jsonData.id){
      					$("#markId").val(jsonData.id);
      				}
      				if(jsonData.qqNum){
      					$("#qqval").val(jsonData.qqNum);
      				}
      				if(jsonData.qqKey){
      					$("#qqkeyval").val(jsonData.qqKey);
      				}
      				if(jsonData.weixinNo){
      					$("#weixinNo").val(jsonData.weixinNo);
      				}
      				if(jsonData.weiboNo){
      					$("#weiboNo").val(jsonData.weiboNo);
      				}
      				if(jsonData.weiboUrl){
      					$("#xlNo").val(jsonData.weiboUrl);
      				}
      				if(jsonData.servicePhone){
      					$("#comPhone").val(jsonData.servicePhone);
      				}
      				if(jsonData.serviceTime){
      					$("#serviceTime").val(jsonData.serviceTime);
      				}
      			}
      		});
         }
     })
     //添加
     $(".set-focus").find("p.btns").find("input.btn-success").on('click',function(){
    	 var $this=$(this);
    	 var type=$(this).attr("marks");
    	 var id=$("#markId").val();
    	 console.log(id);
    	 //var sta=$this.parents(".focus-content").prev('.focus-title').find("i.iconfont").hasClass('open');
    	 if(type=="qqmark"){
    		 var qqType,qqNum,qqKey;
    		 $("input[type=radio]").each(function(){
    			 var o=$(this).is(":checked");
    			 if(o){
    				 qqType=$(this).attr("value");
    			 }
    		 });
    		 qqNum=$("#qqval").val();
    		 qqKey=$("#qqkeyval").val();
    		 if(qqNum.length<=0||isNaN(qqNum)){
    			 $.msg("QQ号不能为空并且为数字");
    			 return;
    		 }
//    		 if(qqKey.length<=0){
//    			 $.msg("QQkey值不能为空");
//    			 return;
//    		 }
    		 $.ajax({
     			type: "post",
     			url:  rootPath+"/companyMarketSet/updateMarket",
     			dataType : "json",
     			data:{"id":id,"qqType":qqType,"qqNum":qqNum,"qqKey":qqKey},
     			success: function(jsonData){
     				$.msg("操作成功");
     				loadData();
     			}
     		});
    	 }else if(type=="wxmark"){
    		 var weixinNo,weixinPic;
    		 weixinNo=$("#weixinNo").val();
    		 weixinPic=$("#imageObject").attr("src");
    		 if(weixinNo.length<=0){
    			 $.msg("公众号不能为空");
    			 return;
    		 }
    		 $.ajax({
      			type: "post",
      			url:  rootPath+"/companyMarketSet/updateMarket",
      			dataType : "json",
      			data:{"id":id,"weixinNo":weixinNo,"weixinPic":weixinPic},
      			success: function(jsonData){
      				$.msg("操作成功");
      				loadData();
      			}
      		});
    	 }else if(type=="xlmark"){
    		 var weiboNo,weiboUrl,weiboPic;
    		 weiboNo=$("#weiboNo").val();
    		 weiboUrl=$("#xlNo").val();
    		 weiboPic=$("#imageObject1").attr("src");
    		 $.ajax({
      			type: "post",
      			url:  rootPath+"/companyMarketSet/updateMarket",
      			dataType : "json",
      			data:{"id":id,"weiboNo":weiboNo,"weiboUrl":weiboUrl,"weiboPic":weiboPic},
      			success: function(jsonData){
      				$.msg("操作成功");
      				loadData();
      			}
      		});
    	 }else if(type=="lymark"){
    		 var leyuJsUrl,leyuNo;
    		 var lylb=$("#lyurlb").val();
    		 var lyla=$("#lyurla").val();
    		 console.log(lylb+"--"+lyla);
    		 if(lylb==""||lyla==""){
    			 $.msg("文件地址不能为空");
    			 return; 
    		 }
    		 if(isNaN(lylb)||isNaN(lyla)){
    			 $.msg("文件地址只能输入数字");
    			 return;
    		 }
    		 leyuJsUrl=$("#lyurlb").val()+"/"+$("#lyurla").val();
    	     leyuNo=$("#lykfNum").val();
    	     if(leyuNo.length<=0){
    			 $.msg("客服分组id不能为空");
    			 return;
    		 }
    		 $.ajax({
       			type: "post",
       			url:  rootPath+"/companyMarketSet/updateMarket",
       			dataType : "json",
       			data:{"id":id,"leyuJsUrl":leyuJsUrl,"leyuNo":leyuNo},
       			success: function(jsonData){
       				$.msg("操作成功");
       				loadData();
       			}
       		});
    	 }else if(type=="kfmark"){
    		 var servicePhone,serviceTime;
    		 servicePhone=$("#comPhone").val();
    		 if(servicePhone.length<=0||isNaN(servicePhone)){
    			 $.msg("请正确输入电话号码");
    			 return;
    		 }
    		 serviceTime=$("#serviceTime").val();
    		 $.ajax({
        			type: "post",
        			url:  rootPath+"/companyMarketSet/updateMarket",
        			dataType : "json",
        			data:{"id":id,"servicePhone":servicePhone,"serviceTime":serviceTime},
        			success: function(jsonData){
        				$.msg("操作成功");
        				loadData();
        			}
        		});
    	 }
     });
	 
	 $("#qqTypeLists").on('click','input[type=radio]',function(){
		 var $this=$(this);
		 var o=$this.attr("value");
		 if(o=="MARKET_QQ_PERSON"){
			$("#qqkey").css("display","none");
		 }else{
			 $("#qqkey").css("display","block");
		 }
	 });
});
//初始化数据
function loadData(){
	$.ajax({
		type: "post",
		async:false,
		url:  rootPath+"/companyMarketSet/companyMarketData",
		success: function(jsonData){
			if(jsonData==""){
				
			}else{
//				jsonData=eval("("+jsonData+")");
//				console.log(jsonData);
			}
			$("#markId").val(jsonData.id);
			//营销
		     if(jsonData.qqFlag==1){
		    	 $(".markqq").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open">启用</span>');
		     }else{
		    	 $(".markqq").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">禁用</span>');
		     }
		     if(jsonData.qqNum){
		    	 $("#qq").html('').append('<span class="t">QQ号码</span><input class="c" type="text" id="qqval" value="'+(jsonData.qqNum)+'"/>');
		     }else{
		    	 $("#qq").html('').append('<span class="t">QQ号码</span><input class="c" type="text" id="qqval" value=""/>');
		     }
		     if(jsonData.qqKey){
				  $("#qqkey").html('').append('<span class="t">营销QQ-KEY值</span><input class="c" type="text" style="width:350px;" id="qqkeyval" value="'+(jsonData.qqKey)+'"/>');
		     }else{
				  $("#qqkey").html('').append('<span class="t">营销QQ-KEY值</span><input class="c" type="text" style="width:350px;" id="qqkeyval" value=""/>');
		     }
		     $(".focus-left").find("input[type=radio]").each(function(){
		    	 var $this=$(this);
		    	 var num=$this.attr("value");
		    	 if(jsonData.qqType&&num==jsonData.qqType){
		    		 $this.attr("checked","checked");
		    	 }
		     });
		   //微信
		     if(jsonData.weixinFlag==1){
		    	 $(".weixinmark").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open">启用</span>');
		    	 
		     }else{
		    	 $(".weixinmark").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">禁用</span>');
		    	 // $("#weixinhao").html('').append('<span class="t">公众号</span><span class="t">"'+(jsonData.weixinNo?jsonData.weixinNo:'')+'"</span>');
		     }
		     if(jsonData.weixinNo){
		    	 $("#weixinhao").html('').append('<span class="t">公众号</span><input type="text" style="margin-left:18px;" id="weixinNo" value="'+(jsonData.weixinNo)+'"/>'); 
		     }else{
		    	 $("#weixinhao").html('').append('<span class="t">公众号</span><input type="text" style="margin-left:18px;" id="weixinNo" value=""/>'); 
		     }
		     //微信二维码
		     $(".wxewm").attr("src",jsonData.weixinPic);
		     
		     //新浪
		     if(jsonData.weiboFlag==1){
		    	 $(".xinlang").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open">启用</span>');
		    	
		     }else{
		    	 $(".xinlang").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">禁用</span>');
//		    	  $("#xinlangNo").html('').append('<span class="t">微博号</span><span class="t">"'+(jsonData.weiboNo?jsonData.weiboNo:'')+'"</span>');
//		    	  $("#xinlangUrl").html('').append('<span class="t">公众号</span><span class="t">"'+(jsonData.weiboUrl?jsonData.weiboUrl:'')+'"</span>');
		     }
		     if(jsonData.weiboNo){
		    	 $("#xinlangNo").html('').append('<span class="t">微博号</span><input class="c" style="width:188px;" type="text" id="weiboNo" value="'+(jsonData.weiboNo)+'"/>');
		     }else{
		    	 $("#xinlangNo").html('').append('<span class="t">微博号</span><input class="c" style="width:188px;" type="text" id="weiboNo" value=""/>');
		     }
		     if(jsonData.weiboUrl){
		    	 $("#xinlangUrl").html('').append('<span class="t">微博地址</span><input type="text" style="margin-left:18px;width:188px;" id="xlNo" value="'+(jsonData.weiboUrl)+'"/>');
		     }else{
		    	 $("#xinlangUrl").html('').append('<span class="t">微博地址</span><input type="text" style="margin-left:18px;width:188px;" id="xlNo" value=""/>');
		     }
		     //新浪二维码
		     $(".xlewm").attr("src",jsonData.weiboPic);
		     //乐于
		     if(jsonData.leyuFlag==1){
		    	 $(".leyu").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open">启用</span>');
		     }else{
		    	 $(".leyu").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">禁用</span>');
		     }
		     if(jsonData.leyuJsUrl){
		    	 var a=jsonData.leyuJsUrl;
		    	 var b;
		    	 b=a.split("/");
		    	 console.log(b[0]);
		    	 $("#leyuUrl").html('').append('<span class="t">乐语js文件地址</span><span class="c" style="width:135px;">http://gate.looyu.com/</span><input class="c" style="width:75px;margin-left:20px;" id="lyurlb" type="text" value="'+b[0]+'"/>/<input class="c" style="width:75px;margin-left:0px;" id="lyurla" type="text" value="'+b[1]+'"/>.js'); 
		     }else{
		    	 $("#leyuUrl").html('').append('<span class="t">乐语js文件地址</span><span class="c" style="width:135px;">http://gate.looyu.com/</span><input class="c" style="width:75px;margin-left:20px;" id="lyurlb" type="text" value=""/>/<input class="c" style="width:75px;margin-left:0px;" id="lyurla" type="text" value=""/>.js');
		     }
		     if(jsonData.leyuNo){
		    	 $("#leyuPhone").html('').append('<span class="t">客服分组id</span><input class="c" id="lykfNum" style="width:188px;" type="text" value="'+(jsonData.leyuNo)+'"/>'); 
		     }else{
		    	 $("#leyuPhone").html('').append('<span class="t">客服分组id</span><input class="c" id="lykfNum" style="width:188px;" type="text" value=""/>'); 
		     }
		     //客服电话
		     if(jsonData.serviceFlag==1){
		    	 $(".services").html('').append('<i class="iconfont open">&#xe603;</i><span class="i open">启用</span>');
		    	
		     }else{
		    	 $(".services").html('').append('<i class="iconfont close">&#xe604;</i><span class="i close">禁用</span>');
		    	// $("#kfPhone").html('').append("<span class='t'>电话号码</span><span class='t'>'"+(jsonData.servicePhone?jsonData.servicePhone:"")+"'</span>");
		     }
		     if(jsonData.servicePhone){
		    	 $("#kfPhone").html('').append('<span class="t">电话号码</span><input class="c" id="comPhone" style="width:188px;" type="text" value="'+(jsonData.servicePhone)+'"/>');
		     }else{
		    	 $("#kfPhone").html('').append('<span class="t">电话号码</span><input class="c" id="comPhone" style="width:188px;" type="text" value=""/>');
		     }
		    if(jsonData.serviceTime){
		    	$("#fwTime").html('').append('<span class="t">服务时间</span><input class="c" id="serviceTime" style="width:188px;" type="text" value="'+(jsonData.serviceTime)+'"/>');
		    }else{
		    	$("#fwTime").html('').append('<span class="t">服务时间</span><input class="c" id="serviceTime" style="width:188px;" type="text" value="上午8:00-下午18:00"/>');
		    }
		    $('input[type=radio]').each(function(){
				 var $this=$(this);
				 var o=$this.is(":checked");
				 if(o){
					 var t=$this.attr("value");
					 if(t=="MARKET_QQ_PERSON"){
						 $("#qqkey").css("display","none");
					 }else{
						 $("#qqkey").css("display","block");
					 }
				 }
			 });
		}
	});
	$("i.iconfont").each(function(i){
		var t=$(this).hasClass("close");
		if(t){
			$(this).parents("div.focus-title").next().find("div.focus-left").find("input[type=text]").attr("disabled","disabled");
			$(this).parents("div.focus-title").next().find("div.focus-left").find("input[type=button]").attr("disabled","disabled");
		}
	});
}
//上传二维码
function savePic(type){
		$.ajaxFileUpload({
			url : rootPath+"/companyMarketSet/Uploadewm",
			secureuri : false,// 安全协议
			async : false,
			fileElementId : 'imgData',
			dataType:'json',
			type : "POST",
			success : function(data) {
				$(".wxewm").attr("src",data.url);
				$(".wxewm").attr("ids",data.picPath);
			},
			error:function(arg1,arg2,arg3){
				//console.log(arg1);
			}
		});
}
//上传新浪
function savexlPic(){
	$.ajaxFileUpload({
		url : rootPath+"/companyMarketSet/Uploadxl",
		secureuri : false,// 安全协议
		async : false,
		fileElementId : 'imgDatas',
		dataType:'json',
		type : "POST",
		success : function(data) {
			console.log(data.picPath);
			console.log(data.url);
			$(".xlewm").attr("src",data.url);
			$(".xlewm").attr("ids",data.picPath);
		},
		error:function(arg1,arg2,arg3){
			//console.log(arg1);
		}
	});
}