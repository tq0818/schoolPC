$(function(){
	loadMessage(null,null);
	//搜索点击
	$(".selectInfo").click(function(){
		var start = $("#start").val();
		var end = $("#end").val();
		if(new Date(end)< new Date(start)){
			$('<div class="c-fa">'+ "结束日期不能小于开始日期" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
			return;
		}
		loadMessage(start,end);
	});
	/* 选择日期 */
	laydate({
		elem : '#start',
		format : 'YYYY-MM-DD hh:mm:ss',
		/* min: laydate.now(), //设定最小日期为当前日期 */
		max : laydate.now(), // 最大日期
		istime : true,
		istoday : false,
		choose : function(datas) {
			end.min = datas; // 开始日选好后，重置结束日的最小日期
			end.start = datas // 将结束日的初始值设定为开始日

		}
	});
	laydate({
		elem : '#end',
		format : 'YYYY-MM-DD hh:mm:ss',
		max : '2099-06-16 23:59:59',
		istime : true,
		istoday : false,
		choose : function(datas) {
			start.max = datas; // 结束日选好后，重置开始日的最大日期
		}
	});
	/* 选择日期 end */
});

function loadMessage(startTime, endTime) {
	$.ajax({
		url : rootPath + "/companyMemberService/toMsAjaxInfo",
		type : "post",
		data : {"startTime":startTime,"endTime":endTime},
		beforeSend : function(XMLHttpRequest) {
			$(".loading").show();
			$(".loading-bg").show();
		},
		success : function(data) {
			var time = new Array();
			var datas = new Array();
			$.each(data.message,function(index,item){
				time[index] = item.useDate;
//				alert(time[index])
				datas[index] = item.messageNum;
			});
			var myChartMessageDetail = echarts.init(document
					.getElementById('lineDomMessageDetail'));
			optionMessageDetail = {
				title : {
					text : "使用短信详情(单位：条；显示：默认最近15天)",
					x : "center"
				},
				tooltip : {
					trigger : "axis",
					formatter : "{a} <br/>{b} : {c}条"
				},
				
				xAxis : {
					type : "category",
					boundaryGap : false,
					data : time
				},
				yAxis : {
					type : "value",
					name : "y"
				} ,
				toolbox : {
					show : true,
					feature : {
						restore : {
							show : true
						}
					}
				},
				series : [ {
					name : "已发送",
					type : "line",
					data : datas

				} ]
			};
			console.log(time);
			console.log(datas);
			myChartMessageDetail.setOption(optionMessageDetail);
		},
		complete : function(XMLHttpRequest, textStatus) {
			$(".loading").hide();
			$(".loading-bg").hide();
		}
	});
}	