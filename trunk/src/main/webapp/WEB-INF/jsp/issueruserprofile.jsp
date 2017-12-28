<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="context" value="<%=request.getContextPath()%>" />
<div class="row">
	<div class="col-12 col-md-4 col-xl-3">
			<div class="card text-center bg-success" >
				<img class="rounded-circle mx-auto d-block mt-1" width="100" height="100" id="image" style="max-width: 100%" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Sun_poster.svg/500px-Sun_poster.svg.png" alt="Card image cap">
				<div class="card-body">
					<h5 class="card-title text-white">Swapnil Singhai</h5>
				</div>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">Associate Developer</li>
					<li class="list-group-item">MSCI</li>
					<li class="list-group-item">Mumbai, India</li>
				</ul>
			</div>
	</div>
	<div class="col-12 col-md-6 col-xl-7 mt-1 mt-md-0">
		<jsp:include page="activities.jsp"></jsp:include>
	</div>	
	<%-- div class="col-12 col-md-8 col-xl-9 mt-1 mt-md-0">
		<div class="card">
			<div class="card-header text-center" style="background-color: #fff">
				<ul class="nav nav-tabs card-header-tabs">
					<li class="nav-item"><a class="nav-link active" class="nav-link" id="home-tab" data-toggle="tab" href="#home">Activities</a>
					</li>
					<li class="nav-item"><a class="nav-link" id="link-tab" data-toggle="tab" href="#link">Profile</a></li>
				</ul>
			</div>
			<div class="card-body">
					  <div class="tab-content" id="myTabContent">
							<div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
									<ul class="list-unstyled">
										<li class="media"><img class="d-flex mr-3" style="height: 64px;width: 64px;" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Sun_poster.svg/500px-Sun_poster.svg.png"
											alt="Generic placeholder image">
											<div class="media-body">
												<h5 class="mt-0 mb-1">HSG Examination</h5>
												<h6 class="mt-0 mb-1">CBSE Board</h6>
												<label class="text-muted">Bhopal ,MP</label>
												
											</div></li>
										<li class="media my-4"><img class="d-flex mr-3" style="height: 64px;width: 64px;" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Sun_poster.svg/500px-Sun_poster.svg.png"
											alt="Generic placeholder image">
											<div class="media-body">
												<h5 class="mt-0 mb-1">List-based media object</h5>
												Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
												scelerisque ante sollicitudin. Cras purus odio, vestibulum in
												Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
												scelerisque ante sollicitudin. Cras purus odio, vestibulum in
											</div></li>
										<li class="media"><img class="d-flex mr-3" style="height: 64px;width: 64px;" src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d4/Sun_poster.svg/500px-Sun_poster.svg.png"
											alt="Generic placeholder image">
											<div class="media-body">
												<h5 class="mt-0 mb-1">List-based media object</h5>
												Cras sit amet nibh libero, in gravida nulla. Nulla vel metus
											</div>
										</li>
									</ul>
								</div>
						  <div class="tab-pane fade" id="link" role="tabpanel" aria-labelledby="dropdown1-tab">
								<%@ include file="testuserpersonaldet.jsp" %>
								<%@ include file="testcertificatelist.jsp" %>
					</div>
					</div>
			</div>
		</div>
	</div> --%>
</div>	