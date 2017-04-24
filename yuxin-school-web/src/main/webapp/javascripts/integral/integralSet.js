/**
 * 在jQuery加载完成后执行
 */
(function($,cicDescription){
	var Model = {};
	   	Model.ajax = function(url,dataInfo,func){
	   		 $.ajax({ 
	   	 		  type: "post", 
	   	 		  url : rootPath+url, 
	   	 		  data: dataInfo,
	   	 		  success: func,
	   	 	  });
	   	};
	   	Model.msg = ["积分名称不能为空",
	   	             "积分名称不能超过10个字",
	   	             "请上传积分图标",
	   	             "积分说明不能为空",
	   	             "积分兑换比例不能为空",
	   	             "保存成功",
	   	             "请填写有效的正整数",
	   	             "积分说明不能超过200字",
	   	             "积分描述过长"];
	   	Model.init = function(){
	   		this.event();
	   		window.upFile = this.upFile;
	   	}
	   	//绑定事件
	   	Model.event = function(){
	   		var _this = this;
	   		$(".l-upload,.trans").on('click',function(){
	   			$("#imgData").trigger('click');
	   		});
//	   		$("#imgData").on('change',function(){
//	   			_this.upFile();
//	   		});
	   		$(".save-but").on('click',function(){
	   			_this.save();
	   		});
	   		$(".change-rate2").on('blur',function(){
	   			var num = $(this).val();
	   			checkNum(num);
	   		});
	   		$('.btn-default').on('click',_this.cancle);
	   	}
	   	/**
	   	 * 保存
	   	 */
	   	Model.save = function(){
	   		var data = {};
	   		CKupdate();
	   		if($(".point-name").data("id")){
	   			data.id = $(".point-name").data("id");
	   		}
	   		data.name = $(".point-name").val(),
	   		data.ico = $("#showImg").data("url"),
	   		data.description = $("#description").val(),
	   		data.exchangeScale = $(".change-rate2").val();
	   		
	   		if(!data.name || !$.formatstr(data.name)){
	   			$.msg(this.msg[0]);
	   			return;
	   		}
	   		if(data.name.length>10){
	   			$.msg(this.msg[1]);
	   			return;
	   		}
	   		if(!data.ico){
	   			$.msg(this.msg[2]);
	   			return;
	   		}
	   		if(!data.description || !$.formatstr(data.description)){
	   			$.msg(this.msg[3]);
	   			return;
	   		}
	   		/*if(data.description.length>200){
	   			$.msg(this.msg[7]);
	   			return;
	   		}*/
	   		if(!checkNum(data.exchangeScale)){
	   			return;
	   		}
	   		data.description = encodeURI(data.description);//.replace("<p>","").replace("</p>","")
	   		if(data.description.length>1000){
	   			$.msg(this.msg[8]);
	   			return;
	   		}
	   		Model.ajax("/companyIntegralConfig/saveOrUpdate", data, function(result){
	   			if(result)
	   				$.msg(Model.msg[5]);
	   		});
	   	}
	   	Model.cancle = function(){
	   		window.location.reload();
	   	}
	   	/**
	   	 * 格式化小数
	   	 */
	   	Model.parseInt = function(str){
	   		str = parseInt(str);
	   		if(isNaN(str))
	   			return false;
	   		return Math.abs(str);
	   	}
	   	/**
	   	 * 上传图片
	   	 */
	   	Model.upFile = function(){
	   		$.ajaxFileUpload({
				url : rootPath+"/companyIntegralConfig/upIntegralFile;"+ window["sessionName"] + "=" + window["sessionId"],
				secureuri : false,// 安全协议
				async : false,
				fileElementId : 'imgData',
				dataType:'json',
				type : "POST",
				success : function(data) {
					if("url" in data){
						$("#showImg").attr('src',data.url).data("url",data.picPath);
					}else{
						$.msg('上传失败');
					}
				},
				error : function(resp,msg,err){
					console.log(resp);
					$.msg('上传失败');
				}
			});
	   	}
	function checkNum(num){
		if(!num){
			$.msg(Model.msg[4]);
		return false;
		}
		intNum = Model.parseInt(num);
		if(!intNum){
			$.msg(Model.msg[6]);
		return false;
		}
		$(".change-rate2").val(intNum);
		return true;
	}
	 //处理CKEDITOR的值
	function CKupdate() {
	    for (instance in CKEDITOR.instances){
	      CKEDITOR.instances[instance].updateElement();
	    }
	}
	/**
	 * 页面加载完成后执行
	 */
	$(function(){
		Model.init();
		//编辑器赋值
		var editor=CKEDITOR.replace('description');
		editor.config.toolbar=[
				[ 'mode', 'document', 'doctools' ], [ 'Source', '-', 'NewPage' ] ,
				[ 'basicstyles', 'cleanup' ],
				[ 'Bold', 'Italic', 'Underline', 'Strike', 'Subscript', 'Superscript' ] ,
				[ 'list', 'indent', 'blocks', 'align', 'bidi' ],  
				[ 'NumberedList', 'BulletedList', '-', 'Outdent', 'Indent', 'JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock' ],
				[ 'Link', 'Unlink' ] ,
				[ 'Image', 'Table' ],
				[ 'Styles', 'Format', 'Font', 'FontSize' ],
			    [ 'TextColor', 'BGColor' ],
				[ 'Maximize'] ,
				[ '-' ] ,
				[ 'About' ]
		];
		editor.config.baseFloatZIndex = 10100;
		var detailDesc = decodeURI(cicDescription).replace("\r\n","<br>&nbsp;&nbsp;");
		editor.setData(detailDesc);
		editor.config.customConfig ='config.js';
	});
})(jQuery,cicDescription);