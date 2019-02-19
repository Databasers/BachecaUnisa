<!DOCTYPE html>
<%@page import="gestioneannunci.Annuncio"%>
<html>
<head>
<title>Visualizza Annuncio</title>
<link rel="stylesheet" type="text/css" href="CSS/VisualizzaAnnuncio.css">
</head>
<body>
<%@ include file="barraLEFTv2.jsp" %>
<%
Annuncio x = (Annuncio) request.getSession().getAttribute("annuncioTrovato");
if (x == null){

  System.out.println("X non esiste");
  response.sendRedirect("/BACHECAUNISA/AnnunciServlet?azione=visualizzaAnnuncio&id=" + request.getParameter("id"));
} else {
  request.getSession().removeAttribute("annuncioTrovato");
%>
  <img id="avatarannuncio" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
  <span id="nomeutente"><%=x.getUsernameUtente()%></span>   
  
  <div class="titleannuncio">
    <h1 id="Utenteinfo">TITOLO:<%=x.getTitolo()%><br>
      DIPARTIMENTO:<%=x.getDipartimento() %></h1>
  </div>

  <p class="txtannuncio"><%=x.getDescrizione()%></p>
  
    
  
  <div id="segnalaannuncio"><input class="valuebutton" type="submit" value="Segnala Annuncio" formaction="/segnalaAnnuncio.jsp?id=<%=x.getId()%>"></div>
  <%} %>
  </body>
  
  