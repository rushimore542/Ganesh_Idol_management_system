
var base64String = "";
var imageBase64Stringsep=""; 
function imageUploaded() {
	var file = document.querySelector(
		'input[type=file]')['files'][0];

	var reader = new FileReader();
	console.log("next");
	
	reader.onload = function () {
		base64String = reader.result.replace("data:", "")
			.replace(/^.+,/, "");

		imageBase64Stringsep = base64String;

		//alert(imageBase64Stringsep);
		console.log(base64String);
		
		var type =document.getElementById("sel1").value;
		var colour =document.getElementById("sel2").value;
		var sizeOfIdol =document.getElementById("sel3").value;
		
		var content1 =document.getElementById("check1");
		var content2 =document.getElementById("check2");
		var content3 =document.getElementById("check3");
		
		var content="";
		
		if(content1.checked == true){
			content = document.getElementById("check1").value;
		}else if(content2.checked == true){
			content = document.getElementById("check2").value;
		}else if(content3.checked == true){
			content = document.getElementById("check3").value;
		}else if(content1.checked == true && content2.checked == true) {
			content = document.getElementById("check1").value + "," + document.getElementById("check2").value;
		}else if(content2.checked == true && content3.checked == true) {
			content = document.getElementById("check2").value + "," + document.getElementById("check3").value;
		}else if(content1.checked == true && content3.checked == true) {
			content = document.getElementById("check1").value + "," + document.getElementById("check3").value;
		}else if(content1.checked == true && content2.checked == true && content3.checked == true) {
			content = document.getElementById("check1").value + "," + document.getElementById("check2").value+ "," + document.getElementById("check2").value;
		}
		
		var price =document.getElementById("price").value;
		
		var jsonObj = new Object();
		jsonObj.type = type;
		jsonObj.colour = colour;
		jsonObj.sizeOfIdol = sizeOfIdol;
		jsonObj.content = content;
		jsonObj.price = price;
		jsonObj.base64String1 = imageBase64Stringsep;
		
		var jsonString= JSON.stringify(jsonObj);
		
		alert(jsonString);
		
		var xhttp = new XMLHttpRequest();
// xhttp.onreadystatechange = function() {
   //  if (this.readyState == 4 && this.status == 200) {
    	// if(!(this.responseText==="QR NOT CREATED")){
    	 //srcc="data:image/png;base64,"+this.responseText;
    	 //document.getElementById('imgshow').src=srcc;
    	// }else {
        //	 document.getElementById('detailsnew').innerHTML = this.responseText;

    	// }
    // }
// };
xhttp.open("POST", "./AddIdol", true);
xhttp.setRequestHeader("Content-type", "application/json");
xhttp.send(jsonString);
	}
	reader.readAsDataURL(file);

}

function displayString() {
	console.log("Base64String about to be printed");
}


