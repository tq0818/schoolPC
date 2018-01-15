function closeBtn(companyId,itemCode,delFlag) {

    $.ajax({
        type : 'post',
        url : '/serviceManager/updateDelFlag',
        data : {
            companyId : companyId,
            itemCode : itemCode,
            delFlag : delFlag
        },
        success : function(data){
        	if(data.msg=="success"){
        		window.location.href = rootPath+"/serviceManager/getServiceManager?companyId="+companyId;
    		}else{
    			alert("操作失败");
    			return;
    		}
        }
    });
}
function addBerkeley(biaoshi){
    	if(biaoshi==0){
    		var branchCode=$("#branchCode").val();
    		if(null==branchCode || ''==branchCode){
    			alert("分校机构代码不能为空");
    			return;
    		}else{
    			var data={};
    	    	data.brachCode=branchCode;
    			   $.ajax({
    	               url: rootPath + "/berkeley/queryCompanyVo",
    	               data: data,
    	               type: 'post',
    	               beforeSend: function (XMLHttpRequest) {
    	               },
    	               success: function (jsonData) {
    	               	if(jsonData==null || jsonData==''){
    	               		alert("输入错误");
    	               		$("#branchCode").val("");
    	               		$("#branchSchool").text("");
    	               		$("#eara").text("");
    	               		return;
    	               	}
    	               	var companyName=jsonData.companyName;
    	               	var eduArea=jsonData.eduArea;
    	               	var dictCode=jsonData.dictCode;
    	               	$('#branchSchool').text(companyName);
    	               	$('#eara').text(eduArea);
    	               	$('#isArea').val(dictCode);
    	               	if(companyName==eduArea){
                    		var options = document.getElementById('schoolProperties').children;
                        	options[0].selected=true;
                        	document.getElementById("schoolProperties").disabled=true;	
                    	}else{
                    		document.getElementById("schoolProperties").disabled=false;	
                    	}
    	               	var branchSchool=$("#branchSchool").text();var branchCode=$("#branchCode").val();
    	        		var isArea=$("#isArea").val();
    	        		var eara=$("#eara").text();
    	        		var schoolProperties=$("#schoolProperties").val();
    	        		/*if(branchSchool!=eara){
    	    				if(null== schoolProperties || ''==schoolProperties){
    	            			alert("学校性质不能为空");
    	            			return;
    	            		}
    	    			}*/
    	        		var linkPerson=$("#linkPerson").val();
    	        	/*	if(null==linkPerson || ''==linkPerson){
    	        			alert("联系人不能为空");
    	        			return;
    	        		}分校名称*/
    	        		var linkPhone=$("#linkPhone").val();
    	        		/*if(null==linkPhone || ''==linkPhone){
    	        			alert("联系方式不能为空");
    	        			return;
    	        		}else if(linkPhone.length!=11){
                            alert("联系方式不是11位有效电话");
                            return;
						}else if(!(/^1[34578]\d{9}$/.test(linkPhone))){
                            alert("手机号码有误，请重填");
                            return ;
                        }*/
                           var domain=$("#domain").val();
    	        		domain=domain.replace(/(^\s+)|(\s+$)/g,"");
    	        		domain = domain.replace(/\s/g,"");
    	        		if(null!=domain && ''!=domain){
    	        			var data={};
    	        	    	data.domain=domain+'.cdds365.com';
    	        			$.ajax({
    	        	               url: rootPath + "/berkeley/checkDomain",
    	        	               data: data,
    	        	               type: 'post',
    	        	               beforeSend: function (XMLHttpRequest) {
    	        	               },
    	        	               success: function (data) {
    	        	            	   if(data.msg=="success"){
    	        	            		   domain=domain+'.cdds365.com';
    	        	            		   var domainManage=$("#domainManage").val();
    	        	            			domainManage=domainManage.replace(/(^\s+)|(\s+$)/g,"");
    	        	            			domainManage = domainManage.replace(/\s/g,"");
	        	            				if(null!=domainManage && ''!= domainManage){
	        	            					var data={};
	        	            			    	data.domainManage=domainManage+'.cdds365.manage.com';
	        	            					$.ajax({
	        	            			               url: rootPath + "/berkeley/checkDomain",
	        	            			               data: data,
	        	            			               type: 'post',
	        	            			               beforeSend: function (XMLHttpRequest) {
	        	            			               },
	        	            			               success: function (data) {
	        	            			            	   if(data.msg=="success"){
	        	            			            		   domainManage=domainManage+'.cdds365.manage.com';
	        	            			            		   var privateCost=$("#privateCost").val();
        	            			            				if(null==privateCost || ''==privateCost){
        	            			            					alert("学校私有课程收费比例不能为空");
        	            			            					return;
        	            			            				}
	        	            			            			var publicCost=$("#publicCost").val();
        	            			            				if(null==publicCost || ''==publicCost){
        	            			            					alert("学校开放课程收费比例不能为空");
        	            			            					return;
        	            			            				}
	        	            			            			var flowSize=$("#flowSize").val();
        	            			            				if(null==flowSize || ''==flowSize){
        	            			            					alert("流量不能为空");
        	            			            					return;
        	            			            				}
	        	            			            			var spaceSize=$("#spaceSize").val();
        	            			            				if(null==spaceSize || ''==spaceSize){
        	            			            					alert("空间不能为空");
        	            			            					return;
        	            			            				}
	        	            			            			var ccUserName=$("#ccUserName").val();
        	            			            				if(null==ccUserName || ''==ccUserName){
        	            			            					alert("cc账号不能为空");
        	            			            					return;
        	            			            				}
	        	            			            			var ccPwd=$("#ccPwd").val();
        	            			            				if(null==ccPwd || ''==ccPwd){
        	            			            					alert("cc密码不能为空");
        	            			            					return;
        	            			            				}
	        	            			            			var zsUserName=$("#zsUserName").val();
        	            			            				if(null==zsUserName || ''==zsUserName){
        	            			            					alert("展视互动账号不能为空");
        	            			            					return;
        	            			            				}
	        	            			            			var zsPwd=$("#zsPwd").val();
        	            			            				if(null==zsPwd || ''==zsPwd){
        	            			            					alert("展视互动密码不能为空");
        	            			            					return;
        	            			            				}
	        	            			            			var schoolSummary=$("#schoolSummary").val();
        	            			            				if(null==schoolSummary || ''==schoolSummary){
        	            			            					alert("分校简介不能为空");
        	            			            					return;
        	            			            				}else if (schoolSummary.length>200){
        	            			            					alert("分校简介不能超过200个字符");
        	            			            					return;
																}else{
        	            			            					schoolSummary=schoolSummary.replace(/(^\s+)|(\s+$)/g,"");
        	            			            					schoolSummary = schoolSummary.replace(/\s/g,"");
        	            			            				}
                                                               $.confirm("保存后，部分属性无法修改，是否继续？",function (r) {
        	            			            				if(r){
                                                                    $.ajax({
                                                                        type: 'post',
                                                                        url: rootPath + '/berkeley/addBerkeley',
                                                                        data: {
                                                                            branchCode: branchCode,
                                                                            isArea: isArea,
                                                                            branchSchool: branchSchool,
                                                                            eara: eara,
                                                                            schoolProperties: schoolProperties,
                                                                            linkPerson: linkPerson,
                                                                            linkPhone: linkPhone,
                                                                            domain: domain,
                                                                            domainManage: domainManage,
                                                                            privateCost: privateCost,
                                                                            publicCost: publicCost,
                                                                            flowSize: flowSize,
                                                                            spaceSize: spaceSize,
                                                                            ccUserName: ccUserName,
                                                                            ccPwd: ccPwd,
                                                                            zsUserName: zsUserName,
                                                                            zsPwd: zsPwd,
                                                                            schoolSummary: schoolSummary
                                                                        },
                                                                        success: function (data) {
                                                                            if (data.msg == "success") {
                                                                                alert("保存成功");
                                                                                $('#privateCost').css('text-align', 'right');
                                                                                $('#publicCost').css('text-align', 'right');
                                                                                $('.popupContainer').hide();
                                                                                $('.popupOpacity').hide();
                                                                                window.location.href = rootPath + "/berkeley/berkeleyIndex";
                                                                            } else {
                                                                                alert("保存失败");
                                                                            }
                                                                    }
                                                                    });
        	            			            				} else{
        	            			            					return;
																}
                                                               });
	        	            			        			}
	        	            			        			else{
	        	            			        				alert("分校后台域名重复");
	        	            			        				$("#domainManage").val('');
	        	            			        				return;
	        	            			   	        		}
	        	            			               },
	        	            			              
	        	            			           })
	        	            					
	        	            				}else{
	        	            					alert("分校后台域名不能为空");
	        	            					return;
	        	            				}
    	        	            		   
    	           	        			}else{
    	           	        				alert("分校域名重复");
    	           	        				$("#domain").val('');
    	           	        				return;
    	        	   	        		}
    	        	               },
    	        	              
    	        	           });
    	        		}else{
    	        			alert("分校域名不能为空");
    	        			return;
    	        		}
	               },
	           });
    		}
    	}
    	if(biaoshi==1){
			var linkPerson=$("#linkPerson").val();
			/*if(null==linkPerson || ''==linkPerson){
				alert("联系人不能为空");
				return;
			}*/
    		var linkPhone=$("#linkPhone").val();
    		/*if(null==linkPhone || ''==linkPhone){
    			alert("联系方式不能为空");
    			return;
    		}*/
		   var privateCost=$("#privateCost").val();
		   if(null==privateCost || ''==privateCost){
				alert("学校私有课程收费比例不能为空");
				return;
		   }	
		   var publicCost=$("#publicCost").val();
		   if(null==publicCost || ''==publicCost){
			   	alert("学校开放课程收费比例不能为空");
				return;
		   }
		   var flowSize=$("#flowSize").val();
		  /* if(null==flowSize || ''==flowSize){
				alert("流量不能为空");
				return;
		   }*/
		   var spaceSize=$("#spaceSize").val();
		   /*if(null==spaceSize || ''==spaceSize){
				alert("空间不能为空");
				return;
		   }	*/
		   var ccUserName=$("#ccUserName").val();
		   if(null==ccUserName || ''==ccUserName){
				alert("cc账号不能为空");
				return;
		   }
		   var ccPwd=$("#ccPwd").val();
		   if(null==ccPwd || ''==ccPwd){
				alert("cc密码不能为空");
				return;
		   }
		   var zsUserName=$("#zsUserName").val();
		   if(null==zsUserName || ''==zsUserName){
				alert("展视互动账号不能为空");
				return;
		   }
		   var zsPwd=$("#zsPwd").val();
		   if(null==zsPwd || ''==zsPwd){
				alert("展视互动密码不能为空");
				return;
		   }
		   var schoolSummary=$("#schoolSummary").val();
		   if(null==schoolSummary || ''==schoolSummary){
				alert("学校简介不能为空");
				return;
		   }else{
				schoolSummary=schoolSummary.replace(/(^\s+)|(\s+$)/g,"");
				schoolSummary = schoolSummary.replace(/\s/g,"");
		   }
   			var companyId=$("#companyId").val();
			$.ajax({
	   	        type : 'post',
	   	        url : rootPath + '/classManager/editBerkeley',
	   	        data : {
	   	        	id : companyId,
	   	        	linkPerson : linkPerson,
	   	        	linkPhone : linkPhone,
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
	   	        	if(data.msg=="success"){
	   	        		alert("保存成功");
	   	        		window.location.href = rootPath+"/classManager/getClassInfo/"+companyId;
	   	        	}else{
	   	        		alert("保存失败");
	   	        	}
	   	        }
	   	    });
    	}
    		
	}
