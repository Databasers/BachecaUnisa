<%@page import="gestioneutenti.Utente"%>
<%@page import="gestioneutenti.SessioneUtente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
<title>Profilo Personale</title>
<link rel="stylesheet" type="text/css" href="CSS/ProfiloPersonale.css">
</head>
<body>
    <div id="barraleft"><%@ include file="barraLEFTv2.jsp" %></div>
    
  <%
  	SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
  	if (su == null) {
    	response.sendRedirect(request.getContextPath() + "/Login.jsp");
  	}
    Utente u = (Utente) request.getSession().getAttribute("utenteTrovato");
    if (u == null) {
      response.sendRedirect("/BACHECAUNISA/UtenteServlet?azione=prelevaUtente&luogo=pro&username=" + su.getUsername());
    } else {
      request.getSession().removeAttribute("utenteTrovato");
  %>


    <div class="utentetxt">
        <img id="avatarProf" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
        <h1 id="nomecognome"><%=u.getNome() %> <%=u.getCognome() %></h1>
        <h3 id="Utenteinfo"><%=u.getUsername()%></h3>
        <button id="modPro" onclick="location.href = 'modificaProfilo.jsp'">Modifica Profilo</button> 
        <textarea id="textarea" disabled="disabled"><%=u.getDescrizione() %></textarea>
        <button id="visFeed" onclick="location.href = 'VisualizzaFeedback.jsp'">Visualizza Feedback</button>
    </div>
  <%} %>
</body>
</html>




