<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>
 <div class="mark-more">
          <div class="main-content">
            <div class="m-title">
                <h2 class="h6">代报考</h2>
            </div>
            <div class="m-diy space">
                <p class="m-tools">
                  <c:if test="${payMaster.isAgent==1 }">
                  		 <span>是否代报考<input class="iconfont" disabled="disabled" type="checkbox" checked="checked" name="dbk" id="dbk"/></span>
                  </c:if>
                   <c:if test="${payMaster.isAgent!=1 }">
                         <span>是否代报考<input class="iconfont" type="checkbox" name="dbk" id="dbk"/></span>
                  </c:if>
                    <c:if test="${payMaster.isAgentMaterialComplete==1 }">
                  	     <span>资料是否齐全 <input type="checkbox" checked="checked" name="zlqq" id="zlqq"/></span>
                  </c:if>
                   <c:if test="${payMaster.isAgentMaterialComplete!=1 }">
                          <span>资料是否齐全 <input type="checkbox" name="zlqq" id="zlqq"/></span>
                  </c:if>
                    <!-- <i class="iconfont">&#xe609;</i> -->
                </p>
                <div class="m-content">
                    <div class="m-c-top clear">
                        <p class="m-c-title">
                          	  资料交接
                        </p>
                        <div class="m-c-c">
                            <p class="m-c-c-t" id="material">
                                <span class="m-c-c-tt">已交资料</span>
                                <span class="m-c-c-c">
                                <c:forEach items="${stuMaterialList }" var="materialList">
                                	 <em class="icon-btn" style="cursor: pointer;">${materialList.materialName }<i ids="${materialList.id }" txt="${materialList.materialName }">${materialList.materialNumYet }</i></em>
                                </c:forEach>
                               <c:if test="${empty stuMaterialList}">
                               		<em class="icon-btn" style="cursor: pointer;">一寸<i txt="一寸">0</i></em> 
                                    <em class="icon-btn" style="cursor: pointer;">二寸<i txt="二寸">0</i></em>
                                    <em class="icon-btn" style="cursor: pointer;">身份证<i txt="身份证">0</i></em>
                                    <em class="icon-btn" style="cursor: pointer;">学历证<i txt="学历证">0</i></em>
                                    <em class="icon-btn" style="cursor: pointer;">学位证<i txt="学位证">0</i></em>
                                    <em class="icon-btn" style="cursor: pointer;">证明材料<i txt="证明材料">0</i></em>
                                    <em class="icon-btn" style="cursor: pointer;">其他<i txt="其他">0</i></em>
                               </c:if>
                                </span>
                            </p>
                        </div>
                    </div>
                    <div class="m-c-bottom clear">
                        <p class="m-c-title">
                         	   考务备注
                        </p>
                        <p class="m-c-c">
                            <textarea id="matrailMark" name="" id="" placeholder="输入你的备注信息">${payMaster.agentRemark }</textarea>
                        </p>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${payMaster.isAgent!=1 }">
         <div class="mark-more">
            <div class="main-content">
                <div class="m-title">
                    <h2 class="h6">应交费用</h2>
                </div>
                 <ul class="list-infos clear">
                 	<li>
	                 <span class="c-title">代报考费用</span>
	                 <input type="text" id="materialFee" value="${payMaster.examAgentFee }"/>
	                 <span class="c-title">缴费方式<input type="radio" checked="checked" name="payFeeMethod"/>现金<input type="radio" name="payFeeMethod"/>POS</span>
	                </li>
	             </ul>
             </div>
        </div>
       </c:if>
        <div>
        	<ul>
        		<li>
        			  <div class="m-content">
			            <p class="c text-center">
			                <span class="c-title">&nbsp;</span>
			                <span class="c-content">
			                    <a href="javascript:saveStuMaterial();" class="btn btn-sm btn-primary">提交</a>
			                    <a href="" class="btn btn-sm btn-default">取消</a>
			                </span>
			            </p>
			        </div>
        		</li>
        	</ul>
        </div> 
    </div>