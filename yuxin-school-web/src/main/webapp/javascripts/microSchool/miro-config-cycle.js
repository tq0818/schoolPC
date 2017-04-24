$(document).ready(function(){
	$(".sc-type").on('click','a.btn',function(){
		var status=$(this).hasClass('btn-success');
		if(!status){
			$(this).addClass('btn-success').siblings('a').removeClass('btn-success');
		}
	});
	$("#cycleUrl").on("mouseenter", function() {
		$(this).attr("placeholder","");
	}).on("mouseleave", function() {
		$(this).attr("placeholder","请输入完整的网页地址");
	});
	$selectMenu('org_service');
	queryCycleData();
	 $("input.shangchuan").on('click',function(){
		  var _this = $(this).parents('div.left').next().find('.set-infos').find('a.btn-submit');
          if ( !(_this.hasClass('.btn-primary')) ){
              _this.addClass('btn-primary').removeClass('btn-prevent');
          }
	  })
	  
	  
	  $(document).on("click",".yesOrno", function(){
		  if($(this).val() == "0"){
			  $(".addbtn").hide();
			  $(".set-focus").hide();
		  }else{
			  $(".addbtn").show();
			  $(".set-focus").show();
		  }
		  saveCycleUseStatus();
	  })
	  
});

//li位置向下移动
function changeStatus(id,sort){
	var liOne=$("#liStatus"+sort);
	var liTwo=$("#liStatus"+sort).next();
	liTwo.after(liOne);
	var liTwoSort=liTwo.attr("_sort");
	var liTwo=liTwo.attr("id1");
	$.ajax({
		type: "post",
		url:  rootPath+"/sysCyclePic/change",
		data: {"id1" : id,"sortOne" : sort,"liTwo" : liTwo, "liTwoSort" : liTwoSort},
		success: function(result){
			queryCycleData();
		}
	});
}  

//li位置向上移动
function upStatus(id,sort){
	var liOne=$("#liStatus"+sort);
	var liTwo=$("#liStatus"+sort).prev();
	liOne.after(liTwo);
	var liTwoSort=liTwo.attr("_sort")
	var liTwo=liTwo.attr("id1");
	$.ajax({
		type: "post",
		url:  rootPath+"/sysCyclePic/change",
		data: {"id1" : id,"sortOne" : sort,"liTwo" : liTwo, "liTwoSort" : liTwoSort},
		success: function(result){
			queryCycleData();
		}
	});
}           
            
//异步加载数据
function queryCycleData(){
	 $.ajax({ 
		  type: "post", 
		  url: rootPath+"/sysCyclePic/queryCycleDatas", 
		  data:{ "picType":"wap" },
		  success: function(result){
			$("#cycleDataLists li").not(":first").remove();
			$("#cycleDataLists").append(result);
			var count=0;
			$("#cycleDataLists").find("li").each(function(i){
				count++;
			});
			if(count==2){
				$("#cycleDataLists").find("li:eq(1)").find("div.direction").css("display","none");
			}
		  }
	  });
}

//添加轮播图
function addCyclePic(){
	$(".loading-bg").show();
	var clickUrl=$("#cycleUrl").val();
	if(clickUrl!=""){
		if(clickUrl.indexOf("http://")!=-1){
			$.msg("输入链接无效");
			$(".loading-bg").hide();
			return;
		}
		clickUrl="http://"+clickUrl;
		var str = clickUrl.match(/http:\/\/.+/);
		if(str==null){
			$.msg("输入链接无效");
			$(".loading-bg").hide();
			return;
		}
	}else{
		clickUrl="";
	}
	
	var valid_flag=0;
	var status=$("#addStatus").attr("class");
	var imgData=$("#imgData").val();
	if(status=="i open"){
		valid_flag=1;
	}
	var picUrl=$("#imgObject").attr("ids");
	if(picUrl==""){
		$.msg("图片不能为空");
		$(".loading-bg").hide();
		return;
	}
	$.ajax({
		url:  rootPath+"/sysCyclePic/addCycles",
		data : {"clickUrl" :clickUrl,"validFlag" : valid_flag,"picUrl":picUrl, "picType":"wap"},
		success: function(result){
			queryCycleData();
			$("#cycleUrl").val('');
			$(".add-focus").css("display","none");
			$(".loading-bg").hide();
			clearData();
		}
	});
}

//修改轮播图
function updateSysCyclePic(id,ele){
	var status;
	$(".is-save"+id).find("p:last").find("a.btn-submit").each(function(){
		status=$(this).hasClass("btn-prevent");
	});
	if(status){
		return;
	}
	var clickUrl=$("#cycleUrl"+id).val();
	if(clickUrl!=""){
		if(clickUrl.indexOf("http://")!=-1){
				$.msg("输入链接无效");
				return;
			}
		clickUrl="http://"+clickUrl;
		var str = clickUrl.match(/http:\/\/.+/);
		if(str==null){
			$.msg("输入链接无效");
			return;
		}
	}else{
		clickUrl="";
	}
	var valid_flag=0;
	var status=$("#addStatus"+id).attr("class");
	if(status=="i open"){
		valid_flag=1;
	}
	$.ajax({
		type: "post",
		url:  rootPath+"/sysCyclePic/updateSysCycles",
		data : {"id" :id, "clickUrl" :clickUrl,"validFlag" : valid_flag,"picUrl":$("#imgOne"+id).attr("ids")},
		success: function(result){
			$.msg("修改成功");
			queryCycleData();
		}
	});
}

//删除轮播图
function deleteSysCyclePic(id,sort){
	$.confirm("确认删除此张轮播图吗?",function(b){
		if(b==true){
			$("#liStatus"+sort).remove();
			 $.ajax({ 
				  type: "post", 
				  url: rootPath+"/sysCyclePic/delSysCycle", 
				  data: {"id" : id},
				  success: function(result){
					  queryCycleData();
				  }
			  });
		}else{
			return; 
		}
	});
}

//清空数据
function clearData(){
	$("#cycleUrl").val('');
	$("#imgObject").attr("src","");
	$("#imgObject").attr("ids","");
	$("#leftCont").find("p.tips-msg").removeClass("none");
	$("#imgObject").css("visibility","hidden");
	$(".add-focus").html(' '+
			'   <div class="focus-title" style="font-size: 16px; color:#333; line-height: 16px;">新的轮播图</div>'+
			'   <div class="fileupload fileupload-new focus-content clear " data-provides="fileupload">'+
			'     <div class="left" id="leftCont">'+
			'         <p class="tips-msg">上传图片尺寸为750*375 <a class="c-uf"href="javascript:;">点击上传</a><input type="file" id="imgDatas" name="imgData" accept="image/*" onchange="changeCyclePic(\'so\',this);" class="up shangchuan"></p>'+
			'         <img id="imgObject" ids="" src="" alt="" style="visibility: hidden;">'+
			'     </div>'+
			'     <div class="right" >'+
			'         <div class="title">配置信息</div>'+
			'        <div class="set-infos">'+
			'             <p>'+
			'                 <span class="t">上传图片：</span>'+
			'         <span class="btns">'+
			'          <input type="file" class="u-f" id="imgData" name="imgData" onchange="changeCyclePic(\'st\',this);" accept="image/*">'+
			'             <a href="javascript:;" class="btn btn-default btn-mini c-uf">上传图片</a>'+
			'        </span>'+
			'                 <span style="color:red;">上传图片尺寸为750*375</span>'+
			'             </p>'+
			'             <p><span class="t">链接地址：</span>'+
			'                 <label for="cycleUrl" class="inputPosi">'+
			'                     <span>http://</span>'+
			'                    <input type="text" id="cycleUrl" class="url" value="" placeholder="请输入完整的网页地址">'+
			'                    <small class="express">已适配到微信端的功能链接：课程、课程包</small>'+
			'                </label>'+
			'            </p>'+
			'             <p><span class="t">是否启用：</span><i class="iconfont normal open"></i><span id="addStatus" class="i open">已启用</span></p>'+
			'             <p>'+
			'                 <span class="actionBut">'+
			'                     <a href="javascript:clearData();" class="btn btn-default btn-cancel-add">取消添加</a>'+
			'                    <a href="javascript:addCyclePic();" class="btn btn-gray btn-submit">保存设置</a>'+
			'                 </span>'+
			'             </p>'+
			'         </div>'+
			'     </div>'+
			' </div>'+
			'  ')
}
//上传图片
function changeCyclePic(type,ele){
	if(type=="so"){
		$.ajaxFileUpload({
			url : rootPath+"/sysCyclePic/UploadCycles;"+ window["sessionName"] + "=" + window["sessionId"],
			secureuri : false,// 安全协议
			async : false,
			fileElementId : 'imgDatas',
			dataType:'json',
			type : "POST",
			success : function(data) {
				$("#imgObject").css('visibility','visible');
				$("#leftCont").find("p.tips-msg").addClass("none");
				$("#imgObject").attr("src",data.url);
				$("#imgObject").attr("ids",data.picPath);
			},
			error : function(resp,msg,err){
				console.log(resp);
			},
			loadingEle: $(ele).parents(".focus-content").find(".left"),
			fileName: 'imgData',
			sessionId: $("#sessionId").attr("value")
		});
	}else{
		$.ajaxFileUpload({
			url : rootPath+"/sysCyclePic/UploadCycles;"+ window["sessionName"] + "=" + window["sessionId"],
			secureuri : false,// 安全协议
			async : false,
			fileElementId : 'imgData',
			dataType:'json',
			type : "POST",
			success : function(data) {
				$("#imgObject").css('visibility','visible');
				$("#leftCont").find("p.tips-msg").addClass("none");
				$("#imgObject").attr("src",data.url);
				$("#imgObject").attr("ids",data.picPath);
			},
			error : function(resp,msg,err){
				console.log(resp);
			},
			loadingEle: $(ele).parents(".focus-content").find(".left"),
			fileName: 'imgData'
		});
	}
		
}

function updateCyclePic(id,ele){
	$.ajaxFileUpload({
		url : rootPath+"/sysCyclePic/UploadCycles;"+ window["sessionName"] + "=" + window["sessionId"],
		secureuri : false,// 安全协议
		async : false,
		fileElementId : 'imgData'+id,
		dataType:'json',
		type : "POST",
		data : {"id":id},
		success : function(data) {
			if(data=="null"){
				alert("上传图片失败!");
			}
			$("#imgOne"+id).css("background-image","url("+data.url+")");
			$("#imgOne"+id).attr("ids",data.picPath);
		},
		error : function(resp,msg,err){
			console.log(resp);
		},
		loadingEle: $(ele).parents(".focus-content").find(".left img"),
		fileName: 'imgData'
	});
}


function saveCycleUseStatus(){
	var status = $('input:radio:checked').val();
	$.ajax({
		url: rootPath + "/microSchool/cycleUseStatus",
		data:{"status":status},
		type:"post",
		dataType:"json",
		success:function(jsonData){
			if(jsonData.result == "success"){
				$.msg("切换轮播图成功");
			}
		}
	})
}