<%@page import="gestioneutenti.SessioneUtente"%>
<%@page import="gestioneutenti.Utente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
<link rel="stylesheet" type="text/css" href="CSS/ProfiloUtente.css">

<title>Profilo Utente</title>

</head>
<body>
  <%@ include file="barraLEFTv2.jsp" %>
  
  <%
  	SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
  	if (su == null) {
    	response.sendRedirect(request.getContextPath() + "/Login.jsp");
  	}
  	Utente u = (Utente) request.getSession().getAttribute("utenteTrovato");
    if (u == null) {
      response.sendRedirect("/BACHECAUNISA/UtenteServlet?azione=prelevaUtente&luogo=ut&username=" + request.getParameter("username"));
    } else {
    request.getSession().removeAttribute("utenteTrovato");
  %>
  <div class="utentetxt">
    <img id="avatar" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
    <h1 id="Utenteinfo"><%=u.getUsername()%></h1>
    <h3><%=u.getNome() %> <%=u.getCognome() %></h3>
  </div>
  
<textarea id="textarea" disabled="disabled"><%=u.getDescrizione() %></textarea>
  
 <form action="">
 <span style="width:50%; text-align: right">
    <input type="hidden" name="username" value="<%=request.getParameter("username")%>">
 <button id="riFeed" type="submit" formaction="/BACHECAUNISA/RilascioFeedback.jsp">Rilascia Feedback</button>
</span>
</form>
<%} %>
</body>
</html>



