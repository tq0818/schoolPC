<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	String filename = request.getParameter("filename");
	Integer filesize = Integer.parseInt(request.getParameter("filesize"));
	String filekey = request.getParameter("filekey");
	Integer filetype = Integer.parseInt(request.getParameter("filetype"));
	Integer uptype = Integer.parseInt(request.getParameter("uptype"));
	Integer servicetype = Integer.parseInt(request.getParameter("servicetype"));
	String callbackurl = request.getParameter("callbackurl");
	long deadline = System.currentTimeMillis() + 3600;
%>