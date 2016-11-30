$(document).ready(function(){
	getAllCustomer();
})
$(document).on('click','#add',function(){
	var cname = $('#cusname').val();
	var line = $('#line').val();
	var add = $('#addr').val();
	var cno = $('#conno').val();
	var ctype = $('#colltype').val();
	var url = "/Finance/finance?operation=addCustomer&cname="+ cname +"&line="+ line +"&add="+ add +"&cno="+ cno +"&ctype="+ ctype ;
    if(cname === ""){
    	$('#cusname').focus().css('outline-color','red');
    	return false;
    }
    if(line === ""){
    	$('#line').focus().css('outline-color','red');
    	return false;
    }
    if(add === ""){
    	$('#addr').focus().css('outline-color','red');
    	return false;
    }
    if(cno === ""){
    	$('#conno').focus().css('outline-color','red');
    	return false;
    }
    if(ctype === ""){
    	$('#colltype').focus().css('outline-color','red');
    	return false;
    }
	$.ajax({
		url:url,
		type:'POST'
	})
	.done(function(result){
		getAllCustomer();
		$('#cusname').val("");
		$('#line').val("");
		$('#addr').val("");
		$('#conno').val("");
		$('#colltype').val("");
	});
	fail(function(result){
		alert(result);
	})
});
$(document).on('click','.delete',function(){
	var td = $(this).parent();
	var tr = td.parent();
	var accNo = tr.children()[0].innerHTML;
	var url = "/Finance/finance?operation=deleteCustomer&accno="+ accNo;
	$.ajax({
		url:url,
		type:'POST'
	})
	.done(function(result){
		tr.remove();
	});
	fail(function(result){
		alert(result);
	})

});
$(document).on('keyup','#acc',function(){
	var acc = $('#acc').val();
	if(acc !== "" ){
		if(acc.length>=5){
			var url = "/Finance/finance?operation=getOneCustomer&accno="+ acc;
			$.ajax({
				url:url,
				type:'POST'
			})
			.done(function(result){
				var res = JSON.parse(result);
				$('#cusname').val(res.customerName);
				$('#line').val(res.line);
				$('#addr').val(res.address);
				$('#conno').val(res.contactNo);
				$('#colltype').val(res.collectionType);
			})
			.fail(function(result){
				alert(result);
			})
			}else{
			$('#cusname').val("");
			$('#line').val("");
			$('#addr').val("");
			$('#conno').val("");
			$('#colltype').val("");
		}
	}
})
