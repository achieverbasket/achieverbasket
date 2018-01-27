<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

	<%@include file="header.jsp"%>

	<!-- left panel -->

	<div class="container-fluid">
		<div class="row flex-xl-nowrap">
			<%@include file="leftnav.jsp"%>
			<main class="col-12 col-md-9  bd-content" role="main"><!-- py-md-3 pl-md-5 -->
				<h1 class="bd-title" id="content"></h1>
				<div class="card mb-1 border-light">
					<div class="card-header">
						<h5 class="mb-0">Blog</h5>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-12 col-md-2">
							<input class="form-control" type="radio" name="arrowQuestion" id="text" value="Text" >
							<input type="text" value="enter name" name="name" id="username">
							<input type="button" id="saveImage" value="save image">
					</div>
					<div class="col-sm-12 col-md-10">
						<canvas id="c" class="img-fluid"  width="600" height="600" style="border:2px solid black"></canvas>
					</div>
				</div>
			</main>
		</div>
	</div>
	

	<%@include file="footer.jsp"%>


	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/js/bootstrap.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/fabric.js/1.7.20/fabric.js"></script>
		<script type="text/javascript">

		
		
		
			var canvas = new fabric.Canvas('c');
			var canvasHeight=canvas.height;
			var canvasWidth=canvas.width;
			canvas.setBackgroundImage('http://fabricjs.com/assets/jail_cell_bars.png', canvas.renderAll.bind(canvas), {
		    backgroundImageOpacity: 0.5,
		    backgroundImageStretch: true,
		    crossOrigin: 'anonymous'
		 });
			fabric.util.addListener(document.getElementById('saveImage'), 'click', function () {
			    var img = new Image();
			    img.src = canvas.toDataURL('image/png');
			    console.log(img.src);
			    var dataURL = canvas.toDataURL();
			    var blobBin = atob(dataURL.split(',')[1]);
			    var array = [];
			    for (var i = 0; i < blobBin.length; i++) {
			        array.push(blobBin.charCodeAt(i));
			    }
			    window.open(canvas.toDataURL('image/png'));
			});

				getItem = function(id) {
			    var object = null,
			        objects = canvas.getObjects();
//alert(objects.length);
			    for (var i = 0, len = objects.length; i < len; i++) {
	//					alert(objects[i].id)
			        if (objects[i].id && objects[i].id === id) {
			            object = objects[i];
			            break;
			        }
			    }

			    return object;
			};
			document.getElementById('username').addEventListener('keyup', function () {
				  //textObject.text = document.getElementById('title').value;
				  	
				  	var obj = getItem('nametxt');
			        obj.text = document.getElementById('username').value;
				  canvas.renderAll();
				});

			
		
			addTextBox(0,0);        
		

		function addTextBox(arrowLeft,arrowTop)
		{
		    textBox = new fabric.Textbox("Enter Text",{
		    fontSize: 16,
		    id:'nametxt',
		    fontFamily: 'Arial',
		    textAlign: 'left',  
		    width: 180,	// for 20 characters
		    top:arrowTop,
		    left:arrowLeft,
		    backgroundColor : 'yellow',
		    textAlign: 'center',
		    selectable: false
		    });
		    
		   canvas.add(textBox);
		   canvas.renderAll(); 

			textBox.on('selected', function(e) {
			        //console.log(textBox);
			        console.log('selected Textbox');
			            canvas.renderAll();
			        
			});

			textBox.on("editing:entered", function (e) {
				console.log("enter editing");
				//canvas.setActiveObject(textBox);
					var obj = canvas.getActiveObject();
			        if(obj.text == "Enter Text")
			        {
			        		obj.selectAll();
			        		obj.removeChars();
			        }
			});
	
			textBox.on("editing:exited", function (e) {
			        console.log("exited editing");
			});
		    var left = arrowLeft;

		    textBox.on(("changed"),function(){
		       //canvas.setActiveObject(textBox);
		       console.log("chnaged");
		       var actualWidth = textBox.scaleX * textBox.width;
		        var largest = canvas.getActiveObject().__lineWidths[0];
		        var tryWidth = (largest + 15) * textBox.scaleX;
		        canvas.getActiveObject().set("width",tryWidth);
		        if((textBox.left + actualWidth) >= canvas.width - 10)
		         {
		             //console.log(canvas.height - arrowLeft)
		             textBox.set("width", canvas.width - left - 10)
		        }
		            canvas.renderAll();

		    });
		    textBox.on(("modified"),function(){
		    	console.log("textbox modified");
			  left = textBox.left;
		      console.log("textbox modified");
		            canvas.renderAll();

		    });  
		}
	   			
	</script>
</body>
</html>