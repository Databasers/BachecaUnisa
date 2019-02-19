<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Modifica profilo</title>
<style type="text/css">

{

#descrizione{
display: inline-block;
text-align: right;

}

#textareaProfilo{

min-width:400xp;
min-height: 150px;
overflow: hidden;


}
#modProfilo{
float: left;
width: 100%;
}
}

</style>
</head>
<body>
<div style="text-align:center">
<img id="avatar" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png"> <br/>		
</div>
<form id="modificaProfiloForm"  method="Post">



<div id=modProfilo>
	<label for="Nome">Nome:</label>
	<input type="text" class="campo" name="Nome" id = "nome">
	<br>
	<br>
	<label for="cognome">Cognome:</label>
	<input type="text" class="campo" name="cognome"> <br/> <br/> <br/>
	<textarea cols="100" rows="10" id="textareaProfilo" name="descrizioneText" form="modProfilo"> Inserisci qui la descrizione che vuoi inserire sul tuo profilo </textarea>
	</div>
	
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
