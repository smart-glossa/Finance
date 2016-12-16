$(document).ready(function(){
})
$(document).on('click','#addpay',function(){
	var  payment = $('#payment').val();
	var  accId = $('#accId').val();
	var  paydate = $('#paydate').val();
	if(payment ===""){
		$('#payment').focus().css('outline-color','red');
		return;
	}
	if(acccId ===""){
		$('#accId').focus().css('outline-color','red');
		return;
	}
	if(paydate ===""){
		$('#paydate').focus().css('outline-color','red');
		return;
	}
	var url= "/Finance/payment?operation=addPayment&payment=" + payment +"&accId=" + accId +"&paydate=" + paydate;
	$.ajax({
		url:url,
		type:'POST'
	})
	.done(function(result){
		getAllPayment();
		$('#payment').val("");
		$('#accId').val("");
		$('#paydate').val("");
	})
	.fail(function(result){
		alert(result);
	})
});
function getAllPayment(){
	var url = "/Finance/payment?operation=getAllPayment";
	$.ajax({
		url:url,
	type:'POST'
	})
	.done(function(result){
		var res = JSON.parse(result);
		var length = res.length;
		var table = '<table>'
			table += '<tr><th>SerialNumber</th><th>Payment</th><th>Account Id</th><th>PayDate</th></tr>'
				for(i=0;i<length;i++){
					table += '<tr class="row">'
					table += '<td>' + res[i].pId +'</td>';	
				    table += '<td>' + res[i].accId +'</td>';
					table += '<td>' + res[i].amount +'</td>';
					table += '<td>' + res[i]. date +'</td>';
	    	    	table += '<td><img src="images/delete.jpg" height="35px" width="35px" class="delete"></td></tr>';

				}
					table += '</table>';  
	                  $('.getAllPayment')[0].innerHTML = table;
				
	      })
		.fail(function(result){
			alert(result);
		})
}
/*$(document).on('click','#updatepay',function(){
	var  accId = $('#accId').val();
	var  amount = $('#amount').val();
	var  date = $('#date').val();
	if(payId ===""){
		$('#payId').focus().css('outline-color','red');
		return;
	}
	if(accId ===""){
		$('#accId').focus().css('outline-color','red');
		return;
	}
	if(amount ===""){
		$('#amount').focus().css('outline-color','red');
		return;
	}
	if(date ===""){
		$('#date').focus().css('outline-color','red');
		return;
	}
	
	var url = "/Finance/payment?operation=updatePayment&payId=" + payId +"&accId=" + accId +"&amount=" + amount +"&date=" + date;
	$.ajax({
		url:url,
		type:'POST'
	})
	.done(function(result){
		getAllPayment();
		$('#payId').val("");
		$('#accId').val("");
		$('#amount').val("");
		$('#date').val("");
	})
	.fail(function(result){
		alert(result);
	})
})
$(document).on('click','.delete',function(){
	var td = $(this).parent();
	var tr = td.parent();
	var payId = tr.children()[0].innerHTML;
	var cusId = tr.children()[0].innerHTML;
	var url = "/Finance/payment?operation=deletePayment&payId="+ payId;
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
$(document).on('keyup','#payId',function(){
	var payId = $('#payId').val();
	if(payId !== ""){
			var url = "/Finance/payment?operation=getOnePayment&payId="+ payId;
			$.ajax({
				url:url,
				type:'POST'
			})
			.done(function(result){
				var res = JSON.parse(result);
					$('#amount').val(res.amount);
					$('#date').val(res.date);
					$('#accId').val(res.accId);
			})
			.fail(function(result){
			  alert(result);
			})
	}else{
		$('#accId').val("");
		$('#amount').val("");
		$('#date').val("");
	}
}).on('keypress','#payId',function(key){
	if (key.which == 13){
		$('#accId').focus();
	}
}).on('keypress','#accId',function(key){
	if (key.which == 13){
		$('#amount').focus();
	}
}).on('keypress','#amount',function(key){
	if (key.which == 13){
		$('#date').focus();
	}
}).on('keypress','#date',function(key){
	if (key.which == 13){
		$('#addpay').click();
	}
}).on('keypress','#payId,#accId,#amount',function(e){
	if (e.which != 8 && e.which != 0 && e.which != 191 && (e.which < 48 || e.which > 57)) {
        //display error message
        $("#msg").html("Numbers Only").show().fadeOut(3000);
               return false;
    }
})*/
