function forwardCourse(){
	var id=$("#courseHeadPri").val();
	if(id==1){
		window.location.href=rootPath+"/classManage/manageCourseValidaty";
	}
}
function forwardnetSchool(){
	var id=$("#netschoolPri").val();
	if(id==1){
		window.location.href=rootPath+"/sysPageHeadFoot/showHead";
	}
}
function forwardLive(){
	var id=$("#liveServicePri").val();
	if(id==1){
		window.location.href=rootPath+"/companyMemberService/toLiveStatistics";
	}
}
function forwardVideo(){
	var id=$("#videoServicePri").val();
	if(id==1){
		window.location.href=rootPath+"/companyMemberService/toVideoStatistics";
	}
}
function forwardMarking(){
	var id=$("#marketingServicePri").val();
	if(id==1){
		window.location.href=rootPath+"/companyMarketSet/showCompanyMarket";
	}
}
function forwardSms(){
	var id=$("#smsServicePri").val();
	if(id==1){
		window.location.href=rootPath+"/companyMemberService/toMessageStatistics";
	}	
}
function forwardEmail(){
	var id=$("#emailServicePri").val();
	if(id==1){
		window.location.href=rootPath+"/companyMemberService/toEmailStatistics";
	}	
}
function forwardOpenCourse(){
	var id=$("#openClassServicePri").val();
	if(id==1){
		window.location.href=rootPath+"/liveOpenCourse/showLiveOpenClassSetting";
	}
}
function forwardTiku(){
	var id=$("#tikuServicePri").val();
	if(id==1){
		window.location.href=rootPath+"/tikuSet/toSet/stuFree";
	}
}
function forwardPaySet(){
	var id=$("#payServicePri").val();
	if(id==1){
		window.location.href=rootPath+"/payConfig/showPayChoice";
	}
}
function forwardQuest(){
	var id=$("#questServicePri").val();
	if(id==1){
		window.location.href=rootPath+"/Question/questionSetIndex";
	}
}
function forwardSchool(){
	var id=$("#schoolServicePri").val();
	if(id==1){
		window.location.href=rootPath+"/companyMemberService/openSchool";
	}
}
function forwardclassPackage(){
	var id=$("#classPackageServicePri").val();
	if(id==1){
		window.location.href=rootPath+"/classPackageCategory/SetClassPackageCategory";
	}
}
function forwardIntegral(){
	var id=$("#integralServicePri").val();
	if(id==1){
		window.location.href=rootPath+"/companyIntegralConfig/openIntegralSet";
	}
}
function forwardMember(){
	var id=$("#memberServicePri").val();
	if(id==1){
		window.location.href=rootPath+"/companyMemberConfig/addUI";
	}
}
function forwardApp(){
	var id=$("#appServicePri").val();
	if(id==1){
		window.location.href=rootPath+"/companyAppBarConfig/configBaseInfo";
	}
}
function forwardpromotion(){
	var id=$("#promotionServicePri").val();
	if(id==1){
		window.location.href=rootPath+"/company/setCouponService";
	}
}
function forwardStudentSet(){
	var id=$("#studentSet").val();
	if(id==1){
		var url = $("a.stu-set:first").attr("href");
		window.location.href = url;
	}
}
function forwardMicroSchool(){
	var id=$("#microSchoolPri").val();
	if(id==1){
		var url = $("a.micro-set:first").attr("href");
		window.location.href = url;
	}
}

function forwardStudentAspirations(){
	var id=$("#studentAspirationsPri").val();
	if(id==1){
		window.location.href = rootPath + "/studentStar/showImg";
	}
}

///*新功能提示*/
//function newfn(newList){
//	if(newList.length>0){
//		$(".service-list .left .s-name").each(function(i, dom){
//			if($.inArray($.trim($(this).find("a").html()), newList)!=-1){
//				$(this).append('<br><span class="add_text">有新增功能</span>');
//			}
//		})
//	}
//}

//$(function(){
//	var newList=new Array();
//	newList=["网校设置"];  //配置新功能列表
//	newfn(newList);
//})