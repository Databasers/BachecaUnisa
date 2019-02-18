<!DOCTYPE html>
<%@page import="gestionerecensioni.Recensione"%>
<%@page import="gestioneutenti.SessioneUtente"%>
<%@page import="gestioneannunci.Annuncio"%>
<%@page import="java.util.ArrayList"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Visuale Gestore</title>
<style>

#listaAnnunci{
margin-top: 20px;
margin-left:20px;
}


#listaRecensioni{

margin-top: 20px;
margin-left:20px;
display:none;

}

#tabAnnunci, #tabRecensioni {
	background-color: #ddd;
	border: none;
	color: black;
	text-decoration: none;
	cursor: pointer;
	border-radius: 16px;
	font-size: 14px;
	width: 150px;
	height: 35px;
	
margin-top: 20px;
margin-left:20px;
}

</style>
</head>
<body>
<%
  ArrayList<Annuncio> elenco =(ArrayList<Annuncio>) request.getSession().getAttribute("arisultato");
  if(elenco==null){
   response.sendRedirect("/BACHECAUNISA/SegnalazioniServlet?azione=stampaAnnuncis");
   return;
  } else {
   
   request.getSession().removeAttribute("arisultato");
  }
  Annuncio[] list  = elenco.toArray(new Annuncio[0]);
%>
<%
  ArrayList<Recensione> elenco2 =(ArrayList<Recensione>) request.getSession().getAttribute("rrisultato");
  if(elenco==null){
   response.sendRedirect("/BACHECAUNISA/AnnunciServlet?"); //TODO Tutti i metodi per stampare i segnalati
   return;
  } else {
   
   request.getSession().removeAttribute("rrisultato");
  }
  Annuncio[] list2  = elenco.toArray(new Annuncio[0]);
%>

<button id="tabAnnunci" onclick="switchAnnunci()">Annunci</button>
<button id="tabRecensioni" onclick="switchRecensioni()">Recensioni</button>

<div id="listaAnnunci">


</div>


<div id="listaRecensioni">

BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB

</div>

<script type="text/javascript">
function switchAnnunci() {
	  var x = document.getElementById("listaRecensioni");
	    x.style.display = "none";
	    x = document.getElementById("listaAnnunci");
	    x.style.display = "block";
	}

function switchRecensioni() {
	  var x = document.getElementById("listaAnnunci");
	    x.style.display = "none";
	    x = document.getElementById("listaRecensioni");
	    x.style.display = "block";
	}


</script>
</body>
</html>