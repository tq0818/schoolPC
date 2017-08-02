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
        enable: true
    },
   edit: {
        enable: true,
       showRenameBtn: false,
       showRemoveBtn: true
     //   showRenameBtn: showRenameBtn
    },
    callback: {
        // beforeEditName: beforeEditName,
        beforeRemove: beforeRemove,
        onDblClick: onNodeDblClick,
        onClick: onSelected,
        onExpand: onExpandClick,
        onRemove: zTreeOnRemove
    }
};

function zTreeOnRemove(event, treeId, treeNode) {
    $.ajax({
        type: "post",
        url: "/itemTree/delNodes",
        data: {"id": treeNode.id},
        success: function (data) {
            alert(data);
        }
    });
}
function showRenameBtn(treeId, treeNode) {
    return !treeNode.getParentNode() || treeNode.level == 3;
}
function onExpandClick(event, treeId, treeNode){
    $.ajax({
        type: "post",
        url: "/itemTree/getNodes",
        data: {"level":treeNode.level,"parentId":treeNode.id},
        success: function (data) {

             if(treeNode.children==undefined||(treeNode.children).length<1){
                var list = data.list;
                var names = data.name;
                getTreeList(list,names);
                var newNodes = list;
                ztree.addNodes(treeNode, newNodes);
             }

        }
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
    $('.tree_setting .tree-listtype').hide();
    $('input:checkbox').prop('checked', false);
    $('#newTopicName').val('');

    var childrenArr = ztree.transformToArray(treeNode.children);

    $(childrenArr).each(function() {
        $('input[value=' + this.itemCode+ ']').prop('checked', true);
    })

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
            $('#topic').show().find("input").val(treeNode.itemCode).addClass("editing");

            break;
    }
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
        ztree.cancelSelectedNode();
        $('#bigType').show();
    });
    $("#savabtn").on('click', function() {
        var input = $(".tree-listtype:visible input");
            var selectNode = ztree.getSelectedNodes()[0];
            var checkIds = [];
        //on checkbox
        if(input.attr("type")!='text'){
            var inputList = $(".tree-listtype:visible input:checked");

            $.each(inputList, function(i,v) {
                checkIds.push($(v).val());
            });
        }else{
            checkIds.push(input.val());
        }

        var parentCode,parentId,levelPath=null;
        var level=0;
        if(selectNode){
            parentCode = selectNode.itemCode;
            parentId = selectNode.id;
           levelPath = selectNode.id+',';
           level = selectNode.level+1;
        }
        if(input.hasClass("editing")){
            parentCode = selectNode.parentCode;
            parentId = selectNode.parentId;
            levelPath = selectNode.parentId+',';
            level = selectNode.level-1;
        }
        $.ajax({
            type:"post",
            url:"/itemTree/insert",
            data:{'level':level,"codes":checkIds.join(","),"parentCode":parentCode,"parentId":parentId,"levelPath":levelPath},
            success:function(data){
                if(input.hasClass("editing")){
                   var pnode = ztree.getNodeByTId(selectNode.parentTId);
                    ztree.removeChildNodes(pnode);
                    if(checkIds.length>0){
                        //更新根节点中第i个节点的名称
                        pnode.isParent = true;
                        ztree.updateNode(pnode);
                    }
                }else{
                    ztree.removeChildNodes(selectNode);
                    if(checkIds.length>0){
                        //更新根节点中第i个节点的名称
                        selectNode.isParent = true;
                        ztree.updateNode(selectNode);
                    }
                }



                /*强行异步加载父节点的子节点。[setting.async.enable = true 时有效]*/
               // ztree.reAsyncChildNodes(selectNode, "refresh", false);
            }
        });

    });
    //新建科目/学段
/*    $('input:checkbox').on('change', function(e) {
        // alert(1);
        var me = $(this);
        var newNodeName = me.val();
        var selectNode = ztree.getSelectedNodes()[0];
        var isChceked = me.prop('checked');

        if (isChceked) {
            ztree.addNodes(selectNode, [{ name: newNodeName }]);
        } else {
            var willRemove = ztree.getNodesByParam('name', newNodeName, selectNode)[0];
            ztree.removeNode(willRemove, false);
        }

    });*/

/*    //新增知识点
    $('#addTopic').on('click', function() {
        var newNodeName = $.trim($('#newTopicName').val());
        var selectNode = ztree.getSelectedNodes()[0];
        if (newNodeName == '') {
            alert('知识点不能为空！');
            return;
        }
        if (ztree.getNodesByParam('name', newNodeName, selectNode).length == 0) {
            ztree.addNodes(selectNode, [{ name: newNodeName }]);
            $('#newTopicName').val('');
        } else {
            alert('知识点已存在。')
        }
    })*/

    //让ZTREE容器高度和窗口高度一致
    $(window).on('resize', function() {
        ztreeResize();
    })

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
});
