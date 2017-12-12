/**
 * author zhang.zx
 * 代报考
 * 页面js封装
 */
(function($){
	var rules={
	        errorElement: 'span', 
	        errorClass: 'tips', 
	        focusInvalid: false, 
	        ignore: "",
	        rules: {
	            name: {
	                minlength: 2,
	                maxlength: 20,
	                required: true,
	                remote: {
                		url: rootPath+"/simpleClasses/checkClassTypeName",
                		type:"post",
                		dataType:"json",
                		data:{
                			name : function(){
                				return $("#classTypeName").val();
                			},
                		 error:function(arg,arg1,arg2){
                			 $(".loading-bg").hide();
                		 }
                		}
                	}
	            },
	            originalPrice:{
	            	number:true,
	            	required:true,
	            	max:99999
	            	
	            },
	            realPrice:{
	            	required:true,
	            	number:true,
	            	max:99999
	            },
	            publicPrice:{
	            	number:true,
	            	max:99999
	            },
	            baseNum:{
	            	required: true,
	            	digits:true,
	            	number:true,
	            	max:999999
	            },
	            buyNumMax:{
	            	digits:true,
	            	number:true,
	            	max:999999
	            },
	            courseNum:{
	            	required: true,
	            	digits:true,
	            	number:true,
	            	min:1,
	            	range:[0,999]
	            }
	        },
	        messages: {
	        	name:{
	        		remote:"该课程名称已存在"
	        	}
	        },
	        success: function (label) {
	        },
	        submitHandler: function (form) {
	            form.submit();
	        },
	        onkeyup: false
	    }
	  var rule1={
	        errorElement: 'span', 
	        errorClass: 'tips', 
	        focusInvalid: false, 
	        ignore: "",
	        rules: {
	            name: {
	                minlength: 2,
	                maxlength: 20,
	                required: true
	            },
	            originalPrice:{
	            	number:true,
	            	required:true,
	            	max:99999
	            	
	            },
	            realPrice:{
	            	required:true,
	            	number:true,
	            	max:99999
	            },
	            publicPrice:{
	            	number:true,
	            	max:99999
	            },
	            baseNum:{
	            	required: true,
	            	digits:true,
	            	number:true,
	            	max:999999
	            },
	            buyNumMax:{
	            	digits:true,
	            	number:true,
	            	max:999999
	            },
	            courseNum:{
	            	required: true,
	            	digits:true,
	            	number:true,
	            	min:1,
	            	range:[0,999]
	            }
	        },
	        messages: {
	        },
	        success: function (label) {
	        },
	        submitHandler: function (form) {
	            form.submit();
	        },
	        onkeyup: false
	    }
	var Form={
			init : function(){
				
				$.extend($.validator.messages, {
				    required: "必选字段",
					remote: "请修正该字段",
					email: "请输入正确格式的电子邮件",
					url: "请输入合法的网址",
					date: "请输入合法的日期",
					dateISO: "请输入合法的日期 (ISO).",
					number: "请输入合法的数字",
					digits: "只能输入整数",
					creditcard: "请输入合法的信用卡号",
					equalTo: "请再次输入相同的值",
					accept: "请输入拥有合法后缀名的字符串",
					maxlength: jQuery.validator.format("请输入一个 长度最多是 {0} 的字符串"),
					minlength: jQuery.validator.format("请输入一个 长度最少是 {0} 的字符串"),
					rangelength: jQuery.validator.format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
					range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
					max: jQuery.validator.format("请输入一个最大为{0} 的值"),
					min: jQuery.validator.format("请输入一个最小为{0} 的值"),
					isMobile : "不是有效的手机号"
				});
				var itemOneId="";
				$("#itemOneList").find("option").each(function(i){
					if($(this).is(':selected')){
						var cid=$(this).attr("value");
						itemOneId=cid;
					}
				});
				this.queryItemSecond(itemOneId);
				
				//点击根据所选授课方式改变导航栏
				$("#ltype").on('click','a.btn',function(){
					if($(this).hasClass("btn-success")){
						var count=0;
						$("#ltype").find("a.btn").each(function(i){
							if(!$(this).hasClass("btn-success")){
								count++;
							}
						});
						if(count>=1){
							return;
						}
						$(this).removeClass("btn-success");
					}else{
						$(this).addClass("btn-success");
					}
				});
				
				//查询公司不存在服务
				$.ajax({
					url : rootPath + "/companyServiceStatic/simplecompanyNoServices",
					type : "post",
					dataType : 'json',
					success : function(jsonData) {
						var count=jsonData.length;
						if(count<=1){
							$.each(jsonData,function(i,item){
								if(item.groupCode=='SERVICE_LIVE'){
									$("#ltype").find("a.btn").each(function(i){
										var mark=$(this).attr("ids");
										if(mark=="live"){
											$(this).addClass("none").removeClass("btn-success");
										}
									});
								}else if(item.groupCode=='SERVICE_FACE'){
									$("#ltype").find("a.btn").each(function(i){
										var mark=$(this).attr("ids");
										if(mark=="face"){
											$(this).addClass("none").removeClass("btn-success");
										}
									});
								}else{
									$("#ltype").find("a.btn").each(function(i){
										var mark=$(this).attr("ids");
										if(mark=="video"){
											$(this).addClass("none").removeClass("btn-success");
										}
									});
								}
							});
						}
					}
				});
				
				$(".prices").bind("keyup",function(event){
	    			//先把非数字的都替换掉，除了数字和. 
	    			$(this).val($(this).val().replace(/[^\d.]/g,""));
	    	        //必须保证第一个为数字而不是. 
	    	        $(this).val($(this).val().replace(/^\./g,"0."));
	    	        //保证只有出现一个.而没有多个. 
	    	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
	    	        //保证.只出现一次，而不能出现两次以上
	    	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
	    		})
	    		$(".prices").bind("blur",function(event){
	    			//先把非数字的都替换掉，除了数字和. 
	    			$(this).val($(this).val().replace(/[^\d.]/g,""));
	    			var value=$(this).val();
	     	        var is=false;
	     	        for (var i = 0; i < value.length; i++) {
	     	            var  item =  value.charAt(i);
	     	           	if("."==item){
	     	           		is=true;
	     	           	}
	     	        }
	     	        if(value!=null||value!=""){
	     	        	if(!is){
	     	        		$(this).val($(this).val()+".00");	
	     	        	}
	     	        } 
	    	        //必须保证第一个为数字而不是. 
	    	        $(this).val($(this).val().replace(/^\./g,"0."));
	    	        //保证只有出现一个.而没有多个. 
	    	        $(this).val($(this).val().replace(/\.{2,}/g,"."));
	    	        //保证.只出现一次，而不能出现两次以上
	    	        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));
	    	        //保留小数点后两位
	    	        $(this).val($(this).val().substring(0,$(this).val().indexOf(".")+3));
	    		})
	    		//课程设置框只能输入数字
				$(".settingcount").keyup(function(){     
			        var tmptxt=$(this).val();     
			        $(this).val(tmptxt.replace(/\D/g,''));     
			    }).bind("paste",function(){     
			        var tmptxt=$(this).val();     
			        $(this).val(tmptxt.replace(/\D/g,''));     
			    }).css("ime-mode", "disabled");
				

				//公开课程价格
				$("input[name='isPublic']").on('click',function(){
					var ispublic=$(this).val();
					if('1'==ispublic){
						$(".publicPrice").removeClass("none");
					}else{
						$(".publicPrice").addClass("none");
						$("#publicPrice").val(null);
					}
				});		
				//判断是否显示三级标签
	    		$.ajax({
					url : rootPath + "/serviceGroup/lableSetting",
					type : "post",
					success : function(result) {
						if(result==""||result.status==0){
							$("#labeSets").addClass("none");
						}else{
							$("#labeSets").removeClass("none");
						}
					}
				});
	    		$.ajax({
					url : rootPath + "/serviceGroup/lableSeondSetting",
					type : "post",
					success : function(result) {
						if(result==""||result.status==0){
							$("#labeSecondSets").addClass("none");
						}else{
							$("#labeSecondSets").removeClass("none");
						}
					}
				});
	    		
	    		this.queryCourseProtocolConfig();
	    		
			},
			/**
			 * 查询课程协议
			 * @param callback 扩展以后如果再添加dom元素的时候可以执行该方法
			 */
			queryCourseProtocolConfig : function(callback){
				var pHtml = '',
					optionHtml = '<option value="0"></option>',
					selected = false,
					courseProtocols,
					courseProtocol,
					protocolId = $('.heading').data('protocol');
				$.ajax({
					url : rootPath + "/classType/getCtPotocolSet",
					type : "post",
					data : {type : 0 ,functionCode : 'CLASS_POTOCOL_SET'},
					success : function(result){
						if(!result || !result.open) return;
						courseProtocols = result.courseProtocolConfig;
						for(var i=0; i<courseProtocols.length; i++){
							courseProtocol = courseProtocols[i];
							selected = protocolId == courseProtocol.id;
							optionHtml += '<option value="'+courseProtocol.id+'" '+ (selected?'selected':'') +'>'+ courseProtocol.name +'</option>';
						}
						
						pHtml = '<p class="c">'+
				                    '<span class="c-title">课程协议</span>'+
				                    '<span class="c-content">'+
				                        '<select id="courseProtocol" name="protocolId" style="width:189px;min-width: 180px;">'+ optionHtml +'</select>'+
				                    '</span>'+
				                '</p>';
						$('#lableType').parents('p').before(pHtml);
						
						if(callback){
							callback();
						}
					}
				});
			},
			queryItemSecond : function (id){
				if(id==null){
					$("#itemOneList").find("option").each(function(i){
						if($(this).is(':selected')){
							var cid=$(this).attr("value");
							id=cid;
						}
					});
				}
				$("#itemSecondList").html('');
				$.ajax({
					url : rootPath + "/exam/queryItemSecond",
					type : "post",
					data : {pid:id},
					dataType : "json",
					success : function(result) {
						if(result.length>0){
							$("#itemSecondList").css("display","block");
							$.each(result,function(i,item){
								if(item.id==$("#twoSecItemId").val()){
									$("#itemSecondList").append("<option selected='selected' value='"+item.id+"'>"+item.itemName+"</option>");
								}else{
									$("#itemSecondList").append("<option value='"+item.id+"'>"+item.itemName+"</option>");
								}
							});
						}else{
							$("#itemSecondList").css("display","none");
						}
						
					}
				});
				Form.queryTagsList();
				Form.queryCourseSetting();
			},
			queryTagsList : function(){
				var id=$("#itemOneName").attr("marks");
		    	var twoId=$("#itemSecondName").attr("marks");
				$(".itemTagLists").html("");
				//初始化标签库
				$.ajax({
					url: rootPath+"/sysConfigItemTag/queryTags",
					type: "post",
					dataType : "json",
					data:{"itemOneId":id,"itemSecondId":twoId,"level":1},
					success: function(jsonData){
						var html="";
						//标签数据
			    		var lbName=$("#course_lable_tag").val();
			    		var str=lbName.split(",");
						$.each(jsonData,function(i,data){
		    				if(str[0] && str[0]!="" && str[0]!=null && str[0]!="null"){
		    					if(data.id==str[0]){
		    						html+='<option selected="selected" value='+data.id+'>'+(data.tagName?data.tagName:"")+'</option>';
		    					}else{
		    						html+='<option value='+data.id+'>'+(data.tagName?data.tagName:"")+'</option>';
		    					}
		    				}else{
		    					html+='<option value='+data.id+'>'+(data.tagName?data.tagName:"")+'</option>';
		    				}
						});
						$("#itemTagLists_one").html(html);
						$("#itemTagLists_one").select2();
					}
				});
				//初始化标签库
				$.ajax({
					url: rootPath+"/sysConfigItemTag/queryTags",
					type: "post",
					dataType : "json",
					data:{"itemOneId":id,"itemSecondId":twoId,"level":2},
					success: function(jsonData){
						var html1="";
						var lbName=$("#course_lable_tag").val();
						var str=lbName.split(",");
						$.each(jsonData,function(i,data){
		    				if(str[1] && str[1]!="" && str[1]!=null && str[1]!="null"){
		    					if(data.id==str[1]){
		    						html1+='<option selected="selected" value='+data.id+'>'+(data.tagName?data.tagName:"")+'</option>';
		    					}else{
		    						html1+='<option value='+data.id+'>'+(data.tagName?data.tagName:"")+'</option>';
		    					}
		    				}else{
		    					html1+='<option value='+data.id+'>'+(data.tagName?data.tagName:"")+'</option>';
		    				}
						});
						$("#itemTagLists_two").html(html1);
						$("#itemTagLists_two").select2();
					}
				})
			},
			queryCourseSetting : function(){
				//判断公司课程设置开关
				$.ajax({
					url : rootPath + "/serviceGroup/couseSetting",
					type : "post",
					success : function(result){
						if(result==""||result.status==0){
							$(".setting").addClass("none");
						}else{
							$(".setting").removeClass("none");
						}
					}
				});
			},
			
			addFormOne : function(mark){
				$("#mark").val(mark);
				var type=$("#type1").val();
				var ltype="",ltype1="";
				$("#ltype").find("a.btn").each(function(){
					if($(this).hasClass("btn-success")){
						ltype+=$(this).attr("ids")+",";
					}else{
						ltype1+=$(this).attr("ids")+",";
					}
				});
				$("#courseType").val(ltype);
				$.cookie("ltype", ltype1); 
				$("#lable").val("togther");
				//验证优惠价和原价
				var np=$.MoneyToNum($("#realPrice").val());//优惠价
			    var op=$.MoneyToNum($("#prices").val());//原价
				if(np>op){
					$.msg("优惠价不能大于原价");
					return;
				}
				//标签数据
				var str="",str1=$("#itemTagLists_one").val(),str2=$("#itemTagLists_two").val();
				if(str1 && str2){
					str=str1+","+str2;
				}else if(str1 && !str2){
					str=str1;
				}else if(!str1 && str2){
					str=null+","+str2;
				}
				$("#course_lable_tag").val(str);
				var flag=$(".member_settings").find("input[name=memberFlag]:checked").val();
				if(flag && flag==1 && !$(".member_settings").hasClass("none")){
					var len=$("#level_member_list").find("input[type=checkbox]:checked").length;
					if(!len || len<=0){
						$.msg("请设置会员等级");
						return;
					}
				}	
			    if(type=="update"){
			    	$(".firstspan").html('');
					$("#addFormOne").validate(rule1);
					var ispublic=$("input[name='isPublic']:checked").val();
                    var publicPrice=$("#publicPrice").val();
                    if('1'==ispublic&&!publicPrice){
                    	$.msg("请设置公开课程价格!");
                        return;
                    }
			    	if($("#addFormOne").valid()){
			    		$.ajax({
			    			url: rootPath+"/simpleClasses/checkUpdateClassTypeName",
	                		type:"post",
	                		dataType:"json",
	                		data:{"name":$("#classTypeName").val(),"id":$("#classtypeIds").val()},
	                		success:function(b){
	                			if(b){
	                				var itemOneId=$("#itemOneName").attr("marks");
	        				    	var itemSecondId=$("#itemSecondName").attr("marks");
	        				    	$("#oneId").val(itemOneId);
	        				    	$("#twoId").val(itemSecondId);
	        				    	$.ajax({
	        			    			url: rootPath+"/editSimpleCourse/updateClassTypeMessage",
	        	                		type:"post",
	        	                		dataType:"json",
	        	                		data:$("#addFormOne").serialize(),
	        	                		success:function(b){
	        	                			Form.updateCourseMember($("#classtypeIds").val(),function(){
	        	                				$.msg("保存信息成功");
		        	                			window.location.href=rootPath+'/editSimpleCourse/editClassBaseInfo/'+$("#classtypeIds").val()+'/togther';
	        	                			});
	        	                		}
	        				    	});	
	        				    	return;
	                			}else{
	                				$(".firstspan").html('该课程名称已存在').css("display","block");
	                				return;
	                			}
	                		}
	                	})
			    	}else{
			    		$(".loading-bg").hide();
			    		return;
			    	}
			    }
			},
			updateCourseMember:function(id,callback){
				var data={};
				data.classTypeId=id;
				var flag=$(".member_settings").find("input[name=memberFlag]:checked").val();
				if(flag && flag==1){
					var memberLevel=new Array();
					$("#level_member_list").find("input[type=checkbox]:checked").each(function(){
						var typ=$(this).attr("setmark");
						if(!$(this).attr("setmark") || $(this).attr("setmark")=="null"){
							typ=1;
						}
						memberLevel.push({memberLevel:$(this).attr("leveName"),memberDiscount:$(this).attr("discount"),memberId:$(this).val(),classTypeId:id,discountType:typ});
					});
					data.list=JSON.stringify(memberLevel);
				}
				$.ajax({
	    			url: rootPath+"/classTypeMemberDiscount/addMemberLevel",
            		type:"post",
            		dataType:"json",
            		data:data,
            		success:function(b){
            			if(callback){
            				callback();
            			}
            		}
		    	});	
			}
		}
	$(document).ready(function(){
		Form.init();
	})
	window.Form=Form;
})(jQuery)