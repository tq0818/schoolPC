<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script type="text/javascript" src="<%=request.getContextPath()%>/javascripts/common/utils.js"></script>
<div class="main-content">
	<div class="m-title">
		<h2 class="h6">原因</h2>
	</div>
	<ul class="list-infos clear">
		<li>
			<p class='c'>
				<span class="c-title">原因分类</span> <span class="c-content"> <select
					class="form-control" id="reason_type" name="reasonType"></select>
				</span>
			</p>
		</li>
		<li>
			<p class='c'>
				<span class="c-title">具体原因</span> <span class="c-content"> <select
					class="form-control" id="reason_detail" name="reasonCode">
				</select>
				</span>
			</p>
		</li>
		<li>
			<p class='c'>
				<span class="c-title">责任部门</span> <span class="c-content"> <select
					class="form-control" id="reason_depart" name="dutySectionCode">
				</select>
				</span>
			</p>
		</li>
		<li class="full clear">
			<p class='c'>
				<span class="c-title c-l-title">备注</span> <span class="c-content"> <textarea
						name="remark" class="text" placeholder="输入备注信息"></textarea>
				</span>
			</p>
		</li>
	</ul>
</div>
