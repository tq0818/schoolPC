(function ($) {
	
	$('.user-list').on("click",".a-detail",function(){
		var userId = $(this).attr('mark');
		var data = {};
		data.userId = userId;
		$.ajax({
			type:'get',
			url:rootPath+'/userInviteRewardsRecord/toinviterewardsrecorddetail',
			data:data,
			success:function(data){
					$(".coupon-code-detail").find('#totalMoneyid').text(data.totalMoney?data.totalMoney.toFixed(2):0);
					$(".coupon-code-detail").find('#totalIntegralid').text(data.totalIntegral?data.totalIntegral:0);
					$('.test12').val(data.userId);
					$(".coupon-code-detail").show();
					rewardsrecorddetail.init();
			}
		})
		
    })
    
    
    $('#yaoqingsel').on('change',function(){
    		rewardsrecorddetail.search();
    	});
    	$('#typesel').on('change',function(){
    		rewardsrecorddetail.search();
    	});
    	
    var rewardsrecorddetail = {
        init: function () {
            var $this = this;
            // 初始化数据
            $this.search();

        },
        search: function (page) {
            var $this = this;
            var data = {};
            var userId = $('.test12').val();
            data.userId = userId;
            var yaoqingsel = $('#yaoqingsel').find('option:selected').val();
            if(yaoqingsel == 'volvo'){
            	yaoqingsel = '1';
            }else if(yaoqingsel == 'saab'){
            	yaoqingsel = '2';
            }else if(yaoqingsel == 'opel'){
            	yaoqingsel = '3';
            }else{
            	yaoqingsel = null;
            }
            data.yaoqingsel = yaoqingsel;
            var typesel = $('#typesel').find('option:selected').val();
            if(typesel == 'volvo'){
            	typesel = '1';
            }else if(typesel == 'saab'){
            	typesel = '2';
            }else if(typesel == 'opel'){
            	typesel = '3';
            }else{
            	typesel = null;
            }
            data.typesel = typesel;
            data.page = page ? page : 1;
            
            $(".coupon-use-detail").find("table").find("tr:gt(0)").remove();
            $.ajax({
                    url: rootPath + "/userInviteRewardsRecord/findDetailInviteRewardsRecord",
                    data: data,
                    type: 'POST',
                    beforeSend: function (XMLHttpRequest) {
                        $(".loading").show();
                        //$(".loading-bg").show();
                    },
                    success: function (jsonData) {
                        if (jsonData.data.length == 0 || jsonData.data == null) {
                            $(".coupon-use-detail")
                                .find("table")
                                .append(
                                '<tr><td colspan="10">没有查找到数据</td></tr>');
                        }
                        
                        
                        $.each(jsonData.data,function (i, stu) {
                                $(".coupon-use-detail")
                                    .find("table")
                                    .append(
                                            '<tr >'
                                            + '<td>'
                                            + (stu.parentMobile ? stu.parentMobile
                                                : stu.parentUsername)
                                            + '</td>'
                                            + '<td>'
                                            + (stu.mobile ? stu.mobile
                                                : stu.username)
                                            + '</td>'
                                            + '<td>'
                                            + (stu.reason==0? '邀请注册'
                                                : '首次消费')
                                            + '</td>'
                                            + '<td>'
                                            + (stu.createTime ? stu.createTime
                                                : '')
                                            + '</td>'
                                            + '<td>'
                                            + (stu.rewardsMoney ? stu.rewardsMoney.toFixed(2)
                                                : 0)
                                            + '</td>'
                                            + '<td>'
                                            + (stu.rewardsIntegral ? stu.rewardsIntegral
                                                : 0)
                                            + '</td>'
                                            + '</tr>'
                                            +'<input type="hidden" value="'+stu.userId+'"/>');
                            });
                        
                        if (jsonData.rowCount > 10) {
                            $(".pagination1").pagination(jsonData.rowCount,
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
                            $(".pagination1").html('');
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
    	
    })
   
})(jQuery)
