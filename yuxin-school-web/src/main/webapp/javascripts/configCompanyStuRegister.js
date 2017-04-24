;(function($){
	var first=0;
    var Page={
		init:function(){
			$selectSubMenu('org_service');
     		$selectSubMenus('system_stu_register');
     		Page.btnInit();
     		Page.selectInit();
     		Page.initRegisterAgreement();
     		Page.saveRegisterAgreement();
     		Page.bindRegisterAgreement();
		},
    	selectInit:function(){
    		var mobileFlag=$("#mobileFlag").val();
    		var usernameFlag=$("#usernameFlag").val();
    		var closeFlag=$("#closeFlag").val();
    		if(mobileFlag==1){
    			$("#mobile").click();
    			$("#mobile").addClass("click");
    		}
    		if(usernameFlag==1){
    			$("#username").click();
    			$("#username").addClass("click");
    		}
    		if(closeFlag==1){
    			$("#close").click();
    			$("#close").addClass("click");
    		}
    		first++;
    	},
    	btnInit:function(){
    		$(".checkbox").on("click",function(){
    			var kind=$(this).attr("kind");
    			if(kind=="mobile"){
    				if($("#close").hasClass("click")){
    					$("#close").removeClass("click");
        				$("#close").removeAttr("checked");
    					$("#closeFlag").val(0);
    				}
    				if($("#mobile").hasClass("click")){
    					if($("#usernameFlag").val()==0){
    						$("#close").click();
    						$("#mobile").removeClass("click");
            				$("#username").removeClass("click");
            				$("#mobile").removeAttr("checked");
            				$("#username").removeAttr("checked");
            				$("#close").addClass("click");
            				$("#mobileFlag").val(0);
            				$("#usernameFlag").val(0);
            				$("#closeFlag").val(1);
    					}else{
	    					$("#mobile").removeClass("click");
	    					$("#mobileFlag").val(0);
    					}
    				}else{
    					$("#mobile").addClass("click");
    					$("#mobileFlag").val(1);
    				}
    				
    			}else if(kind=="username"){
    				if($("#close").hasClass("click")){
    					$("#close").removeClass("click");
    					$("#close").removeAttr("checked");
    					$("#closeFlag").val(0);
    				}
    				if($("#username").hasClass("click")){
    					if($("#mobileFlag").val()==0){
    						$("#close").click();
    						$("#mobile").removeClass("click");
            				$("#username").removeClass("click");
            				$("#mobile").removeAttr("checked");
            				$("#username").removeAttr("checked");
            				$("#close").addClass("click");
            				$("#mobileFlag").val(0);
            				$("#usernameFlag").val(0);
            				$("#closeFlag").val(1);
    					}else{
	    					$("#username").removeClass("click");
	    					$("#usernameFlag").val(0);
    					}
    				}else{
    					$("#username").addClass("click");
    					$("#usernameFlag").val(1);
    				}
    			}else if(kind=="close"){
    				if($("#close").hasClass("click")){
    					$("#closeFlag").val(0);
    					$("#mobile").click();
    					$("#mobile").addClass("click");
    					$("#mobileFlag").val(1);
    				}else{
    					$("#mobile").removeClass("click");
        				$("#username").removeClass("click");
        				$("#mobile").removeAttr("checked");
        				$("#username").removeAttr("checked");
        				$("#close").addClass("click");
        				$("#mobileFlag").val(0);
        				$("#usernameFlag").val(0);
        				$("#closeFlag").val(1);
    				}
    			}
    			if(first>0){
    				Page.saveRegisterConfig();
    			}
    		})
    	},
    	saveRegisterConfig:function(){
    		var data={};
    		data.mobileFlag=$("#mobileFlag").val();
    		data.usernameFlag=$("#usernameFlag").val();
    		data.closeFlag=$("#closeFlag").val();
    		$.ajax({
    			url : rootPath + "/companyRegisterConfig/saveRegisterConfig",
    			type : "post",
    			data : data,
    			success : function(data){
    				if(data.msg="success"){
    					$.msg("操作成功！")
    				}else{
    					$.msg("操作失败，请刷新页面重新操作！")
    				}
    			}
    		});
    	},
    	initRegisterAgreement:function(){
    		var registerAgreement = $("#registerAgreement").val();
    		if(null == registerAgreement || "" == registerAgreement ){
    			registerAgreement = $("#registerAgreementDef").val();
    		}
    		$("#registerAgreement").val(encodeURI(registerAgreement));
    		var registerAgreementFlag = $("#registerAgreementFlag").val();
    		if(registerAgreementFlag != 1){
    			$("#registerAgreement_").css("display","none");
    		}else{
    			$("#registerAgreement_").css("display","inline");
    		}
    	},
    	saveRegisterAgreement:function(){
    		$("p .btn-primary").on("click",function(){
    			Page.initSaveRegisterAgreement();
    			var registerAgreementTest = $("#newsContents").val();
    			var data={};
    			data.registerAgreement = $("#registerAgreement").val();
    			data.registerAgreementFlag = $("#registerAgreementFlag").val();
    			data.registerAgreement = $("#newsContents").val();
    			data.registerAgreementFlag = $("#registerAgreementFlag").val();
    	        $.ajax({
    	    		url : rootPath + "/companyRegisterConfig/updateRegisterConfig",
    	    		type : "post",
    	    		data : data,
    	    		success : function(data){
    	    			if(data=="success"){
    	    				$.msg("保存成功");
    	    			}else{
    	    				$.msg("操作失败，请刷新页面重新操作！");
    	    			}
    	    		}
    	    	});
    		});
    	},
    	initSaveRegisterAgreement:function(){
    		for (instance in CKEDITOR.instances){
    			CKEDITOR.instances[instance].updateElement();
    		}
    	},
    	bindRegisterAgreement:function(){
    		$(".screen-right-cont em.normal").off().on("click",function(){
    			var $this = $(this),
    				licong_data = {validateEmailFlag:0};
    			if($this.hasClass('emailFlag')){
    				if(!$this.hasClass('open')){
    					licong_data.validateEmailFlag = 1;
    				}
    				$.ajax({
     	    			url : rootPath + "/companyRegisterConfig/update",
     	    			type : "post",
     	    			data : licong_data,
     	    			success : function(){
     	    				$.msg("操作成功");
     	    				if($this.hasClass('open')){
     	    					$this.removeClass('open').addClass('close').html('&#xe604;').next().removeClass('open').addClass('close').html('已禁用');
     	    				}else{
     	    					$this.removeClass('close').addClass('open').html('&#xe603;').next().removeClass('close').addClass('open').html('已启用');
     	    				}
     	    			},
     	    			error : function(){
     	    				$.msg("操作失败，请联系管理员！");
     	    			}
     	    		});
    				return;
    			}
    			if($(this).hasClass("open")){
    				var data={};
        			data.registerAgreementFlag = $("#registerAgreementFlag").html();
        			if("" == data.registerAgreementFlag || null == data.registerAgreementFlag || 1 == data.registerAgreementFlag )
        				data.registerAgreementFlag = 0;
        			$("#registerAgreementFlag").val(0);
     	            $.ajax({
     	    			url : rootPath + "/companyRegisterConfig/updateRegisterConfigflag",
     	    			type : "post",
     	    			data : data,
     	    			success : function(data){
     	    				if(data=="success"){
     	    					$.msg("操作成功")
     	    				}else{
     	    					$.msg("操作失败，请刷新页面重新操作！")
     	    				}
     	    			}
     	    		});
     	           $(this).removeClass("open").addClass("close").html("&#xe604;");
     	           $(this).parents(".l-title").find(".i").removeClass("open").addClass("close").text("已禁用");
     	           $("#registerAgreement_").css("display","none");
    			}else if($(this).hasClass("close")){
    				var data={};
    	   			data.registerAgreementFlag = $("#registerAgreementFlag").html();
        			if("" == data.registerAgreementFlag || null == data.registerAgreementFlag || 1 != data.registerAgreementFlag)
        				data.registerAgreementFlag = 1;
        			$("#registerAgreementFlag").val(1);
    		        $.ajax({
    		    			url : rootPath + "/companyRegisterConfig/updateRegisterConfigflag",
    		    			type : "post",
    		    			data : data,
    		    			success : function(data){
    		    				if(data=="success"){
    		    				   $.msg("操作成功")
    		    				}else{
    		    					$.msg("操作失败，请刷新页面重新操作！")
    		    				}
    		    			}
    		    	});
    		        $(this).removeClass("close").addClass("open").html("&#xe603;");
     	 	        $(this).parents(".l-title").find(".i").removeClass("close").addClass("open").text("已启用");
     	 	        $("#registerAgreement_").css("display","inline");
    			}
    		})
    	}
    }
 
    $(document).ready(function(){
		Page.init();
	});
})(jQuery)
