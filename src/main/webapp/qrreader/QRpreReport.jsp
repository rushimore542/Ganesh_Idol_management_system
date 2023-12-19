<%@page import="ganeshmurthi.model.OrderBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
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
   <body>
   <div  style="margin-top:30px;margin-left:30px;margin-right:30px; margin-bottom:30px">
   <h2 style="text-align:center">Customer Details</h2>
      <table class="table table-bordered table-hover table-primary">
         <thead>
            <tr>
               <th>OID</th>
               <th>VID</th>
               <th>CID</th>
               <th>QRID</th>
               <th>CUSTOMER NAME</th>
               <th>CUSTOMER SIRNAME</th>
               <th>CUSTOMER MOBILE NUMBER</th>
               <th>CUSTOMER EMAIL</th>
               <th> CUSTOMER ADDRESS</th>
             
            </tr>
         </thead>
         <tbody>
            <tr>
               <td>${orderData.oid} </td>
               <td>${orderData.vid}</td>
               <td>${orderData.cid}</td>
               <td>${orderData.qrid}</td>
               <td>${customerData.name}</td>
               <td>${customerData.sirname}</td>
               <td>${customerData.mobile}</td>
               <td>${customerData.email}</td>
               <td>${customerData.address}</td>
              
              
            </tr>
         </tbody>
      </table>
      <br>
            <br>
             <h2 style="text-align:center">Vendor Details</h2>
            <table class="table table-bordered table-hover table-primary">
            <thead>
            	<tr>
            	  <th>VENDOR NAME</th>
               <th>VENDOR SIRNAME</th>
               <th>VENDOR MOBILE NUMBER</th>
               <th>VENDOR EMAIL</th>
               <th> VENDOR ADDRESS</th>
            	</tr>
            </thead>
            <tbody>
            	<tr>
            	 <td>${vendorData.name}</td>
               <td>${vendorData.sirname}</td>
               <td>${vendorData.mobile}</td>
               <td>${vendorData.email}</td>
               <td>${vendorData.address}</td>
            	</tr>
            </tbody>
            </table>
            <br>
            <br>
      
      </div>
      
      <div name="idoldata" style="margin-top:30px;margin-left:30px;margin-right:30px; margin-bottom:30px">
       <h2 style="text-align:center">Idol Details</h2>
      <table class="table table-bordered table-hover table-primary">
         <thead>
            <tr>
              <th> IDOL ID </th>
               <th> IDOL PRICE</th>
               <th> IDOL COLOR</th>
               <th> IDOL SIZE </th>
               <th> IDOL CONTENTS </th>
               <th> IDOL IS DISPOSABLE </th>
               			
               </tr>
               </thead>
                    <tbody>
                        <tr>
                <td>${idolData.idolid}</td>
               <td>${idolData.priceofidol}</td>
               <td>${idolData.colorofidol}</td>
               <td>${idolData.sizeofidol}</td>
               <td>${contentData.contents}</td>
               <td>${idolData.isdisposable}</td>
               
               </tr>
               </tbody>
               </table>
      
      </div>
     
   <div class="container" d="createQR">
  <div class="row">
    <div class="col text-center">
       <button class="btn btn-primary"onclick="getQR(${orderData.oid})">GET QR</button>
    </div>
  </div>
</div>
<br>
      
 
      
   </body>
  
</html>