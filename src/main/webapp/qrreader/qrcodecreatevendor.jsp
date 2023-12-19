<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>

  <link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Rubik&display=swap" rel="stylesheet">

<title>Insert title here</title>
</head>
<body onload="updateVendorList()">
    <h4 style="text-align:center;margin-top:50px">QR Generation</h4>

<br>
<br>

 <div class="container">
  <div class="row">
    <div class="col text-center">
       <h2>Select Vendor Id</h2>

    <select id="vendorlistdata" name="vendorlist">
        <option value="none" selected disabled hidden>Select Vendor</option> 
    
        <c:forEach items="${vendorList}" var="vendor">
            <option value="${vendor.vid}">${vendor.name}</option>
        </c:forEach>
    </select>
   </div> 
    <br/><br/>
    <div class="col text-center">
    <h2>Select Order Number</h2>
 	<select id="orderlistdata" name="orderlist">
 	        <option value="none" selected disabled hidden>Select an Option</option> 
 	
    </select>
    </div>
       
    </div>
  </div>
</div>
<div >
    
   </div> 
  
    
        <img alt="" src="" id="imgshow" class="rounded mx-auto d-block">
    <div class="col text-center">
        <button class="btn btn-primary"onclick="window.print()">Print QR
</button>
    </div>
       
    
    <div id="details">
    </div>
    
     <div id="detailsnew ">
     
    </div>
    
</body>
 <script language="javascript">
 function updateVendorList() {
	    var xhttp = new XMLHttpRequest();
	    xhttp.onreadystatechange = function() {
	         if (this.readyState == 4 && this.status == 200) {
	        	 document.getElementById('vendorlistdata').innerHTML = this.responseText;
	             alert(this.responseText+"hi sagar");
	         }
	    };
	    xhttp.open("POST", "<%=request.getContextPath()%>/createQRcode", true);
	    xhttp.setRequestHeader("Content-type", "application/json");
	    xhttp.send();
	}
 
 let vendorchangeevent = document.querySelector("#vendorlistdata");
 let orderdata = document.querySelector("#orderlistdata");

	 vendorchangeevent.addEventListener("change", function() {
		  alert("change occured");
		  var xhttp = new XMLHttpRequest();
		    xhttp.onreadystatechange = function() {
		         if (this.readyState == 4 && this.status == 200) {
		        	 const data = JSON.parse(this.responseText);
		        	    let option;
		        	    for (let i = 0; i < data.length; i++) {
		        	  
		        	      option = document.createElement('option');
		        	      option.text = data[i].oid;
		        	      orderdata.add(option,i);
		        	    }
		        	 
		         }
		    };
		    xhttp.open("POST", "<%=request.getContextPath()%>/getOrdersByVendorServlet?vid="+vendorchangeevent.value, true);
		    xhttp.setRequestHeader("Content-type", "application/json");
		    xhttp.send();
		});
	 

	 document.querySelector("#orderlistdata").addEventListener("change", function() {
			  alert("change occured 2");
			  var xhttp = new XMLHttpRequest();
			    xhttp.onreadystatechange = function() {
			         if (this.readyState == 4 && this.status == 200) {
			        	 document.getElementById('details').innerHTML = this.responseText;

			         }
			    };
			    xhttp.open("POST", "<%=request.getContextPath()%>/QRdataFetch", true);
			    xhttp.setRequestHeader("Content-type", "application/json");
			    xhttp.send(orderdata.value);
			});
	 
	 function getQR(orderid){
		 alert("change occured 3");
		  var xhttp = new XMLHttpRequest();
		    xhttp.onreadystatechange = function() {
		         if (this.readyState == 4 && this.status == 200) {
		        	 if(!(this.responseText==="QR NOT CREATED")){
		        	 srcc="data:image/png;base64,"+this.responseText;
		        	 document.getElementById('imgshow').src=srcc;
		        	 }else {
			        	 document.getElementById('detailsnew').innerHTML = this.responseText;
 
		        	 }

		         }
		    };
		    xhttp.open("POST", "<%=request.getContextPath()%>/QRCodeCreator?oid="+orderdata.value, true);
		    xhttp.setRequestHeader("Content-type", "application/json");
		    xhttp.send();
	 }
	
    </script>
</html>