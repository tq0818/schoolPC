function closeBtn(companyId,itemCode,delFlag) {

    $.ajax({
        type : 'post',
        url : '/serviceManager/updateDelFlag',
        data : {
            companyId : companyId,
            itemCode : itemCode,
            delFlag : delFlag
        },
        success : function(data){
        	if(data.msg=="success"){
        		window.location.href = rootPath+"/serviceManager/getServiceManager?companyId="+companyId;
    		}else{
    			alert("操作失败");
    			return;
    		}
        }
    });
}

(function ($) {
	//记录排序规则
	var recordPaixu =null;
    var company = {
        init: function () {
            var $this = this;
            // 初始化日期框
            $(".date-picker").datetimepicker({
                format: "yyyy-mm-dd",
                minView: 2,
                autoclose: true,
                language: "zh-CN"
            });
            // 初始化数据
            $this.search(1,null);
            //搜索
            $(".searchContents").on('click', function () {
                $this.search(1,null);
            });
            $(".time").on('click', function () {
            	if($("#time").val()==1){
            		//2 asc 1 desc
            		$("#time").val("2")
            		$this.search($("#pageNo").val(),1);
            	}else{
            		$("#time").val("1")
            		$this.search($("#pageNo").val(),2);
            	}
            });
            $(".penNum").on('click', function () {
            	if($("#penNum").val()==3){
            		//2 asc 1 desc
            		$("#penNum").val("4")
            		$this.search($("#pageNo").val(),3);
            	}else{
            		$("#penNum").val("3")
            		$this.search($("#pageNo").val(),4);
            	}
            });
            $(".lessNum").on('click', function () {
            	if($("#lessNum").val()==7){
            		//2 asc 1 desc
            		$("#lessNum").val("8")
            		$this.search($("#pageNo").val(),7);
            	}else{
            		$("#lessNum").val("7")
            		$this.search($("#pageNo").val(),8);
            	}
            });
            $(".clasNum").on('click', function () {
            	if($("#clasNum").val()==5){
            		//2 asc 1 desc
            		$("#clasNum").val("6")
            		$this.search($("#pageNo").val(),5);
            	}else{
            		$("#clasNum").val("5")
            		$this.search($("#pageNo").val(),6);
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
            $("#searchBranchSchool").on('click', function () {
                $this.searchBranchSchool($('#branchCode').val());
            });
        },
        searchBranchSchool:function(brachCode){
        	if(brachCode==""){
        		alert("机构代码不能为空");
        		return;
        	}
        	var data={};
        	data.brachCode=brachCode;
            $.ajax({
                    url: rootPath + "/berkeley/queryCompanyVo",
                    data: data,
                    type: 'post',
                    beforeSend: function (XMLHttpRequest) {
                    },
                    success: function (jsonData) {
                    	if(jsonData==null || jsonData==''){
                    		alert("输入错误");
                    		$("#branchCode").val("");
                    		$("#branchSchool").text("");
                    		$("#eara").text("");
                    		return;
                    	}
                    	var companyName=jsonData.companyName;
                    	var eduArea=jsonData.eduArea;
                    	var dictCode=jsonData.dictCode;
                    	var schoolProperty=jsonData.schoolProperty;
                    	$('#branchSchool').text(companyName);
                    	$('#eara').text(eduArea);
                    	$('#schoolProperty').text(schoolProperty);
                    	$('#isArea').val(dictCode);
                    	if(companyName==eduArea){
                    		var options = document.getElementById('schoolProperties').children;
                        	options[0].selected=true;
                        	document.getElementById("schoolProperties").disabled=true;
                            document.getElementById("selectSchoolProperties").style.display="none";
                            document.getElementById("selectSchoolProperties1").style.display="";
                    	}else{
                            document.getElementById("selectSchoolProperties").style.display="";
                            document.getElementById("selectSchoolProperties1").style.display="none";
                    		document.getElementById("schoolProperties").disabled=false;
                    	}
                    	
                    	
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                    }
                });
        },
        saveBranchSchool:function(){
        	var data={};
        	data.branchCode=$.trim($('#branchCode').val());
        	data.schoolProperties=$('#schoolProperties').val();
        	data.linkPerson=$('#linkPerson').val();
        	data.linkPhone=$('#linkPhone').val();
        	data.domain=$('#domain').val();
        	data.domainManage=$('#domainManage').val();
        	data.privateCost=$('#privateCost').val();
        	data.publicCost=$('#publicCost').val();
        	data.flowSize=$('#flowSize').val();
        	data.spaceSize=$('#spaceSize').val();
        	data.ccUserName=$('#ccUserName').val();
        	data.ccPwd=$('#ccPwd').val();
        	data.zsUserName=$('#zsUserName').val();
        	data.zsPwd=$('#zsPwd').val();
        	data.schoolSummary=$('#schoolSummary').val();
        	
        },
        searchCount: function(){
        	$("#selectCounts").val($("#selectCount").val());
        	$this.search(1,null);
        },
        search: function (page,paixu) {
            recordPaixu=paixu;
            var $this = this;
            var data = {};
            data.eduArea=$("#eduArea").val();
            data.companyName=$("#companyName").val();
            data.startTime=$("#startTime").val();
            data.endTime=$("#endTime").val();
            data.page = page ? page : 1;
            data.pageSize=$("#selectCounts").val() || 10;
            data.paixu=paixu;
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
            //代理机构权限
            $.ajax({
                    url: rootPath + "/berkeley/queryCompanyList",
                    data: data,
                    type: 'post',
                    beforeSend: function (XMLHttpRequest) {
                        $(".loading").show();
                        $(".loading-bg").show();
                    },
                    success: function (jsonData) {
                        if (jsonData.data.length == 0) {
	                          $(".user-list").find("table").append('<tr><td colspan="15">没有查找到数据</td></tr>');
                        }
                        $.each(jsonData.data,function (i, stu) {
                            var eduIdentity = null;
                                $(".user-list")
                                    .find("table")
                                    .append('<tr>'+
                                            '<td>'+((jsonData.pageNo-1)*jsonData.pageSize+i+1)+'</td>'+
                                            '<td>'+stu.eduAreaSchool+'</td>'+
                                            '<td>'+stu.companyName+'</td>'+
                                            '<td>'+stu.eduArea+'</td>'+
                                            '<td>'+stu.registTime+'</td>'+
                                            '<td>'+stu.registStudentCounts+'</td>'+
                                            '<td>'+stu.classTypeCounts+'</td>'+
                                            '<td>'+stu.classCounts+'</td>'+
                                            '<td class="slink">'+
                                                '<a class="showSignUp" mobile="" uname="sdsdsd" href="'+rootPath+'/classManager/getClassInfo/'+stu.id+'">详情</a>|'+
                                                '<a class="studentDetail" mobile="" uname="sdsdsd"  target="_Blank" href="//'+stu.domain+'">查看官网</a>|'+
                                                '<a class="more" href="javascript:void(0);">更多'+
                                                '</a>'+
                                                '<ul class="none box" style="display: none;">'+
                                                    '<li><a class=""  href="'+rootPath+'/berkeley/berkeleyOrder/'+stu.id+'">订单查询</a></li>'+
                                                    '<li><a class=""  href="'+rootPath+'/berkeley/permissionManagement/'+stu.id+'">权限管理</a></li>'+
                                                    '<li><a class=""  href="'+rootPath+'/classTypeManage/queryClassType/'+stu.id+'">课程管理</a></li>'+
                                                    '<li><a class=""  href="'+rootPath+'/serviceManager/getServiceManager/'+stu.id+'">服务管理</a></li>'+
                                                    '<li><a class=""  href="'+rootPath+'/teacherManger/getFirstItems/'+stu.id+'">老师管理</a></li>'+
                                                    '<li><a class=""  href="'+rootPath+'/classQuery/getClassList/'+stu.id+'">分校课程</a></li>'+
                                                    '<li><a class=""  href="'+rootPath+'/computingResource/getVideoResourceAndMessageStatistics/'+stu.id+'">计算资源</a></li>'+
                                                '</ul>'+
                                            '</td>'+
                                        '</tr>');
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
                                        $this.search(pageNo,recordPaixu);
                                    }
                                });
                            $(".pagination").find("li:first").css("background-color","#fff").css("border","1px solid #999").css('cursor','default');
                            $(".pagination").find("li:first").before('每页：<select id="selectCount"  onchange="javascript:company.searchCount()">'+
                					' <option value="10">10</option>'+
                					' <option value="20">20</option>'+
                					' <option value="30">30</option>'+
                					' <option value="50">50</option>'+
                					' <option value="100">100</option>'+
                					' </select> 条   ');
                            $("#selectCount").val($("#selectCounts").val());
                        } else {
                            $(".pagination").html('');
                        }
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        $(".loading").hide();
                        $(".loading-bg").hide();
                    }
                });
            $("#maxCount").remove();
        }
    }

    $(document).ready(function () {
    	company.init();
    })
    window.company=company;
})(jQuery)

function addBerkeley(biaoshi){
    	if(biaoshi==0){
    		var branchCode=$("#branchCode").val();
    		if(null==branchCode || ''==branchCode){
    			alert("分校机构代码不能为空");
    			return;
    		}else{
    			var data={};
    	    	data.brachCode=branchCode;
    			   $.ajax({
    	               url: rootPath + "/berkeley/queryCompanyVo",
    	               data: data,
    	               type: 'post',
    	               beforeSend: function (XMLHttpRequest) {
    	               },
    	               success: function (jsonData) {
    	               	if(jsonData==null || jsonData==''){
    	               		alert("输入错误");
    	               		$("#branchCode").val("");
    	               		$("#branchSchool").text("");
    	               		$("#eara").text("");
    	               		return;
    	               	}
    	               	var companyName=jsonData.companyName;
    	               	var eduArea=jsonData.eduArea;
    	               	var dictCode=jsonData.dictCode;
    	               	$('#branchSchool').text(companyName);
    	               	$('#eara').text(eduArea);
    	               	$('#isArea').val(dictCode);
    	               	/*if(companyName==eduArea){
                    		var options = document.getElementById("schoolProperty");
                        	options[0].selected=true;
                        	document.getElementById("schoolProperties").disabled=true;	
                    	}else{
                    		document.getElementById("schoolProperties").disabled=false;	
                    	}*/
    	               	var branchSchool=$("#branchSchool").text();var branchCode=$("#branchCode").val();
    	        		var isArea=$("#isArea").val();
    	        		var eara=$("#eara").text();
    	        		var schoolProperties=document.getElementById("schoolProperty").innerHTML;
    	        		/*if(branchSchool!=eara){
    	    				if(null== schoolProperties || ''==schoolProperties){
    	            			alert("学校性质不能为空");
    	            			return;
    	            		}
    	    			}*/
    	        		var linkPerson=$("#linkPerson").val();
    	        		/*if(null==linkPerson || ''==linkPerson){
    	        			alert("联系人不能为空");
    	        			return;
    	        		}*/
    	        		var linkPhone=$("#linkPhone").val();
    	        		/*if(null==linkPhone || ''==linkPhone){
    	        			alert("联系方式不能为空");
    	        			return;
    	        		}else*/ if(linkPhone.length!=11){
                            alert("联系方式不是11位有效电话");
                            return;
						}else if(!(/^1[34578]\d{9}$/.test(linkPhone))){
                            alert("手机号码有误，请重填");
                            return ;
                        }
                           var domain=$("#domain").val();
    	        		domain=domain.replace(/(^\s+)|(\s+$)/g,"");
    	        		domain = domain.replace(/\s/g,"");
    	        		if(null!=domain && ''!=domain){
    	        			var data={};
    	        	    	data.domain=domain+'.cdds365.com';
    	        			$.ajax({
    	        	               url: rootPath + "/berkeley/checkDomain",
    	        	               data: data,
    	        	               type: 'post',
    	        	               beforeSend: function (XMLHttpRequest) {
    	        	               },
    	        	               success: function (data) {
    	        	            	   if(data.msg=="success"){
    	        	            		   domain=domain+'.cdds365.com';
    	        	            		   var domainManage=$("#domainManage").val();
    	        	            			domainManage=domainManage.replace(/(^\s+)|(\s+$)/g,"");
    	        	            			domainManage = domainManage.replace(/\s/g,"");
	        	            				if(null!=domainManage && ''!= domainManage){
	        	            					var data={};
	        	            			    	data.domainManage=domainManage+'.cdds365.manage.com';
	        	            					$.ajax({
	        	            			               url: rootPath + "/berkeley/checkDomain",
	        	            			               data: data,
	        	            			               type: 'post',
	        	            			               beforeSend: function (XMLHttpRequest) {
	        	            			               },
	        	            			               success: function (data) {
	        	            			            	   if(data.msg=="success"){
	        	            			            		   domainManage=domainManage+'.cdds365.manage.com';
	        	            			            		   var privateCost=$("#privateCost").val();
        	            			            				if(null==privateCost || ''==privateCost){
        	            			            					alert("学校私有课程收费比例不能为空");
        	            			            					return;
        	            			            				}
	        	            			            			var publicCost=$("#publicCost").val();
        	            			            				if(null==publicCost || ''==publicCost){
        	            			            					alert("学校开放课程收费比例不能为空");
        	            			            					return;
        	            			            				}
	        	            			            			var flowSize=$("#flowSize").val();
        	            			            				if(null==flowSize || ''==flowSize){
        	            			            					alert("流量不能为空");
        	            			            					return;
        	            			            				}
	        	            			            			var spaceSize=$("#spaceSize").val();
        	            			            				if(null==spaceSize || ''==spaceSize){
        	            			            					alert("空间不能为空");
        	            			            					return;
        	            			            				}
	        	            			            			var ccUserName=$("#ccUserName").val();
        	            			            				if(null==ccUserName || ''==ccUserName){
        	            			            					alert("cc账号不能为空");
        	            			            					return;
        	            			            				}
	        	            			            			var ccPwd=$("#ccPwd").val();
        	            			            				if(null==ccPwd || ''==ccPwd){
        	            			            					alert("cc密码不能为空");
        	            			            					return;
        	            			            				}
	        	            			            			var zsUserName=$("#zsUserName").val();
        	            			            				if(null==zsUserName || ''==zsUserName){
        	            			            					alert("展视互动账号不能为空");
        	            			            					return;
        	            			            				}
	        	            			            			var zsPwd=$("#zsPwd").val();
        	            			            				if(null==zsPwd || ''==zsPwd){
        	            			            					alert("展视互动密码不能为空");
        	            			            					return;
        	            			            				}
	        	            			            			var schoolSummary=$("#schoolSummary").val();
	        	            			            			var synopsis = schoolSummary.replace(/(^\s+)|(\s+$)/g,"");
	        	            			            			var synopsisNew = synopsis.replace(/\s/g,"");
        	            			            				if(null==schoolSummary || ''==schoolSummary || null==synopsisNew || ''==synopsisNew){
        	            			            					alert("分校简介不能为空");
        	            			            					return;
        	            			            				}else if (synopsis.length>200){
        	            			            					alert("分校简介不能超过200个字符");
        	            			            					return;
																}else{
        	            			            					schoolSummary=schoolSummary.replace(/(^\s+)|(\s+$)/g,"");
        	            			            					/*schoolSummary = schoolSummary.replace(/\s/g,"");*/
        	            			            				}
                                                               $.confirm("保存后，部分属性无法修改，是否继续？",function (r) {
        	            			            				if(r){
                                                                    $.ajax({
                                                                        type: 'post',
                                                                        url: rootPath + '/berkeley/addBerkeley',
                                                                        data: {
                                                                            branchCode: branchCode,
                                                                            isArea: isArea,
                                                                            branchSchool: branchSchool,
                                                                            eara: eara,
                                                                            schoolProperties: schoolProperties,
                                                                            linkPerson: linkPerson,
                                                                            linkPhone: linkPhone,
                                                                            domain: domain,
                                                                            domainManage: domainManage,
                                                                            privateCost: privateCost,
                                                                            publicCost: publicCost,
                                                                            flowSize: flowSize,
                                                                            spaceSize: spaceSize,
                                                                            ccUserName: ccUserName,
                                                                            ccPwd: ccPwd,
                                                                            zsUserName: zsUserName,
                                                                            zsPwd: zsPwd,
                                                                            schoolSummary: schoolSummary
                                                                        },
                                                                        success: function (data) {
                                                                            if (data.msg == "success") {
                                                                                alert("保存成功");
                                                                                $('#privateCost').css('text-align', 'right');
                                                                                $('#publicCost').css('text-align', 'right');
                                                                                $('.popupContainer').hide();
                                                                                $('.popupOpacity').hide();
                                                                                window.location.href = rootPath + "/berkeley/berkeleyIndex";
                                                                            } else {
                                                                                alert("保存失败");
                                                                            }
                                                                    }
                                                                    });
        	            			            				} else{
        	            			            					return;
																}
                                                               });
	        	            			        			}
	        	            			        			else{
	        	            			        				alert("分校后台域名重复");
	        	            			        				$("#domainManage").val('');
	        	            			        				return;
	        	            			   	        		}
	        	            			               },
	        	            			              
	        	            			           })
	        	            					
	        	            				}else{
	        	            					alert("分校后台域名不能为空");
	        	            					return;
	        	            				}
    	        	            		   
    	           	        			}else{
    	           	        				alert("分校域名重复");
    	           	        				$("#domain").val('');
    	           	        				return;
    	        	   	        		}
    	        	               },
    	        	              
    	        	           });
    	        		}else{
    	        			alert("分校域名不能为空");
    	        			return;
    	        		}
	               },
	           });
    		}
    	}
    	if(biaoshi==1){
			var linkPerson=$("#linkPerson").val();
			if(null==linkPerson || ''==linkPerson){
				alert("联系人不能为空");
				return;
			}
    		var linkPhone=$("#linkPhone").val();
    		if(null==linkPhone || ''==linkPhone){
    			alert("联系方式不能为空");
    			return;
    		}
		   var privateCost=$("#privateCost").val();
		   if(null==privateCost || ''==privateCost){
				alert("学校私有课程收费比例不能为空");
				return;
		   }	
		   var publicCost=$("#publicCost").val();
		   if(null==publicCost || ''==publicCost){
			   	alert("学校开放课程收费比例不能为空");
				return;
		   }
		   var flowSize=$("#flowSize").val();
		   if(null==flowSize || ''==flowSize){
				alert("流量不能为空");
				return;
		   }	
		   var spaceSize=$("#spaceSize").val();
		   if(null==spaceSize || ''==spaceSize){
				alert("空间不能为空");
				return;
		   }	
		   var ccUserName=$("#ccUserName").val();
		   if(null==ccUserName || ''==ccUserName){
				alert("cc账号不能为空");
				return;
		   }
		   var ccPwd=$("#ccPwd").val();
		   if(null==ccPwd || ''==ccPwd){
				alert("cc密码不能为空");
				return;
		   }
		   var zsUserName=$("#zsUserName").val();
		   if(null==zsUserName || ''==zsUserName){
				alert("展视互动账号不能为空");
				return;
		   }
		   var zsPwd=$("#zsPwd").val();
		   if(null==zsPwd || ''==zsPwd){
				alert("展视互动密码不能为空");
				return;
		   }
		   var schoolSummary=$("#schoolSummary").val();
		   if(null==schoolSummary || ''==schoolSummary){
				alert("学校简介不能为空");
				return;
		   }else{
				schoolSummary=schoolSummary.replace(/(^\s+)|(\s+$)/g,"");
				schoolSummary = schoolSummary.replace(/\s/g,"");
		   }
   			var companyId=$("#companyId").val();
			$.ajax({
	   	        type : 'post',
	   	        url : rootPath + '/classManager/editBerkeley',
	   	        data : {
	   	        	id : companyId,
	   	        	linkPerson : linkPerson,
	   	        	linkPhone : linkPhone,
	   	        	privateCost : privateCost,
	   	        	publicCost : publicCost,
	   	        	flowSize : flowSize,
	   	        	spaceSize : spaceSize,
	   	        	ccUserName : ccUserName,
	   	        	ccPwd : ccPwd,
	   	        	zsUserName : zsUserName,
	   	        	zsPwd : zsPwd,
	   	        	schoolSummary : schoolSummary
	   	        },
	   	        success : function(data){
	   	        	if(data.msg=="success"){
	   	        		alert("保存成功");
	   	        		window.location.href = rootPath+"/classManager/getClassInfo/"+companyId;
	   	        	}else{
	   	        		alert("保存失败");
	   	        	}
	   	        }
	   	    });
    	}
    		
	}
