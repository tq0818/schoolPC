<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<div id="m2" class="mark-more">
            <div class="main-content">
                <div class="m-title">
                    <h2 class="h6">课程</h2>
                    <span class="more">
                        <a href="javascript:;" class="m"  onclick='moreClass(this)'>更多</a>
                    </span>
                </div>
                <ul class="list-infos clear">
                    <li>
                        <p class='c'>
                            <span class="c-title">学科</span>
                            <span class="c-content">${oneItem.itemName }</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">学科小类</span>
                            <span class="c-content">${secondItem.itemName }</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">学习课程</span>
                            <span class="c-content">${classType.name }</span>
                        </p>
                    </li>
                    <li class="none n2">
                        <p class='c'>
                            <span class="c-title">总 课 时</span>
                            <span class="c-content">${classHour }</span>
                        </p>
                    </li>
                    <li class="none n2">
                        <p class='c'>
                            <span class="c-title">运营分校</span>
                            <span class="c-content">
                            ${createSchool.schoolName}
                            </span>
                        </p>
                    </li>
                </ul>
            </div>
            <div class="main-content none n2">
                <div class="m-title">
                    <h2 class="h6">课程</h2>
                </div>
                <ul class="list-infos clear">
                    <li>
                        <p class='c'>
                            <span class="c-title">优先上课校区</span>
                            <span class="c-content">${schoo.schoolName }</span>
                        </p>
                    </li>
                    <li>
                        <p class='c'>
                            <span class="c-title">学员计划考期</span>
                            <span class="c-content" id="pExamTermName"></span>
                        </p>
                    </li>
                </ul>
                <div class="c-list">
                	<c:forEach items="${paySlaveList }" var="paySlave">
                		<c:if test="${paySlave.resourceType=='TEACH_METHOD_FACE'||paySlave.resourceType=='TEACH_METHOD_LIVE' }">
                			  <p class="public clear">
			                        <span class="left clear">
			                            <span class="c-title">课程单元</span>
			                            <span class="c-content">${paySlave.name }</span>
			                            <span class="c-title">课程单元类型</span>
			                            <c:if test="${paySlave.resourceType=='TEACH_METHOD_FACE'}">
				                            <span class="c-content">面授</span>
			                            </c:if>
			                            <c:if test="${paySlave.resourceType=='TEACH_METHOD_LIVE'}">
				                            <span class="c-content">直播</span>
			                            </c:if>
			                        </span>
			                        <span class="right clear">
			                            <span class="c-title">班号</span>
			                            <span class="c-content"><b>${paySlave.moduleNo }</b></span>
			                        </span>
			                   </p>
                		</c:if>
                		<c:if test="${paySlave.resourceType=='TEACH_METHOD_VIDEO'}">
                 		    <p class="long">
		                        <span class="c-title">课程单元</span>
		                        <span class="c-content">${paySlave.name }</span>
		                        <span class="c-title">课程单元类型</span>
		                        <span class="c-content">录播</span>
		                        <span class="c-title">总课时</span>
		                        <span class="c-content">${paySlave.totalClassHour }课时</span>
		                    </p>
                		</c:if>
                	
                	</c:forEach>
                </div>
            </div>
        </div>
<script>
$(function(){
	$("#pExamTermName").html($("#examTermName").val());
    $('.s-list')
        .on('click','a.btn',function(){
        	alert(123);
            var 
                // 当前对象
                _this = $(this),
                // 是否被选中
                active = _this.hasClass('active');

            if ( !active ) {
                _this.addClass('active').siblings('a').removeClass('active');
            }
        })

    // 复选框
    $('.m-tools')
        .on('click','i.iconfont',function(){
            var
                // 文字 A为选中 9为置空
                txt = ['&#xe60a;','&#xe609;'],
                // 是否为选中
                active = $(this).hasClass('active');

                $(this)
                    [active?'removeClass':'addClass']('active')
                    .html(txt[active?1:0]);

        })

});

function moreClass(ele){
/* 	var mm = ele.innerHTML;
	var $this=document.getElementById('m2');
	if("更多"==mm){
		ele.innerHTML="收起";
		var eles=getElementsByClassName('n2');
		for(var i=0;i<eles.length;i++){
			console.log(eles[i],eles[i].style.display);
			eles[i].style.display="block";
			console.log(eles[i].style.display);
		}
		return false;
	}else{
		console.log('-----------');
		ele.innerHTML="更多";
		var eles=getElementsByClassName('n2');
		for(var i=0;i<eles.length;i++){
			eles[i].style.display="none";
		}
		return false;
	} */
	
}

</script>