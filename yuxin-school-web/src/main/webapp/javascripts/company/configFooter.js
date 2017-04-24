var editor=null;
    	var editor0=null;
    	var editor1=null;
    	var editor2=null;
    $(function(){
    	$selectMenu('org_service');
    	editor=CKEDITOR.replace( 'newsContents');
    	editor0=CKEDITOR.replace( 'newsContents0');
    	editor1=CKEDITOR.replace('newsContents1');
    	editor2=CKEDITOR.replace('newsContents2');
    	editor.config.toolbar=[
    			['Source','Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
    			['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'],
    			['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
    			['Link','Unlink','Anchor'],
    			['Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak'],
    			['Code'],    
    		    ['Styles','Format','Font','FontSize'],
    		    ['TextColor','BGColor'],
    		    ['Maximize', 'ShowBlocks','-','About']
    		];
    	editor0.config.toolbar=[
    			['Source','Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
    			['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'],
    			['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
    			['Link','Unlink','Anchor'],
    			['Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak'],
    			['Code'],    
    		    ['Styles','Format','Font','FontSize'],
    		    ['TextColor','BGColor'],
    		    ['Maximize', 'ShowBlocks','-','About']
    		];
    	editor1.config.toolbar=[
    			['Source','Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
    			['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'],
    			['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
    			['Link','Unlink','Anchor'],
    			['Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak'],
    			['Code'],    
    		    ['Styles','Format','Font','FontSize'],
    		    ['TextColor','BGColor'],
    		    ['Maximize', 'ShowBlocks','-','About']
    		];
    	editor2.config.toolbar=[
    			['Source','Bold','Italic','Underline','Strike','-','Subscript','Superscript'],
    			['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'],
    			['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
    			['Link','Unlink','Anchor'],
    			['Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak'],
    			['Code'],    
    		    ['Styles','Format','Font','FontSize'],
    		    ['TextColor','BGColor'],
    		    ['Maximize', 'ShowBlocks','-','About']
    		];
    	
    	editor.config.baseFloatZIndex=10100;
    	editor0.config.baseFloatZIndex=10100;
    	editor1.config.baseFloatZIndex=10100;
    	editor2.config.baseFloatZIndex=10100;
    	var desc="";
    	var desc0="";
    	var desc1="";
    	var desc2="";
    	if($("#about").val()){
    		desc=decodeURI($("#about").val());	
    	}
    	if($("#connect").val()){
    		desc0=decodeURI($("#connect").val());
    	}
    	if($("#privacy").val()){
    		desc1=decodeURI($("#privacy").val());	
    	}
    	if($("#legal").val()){
    		desc2=decodeURI($("#legal").val());	
    	}
    	//alert($("#privacy").val());
    	//alert($("#legal").val());
    	
    	
    	var detailDesc=desc.replace("\r\n","<br>&nbsp;&nbsp;");
    	var detailDesc0=desc0.replace("\r\n","<br>&nbsp;&nbsp;");
    	var detailDesc1=desc1.replace("\r\n","<br>&nbsp;&nbsp;"); 
    	var detailDesc2=desc2.replace("\r\n","<br>&nbsp;&nbsp;"); 
    	editor.setData(detailDesc); 
    	editor0.setData(detailDesc0); 
    	editor1.setData(detailDesc1); 
    	editor2.setData(detailDesc2); 
    	editor.config.customConfig='config.js';
    	editor0.config.customConfig='config.js';
    	editor1.config.customConfig='config.js';
    	editor2.config.customConfig='config.js';
    	//判断是否为自主域名
    	isSelf();
	    })

	//判断是否为自主域名
	function isSelf(){
    	$.ajax({
    		url:rootPath+"/sysPageHeadFoot/isEnterootPathriseVersion",
    		type:"post",
    		success:function(data){
    			if(data){
    				$.ajax({
    			        type:"post",
    			        url:rootPath+"/sysPageHeadFoot/querySelfCompany",
    			        success:function(result){
    			        	$("#copyrightList").html('');
    			        	var html="";
    			        	html+="<p><span id='i-copyright' style='padding:15px'>"+result.copyright+"</span> <span id='i-name'>"+result.companyName+"</span>　版权所有　    <a href='javascript:;' id='i-icp'>"+result.registNo+"</a></p>";
    			        	$("#copyrightList").append(html);
    			        }
    				});
    			}else{
    				var html="";
    	        	html+="<p><span id='i-copyright' style='padding:15px'>@2014-2017</span> <span id='i-name'>云朵课堂</span>　技术支持　   <a href='http://www.miibeian.gov.cn' id='i-icp' target='_blank'>京ICP备15011506号</a></p>";
    	        	$("#copyrightList").append(html);
    	        	$("#nameId").val("云朵课堂");
    	        	$("#bqInfo").val("@2014-2017    技术支持");
    	        	$("#baInfo").val("京ICP备15011506号");
    			}
    		}
    	})
    }

 //处理CKEDITOR的值
 function CKupdate() {
    for (instance in CKEDITOR.instances){
      CKEDITOR.instances[instance].updateElement();
    }
 }		
function save(flag){
	CKupdate();
	var id = $("#companyId").val();
	var value=null;
	if(flag=="1"){
		value = $("#newsContents").val();
		if($("#about").length==0){
			$("#companyId").after('<input id="about"  type="hidden"/>');
		}
		$("#about").val(encodeURI(value));
	}
	if(flag=="2"){
		value = $("#newsContents0").val();
		if($("#connect").length==0){
			$("#companyId").after('<input id="connect"  type="hidden"/>');
		}
		$("#connect").val(encodeURI(value));
	}
	if(flag=="3"){
		value = $("#newsContents1").val();
		if($("#privacy").length==0){
			$("#companyId").after('<input id="privacy"  type="hidden"/>');
		}
		$("#privacy").val(encodeURI(value));
	}
	if(flag=="4"){
		value = $("#newsContents2").val();
		if($("#legal").length==0){
			$("#companyId").after('<input id="legal"  type="hidden"/>');
		}
		$("#legal").val(encodeURI(value));
	}
	value=encodeURI(value);
	$.ajax({
		url:rootPath+"/sysPageHeadFoot/saveFoot",
		type:"post",
		data:{
			"flag":flag,
			"id":id,
			"value":value
		},
		success:function(text){
			//alert("保存成功!");
			$.msg("保存成功!");
		}
		
		
	})
	
}
function cancel(flag){
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
	if(value){
		value=encodeURI(value);
	}
	
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
			if(flag=="1"){
				editor.setData(decodeURI(content)); 
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
			//alert("数据未做更改!");
			$.msg("数据未做更改!");
		}
	
	
}