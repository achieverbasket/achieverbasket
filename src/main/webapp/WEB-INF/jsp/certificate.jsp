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
</style>
<link href="${context}/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css"/>

</head>
<body class="bd-home">

	<%@include file="header.jsp"%>

	<!-- left panel -->

	<div class="container-fluid">
		<div class="row flex-xl-nowrap">
			<main class="col-12 col-md-9 col-xl-8  bd-content" role="main"><!-- py-md-3 pl-md-5 -->
			<h1 class="bd-title" id="content"></h1>
			<div class="card mb-1 border-light">
				<div class="card-header">
					<h5 class="mb-0">Create Certificate</h5>
				</div>
				<div class="card-body">
					<form:form id="user-academic-det" modelAttribute="form" action="academic?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
						<div class="card mb-1">
							<div class="card-body">
								<div class="row">
									<div class="col-12 col-md-6">
										<label class="col-form-label">Certificate Name</label>
										<form:input path="certificateName" class="form-control"></form:input>
										<form:errors path="certificateName" />
									</div>
									<div class="col-12 col-md-6">
										<label class="col-form-label">Issuer</label>
										<form:input path="issuer.issuerName" class="form-control"></form:input>
										<form:errors path="issuer.issuerName" />
									</div>
									<div class="col-12 col-md-6">
										<label class="col-form-label">Start Date</label>
										<form:input path="issueDate" class="form-control datepicker"></form:input>
										<form:errors path="issueDate" />
									</div>
									<div class="col-12 col-md-6">
										<label class="col-form-label">End Date</label>
										<form:input path="endDate" class="form-control datepicker"></form:input>
										<form:errors path="endDate" />
									</div>
									<div class="col-12 col-md-6">
										<label class="col-form-label">Privacy</label>
										<form:input path="preferenceStatusType" class="form-control"></form:input>
										<form:errors path="preferenceStatusType" />
									</div>
									<div class="col-12 col-md-6">
										<label class="col-form-label">CertificateType</label>
										<form:input path="certificateType" class="form-control"></form:input>
										<form:errors path="certificateType" />
									</div>
									<div class="col-12 col-md-6">
										<label class="col-form-label">Upload</label>
										<form:input type="file" path="certificateFile"></form:input><br/>
									</div>
								</div>
							</div>
							<div class="card-body">
								<input type="button" class="btn btn-sm btn-secondary" onclick="javascript:history.go(-1)" value="Cancel" name="Cancel"> <input type="submit"
									class="btn btn-sm btn-primary float-right" value="Save" id="save-acd-cert" name="Save">
							</div>
						</div>
					</form:form>
				</div>
			</div>
			</main>
		</div>
	</div>


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
	
	<script>
	
	</script>
</body>
</html>

