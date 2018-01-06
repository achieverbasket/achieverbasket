/**
 * @author Swapnil Singhai
 * Date 10-09-2017
 * 
 */
$(document).ready(function() {
	
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	
	
	$.fn.datepicker.defaults.format = "dd/mm/yyyy";
	$('.datepicker').datepicker({
		autoclose: true,todayHighlight: true,todayBtn:'linked'
	});
	$("input[type=button]").click(function(e) {
		if($(this).parents('form:first').attr('id') == 'user-personal-det'){
			
			displayOverlay('#processing-div',$(this).parents('form:first'),'show');
			
			$.ajax({
				type:"post",
			    data:$(this).parents('form:first').serialize(),
			    url:"save/personaldet",
			    success: function(e){
			    	displayOverlay('#processing-div',$(this).parents('form:first'),'hide');
			    	alert("success"+e);
			    },failure: function(e){
			    	displayOverlay('#processing-div',$(this).parents('form:first'),'hide');
			    	alert("error"+e);
			    }
			});
			
		}
		if($(this).attr('id') == 'save-acd-cert' || $(this).attr('id') == 'save-prf-cert' || $(this).attr('id') == 'save-extra-cert'){
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
    	localStorage.setItem('activeAccord',$(e.target).attr('href'));
    	})
    
    var activeTab = localStorage.getItem('activeTab');
    var activeAccord = localStorage.getItem('activeAccord');

    if(activeTab){
        $('.card-header-tabs a[href="' + activeTab + '"]').tab('show');
    }
    if(activeAccord){
    	$('.collapse a[href="' + activeAccord + '"]').collapse('show');
    }
	
    var searchCLucene = new Bloodhound({
	    datumTokenizer: function (datum) {
	        return Bloodhound.tokenizers.whitespace(datum.value);
	    },
	    queryTokenizer: Bloodhound.tokenizers.whitespace,
	    remote: {
	    	wildcard: '%QUERY',
	        url: 'country?query=%QUERY',
	        replace: function(url, uriEncodedQuery) {
	            val = $('#selectdd option:selected').val();
	            return url.replace("%QUERY",uriEncodedQuery)
	          },
	        cache: false,
	        filter: function (obj) {
	        	return $.each( obj, function( key, value ) {
	        		return {
	                    value: value.name
	                };
	        	});
	        }
	    },
	    limit:100
	});
    
    var searchCityLucene = new Bloodhound({
	    datumTokenizer: function (datum) {
	        return Bloodhound.tokenizers.whitespace(datum.value);
	    },
	    queryTokenizer: Bloodhound.tokenizers.whitespace,
	    remote: {
	    	wildcard: '%QUERY',
	        url: 'city?query=%QUERY',
	        replace: function(url, uriEncodedQuery) {
	            val = $('#selectdd option:selected').val();
	            return url.replace("%QUERY",uriEncodedQuery)
	          },
	        cache: false,
	        filter: function (obj) {
	        	return $.each( obj, function( key, value ) {
	        		return {
	                    value: value.name
	                };
	        	});
	        }
	    },
	    limit:100
	});
    var searchSLucene = new Bloodhound({
	    datumTokenizer: function (datum) {
	        return Bloodhound.tokenizers.whitespace(datum.value);
	    },
	    queryTokenizer: Bloodhound.tokenizers.whitespace,
	    remote: {
	    	wildcard: '%QUERY',
	        url: 'state?query=%QUERY',
	        replace: function(url, uriEncodedQuery) {
	            val = $('#selectdd option:selected').val();
	            return url.replace("%QUERY",uriEncodedQuery)
	          },
	        cache: false,
	        filter: function (obj) {
	        	return $.each( obj, function( key, value ) {
	        		return {
	                    value: value.name
	                };
	        	});
	        }
	    },
	    limit:100
	});
    var searchILucene = new Bloodhound({
	    datumTokenizer: function (datum) {
	        return Bloodhound.tokenizers.whitespace(datum.value);
	    },
	    queryTokenizer: Bloodhound.tokenizers.whitespace,
	    remote: {
	    	wildcard: '%QUERY',
	        url: 'issuer?query=%QUERY',
	        replace: function(url, uriEncodedQuery) {
	            val = $('#selectdd option:selected').val();
	            return url.replace("%QUERY",uriEncodedQuery)
	          },
	        cache: false,
	        filter: function (obj) {
	        	return $.each( obj, function( key, value ) {
	        		return {
	                    value: value.issuerName
	                };
	        	});
	        }
	    },
	    limit:100
	});
    
	searchCLucene.initialize();searchSLucene.initialize();searchCityLucene.initialize();searchILucene.initialize();
	
		$('#'+$.escapeSelector('country.name')).typeahead({hint: true,
			  highlight: true,
			  minLength: 3}, {
			display : function(item){ return item.name},
			source : searchCLucene.ttAdapter(),
			templates: {
		        empty: [
		            '<div class="empty-message"> Cannot find.</div>'
		        ]   
		    }
		});
		$('#'+$.escapeSelector('state.name')).typeahead({hint: true,
			  highlight: true,
			  minLength: 3}, {
			display : function(item){ return item.name},
			source : searchSLucene.ttAdapter(),
			templates: {
		        empty: [
		            '<div class="empty-message"> Cannot find.</div>'
		        ]   
		    }
		});
		$('#'+$.escapeSelector('city.name')).typeahead({hint: true,
			  highlight: true,
			  minLength: 3}, {
			display : function(item){ return item.name},
			source : searchCityLucene.ttAdapter(),
			templates: {
		        empty: [
		            '<div class="empty-message"> Cannot find.</div>'
		        ]   
		    }
		});
		$('#'+$.escapeSelector('city.name')).on('typeahead:selected', function (e, datum) {
			$('#'+$.escapeSelector('city.name')).val(datum.name);
		});
		$('#'+$.escapeSelector('issuer.issuerName')).typeahead({hint: true,
			  highlight: true,
			  minLength: 3}, {
			display : function(item){ return item.issuerName},
			source : searchILucene.ttAdapter(),
			templates: {
		        empty: [
		            '<div class="empty-message"> Cannot find.</div>'
		        ]   
		    }
		});
		
		var comment_div = '<div id="commentbox" class="input-group input-group-sm"><input type="text" class="form-control">';
		comment_div = comment_div+'<span class="input-group-btn"><button class="btn btn-primary" type="button">Reply</button></span></div>';
		
		$('.1_reply_btn').click(function(){
			var btn = $(this);if(btn.next('div').attr('id') == 'commentbox'){return false;};
			$(comment_div).insertAfter($(this));
		    return false;
		});    
		$('.comment-btn').click(function(){
			var btn = $(this);
			var div = btn.parent().next('div');
			if(div.attr('class') == 'card-body'){
				$(div).show();
				return false;
			};
		});
		
		
		
		













































































});
