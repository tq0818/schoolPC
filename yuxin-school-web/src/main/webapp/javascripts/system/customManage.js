$(document).ready(function(){
    var setTime=null;

    $selectSubMenus('system_custom_page');
    loadCustomList(function(){
        initEditor();
    });
    //点击保存
    $("#saveCustom_pageContent").on('click',function(){
        var $this=$(this);
        CKupdate();
        var contents=encodeURI($("#footContents").val());
        if( contents.length > 65535 ){
        	$.msg("内容长度过长！最大长度为65535个字符");
        	return false;
        }
        if($this.hasClass("disabled")){
            return;
        }
        $this.addClass("disabled");
        var data={},list={};
        var title=$("#customTitle").val();
        var customUrl=$("#customUrl").val();
        var groupId=$("#group_firstName").attr("ids");
        var tyName=$("#customTypeList option:selected").text();
        if(tyName && tyName=="分组模板"){
            var sortMap=new Array();
            $(".cusListPage").find("li").each(function(i){
                var slist={};
                slist.id=$(this).attr("ids");
                slist.sort=$(this).index()+1;
                sortMap.push(slist);
            })
            data.list=JSON.stringify(sortMap);
            if(!groupId || groupId ==""){
                $.msg("您还没有添加分组");
                $this.removeClass("disabled");
                return;
            }
        }
        if(title.length<=0){
            $.msg("请输入标题");
            $this.removeClass("disabled");
            return;
        }
        if(title.length<2 || title.length>12){
            $.msg("标题最多输入2-12个字符");
            $this.removeClass("disabled");
            return;
        }
        if(customUrl.length<=0){
            $.msg("请输入自定义域名");
            $this.removeClass("disabled");
            return;
        }
        var regex = /^[0-9a-zA-Z_/-]*$/g;
        if(customUrl.match(regex)==null){
            $.msg("自定义域名不正确");
            $this.removeClass("disabled");
            return;
        }
        data.id=$("#cusPageId").val();
        data.title=title;
        data.url=customUrl;
        data.templateId=$("#customTypeList").val();
        if(groupId && groupId>0){
            data.groupId=groupId;
        }
        data.includeHeadFoot=$("#isContainStatus").val();
        data.screenFlag=$("#customWidthSet").val();
        data.content=contents;
        $.ajax({
            type: "post",
            url: rootPath+"/companyConfigCustompage/editCustom_page",
            data:data,
            dataType:'json',
            success: function(jsonData){
                $.msg("保存成功");
                window.location.href=rootPath+"/companyConfigCustompage/customPage";
            }
        });
    });

    //点击取消
    $(".cancel-btn").on('click',function(){
        window.location.href=rootPath+"/companyConfigCustompage/customPage";
    });

    //添加模板组
    $(".addTemplateGroup").on('click',function(){
        var $this=$(this);
        if($this.hasClass("disabled")){
            return;
        }
        $this.addClass("disabled");
        var data={};
        var gName=$("#template_Name").val();
        if(gName.length<=0 || gName==""){
            $this.removeClass("disabled");
            $.msg("请输入模板组名称");
            return;
        }
        if(gName.length>12){
            $this.removeClass("disabled");
            $.msg("模板组最多为12个字符");
            return;
        }
        data.groupName=gName;
        data.templateId=$("#customTypeList").val();
        $.ajax({
            type: "post",
            url: rootPath+"/companyConfigCustompageGroup/checkName",
            data:{"name":gName},
            success: function(jsonData){
                if(jsonData){
                    $.ajax({
                        type: "post",
                        url: rootPath+"/companyConfigCustompageGroup/manageCustomGroup",
                        data:data,
                        dataType:'json',
                        success: function(jsonData){
                            $.msg("保存成功");
                            $this.removeClass("disabled");
                            loadGroupList();
                            cancle();
                        }
                    });
                }else{
                    $this.removeClass("disabled");
                    $.msg("模板组名称已存在");
                }
            }
        });
    });
    //编辑模板组
    $(".editTemplateGroup").on('click',function(){
        var data={};
        var gName=$("#edit_template_Name").val();
        if(gName.length<=0 || gName==""){
            $.msg("请输入模板组名称");
            return;
        }
        if(gName.length>12){
            $.msg("模板组最多为12个字符");
            return;
        }
        data.id=$("#g_Id").val();
        data.groupName=gName;
        data.templateId=$("#customTypeList").val();
        $.ajax({
            type: "post",
            url: rootPath+"/companyConfigCustompageGroup/manageCustomGroup",
            data:data,
            dataType:'json',
            success: function(jsonData){
                $.msg("操作成功");
                loadGroupList();
                cancle();
            }
        });
    });

    //返回
    $(".cancle_btn").on('click',function(){
        cancle();
    });

    //点击预览
    $("#showTemplete").on('click',function(){
        CKupdate();
        //存对象
        var contents=encodeURI($("#footContents").val());
    	if( contents.length > 65535 ){
    		$.msg("内容长度过长！最大长度为65535个字符");
    		return false;
    	}
        var data={};
        var title=$("#customTitle").val();
        var customUrl=$("#customUrl").val();
        data.title=title;
        data.url=customUrl;
        data.templateId=$("#customTypeList").val();
        data.includeHeadFoot=$("#isContainStatus").val();
        data.screenFlag=$("#customWidthSet").val();
        data.content=contents;
        var title1="";
        $(".cusListPage").find("li").each(function(i){
            title1+=$(this).attr("title")+",";
        });
        if(title1 && title1!=""){
            data.groupList=title1.substring(0,title1.length-1);
        }
        localStorage.clear();
        //$.cookie("templeteContent",JSON.stringify(data),{path:"/",domain:'yunduoketang.cn'});
        localStorage.setItem("templeteContent", JSON.stringify(data));
        //window.name=JSON.stringify(data);
        //跳转
        var cName=$("#customTypeList option:selected").text();
        if("分组模板"==cName){
            window.open(rootPath+"/companyConfigCustompage/page/showTemplate2","_blank");
        }else{
            window.open(rootPath+"/companyConfigCustompage/page/showTemplate","_blank");
        }
//		var w=window.open();
//		$.ajax({
//			url: rootPath+"/company/currtCompany",
//			type:"post",
//			dataType: "json",
//			success: function(jsonData){
//				var domain=jsonData.domain;
//				if("分组模板"==cName){
//					w.location="http://"+domain+"/custom/showGroupTemplate";
//				}else{
//					w.location="http://"+domain+"/custom/showTemplate";
//				}
//			}
//		});
    });

    //打开编辑组弹框
    $(document).on('click.editGroup','span.editGroup',function(e){
            var id=$(this).attr("ids");
            $.ajax({
                type: "post",
                url: rootPath+"/companyConfigCustompageGroup/queryBroupId/"+id,
                dataType:'json',
                success: function(jsonData){
                    $("#g_Id").val(jsonData.id);
                    $("#edit_template_Name").val(jsonData.groupName);
                    $(".editTemplateCustom").fadeIn(200);
                }
            });
            e.stopPropagation();
        })//删除组
        .on('click.delGroup','li span.delGroup',function(){
            var $this=$(this);
            var id=$(this).attr("ids");
            $.ajax({
                type: "post",
                url: rootPath+"/companyConfigCustompageGroup/isDelete",
                data:{"groupId":id},
                success: function(fla){
                    if(fla){
                        $.confirm("您确定要删除模板组?",function(b){
                            if(b){
                                $.ajax({
                                    type: "post",
                                    url: rootPath+"/companyConfigCustompageGroup/del/"+id,
                                    dataType:'json',
                                    success: function(jsonData){
                                        if("success"==jsonData){
                                            $.msg("删除成功");
                                            loadGroupList();
                                        }
                                    }
                                });
                            }
                        });
                    }else{
                        $.msg("请先删除该模板组下的页面");
                        return;
                    }
                }
            });
        })//切换分组
        .on('click.groupChoose','li.groupChoose',function(){
            var id=$(this).attr("ids");
            $("#cusPageGroupId").val(id);
            var name=$(this).attr("name");
            $("#group_firstName").text(name).attr("ids",id);
            $(".temp-pop").fadeOut(100);
            $(".add-temp-box").addClass("pop");
            clearTimeout(setTime);
            setTime=setTimeout(function(){
                loadGroupList(function(){
                    $(".cusListPage").html('');
                    var data={};
                    data.groupId=id;
                    data.templateId=$("#customTypeList").val();
                    $.ajax({
                        type: "post",
                        url: rootPath+"/companyConfigCustompage/queryCustomPageListById",
                        data:data,
                        dataType:'json',
                        success: function(jsonData){
                            var content = '<span class="mark-wrap"><em>编辑中</em><i class="iconfont">&#xe630;</i> </span>';
                            if(jsonData && jsonData.length>0){
                                var cId=$("#cusPageId").val();
                                $.each(jsonData,function(i,item){
                                    if(cId && cId==item.id){
                                        $(".cusListPage").append('<li ids='+item.id+' class="active" title='+item.title+'><div><p>'+item.title+'</p></div>'+content+'</li>');
                                    }else{
                                        $(".cusListPage").append('<li ids='+item.id+' title='+item.title+'><div><p>'+item.title+'</p></div></li>');
                                    }
                                });
                                var len=$(".cusListPage li.active").length;
                                if(len<1){
                                    $(".cusListPage").append('<li class="active"><div><p></p></div>'+content+'</li>');
                                    //为input赋值
                                    var pVal = $(".textarea-left.block li.active").find("p").html();
                                    var inputVal=$(".editarea input").val();
                                    if(pVal && pVal!=""){
                                        $(".editarea input").val(pVal);
                                    }
                                    if(inputVal && inputVal!=""){
                                        $(".textarea-left.block li.active").find("p").html(inputVal);
                                    }
                                }
                            }else{
                                $(".cusListPage").append('<li class="active"><div><p></p></div>'+content+'</li>');
                                //为input赋值
                                var pVal = $(".textarea-left.block li.active").find("p").html();
                                var inputVal=$(".editarea input").val();
                                if(pVal && pVal!=""){
                                    $(".editarea input").val(pVal);
                                }
                                if(inputVal && inputVal!=""){
                                    $(".textarea-left.block li.active").find("p").html(inputVal);
                                }
                            }
                        }
                    });
                });
            },30);
        });
});

function loadCustomList(callback){
    var tempId=$("#cusPageTempId").val();
    //查询页面类型
    $("#customTypeList").html("");
    $.ajax({
        type: "post",
        url: rootPath+"/companyConfigCustompageTemplate/queryCustomTypeList",
        dataType:'json',
        success: function(jsonData){
            var html="";
            $.each(jsonData,function(i,item){
                if(tempId && tempId !=""){
                    if(tempId==item.id){
                        html+='<option value="'+item.id+'" selected="selected">'+item.title+'</option>';
                        var content=decodeURIComponent(decodeURI(item.contents)).replace(/\+/g, ' ').replace(/\\/g, '\\\\');
                        $("#customTypeContent").html(content);
                    }else{
                        html+='<option value="'+item.id+'">'+item.title+'</option>';
                    }
                }else{
                    if(i==0){
                        html+='<option value="'+item.id+'" selected="selected">'+item.title+'</option>';
                        var content=decodeURIComponent(decodeURI(item.contents)).replace(/\+/g, ' ').replace(/\\/g, '\\\\');
                        $("#customTypeContent").html(content);
                    }else{
                        html+='<option value="'+item.id+'">'+item.title+'</option>';
                    }
                }
            });
            $("#customTypeList").append(html);
            if(callback){
                callback();
            }
        }
    });
}

function changeTemplete(){
    var id=$("#customTypeList").val();
    $.ajax({
        type: "post",
        url: rootPath+"/companyConfigCustompageTemplate/queryTemplate/"+id,
        dataType:'json',
        success: function(jsonData){
            var content=decodeURIComponent(decodeURI(jsonData.contents)).replace(/\+/g, ' ').replace(/\\/g, '\\\\');
            $("#customTypeContent").html(content);
            initEditor();
        }
    });
}

function loadGroupList(callback){
    var groupId=$("#cusPageGroupId").val();
    //查询模板组
    $("#group_list").html("");
    var templateId=$("#customTypeList").val();
    $.ajax({
        type: "post",
        url: rootPath+"/companyConfigCustompageGroup/queryGroupList",
        dataType:'json',
        data:{"templateId":templateId},
        success: function(jsonData){
            $("#group_list").data(jsonData);
            $.each(jsonData,function(i,item){
                if(groupId && groupId !=""){
                    if(groupId==item.id){
                        $("#group_list").append('<li class="active groupChoose clear" ids='+item.id+' name='+(item.groupName?item.groupName:"")+' title='+(item.groupName?item.groupName:"")+'><s>'+(item.groupName?item.groupName:"")+'</s><span class="iconfont delGroup" ids='+item.id+'>&#xe626;</span><span class="iconfont editGroup" ids='+item.id+'>&#xe628;</span></li>');
                        $("#group_firstName").text(item.groupName?item.groupName:"").attr({"ids":item.id,"title":item.groupName?item.groupName:""});
                    }else{
                        $("#group_list").append('<li class="groupChoose clear" ids='+item.id+' name='+(item.groupName?item.groupName:"")+' title='+(item.groupName?item.groupName:"")+'><s>'+(item.groupName?item.groupName:"")+'</s><span class="iconfont delGroup" ids='+item.id+'>&#xe626;</span><span class="iconfont editGroup" ids='+item.id+'>&#xe628;</span></li>');
                    }
                }else{
                    if(i==0){
                        $("#group_list").append('<li class="active groupChoose clear" ids='+item.id+' name='+(item.groupName?item.groupName:"")+' title='+(item.groupName?item.groupName:"")+'><s>'+(item.groupName?item.groupName:"")+'</s><span class="iconfont delGroup" ids='+item.id+'>&#xe626;</span><span class="iconfont editGroup" ids='+item.id+'>&#xe628;</span></li>');
                        $("#group_firstName").text(item.groupName?item.groupName:"").attr({"ids":item.id,"title":item.groupName?item.groupName:""});
                    }else{
                        $("#group_list").append('<li class="groupChoose clear" ids='+item.id+' name='+(item.groupName?item.groupName:"")+' title='+(item.groupName?item.groupName:"")+'><s>'+(item.groupName?item.groupName:"")+'</s><span class="iconfont delGroup" ids='+item.id+'>&#xe626;</span><span class="iconfont editGroup" ids='+item.id+'>&#xe628;</span></li>');
                    }
                }
            });

            $(".add-temp-box").on("mouseenter",".temp-pop li",function(){
                $(this).find("span").fadeIn(50);
            }).on("mouseleave",".temp-pop li",function(){
                $(this).find("span").fadeOut(50);
            });

            if(callback){
                callback();
            }
        }
    });
}

//查询组下的页面
function queryGroupCustomList(){
    var cId=$("#cusPageId").val();
    $(".cusListPage").html('');
    var data={};
    data.groupId=$("#group_firstName").attr("ids");
    data.templateId=$("#customTypeList").val();
    $.ajax({
        type: "post",
        url: rootPath+"/companyConfigCustompage/queryCustomPageListById",
        data:data,
        dataType:'json',
        success: function(jsonData){
            //为input赋值
            var pVal = $(".textarea-left.block li.active").find("p").html();
            var inputVal=$(".editarea input").val();
            var content = '<span class="mark-wrap"><em>编辑中</em><i class="iconfont">&#xe630;</i> </span>';
            if(jsonData && jsonData.length>0){
                $.each(jsonData,function(i,item){
                    if(cId && cId==item.id){
                        $(".cusListPage").append('<li class="active" ids='+item.id+' title='+item.title+'><div><p>'+item.title+'</p></div>'+content+'</li>');
                    }else{
                        $(".cusListPage").append('<li ids='+item.id+' title='+item.title+'><div><p>'+item.title+'</p></div></li>');
                    }
                });
                if(!cId || cId==""){
                    $(".cusListPage").find("li:last").after('<li class="active"><div><p></p></div>'+content+'</li>');
                }
            }else{
                $(".cusListPage").append('<li class="active" ids=""><div><p>'+inputVal+'</p></div>'+content+'</li>');
            }
            if(pVal && pVal!=""){
                $(".editarea input").val(pVal);
            }
            if(inputVal && inputVal!=""){
                $(".textarea-left.block li.active").find("p").html(inputVal);
            }
        }
    });
}

function initEditor(){
    var name=$("#customTypeList option:selected").text();
    //文本编辑器赋值
    var cusPageContent=$("#cusPageContent").val();
    var editor=CKEDITOR.replace('footContents', { allowedContent: true});
    editor.config.width="100%";
    if(name=="分组模板"){
        editor.config.width="100%";
        $("#setWidthLable").hide();
    }else{
        $("#setWidthLable").show();
    }
    editor.config.toolbar=[
        ['Source','Bold','Italic','Underline','Strike'],
        ['NumberedList','BulletedList','-','Outdent','Indent','Blockquote','CreateDiv'],
        ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock'],
        ['Link','Unlink','Anchor'],
        ['Image', 'Flash', 'Table', 'HorizontalRule', 'Smiley', 'SpecialChar', 'PageBreak'],
        ['Code'],
        ['Styles','Format','Font','FontSize'],
        ['TextColor','BGColor'],
        ['Maximize', 'ShowBlocks','-','About']
    ];
    if(cusPageContent){
        var content=decodeURI(cusPageContent);
        //decodeURIComponent(decodeURI(cusPageContent)).replace(/\+/g, ' ').replace(/\\/g, '\\\\');
        editor.setData(content);
    }
    editor.config.baseFloatZIndex=10100;
    editor.config.removeButtons = 'Underline,Subscript,Superscript';
    editor.config.format_tags = 'p;h1;h2;h3;pre';
    editor.config.image_previewText=' ';
    editor.config.removeDialogTabs = 'image:advanced;link:advanced';
    editor.config.filebrowserImageUploadUrl = rootPath + '/classType/editorUploadImg';
    initBevent();
    loadGroupList(function(){
        queryGroupCustomList();
    });
}

//处理CKEDITOR的值
function CKupdate() {
    for (instance in CKEDITOR.instances){
        CKEDITOR.instances[instance].updateElement();
    }
}

//初始化
function initBevent(){
    $(".page-pop-title em").on("click", function () {
        $(".add-page-pop").fadeOut(200);
    });

    //编辑
    $(".editarea input").on("change keyup keydown keypress",function(){
        var eaVal = $(this).val();
        $(".textarea-left.block li.active").find("p").html(eaVal);
    });
    //拖拽
    $(".sortable").each(function () {
        $(this).sortable({
            placeholder: "ui-state-highlight",
            cancel: "p,em",
            cursor: "move"
        });
    })
    //模板组选择
    $("select.mould option").each(function () {
        var n = $(this).index();
        $(this).attr("value", n)
    });
    $("select.mould").on("change", function () {
        var n = $(this).val();
        $(".mould-content .textarea-left").removeClass("block");
        $(".mould-content .textarea-left").eq(n).addClass("block");
        var cVal = $(".textarea-left.block li.active").find("p").html();
        $(".editarea input").val(cVal);

    });
    $(".add-temp-box").on("mouseenter",function(){
        $(".temp-pop").fadeIn(100);
        $(this).removeClass("pop");
    }).on("mouseleave",function(){
        $(".temp-pop").fadeOut(100);
        $(this).addClass("pop");
    });
    //点击添加模板组
    $(".add-temp").on('click',function(){
        $(".addTemplateCustom").fadeIn(200);
    });
}

function cancle(){
    $("#template_Name").val("");
    $("#edit_template_Name").val("");
    $(".add-page-pop").fadeOut(200);
}

