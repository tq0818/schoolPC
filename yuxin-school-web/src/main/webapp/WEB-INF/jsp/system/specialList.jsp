<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/decorators/import.jsp" %>  
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>专题列表</title>
  <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/vip.css">
  <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css">
  <link rel="stylesheet" type="text/css" href="<%=rootPath %>/stylesheets/special/special-news-manage.css">
  <script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
  <script type="text/javascript" src="<%=rootPath %>/javascripts/special/special.js"></script>
    <script type="text/javascript" src="<%=rootPath %>/javascripts/common/utils.js"></script>
  <script type="text/javascript" src="<%=rootPath%>/javascripts/plus/jquery.pagination.js"></script>
  
<script type="text/javascript">
       function updateSpecial(id){
    	   window.location = "<%=rootPath%>/commodity/toAddSpecialPage?id="+id;
       }
       
       function updateStatus(specialId){
      	   var status = $('#status'+specialId).attr("status");
           if(status == 1){
      			status = 0;
      		}else if(status ==0){
      			status = 1;
      		}
      		$.ajax({
   			url: "<%=rootPath%>/commodity/updateStatusOrder",
   			data:{"specialId":specialId,"status":status},
   			dataType:"text",
   			success: function(data){
   				
   				if(data == "success"){
   					 $.msg("操作成功");
   				
   					if(status == 1){
   					    $('#status'+specialId).html("下架");
   					}else if(status == 0){
   					    $('#status'+specialId).html("上架");
   						
   					}
   					$('#status'+specialId).attr("status",status);
   				}
   			}
   		});
      		
   	  }
       
       function updateOrder(specialId){
    	   var orderFlag =  $('#orderFlag'+specialId).val(),
               regu = /^[1-9]\d*$/;
           if(!regu.test(orderFlag)){
               $.msg("请输入非零正整数！");
               return false;
           }
    	   $.ajax({
      			url: "<%=rootPath%>/commodity/updateStatusOrder",
      			data:{"specialId":specialId,"orderFlag":orderFlag},
      			dataType:"text",
      			success: function(data){
      				
      				if(data == "success"){
      					 $.msg("操作成功");
      				
      				}
      			}
      		});
       }
       
      function  clearOrder(specialId){
    	  $('#orderFlag'+specialId).val("");
      }
       
      function findSpecialByapge(pageNum){
    	  var url = "<%=rootPath%>/commodity/findSpecialByapge";
  		  var data = {"pageNum":pageNum,"pageSize":12}
    	  $('#specialList').load(url,data)
      }
      
      $(document).ready(function(){
    	
    	  $(".pagenations").pagination('${count}', {
    	    	 next_text : "下一页",
    	    	 prev_text : "上一页",
    	    	 current_page :'${pageNum-1}',
    	    	 link_to : "javascript:void(0)",
    	    	 num_display_entries : 8,
    	    	 items_per_page : 12,
    	    	 num_edge_entries : 1,
    	    	 callback:function(page,jq){
    		    	 var pageNo = page + 1;
    		    	 findSpecialByapge(pageNo);  
    	    	 }
    	   });
      });
</script>
</head>
<body>
    <div class="specialTab">
  
        <table class="table table-center">
            
           <tbody>
               <tr>
                    <th>序号</th>
                    <th>专题名称</th>
                    <th>专题标签</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${specialList}" var="special" varStatus="status">
                <tr>
                    <td>${status.index +1 }</td>
                    <td>${special.title}</td>
                    <td>${special.label }</td>
                    <td>
                        <a href="javascript:void(0)" onclick="updateSpecial('${special.id}')" class="btn btn-mini btn-primary">编辑</a>
                        <a id="status${special.id}" status="${special.status}" href="javascript:void(0)" onclick="updateStatus('${special.id}')"  class="btn btn-mini btn-default"><c:if test="${special.status == 0}">上架</c:if> <c:if test="${special.status == 1}">下架</c:if></a>
                        <div class="sort-info">
                            <input id="orderFlag${special.id}" type="text" value="${special.orderFlag}" class="sortnume"> 
                            <i onclick="updateOrder('${special.id}')" class="btn-ico btn-gou">√</i>
                            <i onclick="clearOrder('${special.id}')" class="btn-ico btn-cha">X</i>
                        </div>
                    </td>
                </tr>
              </c:forEach>
           </tbody>
        </table>
    </div>
<div class="pages">
	<ul class="pagination pagenations"></ul>
</div>

</body>
</html>   


