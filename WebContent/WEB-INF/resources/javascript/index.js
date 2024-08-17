var session_match;
function processWaitingButtonSpinner(whatToProcess) 
{
	switch (whatToProcess) {
	case 'START_WAIT_TIMER': 
		$('.spinner-border').show();
		$(':button').prop('disabled', true);
		break;
	case 'END_WAIT_TIMER': 
		$('.spinner-border').hide();
		$(':button').prop('disabled', false);
		break;
	}
}
function reloadPage(whichPage)
{
	switch(whichPage){
	case 'OUTPUT':
		processModalPopUp('READ-PREVIEW');
		break;
	}
}
function processModalPopUp(whatToProcess,dataToUse) 
{
	switch (whatToProcess) {
 	case 'READ-PREVIEW':
	  setTimeout(() => {processBadmintonProcedures('READ-PREVIEW');}, 500);
	  $('#preview_image').fadeIn("fast").attr('src',URL.createObjectURL(
			new Blob([new Uint8Array(dataToUse.file_data)], {type: dataToUse.content_type})));
	  break;
	}
}
function initialiseForm(whatToProcess, dataToProcess)
{
	document.getElementById('previewIpAddress').value = dataToProcess.previewIpAddress;
}
function processUserSelection(whichInput)
{	
	switch($(whichInput).attr('id')){
		case 'select_formate':
			processBadmintonProcedures('GET-CONFIG-DATA');
			break;
		case 'load_match_btn':
		if(checkEmpty($('#previewIpAddress'),'IP Address Blank') == false) {
			return false;
		}
      	document.initialise_form.submit();
		break;
	}
}
function processBadmintonProcedures(whatToProcess)
{
	var valueToProcess;
	switch(whatToProcess.toUpperCase()) {
	case 'READ-DATABASE-AND-POPULATE':
		valueToProcess = $('#database_file_timestamp').val();
		break;
		
	case 'ON-STRIKE_PLAYER':
		valueToProcess = $('#select_onstrike_player option:selected').val() ;
		break;
		
	case 'GOLDEN-POINTS_PLAYER':
		valueToProcess = $('#select_golden_points_player option:selected').val() ;
		break;
		
	}
	
	
	$.ajax({    
        type : 'Get',     
        url : 'processBadmintonProcedures.html',     
        data : 'whatToProcess=' + whatToProcess + '&valueToProcess=' + valueToProcess, 
        dataType : 'json',
        success : function(data) { 
        	switch(whatToProcess) {
			case 'GET-CONFIG-DATA':
				initialiseForm('UPDATE-CONFIG',data);
				break;
			case 'READ-PREVIEW':
				processModalPopUp('READ-PREVIEW',data);
				break;
        	}
	    },    
	    error : function(e) {    
	  	 	console.log('Error occured in ' + whatToProcess + ' with error description = ' + e);     
	    }    
	});
}
function checkEmpty(inputBox,textToShow) {

	var name = $(inputBox).attr('id');
	
	document.getElementById(name + '-validation').innerHTML = '';
	document.getElementById(name + '-validation').style.display = 'none';
	$(inputBox).css('border','');
	if(document.getElementById(name).value.trim() == '') {
		$(inputBox).css('border','#E11E26 2px solid');
		document.getElementById(name + '-validation').innerHTML = textToShow + ' required';
		document.getElementById(name + '-validation').style.display = '';
		document.getElementById(name).focus({preventScroll:false});
		return false;
	}
	return true;	
}