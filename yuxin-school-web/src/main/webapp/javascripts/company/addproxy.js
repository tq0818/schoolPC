/**
 * 
 */

$(function(){
	$selectMenu('resource_org');
	$('.proxyBtn,.cancel').click(function(){
		window.location.href=rootPath+"/companyConfigProxyOrg/viewPrxoyList";
	})
	var reg = new  RegExp("^\\d+$");
	var reg2 = new RegExp('^[0-9]+([.]{1}[0-9]{1,2})?$');
	var mobilereg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	var prefixreg = new RegExp("^[A-Za-z]+$");
	var error = "";
	$.ajax({
		url:rootPath+"/companyInvitConfigOrg/checkCustomSetting",
		type:"post",
		success:function(jsondata){
			if(jsondata=="success"){
				$('#customsetting').show();
			}
			else
				$('#customsetting').hide();
		}
	})
	$.ajax({
		url:rootPath+"/sysConfigDivision/getProvince",
		type:"POST",
		success:function(data){
			if(data&&data.provinces){
				$.each(data.provinces,function(i,pro){
					$('#province').append('<option value="'+pro.id+'">'+pro.name+'</option>');
					if(provinceid&&provinceid==pro.id){
						console.log(provinceid);
						$('#province option[value='+provinceid+']').attr('selected',true);
						if(cityid){
							$.ajax({
								url:rootPath+"/sysConfigDivision/getCityByPid",
								data:{"pid":provinceid},
								type:"POST",
								success:function(data){
									if(data&&data.citys){
										$('#city').find('option').remove();
										$.each(data.citys,function(i,city){
											$('#city').append('<option value="'+city.id+'">'+city.name+'</option>');
											if(cityid&&cityid==city.id){
												console.log(cityid);
												$('#city option[value='+cityid+']').attr('selected',true);
											}
										})
									}
								}
							})
						}
					}
				})
			}
		}
	})
	
	
	$('#province').on('change',function(){
		var pid = $(this).val();
		if(pid){
			$.ajax({
				url:rootPath+"/sysConfigDivision/getCityByPid",
				data:{"pid":pid},
				type:"POST",
				success:function(data){
					if(data&&data.citys){
						$('#city').find('option').remove();
						$.each(data.citys,function(i,city){
							$('#city').append('<option value="'+city.id+'">'+city.name+'</option>');
						})
					}
				}
			})
		}else{
			$('#city').find('option').remove();
			$('#city').html('<option value="">请选择</option>');
		}
	})
	
	$('#prefix1').on('change',function(){
		if($(this).val()){
			//prefix = prefix.toUpperCase();
			var pp = $(this).val();
			$.ajax({
				url:rootPath+"/companyConfigProxyOrg/checkPrefix",
				data:{"prefix":pp,"id":pid,"prefixu":prefixu},
				type:"POST",
				success:function(jsondata){
					if(jsondata=="fail"){
						$.msg('已有该代理商前缀（请重新设置）！');
						$('input[name="prefix"]').focus();
						error = "1";
						return;
					}else
						error = "";
				}
			})
		}
	})
	
	$('.save').on('click',function(){
		if($(this).hasClass('ok'))
			return;
		if(error){
			$.msg('已有该代理商前缀（请重新设置）！');
			$('input[name="prefix"]').focus();
			return;
		}
		var name=$('input[name="name"]').val();
		if(!name){
			$.alert('请填写代理机构名称！');
			$('input[name="name"]').focus();
			return;
		}
		var contracter = $('input[name="contracter"]').val();
		if(!contracter){
			$.alert('请填写联系人名称！');
			$('input[name="contracter"]').focus();
			return;
		}
		
		var contractNumber = $('input[name="contractNumber"]').val();
		if(!contractNumber){
			$('input[name="contractNumber"]').focus();
			$.alert('请填写联系人手机号码！');
			return;
		}
		if(!mobilereg.test(contractNumber)){
			$.alert('请填写正确的手机号码！');
			$('input[name="contractNumber"]').focus();
			return;
		}
		
		if($('#remark').val()){
			$('input[name="remark"]').val($('#remark').val());
		}
		var prefix = $('input[name="prefix"]').val();
		if(!prefix){
			$.alert('请填写代理商前缀');
			$('input[name="prefix"]').focus();
			return;
		}
		if(!prefixreg.test(prefix)||prefix.length>4){
			$.alert('请填写正确的代理商前缀（大小写字母且长度不能超过4个）！');
			$('input[name="prefix"]').focus();
			return;
		}
		
		var commissionRate = $('input[name="commissionRate"]').val();
		if(commissionRate&&!reg2.test(commissionRate)){
			$.alert('请填写正确的提成比例（小数点保留两位有效数字）！');
			$('input[name="commissionRate"]').focus();
			return;
		}
		var pname="";
		var cname="";
		var detailAddress="";
		var address="";
		if($('select[name="province"]').val()){
			pname = $('select[name="province"]').find("option:selected").html();
		}
		if($('select[name="city"]').val()){
			 cname= $('select[name="city"]').find("option:selected").html();
		}
		if($('input[name="detailAddress"]').val()){
			 detailAddress = $('input[name="detailAddress"').val();
		}
		if(detailAddress)
			address =  pname+cname+"&"+detailAddress;
		else
			address = pname+cname;
		$('input[name="address"]').val(address);
		if($('.save').hasClass('ok')||error)
			return ;
		$('.save').addClass('ok');
		$.ajax({
			url:rootPath+"/companyConfigProxyOrg/addOrUpdateProxy",
			type:"POST",
			data:$('#proxyForm').serialize(),
			success:function(jsondata){
				if(jsondata=="success"){
					$.msg('操作成功');
					window.location.href=rootPath+"/companyConfigProxyOrg/viewPrxoyList";
				}else{
					$('.save').removeClass('ok');
					$.msg('操作失败');
				}
				//window.location.href=rootPath+"/companyConfigProxyOrg/viewPrxoyList";
			}
		})
	})
})
