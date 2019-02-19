<!DOCTYPE html>
<%@page import="gestioneannunci.Annuncio"%>
<html>
<head>
<title>Visualizza Annuncio</title>
<link rel="stylesheet" type="text/css" href="CSS/VisualizzaAnnuncio.css">
</head>
<body>

<%
Annuncio x = (Annuncio) request.getSession().getAttribute("annuncioTrovato");

if (x == null){
  response.sendRedirect("BACHECAUNISA/AnnunciServlet?id=" + request.getParameter("id"));
} else {
  request.getSession().removeAttribute("annuncioTrovato");
}
%>
  
  
  
  
  <img id="avatarannuncio" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
  <span id="nomeutente"><%=x.getUsernameUtente()%></span>
   <div id="modificannuncio"><input class="valuebutton" type="submit" value="Modifica Annuncio" formaction="/modificaAnnuncio.jsp"></div>
   
  
  <div class="titleannuncio">
    <h1 id="Utenteinfo">TITOLO:<%=x.getTitolo()%><br>
      DIPARTIMENTO:<%=x.getDipartimento() %></h1>
  </div>

  <p class="txtannuncio"><%=x.getDescrizione()%></p>
  
    
  
  <div id="segnalaannuncio"><input class="valuebutton" type="submit" value="Segnala Annuncio"></div>
  
  </body>
  
  