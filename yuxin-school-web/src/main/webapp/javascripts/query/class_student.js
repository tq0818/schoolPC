/*$(document).ready(function () {
    $.ajax({
        url: rootPath + "/query/learningDetails/queryStudentsList",
        data: {
            "page": 1,
            "pageSize":$("#selectCounts").val() || 10,
            "eduSchool":$("#eduSchool").val(),
            "eduStep" : $('#eduStep2').val(),
            "eduYear" : $('#eduYear2').val(),
            "deduClass" : $('#eduClass2').val(),
            " liveFlag":$('#videoFlag').val(),
            "subject":$('#subject').val()
        },
        type: 'post',
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success: function (jsonData) {

            /!*if (jsonData.data.length == 0) {
                if(userorg_roleopenflag == 1 && proxyOrgRole == 1){
                    $(".user-list")
                        .find("table")
                        .append(
                            '<tr><td colspan="12">没有查找到数据</td></tr>');
                }else{
                    $(".user-list")
                        .find("table")
                        .append(
                            '<tr><td colspan="11">没有查找到数据</td></tr>');
                }
            }
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
                $(".user-list")
                    .find("table")
                    .append(
                        '<tr data-buy="'+(stu.paymaterCount>0)+'">'
                        // + '<td>'
                        // + '<input type="checkbox" class="signUpMany" uName="'+(stu.username?stu.username:"")+'" value="' + (stu.mobile?stu.mobile:"") + '">'
                        // + '</td>'
                        // + '<td>'
                        // + (stu.mobile ? stu.mobile
                        //     : "")
                        // + '</td>'
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
                        /!*+ '<td>'
                        + (stu.eduArea ? stu.eduArea
                            : "")
                        + '</td>'*!/
                        /!*+ '<td>'
                        + (stu.eduSchool ?stu.eduSchool
                            : "")
                        + '</td>'*!/
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
                        /!*   + '<td class="haostatus">'
                           + (stu.userId ? '已开通'
                               : '未开通')
                           + '</td>'*!/
                        /!*+ '<td class="ustatus">'
                        + (stu.status == 1 ? '正常'
                            : '异常')
                        + '</td>'*!/
                        /!*  + '<td class="baoming" value="' + stu.ispay + '">'
                          + (stu.paymaterCount > 0 ? '已报名'
                              : '未报名')
                          + '</td>'*!/
                        // + '<td class="slink">'
                        // + '<a class="showSignUp" mobile="' + (stu.mobile?stu.mobile:"") + '" uName="'+(stu.username?stu.username:"")+'" href="javascript:void(0);">报名</a>|'
                        // + '<a class="studentDetail" mobile="' + (stu.mobile?stu.mobile:"") + '" uName="'+(stu.username?stu.username:"")+'" href="javascript:void(0);">详情</a>|'
                        // + '<a class="more" href="javascript:void(0);">更多</a>'
                        // + '<ul class="none box">'
                        // + ' <li><a class="updateStudentMsg" stuId="' + stu.id + '" href="javascript:void(0);">修改信息</a></li>'
                        // +(($("#isDelete").val()==1)?((stu.paymaterCount > 0)?' <li><a class="delStudent" stuId="'+stu.id+'" href="javascript:void(0);">取消报名</a></li>':""):"")
                        // + (stu.userId ? (stu.status == 1 ? '<li><a class="updateStatus" userId="' + stu.userId + '" status="' + stu.status + '" href="javascript:void(0);">禁用用户</a></li>'
                        //     : '<li><a class="updateStatus" userId="' + stu.userId + '" status="' + stu.status + '" href="javascript:void(0);">启用用户</a></li>' ) : '')
                        // + (stu.status == 1 ? '<li><a class="changePwd" userId="' + stu.userId + '" href="javascript:void(0);">修改密码</a></li>' : '')
                        // +(stu.status == 1 && stu.paymaterCount > 0 ? '<li><a class="exportStudyRecord" stuId="'+stu.id+'" href="'+rootPath+'/student_detail/openStdentAllCl?stuId='+stu.id+'" target="_blank">学习记录</a></li>' : '')
                        // +(stu.status == 1 && stu.paymaterCount > 0 ? '<li><a class="exportExcleRecord" stuId="'+stu.id+'" href="'+rootPath+'/student_detail/openStdentAllExt?stuId='+stu.id+'" target="_blank">做题记录</a></li>' : '')
                        // + ((stu.paymaterCount > 0 && stu.commodityType!='COMMODITY_PACKAGE') ? '<li><a class="toTransaction" mobile="' + (stu.mobile?stu.mobile:"") + '" uName="'+(stu.username?stu.username:"")+'" href="javascript:void(0);">异动</a></li>' : '')
                        // + (stu.paymaterCount > 0 ? (stu.ispay == "1" ? '<li><a class="toMessage" mobile="' + (stu.mobile?stu.mobile:"") + '" uName="'+(stu.username?stu.username:"")+'" href="javascript:void(0);">补费</a></li>' : '' ) : '')
                        // + (stu.paymaterCount > 0 ?
                        //     (stu.agentFlag == "1" ?
                        //         (stu.isAgent == "1" ?
                        //         '<li><a class="showStuMaterial" mobile="' +(stu.mobile?stu.mobile:"")+ '" uName="'+(stu.username?stu.username:"")+'" href="javascript:void(0);">报考材料</a></li>'
                        //             : '')
                        //         : '')
                        //     : '')
                        // + '</ul></td>'

                        + '<td>'
                        +'<a class="updateStudentMsg" stuid="'+stu.id+'" href="javascript:void(0);">修改信息</a>'
                        +'<a class="updateStuStatus" stuid="'+stu.id+'" href="javascript:void(0);" onclick="student.removeStatus(this)">&nbsp|&nbsp移除用户&nbsp|&nbsp</a>'
                        +'<a class="changePwd" userid="'+stu.userId+'" href="javascript:void(0);">修改密码</a>'
                        +'</td>'
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
            }*!/
        },
        complete: function (XMLHttpRequest, textStatus) {
            $(".loading").hide();
            $(".loading-bg").hide();
        }
    });
})*/


var nowClass = 0;
var headArr = null;
var bodyArr = null;

function findClassStu(page) {
    $.ajax({
        url: rootPath + "/query/learningDetails/queryStudentsList",
        data: {
            "page": page,
            "pageSize":$("#selectCounts").val() || 10,
            "eduSchool":$("#eduSchool").val(),
            "eduStep" : $('#eduStep2').val(),
            "eduYear" : $('#eduYear2').val(),
            "eduClass" : $('#eduClass2').val(),
            "liveFlag":$('#liveFlag').val(),
            "subject":$('#subject').val()
        },
        type: 'post',
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success: function (jsonData) {
            if (jsonData.pageFinder.data.length == 0) {
                $(".classListContent")
                    .find(".tableFirst")
                    .html('');
                $(".classListContent")
                    .find(".tableSecond")
                    .html('');

                // $(".classListContent")
                //     .find(".tableFirst")
                //     .append(
                //         '<tr><td colspan="12">没有查找到数据</td></tr>');
                $('.studentContent').hide();
                $('.studentNo').show();
            }
            if(jsonData.classList.length == 0){
                // $(".classListContent")
                //     .find(".tableSecond")
                //     .html('<tr><td colspan="12">没有查找到数据</td></tr>');

                //无课程数据时，显示默认提示
                $('.tableSecond').hide();
                $('.leftIcon').hide();
                $('.rightIcon').hide();
                $('.classNo').show();


            }

            var eduStep = $('#eduStep2').val();
            if(eduStep=='STEP_01'){
                eduStep='小学';
            }else if(eduStep=='STEP_02'){
                eduStep='初';
            }else{
                eduStep='高';
            }
            
            var stuHtml = "";
            $.each(jsonData.pageFinder.data,function (i, stu) {
            	stuHtml += "<tr>";
            	stuHtml += '<td>'+(stu.info.name ? stu.info.name : "") + '</td>';
            	stuHtml += '<td>'+  eduStep+stu.info.eduYear+"年"+stu.info.eduClass+"班"+ '</td>';
            	stuHtml += '<td>'+ (stu.info.countClass!=null ? stu.info.countClass: "0") +'</td>';
            	stuHtml += '<td>'+ (stu.info.studyTime!=null ? stu.info.studyTime : "0") +'</td>';
            	stuHtml += "</tr>";
            })
            $("#stuListTbody").html(stuHtml);
            
            
            
            //组装课程head
            var headHtml = "";
            headArr = new Array();
            $.each(jsonData.classList,function (i,clas) {
            	if(i % 5 == 0 && i > 0){
            		headArr.push(headHtml);
            		headHtml = "";
            	}
            	headHtml += '<th>'+(clas.lesson_name ? clas.lesson_name: "") + "</th>";
            })
            if(headHtml != ''){
            	headArr.push(headHtml);
            }
            $("#className").html(headArr[0]);
            nowClass = 0;
            
            
            if(jsonData.classList.length == 0){
            	//没有课程学习记录，清空table
            	$(".classListContent").find(".tableSecond").html('')
            	return;
            }
            
            var classHtml = "";
            bodyArr = new Array();
            for(var i in headArr){
            	bodyArr.push("");
            }
            $.each(jsonData.pageFinder.data,function (i,cla) {
            	var tempHtml = "";
                $.each(cla.list ,function (i,study) {
                	if(i % 5 == 0 && i > 0){
                		bodyArr[parseInt(i/5) - 1] += "<tr>"+tempHtml+"</tr>";
                		tempHtml = "";
                	}
                	tempHtml += '<td>' +(study == 0 ? '✘' : '√')+'</td>';
                })
                if(tempHtml != ''){
                	bodyArr[ headArr.length - 1] += "<tr>"+tempHtml+"</tr>";
                }
                
            })
            
           $("#classListTbody").html(bodyArr[0]);
            
          /* $(".changeIcon").css("margin-top",$("#className").height()+'px');
           $('.changeIcon').height($("#classListTbody").height());*/

            var tableHeight = ($('.tableFirst').height()-35)+'px';
            $('.changeIcon').css('height',tableHeight).css('line-height',tableHeight).css('margin-top','105px');
            console.log(tableHeight);


            //分页
            $("#paginationStuList").pagination(jsonData.pageFinder.count,
                    {
                        next_text: "下一页",
                        prev_text: "上一页",
                        current_page: jsonData.pageFinder.page,
                        link_to: "javascript:void(0)",
                        num_display_entries: 8,
                        items_per_page: jsonData.pageFinder.size,
                        num_edge_entries: 1,
                        callback: function (page, jq) {
                            var pageNo = page + 1;
                            findClassStu(pageNo);
                        }
                    });
            
            
            $("#leftIconBtn").click(function(){
            	if(nowClass == 0){

            		return;
            	}
            	//隐藏左侧icon
            	if(nowClass == 1||nowClass == 0){
                    $(".leftIcon").hide();
                }else {
                    $(".leftIcon").show();
                }
                $(".rightIcon").show();
            	
            	nowClass --;
            	$("#className").html(headArr[nowClass]);
            	$("#classListTbody").html(bodyArr[nowClass]);
            	
            })
            
             $("#rightIconBtn").click(function(){
            	if(nowClass == headArr.length - 1){
            		return;
            	}
                 //隐藏右侧icon

                 if(nowClass == headArr.length - 2){
                     $(".rightIcon").hide();
                 }
                 $(".leftIcon").show();
            	
            	nowClass ++;
            	$("#className").html(headArr[nowClass]);
            	$("#classListTbody").html(bodyArr[nowClass]);
            	
            })
            
            
        },
        complete: function (XMLHttpRequest, textStatus) {
            $(".loading").hide();
            $(".loading-bg").hide();
        }
    });
}

