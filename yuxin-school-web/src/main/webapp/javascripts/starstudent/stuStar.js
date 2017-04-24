$(function(){
	$(".goBack").click(function(){
		location.href=rootPath+"/company/companyService";
	});
	$(".screen-left-list .active").click(function(){
		location.href=rootPath+"/starStudent/stuStar";
	});
	$selectSubMenu('org_service');
});