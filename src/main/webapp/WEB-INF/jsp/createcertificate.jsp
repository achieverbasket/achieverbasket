<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
<link href="${context}/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css"/>
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
						<h5 class="mb-0">Create Certificate</h5>
					</div>
				</div>
				<c:if test="${!empty success}">
					<div class="alert alert-success" role="alert">${success}</div>
				</c:if>
				<c:if test="${!empty  error}">
					<div class="alert alert-danger" role="alert">${error}</div>
				</c:if>
				<form:form  modelAttribute="form" action="${context}/issuer/certificate/create"   method="POST">
				<div class="row">
					<div class="col-12 col-md-9">
						<a href="#" onclick="window.open('${context}/issuer/certificate/imagelist','popwin','width=640, height=480')"
						>Select Certificate Template</a>
					</div>
				</div>
				<table class="table table-sm table-responsive table-bordered ">
					<thead class="thead-light">
						<tr>
							<th scope="col">Template Id</th><th scope="col">Name</th><th scope="col">Description</th><th scope="col">Certificate Type</th><th scope="col">Image Path</th>
						</tr>
					</thead>
					<tbody>
						<tr id="row-p">
							<td>
								<%-- <form:input path="endDate" class="form-control form-control-sm readonly="readonly"/> --%>
								<input value="" name="certificateTemplate.certificateTemplateId" id="certificateTemplate.certificateTemplateId" class="form-control form-control-sm" type="text" readonly="readonly" >
							</td>
							<td>
								<%-- <form:input path="endDate" class="form-control form-control-sm readonly="readonly"/> --%>
								<input value="" name="certificateTemplate.templateName" id="certificateTemplate.templateName" class="form-control form-control-sm" type="text" readonly="readonly" >
							</td>
							<td>
								<%-- <form:input path="endDate" class="form-control form-control-sm readonly="readonly"/> --%>
								<input value="" name="certificateTemplate.certificateDesc" id="certificateTemplate.certificateDesc" type="text" readonly="readonly" class="form-control form-control-sm">
							</td>
							<td>
								<%-- <form:input path="endDate" class="form-control form-control-sm readonly="readonly"/> --%>
								<input value="" name="certificateTemplate.certificateType" id="certificateTemplate.certificateType" type="text" readonly="readonly" class="form-control form-control-sm">
							</td>
							<td>
								<img name="certificateTemplate.templateFile" id="certificateTemplate.templateFile" class="img-fluid mr-3" style="height: 64px;width: 64px;" src="">
							</td>
						</tr>
					</tbody>
				</table>
				<div class="row">
					<div class="col-12 col-md-3">
						<label class="col-form-label">Certificate Type</label>
						<form:select path="certificateType" class="form-control form-control-sm ">
							<form:options items="${certType}"  />
						</form:select>
						<form:errors path="certificateType" />
					</div>
				</div>	
				<div class="row">
					<div class="col-12 col-md-3">
						<label class="col-form-label">issueDate</label>
						<form:input path="issueDate" class="form-control form-control-sm datepicker"/>
						<form:errors path="certificateType" />
					</div>
					<div class="col-12 col-md-3">
						<label class="col-form-label">endDate</label>
						<form:input path="endDate" class="form-control form-control-sm datepicker"/>
						<form:errors path="certificateType" />
					</div>
				</div>
				<div class="row mt-2">	
					<div class="col-12 col-md-3">
						<input type="submit" value="submit" name="submit" class="btn btn-sm btn-dark">
					</div>
				</div>
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
	<script type="text/javascript" src="${context}/js/bootstrap-datepicker.min.js">
		</script>	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/corejs-typeahead/0.11.1/typeahead.bundle.min.js"
		type="text/javascript"></script>
	<script src="${context}/js/form.js" type="text/javascript"></script>
</body>
</html>