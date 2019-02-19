<!DOCTYPE html>
<%@page import="gestioneutenti.Utente"%>
<%@page import="gestioneannunci.Annuncio"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>
<link rel="stylesheet" type="text/css" href="CSS/Homepage.css">
<script type="text/javascript" src ="jquery.js"></script>
</head>
<body>
 <%@ include file="barraLEFTv2.jsp" %>
<%
ArrayList<Annuncio> lista =  (ArrayList<Annuncio>) request.getSession().getAttribute("arisultato");
if(lista == null) {
  System.out.println("Lista A non trovata");
  Integer numPagina = (Integer) request.getSession().getAttribute("numPagina");
  if(numPagina == null) {
    System.out.println("Numero pagina non trovato");
    numPagina = 0;
  }
  response.sendRedirect("/BACHECAUNISA/AnnunciServlet?azione=stampaAnnunci&filtro=n&numPagina=" + numPagina.toString());
 return;
}
ArrayList<Utente> lista2 = (ArrayList<Utente>) request.getSession().getAttribute("urisultato");
if(lista2 == null) {
  Integer numPagina = (Integer) request.getSession().getAttribute("numPagina");
  if(numPagina == null) {
    numPagina = 0;
  }
  response.sendRedirect("/BACHECAUNISA/UtenteServlet?azione=stampaUtenti&numPagina=" + numPagina.toString());
}
request.getSession().removeAttribute("arisultato");
request.getSession().removeAttribute("urisultato");
%>
<button id="tabAnnunci" onclick="switchAnnunci()">Annunci</button>
<button id="tabProfilo" onclick="switchProfilo()">Profilo</button>

<div id="listaAnnunci" name="listaAnnunci">
<%for(Annuncio x : lista) {%>
   <div class = "annuncio">
    <div class = "Titolo">
    <h2><%=x.getTitolo()%></h2>
    </div>
    <div class = "preview"><%=x.getDescrizione()%></div>
    <div class = "btn">
<form method= "post">
         <button type="submit" formaction="/BACHECAUNISA/VisualizzaAnnuncio.jsp?id=<%=x.getId()%>">
           Apri
         </button>
</form>
</div>
</div>
<%}%>

</div>


<div id="listaProfili" name="listaProfili">
<% if (lista2 != null){
    for(Utente x : lista2) {%>
   <div class = "annuncio">
    <div class = "Titolo">
    <h2><%=x.getUsername() %></h2>
    </div>
    
    <div class = "preview">
    <h3><%=x.getNome()%>  <%=x.getCognome()%></h3>
    <%= x.getDescrizione() %>
    </div>
    
    <div class = "btn">
    <form method= "post">
    <input type="hidden" name="username" value="<%=x.getUsername()%>">
    <button type="submit"  formaction="/BACHECAUNISA/ProfiloUtente.jsp">
       Apri
      </button>
</form>
</div>
</div>
<%}} else {%>
   <div id="NothingFound">Non è stato trovato nessun utente con questo username :(</div>


<%} %>
</div>

<script type="text/javascript">
function switchAnnunci() {
	$("div[name=listaProfili]").hide();
	$("div[name=listaAnnunci]").fadeIn();
	}

function switchProfilo() {
	$("div[name=listaAnnunci]").hide();
	$("div[name=listaProfili]").fadeIn();
	}


</script>
</body>
</html>