<!DOCTYPE html>
<html>
<head>
<title>
Pagina di login
</title>
<link rel="stylesheet" type="text/css" href="CSS/Login.css">
<meta charset="ISO-8859-1">
<script type="text/javascript" src ="jquery.js"></script>
<title>Login</title>
</head>
<body>
	<div id="logo">
	   <img alt="Login" src="https://nextindustry.net/wp-content/uploads/2018/01/Logo_TV_2015.png">
	</div>
	<div>
	   <form class="container" name ="Login">
			<label for="username">Username</label>
			<input type="text" placeholder="Inserisci username" name="username" required>
			<label for="password">Password</label>
			<input type="password" placeholder="Inserisci password" name="password" required>
			<div id="logbutton">
			   <button value="Login" class="btn" name ="azione" formaction="/BACHECAUNISA/UtenteServlet">Login</button>
			</div>
			
			<script>
                function login(){
                    var us = $("input[name=username]").val();
                    var pass = $("input[name=password]").val();
                    console.log(us + " " + pass);
                    $("form[name=Login]").action = "/BACHECAUNISA/UtenteServlet?azione=Login";
                    $("form[name=Login]").submit();
                }
                
                function redirect() {
                               $(location).attr('href', '<%=request.getContextPath()%>/registrazione');
                }
                </script>
			
			</form>
			
			<form action="registrazione.jsp" method="get" class="container">
			
			<input type="submit" class="btnFAQ" value ="Registrati"
			         name="Submit" />
                                                </form>
			
			
	</div>



	
</body>