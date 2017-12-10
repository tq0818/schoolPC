$(document).ready(function(){
	 $.ajax({
			url : rootPath + "/branchSchool/queryServiceSet",
			type : "post",
			data : {"groupCode":"SERVICE_INTEGRAL","companyId":$("#companyId").val()},
			success : function(result) {
				if(result=="hide"){
					$(".integral_settings").addClass("none");
				}else{
					$.ajax({
						url : rootPath + "/branchSchool/queryIntegralConfig",
						type : "post",
						data : {"companyId":$("#companyId").val()},
						success : function(result) {
							if(result){
								if(result.classCost && result.classCost==1){
									$(".integral_settings").removeClass("none");
								}
								if(result.classCostMaxScale){
									$(".jf_bl").html(result.classCostMaxScale);
								}else{
									$(".jf_bl").html(0);
								}
							}else{
								$(".integral_settings").addClass("none");
							}
						}
					});
				}
			}
		});
	 //课程购买人数设置
	 $.ajax({
			url : rootPath + "/branchSchool/buySet",
			type : "post",
			data : {"companyId":$("#companyId").val()},
			success : function(result) {
				if(result && result=="show"){
					$(".buyNumSetting").removeClass("none");
				}
			}
	 });		
	 $.ajax({
			url : rootPath + "/branchSchool/queryServiceSet",
			type : "post",
			data : {"groupCode":"SERVICE_MEMBER","companyId":$("#companyId").val()},
			success : function(result) {
				if(result=="hide"){
					$(".member_settings").addClass("none");
					$(".member_setting_bl").addClass("none");
				}else{
					$(".member_settings").removeClass("none");
					var flag=$(".member_settings").find("input[name=memberFlag]:checked").val();
					if(flag && flag==1){
						$(".member_setting_bl").removeClass("none");
					}
					$.ajax({
						url : rootPath + "/branchSchool/queryMemberConfig",
						type : "post",
						data : {"companyId":$("#companyId").val()},
						success : function(result) {
							if(result){
								if(result.classDiscount && result.classDiscount==1){
									$(".member_setting_bl").find("a.edt").removeClass("none");
								}
//								$(".member_settings").addClass("none");
//								$(".member_setting_bl").addClass("none");
							}
//							else{
//								//$(".member_settings").removeClass("none");
//								if(result && result.classDiscount && result.classDiscount==1){
//									$(".member_setting_bl").find("a.edt").removeClass("none");
//								}
//							}
						}
					});
				}
			}
		});
	 //查询会员列表
	 $("#level_member_list").html("");
	 $.ajax({
			url : rootPath + "/companyMemberLevelConfig/queryLevelList",
			type : "post",
			data : {"classTypeId":$("#classtypeIds").val(),"companyId":$("#companyId").val()},
			success : function(data) {
				var html="";
				$.each(data,function(i,level){
					var ht='<em><z class="zk'+level.id+'" mark="'+(level.discount?level.discount:0)+'">'+(level.discount?level.discount:0)+'<f>折</f></z></em>';
					if(i==0){
						if(level && level.discountType==0){
							ht='<em><z class="zk'+level.id+'" mark="0">免费</z></em>';
						}
						html+='<input class="level'+level.id+'" type="checkbox" discount='+(level.discount?level.discount:0)+' setmark='+level.discountType+' '+(level.useFlag==1?('checked="checked"'):"")+' value="'+level.id+'" leveName='+level.name+' name="grade"> <em>'+level.name+'</em> '+ht+' <a class="edt none" ids='+level.id+' setmark='+level.discountType+' href="javascript:void(0)">编辑</a>';
					}else{
						if(level && level.discountType==0){
							ht='<em><z class="zk'+level.id+'" mark="0">免费</z></em>';
						}
						html+='<input class="level'+level.id+'" type="checkbox" discount='+(level.discount?level.discount:0)+' setmark='+level.discountType+' '+(level.useFlag==1?('checked="checked"'):"")+' value="'+level.id+'" leveName='+level.name+' name="grade" style="margin-left: 30px"> <em>'+level.name+'</em> '+ht+' <a class="edt none" ids='+level.id+' setmark='+level.discountType+' href="javascript:void(0)">编辑</a>';
					}
				});
				$("#level_member_list").html(html);
				//课程添加会员
			     $(".edt").on("click.b",function(){
			    	 $(".pric").val($("#realPrice").val());
			    	 var id=$(this).attr("ids");
			    	 $(".save_member_set").attr("ids",id);
			    	 $("#mem_title_Name").html($(this).prev().prev().text());
			    	 var openWay=$(this).attr("setmark");
			    	 if(openWay==0){
			    		 $(".add-page-board").find("input[type=radio]").eq(0).removeAttr("checked");
			    		 $(".add-page-board").find("input[type=radio]").eq(1).attr("checked","checked");
			    		 $(".add-page-board").find("input[type=radio]").eq(1).click();
			    	 }else{
			    		 $(".add-page-board").find("input[type=radio]").eq(0).attr("checked","checked");
			    		 $(".add-page-board").find("input[type=radio]").eq(1).removeAttr("checked");
			    		 $(".add-page-board").find("input[type=radio]").eq(0).click();
			    	 }
			    	 var discount=$(".level"+id).attr("discount");
			    	 var realPrice=$.MoneyToNum($(".pric").val());
			    	 var youhuiPrice=realPrice*(discount*0.1);
			    	 var chaPrice=realPrice-youhuiPrice;
			    	 $("#chaPrice").val(Math.round(chaPrice*100)/100);
			    	 $("#youhuiPrice").val(Math.round(youhuiPrice*100)/100);
			    	 $("#course_discount").val(discount);
			         $(".add-page-pop").fadeIn(100);
			     });
			     
			     $("#course_discount").on('keyup',function(){
			    	 var discount=$(this).val();
			    	 var realPrice=$("#realPrice").val();
			    	 var youhuiPrice=$("#realPrice").val();
			    	 var chaPrice=0;
			    	 if(discount && discount!=""){
			    		 youhuiPrice=realPrice*(discount*0.1);
			    		 chaPrice=realPrice-youhuiPrice;
			    	 }
			    	 $("#chaPrice").val(Math.round(chaPrice*100)/100);
			    	 $("#youhuiPrice").val(Math.round(youhuiPrice*100)/100);
			     }).on('blur',function(){
			    	 var discount=$(this).val();
			    	 var realPrice=$.MoneyToNum($("#realPrice").val());
			    	 var youhuiPrice=$.MoneyToNum($("#realPrice").val());
			    	 var chaPrice=0;
			    	 if(discount && discount!=""){
			    		 youhuiPrice=realPrice*(discount*0.1);
			    		 chaPrice=realPrice-youhuiPrice;
			    	 }
			    	 $("#chaPrice").val(Math.round(chaPrice*100)/100);
			    	 $("#youhuiPrice").val(Math.round(youhuiPrice*100)/100);
			     });
			     
			     $("#chaPrice").on('blur',function(){
			    	 var $this=$(this);
			    	 var chaPrice=$.MoneyToNum($(this).val());
			    	 var realPrice=$.MoneyToNum($("#realPrice").val());
			    	 var youhuiPrice=$.MoneyToNum($("#realPrice").val());
			    	 var discount=10;
			    	 if(chaPrice && chaPrice!=""){
			    		 if(chaPrice>realPrice || chaPrice<0){
			    			 $this.val(0);
			    			 chaPrice=0;
			    		 }
			    		 youhuiPrice=realPrice-chaPrice;
			    		 if(realPrice>0){
			    			 discount=(youhuiPrice/realPrice)*10;  
			    		 }else{
			    			 discount=0;
			    		 } 
			    	 }
			    	 $("#course_discount").val(Math.round(discount*100)/100);
			    	 $("#youhuiPrice").val(Math.round(youhuiPrice*100)/100);
			     }).on('keyup',function(){
			    	 var $this=$(this);
			    	 var chaPrice=$.MoneyToNum($(this).val());
			    	 var realPrice=$.MoneyToNum($("#realPrice").val());
			    	 var youhuiPrice=$.MoneyToNum($("#realPrice").val());
			    	 var discount=10;
			    	 if(chaPrice && chaPrice!=""){
			    		 if(chaPrice>realPrice || chaPrice<0){
			    			 $this.val(0);
			    			 chaPrice=0;
			    		 }
			    		 youhuiPrice=realPrice-chaPrice;
			    		 if(realPrice>0){
			    			 discount=(youhuiPrice/realPrice)*10;  
			    		 }else{
			    			 discount=0;
			    		 } 
			    	 }
			    	 $("#course_discount").val(Math.round(discount*100)/100);
			    	 $("#youhuiPrice").val(Math.round(youhuiPrice*100)/100);
			     });
			     
			     $("#youhuiPrice").on('blur',function(){
			    	 var $this=$(this);
			    	 var youhuiPrice=$.MoneyToNum($(this).val());
			    	 var realPrice=$.MoneyToNum($("#realPrice").val());
			    	 var discount=10;
			    	 if(youhuiPrice && youhuiPrice!=""){
			    		 if(youhuiPrice>realPrice || youhuiPrice<0){
			    			 $this.val(realPrice);
			    			 youhuiPrice=$("#realPrice").val();
			    		 }
			    		 chaPrice=realPrice-youhuiPrice;
			    		 if(realPrice>0){
			    			 discount=(youhuiPrice/realPrice)*10;
			    		 }else{
			    			 discount=0;
			    		 } 
			    	 }else{
			    		 youhuiPrice=$("#realPrice").val();
			    	 }
			    	 $("#chaPrice").val(Math.round(chaPrice*100)/100);
			    	 $("#course_discount").val(Math.round(discount*100)/100);
			     }).on('keyup',function(){
			    	 var $this=$(this);
			    	 var youhuiPrice=$.MoneyToNum($(this).val());
			    	 var realPrice=$.MoneyToNum($("#realPrice").val());
			    	 var discount=10;
			    	 if(youhuiPrice && youhuiPrice!=""){
			    		 if(youhuiPrice>realPrice || youhuiPrice<0){
			    			 $this.val(realPrice);
			    			 youhuiPrice=$("#realPrice").val();
			    		 }
			    		 chaPrice=realPrice-youhuiPrice;
			    		 if(realPrice>0){
			    			 discount=(youhuiPrice/realPrice)*10;
			    		 }else{
			    			 discount=0;
			    		 } 	
			    	 }else{
			    		 youhuiPrice=$("#realPrice").val();
			    	 }
			    	 $("#chaPrice").val(Math.round(chaPrice*100)/100);
			    	 $("#course_discount").val(Math.round(discount*100)/100);
			     });
			     
			     $(".priced").on("change keyup keydown keypress",function(){
			         var _val = $(this).val();
			         $(".pric").val(_val);
			     })
			     $(".member_settings").on('click','input[type=radio]',function(){
			    	 var val=$(this).val();
			    	 if(val==1){
			    		 $(".member_setting_bl").removeClass("none");
			    	 }else{
			    		 $(".member_setting_bl").addClass("none");
			    	 }
			     })
			     //保存
			     $(".save_member_set").on('click',function(){
			    	 var id=$(this).attr("ids");
			    	 var type=$(".add-page-board").find("input[name=openWay]:checked").val();
			    	 if(type==0){
			    		 $(".zk"+id).html("免费").next().html("");
				    	 $(".level"+id).attr("discount",0);
				    	 $(".level"+id).attr("setmark",0).next().next().next().attr("setmark",0);
			    	 }else{
			    		 var d=$("#course_discount").val();
				    	 if(d>10 || d<0){
				    		 $.msg("折扣输入不正确，请输入0-10之间的数字");
				    		 return;
				    	 }
				    	 $(".zk"+id).html($("#course_discount").val()+"<f>折</f>");
				    	 $(".level"+id).attr("discount",$("#course_discount").val());
				    	 $(".level"+id).attr("setmark",1).next().next().next().attr("setmark",1);
			    	 }
			    	 $(".add-page-pop").fadeOut(200);
			         $("body").removeClass("popup-open");
			     });
			}
		});
	 $(".c-main").on("click.a",".show-student",function(){
         $(".add-page-pop").fadeIn(100);
         $("body").addClass("popup-open");
     });
	 $(".cancle_qx").on("click", function () {
         $(".add-page-pop").fadeOut(200);
         $("body").removeClass("popup-open");
     });
     $(".page-pop-title em").on("click", function () {
         $(".add-page-pop").fadeOut(200);
         $("body").removeClass("popup-open");
     });
     $(".print_prices").bind("keyup",function(event){
    	$(this).removeClass("printErorColor").removeClass("printYesColor").addClass("printColor");
    	//先把非数字的都替换掉，除了数字和. 
		$(this).val($(this).val().replace(/[^\d.]/g,""));
        //必须保证第一个为数字而不是. 
        $(this).val($(this).val().replace(/^\./g,"0."));
        //保证只有出现一个.而没有多个. 
        $(this).val($(this).val().replace(/\.{2,}/g,"."));
        //保证.只出现一次，而不能出现两次以上
        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
        if($(this).val()>10||$(this).val()<0){
        	$(this).val(10);
        	$(this).removeClass("printColor").removeClass("printYesColor").addClass("printErorColor");
        }
	}).on('blur',function(){
		if($(this).val()>10||$(this).val()<0){
        	$(this).val(10);
        	$(this).removeClass("printColor").removeClass("printYesColor").addClass("printErorColor");
        }
		$(this).removeClass("printColor").removeClass("printErorColor").addClass("printYesColor");
	});
 });