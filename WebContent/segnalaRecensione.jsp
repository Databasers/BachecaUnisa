<!DOCTYPE html>
<%@page import="gestioneutenti.SessioneUtente"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Segnala recensione</title>
<link rel="stylesheet" type="text/css" href="CSS/SegnalaRecensione.css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
</head>
<body>
   
  <%
	SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
	if (su == null) {
  	response.sendRedirect(request.getContextPath() + "/Login.jsp");
	}
	%>
  
  
  
  <h3>Stai segnalando l'annuncio:</h3>
 <div class="avatarcont"> <img id="avatarannuncio" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
  
  <p id="textannunciosegn">Testo della recensione</p>
  
  </div>
  <form id="formSegnaRecensione" method="Post">
   <div class="checkboxes">
<input type="checkbox" name="segnala1" value="Bike"> La recensione contiene un linguaggio volgare o inappropriato.<br>
<input type="checkbox" name="segnala2" value="Car"> La recensione non è pertinente all'annuncio.<br>
<input type="checkbox" name="segnala3" value="Boat">Non ho mai incontrato l'utente che ha pubblicato la recensione.<br>  
     <input type="checkbox" name="segnala4" value="Plane"> Altro.<br> 
 </div>
  
 
    <textarea id="textarea">Qui andra'  la descrizione della segnalazione.</textarea>
  
  
<div style="text-align:center">
<input type="submit" value="Segnala" id="segnala" onclick="registra()">
</div>
 </form>
  
  

<script>// Write JavaScript here </script></body>
