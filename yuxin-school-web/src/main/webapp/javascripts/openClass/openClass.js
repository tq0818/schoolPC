var isStart;
(function($) {
	Date.prototype.Format = function(fmt)   
	{ //author: meizz   
	  var o = {   
	    "M+" : this.getMonth()+1,                 //月份   
	    "d+" : this.getDate(),                    //日   
	    "h+" : this.getHours(),                   //小时   
	    "m+" : this.getMinutes(),                 //分   
	    "s+" : this.getSeconds(),                 //秒   
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
	    "S"  : this.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	} 
	var Forms = {
		init : function() {
			var isEdit = $("#isEdit").val();
			if(isEdit!=0){
				var time = $("#time").val();
				var startTime = $("#startTimes").val();
				var endTime = $("#endTimes").val();
				var now = new Date();
				var start = new Date((time+" "+startTime).replace("-", "/").replace(
						"-", "/"));
				var end = new Date((time+" "+endTime).replace("-", "/").replace(
						"-", "/"));
				if(now>start && now<end){
					isStart = "starting";
					$("#time1").remove();
					$("#time2").remove();
				}
				var startTime = $("#startTimes").val();
				var endTime = $("#endTimes").val();
				var startDates = $("#startDate").val();
				var endDates = $("#endDate").val();
				$("#statTime").val(startDates+" "+startTime);
				$("#endTime").val(endDates+" "+endTime);
			}
			
			//编辑时隐藏支持手机端按钮
			var moCouId = $("#couId").val();
			if(moCouId){
				$("#suportM").hide();
				$("#removeLi").remove();
			}
			
			
			$('.form_time').datetimepicker({
    	        language:  'zh-CN',
    			autoclose: 1,
    			todayHighlight: 1,
    			startView: 1,
    			minView: 0,
    			maxView: 2,
    			forceParse: 0
    	    })
    	    
//			$('#time').datetimepicker({
//			    format: 'yyyy-mm-dd'
//			});
			
			Forms.getTeachers();
			Forms.getAssistants();
			
			var nearOneId = $("#oneItem option:selected").val();
			if(nearOneId){
				Forms.getTwpItems(nearOneId);
			}
			
			$("#oneItem").change(function(){
				var oneId = $(this).val();
				Forms.getTwpItems(oneId);
			});
			
			
			$("#upPic").click(function(){
				$(".upload-layer").fadeIn(200,function(){
					$(".add-layer-bg").fadeIn(200);
				});
			});
			
			$(".close").click(function(){
				$(".upload-layer").fadeOut(200,function(){
					$(".add-layer-bg").fadeOut(200);
				});
				$(".pic-upload").hide();
			});
			
			$(".pic").on("change","#target", function() {
				var theImage = new Image();
				console.log($(this).attr("src"));
				theImage.src = $(this).attr("src");
				 if (theImage.complete) {
					 	sourceHeight = theImage.height;
						sourceWidth = theImage.width;
						$.init(sourceWidth, sourceHeight);
	 			    } else {
	 			    	theImage.onload = function () {
	 			        	sourceHeight = theImage.height;
							sourceWidth = theImage.width;
							$.init(sourceWidth, sourceHeight);
	 			        };
	 			    };
				
			}); 
			
			
			//保存
			$(".saveInfo").click(function(){
				var html = $(this).html();
				$("#publishStatus").attr("name","publishStatus");
				if(html == '保存并退出'){
					$("#publishStatus").val("0");
				}
				if(html == '保存并发布'){
					$("#publishStatus").val("1");
				}
				var openCourseName = $("#openCourseName").val();
				var claPicUrl = $("#claPicUrl").val();
				var detailDesc = $("#detailDesc").val();
				var twoItem = $("#twoItem").val();
				var startDate = $("#statTime").val();
				var endDate = $("#endTime").val();
				/*var barrage = $("input[name=barrage]:checked").val();
				var modetype = $("#modetype").val();*/
				if(!twoItem){
					$.confirm("您还没有学科小类, 您确定要进入学科页面设置学科吗?",function(a){
						if(a==true){
							location.href= rootPath + "/sysConfigItem/project";
						}
					});
					return;
				}
				if(!openCourseName){
					$('<div class="c-fa">'+ "请输入课程名称" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					return;
				}
				if(!claPicUrl){
					$('<div class="c-fa">'+ "请选择课程封面" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					return;
				}
				if(isStart != "starting"){
					if(!startDate){
						$('<div class="c-fa">'+ "请选择开始时间" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
						return;
					}
					if(!endDate){
						$('<div class="c-fa">'+ "请选择结束时间" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
						return;
					}
				}
				if(isStart != "starting"){
					var startTime = $("#statTime").val().replace("-","").replace("-","").replace(":","");
					var endTime = $("#endTime").val().replace("-","").replace("-","").replace(":","");
					if(endTime<=startTime){
						$('<div class="c-fa">'+ "结束时间应大于开始时间" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
						return;
					}
				}
				if(detailDesc.length>120){
					$('<div class="c-fa">'+ "字数限制在0～120范围内" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
					return;
				}
				var isCopy = $("#isCopy").val();
				var supMobile = $("input[name=supportMobile]:checked").val();
			
				if(isCopy != 0){
					$("#couId").val("");
					if(supMobile == 1){
						if(isStart != "starting"){
							//支持手机
							var start = new Date();
							var end = new Date(($("#statTime").val()).replace("-", "/").replace(
									"-", "/"));
							var diffTime = end - start;
							diffTime = diffTime/1000/60;
							if (diffTime<=30) {
								$('<div class="c-fa">'+ "支持手机端需提前半个小时创建！" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
								return;
							} 
						}
					}else{
						if(isStart != "starting"){
							var start = new Date();
							var end = new Date(($("#statTime").val()).replace("-", "/").replace(
									"-", "/"));
							if (end <= start) {
								$('<div class="c-fa">'+ "选择日期应大于实际日期！" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
								return;
							} 
						}
					}
				}
				if(isCopy == 0){
					if(supMobile == 1){
						if(isStart != "starting"){
							//支持手机
							var start = new Date();
							var end = new Date(($("#statTime").val()).replace("-", "/").replace(
									"-", "/"));
							var diffTime = end - start;
							diffTime = diffTime/1000/60;
							if (diffTime<=30) {
								$('<div class="c-fa">'+ "支持手机端需提前半个小时创建！" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
								return;
							} 
						}
					}else{
						if(isStart != "starting"){
							var start = new Date();
							var end = new Date(($("#statTime").val()).replace("-", "/").replace(
									"-", "/"));
							if (end <= start) {
								$('<div class="c-fa">'+ "选择日期应大于实际日期！" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){$(this).remove();});
								return;
							} 
						}
					}
				}
				if(startDate){
					var sDate = startDate.substring(0,10);
					var eDate = endDate.substring(0,10);
					var sTimes = startDate.substring(11);
					var eTimes = endDate.substring(11);
					$("#startTimes").val(sTimes);
					$("#endTimes").val(eTimes);
					$("#startDate").val(sDate);
					$("#endDate").val(eDate);
				}
				$.ajax({
					url : rootPath+"/liveOpenCourse/add",
					type : "post",
					data : $("#conForm").serialize(),
					dataType:"json",
					beforeSend:function(XMLHttpRequest){
						$(".saveInfo").hide();
			            $(".loading").show();
			            $(".loading-bg").show();
			        },
					success : function(data){
						console.log(data);
						if(data.msg == 'success'){
							console.log("成功保存");
							location.href=rootPath+"/liveOpenCourse/toLiveShow"
						}else{
							$.msg(data.msg);
							$(".saveInfo").show();
						}
					},
			        complete:function(XMLHttpRequest,textStatus){
						$(".loading").hide();
			            $(".loading-bg").hide();
			        }
				});
			});
		},
		getTeachers : function(){
			$.ajax({
				url : rootPath+"/liveOpenCourse/getTeachers",
				type : "post",
				dataType : "json",
				success : function(jsonData){
					var teacherId = $("#teacherId").val();
					$.each(jsonData,function(i,item){
						if(teacherId == item.id){
							$("#teacherList").append('<option selected="selected" value="'+item.id+'">'+item.name+'</option>');
						}else{
							$("#teacherList").append('<option value="'+item.id+'">'+item.name+'</option>');
						}
					});
					$("#teacherList").select2();
				}
			});
		},
		getAssistants : function(){
			$.ajax({
				url: rootPath+"/simpleClasses/queryTeachers",
				type:"post",
				data : {"type":"ASSISTANT"},
				dataType : "json",
				success : function(jsonData){
					var teacherId = $("#assistantId").val();
					$.each(jsonData,function(i,item){
						if(teacherId == item.id){
							$("#assistantList").append('<option selected="selected" value="'+item.id+'">'+item.name+'</option>');
						}else{
							$("#assistantList").append('<option value="'+item.id+'">'+item.name+'</option>');
						}
					});
					$("#assistantList").select2();
				}
			});
		},
		getTwpItems : function(pId){
			$("#twoItem").html("");
			$.ajax({
				url : rootPath+"/liveOpenCourse/getTwoItemsByOneId",
				type : "post",
				data : {"pId":pId},
				dataType : "json",
				success : function(jsonData){
					var twoId = $("#twoId").val();
					$.each(jsonData,function(i,item){
						if(twoId == item.id){
							$("#twoItem").append('<option selected="selected" value="'+item.id+'">'+item.itemName+'</option>');
						}else{
							$("#twoItem").append('<option value="'+item.id+'">'+item.itemName+'</option>');
						}
						
					});
				}
			});
		},
		savePic : function(){
			$.ajaxFileUpload({
				url : rootPath+"/classType/savePic;"+ window["sessionName"] + "=" + window["sessionId"],
				secureuri : false,// 安全协议
				async : false,
				fileElementId : 'imgData',
				dataType:'json',
				type : "POST",
				success : function(data) {
				  $("#sourcePic").attr("src",data.url);
				  $("#target").parent().html('<img id="target" src="'+data.url+'" style="width:516px;height:282px;"/>');
			      $("#target").trigger("change");
			      $(".p1 img").attr("src",data.url);
			      $(".p2 img").attr("src",data.url);
			      $(".p3 img").attr("src",data.url);
				},
				error:function(arg1,arg2,arg3){
					//console.log(arg1);
				},
				loadingEle: '#target',
				fileName: 'imgData'
			});
		},
		saveCutPic : function(){
			$.ajax({
				url : rootPath + "/liveOpenCourse/saveCutPic",
				data : {
					path : $("#target").attr("src"),
					x : $("#x").val(),
					y : $("#y").val(),
					w : $("#w").val(),
					h : $("#h").val()
				},
				type : "post",
				dataType : "json",
				success : function(data) {
					var imgUrl = $("#imgUrl").val();
					var quan = "http://"+imgUrl+"/"+data;
					chooseOnePic(quan,data);
				}
			})
			$("#chooseDiv").css("display", "none");
			$("#stopDiv").css("display", "none");
			return;
		}
	}
	$(document).ready(function() {
		Forms.init();
	})
	window.Forms = Forms;
})(jQuery)