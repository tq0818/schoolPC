<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp"%>
  <div id="matersList">
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
                         <span>是否代报考<input class="iconfont" disabled="disabled" type="checkbox" name="dbk" id="dbk"/></span>
                  </c:if>
                  <c:if test="${payMaster.isAgentMaterialComplete==1 }">
                  	     <span>资料是否齐全 <input type="checkbox" disabled="disabled" checked="checked" name="zlqq" id="zlqq"/></span>
                  </c:if>
                   <c:if test="${payMaster.isAgentMaterialComplete!=1 }">
                          <span>资料是否齐全 <input type="checkbox" disabled="disabled" name="zlqq" id="zlqq"/></span>
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
                            <%-- <p class="m-c-c-t" id="unmaterial">
                                <span class="m-c-c-tt">未交资料</span>
                                <span class="m-c-c-c">
                        
                                <c:forEach items="${stuMaterialList }" var="materialList">
                                	 <em class="icon-btn" style="cursor: pointer;">${materialList.materialName }<i ids="${materialList.id }" txt="${materialList.materialName }">${materialList.materialNumNot }</i></em>
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
                            </p> --%>
                        </div>
                    </div>
                    <div class="m-c-bottom clear">
                        <p class="m-c-title">
                         	   考务备注
                        </p>
                        <p class="m-c-c">
                            <textarea disabled="disabled" id="matrailMark" name="" id="" placeholder="">${payMaster.agentRemark }</textarea>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>