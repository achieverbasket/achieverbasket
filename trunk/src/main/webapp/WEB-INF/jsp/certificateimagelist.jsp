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
<style type="text/css">
.card{
margin: 0;
}



</style>


</head>
<body class="bd-home">

	

	<!-- left panel -->

	<div class="container-fluid">
		<div class="row flex-xl-nowrap">
		
			<main class="col-12 col-md-10 col-xl-10  bd-content" role="main"><!-- py-md-3 pl-md-5 -->
				<h1 class="bd-title" id="content"></h1>
				<div class="card mb-1 border-light">
					<div class="card-header">
						<h5 class="mb-0">Template List</h5>
					</div>
				</div>
				<form:form  modelAttribute="form" action="${context}/issuer/certificate/create"   method="POST">
				<table class="table table-sm table-responsive table-bordered ">
					<thead class="thead-light">
						<tr>
							<th scope="col">S.No</th>
							<th scope="col">Template Id</th>
							<th scope="col">Name</th>
							<th scope="col">Description</th>
							<th scope="col">Certificate Type</th>
							<th scope="col">Image Path</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="obj" items="${templatelist}" varStatus="i">
							<tr id="row-${i.index}">
								<td id="i-${i.index}">${i.index}</td>
								<td id="cti-${i.index}">${obj.certificateTemplateId}</td>
								<td id="tn-${i.index}">${obj.templateName}</td>
								<td id="cd-${i.index}">${obj.certificateDesc}</td>
								<td id="ct-${i.index}">${obj.certificateType}</td>
								<td ><img id="imgsrc-${i.index}" class="img-fluid mr-3" style="height: 64px;width: 64px;" 
								src="${obj.filePath}"></td>
								<td><a href="#" id="${i.index}">Click to View</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				</form:form>
			</main>
		</div>
	</div>

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
	<script src="${context}/js/form.js" type="text/javascript"></script>
	
	<script language="javascript"> 
	$(document).ready(function () {
		$('a').click(function () {
            window.opener.document.getElementById('certificateTemplate.certificateTemplateId').value = $('#cti-'+$(this).attr('id')).text();
            window.opener.document.getElementById('certificateTemplate.templateName').value = $('#tn-'+$(this).attr('id')).text();
            window.opener.document.getElementById('certificateTemplate.certificateDesc').value = $('#cd-'+$(this).attr('id')).text();
            window.opener.document.getElementById('certificateTemplate.certificateType').value = $('#ct-'+$(this).attr('id')).text();
            window.opener.document.getElementById('certificateTemplate.templateFile').src = $('#imgsrc-'+$(this).attr('id')).attr('src');
            window.close();
        });
        
	});	
</script> 
	
</body>
</html>