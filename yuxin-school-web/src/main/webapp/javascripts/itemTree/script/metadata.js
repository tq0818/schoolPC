var setting = {
    data: {
        simpleData: {
            enable: true
        }
    },
    view: {
        selectedMulti: false
    },
    edit: {
        enable: true,
        editNameSelectAll: true,
        showRenameBtn: showRenameBtn
    },
    callback: {
        // beforeEditName: beforeEditName,
        beforeRemove: beforeRemove,
        onDblClick: onNodeDblClick,
        onClick: onSelected
    }
};
var zNodes = [{
    id: 1,
    pId: 0,
    name: "小低"
}, {
    id: 2,
    pId: 0,
    name: "小高"
}, {
    id: 3,
    pId: 0,
    name: "初中"
}];

function showRenameBtn(treeId, treeNode) {
    return !treeNode.getParentNode() || treeNode.level == 3;
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
    $('.tree_setting div').hide();
    $('input:checkbox').prop('checked', false);
    $('#newTopicName').val('');

    var childrenArr = ztree.transformToArray(treeNode.children);

    $(childrenArr).each(function() {
        $('input[value=' + this.name + ']').prop('checked', true);
    })

    switch (treeNode.level) {
        case 0:
            $('#courses').show();
            break;
        case 1:
            $('#periods').show();
            break;
        case 2:
            $('#topic').show();
            break;
    }
}


$(document).ready(function() {
    $.ajax({
        type:"post",
        url:"/itemTree/ajaxValue",
        data:{},
        success:function(data){
            debugger;
            var list = data.list;
            var names = data.type;
            for(var n = 0 ; n <list.length ; n++){
                var item = list[n];
                if(item.parentId ==null){
                    item.pid = 0 ;
                }else{
                    item.parentId;
                }
                for(var m = 0 ; m <names.length ; m++){
                    var name = names[m];
                    if(item.itemCode == name.itemCode){
                        item.name = name.itemName;
                        break;
                    }
                }
            }
            zNodes = list;
            ztree = $.fn.zTree.init($("#ztree"), setting, zNodes);
        }
    });
    var count = 0; //新节点编号

    //新建根节点
    $('#addCatg').on('click', function() {
        var nodeName = '新节点_' + count++;
        ztree.addNodes(null, [{ name: nodeName }]);
        var newNode = ztree.getNodesByParam('name', nodeName);
        ztree.editName(newNode[0]);
    });

    //新建科目/学段
    $('input:checkbox').on('change', function(e) {
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

    });

    //新增知识点
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
    })

    //让ZTREE容器高度和窗口高度一致
    $(window).on('resize', function() {
        ztreeResize();
    })

    function ztreeResize() {
        $('.tree_cntr').height($(window).height() - 100);
    }
    ztreeResize();
});
