$(function(){
//	  <a class="btn btn-big btn-success back_step" >上一步</a>
//  <a class="btn btn-big btn-success save_step" >保存</a>
//  <a class="btn btn-big btn-success save_next_step" >保存并继续</a>
//  <a class="btn btn-big btn-default back_list" >取消</a>

	//从排课页面返回到办好基本信息页面
	$(".back_step").click(function(){
		var moduleId = $("#id").val();
		window.location.href = rootPath+"/classModuleNo/editModule/"+moduleId;
	});
	
	//保持并继续
	$(".save_next_step").click(function(){
		console.log($("#submitForm").serialize());
		var isNull = checkLessionInfo(".btn.btn-sm.btn-success:last");
		if(!isNull){
			return false;
		}
		$("#submitForm").submit();
	});
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
				if(i == 2){
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
});