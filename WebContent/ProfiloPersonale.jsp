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
 
  
  	<div class="star-rating">
            <h1 class="tit">Valutazione</h1> 
			<input id="star-5" type="radio" name="rating" value="star-5">
			<label for="star-5" title="5 stars">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
			<input id="star-4" type="radio" name="rating" value="star-4">
			<label for="star-4" title="4 stars">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
			<input id="star-3" type="radio" name="rating" value="star-3">
			<label for="star-3" title="3 stars">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
			<input id="star-2" type="radio" name="rating" value="star-2">
			<label for="star-2" title="2 stars">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
			<input id="star-1" type="radio" name="rating" value="star-1">
			<label for="star-1" title="1 star">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
		</div>
    
  <%
    Utente u = (Utente) request.getSession().getAttribute("utenteTrovato");
    if (u == null) {
      SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
      response.sendRedirect("/BACHECAUNISA/UtenteServlet?azione=prelevaUtente&username=" + su.getUsername());
    } else {
      request.getSession().removeAttribute("utenteTrovato");
  %>

    <div class="utentetxt">
        <img id="avatar" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
        <h1 id="Utenteinfo"><%=u.getUsername()%></h1>
        <h3><%=u.getNome() %> <%=u.getCognome() %></h3>
        <button id="modPro" onclick="location.href = 'modificaProfilo.jsp'">Modifica Profilo</button> 
        <textarea id="textarea" disabled="disabled"><%=u.getDescrizione() %></textarea>
        <button id="visFeed" onclick="location.href = 'VisualizzaFeedback.jsp'">Visualizza Feedback</button>
    </div>
  
  <%} %>
</body>




