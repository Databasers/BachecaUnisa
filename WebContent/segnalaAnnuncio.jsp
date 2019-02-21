<!DOCTYPE html>
<%@page import="gestioneannunci.Annuncio"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Segnala Annuncio</title>
<link rel="stylesheet" type="text/css" href="CSS/SegnalaAnnuncio.css">
</head>
<body>
  
  <%
Annuncio x = (Annuncio) request.getSession().getAttribute("annuncioTrovato");
if (x == null){

  System.out.println("X non esiste");
  response.sendRedirect("/BACHECAUNISA/AnnunciServlet?azione=visualizzannuncio&luogo=se&id=" + request.getParameter("id"));
} else {
  request.getSession().removeAttribute("annuncioTrovato");
%>
  
  
  <h3>Stai segnalando l'annuncio:</h3>
 <div class="avatarcont"> <img id="avatarannuncio" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
  
  <p id="textannunciosegn"><%= x.getDescrizione() %></p>
  
  </div>
  
  <form id="formSegnalaannuncio" method="Post" action="/BACHECAUNISA/SegnalazioniServlet">
 <input type="hidden" name="azione" value="creaSegnalazione">
 <input type="hidden" name="recensione" value= ""> 
 <input type="hidden" name="annuncio" value="<%=x.getId()%>">
  
 
 <div class="checkboxes">
<input type="radio" name="motivazione" value="0" checked="checked"> L'annuncio contiene un linguaggio volgare o inappropriato.<br>
<input type="radio" name="motivazione" value="1"> L'annuncio non e' pertinente alla tipologia.<br>
<input type="radio" name="motivazione" value="2"> Non e' possibile contattare l'utente che ha fatto l'annuncio.<br>  
<input type="radio" name="motivazione" value="3"> Altro.<br> 
 </div>
  
 <textarea id="textarea" name = "descrizione" value="Inserire la descrizione" placeholder="Inserire la descrizione"></textarea>
 
  <div style="text-align:center">
 <input type="submit" value="Segnala" id="segnala">
  </div>
 
   </form>
<%}%>
</body>
