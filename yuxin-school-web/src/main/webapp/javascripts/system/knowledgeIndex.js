(function ($) {
    var isFirst = true;
    var idsData = "";
    var knowledge = {
        init: function () {
            $("#itemSecondCode").change(function(){
                $.ajax({
                    url: rootPath + "/sysKnowledgeTree/findItemThreeCode",
                    type: "post",
                    async:false,
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
            $("#eduStep").change(function(){
                $.ajax({
                    url: rootPath + "/sysKnowledgeTree/findItemSecondCode",
                    type: "post",
                    async:false,
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

            var $this = this;
            // 初始化数据
            $this.search();
            // 查询
            $(".searchContents").on('click', function () {
                isFirst = true;
                $this.search();
            });
            // 清空知识树
            $(".removeKnowledge").on('click', function () {
                $.confirm('确定清空知识树吗？',function(a){
                    if(a){
                        $(".checkboxAll").prop("checked", false);
                        isFirst = true;
                        $this.removeKnowledge();
                    }
                });
            });
            // 预览知识树
            $(".viewKnowledge").on('click', function () {
                $this.viewKnowledge();
                $(".knowledgePopup").popup("show");
            });
            // 生成知识树
            $(".buildKnowledge").on('click', function () {
                var data = {};
                data.eduYear = $("#eduYear").val();
                data.eduSeason=$("#eduSeason").val();
                data.eduStep=$("#eduStep").val();
                data.itemSecondCode = $("#itemSecondCode").val();
                data.itemThreeCode = $("#itemThreeCode").val();
                data.idstr = idsData;
                if(idsData == ''){
                    $.alert("请先选择对应课程！");
                    return;
                }
                $.ajax({
                    url: rootPath + "/sysKnowledgeTree/addKnowledgeTree",
                    data: data,
                    type: 'post',
                    beforeSend: function (XMLHttpRequest) {
                        $(".loading").show();
                        $(".loading-bg").show();
                    },
                    success: function (jsonData) {
                        if(jsonData!=null && jsonData == 'true'){
                            isFirst = true;
                            $this.search($("#pageNo").val()!=null ? $("#pageNo").val():1);
                        }
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        $(".loading").hide();
                        $(".loading-bg").hide();
                    }
                });
            });
            //全选 取消全选
            $(".checkboxAll").on('change', function () {
                if ($(this).prop("checked")) {
                    $("#tableList").find(".signUpMany").prop("checked", true);
                    $.each($("#tableList").find(".signUpMany"), function(i, cb){
                        idsData += $(cb).val()+',';
                    });
                } else {
                    $("#tableList").find(".signUpMany").prop("checked", false);
                    $.each($("#tableList").find(".signUpMany"), function(i, cb){
                        var re =new RegExp($(cb).val()+"," , "g");
                        idsData = idsData.replace(re, "");
                    });
                }
            });
        },
        search: function (page) {
            var $this = this;
            var data = {};
            data.eduYear = $("#eduYear").val();
            data.eduSeason=$("#eduSeason").val();
            data.eduStep=$("#eduStep").val();
            data.itemSecondCode = $("#itemSecondCode").val();
            data.itemThreeCode = $("#itemThreeCode").val();
            data.page = page ? page : 1;
            data.pageSize=$("#selectCounts").val() || 10;
            $.each(data, function (key, value) {
                if (!value) {
                    delete data[key];
                }
            });
            $(".course-list").find("table").find("tr:gt(0)").remove();
            $(".checkboxAll").prop("checked", false);
            $.ajax({
                url: rootPath + "/sysKnowledgeTree/knowledgeTreeList",
                data: data,
                type: 'post',
                beforeSend: function (XMLHttpRequest) {
                    $(".loading").show();
                    $(".loading-bg").show();
                },
                success: function (jsonData) {
                    if (jsonData.data.data.length == 0) {
                        $(".course-list")
                            .find("table")
                            .append(
                                '<tr><td colspan="3">没有查找到数据</td></tr>');
                    }
                    if(isFirst){
                        idsData = jsonData.ids;
                        isFirst = false;
                    }
                    var html;
                    $.each(jsonData.data.data,function (i, classType) {
                        html+= '<tr>'
                            + '<td>'
                            + '<input type="checkbox" class="signUpMany" '
                            + (idsData.indexOf(classType.commodityId+",")!=-1 ? 'checked="checked"':"")
                            + 'value="' + (classType.commodityId?classType.commodityId:"") + '" />'
                            + '</td>'
                            + '<td>'
                            + (classType.name ? classType.name
                                : "")
                            + '</td>'
                            + '<td>'
                            + '<a href="http://'+jsonData.company.domain+'/sysConfigItem/selectDetail/'+classType.commodityId+'" target="_blank">查看</a>'
                            + '</td>';
                        html+= '</tr>';
                    });
                    $(".course-list")
                        .find("table")
                        .append(html);
                    $("#rowCount").remove();
                    $("#pageNo").remove();
                    $(".course-list").after('<input type="hidden" id="pageNo" value="'+jsonData.data.pageNo+'"/>');
                    $(".signUpMany").click(function(){
                        if($(this).prop("checked")){
                            idsData += $(this).val()+',';
                        }else{
                            var re =new RegExp($(this).val()+"," , "g");
                            idsData = idsData.replace(re, "");
                        }
                    });
                    $(".course-list").find("tr").click(function(e){
                        if(e.target.tagName == 'TD'){
                            $(this).find('.signUpMany').click();
                        }
                    });
                    if (jsonData.data.rowCount >$("#selectCounts").val()) {
                        $(".pagination").pagination(jsonData.data.rowCount,
                            {
                                next_text: "下一页",
                                prev_text: "上一页",
                                current_page: jsonData.data.pageNo - 1,
                                link_to: "javascript:void(0)",
                                num_display_entries: 8,
                                items_per_page: jsonData.data.pageSize,
                                num_edge_entries: 1,
                                callback: function (page, jq) {
                                    var pageNo = page + 1;
                                    $this.search(pageNo);
                                }
                            });
//                            $("#selectCount").css("margin-bottom","").css("margin-bottom","-78px");
                    } else {
                        $(".pagination").html('');
//                            $("#selectCount").css("margin-bottom","").css("margin-bottom","-30px");
                    }
                    $(".viewTree").bind("click", function() {
                        window.location.href = "";
                    });
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $(".loading").hide();
                    $(".loading-bg").hide();
                }
            });
        },
        viewKnowledge: function () {
            $(".knowledge-list").find("table").find("tr:gt(0)").remove();
            $.ajax({
                url: rootPath + "/sysKnowledgeTree/viewknowledgeTreeList",
                data: {idstr:idsData},
                type: 'post',
                beforeSend: function (XMLHttpRequest) {
                    $(".loading").show();
                    $(".loading-bg").show();
                },
                success: function (jsonData) {
                    if (jsonData.length == 0) {
                        $(".knowledge-list")
                            .find("table")
                            .append(
                                '<tr><td colspan="3">没有查找到数据</td></tr>');
                    }
                    if(jsonData.length > 8){
                        $(".knowledgePopup").css("height","400px");
                    }else{
                        $(".knowledgePopup").css("height","auto");
                    }
                    var html;
                    $.each(jsonData,function (i, lesson) {
                        html+= '<tr>'
                            + '<td>' + (i+1) +'</td>'
                            + '<td>'
                            + (lesson.commodityName ? lesson.commodityName
                                : "")
                            + '</td>'
                            + '<td>'
                            + (lesson.lessonName ? lesson.lessonName
                                : "")
                            + '</td>';
                        html+= '</tr>';

                    });
                    $(".knowledge-list")
                        .find("table")
                        .append(html);
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $(".loading").hide();
                    $(".loading-bg").hide();
                }
            });
        },
        removeKnowledge: function () {
            var $this = this;
            var data = {};
            data.eduYear = $("#eduYear").val();
            data.eduSeason=$("#eduSeason").val();
            data.eduStep=$("#eduStep").val();
            data.itemSecondCode = $("#itemSecondCode").val();
            data.itemThreeCode = $("#itemThreeCode").val();
            $.ajax({
                url: rootPath + "/sysKnowledgeTree/removeKnowledge",
                type: 'post',
                data:data,
                beforeSend: function (XMLHttpRequest) {
                    $(".loading").show();
                    $(".loading-bg").show();
                },
                success: function (jsonData) {
                    if(jsonData!=null && jsonData== 'true'){
                        $this.search();
                    }
                },
                complete: function (XMLHttpRequest, textStatus) {
                    $(".loading").hide();
                    $(".loading-bg").hide();
                }
            });
        }
    };

    $(document).ready(function () {
        knowledge.init();
    });
    window.knowledge = knowledge;
})(jQuery);
