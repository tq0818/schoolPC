/**
 * 
 */

$(function(){
	
	var reg = new  RegExp("^\\d+$");
	var reg2 = new RegExp('^[0-9]+([.]{1}[0-9]{1,2})?$');
	
	$('#1thbtn').click(function(){
		if($(this).hasClass('close')){
			$.ajax({
				type:"POST",
				data:{"flag":"1"},
				url:rootPath+"/companyInvitConfigTeacher/updateBtn",
				success:function(data){
				}
			})
			$(this).removeClass('close').addClass('open').html('&#xe603;').next('span').eq(0).removeClass('close').addClass('open').html('&nbsp;&nbsp;已启用');
			$('.footerSetBox-fen').show();
		}else{
			$.ajax({
				type:"POST",
				data:{"flag":"0"},
				url:rootPath+"/companyInvitConfigTeacher/updateBtn",
				success:function(data){
				}
			})
			$(this).removeClass('open').addClass('close').html('&#xe604;').next('span').eq(0).removeClass('open').addClass('close').html('&nbsp;&nbsp;已禁用');
			$('.footerSetBox-fen').hide();
		}
	})
	
	$('#2secbtn').click(function(){
		if($(this).hasClass('close')){
			$(this).removeClass('close').addClass('open').html('&#xe603;').next('span').eq(0).removeClass('close').addClass('open').html('&nbsp;&nbsp;已启用');
			$('.stubox').show();
		}else{
			$(this).removeClass('open').addClass('close').html('&#xe604;').next('span').eq(0).removeClass('open').addClass('close').html('&nbsp;&nbsp;已禁用');
			$('.stubox').hide();
		}
	})
	
	 //$('.inviteList-uncheck').find('.numberday').attr("disabled", "true");
    $(".chet").on('click', function() {
        if ($(this).is(':checked')) {
        	$(this).parent().siblings().find('.chet').eq(0).prop('checked',false).siblings('.numberday').attr("disabled", "true");
        	if($(this).parent().hasClass('inviteList-uncheck'))
        	$(this).parent().removeClass('inviteList-uncheck').siblings().addClass('inviteList-uncheck');
            $(this).siblings('.numberday').removeAttr("disabled"); 
            $(this).parent().siblings().find('.numberday').val("");
        }
    })
    
    $('.btn-success').on('click',function(){
    	var errorMsg;
    	var invitRgstAward;
    	var invitRgstAwardPeriod;
    	var invitCastAwardFlag;
    	var invitCastAward;
    	var castTypeCourse;
    	var castTypePackage;
    	var castTypeMember;
    	var castTypeIntegral;
    	var stuRewardsFlag;
    	var data={};
    	if($('#2secbtn').hasClass('open')){
    		stuRewardsFlag = 1;
    		invitRgstAward = $('#invitRgstAward').val();
    		if(!invitRgstAward){
    			errorMsg="代金券金额不能为空！";
    			$('#invitRgstAward').val('');
    			$.alert(errorMsg);
    			$('#invitRgstAward').focus();
    			return;
    		}
    		else if(!reg2.test(invitRgstAward)){
    			errorMsg="请输入正确的格式（正数且保留小数点后两位）！";
    			$('#invitRgstAward').val('');
    			$.alert(errorMsg);
    			$('#invitRgstAward').focus();
    			return;
    		}
    		
    		invitRgstAwardPeriod = $('#invitRgstAwardPeriod').val();
    		if(!invitRgstAwardPeriod){
    			errorMsg="代金券有效天数不能为空！";
    			$('#invitRgstAwardPeriod').val('');
    			$.alert(errorMsg);
    			$('#invitRgstAwardPeriod').focus();
    			return;
    		}
    		if(invitRgstAwardPeriod=='0'){
    			errorMsg="代金券有效天数不能为0！";
    			$('#invitRgstAwardPeriod').val('');
    			$.alert(errorMsg);
    			$('#invitRgstAwardPeriod').focus();
    			return;
    		}
    		else if(!reg.test(invitRgstAwardPeriod)){
    			errorMsg="请输入正确的格式（正数）！";
    			$('#invitRgstAwardPeriod').val('');
    			$.alert(errorMsg);
    			$('#invitRgstAwardPeriod').focus();
    			return;
    		}
    	}else{
    		stuRewardsFlag = 0;
    	}
    	//console.log($('#tichenglist').find('.chet').length);
    	if(!$('#tichenglist').find('.chet').eq(0).is(':checked')&&!$('#tichenglist').find('.chet').eq(1).is(':checked')){
    		errorMsg="请设置老师提成金额！";
    		$.alert(errorMsg);
    		return;
    	}
    	if($('#tichenglist').find('.chet').eq(0).is(':checked')){
    		invitCastAwardFlag = 1;
    		invitCastAward = $('#tichenglist').find('.ticheng').eq(0).val();
    		if(!invitCastAward){
    			errorMsg="老师提成金额不能为空！";
    			$.alert(errorMsg);
    			return;
    		}
    		if(!reg2.test(invitCastAward)){
    			errorMsg = '请设置正确的老师提成金额（正数小数点保留两位！）';
    			$.alert(errorMsg);
    			return;
    		}
    	}
    	else{
    		invitCastAwardFlag = 0;
    		invitCastAward = $('#tichenglist').find('.ticheng').eq(1).val();
    		if(!invitCastAward){
    			errorMsg="老师提成金额不能为空！";
    			$.alert(errorMsg);
    			return;
    		}
    		if(!reg2.test(invitCastAward)){
    			errorMsg = '请设置正确的提成金额（正数，小数点保留两位）';
    			$.alert(errorMsg);
    			return;
    		}
    	}
    	
    	var length=0;
    	$('.classficat').find('.inpche').each(function(){
    		if($(this).is(':checked')){
    			length++;
    		}
    	})
    	if(length==0){
    		errorMsg="请至少选择一种奖励的消费类型";
    		$.alert(errorMsg);
    		return;
    	}
    	if($('#castTypeCourse').is(':checked'))
    		castTypeCourse=1;
    	else
    		castTypeCourse=0;
    	if($('#castTypePackage').is(':checked'))
    		castTypePackage=1;
    	else
    		castTypePackage=0;
    	if($('#castTypeMember').is(':checked'))
    		castTypeMember=1;
    	else
    		castTypeMember=0;
    	if($('#castTypeIntegral').is(':checked'))
    		castTypeIntegral=1;
    	else
    		castTypeIntegral=0;
    	data.openFlag =1;
    	data.invitRgstAward=invitRgstAward
    	data.invitRgstAwardPeriod=invitRgstAwardPeriod;
    	data.invitCastAwardFlag=invitCastAwardFlag;
    	data.invitCastAward=invitCastAward;
    	data.castTypeCourse=castTypeCourse;
    	data.castTypePackage=castTypePackage;
    	data.castTypeMember=castTypeMember;
    	data.castTypeIntegral=castTypeIntegral;
    	data.stuRewardsFlag=stuRewardsFlag;
    	$.ajax({
			
			 type:"POST",
			 url :rootPath + "/companyInvitConfigTeacher/saveOrUpdateCompanyInvitConfigTeacher",
			 data :data,
			 success:function(data){
				 if(data == 'success')
					 $.msg('操作成功');
				 else{
					 $.msg('操作失败');
				 }
			 }
			
		});
    })
})
