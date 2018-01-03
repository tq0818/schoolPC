<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<div class="allDisplayDiv">
		<button class="btn_defalut" type="button">知识树设置</button>
		<a href="javascript:;" class="fas fa-minus allDisplay">全部折叠</a>
	</div>
	<!--组织树-->
	<div class="treeSeting">
		<button class="btn_defalut addChaperBtn">新增章</button>
		<ul class="chapter">
			<li>
				<div>
					<a class="fas fa-minus displays" href="javascript:;"></a>
					<a class="fas fa-arrow-circle-up"  href="javascript:;"></a>
					<a class="fas fa-arrow-circle-down"  href="javascript:;"></a>
					<span class="chapterNum">第1章</span>
					<span class="chapterDscribe">八角楼上1</span>
					<span class='fas fa-check sureEditBtn'></span>
					<button type="button" class="delChild btn_defalut">删除</button>
					<button type="button" class="editChild btn_defalut">编辑</button>
					<button type="button" class="addChild btn_defalut">新增节点</button>
				</div>
				<ul>
					<li>
						<div>
							<a class="fas fa-arrow-circle-up"  href="javascript:;"></a>
							<a class="fas fa-arrow-circle-down"  href="javascript:;"></a>
							<span class="numNode">1</span>
							<span class="chapterNum">生字练习</span>
							<span>关联课程：</span>
							<a class="chapterDscribe" href="javascript:;">aaaa333</a>
							<span class='fas fa-check sureEditBtn'></span>
							<button type="button" class="delChild btn_defalut">删除</button>
							<button type="button" class="editChild btn_defalut">编辑</button>
						</div>
					</li>
					<li>
						<div>
							<a class="fas fa-arrow-circle-up"  href="javascript:;"></a>
							<a class="fas fa-arrow-circle-down"  href="javascript:;"></a>
							<span class="numNode">1</span>
							<span class="chapterNum">生字练习11</span>
							<span>关联课程111：</span>
							<a class="chapterDscribe" href="javascript:;">aaaa</a>
							<span class='fas fa-check sureEditBtn'></span>
							<button type="button" class="delChild btn_defalut">删除</button>
							<button type="button" class="editChild btn_defalut">编辑</button>
						</div>
					</li>
				</ul>
			</li>
			<li>
				<div>
					<a class="fas fa-plus displays" href="javascript:;"></a>
					<a class="fas fa-arrow-circle-up"  href="javascript:;"></a>
					<a class="fas fa-arrow-circle-down"  href="javascript:;"></a>
					<span class="chapterNum">第2章</span>
					<span class="chapterDscribe">八角楼上1</span>
					<span class='fas fa-check sureEditBtn'></span>
					<button type="button" class="delChild btn_defalut">删除</button>
					<button type="button" class="editChild btn_defalut">编辑</button>
					<button type="button" class="addChild btn_defalut">新增节点</button>
				</div>
				<ul>
					<li>
						<div>
							<a class="fas fa-arrow-circle-up"  href="javascript:;"></a>
							<a class="fas fa-arrow-circle-down"  href="javascript:;"></a>
							<span class="numNode">1</span>
							<span class="chapterNum">生字练习2</span>
							<span>关联课程：</span>
							<a class="chapterDscribe" href="javascript:;">aaaa333</a>
							<span class='fas fa-check sureEditBtn'></span>
							<button type="button" class="delChild btn_defalut">删除</button>
							<button type="button" class="editChild btn_defalut">编辑</button>
						</div>
					</li>
					<li>
						<div>
							<a class="fas fa-arrow-circle-up"  href="javascript:;"></a>
							<a class="fas fa-arrow-circle-down"  href="javascript:;"></a>
							<span class="numNode">1</span>
							<span class="chapterNum">生字练习2</span>
							<span>关联课程111：</span>
							<a class="chapterDscribe" href="javascript:;">aaaa</a>
							<span class='fas fa-check sureEditBtn'></span>
							<button type="button" class="delChild btn_defalut">删除</button>
							<button type="button" class="editChild btn_defalut">编辑</button>
						</div>
					</li>
				</ul>
			</li>
		</ul>
	</div>
	<!--关联课程弹窗-->
	<div class="relationCourse">
		<div class="article">
			<div class="header">
				<span>关联课程</span>
				<a href="javascript:;" class="fas fa-times closeBn"></a>
			</div>
			<div class="formList">
				<form>
					<label>选择学段
						<select name="">
							<option value="四年级" selected="selected">四年级</option>
							<option value="5年级">5年级</option>
							<option value="6年级">6年级</option>
						</select>
					</label>
					<label>选择学科
						<select name="" >
							<option value="数学" selected="selected">数学</option>
							<option value="语文">语文</option>
							<option value="英语">英语</option>
						</select>
					</label>
					<button type="button" class="btn_defalut">搜索</button>
				</form>
			</div>
			<table>
				<tr>
					<th>课程名称</th>
					<th>操作</th>
				</tr>
				<tr>
					<td>火车过桥</td>
					<td><a href="javascript:;">查看</a>
						<a href="javascript:;" class="chooseCourse">选择</a></td>
				</tr>
				<tr>
					<td>一元二次方程</td>
					<td><a href="javascript:;">查看</a>
						<a href="javascript:;" class="chooseCourse">选择</a></td>
				</tr>
			</table>
			<ul class="pagination"></ul>
		</div>
	</div>
	<!--新增节点clone-->
	<div class="addChildTemplate" style="display: none">
		<a class="fas fa-arrow-circle-up"  href="javascript:;"></a>
		<a class="fas fa-arrow-circle-down"  href="javascript:;"></a>
		<span class="numNode">1</span>
		<span class="chapterNum">生字练习</span>
		<span>关联课程：</span>
		<a class="chapterDscribe" href="javascript:;"></a>
		<span class='fas fa-check sureEditBtn'></span>
		<button type="button" class="delChild btn_defalut">删除</button>
		<button type="button" class="editChild btn_defalut">编辑</button>
	</div>
	<%--新增章clone--%>
	<div class="addChapterTemplate" style="display: none">
		<a class="fas fa-minus displays" href="javascript:;"></a>
		<a class="fas fa-arrow-circle-up"  href="javascript:;"></a>
		<a class="fas fa-arrow-circle-down"  href="javascript:;"></a>
		<span class="chapterNum">第一章</span>
		<span class="chapterDscribe">八角楼上1</span>
		<span class='fas fa-check sureEditBtn'></span>
		<button type="button" class="delChild btn_defalut">删除</button>
		<button type="button" class="editChild btn_defalut">编辑</button>
		<button type="button" class="addChild btn_defalut">新增节点</button>
	</div>
	<script>
		var indexPage = new indexPage();
		indexPage.event();
		$(".pagination").pagination(7, {
			next_text : "下一页",
			prev_text : "上一页",
			current_page : 1,
			link_to : "javascript:;",
			num_display_entries : 4,
			items_per_page : 1,
			num_edge_entries : 8
		});
	</script>
</html>