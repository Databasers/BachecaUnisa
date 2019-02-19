<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Segnala Annuncio</title>
<link rel="stylesheet" type="text/css" href="CSS/SegnalaAnnuncio.css">
</head>
<body>
  
  
  
  <h3>Stai segnalando l'annuncio:</h3>
 <div class="avatarcont"> <img id="avatarannuncio" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
  
  <p id="textannunciosegn">Offro lezioni di tutorato per l'esame...disponibile giorni...contattare via e-mail</p>
  
  </div>
  <form id="formSegnalaannuncio" method="Post">
   <div class="checkboxes">
<input type="checkbox" name="segnala1" value="Bike"> L'annuncio contiene un linguaggio volgare o inappropriato.<br>
<input type="checkbox" name="segnala2" value="Car"> L'annuncio non e' pertinente alla tipologia.<br>
<input type="checkbox" name="segnala3" value="Boat"> Non e' possibile contattare l'utente che ha fatto l'annuncio.<br>  
     <input type="checkbox" name="segnala3" value="Boat"> Altro.<br> 
 </div>
  
 
    <textarea id="textarea">Qui andra'  la descrizione della segnalazione.</textarea>
  <div style="text-align:center">
 <input type="submit" value="Segnala" id="segnala" onclick="registra()">
 </div>
  </form>

<script>// Write JavaScript here </script></body>