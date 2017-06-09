/*
*   title: 页脚设置;
*   description: 系统设置页尾配置页面;
*   date: 2015-04-13;
*   author: Piaoyis;
*/
    $(function(){
        // 相应配置的input的单击事件
        $('.c')
            .on('click','input[type="text"]',function(){
            	var isSelfNet=true;
            	$.ajax({
            		url:rootPath+"/sysPageHeadFoot/isEnterpriseVersion",
            		async:false,
            		type:"post",
            		success:function(data){
            			if(!data){
            				isSelfNet=false;
            			}
            		}
            	})
            	if(!isSelfNet){
            		return;
            	}
                var _this = $(this);
                _this
                    .removeClass('readonly')
                    .removeAttr('readonly')
                    .focus();
            })
            // 如果按键则激活保存按钮
            .on('keyup','input[type="text"]',function(e){
                var _this = $(this),
                    code = e.keyCode;

                // 如果没有被激活则不相应按键事件
                if ( !(_this.hasClass('readonly')) ){

                    // 相应回车事件
                    if ( code == '13' ){
                        // 检测按钮是否被激活
                        var btn = _this.next('.btn').hasClass('btn-primary');
                        // 如果保存按钮没有被激活则不进行保存
                        if ( btn ){
                            _this.next('.btn').click();
                        }else{
                            _this
                                .addClass('readonly')
                                .attr('readonly','readonly');
                        }

                    }else {

                        _this
                            .next('.btn')
                            .removeClass('btn-default')
                            .addClass('btn-primary')
                            .val('保存');    
                    }

                }

                
            })
            .on('click','input[type="button"]',function(){
            	//判断是否企业标准版
            	var isSelfNet=true;
            	$.ajax({
            		url:rootPath+"/sysPageHeadFoot/isEnterpriseVersion",
            		async:false,
            		type:"post",
            		success:function(data){
            			if(!data){
            				$.alert("只有企业标准版及以上版本才可以修改版本信息");
            				isSelfNet=false;
            			}
            		}
            	})
            	if(!isSelfNet){
            		return;
            	}
                var _this = $(this),
                    id = _this.prev('input').attr('name'),
                    val = '';

                if ( _this.hasClass('btn-primary') ){
                    // 获取输入内容
                    val = 
                        _this
                        .val('修改')
                        .removeClass('btn-primary')
                        .addClass('btn-default')
                        .prev('input')
                        .addClass('readonly')
                        .attr('readonly','readonly').val();
                    	$.ajax({
                    		url:rootPath+"/sysPageHeadFoot/toSave",
                    		type:"post",
                    		data:{
                    			"name":id,
                    			"value":val
                    		},
                    		success:function(data){
                    			//alert("保存成功!");
                    			if("success"==data){
                    				$.msg("保存成功!");
                    			}else if("error"==data){
                    				$.alert("只有企业标准版及以上版本才可以修改版本信息");
                    				history.go(0);
                    			}
                    			
                    		}
                    		
                    	})
                        // 提交保存
                       // alert(id + ' 提交保存！内容为：' + val);
                }else{
                    _this
                        .val('保存')
                        .removeClass('btn-default')
                        .addClass('btn-primary')
                        .prev('input')
                        .removeClass('readonly')
                        .removeAttr('readonly');
                }
            })
            // 监控输入框实时变化
            .on('input propertychange','input[type="text"]',function(){
                var 
                    id = $(this).attr('name'),
                    val = $(this).val()||'';

                // 将结果实时显示在页面上，使用纯文本形式显示
                $('#i-'+id).text(val);

            });

        // 设置关于我们 tabs 切换
        $('#about-tabs')
            .on('click','a',function(){
                var o = $(this),
                    i = o.index();
               var flag =  $(".aa").index();
               flag=flag*1+1;
             CKupdate();
     		var id = $("#companyId").val();
     		var value=null;
     		if(flag=="1"){
     			value = $("#newsContents").val();
     		}
     		if(flag=="2"){
     			value = $("#newsContents0").val();
     		}
     		if(flag=="3"){
     			value = $("#newsContents1").val();
     		}
     		if(flag=="4"){
     			value = $("#newsContents2").val();
     		}
     		value=encodeURI(value);
     		var content=null;
    		
			if(flag=="1"){
				content=$("#about").val();
				
    		}
    		if(flag=="2"){
    			content=$("#connect").val(); 
    		}
    		if(flag=="3"){
    			content=$("#privacy").val();
    		}
    		if(flag=="4"){
    			content=$("#legal").val();
    		}
    		if(!content){
				content="";
			}
    		if(content!=value){
//    			$.confirm(("您当前数据还没保存,点击确定保存!"),function(isConfirm){
//    				if(isConfirm){
//    					save(flag);	
//    				}else{
    					if(flag=="1"){
    						editor.setData(decodeURI(content)); 
    						//alert(editor.getData());
    		    		}
    		    		if(flag=="2"){
    		    			editor0.setData(decodeURI(content)); 
    		    		}
    		    		if(flag=="3"){
    		    			editor1.setData(decodeURI(content)); 
    		    		}
    		    		if(flag=="4"){
    		    			editor2.setData(decodeURI(content)); 
    		    		}
//    				}
//    				
//    			})
    			
    			/*if(!confirm("您当前数据还没保存,点击确定保存!")){
    				if(flag=="1"){
						editor.setData(decodeURI(content)); 
						//alert(editor.getData());
		    		}
		    		if(flag=="2"){
		    			editor0.setData(decodeURI(content)); 
		    		}
		    		if(flag=="3"){
		    			editor1.setData(decodeURI(content)); 
		    		}
		    		if(flag=="4"){
		    			editor2.setData(decodeURI(content)); 
		    		}
		    		
    				
    			}else{
    				save(flag);
    			}*/
    			 if( !(o.hasClass('active')) ){
	                	$(".aa").removeClass('aa');
	                	o.addClass('aa');
	                    o.addClass('active').siblings('a').removeClass('active');
	                    if(i=="0"){
	                    	$("#legalStatement").attr("style","display:none");
	                    	$("#legalStatementButton").attr("style","display:none");
	                    	$("#conectUs").attr("style","display:none");
	                    	$("#conectUsButton").attr("style","display:none");
	                    	$("#privacyTerms").attr("style","display:none");
	                    	$("#privacyTermsButton").attr("style","display:none");
	                    	$("#aboutUs").attr("style","display:block");
	                    	$("#aboutUsButton").attr("style","display:block");
	                    }
	                    if(i=="1"){
	                    	$("#legalStatement").attr("style","display:none");
	                    	$("#legalStatementButton").attr("style","display:none");
	                    	$("#conectUs").attr("style","display:block");
	                    	$("#conectUsButton").attr("style","display:block");
	                    	$("#privacyTerms").attr("style","display:none");
	                    	$("#privacyTermsButton").attr("style","display:none");
	                    	$("#aboutUs").attr("style","display:none");
	                    	$("#aboutUsButton").attr("style","display:none");
	                    }
	                    if(i=="2"){
	                    	$("#legalStatement").attr("style","display:none");
	                    	$("#legalStatementButton").attr("style","display:none");
	                    	$("#privacyTerms").attr("style","display:block");
	                    	$("#privacyTermsButton").attr("style","display:block");
	                    	$("#conectUs").attr("style","display:none");
	                    	$("#conectUsButton").attr("style","display:none");
	                    	$("#aboutUs").attr("style","display:none");
	                    	$("#aboutUsButton").attr("style","display:none");
	                    }
	                    if(i=="3"){
	                    	$("#privacyTerms").attr("style","display:none");
	                    	$("#privacyTermsButton").attr("style","display:none");
	                    	$("#conectUs").attr("style","display:none");
	                    	$("#conectUsButton").attr("style","display:none");
	                    	$("#aboutUs").attr("style","display:none");
	                    	$("#aboutUsButton").attr("style","display:none");
	                    	$("#legalStatement").attr("style","display:block");
	                    	$("#legalStatementButton").attr("style","display:block");
	                    }
	                   
	                    //alert('当前索引为: ' + i);
	                }
    		}else{
    			 if( !(o.hasClass('active')) ){
	                	$(".aa").removeClass('aa');
	                	o.addClass('aa');
	                    o.addClass('active').siblings('a').removeClass('active');
	                    if(i=="0"){
	                    	$("#legalStatement").attr("style","display:none");
	                    	$("#legalStatementButton").attr("style","display:none");
	                    	$("#conectUs").attr("style","display:none");
	                    	$("#conectUsButton").attr("style","display:none");
	                    	$("#privacyTerms").attr("style","display:none");
	                    	$("#privacyTermsButton").attr("style","display:none");
	                    	$("#aboutUs").attr("style","display:block");
	                    	$("#aboutUsButton").attr("style","display:block");
	                    }
	                    if(i=="1"){
	                    	$("#legalStatement").attr("style","display:none");
	                    	$("#legalStatementButton").attr("style","display:none");
	                    	$("#conectUs").attr("style","display:block");
	                    	$("#conectUsButton").attr("style","display:block");
	                    	$("#privacyTerms").attr("style","display:none");
	                    	$("#privacyTermsButton").attr("style","display:none");
	                    	$("#aboutUs").attr("style","display:none");
	                    	$("#aboutUsButton").attr("style","display:none");
	                    }
	                    if(i=="2"){
	                    	$("#legalStatement").attr("style","display:none");
	                    	$("#legalStatementButton").attr("style","display:none");
	                    	$("#privacyTerms").attr("style","display:block");
	                    	$("#privacyTermsButton").attr("style","display:block");
	                    	$("#conectUs").attr("style","display:none");
	                    	$("#conectUsButton").attr("style","display:none");
	                    	$("#aboutUs").attr("style","display:none");
	                    	$("#aboutUsButton").attr("style","display:none");
	                    }
	                    if(i=="3"){
	                    	
	                    	$("#privacyTerms").attr("style","display:none");
	                    	$("#privacyTermsButton").attr("style","display:none");
	                    	$("#conectUs").attr("style","display:none");
	                    	$("#conectUsButton").attr("style","display:none");
	                    	$("#aboutUs").attr("style","display:none");
	                    	$("#aboutUsButton").attr("style","display:none");
	                    	$("#legalStatement").attr("style","display:block");
	                    	$("#legalStatementButton").attr("style","display:block");
	                    }
	                   
	                }
    		}
    		
    		

            });
    })
