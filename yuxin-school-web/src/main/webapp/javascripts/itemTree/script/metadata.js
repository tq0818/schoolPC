var zNodes = [];
function getTreeList(list,names){
    for(var n = 0 ; n <list.length ; n++){
        var item = list[n];
        if(item.parentId ==null){
            item.pid = 0 ;
        }else{
            item.parentId;
        }
        if(names){
            for(var m = 0 ; m <names.length ; m++){
                var name = names[m];
                if(item.itemCode == name.itemCode){
                    item.name = name.itemName;
                    break;
                }
            }
        }else{
            item.name = item.itemCode;
        }

    }
    return  list;
}

var setting = {
    data: {
        simpleData: {
            enable: true
        }
    },
    view: {
        selectedMulti: false
    },
    async: {
        enable: true,
        url: "/itemTree/getNodes",
        autoParam: ["id", "level"],
        dataFilter: ajaxDataFilter
    },
   edit: {
       drag: {
           isCopy: false,
           isMove: false
       },
        enable: true,
       showRenameBtn: false,
       showRemoveBtn: true
     //   showRenameBtn: showRenameBtn
    },
    callback: {
        // beforeEditName: beforeEditName,
        beforeRemove: beforeRemove,
        // onDblClick: onNodeDblClick,
        onClick: onSelected,
       //onExpand: onExpandClick,
        onAsyncSuccess: zTreeOnAsyncSuccess,
        onRemove: zTreeOnRemove
    }
};
function ajaxDataFilter(treeId, parentNode, responseData){

    if(parentNode.children==undefined||(parentNode.children).length<1){
        var list = responseData.list;
        var names = responseData.name;
         return getTreeList(list,names);

        // var newNodes = list;
        //
        // ztree.addNodes(treeNode, newNodes);
    }
}
function zTreeOnRemove(event, treeId, treeNode) {
    var nodes = ztree.getNodesByParam("id", treeNode.parentId, null);
    var datas = {};
    if(nodes.length>0 && nodes[0].children.length==0){
         datas = {"id": treeNode.id,"parentId":treeNode.parentId};
    }else{
        datas = {"id": treeNode.id};
    }
    $.ajax({
        type: "post",
        url: "/itemTree/delNodes",
        data: datas,
        success: function (data) {
            // if(data=="true"){
            //     $.msg("删除成功");
                $('.tree_setting .tree-listtype,.btn-list').hide();

            // }

        }
    });
}
/*function showRenameBtn(treeId, treeNode) {
    return !treeNode.getParentNode() || treeNode.level == 3;
}*/
function zTreeOnAsyncSuccess(event, treeId, treeNode){
    var childrenArr = ztree.transformToArray(treeNode.children);
    $(childrenArr).each(function() {
        $('.tree-listtype:visible input[value=' + this.itemCode+ ']').prop('checked', true).attr("disabled","disabled");
    });
}
/*function onNodeDblClick(event, treeId, treeNode) {
    if (!treeNode.getParentNode() || treeNode.level == 3) {
        ztree.editName(treeNode);
    }
}*/

function beforeRemove(treeId, treeNode, newName, isCancel) {
    ztree.selectNode(treeNode);
    return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
}


//控制右侧显示内容
function onSelected(event, treeId, treeNode) {
    //展开就收起。
  /* if(treeNode.open==true){
       ztree.expandNode(treeNode, false, false, true);
       return;
   }*/
    ztree.expandNode(treeNode, true, false, true);
    $('.tree_setting .tree-listtype').hide();
    $('input:checkbox').prop('checked', false);
    $('#newTopicName').val('');
    $(".btn-list").show();
    switch (treeNode.level) {
        case 0:
            $('#periods').show();
            break;
        case 1:
            $('#courses').show();
            break;
        case 2:
            $('#topic').show().find("input").val("").removeClass("editing");
            break;
        case 3:
           // $(".btn-list").hide();
            $('#topic').show().find("input").val(treeNode.itemCode).addClass("editing").attr("ids",treeNode.id);

            break;
    }
    $('.tree-listtype:visible input').removeAttr("disabled");
    var childrenArr = ztree.transformToArray(treeNode.children);
    $(childrenArr).each(function(i,v) {
        var ckinput = $('.tree-listtype:visible input[value=' + v.itemCode+ ']');
        ckinput.prop('checked', true).attr("disabled","disabled");
    });
}


$(document).ready(function() {
    $.ajax({
        type:"post",
        url:"/itemTree/ajaxValue",
        data:{},
        success:function(data){
            var list = data.list;
            var names = data.type;
            getTreeList(list,names);
            zNodes = list;
            ztree = $.fn.zTree.init($("#ztree"), setting, zNodes);
        }
    });
    //新建根节点
    $('#addCatg').on('click', function() {
        $('.tree_setting .tree-listtype').hide();
        $('#bigType,.btn-list').show();
        $(".tree-listtype:visible input").prop('checked', false).removeAttr("disabled");
        if($("#ztree").html()!=""){
            ztree.cancelSelectedNode();
            var ztreeList = ztree.getNodes();
            $.each(ztreeList,function (i,v) {
                $(".tree-listtype:visible input[value="+v.itemCode+"]").prop('checked', true).attr("disabled","disabled");
            });
        }
    });
var timer = null;
    $("#savabtn").on('click', function() {
        var $savebtn = $(this);
        $savebtn.attr("disabled","disabled");
        clearTimeout(timer);

        //如果键盘敲击速度太快，小于100毫秒的话就不会向后台发请求，但是最后总会进行一次请求的。
     timer = setTimeout(function() {
        var input = $(".tree-listtype:visible input");
            var selectNode = ztree.getSelectedNodes()[0];
            var checkIds = [],hasitemCode = [];
        //on checkbox
        if(input.attr("type")!='text'){
            var inputList = $(".tree-listtype:visible input:checked:not('[disabled]')");//勾选且没有disabled 属性的
            var hasitem =[];//已存在的treedata
            if(selectNode){
                hasitem = selectNode.children;
            }else{
                hasitem = ztree.getNodes();//根节点
            }
            var equalCode = [];
            if(inputList.length>0 ) {
                $.each(inputList, function (i, v) {
                    checkIds.push($(v).val());
                });
                if(hasitem!=undefined && hasitem.length>0){
                    $.each(hasitem, function (j, k) {
                        hasitemCode.push(k.itemCode);
                    });
                    for(var s in checkIds){
                        for(var x in hasitemCode){
                            if(checkIds[s]==hasitemCode[x]){
                                equalCode.push(checkIds[s]);
                            }
                        }
                    }
                }

            }else{
                alert("请先选择节点。");
                $savebtn.attr("disabled",false);
                return false;
            }
            if (equalCode.length>0) {
                alert("包含已存在节点，不能保存。");
                $savebtn.attr("disabled",false);
                return false;
            }
        }else{
            if(input.val().trim()==""){
                alert("请输入知识点！");
                $savebtn.attr("disabled",false);
                return false;
            }
                checkIds.push(input.val());
        }
//获取所有新增id 后，，如果树节点有对应id,则阻止保存，
        //如果是修改，就去选中父节点
         if(input.hasClass("editing")){
             var Pnode = ztree.getNodeByParam("id",selectNode.parentId);
             ztree.selectNode(Pnode);
             selectNode = ztree.getSelectedNodes()[0];
         }else{
             var parentCode,parentId,levelPath=null;
             var level=0;
             if(selectNode){
                 parentCode = selectNode.itemCode;
                 parentId = selectNode.id;
                 levelPath = selectNode.levelPath+selectNode.id+',';
                 level = selectNode.level+1;
             }
         }

        // $savebtn.attr("disabled","disabled");

            var   url = '/itemTree/update',
            data = {"id":input.attr("ids"),"itemCode":checkIds.join(",")};
            if(! input.hasClass("editing")){
                url = "/itemTree/insert";
                data = {'level':level,"codes":checkIds.join(","),"parentCode":parentCode,"parentId":parentId,"levelPath":levelPath};
            }
            $.ajax({
            type:"post",
            url:url,
            data:data,
            beforeSend : function(XMLHttpRequest) {
                    $(".loading,.loading-bg").show();
            },
            success:function(data){
                if(input.attr("type")=="text"){
                    input.removeClass("editing").val("");
                }
                if(selectNode){
                    if(checkIds.length>0){
                        selectNode.isParent = true;
                    }else{
                        selectNode.isParent = false;
                    }
                    ztree.reAsyncChildNodes(selectNode, "refresh", false);
                }else{
                    //ztree.reAsyncChildNodes(null, "refresh", false);
                    //根节点重置树，
                   $.get("/itemTree/ajaxValue",function(data,status){
                        var list = data.list;
                        var names = data.type;
                        getTreeList(list,names);
                        zNodes = list;
                        ztree = $.fn.zTree.init($("#ztree"), setting, zNodes);
                    });
                    // 重置根节点类型
                    $.each(checkIds,function (i,v) {
                        $('.tree-listtype:visible input[value=' + v+ ']').prop('checked', true).attr("disabled","disabled");
                    });

                }
                $savebtn.attr("disabled",false);
            },error:function (e) {
                $savebtn.attr("disabled",false);
            },
            complete : function(XMLHttpRequest, textStatus) {
                    $(".loading,.loading-bg").hide();
            }
        });
     },500);
    });


    //让ZTREE容器高度和窗口高度一致
    $(window).on('resize', function() {
        ztreeResize();
    });

    function ztreeResize() {
        $('.tree_cntr').height($(window).height() - 100 - 52);
    }
    ztreeResize();
    $(".tab-sub").delegate(".tab-type","click",function(i,v){
        var href = $(this).attr("href");
        $(this).addClass("active").siblings().removeClass("active");
        $(".tab-info").hide();
        $(href).show();
    });
    $("#publishRelation").on("click",function(i,v){

        $.ajax({
            type: "post",
            url: "/itemTree/publishRelation",
            data: {"name":""},
            success: function (data) {
                alert("发布成功");
            }
        });
    });

});
