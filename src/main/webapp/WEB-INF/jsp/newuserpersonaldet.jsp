<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"> 
<link rel="stylesheet" href="${context}/css/main.css">
<link rel="stylesheet" href="${context}/css/custom.css">

</head>
<body class="bd-home">
	
<%@include file="header.jsp" %>
	
		<!-- left panel -->

	<div class="container-fluid">
		<div class="row flex-xl-nowrap">
			<%@include file="leftnav.jsp" %>
				<main class="col-12 col-md-9 col-xl-8  bd-content" role="main"><!-- py-md-3 pl-md-5 -->
					<h1 class="bd-title" id="content"></h1>
					<div class="card mb-1 border-light">
						<div class="card-header">
							<h5 class="mb-0">
								Personal Details
							</h5>
						</div>
						<div class="card-body">
							<form:form id="user-personal-det" modelAttribute="form">
								<div class="card mb-1">
									<div class="card-body">
										<div class="row">
											<div class="col-12 col-md-6">
												<label class="col-form-label">First Name</label>
												<form:input path="firstName" class="form-control"></form:input>
												<form:errors path="firstName" />	
											</div>
											<div class="col-12 col-md-6">
												<label class="col-form-label">Last City</label>
												<form:input path="lastName" class="form-control"></form:input>
												<form:errors path="lastName" />
											</div>
											<div class="col-12 col-md-6">
												<label class="col-form-label">Email</label>
												<form:input path="email" class="form-control"></form:input>
												<form:errors path="email" /> 
											</div>
											<div class="col-12 col-md-6">
												<label class="col-form-label">Gender</label>
												
												<div class="form-check-inline form-control">
													<label class="form-check-label"><form:radiobutton class="form-check-input"  path="gender" value="M" /> Male</label>
													<label class="form-check-label"><form:radiobutton class="form-check-input" path="gender" value="F" /> Female</label>
												</div>	
												<%-- <form:radiobutton  path="gender" value="M" class="form-control"/>male
												<form:radiobutton  path="gender" value="F" class="form-control" />female --%>
												<form:errors path="gender" />
											</div>
											<div class="col-12 col-md-6">
												<label class="col-form-label">Date of Birth</label>
												<form:input path="dob" class="form-control"></form:input>
												<form:errors path="dob" />
											</div>
											<div class="col-12 col-md-6">
												<label class="col-form-label">Mobile No</label>
												<form:input path="mobileno" class="form-control"></form:input>
												<form:errors path="mobileno" />
											</div>
											<div class="col-12 col-md-6">
												<label class="col-form-label">Alternate Mobile No</label>
												<form:input path="alternatemobileno" class="form-control"></form:input>
												<form:errors path="alternatemobileno" />
											</div>
										</div>
									</div>
									<div class="card-body">
										<input type="button" class="btn btn-sm btn-secondary" onclick="javascript:history.go(-1)" value="Cancel" name="Cancel">
										<input type="submit" class="btn btn-sm btn-primary float-right" value="Save" name="Save">
									</div>
								</div>
							</form:form>
						</div>
					</div>
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
					src="${context}/js/form.js"
					type="text/javascript"></script>	
</body>
</html>