// 分校首页，创建分校弹窗
$(function () {
    //点击创建分校弹窗弹框
    $('.createBranch').click(function () {
        $('.popupContainer').show();
        $('.popupOpacity').show();
    });
    //点击确定关闭弹窗
    

    //点击取消关闭弹窗
    $('.submitSchoolCancel').click(function () {
        $('.popupContainer').hide();
        $('.popupOpacity').hide();
    })
})

function addBerkeley(){
		var branchCode=$("#branchCode").val();
		if(null==branchCode || ''==branchCode){
			alert("分校机构代码不能为空");
			return;
		}
		var branchSchool=$("#branchSchool").text();
		var isArea=$("#isArea").val();
		var eara=$("#eara").text();
		var schoolProperties=$("#schoolProperties").val();
		var linkPerson=$("#linkPerson").val();
		var linkPhone=$("#linkPhone").val();
		var domain=$("#domain").val();
		if(null!=$("#domain").val() && ''!=$("#domain").val()){
			var domain='www.'+$("#domain").val()+'.cdds365.com';
		}
		if(null!=$("#domainManage").val() && ''!=$("#domainManage").val()){
			var domainManage='www.'+$("#domainManage").val()+'.cdds365.manage.com';
		}
		var privateCost=$("#privateCost").val();
		var publicCost=$("#publicCost").val();
		var flowSize=$("#flowSize").val();
		var spaceSize=$("#spaceSize").val();
		var ccUserName=$("#ccUserName").val();
		var ccPwd=$("#ccPwd").val();
		var zsUserName=$("#zsUserName").val();
		var zsPwd=$("#zsPwd").val();
		var schoolSummary=$("#schoolSummary").val();
   	 	$.ajax({
   	        type : 'post',
   	        url : rootPath + '/berkeley/addBerkeley',
   	        data : {
   	        	branchCode : branchCode,
   	        	isArea : isArea,
   	        	branchSchool : branchSchool,
   	        	eara : eara,
   	        	schoolProperties : schoolProperties,
   	        	linkPerson : linkPerson,
   	        	linkPhone : linkPhone,
   	        	domain : domain,
   	        	domainManage : domainManage,
   	        	privateCost : privateCost,
   	        	publicCost : publicCost,
   	        	flowSize : flowSize,
   	        	spaceSize : spaceSize,
   	        	ccUserName : ccUserName,
   	        	ccPwd : ccPwd,
   	        	zsUserName : zsUserName,
   	        	zsPwd : zsPwd,
   	        	schoolSummary : schoolSummary
   	        },
   	        success : function(data){
   	        	if(data=="SUCCESS"){
   	        		alert("保存成功");
   	        		$('.popupContainer').hide();
   	        		$('.popupOpacity').hide();
   	        		window.location.href = rootPath+"/berkeley/berkeleyIndex";
   	        	}else{
   	        		alert("保存失败");
   	        	}
   	        }
   	    });
      
   
}
