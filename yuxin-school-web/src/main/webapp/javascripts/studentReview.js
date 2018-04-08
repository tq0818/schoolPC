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

    //分页
    $(".pagination").pagination(23, {
        next_text : "下一页",
        prev_text : "上一页",
        current_page :'1',
        link_to : "javascript:void(0)",
        num_display_entries : 8,
        items_per_page : '8',
        num_edge_entries : 1,
        callback:function(){
            // var pageNo = page + 1;
            // getWatchInfo(pageNo);
        }
    });

    //加载中
    // $('.loading').show();
    // $('.loading-bg').show();

    //时间先后顺序判断
    $('#searchBtnAll').click(function () {

        var beginDate= $('#starTime').val();
        var endDate=$('#endTime').val();

        function tab(date1,date2){
            var oDate1 = new Date(date1);
            var oDate2 = new Date(date2);
            if(oDate1.getTime() > oDate2.getTime()){
                console.log('第一个大');
                $.msg('开始时间不能大于结束时间');
            } else {
                console.log('第二个大');
            }
        }
        tab(beginDate,endDate);

    });





});