;(function($){

	var msg = function(){
		this.msgError="操作失败，请刷新后尝试！";
	};

	var Page = {

		init : function(){
			Page.onBtn();
			
		},
		onBtn : function(){
			/*显示标签内容*/
			$(".tagBtn").on("click",function(){
				Select.queryItemSecondIdList($(this).attr("itemOneId"));
				if($(".hidden_tag").attr("tag_count")>0){
					$(".tagManage").show();
				}else{
					$.msg("请先添加学科小类！")
				}
			})
			$(".closeBtn").on("click",function(){
				$("._itemSecondId").css("margin-left","0px");
				$("._itemSecondId").css("left","0px");
				$(".tagManage").hide();
			})
			$('body').on('click','.xg' , function(){ 
				$(this).prev().find("input").attr("style","").removeAttr("readonly").addClass("xgInput");
				
				$(".xgInput").focus().val($(".xgInput").val());
				$(".xgInput").attr("oldxg",$(".xgInput").val());
				
				$(this).next().remove();
				$(this).prev().append('<i class="iconfont save_" title="保存">&#xe616;</i>')
				$(this).remove();
				
			})
			.on('keydown','.xgInput',function(e){
				if(e.keyCode==13){
					$this=$(".xgInput");
					 if(!$this.val()){
						 $.msg("请先输入内容！");
						 return false;
					 }
					if($this.attr("oldxg")!=$this.val()){
						Select.checkTag($(".hidden_tag").attr("itemSecondId"),$this.val(),$this.attr("level"));
					}
					Select.editTag($this.attr("tagId"),$(".hidden_tag").attr("itemSecondId"),$this.val(),$this.attr("level"),$this.attr("oldxg"));
				}
			})
			.on('click','.save_',function(){
				$this=$(".xgInput");
				 if($this.length>0 && !$this.val()){
					 $.msg("请先输入内容！");
					 return false;
				 }
				if($this.attr("oldxg")!=$this.val()){
					Select.checkTag($(".hidden_tag").attr("itemSecondId"),$this.val(),$this.attr("level"));
				}
				Select.editTag($this.attr("tagId"),$(".hidden_tag").attr("itemSecondId"),$this.val(),$this.attr("level"),$this.attr("oldxg"));
			})
			.on('blur','.xgInput',function(){
				if($(this).val()==$(this).attr("oldxg")){
					$this=$(".xgInput");
					$(".xgInput").attr("style","background-color:transparent;border:0px;");
					$(".xgInput").attr("readonly","readonly");
					$this.removeClass("xgInput");
					$this.removeAttr("oldxg");
					$(".save_").parent().parent().append('<i class="iconfont pan xg" title="修改">&#xe625;</i><i class="iconfont cha" title="删除">&#xe610;</i>')
					$(".save_").remove();
				}
//				else if(!$(this).val()){
//					$this=$(".xgInput");
//					$this.val($this.attr("oldxg"));
//					$(".xgInput").attr("style","background-color:transparent;border:0px;");
//					$(".xgInput").attr("readonly","readonly");
//					$this.removeClass("xgInput");
//					$this.removeAttr("oldxg");
//				}
			})
			.on('click' , '.tj' , function(){ 
				if(document.getElementById("newAddInput")){
					$.msg("请先保存当前内容");
					return false;
				}
				$(this).before('<li class="list_li" style="border:0px;"><p><input type="text" class="tjInput" id="newAddInput" level="'+$(this).attr("level")+'"  onkeydown="this.onkeyup();" onkeyup="this.size=(this.value.length>this.size?parseInt(this.value.length+2):this.size);" maxlength="45" /><span class="iconfont save_tj" style="padding-left: 7px;" title="保存">&#xe616;</span></p></li>');
				$(".tjInput").focus();
			})
			.on('keydown','.tjInput',function(e){
				if(e.keyCode==13){
					Select.saveTag($(".hidden_tag").attr("itemOneId"),$(".hidden_tag").attr("itemSecondId"),$(this).attr("level"),$(this).val());
				}
			})
			.on('blur','.tjInput',function(){
				if($(this).val().length<=0){
					$(this).parent().parent().remove();
				}
			})
			.on('click','.save_tj',function(){
				Select.saveTag($(".hidden_tag").attr("itemOneId"),$(".hidden_tag").attr("itemSecondId"),$(".tjInput").attr("level"),$(".tjInput").val());
			})
			.on('click','.cha',function(){
				$this=$(this);
				$.confirm("确定要删除？",function(e){
					if(e){
						Select.delTag($this.prev().prev().find("input").attr("tagId"),$this.prev().prev().find("input").attr("level"));
					}
				})
			})
			.on('click' , '.second_class' , function(){
				$this=$(this);
				$(".second_class").each(function(){ 
					$(this).removeClass("clicked");
				})
				$(this).addClass("clicked");
				$(".hidden_tag").attr("itemSecondId",$(this).attr("itemSecondId"));
				var sumwidth=0;
				$(this).prevAll().each(function(){
					sumwidth+=$(this).outerWidth()+23;
				})
				 $("#page_tag_show").val(parseInt(1)+parseInt(Math.floor((sumwidth+$this.outerWidth())/675)));
				$("._itemSecondId").animate({ left : -sumwidth+'px'}, "normal");
				Select.queryTagslist($(this).attr("itemSecondId"));
			});
			$(".next_tag").on("click",function(){
				if($(".second_class.clicked").attr("sort")==$(".second_class").length-1){
					$.msg("已经是最后了！");
					return false;
				}
				$(".second_class.clicked").next().click();
			})
			$(".prev_tag").on("click",function(){
				if($(".second_class.clicked").attr("sort")==0){
					//$.msg("已经是最前面了！");
					return false;
				}
				$(".second_class.clicked").prev().click();
			})
			/*$(".next_tag").on("click",function(){
				$("._itemSecondId").css("margin-left",parseInt($("._itemSecondId").css("margin-left").substring(0,$("._itemSecondId").css("margin-left").length-2)-10)+"px");
			})
			$(".next_tag").on("mousedown",function(){
				$("._itemSecondId").css("margin-left",parseInt($("._itemSecondId").css("margin-left").substring(0,$("._itemSecondId").css("margin-left").length-2)-10)+"px");
			})
			$(".prev_tag").on("click",function(){
				if($("._itemSecondId").css("margin-left").substring(0,$("._itemSecondId").css("margin-left").length-2)==0){
					return false;
				}
				$("._itemSecondId").css("margin-left",parseInt($("._itemSecondId").css("margin-left").substring(0,$("._itemSecondId").css("margin-left").length-2)-(-10))+"px");
			})
			$(".prev_tag").on("mousedown",function(){
				if($("._itemSecondId").css("margin-left").substring(0,$("._itemSecondId").css("margin-left").length-2)==0){
					return false;
				}
				$("._itemSecondId").css("margin-left",parseInt($("._itemSecondId").css("margin-left").substring(0,$("._itemSecondId").css("margin-left").length-2)-(-10))+"px");
			})*/
		}
		
	};

	var Select = {
		queryItemSecondIdList : function(itemOneId){
			$("._itemSecondId").html('');
			$.ajax({
      			type: "post",
      			url:  rootPath+"/sysConfigItemTag/queryItemSecondIdList/"+itemOneId,
      			dataType : "json",
      			async: false,
      			success: function(jsonData){
      				$.each(jsonData,function(i,tag){
      						$("._itemSecondId").append('<li class="second_class" sort="'+i+'" itemSecondId="'+tag.id+'">'+tag.itemName+'</li>');
      				})
      				$("._itemSecondId").append('<input type="hidden" class="hidden_tag" itemOneId="'+itemOneId+'" itemSecondId="'+$("._itemSecondId").find('li:first').attr("itemSecondId")+'" tag_count="'+jsonData.length+'"/>')
      				$("._itemSecondId").find('li:first').addClass("clicked");
      				Select.queryTagslist($("._itemSecondId").find('li:first').attr("itemSecondId"));
      			}
      		});

		},
		queryTagslist : function(itemSecondId,level){
			 if(itemSecondId){
			 /*清空数据*/
			 if(level){
				 if(level==1){ $(".tag_1").html('');}
				 if(level==2){ $(".tag_2").html('');}
			 }else{
				 $(".tag_1").html('');
				 $(".tag_2").html('');
			 }
			 /*查询*/
			 var data={};
			 data.itemSecondId=itemSecondId;
			 data.level=level || "";
			 $.ajax({
      			type: "post",
      			url:  rootPath+"/sysConfigItemTag/queryTagslist",
      			dataType : "json",
      			data : data,
      			success: function(jsonData){
      				$.each(jsonData,function(i,tag){
      						$(".tag_"+tag.level).append('<li class="list_li"><p class="input" ><input type="text" readonly="readonly" level="'+tag.level+'" style="background-color:transparent;border:0px;" onkeydown="this.onkeyup();" onkeyup="this.size=(this.value.length>this.size?parseInt(this.value.length+2):this.size);" size="'+parseInt(tag.tagName.length+2)+'" tagId="'+tag.id+'" value="'+tag.tagName+'" maxlength="45"/></p><i class="iconfont pan xg" title="修改">&#xe625;</i><i class="iconfont cha" title="删除">&#xe610;</i></li>');
      				})
      				if(level){
      					if(level==1){$(".tag_1").append('<li class="add remove list_li"><p class="input" ></p><i class="iconfont pan tj" level="1">&#xe652;</i></li>');}
      					if(level==2){$(".tag_2").append('<li class="add remove list_li"><p class="input" ></p><i class="iconfont pan tj" level="2">&#xe652;</i></li>');}
      				}else{
      					$(".tag_1").append('<li class="add remove list_li"><p class="input" ></p><i class="iconfont pan tj" level="1">&#xe652;</i></li>');
      					$(".tag_2").append('<li class="add remove list_li"><p class="input" ></p><i class="iconfont pan tj" level="2">&#xe652;</i></li>');
      				}
      			}
      		});
			}
		},
		saveTag : function(itemOneId,itemSecondId,level,tagName){

			 var data={};
			 data.itemOneId=itemOneId;
			 data.itemSecondId=itemSecondId;
			 data.level=level;
			 data.tagName=tagName;
			 
			 if(!data.tagName){
				 $.msg("请先输入内容！");
				 return false;
			 }
			 $.ajax({
      			type: "post",
      			url:  rootPath+"/sysConfigItemTag/saveTag",
      			dataType : "json",
      			data : data,
      			success: function(jsonData){
      				if(jsonData.msg == "success"){
      					$.msg("保存成功！");
      					Select.queryTagslist($(".hidden_tag").attr("itemSecondId"),level);
      				}else{$.msg(jsonData.result);}
      			},
      			error : function(){$.msg(msg.msgError);}
      		});

		},
		editTag : function(tagId,itemSecondId,tagName,level,old){
 			var data={};
 			data.id=tagId;
			data.itemSecondId=itemSecondId;
			data.level=level;
			data.tagName=tagName;
			
			$.ajax({
      			type: "post",
      			url:  rootPath+"/sysConfigItemTag/editTag/"+old,
      			dataType : "json",
      			data : data,
      			success: function(jsonData){
      				if(jsonData.msg == "success"){
      					$.msg("修改成功！");
      					$this=$(".xgInput");
      					$(".xgInput").attr("style","background-color:transparent;border:0px;");
    					$(".xgInput").attr("readonly","readonly");
    					$this.removeClass("xgInput");
    					$(".save_").parent().parent().append('<i class="iconfont pan xg" title="修改">&#xe625;</i><i class="iconfont cha" title="删除">&#xe610;</i>')
    					$(".save_").remove();
						Select.queryTagslist($(".hidden_tag").attr("itemSecondId"),level);
      				}else{$.msg(jsonData.result);}
      			},
      			error : function(){$.msg(msg.msgError);}
      		});

		},
		delTag : function(tagId,level){

			$.ajax({
      			type: "post",
      			url:  rootPath+"/sysConfigItemTag/delTag/"+tagId,
      			dataType : "json",
      			success: function(jsonData){
      				if(jsonData.msg == "success"){
						Select.queryTagslist($(".hidden_tag").attr("itemSecondId"),level);
      				}else{$.msg(jsonData.result);}
      			},
      			error : function(){$.msg(msg.msgError);}
      		});

		},
		checkTag : function(itemSecondId,tagName,level){

			var data={};
			data.itemSecondId=itemSecondId;
			data.tagName=tagName;
			data.level=level;

			$.ajax({
				type : "post",
				url : rootPath+"/sysConfigItemTag/checkTag",
				dataType : "json",
				data : data,
				success : function(jsonData){
					if(jsonData.msg == "error"){
						return false;
					}
				},
				error : function(){
					return false;
				}
			})

		}
	};


	 $(document).ready(function(){
			Page.init();
	 });
})(jQuery)