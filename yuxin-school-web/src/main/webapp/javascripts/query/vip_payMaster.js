(function($){
	
	function getDays(strDateStart,strDateEnd){
		var result=new Array();
		var strSeparator = "-"; //日期分隔符
		var mDays=[1,3,5,7,8,10,12];
		var oDate1;
		var oDate2;
		var iDays;
		oDate1= strDateStart.split(strSeparator);
		oDate2= strDateEnd.split(strSeparator);
		var strDateS = new Date(oDate1[0] + "-" + oDate1[1] + "-" + oDate1[2]);
		var strDateE = new Date(oDate2[0] + "-" + oDate2[1] + "-" + oDate2[2]);
		iDays = parseInt(Math.abs(strDateS - strDateE ) / 1000 / 60 / 60 /24)//把相差的毫秒数转换为天数 
		var currtDay=oDate1[2]-1;
		var currtMonth=oDate1[1];
		var currtYear=oDate1[0];
		for(var i=0;i<=iDays;i++){
			var day;
			if(oDate1[0]!=oDate2[0]){
				//不是同一年
				if(oDate1[1]!=oDate2[1]){
					//不是同一个月
					if(parseInt(currtMonth)<=12 && mDays.indexOf(parseInt(currtMonth))!=-1 && currtDay<31){
						currtDay=parseInt(currtDay)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else if(parseInt(currtMonth)<=12 && mDays.indexOf(parseInt(currtMonth))==-1 && currtDay<30){
						currtDay=parseInt(currtDay)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else if(parseInt(currtMonth)<=12){
						currtDay=1;
						currtMonth= parseInt(currtMonth)<10?"0"+(parseInt(currtMonth)+1):parseInt(currtMonth)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else{
						currtDay=1;
						currtMonth=1;
						currtYear=parseInt(currtYear)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}
				}else{
					//是同一个月
					currtDay=parseInt(currtDay)+1;
					day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
				}
			}else{
				//是同一年
				if(oDate1[1]!=oDate2[1]){
					//不是同一个月
					if(parseInt(currtMonth)<12 && mDays.indexOf(parseInt(currtMonth))!=-1 && currtDay<31){
						currtDay=parseInt(currtDay)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else if(parseInt(currtMonth)<12 && mDays.indexOf(parseInt(currtMonth))==-1 && currtDay<30){
						currtDay=parseInt(currtDay)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else if(parseInt(currtMonth)<12){
						currtDay=1;
						currtMonth= parseInt(currtMonth)<9?"0"+(parseInt(currtMonth)+1):parseInt(currtMonth)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}else{
						currtDay=1;
						currtMonth=1;
						currtYear=parseInt(currtYear)+1;
						day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
					}
				}else{
					//是同一个月
					currtDay=parseInt(currtDay)+1;
					day=currtMonth+"-"+(currtDay>=10?currtDay:"0"+currtDay);
				}
			}
			
			result.push(day);
		}
		return result;
	}
	
	var integral={
		init: function(){
			$selectSubMenu('query_statistics');
			$selectSubMenus("vip_payMaster");
			var $this=this;
			//日期列表
			$("#dateList").on("click","a.btn",function(){
				var mark=$(this).attr("mark");
				var status=$(this).hasClass("btn-success");
				if(!status){
					$(this).addClass("btn-success").siblings("a").removeClass("btn-success");
					if(mark=="nos"){
						$(".daterangs").removeClass("none");
					}else{
						$(".daterangs").addClass("none");
					}
				}
			});
			
	        $(".date-picker").datetimepicker({
	            language: 'zh-CN',
	            format: "yyyy-mm-dd",
	            minView: 2,
	            autoclose: true
	        });
	        //点击搜索
	        $(".search_vip_detail").on('click',function(){
	        	$this.search();
	        });
		},
		search: function(page){
			var $this=this;
			var data={};
			data.memberId = $("#stuMemberLevel").val();
            data.mobile = $("#stuMobile").val();
            data.stuName = $("#stuName").val();
            data.username = $("#username").val();
			data.page=page?page:1;
			//日期列表
			$("#dateList").find("a").each(function(){
				var st=$(this).hasClass("btn-success");
				if(st){
					mark="other";
					if($(this).attr("mark")=="nos"){
						mark="nos";
						data.startTime=$(".from").val();
						data.endTime=$(".to").val();
					}else{
						data.timeMark=$(this).attr("mark");
					}
				}
			});
			if(mark==""){
				
			}else if(mark=="nos"){
				if(!$(".from").val() || !$(".to").val()){
					$.msg("请指定日期范围");
					return false;
				}
				if ($(".to").val() != "") {
	                if ($(".to").val() < $(".from").val()) {
	                    $.msg("时间范围不正确");
	                    return;
	                }

	            }
				if(getDays($(".from").val(),$(".to").val()).length>90){
					$.msg("日期范围不能超过三个月");
					return;
				}
			}
			$.each(data,function(key,value){
				if(!value){
					delete data[key];
				}
			})
			$(".order_vipList").find("tr:gt(0)").remove();
			$.ajax({
				url : rootPath+"/query/queryVipOrder",
				data: data,
				dataType:'json',
				success: function(jsonData){
					if(jsonData.data.length==0){
						$(".order_vipList").append('<tr><td colspan="9">没有查找到数据</td></tr>');
					}
					$.each(jsonData.data,function(i,order){
						var payType_str="";
						if(order.payType!=null){
							var payTypes=order.payType.split(",");
						
							for ( var int=0 ;int<payTypes.length;int++) {
								if(payTypes[int]=="PAY_METHOD_CASH"){
									payType_str+="现金，";
								}
								if(payTypes[int]=="PAY_METHOD_POS"){
									payType_str+="POS，";
								}
								if(payTypes[int]=="PAY_METHOD_CHECK"){
									payType_str+="支票，";
								}
								if(payTypes[int]=="PAY_METHOD_ONLINE_PAY"){
									payType_str+="在线支付，";
								}
								if(payTypes[int]=="PAY_METHOD_LOAN"){
									payType_str+="贷款，";
								}
								if(payTypes[int]=="PAY_METHOD_TRANSFE"){
									payType_str+="转账，";
								}
								if(payTypes[int]=="PAY_TYPE_ZFB"){
									payType_str+="支付宝，";
								}
								if(payTypes[int]=="PAY_TYPE_SXY"){
									payType_str+="首信易在线支付，";
								}
								if(payTypes[int]=="PAY_TYPE_YYZF"){
									payType_str+="银联语音支付，";
								}
								if(payTypes[int]=="PAY_TYPE_KJZF"){
									payType_str+="银联快捷支付，";
								}
								if(payTypes[int]=="PAY_TYPE_GRDB"){
									payType_str+="个人担保交易，";
								}
								if(payTypes[int]=="PAY_TYPE_REMIT"){
									payType_str+="银行汇款，";
								}
								if(payTypes[int]=="PAY_TYPE_ZFB_ZZ"){
									payType_str+="支付宝转账，";
								}
								if(payTypes[int]=="PAY_TYPE_WX_PERSON"){
									payType_str+="微信个人收款，";
								}
								if(payTypes[int]=="PAY_TYPE_WX_GZH"){
									payType_str+="微信公众号支付，";
								}
							}
						}
						$(".order_vipList").append('<tr>'+
								'<td>'+(order.stuName?order.stuName:"")+'</td>'+
								'<td>'+(order.username?order.username:"")+'</td>'+
								'<td>'+(order.mobile?order.mobile:"")+'</td>'+
								'<td>'+(order.orderMemberLevel?order.orderMemberLevel:"")+'</td>'+
								'<td>'+(order.memberLength?order.memberLength==-1?"-":order.memberLength+"个月":"")+'</td>'+
								'<td>'+(order.orderTime?order.orderTime:"")+'</td>'+
								'<td>'+(payType_str==""?"其他":payType_str.substring(0,payType_str.length-1))+'</td>'+
								'<td>'+(order.payPrice?order.payPrice+"元":"0元")+'</td>'+
								'<td>'+(order.applyChannelCode=="CHANNEL_ONLINE"?order.commodityName?"线上"+order.commodityName:"":order.commodityName?"线下"+order.commodityName:"")+'</td>'+
								'</tr>');
					});
					
					if(jsonData.rowCount>12){
						$(".pagination").pagination(jsonData.rowCount, {
					    	 next_text : "下一页",
					    	 prev_text : "上一页",
					    	 current_page : jsonData.pageNo-1,
					    	 link_to : "javascript:void(0)",
					    	 num_display_entries : 8,
					    	 items_per_page : jsonData.pageSize,
					    	 num_edge_entries : 1,
					    	 callback:function(page,jq){
						    	 var pageNo = page + 1;
						    	 $this.search(pageNo);
					    	 }
					   });
					}else{
						$(".pagination").html('');
					}
				}
			});
		}
	}
	
	$(document).ready(function(){
		integral.init();
		 $.ajax({
	            url: rootPath+"/companyMemberConfig/selectVips",
	            type: "post",
	            dataType: "json",
	            success : function(vips){
	                $("#stuMemberLevel").empty(); 
	                $("#stuMemberLevel").append("<option value=''>会员等级</option>");
	                $.each(vips,function(i,item){  
	                    $("#stuMemberLevel").append("<option value='"+item.id+"'>"+item.name+"</option>");
	                });
	            }
	        })
	})
	window.integral=integral;
})(jQuery)

 $(".export_vip_detail").on(
                'click',
    function () {
        if ($(".order_vipList").find("tr").eq(1).find("td").length <= 1) {
            $.msg("没有数据可以导出");
        } else {
			//日期列表
			$("#dateList").find("a").each(function(){
				var st=$(this).hasClass("btn-success");
				if(st){
					mark="other";
					mark=$(this).attr("mark");
				}
			});
            $("#searchForm").attr("action",
                rootPath + "/query/exportVipOrder/"+mark)
                .submit();
        }

    });