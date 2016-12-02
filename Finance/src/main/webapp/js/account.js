$(document).ready(function(){
$("#sub").click(function(){
    var cusId = $("#cusId").val();
	var line = $("#line").val();
	var cType = $("#colltype").val();
	var amtGiven = $("#amtgiven").val();
	var amttopay = $("#amtToPay").val();
	var url =" http://localhost:8080/Finance/account?operation=addAccounts&line="+line+"&collType="+cType+"&amountGiven="+amtGiven+"&amountToPay="+amttopay+"&cusId="+cusId ;
	$.ajax({
		url:url,
		typ:'POST'
	})
	.done(function(result){
		alert(result)		
	})
	.fail(function(result){
		alert(result);
	})
});
});
$(document).on('click','#update',function(){
	var accId = $('#acc').val();
	var cusId = $('#line').val();
	var cType = $('#colltype').val();
	var amtGiven = $('#amtgiven').val();
	var amttopay = $('#amtToPay').val();
	var url = "/Finance/account?operation=updateAccounts&accId="+ accId +"&cusId="+ cusId +"&line="+ line +"&collType="+ cType +"&amountGiven="+ amtGiven +"&amoutToPay="+amttopay;
	$.ajax({
		url:url,
		type:'POST'
	})
	.done(function(result){
		alert(result)
	})
	.fail(function(result){
		alert(result);
	})
});
$(document).on('keyup','#acc',function(){
	var acc = $('#acc').val();
	if(acc !== ""){
		if(acc.length===5){
			var url = "/Finance/account?operation=getOne&accId="+ acc;
			$.ajax({
				url:url,
				type:'POST'
			})
			.done(function(result){
				var res = JSON.parse(result);
					$('#accId').val(res.accId);
					$('#line').val(res.line);
					$('#colltype').val(res.collType);
					$('#amtgiven').val(res.amountGiven);
					$('#amtToPay').val(res.amountToPay);
					$('#cusId').val(res.cusId);
			})
			.fail(function(result){
			  alert(result);
			})
		}
		
	}

	
})