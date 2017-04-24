/**
 * author zhang.zx
 * zhang.zx
 * 页面js封装
 */
(function($){
	$.ajax({
		url:rootPath+"/editSimpleCourse/isCommonTeacher",
		type:"post",
		data:{},
		success:function(data){
			if(data&data=="1"||data=="2")
				$('#checkIsCommonTeac').hide();
			else
				$('#checkIsCommonTeac').show();
		}
	})
})(jQuery)