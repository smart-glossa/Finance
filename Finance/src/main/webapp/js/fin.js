function customer(){
	var strVar="";
	strVar += "<div class=\"cus\">";
	strVar += "";
	strVar += "		<div>";
	strVar += "			<h2>Add Customer<\/h2>";
	strVar += "		<\/div>";
	strVar += "      <div>";
	strVar += "          <label>Customer Id*:<\/label>";
	strVar += "         <\/div>";
	strVar += "       <div>"
	strVar += "		<input type=\"text\" id=\"cusId\" placeholder=\"Id\"height:\">";
	strVar += "        <\/div>"
	strVar += "		<div>";
	strVar += "			<label>Customer Name*:<\/label>";
	strVar += "		<\/div>";
	strVar += "		<div>";
	strVar += "			<input type=text id=\"cusname\" placeholder=\"Name\"height:\">";
	strVar += "		<\/div>";
	strVar += "		";
	strVar += "		<div>";
	strVar += "			<label>Address*:<\/label>";
	strVar += "		<\/div>";
	strVar += "		<div>";
	strVar += "			<input type=text id=\"addr\" placeholder=\"Address\">";
	strVar += "		<\/div>";
	strVar += "		<div>";
	strVar += "			<label>Phone Number*:<\/label>";
	strVar += "		<\/div>";
	strVar += "		<div>";
	strVar += "			<input type=text id=\"phoneNo\" placeholder=\"Mobile Number\" maxlength=\"10\">";
	strVar += "		<\/div>";
	strVar += "      <div>";
	strVar += "          <label>Landline Number*:<\/label>";
	strVar += "         </div>";
	strVar += "		<div>";
	strVar += "			<input type=text id=\"landline\" placeholder=\"Landline\" maxlength=\"10\">";
	strVar += "		<\/div>";
	strVar += "		<div>";
	strVar += "			<input type=submit value=\"Add\" id=\"add\" a href=\"account()\">";
	strVar += "			<input type=submit value=\"Update\" id=\"updateCus\">";
	strVar += "		<\/div>";
	strVar += "";
	strVar += "	<\/div>";
	strVar += "	<div class=\"getAllCus\"><\/div>";
$('.fin')[0].innerHTML = strVar;
}
function account(){
	var acc="";
	acc += "<div class=\"account\">";
	acc += "<div>";
	acc += "<h3>AddAccounts<\/h3>";
	acc += "<\/div>";
	acc += "<div> ";
	acc += "<label>Account Number:<\/label>";
	acc += "<\/div>";
	acc += "<div>";
	acc += "<input type=text id=acc>";
	acc += "<\/div>";
	acc += "<div> ";
	acc += "<label>Line:<\/label> ";
	acc += "<\/div>";
	acc += "<div>";
	acc += "<select id=line>";
	acc += "<option><\/option>";
	acc += "<option>1<\/option>";
	acc += "<option>2<\/option>";
	acc += "<option>3<\/option>";
	acc += "<\/select>";
	acc += "<\/div>";
	acc += "<div>";
	acc += "<label>Duration:<\/label>";
	acc += "<\/div>";
	acc += "<div>";
	acc += "<select id=duration>";
	acc += "<option>60days<\/option>";
	acc += "<option>100days<\/option>";
	acc += "<option>10days<\/option>";
	acc += "<\/select>";
	acc += "<\/div>";
	acc += "<label>Mode Of Pay:<\/label>";
	acc += "<\/div>";
	acc += "<div>";
	acc += "<select id=modeofpay>";
	acc += "<option><\/option>";
	acc += "<option>daily<\/option>";
	acc += "<option>weekly<\/option>";
	acc += "<\/select>";
	acc += "<\/div>";
	acc += "<div>";
	acc += "<label>Amount Given:<\/label>";
	acc += "<\/div>";
	acc += "<div>";
	acc += "<input type=text id=amtgiven placeholder=\"Amount Given\">";
	acc += "<\/div>";
	acc += "<div>";
	acc += "<label>Amount To Pay:<\/label>";
	acc += "<\/div>";
	acc += "<div>";
	acc += "<input type=text id=amttopay placeholder=\"Amount To Pay\">";
	acc += "<\/div>";
	acc += "<div> ";
	acc += "<label>Date:<\/label> ";
	acc += "<\/div>";
	acc += "<div>";
	acc += "<input type=date id=date placeholder=\"customer Id\">";
	acc += "<\/div>";
	acc += "<div>";
	acc += "<input type=submit value=Add id=sub>";
	acc += "<input type=submit value=Update id=updateAcc>";
	acc += "<\/div>";
	acc += "<div class=getAllAccount><\/div>";
$('.fin')[0].innerHTML = acc;
}
function payment(){
	var pay="";
	pay += "<div class=pay>";
	pay += "<div>";
	pay += "<h3>AddPayment<\/h3>";
	pay += "<\/div>";
	pay += "<input type=text id=payId>";
	pay += "<div> ";
	pay += "<label>Account Number:<\/label> ";
	pay += "<\/div>";
	pay += "<div>";
	pay += "<input type=\"text\" id=\"accId\" placeholder=\"Account Number\">";
	pay += "<\/div>";
	pay += "<div>";
	pay += "<label>Amount:<\/label>";
	pay += "<\/div>";
	pay += "<div>";
	pay += "<input type=\"text\" id=\"amount\" placeholder=\"Paid Amount\">";
	pay += "<\/div>";
	pay += "<div>";
	pay += "<label>Date:<\/label>";
	pay += "<\/div>";
	pay += "<div>";
	pay += "<input placeholder=\"Date\" class=\"textbox-n\" type=\"text\" onfocus=\"(this.type='date')\"  id=\"date\"> ";
	pay += "<\/div>";
	pay += "<div>";
	pay += "<input type=\"submit\" value=\"Add\" id=\"addpay\">";
	pay += "<input type=\"submit\" value=\"Update\" id=\"updatepay\">";
	pay += "<\/div>";
	pay += "<\/div>";
	pay += "<div class=\"getAllPayment\"><\/div>";
$('.fin')[0].innerHTML = pay;
}
function statement(){
	var sta="";
	sta += "<div class=sta>";
	sta += "<div>";
	sta += "<label>AccountNumber:<\/label>";
	sta += "<\/div>";
	sta += "<div>";
	sta += "<input type=text id=accId placeholder=\"Account Number\">";
	sta += "<\/div>";
	sta += "<h4 id=name><\/h4>";
	sta += "<h4 id=bal><\/h4>";
	sta += "<div class=getStatement><\/div>";
	sta += "<input type=button id=print value=Print onclick=previewStatement()>";
	sta += "<\/div>";
	$('.fin')[0].innerHTML = sta;
}
//Adding js file