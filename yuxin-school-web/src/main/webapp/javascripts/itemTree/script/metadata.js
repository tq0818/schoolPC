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
function onNodeDblClick(event, treeId, treeNode) {
    if (!treeNode.getParentNode() || treeNode.level == 3) {
        ztree.editName(treeNode);
    }
}

function beforeRemove(treeId, treeNode, newName, isCancel) {
    ztree.selectNode(treeNode);
    return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
}

//控制右侧显示内容
function onSelected(event, treeId, treeNode) {
    ztree.expandNode(treeNode, true, true, true);
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
    var count = 0; //新节点编号

//新建根节点
/*    $('#addCatg').on('click', function() {
        var nodeName = '新节点_' + count++;
        ztree.addNodes(null, [{ name: nodeName }]);
        var newNode = ztree.getNodesByParam('name', nodeName);
        ztree.editName(newNode[0]);
    });*/
    //新建根节点
    $('#addCatg').on('click', function() {

        $('.tree_setting .tree-listtype').hide();
        $('#bigType,.btn-list').show();
        if($("#ztree").html()!=""){
            ztree.cancelSelectedNode();
            var ztreeList = ztree.getNodes();
            $.each(ztreeList,function (i,v) {
                $(".tree-listtype:visible input[value="+v.itemCode+"]").prop('checked', true).attr("disabled","disabled");
            });
        }



    });

    $("#savabtn").on('click', function() {
        var input = $(".tree-listtype:visible input");
            var selectNode = ztree.getSelectedNodes()[0];
            var checkIds = [],hasitemCode = [],editCode = '',
                $savebtn = $(this);
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
                alert("请先选择节点。");return false;
            }
            if (equalCode.length>0) {
                alert("包含已存在节点，不能保存。");return false;
            }
        }else{
            if(input.val().trim()==""){
                alert("请输入知识点！");
                return false;
            }
            //如果是修改，就去选中父节点
         /*   if(input.hasClass("editing")){
                editCode = selectNode.itemCode;//当前修改的code
                var Pnode = ztree.getNodeByParam("id",selectNode.parentId);
                ztree.selectNode(Pnode);
                selectNode = ztree.getSelectedNodes()[0];
            }*/
                checkIds.push(input.val());
       /*     if(selectNode.children && selectNode.children.length>0){
                    $.each(selectNode.children,function (i,v) {
                        if(editCode!=v.itemCode){//当前修改的code,,不再传入ids
                            checkIds.push(v.itemCode);
                        }
                    });
            }*/

        }
//获取所有新增id 后，，如果树节点有对应id,则阻止保存，

        var parentCode,parentId,levelPath=null;
        var level=0;
        if(selectNode){
            parentCode = selectNode.itemCode;
            parentId = selectNode.id;
           levelPath = selectNode.levelPath+selectNode.id+',';
           level = selectNode.level+1;
        }
 /*       if(input.hasClass("editing")){
            parentCode = selectNode.parentCode;
            parentId = selectNode.parentId;
            levelPath = selectNode.levelPath;
            level = selectNode.level-1;
        }*/
        $savebtn.attr("disabled","disabled");
        var url = "/itemTree/insert",data = {'level':level,"codes":checkIds.join(","),"parentCode":parentCode,"parentId":parentId,"levelPath":levelPath};
        if(input.hasClass("editing")){
            url = '/itemTree/update';
            data = {"id":input.attr("ids"),"itemCode":checkIds.join(",")}
        }
        $.ajax({
            type:"post",
            url:url,
            data:data,
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
                   $.get("/itemTree/ajaxValue",function(data,status){
                        var list = data.list;
                        var names = data.type;
                        getTreeList(list,names);
                        zNodes = list;
                        ztree = $.fn.zTree.init($("#ztree"), setting, zNodes);
                    });
                }
                $savebtn.attr("disabled",false);
            },error:function (e) {
                $savebtn.attr("disabled",false);
            }
        });

    });


    //让ZTREE容器高度和窗口高度一致
    $(window).on('resize', function() {
        ztreeResize();
    });

    function ztreeResize() {
        $('.tree_cntr').height($(window).height() - 100);
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
