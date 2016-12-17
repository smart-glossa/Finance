$(document).ready(function(){
	if(getCookie("uname") != undefined){
		window.location.href = "home.html";
	}
});
	$(document).on('click','#adduser',function(){
		var uname = $('#uname').val();
		var pass = $('#pass').val();
		if(uname===""){
			$('#uname').focus().css('outline-color','red');
			return false;
		}
		if(pass===""){
			$('#pass').focus().css('outline-color','red');
			return false;
		}
		var url = "http://localhost:8080/Finance/user?operation=addUser&uname="+ uname +"&pass="+ pass;
		$.ajax({
			url:url,
			type:'POST'
		})
		.done(function(result){
			alert(result);
			$('#uname').val("");
			$('#pass').val("");
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
			if(res.status == "success"){
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
