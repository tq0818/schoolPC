$(function(){
	// 记录当前班号一共有多少个课次
	var lessonsFlag = 0;
	var editLessons = $("#lessonsFlag").val();
	if(editLessons != null && editLessons > 0){
		lessonsFlag = editLessons;
	}
	
	var weeks = new Array("周日","周一","周二","周三","周四","周五","周六");
	
	//判断当前班号是否为面授
	var classTeachType = $("#classTeachType").val();
	if(classTeachType != 'TEACH_METHOD_FACE'){
		$(".steps .clear").find("li").each(function(){
			$(this).removeClass("step").addClass("step2");
		});
	}
	
	//隐藏排课下面的区域,  1表示编辑状态， 0表示新增状态
	var isHide = $("#isHide").val();
	if(isHide == 0){
		$(".lessions_class").hide();
	}
	
	//点击排课的修改按钮
	$('.operate_table').on('click','.btn-success',function(){
		if($(this).html() == "修改"){
			updateRowOfClass(this, true);  
		}else{
			var isNull = checkLessionInfo(this);
			if(!isNull){
				return false;
			}
			if(isHide == 0){
				completeRowOfClass(this);   //判断是否编辑，如果不是编辑状态，则在保存的时候提交整个排课的情况
			}else{
				saveRowOfClass(this);  //如果是编辑状态，修改完之后直接保存到数据库
			}
		}
		
	});
	
	//点击时段的时候给开始结束时间赋值
	$(".lesson-time a").on("click" ,function(){
		var timeName = $(this).attr("lessonscope");
		if(timeName == 'LESSON_TIME_MORNING'){
			$("#startTime").val("09:00");
			$("#lessonTimeStart").val("09:00");
			$("#endTime").val("11:30");
			$("#lessonTimeEnd").val("11:30");
		}else if(timeName == 'LESSON_TIME_AFTERNOON'){
			$("#startTime").val("14:00");
			$("#lessonTimeStart").val("14:00");
			$("#endTime").val("18:00");
			$("#lessonTimeEnd").val("18:00");
		}else if(timeName == 'LESSON_TIME_WHOLE_DAY'){
			$("#startTime").val("09:00");
			$("#lessonTimeStart").val("09:00");
			$("#endTime").val("18:00");
			$("#lessonTimeEnd").val("18:00");
		}else if(timeName == 'LESSON_TIME_NIGHT'){
			$("#startTime").val("19:00");
			$("#lessonTimeStart").val("19:00");
			$("#endTime").val("21:00");
			$("#lessonTimeEnd").val("21:00");
		}else{
			$("#startTime").val("09:00");
			$("#lessonTimeStart").val("09:00");
			$("#endTime").val("11:30");
			$("#lessonTimeEnd").val("11:30");
		}
	});
	
	//排课点击删除处理
	$('.operate_table').on('click','.btn-danger',function(){
		
		if(isHide == 0){
			deleteRowOfClass(this);   //判断是否编辑，如果不是编辑状态，则在保存的时候提交整个排课的情况
		}else{
			var $this = this;
			//需要确认是否删除
			$.confirm("您确定要删除此课次？",function(a){
				if(a){
					deleteRowOfClassFromDateBase($this);  //如果是编辑状态，修改完之后直接保存到数据库
				}
			});
		}
	});
	
	//点击删除按钮，从页面级别删除
	function deleteRowOfClass(obj){
		$(obj).parent().parent().remove();
	}
	
	//点击删除按钮，从数据库中删除
	function deleteRowOfClassFromDateBase(obj){
		var lessonId = $(obj).parent().parent().find(".idClass").val();
		if(typeof(lessonId) == "undefined" || lessonId == null || lessonId == ""){
			deleteRowOfClass(obj);
			return false;
		}
		$.ajax({
			url: rootPath+"/classModuleLesson/del/"+lessonId,
			type:"get",
			dataType:"html",
			success:function(data){
				if(data == "success"){
					$('<div class="c-fa">'+ "删除成功" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
						$(this).remove();
						deleteRowOfClass(obj);
					});
				}else{
					$('<div class="c-fa">'+ "删除失败" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
						$(this).remove();
					});
				}
			},
		});
	}
	
	//判断当前课次的信息是否为空
	function checkLessionInfo(obj){
		var $this = $(obj).parent().parent();
		var inputArr = new Array();
		$($this).find("input").each(function(index){
			inputArr.push($(this).val());
		});
		var arrayMsg = ["","课程日期不能为空","星期不能为空","时段不能为空","课程开始时间不能为空","课程结束时间不能为空","课次名称不能为空","课时不能为空"];
		var arrIndex = 0;
		if(inputArr.length > 0){
			for(var i = 0; i < inputArr.length; i++){
				if(i == 2 || i == 7){
					continue;
				}
				if(inputArr[i] == null || inputArr[i] == ""){
					arrIndex = i+1;
					break;
				}
			}
		}
		if(arrIndex != 0){
			$('<div class="c-fa">'+ arrayMsg[arrIndex] +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
				$(this).remove();
			});
			return false;
		}else{
			return true;
		}
	}
	
	//如果是编辑状态的班号，则直接保存到数据库中
	function saveRowOfClass(obj){
		var $this = $(obj).parent().parent();
		
		completeRowOfClass(obj);
		var inputArr = new Array();
		$($this).find("input").each(function(index){
			inputArr.push($(this).val());
		});
		
		var id = inputArr[8];
		var lessonDate = inputArr[0];
		var weekType = inputArr[1];
		var lessonTimeStart = inputArr[3];
		var lessonTimeEnd = inputArr[4];
		var lessonName = inputArr[5];
		var lessonHour = inputArr[6];
		var liveClassType = inputArr[7];
		if(liveClassType=="大讲堂"){
			liveClassType="LIVE_BIG_CLASS_ROOM";
		}else if(liveClassType=="小班课"){
			liveClassType="LIVE_SMALL_CLASS_ROOM";
		}
		var moduleNoId = $("#moduleNoId").val();
		
		var scope = inputArr[2];
		
		var msgTrue = "";
		var msgFalse = "";
		
		var isTrue = true;
		if(id > 0){
			isTrue = false;
		}
		
		if(isTrue){
			msgTrue = "新增成功！";
			msgFalse = "新增失败！";
		}else{
			msgTrue = "更新成功！";
			msgFalse = "更新失败！";
		}
		
		$.ajax({
			url: rootPath+"/classModuleNo/editLesson",
			data : {
				"id" : id,
				"lessonDate" : lessonDate,
				"weekType" : weekType,
				"scope" : scope,
				"lessonTimeStart" : lessonTimeStart,
				"lessonTimeEnd" : lessonTimeEnd,
				"lessonHour" : lessonHour,
				"lessonName":lessonName,
				"moduleNoId" : moduleNoId,
				"liveClassType":liveClassType
			},
			type:"post",
			dataType:"html",
			success:function(data){
				if(data == "success"){
					$('<div class="c-fa">'+ msgTrue +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
						$(this).remove();
						window.location.reload();
					});
				}else{
					$('<div class="c-fa">'+ msgFalse +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
						$(this).remove();
					});
				}
			},
		});
	}
	
	//编辑完成单独的某节课程
	function completeRowOfClass(obj){
		$(obj).html("修改");
		var $this = $(obj).parent().parent();
		var firstSelect = "";
		$($this).find("select").each(function(index){
			if(index == 0){
				firstSelect = $(this).val();
				$(this).remove();
			}
		});
		$($this).find("input").each(function(index){
			if(index == 2){
				$(this).val(firstSelect);
				$(this).attr("type","text");
			}
			$(this).addClass("readonly");
		});
	}
	
	
	//修改排课的课程内容
	function updateRowOfClass(obj, isTrue){
		var isfirst=1;
		var $this = "";
		if(isTrue){
			$(obj).html("完成");
			$this = $(obj).parent().parent();
		}else{
			$(obj).find(".btn-success").html("完成");
			$this = $(obj);
		}
		
		$($this).find("input").each(function(index){
			if(index != 7){
				$(this).removeClass("readonly");
			}
//			if(index == 1){
//				var weekDay = $(this).val();
//				//给选择星期几赋值
////				$(this).attr("type","hidden");
////				$(this).parent().append(choseWeekDay(weekDay));
//			}
			
			if(index == 2){
				var scope = $(this).val();
				//给选择星期几赋值
				$(this).attr("type","hidden");
				$(this).parent().append(choseScope(scope));
			}
		});
		
		// 激活日期插件
		$this.find(".date-picker-24").datetimepicker({
			format: "yyyy-mm-dd",
			language:  'zh-CN',
	        autoclose: true,
	        startView: 2,
			minView: 2
		}).on("changeDate", function(selectedDate){
			$($this).find("input").each(function(index){
				if(index == 1){
					if(isfirst==1){
						$(this).val(weeks[selectedDate.date.getDay()-1]);
					}else{
						$(this).val(weeks[selectedDate.date.getDay()]);
					}
					return false;
				}
			});
			isfirst++;
		});
		
		var date = new Date();
		date = date.getFullYear() +"-"+(date.getMonth() +1) + "-" + date.getDate();
		//初始化开始时间
		$this.find('.date-picker').datetimepicker({
			format: "hh:ii",
	        language:  'zh-CN',
	    	startDate: date,
			autoclose: 1,
			todayHighlight: 1,
			pickerReferer:"input",
			startView: 1,
			minView: 0,
			maxView: 1,
			forceParse: 0
	    });
	}
	
	/*标签、tab切换*/	
	$(".operate .class-name .btn").click(function(){
		var $this = $(this);
		$this.addClass("active").siblings(".btn").removeClass("active");
		
		var thisVal = $this.attr("lessionType");
		$("#lessonType").val(thisVal);
		
		$(".weekDiv :checkbox").attr("checked", false);
		
		if (thisVal == "LESSON_OFF_JOB" || thisVal == "LESSON_NIGHT") {
			$('.weekDiv input').each(function() {
				$(this).parent().show();
			});
		}
		if (thisVal == "LESSON_WEEKEND") {
			$('.weekDiv input').each(function(index) {
				if (index == 5 || index == 6) {
					$(this).parent().show();
				} else {
					$(this).parent().hide();
				}
			});
		}
		if (thisVal == "LESSON_SATURDAY") {
			$('.weekDiv input').each(function(index) {
					if (index == 5) {
						$(this).parent().show();
					} else {
						$(this).parent().hide();
					}
				});
		}
		if (thisVal == "LESSON_SUNDAY") {
			$('.weekDiv input').each(function(index) {
				if (index == 6) {
					$(this).parent().show();
				} else {
					$(this).parent().hide();
				}
			});
		}
	});
		
	$(".operate .lesson-time .btn").click(function(){
		$(this).addClass("active").siblings(".btn").removeClass("active");
		$("#lessonScope").val($(this).attr("lessonScope"));
	});
	
	//课次新增课
	$("#addLessonForModuleNo").click(function(){
		//判断当前页面是否还有未保存的课次，如果有则提示不让继续添加
		var flag = false;
		$(".date-picker-24").each(function(){
			if(!$(this).hasClass("readonly")){
				flag = true;
			}
		});
		if(flag){
			$('<div class="c-fa">您还有未保存的课次，不能继续添加！</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
				$(this).remove();
			});
			return false;
		}
		var search=$("#liveClassType").val();
		if(search!=-1 && typeof(search)!="undefined"){
			var liveClassType = $("input[name='moduleNo.liveClassType']").val();
		}else{
			var liveClassType = $("input[name='moduleNo.liveClassType']:checked").val();
		}
		if(liveClassType == 'big'){
			liveClassType = "大讲堂";
		}else if(liveClassType == 'small'){
			liveClassType = "小班课";
		}else if(liveClassType == 'undefined'){
			liveClassType="";
		}
		
		var addLesson = '<tr>'+
							    	'<td><input type="text" value="" readOnly class=" input_width_60 date-picker-24" name="lesson['+lessonsFlag+'].lessonDate"/></td>'+
							        '<td><input type="text" value="" readOnly class="readonly input_width_40 " name="lesson['+lessonsFlag+'].weekType"/></td>'+
							        '<td><input type="text" value="" class=" input_width_40" name="lesson['+lessonsFlag+'].scope"/></td>'+
							        '<td><input type="text" value="" class=" input_width_15 date-picker" name="lesson['+lessonsFlag+'].lessonTimeStart" readonly="readonly"/>-'+
							        	   '<input type="text" value="" class=" input_width_15 date-picker" name="lesson['+lessonsFlag+'].lessonTimeEnd" readonly="readonly"/></td>'+
							        '<td><input type="text" value="" name="lesson['+lessonsFlag+'].lessonName"/></td>'+
							        '<td><input type="text" value="" class=" input_width_40 lessonHour" maxlength="3" name="lesson['+lessonsFlag+'].lessonHour"/></td>'+
							        '<td><input type="text" value="'+liveClassType+'" readOnly class="readonly input_width_40 " maxlength="3" name="lesson['+lessonsFlag+'].liveClassTypeFlag"/></td>'+
							        '<td>'+
							        	'<a class="btn btn-sm btn-success" href="javascript:;" >修改</a>'+
							        	'<a class="btn btn-sm btn-danger" href="javascript:;">删除</a>'+
							        '</td>'+
							    '</tr>';
		$("#bodyHtml").children().append(addLesson);
		var $this = $("#bodyHtml").find("tr:last-child");
		updateRowOfClass($this, false);
		lessonsFlag++;
	});
	
	//预排课
	$("#preInitClass").click(function() {
		$("#bodyHtml").find("tr").each(function(index){
			if(index != 0){
				$(this).empty();
			}
		});
		
		//判断当前选中的天数是否为空
		var weekDay = false;
		$('.weekDiv input').each(function(index) {
			if($(this).is(':checked')){
				weekDay = true;
				return false;
			}
		});
		if(!weekDay){
			$('<div class="c-fa">排课天至少选择一天</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(400,function(){
				$(this).remove();
			});
			return false;
		}
		
		//判断开始/结束时间， 开始时间不能大于结束时间
		var beginTime = $("#startTime").val().replace(":","");
		var endTime = $("#endTime").val().replace(":","");
		if(parseInt(beginTime) > parseInt(endTime)){
			$('<div class="c-fa">开始时间不能大于结束时间</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(400,function(){$(this).remove();});
			return false;
		}
		if(parseInt(beginTime) == parseInt(endTime)){
			$('<div class="c-fa">开始时间不能等于结束时间</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(400,function(){$(this).remove();});
			return false;
		}
		
		//判断当前每次课时是否为空，或者大于总课时
		//每课次多长时间
		var lessonHour = $("#lessonHour").val();
		if(lessonHour == null || lessonHour == ""){
			$('<div class="c-fa">每课次课时不能为空</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(400,function(){$(this).remove();});
			return false;
		}
		
		if(lessonHour == 0){
			$('<div class="c-fa">每课次课时不能为0</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(400,function(){$(this).remove();});
			return false;
		}
		
		//该模块一共多少时长
		var totalHour = $("#totalHours").val();
		if(lessonHour != null && totalHour != null && parseInt(totalHour) < parseInt(lessonHour)){
			$('<div class="c-fa">每次课课时不能大于总课时</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(400,function(){$(this).remove();});
			return false;
		}
		
		var totalCount = Math.ceil(totalHour / lessonHour); // 总共分几次课，不够整出向上+1
		//一周有那几天上课
		var strWeek = "";
			$('input[name="moduleNo.lessonDay"]:checked').each(
								function() {
									strWeek += $(this).val() + ',';
								});
		var scope = $("#lessonScope").val();
		var lessonTimeStart = $("#lessonTimeStart").val();
		var lessonTimeEnd = $("#lessonTimeEnd").val();
		var startDate = $("#startDate").val();
		var lessonName = $("#moduleName").val();
		
		var liveClassType = $("input[name='moduleNo.liveClassType']:checked").val();
		if(liveClassType == 'big'){
			liveClassType = "大讲堂";
		}else if(liveClassType == 'small'){
			liveClassType = "小班课";
		}
		
		var url = rootPath + "/classModuleNo/preInitClass/";
			$.ajax({
				url : url,
				type : "post",
				contentType : "application/x-www-form-urlencoded; charset=UTF-8",
				data : {
					"lessonHour" : lessonHour,
					"totalCount" : totalCount,
					"weekType" : strWeek,
					"scope" : scope,
					"lessonTimeStart" : lessonTimeStart,
					"lessonTimeEnd" : lessonTimeEnd,
					"startDate" : startDate,
					"lessonName" : lessonName
				},
				dataType : "json",
				beforeSend:function(XMLHttpRequest){
		              $(".loading").show();
		              $(".loading-bg").show();
		         },
				success:function(jsonData){
					$(".loading").hide();
		            $(".loading-bg").hide();
					var strInput = "";
					for(var i = 0; i < jsonData.length; i++){
						lessonsFlag++;
						strInput += '<tr>'+
		                	'<td><input type="text" value="'+jsonData[i].workDay+'" readonly class="readonly input_width_60 date-picker-24" name="lesson['+i+'].lessonDate"/></td>'+
		                    '<td><input type="text" value="'+jsonData[i].weekType+'" readonly class="readonly input_width_40 " name="lesson['+i+'].weekType"/></td>'+
		                    '<td><input type="text" value="'+jsonData[i].scope+'" class="readonly input_width_40" name="lesson['+i+'].scope"/></td>'+
		                    '<td><input type="text" value="'+jsonData[i].lessonTimeStart+'" class="readonly input_width_15 date-picker" name="lesson['+i+'].lessonTimeStart"/>-'+
		                    	   '<input type="text" value="'+jsonData[i].lessonTimeEnd+'" class="readonly input_width_15 date-picker" name="lesson['+i+'].lessonTimeEnd"/></td>'+
		                    '<td><input type="text" value="'+jsonData[i].lessonName+'" class="readonly " name="lesson['+i+'].lessonName"/></td>'+
		                    '<td><input type="text" value="'+jsonData[i].lessonHour+'" class="readonly input_width_40 lessonHour" maxlength="3" name="lesson['+i+'].lessonHour"/></td>'+
		                    '<td><input type="text" value="'+liveClassType+'" readOnly class="readonly input_width_40 " maxlength="3" name="lesson['+i+'].liveClassTypeFlag"/></td>'+
		                    '<td>'+
		                    	'<a class="btn btn-sm btn-success" href="javascript:;" >修改</a>'+
		                    	'<a class="btn btn-sm btn-danger" href="javascript:;">删除</a>'+
		                    '</td>'+
		                '</tr>';
					}
					$("#bodyHtml").children().append(strInput);
					$(".lessions_class").show();
				}
			});
	});
	
});

//function onlyNum(id){
//	$("."+id+"").keyup(function(){     
//        var tmptxt=$(this).val();
//        if(tmptxt.replace(/\D\[.]|^0/g,'') || ){
//        	
//        }
//        $(this).val(tmptxt.replace(/\D\[.]|^0/g,''));     
//    });
//}

//当排课的课程要修改的时候，下拉框选择星期几
//function choseWeekDay(weekDay){
//	var weeks=new Array("一","二","三","四","五","六","日");
//	if(weekDay && weekDay.length == 2){
//		weekDay = weekDay.substring(1);
//	}
//	var weekDays = '<select>';
//	for(var i = 0; i < weeks.length; i++){
//		var strValue = "";
//		if(weekDay == weeks[i]){
//			strValue = "value='周"+weeks[i]+"'"+"selected";
//		}else{
//			strValue = "value='周"+weeks[i]+"'";
//		}
//		weekDays += "<option "+strValue+">周"+weeks[i]+"</option>";
//	}
//	weekDays += '</select>';
//	return weekDays;
//}

//当前排课的课程要修改的时候，下拉框选择上课时段
function choseScope(scope){
	var scopes = new Array("上午","下午","全天","晚上");
	
	var scopeSelect = '<select>';
	for(var i = 0; i < scopes.length; i++){
		var strValue = "";
		if(scope == scopes[i]){
			strValue = "value='"+scopes[i]+"'"+"selected";
		}else{
			strValue = "value='"+scopes[i]+"'";
		}
		scopeSelect += "<option "+strValue+">"+scopes[i]+"</option>";
	}
	scopeSelect += '</select>';
	return scopeSelect;
}