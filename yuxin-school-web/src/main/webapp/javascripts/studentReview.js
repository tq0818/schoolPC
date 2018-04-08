$(function () {
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
        for(var i = 0 ;i < $('.checkbox').length;i++){
            if($('.checkbox').eq(i).prop('checked')){
                checkNum++;
                checkListArr.push(i);
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
                $.msg('所选择学生已认证为本校用户！');
                //认证通过，删除该条学生数据
                that.parent().parent('tr').remove();
            }else{
                console.log("点击了否");
            }
        })
    });




});