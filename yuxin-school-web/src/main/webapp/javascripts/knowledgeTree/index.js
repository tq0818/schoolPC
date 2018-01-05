(function ($) {

    var knowledge = {
        init: function () {
            $("#eduStep").change(function(){
                $.ajax({
                    url: rootPath + "/sysKnowledgeTree/findItemSecondCode",
                    type: "post",
                    data:{eduStep:$("#eduStep").val()},
                    success: function (data) {
                        var options = '';
                        $.each(data,function(i,j){
                            options+='<option value="'+j.itemCode+'">'+j.itemName+'</option>';
                        });
                        $("#itemSecondCode").html("").append(options);
                        $("#itemSecondCode").change();
                    }
                });
            });
            $("#eduStep").change();
            $("#itemSecondCode").change(function(){
                $.ajax({
                    url: rootPath + "/sysKnowledgeTree/findItemThreeCode",
                    type: "post",
                    data:{parentCode:$("#itemSecondCode").val()},
                    success: function (data) {
                        var options = '';
                        $.each(data,function(i,j){
                            options+='<option value="'+j.itemCode+'">'+j.itemName+'</option>';
                        });
                        $("#itemThreeCode").html("").append(options);
                    }
                });
            });

            $("#edit_eduIdentity_normal").click(function(){
                //$("#edit_div_school").hide();
                $("#editEduSchool").hide();
                $("#editEduSchool").parent().prev().hide();
                $("#edit_div_class").hide();
            });

            var $this = this;
            // 初始化数据
            $this.search();
            // 收索
            $(".searchContents").on('click', function () {
                $this.search();
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
            data.isStu ="1";
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
    }

    $(document).ready(function () {
        knowledge.init();
    })
    window.knowledge = knowledge;
})(jQuery)
