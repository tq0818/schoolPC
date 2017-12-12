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
				$("#itemOneCodeList").find("option").each(function(i){
					if($(this).is(':selected')){
						var cid=$(this).attr("data-id");
						itemOneId=cid;
					}
				});
				if(itemOneId !=null &&itemOneId.length>0)
				this.queryItemSecond(itemOneId,1);

				//选择授课方式
				$("#ltype").on('click','a.btn',function(){
					if($(this).hasClass("btn-success")){
						$(this).removeClass("btn-success");
					}else{
						$(this).addClass("btn-success");
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
	    		Form.queryCourseSetting();
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
        queryItemSecond : function (id,index){
            if(id==null){
                $("#itemOneCodeList").find("option").each(function(i){
                    if($(this).is(':selected')){
                        var cid=$(this).attr("data-id");
                        id=cid;
                    }
                });
            }
            $("#itemSecondCodeList").html('');
            $("#itemSecondCodeList").append('<option  value="" data-id="">请选择</option>');
            if(id ==null || id.length==0){
                Form.queryItemThird();
                return;
            }
            $.ajax({
                url : rootPath + "/itemTree/queryItemSecond",
                type : "post",
                data : {pid:id},
                dataType : "json",
                success : function(result) {
                    if(result.length>0){
                        $("#itemSecondCodeList").css("display","block");
                        $.each(result,function(i,item){
                        	 if(index>0){
                                if(item.itemCode==$("#twoSecItemCode").val()){
                                    $("#itemSecondCodeList").append("<option selected='selected' value='"+item.itemCode+"' data-id='"+item.id+"'>"+item.itemName+"</option>");
                                }else{
                                    $("#itemSecondCodeList").append("<option value='"+item.itemCode+"' data-id='"+item.id+"'>"+item.itemName+"</option>");
                                }
							}
                          else{
                                $("#itemSecondCodeList").append("<option value='"+item.itemCode+"' data-id='"+item.id+"'>"+item.itemName+"</option>");
                            }
                        });
                    }else{
                        $("#itemSecondCodeList").css("display","none");
                    }
                    Form.queryItemThird(null,index);
                }
            });
        },
        queryItemThird:function(id,index){
            if(id==null){
                $("#itemSecondCodeList").find("option").each(function(i){
                    if($(this).is(':selected')){
                        var cid=$(this).attr("data-id");
                        id=cid;
                    }
                });
            }
            $("#itemThirdCodeList").html('');
            $("#itemThirdCodeList").append('<option  value="" data-id="">请选择</option>');
            if(id ==null || id.length==0){
                Form.queryTagsList();
                return;
            }
            $.ajax({
                url : rootPath + "/itemTree/queryItemSecond",
                type : "post",
                data : {pid:id},
                dataType : "json",
                success : function(result) {
                    if(result.length>0){
                        $("#itemThirdCodeList").css("display","block");
                        $.each(result,function(i,item){
                            if(index>0){
                                if(item.itemCode==$("#itemThirdCode").val()){
                                    $("#itemThirdCodeList").append("<option selected='selected' value='"+item.itemCode+"' data-id='"+item.id+"'>"+item.itemName+"</option>");
                                }else{
                                    $("#itemThirdCodeList").append("<option value='"+item.itemCode+"' data-id='"+item.id+"'>"+item.itemName+"</option>");
                                }
                            }
                           else{
                                $("#itemThirdCodeList").append("<option value='"+item.itemCode+"' data-id='"+item.id+"'>"+item.itemName+"</option>");
                            }
                        });
                    }else{
                        // $("#itemThirdCodeList").css("display","none");
                    }
                    Form.queryTagsList(null,index);
                }
            });
        },
        queryTagsList : function(id,index){
            if(id==null){
                $("#itemThirdCodeList").find("option").each(function(i){
                    if($(this).is(':selected')){
                        var cid=$(this).attr("data-id");
                        id=cid;
                    }
                });
            }
            $("#itemFourthCodeList").html('');
            $("#itemFourthCodeList").append('<option  value="" data-id="">请选择</option>');
            if(id !=null && id.length>0)
            $.ajax({
                url : rootPath + "/itemTree/queryItemSecond",
                type : "post",
                data : {pid:id},
                dataType : "json",
                success : function(result) {
                    if(result.length>0){
                        $("#itemFourthCodeList").css("display","block");
                        $.each(result,function(i,item){
                        	if(index>0){
                                if(item.itemCode==$("#itemFourthCode").val()){
                                    $("#itemFourthCodeList").append("<option selected='selected' value='"+item.itemCode+"' data-id='"+item.id+"'>"+item.itemName+"</option>");
                                }else{
                                    $("#itemFourthCodeList").append("<option value='"+item.itemCode+"' data-id='"+item.id+"'>"+item.itemName+"</option>");
                                }
							}
                          else{
                                $("#itemFourthCodeList").append("<option value='"+item.itemCode+"' data-id='"+item.id+"'>"+item.itemName+"</option>");
                            }
                        });
                    }else{
                        // $("#itemFourthCodeList").css("display","none");
                    }
                }
            });
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
			addFormOne : function(mark){
                var tId=$("#itemOneCodeList").val();
                if(tId==""||tId==null){
                    $.msg("请选择分类");
                    return;
                }

                var tId=$("#itemSecondCodeList").val();
                if(tId==""||tId==null){
                    $.msg("请选择学段");
                    return;
                }
                var thId=$("#itemThirdCodeList").val();
                if(thId==""||thId==null){
                    $.msg("请选择学科");
                    return;
                }
				$("#mark").val(mark);
				var type=$("#type1").val();
				$("#lable").val($("#lableType").attr("ids"));
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
		        	                			window.location.href=rootPath+'/editSimpleCourse/editClassBaseInfo/'+$("#classtypeIds").val()+'/'+$("#lable").val();
	        	                			});
	        	                		}
	        				    	});	
	                			}else{
	                				$(".firstspan").html('该课程名称已存在').css("display","block");
	                				return;
	                			}
	                		}
	                	})
			    	}else{
			    		$(".loading-bg").hide();
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