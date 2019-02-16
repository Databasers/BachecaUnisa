<!DOCTYPE html>
<html>
<head>
<title>
Pagina di login
</title>
<style type="text/css">
body, html {
	height: 100%;
	font-family: Arial, Helvetica, sans-serif;
}

* {
	box-sizing: border-box;
}

#logo {
	display: block;
	margin-left: auto;
	margin-right: auto;
	width: 25%;
}

/* Add styles to the form container */
.container {
	position: relative;
	margin: auto;
	max-width: 300px;
	padding: 16px;
	background-color: white;
}

/* Full-width input fields */
input[type=text], input[type=password] {
	width: 100%;
	padding: 15px;
	margin: 5px 0 22px 0;
	border: none;
	background: #f1f1f1;
}

input[type=text]:focus, input[type=password]:focus {
	background-color: #ddd;
	outline: none;
}

/* Set a style for the submit button */
.btn {
	background-color: #2196F3;
	color: white;
	padding: 16px 20px;
	border: none;
	cursor: pointer;
	width: 100%;
	opacity: 0.9;
}

.btn:hover {
	opacity: 1;
}

.btnFAQ {
	background-color: #ddd;
	margin-top: 5px;
	color: dark grey;
	padding: 16px 20px;
	border: none;
	cursor: pointer;
	width: 100%;
	opacity: 0.9;
}

.btnFAQ:hover {
	opacity: 1;
}

</style>
<meta charset="ISO-8859-1">
<script type="text/javascript" src ="jquery.js"></script>
<title>Login</title>
</head>
<body>
	<div id="logo">
	   <img alt="Login" src="https://nextindustry.net/wp-content/uploads/2018/01/Logo_TV_2015.png">
	</div>
	<div>
	   <form class="container">
			<label for="username">Username</label>
			<input type="text" placeholder="Inserisci username" name="username" required>
			<label for="password">Password</label>
			<input type="password" placeholder="Inserisci password" name="password" required>
			<div id="logbutton">
				<button value="Login" class="btn" onclick="login()">Login</button>
			</div>
			
			<script>
                function login(){
                    alert("Cose");
                    var us = $(input[name="username"].val());
                    var pass = $(input[name="password"].val());
                    var action = "/BACHECAUNISA/UtenteServlet?azione=Login&username=" + us + "&password=" + pass;
                    document.container.action = action;
                    document.container.submit();
                }
                
                function redirect() {
                               $(location).attr('href', '<%request.getContextPath();%>/registrazione');
                }
                </script>
			
			</form>
			
			<form action="registrazione.jsp" method="get" class="container">
			
			<input type="submit" class="btnFAQ" value ="Registrati"
			         name="Submit" />
                                                </form>
			
			
		</form>
	</div>



	
</body>