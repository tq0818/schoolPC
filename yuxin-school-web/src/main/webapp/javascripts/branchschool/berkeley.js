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
            $this.search(1);
            //搜索
            $(".searchContents").on('click', function () {
                $this.search(1);
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
                    		/*$.msg('输入错误');*/
                    		return;
                    	}
                    	var companyName=jsonData.companyName;
                    	var eduArea=jsonData.eduArea;
                    	var dictCode=jsonData.dictCode;
                    	$('#branchSchool').text(companyName);
                    	$('#eara').text(eduArea);
                    	$('#isArea').val(dictCode);
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
        	$this.search(1);
        },
        search: function (page) {
            var $this = this;
            var data = {};
            data.eduArea=$("#eduArea").val();
            data.companyName=$("#companyName").val();
            data.startTime=$("#startTime").val();
            data.endTime=$("#endTime").val();
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
                                            '<td>'+(1+i++)+'</td>'+
                                            '<td>'+stu.eduAreaSchool+'</td>'+
                                            '<td>'+stu.companyName+'</td>'+
                                            '<td>'+stu.eduArea+'</td>'+
                                            '<td>'+stu.registTime+'</td>'+
                                            '<td>'+stu.registStudentCounts+'</td>'+
                                            '<td>'+stu.classTypeCounts+'</td>'+
                                            '<td>'+stu.classCounts+'</td>'+
                                            '<td class="slink">'+
                                                '<a class="showSignUp" mobile="" uname="sdsdsd" href="'+rootPath+'/classManager/getClassInfo/'+stu.id+'">详情</a>|'+
                                                '<a class="studentDetail" mobile="" uname="sdsdsd" href="'+stu.domain+'">查看官网</a>|'+
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
                                        $this.search(pageNo);
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
		
		var branchSchool=$("#branchSchool").text();
		var isArea=$("#isArea").val();
		var eara=$("#eara").text();
		var schoolProperties=$("#schoolProperties").val();
		var linkPerson=$("#linkPerson").val();
		var linkPhone=$("#linkPhone").val();
		var domain=$("#domain").val();
		if(null!=$("#domain").val() && ''!=$("#domain").val()){
			var domain='www.'+$("#domain").val()+'.cdds365.com';
		}
		if(null!=$("#domainManage").val() && ''!=$("#domainManage").val()){
			var domainManage='www.'+$("#domainManage").val()+'.cdds365.manage.com';
		}
		var privateCost=$("#privateCost").val();
		var publicCost=$("#publicCost").val();
		var flowSize=$("#flowSize").val();
		var spaceSize=$("#spaceSize").val();
		var ccUserName=$("#ccUserName").val();
		var ccPwd=$("#ccPwd").val();
		var zsUserName=$("#zsUserName").val();
		var zsPwd=$("#zsPwd").val();
		var schoolSummary=$("#schoolSummary").val();
		if(biaoshi==0){
			var branchCode=$("#branchCode").val();
			if(null==branchCode || ''==branchCode){
				alert("分校机构代码不能为空");
				return;
			}
			$.ajax({
	   	        type : 'post',
	   	        url : rootPath + '/berkeley/addBerkeley',
	   	        data : {
	   	        	branchCode : branchCode,
	   	        	isArea : isArea,
	   	        	branchSchool : branchSchool,
	   	        	eara : eara,
	   	        	schoolProperties : schoolProperties,
	   	        	linkPerson : linkPerson,
	   	        	linkPhone : linkPhone,
	   	        	domain : domain,
	   	        	domainManage : domainManage,
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
	   	        		$('.popupContainer').hide();
	   	        		$('.popupOpacity').hide();
	   	        		window.location.href = rootPath+"/berkeley/berkeleyIndex";
	   	        	}else{
	   	        		alert("保存失败");
	   	        	}
	   	        }
	   	    });
		}else{
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
