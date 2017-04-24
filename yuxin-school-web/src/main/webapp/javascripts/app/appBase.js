$(document).ready(function(){
	$selectMenu('org_service');
	//启用禁用
    var static=[{icon:"&#xe603",word:"启用"},{icon:"&#xe604;",word:"禁用"}];
    $(".nav-static").on("click",function(){
    	var data={};
    	data.appMenuFlag=1;
        var open=$(this).hasClass("open");
        if(open){
            $(this).removeClass("open").addClass("l-close");
            $(this).find("i").html(static[1].icon);
            $(this).find("em").html(static[1].word);
            $(this).parents(".bas-set-itm").find(".app-open").hide();
            data.appMenuFlag=0;
        }else{
            $(this).removeClass("l-close").addClass("open");
            $(this).find("i").html(static[0].icon);
            $(this).find("em").html(static[0].word);
            $(this).parents(".bas-set-itm").find(".app-open").show();
        }
        $.ajax({
  			type: "post",
  			url:  rootPath+"/companyAppConfig/addConfig",
  			dataType : "json",
  			data:data,
  			success: function(jsonData){
  				
  			}
  		});
    });
	loadData();
	//点击类型
	$(".chose-address").find("input[type=radio]").on('click',function(){
		var val=$(this).val();
		var data={};
    	data.appPageType=val;
		if(val==1){
			$(".editUrling").show();
		}else{
			$(".editUrling").hide();
		}
		$.ajax({
  			type: "post",
  			url:  rootPath+"/companyAppConfig/addConfig",
  			dataType : "json",
  			data:data,
  			success: function(jsonData){
  				
  			}
  		});
	})
	//点击编辑或保存
	$(".app-open .edit_page").on('click',function(){
	    var $this=$(this);
	    var mark=$(this).attr("mark");
	    if($(this).text()=="保存"){
	    	var url=$this.prev().val();
			 var data={};
			 if(mark=="android"){
				 data.androidDownUrl=url;
			 }else if(mark=="ios"){
				 data.iosDownUrl=$this.prev().val();
			 }else{
				 data.appPageType=$(".chose-address").find("input[type=radio]:checked").val();
				 data.appPageLink=url;
			 }
			 if(url.length<=0 || url==""){
				 $.msg("输入地址不能为空");
				 return;
			 }
			 if(data){
				 $.ajax({
			  			type: "post",
			  			url:  rootPath+"/companyAppConfig/addConfig",
			  			dataType : "json",
			  			data:data,
			  			success: function(jsonData){
			  				if(jsonData=="success"){
			  					$this.prev().attr("disabled","disabled");
			  					$.msg("保存成功");
			  					$this.text("编辑");
			  					$this.next().hide();
			  					loadData();
			  				}
			  			}
			  		}); 
			 }
		  }else{
			 $this.prev().removeAttr("disabled");
			 $(this).text("保存");
			 $(this).next().show();
		  }
	});
	  //点击取消
	 $(".edit_cancle").on('click',function(){
		 var txt=$.trim($(this).attr("mark"));
		 $(this).prev().text("编辑").prev().val(txt).attr("disabled","disabled");
		 $(this).hide();
	 });
	 $.ajax({
			url: rootPath+"/company/currtCompany",
			type:"post",
			dataType: "json",
			success: function(jsonData){
				var domain=jsonData.domain;
				$(".dmoain_ewm div").qrcode({
					//render	: "div",
					text  : "http://"+domain,
					size:150
				});
				var canvas=$("#qrcode").find("canvas")[0];
				var img=convertCanvasToImage(canvas);
				$("#qrcode").html(img);
			}
		});
	 //上传启动图
	 $("#imageObject").on('click',function(){
		 $("#imgData").trigger('click');
	 });
	 $("#imageObjects").on('click',function(){
		 $("#imgDatas").trigger('click');
	 });
	 //下载二维码
	 $(".downloadewm").on('click',function(){
		 var $this=$(this);
		 var mark=$(this).attr("ids");
		 var url1="";
		 if(mark=="url"){
			 url1=$this.prev().attr("src");
		 }else{
			 url1=encodeURI($this.prev().find("img").attr("src"));
		 }
		 console.log(url1);
		 if(url1 && url1!=""){
			 window.location.href=rootPath+"/companyAppConfig/downLoadEwm/"+mark+"?url1="+url1;
		 }else{
			$.msg("获取下载地址失败"); 
		 }
	 });
	 //删除启动图
	 $(".del_btn_qd").on('click',function(){
		 var $this=$(this);
		 var id=$(this).attr("ids");
		 $.ajax({
	  			type: "post",
	  			url:  rootPath+"/companyAppConfig/del_qd/"+id,
	  			dataType : "json",
	  			success: function(jsonData){
	  				if(jsonData=="success"){
	  					$("#imageObject").text("上传");
	  					$("#show_qd_pic").html("");
	  					$this.hide();
	  				}
	  			}
	  		}); 
	 });
	 //复制链接 
//	 $("#copany_iphone").on('click',function(){
//		 var url=$(this).attr("url");
//		 copyToClipBoard(url);
//	 });
	 var clip = new ZeroClipboard(document.getElementById("copany_iphone"));
	 var clip1 = new ZeroClipboard(document.getElementById("copany_android"));
});



function copyToClipBoard(s) {
    if (window.clipboardData) {
        window.clipboardData.setData("Text", s);
        alert("已经复制到剪切板！"+ "\n" + s);
    } else if (navigator.userAgent.indexOf("Opera") != -1) {
        window.location = s;
    } else if (window.netscape) {
        try {
            netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");
        } catch (e) {
            alert("被浏览器拒绝！\n请在浏览器地址栏输入'about:config'并回车\n然后将'signed.applets.codebase_principal_support'设置为'true'");
        }
        var clip = Components.classes['@mozilla.org/widget/clipboard;1'].createInstance(Components.interfaces.nsIClipboard);
        if (!clip)
            return;
        var trans = Components.classes['@mozilla.org/widget/transferable;1'].createInstance(Components.interfaces.nsITransferable);
        if (!trans)
            return;
        trans.addDataFlavor('text/unicode');
        var str = new Object();
        var len = new Object();
        var str = Components.classes["@mozilla.org/supports-string;1"].createInstance(Components.interfaces.nsISupportsString);
        var copytext = s;
        str.data = copytext;
        trans.setTransferData("text/unicode", str, copytext.length * 2);
        var clipid = Components.interfaces.nsIClipboard;
        if (!clip)
            return false;
        clip.setData(trans, null, clipid.kGlobalClipboard);
    }
}
function convertCanvasToImage(canvas) {
	var image = new Image();
	image.src = canvas.toDataURL("image/png");
	return image;
}

function loadData(){
	$.ajax({
		type: "post",
		url:  rootPath+"/companyAppConfig/queryCompanyConfig",
		success: function(result){
			if(result){
				if(result.appMenuFlag && result.appMenuFlag==1){
					$(".setConfigLable").addClass("open").removeClass("l-close").find("i").html("&#xe603;").next().html("启用");
					$(".app-open").show();
				}else{
					$(".setConfigLable").addClass("l-close").removeClass("open").find("i").html("&#xe604;").next().html("禁用");
					$(".app-open").hide();
				}
				if(result.qrcodeUrl && result.qrcodeUrl!=""){
					$("#imageObjects img").attr("src",result.qrcodeUrl);
				}
				if(result.iosDownUrl && result.iosDownUrl!=""){
					$("#ios_address").val(result.iosDownUrl);
					$("#ios_cancle").attr("mark",result.iosDownUrl);
				}
				if(result.androidDownUrl && result.androidDownUrl!=""){
					$("#android_address").val(result.androidDownUrl);
					$("#andorid_cancle").attr("mark",result.androidDownUrl);
				}
				if(result.appPageLink && result.appPageLink!=""){
					$("#custom_address").val(result.appPageLink);
					$("#custom_cancke").attr("mark",result.appPageLink);
				}
				if(result.appPageType && result.appPageType==1){
					$(".chose-address").find("input[type=radio]").eq(1).attr("checked","checked");
					$(".editUrling").show();
				}else{
					$(".chose-address").find("input[type=radio]").eq(0).attr("checked","checked");
					$(".editUrling").hide();
				}
			}
		}
	});
}

//上传启动图
function savePic(){
	$.ajaxFileUpload({
		url : rootPath+"/companyAppConfig/UploadeAppImage",
		secureuri : false,// 安全协议
		async : false,
		fileElementId : 'imgData',
		dataType:'json',
		type : "POST",
		success : function(data) {
			$.msg("图片上传成功");
			$("#imageObject").text("更换");
			$("#show_qd_pic").html('<img src='+data.url+' alt=""/>');
			$(".del_btn_qd").show().attr("ids",data.id);
		},
		error:function(arg1,arg2,arg3){
			console.log(arg1);
		}
	});
}

//上传二维码
function saveewmPic(){
	$.ajaxFileUpload({
		url : rootPath+"/companyAppConfig/UploadeAppEwm",
		secureuri : false,// 安全协议
		async : false,
		fileElementId : 'imgDatas',
		dataType:'json',
		type : "POST",
		success : function(data) {
			$.msg("上传成功");
			$("#imageObjects img").attr("src",data.url);
			$("#imageObjects img").attr("ids",data.picPath);
		},
		error:function(arg1,arg2,arg3){
			console.log(arg1);
		}
	});
}