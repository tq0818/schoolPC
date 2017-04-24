/**
 * 
 */

$(function(){
	var pattern =/^[0-9]+([.]\d{1,2})?$/;
	var pattern2 = /^(0|[1-9]\d*)$/;
	var oneBeinviteFlag = $('.btn-cro').find('#jiangliyiji').attr('class');
	function test1(num){
		if(num == '')
			return false;
		else if(isNaN(num))
			return false;
		else if(num<0)
			return false;
		else if(!pattern.test(num))
			return false;
		return true;
	}
	
	function test2(num){
		if(num == '')
			return false;
		else if(isNaN(num))
			return false;
		else if(num<0)
			return false;
		else if(!pattern2.test(num))
			return false;
		return true;
	}
	
	function test3(num){
		if(num == '')
			return false;
		else if(isNaN(num))
			return false;
		else if(num<0 || num >100)
			return false;
		else if(!pattern2.test(num))
			return false;
		return true;
	}
	$('#backservice1').on('click',function(){
		window.location.href=rootPath+'/company/companyService';
	})
		
	$('#backservice2').on('click',function(){
		window.location.href=rootPath+'/company/companyService';
	})
	
		$('.delate').on('click',function(){
			window.location.href=rootPath+'/company/companyService';
		})
	
	if(oneBeinviteFlag == 'i open'){
		
		$("#oneBeinviteFlag").val(1);
		
	}else {
		$("#oneBeinviteFlag").val(0);
		$('#oneBeinviteGetMoney').val('');
		$('#oneBeinviteMoneyPeriod').val('');
	}
	
	var flag = $('#yijidaijin').on('change',function(){
		var yijidaijin = $("#yijidaijin").val();
		if(yijidaijin == '' || isNaN(yijidaijin) || yijidaijin <0 || !pattern.test(yijidaijin))
			{
			$("#yijidaijinerror").text("请输入有效的金额(非负数值,小数保留两位)");
			$("#yijidaijin").focus();
			return;
			}
		else {
			$("#yijidaijinerror").text("");
			$("#oneBeinviteGetMoney").val(yijidaijin);
		}
	})
	$('#daijinPercent').on('change',function(){
		var daijinPercent = $('#daijinPercent').val();
		if(daijinPercent == '' || isNaN(daijinPercent) || daijinPercent <0 ||!pattern2.test(daijinPercent)){
			$('#daijinPercenterror').text("请输入有效的天数(非负整数)");
			$('#daijinPercent').focus();
			return;
		}else{
			$("#daijinPercenterror").text("");
			$("#oneBeinviteMoneyPeriod").val(daijinPercent);
		}
	})
	
	$('#erjiregdaijin').on('change',function(){
		var erjiregdaijin = $('#erjiregdaijin').val();
		if( erjiregdaijin == '' || isNaN(erjiregdaijin) || erjiregdaijin <0 || !pattern.test(erjiregdaijin)){
			$('#erjiregdaijinerror').text('请输入有效的金额(非负数值,小数点保留两位有效数字)');
			$('#erjiregdaijin').focus();
			return;
		}else{
			$('#erjiregdaijinerror').text('');
			$('#oneInviteRgstMoney').val(erjiregdaijin);
			$('#oneInviteRgstIntegral').val('-1');
		}
	})
	
	
	$('#erjiregdaijinradio').on('click',function(){
		if(($('#erjiregdaijinradio').is(':checked'))==true){
			$(this).parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
			find("input[type='text']").removeAttr("disabled");
			$('#erjiregjifenradio').prop('checked',false);
			$('#erjiregjifen').val('');
			$('#erjiregjifenerror').text('');
		}else {
			$('#erjiregdaijin').val('');
			$(this).parent().css("color", "#949494");
			$('#erjiregdaijin').attr('disabled','disabled');
		}
	})
	
	
	$('#erjiregjifen').on('change',function(){
		var erjiregjifen = $('#erjiregjifen').val();
		if( erjiregjifen == '' || isNaN(erjiregjifen) || erjiregjifen <0 || !pattern2.test(erjiregjifen)){
			$('#erjiregjifenerror').text('请输入有效的金额(非负整数)');
			$('#erjiregjifen').focus();
			return;
		}else{
			$('#erjiregjifenerror').text('');
			$('#oneInviteRgstIntegral').val(erjiregjifen);
			$('#oneInviteRgstMoney').val('-1');
		}
		
	})
	
	
	$('#erjiregjifenradio').on('click',function(){
		if(($('#erjiregjifenradio').is(':checked'))==true){
			$(this).parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
			find("input[type='text']").removeAttr("disabled");
			$('#erjiregdaijinradio').prop('checked',false);
			$('#erjiregdaijin').val('');
			$('#erjiregdaijinerror').text('');
		}else{
			$('#erjiregjifen').val('');
			$(this).parent().css("color", "#949494");
			$('#erjiregjifen').attr('disabled','disabled');
		}
	})
	
	$('#erjicondaijin').on('change',function(){
		var erjicondaijin = $('#erjicondaijin').val();
		if( erjicondaijin == '' || isNaN(erjicondaijin) || erjicondaijin <0 || !pattern.test(erjicondaijin)){
			$('#erjicondaijinerror').text('请输入有效的金额(非负数值,小数点保留两位有效数字)');
			$('#erjicondaijin').focus();
			return;
		}else{
			$('#erjicondaijinerror').text('');
			$('#oneInviteCsptMoney').val(erjicondaijin);
			$('#oneInviteCsptPercent').val('-1');
			$('#oneInviteCsptIntegral').val('-1');
		}
	})
	
	
	
	
	$('#erjicondaijinradio').on('click',function(){
		if(($('#erjicondaijinradio').is(':checked'))==true){
			$(this).parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
			find("input[type='text']").removeAttr("disabled");
			$('#erjitichengradio').prop('checked',false);
			$('#erjiconjifenradio').prop('checked',false);
			$('#erjiticheng').val('');
			$('#erjiconjifen').val('');
			$('#erjitichengerror').text('');
			$('#erjiconjifenerror').text('');
		}else{
			$('#erjicondaijin').val('');
			$(this).parent().css("color", "#949494");
			$('#erjicondaijin').attr('disabled','disabled');
		}
	})
	
	$('#erjiticheng').on('change',function(){
		var erjiticheng = $('#erjiticheng').val();
		if( erjiticheng == '' || isNaN(erjiticheng) || erjiticheng <0 || erjiticheng >100){
			$('#erjitichengerror').text('请输入有效的金额(非负数值且在0到100之内)');
			$('#erjiticheng').focus();
			return;
		}else{
			$('#erjitichengerror').text('');
			$('#oneInviteCsptPercent').val(erjiticheng);
			$('#oneInviteCsptMoney').val('-1');
			$('#oneInviteCsptIntegral').val('-1');
		}
	})
	
	$('#erjitichengradio').on('click',function(){
		if(($('#erjitichengradio').is(':checked'))==true){
			$(this).parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
			find("input[type='text']").removeAttr("disabled");
			$('#erjicondaijinradio').prop('checked',false);
			$('#erjiconjifenradio').prop('checked',false);
			$('#erjicondaijin').val('');
			$('#erjiconjifen').val('');
			$('#erjicondaijinerror').text('');
			$('#erjiconjifenerror').text('');
		}else{
			$('#erjiticheng').val('');
			$(this).parent().css("color", "#949494");
			$('#erjiticheng').attr('disabled','disabled');
		}
	})
	
	$('#erjiconjifen').on('change',function(){
		var erjiconjifen = $('#erjiconjifen').val();
		if( erjiconjifen == '' || isNaN(erjiconjifen) || erjiconjifen <0 || !pattern2.test(erjiconjifen)){
			$('#erjiconjifenerror').text('请输入有效的金额(非负数值)');
			$('#eerjiconjifen').focus();
			return;
		}else{
			$('#erjiconjifenerror').text('');
			$('#oneInviteCsptIntegral').val(erjiconjifen);
			$('#oneInviteCsptPercent').val('-1');
			$('#oneInviteCsptMoney').val('-1');
		}
	})
	
	$('#erjiconjifenradio').on('click',function(){
		if(($('#erjiconjifenradio').is(':checked'))==true){
			$(this).parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
			find("input[type='text']").removeAttr("disabled");
			$('#erjicondaijinradio').prop('checked',false);
			$('#erjitichengradio').prop('checked',false);
			$('#erjicondaijin').val('');
			$('#erjiticheng').val('');
			$('#erjicondaijinerror').text('');
			$('#erjitichengerror').text('');
		}else{
			$('#erjiconjifen').val('');
			$(this).parent().css("color", "#949494");
			$('#erjiconjifen').attr('disabled','disabled');
		}
	})
	
	$('#sanjiregdaijin').on('change',function(){
			var sanjiregdaijin = $('#sanjiregdaijin').val();
			if(sanjiregdaijin == '' || isNaN(sanjiregdaijin) || sanjiregdaijin <0 ||!pattern.test(sanjiregdaijin)){
				$('#sanjiregdaijinerror').text('请输入有效的代金额(非负数值,小数点保留两位有效数字)');
				$('#sanjiregdaijin').focus();
				return;
			}else{
				$('#sanjiregdaijinerror').text('');
				$("#twoInviteRgstMoney").val(sanjiregdaijin);
				$("#twoInviteRgstIntegral").val('-1');
			}
		})
		
		
		
		
		
		
		$('#sanjiregdaijinradio').on('click',function(){
			if(($('#sanjiregdaijinradio').is(':checked'))==true){
				$(this).parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
				find("input[type='text']").removeAttr("disabled");
				$('#sanjiregjifenradio').prop('checked',false);
				$('#sanjiregjifen').val('');
				$('#sanjiregjifenerror').text('');
				
			}else{
				$('#sanjiregdaijin').val('');
				$("#twoInviteRgstMoney").val("-1");
				$(this).parent().css("color", "#949494");
				$('#sanjiregdaijin').attr('disabled','disabled');
			}
		})
		
		$('#sanjiregjifen').on('change',function(){
			var sanjiregjifen = $('#sanjiregjifen').val();
			if(sanjiregjifen == '' || isNaN(sanjiregjifen) || sanjiregjifen < 0 ||!pattern2.test(sanjiregjifen)){
				$('#sanjiregjifenerror').text('请输入有效的积分值(非负整数)');
				$('#sanjiregjifen').focus();
				return;
			}else{
				$('#sanjiregjifenerror').text('');
				$("#twoInviteRgstIntegral").val(sanjiregjifen);
				$("#twoInviteRgstMoney").val('-1');
			}
		})
		
		
		$('#sanjiregjifenradio').on('click',function(){
			if(($('#sanjiregjifenradio').is(':checked'))==true){
				$(this).parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
				find("input[type='text']").removeAttr("disabled");
				$('#sanjiregdaijinradio').prop('checked',false);
				$('#sanjiregdaijin').val('');
				$('#sanjiregdaijinerror').text('');
			}else{
				$('#sanjiregjifen').val('');
				$("#twoInviteRgstIntegral").val('-1');
				$(this).parent().css("color", "#949494");
				$('#sanjiregjifen').attr('disabled','disabled');
			}
		})
		
		$('#sanjicondaijin').on('change',function(){
			var sanjicondaijin = $('#sanjicondaijin').val();
			if(sanjicondaijin == '' || isNaN(sanjicondaijin) || sanjicondaijin <0 || !pattern.test(sanjicondaijin)){
				$('#sanjicondaijinerror').text('请输入有效的代金额(非负数值,小数点保留两位有效数字)');
				$('#sanjicondaijin').focus();
				return;
			}else{
				$('#sanjicondaijinerror').text('');
				$("#twoInviteCsptMoney").val(sanjicondaijin);
				$("#twoInviteCsptPercent").val('-1');
				$("#twoInviteCsptIntegral").val('-1');
			}
		})
		
		
		
		$('#sanjicondaijinradio').on('click',function(){
			if(($('#sanjicondaijinradio').is(':checked'))==true){
				$(this).parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
				find("input[type='text']").removeAttr("disabled");
				$('#sanjicontichengradio').prop('checked',false);
				$('#sanjiconjifenradio').prop('checked',false);
				$('#sanjiconticheng').val('');
				$('#sanjiconjifen').val('');
				$('#sanjicontichengerror').text('');
				$('#sanjiconjifenerror').text('');
			}else{
				$('#sanjicondaijin').val('');
				$(this).parent().css("color", "#949494");
				$('#sanjicondaijin').attr('disabled','disabled');
			}
		})
		
		
		$('#sanjicontichengradio').on('click',function(){
			if(($('#sanjicontichengradio').is(':checked'))==true){
				$(this).parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
				find("input[type='text']").removeAttr("disabled");
				$('#sanjicondaijinradio').prop('checked',false);
				$('#sanjiconjifenradio').prop('checked',false);
				$('#sanjicondaijin').val('');
				$('#sanjiconjifen').val('');
				$('#sanjicondaijinerror').text('');
				$('#sanjiconjifenerror').text('');
			}else{
				$('#sanjiconticheng').val('');
				$(this).parent().css("color", "#949494");
				$('#sanjiconticheng').attr('disabled','disabled');
			}
		})
		
		$('#sanjiconticheng').on('change',function(){
			var sanjiconticheng = $('#sanjiconticheng').val();
			if(sanjiconticheng == '' || isNaN(sanjiconticheng) || sanjiconticheng <0 || sanjiconticheng >100){
				$('#sanjicontichengerror').text('请输入有效的提成值(非负数值且在0到100之间)');
				$('#sanjiconticheng').focus();
				return;
			}else{
				$('#sanjicontichengerror').text('');
				$("#twoInviteCsptPercent").val(sanjiconticheng);
				$("#twoInviteCsptMoney").val('-1');
				$("#twoInviteCsptIntegral").val('-1');
			}
		})
		
		$('#sanjiconjifenradio').on('click',function(){
			if(($('#sanjiconjifenradio').is(':checked'))==true){
				$(this).parent().css("color", "#333333").siblings(".text-right").css("color", "#333333").
				find("input[type='text']").removeAttr("disabled");
				$('#sanjicontichengradio').prop('checked',false);
				$('#sanjicondaijinradio').prop('checked',false);
				$('#sanjicondaijin').val('');
				$('#sanjiconticheng').val('');
				$('#sanjicondaijinerror').text('');
				$('#sanjiconjifenerror').text('');
			}else{
				$('#sanjiconjifen').val('');
				$(this).parent().css("color", "#949494");
				$('#sanjiconjifen').attr('disabled','disabled');
			}
		})
		
		$('#sanjiconjifen').on('change',function(){
			var sanjiconjifen = $('#sanjiconjifen').val();
			if(sanjiconjifen == '' || isNaN(sanjiconjifen) || sanjiconjifen <0 || !pattern2.test(sanjiconjifen)){
				$('#sanjiconjifenerror').text('请输入有效的积分值(非负整数)');
				$('#sanjiconjifen').focus();
				return;
			}else{
				$('#sanjiconjifenerror').text('');
				$("#twoInviteCsptIntegral").val(sanjiconjifen);
				$("#twoInviteCsptPercent").val('-1');
				$("#twoInviteCsptMoney").val('-1');
			}
		})
	
	var erjiyaoqing = $('#erjiyaoqing').attr('class');
	if(erjiyaoqing == 'qi open'){
		$('#twoInviteFlag').val(1);
		
		
	}else{
		$('#twoInviteFlag').val(0);
	}
	
	
	$('.save').on('click',function(){
		var usePriv = $('.user-choses-selected').attr('value');
		if(usePriv == 'ueser2'){
			$('#usePriv').val('COUPON_OBJECT_STUDENT');
		}else if(usePriv == 'ueser1'){
			$('#usePriv').val('COUPON_OBJECT_ALL');
		}else {
			$('#usePriv').val('COUPON_OBJECT_MEMBER');
		}
		
		
		var oneBeinviteFlag = $('.btn-cro').find('#jiangliyiji').attr('class');
		if(oneBeinviteFlag == 'i open'){
			
			$("#oneBeinviteFlag").val(1);
			
		}else {
			$("#oneBeinviteFlag").val(0);
		}
		
		if(oneBeinviteFlag == 'i open'){
			var yijidaijin = $("#yijidaijin").val();
			if(!test1(yijidaijin)){
				$("#yijidaijinerror").text("金额不能为空或格式不正确");	
				if(yijidaijin == '' || yijidaijin == undefined || yijidaijin == null)
				$("#yijidaijin").focus();
				return;
			}
			
			var daijinPercent = $('#daijinPercent').val();
			if(daijinPercent == '' || daijinPercent == undefined ||daijinPercent == null ||isNaN(daijinPercent) || daijinPercent <0 ||!pattern2.test(daijinPercent)){
				$('#daijinPercenterror').text("天数不能为空或格式不正确");
				$('#daijinPercent').focus();
				return;
			}
		}
		
		
		if(($('#erjiregdaijinradio').is(':checked'))==true){
			var erjiregdaijin = $('#erjiregdaijin').val();
			if(!test1(erjiregdaijin)){
				$('#erjiregdaijinerror').text('金额为空或格式不正确');
				$('#erjiregdaijin').focus();
				return;
			}
			
		}else{
			$('#oneInviteRgstMoney').val('');
		}
		
		
		
		if(($('#erjiregjifenradio').is(':checked'))==true ){
			var erjiregjifen = $('#erjiregjifen').val();
			if(!test2(erjiregjifen)){
				$('#erjiregjifenerror').text('积分不能为空或格式不正确');
				$('#erjiregjifen').focus();
				return;
			}
		}else{
			$('#oneInviteRgstIntegral').val('');
		}
		
		if(($('#erjicondaijinradio').is(':checked'))==true){
			var erjicondaijin = $('#erjicondaijin').val();
			if(!test1(erjicondaijin)){
				$('#erjicondaijinerror').text('代金券金额不能为空或格式不正确');
				$('#erjicondaijin').focus();
				return;
			}
		}else{
			$('#oneInviteCsptMoney').val('');
		}
		
		if(($('#erjitichengradio').is(':checked'))==true){
			var erjiticheng = $('#erjiticheng').val();
			if(!test3(erjiticheng)){
				$('#erjitichengerror').text('提成百分比不能为空或格式不正确（0-100）');
				$('#erjiticheng').focus();
				return;
			}
		}else{
			$('#oneInviteCsptPercent').val('');
		}
		
		
		if(($('#erjiconjifenradio').is(':checked'))==true){
			var erjiconjifen = $('#erjiconjifen').val();
			if(!test2(erjiconjifen)){
				$('#erjiconjifenerror').text('积分数值不能为空或格式不正确');
				$('#erjiconjifen').focus();
				return;
			}
		}else{
			$('#oneInviteCsptIntegral').val('');
		}
		
		
		
		
		
		var erjiyaoqing = $('#erjiyaoqing').attr('class');
		if(erjiyaoqing == 'qi open'){
			$('#twoInviteFlag').val(1);
			if(($('#sanjiregdaijinradio').is(':checked'))==true){
				var sanjiregdaijin = $('#sanjiregdaijin').val();
				if(!test1(sanjiregdaijin)){
					$('#sanjiregdaijinerror').text('代金额不能为空或格式不正确');
					$('#sanjiregdaijin').focus();
					return;
				}
			}else{
				$('#twoInviteRgstMoney').val('');
			}
			
			
			if(($('#sanjicondaijinradio').is(':checked'))==true){
				var sanjicondaijin = $('#sanjicondaijin').val();
				if(!test1(sanjicondaijin)){
					$('#sanjicondaijinerror').text('代金额不能为空或格式不正确');
					$('#sanjicondaijin').focus();
					return;
				}
			}else{
				$('#twoInviteCsptMoney').val('');
			}
			
			
			
			if(($('#sanjiregjifenradio').is(':checked'))==true){
				var sanjiregjifen = $('#sanjiregjifen').val();
				if(!test2(sanjiregjifen)){
					$('#sanjiregjifenerror').text('积分不能为空或格式不正确');
					$('#sanjiregjifen').focus();
					return;
				}
			}else{
				$('#twoInviteRgstIntegral').val('');
			}
			
			if(($('#sanjicontichengradio').is(':checked'))==true){
				var anjiconticheng = $('#sanjiconticheng').val();
				if(!test3(anjiconticheng)){
					$('#sanjicontichengerror').text('提成不能为空或格式不正确（0-100）');
					$('#sanjiconticheng').focus();
					return;
				}
			}else{
				$('#twoInviteCsptPercent').val('');
			}
			
			if(($('#sanjiconjifenradio').is(':checked'))==true){
				var sanjiconjifen = $('#sanjiconjifen').val();
				if(!test2(sanjiconjifen)){
					$('#sanjiconjifenerror').text('积分不能为空或格式不正确');
					$('#sanjiconjifen').focus();
					return;
				}
			}else{
				$('#twoInviteCsptIntegral').val('');
			}
		}else{
			$('#twoInviteFlag').val(0);
		}
		
		var openFlag = $('.screen-right-title').find('.i').html();
		if (openFlag == '已启用'){
			$('#openFlag').val(1);
		}
		else {
			$('#openFlag').val(0);
		}
		
		if(($('#erjiregdaijinradio').is(':checked'))==false&&($('#erjiregjifenradio').is(':checked'))==false&&($('#erjicondaijinradio').is(':checked'))==false
				&&($('#erjicontichengradio').is(':checked'))==false&&($('#erjiconjifenradio').is(':checked'))==false){
			$.alert('至少选择一种奖励行为(注册或消费)');
			return;
		}
		
		if(erjiyaoqing == 'qi open'){
			if(($('#sanjicondaijinradio').is(':checked'))==false&&($('#sanjiconjifenradio').is(':checked'))==false
					&&($('#sanjicontichengradio').is(':checked'))==false&&($('#sanjiregdaijinradio').is(':checked'))==false
					&&($('#sanjiregjifenradio').is(':checked'))==false){
				$.alert('请至少为二级邀请人选择一种 奖励行为（注册或消费）或者关闭二级邀请');
				return;
			}
		}
		$.ajax({
			
			 type:"POST",
			 url :rootPath + "/companyInvitConfig/saveOrUpdateCompanyInvitConfig",
			 data :{
				 "usePriv":$('#usePriv').val(),"openFlag":$('#openFlag').val(),"oneBeinviteFlag":$('#oneBeinviteFlag').val(),"oneBeinviteGetMoney":$('#oneBeinviteGetMoney').val(),
				 "oneBeinviteMoneyPeriod":$('#oneBeinviteMoneyPeriod').val(),"oneInviteRgstMoney":$('#oneInviteRgstMoney').val(),"oneInviteRgstIntegral":$('#oneInviteRgstIntegral').val(),"oneInviteCsptMoney":$('#oneInviteCsptMoney').val(),
				 "oneInviteCsptIntegral":$('#oneInviteCsptIntegral').val(),"oneInviteCsptPercent":$('#oneInviteCsptPercent').val(),"twoInviteRgstMoney":$('#twoInviteRgstMoney').val(),"twoInviteRgstIntegral":$('#twoInviteRgstIntegral').val(),"twoInviteCsptMoney":$('#twoInviteCsptMoney').val(),
				 "twoInviteCsptIntegral":$('#twoInviteCsptIntegral').val(),"twoInviteCsptPercent":$('#twoInviteCsptPercent').val(),"twoInviteFlag":$('#twoInviteFlag').val()
			 },
			 success:function(data){
				 if(data == '1')
					 $.alert('操作成功',null);
				 else{
					 $.alert('操作失败',null);
				 }
			 }
			
		});
		
	})
})
