<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Modifica profilo</title>
        <link rel="stylesheet" type="text/css" href="CSS/modificaProfilo.css">
    </head>
    <body>
        <div class="avatar">
            <img alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png"> <br/>		
        </div>
        
        <div id=modProfilo>
            <form method="Post">
                <input type="text" placeholder="Nome" class="campo" name="Nome" id = "nome" required="required">

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

	<br><script>
                function registra() {
                    console.log("Entrati");
                    var nom = $("input[name = Nome]").val();
                    var cog = $("input[name = cognome]").val();
                    var ss = $("select[name = sesso]").val();
                    var user = $("input[name = username]").val();
		    var descrizione= $("input[name = descrizioneText]").val();
                    var pas = $("input[name = password]").val();
                    var cpass = $("input[name = confpassword]").val();
                    var rego = $("input[name = ceckbox]").prop("checked");
                    
                  
                    if(pas != cpass || pas == "" || !rego) {
                      if(pas != cpass || pas == "") {
                        alert("Le password non combaciano");
                      }
                 
                    } else {
                    var azione = "/BACHECAUNISA/UtenteServlet?azione=creaUtente&username=" + user +
                                                "&cognome=" + cog + "&sesso=" + ss + "&nome=" + nom + "&password=" + pas;  //aggiungi la descrizione qui da qualche parte

                    console.log(azione + "");
                    const elem = document.getElementById("modProfilo");
                    elem.action = azione;
                    console.log(elem.action + " è l'azione");
                    elem.submit();
                    }
                }

                </script>
                
	
    <input type="submit" value="Modifica Profilo" id="modProfilo" onclick="registra()">   
	
	<br>
	<br>
	
	</div>
	
	</form>
	
</body>
</html>
