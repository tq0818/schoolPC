<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
<input type="hidden" value="${classVoPage.rowCount }" id="rowCount"/>
<input type="hidden" value="${classVoPage.pageNo }" id="pageNo"/>
<input type="hidden" value="${classVoPage.pageSize }" id="pageSize"/>
<ul>
	<c:if test="${status == 'MODULE_NO_CREATED' }">
	<c:forEach var="c" items="${classVoPage.data }">
	<li>
	    <table class="table table-hover table-class">
	        <tr>
	        <td width="8%" rowspan="2">
               <c:if test="${c.classTeachType == 'TEACH_METHOD_FACE' }">
               	<img src="<%=rootPath %>/images/operate-icon1.png" class="operate_icon"/>
               </c:if>
               <c:if test="${c.classTeachType == 'TEACH_METHOD_LIVE' }">
                   <img src="<%=rootPath %>/images/iconfont-shexiangji.png" class="operate_icon"/>
               </c:if>
            </td>
            <td width="25%" rowspan="2">
                <span class="c-b-title">
	                    ${c.name }
                </span>
            </td>
            <td width="18%">
                <span class="c-title">学科:</span>
                <span class="c-content">${c.oneItemName }</span>
            </td>
            <td width="18%">
                <span class="c-title">模块名称:</span>
                <span class="c-content">${c.moduleName }</span>
            </td>
            <td width="15%">
                <span class="c-title">已排课次:</span>
                <span class="c-content"><i class="cl_fa6900">${c.lessonPlan == null ? 0 : c.lessonPlan }/</i>${c.lessonTotal == null ? 0 : c.lessonTotal}</span>
            </td>
            <td width="16%" rowspan="2">
                 <a href="javascript:;" class="allowAdmissions btn btn-default operate_btn btn-stu" data-id="${c.id }">允许招生</a>
                 <a href="<%=rootPath %>/classModuleNo/editModule/${c.id}" class=" btn btn-default operate_btn lh30 btn-cls" data-id="${c.id }">排课</a>
            </td>
	        </tr>
	        <tr>
              <td>
                  <span class="c-title">学科小类:</span>
                  <span class="c-content">${c.twoItemName }</span>
              </td>
              <td>
                  <span class="c-title">授课方式:</span>
                  <span class="c-content">
	                    <c:if test="${c.classTeachType == 'TEACH_METHOD_FACE' }">面授</c:if>
	                    <c:if test="${c.classTeachType == 'TEACH_METHOD_LIVE' }">直播</c:if>
	              </span>
              </td>
              <td>
                  <span class="c-title">总课时:</span>
                  <span class="c-content">${c.totalHourse == null ? 0 : c.totalHourse }</span>
              </td>
          </tr>
	    </table>
	</li>
	</c:forEach>
	</c:if>
	<c:if test="${status == 'MODULE_NO_ON_SALE' }">
	<c:forEach var="c" items="${classVoPage.data }">
	<li>
		<table class="table table-hover table-class">
		<tr>
	        <td width="8%" rowspan="2">
               <c:if test="${c.classTeachType == 'TEACH_METHOD_FACE' }">
               	<img src="<%=rootPath %>/images/operate-icon1.png" class="operate_icon"/>
               </c:if>
               <c:if test="${c.classTeachType == 'TEACH_METHOD_LIVE' }">
                   <img src="<%=rootPath %>/images/iconfont-shexiangji.png" class="operate_icon"/>
               </c:if>
            </td>
            <td width="25%" rowspan="2">
                <span class="c-b-title">
	                    ${c.name }
                </span>
            </td>
            <td width="18%">
                <span class="c-title">学科:</span>
                <span class="c-content">${c.oneItemName }</span>
            </td>
            <td width="18%">
                <span class="c-title">模块名称:</span>
                <span class="c-content">${c.moduleName }</span>
            </td>
            <td width="15%">
                <span class="c-title">已排课次:</span>
                <span class="c-content"><i class="cl_fa6900">${c.lessonPlan == null ? 0 : c.lessonPlan }/</i>${c.lessonTotal == null ? 0 : c.lessonTotal}</span>
            </td>
            <td width="16%" rowspan="2">
            	<span class="fl"> 
	            	<a href="javascript:;" class="btn btn-default checkStudent operate_btn_30 btn-sel-stu" data-id="${c.id }">已招学员</a>
	            	<a href="javascript:;" class="btn btn-default allowAdmissions operate_btn_30 btn-stop-sale" data-id="${c.id }">停止招生</a>
            	</span>
            	<a href="<%=rootPath %>/classModuleNo/editModule/${c.id}" class=" btn btn-default operate_btn lh30 btn-cls" data-id="${c.id }">排课</a>
            </td>
	        </tr>
	        <tr>
              <td>
                  <span class="c-title">学科小类:</span>
                  <span class="c-content">${c.twoItemName }</span>
              </td>
              <td>
                  <span class="c-title">授课方式:</span>
                  <span class="c-content">
	                    <c:if test="${c.classTeachType == 'TEACH_METHOD_FACE' }">面授</c:if>
	                    <c:if test="${c.classTeachType == 'TEACH_METHOD_LIVE' }">直播</c:if>
	              </span>
              </td>
              <td>
                  <span class="c-title">已招生:</span>
                  <span class="c-content"><i class="cl_fa6900">${c.enrollYetStudents == null ? 0 : c.enrollYetStudents }/</i>${c.planEnrollStudents == null ? 0 : c.planEnrollStudents}</span>
              </td>
          </tr>
        </table>
    </li>
	</c:forEach>
	</c:if>
	<c:if test="${status == 'MODULE_NO_OFFLINE' }">
	<c:forEach var="c" items="${classVoPage.data }">
	<li>
		<table class="table table-hover table-class">
		<tr>
	        <td width="8%" rowspan="2">
               <c:if test="${c.classTeachType == 'TEACH_METHOD_FACE' }">
               	<img src="<%=rootPath %>/images/operate-icon1.png" class="operate_icon"/>
               </c:if>
               <c:if test="${c.classTeachType == 'TEACH_METHOD_LIVE' }">
                   <img src="<%=rootPath %>/images/iconfont-shexiangji.png" class="operate_icon"/>
               </c:if>
            </td>
            <td width="25%" rowspan="2">
                <span class="c-b-title">
	                    ${c.name }
                </span>
            </td>
            <td width="18%">
                <span class="c-title">学科:</span>
                <span class="c-content">${c.oneItemName }</span>
            </td>
            <td width="18%">
                <span class="c-title">模块名称:</span>
                <span class="c-content">${c.moduleName }</span>
            </td>
            <td width="15%">
                <span class="c-title">已排课次:</span>
                <span class="c-content"><i class="cl_fa6900">${c.lessonPlan == null ? 0 : c.lessonPlan }/</i>${c.lessonTotal == null ? 0 : c.lessonTotal}</span>
            </td>
            <td width="16%" rowspan="2">
           		<span class="fl">
	                <a href="javascript:;" class="btn btn-default checkStudent operate_btn_30 btn-sel-stu" data-id="${c.id }">已招学员</a>
	        		<a href="javascript:;" class="btn btn-default allowAdmissions operate_btn_30 btn-stop-offline" data-id="${c.id }">结&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;课</a>
            	</span>
            	<a href="<%=rootPath %>/classModuleNo/editModule/${c.id}" class=" btn btn-default operate_btn lh30 btn-cls" data-id="${c.id }">排课</a>
            </td>
	        </tr>
	        <tr>
              <td>
                  <span class="c-title">学科小类:</span>
                  <span class="c-content">${c.twoItemName }</span>
              </td>
              <td>
                  <span class="c-title">授课方式:</span>
                  <span class="c-content">
	                    <c:if test="${c.classTeachType == 'TEACH_METHOD_FACE' }">面授</c:if>
	                    <c:if test="${c.classTeachType == 'TEACH_METHOD_LIVE' }">直播</c:if>
	              </span>
              </td>
              <td>
                  <span class="c-title">已招生:</span>
                  <span class="c-content"><i class="cl_fa6900">${c.enrollYetStudents == null ? 0 : c.enrollYetStudents }/</i>${c.planEnrollStudents == null ? 0 : c.planEnrollStudents}</span>
              </td>
          </tr>
		</table>
	</li>
	</c:forEach>
	</c:if>
	<c:if test="${status == 'MODULE_NO_FINISH' }">
	<c:forEach var="c" items="${classVoPage.data }">
	<li>
		<table class="table table-hover table-class">
		<tr>
	        <td width="8%" rowspan="2">
               <c:if test="${c.classTeachType == 'TEACH_METHOD_FACE' }">
               	<img src="<%=rootPath %>/images/operate-icon1.png" class="operate_icon"/>
               </c:if>
               <c:if test="${c.classTeachType == 'TEACH_METHOD_LIVE' }">
                   <img src="<%=rootPath %>/images/iconfont-shexiangji.png" class="operate_icon"/>
               </c:if>
            </td>
            <td width="25%" rowspan="2">
                <span class="c-b-title">
	                    ${c.name }
                </span>
            </td>
            <td width="18%">
                <span class="c-title">学科:</span>
                <span class="c-content">${c.oneItemName }</span>
            </td>
            <td width="18%">
                <span class="c-title">模块名称:</span>
                <span class="c-content">${c.moduleName }</span>
            </td>
            <td width="15%">
                <span class="c-title">校区:</span>
                <span class="c-content">${c.campusName }</span>
            </td>
            <td width="16%" rowspan="2">
                <a href="javascript:;" class="btn btn-default checkStudent operate_btn btn-sel-stu" data-id="${c.id }">上课学员</a>
            	<a href="javascript:;" class=" btn btn-default operate_btn btn-cls" data-id="${c.id }">查看排课</a>
            </td>
	        </tr>
	        <tr>
              <td>
                  <span class="c-title">学科小类:</span>
                  <span class="c-content">${c.twoItemName }</span>
              </td>
              <td>
                  <span class="c-title">授课方式:</span>
                  <span class="c-content">
	                    <c:if test="${c.classTeachType == 'TEACH_METHOD_FACE' }">面授</c:if>
	                    <c:if test="${c.classTeachType == 'TEACH_METHOD_LIVE' }">直播</c:if>
	              </span>
              </td>
              <td>
                  <span class="c-title">已招生:</span>
                  <span class="c-content"><i class="cl_fa6900">${c.enrollYetStudents == null ? 0 : c.enrollYetStudents }/</i>${c.planEnrollStudents == null ? 0 : c.planEnrollStudents}</span>
              </td>
          </tr>
		</table>
	</li>
	</c:forEach>
	</c:if>
</ul>

<script type="text/javascript" src="<%=rootPath%>/javascripts/class/classes/detail.js"></script>