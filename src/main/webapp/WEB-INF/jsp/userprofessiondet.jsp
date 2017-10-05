<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="<%=request.getContextPath()%>" />
<%-- form:action="${context}/user/education" --%>
<div class="card mb-1 border-light">
	<div class="card-header">
		<h5 class="mb-0">
			Professional Details<a href="${context}/user/pro/add" class="card-link float-right">Add</a>
		</h5>
	</div>
	<div class="card-body">
		<form:form id="user-profession-det" modelAttribute="userDetailObjform">

			<c:forEach items="${userDetailObjform.userProfessionForm}"
				var="userProfessionForm" varStatus="status">
				<div class="card mb-1">
					<div class="card-body">
						<div class="row">
							<div class="col-12 col-md-6">
								<label class="col-form-label">Institute Name</label> <label
									class="col-form-label">${userProfessionForm.companyName}</label>
							</div>
							<div class="col-12 col-md-6">
								<label class="col-form-label">Institute City</label> <label
									class="col-form-label">${userProfessionForm.companyCity}</label>
							</div>
							<div class="col-12 col-md-6">
								<label class="col-form-label">Qualification</label> <label
									class="col-form-label">${userProfessionForm.joiningYear}</label>
							</div>
							<div class="col-12 col-md-6">
								<label class="col-form-label">Registration Year</label> <label
									class="col-form-label">${userProfessionForm.leavingYear}</label>
							</div>
							<div class="col-12 col-md-6">
								<label class="col-form-label">Passout Year</label> <label
									class="col-form-label">${userProfessionForm.designation}</label>
							</div>
						</div>
					</div>
					<div class="card-body">
						<a href="${context}/user/pro/edit/${userProfessionForm.id}"
							class="card-link float-right">Edit</a>
					</div>
				</div>
			</c:forEach>


		</form:form>
</div>
</div>		