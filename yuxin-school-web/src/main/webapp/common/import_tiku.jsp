<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri = "/WEB-INF/wx.tld" prefix = "wx" %>
<%
	String rootPath=request.getContextPath();
%>

<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/fatstyle.css" />
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/register.css"/>
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/tiku/queStyle.css" />
<link rel="stylesheet"  type="text/css" href="<%=rootPath %>/stylesheets/tiku/base.css" />
<link href="<%=rootPath %>/stylesheets/manage.css" rel="stylesheet" type="text/css"/>

<script type="text/javascript">var rootPath='<%=rootPath%>'</script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/plus/jquery.min.js"></script>
<script type="text/javascript" src="<%=rootPath%>/plugins/jquery-validation/jquery.validate.js"></script>
<script type="text/javascript" src="<%=rootPath %>/javascripts/register.js"></script>
