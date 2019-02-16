<!DOCTYPE html>
<html>
<head><style type="text/css">.lw { font-size: 60px; }</style>

<title>Registrazione</title>
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
<meta charset="UTF-8">
<script type="text/javascript" src ="jquery.js"></script>
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
	<form id="register" name = "register" class="register" method="Post">
	<div>
	<label for="Nome">Nome:</label>
	<input type="text" class="campo" name="Nome" id = "nome">
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
	<input id="nomefield" type="text" class="campo" name="username" required="required" >
	<br>
	<br>
	<label for="password">Password:</label>
	<input type="password" class="campo" name="password" required="required">
	<br>
	<br>
	<label for="confpassword">Conferma <br>Password:</label>
	<input type="password" class="campo" name="confpassword" required="required">
	<br>
	<br>
	<label for="checkbox">Conferma <br>Regolamento</label>
	<input type="checkbox" class="campo" name="ceckbox" required="required">
	<br>
	<br><script>
                function registra() {
                    console.log("Entrati");
                    var nom = $("input[name = Nome]").val();
                    var cog = $("input[name = cognome]").val();
                    var ss = $("select[name = sesso]").val();
                    var user = $("input[name = username]").val();
                    var pas = $("input[name = password]").val();
                    var cpass = $("input[name = confpassword]").val();
                    var rego = $("input[name = ceckbox]").prop("checked");
                    
                  
                    if(pas != cpass || pas == "" || !rego) {
                      if(pas != cpass || pas == "") {
                        alert("Le password non combaciano");
                      }
                      if(!rego) {
                          alert("Per favore accetta il contratto");
                      }
                    
                    } else {
                    var azione = "/BACHECAUNISA/UtenteServlet?azione=creaUtente&username=" + user +
                                                "&cognome=" + cog + "&sesso=" + ss + "&nome=" + nom + "&password=" + pas;

                    console.log(azione + "");
                    const elem = document.getElementById("register");
                    elem.action = azione;
                    console.log(elem.action + " è l'azione");
                    elem.submit();
                    }
                }

                </script>
                
	<button id="regbutton" onclick="registra()">Registrati</button>
	
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