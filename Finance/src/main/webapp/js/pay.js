$(document).ready(function(){
	getAllPayment();
})
$(document).on('click','#addpay',function(){
	var  accId = $('#accId').val();
	var  amount = $('#amount').val();
	var  date = $('#date').val();
	var url= "/Finance/payment?operation=addPayment&accId=" + accId +"&amount=" + amount +"&date=" + date;
	$.ajax({
		url:url,
		type:'POST'
	})
	.done(function(result){
		alert(result);
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
			table += '<tr><th>Account Id</th><th>Amount</th><th>Date</th></tr>'
				for(i=0;i<length;i++){
					table += '<tr class="row">'
				    table += '<td>' + res[i].accId +'</td>';
					table += '<td>' + res[i].amount +'</td>';
					table += '<td>' + res[i]. date +'</td>';
				}
					table += '</table>';  
	                  $('.getAll')[0].innerHTML = table;
				
	      })
		.fail(function(result){
			alert(result);
		})
}
$(document).on('click','#updatepay',function(){
	var payId = $('#payId').val();
	vay accId = $('#accId').val();
	var amount = $('#amount').val();
	var date = $('date').val();
	var url = "/Finance/payment?operation=updatePayment&payId=" + payId +"&accId=" + accId+"&amount=" + amount +"&date=" + date ; 
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
$(document).on('click','.delete',function(){
	var td = $(this).parent();
	var tr = td.parent();
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
	if(payId !== "" ){
		
			var url = "/Finance/payment?operation=getOnePayment&payId="+ payId;
			$.ajax({
				url:url,
				type:'POST'
			})
			.done(function(result){
				var res = JSON.parse(result);
				$('#accId').val(res.accId);
				$('#amount').val(res.amount);
				$('#date').val(res.date);
			})
			.fail(function(result){
				alert(result);
			})
	}
});
	}