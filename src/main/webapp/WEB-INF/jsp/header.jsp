
<nav class="navbar navbar-expand-md fixed-top navbar-dark bg-dark">
	<a class="navbar-brand" href="#">Achiever Basket</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarsExampleDefault"
		aria-controls="navbarsExampleDefault" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="${context}/">
					<i class="fa fa-home" aria-hidden="true"></i> Home
			</a></li>
		</ul>
		<form class="form-inline col-md-4">
			<input class="form-control col-md-12" type="text" placeholder="Search"
				aria-label="Search">
		</form>
		
		<ul class="navbar-nav mr-auto">
			<c:if test="${type == 'candidate'}">	
			<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="http://example.com"
					id="dropdown01" data-toggle="dropdown" aria-haspopup="true"
					aria-expanded="false">Certificate</a>
					<div class="dropdown-menu" aria-labelledby="dropdown01">
						<a class="dropdown-item" href="${context}/certificates/1">My Certificates</a>
						<a class="dropdown-item" href="${context}/certificate">Create Certificate</a>
					</div>
			</li>
			</c:if>	
		</ul>	
		
		<form class="form-inline">
			<c:choose>
  				<c:when test="${not empty username}">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle"  data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
								<i class="fa fa-user" aria-hidden="true"></i> <c:out value="${username}"></c:out>
							</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="${context}/setting">Setting</a>
								<a class="dropdown-item" href="${context}/profile">Profile</a>
								<a class="dropdown-item" href="${context}/login?logout">Logout</a>
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