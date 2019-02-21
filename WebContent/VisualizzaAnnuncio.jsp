<!DOCTYPE html>
<%@page import="gestioneannunci.Annuncio"%>
<html>
<head>
<title>Visualizza Annuncio</title>
<link rel="stylesheet" type="text/css" href="CSS/VisualizzaAnnuncio.css">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
</head>
<body>
    <div id="barraleft"><%@ include file="barraLEFTv2.jsp" %></div>
<%
Annuncio x = (Annuncio) request.getSession().getAttribute("annuncioTrovato");
if (x == null){

  System.out.println("X non esiste");
  response.sendRedirect("/BACHECAUNISA/AnnunciServlet?azione=visualizzannuncio&id=" + request.getParameter("id"));
} else {
  request.getSession().removeAttribute("annuncioTrovato");
%>
<div class="annuncio">
  <img id="avatarannuncio" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
  <br>
  <span id="nomeutente"><%=x.getUsernameUtente()%></span>   
  <div class="annuncioInfo">
    <h2 id="annuncioTitle"><%=x.getTitolo()%><br></h2>
    <span><%=x.getDipartimento()%></span>
  </div>
  <p class="txtannuncio"><%=x.getDescrizione()%></p>

  <div id="segnalaannuncio"><input class="valuebutton" type="submit" value="Segnala Annuncio" formaction="/segnalaAnnuncio.jsp?id=<%=x.getId()%>"></div>
</div>
  <%} %>
  </body>
  
  