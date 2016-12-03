$(document).ready(function(){
	getAllAccount();
$(document).on('click','#sub',function(){
	var line = $("#line").val();
	var cType = $("#colltype").val();
	var amtGiven = $("#amtgiven").val();
	var amttopay = $("#amttopay").val();
	var cusId = $("#cusId").val();
	if(line ===""){
		$("#line").focus().css("outline-color","Red");
		return;
	}
	if(cType ===""){
		$("#colltype").focus().css("outline-color","Red");
		return;
	}
	if(amtGiven ===""){
		$("#amtgiven").focus().css("outline-color","Red");
		return;
	}
	if(amttopay ===""){
		$("#amttopay").focus().css("outline-color","Red");
		return;
	}
	if(cusId ===""){
		$("#cusId").focus().css("outline-color","Red");
		return;
	}
	var url =" http://localhost:8080/Finance/account?operation=addAccounts&line="+ line +"&collType="+cType+"&amountGiven="+amtGiven+"&amountToPay="+amttopay+"&cusId="+cusId ;
	$.ajax({
		url:url,
		typ:'POST'
	})
	.done(function(result){
		getAllAccount();
		$('#line').val("");
		$('#colltype').val("");
		$('#amtgiven').val("");
		$('#amttopay').val("");
		$('#cusId').val("");
	})
	.fail(function(result){
		alert(result);
	})
});
});
$(document).on('click','#update',function(){
	var accId = $('#acc').val();
	var line = $('#line').val();
	var cType = $('#colltype').val();
	var amtGiven = $('#amtgiven').val();
	var amttopay = $('#amttopay').val();
	var cusId = $('#cusId').val();
	if(accId ===""){
		$("#acc").focus().css("outline-color","red");
		return;
	}
	if(line ===""){
		$("#line").focus().css("outline-color","Red");
		return;
	}
	if(cType ===""){
		$("#colltype").focus().css("outline-color","Red");
		return;
	}
	if(amtGiven ===""){
		$("#amtgiven").focus().css("outline-color","Red");
		return;
	}
	if(amttopay ===""){
		$("#amttopay").focus().css("outline-color","Red");
		return;
	}
	if(cusId ===""){
		$("#cusId").focus().css("outline-color","Red");
		return;
	}
	var url = "/Finance/account?operation=updateAccount&accId="+ accId +"&cusId="+ cusId +"&line="+ line +"&collType="+ cType +"&amountGiven="+ amtGiven +"&amountToPay="+amttopay;
	$.ajax({
		url:url,
		type:'POST'
	})
	.done(function(result){
		getAllAccount();
		$('#acc').val("");
		$('#line').val("");
		$('#colltype').val("");
		$('#amtgiven').val("");
		$('#amttopay').val("");
		$('#cusId').val("");
	})
	.fail(function(result){
		alert(result);
	})
});
$(document).on('keyup','#acc',function(){
	var acc = $('#acc').val();
	if(acc !== ""){
		if(acc.length===5){
			var url = "/Finance/account?operation=getOneAccount&accId="+ acc;
			$.ajax({
				url:url,
				type:'POST'
			})
			.done(function(result){
				var res = JSON.parse(result);
					$('#line').val(res.line);
					$('#colltype').val(res.collType);
					$('#amtgiven').val(res.amountGiven);
					$('#amttopay').val(res.amountToPay);
					$('#cusId').val(res.cusId);
			})
			.fail(function(result){
			  alert(result);
			})
		}
		
	}

	
})
function getAllAccount(){
	var url = "/Finance/account?operation=getAllAccount";
	$.ajax({
		url:url,
		type:'POST'
	})
      .done(function(result){
    	  var res = JSON.parse(result);
    	  var length = res.length;
    	  var table = '<table>'
    		  table += '<tr><th>SerialNumber</th><th>AccountNumber</th><th>Line</th><th>CollectionType</th><th>AmountGiven</th><th>AmountT0Pay</th><th>Delete</th></tr>';
    	      for(i=0;i<length;i++){
    	    	  table += '<tr class="row">'
    	    	  table += '<td>'+ res[i].cusId +'</td>';
    	    	  table += '<td>'+ res[i].accId +'</td>';
    	    	  table += '<td>'+ res[i].line +'</td>';
    	    	  table += '<td>'+ res[i].collType +'</td>';
    	    	  table += '<td>'+ res[i].amountGiven +'</td>';
    	    	  table += '<td>'+ res[i].amountToPay +'</td>';
    	    	  table += '<td><img src="images/delete.jpg" height="35px" width="35px" class="delete"></td></tr>';
    	      }
                  table += '</table>';  
                  $('.getAll')[0].innerHTML = table;
      })
	.fail(function(result){
		alert(result);
	})
}
$(document).on('click','.delete',function(){
	var td = $(this).parent();
	var tr = td.parent();
	var accId = tr.children()[0].innerHTML;
	var url = "/Finance/account?operation=deleteCustomer&accId="+ accId;
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
