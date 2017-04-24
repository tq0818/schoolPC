function alertMsg(msg){
        $('#alert-msg').find(".title-prompt").html(msg);
        $('.layer-tips-bg').css('display', 'block');
        $('#alert-msg').css('display', 'block');
    }
    $(function(){
        $('.couponCreate').eq(0).click(function(){
            $('.layer-tips-bg').show();
            $('#L-allowAdmissionsTc').css('display','block');
            $('#L-allowAdmissionsTc').find(".optype").text("添加");
            $('#L-allowAdmissionsTc').find("input,textarea").val("");
            $('#L-allowAdmissionsTc').find("textarea").trigger("change");
            $('#L-allowAdmissionsTc').find(".left-m").empty();
            $("#L-edior").attr("type",1);
        });
        $('.close').click(function(){
            $(this).parents('.L-allowAdmissionsTc').css('display','none');
            $('.layer-tips-bg').css('display','none');
        });
        $("textarea[name='des']").bind("change keyup",function(){
            var _this=$(this);
            var len=_this.val().length;
            if(len>200){
                _this.val(_this.val().substring(0,200));
                len=200;
            }
            _this.next(".number").text(len+"/200");
        })
        $("input[name='userName']").bind("change keyup",function(){
            var _this=$(this);
            var len=_this.val().length;
            $('#name_tip').hide();
        })
        $('.layer-tips-btns .btn').click(function(){
            var _this=$(this);
            if(_this.is("#L-edior")){
                var tar=$("#L-allowAdmissionsTc");
                var data={};
                data.userName= $.trim(tar.find(":text[name='userName']").val());
                data.userPic= $.trim(tar.find(":hidden[name='userPic']").val());
                data.userPhoto= $.trim(tar.find(":hidden[name='userPhoto']").val());
                data.sortNum= $.trim(tar.find(":text[name='sortNum']").val());
                data.des= $.trim(tar.find("textarea[name='des']").val());
                if(!/.{1,10}/.test(data.userName)){
//                    alertMsg("请输入有效的学员内容!(10字以内)");
                	$.msg('请输入有效的学员内容!(10字以内)');
                    return;
                }
                if(data.userName.length > 10){
//                	alertMsg("学员名称必须在 10字以内)");
                	$.msg('学员名称必须在10个字以内');
                    return;
                }
                if(!/^\d{1,6}$/.test(data.sortNum)){
                	$.msg("请输入有效的学员排序!(6位以内正整数)");
                    return;
                }
                if(!/.+/.test(data.userPic)){
                	$.msg("请上传学员图像");
                    return;
                }
                if(!/^.{1,200}$/.test(data.userPic)){
                	$.msg("请输入有效的学员感想!(200字符以内)");
                    return;
                }
                var url=rootPath;
                var optype="";
                if($("#L-edior").attr("type")=="1"){
                    url+="/studentStar/saveStar";
                    optype="添加";
                }else{
                    url+="/studentStar/updateStar";
                    data.id=$("#L-edior").attr("data-id");
                    optype="编辑";
                }
                $.ajax({
                    url: url,
                    async: true,
                    data:data,
                    dataType: 'json',
                    type: "POST",success: function (data) {
                       if(data.status="success"){
                    	   $.msg(optype+"学员心声成功");
                           loadPage(1,8);
                       }else{
                    	   $.msg(optype+"学员心声失败<br/>"+data.msg);
                           return;
                       }
                    }
                })
            }else if(_this.is("#L-delete")){
                var data={};
                data.id=$("#L-delete").attr("data-id");
                $.ajax({
                    url: rootPath + "/studentStar/delStar",
                    async: true,
                    data:data,
                    dataType: 'json',
                    type: "POST",success: function (result) {
                        if(result.status="success"){
                        	$.msg("删除成功");
                            loadPage(1,8);
                        }else{
                        	$.msg("删除失败:"+result.msg);
                            return;
                        }
                    }
                })
            }
            $(this).parents('.L-allowAdmissionsTc').css('display','none');
            $('.layer-tips-bg').css('display','none');
        });
        $("#studendList").on("click",".center .right", function() {
            var _this=$(this);
            var id =_this.parents(".study").attr("data-id");
            var data={};
            data.id=id;
            $.ajax({
                url: rootPath + "/studentStar/findById",
                async: true,
                data:data,
                dataType: 'json',
                type: "POST",success: function (result) {
                    if(result.status="success"&&result.obj!=null){
                        $('.layer-tips-bg').css('display', 'block');
                        var tar=$("#L-allowAdmissionsTc");
                        tar.css('display', 'block');
                        tar.find(".optype").text("编辑");
                        tar.find("input,textarea").val("");
                        tar.find(".left-m").empty();
                        $("#L-edior").attr("type",2);
                        $("#L-edior").attr("data-id",id);
                        var data=result.obj;
                        tar.find(":text[name='userName']").val(data.userName);
                        tar.find(":hidden[name='userPic']").val(data.userPic);
                        tar.find(":text[name='sortNum']").val(data.sortNum);
                        tar.find("textarea[name='des']").val(data.des);
                        if(typeof data.userPic!='undefined'&&data.userPic!=null&&data.userPic!=""){
                            tar.find(".userPic").html('<img style="width: 120px;height: 120px;" src="'+result.imgHost+data.userPic+'"/>');
                        }
                        if(typeof data.userPhoto!='undefined'&&data.userPhoto!=null&&data.userPhoto!=""){
                            tar.find(".userPhoto").html('<img style="width: 120px;height: 120px;" src="'+result.imgHost+data.userPhoto+'"/>');
                        }
                        tar.find("textarea").trigger("change");
                    }else{
                    	$.msg("无效的学员心声");
                        return;
                    }
                }
            })
        })
        $("#studendList").on("click",".center .left", function() {
            var _this=$(this);
            var id =_this.closest(".study").attr("data-id");
            var datainfo = {id:id};
            $.confirm({
				title:'删除',
				text:'确定要删除该学员心声吗？',
				saveOk:true,
				cancelOk:true,
				save:'确定',
				callback:function(flag){
					if(flag){
						if($(this).hasClass('disable'))
		            		return;
		            	$(this).addClass('disable')
	                	$.ajax({
		                    url: rootPath + "/studentStar/delStar",
		                    async: true,
		                    data:datainfo,
		                    dataType: 'json',
		                    type: "POST",success: function (result) {
		                        if(result.status="success"){
		                        	$.msg("删除成功");
		                            loadPage(1,8);
		                        }else{
		                        	$.msg("删除失败:"+result.msg);
		                            return;
		                        }
		                    }
		                })
					}
				}
			});
            /*$("#L-delete").attr("data-id",id);
            $("#L-delete").closest('.L-allowAdmissionsTc').css('display', 'block');
            $('.layer-tips-bg').css('display', 'block');*/
        })
        loadPage(1,8);
    });
    function uploadImg(){
        $.ajaxFileUpload({
            url: rootPath + "/studentStar/UploadImg",
            secureuri: false, // 安全协议
            async: false,
            fileElementId: 'imgData',
            dataType: 'json',
            type: "POST",
            success: function (data) {
                $('#L-allowAdmissionsTc').find(".userPic").html('<img style="width: 120px;height: 120px;" src="'+data.url+'"/>');
                $(":hidden[name='userPic']").val(data.picPath);
            },
            error: function (arg1, arg2, arg3) {
                //console.log(arg1);
            }
        });
    }
    function uploadImg1(){
        $.ajaxFileUpload({
            url: rootPath + "/studentStar/UploadImg",
            secureuri: false, // 安全协议
            async: false,
            fileElementId: 'imgData1',
            dataType: 'json',
            type: "POST",
            success: function (data) {
                $('#L-allowAdmissionsTc').find(".userPhoto").html('<img style="width: 120px;height: 120px;" src="'+data.url+'"/>');
                $(":hidden[name='userPhoto']").val(data.picPath);
            },
            error: function (arg1, arg2, arg3) {
                //console.log(arg1);
            }
        });
    }

    function loadPage(page,pageSize){
        var data={};
        data.page=page;
        data.pageSize=pageSize;
        $.ajax({
            url: rootPath+"/studentStar/findByPage",
            async: true,
            data:data,
            type: "POST",
            success: function (html) {
                $("#studendList").html(html);
            },
	        complete: function(XMLHttpRequest, textStatus) {
	            $.footerPosition({ cur: '.footer', pre: '#studendList' });
	        }
        })
    }