/**
 * @author Swapnil Singhai
 * Date 10-09-2017
 * 
 */
$(document).ready(function() {
	$.fn.datepicker.defaults.format = "dd/mm/yyyy";
	$('.datepicker').datepicker({
		autoclose: true,todayHighlight: true,todayBtn:'linked'
	});
	$("input[type=button]").click(function(e) {
		//alert($(this).parents('form:first').offset().top + 'left' +$(this).parents('form:first').offset().left);
		
		// get the form height and width and mark that with processing image
		if($(this).parents('form:first').attr('id') == 'user-personal-det'){
			
			//overlay image
			displayOverlay('#processing-div',$(this).parents('form:first'),'show');
			
			$.ajax({
				type:"post",
			    data:$(this).parents('form:first').serialize(),
			    url:"save/personaldet",
			    success: function(e){
			       // overlay image
			    	displayOverlay('#processing-div',$(this).parents('form:first'),'hide');
			    	alert("success"+e);
			    },failure: function(e){
			    	// overlay image
			    	displayOverlay('#processing-div',$(this).parents('form:first'),'hide');
			    	alert("error"+e);
			    }
			});
			
		}
		//alert($(this).attr('id'));
		if($(this).attr('id') == 'save-acd-cert' || $(this).attr('id') == 'save-prf-cert' || $(this).attr('id') == 'save-extra-cert'){
			//alert('uploading data');
			e.preventDefault();
			var formData = new FormData();
		    
		    formData.append('type', $(this).attr('id'));
		    if($(this).attr('id') == 'save-acd-cert'){
		    	formData.append('file', $('#user-academic-det input[type=file]')[0].files[0]);
		    	formData.append('dataparam', JSON.stringify({
		    		 	"certificateName": $('#user-academic-det #certificateName').val(),
		                "issueDate": $('#user-academic-det #issueDate').val() ,
		                "endDate": $('#user-academic-det #endDate').val(),
		                "preferenceStatusType": $('#user-academic-det #preferenceStatusType').val()
		            }, {
		                type: "application/json"
		            }));
		    }else if($(this).attr('id') == 'save-prf-cert'){
		    	formData.append('file', $('#user-pro-det input[type=file]')[0].files[0]);
		    	alert('organization value --'+$(document.getElementById('organization.organizationName')).val());
		    	formData.append('dataparam', JSON.stringify({
		    		 	"certificateName": $('#user-pro-det #certificateName').val(),
		                "issueDate": $('#user-pro-det #issueDate').val() ,
		                "salary": $('#user-pro-det #salary').val(),
		                "endDate": $('#user-pro-det #endDate').val(),
		                "preferenceStatusType": $('#user-pro-det #preferenceStatusType').val(),
		                "organization":{
		                	"organizationName": $(document.getElementById('organization.organizationName')).val()
		                }
		            }, {
		                type: "application/json"
		            }));
		    }else if($(this).attr('id') == 'save-extra-cert'){
		    	formData.append('file', $('#user-extra-det input[type=file]')[0].files[0]); 
		    	formData.append('dataparam', JSON.stringify({
		    		 	"certificateName": $('#user-extra-det #certificateName').val(),
		                "issueDate": $('#user-extra-det #issueDate').val() ,
		                "endDate": $('#user-extra-det #endDate').val(),
		                "preferenceStatusType": $('#user-extra-det #preferenceStatusType').val()                   
		            }, {
		                type: "application/json"
		            }));
		    }
		   
		    
		    
		    
			$.ajax({
	            type: "POST",
	            enctype: 'multipart/form-data',
	            url: "upload",
	            data: formData,
	            headers : {
	                'Content-Type' : undefined
	            },
	            processData: false,
	            contentType: false,
	            cache: false,
	            success: function (data) {alert('success');
	            },
	            error: function (xhr, ajaxOptions, thrownError) {
	                if (xhr.readyState == 0 || xhr.status == 0) {
	                    // not really an error
	                    return;
	                } else {
	                    alert("XHR Status = "+xhr.status);
	                    alert("Thrown Error = "+thrownError);
	                    alert("AjaxOptions = "+ajaxOptions)
	                }
	          }
	        });
			
		}
		
	});
	
	function displayOverlay(div,form,action){
		if(action == 'show'){
			$(div).css({top: form.offset().top, left: form.offset().left,height:form.height() , width:form.width()});
			$(div).show();
		}else{	
			$(div).hide();
		}	
	}
	
	
	/*$('#add-u-edu-det').click(function(e){
		$('#user-edu-table tbody>tr:last').clone(true).insertBefore('#user-edu-table tbody>tr:first');
		// chnage the loop
		var rows = $('#user-edu-table tr');

		var count = rows.length;
		var lastRow = rows[count-1];
		var textinput = $(lastRow).find('input');
		
		textinput.eq(0).attr('id', textinput.attr('id') + count);
		textinput.eq(0).attr('name', textinput.attr('name') + count);
		
		
        return false;
	});*/
	
	
	/*$('#add-u-edu-det').click(function(e){
		window.open("new/edudet", 'window', 'width=200,height=100');
	});*/
	
    $('a[data-toggle="tab"]').on('show.bs.tab', function(e) {
        localStorage.setItem('activeTab', $(e.target).attr('href'));
    });
    
    $('.card a[data-toggle="collapse"]').on('show.bs.collapse',function (e) {
    	//alert('set accordian' + $(e).attr('aria-labelledby')+'----------'+$(e.target).attr('href'));
    	//alert(JSON.stringify($(e)));
    	localStorage.setItem('activeAccord',$(e.target).attr('href'));
    	})
    
    var activeTab = localStorage.getItem('activeTab');
    var activeAccord = localStorage.getItem('activeAccord');


    if(activeTab){
        $('.card-header-tabs a[href="' + activeTab + '"]').tab('show');
    }
    if(activeAccord){
    	//alert('activeAccord....'+activeAccord);
    	$('.collapse a[href="' + activeAccord + '"]').collapse('show');
    }
	
	





















































































});
