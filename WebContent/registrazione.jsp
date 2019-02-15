<!DOCTYPE html>
<html>
<head><style type="text/css">.lw { font-size: 60px; }</style>


<style>
#container, #logo{
margin-top: 30px;
}
#container{
padding-top:12px;
padding-left: 12px;
display: block;
  margin-left: auto;
  margin-right: 56px;
  float:right;
  border-style: solid;
  width: 30%; 
  height: auto;
  padding-bottom: 7%;
}
    
.campo{
  float: right;
margin-right: 10%;
width: 50%;
}
  
#logo img{
display: block;
margin-left: auto;
margin-right: auto;
}
  
#logo{
border-style:solid;
  width: 50%;
  height:auto;
  float: left;
  margin-left: 3%;
}
  
#faq{

}

#sextext{
    float: left;
}
  
 
#sex{
float: right;
margin-right: 10%;
width: 50%;
  }
  
#field{
margin-bottom: 5px;
}

#sextext,#sex{
position:relative;
margin-top:-25px;
}

#regbutton{
    float:left;

}
</style>
<meta charset="ISO-8859-1">
<script type="text/javascript" src ="<%request.getContextPath(); %>/JS/jquery.js"></script>
<title>Registrazione</title>
</head>
<body>
<div id="logo">
<img alt="Logo" src="https://nextindustry.net/wp-content/uploads/2018/01/Logo_TV_2015.png">
</div>
<div id="container">   

<% String registered = (String)request.getAttribute("alreadyRegistered");
   if(registered != null && registered=="true"){%>
   <h3><font color="red">Questo utente esiste già</font></h3>
   <% }
      request.removeAttribute("alreadyRegistered");%>
      
	<div id="field">   
	<form id="register" class="register" method="post">
	<div>
	<label for="Nome">Nome:</label>
	<input type="text" class="campo" name="Nome">
	<br>
	<br>
	<label for="cognome">Cognome:</label>
	<input type="text" class="campo" name="cognome">
		
	</div>
	<br>
	<br>
	<div id="sextext">Sesso:
	<select name="sesso">
	               <option value="maschio">maschio</option>
	               <option value="femmina">femmina</option>
	               <option value="altro">altro</option>
                </select>
                </div>
                <br>
                <div>
	<label for="username">Username:</label>
	<input id="nomefield" type="text" class="campo" name="username">
	<br>
	<br>
	<label for="password">Password:</label>
	<input type="text" class="campo" name="password">
	<br>
	<br>
	<label for="password">Conferma <br>Password:</label>
	<input type="text" class="campo" name="confpassword">
	<br>
	<br>
	<label for="checkbox">Conferma <br>Regolamento</label>
	<input type="checkbox" class="campo" name="ceckbox">
	<br>
	<br><script>
                function register() {
                    console.log("Entrati")
                    var nom = $(input[name = "Nome"]).val();
                    var cog = $(input[name = "cognome"]).val();
                    var ss = $(select[nome = "sesso"]).val();
                    var user = $(input[name = "username"]).val();
                    var pas = $(input[name = "password"]).val();
                    var cpass = $(input[name = "confpassword"]).val();
                    var rego = $(input[name = "ceckbox"]).val();
                    
                    alert(rego +"");
                    if (nom == "" || cog == "" || user == "" || pas == "" || rego == null) {
                        
                        alert("Inserimento non effettuato");
                        
                        if (rego == "") {
                            $(input[name = "ceckbox"]).css("background-color", "red");
                        }
                        if (pas == "") {
                            $(input[name = "password"]).css("background-color", "red");
                        }
                        if (user == "") {
                            $(input[name = "username"]).css("background-color", "red");
                        }       
                        if (nom == "") {
                                $(input[name = "Nome"]).css("background-color", "red");
                        }
                        if (cog == "") {
                            $(input[name = "cognome"]).css("background-color", "red");
                        }
                    }else if(pas != cpass) {
                        alert("Le password non combaciano");
                        $(input[name = "password"]).css("background-color", "red");
                        $(input[name = "confpassword"]).css("background-color", "red");
                        
                    } else {
                    
                    $("form.register").action = "/BACHECAUNISA/UtenteServlet?action=creaUtente&username=" + user +
                                                "&cognome=" + cog + "&sesso=" + ss + "&nome=" + nom + "&password=" + pas;
                    $("from.register").submit();
                    }
                }

                </script>
                
	<button id="regbutton" onclick="register()">Registrati</button>
	
	<br>
	<br>
	
	</div>
	
	</form>
	
	</div> 
	
	<form action="FAQ.jsp">
                                <input type="submit" value="Visualizza FAQ" id="faq" >
	</form>
	
                </div>


                

</body>