var rootUrl ="http://localhost:8080/MobileCustomerEJB/rest";
var rootUrl1 ="http://localhost:8080/MobileCustomerEJB";

var findAllUsers=function(){
    $.ajax({type:'GET',
            url:rootUrl + '/users',
            dataType:"json",
            success:renderTableUsers
            });

}




var countUser=function(){
	console.log("Bhagya - countUser");
    $.ajax({type:'GET',
        url:rootUrl + '/users',
        dataType:"json",
        success: renderCards
        //success: function(data, textStatus, jqXHR){
		//	var responseText = jQuery.parseJSON(jqXHR.responseText);
		//	console.log(responseText)			
			
 	//}
});

}

var renderCards=function(users){
    $.each(users, function(index,user){
    	//console.log(hi);
    });
    var count = users.length;
    $('#userHeader').append('<h3> '+count+'</h3>');
    console.log(count);
}

var countMobile=function(){
	console.log("Bhagya ----- countMobiles");
    $.ajax({type:'GET',
        url:rootUrl + '/mobiles',
        dataType:"json",
        success: renderCardMobile
    
});

}

var renderCardMobile=function(mobiles){
    $.each(mobiles, function(index,mobile){
    	console.log('hi');
    });
    var count = mobiles.length;
    $('#totalProductVal').append('<h3> '+count+'</h3>');
    console.log(count);
}



var findAllU=function(){
    $.ajax({type:'GET',
        url:rootUrl + '/users',
        dataType:"json",
        success:renderListUsers});

}

var renderListUsers=function(users){
	$('ul').empty();
    $.each(users, function(index,user){
    	
    $('#userList').append('<li><a href="#" uid="'+user.uid+'">'+user.nameu+'</a></li>');
    });

}


var rendersucessfulLogin = function(){
	 $("#submitlogin").click(function(){
	        $("#myModal").modal("hide");
	        $("#submitlogin").click(function(){
	            $("#myModal").modal({show: false});
	          });
	    });
	
}





var findByIdUsers=function(uid) {
	console.log('findByIdUsers: ' + uid)
	$.ajax({
		type:'GET',
		url :rootUrl +'/users/'+uid,
		dataType:"json",
		success: function(user){
			$('#btnDeleteu').show();
			console.log('findbyIdUsers success:'+user.nameu);
			currentUser = user;
			renderDetailsUsers(currentUser);
		}
	});
}

var renderTableUsers=function(data){
	//list=data.mobile;
//	console.log("response for users");
	counteru=1;
	$.each(data, function(index,user){
		$('#table_bodyu').append('<tr><th>'+counteru+'</th><td>'+user.nameu+'</td><td>'
				+user.username+'</td><td>'+
				'<a href="#" onclick="findByIdUsers('+user.uid+')"  data-toggle="modal" data-target="#userModal">Edit</a></td></tr>');
		counteru +=1;
	});
	$('#table_idu').DataTable();
		
};

var renderDetailsUsers=function(user){
	$('#uid').val(user.uid);
	$('#nameu').val(user.nameu);
	$('#username').val(user.username);
	$('#password').val(user.password);
	$('#profilepic').attr('src','pics/'+user.profilepic);
	
};



var newUser=function() {
	$('#btnDeleteu').hide();
	currentUser = {};
	// Display empty form
	renderDetailsUsers(currentUser); 
}


var addMobile = function (){
	console.log('addMobile');
	$.ajax({
		type:'POST',
		contentType:'application/json',
		url:rootUrl+ '/mobiles',
		dataType:"json",
		data:formToJSON(),
		success: function(data, testStatus, jqXHR){
			alert('Book Created succesfully');
			$('#id').val(data.id);
			newMobile();
			findAll();
				
		},
		error:function(jqXHR, textStatus, errorThrown){
			alert('addMobile error: '+textStatus);
		}
	});
};

var addUser = function (){
	console.log('addUser');
	$.ajax({
		type:'POST',
		contentType:'application/json',
		url:rootUrl+'/users',
		dataType:"json",
		data:formToJSONUser(),
		success: function(data, testStatus, jqXHR){
			alert('User Created succesfully');
			$('#uid').val(data.uid);
			newUser();
			
			findAllUsers();
				
		},
		error:function(jqXHR, textStatus, errorThrown){
			alert('addUser error: '+textStatus);
		}
	});
};

var updateUser=function() {
	console.log('updateUser');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url:rootUrl +'/users/'+ $('#uid').val(),
		dataType: "json",
		data: formToJSONUser(),
		success: function(user, textStatus, jqXHR){
			alert('User updated successfully');
			newUser();
			findAllU();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateUser error: ' + textStatus);
		}
	});
};

var updateMobile=function() {
	console.log('updateMobile');
	$.ajax({
		type: 'PUT',
		contentType: 'application/json',
		url:rootUrl + '/mobiles/' + $('#id').val(),
		dataType: "json",
		data: formToJSON(),
		success: function(mobile, textStatus, jqXHR){
			alert('Book updated successfully');
			newMobile();
			findAll();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('updateMobile error: ' + textStatus);
		}
	});
}; 


var deleteUser=function() {
	console.log('deleteUser');
	$.ajax({
		type: 'DELETE',
		url: rootUrl + '/users/' + $('#uid').val(),
		success: function(data, textStatus, jqXHR){
			alert('User deleted successfully');
			newUser();
			findAllUsers();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteUser error');
		}
	});
};



var formToJSONUser=function () {
	return JSON.stringify({
		"uid": $('#uid').val(),
		"nameu" :$('#nameu').val(),
		"username" :$('#username').val(),
		"password" :$('#password').val(),
		"profilepic" :""
	});
}

var getModelForUser=function(row){
	var tds = $( row ).find( 'td' );
	alert($( tds[0] ).html());

};










function createClickHandler(row){

	return function() { 
		var cell = row.getElementsByTagName("td")[0];
		// if you put 0 here then it will return first column of this row
		var uid = cell.innerHTML;
		alert("uid:" + uid);
	};

}







var findAll=function(){
    $.ajax({type:'GET',
            url:rootUrl + '/mobiles',
            dataType:"json",
            success:renderList});

}
var findAllProducts=function(){
    $.ajax({type:'GET',
            url:rootUrl + '/mobiles',
            dataType:"json",
            success:renderTable});

}

var findAllRawMedicalDatas=function(){
    $.ajax({type:'GET',
            url:rootUrl + '/rawdata',
            dataType:"json",
            success:renderTableMedical});

}


var renderTableMedical=function(data){
	list=data.rawdata;
//	console.log("response for mobile");
	var counterm =1;
	$.each(data, function(index,rawdata){
	
		var uts = rawdata.dateof;
		var d = new Date(0);
		d.setUTCSeconds(uts)
		$('#table_bodym').append('<tr><th>'+counterm+'</th><td>'+d+'</td><td>'
				+rawdata.injuryType+'</td><td>'
					+rawdata.description+'</td><td>');
		
		counterm +=1;
	});
	$('#table_idm').DataTable();
		
};





var renderList=function(mobiles){
	$('ul').empty();
    $.each(mobiles, function(index,mobile){
    	
    $('#mobileList').append('<li><a href="#" id="'+mobile.id+'">'+mobile.name+'</a></li>');
    });

}



var renderTable=function(data){
	list=data.mobile;
//	console.log("response for mobile");
	var counterm =1;
	$.each(data, function(index,mobile){
	
		
		$('#table_body').append('<tr><th>'+counterm+'</th><td>'+mobile.name+'</td><td>'
				+mobile.modelNo+'</td><td>'
					+mobile.company+'</td><td>'+mobile.price+'</td><td>'+
					'<a href="#"  onclick="findById('+mobile.id+')"  data-toggle="modal" data-target="#largeShoes">Edit</a></td></tr>');
		
		counterm +=1;
	});
	$('#table_idp').DataTable();
		
};



var getModelForProduct=function(row){
	var tds = $( row ).find( 'td' );
	alert($( tds[0] ).html());

};


var findById=function(id) {
	console.log('findById: ' + id)
	$.ajax({
		type:'GET',
		url :rootUrl + '/mobiles/' +id,
		dataType:"json",
		success: function(mobile){
			$('#btnDelete').show();
			console.log('findbyId success: '+mobile.name);
			currentMobile = mobile;
			renderDetails(currentMobile);
		}
	});
}




var renderDetails=function(mobile){
	$('#id').val(mobile.id);
	$('#name').val(mobile.name);
	$('#releaseyear').val(mobile.releaseyear);
	$('#modelNo').val(mobile.modelNo);
	$('#company').val(mobile.company);
	$('#price').val(mobile.price);
	$('#description').val(mobile.description);
	$('#picture').attr('src','pics/'+mobile.picture);
}

var newMobile=function() {
	$('#btnDelete').hide();
	currentMobile = {};
	// Display empty form
	renderDetails(currentMobile); 
}






 
var deleteMobile=function() {
	console.log('deleteMobile');
	$.ajax({
		type: 'DELETE',
		url: rootUrl + '/mobiles/' + $('#id').val(),
		success: function(data, textStatus, jqXHR){
			alert('Book deleted successfully');
			newMobile();
			findAll();
		},
		error: function(jqXHR, textStatus, errorThrown){
			alert('deleteBook error');
		}
	});
};


var formToJSON=function () {
	return JSON.stringify({
		"id": $('#id').val(),
		"name" :$('#name').val(),
		"releaseyear" :$('#releaseyear').val(),
		"modelNo" :$('#modelNo').val(),
		"company" :$('#company').val(),
		"price" :$('#price').val(),
		"description" :$('#description').val(),
		"picture" :""
	});
};

//Replace broken images with generic
/*$(document).ready(function(){
	  $("img").bind("error",function(){
	    // Set the default image
	    $(this).attr("src","pics/joe.jpg");
	  });
	});*/


var search=function(searchKey) {
	if (searchKey == '') 
		findAll();
	else
		findByName(searchKey);
};

var findByName = function(searchKey) {
	console.log('findByName:'+searchKey);
	$.ajax({
		type: 'GET',
		url: rootUrl + '/mobiles/search/'+searchKey,
		dataType: "json",
		success: renderList,
		error: function(jqXHR, textStatus, errorThrown){
			alert('search: ' + textStatus);
		}
	});
	

};

var searchu=function(searchKeyu) {
	if (searchKeyu == '') 
		findAllU();
	else
		findByNameU(searchKeyu);
};

var findByNameu = function(searchKeyu) {
	console.log('findByNameu:'+searchKeyu);
	$.ajax({
		type: 'GET',
		url: rootUrl + '/users/search/'+searchKeyu,
		dataType: "json",
		success: renderListUsers,
		error: function(jqXHR, textStatus, errorThrown){
			alert('search: ' + textStatus);
		}
	});
	

};

$(document).on("click", "#mobileList a", function(){findById(this.id);});
$(document).on("click", "#userList u", function(){findByIdUsers(this.uid);});

/*
$('#table_id').on('click', 'a.editor_edit', function (e) {
		e.preventDefault();
		alert("Yeyy. edit done");
	} );*/

$(document).on('click', '#btnAdd', function () {
	newMobile();
});

$(document).on('click', '#btnAddu', function () {
	newUser();
});

$(document).on('click', '#btnDelete', function (e) {
	console.log('deleteBook');
	deleteMobile();
});

$(document).on('click', '#btnDeleteu', function (e) {
	console.log('deleteUser');
	deleteUser();
	return false;
});



// Trigger search when pressing 'Return' on search key input field

$(document).on('click', '#btnSearch', function () {
	search($('#searchKey').val());
});

$(document).on('click', '#btnSearchu', function () {
	searchUser($('#searchKeyu').val());
});

$(document).on('keypress','#searchKey', function (e) {
	if(e.which == 13) {
		search($('#searchKey').val());
		e.preventDefault();
		return false;
    }
});

$(document).on('keypress','#searchKeyu', function (e) {
	if(e.which == 13) {
		searchUser($('#searchKeyu').val());
		e.preventDefault();
		return false;
    }
});






$(document).on('click', '#btnSave', function (e) {
	if ($('#id').val() == ''){
		addMobile();
		}
	else{
		updateMobile();
		}
	return false;
});

$(document).on('click', '#btnSaveu', function (e) {
	if ($('#uid').val() == ''){
		addUser();
		}
	else{
		updateUser();
		}
	return false;
});



function logiFormToJSON() {
	return JSON.stringify({
		"userName": $('#usname').val(), 
		"password": $('#psw').val(),
		});
}


 function login(){
	 var us = $('#usname').val()
		console.log(us)
		
		var psw = $('#psw').val()
		console.log(psw)
		
		if(us==""){
			alert("Enter username")
		//	return
		}
		if(psw==""){
			alert("Enter password")
		}
		$.ajax({
			type:'GET',
			url :rootUrl +'/users/validate/'+us+'/'+psw,
			dataType:"json",
			
			success: function(data, textStatus, jqXHR){
					var responseText = jQuery.parseJSON(jqXHR.responseText);
					if(responseText==1){
						 console.log(responseText);
						 alert("Login success");
							  $("#myModal").modal("hide");
							  $("#body").show();
							  document.getElementById('userlogged').innerHTML = 'Welcome' + $('#usname').val();
								 console.log('User logged in -->',document.getElementById('userlogged').innerHTML);
								 findAll();
								 findAllUsers();

						
					}
					else {
					 console.log(responseText);
					 alert("Login failed");
					 location.reload();
  
					}
	     	}
		});
 }
 $(document).on('click', '#logout', function () {
	 alert("Are you sure to logout?")
		location.reload();
	});
 $(document).on('click', '#login', function () {
		console.log("Login");
		var userName = $('#usname').val();
		var password = $('#psw').val();
		if(userName&&password){
			login();	
		}else{
			alert("Enter correct username & password");
			location.reload();
		}
		
	});
		 
	   
	   

//DOM has loaded
$(document).ready(function(){
	
	
	   $('#myModal').modal("show");
	     $('#body').hide();
	  
	  findAllProducts();
	   findAllUsers();
	   findAllRawMedicalDatas();
	   
	   countUser();
	   countMobile();
	  
	   $('#btnDelete').hide();
	  
	 $('#btnDeleteu').hide();
		  

	   
	   
	 	   
});
	 








