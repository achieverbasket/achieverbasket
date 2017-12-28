<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="context" value="<%=request.getContextPath()%>"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
<title>Register</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css"> 

<style type="text/css">
/* GLOBAL STYLES
-------------------------------------------------- */
/* Padding below the footer and lighter body text */

body {
  padding-top: 3rem;
  padding-bottom: 3rem;
  color: #5a5a5a;
}


/* CUSTOMIZE THE CAROUSEL
-------------------------------------------------- */

/* Carousel base class */
.carousel {
  margin-bottom: 4rem;
}
/* Since positioning the image, we need to help out the caption */
.carousel-caption {
  z-index: 10;
  bottom: 3rem;
}

/* Declare heights because of positioning of img element */
.carousel-item {
  height: 32rem;
  background-color: #777;
}
.carousel-item > img {
  position: absolute;
  top: 0;
  left: 0;
  min-width: 100%;
  height: 32rem;
}


/* MARKETING CONTENT
-------------------------------------------------- */

/* Center align the text within the three columns below the carousel */
.marketing .col-lg-4 {
  margin-bottom: 1.5rem;
  text-align: center;
}
.marketing h2 {
  font-weight: normal;
}
.marketing .col-lg-4 p {
  margin-right: .75rem;
  margin-left: .75rem;
}


/* Featurettes
------------------------- */

.featurette-divider {
  margin: 5rem 0; /* Space out the Bootstrap <hr> more */
}

/* Thin out the marketing headings */
.featurette-heading {
  font-weight: 300;
  line-height: 1;
  letter-spacing: -.05rem;
}


/* RESPONSIVE CSS
-------------------------------------------------- */

@media (min-width: 40em) {
  /* Bump up size of carousel content */
  .carousel-caption p {
    margin-bottom: 1.25rem;
    font-size: 1.25rem;
    line-height: 1.4;
  }

  .featurette-heading {
    font-size: 50px;
  }
}

@media (min-width: 62em) {
  .featurette-heading {
    margin-top: 7rem;
  }
}

</style>

</head>
<body class="bd-home">
	
<%@include file="header.jsp" %>

	<div class="container mt-lg-5">
		<div class="row">
			<div class="col-md-4">
				<div class="card mb-2">
					<div class="card-body text-xs-center">
						<h5>Registration for Candidates/User</h5>
					</div>
				</div>
				<div class="card">
					<div class="card-body">
						<h4 class="card-title text-xs-center">Join Us</h4>
						<form:form name="f" form:action="${context}/register/user" method="post" modelAttribute="userRegisterForm">
							<div class="form-group">
								<form:input  type="fname" class="form-control" path="firstName" 
									 placeholder="First Name" required="true" maxlength="20"/>
								<form:errors path="firstName" cssclass="error" />	 
							</div>
							<div class="form-group">
								<form:input type="lname" class="form-control" path="lastName" 
									placeholder="Last Name" required="true" maxlength="20"/>
								<form:errors path="lastName"  />	
							</div>
							<div class="form-group">
								<form:input type="email" class="form-control" path="email" 
									aria-describedby="emailHelp" placeholder="Email" required="true" maxlength="50"/>
								<form:errors path="email" />	
							</div>
							<div class="form-group">
								<form:input type="mobile" class="form-control" path="mobile" 
									aria-describedby="mobileHelp" placeholder="Mobile" required="false" maxlength="15"/>
								<form:errors path="mobile" />	
							</div>
							<div class="form-group">
								<form:input type="password" class="form-control"
									path="password" placeholder="Password" maxlength="30" required="true"/>
								<form:errors path="password" cssClass="error" />	
							</div>
							<div class="form-group">
								<form:input type="password1" class="form-control"
									path="password1" placeholder="Retype Password" maxlength="30" required="true"/>
								<form:errors path="password1" cssClass="error" />	
								<font class="text-muted smallcase">
								Password policy:<br> 
								Alphanumeric, Char length 6 - 10
								</font>	
							</div>
							<button type="submit" class="btn btn-primary">Join Us</button>
						</form:form>
					</div>
				</div>
			</div>
			<div class="col-md-3"></div>
			<div class="col-md-4">
				<div class="card mb-2">
					<div class="card-body text-xs-center">
						<h5>Registration for Organization/Issuer</h5>
					</div>
				</div>	
				<div class="card">
					<div class="card-body">
						<h4 class="card-title text-xs-center">Join Us</h4>
						<form:form name="f" form:action="${context}/register/issuer" method="post" modelAttribute="issuerRegisterForm">
							<div class="form-group">
								<form:input class="form-control" path="orgName" 
									 placeholder="Organization Name" required="true" maxlength="20"/>
								<form:errors path="orgName" cssclass="error" />	 
							</div>
							<div class="form-group">
								<form:input  type="fname" class="form-control" path="firstName" 
									 placeholder="Contact Person First Name" required="true" maxlength="20"/>
								<form:errors path="firstName" cssclass="error" />	 
							</div>
							<div class="form-group">
								<form:input type="lname" class="form-control" path="lastName" 
									placeholder="Contact Person Last Name" required="true" maxlength="20"/>
								<form:errors path="lastName"  />	
							</div>
							<div class="form-group">
								<form:input type="email" class="form-control" path="email" 
									aria-describedby="emailHelp" placeholder="Contact Person Email" required="true" maxlength="50"/>
								<form:errors path="email" />	
							</div>
							<div class="form-group">
								<form:input type="mobile" class="form-control" path="mobile" 
									aria-describedby="mobileHelp" placeholder="Contact Person Mobile" required="false" maxlength="15"/>
								<form:errors path="mobile" />	
							</div>
							<div class="form-group">
								<form:input type="password" class="form-control"
									path="password" placeholder="Password" maxlength="30" required="true"/>
								<form:errors path="password" cssClass="error" />	
							</div>
							<div class="form-group">
								<form:input type="password1" class="form-control"
									path="password1" placeholder="Retype Password" maxlength="30" required="true"/>
								<form:errors path="password1" cssClass="error" />	
								<font class="text-muted smallcase">
								Password policy:<br> 
								Alphanumeric, Char length 6 - 10
								</font>	
							</div>
							<button type="submit" class="btn btn-primary">Join Us</button>
						</form:form>
					</div>
				</div>
			</div>
	</div>
	</div>


<%@include file="footer.jsp" %>				
	

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/corejs-typeahead/0.11.1/typeahead.bundle.min.js" type="text/javascript"></script>
</body>
</html>