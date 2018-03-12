$(document).ready(function () {
    var myDate = new Date();
    console.log(myDate.getFullYear());
    masterFindClassStu(0,myDate.getFullYear());
})

function masterFindClassStu(page,year) {
    $.ajax({
        url: rootPath + "/query/learningDetails/queryStudentsList",
        data: {
            "page": page,
            "pageSize":$("#selectCounts").val() || 10,
            "eduSchool":$("#eduSchool").val(),
            "eduStep" : $('#eduStepM').val(),
            "eduYear" : $('#eduYearM').val() || year,
            "eduClass" : $('#eduClassM').val(),
            "liveFlag":$('#liveFlag').val(),
            "subject":$('#subject').val()
        },
        type: 'post',
        beforeSend: function (XMLHttpRequest) {
            $(".loading").show();
            $(".loading-bg").show();
        },
        success: function (jsonData) {
            console.log(jsonData);
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
                $('#paginationStuList').hide();
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
                eduStep='小';
            }else if(eduStep=='STEP_02'){
                eduStep='初';
            }else{
                eduStep='高';
            }

            var stuHtml = "";
            $.each(jsonData.pageFinder.data,function (i, stu) {
                stuHtml += "<tr>";
                stuHtml += '<td>'+(stu.name ? stu.name : "") + '</td>';
                stuHtml += '<td>'+  eduStep+stu.eduYear+"年"+stu.eduClass+"班"+ '</td>';
                stuHtml += '<td>'+ (stu.countClass!=null ? stu.countClass: "0") +'</td>';
                stuHtml += '<td>'+ (stu.studyTime!=null ? stu.studyTime : "0") +'</td>';
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
                headHtml += '<th>'+(clas.lessonName ? clas.lessonName: "") + "</th>";
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
                $.each(cla.studyFlag ,function (i,study) {
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


            //分页
            $("#paginationStuList").pagination(jsonData.pageFinder.rowCount,
                {
                    next_text: "下一页",
                    prev_text: "上一页",
                    current_page: jsonData.pageFinder.pageNo - 1,
                    link_to: "javascript:void(0)",
                    num_display_entries: 8,
                    items_per_page: jsonData.pageFinder.pageSize,
                    num_edge_entries: 1,
                    callback: function (page, jq) {
                        var pageNo = page + 1;
                        masterFindClassStu(pageNo);
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


            //根据列表的高度设置切换按钮的高度
            var tableHeight = ($('.tableFirst').height()-35)+'px';
            $('.changeIcon').css('height',tableHeight).css('line-height',tableHeight).css('margin-top','75px');
            // $('.classNo').css('height',($('.tableFirst').height()-2)+'px');
        }
    });
}