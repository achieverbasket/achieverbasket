<div class="col-12 col-md-3 col-xl-2 bd-sidebar">
<!-- 	<form class="bd-search d-flex align-items-center">
		<input type="search" class="form-control" id="search-input"
			placeholder="Search..." aria-label="Search for..." autocomplete="off">
		<button class="btn-link bd-search-docs-toggle d-md-none p-0 ml-3"
			type="button" data-toggle="collapse" data-target="#bd-docs-nav"
			aria-controls="bd-docs-nav" aria-expanded="false"
			aria-label="Toggle docs avigation">
			<svg class="" xmlns="http://www.w3.org/2000/svg" viewbox="0 0 30 30"
				width="30" height="30" focusable="false">
						<title>Menu</title>
						<path stroke="currentColor" stroke-width="2"
					stroke-linecap="round" stroke-miterlimit="10"
					d="M4 7h22M4 15h22M4 23h22" /></svg>

		</button>
	</form> -->
	<nav class="collapse bd-links" id="bd-docs-nav">
		<div class="bd-toc-item active">
			<%-- <a class="bd-toc-link" href="${context}/dashboard"> Getting
				started </a> --%>
			<ul class="nav bd-sidenav">
			<c:if test="${type != 'candidate'}">
				<li class="active bd-sidenav-active"><a href="${context}/dashboard">DashBoard</a></li>
				<li class=""><a href="${context}/issuer/certificate/create">Issue Certificate</a></li>
				<li class=""><a href="${context}/issuer/certificate/bulkload">Bulk Certificate Issue</a></li>
				<%-- <li class=""><a href="${context}/issuer/certificate/issue">Issue Certificate</a></li> --%>
				<li class=""><a href="${context}/issuer/certificate/templates">View Certificate Templates</a></li>
				<li class=""><a href="${context}/issuer/certificate/create/template">Create Certificate Template</a></li>
				<%-- <li class=""><a href="${context}/issuer/certificate/inqueue">Certificates in Queue</a></li> --%>
				<li class=""><a href="${context}/issuer/certificate/search">Search Certificate</a></li>
				<li class=""><a href="${context}/issuer/blog">Write Blog</a></li>
				<li class=""><a href="${context}/issuer/certificate/image/create">Certificate Image Creation</a></li>
			</c:if>
			<c:if test="${type == 'candidate'}">
				<li class="active bd-sidenav-active"><a href="${context}/dashboard">DashBoard</a></li>
			</c:if>	
			</ul>
		</div>

<!-- 		<div class="bd-toc-item ">
			<a class="bd-toc-link" href="/docs/4.0/layout/overview/"> Layout
			</a>
			<ul class="nav bd-sidenav">
				<li class=""><a href="/docs/4.0/layout/overview/"> Overview
				</a></li>
			</ul>
		</div> -->
	</nav>

</div>