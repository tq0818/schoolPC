(function ($) {
    var rewardsrecord = {
        init: function () {
            var $this = this;
            // 初始化数据
            $this.search();

        },
        
        search: function (page) {
            var $this = this;
            var data = {};
            data.content = $("#conentSearch").val();
            var date=$(".use-time").find('.all').text();
            if(date == '今天'){
            	date = '1';
            }else if(date == '7天'){
            	date = '2';
            }else if(date == '当月'){
            	date = '3';
            }else{
            	date = '';
            }
            data.date = date;
            data.page = page ? page : 1;
            
            $(".user-list").find("table").find("tr:gt(0)").remove();
            $.ajax({
                    url: rootPath + "/userInviteRewardsRecord/queryRewardsRecordList",
                    data: data,
                    type: 'post',
                    beforeSend: function (XMLHttpRequest) {
                        $(".loading").show();
                        $(".loading-bg").show();
                    },
                    success: function (jsonData) {
                        if (jsonData.data.length == 0 || jsonData.data == null) {
                            $(".user-list")
                                .find("table")
                                .append(
                                '<tr><td colspan="10">没有查找到数据</td></tr>');
                        }
                        
                        $('#pageNo1').html('<input type="hidden" class="hiddenPageNo" value="'+jsonData.pageNo+'"/>');
                       
                        
                        $.each(jsonData.data,function (i, stu) {
                                $(".user-list")
                                    .find("table")
                                    .append(
                                            '<tr >'
                                            + '<td>'
                                            + (stu.mobile ? stu.mobile
                                                : stu.username)
                                            + '</td>'
                                            + '<td>'
                                            + (stu.inviteCode ? stu.inviteCode
                                                : "")
                                            + '</td>'
                                            + '<td>'
                                            + (stu.inviteRegNumber ? stu.inviteRegNumber
                                                : 0)
                                            + '</td>'
                                            + '<td>'
                                            + (stu.inviteConNumber ? stu.inviteConNumber
                                                : 0)
                                            + '</td>'
                                            + '<td>'
                                            + (stu.totalMoney ? stu.totalMoney.toFixed(2)
                                                : 0)
                                            + '</td>'
                                            + '<td>'
                                            + (stu.totalIntegral ? stu.totalIntegral
                                                : 0)
                                            + '</td>'
                                            + '<td><div class="operate-cont">'
                                            + '<a href="javascript:;" class="a-detail q-detail" mark="'+stu.userId+'">详情</a>'
                                            + '</div></td>'
                                            + '</tr>'
                                            +'<input type="hidden" class="hiddencla" value="'+stu.userId+'"/>'
                                            );
                            });
                        
                        if (jsonData.rowCount > 10) {
                            $(".pagination").pagination(jsonData.rowCount,
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
                            $(".pagination").html('');
                        }
                       
                    },
                    complete: function (XMLHttpRequest, textStatus) {
                        $(".loading").hide();
                        $(".loading-bg").hide();
                    }
                });
        }
    }

    $(document).ready(function(){
    	rewardsrecord.init();
    	$(".use-time").find('.all').siblings().on('click',function(){
    		rewardsrecord.search();
    	})
    	
    	$(".use-time").find('.all').on('click',function(){
    		rewardsrecord.search();
    	})
    	
    	$(".manage-button").on('click',function(){
    		rewardsrecord.search();
    	})
    	
    	$(".a-derive").on('click',function(){
    		if ($("#tableList").find("tr").eq(1).find("td").length <= 1) {
                $.msg("没有数据可以导出");
    		}else{
    			var data = {};
                content = $("#conentSearch").val();
                var date=$(".use-time").find('.all').text();
                if(date == '今天'){
                	date = '1';
                }else if(date == '7天'){
                	date = '2';
                }else if(date == '当月'){
                	date = '3';
                }else{
                	date = '';
                }
                
                
                var page = $('.hiddenPageNo').val();
                page = page ? page : 1;
                window.location.href=rootPath+"/userInviteRewardsRecord/exportExcelAll?searchName="+content+"&date="+date+"&page="+page;
    		}
    		
    	})
    	
    })
   
})(jQuery)
