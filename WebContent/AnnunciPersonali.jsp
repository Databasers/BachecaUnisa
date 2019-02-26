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
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
        <link rel="stylesheet" type="text/css" href="CSS/AnnunciPersonali.css">
        <title>AnnunciPersonali</title>
    </head>

    <%
        SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
        if (su == null) {
          response.sendRedirect(request.getContextPath() + "/Login.jsp");
        } else {
    	
        ArrayList<Annuncio> elenco = (ArrayList<Annuncio>) request.getSession().getAttribute("arisultato");
        if (elenco == null) {
            response.sendRedirect("/BACHECAUNISA/AnnunciServlet?azione=stampaAnnunci&luogo=per&filtro=utente&usernameUtente=" + su.getUsername());
            return;
        } else {
        request.getSession().removeAttribute("arisultato");
        Annuncio[] list = elenco.toArray(new Annuncio[0]);
    %>

    <body>
        <div id="barraleft"><%@ include file="barraLEFTv2.jsp" %></div>
        <div id="AnnunciPersonali">
            <p class="title">Annunci di <%=su.getUsername()%></p>
            <%for (Annuncio x : list) {%>
            <div class = "annuncio<%if (x.isTipologia()){%>Tutorato<%}%><%if (!x.isTipologia()){%>Gruppo<%}%>">
                <div class="tags">             
                    <%if (x.isTipologia()) {%> <p class="etichettaTutorato">ATTIVITA' DI TUTORATO</p> <%}%>
                    <%if (!x.isTipologia()) {%> <p class="etichettaGruppo">GRUPPO DI STUDIO</p> <%}%>
                    <p class="dipartimento"><%=x.getDipartimento()%></p>
                </div>
                <div class = "Titolo">
                    <h2><%=x.getTitolo()%></h2>
                </div>
                <div class = "preview"><%= x.getDescrizione()%></div>
                <div class = "btn" accept-charset="utf-8">
                    <form method= "post" accept-charset="utf-8">
                        <button type="submit" formaction="/BACHECAUNISA/VisualizzaAnnuncio.jsp?id=<%=x.getId()%>">Visualizza</button>
                        <button type="submit" formaction="/BACHECAUNISA/modificaAnnuncio.jsp?id=<%=x.getId()%>">Modifica</button>
                    </form>
                    <form action="/BACHECAUNISA/AnnunciServlet">
                    <input type="hidden" name = "azione" value="rimuoviAnnuncio">
                    <input type="hidden" name = "id" value="<%=x.getId()%>">
                    <button style="background: red" type="submit">Rimuovi</button>

                    </form>
                </div>
            </div>
            <%}%>
        </div>
        <%}} %>
    </body>
</html>