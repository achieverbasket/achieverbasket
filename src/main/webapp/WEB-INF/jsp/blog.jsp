<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="<%=request.getContextPath()%>" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title></title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="stylesheet" href="${context}/css/main.css">
<link rel="stylesheet" href="${context}/css/custom.css">
<style type="text/css">
.card{
margin: 0;
}



</style>


</head>
<body class="bd-home">

	<%@include file="header.jsp"%>

	<!-- left panel -->

	<div class="container-fluid">
		<div class="row flex-xl-nowrap">
			<%@include file="leftnav.jsp"%>
			<main class="col-12 col-md-9 col-xl-8  bd-content" role="main"><!-- py-md-3 pl-md-5 -->
				<h1 class="bd-title" id="content"></h1>
				<div class="card mb-1 border-light">
					<div class="card-header">
						<h5 class="mb-0">Blog</h5>
					</div>
				</div>
				<form:form modelAttribute="form">
				<div class="card mb-2 border-0">
					<label class="col-form-label">Heading</label>
					<form:input path="heading" class="form-control"/>
					<form:errors path="heading" /> 
					
					<label class="col-form-label">Content</label>
					<form:textarea id="contenttext" path="content" class="form-control"/>
					<form:errors path="content" /> 
					
					
				</div>
				<%@ include file="comment.jsp" %>
				</form:form>
			</main>
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
	<script src="https://cdn.ckeditor.com/4.7.3/standard/ckeditor.js"></script>	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/corejs-typeahead/0.11.1/typeahead.bundle.min.js"
		type="text/javascript"></script>
	<script src="${context}/js/form.js" type="text/javascript"></script>
	<script type="text/javascript">
	CKEDITOR.replace('contenttext');
	</script>
</body>
</html>