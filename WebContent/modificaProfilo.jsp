<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<<<<<<< HEAD
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
=======
<head>
<meta charset="ISO-8859-1">
<title>Modifica profilo</title>
<link rel="stylesheet" type="text/css" href="CSS/ModificaProfilo.css">
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
>>>>>>> 3dc7fe488f88c9ef7066b4468e8ff5f102e85af3
                <br>
                <input type="text" placeholder="Cognome" class="campo" name="cognome" required="required">
                <br>
                <textarea cols="100" rows="10" placeholder="Inserisci nuova descrizione" id="textareaProfilo" name="descrizioneText" form="modProfilo"></textarea>
                <input type="submit" value="Modifica Profilo" id="modProfilo" onclick="registra()">   
            </form>
        </div>
    </body>
</html>
