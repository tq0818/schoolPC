var pageId =0;
$(function () {
    var myDate = new Date();
    //学员审核
    //pageId = 0
    studentReview(pageId,myDate.getFullYear());

    //点击选择全部
    $('#checkBoxList').change(function () {
        if($(this).prop('checked')){
            $('.checkbox').prop('checked',true);
        }else {
            $('.checkbox').prop('checked',false);
        }
    });
    //点击批量审核
    $('.batchAudit').click(function () {
        //判断checkbox是否被选中
        var checkNum = 0;
        var checkListArr = [];
        var checkLisrIdArr = [];
        for(var i = 0 ;i < $('.checkbox').length;i++){
            if($('.checkbox').eq(i).prop('checked')){
                checkNum++;
                checkListArr.push(i);
                checkLisrIdArr.push($('.checkbox').eq(i).val());
            }
        }
        //如果没有被选中的checkbox则弹窗提示请选择学生！
        if(!checkNum){
            $.msg('请选择学生！');
        }else {
            //弹窗再次确认 是否认证所选择学生？
            $.confirm('是否认证所选择学生？',function (data) {
                if(data){
                    console.log("点击了是");
                    //认证成功后，所选择学生已认证为本校用户！
                    $.msg("所选择学生已认证为本校用户！");
                    //认证通过，删除该条学生数据
                    if(checkListArr.length>0){
                        for(var i= checkListArr.length;i > 0;i--){
                            $('.dataLine').eq(checkListArr[i-1]).remove();
                        }
                        //点击批量审核调用接口
                        review(checkLisrIdArr.join(','),0);
                    }

                }else{
                    console.log("点击了否");
                }
            })
        }
    });
    //用户点击通过按钮
    $('body').on('click','.passBtn',function () {
        var that  = $(this);
        $.confirm('是否认证所选择学生？',function (data) {
            if(data){
                review(that.attr('data-id'),1);
                //认证通过，删除该条学生数据
                // that.parent().parent('tr').remove();
            }else{
                console.log("点击了否");
            }
        })
    });



    //加载中
    // $('.loading').show();
    // $('.loading-bg').show();

    //头部切换
    $('.heading').children('h2').click(function () {
        $(this).addClass('active');
        $(this).siblings('h2').removeClass('active');
        if($(this).index()==0){
            $('.studentListAll').show();
            $('.studentReviewContent').hide();
        }else if($(this).index()==2){
            $('.studentListAll').hide();
            $('.studentReviewContent').show();
        }

    });

    //时间先后顺序判断
    $('#searchAll').click(function () {

        var beginDate= $('#starTime').val();
        var endDate=$('#endTime').val();

        function tab(date1,date2){
            var oDate1 = new Date(date1);
            var oDate2 = new Date(date2);
            if(oDate1.getTime() > oDate2.getTime()){
                $.msg('开始时间不能大于结束时间');
            } else {
                studentReview(0);
            }
        }
        tab(beginDate,endDate);

    });



});

function studentReview(pageNum){
    var roleType = $("#roleType").val();
    var eduSchool = $("#eduSchoolCode").val();
    var eduStep;
    var eduYear;
    var eduClass;
    var pageSize=$("#selectCountSchool").val();
    if(roleType == 1){
        eduStep = $("#eduStepMaster").val();
        eduYear = $("#eduYearMaster").val();
        eduClass = $("#eduClassMaster").val();
    }else{
        eduStep = $('#eduStep3').val();
        eduYear = $("#eduYear3").val();
        eduClass = $("#eduClass3").val();
    }
    console.log("BB:"+$("#startTime").val());
    console.log("AA:"+$("#endTime").val());
    $.ajax({
        url : rootPath + "/query/learningDetails/studentReview",
        data : {
            "name":$("#name").val(),
            "mobile":$("#mobile").val(),
            "startTime":$("#starTime").val(),
            "endTime":$("#endTime").val(),
            "page" : pageNum,
            "pageSize" : $("#selectCounts").val() || 10,
            "eduSchool" : eduSchool,
            "eduStep" : eduStep,
            "eduYear" : eduYear,
            "eduClass" : eduClass,
            "pageSize": pageSize
        },
        type : 'post',
        beforeSend : function(XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success : function(jsonData) {
            /*if(jsonData.data.length <=2){
                $("#studentReview").html("");
            }*/
            var html ="";
            $.each(jsonData.data, function(i,stu){
                html+="<tr class='dataLine'>";
                html+="<td ><input type='checkbox' class='checkbox' value="+stu.id+"></td>";
                html+="<td>"+stu.mobile+"</td>";
                html+="<td>"+stu.username+"</td>";
                html+="<td>"+stu.name+"</td>";
                if(stu.eduStep == 'STEP_01'){
                    html+="<td>小学</td>";
                }else if(stu.eduStep == 'STEP_02'){
                    html+="<td>初中中学</td>";
                }else{
                    html+="<td>高中中学</td>";
                }
                html+="<td>"+stu.eduYear+'年'+stu.eduClass+'班'+"</td>";
                html+="<td>"+stu.createTime+"</td>";
                html+="<td><a href='javascript:void(0);' class='btn btn-mb btn-primary passBtn' data-id="+stu.id+">通过</a></td>";
            })
            $("#review").html(html);
            pageId = jsonData.pageNo;
            // 分页
            if(pageId ==0){
                if(jsonData.rowCount>10){
                    $("#studentReview").pagination(jsonData.rowCount, {
                        next_text : "下一页",
                        prev_text : "上一页",
                        current_page : jsonData.pageNo-1,
                        link_to : "javascript:void(0)",
                        num_display_entries : 8,
                        items_per_page : jsonData.pageSize,
                        num_edge_entries : 1,
                        callback:function(page,jq){
                            var pageNo = page + 1;
                            studentReview(pageNo);
                        }
                    });
                    $("#studentReview").find("li:first").css("background-color","#fff").css("border","1px solid #999").css('cursor','default');
                    $("#studentReview").find("li:first").before('每页：<select id="selectCountSl"  onchange="javascript:changePageSize()">'+
                        ' <option value="10">10</option>'+
                        ' <option value="20">20</option>'+
                        ' <option value="30">30</option>'+
                        ' <option value="50">50</option>'+
                        ' <option value="100">100</option>'+
                        ' </select> 条   ');
                    $("#selectCountSl").val($("#selectCountSchool").val());
                }else{
                    $("#studentReview").html('');
                }
            }else{
                if(jsonData.rowCount>10){
                    $("#studentReview").pagination(jsonData.rowCount, {
                        next_text : "下一页",
                        prev_text : "上一页",
                        current_page : jsonData.pageNo-1,
                        link_to : "javascript:void(0)",
                        num_display_entries : 8,
                        items_per_page : jsonData.pageSize,
                        num_edge_entries : 1,
                        callback:function(page,jq){
                            console.log('page = '+page)
                            var pageNo = page + 1;
                            studentReview(pageNo);
                        }
                    });
                    $("#studentReview").find("li:first").css("background-color","#fff").css("border","1px solid #999").css('cursor','default');
                    $("#studentReview").find("li:first").before('每页：<select id="selectCountSl"  onchange="javascript:changePageSize()">'+
                        ' <option value="10">10</option>'+
                        ' <option value="20">20</option>'+
                        ' <option value="30">30</option>'+
                        ' <option value="50">50</option>'+
                        ' <option value="100">100</option>'+
                        ' </select> 条   ');
                    $("#selectCountSl").val($("#selectCountSchool").val());
                }else{
                    $("#studentReview").html('');
                }
            }
        },
        complete : function(XMLHttpRequest, textStatus) {
            $(".loading").hide();
            $(".loading-bg").hide();
        }
    });
}

function review(stuId,flag){
    if(flag == 1){
        $.ajax({
            url : rootPath + "/query/learningDetails/reviewPass",
            data : {
                "stuId":stuId,
                "flag":1
            },
            type : 'post',
            beforeSend : function(XMLHttpRequest) {
                $(".loading").show();
                $(".loading-bg").show();
            },
            success : function(jsonData) {
                var myDate = new Date();
                //学员审核
                studentReview(pageId,myDate.getFullYear());
            },
            complete : function(XMLHttpRequest, textStatus) {
                $(".loading").hide();
                $(".loading-bg").hide();
            }
        });
    }else{
        $.ajax({
            url : rootPath + "/query/learningDetails/reviewPass",
            data : {
                "stuId":stuId,
                "flag":0
            },
            type : 'post',
            beforeSend : function(XMLHttpRequest) {
                $(".loading").show();
                $(".loading-bg").show();
            },
            success : function(data) {
                var myDate = new Date();
                studentReview(pageId,myDate.getFullYear());
            },
            complete : function(XMLHttpRequest, textStatus) {
                $(".loading").hide();
                $(".loading-bg").hide();
            }
        });
    }
}
function changePageSize(){
    $("#selectCountSchool").val($("#selectCountSl").val());
    studentReview(0);
}