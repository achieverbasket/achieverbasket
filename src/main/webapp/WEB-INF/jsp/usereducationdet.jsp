<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="<%=request.getContextPath()%>" />
<%-- form:action="${context}/user/education" --%>
<div class="card mb-1 border-light">
	<div class="card-header">
		<h5 class="mb-0">
			Educational Details<a href="${context}/user/edu/add" class="card-link float-right">Add</a>
		</h5>
	</div>
	<div class="card-body">
		<form:form id="user-education-det" modelAttribute="userDetailObjform">

			<c:forEach items="${userDetailObjform.userEducationForm}"
				var="userEducationForm" varStatus="status">
				<div class="card mb-1">
					<div class="card-body">
						<div class="row">
							<div class="col-12 col-md-6">
								<label class="col-form-label">Institute Name</label> <label
									class="col-form-label">${userEducationForm.instituteName}</label>
							</div>
							<div class="col-12 col-md-6">
								<label class="col-form-label">Institute City</label> <label
									class="col-form-label">${userEducationForm.instituteCity}</label>
							</div>
							<div class="col-12 col-md-6">
								<label class="col-form-label">Qualification</label> <label
									class="col-form-label">${userEducationForm.qualification}</label>
							</div>
							<div class="col-12 col-md-6">
								<label class="col-form-label">Registration Year</label> <label
									class="col-form-label">${userEducationForm.registrationYear}</label>
							</div>
							<div class="col-12 col-md-6">
								<label class="col-form-label">Passout Year</label> <label
									class="col-form-label">${userEducationForm.passoutYear}</label>
							</div>
							<div class="col-12 col-md-6">
								<label class="col-form-label">Percent marks</label> <label
									class="col-form-label">${userEducationForm.percentmarks}</label>
							</div>
						</div>
					</div>
					<div class="card-body">
						<a href="${context}/user/edu/edit/${userEducationForm.id}"
							class="card-link float-right">Edit</a>
					</div>
				</div>
			</c:forEach>


		</form:form>
</div>
</div>
		