<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="card mb-1 border-light">
	<div class="card-header">Personal Details</div>
	<div class="card-body">
		<form:form id="user-personal-det" modelAttribute="userDetailObjform">
			<div class="row">
				<div class="col-12 col-md-6">
					<label class="col-form-label">First name</label> <label
						class="col-form-label">${userDetailObjform.firstName}</label>
				</div>
				<div class="col-12 col-md-6">
					<label class="col-form-label">Last name</label> <label
						class="col-form-label">${userDetailObjform.lastName}</label>
				</div>
				<div class="col-12 col-md-6">
					<label class="col-form-label">Email</label> <label
						class="col-form-label">${userDetailObjform.email}</label>
				</div>
				<div class="col-12 col-md-6">
					<label class="col-form-label">Gender</label> <label
						class="col-form-label">${userDetailObjform.gender}</label>
				</div>
				<div class="col-12 col-md-6">
					<label class="col-form-label">Date of Birth</label> <label
						class="col-form-label">${userDetailObjform.dob}</label>
				</div>
				<div class="col-12 col-md-6">
					<label class="col-form-label">Mobile No</label> <label
						class="col-form-label">${userDetailObjform.mobileno}</label>
				</div>
				<div class="col-12 col-md-6">
					<label class="col-form-label">Alternate Mobile No</label> <label
						class="col-form-label">${userDetailObjform.alternatemobileno}</label>
				</div>
			</div>
			<a href="${context}/user/per/edit/${userDetailObjform.id}"
							class="card-link float-right">Edit</a>
		</form:form>
</div>
</div>		