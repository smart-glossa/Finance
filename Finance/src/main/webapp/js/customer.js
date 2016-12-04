$(document).ready(function(){
})
$(document).on('click','#add',function(){
	var cname = $('#cusname').val();
	var add = $('#addr').val();
	var cno = $('#conno').val();
	var url = "/Finance/finance?operation=addCustomer&cname="+ cname +"&add="+ add +"&cno="+ cno ;
    if(cname === ""){
    	$('#cusname').focus().css('outline-color','red');
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
	$.ajax({
		url:url,
		type:'POST'
	})
	.done(function(result){
		getAllCustomer();
		$('#cusname').val("");
		$('#addr').val("");
		$('#conno').val("");
	})
	.fail(function(result){
		alert(result);
	})
});
$(document).on('click','.delete',function(){
	var td = $(this).parent();
	var tr = td.parent();
	var cusId = tr.children()[0].innerHTML;
	var url = "/Finance/finance?operation=deleteCustomer&cusId="+ cusId;
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
$(document).on('keyup','#cusId',function(){
	var cusId = $('#cusId').val();
	if(cusId !== "" ){
		
			var url = "/Finance/finance?operation=getOneCustomer&cusId="+ cusId;
			$.ajax({
				url:url,
				type:'POST'
			})
			.done(function(result){
				var res = JSON.parse(result);
				$('#cusname').val(res.cusName);
				$('#addr').val(res.address);
				$('#conno').val(res.contactNo);
			})
			.fail(function(result){
				alert(result);
			})
			}else{
			$('#cusname').val("");
			$('#addr').val("");
			$('#conno').val("");
		}
});
$(document).on('click','#update',function(){
	var cusId = $('#cusId').val();
	var cname = $('#cusname').val();
	var add = $('#addr').val();
	var cno = $('#conno').val();
	var url = "/Finance/finance?operation=updateCustomer&cusId=" + cusId +"&cname=" + cname+"&add=" + add +"&cno=" + cno ; 
	$.ajax({
		url:url,
		type:'POST'
	})
	.done(function(result){
		getAllCustomer();
	})
	.fail(function(result){
		alert(result);
	})
})
function getAllCustomer(){
	var url = "/Finance/finance?operation=getAllCustomer";
	$.ajax({
		url:url,
		type:'POST'
	})
      .done(function(result){
    	  var res = JSON.parse(result);
    	  var length = res.length;
    	  var table = '<table>'
    		  table += '<tr><th>SerialNumber</th><th>CustomerName</th><th>Address</th><th>ContactNumber</th><th>Delete</th></tr>';
    	      for(i=0;i<length;i++){
    	    	  table += '<tr class="row">'
    	    	  table += '<td>'+ res[i].cusId +'</td>';
    	    	  table += '<td>'+ res[i].cusName +'</td>';
    	    	  table += '<td>'+ res[i].address +'</td>';
    	    	  table += '<td>'+ res[i].contactNo +'</td>';
    	    	  table += '<td><img src="images/delete.jpg" height="35px" width="35px" class="delete"></td></tr>';
    	      }
                  table += '</table>';  
                  $('.getAllCus')[0].innerHTML = table;
      })
	.fail(function(result){
		alert(result);
	})
}



