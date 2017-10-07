<nav class="navbar navbar-expand-md fixed-top navbar-dark bg-dark">
	<a class="navbar-brand" href="#">Navbar</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarsExampleDefault"
		aria-controls="navbarsExampleDefault" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="#">
					<i class="fa fa-home" aria-hidden="true"></i> Home
			</a></li>
			<li class="nav-item"><a class="nav-link" href="#">Link</a></li>
			<li class="nav-item"><a class="nav-link disabled" href="#">Disabled</a>
			</li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="http://example.com"
				id="dropdown01" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false">Dropdown</a>
				<div class="dropdown-menu" aria-labelledby="dropdown01">
					<a class="dropdown-item" href="#">Action</a> <a
						class="dropdown-item" href="#">Another action</a> <a
						class="dropdown-item" href="#">Something else here</a>
				</div></li>
		</ul>
		<form class="form-inline">
			<input class="form-control" type="text" placeholder="Search"
				aria-label="Search">
			<button class="btn btn-outline-success mr-1" type="submit">Search</button>
		</form>
		<form class="form-inline mr-5">
			<c:choose>
  				<c:when test="${not empty userDetailObjform.firstName}">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<i class="fa fa-user" aria-hidden="true"></i> <c:out value="${userDetailObjform.firstName}"></c:out>
							</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="${context}/setting">Setting</a>
								<a class="dropdown-item" href="${context}/logout">Logout</a>
							</div>
						</li>
					</ul>
				</c:when>
  				<c:otherwise>
    				<a href="${context}/register" class="btn btn-outline-success mr-1"  type="submit">Register</a>
					<a href="${context}/login" class="btn btn-outline-success"  type="submit">Login</a>
  				</c:otherwise>
  			</c:choose>	
		</form>
	</div>
</nav>