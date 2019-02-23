<!DOCTYPE html>
<%@page import="gestioneutenti.Utente"%>
<%@page import="gestioneannunci.Annuncio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="gestioneutenti.SessioneUtente"%>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Homepage</title>
        <link rel="stylesheet" type="text/css" href="CSS/Homepage.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
        <script type="text/javascript" src ="jquery.js"></script>
    </head>
    <body>
        <div id="barraleft"><%@ include file="barraLEFTv2.jsp" %></div>
        <div id="barrasopra"><%@ include file="barraSopra.jsp" %></div>
        
        <%
        	SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
        	if (su == null) {
          		response.sendRedirect(request.getContextPath() + "/Login.jsp");
        	} else {
            ArrayList<Annuncio> lista = (ArrayList<Annuncio>) request.getSession().getAttribute("arisultato");
            ArrayList<Utente> lista2 = (ArrayList<Utente>) request.getSession().getAttribute("urisultato");
            if (lista == null || lista2 == null) {
	            if (lista == null) {
	                System.out.println("Lista A non trovata");
	                response.sendRedirect("/BACHECAUNISA/AnnunciServlet?azione=stampaAnnunci&filtro=n");
	                return;
	            }
	            if (lista2 == null) {
	                System.out.println("Lista U non trovata");
	                response.sendRedirect("/BACHECAUNISA/UtenteServlet?azione=stampaUtenti");
	            }
            } else {
              request.getSession().removeAttribute("arisultato");
              request.getSession().removeAttribute("urisultato");
            
        %>
        <div id="switchTab">
        <button id="tabAnnunci" onclick="switchAnnunci()">Annunci</button>
        <button id="tabProfilo" onclick="switchProfilo()">Utenti</button>
        </div>


        <div id="listaAnnunci" name="listaAnnunci">
            
            <%if (!lista.isEmpty()) { for (Annuncio x : lista) {%>
            <div class = "annuncio<%if (x.isTipologia()) {%>Tutorato<%}%><%if (!x.isTipologia()) {%>Gruppo<%}%>">
                    <%if (x.isTipologia()) {%> <p class="etichettaTutorato">TUTORATO</p> <%}%>
                    <%if (!x.isTipologia()) {%> <p class="etichettaGruppo">GRUPPO</p> <%}%>
                <div class = "Titolo">
                    <h2><%=x.getTitolo()%></h2>
                    <h3><%=x.getUsernameUtente()%></h3>
                </div>
                <div class = "preview"><%=x.getDescrizione()%></div>
                <div class = "btn">
                    <form method= "post">
                        <button type="submit" formaction="/BACHECAUNISA/VisualizzaAnnuncio.jsp?id=<%=x.getId()%>">Visualizza</button>
                    </form>
                </div>
            </div>
            <%}} else {%>
                
             Non ho trovato nulla
             
             <%} %>
        </div>


        <div id="listaProfili" name = "listaProfili">
            <% if (!lista2.isEmpty()) {
              for (Utente x : lista2) {  if (x.getUsername() != null) {%>
            <div class = "profilo">
                <div class = "Titolo">
                    <h2><%=x.getUsername()%></h2>
                    <h3><%=x.getNome()%>  <%=x.getCognome()%></h3>
                </div>

                <div class = "preview">
                    <%= x.getDescrizione()%>
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
            <%}}
              } else {%>
            <div id="NothingFound">Non è stato trovato nessun utente con questo username :(</div>
            <%}}}%>
            
        </div>

        <script type="text/javascript">
            function switchAnnunci() {
            	console.log("SWIITCHI");
                $("#listaProfili").hide();
                $("#listaAnnunci").fadeIn();
            }

            function switchProfilo() {
            	console.log("SWIITCHI");
                $("#listaAnnunci").hide();
                $("#listaProfili").fadeIn();
            }


        </script>
    </body>
</html>