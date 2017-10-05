<div class="card border">
	<div class="card-body">
		<label class="col-form-label">Name</label>
		<form:input path="commentorname" class="form-control"/>
		<form:errors path="commentorname" /> 
		
		<label class="col-form-label">Email</label>
		<form:input path="commentoremail" class="form-control"/>
		<form:errors path="commentoremail" /> 
		
		
		<label class="col-form-label">Comment</label>
		<form:textarea path="commentorText" class="form-control"/>
		<form:errors path="commentorText" /> 
	</div>	
	
	<div class="card-body">
		<input type="submit" class="btn btn-sm btn-primary float-right" value="Save" name="Save">
	</div>
</div>






