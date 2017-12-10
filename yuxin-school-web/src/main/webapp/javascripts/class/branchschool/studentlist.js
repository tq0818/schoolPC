(function ($) {

    var student = {
        init: function () {
            var $this = this;
            $selectMenu("course_class_type");
            // 初始化日期框
            $(".date-picker").datetimepicker({
                format: "yyyy-mm-dd",
                minView: 2,
                autoclose: true,
                language: "zh-CN"
            });
            // 初始化数据
            $this.search();

            // 收索
            $(".searchContents").on('click', function () {
                $this.search();
            });
            // 导入用户
            $(".importexcle").on('click', function () {
            	window.location.href=rootPath + "/branchSchool/importStudents/"+$("#classTypeId").val()+"/"+$("#lableType").val();
            });
            // 导出用户
            $(".exportexcle").on(
                'click',
                function () {
                    if ($("#tableList").find("tr").eq(1).find("td").length <= 1) {
                        $.msg("没有数据可以导出");
                    } else {
                        $("#searchForm").attr("action",
                            rootPath + "/student/exportExcle")
                            .submit();
                    }

                });
            $(".exportStudyPercent").on('click',function(){
				if ($("#tableList").find("tr").eq(1).find("td").length <= 1) {
									$.msg("没有数据可以导出");
								} else {
									$("#searchForm").attr("action",
											rootPath + "/student_detail/exportStdent")
											.submit();
								}
			});
        },
        search: function (page) {
            var $this = this;
            var data = {};
            data.classTypeId = $("#classTypeId").val();
            data.mobile = $("#stuMobile").val();
            data.username=$("#stuusername").val();
            data.name = $("#stuName").val();
            data.identityId = $("#sfzh").val();
            data.startTime = $(".from").val();
            data.endTime = $(".to").val();
            data.status = $("#registStatus").val();// 注册状态
            if($('.heading').data('protocolconfig')) data.protocolConfig = 1;//开启课程协议
			if($('#protocolStatus').val()) data.protocolStatus = $('#protocolStatus').val();//学员是否签署课程协议
            data.page = page ? page : 1;

            var tel = $("#stuMobile").val(); // 获取手机号
            if (tel != "") {
                var telReg = !!tel
                    .match(/^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
                // 如果手机号码不能通过验证
                if(isNaN(tel)){
					$.msg('请输入有效的手机号码');
					return;
				}
//                if (telReg == false) {
//                    $.msg('请输入有效的手机号码');
//                    return;
//                }
            }


            if ($(".to").val() != "") {
                if ($(".to").val() < $(".from").val()) {
                    $.msg("时间范围不正确");
                    return;
                }

            }
            $.each(data, function (key, value) {
                if (!value) {
                    delete data[key];
                }
            })
            $(".user-list").find("table").find("tr:gt(0)").remove();
            $(".checkboxAll").prop("checked", false);
            $.ajax({
                    url: rootPath + "/classStu/queryStudentsList",
                    data: data,
                    type: 'post',
                    beforeSend: function (XMLHttpRequest) {
                        $(".loading").show();
                        $(".loading-bg").show();
                    },
                    success: function (jsonData) {
                       
                        if (jsonData.data.length == 0) {
                            $(".user-list")
                                .find("table")
                                .append(
                                '<tr><td colspan="8">没有查找到数据</td></tr>');
                        }
                        $.each(jsonData.data,function (i, stu) {
                                $(".user-list")
                                    .find("table")
                                    .append(
                                    '<tr data-buy="'+(stu.paymaterCount>0)+'">'
//                                    + '<td>'
//                                    + '<input type="checkbox" class="signUpMany" value="' + stu.mobile + '">'
//                                    + '</td>'
                                    + '<td>'
                                    + (stu.name ? stu.name
                                        : "")
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
                                    + (stu.email ? stu.email
                                        : "")
                                    + '</td>'
                                    + '<td>'
                                    + (stu.signUpTime ? stu.signUpTime
                                        : "")
                                    + '</td>'
                                    + '<td>'
                                    + (stu.lastLoginTime ? stu.lastLoginTime
                                        : "")
                                    + '</td>'
                                    + '<td class="ustatus">'
                                    + (stu.status == 1 ? '启用'
                                        : '禁用')
                                    + '</td>'
                                    + '<td class="slink">'
                                    + '<a stuId="' + stu.id + '" href="'+rootPath+'/branchSchool/classLeanRecord/'+stu.id+'/'+$("#classTypeId").val()+'/'+$("#lableType").val()+'">学习记录</a>|'
//                                    + '<a class="showSignUp" mobile="' + stu.mobile + '" href="javascript:void(0);">报名</a>|'
//                                    + '<a class="studentDetail" mobile="' + stu.mobile + '" href="javascript:void(0);">详情</a>|'
                                    + '<a class="more" href="javascript:void(0);">更多</a>'
                                    + '<ul class="none">'
                                    + (stu.paymaterCount > 0 ? '<li><a class="toTransaction" mobile="' + (stu.mobile?stu.mobile:"") + '" uName="'+(stu.username?stu.username:"")+'" href="javascript:void(0);">异动</a></li>' : '')
                                    + (stu.paymaterCount > 0 ? (stu.ispay == "1" ? '<li><a class="toMessage" mobile="' + (stu.mobile?stu.mobile:"") + '" uName="'+(stu.username?stu.username:"")+'" href="javascript:void(0);">补费</a></li>' : '' ) : '')
                                    + (stu.paymaterCount > 0 ?
                                        (stu.agentFlag == "1" ?
                                            (stu.isAgent == "1" ?
                                            '<li><a class="showStuMaterial" mobile="' + (stu.mobile?stu.mobile:"") + '" uName="'+(stu.username?stu.username:"")+'" href="javascript:void(0);">报考材料</a></li>'
                                                : '')
                                            : '')
                                        : '')

                                    + (($("#isDelete").val()==1) ? '<li><a class="delteStuOrder" stuId="' + stu.id + '" href="javascript:void(0);">移出此课程</a></li>' : "")
                                    + (stu.protocolId ? '<li><a class="protocolDetail" data-protocolid="' + stu.protocolId + '" href="/courseProtocolConfig/toProtocolDetail?protocolId='+ stu.protocolId +'&classTypeId='+$("#classTypeId").val()+'&stuId='+stu.id+'"  target="blank">课程协议</a></li>':"")
                                    + '</ul></td>'
                                    + '</tr>');
                            });
                        $("#rowCount").remove();
                        $("#pageNo").remove();
                        $.ajax({
                        	url: rootPath+"/student/queryBuyNum",
                        	dataType: "text",
                        	success:function(data){
                        		
                        		$(".user-list").after('<input type="hidden" id="rowCount" value="'+(data?data:0)+'"/>');
                        	}
                        })
                        $(".user-list").after('<input type="hidden" id="pageNo" value="'+jsonData.pageNo+'"/>');
                        
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
                        $(".ustatus").each(function (i) {
                            if ($.trim($(this).text()) == "禁用") {
                                $(this).css("color", "red");
                            }
                        });
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        $(".loading").hide();
                        $(".loading-bg").hide();
                    }
                });
            $("#maxCount").remove();
            // 查询允许本机构报名的最大数量
            $.ajax({
            	url: rootPath+"/companyMemberService/studentNum",
            	dataType: "json",
            	success: function(num){
            		if(num){
            			$(".user-list").after('<input type="hidden" id="maxCount" value="'+num+'"/>');
            		}else{
            			$(".user-list").after('<input type="hidden" id="maxCount" value="0"/>');
            		}
            	}
            })
        },
        checkMaxSignUpNum: function(ele){
        	var flag=true;
        	$.ajax({
        		type: "post",
        		url:  rootPath+"/company/queryCompany",
        		async: false,
        		success: function(result){
        			if(result){
        				var cnt=0;
        	        	$("#tableList").find("tr").find("input:checkbox:checked").each(function(){
        	        		if($(this).parents("tr").attr("data-buy")!="true"){
        	        			cnt++;
        	        		}
        	        	})
        	        	if(ele && ele.parents("tr").attr("data-buy")!="true"){
        	        		cnt=1;
        	        	}
        	        	if((parseInt($("#rowCount").val())+cnt)>$("#maxCount").val()){
        	        		$.msg("报名人数已超过最大服务数，请升级版本或续费");
        	        		flag=false;
        	        	}
        			}
        		}
        	});
        	return flag;
        }
    }

    var rules = {
        errorElement: 'span',
        errorClass: 'tips',
        focusInvalid: false,
        ignore: "",
        rules: {
            nPassword: {
                required: true,
                minlength: 6
            },
            rPassword: {
                required: true,
                equalTo: "#nPassword"
            },
            sName: {
                required: true
            },
            sAge: {

                maxlength: 2,
                digits: true
            },
            sBirth: {
                date: true
            },
            sEducation: {},
            sIdentity: {},
            sIdentityNum: {
                minlength: 15,
                remote: {
                    url: rootPath + "/student/checkIdentityExist",
                    type: "post",
                    dataType: "json",
                    data: {
                        identityType: function () {
                            return $("#sIdentity option:selected").val();
                        },
                        identityNum: function () {
                            return $("#sIdentityNum").val();
                        }
                    }
                }
            },
            sMobile: {
                required: true,
                minlength: 9,
                maxlength: 11,
                isMobile: true,
                remote: {
                    url: rootPath + "/student/checkMobileExist",
                    type: "post",
                    dataType: "json",
                    data: {
                        mobile: function () {
                            return $("#sMobile").val();
                        }
                    }
                }
            },
            sEmergencyPhone: {
                minlength: 11,
                maxlength: 11,
                digits: true
            },
            sEmail: {
                email: true
            }
        },
        messages: {
            sName: {
                required: "姓名不能为空"
            },
            sBirth: {
                date: "请输入正确时间"
            },
            sIdentityNum: {
                minlength: "不是有效的身份证",
                required: "证件号码不能为空",
                remote: "该身份证号已经被使用"
            },
            sMobile: {
                required: "手机号不能为空",
                maxlength: "请输入正确手机号",
                minlength: "请输入正确手机号",
                isMobile: "请输入正确手机号",
                remote: "手机号已经存在",
            },
            nPassword: {
                required: "密码不能为空，且最少为六位数字或英文字母",
                minlength: "密码最少为6位数字或英文字母"
            },
            rPassword: {
                required: "请重复输入密码",
                equalTo: "2次密码不一致，请修改"
            }

        },
        success: function (label) {

        },
        submitHandler: function (form) {
            form.submit();
        },
        onkeyup: false
    }
    var rule2 = {
        errorElement: 'span',
        errorClass: 'tips',
        focusInvalid: false,
        ignore: "",
        rules: {
            uName: {
                required: true
            },
            uAge: {
                maxlength: 2,
                digits: true
            },
            uBirth: {
                date: true
            },
            uMobile: {
                required: true,
                minlength: 11,
                maxlength: 11,
                isMobile: true,

            },
            uEmergencyPhone: {
                minlength: 11,
                maxlength: 11,
                digits: true
            },
            uEmail: {
                email: true
            }
        },
        messages: {
            uName: {
                required: "姓名不能为空"
            },
            uBirth: {
                date: "请输入正确时间"
            },
            uIdentityNum: {
                minlength: "不是有效的身份证",
                required: "证件号码不能为空",
                remote: "该身份证号已经被使用"
            },
            uMobile: {
                required: "手机号不能为空",
                maxlength: "请输入正确手机号",
                minlength: "请输入正确手机号",
                isMobile: "请输入正确手机号",
                remote: "手机号已经存在",
            }
        },
        success: function (label) {

        },
        submitHandler: function (form) {
            form.submit();
        },
        onkeyup: false

    }
    var Validator;
    var Validator2;
    var Validator3;
    var changePw = {
        init: function () {
            Validator3 = $("#changePw").validate(rules);
            $(".user-list").on("click", ".changePwd", function () {
                var mobile = $(this).attr("userId");
                changePw.clearData();
                $(".changePw").popup("show");
                $("#userId").val(mobile);
            });

        },
        submit: function () {
            if ($("#changePw").valid()) {
                var id = $("#userId").val();
                var password = $("#nPassword").val();
                $.ajax({
                    type: 'post',
                    url: rootPath + "/student/changePassword",
                    data: {
                        "password": password,
                        "id": id
                    },
                    dataType: 'json',
                    success: function (jsonData) {
                        if (jsonData == "success") {
                            $.msg("修改正确");
                        }
                        else {
                            $.msg("异常，请重新修改");
                        }
                        $(".changePw").popup("hide");
                        var pageNo=$("#pageNo").val();
                        student.search(pageNo);
                        changePw.clearData();
                    }

                });
            }
        },
        clearData: function () {
            $("#changePw")[0].reset();
            Validator3.resetForm();
        }
    };
    var Link = {
        init: function () {
            $(".user-list").on("click", ".toTransaction", function () {
            	var umbile=$("#mobileSet").val();
            	var uName=$("#userNameSet").val();
            	var mobile = $(this).attr("mobile");
                if(umbile!=1){
                 	if(uName==1){
                 		if($(this).attr("uname") && $(this).attr("uname")!=""){
                 			mobile=$(this).attr("uname");
                 		}else{
                 			mobile=$(this).attr("mobile");
                 		}
             		}
             	}
                if(!mobile || mobile==""){
                	mobile=$(this).attr("uname");
                }
                    var form = document.createElement("form");
                    document.body.appendChild(form);
                    form.action = rootPath + '/classStu/toTransaction';
                    form.method = "post";
                    form.target = "_blank";
                    var fileName = document.createElement("input");
                    fileName.type = 'hidden';
                    fileName.value = mobile;
                    fileName.name = 'mobile';
                    form.appendChild(fileName);
                    form.submit();
                })
                .on("click", ".toMessage", function () {
                	var umbile=$("#mobileSet").val();
                	var uName=$("#userNameSet").val();
                	var mobile = $(this).attr("mobile");
                    if(umbile!=1){
                     	if(uName==1){
                     		if($(this).attr("uname") && $(this).attr("uname")!=""){
                     			mobile=$(this).attr("uname");
                     		}else{
                     			mobile=$(this).attr("mobile");
                     		}
                 		}
                 	}
                    if(!mobile || mobile==""){
                    	mobile=$(this).attr("uname");
                    }
                    var form = document.createElement("form");
                    document.body.appendChild(form);
                    form.action = rootPath + '/classStu/toMessage';
                    form.method = "post";
                    form.target = "_blank";
                    var fileName = document.createElement("input");
                    fileName.type = 'hidden';
                    fileName.value = mobile;
                    fileName.name = 'mobile';
                    form.appendChild(fileName);
                    form.submit();
                })
                .on("click", ".showStuMaterial", function () {
                	var umbile=$("#mobileSet").val();
                	var uName=$("#userNameSet").val();
                	var mobile = $(this).attr("mobile");
                    if(umbile!=1){
                     	if(uName==1){
                     		if($(this).attr("uname") && $(this).attr("uname")!=""){
                     			mobile=$(this).attr("uname");
                     		}else{
                     			mobile=$(this).attr("mobile");
                     		}
                 		}
                 	}
                    if(!mobile || mobile==""){
                    	mobile=$(this).attr("uname");
                    }
                    var form = document.createElement("form");
                    document.body.appendChild(form);
                    form.action = rootPath + '/classStu/stuMaterial2';
                    form.method = "post";
                    form.target = "_blank";
                    var fileName = document.createElement("input");
                    fileName.type = 'hidden';
                    fileName.value = mobile;
                    fileName.name = 'mobile';
                    form.appendChild(fileName);
                    form.submit();
                })
                .on("click", ".studentDetail", function () {
                	var umbile=$("#mobileSet").val();
                	var uName=$("#userNameSet").val();
                	var mobile = $(this).attr("mobile");
                    if(umbile!=1){
                     	if(uName==1){
                     		if($(this).attr("uname") && $(this).attr("uname")!=""){
                     			mobile=$(this).attr("uname");
                     		}else{
                     			mobile=$(this).attr("mobile");
                     		}
                 		}
                 	}
                    if(!mobile || mobile==""){
                    	mobile=$(this).attr("uname");
                    }
                    var form = document.createElement("form");
                    document.body.appendChild(form);
                    form.action = rootPath + '/student/studentDetailMessage';
                    form.method = "post";
                    form.target = "_blank";
                    var fileName = document.createElement("input");
                    fileName.type = 'hidden';
                    fileName.value = mobile;
                    fileName.name = 'mobile';
                    form.appendChild(fileName);
                    form.submit();

                })
                .on("click", ".updateStatus", function () {
                    var userId = $(this).attr("userId");
                    var status = $(this).attr("status");
                    $.ajax({
                        type: 'post',
                        url: rootPath + "/student/updateStatus",
                        data: {
                            "userId": userId,
                            "status": status,
                        },
                        dataType: 'json',
                        success: function (jsonData) {
                            if (jsonData == "success") {
                                $.msg("修改成功");
                                var pageNo=$("#pageNo").val();
                                student.search(pageNo);
                            }
                            else {
                                $.msg("发生错误，请重新修改");
                            }
                        }
                    });
                })
                .on('click','.delteStuOrder',function(){
                	var $this=$(this);
                	var stuId=$(this).attr("stuId");
                	$.confirm("您确认要移出此课程吗",function(b){

                		if(b){
                        	var classTypeId=$("#classTypeId").val();
                        	 $.ajax({
                                 type: 'post',
                                 url: rootPath + "/classStu/deleteStuPaymaster",
                                 data: {id: stuId,classTypeId: classTypeId},
                                 dataType: 'json',
                                 success: function (jsonData) {
                                     if (jsonData == "success") {
                                    	 $.msg("该学员已移出此课程",function(){
                                    		 $.ajax({
                                                 type: 'post',
                                                 url: rootPath + "/classStu/queryStupaymater",
                                                 data: {stuId: stuId},
                                                 dataType: 'json',
                                                 success: function (jsonData) {
                                                	
                                                 }
                                        	 });
                                    	 });
                                    	 $this.parents("tr").remove();
                                     }else {
                                         $.msg("操作成功");
                                     }
                                 }
                             });
                		}
                	});
                })
                .on("mouseenter.link", ".slink .more", function () {
                    $(this).nextAll("ul").show();
                })
                .on("mouseleave.link", ".slink", function () {
                    $(this).find("ul").hide();
                })
        }
    }
    //添加学员form
    var Form = {
        init: function () {
            $.validator.addMethod("isMobile", function (value, element, params) {
                if (/^09\d{8}|1[3,4,5,7,8]\d{9}$/.test(value)) {
                    return true;
                } else {
                    return false;
                }
            });
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
                rangelength: jQuery.validator
                    .format("请输入 一个长度介于 {0} 和 {1} 之间的字符串"),
                range: jQuery.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),
                max: jQuery.validator.format("请输入一个最大为{0} 的值"),
                min: jQuery.validator.format("请输入一个最小为{0} 的值"),
                isMobile: "不是有效的手机号"
            });
            //证件类型切换事件  如果为身份证，做完整校验，如果是不确定证件，不做校验，其他的不做号码校验
            $("#addStudentForm").find("#sIdentity").on("change.txt.page", function (e) {
                if ($("#sIdentity").find("option:selected").val() == "ID_IDCARD") {
                    $("#addStudentForm").find("#sIdentityNum").rules("add", {
                        minlength: 15,
                        remote: {
                            url: rootPath + "/student/checkIdentityExist",
                            type: "post",
                            dataType: "json",
                            data: {
                                identityType: function () {
                                    return $("#sIdentity option:selected").val();
                                },
                                identityNum: function () {
                                    return $("#sIdentityNum").val();
                                }
                            }
                        }
                    });
                    //自动计算出生日期
                    $("#addStudentForm").find("#sIdentityNum").off("change");
                    $("#addStudentForm").find("#sIdentityNum").on("change", function () {
                        //获取输入身份证号码
                        var UUserCard = $(this).val().length == 18 ? $(this).val() : $(this).val().substring(0, 6) + "19";
                        var re = new RegExp("/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i");
                        if (!UUserCard || re.test(UUserCard)) {
                            return;
                        }
                        //获取出生日期
                        var birthday = UUserCard.substring(6, 10) + "-" + UUserCard.substring(10, 12) + "-" + UUserCard.substring(12, 14);
                        console.log(birthday);
                        $("#addStudentForm").find("#sBirth").val(birthday);
                        $('#insertman').prop("checked", false);
                        $('#insertwoman').prop("checked", false);
                        //获取性别
                        if (parseInt(UUserCard.substr(16, 1)) % 2 == 1) {
                            $('#insertman').prop("checked", true);
                        } else {
                            $('#insertwoman').prop("checked", true);
                        }
                        //获取年龄
                        var myDate = new Date();
                        var month = myDate.getMonth() + 1;
                        var day = myDate.getDate();
                        var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
                        if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
                            age++;
                        }
                        $("#addStudentForm").find("#sAge").val(age);
                    });
                } else if ($(this).find("option:selected").val() == "ID_UNCONFM_ID") {
                    $("#addStudentForm").find("#sIdentityNum").rules("remove");
                    $("#addStudentForm").find("#sIdentityNum").off("change");
                } else {
                    $("#addStudentForm").find("#sIdentityNum").rules("remove", "minlength remote");
                    $("#addStudentForm").find("#sIdentityNum").rules("add", "required");
                    $("#addStudentForm").find("#sIdentityNum").off("change");
                }
            });
            // 打开添加学生
            $(".addStudent").on('click', function () {
            	if(!student.checkMaxSignUpNum($(this))){
            		return false;
            	}
                var classTypeId = $("#classTypeId").val();
                var lable=$("#lableType").val();
                var form = document.createElement("form");
                document.body.appendChild(form);
                form.action = rootPath + '/branchSchool/showSignUp';
                form.method = "post";
                form.target = "_self";
                var fileName = document.createElement("input");
                fileName.type = 'hidden';
                fileName.value = classTypeId;
                fileName.name = 'classTypeId';
                var fileName1 = document.createElement("input");
                fileName1.type = 'hidden';
                fileName1.value = lable;
                fileName1.name = 'lable';
                form.appendChild(fileName);
                form.appendChild(fileName1);
                form.submit();
            });
            Validator = $("#addStudentForm").validate(rules);
        },
        addTeacher: function () {
            if ($("#addStudentForm").valid()) {
                var sName, sSex, sBirth, sAge, sRegist, sEducation, sIdentity, sIdentityNum, sMobile, sTel, sOfficeTel, sEmergencyContact, sEmergencyPhone, sEmail, sQQ, sWebChat, sUserFront;
                sName = $("#sName").val();
                sSex = $('input:radio[name="sSex"]:checked').val();
                sBirth = $("#sBirth").val();
                sAge = $("#sAge").val();
                sRegist = $("#sRegist").val();
                sEducation = $("#sEducation option:selected").val();
                sIdentity = $("#sIdentity option:selected").val();
                sIdentityNum = $("#sIdentityNum").val();
                sMobile = $("#sMobile").val();
                sTel = $("#sTel").val();
                sOfficeTel = $("#sOfficeTel").val();
                sEmergencyContact = $("#sEmergencyContact").val();
                sEmergencyPhone = $("#sEmergencyPhone").val();
                sEmail = $("#sEmail").val();
                sQQ = $("#sQQ").val();
                sWebChat = $("#sWebChat").val();
                sUserFront = $('input:radio[name="sUserFront"]:checked').val();
                $.ajax({
                    type: 'post',
                    url: rootPath + "/student/insert",
                    data: {
                        "name": sName,
                        "sex": sSex,
                        "identityTypeCode": sIdentity,
                        "identityId": sIdentityNum,
                        "age": sAge,
                        "birthday": sBirth,
                        "educationCode": sEducation,
                        "hkAddress": sRegist,
                        "mobile": sMobile,
                        "email": sEmail,
                        "qq": sQQ,
                        "homePhone": sTel,
                        "officePhone": sOfficeTel,
                        "weixinId": sWebChat,
                        "emergencyContact": sEmergencyContact,
                        "emergencyPhone": sEmergencyPhone,
                        "isUserFront": sUserFront
                    },
                    dataType: 'json',
                    success: function (jsonData) {
//						$("#teacherList").append(
//								'<option value="' + jsonData.id + '">'
//										+ jsonData.name + '</option>');
                        if (jsonData == "success") {
                            $.msg("添加成功");
                            student.search();
                        }
                        else {
                            $.msg("格式错误，请重新添加");
                        }
                        $(".addStudentPopup").popup("hide");
                        Form.clearData();
                    }
                });
            } else {
                console.log("校验不通过");
            }
        },
        clearData: function () {
            $("#addStudentForm")[0].reset();
            Validator.resetForm();
            $("#insertwoman").prop("checked", false);
            $("#insertman").prop("checked", false);
        }
    }
    //更新学员信息
    var update = {
        init: function () {
            Validator2 = $("#updateStudentForm").validate(rule2);
            //证件类型切换事件  如果为身份证，做完整校验，如果是不确定证件，不做校验，其他的不做号码校验
            $("#updateStudentForm").find("#uIdentity").on("change.txt.page", function (e) {
                $("#uIdentityNum").prop('disabled', false);
            });
            //自动计算出生日期
            $("#updateStudentForm").find("#uIdentityNum").off("change");
            $("#updateStudentForm").find("#uIdentityNum").on("change", function () {
                if ($("#uIdentity").find("option:selected").val() == "ID_IDCARD") {
                    $("#updateStudentForm").find("#uIdentityNum").rules("add", {
                        minlength: 15,
                        remote: {
                            url: rootPath + "/student/checkUpdateIdentityExist",
                            type: "post",
                            dataType: "json",
                            data: {
                                identityType: function () {
                                    return $("#uIdentity option:selected").val();
                                },
                                identityNum: function () {
                                    return $("#uIdentityNum").val();
                                },
                                id: function () {
                                    return $("#uId").val();
                                }
                            }
                        }
                    });

                    //获取输入身份证号码
                    var UUserCard = $(this).val().length == 18 ? $(this).val() : $(this).val().substring(0, 6) + "19";
                    var re = new RegExp("/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i");
                    if (!UUserCard || re.test(UUserCard)) {
                        return;
                    }
                    //获取出生日期
                    var birthday = UUserCard.substring(6, 10) + "-" + UUserCard.substring(10, 12) + "-" + UUserCard.substring(12, 14);
                    console.log(birthday);
                    $("#updateStudentForm").find("#uBirth").val(birthday);
                    $('#updateman').prop("checked", false);
                    $('#updatewoman').prop("checked", false);
                    //获取性别
                    if (parseInt(UUserCard.substr(16, 1)) % 2 == 1) {
                        $('#updateman').prop("checked", true);
                    } else {
                        $('#updatewoman').prop("checked", true);
                    }
                    //获取年龄
                    var myDate = new Date();
                    var month = myDate.getMonth() + 1;
                    var day = myDate.getDate();
                    var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
                    if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
                        age++;
                    }
                    $("#updateStudentForm").find("#uAge").val(age);
                } else if ($(this).find("option:selected").val() == "ID_UNCONFM_ID") {
                    $("#updateStudentForm").find("#uIdentityNum").rules("remove");
                    $("#updateStudentForm").find("#uIdentityNum").off("change");
                } else {
                    $("#updateStudentForm").find("#uIdentityNum").rules("remove", "minlength remote");
                    $("#updateStudentForm").find("#uIdentityNum").off("change");
                }
            });

        },
        queryStudent: function () {
            $(".user-list").on("click", ".updateStudentMsg", function () {
                update.clearData();
                var id = $(this).attr("stuId");
                $.ajax({
                    type: 'post',
                    url: rootPath + "/student/findById",
                    data: {
                        "id": id
                    },
                    dataType: 'json',
                    success: function (jsonData) {
                        $("#uName").val(jsonData.name);
                        var sex = jsonData.sex;
                        $("#updatewoman").prop("checked", false);
                        $('#updateman').prop("checked", false);
                        if (sex == "MALE") {
                            $('#updateman').prop("checked", true);
                        } else {
                            $('#updatewoman').prop("checked", true);
                        }
                        $("#uId").val(jsonData.id);
                        $("#uBirth").val(jsonData.birthday);
                        $("#uAge").val(jsonData.age);
                        $("#uRegist").val(jsonData.hkAddress);
                        $("#uEducation").val(jsonData.educationCode);
                        $("#uIdentity").val(jsonData.identityTypeCode);
                        if (jsonData.identityTypeCode == '' || jsonData.identityTypeCode == null) {
                            $("#uIdentityNum").prop('disabled', true);
                        } else {
                            $("#uIdentityNum").prop('disabled', false);
                        }
                        $("#uIdentityNum").val(jsonData.identityId);
                        $("#uMobile").val(jsonData.mobile);
                        $("#uTel").val(jsonData.homePhone);
                        $("#uOfficeTel").val(jsonData.officePhone);
                        $("#uEmergencyContact").val(jsonData.emergencyContact);
                        $("#uEmergencyPhone").val(jsonData.emergencyPhone);
                        $("#uEmail").val(jsonData.email);
                        $("#uQQ").val(jsonData.qq);
                        $("#uWebChat").val(jsonData.weixinId);
                        if (jsonData.userId != null) {
                            $(".isUserFront").hide();
                        } else {
                            $(".isUserFront").show();
                        }
                    }
                });
                $(".updateStudentPopup").popup("show");
                $(".updateStudentPopup").css("top", "2%");
            });
        },
        updateStudent: function () {
            if ($("#updateStudentForm").valid()) {
                var sId, sName, sSex, sBirth, sAge, sRegist, sEducation, sIdentity, sIdentityNum, sMobile, sTel, sOfficeTel, sEmergencyContact, sEmergencyPhone, sEmail, sQQ, sWebChat, sUserFront;
                sId = $("#uId").val();
                sName = $("#uName").val();
                sSex = $('input:radio[name="uSex"]:checked').val();
                sBirth = $("#uBirth").val();
                sAge = $("#uAge").val();
                sRegist = $("#uRegist").val();
                sEducation = $("#uEducation option:selected").val();
                sIdentity = $("#uIdentity option:selected").val();
                sIdentityNum = $("#uIdentityNum").val();
                sMobile = $("#uMobile").val();
                sTel = $("#uTel").val();
                sOfficeTel = $("#uOfficeTel").val();
                sEmergencyContact = $("#uEmergencyContact").val();
                sEmergencyPhone = $("#uEmergencyPhone").val();
                sEmail = $("#uEmail").val();
                sQQ = $("#uQQ").val();
                sWebChat = $("#uWebChat").val();
                sUserFront = $('input:radio[name="uUserFront"]:checked').val();
                $.ajax({
                    type: 'post',
                    url: rootPath + "/student/updateStudent",
                    data: {
                        "id": sId,
                        "name": sName,
                        "sex": sSex,
                        "identityTypeCode": sIdentity,
                        "identityId": sIdentityNum,
                        "age": sAge,
                        "birthday": sBirth,
                        "educationCode": sEducation,
                        "hkAddress": sRegist,
                        "mobile": sMobile,
                        "email": sEmail,
                        "qq": sQQ,
                        "homePhone": sTel,
                        "officePhone": sOfficeTel,
                        "weixinId": sWebChat,
                        "emergencyContact": sEmergencyContact,
                        "emergencyPhone": sEmergencyPhone,
                        "isUserFront": sUserFront
                    },
                    dataType: 'json',
                    success: function (jsonData) {
                        if (jsonData == "success") {
                            $.msg("修改成功");
                            var pageNo=$("#pageNo").val();
                            student.search(pageNo);
                        }
                        else {
                            $.msg("格式错误，请重新修改");
                        }
                        $(".updateStudentPopup").popup("hide");
                        update.clearData();
                    }
                });
            } else {
                console.log("校验不通过");
            }
        },
        clearData: function () {
            $("#updateStudentForm")[0].reset();
            Validator2.resetForm();
            $("#updatewoman").prop("checked", false);
            $("#updateman").prop("checked", false);
        }

    }
    var list = {
        identityList: function () {
            $.ajax({
                url: rootPath + "/student/queryIdentityType",
                type: 'post',
                success: function (jsonData) {
                    $.each(jsonData, function (index, value) {
                        $("#sIdentity").append(
                            '<option value="' + value.itemCode + '">'
                            + value.itemValue + '</option>');
                        $("#uIdentity").append(
                            '<option value="' + value.itemCode + '">'
                            + value.itemValue + '</option>');
                    });

                }
            });
        },
        educationList: function () {
            $.ajax({
                url: rootPath + "/student/queryEducationType",
                type: 'post',
                success: function (jsonData) {
                    $.each(jsonData, function (index, value) {
                        $("#sEducation").append(
                            '<option value="' + value.itemCode + '">'
                            + value.itemValue + '</option>');
                        $("#uEducation").append(
                            '<option value="' + value.itemCode + '">'
                            + value.itemValue + '</option>');
                    });
                }
            });
        }
    }
    $(document).ready(function () {
        student.init();
        Form.init();
        list.identityList();
        list.educationList();
        Link.init();
        update.init();
        update.queryStudent();
        changePw.init();
        $(".ico").css("color", "red");
    })
    window.Form = Form;
    window.update = update;
    window.changePw = changePw;
})(jQuery)