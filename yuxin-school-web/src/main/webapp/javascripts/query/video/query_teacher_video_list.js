(function ($) {

    var student = {
        init: function () {
            $("#eduArea").change(function(){
                var area = $(this).find(":selected").attr("data-id");
                var stepId = $("#eduSchoolStep").find(":selected").attr("data-id");
                var schoolVal = $.trim($("#eduSchool").attr("data-id"));
                if(stepId==null || stepId==""){
                    $("#eduSchool").html('<option value="">请选择所在学校</option>');
                }else{
                    $.ajax({
                        url: rootPath + "/student/getSchoolListByStep",
                        data:{stepId:stepId, parentItemId:area},
                        type: "post",
                        success: function (data) {
                            $("#eduSchool").html('<option value="">请选择所在学校</option>');
                            var options = '';
                            $.each(data,function(i,j){
                                if(schoolVal==j.itemValue){
                                    options+='<option value="'+j.itemCode+'" selected="selected">'+j.itemValue+'</option>';
                                }else{
                                    options+='<option value="'+j.itemCode+'">'+j.itemValue+'</option>';
                                }

                            });
                            $("#eduSchool").append(options);
                        }
                    });
                }
            });
            $("#eduSchoolStep").change(function(){
                var area = $("#eduArea").find(":selected").attr("data-id");
                var stepId = $(this).find(":selected").attr("data-id");
                var schoolVal = $.trim($("#eduSchool").attr("data-id"));
                if(stepId==null || stepId==""){
                    $("#eduSchool").html('<option value="">请选择所在学校</option>');
                }else{
                    $.ajax({
                        url: rootPath + "/student/getSchoolListByStep",
                        data:{stepId:stepId, parentItemId:area},
                        type: "post",
                        success: function (data) {
                            $("#eduSchool").html('<option value="">请选择所在学校</option>');
                            var options = '';
                            $.each(data,function(i,j){
                                if(schoolVal==j.itemValue){
                                    options+='<option value="'+j.itemCode+'" selected="selected">'+j.itemValue+'</option>';
                                }else{
                                    options+='<option value="'+j.itemCode+'">'+j.itemValue+'</option>';
                                }

                            });
                            $("#eduSchool").append(options);
                        }
                    });
                }
            });
            var $this = this;
            // $selectSubMenu('statistics_all_detail');
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
            // 导出用户
            $(".exportexcle").on(
                'click',
                function () {
                    if ($(".to").val() != "") {
                        if ($(".to").val() < $(".from").val()) {
                            $.msg("时间范围不正确");
                            return;
                        }
                    }
                    if ($("#tableList").find("tr").eq(1).find("td").length <= 1) {
                        $.msg("没有数据可以导出");
                    } else {
                        $("#searchForm").attr("action",
                            rootPath + "/query/exportTeacherCourseExcle")
                            .submit();
                    }

                });
            //全选 取消全选
            $(".checkboxAll").on('change', function () {
                if ($(this).prop("checked")) {
                    $("#tableList").find(".signUpMany").prop("checked", true);
                } else {
                    $("#tableList").find(".signUpMany").prop("checked", false);
                }
            });
            $("#caddress").cityselect({
				url:rootPath + "/javascripts/company/city.min.js",
			    prov:"", //省份 
			    city:"",     //市
			    nodata:"none", //当子集无数据时，隐藏select
			    required: false	
			});
          
            
            selectGroup1('');
			selectGroup1('_add');
        },
        searchCount: function(){
        	$("#selectCounts").val($("#selectCount").val());
        	student.search();
        },
        search: function (page,sortdata) {
            var $this = this;
            var data = {};
            if(sortdata){
                data =$.extend(data,sortdata);
            }
            data.teaName=$("#teaName").val();
            data.startTime = $(".from").val();
            data.endTime = $(".to").val();
            data.eduArea=$("#eduArea").val();
            data.eduSchool=$("#eduSchool").val();
            data.eduSchoolStep=$("#eduSchoolStep").val();
            data.page = page ? page : 1;
            data.pageSize=$("#selectCounts").val() || 10;
            data.proxyOrgName = $('#proxyOrgName').val();
            data.eduSubject = $('#eduSubject').val();

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
            });
            $(".user-list").find("table").find("tr:gt(0)").remove();
            $(".checkboxAll").prop("checked", false);
            //代理机构权限
            var proxyOrgRole = $("#proxyOrgRole").val();
            var userorg_roleopenflag = $("#userorg_roleopenflag").val();
            $.ajax({
                    url: rootPath + "/query/queryTeacherCourseList",
                    data: data,
                    type: 'post',
                    beforeSend: function (XMLHttpRequest) {
                        $(".loading").show();
                        $(".loading-bg").show();
                    },
                    success: function (jsonData) {
                       
                        if (jsonData.data.length == 0) {
                        	if(userorg_roleopenflag == 1 && proxyOrgRole == 1){
	                            $(".user-list")
	                                .find("table")
	                                .append(
	                                '<tr><td colspan="15">没有查找到数据</td></tr>');
                        	}else{
                        		  $(".user-list")
	                                .find("table")
	                                .append(
	                                '<tr><td colspan="14">没有查找到数据</td></tr>');
                        	}
                        }
                        $.each(jsonData.data,function (i, videoCourse) {
                                $(".user-list")
                                    .find("table")
                                    .append(
                                    '<tr data-buy="'+(videoCourse.paymaterCount>0)+'">'
                                    + '<td>'
                                    + (videoCourse.teaName ? videoCourse.teaName
                                        : "")
                                    + '</td>'
                                    + '<td>'
                                    + (videoCourse.courseName ? videoCourse.courseName
                                        : "")
                                    + '</td>'
                                    + '<td>'
                                    + (videoCourse.areaName ? videoCourse.areaName
                                        : "")
                                    + '</td>'
                                    + '<td>'
                                    + (videoCourse.schoolName ? videoCourse.schoolName
                                        : "")
                                    + '</td>'
                                    + '<td>'
                                    + (videoCourse.subjectName ? videoCourse.subjectName
                                        : "")
                                    + '</td>'
                                    + '<td>'
                                    + (videoCourse.stepName ? videoCourse.stepName
                                        : "")
                                    + '</td>'
                                    + '<td>'
                                    + (videoCourse.totleStudy ? videoCourse.totleStudy
                                        : "")
                                    + '</td>'
                                    + '<td>'
                                    + (videoCourse.totleStudyLength ? videoCourse.totleStudyLength
                                        : "")
                                    + '</td>'
                                    + '<td>'
                                    + (videoCourse.studyRate ? videoCourse.studyRate + "%"
                                        : "")
                                    + '</td>'
                                    + '<td class="slink">'
                                    + '<a class="more" href="javascript:void(0);">详情</a>'
                                    + '<ul class="none box">'
                                    + ' <li><a class="updateStudentMsg" videoCCId="' + videoCourse.videoId + '" href="javascript:void(0);">观看比例</a></li>'
                                    + ' <li><a class="delStudent" videoCCId="' + videoCourse.videoId + '" href="javascript:void(0);">观看热点</a></li>'
                                    + '</ul></td>'
                                    + '</tr>');
                            });
                        $("#rowCount").remove();
                        $("#pageNo").remove();
                        $(".user-list").after('<input type="hidden" id="pageNo" value="'+jsonData.pageNo+'"/>');
                        
                        if (jsonData.rowCount >$("#selectCounts").val()) {
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
                            $(".pagination").find("li:first").css("background-color","#fff").css("border","1px solid #999").css('cursor','default');
                            $(".pagination").find("li:first").before('每页：<select id="selectCount"  onchange="javascript:student.searchCount()">'+
                					' <option value="10">10</option>'+
                					' <option value="20">20</option>'+
                					' <option value="30">30</option>'+
                					' <option value="50">50</option>'+
                					' <option value="100">100</option>'+
                					' </select> 条   ');
                            $("#selectCount").val($("#selectCounts").val());
//                            $("#selectCount").css("margin-bottom","").css("margin-bottom","-78px");
                        } else {
                            $(".pagination").html('');
//                            $("#selectCount").css("margin-bottom","").css("margin-bottom","-30px");
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
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        $(".loading").hide();
                        $(".loading-bg").hide();
                    }
                });
            $("#maxCount").remove();
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
                minlength: 8,
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
            suserName:{
            	required: true,
            	remote: {
                     url: rootPath + "/student/checkFrontUserName",
                     type: "post",
                     dataType: "json",
                     data: {
                         username: function () {
                             return $("#suserName").val();
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
                email: true,
                remote: {
            		url: rootPath+"/student/check",
            		type:"post",
            		dataType:"json",
            		data:{
            			email:function(){
            				return $("#sEmail").val();
            			}
            		}
            	}
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
                remote: "手机号已经存在"
            },
            suserName:{
            	 required: "用户名不能为空",
            	 remote: "用户名已经存在"
            },
            nPassword: {
                required: "密码不能为空，且最少为六位数字或英文字母",
                minlength: "密码最少为6位数字或英文字母"
            },
            rPassword: {
                required: "请重复输入密码",
                equalTo: "2次密码不一致，请修改"
            },
            sEmail:{
            	remote: "邮箱已经存在"
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
                required: true,
                maxlength: 15
            },
            uAge: {
                maxlength: 2,
                digits: true
            },
            uBirth: {
                date: true
            },
            uIdentityNum: {
               // minlength: 15,
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

            },
            uMobile: {
                required: true,
                minlength: 8,
                maxlength: 11,
                isMobile: true,

            },
            uEmergencyPhone: {
            	minlength: 8,
                maxlength: 20,
                digits: true
            },
            uTel:{
            	  minlength: 8,
            	  maxlength: 20,
                  digits: true
            },
            uOfficeTel:{
            	  minlength: 8,
            	  maxlength: 20,
                  digits: true
            },
            uQQ:{
            	maxlength: 15,
            	digits: true
            },
            uEmergencyContact:{
            	maxlength: 20
            },
            uRegist:{
            	 maxlength: 200
            },
            uWebChat:{
            	maxlength: 50
            },
            uAddressDetail:{
            	maxlength: 128
            },
            uEmail: {
            	maxlength: 50,
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
                $(".colsekuang").hide();
                $("#userId").val(mobile);
                $(".changePw1").show();
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
                        $(".changePw1").hide();
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
            $(".user-list").on("click", ".showSignUp", function () {
            	var flag=$(this).parents("tr").attr("data-buy");
				if(flag!="true"){
					if(!student.checkMaxSignUpNum($(this))){
	            		return false;
	            	}
				}
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
                form.action = rootPath + '/student/showSignUp';
                form.method = "post";
                form.target = "_self";
                var fileName = document.createElement("input");
                fileName.type = 'hidden';
                fileName.value = mobile;
                fileName.name = 'mobile';
                form.appendChild(fileName);
                form.submit();
            })
                .on("click", ".toTransaction", function () {
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
                    form.action = rootPath + '/student/toTransaction';
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
                    form.action = rootPath + '/student/toMessage';
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
                    form.action = rootPath + '/studentAgentMaterial2/stuMaterial2';
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
                .on("mouseenter.link", ".slink .more", function () {
                    $(this).nextAll("ul").show();
                })
                .on("mouseleave.link", ".slink", function () {
                    $(this).find("ul").hide();
                })
                .on("click.link",".delStudent",function(){
                	var id=$(this).attr("stuId");
                	var $this=$(this).parents("tr");
                	$.confirm("确定取消此学员的所有订单吗?",function(ok){
                		if(ok){
                			$.ajax({
                    			url: rootPath+"/studentPayMaster/abolishPayMaster",
                    			data:"stuId="+id,
                    			dataType: "json",
                    			success: function(data){
                    				if(data){
                    					$.msg("操作成功");
                    					$this.find(".baoming").css("color","red").html("未报名");
                    				}else{
                    					$.msg("稍后重试");
                    				}
                    			}
                    		})
                		}
                	});
                }).on('click',".exportStudyRecord",function(){
//                	var stuId = $(this).attr("stuId");
//                	$("#exprotForm").attr("action",rootPath+"/student_detail/exportStuLeanRecord").find('input').val(stuId);
//                	$("#exprotForm").submit();
                }).on('click',".exportExcleRecord",function(){
//                	$("#exprotForm").attr("action",rootPath+"/student_detail/exportExerciseRecord").find('input').val($(this).attr("stuId"));
//                	$("#exprotForm").submit();
                });
        }
    }
    //添加学员form
    var Form = {
        init: function () {
        	var $this=this;
            $.validator.addMethod("isMobile", function (value, element, params) {
                if (/^09\d{8}|1[3,4,5,7,8]\d{9}$/.test(value)) {
                    return true;
                } else {
                    return false;
                }
            });

            getExpandField(".addStudentPopup","w");
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
            	$(".addStudentPopup1").show();
                $(".addStudentPopup").popup("show");
                $(".addStudentPopup").css("top", "2%");
                $("#add_div_school").show();
                $("#add_div_class").show();
                Form.clearData();
                $('#insertman').prop("checked", true);
                $(".colsekuang").hide();
            });
            $(".canclekuang").on('click',function(){
            	$(".addStudentPopup1").hide();
            	$(".updateStudentPopup1").hide();
            	 $(".changePw1").hide();
            })
            Validator = $("#addStudentForm").validate(rules);
			$("#sAddress").cityselect({
				url:rootPath + "/javascripts/company/city.min.js",
			    prov:"", //省份 
			    city:"",     //市
			    nodata:"none", //当子集无数据时，隐藏select
			    required: false
			});
        },
        clearData: function () {
            $("#addStudentForm")[0].reset();
            Validator.resetForm();
            $("#insertwoman").prop("checked", false);
            $("#insertman").prop("checked", false);
        },
        confirm: function(msg,callback){ //根据需求1415 任务656 将公共confirm修改到此
    		var reminder,information,tips,sava,fadeOutBefor;
    		// 如果传入的不是对象
    		if(typeof msg!='object'){
    			reminder='提示';
    			information=msg;
    			fadeOutBefor='yes';
    			sava='确定';
    		}else{
    			reminder=msg.title || '提示';
    			callback=msg.callback;
    			fadeOutBefor=msg.fadeOutBefor || 'yes';
    			information=msg.text;
    			sava=msg.save || '保存';
    		}
    		tips='<div class="layerTips Confirm" style="display: none;">'+
    			'<div class="layerTipsTitle">'+reminder+'<i class="Close iconfont Confirm_Close"></i></div>'+
    			'<div class="layerTipsContent">'+information+'</div>'+
    			'<div class="layerTipsBtns">'+
    			'<a href="javascript:;" class="btn btn-mini btn-success Confirm_Ok">'+sava+'</a>'+
    			'</div>'+
    			'</div>'+
    			'<div class="layerTipsBg Confirm" style="display: none;"></div>';
    		$(document).find(".layerTips").remove();
    		$(document).find(".layerTipsBg").remove();
    		$(document).find("body").append(tips);

    		// 判断传入参数是否元素
    		if(!(/<.*>.*<\/(\w+){3,8}/.test(information))){
    			$('.layerTips .layerTipsContent').width('200px');
    		}else{
    			// 如果输入的是元素控制元素居中
    			$('.layerTips.Confirm').css(
    				{
    					'margin-left':-$('.layerTips.Confirm').width()/2,
    					'margin-top':-$('.layerTips.Confirm').height()/2
    				});
    		}

    		// 背景样式及背景出现
    		$('.layerTipsBg').fadeIn(200,function(){
    			$('.layerTips.Confirm').fadeIn(200)
    		});
    		//确定
    		$(document).off("click.ok.Confirm").on("click.ok.Confirm",".Confirm_Ok",function(){
    			if(fadeOutBefor == 'yes'){
    				$(this).parents('.layerTips').fadeOut(200,function(){
    					$('.layerTipsBg.Confirm').fadeOut(200);
    				});
    			}
    			if(callback){
    				callback(true);
    			}
    		})
    			.on("click.Close.Confirm",".Confirm_Close",function(){
    				$(this).parents('.layerTips').fadeOut(200,function(){
    					$('.layerTipsBg.Confirm').fadeOut(200);
    				});
    			});
    		//取消
    		$(document).off("click.cancle.Confirm").on("click.cancle.Confirm",".Confirm_Cancle",function(){
    			$(this).parents('.layerTips').fadeOut(200,function(){
    				$('.layerTipsBg.Confirm').fadeOut(200);
    			});
    			if(callback){
    				callback(false);
    			}
    		});
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
    //查询扩展字段
	var getExpandField=function(ele,rw,func){
    	$(ele).find(".customData").remove();
		$.ajax({
			url: rootPath+"/longitudinalTableColDefine/getData",
			dataType:"json",
			success:function(jsonData){
				var valueble;
				$.each(jsonData,function(i,data){
					if(data.allowModify){
					var dom='<div class="form-group customData">';
					if(rw=="r" && data.orgAllowRead ){
						dom+='<label class="col-md-2 control-label">'+data.colComment+'</label> <div class="col-md-2">'+
						'<span id="'+data.id+'" value="'+data.colValue+'" class="field">'+data.colName+'</span></div>';
					}else if(rw=="w" && data.orgAllowWrite){
						if(data.tldType=="text"){
							dom+='<label class="col-md-2 control-label">'+data.colComment+(data.colAllowNull==0?'<i class="iconfont ico"></i>':'')+'</label> <div class="col-md-3">'+
							'<input type="text" id="'+data.colName+'" name="'+data.colName+'" style="'+(data.styleCss?data.styleCss:'')+'" class="form-control field '+(data.styleClass?data.styleClass:'')+'" value="'+(data.defaultValue?data.defaultValue:'')+'" >'+
							'</div>'+
							'</div>';
						}else if(data.tldType=="select"){
							dom+='<label class="col-md-2 control-label">'+data.colComment+(data.colAllowNull==0?'<i class="iconfont ico"></i>':'')+'</label> <div class="col-md-3">'+
							'<select style="'+(data.styleCss?data.styleCss:'')+'" class="form-control field '+(data.styleClass?data.styleClass:'')+'" value="'+(data.defaultValue?data.defaultValue:'')+'" id="'+data.colName+'" name="'+data.colName+'" value="'+(data.defaultValue?data.defaultValue:'')+'">';
							$.each(data.itemsData,function(j,item){
								dom+='<option value="'+item[data.itemsValue]+'">'+item[data.itemsName]+'</option>';
							})
							dom+='</div>'+
							'</div>';
						}
					}
					}else{
						dom='<input type="hidden" id="'+data.colName+'" name='+data.colName+' value=""/>'
					}
					$(ele).find(".form-group:last").before(dom);
				})
				if(func){func()}
			}
		})
	}
	//查询扩展数据
	var getExpandData=function(ele,table,colName,colValue){
		var data={};
		data.tableName=table;
		data.colName=colName;
		data.colValue=colValue;
		$.ajax({
			url: rootPath+"/longitudinalTableData/getData",
			data:data,
			dataType:"json",
			success:function(jsonData){
				$.each(jsonData,function(i,data){
					if($(ele).find("#"+data.colName).is('input') || $(ele).find("#"+data.colName).is('select')){
						$(ele).find("#"+data.colName).val(data.colValue);
					}else{
						$(ele).find("#"+data.colName).html(data.colValue);
					}
					
				})
			}
		})
	}
    $(document).ready(function () {
        student.init();
        Form.init();
        list.identityList();
        list.educationList();
        Link.init();
        update.init();
        changePw.init();
        $(".ico").css("color", "red");
    })
    window.student = student;
    window.Form = Form;
    window.update = update;
    window.changePw = changePw;
})(jQuery)
