(function($) {

	var Forms = {
		init : function() {
			 var $this=this;
			 //点击保存按钮
			 $("#websiteTitle").on('blur',function(){
					var content=$(this).val();
					if(content.length<0&&content!=""){
						return;
					}
					var d=$.formatstr(content);
					$(this).val(d);
			 });
			 $("#websitKeyWords").on('blur',function(){
					var content=$(this).val();
					if(content.length<0&&content!=""){
						return;
					}
					var d=$.formatstr(content);
					$(this).val(d);
			 });
			 $(".savebuttoms").on('click',function(){
				 var web_titles = $("#websiteTitle").val();
				 var web_keys = $("#websitKeyWords").val();
				 var web_desciption =$("#websitedescription").val();
				 if(web_titles.length>=500){
					 $.msg("网站标题不能超过500个字符");
					 return;
				 }
				 if(web_keys.length>=500){
					 $.msg("SEO关键字不能超过500个字符");
					 return;
				 }
				 if(web_desciption.length>=500){
					 $.msg("网站描述不能超过500个字符");
					 return;
				 }
				 $.ajax({ 
	        		  type: "post", 
	        		  async : false,
	        		  url: rootPath+"/sysConfigSeo/addSeo", 
	        		  data: {"keywords":web_keys,"title":web_titles,"description":web_desciption,"type":$("#typesContent").val()},
	        		  dataType:'json',
	        		  success: function(jsonData){
	        			  $.msg("操作成功!",function(){
	     					 window.location.href=rootPath+"/sysConfigIco/showIco";
	     				 });
	        		  }
				 });
			 });
			 
			 //点击取消
			 $(".canclebtn").on('click',function(){
				 var title=$("#titleContent").val();
				 var key=$("#keyContent").val();
				 var des=$("#desContent").val(); 
				 $("#websiteTitle").val(title);
				 $("#websitKeyWords").val(key);
				 $("#websitedescription").val(des);
				 window.location.href=rootPath+"/sysConfigIco/showIco";
			 });
			//点击返回
			 $(".hcancle").on('click',function(){
				 window.location.href=rootPath+"/sysConfigIco/showIco";
			 });
		}
	}
	
	$(document).ready(function() {
		Forms.init();
	})
	window.Forms = Forms;
})(jQuery)