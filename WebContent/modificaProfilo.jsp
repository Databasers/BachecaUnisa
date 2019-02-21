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
            <form id="modify" name = "modify" class="modify" method="Post" action="/BACHECAUNISA/UtenteServlet?azione=creaUtente">
                <input type="text" placeholder="Nome" maxlength="20" pattern="[a-zA-Z\s]{1,}" class="campo" name="nome" id = "nome" required="required" title="Solo lettere">
                <input type="text" placeholder="Cognome" maxlength="20" pattern="[a-zA-Z\s]{1,}" class="campo" name="cognome" required="required" title="Solo lettere">
                <textarea rows="4" cols="50" placeholder="Descrizione" maxlength="2000">   
                </textarea>
                <input id="password" type="password" placeholder="Password" maxlength="20" pattern=".{8,}" class="campo" name="password" required="required" title="Almeno 8 caratteri">
                <input type="password" placeholder="Conferma password" maxlength="20" pattern=".{8,}" class="campo" name="confpassword" required="required" title="Almeno 8 caratteri" oninput="check(this)">
                <script>
                    function check(input){
                        if(input.value !== $("#password").val()){
                            input.setCustomValidity("Le password non coincidono");
                        } else {
                            input.setCustomValidity("");
                        }
                    }
                </script>


                <input type="submit" value="Modifica Profilo" id="modbutton">   

            </form>
        </div>
    </body>
</html>
