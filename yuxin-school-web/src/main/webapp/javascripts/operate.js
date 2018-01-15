	$(function(){

	// 班号页面js
	// laydate({
	// 	elem: '#classComeTime',
	// 	format: 'YYYY/MM/DD hh:mm:ss',
	// 	min: laydate.now(), //设定最小日期为当前日期
	// 	max: '2099-06-16 23:59:59', //最大日期
	// 	istime: true,
	// 	istoday: false,
	// 	choose: function(datas){
	// 		 // end.min = datas; //开始日选好后，重置结束日的最小日期
	// 		 // end.start = datas //将结束日的初始值设定为开始日
	// 	}
	// });
	/*标签、tab切换*/	
		
	/*点击允许or禁止 班号中的允许、禁止*/	
		$(".allowIconfont").click(function(){
			if($(this).hasClass("open")){
				$("#publishStatus").val("MODULE_NO_CREATED");
				$(this).html("&#xe604;").removeClass("open").addClass("close");
				$(this).next("span").removeClass("open").addClass("close").text("已禁止");
			}else{
				$("#publishStatus").val("MODULE_NO_ON_SALE");
				$(this).html("&#xe603;").removeClass("close").addClass("open");
				$(this).next("span").removeClass("close").addClass("open").text("已允许");	
			}
		})	
	/*运营首页*/	
		/*显示校区*/
		$(".operateStyle .s-list a").click(function(){
			$(".operateSchool").slideDown(400);	
		})
		/*点击允许招生-弹出询问框*/
		$(".allowAdmissions").click(function(){
			$(".allowAdmissionsTc").showTc();
		})
		/*点击停止招生-弹出询问框*/
		$(".stopAdmissions").click(function(){
			$(".stopAdmissionsTc").showTc();		
		})
		/*点击结课 -弹出询问框*/
		$(".overAdmissions").click(function(){
			$(".overAdmissionsTc").showTc();			
		})
		/*点击已招学员-弹出已招学员列表*/
		$(".checkStudent").click(function(){
			$(".checkStudentTc").showTc();				
		})
	/*传视频*/	
		/*时间排序*/
		$(".operate_vedio_time span").click(function(){
			$(this).addClass("active").siblings("span").removeClass("active");	
		})
		/*checkbox全选*/	
		$(".allCheckbox").click(function(){
			var flag = $(this).prop("checked");
			$(".operateCheckbox").each(function(){
				$(this).prop("checked",flag);	
			})	
		})
		/*选择视频弹出框*/
		$(".addVideoBtn").click(function(){
			$(".addVideoTc").showTc();
			var docheight = -parseInt($(".addVideoTc").height());
			var divheight = parseInt(parseInt($(".check_student_hd").height()) + parseInt($(".check_student_bd").height()));
			$(".addVideoTc").height(divheight);
			$(".addVideoTc").css("top","0px");
			$(".addVideoTc").css("margin-top", "80px");
		})
		$(".addResourceBtn").click(function(){
			$(".addResourceTc").showTc();
			var docheight = -parseInt($(".addResourceTc").height());
			//var divheight = parseInt(parseInt($(".check_student_hd").height()) + parseInt($(".check_student_bd").height()));
			//$(".addResourceTc").height(divheight);
			$(".addResourceTc").css("top","0px");
			$(".addResourceTc").css("margin-top", "80px");
		})

		// 弹层
        $('.add-classes').on('click','i.close,.btn-cancel',function(){
                $('.add-classes').fadeOut(200,function(){
                    $('.add-classes-bg').fadeOut(200);
                });
            })
            .on('click','small',function(){
                var 
                    _this = $(this).find('i'),
                    status = ['&#xe609;','&#xe60a;'],
                    active = _this.hasClass('active');

                if ( !active ) {
                    _this.addClass('active').html(status[1]);
                } else {
                    _this.removeClass('active').html(status[0]);                    
                }
            });

		// 上传按钮
        $('#startbutton').on('click',function(){
        	if(!$("div.addVideoTc #itemOneId").val()){
        		$("div.addVideoTc .item_msg").html("请选择学科");
        		return false;
        	}
        	/*if(!$("div.addVideoTc #itemSecondId").val()){
        		$("div.addVideoTc .item_msg").html("请选择学科小类");
        		return false;
        	}*/
        	if(!$("div.addVideoTc #protocol").prop("checked")){
        		$("div.addVideoTc .protocol_msg").html("您尚未同意上传协议");
        		return false;
        	}
        	$("div.addVideoTc .protocol_msg").html("");
            $('#filebutton').click();
            $('div.addVideoTc').find('i.close').click();
        });
     // 上传按钮
        $('#scromupload').on('click',function(){
        	if(!$("div.addVideoTc #itemOneId").val()){
        		$("div.addVideoTc .item_msg").html("请选择学科");
        		return false;
        	}
        	// if(!$("div.addVideoTc #itemSecondId").val()){
        	// 	$("div.addVideoTc .item_msg").html("请选择学科小类");
        	// 	return false;
        	// }
        	if(!$("div.addVideoTc #protocol").prop("checked")){
        		$("div.addVideoTc .protocol_msg").html("您尚未同意上传协议");
        		return false;
        	}
        	$("div.addVideoTc .protocol_msg").html("");
            $('#filescorm').click();
            $('div.addVideoTc').find('i.close').click();
        });
        $('#qnuploadvideo').on('click',function(){
        	if(!$("div.addVideoTc #itemOneId").val()){
        		$("div.addVideoTc .item_msg").html("请选择学科");
        		return false;
        	}
        	// if(!$("div.addVideoTc #itemSecondId").val()){
        	// 	$("div.addVideoTc .item_msg").html("请选择学科小类");
        	// 	return false;
        	// }
        	if(!$("div.addVideoTc #protocol").prop("checked")){
        		$("div.addVideoTc .protocol_msg").html("您尚未同意上传协议");
        		return false;
        	}
        	$("div.addVideoTc .protocol_msg").html("");
            $('#quVideoFiles').click();
            $('div.addVideoTc').find('i.close').click();
        });

        $('#blvsloadvideo').on('click',function(){
        	var itemOneId = $("div.addVideoTc #itemOneId").val();
        	var itemTwoId = $("div.addVideoTc #itemSecondId").val();
        	var tag = $("div.addVideoTc #tag").val();
        	if(!itemOneId){
        		$("div.addVideoTc .item_msg").html("请选择学科");
        		return false;
        	}
        	// if(!itemTwoId){
        	// 	$("div.addVideoTc .item_msg").html("请选择学科小类");
        	// 	return false;
        	// }
        	if(!$("div.addVideoTc #protocol").prop("checked")){
        		$("div.addVideoTc .protocol_msg").html("您尚未同意上传协议");
        		return false;
        	}
        	$("div.addVideoTc .protocol_msg").html("");
        	var states = itemOneId + "::" + itemTwoId + "::" + encodeURI(tag)
        		+"::"+$("#companyId").val()+"::"+$("#schoolId").val()+"::"+$("#uid").val();
        	upload.update({extra:JSON.stringify({state:states})});
            $('#BLVSFiles').click();
            $('div.addVideoTc').find('i.close').click();
        });
/*
        $('#resourceSubmit').on('click',function(){
        	if(!$("div.addResourceTc #itemOneId").val()){
        		$("div.addResourceTc .item_msg").html("请选择学科");
        		return false;
        	}
        	if(!$("div.addResourceTc #itemSecondId").val()){
        		$("div.addResourceTc .item_msg").html("请选择学科小类");
        		return false;
        	}
        	if(!$("div.addResourceTc #protocol").prop("checked")){
        		$("div.addResourceTc .protocol_msg").html("您尚未同意上传协议");
        		return false;
        	}
        	$("div.addResourceTc .protocol_msg").html("");
            $('#resourceUploadInput').click();
            $('div.addResourceTc i.close').click();
        });*/
	});