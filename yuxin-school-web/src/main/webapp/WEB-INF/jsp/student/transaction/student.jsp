<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<%--  <script src="<%=request.getContextPath()%>/javascripts/sea.js" id="seajsnode"></script>
    <script>
    seajs.use(['baseCSS','<%=request.getContextPath()%>/stylesheets/manage.css','<%=request.getContextPath()%>/stylesheets/student.css','student']);
    </script> --%>
<div class="mark-more">
            <div class="main-content" id="oldMessage">
                <div class="m-title">
                    <h2 class="h6">基本信息</h2>
                    <span class="more">
                        <a href="javascript:;" class="m">更多</a>
                        <a href="javascript:;" class="edit" onclick="edit();">编辑</a>
                    </span>
                </div>
                <ul class="list-infos clear">
                	<li>
                        <p class='c'>
                            <span class="c-title">用户名</span>
                            <span class="c-content cc">${student.username}</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">姓名</span>
                            <span class="c-content cc">${student.name }</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">性别</span>
                            <span class="c-content">${wx:dictCode2Name(student.sex)}</span>
                        </p>
                    </li>
                    <li class="none">
                        <p class='c'>
                            <span class="c-title">出生日期</span>
                            <span class="c-content cc"><fmt:formatDate value="${student.birthday}" timeStyle="yyyy-MM-dd"/></span>
                        </p>
                    </li>
                    <li id="more-tel">
                        <p class='c'>
                            <span class="c-title">手机号</span>
                            <span class="c-content cc"><em id="mobile">${student.mobile}</em></span>
                        </p>
                    </li>
                    <li style="display: none;">
                        <p class='c'>
                            <span class="c-title">年龄</span>
                            <span class="c-content cc">${student.age}</span>
                        </p>
                    </li>
                    <li class="none">
                        <p class='c'>
                            <span class="c-title">户口所在地</span>
                            <span class="c-content cc">${student.hkAddress}</span>
                        </p>
                    </li>
                    <li class="none">
                        <p class='c'>
                            <span class="c-title">最高学历</span>
                            <span class="c-content">${wx:dictCode2Name(student.educationCode)}</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">证件类型</span>
                            <span class="c-content">${wx:dictCode2Name(student.identityTypeCode)}</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">证件号码</span>
                            <span class="c-content cc">${student.identityId}</span>
                        </p>
                    </li>
                </ul>
            </div>
            <div class="main-content none">
                <div class="m-title">
                    <h2 class="h6">联系信息</h2>
                </div>
                <ul class="list-infos clear">
                    <li>
                        <p class='c'>
                            <span class="c-title">手机号</span>
                            <span class="c-content"><em>${student.mobile}</em></span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">家庭电话</span>
                            <span class="c-content">${student.homePhone}</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">办公电话</span>
                            <span class="c-content">${student.officePhone}</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">紧急联系人</span>
                            <span class="c-content">${student.emergencyContact}</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">紧急人电话</span>
                            <span class="c-content">${student.emergencyPhone}</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">邮箱</span>
                            <span class="c-content">${student.email}</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">QQ号</span>
                            <span class="c-content">${student.qq}</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">微信</span>
                            <span class="c-content">${student.weixinId}</span>
                        </p>
                    </li>
                </ul>
            </div>
        </div>
        <form action="" method="post" id="studentMessage">
    	<input type="hidden" name="id" value="${student.id }"/>
        <div class="mark-more none"  id="editMessage">
            <div class="main-content">
                <div class="m-title">
                    <h2 class="h6">基本信息</h2>
                    <span class="more">
                        <a href="javascript:;" class="e" onclick="save();">保存</a>
                    </span>
                </div>
                
                <ul class="list-infos clear">
                   <li>
                        <p class='c'>
                            <span class="c-title">用户名</span>
                            <span class="c-content">
                                <input type="text" name="username" value="${student.username }" disabled="disabled"/>
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">姓名</span>
                            <span class="c-content"><input type="text" name="name" value="${student.name }"></span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">性别</span>
                            <span class="c-content">
                                <input type="radio" name="sex" value="MALE" 
                                <c:if test="${student.sex=='MALE'}">checked</c:if>
                                > 男
                                <input type="radio" name="sex" value="FEMALE" 
                                 <c:if test="${student.sex=='FEMALE'}">checked</c:if>
                                > 女
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">出生日期</span>
                            <span class="c-content">
                                <input type="text" name="birthday" value="<fmt:formatDate value='${student.birthday }' timeStyle='yyyy-MM-dd'/>">
                            </span>
                        </p>
                    </li>                    
                    <li style="display: none;">
                        <p class='c'>
                            <span class="c-title">年龄</span>
                            <span class="c-content">
                                <input type="text" name="age" value="${student.age }">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">户口所在地</span>
                            <span class="c-content">
                                <input type="text" name="hkAddress" value="${student.hkAddress }">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">最高学历</span>
                            <span class="c-content">
                            	<select name="educationCode">
                            		<option value="UNDER_JUNIOR" 
                            		<c:if test="${student.educationCode== 'UNDER_JUNIOR'}">selected="selected"</c:if>
                            		>大专以下</option>
                            		<option value="JUNIOR"
                            		<c:if test="${student.educationCode== 'JUNIOR'}">selected="selected"</c:if>
                            		>大专</option>
                            		<option value="BECHELOR"
                            		<c:if test="${student.educationCode== 'BECHELOR'}">selected="selected"</c:if>
                            		>本科</option>
                            		<option value="POSTGRADUATE"
                            		<c:if test="${student.educationCode== 'POSTGRADUATE'}">selected="selected"</c:if>
                            		>研究生</option>
                            		<option value="DOCTOR"
                            		<c:if test="${student.educationCode== 'DOCTOR'}">selected="selected"</c:if>
                            		>博士生及以上</option>
                            	</select>
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">证件类型</span>
                            <span class="c-content">
                            
                                <select name="identityTypeCode" id="">
                                    <option value="ID_IDCARD"
                                    <c:if test="${student.identityTypeCode=='ID_IDCARD' }">selected</c:if>
                                    >身份证</option>
                                    <option value="ID_PASSPORT"
                                    <c:if test="${student.identityTypeCode=='ID_PASSPORT' }">selected</c:if>
                                    >护照</option>
                                    <option value="ID_HMP"
                                    <c:if test="${student.identityTypeCode=='ID_HMP' }">selected</c:if>
                                    >港澳通行证</option>
                                    <option value="ID_TCP"
                                    <c:if test="${student.identityTypeCode=='ID_TCP' }">selected</c:if>
                                    >台胞证</option>
                                    <option value="ID_OFFICERS"
                                    <c:if test="${student.identityTypeCode=='ID_OFFICERS' }">selected</c:if>
                                    >军官证</option>
                                    <option value="ID_SERGEANTS"
                                    <c:if test="${student.identityTypeCode=='ID_SERGEANTS' }">selected</c:if>
                                    >士官证</option>
                                    <option value="ID_UNCONFM_ID"
                                    <c:if test="${student.identityTypeCode=='ID_UNCONFM_ID' }">selected</c:if>
                                    >其他</option>
                                </select>
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">证件号码</span>
                            <span class="c-content">
                                <input type="text" name="identityId" value="${student.identityId }">
                            </span>
                        </p>
                    </li>
                </ul>
            </div>
            <div class="main-content">
                <div class="m-title">
                    <h2 class="h6">联系信息</h2>
                </div>
                <ul class="list-infos clear">
                    <li>
                        <p class='c'>
                            <span class="c-title">手机号</span>
                            <span class="c-content">
                                <input type="text" name="mobile" value="${student.mobile }">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">家庭电话</span>
                            <span class="c-content">
                                <input type="text" name="homePhone" value="${student.homePhone }">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">办公电话</span>
                            <span class="c-content">
                                <input type="text" name="officePhone" value="${student.officePhone }">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">紧急联系人</span>
                            <span class="c-content">
                                <input type="text" name="emergencyContact" value="${student.emergencyContact }">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">紧急人电话</span>
                            <span class="c-content">
                                <input type="text" name="emergencyPhone" value="${student.emergencyPhone }">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">邮箱</span>
                            <span class="c-content">
                                <input type="text" name="email" value="${student.email }">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">QQ号</span>
                            <span class="c-content">
                                <input type="text" name="qq" value="${student.qq }">
                            </span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">微信</span>
                            <span class="c-content">
                                <input type="text" name="weixinId" value="${student.weixinId }">
                            </span>
                        </p>
                    </li>
                </ul>
            </div>
        </div>
        </form>
<script>
	$(function(){
		 // 更多
        $('span.more')
            .on('click','a.m',function(){
                var 
                    // 获得当前元素
                    _this = $(this),
                    // 状态文字
                    txt = ['更多','收起'],
                    // 获得当前状态 true 为显示更多
                    active = _this.text() == txt[0],
                    // 获得父元素
                    parent = $(this).parents('.mark-more');

                if ( active ) {
                    _this.text(txt[1]);
                    parent.find('.none').fadeIn(200);
                    $('#more-tel').fadeOut(0);
                } else {
                    _this.text(txt[0]);
                    $('#more-tel').fadeIn(0);
                    parent.find('.none').fadeOut(200);
                }
            })
            getExpandField(".mark-more","r",function(){
				getExpandData(".mark-more","student","stu_id",'${student.id}');
			});
	})
	 //查询扩展字段
	var getExpandField=function(ele,rw,func){
    	$(ele).find(".customData").remove();
		$.ajax({
			url: rootPath+"/longitudinalTableColDefine/getData",
			dataType:"json",
			success:function(jsonData){
				var valueble;
				$.each(jsonData,function(i,data){
					if(data.allowModify){
					var dom='<div class="form-group customData">';
					if(rw=="r" && data.orgAllowRead ){
						dom+='<label class="col-md-2 control-label">'+(data.colComment?data.colComment:"")+'</label> <div class="col-md-2">'+
						'<span id="'+data.id+'" value="'+(data.colValue?data.colValue:"")+'" class="field">'+data.colName+'</span></div>';
					}else if(rw=="w" && data.orgAllowWrite){
						if(data.tldType=="text"){
							dom+='<label class="col-md-2 control-label">'+data.colComment+(data.colAllowNull==0?'<i class="iconfont ico"></i>':'')+'</label> <div class="col-md-3">'+
							'<input type="text" id="'+data.colName+'" name="'+data.colName+'" style="'+(data.styleCss?data.styleCss:'')+'" class="form-control field '+(data.styleClass?data.styleClass:'')+'" value="'+(data.colValue?data.colValue:"")+'" >'+
							'</div>'+
							'</div>';
						}else if(data.tldType=="select"){
							dom+='<label class="col-md-2 control-label">'+data.colComment+(data.colAllowNull==0?'<i class="iconfont ico"></i>':'')+'</label> <div class="col-md-3">'+
							'<select style="'+(data.styleCss?data.styleCss:'')+'" class="form-control field '+(data.styleClass?data.styleClass:'')+'" value="'+(data.defaultValue?data.defaultValue:'')+'" id="'+data.colName+'" name="'+data.colName+'" value="'+(data.defaultValue?data.defaultValue:'')+'">';
							$.each(data.itemsData,function(j,item){
								dom+='<option value="'+item[data.itemsValue]+'">'+item[data.itemsName]+'</option>';
							})
							dom+='</div>'+
							'</div>';
						}
					}
					}else{
						dom='<input type="hidden" id="'+data.colName+'" name='+data.colName+' value=""/>'
					}
					$(ele).find(".form-group:last").before(dom);
				})
				if(func){func()}
			}
		})
	}
	//查询扩展数据
	var getExpandData=function(ele,table,colName,colValue){
		var data={};
		data.tableName=table;
		data.colName=colName;
		data.colValue=colValue;
		$.ajax({
			url: rootPath+"/longitudinalTableData/getData",
			data:data,
			dataType:"json",
			success:function(jsonData){
				$.each(jsonData,function(i,data){
					if($(ele).find("#"+data.colName).is('input') || $(ele).find("#"+data.colName).is('select')){
						$(ele).find("#"+data.colName).val(data.colValue);
					}else{
						$(ele).find("#"+data.colName).html(data.colValue);
					}
					
				})
			}
		})
	}
</script>