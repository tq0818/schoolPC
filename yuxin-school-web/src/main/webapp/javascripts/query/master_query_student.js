function masterFindClassStu(page) {
    $.ajax({
        url: rootPath + "/query/learningDetails/queryStudentsList",
        data: {
            "page": page,
            "pageSize":$("#selectCounts").val() || 10,
            "eduSchool":$("#eduSchool").val(),
            "eduStep" : $('#eduStepM').val(),
            "eduYear" : $('#eduYearM').val(),
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
            if (jsonData.pageFinder.data.length == 0) {
                $(".classListContent")
                    .find(".tableFirst")
                    .html('');
                $(".classListContent")
                    .find(".tableSecond")
                    .html('');

                $(".classListContent")
                    .find(".tableFirst")
                    .append(
                        '<tr><td colspan="12">没有查找到数据</td></tr>');
            }
            if(jsonData.classList.length == 0){
                $(".classListContent")
                    .find(".tableSecond")
                    .append(
                        '<tr><td colspan="12">没有查找到数据</td></tr>');
            }

            var eduStep = $('#eduStep2').val();
            if(eduStep=='STEP_01'){
                eduStep='小学';
            }else if(eduStep=='STEP_02'){
                eduStep='初';
            }else{
                eduStep='高';
            }

        },
        complete: function (XMLHttpRequest, textStatus) {
            $(".loading").hide();
            $(".loading-bg").hide();
        }
    });
}