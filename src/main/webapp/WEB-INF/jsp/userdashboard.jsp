<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta content="<%=response.getHeader("auth-token")%>" name="auth-token">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title></title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"> 
<link rel="stylesheet" href="${context}/css/main.css">
<link rel="stylesheet" href="${context}/css/custom.css">
<style type="text/css">

</style>

</head>
<body class="bd-home">
	
<%@include file="header.jsp" %>
	
		<!-- left panel -->

	<div class="container-fluid">
		<div class="row flex-xl-nowrap">
			<%@include file="leftnav.jsp" %>
			<main class="col-12 col-md-9 col-xl-8  bd-content" role="main"><!-- py-md-3 pl-md-5 -->
				<h1 class="bd-title" id="content">Introduction</h1><%=response.getHeader("auth-token")%>
					<jsp:include page="userprofile.jsp" ></jsp:include>
			</main>	
			<div id="processing-div" style="position:absolute;display: none;">Kindly wait...</div>
		</div>
	</div>		

				<%-- <center></center> --%>
				<div></div>


				<%@include file="footer.jsp"%>


				<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
				<script
					src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
				<script
					src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
				<script
					src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
				<script
					src="https://cdnjs.cloudflare.com/ajax/libs/corejs-typeahead/0.11.1/typeahead.bundle.min.js"
					type="text/javascript"></script>
				<script
					src="../js/form.js"
					type="text/javascript"></script>	
				<script type="text/javascript">


$(document).ready(function(){

$.ajax({
		type:"get",
	    url:"${context}/certificates/user/1",
	    success: function(e){
	    	// get the grid for certificates
	    	
	    },failure: function(e){
	    	alert("error"+e);
	    }
	});

});
</script>	
</body>
</html>