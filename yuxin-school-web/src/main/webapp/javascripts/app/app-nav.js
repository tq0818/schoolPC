(function(){
    $(function(){
    	var Model = {};
        Model.ajaxLoad = function(url,dataInfo,func){
 	   		 $.ajax({ 
 	   	 		  type: "post", 
 	   	 		  url : rootPath+url, 
 	   	 		  data: dataInfo,
 	   			  beforeSend:function(XMLHttpRequest){
 	   	            $(".loading").show();
 	   	            $(".loading-bg").show();
 	   	          },
 	   	 		  success: func,
 	   	 		  complete:function(XMLHttpRequest,textStatus){
 	   				  $(".loading").hide();
 	   		          $(".loading-bg").hide();
 	   		      }
 	   	 	  });
 	   	};
 	   	Model.ajax = function(url,dataInfo,func){
 	   		 $.ajax({ 
 	   	 		  type: "post", 
 	   	 		  url : rootPath+url, 
 	   	 		  data: dataInfo,
 	   	 		  success: func,
 	   	 	  });
 	   	};
 	   	Model.init = function(){
 	   		
 	   	}
 	   	Model.chooseOption = function(arr,key){
 		  var _value = 0;
 		  if(!arr || arr.length == 0)
 			  return _value;
 		  $.each(arr,function(i,obj){
 			  if(obj.pageCode == key){
 				  _value = obj.optionIndex;
 				  return _value;
 			  }
 		  });
 		  return _value;
        };
        Model.chooseImg = function(arr,key){
   		  var _value = rootPath+"/images/app/kechen@2x.png";
   		  if(!arr || arr.length == 0)
   			  return _value;
   		  $.each(arr,function(i,obj){
   			  if(obj.pageCode == key){
   				  _value = obj.imgs;
   				  return _value;
   			  }
   		  });
   		  return _value;
          };
        Model.sortCap = function($domArr){
        	if(!$domArr)
        		return;
        	var sortMap = [];
        	$.each($domArr,function(i,obj){
        		var cap = {};
        		cap.id = $(this).data("id");
        		cap.sequence = $(this).index();
				sortMap.push(cap);
        	});
			this.ajax("/companyAppBarConfig/orderCaps","list="+JSON.stringify(sortMap),function(data){});
        }
    	
    	var len = $(".nav-show").find("li").length;
        $(".nav-show li").width(100 / len + "%");
        var len_ft = $(".right-side footer").find("li").length;
        $(".right-side footer li").width(100 / len_ft + "%");
    	var navhead = [
    	   { "imgs":rootPath+"/images/app/kechen@2x.png" , "word":"课程","pageCode":"2","optionIndex":0},
    	   { "imgs":rootPath+"/images/app/tiku@2x.png" , "word": "题库","pageCode":"3","optionIndex":1},
           { "imgs":rootPath+"/images/app/gongkike@2x.png" , "word":"公开课","pageCode":"6","optionIndex":2},
//           { "imgs":rootPath+"/images/app/wenda@2x.png" , "word":"问答","pageCode":"5","optionIndex":3},
           { "imgs":rootPath+"/images/app/my@2x.png" , "word":"我的","pageCode":"4","optionIndex":4}
       ];
       var navfoot = [
           { "imgs":rootPath+"/images/app/home@2x.png" , "word":"首页","pageCode":"1","optionIndex":0},
           { "imgs":rootPath+"/images/app/course@2x.png" , "word":"课程","pageCode":"2","optionIndex":1},
           { "imgs":rootPath+"/images/app/topic@2x.png" , "word": "题库","pageCode":"3","optionIndex":2},
           { "imgs":rootPath+"/images/app/me@2x.png" , "word": "我的","pageCode":"4","optionIndex":3},
           { "imgs":rootPath+"/images/app/gongkaike_grey@2x.png" , "word": "公开课","pageCode":"6","optionIndex":4}
       ];

       //首页导航select选择
       var addcont, n,setname;
       $("#navhead").on("change",function(){
    	   var optionIndex ;
    	   optionIndex = $(this).find("option:selected").index();
           $(this).parents(".nav-choose-alert").find("input").val(navhead[optionIndex].word);
       });
       // 首页导航添加按钮
       $(".head-nav-set .nav-add-icon").on("click",function(){
           $(".mask-bg").show();
           var _$options =  $(this).parents(".nav-set-itm").find("select option");
           _$options[0].selected = true;
           $(this).parents(".nav-set-itm").find("input").val(_$options.eq(0).text());
           $(this).parents(".nav-set-itm").find(".nav-choose-alert").show().data("cap",{});
       });
       //    选择首页导航保存
       var ctrs = function (cap,parm1) {
           n = $("#navhead").find("option:selected").index();
           setname = $(".head-nav-set .nav-choose-alert").find("input").val();
           if (!parm1) {
               addcont = "<li class=\"choose-show swiper-slide headli\" num=\"" + n + "\" data-id='"+cap.id+"' data-type='"+cap.pageType+"' data-code='"+cap.pageCode+"'>" +
               "<div class=\"choose-show\">" +
               "<img src=" + navhead[n].imgs + " alt=\"\"/>" +
               "<p>" + setname + "</p>" +
               "</div>" +
               " <div class=\"choose-mask\">" +
               "<button class='eidtBtn'>编辑</button>" +
               " <i class=\"iconfont mask-close-btn\">&#xe610;</i>" +
               " </div>" +
               " <div class=\"delet-alert\">" +
               " <p><i class=\"iconfont\">&#xe653;</i>删除后该导航将不在APP首页显示<br/>是否删除</p>" +
               " <div class=\"btns-wrap\">" +
               " <button class=\"btn-yes\">是</button>" +
               " <button class=\"btn-no\">否</button>" +
               " </div>" +
               " </div>" +
               "</li>";
               $(".head-nav-set").find("ul").prepend(addcont);
               $(".nav-show").prepend(addcont);
                len ++;
               if(len<4){
                   $(".nav-show li").width(100 / len + "%");
               }else{
                   var swiper = new Swiper('.swiper-container', {
                       paginationClickable: true,
                       slidesPerView:4
                   });
               }
               $(".nav-choose-alert").hide();
               $(".mask-bg").hide();
               Model.sortCap($(".head-nav-set .headli"));
           } else {
               $(".head-nav-set li").eq(parm1).find(".choose-show img").attr("src", navhead[n].imgs);
               $(".head-nav-set li").eq(parm1).find(".choose-show p").html(setname);
               $(".nav-show li").eq(parm1).find(".choose-show img").attr("src", navhead[n].imgs);
               $(".nav-show li").eq(parm1).find(".choose-show p").html(setname);
               $(".nav-choose-alert").hide().removeAttr("edit");
               $(".mask-bg").hide();
           }
       }
       /**
        * 新增或者修改头部导航
        */
       $(".head-nav-set .nav-success-btn").on("click", function () {
    	   var pageType = $(this).data("type"),
    	   		pageCode = $(this).parents(".nav-choose-alert").find("option:selected").val(),
    	   		customName = $.formatstr($(this).parent().prev().find('input').val()),
    	   		addOrEdit = $(this).parents(".nav-choose-alert").attr("edit"),
    	   		url,
    	   		_this = this,
    	   		cap = $(this).parents(".nav-choose-alert").data("cap") || {};
    	   
    	   if(!checkCustomName(customName))
    		   return;
    	   
    	   cap.pageType = pageType;
    	   cap.pageCode = pageCode;
    	   cap.name = customName;
    	   cap.sequence = cap.sequence?cap.sequence:$(".headli").length?$(".headli").length:1;
    	   if(addOrEdit){
    		   url = "/companyAppBarConfig/updateCap";
    	   }else{
    		   url = "/companyAppBarConfig/addCap";
    	   }
    	   Model.ajax(url, cap, function(result){
    		   if(!result.flag){
    			   $.msg(result.msg);
    			   return;
    		   }
    		   $(_this).parents(".nav-choose-alert").data("cap",{});
    		   ctrs(result.cap,addOrEdit);
    	   });
       });
       function checkCustomName(name){
    	   if(!name){
    		   $.msg("自定义名称不能为空");
    		   return false;
    	   }
    	   if(name.length>3){
    		   $.msg("自定义名称不能超过3个字");
    		   return false;
    	   }
    	   return true;
       }
     //删除头部导航
       $(".head-nav-set  .nav-choose").on("click",".btn-yes",function(){
    	   var capId = $(this).parents('li').data("id"),
    	   		indexnum=$(this).parents("li").index(),
    	   		_this = this;
    	   if(!capId)
    		   return;
           
    	   Model.ajax("/companyAppBarConfig/deleteCap", {id:capId}, function(result){
    		   if(result.flag){
    			   $(".nav-show li").eq(indexnum).remove();
        		   $(_this).parents("li").remove();
        		   len --;
                   if(len<4){
                       $(".nav-show li").width(100 / len + "%");
                   }else{
                       var swiper = new Swiper('.swiper-container', {
                           paginationClickable: true,
                           slidesPerView:4
                       });
                       $(".swiper-wrapper").css({
                           transform: "translate3d(0px, 0px, 0px)",
                           "-moz-transform": "translate3d(0px, 0px, 0px)",
                           "-webkit-transform": "translate3d(0px, 0px, 0px)",
                           "-0-transform": "translate3d(0px, 0px, 0px)",
                           "-ms-transform": "translate3d(0px, 0px, 0px)"
                       })
                   }
    		   }
    	   });
       });

       //    底部导航select选择
       var addcont_ft, n_ft,setname_ft;
       $("#navfoot").on("change",function(){
           n_ft=$(this).find("option:selected").index();
           $(this).parents(".nav-choose-alert").find("input").val(navfoot[n_ft].word);
       });
       //    底部导航添加按钮
       $(".foot-nav-set .nav-add-icon").on("click",function(){
           var len_li=$(".right-side footer ul li").length;
           if(len_li==5){
               alert("最多添加5个导航")
           }else{
               $(".mask-bg").show();
               $(this).parents(".nav-set-itm").find("select option")[0].selected = true;
               $(this).parents(".nav-set-itm").find("input").val("首页");
               $(this).parents(".nav-set-itm").find(".nav-choose-alert").show().data("cap",{});
           }

       });
       //    选择底部导航保存
       var ctrs_ft = function (cap,parm1) {
           n_ft = $("#navfoot").find("option:selected").index();
           setname_ft = $(".foot-nav-set .nav-choose-alert").find("input").val();
           if (!parm1) {
               addcont = "<li num=\"" + n_ft + "\" data-id='"+cap.id+"' data-type='"+cap.pageType+"' data-code='"+cap.pageCode+"' class='footli'>" +
               "<div class=\"choose-show\">" +
               "<img src=" + navfoot[n_ft].imgs + " alt=\"\"/>" +
               "<p>" + setname_ft + "</p>" +
               "</div>" +
               " <div class=\"choose-mask\">" +
               "<button class='eidtBtn'>编辑</button>" +
               " <i class=\"iconfont mask-close-btn\">&#xe610;</i>" +
               " </div>" +
               " <div class=\"delet-alert\">" +
               " <p><i class=\"iconfont\">&#xe653;</i>删除后该导航将不在APP首页显示<br/>是否删除</p>" +
               " <div class=\"btns-wrap\">" +
               " <button class=\"btn-yes\">是</button>" +
               " <button class=\"btn-no\">否</button>" +
               " </div>" +
               " </div>" +
               "</li>";
               $(".foot-nav-set").find("ul").prepend(addcont);
               $(".right-side footer ul").prepend(addcont);
               len_ft++;
               $(".right-side footer li").width(100 / len_ft + "%");
               $(".nav-choose-alert").hide();
               $(".mask-bg").hide();
           } else {
               $(".foot-nav-set li").eq(parm1).find(".choose-show img").attr("src", navfoot[n_ft].imgs);
               $(".foot-nav-set li").eq(parm1).find(".choose-show p").html(setname_ft);
               $(".right-side footer li").eq(parm1).find(".choose-show img").attr("src", navfoot[n_ft].imgs);
               $(".right-side footer li").eq(parm1).find(".choose-show p").html(setname_ft);
               $(".nav-choose-alert").hide().removeAttr("edit");
               $(".mask-bg").hide();
           }
           Model.sortCap($(".foot-nav-set .footli"));
       }
       /**
        * 新增或者修改页尾导航
        */
       $(".foot-nav-set .nav-success-btn").on("click",function(){
    	   var pageType = $(this).data("type"),
		   		pageCode = $(this).parents(".nav-choose-alert").find("option:selected").val(),
		   		customName = $.formatstr($(this).parent().prev().find('input').val()),
		   		addOrEdit = $(this).parents(".nav-choose-alert").attr("edit"),
		   		url,
		   		_this = this,
		   		cap = $(this).parents(".nav-choose-alert").data("cap") || {};
	   
		   if(!checkCustomName(customName))
			   return;
		   
		   cap.pageType = pageType;
		   cap.pageCode = pageCode;
		   cap.name = customName;
		   cap.sequence = cap.sequence?cap.sequence:$(".footli").length?$(".footli").length:1;
		   if(addOrEdit){
			   url = "/companyAppBarConfig/updateCap";
		   }else{
			   url = "/companyAppBarConfig/addCap";
		   }
		   Model.ajax(url, cap, function(result){
			   if(!result.flag){
				   $.msg(result.msg);
				   return;
			   }
			   $(_this).parents(".nav-choose-alert").data("cap",{});
			   ctrs_ft(result.cap,addOrEdit);
		   });
          
       });
     //删除底部导航
       $(".foot-nav-set  .nav-choose").on("click", ".btn-yes", function () {
    	   var capId = $(this).parents('li').data("id"),
		   		indexnum=$(this).parents("li").index(),
		   		_this = this;
		   if(!capId)
			   return;
		   Model.ajax("/companyAppBarConfig/deleteCap", {id:capId}, function(result){
			   if(result.flag){
				   $(".right-side footer ul li").eq(indexnum).remove();
		   		   $(_this).parents("li").remove();
		           len_ft--;
		           $(".right-side footer li").width(100 / len_ft + "%");
			   }
		   });
       });


       //鼠标移入导航里的选项，编辑框弹出*
       $(".nav-choose").on("mouseenter",".headli,.footli",function(){
           $(this).find(".choose-mask").fadeIn(100);
       }).on("mouseleave",".choose-mask",function(){
           $(this).fadeOut(100);
       });
       //点击设置中取消按钮*
       $(".nav-cancel-btn").on("click",function(){
           $(this).parents(".nav-choose-alert").hide();
           $(".mask-bg").hide();
       });
       //点击编辑框弹出框的关闭按钮*
       $(".nav-choose").on("click",".mask-close-btn",function(){
           $(this).parents("li").find(".delet-alert").show();
       });
       //点击否按钮*
       $(".nav-choose").on("click",".btn-no",function(){
           $(this).parents(".delet-alert").hide();
       });
     //点击编辑按钮，修改--显示修改弹框 
       $(".nav-set-cont").on("click",".eidtBtn",function(){
    	   var _cap = $(this).parents("li").data(),
	    	   _$parent,
	    	   _arr,
	    	   _optionIdex,
	    	   _this = this;
    	   if(_cap.type == "head"){
    		   _$parent = $(this).parents(".head-nav-set");
    		   _arr = navhead;
    	   }else{
    		   _$parent = $(this).parents(".foot-nav-set");
    		   _arr = navfoot;
    	   }
    	   _optionIdex = Model.chooseOption(_arr,_cap.code);
    	   if(!_cap.id){
    		   $(".mask-bg").show();
    		   _$parent.find(".nav-choose-alert select option")[_optionIdex].selected = true;
    		   _$parent.find(".nav-choose-alert input").val(_arr[_optionIdex].word);
    		   _$parent.find(".nav-choose-alert").show();
    	   }else{
    		   Model.ajax("/companyAppBarConfig/findOne", {id:_cap.id}, function(result){
        		   if(!result)
        			   return;
        		   $(".mask-bg").show();
        		   _$parent.find(".nav-choose-alert select option")[_optionIdex].selected = true;
        		   _$parent.find(".nav-choose-alert input").val(result.name);
        		   _$parent.find(".nav-choose-alert").show();
        		   $(_this).parents(".nav-choose").next().data("cap",result);
//        		   $(_this).parents(".nav-choose").next().attr("data-id",result.id).attr("data-pageType",result.pageType).attr("data-pageCode",result.pageCode).attr("data-sequence",result.sequence);
        	   });
    	   }
    	   $(this).parents(".nav-choose").next().attr("edit",$(this).parents("li").index());//编辑还是修改判断
    	   
       });
       // 点击启用禁用
       var static_code=[{icon:"&#xe603",word:"启用"},{icon:"&#xe604;",word:"禁用"}];
       $(".nav-static").on("click",function(){
           var open=$(this).hasClass("open");
           var $headLi = $(".head-nav-set .headli"),
           		capArr = [],
           		_this = this;
           if(open){//禁用
        	   $.each($headLi,function(i,obj){
             	  var capId = $(obj).data("id"),
             	  		cap = {};
             	  cap.id = capId;
             	  cap.status = 0;
             	  capArr.push(cap);
                });
        	   Model.ajax("/companyAppBarConfig/orderCaps", "list="+JSON.stringify(capArr), function(result){
        		   if(!result)
        			   return;
        		   $(_this).removeClass("open").addClass("l-close");
                   $(_this).find("i").html(static_code[1].icon);
                   $(_this).find("em").html(static_code[1].word);
                   $(".show-head-ul").html("");
        	   });
           }else{//启用
        	   $.each($headLi,function(i,obj){
              	  var capId = $(obj).data("id"),
              	  		cap = {};
              	  cap.id = capId;
              	  cap.status = 1;
              	  capArr.push(cap);
                 });
         	   Model.ajax("/companyAppBarConfig/orderCaps", "list="+JSON.stringify(capArr), function(result){
         		   if(!result)
         			   return;
         		  $(_this).removeClass("l-close").addClass("open");
                  $(_this).find("i").html(static_code[0].icon);
                  $(_this).find("em").html(static_code[0].word);
                  
                  var _headLi = '',img;
             	  $.each($headLi,function(i,obj){
             		 img = Model.chooseImg(navhead,$(obj).data("code"));
             		  _headLi += '<li class="choose-show swiper-slide">'+
									'<div class="choose-show">'+
										'<img src="'+img+'" alt=""/>'+
						                '<p>'+$(obj).find('p').eq(0).text()+'</p>'+
					                '</div>'+
					              '</li>';
             	  });
             	  $(".show-head-ul").html(_headLi);
             	 if($headLi.length < 4){
                     $(".nav-show li").width(100 / len + "%");
                 }else{
                     var swiper = new Swiper('.swiper-container', {
                         paginationClickable: true,
                         slidesPerView:4
                     });
                     $(".swiper-wrapper").css({
                         transform: "translate3d(0px, 0px, 0px)",
                         "-moz-transform": "translate3d(0px, 0px, 0px)",
                         "-webkit-transform": "translate3d(0px, 0px, 0px)",
                         "-0-transform": "translate3d(0px, 0px, 0px)",
                         "-ms-transform": "translate3d(0px, 0px, 0px)" 
                     })
                 }
         	   });
           }
       });
   
      
   
       
       
        $("#sortTable").sortable({
            update:function(event,ui){
				var sortMap = [];
				$("#sortTable").find(".ui-state-default").each(function(i){
					var oneItem={};
					oneItem.id=$(this).data("id");
					oneItem.sort=$(this).index()+1;
					sortMap.push(oneItem);
				})
				$this.ajaxLoad("/sysConfigItem/orderOneItem","list="+JSON.stringify(sortMap),function(data){
					$.msg("修改成功");
				});
			},
			delay: 100,
      }).disableSelection();
    })
})(jQuery)