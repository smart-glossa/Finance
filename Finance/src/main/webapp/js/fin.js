function getAllCustomer(){
	var url = "/Finance/finance?operation=getAllCustomer";
	$.ajax({
		url:url,
		type:'POST'
	})
      .done(function(result){
    	  var res = JSON.parse(result);
    	  var length = res.length;
    	  var table = '<table>'
    		  table += '<tr><th>AccountNumber</th><th>CustomerName</th><th>Line</th><th>Address</th><th>ContactNumber</th><th>CollectionType</th><th>Delete</th></tr>';
    	      for(i=0;i<length;i++){
    	    	  table += '<tr class="row">'
    	    	  table += '<td>'+ res[i].accountNo +'</td>';
    	    	  table += '<td>'+ res[i].customerName +'</td>';
    	    	  table += '<td>'+ res[i].line +'</td>';
    	    	  table += '<td>'+ res[i].address +'</td>';
    	    	  table += '<td>'+ res[i].contactNo +'</td>';
    	    	  table += '<td>'+ res[i].collectionType +'</td>';
    	    	  table += '<td><img src="images/delete.jpg" height="35px" width="35px" class="delete"></td></tr>';
    	      }
                  table += '</table>';  
                  $('.getAll')[0].innerHTML = table;
      })
	.fail(function(result){
		alert(result);
	})
	
      
      
}
