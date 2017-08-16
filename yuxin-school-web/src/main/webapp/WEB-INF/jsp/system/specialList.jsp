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
</head>
<body>
    <div class="specialTab mainbackground">
  
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
                        <a href="javascript:Form.editUser('update',1137)" class="btn btn-mini btn-primary">编辑</a>
                        <a href="javascript:Form.deleteUser(1137)" class="btn btn-mini btn-default">下架</a>
                        <div class="sort-info">
                            <input type="text" class="sortnume"> 
                            <i class="btn-ico btn-gou">√</i>
                            <i class="btn-ico btn-cha">X</i>
                        </div>
                    </td>
                </tr>
              </c:forEach>
           </tbody>
        </table>
    </div>
     

</body>
</html>   


