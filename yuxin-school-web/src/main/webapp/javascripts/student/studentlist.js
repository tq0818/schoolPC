(function ($) {

    var student = {
        init: function () {
            $("#eduArea").change(function(){
                var area = $(this).find(":selected").attr("data-id");
                var schoolVal = $.trim($("#eduSchool").attr("data-id"));
                if(area==null || area==""){
                    $("#eduSchool").html('<option value="">请选择所在学校</option>');
                }else{
                    $.ajax({
                        url: rootPath + "/student/getSchoolList/"+area,
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
            $("#editEduArea").change(function(){
                var area = $(this).find(":selected").attr("data-id");
                var schoolVal = $.trim($("#editEduSchool").attr("data-id"));
                if(area==null || area==""){
                    $("#editEduSchool").html('<option value="">请选择所在学校</option>');
                }else{
                    $.ajax({
                        url: rootPath + "/student/getSchoolList/"+area,
                        type: "post",
                        success: function (data) {
                            $("#editEduSchool").html('<option value="">请选择所在学校</option>');
                            var options = '';
                            $.each(data,function(i,j){
                                if(schoolVal==j.itemCode){
                                    options+='<option value="'+j.itemCode+'" selected="selected">'+j.itemValue+'</option>';
                                }else{
                                    options+='<option value="'+j.itemCode+'">'+j.itemValue+'</option>';
                                }

                            });
                            $("#editEduSchool").append(options);
                        }
                    });
                }
            });
            $("#addEduArea").change(function(){
                var area = $(this).find(":selected").attr("data-id");
                var schoolVal = $.trim($("#addEduSchool").attr("data-id"));
                if(area==null || area==""){
                    $("#addEduSchool").html('<option value="">请选择所在学校</option>');
                }else{
                    $.ajax({
                        url: rootPath + "/student/getSchoolList/"+area,
                        type: "post",
                        success: function (data) {
                            $("#addEduSchool").html('<option value="">请选择所在学校</option>');
                            var options = '';
                            $.each(data,function(i,j){
                                if(schoolVal==j.itemValue){
                                    options+='<option value="'+j.itemCode+'" selected="selected">'+j.itemValue+'</option>';
                                }else{
                                    options+='<option value="'+j.itemCode+'">'+j.itemValue+'</option>';
                                }

                            });
                            $("#addEduSchool").append(options);
                        }
                    });
                }
            });
            $("#addEduArea").change();

            $("#add_eduIdentity_stu").click(function(){
                //$("#add_div_school").show();
                $("#addEduSchool").show();
                $("#addEduSchool").parent().prev().show();
                $("#add_div_class").show();
            });
            $("#add_eduIdentity_normal").click(function(){
                $("#addEduSchool").hide();
                $("#addEduSchool").parent().prev().hide();
                $("#add_div_class").hide();
            });
            $("#edit_eduIdentity_stu").click(function(){
                //$("#edit_div_school").show();
                $("#editEduSchool").show();
                $("#editEduSchool").parent().prev().show();
                $("#edit_div_class").show();
            });
            $("#edit_eduIdentity_normal").click(function(){
                //$("#edit_div_school").hide();
                $("#editEduSchool").hide();
                $("#editEduSchool").parent().prev().hide();
                $("#edit_div_class").hide();
            });



            var $this = this;
            $selectSubMenu('student_manage');
            // 初始化日期框
            $(".date-picker").datetimepicker({
                format: "yyyy-mm-dd",
                minView: 2,
                autoclose: true,
                language: "zh-CN"
            });
            // 初始化数据
            $this.search();
            //添加学员
            $(document).on('click','.addStudentOk',function(){
            	Form.addTeacher();
            })
            // 收索
            $(".searchContents").on('click', function () {
                $this.search();
            });
            // 导入用户
            $(".importexcle").on('click', function () {
                $(this).attr("href", rootPath + "/student/importPage");
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
            $(".exportStudentDatas").on('click',function(){
                	 $("#searchForm").attr("action",
                             rootPath + "/student/exportStudentPayMaster")
                             .submit();
              
            });
            $("#eduArea").change();
            //批量报名
            $(".signUpMany").on('click', function () {
            	if(!student.checkMaxSignUpNum()){
            		return false;
            	}
            	var umbile=$("#mobileSet").val();
            	var uName=$("#userNameSet").val();
                var list = new Array;
                $("#tableList").find(".signUpMany:checked").each(function () {
                	if(umbile==1){
                		if($(this).val() && $(this).val()!=""){
                			list.push($(this).val());
                		}else{
                			list.push($(this).attr("uname"));
                		}
                	}else{
                		if(uName==1){
                			if($(this).attr("uname") && $(this).attr("uname")!=""){
                				list.push($(this).attr("uname"));
                			}else{
                				list.push($(this).val());
                			}
                		}else{
                			list.push($(this).val());
                		}
                	}
                });
                if (list.length == 0) {
                    $.msg("请至少选择一个学员");
                    return false;
                }
                var form = document.createElement("form");
                document.body.appendChild(form);
                form.action = rootPath + '/student/showSignUpMany';
                form.method = "post";
                form.target = "_self";
                var fileName = document.createElement("input");
                fileName.type = 'hidden';
                fileName.value = list;
                fileName.name = 'list';
                form.appendChild(fileName);
                form.submit();
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
        search: function (page) {
            var $this = this;
            var data = {};
            data.mobile = $("#stuMobile").val();
            data.username=$("#stuusername").val();
            data.name = $("#stuName").val();
            data.identityId = $("#sfzh").val();
            data.startTime = $(".from").val();
            data.endTime = $(".to").val();
            data.status = $("#registStatus").val();// 注册状态
            data.registType = $("#registMethods").val();// 注册方式
            data.paymaterCount = $("#payStatus").val();// 报名状态
			data.province=$("#caddress").find("#prov").val()?$("#caddress").find("#prov").val():"";
			data.city=$("#caddress").find("#city").val()?$("#caddress").find("#city").val():"";
			data.county=$("#caddress").find("#dist").val()?$("#caddress").find("#dist").val():"";
			data.groupOneId=$("#studentG1").val();
			data.groupTwoId=$("#studentG2").val();
            data.eduArea=$("#eduArea").val();
            data.eduSchool=$("#eduSchool").val();
            data.eduStep=$("#EduSteps").val();
            data.eduYear=$("#EduYears").val();
            data.eduClass=$("#EduClasses").val();
            data.page = page ? page : 1;
            data.pageSize=$("#selectCounts").val() || 10;
            data.proxyOrgName = $('#proxyOrgName').val();
            var tel = $("#stuMobile").val(); // 获取手机号
            if (tel != "") {
                var telReg = !!tel.match(/^(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
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
            //代理机构权限
            var proxyOrgRole = $("#proxyOrgRole").val();
            var userorg_roleopenflag = $("#userorg_roleopenflag").val();
            $.ajax({
                    url: rootPath + "/student/queryStudentsList",
                    data: data,
                    type: 'post',
                    beforeSend: function (XMLHttpRequest) {
                        $(".loading").show();
                        $(".loading-bg").show();
                    },
                    success: function (jsonData) {
                       var isArea=$("#eduArea").val();
                       var roleType=$("#roleType").val();
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
                        var html;
                        $.each(jsonData.data,function (i, stu) {
                            var eduIdentity = null;
                            if(stu.teacherFlag!=null){
                                if(stu.teacherFlag==1){
                                    eduIdentity = "教师";
                                }
                            }
                            if(eduIdentity==null){
                                eduIdentity = (stu.eduIdentity!=null ? (stu.eduIdentity==0?"学生":"普通用户"): "")
                            }
                                
                                    html+= '<tr data-buy="'+(stu.paymaterCount>0)+'">'
                                    + '<td>'
                                    + '<input type="checkbox" class="signUpMany" uName="'+(stu.username?stu.username:"")+'" value="' + (stu.mobile?stu.mobile:"") + '">'
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
                                    + eduIdentity
                                    + '</td>'
                                    + '<td>'
                                    + (stu.eduArea ? stu.eduArea
                                        : "")
                                    + '</td>'
                                    + '<td>'
                                    + (stu.eduSchool ?stu.eduSchool
                                        : "")
                                    + '</td>'
                                    + '<td>'
                                    + (stu.eduStep!=null ? (stu.eduStep+stu.eduYear+"年"+stu.eduClass+"班")
                                        : "")
                                    + '</td>'
                                    + (userorg_roleopenflag==1 && proxyOrgRole==1?'<td>'
                                    + (stu.proxyOrgName ? stu.proxyOrgName
                                        : "")
                                    + '</td>':'')
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
                                    + '</td>';
                                    if(roleType==1 || roleType==0 || roleType==3){
                                	html+= '<td class="slink">'
                                    + '<a class="showSignUp" mobile="' + (stu.mobile?stu.mobile:"") + '" uName="'+(stu.username?stu.username:"")+'" href="javascript:void(0);">报名</a>|'
                                    + '<a class="studentDetail" mobile="' + (stu.mobile?stu.mobile:"") + '" uName="'+(stu.username?stu.username:"")+'" href="javascript:void(0);">详情</a>|'
                                    + '<a class="more" href="javascript:void(0);">更多</a>'
                                    + '<ul class="none box">'
                                    + ' <li><a class="updateStudentMsg" stuId="' + stu.id + '" href="javascript:void(0);">修改信息</a></li>'
                                    +(($("#isDelete").val()==1)?((stu.paymaterCount > 0)?' <li><a class="delStudent" stuId="'+stu.id+'" href="javascript:void(0);">取消报名</a></li>':""):"");
                                    if(isArea==0){
                                    	 html+= (stu.userId ? (stu.status == 1 ? '<li><a class="updateStatus"  userId="' + stu.userId + '" status="' + stu.status + '" href="javascript:void(0);">禁用用户</a></li>' : '<li><a class="updateStatus" userId="' + stu.userId + '" status="' + stu.status + '" href="javascript:void(0);">启用用户</a></li>' ) : '');	
                                    }else{
                                    	 html+='<li><a class="updateStuStatus" stuId="' + stu.id+ '" href="javascript:void(0);">移除用户</a></li>';	
                                    }
                                    html+=  '<li><a class="changePwd" userId="' + stu.userId + '" href="javascript:void(0);">修改密码</a></li>' ;
                                    +(stu.status == 1 && stu.paymaterCount > 0 ? '<li><a class="exportStudyRecord" stuId="'+stu.id+'" href="'+rootPath+'/student_detail/openStdentAllCl?stuId='+stu.id+'" target="_blank">学习记录</a></li>' : '')
									+(stu.status == 1 && stu.paymaterCount > 0 ? '<li><a class="exportExcleRecord" stuId="'+stu.id+'" href="'+rootPath+'/student_detail/openStdentAllExt?stuId='+stu.id+'" target="_blank">做题记录</a></li>' : '')
                                    + ((stu.paymaterCount > 0 && stu.commodityType!='COMMODITY_PACKAGE') ? '<li><a class="toTransaction" mobile="' + (stu.mobile?stu.mobile:"") + '" uName="'+(stu.username?stu.username:"")+'" href="javascript:void(0);">异动</a></li>' : '')
                                    + (stu.paymaterCount > 0 ? (stu.ispay == "1" ? '<li><a class="toMessage" mobile="' + (stu.mobile?stu.mobile:"") + '" uName="'+(stu.username?stu.username:"")+'" href="javascript:void(0);">补费</a></li>' : '' ) : '')
                                    + (stu.paymaterCount > 0 ?
                                        (stu.agentFlag == "1" ?
                                            (stu.isAgent == "1" ?
                                            '<li><a class="showStuMaterial" mobile="' +(stu.mobile?stu.mobile:"")+ '" uName="'+(stu.username?stu.username:"")+'" href="javascript:void(0);">报考材料</a></li>'
                                                : '')
                                            : '')
                                        : '')
                                    + '</ul></td>';
                                    }
                                   html+= '</tr>';
                                   
                            });
                        $(".user-list")
                        .find("table")
                        .append(html);
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
                .on("click", ".updateStuStatus", function () {
                	var userId = $(this).attr("stuId");
                	$.ajax({
                		type: 'post',
                		url: rootPath + "/student/updateStatus",
                		data: {
                			"userId": userId,
                			"status": null,
                			
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
        addTeacher: function () {
            if ($("#addStudentForm").valid()) {
                var add_eduIdentity = 1;
                
            	var data={};
            	data.roleType=$("#roleType").val();
            	data.isArea=$("#isArea").val();
                data.name = $("#sName").val();
                data.sex = $('input:radio[name="sSex"]:checked').val();
                data.birthday = $("#sBirth").val();
                data.age = $("#sAge").val();
                data.hkAddress = $("#sRegist").val();
                data.educationCode = $("#sEducation option:selected").val();
                data.identityId = $("#sIdentityNum").val();
                data.identityTypeCode = $("#sIdentity option:selected").val();
                data.mobile = $("#sMobile").val();
                data.homePhone = $("#sTel").val();
                data.officePhone = $("#sOfficeTel").val();
                data.emergencyContact = $("#sEmergencyContact").val();
                data.emergencyPhone = $("#sEmergencyPhone").val();
                data.email = $("#sEmail").val();
                data.qq = $("#sQQ").val();
                data.remarkName = $("#remark_name").val();
                data.weixinId = $("#sWebChat").val();
                data.province=$("#sAddress").find("#prov").val();
                data.city=$("#sAddress").find("#city").val();
                data.county=$("#sAddress").find("#dist").val();
                data.addressDetail=$("#sAddressDetail").val();
                data.username=$("#suserName").val();
                data.groupOneId=$("#studentG1_add").val();
                data.groupTwoId=$("#studentG2_add").val();
                data.eduArea=$("#addEduArea").val();
                if(null==$("#addEduArea").val() ||''==$("#addEduArea").val()){
                	 $.msg("请选择所在区域");
                    return;
               }
                if(data.isArea==1||data.isArea==0){
                	 data.eduSchool=$("#addEduSchool").val();
                	 if(null==$("#addEduSchool").val() ||''==$("#addEduSchool").val()){
                    	 $.msg("请选择学校");
                         return;
                    }
                }else{
        			data.eduSchool=$("#addEduSchools").val();
        			if(null==$("#addEduSchools").val() ||''==$("#addEduSchools").val()){
                    	 $.msg("请选择学校");
                         return;
                    }
                }
               
                data.eduStep=$("#addEduStep").val();
                if(null==$("#addEduStep").val() ||''==$("#addEduStep").val()){
                	$.msg("请选择学段");
                	return;
                }
                data.eduYear=$("#addEduYear").val();
                if(null==$("#addEduYear").val() ||''==$("#addEduYear").val()){
                	$.msg("请选择学年");
                	return;
                }
                data.eduClass=$("#addEduClass").val();
                if(null==$("#addEduClass").val() ||''==$("#addEduClass").val()){
                	$.msg("请选择班级");
                	return;
                }
                data.isUserFront = $('input:radio[name="sUserFront"]:checked').val()==1;
                data.eduIdentity=add_eduIdentity;
               
                $(".customData").find(".field").each(function(){
                	data[$(this).attr("name")]=$(this).val();
                });
                var re=/^[a-zA-Z]+[a-zA-Z0-9_]\w{2,14}$/;
                if($("#suserName").val() && $("#suserName").val()!=""){
                	if(!$("#suserName").val().match(re) || $("#suserName").val()=="null"){
                    	$.msg("用户名格式不正确");
                    	return;
                    }
                }
                var addrflag=$("#addreSet").val();
                if(addrflag && addrflag==1){
                	if(!data.province || data.province==""){
                		$.msg("请选择地址信息");
                		return;
                	}
                }
                $.ajax({
                    type: 'post',
                    url: rootPath + "/student/insert",
                    data: data,
                    dataType: 'json',
                    beforeSend: function ( XMLHttpRequest ) {
                    	$(".addStudentOk").attr({"disabled":"disabled"});
                    },
                    success: function (jsonData) {
//						$("#teacherList").append(
//								'<option value="' + jsonData.id + '">'
//										+ jsonData.name + '</option>');
                        if (jsonData == "success") {
                            student.search();
                            if(!data.mobile && data.username){
                            	Form.confirm("添加成功！学员账号初始密码为 ：111111");
                            }else{
                            	Form.confirm("添加成功！学员账号初始密码为手机号后六位");
                            }
                            $(".addStudentPopup").popup("hide");
                            $(".addStudentPopup1").hide();
                            Form.clearData();
                        }else {
                            if(jsonData=="0101"){
                                $.msg("手机号为空，请添加！");
                                return;
                            }else if(jsonData=="0102"){
                                $.msg("手机号格式不正确，请修改！");
                                return;
                            }else if(jsonData=="0302"){
                                $.msg("身份证号格式不正确，请修改！");
                                return;
                            }else if(jsonData=="0303"){
                                $.msg("身份证号已存在，请修改！");
                                return;
                            }else if(jsonData=="0201" || jsonData=="0202"){
                                $.msg("姓名不正确，请修改！");
                                return;
                            }else if(jsonData=="0402" ){
                                $.msg("邮箱不正确，请修改！");
                                return;
                            }else if(jsonData=="0602" ){
                                $.msg("紧急联系人不正确，请修改！");
                                return;
                            }else if(jsonData=="0702" ){
                                $.msg("紧急联系人电话不正确，请修改！");
                                return;
                            }else{
                                $.msg("格式错误，请重新修改");
                            }
                        }
                    },
                    complete: function ( XMLHttpRequest, textStatus ) {
                    	$(".addStudentOk").removeAttr("disabled");
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
        queryStudent: function () {
        	selectGroup1('_edit');
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
                        $("#uuserName").val(jsonData.username);
                        $("#uTel").val(jsonData.homePhone);
                        $("#uOfficeTel").val(jsonData.officePhone);
                        $("#uEmergencyContact").val(jsonData.emergencyContact);
                        $("#uEmergencyPhone").val(jsonData.emergencyPhone);
                        $("#uEmail").val(jsonData.email);
                        $("#uQQ").val(jsonData.qq);
                        $("#uWebChat").val(jsonData.weixinId);
                        $("#uAddressDetail").val(jsonData.addressDetail);
                        $(".remark_names").val(jsonData.remarkName);
                        if (jsonData.userId != null) {
                            $(".isUserFront").hide();
                        } else {
                            $(".isUserFront").show();
                        }
                        $("#uAddress").cityselect({
            				url:rootPath + "/javascripts/company/city.min.js",
            			    prov:jsonData.province?jsonData.province:"", //省份 
            			    city:jsonData.city?jsonData.city:"",
            			    dist: jsonData.county?jsonData.county:"",//市
            			    nodata:"none", //当子集无数据时，隐藏select
            			    required: false	
            			});
                        $("#studentG1_edit").find("option").each(function(){
                        	$(this).removeAttr("selected");
                        })
                        $("#studentG1_edit").find("option[value='"+(jsonData.groupOneId?jsonData.groupOneId:'')+"']").attr("selected",true); 
                        $("#studentG1_edit").html($("#studentG1_edit").html());
                        selectGroup_2(null,'_edit',(jsonData.groupOneId?jsonData.groupOneId:''));
                        $("#studentG2_edit").find("option").each(function(){
                        	$(this).removeAttr("selected");
                        })
                        $("#studentG2_edit").find("option[value='"+(jsonData.groupTwoId?jsonData.groupTwoId:'')+"']").attr("selected",true); 
                        $("#studentG2_edit").html($("#studentG2_edit").html());


                        var eduIdentity = jsonData.eduIdentity;
                        var eduArea = jsonData.eduArea;
                        if (eduIdentity == 1) {
                            $('#edit_eduIdentity_normal').prop("checked", true);
                            //$("#edit_div_school").hide();
                            //$("#edit_div_class").hide();


                            //$("#edit_div_school").hide();
                            $("#editEduSchool").hide();
                            $("#editEduSchool").parent().prev().hide();
                            $("#edit_div_class").hide();


                        } else {
                            $('#edit_eduIdentity_stu').prop("checked", true);

                            $("#editEduSchool").show();
                            $("#editEduSchool").parent().prev().show();
                            $("#edit_div_class").show();



                            //$("#edit_div_school").show();
                           // $("#edit_div_class").show();
                        }

                        $("#editEduArea").find("option").each(function(){
                            $(this).removeAttr("selected");
                        });
                        $("#editEduStep").find("option").each(function(){
                            $(this).removeAttr("selected");
                        })
                        $("#editEduYear").find("option").each(function(){
                            $(this).removeAttr("selected");
                        })
                        $("#editEduClass").find("option").each(function(){
                            $(this).removeAttr("selected");
                        })

                        $("#editEduArea option[value="+eduArea+"]").prop("selected",true);

                        $("#editEduStep option[value="+jsonData.eduStep+"]").prop("selected",true);
                        $("#editEduYear option[value="+jsonData.eduYear+"]").prop("selected",true);
                        $("#editEduClass option[value="+jsonData.eduClass+"]").prop("selected",true);
                        $("#editEduSchool").attr("data-id",jsonData.eduSchool);
                        $("#editEduArea").change();

                    }
                });
                $(".updateStudentPopup1").show();
                $(".updateStudentPopup").popup("show");
                $(".updateStudentPopup").css("top", "2%");
                $(".colsekuang").hide();
                
                getExpandField(".updateStudentPopup","w",function(){
    				getExpandData(".updateStudentPopup","student","stu_id",id);
    			});
                
            });
        },
        updateStudent: function () {
            if ($("#updateStudentForm").valid()) {
                var add_eduIdentity = 1;
                if($('input:radio[name="editeduIdentity"]:checked').val()=="0"){
                    add_eduIdentity = 0;
                }else{
                    $("#editEduStep").find("option[value='']").prop("selected","true");
                    //$("#editEduArea").find("option[value='']").attr("selected","true");
                    $("#editEduSchool").find("option[value='']").prop("selected","true");
                    $("#editEduYear").find("option[value='']").prop("selected","true");
                }
            	var data={};
            	data.id=$("#uId").val();
                data.name = $("#uName").val();
                data.sex = $('input:radio[name="uSex"]:checked').val();
                data.birthday = $("#uBirth").val();
                data.age = $("#uAge").val();
                data.hkAddress = $("#uRegist").val();
                data.educationCode = $("#uEducation option:selected").val();
                data.identityId = $("#uIdentityNum").val();
                data.identityTypeCode = $("#uIdentity option:selected").val();
                data.mobile = $("#uMobile").val();
                data.homePhone = $("#uTel").val();
                data.officePhone = $("#uOfficeTel").val();
                data.emergencyContact = $("#uEmergencyContact").val();
                data.emergencyPhone = $("#uEmergencyPhone").val();
                data.email = $("#uEmail").val();
                data.qq = $("#uQQ").val();
                data.remarkName=$(".remark_names").val();
                data.weixinId = $("#uWebChat").val();
                data.province=$("#uAddress").find("#prov").val();
                data.city=$("#uAddress").find("#city").val();
                data.county=$("#uAddress").find("#dist").val();
                data.addressDetail=$("#uAddressDetail").val();
                data.isUserFront = $('input:radio[name="uUserFront"]:checked').val()==1;
                data.groupOneId=$("#studentG1_edit").val();
                data.groupTwoId=$("#studentG2_edit").val();

                data.eduIdentity=add_eduIdentity;
                data.eduArea=$("#editEduArea").val();
                data.eduSchool=$("#editEduSchool").val();
                data.eduStep=$("#editEduStep").val();
                data.eduYear=$("#editEduYear").val();
                data.eduClass=$("#editEduClass").val();


                if(add_eduIdentity==0){
                    if(data.eduArea==""){
                        $.msg("请选择所在区域");
                        return;
                    }
                    if(data.eduSchool==""){
                        $.msg("请选择学校");
                        return;
                    }
                    if(data.eduStep=="" || data.eduYear==""){
                        $.msg("请选择班级");
                        return;
                    }
                }else{
                    if(data.eduArea==""){
                        $.msg("请选择所在区域");
                        return;
                    }
                }

                $(".customData").find(".field").each(function(){
                	console.log($(this).attr("name"),$(this).val());
                	data[$(this).attr("name")]=$(this).val();
                });
                var addrflag=$("#addreSet").val();
                if(addrflag && addrflag==1){
                	if(!data.province || data.province==""){
                		$.msg("请选择地址信息");
                		return;
                	}
                }
                $.ajax({
                    type: 'post',
                    url: rootPath + "/student/updateStudent",
                    data: data,
                    dataType: 'json',
                    success: function (jsonData) {
                        if (jsonData == "success") {
                            $.msg("修改成功");
                            var pageNo=$("#pageNo").val();
                            student.search(pageNo);
                            $(".updateStudentPopup").popup("hide");
                            $(".updateStudentPopup1").hide();
                            update.clearData();
                        }else {
                        	if(jsonData=="0101"){
                        		 $.msg("手机号为空，请添加！");
                        		 return;
                        	}else if(jsonData=="0102"){
                        		 $.msg("手机号格式不正确，请修改！");
                        		 return;
                        	}else if(jsonData=="0302"){
                        		$.msg("身份证号格式不正确，请修改！");
                        		return;
                        	}else if(jsonData=="0303"){
	                        	$.msg("身份证号已存在，请修改！");
	                        	return;
                        	}else if(jsonData=="0201" || jsonData=="0202"){
	                        	$.msg("姓名不正确，请修改！");
	                        	return;
                        	}else if(jsonData=="0402" ){
	                        	$.msg("邮箱不正确，请修改！");
	                        	return;
	                        }else if(jsonData=="0602" ){
	                        	$.msg("紧急联系人不正确，请修改！");
	                        	return;
	                        }else if(jsonData=="0702" ){
	                        	$.msg("紧急联系人电话不正确，请修改！");
	                        	return;
	                        }else{
	                        	$.msg("格式错误，请重新修改");
	                        }
                        }
                       
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
        update.queryStudent();
        changePw.init();
        $(".ico").css("color", "red");
    })
    window.student = student;
    window.Form = Form;
    window.update = update;
    window.changePw = changePw;
})(jQuery)
