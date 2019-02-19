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
                <br>
                <input type="text" placeholder="Cognome" class="campo" name="cognome" required="required">
                <br>
                <textarea cols="100" rows="10" placeholder="Inserisci nuova descrizione" id="textareaProfilo" name="descrizioneText" form="modProfilo"></textarea>
                <input type="submit" value="Modifica Profilo" id="modProfilo" onclick="registra()">   
            </form>
        </div>
    </body>
</html>
