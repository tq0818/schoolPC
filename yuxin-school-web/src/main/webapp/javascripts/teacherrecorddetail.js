(function ($) {
	
	$('.table').on("click",".Withdraw",function(){
		$('html').addClass('overflow-y-h');
		$('.loading-bg').css('backgroud-color','#000').show();
		var teacherId = $(this).attr('mark');
		var data = {};
		data.teacherId = teacherId;
		$.ajax({
			type:'get',
			url:rootPath+'/teacherInviteRewardRecord/todetaillistpage',
			data:data,
			success:function(jsondata){
					//$(".detailsPop").find('.teacz').text(jsondata.teacher.name?data.totalMoney.toFixed(2):0);
					$(".detailsPop").find('.teacz').text(jsondata.teacher.name?jsondata.teacher.name+"  :  ":"");
					$(".detailsPop").find('.teacz').next('em').text(jsondata.teacher.mobile?jsondata.teacher.mobile:"");
					$(".detailsPop").find('#rewardBalance').text(jsondata.rewardBalance.toFixed(2)+" 元");
					$(".detailsPop").find('#consumeBalance').text(jsondata.consumeBalance.toFixed(2)+" 元");
					$(".detailsPop").find('#consumeTimes').text(jsondata.consumerTimes+" 次");
					$(".detailsPop").find('#inviteNumber').text(jsondata.inviteNumber+" 人");
					$('.hiddendetailteacherId').val(teacherId);
					$(".detail-Parent").show();
					rewardsrecorddetail.init();
			}
		})
		
    })
    
    var rewardsrecorddetail = {
        init: function () {
            var $this = this;
            // 初始化数据
            $this.search();

        },
        search: function (page) {
            var $this = this;
            var data = {};
            var teacherId = $('.hiddendetailteacherId').val();
            data.teacherId = teacherId;
            var consumeFlag = $('select[name="consumeFlag"]').find('option:selected').val();
            data.name = $('.nameTel-t').val();
            data.page = page ? page : 1;
            data.sortName = $('.hiddendetailsortName').val();
            data.sortType = $('.hiddendetailsortType').val();
            data.consumeFlag = consumeFlag;
            $(".detailsPop").find("table").find('tbody').find("tr:gt(0)").remove();
            $.ajax({
                    url: rootPath + "/teacherInviteRewardRecord/queryRewardsRecordDetailList",
                    data: data,
                    type: 'POST',
                    beforeSend: function (XMLHttpRequest) {
                        $(".loading").show();
                        $(".loading-bg").show();
                    },
                    success: function (jsonData) {
                        if (jsonData.data.length == 0 || jsonData.data == null) {
                            $(".detailsPop")
                                .find("table").find('tbody')
                                .append(
                                '<tr><td colspan="6">没有查找到数据</td></tr>');
                        }
                        
                        $("#pageNo2").remove();
                        $(".detailsPop").after('<input type="hidden" id="pageNo2" value="'+jsonData.pageNo+'"/>');
                        $('.hiddendetailsortName').val('');
                    	$('hiddendetailsortType').val('');
                        $.each(jsonData.data,function (i, stu) {
                                $(".detailsPop")
                                    .find("table").find('tbody')
                                    .append(
                                            '<tr >'
                                            + '<td>'
                                            + (stu.mobile ? stu.mobile+"/"+(stu.name?stu.name:""):
                                                (stu.name?stu.name:""))
                                            + '</td>'
                                            + '<td>'
                                            + (stu.commodityName ? stu.commodityName
                                                : "")
                                            + '</td>'
                                            + '<td>'
                                            + (stu.inviteDate? stu.inviteDate.substring(0,11)
                                                : '')
                                            + '</td>'
                                            + '<td>'
                                            + (stu.consumeDate ? stu.consumeDate.substring(0,11)
                                                : '')
                                            + '</td>'
                                            + '<td>'
                                            + (stu.consumeBalance ? stu.consumeBalance.toFixed(2)
                                                : '')
                                            + '</td>'
                                            + '<td>'
                                            + (stu.rewardBalance ? stu.rewardBalance.toFixed(2)
                                                : '')
                                            + '</td>'
                                            + '</tr>'
                                            +'<input type="hidden" value=""/>');
                            });
                        
                        if (jsonData.rowCount > 10) {
                            $(".pagination2").pagination(jsonData.rowCount,
                                {
                                    next_text: "下一页",
                                    prev_text: "上一页",
                                    current_page: jsonData.pageNo - 1,
                                    link_to: "javascript:void(0)",
                                    num_display_entries: 8,
                                    items_per_page: jsonData.pageSize,
                                    num_edge_entries: 1,
                                    callback: function (page, jq) {
                                        var pageNo = page + 1;
                                        $this.search(pageNo);
                                    }
                                });
                        } else {
                            $(".pagination2").html('');
                        }
                       
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        $(".loading").hide();
                        //$(".loading-bg").hide();
                    }
                });
        }
    }

    $(document).ready(function(){
    	$('.detailup,.detaildown').click(function(){
    		var mark = $(this).parent().attr('mark');
    		$('.hiddendetailsortName').val(mark);
    		var pageNo2 = $('#pageNo2').val();
    		if($(this).hasClass('detailup')){
    			$('.hiddendetailsortType').val('asc');
    			if(pageNo2&&pageNo2 != 1)
    			rewardsrecorddetail.search(pageNo2);
    			else
    				rewardsrecorddetail.search();	
    		}else{
    			$('.hiddendetailsortType').val('desc');
    			if(pageNo2&&pageNo2 != 1)
    			rewardsrecorddetail.search(pageNo2);
    			else
    				rewardsrecorddetail.search();	
    		}
    	})
    	
    	$('#detailSearch').on('click',function(){
    		rewardsrecorddetail.search();
    	})
    	$('.spe-close').on('click',function(){
    		$('.detail-Parent').hide();
    		$('.loading-bg').hide();
    		$('html').removeClass('overflow-y-h');
    	})
    })
   
})(jQuery)
