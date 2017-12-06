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
                window.location.href = rootPath+"/serviceManager/getServiceManager?companyId=18113";
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
            $("#searchBranchSchool").on('click', function () {
                $this.searchBranchSchool();
            });
            //全选 取消全选
            $(".checkboxAll").on('change', function () {
                if ($(this).prop("checked")) {
                    $("#tableList").find(".signUpMany").prop("checked", true);
                } else {
                    $("#tableList").find(".signUpMany").prop("checked", false);
                }
            });
        },
        searchCount: function(){
        	$("#selectCounts").val($("#selectCount").val());
        	$this.search(1);
        },
        searchBranchSchool:function(brachCode){
        	if(brachCode=="") $.msg("机构代码不能为空！");
        	var data={};
        	data.brachCode=brachCode;
            $.ajax({
                    url: rootPath + "/berkeley/queryCompanyList",
                    data: data,
                    type: 'post',
                    beforeSend: function (XMLHttpRequest) {
                        $(".loading").show();
                        $(".loading-bg").show();
                    },
                    success: function (jsonData) {

                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        $(".loading").hide();
                        $(".loading-bg").hide();
                    }
                });
        },
        search: function (page) {
            var $this = this;
            var data = {};
            data.eduArea=$("#eduArea").val();
            data.schoolName=$("#schoolName").val();
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
                                            '<td>'+(i++)+'</td>'+
                                            '<td>'+stu.eduAreaSchool+'</td>'+
                                            '<td>'+stu.companyName+'</td>'+
                                            '<td>'+stu.eduArea+'</td>'+
                                            '<td>'+stu.registTime+'</td>'+
                                            '<td>'+stu.registStudentCounts+'</td>'+
                                            '<td>'+stu.classTypeCounts+'</td>'+
                                            '<td>'+stu.classCounts+'</td>'+
                                            '<td class="slink">'+
                                                '<a class="showSignUp" mobile="" uname="sdsdsd" href="'+stu.companyId+'">详情</a>|'+
                                                '<a class="studentDetail" mobile="" uname="sdsdsd" href="'+stu.domain+'">查看官网</a>|'+
                                                '<a class="more" href="javascript:void(0);">更多'+
                                                '</a>'+
                                                '<ul class="none box" style="display: none;">'+
                                                    '<li><a class=""  href="javascript:void(0);">订单查询</a></li>'+
                                                    '<li><a class=""  href="javascript:void(0);">权限管理</a></li>'+
                                                    '<li><a class=""  href="javascript:void(0);">课程管理</a></li>'+
                                                    '<li><a class=""  href="javascript:void(0);">服务管理</a></li>'+
                                                    '<li><a class=""  href="javascript:void(0);">老师管理</a></li>'+
                                                    '<li><a class=""  href="javascript:void(0);">分校课程</a></li>'+
                                                    '<li><a class=""  href="javascript:void(0);">计算资源</a></li>'+
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
    	company.search(1);
    })
    window.company=company;
})(jQuery)
