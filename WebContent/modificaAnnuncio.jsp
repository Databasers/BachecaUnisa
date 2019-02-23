<!DOCTYPE html>
<%@page import="gestioneutenti.SessioneUtente"%>
<%@page import="gestioneannunci.AnnuncioManager"%>
<%@page import="gestioneannunci.Annuncio"%>
<html>
    <head>
        <title>Modifica Annuncio</title>
        <link rel="stylesheet" type="text/css" href="CSS/ModificaAnnuncio.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
        <meta charset="UTF-8">
    </head>
    <body>
        <%
            System.out.println("Entrati in modifica annuncio pagina");
            Annuncio x = (Annuncio) request.getSession().getAttribute("annuncioTrovato");
            SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
            if (su == null) {
              response.sendRedirect(request.getContextPath() + "/Login.jsp");
            } else if (x == null) {
              System.out.println("Annuncio non trovato");
              response.sendRedirect("/BACHECAUNISA/AnnunciServlet?azione=visualizzannuncio&luogo=mo&id=" + request.getParameter("id"));
            } else {
              request.getSession().removeAttribute("annuncioTrovato");
              
        %>
        <div id="barraleft"><%@ include file="barraLEFTv2.jsp" %></div>
        <div class="contna" > <!-- na vicino ai nomi delle classi sta per nuovo annuncio -->
            <p id="oldAnnuncio"> </p>
            <form action="/BACHECAUNISA/AnnunciServlet" id = "form">
                <input type="hidden" name = "azione" value="modificaAnnuncio">
                <input type="hidden" name="usernameUtente" value="<%=su.getUsername()%>">
                <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                    <input type="text" class="title" name="titolo" placeholder="Titolo" value="<%= x.getTitolo()%>">
                    <textarea name="descrizione" form="form" class="txtna"><%= x.getDescrizione()%></textarea>
                    <input type="submit" value="Modifica annuncio" class="sfdbna">
            </form>
        </div>


        <script>
            function ceck() {
                var tipologia = $("input[name=tipologia]:selected").val());
                var dipartimento = $("select[name = Dipartimento]").val();
                var titolo = $("label[name=titolo]").val();
                var descrizione = $("div[name = descrizione]").val();

                var azione = "/BACHECAUNISA/gestioneannunci/AnnunciServlet?action=modificaAnnuncio&usernameUtente=" + session.getAttribute("username")
                        + "&dipartimento=" + dipartimento + "&titolo=" + titolo + "&descrizione=" + descrizione + "&tipologia=" + tipologia;
                const elem = getElementById("mod");
                elem.action = azione;
                elem.submit();

            }
        </script>
        <%} %>
    </body>
    </html>