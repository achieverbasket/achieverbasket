/**
 * @author Swapnil Singhai
 * Date 10-09-2017
 * 
 */
$(document).ready(function() {
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