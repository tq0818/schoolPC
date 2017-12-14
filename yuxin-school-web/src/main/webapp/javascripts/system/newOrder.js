$(function(){
	var _payStatus = $.getUrlParam('payStatus');
	if(_payStatus){
		var _aps;
		$.each($('.payStatus a'),function(i,obj){
			_aps = $(this).attr('pay-status');
			if(_aps == _payStatus){
				$(this).addClass('btn-success').removeClass('btn-default').siblings().addClass('btn-default').removeClass('btn-success');
				return;
			}
		});
	}
	selOrder(1);
	
	$(".btn-status").click(function(){
		$(this).addClass("btn-success").removeClass("btn-default").siblings("a").addClass("btn-default").removeClass("btn-success");
		$("#startDate").val("");
		$("#endDate").val("");
		selOrder(1);
	});
	
	$(".day-time").click(function(){
		$(this).addClass("btn-success").removeClass("btn-default").siblings("a").addClass("btn-default").removeClass("btn-success");
		$(".show").each(function(){
			$(this).addClass("none").removeClass("show");
		});
		$("#startDate").val("");
		$("#endDate").val("");
	});
	
	$(".day-time-point").click(function(){
		$(this).addClass("btn-success").removeClass("btn-default").siblings("a").addClass("btn-default").removeClass("btn-success");
		$(".c .none").each(function(){
			$(this).addClass("show").removeClass("none");
		});
		$("#startDate").val("");
		$("#endDate").val("");
	});
	
	$(".search").click(function(){
		selOrder(1);
	});
	$(".reset").click(function(){
		$("#startDate").val("");
		$("#endDate").val("");
		$("#mobile").val("");
	});
	//加载日期控件
	$(".laydate-icon").datetimepicker({
        format: 'yyyy-mm-dd',
        autoclose:true, //选择日期后自动关闭
        minView: "month", //选择日期后，不会再跳转去选择时分秒 
        language: 'zh-CN'
    });
	
	$("#startDate").change(function(){
		var sstr = $("#startDate").val();
		sstr = sstr.replace(/-/g,"/");
		var start = new Date(sstr);
		$("#endDate").datetimepicker("setStartDate",start.getFullYear()+ "-" + (start.getMonth() + 1)+"-"+start.getDate());
	});
	$("#endDate").change(function(){
		var sstr = $("#endDate").val();
		sstr = sstr.replace(/-/g,"/");
		var start = new Date(sstr);
		$("#startDate").datetimepicker("setEndDate",start.getFullYear()+ "-" + (start.getMonth() + 1)+"-"+start.getDate());
	});
	//填写手机号必须为数字
//	onlyNum("mobile");
});
function selOrder(pageNo){
	var payStatus = $(".btn-status.btn-success").eq(0).attr("pay-status");
	var payType = $(".btn-status.btn-success").eq(1).attr("pay-type");
	var payTime = $(".btn-sm.btn-success").eq(2).attr("mark");
	var startDate = $("#startDate").val();
	var endDate = $("#endDate").val();
	var companyId = $("#companyId").val();
	if(startDate == null || startDate ==""){
		startDate = null;
	}
	if(endDate == null || endDate ==""){
		endDate = null;
	}
	
	$.ajax({
		url : rootPath + "/newPayOrder/selOrder",
		type:"post",
		data:{"companyId": companyId ,"page":pageNo,"pageSize":5,"payStatus":payStatus, "payType":payType, "mobile":$("#mobile").val(), "startDate":startDate, "endDate":endDate, "payTime":payTime},
		dataType:"html",
		beforeSend:function(XMLHttpRequest){
              $(".loading").show();
              $(".loading-bg").show();
         },
		success:function(data){
			$(".o-list").html("").html(data);
		},
		complete:function(XMLHttpRequest,textStatus){
			$(".loading").hide();
            $(".loading-bg").hide();
        }
	});
}

function onlyNum(id){
	$("#"+id+"").keyup(function(){     
        var tmptxt=$(this).val();     
        $(this).val(tmptxt.replace(/\D|^0/g,''));     
    }).bind("paste",function(){     
        var tmptxt=$(this).val();     
        $(this).val(tmptxt.replace(/\D|^0/g,''));     
    }).css("ime-mode", "disabled"); 
}