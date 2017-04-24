/***
 * author:chopin
 * 工具类，提供获取字典、考期、校区等系统配置信息的方法
 * @param $
 */
(function($){
	
	/** 
     * 将数值四舍五入(保留2位小数)后格式化成金额形式 
     * 
     * @param num 数值(Number或者String) 
     * @return 金额格式的字符串,如'1,234,567.45' 
     * @type String 
     */  
	$.formatMoney=function(num) {  
        num = num.toString().replace(/\$|\,/g,'');  
        if(isNaN(num))  
            num = "0";  
        sign = (num == (num = Math.abs(num)));  
        num = Math.floor(num*100+0.50000000001);  
        cents = num%100;  
        num = Math.floor(num/100).toString();  
        if(cents<10)  
        cents = "0" + cents;  
        for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
        num = num.substring(0,num.length-(4*i+3))+','+  
        num.substring(num.length-(4*i+3));  
        return (((sign)?'':'-') + num + '.' + cents);  
    }  
	
	/**
	 * 将金额格式的字符串转换为float型数字
	 * @param money 金额格式的字符串(String) ,如'1,234,567.45' 
     * @return 数字,float型
     * @type String 
	 */
	$.MoneyToNum=function(money){
		if(money){
			money=$.replaceAll(money.toString(),",","");
		}else{
			money=0
		}
		return parseFloat(money);
	}
	
	/**
	 * 提供字符串的replaceAll功能，原生js是没有这个功能的
	 * @param replaceStr 字符串(String) 
     * @return 替换后的字符串
     * @type String 
	 */
	$.replaceAll=function(replaceStr,src,target){
		return (replaceStr.replace(new RegExp(src,'gm'),target));
	}
	
	/**
	 * 
	 * 参数一个：1、回调函数(非必须)
	 */
	$.fn.getSysCampus = function(){
		// 回调函数
		var func = arguments[0] || null;
		// 访问url
		var url = rootPath + "/sysConfigCampus/getCampusesJson";
		
		_getsyscfg($(this),url,func,"campusName","id");
	};
	
	/**
	 * 
	 * 参数一个：1、回调函数(非必须)
	 */
	$.fn.getSysSchool = function(){
		// 回调函数
		var func = arguments[0] || null;
		// 访问url
		var url = rootPath + "/sysConfigSchool/getSchoolJson";
		
		_getsyscfg($(this),url,func,"schoolName","id");
	};
	
	/**
	 * author:chopin
	 * $('#id 可以是select或input').getSysItem(id,func,selected);
	 * 参数：pid(父ID,学科的父ID是0),func(回调),selected(默认选中值)  接受0、1、2、3个参数，
	 * 
	 */
	$.fn.getSysItem=function(){
		var len= arguments.length; 
		var settings={
				type: 1,
				url : rootPath+"/sysConfigItem/getJsons?",
				func: function(){},
				pid: 0,
				selected: 0
		}
		if(1==len){
		    if(typeof arguments[0]==="function"){
		    	settings.func=arguments[0];
		    }else if(typeof arguments[0]==='string' || !isNaN(arguments[0])){
				settings.selected=arguments[0];
			}else if(arguments[0].match("^\{(.+:.+,*){1,}\}$")){
				settings=$.extend({},settings,arguments[0]);
			}
			settings.url+="itemType="+settings.type;
			_getsyscfg($(this),settings.url,settings.func,"itemName","id");
		}else if(2==len){
				if(typeof arguments[0]==='string' || !isNaN(arguments[0])){
					settings.pid=arguments[0];
					settings.func=arguments[1];
					settings.type=2;
					settings.url+="itemType="+settings.type+"&parentId="+settings.pid;
				}else if(typeof arguments[0]==="function"){
					settings.func=arguments[0];
					settings.selected=arguments[1];
					settings.type=1;
					settings.url+="itemType="+settings.type;
				}
			_getsyscfg($(this),settings.url,settings.func,"itemName","id",settings.selected);
		}else if(3==len){
			settings.pid=arguments[0];
			settings.func=arguments[1];
			settings.selected=arguments[2];
			settings.type=2;
			settings.url+="itemType="+settings.type+"&parentId="+settings.pid;
			_getsyscfg($(this),settings.url,settings.func,"itemName","id",settings.selected);
		}else if(0==len){
			settings.url+="itemType="+settings.type;
			_getsyscfg($(this),settings.url,null,"itemName","id");
		}else{
			return false;
		}
	};
	
	/**
	 * author:chopin
	 * 通过ID获取一学科小类
	 * 参数：id(学科ID),colname(要返回的学科字段名),func(回调)  接受1、2、3个参数，
	 */
	$.getSysItemById=function(id,colname,func){ 
		var url=rootPath+"/sysConfigItem/getJson/"+id;
		$.ajax({
			url: url,
			type: 'post',
			datatype: 'json',
			success: function(jsonData){
				func(jsonData[colname]);
			},
			error: function(){
			}
				
		});
	};
	/**
	 * author:chopin
	 * $('#id 可以是select或input').getSysDict(lvl,id,func);
	 * 参数:tag(关键字),func(回调),type(查询类型:byname,bycode),selected(默认选中值)
	 * 
	 */
	$.fn.getSysDict=function(){
		var tag=arguments[0];
		var func=arguments[1];
		var type=arguments[2];
		var selected=arguments[3]
		var settings={
				tag:"",
				func: function(){},
				type: "bycode",
				selected:0
		}
		if(tag.match("^\{(.+:.+,*){1,}\}$")){
			settings=$.extend({},tag,settings);
		}else{
			settings.tag=tag;
			settings.func=func;
			settings.type=type;
			settings.selected=selected;
		}
		var url=rootPath+"/sysLoader/getdict";
		if(settings.type=="byname"){
			url+="/dictname/"+settings.tag;
		}else if(settings.type=="bycode"){
			url+="/dictcode/"+settings.tag;
		}else if(settings.type=="byid"){
			url+="/id/"+settings.tag;
		}else{
			return ;
		}
		_getsyscfg($(this),url,func,"itemValue","itemCode",settings.selected);
	};
	
	
	/**
	 * author:chopin
	 * 根据父级ID取子字典，自动绑定至select中，也可以在回调中取到列表，于多级联动
	 * @parameter pid 父级ID
	 * @parameter func 回调
	 */
	$.fn.getSubDict=function(pcode,dcode,func){
		if(!pcode){
			return;
		}
		var url=rootPath+"/sysLoader/subdict/"+pcode+"&"+dcode;
		_getsyscfg($(this),url,func,"itemValue","itemCode")
	};
	 
	/***
	 * author:chopin
	 * 翻译字典内容，需要提供两个值，分别是dictCode和itemCode
	 */
	$.transeDict=function(value,func){
		if(!value){
			return;
		}
		$.ajax({
			url: rootPath+"/sysLoader/getdict/"+value,
			type:"get",
			dataType:"json",
			success:function(text){
				if(func){
					func(text);
				}
			},
			error:function(err,msg){
				if(func){
					func(msg);
				}
				
			}
		});
	};
		
	function _getsyscfg ($this,url,func,key,value,selected){ 
		$.ajax({
			url: url,
			type: 'post',
			datatype: 'json',
			async: false,
			success: function(jsonData){
				if(jsonData && jsonData.length>0){
					$this.html('').append('<option value="">请选择</option>');
					if($this.is("select") || $this.attr("tagName")=="select"){
						for(var i=0;i<jsonData.length;i++){
							$this.append('<option value="'+jsonData[i][value]+'">'+jsonData[i][key]+'</option>');
						}
						if(selected){
							$this.find("option[value="+selected+"]").attr("selected","selected");
						}
					}else if($this.is("input") || $this.attr("tagName")=="input"){
						var d=jsonData[0] || jsonData;
						$this.val(d.itemCode).text(d.itemName);
					}
					if(func){
						func(jsonData);
					}
				}
				return $this;
			},
			error: function(){
			}
				
		});
	};
	
	/**
	 * author:chopin 
	 * 根据分校ID和学科获取考期,分校可以为空，默认为当前用户所在分校
	 * @param itemOneId
	 * @param func
	 * @param schoolId
	 */
	$.fn.getTerm=function(itemOneId,func,schoolId){	
		var $this=$(this);
		var url=rootPath+"/sysConfigTerm/dict?itemOneId="+itemOneId;
		if(!itemOneId){
			return;
		}
//		if(schoolId){
//			url+="&schoolId="+schoolId;
//		}
		_getsyscfg($this,url,func,"termName","id");
	};
	
	/**
	 * 根据学科小类查询对应的模块
	 * author:wang.zx
	 * @param secondItemId
	 */
	$.fn.getModuleBySecondItem=function(secondItemId, isAll){
		var url=rootPath+"/classModule/modules/"+secondItemId+"/"+isAll;
		$.ajax({
			 url: url,
			 type: "get",
			 dataType: "json",
			 success: function(jsonData){
				//清空原来的选项
				$("#moduleId").empty();
				
         	 	$("#moduleId").append("<option value=''>请选择</option>");
        	 	for(var i=0;i<jsonData.length;i++){
        	 		$("#moduleId").append("<option value='"+jsonData[i].id + "'methed='" + jsonData[i].teachMethod + "'total='" + jsonData[i].totalClassHour + "'type='" + jsonData[i].moduleType+  "'desc='" + jsonData[i].moduleDesc+"'>"+jsonData[i].name+"</option>");
        	 	}
         	},
			 error: function(msg,err,ee){
				 alert(err);
			 }
		 });
	};
	
	/**
	 * 根据模块Id获取对应的老师
	 * author:wang.zx
	 * @param moduleId
	 */
	$.fn.getTeacherByModuleId = function(moduleId,type){
		var url=rootPath+"/sysConfigTeacher/"+moduleId+"/"+type;
		$.ajax({
			 url: url,
			 type: "get",
			 dataType: "json",
			 success: function(jsonData){
				//清空原来的选项
				 if(type == "PERSON_TEACHER"){
					 $('#PERSON_TEACHER').empty();
	        	 	 $('#PERSON_TEACHER').append("<option value=''>请选择</option>");
			       	 	 for(var i=0;i<jsonData.length;i++){
			       	 		$('#PERSON_TEACHER').append("<option value='"+jsonData[i].id + "'>"+jsonData[i].name+"</option>");
			       	 	 };
				 };
				 if(type == "PERSON_MANAGER"){
					 $('#PERSON_MANAGER').empty();
	        	 	 $('#PERSON_MANAGER').append("<option value=''>请选择</option>");
			       	 	 for(var i=0;i<jsonData.length;i++){
			       	 		$('#PERSON_MANAGER').append("<option value='"+jsonData[i].id + "'>"+jsonData[i].name+"</option>");
			       	 	 };
				 };
				 if(type == "PERSON_ASSISTANT"){
					 $('#PERSON_ASSISTANT').empty();
	        	 	 $('#PERSON_ASSISTANT').append("<option value=''>请选择</option>");
			       	 	 for(var i=0;i<jsonData.length;i++){
			       	 		$('#PERSON_ASSISTANT').append("<option value='"+jsonData[i].id + "'>"+jsonData[i].name+"</option>");
			       	 	 };
				 };
				
        	},
			 error: function(msg,err,ee){
				 alert(err);
			 }
		 });
	};
	
	
	/**
	 * author:chopin 
	 * 根据分校ID获取校区,分校可以为空，默认为当前用户所在分校
	 * @param func 回调
	 */
	$.fn.getCampus=function(func){
		var $this=$(this);
		var url=rootPath+"/sysConfigCampus/dicts";
		_getsyscfg($this,url,func,"campusName","id");
	};
	
	
	$.fn.getCampusToModuleNo=function(func){
		var $this=$(this);
		var url=rootPath+"/sysConfigCampus/dicts";
//		_getsyscfg($this,url,func,"campusName","id");
		
		$.ajax({
			url: url,
			type: 'post',
			datatype: 'json',
			async: false,
			success: function(jsonData){
				if(jsonData && jsonData.length>0){
					$this.html('');
					for(var i=0;i<jsonData.length;i++){
						if(i == 0){
							$this.append('<a class="btn btn-sm btn-default active" href="javascript:;" campusId='+jsonData[i].id+ ' campusNo='+jsonData[i].campusNo+' >'+jsonData[i].campusName+'</a>');
							$("#campus_no").val(jsonData[i].campusNo);
						}else{
							$this.append('<a class="btn btn-sm btn-default" href="javascript:;" campusId='+jsonData[i].id+ ' campusNo='+jsonData[i].campusNo+' >'+jsonData[i].campusName+'</a>');
						}
					}
					if(func){
						func(jsonData);
					}
				}
				return $this;
			},
			error: function(){
			}
				
		});
		
	};
	
	
	/**
	 * author:chopin 
	 * 根据分校ID获取校区,分校可以为空，默认为当前用户所在分校
	 * campusId
	 * @param func 回调
	 */
	$.fn.getClassRoom=function(campusId,func){
		var $this=$(this);
		var url=rootPath+"/sysConfigClassroom/getClassRoomByCampus/"+campusId;
		_getsyscfg($this,url,func,"roomName","id");
	};
	
	
	$.transeCampus=function(campusNo,func){
		var url=rootPath+"/sysConfigCampus/dict";
		$.ajax({
			 url: url,
			 type: "post",
			 data:"campusNo="+campusNo,
			 dataType: "json",
			 success: function(jsonData){
        	 	if(func){
        	 		func(jsonData);
        	 	}
         	},
			 error: function(msg,err,ee){
				 alert(err);
			 }
		 });
	};
	
	/**
	 * author:chopin 
	 * 根据班型编号获取模块和视频,取得的数据作为回调方法的参数返回，
	 * 模块jsonData.modules, 视频：jsonData.videos
	 * @param classId 班型编号
	 * @param func 回调
	 */
	$.fn.getmodulesAndVideo=function(classId,func){
		$.ajax({
    		url: rootPath+"/classType/detail/"+classId,
    		type:"post",
    		dataType:"json",
    		success:function(jsonData){
    			if(func){
    				func(jsonData);
    			}
    		},
    		error: function(){
    			
    		}
    	});
	};
	
	/**
	 * author:chopin 
	 * 根据班型编号获取模块和视频,取得的数据作为回调方法的参数返回，
	 * 模块jsonData.modules, 视频：jsonData.videos
	 * @param classId 班型编号
	 * @param func 回调
	 */
	$.fn.getClassType=function(itemOneId,itemSecondId,func){
		var $this=$(this);
		var url=rootPath+"/classType/findList?itemOneId="+itemOneId+"&itemSecondId="+itemSecondId;
		_getsyscfg($this,url,func,"name","id");
	};
	
	/**
	 *@date 2014-12-10 下午3:21:39
	 * author:wang.zx
	 */
	$.fn.getIsMust=function(){
		var obj = arguments[0];
		var tableName = arguments[1];
		var columnName = arguments[2];
		var url=rootPath+"/sysConfigTable/getConfigVo";
		
		$.ajax({
			url: url,
			type: 'post',
			jsonDatatype: 'json',
			data:"tableName="+tableName+"&columnName="+columnName,
			success: function(jsonData){
				if(jsonData && jsonData.length>0){
					jsonData = eval("(" + jsonData + ")");
					var isRequired = jsonData.isRequired;
					var isSystem = jsonData.isSystem;
					if(isRequired && isRequired == "true"){
						$(obj).attr("isReq","true");
					}else{
						$(obj).attr("isReq","false");
					}
					if(isSystem && isSystem == "true"){
						$(obj).attr("isSys","true");
					}else{
						$(obj).attr("isSys","false");
					}
				}
			},
			error: function(){
			}
		});
	};
	
	$.fn.getParams=function(){
		var fields=$(this).serialize().replace(/[^&]+=\.?(?:&|$)/g, '');
		if(fields.lastIndexOf("&")==fields.length){
			fields=fields.substring(0,fields.length-1);
		}
		return fields;
	};
	$.getRootPath=function(){
	    var curWwwPath=window.document.location.href;
	    var pathName=window.document.location.pathname;
	    var pos=curWwwPath.indexOf(pathName);
	    var localhostPaht=curWwwPath.substring(0,pos);
	    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
	    return(localhostPaht+projectName);
	};
	$.message=function(resp){
		console.log(resp);
		var responText=JSON.parse(resp.responseText);
		if( resp.status==403 && responText && responText.message){
			confirm("出了一点小小的意外 :-( ",responText.message,function(){window.location.href=rootPath+"/login"});
		}
	}
	/**
	 * alert 提示框,可以替代原生alert
	 * @param msg 消息内容
	 * @param callback 回调，用户点击了确定后触发
	 */
	$.alert=function(msg,callback){
		msg="<p>"+msg+"</p>";
		var tips='<div class="layer-tips _alert" style="display: none;">'+
	        '<div class="layer-tips-title">提示信息 <i class="close iconfont alert_close"></i></div>'+
	        '<div class="layer-tips-content">'+msg+'</div>'+
	        '<div class="layer-tips-btns">'+
	        '    <a href="javascript:;" class="btn btn-mini btn-success alert_ok">确定</a>'+
	        '</div>'+
	    '</div>'+
	    '<div class="layer-tips-bg _alert" style="display: none;"></div>';
		$(document).find(".layer-tips").remove();
		$(document).find(".layer-tips-bg").remove();
		$(document).find("body").append(tips);
		$('.layer-tips-bg._alert').fadeIn(200,function(){
            $('.layer-tips._alert').fadeIn(200);
        })
		$(document).on("click.ok.alert",".alert_ok",function(){
			if(callback){
				callback();
			}
			$(this).parents('.layer-tips').fadeOut(200,function(){
                $('.layer-tips-bg._alert').fadeOut(200);
            })
		})
		.on("click.close.alert",".alert_close",function(){
			$(this).parents('.layer-tips').fadeOut(200,function(){
                $('.layer-tips-bg._alert').fadeOut(200);
            })
		});
	}
	/**
	 * 确认框，可以替代js原生的 confirm
	 * @param  msg 消息内容
	 * @param  callback  回调，用户点击按钮后出发，回调有一个boolean参数，如果用户点击了确定则是true ，点击了取消则是false
	 */
	$.confirm=function(msg,callback){
		var tips='<div class="layer-tips confirm" style="display: none;">'+
	        '<div class="layer-tips-title">请确认 <i class="close iconfont confirm_close"></i></div>'+
	        '<div class="layer-tips-content">'+msg+'</div>'+
	        '<div class="layer-tips-btns">'+
	        '    <a href="javascript:;" class="btn btn-mini btn-success confirm_ok">确定</a>'+
	        '   <a href="javascript:;" class="btn btn-mini btn-default confirm_cancle">取消</a>'+
	        '</div>'+
	    '</div>'+
	    '<div class="layer-tips-bg confirm" style="display: none;"></div>';
		$(document).find(".layer-tips").remove();
		$(document).find(".layer-tips-bg").remove();
		$(document).find("body").append(tips);
		$('.layer-tips-bg').fadeIn(200,function(){
            $('.layer-tips.confirm').fadeIn(200);
        })
        //确定
		$(document).off("click.ok.confirm").on("click.ok.confirm",".confirm_ok",function(){
			$(this).parents('.layer-tips').fadeOut(200,function(){
                $('.layer-tips-bg.confirm').fadeOut(200);
            })
			if(callback){
				callback(true);
			}
		})
		.on("click.close.confirm",".confirm_close",function(){
			$(this).parents('.layer-tips').fadeOut(200,function(){
                $('.layer-tips-bg.confirm').fadeOut(200);
            })
		});
		//取消
		$(document).off("click.cancle.confirm").on("click.cancle.confirm",".confirm_cancle",function(){
			$(this).parents('.layer-tips').fadeOut(200,function(){
                $('.layer-tips-bg.confirm').fadeOut(200);
            })
            if(callback){
				callback(false);
			}
		});
	}
	/**
	 * 阿拉伯数字转换为汉字
	 */
	$.changeNum= function(i){
		var arg=["一", "二", "三", "四", "五", "六", "七", "八", "九","十"];
		var str="";
		if(i/10>1){
			var n=Math.floor(i/10-1)?Math.floor(i/10-1):"";
			if(n>0){
				str=arg[n]+arg[9];
			}else{
				str=arg[9];
			}
			
			if(i%10>0){
				str+=arg[i%10-1]
			}
		}else if(i>10){
			str=(arg[Math.floor(i/10-1)]?arg[i/10-1]:"")+arg[9];
		}else{
			str=arg[i-1];
		}
		return str;
	}
	/**
	 * 加载框
	 * @param text 文案 ,可以为空，默认显示  加载中,请稍后...
	 */
	//加载中
	$.loading=function(text,ele){
		var content='加载中,请稍后...';
		if(text){
			content=text;
		}
		html = '<div class="loading lp-units-loading none">'
		    +'<p><i></i>'+ content +'</p>'
		    +'</div>'
		    +'<div class="loading-bg lp-units-loading-bg"></div>';
		$('body').find(".loading").remove();
		$('body').find(".loading-bg").remove();
		if(ele){
			$(ele).append(html).fadeIn(200);
		}else{
			$('body').append(html).fadeIn(200);
		}
		
	}
	//加载结束
	$.loadover=function(){
		$('body').find(".loading-bg").fadeOut(200,function(){
			$('body').find(".loading").fadeOut(300).remove();	
			$('body').find(".loading-bg").remove();
		});
	}
	/**
	 * 消息框
	 * @param  text 要显示的消息内容
	 * @param  delay 存在时间,毫秒
	 * @param  callback 回调，消失后调用
	 */
	$.msg=function(text,delay,callback){
		var html='<div class="c-fa">'+ text +'</div>';
		var args=arguments;
		if(args.length>2){
			if(delay&& isNaN(delay)){
				delay=parseInt(delay);
			}else{
				delay=2000;
			}
		}else if(args.length==2){
			if(args[1] && !isNaN(args[1])){
				delay=parseInt(delay);
			}else{
				callback=args[1];
				delay=2000;
			}
		}else{
			delay=2000;
		}
		
		$(html).appendTo('body').fadeIn(100).delay(delay).fadeOut(200,function(){
			if(callback){
				callback();
			}
			$(this).remove();
		});
	}
//	window.alert=$.alert;
//	window.confirm=$.confirm;
	
})(jQuery);
