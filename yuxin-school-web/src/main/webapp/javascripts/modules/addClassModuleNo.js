$(function() {
	//窗体加载时加载模块名称
	$.cookie("classTypeIdFlag",$("#classTypeId").val(),{ path: "/"});
	$.cookie("methed",$("#methed").val(),{ path: "/"});
	$.cookie("courselable",$("#courselable").val(),{ path: "/"});
	var secondItem = $("#itemSecondId").find("option").eq(0);
	var itemId = secondItem.val();
	if(itemId){
		$("#moduleId").getModuleBySecondItem(itemId, 0);
	}
	
	var classTypeId = $("#classTypeId").val();
	var editModuleNo = $("#editModuleNo").val();
	if(classTypeId > 0 && editModuleNo == 0){
		//用jQuery-cookie存放班型ID的值
		if($("#teachMethod").html() == "直播" ){
			var ulObj = $(".clear");
			$(ulObj).find("li").each(function(){
				$(this).removeClass("step").addClass("step2");
			});
		}
	}
	
	//加载日期控件
	$("#startDate").datetimepicker({
		lang:'ch',
		timepicker:false,
		format:'Y-m-d',
		onSelectDate: function(selectedDate){
			generateModuleNoName();
		}
	});
	
	function generateModuleNoName(){
		//生成模块名称
		var module = $("#moduleId").find("option:selected");
		var moduleName = module.html();
		if(!moduleName){
			moduleName = $("#moduleName").html();
		}
		var moduleMethed = $("#teachMethod").html();
		var campusNo = $("#campus").children(".active").attr("campusno");
		if(!campusNo){
			campusNo = $("#campus_no").val();
		}
		
		var lessonPoint = $("#startDate").val();
		if (lessonPoint) {
			var pointArr = "";
			pointArr = lessonPoint.split("-");
			lessonPoint = pointArr[0] + pointArr[1]+ pointArr[2];
		} else {
			$('<div class="c-fa">'+ "开班点不能为空" +'</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
				$(this).remove();
			});
			return false;
		}
		if (moduleMethed == "面授") {
			$("#moduleNoName").val(moduleName + "-" + campusNo + "-" + moduleMethed + "-" + lessonPoint);
		} else {
			$("#moduleNoName").val(moduleName + "-" + moduleMethed + "-" + lessonPoint);
		}
	}
	// 根据学科获取对应的考期
	$('#itemOneId').change(function() {
		var oneId = $(this).val();
		$("#examTermId").getTerm(oneId);
	});

	// 根据学科小类获取对应的模块
	$("#itemSecondId").change(function() {
		var $this = $(this).val();
		$("#moduleId").getModuleBySecondItem($this, 0);
	});
	
	//选择校区
	$('.operate .s-list').on('click','.btn',function(){
		$(this).addClass("active").siblings(".btn").removeClass("active");
		$("#campus_no").val($(this).attr("campusNo"));
		$("#campus_id").val($(this).attr("campusid"));
		
		var startDate = $("#startDate").val();
		if(startDate){
			generateModuleNoName();
		}
	});

	// 根据模块展示其对应的基本信息
	// 根据模块查询对应的老师、班主任、助教
	$("#moduleId").change(
			function() {
				var $this = $(this).find("option:selected"); // 获取选中的对象
				// 获取对应的 ==> 授课方式 、总课时、课程单元类型
				var methed = $this.attr("methed");
				var total = $this.attr("total");
				var type = $this.attr("type");
				var desc = $this.attr("desc");
				if (methed) {
					$("#teachMethod").html(methed);
					$("#teachMethod").next().val(methed);
					//只有在面授的情况下，展示安排教室
					if(methed == "面授"){
						var ulObj = $(".clear");
						$(ulObj).find("li").each(function(){
							$(this).removeClass("step2").addClass("step");
						});
						$(".campus_div").show();
					}else{
						var ulObj = $(".clear");
						$(ulObj).find("li").each(function(){
							$(this).removeClass("step").addClass("step2");
						});
						$(".campus_div").hide();
					}
				}
				if (total) {
					$("#totalClassHour").html(total);
					$("#totalClassHour").next().val(total);
				}
				if (type) {
					$("#moduleType").html(type);
				}
				if (desc) {
					$("#moduleDesc").html(desc);
				}
			});

	//判断当前班号是增加还是更新
	var moduleNoId = $("#id").val();
	if(!moduleNoId){
		// 根据当前的学校获取对应的开课校区
		$("#campus").getCampusToModuleNo();
	}
	// 根据校区选择教室
	$("#campus").change(function() {
		var $this = $(this).find("option:selected");
		var val = $this.val();
		if (val) {
			$("#classRoom").empty();
			$("#classRoom").getClassRoom($this.val());
		}

	});
	
	// jquery - form验证
	$.extend($.validator.messages, {
		required : "必选字段",
		digits : "只能输入整数",
		maxlength : jQuery.validator.format("请输入一个 长度最多是 {0} 的字符串"),
		minlength : jQuery.validator.format("请输入一个 长度最少是 {0} 的字符串"),
		min : jQuery.validator.format("请输入一个最小为{0} 的值")
	});
	
	function getOption1() {
		var rules = {
			"moduleNo.itemOneId" : {
				required : true
			},
			"moduleNo.itemSecondId" : {
				required : true
			},
			"moduleNo.moduleId" : {
				required : true
			},
			"moduleNo.lessonHour" : {
				required : true,
				digits : true,
			},
			"moduleNo.startTime" : {
				required : true,
			},
			"moduleNo.endTime" : {
				required : true,
			}

		};
		return rules;
	}
	;

	// 验证视频基本信息
	$("#submitForm").validate(
			{
				errorElement : 'span',
				errorClass : 'help-block',
				focusInvalid : false,
				ignore : "",
				rules : getOption1(),

				invalidHandler : function(event, validator) {

				},

				highlight : function(element) {
					$(element).closest('.form-group').addClass('has-error');
					if (!$(element).prev() && !$(element).is('radio')
							&& !$(element).is('checkbox')) {
						$(element).wrap('<div class="input-icon right"></div>')
								.before('<i class="icon-warning-sign"></i>');
					}
				},

				unhighlight : function(element) {
					$(element).closest('.form-group').removeClass('has-error');
					$(element).prev().remove().unwrap(
							'<div class="input-icon right"></div>');
				},

				success : function(label) {
					label.closest('.form-group').removeClass('has-error');
				},
				onkeyup : false
			});

	// 页面加载结束的地方
	
	//保存模块并继续
	$("#saveAndGo").click(function(){
		var validate = validateModuleNo();
		if(!validate){
			return false;
		}
		
		$.cookie("classTypeIdFlag",$("#classTypeId").val(),{ path: "/"});
		$("#next").val("next");
		//去数据库检查当前班号是否已经存在，不能有重复的班号
		var moduleNoName = $("#moduleNoName").val();
		var moduleNoid = $("#id").val();
		//判断当前校区ID是否为空，如果为空赋值
		var camId = $("#campus_id").val();
		if(!camId){
			$("#campus_id").val($("#campus a.active").attr("campusid"));
		}
		 $.ajax({
	            type: "get",
	            data:{"name":moduleNoName, "id":moduleNoid},
	            url: rootPath + "/classModuleNo/findModuleNoByName",
	            success: function (data) {
	            	if(data == "exist"){
						$('<div class="c-fa">该班号名称已经存在，请重新更换班号名称</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
							$(this).remove();
						});
					}else{
						$("#moduleForm").submit();
					}
	            }
	        });
	});
	$("#saveNotGo").click(function(){
		var validate = validateModuleNo();
		if(validate){
			$("#moduleForm").submit();
		}
	});
	
	
	//填写课时的时候控制职能填写数字
	onlyNum("planEnrollStudents");
});

//验证班号信息
function validateModuleNo(){
	var module = $("#moduleId").val();
	if(!module){
		$('<div class="c-fa">请选择单元名称</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
			$(this).remove();
		});
		return false;
	}
	
	var moduleNoName = $("#moduleNoName").val();
	if(!moduleNoName){
		$('<div class="c-fa">班号名称不能为空</div>').appendTo('body').fadeIn(100).delay(1000).fadeOut(200,function(){
			$(this).remove();
		});
		return false;
	}
	return true;
}

function onlyNum(id){
	$("#"+id+"").keyup(function(){     
        var tmptxt=$(this).val();     
        $(this).val(tmptxt.replace(/\D|^0/g,''));     
    }).bind("paste",function(){     
        var tmptxt=$(this).val();     
        $(this).val(tmptxt.replace(/\D|^0/g,''));     
    }).css("ime-mode", "disabled"); 
}
