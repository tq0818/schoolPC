(function ($) {

    var student = {
        init: function () {
            var $this = this;
            // 初始化数据
            $this.search();
            $this.queryCompanyMemberList();
            // 收索
            $(".searchContents").on('click', function () {
                $this.search();
            });
            //显示会员
            $(".is_member").on('click',function(){
            	$this.search();
            })
            //全选 取消全选
            $(".checkboxAll").on('change', function () {
            	var html="";
                if ($(this).prop("checked")) {
                	 $(".student-list").find(".signUpMany").prop("checked", true);
                	 $(".student-list").find(".signUpMany").each(function(){
                		 var id=$(this).attr("uId");
                		 var name=$(this).attr("mark");
                		 if($(this).is(":checked")){
                			 if($("#group").find("span.u_list"+id).length<=0){
                				 $("#group").append('<span class="u_list'+id+'" ids='+id+'>'+($(this).val()?$(this).val():"")+(name?"-"+name:"")+'<i class="iconfont">&#xe6be;</i></span>');
                			 }
                		 }
                	 });
                	 $("#choseStuNum").text($("#group").find("span").length);
                } else {
                	 $(".student-list").find(".signUpMany").prop("checked", false);
                	 $(".student-list").find(".signUpMany").each(function(){
                		 var id=$(this).attr("uId");
                		 if(!$(this).is(":checked")){
                			 $("#group").find("span.u_list"+id).remove();
                		 }
                	 });
                	 $("#choseStuNum").text($("#group").find("span").length);
                }
            });
            //优惠码前缀校验
//            $(".tikeckts_fornt").on('blur',function(){
//            	var mark=$(this).val();
//            	var re=new RegExp("^[A-Za-z]+$");
//            	if(mark=="" || mark.length<=0){
//            		return;
//            	}else{
//            		 if(!re.test(mark)){
//            	    	 $.msg("优惠码前缀只能是英文字母A-Z，限4个字母，不区分大小写");
//            	    	 return;
//            	    }
//            	}
//        	    $.ajax({
//            		url: rootPath+"/companyCouponsPatch/checkIsExist",
//            		type: "post",
//            		dataType: "json",
//            		data:{"promoCodePrefix":mark},
//            		success: function(result){
//            			if(result && result=="exist"){
//            				$.msg("优惠码前缀已存在");
//            				return;
//            			}
//            		}
//            	});
//            });
            
          
    	//删除
    	$(document).on('click.iconfont','.group .iconfont',function(){
    		var id=$(this).parent().attr("ids");
    		$(this).parent().remove();
    		$(".student-list").find(".c_choose"+id).prop("checked", false);
    		$("#choseStuNum").text($("#group").find("span").length);
    	})
    	//折扣格式验证
    	$(".print_prices").bind("keyup",function(event){
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
            }
    	}).on('blur',function(){
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
    		if($(this).val()>10||$(this).val()<0){
            	$(this).val(10);
            }
    	});
	    	$("#onLine_overCutNum").on('blur',function(){
	    	    var jian=$.MoneyToNum($(this).val());
	    		var man=$.MoneyToNum($("#onLine_overNum").val());
	    		if(jian!="" && man!="" && man>0){
	    			if(jian>man){
	    				$.msg("您输入的“减”的金额大于“满”的金额，请确认是否输入有误！");
	    				return;
	    			}
	    		}
	    	});
	    	$("#offLine_overCutNum").on('blur',function(){
	    		var jian=$.MoneyToNum($(this).val());
	    		var man=$.MoneyToNum($("#offLine_overNum").val());
	    		if(jian!="" && man!="" && man>0){
	    			if(jian>man){
	    				$.msg("您输入的“减”的金额大于“满”的金额，请确认是否输入有误！");
	    				return;
	    			}
	    		}
	    	})
        },
        search: function (page) {
            var $this = this;
            var data = {};
            data.mobile = $("#stuMobile").val();
            data.name = $("#stuName").val();
            data.identityId = $("#sfzh").val();
            data.status = $(".registStatus").val();// 注册状态
            data.registType = $(".registMethods").val();// 注册方式
            data.paymaterCount = $(".payStatus").val();// 报名状态
            data.isMember=$(".is_member:checked").val();
            data.page = page ? page : 1;
            data.username=$("#username").val();

            var tel = $("#stuMobile").val(); // 获取手机号
            if (tel != "") {
                var telReg = !!tel
                    .match(/^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
                // 如果手机号码不能通过验证
                if(isNaN(tel)){
					$.msg('请输入有效的手机号码');
					return;
				}
            }
            $.each(data, function (key, value) {
                if (!value) {
                    delete data[key];
                }
            })
            $(".student-list").find("tr:gt(0)").remove();
            $(".checkboxAll").prop("checked", false);
            $.ajax({
                    url: rootPath + "/student/queryStudentsList",
                    data: data,
                    type: 'post',
                    beforeSend: function (XMLHttpRequest) {
                        $(".loading").show();
                        $(".loading-bg").show();
                    },
                    success: function (jsonData) {
                        if (jsonData.data.length == 0) {
                            $(".student-list").append(
                                '<tr><td colspan="9">没有查找到数据</td></tr>');
                        }
                        $.each(jsonData.data,function (i, stu) {
                        	var memberName="";
                        	if(stu && stu.memberStatus && stu.memberStatus==1){
                        		memberName=stu.memberLevel?stu.memberLevel:"";
                        	}
                        	$(".student-list")
                                    .append(
                                    '<tr>'
                                    + '<td>'
                                    + '<input type="checkbox" class="signUpMany c_choose'+(stu.userId?stu.userId:0)+'" mark="'+(stu.name?stu.name:"")+'" uId="'+(stu.userId?stu.userId:0)+'" value="' + (stu.mobile?stu.mobile:(stu.username?stu.username:'')) + '">'
                                    + '</td>'
                                    + '<td>'
                                    + (stu.mobile ? stu.mobile
                                        : "")
                                    + '</td>'
                                    + '<td>'
                                    + (stu.username ? stu.username
                                    		: "")
                                    		+ '</td>'
                                    + '<td>'
                                    + (stu.name ? stu.name
                                        : "")
                                    + '</td>'
                                    + '<td>'
                                    + (stu.identityId ? stu.identityId
                                        : "")
                                    + '</td>'
                                    + '<td>'
                                    + (stu.createTime ? stu.createTime
                                        : "")
                                    + '</td>'
                                    + '<td class="haostatus">'
                                    + (stu.userId ? '已开通'
                                        : '未开通')
                                    + '</td>'
                                    + '<td class="ustatus">'
                                    + (stu.status == 1 ? '启用'
                                        : '禁用')
                                    + '</td>'
                                    + '<td class="baoming" value="' + stu.ispay + '">'
                                    + (stu.paymaterCount > 0 ? '已报名'
                                        : '未报名')
                                    + '</td>'
                                    + '<td>'+memberName+'</td>'
                                    + '</tr>');
                            });
                        //对比选中
                        $(".student-list").find(".signUpMany").each(function(){
                        	var $this=$(this);
                        	var id=$(this).attr("uid");
                        	if($("#group").find("span.u_list"+id).length>0){
                        		$this.prop("checked", true);
                        	}
                        });
                        //处理数据
                        var list_user=$("#user_list_types").val();
                        if(list_user){
                        	var str=list_user.split(",");
                        	 $(".student-list").find(".signUpMany").each(function(){
                        		 var $this=$(this);
                        		 var id=$(this).attr("uId");
                        		 var mobile=$(this).val();
                        		 var name=$(this).attr("mark");
                        		 for(var i=0;i<str.length;i++){
                             		if(id==str[i]){
                             			$this.prop("checked", true);
//                             			if($("#group").find("span.u_list"+id).length<1){
//                             				$("#group").append('<span class="u_list'+id+'" ids='+id+'>'+mobile+'-'+(name?name:"")+'<i class="iconfont">&#xe6be;</i></span>');
//                                    	}
                             		}
//                             		else{
//                             		   if($("#group").find("span.u_list"+str[i]).length<1){
//                          				  $("#group").append('<span class="u_list'+str[i]+'" ids='+str[i]+'>暂无手机号-学员<i class="iconfont">&#xe6be;</i></span>');
//                          			   }	
//                             		}
                             	 }
                        	 });
                        	 $.ajax({
                         		url: rootPath+"/student/queryByUId",
                         		type: "post",
                         		dataType: "json",
                         		data:{"userId":list_user},
                         		success: function(jsonData){
                         		   $.each(jsonData,function(i,result){
                         			  if(result){
                            			   if($("#group").find("span.u_list"+result.userId).length<1){
                            				  $("#group").append('<span class="u_list'+result.userId+'" ids='+result.userId+'>'+(result.mobile?result.mobile:"")+('-'+result.name?result.name:"")+'<i class="iconfont">&#xe6be;</i></span>');
                            			   }
                            			}
                         		   });
                         		  $("#choseStuNum").text($("#group").find("span").length);
                         		}
                         	});
                        	
                        }
                        if (jsonData.rowCount > 10) {
                            $(".pagination").pagination(jsonData.rowCount,
                                {
                                    next_text: "下一页",
                                    prev_text: "上一页",
                                    current_page: jsonData.pageNo - 1,
                                    link_to: "javascript:void(0)",
                                    num_display_entries: 8,
                                    items_per_page: jsonData.pageSize,
                                    num_edge_entries: 1,
                                    callback: function (page, jq) {
                                        var pageNo = page + 1;
                                        $this.search(pageNo);
                                    }
                                });
                        } else {
                            $(".pagination").html('');
                        }
                        $(".haostatus").each(function (i) {
                            if ($.trim($(this).text()) == "未开通") {
                                $(this).css("color", "red");
                            }
                        });
                        $(".ustatus").each(function (i) {
                            if ($.trim($(this).text()) == "禁用") {
                                $(this).css("color", "red");
                            }
                        });
                        $(".baoming").each(function (i) {
                            if ($.trim($(this).text()) == "未报名") {
                                $(this).css("color", "red");
                            }
                        });
                       
                        $(".student-list").find(".signUpMany").on('click',function(){
                        	var id=$(this).attr("uid");
                        	var name=$(this).attr("mark");
                        	if($(this).is(":checked")){
                        		$("#group").append('<span class="u_list'+id+'" ids='+id+'>'+$(this).val()+(name?'-'+name:"")+'<i class="iconfont">&#xe6be;</i></span>');
                        		$("#choseStuNum").text($("#group").find("span").length);
                        	}else{
                        		$("#group").find("span.u_list"+id).remove();
                        		$("#choseStuNum").text($("#group").find("span").length);
                        	}
                        });
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        $(".loading").hide();
                        $(".loading-bg").hide();
                    }
                });
        },
        queryCompanyMemberList:function(){
        	var mList=$("#member_list_types").val();
        	$(".member_lists_chose1").html("");
        	 $.ajax({
         		url: rootPath+"/companyMemberLevelConfig/queryMemberLists",
         		type: "post",
         		dataType: "json",
         		success: function(jsonData){
         			var html="";
         			$.each(jsonData,function(i,item){
         				if(mList && mList.length>0){
     						if(mList.indexOf(item.id)>=0){
     							html+='<span><input type="checkbox" checked="checked" name="memberListName" value="'+item.id+'"/>'+(item.name?item.name:"")+'</span>';
							}else{
								html+='<span><input type="checkbox" name="memberListName" value="'+item.id+'"/>'+(item.name?item.name:"")+'</span>';
							}
         				}else{
         					html+='<span><input type="checkbox" checked="checked" name="memberListName" value="'+item.id+'"/>'+(item.name?item.name:"")+'</span>';
         				}
         			});
         			$(".member_lists_chose1").html(html);
         		}
         	});
        }
    }
    $(document).ready(function () {
        student.init();
    })
})(jQuery)