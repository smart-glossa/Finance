$(document).ready(function(){
	if(getCookie("uname") != undefined){
		window.location.href = "home.html";
	}
});
	$(document).on('click','#adduser',function(){
		var name = $('#name').val();
		var uname = $('#uname').val();
		var pass = $('#pass').val();
		var address = $('#address').val();
		var mobileNo = $('#mobileNo').val();
		if(name===""){
			$('#name').focus().css('outline-color','red');
			return false;
		}
		if(uname===""){
			$('#uname').focus().css('outline-color','red');
			return false;
		}
		if(pass===""){
			$('#pass').focus().css('outline-color','red');
			return false;
		}
		if(address===""){
			$('#address').focus().css('outline-color','red');
			return false;
		}
		if(mobileNo===""){
			$('#mobileNo').focus().css('outline-color','red');
			return false;
		}
		if (mobileNo.charAt(0)!="7" && mobileNo.charAt(0)!="8" && mobileNo.charAt(0)!="9"){
			$('#mobileNo').focus().css('outline-color','red');
	    	//$("#msg").html("Enter Phone Number as correct format").show().fadeOut(3000);
		    return false;
		}
		var url = "http://localhost:8080/Finance/user?operation=addUser&name=" + name +"&uname="+ uname +"&pass="+ pass +"&address="+ address +"&mobileNumber=" + mobileNo;
		$.ajax({
			url:url,
			type:'POST'
		})
		.done(function(result){
			var res = JSON.parse(result);
			if (res.status == 1){
				alert("Added Sussfully");
			}
			$('#name').val("");
			$('#uname').val("");
			$('#pass').val("");
			$('#address').val("");
			$('#mobileNo').val("");
		})
		.fail(function(result){
			alert(result);
		})
	}).on('click','#login',function(){
		var uname = $('#user').val();
		var pass = $('#passw').val();
		if(uname===""){
			$('#user').focus().css('outline-color','red');
			return false;
		}
		if(pass===""){
			$('#passw').focus().css('outline-color','red');
			return false;
		}
		var url = "http://localhost:8080/Finance/user?operation=login&user="+ uname +"&passw="+ pass;
		$.ajax({
			url:url,
			type:'POST'
		})
		.done(function(result){
			//alert(result);
			var res = JSON.parse(result);
			if(res.status == 1){
				document.cookie = "uname="+ uname;
				window.location.href = "home.html";
			}
		})
		.fail(function(result){
			alert(result);
		})
	})
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for (var i = 0; i < ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return undefined;
}
	$(document).on('keypress','#mobileNo',function(e){
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57) && e.which != 13) {
	        //display error message
	       // $("#msg").html("Numbers Only").show().fadeOut(3000);
	               return false;
	    }
	})
	.on('keypress','#name,#uname,#address,#user',function(event){
	var inputValue = event.which;
    // allow letters and whitespaces only.
    if(!(inputValue >= 65 && inputValue <= 122) && (inputValue != 32 && inputValue != 0 && inputValue != 13)) { 
       // $("#msg").html("Alphabets only").show().fadeOut(3000);
        event.preventDefault(); 
    }
})
