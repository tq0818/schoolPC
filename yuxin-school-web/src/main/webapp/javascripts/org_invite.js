(function ($) {
    var inviteOrg = {
        init: function () {
            var $this = this;
            $(".date-picker").datetimepicker({
                format: "yyyy-mm-dd",
                minView: 2,
                autoclose: true,
                language: "zh-CN"
            });
            // 初始化数据
            $this.search();
        },
        
        search: function (page) {
            var $this = this;
            var data = {};
            data.name = $(".nameTel").val();
            var searchDateType;
            var startTime;
            var endTime;
            $('.screenList-check').find('a').each(function(){
            	if($(this).hasClass('btn-success')){
            		if($(this).text()=='全部')
            			searchDateType = '';
            		else if($(this).text()=='上月')
            			searchDateType=1;
            		else if($(this).text()=='本月')
            			searchDateType=2;
            		else if($(this).text()=='指定时间'){
            			startTime = $(".from").val();
            	        endTime = $(".to").val();
            		}
            	}
            })
            if ($(".to").val() != "") {
	            if ($(".to").val() < $(".from").val()) {
	                $.msg("时间范围不正确");
	                return;
	            }

	        }
	        $.each(data, function (key, value) {
	            if (!value) {
	                delete data[key];
	            }
	        })
            data.searchDateType = searchDateType;
	        data.startTime = startTime;
	        data.endTime = endTime;
	        data.sortType = $('.hidSortType').val();
	        data.sort = $('.hidSort').val();
            data.page = page ? page : 1;
            
            $('.table').find('tbody').find('tr:gt(0)').remove();
            $.ajax({
                    url: rootPath + "/organInviteRewardRecord/queryRewardsRecordList",
                    data: data,
                    type: 'post',
                    beforeSend: function (XMLHttpRequest) {
                        $(".loading").show();
                        $(".loading-bg").show();
                    },
                    success: function (jsonData) {
                        if (jsonData.data.length == 0 || jsonData.data == null) {
                        	$('.table').find('tbody')
	                        .append(
	                        '<tr><td colspan="7">没有查找到数据</td></tr>');
                        	$('.hidSortType').val('');
                        	$('.hidSort').val('');
                        }
                        
                        
                        $.each(jsonData.data,function (i, teac) {
                        	$('.table').find('tbody').append('<tr class="tab-dtr">'+
                                    '<td>'+(teac.name?teac.name:"")+'</td>'+
                                    '<td>'+(teac.createDate?teac.createDate:"")+'</td>'+
                                    '<td>'+(teac.inviteNumber?teac.inviteNumber:0)+'</td>'+
                                    '<td>'+(teac.consumeTimes?teac.consumeTimes:0)+'</td>'+
                                    '<td>'+(teac.consumeBalance?teac.consumeBalance.toFixed(2):0)+'</td>'+
                                    '<td>'+(teac.rewardBalance?teac.rewardBalance.toFixed(2):0)+'</td>'+
                                    '<td><span class=" Withdraw" mark="'+(teac.proxyOrganId)+'">详情</span></td>'+
                                '</tr>');
                            });
                        $("#pageNo").remove();
                        $(".table").after('<input type="hidden" id="pageNo" value="'+jsonData.pageNo+'"/>');
                        $('.hidSortType').val('');
                    	$('.hidSort').val('');
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
    	var hcb = parseFloat($('.hiddenConsumeBalance').val()).toFixed(2);
    	var hrb = parseFloat($('.hiddenRewardBalance').val()).toFixed(2);
    	$('.hiddenConsumeBalance').next('em').text(hcb+" 元");
    	$('.hiddenRewardBalance').next('em').text(hrb+" 元");
    	 $('.screenList-check .btn-mini').on('click', function() {
            $('.btn-mini').removeClass('btn-success').css('border-color', '#ccc');
            $(this).addClass('btn-success').css('border-color', '#1ebb90');
            if ($(this).hasClass('btn-default')) {

                $('.date-picker').hide();
                $('.datetit-z').hide();

            }

            if ($(this).hasClass('btn-td')) {
                $('.screenList').find('.date-picker').show();
                $('.screenList').find('.datetit-z').show();
            }
        });
    	inviteOrg.init();
    	$(".screenList-check a").click(function(){
    		if($(this).text()!='指定时间'&&$(this).text()!="查询")
    			inviteOrg.search();
    	})
    
    	$("#searchTeacInvite").on('click',function(){
    		inviteOrg.search();
    	})
    	
    	$('.up,.down').click(function(){
    		var mark = $(this).parent().siblings().eq(0).attr('mark');
    		$('.hidSortType').val('').val(mark);
    		if($(this).hasClass('up'))
    		$('.hidSort').val('asc');
    		else
    		$('.hidSort').val('desc');
    		var pageNo = $('#pageNo').val();
    		if(pageNo && pageNo !=1)
    		inviteOrg.search(pageNo);
    		else
    		inviteOrg.search();	
    	})
    	$(".export").on('click',function(){
    		//console.log($(".table").find("tbody").find("tr").eq(1).find('td').eq(0).html());
    		if ($("#table1").find("tbody").find("tr").length <= 1) {
                $.msg("没有数据可以导出");
                return;
    		}else if(($("#table1").find("tbody").find("tr").length ==2)&&($("#table1").find("tbody").find("tr").eq(1).find('td').eq(0).html()=='没有查找到数据')){
    				$.msg("没有数据可以导出");
    				return;
    		}
    			else{
    			var teacherName = $(".nameTel").val();
                var searchDateType1='';
                var startTime1='';
                var endTime1='';
                $('.screenList-check').find('a').each(function(){
                	if($(this).hasClass('btn-success')){
                		if($(this).text()=='全部')
                			searchDateType1 = '';
                		else if($(this).text()=='上月')
                			searchDateType1=1;
                		else if($(this).text()=='当月')
                			searchDateType1=2;
                		else if($(this).text()=='指定时间'){
                			startTime1 = $(".from").val();
                	        endTime1 = $(".to").val();
                		}
                	}
                })
                var sortType1 = $('.hidSortType').val();
                var sort1 = $('.hidSort').val();
                var page = $('#pageNo').val();
                page = page ? page : 1;
                window.location.href=rootPath+"/organInviteRewardRecord/exportExcel?name="+teacherName+"&date="+searchDateType1+"&page="+page
                +"&startTime="+startTime1+"&endTime="+endTime1+"&sortType="+sortType1+"&sort="+sort1;
    		}
    	})
    	
    })
   
})(jQuery)
