<!DOCTYPE html>
<%@page import="gestioneutenti.Utente"%>
<%@page import="gestioneutenti.SessioneUtente"%>
<html>
    <head>
        <title>Nuovo Annuncio</title>
        <link rel="stylesheet" type="text/css" href="CSS/CreaNuovoAnnuncio.css">
        <meta charset="UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
        <script type="text/javascript" src ="jquery.js"></script>
    </head>
    <body>
        <div id="barraleft"><%@ include file="barraLEFTv2.jsp" %></div>
        <div class="contna"> <!-- na vicino ai nomi delle classi sta per nuovo annuncio -->
            <%
           		SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
          		if (su == null) {
              		response.sendRedirect(request.getContextPath() + "/Login.jsp");
            	} else {
                Utente h = (Utente) request.getSession().getAttribute("utenteTrovato");
                String username = null;
                if (h == null) {
                    response.sendRedirect("/BACHECAUNISA/UtenteServlet?azione=prelevaUtente&luogo=crea&username=" + su.getUsername());
                } else if (h.getNumAnnunci() > 4) {
            %>
            <script type="text/javascript">
                $("input[name=azione]").prop("disabled", true);
            </script>
            <%} else {
            username = h.getUsername();
        }%>
            <h1 class="titlena">Crea Nuovo Annuncio</h1>
            <form id="AnnuncioForm">
            <label class="etichettaGruppoTOP">GRUPPO  <input type="radio" name="filtro" value="gruppo"></label>
            <label class="etichettaTutoratoTOP">TUTORATO  <input type="radio" checked="checked" name="filtro" value="tutorato"></label>
                <select>
                        <option value="informatica">Informatica</option>
                        <option value="ingegneria">Ingegneria</option>
                        <option value="farmacia">Farmacia</option>
                        <option value="lettere">Lettere</option>
                </select>
                <input type="text" class="inputtitle" name="titolo" placeholder="Titolo" pattern="[a-zA-Z\s]{1,}" title="Solo lettere" required="required">
                <textarea class="txtna" maxlength="2000" name="descrizione" contenteditable="true" required="required" placeholder="Descrizione..."></textarea>
                <input type="hidden" name="usernameUtente" value="<%=su.getUsername()%>">
                <input type="submit" name="azione" value="Crea annuncio" class="sfdbna" formaction="/BACHECAUNISA/AnnunciServlet">
            </form>
        </div>
        <%} %>
    </body>