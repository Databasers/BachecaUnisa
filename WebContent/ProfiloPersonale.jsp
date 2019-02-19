<%@page import="gestioneutenti.Utente"%>
<%@page import="gestioneutenti.SessioneUtente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Profilo Personale</title>
<link rel="stylesheet" type="text/css" href="CSS/ProfiloPersonale.css">
</head>
<body>
  <%@ include file="barraLEFTv2.jsp" %>
 
    
  <%
    Utente u = (Utente) request.getSession().getAttribute("utenteTrovato");
    if (u == null) {
      SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
      response.sendRedirect("/BACHECAUNISA/UtenteServlet?azione=prelevaUtente&luogo=pro&username=" + su.getUsername());
    } else {
      request.getSession().removeAttribute("utenteTrovato");
  %>

  <div class="utentetxt">
    <img id="avatar" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
    <h1 id="Utenteinfo"><%=u.getUsername()%></h1>
    <h3><%=u.getNome() %> <%=u.getCognome() %></h3>
  </div>
   
   <div style="text-align:right">
   <button id="modPro" onclick="location.href = 'modificaProfilo.jsp'">Modifica Profilo</button> 
   </div>
<textarea id="textarea" disabled="disabled"><%=u.getDescrizione() %></textarea>
    
 <button id="visFeed" onclick="location.href = 'VisualizzaFeedback.jsp?username=<%=u.getUsername()%>'">Visualizza Feedback</button>
  
  
  <%} %>
</body>




