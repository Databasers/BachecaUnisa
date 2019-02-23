<!DOCTYPE html>
<%@page import="gestionerecensioni.Recensione"%>
<%@page import="gestioneutenti.SessioneUtente"%>
<html>
<head>
<meta charset="UTF-8">
<title>Segnala recensione</title>
<link rel="stylesheet" type="text/css" href="CSS/SegnalaRecensione.css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
</head>
<body>
   
  <%
SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
Recensione x = (Recensione) request.getSession().getAttribute("recensione");
if (su == null) {
  response.sendRedirect(request.getContextPath() + "/Login.jsp");
} else if (x == null){
  System.out.println("X non esiste");
  response.sendRedirect("/BACHECAUNISA/RecensioniServlet?azione=segnalaRecensione&luogo=se&id=" + request.getParameter("id"));
} else {
  request.getSession().removeAttribute("annuncioTrovato");

	%>
  
  
  
  <h3>Stai segnalando la recensione:</h3>
 <div class="avatarcont"> <img id="avatarannuncio" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
  
  <p id="textannunciosegn"><%=x.getDescrizione()%></p>
  
  </div>
  <form id="formSegnaRecensione" method="Post">
   <div class="checkboxes">
<input type="radio" name="motivazione" value="0" checked="checked"> La recensione contiene un linguaggio volgare o inappropriato.<br>
<input type="radio" name="motivazione" value="1"> La recensione non è pertinente<br>
<input type="radio" name="motivazione" value="2">Non ho mai incontrato l'utente che ha pubblicato la recensione.<br>  
<input type="radio" name="motivazione" value="3"> Altro.<br> 
 </div>
  
 <textarea id="textarea" placeholder="Descrizione"></textarea>
  
  
<div style="text-align:center">
<input type="submit" value="Segnala" id="segnala">
</div>
 </form>
 <%} %>
  </body>
 </html>
