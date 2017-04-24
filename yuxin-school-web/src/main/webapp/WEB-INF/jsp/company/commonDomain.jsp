<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<script src="<%=request.getContextPath()%>/javascripts/company/commonHeader.js" id="seajsnode"></script>
<style>
.rows .col-3{width:300px !important;}
</style>

<script>
	$(function(){
		$selectMenu('org_head');
	});
</script>
