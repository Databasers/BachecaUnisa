<!DOCTYPE html>
<%@page import="gestioneutenti.SessioneUtente"%>
<%@page import="gestioneutenti.Utente"%>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
<link rel="stylesheet" type="text/css" href="CSS/RilascioFeedback.css">
<script type="text/javascript" src ="jquery.js"></script>
<title>Rilascio Feedback</title>
</head>
<body>
<script type="text/javascript">console.log("\t ENTRATI IN RILASCIO FEEDBACK");</script>
                <%
              	SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
              	if (su == null) {
                	response.sendRedirect(request.getContextPath() + "/Login.jsp");
              	}
                Utente u = (Utente) request.getSession().getAttribute("utenteTrovato");
                if (u == null) {
                  System.out.println(request.getContextPath() + "/UtenteServlet?azione=prelevaUtente&luogo=feed&username=" + request.getParameter("username"));
                  response.sendRedirect(request.getContextPath() + "/UtenteServlet?azione=prelevaUtente&luogo=feed&username=" + request.getParameter("username"));
                } else {
                  request.getSession().removeAttribute("utenteTrovato");
                
                %>

  <img id="avatar" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
  <p style="text-align:center">Stai rilasciando feedback a:
   </p> <br>
  <div class="utentetxt">
    <h1 id="Utenteinfo"><%=u.getUsername() %><br></h1>
  </div>
  
  <form class="togna" name = "FeedForm">
  <textarea id="textarea" maxlength="2000" name="descrizione">Inserisci qui la tua recensione!</textarea>
    <h1 class="tit">Valutazione:</h1>

  	<div class="star-rating" >
			<input id="star-5" type="radio" name="rating" value="5">
			<label for="star-5" title="5 stars">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
			<input id="star-4" type="radio" name="rating" value="4">
			<label for="star-4" title="4 stars">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
			<input id="star-3" type="radio" name="rating" value="3">
			<label for="star-3" title="3 stars">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
			<input id="star-2" type="radio" name="rating" value="2">
			<label for="star-2" title="2 stars">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
			<input id="star-1" type="radio" name="rating" value="1" checked="checked">
			<label for="star-1" title="1 star">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
		</div>
    <input type="hidden" name = "mittente" value = "<%=((SessioneUtente) request.getSession().getAttribute("Utente")).getUsername() %>">
    <input type="hidden" name = "destinatario" value = "<%=u.getUsername()%>">
    <input type="hidden" name="azione" value="creaRecensione">
  <div id="visualizzafeed"><input type="submit" value="Rilascia Feedback" formaction="/BACHECAUNISA/RecensioniServlet"></div>
  </form>
<%} %>
</body>