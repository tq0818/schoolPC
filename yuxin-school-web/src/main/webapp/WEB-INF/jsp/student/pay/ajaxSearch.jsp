<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<div class="mark-more">
	<div class="main-content">
		<div class="m-title">
			<h2 class="h6">基本信息</h2>
			<span class="more"> <a href="javascript:;" class="m">更多</a>
			</span>
		</div>
		<ul class="list-infos clear">
			<li>
				<p class='c'>
					<span class="c-title">姓名</span> <span class="c-content">${student.name }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">性别</span> <span class="c-content">${wx:dictCode2Name(student.sex)}</span>
				</p>
			</li>
			<li class="none">
				<p class='c'>
					<span class="c-title">出生日期</span> <span class="c-content"><fmt:formatDate
							value="${student.birthday }" timeStyle="yyyy-MM-dd" /></span>
				</p>
			</li>
			<li id="more-tel">
				<p class='c'>
					<span class="c-title">手机号</span> <span class="c-content"><em>${mobile }</em></span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">年龄</span> <span class="c-content">${student.age }</span>
				</p>
			</li>
			<li class="none">
				<p class='c'>
					<span class="c-title">户口所在地</span> <span class="c-content">${student.hkAddress }</span>
				</p>
			</li>
			<li class="none">
				<p class='c'>
					<span class="c-title">最高学历</span> <span class="c-content">${wx:dictCode2Name(student.educationCode)}</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">证件类型</span> <span class="c-content">${wx:dictCode2Name(student.identityTypeCode)}</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">证件号码</span> <span class="c-content">${student.identityId }</span>
				</p>
			</li>
		</ul>
	</div>
	<div class="main-content none" id="connect">
		<div class="m-title">
			<h2 class="h6">联系信息</h2>
		</div>
		<ul class="list-infos clear">
			<li>
				<p class='c'>
					<span class="c-title">手机号</span> <span class="c-content"><em>${mobile }</em></span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">家庭电话</span> <span class="c-content">${student.homePhone }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">办公电话</span> <span class="c-content">${student.officePhone }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">紧急联系人</span> <span class="c-content">${student.emergencyContact }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">紧急人电话</span> <span class="c-content">${student.emergencyPhone }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">邮箱</span> <span class="c-content">${student.email }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">QQ号</span> <span class="c-content">${student.qq }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">微信</span> <span class="c-content">${student.weixinId }</span>
				</p>
			</li>
		</ul>
	</div>
</div>
<div class="mark-more">
	<div class="main-content">
		<div class="m-title">
			<h2 class="h6">课程</h2>
			<span class="more"> <a href="javascript:;" class="m">更多</a>
			</span>
		</div>
		<ul class="list-infos clear">
			<li>
				<p class='c'>
					<span class="c-title">学科</span> <span class="c-content">${oneItem.itemName }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">学科小类</span> <span class="c-content">${secondItem.itemName }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">学习课程</span> <span class="c-content">${classType.name }</span>
				</p>
			</li>
			<li class="none">
				<p class='c'>
					<span class="c-title">总 课 时</span> <span class="c-content">${classHour }</span>
				</p>
			</li>
			<li class="none">
				<p class='c'>
					<span class="c-title">运营分校</span> <span class="c-content"> <c:forEach
							items="${schoolList }" var="school" varStatus="v">
                            		&nbsp;${school.schoolName }
                            </c:forEach>
					</span>
				</p>
			</li>
		</ul>
	</div>
	<div class="main-content none">
		<div class="m-title">
			<h2 class="h6">课程</h2>
		</div>
		<ul class="list-infos clear">
			<li>
				<p class='c'>
					<span class="c-title">优先上课校区</span> <span class="c-content">${schoo.schoolName }</span>
				</p>
			</li>
			<li>
				<p class='c'>
					<span class="c-title">学员计划考期</span> <span class="c-content">${payMaster.examTermName }</span>
				</p>
			</li>
		</ul>
		<div class="c-list">
			<c:forEach items="${paySlaveList }" var="paySlave">
				<c:if
					test="${paySlave.resourceType=='TEACH_METHOD_FACE'||paySlave.resourceType=='TEACH_METHOD_LIVE' }">
					<p class="public clear">
						<span class="left clear"> <span class="c-title">课程单元</span>
							<span class="c-content">${paySlave.name }</span> <span
							class="c-title">课程单元类型</span> <c:if
								test="${paySlave.resourceType=='TEACH_METHOD_FACE'}">
								<span class="c-content">面授</span>
							</c:if> <c:if test="${paySlave.resourceType=='TEACH_METHOD_LIVE'}">
								<span class="c-content">直播</span>
							</c:if>
						</span> <span class="right clear"> <span class="c-title">班号</span>
							<span class="c-content"><b>${paySlave.resourceId }</b></span>
						</span>
					</p>
				</c:if>
				<c:if test="${paySlave.resourceType=='TEACH_METHOD_VIDEO'}">
					<p class="long">
						<span class="c-title">课程单元</span> <span class="c-content">${paySlave.name }</span>
						<span class="c-title">课程单元类型</span> <span class="c-content">录播</span>
						<span class="c-title">总课时</span> <span class="c-content">${paySlave.totalClassHour }课时</span>
					</p>
				</c:if>

			</c:forEach>
		</div>
	</div>
</div>
<div class="main-content">
	<div class="m-title">
		<h2 class="h6">应缴费用</h2>
	</div>
	<ul class="list-infos clear">
		<li>
			<p class='c'>
				<span class="c-title">培训费用</span> <span class="c-content">${studentPayMaster.trainingFee }</span>
			</p>
		</li>
		<li>
			<p class='c'>
				<span class="c-title">代报考费用</span> <span class="c-content">${studentPayMaster.examAgentFee }</span>
			</p>
		</li>
		<li>
			<p class='c'>
				<span class="c-title">订单总价</span> <span class="c-content">${studentPayMaster.trainingFee+studentPayMaster.examAgentFee }</span>
			</p>
		</li>
	</ul>
</div>

<div class="main-content">
	<div class="m-title">
		<h2 class="h6">已缴费用</h2>
	</div>
	<ul class="list-infos clear">
		<li>
			<p class='c'>
				<span class="c-title">POS</span> <span class="c-content">${postReal }</span>
			</p>
		</li>
		<li>
			<p class='c'>
				<span class="c-title">现金</span> <span class="c-content">${cashReal }</span>
			</p>
		</li>
		<li>
			<p class='c'>
				<span class="c-title">支票</span> <span class="c-content">${checkReal }</span>
			</p>
		</li>
		<li>
			<p class='c'>
				<span class="c-title">转账</span> <span class="c-content">${remitReal }</span>
			</p>
		</li>
	</ul>
</div>
<div class="main-content">
	<div class="m-title">
		<h2 class="h6">欠费</h2>
	</div>
	<div class="m-p-list">
		<ul class="clear">
			<c:forEach items="${studentFeeStageList }" var="studentFeeStage"
				varStatus="v">
					<li><span class="m-p-date">第${count+v.index}期</span> <span
						class="m-p-time">缴款日期：</span> <span class="m-p-time-n"><fmt:formatDate
								value="${studentFeeStage.stageDate }" timeStyle="yyyy-MM-dd" /></span>
						<span class="m-p-pay">金额：</span> <span class="m-p-pay-m">￥
							${studentFeeStage.stageFee }</span> <span class="m-p-b"><a
						href="javascript:void(0);" class="btn btn-sm btn-default"
						onclick="pay(${studentFeeStage.id});">缴费</a></span></li>
			</c:forEach>
		</ul>
	</div>
</div>
<script>
$(function(){
    $('.s-list')
        .on('click','a.btn',function(){
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

/*     // 更多
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
                $('#more-tel').fadeInt(0);
                parent.find('.none').fadeOut(200);
            }
        }) */
});

</script>