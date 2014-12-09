function callRest(){
	alert("inside callRest");
		/*$.ajax({
		    url : "http://172.25.129.77:7080/RestWS/hexaware/employees/emplist",
		    type: "GET",
		   // data : "19119",
		    //contentType: "application/json",
		    success: function(data, textStatus, jqXHR)
		    {
		    	console.log("hello");
		        //data - response from server
		        alert("get");
		    },
		    error: function (jqXHR, textStatus, errorThrown)
		    {
		    	console.log("hello");
		    	alert("textStatus")
		
		    }
		});*/
	
	 $.get("http://172.25.129.77:7080/RestWS/hexaware/employees/emplist",function(data,status){
		   // alert("Data: " +  + "\nStatus: " + status);
		 console.log("Data: " +  + "\nStatus: " + status);
		  });
	
} 