$(document).ready(function() {
  //Adding accounts file
  $(document).on('click', '#addAcc', function() {
    var accNo = $('#accNo').val();
    var line = $('#line').val();
    var duration = $('#duration').val();
    var modeofpay = $('#modeofpay').val();
    var amtGiven = $('#amtgiven').val();
    var amttopay = $('#amttopay').val();
    var date = $('#date').val();
    var currentAccount = $('#currentacc').val("");
    var amtG = parseInt(amtGiven);
    var amtP = parseInt(amttopay);
    if (accNo === "") {
      $('#accNo').focus().css("outline-color", "red");
      return false;
    }
    if (line === "") {
      $("#line").focus().css("outline-color", "Red");
      return false;
    }
    if (duration === "") {
      $("#duration").focus().css("outline-color", "Red");
      return false;
    }
    if (amtGiven === "") {
      $("#amtgiven").focus().css("outline-color", "Red");
      return false;
    }
    if (modeofpay === "") {
      $("#modeofpay").focus().css("outline-color", "red");
      return false;
    }
    if (amttopay === "") {
      $("#amttopay").focus().css("outline-color", "Red");
      return false;
    }
    if (date === "") {
      $("#date").focus().css("outline-color", "red");
      return false;
    }
    if (currentAccount === "") {
      $('#currentacc').focus.css("outline-color", "red");
      return false;
    }
    if (amtG >= amtP) {
      $("#amttopay").focus().css("outline-color", "Red");
      $("#msg").html("PayAmount should be greater than GivenAmount").show().fadeOut(3000);
      return false;
    }
    var url = " http://localhost:8080/Finance/account?operation=addAccounts&accNo=" + accNo + "&line=" + line + "&duration=" + duration + "&modeOfPayment=" + modeofpay + "&amountGiven=" + amtGiven + "&amountToPay=" + amttopay + "&date=" + date + "&currentAccount=" + currentAccount;
    $.ajax({
        url: url,
        typ: 'POST'
      })
      .done(function(result) {
    	  var res = JSON.parse(result);
			if (res.status == 1){
				alert("Added Sussfully");
			}
        $('#accNo').val("");
        $('#line').val("");
        $('#duration').val("");
        $('#modeofpay').val("");
        $('#amtgiven').val("");
        $('#amttopay').val("");
        $('#date').val("");
        $('#currentacc').val("");
      })
      .fail(function(result) {
        alert(result);
      })
  });
});
$(document).on('click', '#updateAcc', function() {
  var accNo = $('#accNo').val();
  var line = $('#line').val();
  var duration = $('#duration').val();
  var modeofpay = $('modeofpay').val();
  var amtGiven = $('#amtgiven').val();
  var amttopay = $('#amttopay').val();
  var date = $('date').val();
  var currentAccount = $('#currentacc').val();
  var amtG = parseInt(amtGiven);
  var amtP = parseInt(amttopay);
  if (accNo === "") {
    $("#acc").focus().css("outline-color", "red");
    return false;
  }
  if (line === "") {
    $("#line").focus().css("outline-color", "Red");
    return false;
  }
  if (duration === "") {
    $("#duration").focus().css("outline-color", "Red");
    return false;
  }
  if (modeofpay === "") {
    $('#modeofpay').focus().css("outline-color", "red");
    return false;
  }
  if (amtGiven === "") {
    $("#amtgiven").focus().css("outline-color", "Red");
    return false;
  }
  if (amttopay === "") {
    $("#amttopay").focus().css("outline-color", "Red");
    return false;
  }
  if (date === "") {
    $("#date").focus().css("outline-color", "Red");
    return false;
  }
  if (amtG >= amtP) {
    $("#amttopay").focus().css("outline-color", "Red");
    $("#msg").html("AmountToPay should be greater than AmountGiven").show().fadeOut(3000);
    return false;
  }
  var url = "/Finance/account?operation=updateAccount&accNo=" + acc + "&line=" + line + "&duration=" + duration + "modeofpayment=" + modeofpay + "&amountGiven=" + amtGiven + "&amountToPay=" + amttopay + "&date=" + date +"&currentAccount=" + currentAccount;
  $.ajax({
      url: url,
      type: 'POST'
    })
    .done(function(result) {
      getAllAccount();
      $('#acc').val("");
      $('#line').val("");
      $('#duration').val("");
      $('#modeofpay').val("");
      $('#amtgiven').val("");
      $('#amttopay').val("");
      $('#date').val("");
      $('#currentacc').val("");
    })
    .fail(function(result) {
      alert(result);
    })
});
$(document).on('keyup', '#accId', function() {
  var accId = $('#accId').val();
  if (accId !== "") {
    if (accId.length === 1) {
      var url = "/Finance/account?operation=getOneAccount&accId=" + accId;
      $.ajax({
          url: url,
          type: 'POST'
        })
        .done(function(result) {
          var res = JSON.parse(result);
          $('#accNo').val(res.accNo);
          $('#line').val(res.line);
          $('#duration').val(res.duration);
          $('#modeofpay').val(res.modeofpay)
          $('#amtgiven').val(res.amountGiven);
          $('#amttopay').val(res.amountToPay);
          $('#date').val(res.date);
          $('#currentacc').val(res.currentAccount);
        })
        .fail(function(result) {
          alert(result);
        })
    }
  }else {
      $('#accNo').val("");
      $('#line').val("");
      $('#duration').val("");
      $('#modeofpay').val("");
      $('#amtgiven').val("");
      $('#amttopay').val("");
      $('#date').val("");
      $('#currentacc').val("");
    }
})
function getAllAccount() {
  var url = "/Finance/account?operation=getAllAccount";
  $.ajax({
      url: url,
      type: 'POST'
    })
    .done(function(result) {
      var res = JSON.parse(result);
      var length = res.length;
      var table = '<table>'
      table += '<tr><th>AccountId</th><th>AccountNumber</th><th>Line</th><th>Duration</th><th>Mode Of Pay</th><th>AmountGiven</th><th>AmountToPay</th><th>date</th><th>currentAccount</th><th>Delete</th></tr>';
      for (i = 0; i < length; i++) {
        table += '<tr class="row">'
        table += '<td>' + res[i].acc + '</td>';
        table += '<td>' + res[i].line + '</td>';
        table += '<td>' + res[i].duration + '</td>';
        table = +'<td>' + res[i].modeofpay + '<td>';
        table += '<td>' + res[i].amountGiven + '</td>';
        table += '<td>' + res[i].amountToPay + '</td>';
        table += '<td>' + res[i].date + '</td>';
        table += '<td>' + res[i].currentAccount + '</td>';
        table += '<td><img src="images/delete.jpg" height="35px" width="35px" class="delete"></td></tr>';
      }
      table += '</table>';
      $('.getAllAccount')[0].innerHTML = table;
    })
    .fail(function(result) {
      alert(result);
    })
}
$(document).on('click', '.delete', function() {
  var td = $(this).parent();
  var tr = td.parent();
  var accId = tr.children()[0].innerHTML;
  var url = "/Finance/account?operation=deleteCustomer&acc=" + accNo;
  $.ajax({
      url: url,
      type: 'POST'
    })
    .done(function(result) {
      tr.remove();
    });
  fail(function(result) {
    alert(result);
  })
});

/*$(document).on('keyup','#accId',function(){
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

})*/
$(document).on('keypress', '#acc', function(key) {
  if (key.which == 13) {
    $('#line').focus();
  }

})
$(document).on('keypress', '#line', function(key) {
    if (key.which == 13) {
      $('#duration').focus();
    }
  })
  .on('keypress', '#duration', function(key) {
    if (key.which == 13) {
      $('#modeofpay')
    }
  })
  .on('keypress', '#modeofpay', function(key) {
    if (key.which == 13) {
      $('#amtgiven').focus();
    }
  }).on('keypress', '#amtgiven', function(key) {
    if (key.which == 13) {
      $('#amttopay').focus();
    }
  }).on('keypress', '#amttopay', function(key) {
    if (key.which == 13) {
      $('#date').focus();
    }
  }).on('keypress', '#date', function(key) {
    if (key.which == 13) {
      $('#currentacc').focus();
    }
  }).on('keypress', '#currentacc', function(key) {
    if (key.which == 13) {}
  })
  .on('keypress', '#accNo,#amtgiven,#amttopay', function(e) {
    if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57) && e.which != 13) {
      //display error message
      $("#msg").html("Numbers Only").show().fadeOut(3000);
      return false;
    }
  })
  /*function previewStatement() {
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
      htmlFile += "";
      htmlFile += "</table></div></center>";
      htmlFile += "<center><input type='submit' value='PRINT' onclick='window.print()'>";
      htmlFile += "<input type='submit' value='CANCEL' onclick='window.close()'></center>";
      htmlFile += '</body></html>';
      return htmlFile;
      }*/
