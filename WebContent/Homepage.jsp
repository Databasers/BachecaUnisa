<!DOCTYPE html>
<%@page import="gestioneutenti.Utente"%>
<%@page import="gestioneannunci.Annuncio"%>
<%@page import="java.util.ArrayList"%>
<html>
    <head>
        <meta charset="ISO-8859-1">
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
            ArrayList<Annuncio> lista = (ArrayList<Annuncio>) request.getSession().getAttribute("arisultato");
            ArrayList<Utente> lista2 = (ArrayList<Utente>) request.getSession().getAttribute("urisultato");
            if (lista == null || lista2 == null) {
	            if (lista == null) {
	                System.out.println("Lista A non trovata");
	                Integer numPagina = (Integer) request.getSession().getAttribute("numPagina");
	                if (numPagina == null) {
	                    System.out.println("Numero pagina non trovato");
	                    numPagina = 0;
	                }
	                response.sendRedirect("/BACHECAUNISA/AnnunciServlet?azione=stampaAnnunci&filtro=n&numPagina=" + numPagina.toString());
	                return;
	            }
	            if (lista2 == null) {
	                Integer numPagina = (Integer) request.getSession().getAttribute("numPagina");
	                if (numPagina == null) {
	                    numPagina = 0;
	                }
	                response.sendRedirect("/BACHECAUNISA/UtenteServlet?azione=stampaUtenti&numPagina=" + numPagina.toString());
	            }
            } else {
              request.getSession().removeAttribute("arisultato");
              request.getSession().removeAttribute("urisultato");
            }
        %>
        <div id="switchTab">
        <button id="tabAnnunci" onclick="switchAnnunci()">Annunci</button>
        <button id="tabProfilo" onclick="switchProfilo()">Utenti</button>
        </div>


        <div id="listaAnnunci" name="listaAnnunci">
            
            <%for (Annuncio x : lista) {%>
            <div class = "annuncio">
                <p class="etichettaTipologia">
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
            <%}%>

        </div>


        <div id="listaProfili" name="listaProfili">
            <% if (lista2 != null) {
        for (Utente x : lista2) {%>
            <div class = "annuncio">
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
            <%}
} else {%>
            <div id="NothingFound">Non è stato trovato nessun utente con questo username :(</div>


            <%}%>
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