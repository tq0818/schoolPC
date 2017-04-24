var res = null;
$(function() {
	$("#editdomain").click(function(){
		var companyId = $("#companyId").val();
		if(res != null){
			res.abort();
		}
		res = $.ajax({
			url : rootPath+"/companyMemberService/findCompanyLevel",
			type : "get",
			data : {"companyId":companyId},
			success : function(data){
				if(data == "novip"){
					$('<div class="c-fa">'+ "只有企业标准版及以上版本才可以使用自主域名" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					return;
				}
				if(data == "vip"){
					location.href = rootPath+"/companyMemberService/editDomain";
					return;
				}
			}
		});
	});
	$(".btn-authen").click(function(){
		//认证页面
		if(res != null){
			res.abort();
		}
		res = $.ajax({
			url : rootPath + "/company/authenShow",
			type:"post",
			dataType:"json",
			success:function(data){
				if(data.msg == "success"){
					window.location.href = rootPath + "/companyAuthority/show";
				}else if(data.msg == "error"){
					$('<div class="c-fa">'+ '您没有操作权限！' +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){$(this).remove();});
				}else{
					$('<div class="c-fa">'+ data.msg +'</div>').appendTo('body').fadeIn(100).delay(2000).fadeOut(200,function(){$(this).remove();});
				}
			}
		});
	});
});

