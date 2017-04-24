<%@ page import="com.yuxin.wx.model.user.Users" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>
<% String rootPath=request.getContextPath();
	Users loginUser=(Users)session.getAttribute("loginUser");
%>
