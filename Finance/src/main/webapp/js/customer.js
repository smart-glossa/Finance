$(document).ready(function() {
  $('#cus').click();
  $(document).on("click", "#logout", function() {
    //postToServer("logout");
    document.cookie = 'uname=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    window.location.href = 'index.html';
  })
})
$(document).on('click', '#add', function() {
  var cusId = $('#cusId').val();
  var accNo = $('#accNo').val();
  var cname = $('#cusname').val();
  var add = $('#addr').val();
  var pno = $('#phoneNo').val();
  var lno = $('#landline').val();
  var refName = $('#refName').val();
  var refAddress = $('#refAddress').val();
  var refConNo = $('#refConNo').val();
  if (cusId === "") {
    $('#cusId').focus().css('outline-color', 'red');
    return false;
  }
  if (cname === "") {
    $('#cusname').focus().css('outline-color', 'red');
    return false;
  }
  if (add === "") {
    $('#addr').focus().css('outline-color', 'red');
    return false;
  }
  if (pno === "") {
    $('#phoneNo').focus().css('outline-color', 'red');
    return false;
  }

  if (pno.length != 10) {
    $('#phoneNo').focus().css('outline-color', 'red');
    $("#msg").html("Enter Phone Number as correct format").show().fadeOut(3000)
    return false;
  }
  if (pno.charAt(0) != "7" && pno.charAt(0) != "8" && pno.charAt(0) != "9") {
    $('#phoneNo').focus().css('outline-color', 'red');
    $("#msg").html("Enter Phone Number as correct format").show().fadeOut(3000);
    return false;
  }
  var url = "/Finance/finance?operation=addCustomer&cusId=" + cusId + "&cusAccount=" + accNo + "&cname=" + cname + "&add=" + add + "&mno=" + pno + "&lno=" + lno + "&refName=" + refName + "&refAdd=" + refAddress + "&refConNo=" + refConNo;
  $.ajax({
      url: url,
      type: 'POST'
    })
    .done(function(result) {
      getAllCustomer();
      $('#cusId').val("");
      $('#accNo').val("");
      $('#cusname').val("");
      $('#addr').val("");
      $('#phoneNo').val("");
      $('#landline').val("");
      $('#refName').val("");
      $('#refAddress').val("");
      $('#refConNo').val("");
    })
    .fail(function(result) {
      alert(result);
    })

});
$(document).on('click', '.delete', function() {
  var td = $(this).parent();
  var tr = td.parent();
  var cusId = tr.children()[0].innerHTML;
  var url = "/Finance/finance?operation=deleteCustomer&cusId=" + cusId;
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
$(document).on('keyup', '#cusId', function() {
  var cusId = $('#cusId').val();
  if (cusId !== "") {
    var url = "/Finance/finance?operation=getOneCustomer&cusId=" + cusId;
    $.ajax({
        url: url,
        type: 'POST'
      })
      .done(function(result) {
        var res = JSON.parse(result);
        $('#cusname').val(res.cusName);
        $('#accNo').val(res.accNo);
        $('#addr').val(res.address);
        $('#phoneNo').val(res.mobileNo);
        $('#landline').val(res.landLine);
        $('#refName').val(res.referenceName);
        $('#refAddress').val(res.referenceAddress);
        $('#refConNo').val(res.referenceContactNo);
      })
      .fail(function(result) {
        alert(result);
      })
  } else {
    $('#cusname').val("");
    $('#accNo').val("");
    $('#addr').val("");
    $('#phoneNo').val("");
    $('#landline').val("");
    $('#refName').val("");
    $('#refAddress').val("");
    $('#refConNo').val("");
  }
});
$(document).on('click', '#updateCus', function() {
	 var cusId = $('#cusId').val();
	  var accNo = $('#accNo').val();
	  var cname = $('#cusname').val();
	  var add = $('#addr').val();
	  var pno = $('#phoneNo').val();
	  var lno = $('#landline').val();
	  var refName = $('#refName').val();
	  var refAddress = $('#refAddress').val();
	  var refConNo = $('#refConNo').val();
  if (cusId === "") {
    $('#cusId').focus().css('outline-color', 'red');
    return;
  }
  if (cname === "") {
    $('#cusname').focus().css('outline-color', 'red');
    return;
  }
  if (add === "") {
    $('#addr').focus().css('outline-color', 'red');
    return;
  }

  if (pno === "") {
    $('phoneNo').focus().css('outline-color', 'red');
    return;
  }
  if (lno === "") {
    $('landline').focus().css('outline-color', 'red');
    return;
  }
  if (pno.length != 10) {
    $('#phoneNo').focus().css('outline-color', 'red');
    $("#msg").html("Enter Phone Number  as correct format").show().fadeOut(3000);
    return false;
  }
  if (pno.charAt(0) != "7" && pno.charAt(0) != "8" && pno.charAt(0) != "9") {
    $('#conno').focus().css('outline-color', 'red');
    $("#msg").html("Enter Phone Number as correct format").show().fadeOut(3000);
    return false
  }
  if (lno.length != 10) {
    $('#landline').focus().css('outline-color', 'red');
    $("#msg").html("Enter Landline Number  as correct format").show().fadeOut(3000);
    return false;
  }
  if (lno.charAt(0) != "0") {
    $('#conno').focus().css('outline-color', 'red');
    $("#msg").html("Enter Landline Number as correct format").show().fadeOut(3000);
    return false;
  }
  var url = "http://localhost:8080/Finance/finance?operation=updateCustomer&cusId="+cusId+"&cusAccount="+accNo+"&cname="+cname+"&add="+add+"&mno="+pno+"&lno="+lno+"&refName="+refName+"&refAdd="+refAddress+"&refConNo="+refConNo+"";
  $.ajax({
      url: url,
      type: 'POST'
    })
    .done(function(result) {
      getAllCustomer();
      $('#cusname').val("");
      $('#accNo').val("");
      $('#addr').val("");
      $('#phoneNo').val("");
      $('#landline').val("");
      $('#refName').val("");
      $('#refAddress').val("");
      $('#refConNo').val("");
    })
    .fail(function(result) {
      alert(result);
    })
})
function getAllCustomer() {
  var url = "/Finance/finance?operation=getAllCustomer";
  $.ajax({
      url: url,
      type: 'POST'
    })
    .done(function(result) {   
      var res = JSON.parse(result);
      var length = res.length;
      var table = '<table>'
      table += '<tr><th>SerialNumber</th><th>CustomerName</th><th>Address</th><th>PhoneNumber</th><th>LandlineNumber</th><th>Reference Name</th><th>Reference Address</th><th>Reference ContactNumber</th><th>Delete</th></tr>';
      for (i = 0; i < length; i++) {
        table += '<tr class="row">'
        table += '<td>' + res[i].cusId + '</td>';
        table += '<td>' + res[i].cusName + '</td>';
        table += '<td>' + res[i].address + '</td>';
        table += '<td>' + res[i].mobileNo + '</td>';
        table += '<td>' + res[i].landLine + '</td>';
        table += '<td>' + res[i].referenceName + '</td>';
        table += '<td>' + res[i].referenceAddress + '</td>';
        table += '<td>' + res[i].referenceContactNo + '</td>';
        table += '<td><img src="images/delete.jpg" height="35px" width="35px" class="delete"></td></tr>';
      }
      table += '</table>';
      $('.getAllCus')[0].innerHTML = table;
    })
    .fail(function(result) {
      alert(result);
    })
}
$(document).on('keypress', '#cusId', function(key) {
  if (key.which == 13) {
    $('#cusname').focus();
  }
})
$(document).on('keypress', '#cusname', function(key) {
  if (key.which == 13) {
    $('#addr').focus();
  }
  if (key.which == 38) {
    $('#cusId').focus();
  }
})
$(document).on('keypress', '#addr', function(key) {
  if (key.which == 13) {
    $('#phoneNo').focus();
  }
  if (key.which == 38) {
    $('#cusname').focus();
  }
})
$(document).on('keypress', '#phoneNo', function(key) {
  var div = $(this).parent();
  if (key.which == 13) {
    $('#add').click();
  } else if (key.which == 38) {
    div.prev().children('#addr').focus();
  }
})
$(document).on('keypress', '#cusId,#phoneNo,#landline,#refConNo,#cusId,#accNo', function(e) {
  if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
    //display error message
    $("#msg").html("Numbers Only").show().fadeOut(3000);
    return false;
  }

}).on('keypress', '#cusname,#addr,#refName,#refAddress', function(event) {
  var inputValue = event.which;
  // allow letters and whitespaces only.
  if (!(inputValue >= 65 && inputValue <= 122) && (inputValue != 32 && inputValue != 0 && inputValue != 13)) {
    $("#msg").html("Alphabets only").show().fadeOut(3000);
    event.preventDefault();
  }
})
