$(document).ready(function(){
$(document).on('click','#sub',function(){
	var line = $("#line").val();
	var cType = $("#colltype").val();
	var amtGiven = $("#amtgiven").val();
	var amttopay = $("#amttopay").val();
	var cusId = $("#cusId").val();
	if(line ===""){
		$("#line").focus().css("outline-color","Red");
		return false;
	}
	if(cType ===""){
		$("#colltype").focus().css("outline-color","Red");
		return false;
	}
	if(amtGiven ===""){
		$("#amtgiven").focus().css("outline-color","Red");
		return false;
	}
	if(amttopay ===""){
		$("#amttopay").focus().css("outline-color","Red");
		return false;
	}
	if(cusId ===""){
		$("#cusId").focus().css("outline-color","Red");
		return false;
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
		return false;
	}
	if(line ===""){
		$("#line").focus().css("outline-color","Red");
		return false;
	}
	if(cType ===""){
		$("#colltype").focus().css("outline-color","Red");
		return false;
	}
	if(amtGiven ===""){
		$("#amtgiven").focus().css("outline-color","Red");
		return false;
	}
	if(amttopay ===""){
		$("#amttopay").focus().css("outline-color","Red");
		return false;
	}
	if(cusId ===""){
		$("#cusId").focus().css("outline-color","Red");
		return false;
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
		}else{
			$('#line').val("");
			$('#colltype').val("");
			$('#amtgiven').val("");
			$('#amttopay').val("");
			$('#cusId').val("");
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
    		  table += '<tr><th>SerialNumber</th><th>AccountNumber</th><th>Line</th><th>CollectionType</th><th>AmountGiven</th><th>AmountToPay</th><th>Delete</th></tr>';
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
                  $('.getAllAccount')[0].innerHTML = table;
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

	$(document).on('keyup','#accId',function(){
		var accId = $('#accId').val();
		if(accId.length===5){
			var url = "/Finance/account?operation=getStatement&accId="+ accId;
			$.ajax({
				url:url,
				type:'POST'
			})
			.done(function(result){
				res = JSON.parse(result);
				var re = res[0];
				$('#name').text(re.cusName);
				$('#bal').text(re.AmountToPay);
				var length = res.length;
		    	  var table = '<table>'
		    		  table += '<tr><th>SerialNumber</th><th>PaymentDate</th><th>Paid</th><th>Balance</th></tr>';
		    	      for(i=1;i<length;i++){
		    	    	  table += '<tr class="row">'
		    	    	  table += '<td class="sno">'+ i +'</td>';
		    	    	  table += '<td class="date">'+ res[i].Date +'</td>';
		    	    	  table += '<td class="paid">'+ res[i].pay +'</td>';
		    	    	  table += '<td class="bal">'+ res[i].Bal +'</td>';
		    	      }
		                  table += '</table>';  
		                  $('.getStatement')[0].innerHTML = table;
			})
		}
		
	})
	$(document).on('keypress','#acc',function(key){
	        	   if (key.which == 13){
	        		   $('#line').focus();
	        	   }
	        	  
	           })  
	$(document).on('keypress','#line',function(key){
	        if (key.which == 13){
	        		$('#colltype').focus();	  
	        }
}) 
.on('keypress','#colltype',function(key){
	if (key.which == 13){
		$('#amtgiven').focus();
	}
}).on('keypress','#amtgiven',function(key){
	if (key.which == 13){
		$('#amttopay').focus();
	}
}).on('keypress','#amttopay',function(key){
	if (key.which == 13){
		$('#cusId').focus();
	}
}).on('keypress','#cusId',function(key){
	if (key.which == 13){
		$('#sub').click();
	}
}).on('keypress','#acc,#amtgiven,#amttopay,#cusId,#accId',function(e){
	if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
        //display error message
        $("#msg").html("Numbers Only").show().fadeOut(3000);
               return false;
    }
})
function previewStatement() {
    var printWindow = window.open('', '', 'height=400,width=800');
    printWindow.document.write(getStatement());
    printWindow.document.close();
    printWindow.focus();
}

function getStatement() {
    var htmlFile = "";
    htmlFile += '<html><head><title>RECEIPT</title>';
    htmlFile += '</head><body >';
    htmlFile += "<center><h3>SMARTGLOSSA <br>Chennai<br>Website: www.smartglossa.com</h3> </center>";
    htmlFile += '<center><div>';
    htmlFile += '<h4>Name:'+ $("#name").text() +'</h4>';
    htmlFile += '<h4>Outstanding:'+ $("#bal").text() +'</h4>';
    htmlFile += "<table>";
    htmlFile += "<tr><th>Serial Number</th><th>Payment Date</th><th>Paid</th><th>Balance</th></tr>";
    /*var tr = $(".row")[0].children;
    for (var i = 0; i < tr.length; i++) {
        var div = $(tr[i]);
        if ($(div.children(".sno")[0]).val().trim() !== "") {

            var row = "<tr><td>" + $(div.children(".sno")[0].val() + "</td>"
            row += "<td>" + $(div.children(".date")[0]).val() + "</td>";
            row += "<td>" + $(div.children(".paid")[0]).val() + "</td>";
            row += "<td>" + $(div.children(".bal")[0]).val() + "</td>";
            row += "</tr>";
            htmlFile += row;
        }
    }
    htmlFile += row;
    htmlFile += "";*/
    htmlFile += "</table></div></center>";
    htmlFile += "<center><input type='submit' value='PRINT' onclick='window.print()'>";
    htmlFile += "<input type='submit' value='CANCEL' onclick='window.close()'></center>";
    htmlFile += '</body></html>';
    return htmlFile;
}
	
	


