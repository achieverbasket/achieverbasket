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
		
			<main class="col-12 col-md-9 col-xl-8  bd-content" role="main"><!-- py-md-3 pl-md-5 -->
				<h1 class="bd-title" id="content"></h1>
				<div class="card mb-1 border-light">
					<div class="card-header">
						<h5 class="mb-0">Imgae List</h5>
					</div>
				</div>
				<form:form  modelAttribute="form" action="${context}/issuer/certificate/create"   method="POST">
				<div class="row">
					<c:forEach var="list" items="${templatelist}">
						<div class="col-12 col-md-3">
							${list.certificateTemplateId}
							<img alt="" class="img-fluid" id="${list.certificateTemplateId}"
							src="https://i.guim.co.uk/img/media/d17f2f060f4e607ad357ba7cf8ee618999840540/0_0_1920_1152/master/1920.jpg?w=1900&q=55&auto=format&usm=12&fit=max&s=05557699bc9c3f0999416d4bdb422f19" >
							</div>
					</c:forEach>
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
		$('img').click(function () {
            alert($(this).attr('src'));
            window.opener.document.getElementById('cet-image').src = $(this).attr('src');
            window.opener.document.getElementById('filePath').value = $(this).attr('id');
            window.close();
        });
        
	});	
</script> 
	
</body>
</html>