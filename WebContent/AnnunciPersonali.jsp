<%@page import="gestioneannunci.Annuncio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="gestioneannunci.AnnuncioManager"%>
<%@page import="gestioneutenti.UtenteManager"%>
<%@page import="gestioneutenti.SessioneUtente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AnnunciPersonali</title>
</head>

<%
  ArrayList<Annuncio> elenco =(ArrayList<Annuncio>) request.getSession().getAttribute("risultato");
  if(elenco==null){
    SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("User");
   response.sendRedirect("/BACHECAUNISA/AnnunciServlet?azione=stampaAnnunci&filtro=utente&username=" + su.getUsername());
   return;
  } else {
   
   request.getSession().removeAttribute("risultato");
  }
  Annuncio[] list  = elenco.toArray(new Annuncio[0]);
%>

<body>
<div id="AnnunciPersonali">

<%for(Annuncio x : list) {%>
   <div class = "annuncio">
    <div class = "Titolo">
    <h2><%=x.getTitolo()%></h2>
    </div>
    <div class = "preview"><%= x.getDescrizione() %></div>
    <div class = "btn">
<form method= "post">
<button type="submit" formaction="/BACHECAUNISA/AnnunciServlet?azione=visualizzannuncio&id=<%=x.getId()%>">
Apri
</button>
</form>
</div>
</div>


<%}%>
</div>
</body>
</html>