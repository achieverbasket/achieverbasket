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
					<h1 class="bd-title" id="content"></h1>
					<div class="card mb-1 border-light">
						<div class="card-header">
							<h5 class="mb-0">
								Educational Details<a href="${context}/user/edu/add" class="card-link float-right">Add</a>
							</h5>
						</div>
						<div class="card-body">
							<form:form id="user-education-det" modelAttribute="form">
								<div class="card mb-1">
									<div class="card-body">
										<div class="row">
											<div class="col-12 col-md-6">
												<label class="col-form-label">Institute Name</label>
												<form:input path="instituteName" class="form-control"></form:input>
												<form:errors path="instituteName" />	
											</div>
											<div class="col-12 col-md-6">
												<label class="col-form-label">Institute City</label>
												<form:input path="instituteCity" class="form-control"></form:input>
												<form:errors path="instituteCity" />
											</div>
											<div class="col-12 col-md-6">
												<label class="col-form-label">Qualification</label>
												<form:input path="qualification" class="form-control"></form:input>
												<form:errors path="qualification" /> 
											</div>
											<div class="col-12 col-md-6">
												<label class="col-form-label">Registration Year</label>
												<form:input path="registrationYear" class="form-control"></form:input>
												<form:errors path="registrationYear" />
											</div>
											<div class="col-12 col-md-6">
												<label class="col-form-label">Passout Year</label>
												<form:input path="passoutYear" class="form-control"></form:input>
												<form:errors path="passoutYear" />
											</div>
											<div class="col-12 col-md-6">
												<label class="col-form-label">Percent marks</label>
												<form:input path="percentmarks" class="form-control"></form:input>
												<form:errors path="percentmarks" />
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