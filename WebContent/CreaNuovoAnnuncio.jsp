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
                } else {
                  request.getSession().removeAttribute("utenteTrovato");
                  username = h.getUsername();
                  int nannunci = h.getNumAnnunci();
                  System.out.println(h.getUsername());
                  System.out.println(h.getNumAnnunci() < 5);
        %>
            <h1 class="titlena">Crea Nuovo Annuncio</h1>


            <form id="AnnuncioForm" class ="a" accept-charset="utf-8">
                
                <label class="etichettaGruppoTOP">GRUPPO DI STUDIO <input type="radio" checked="checked" name="tipologia" value="0"></label>
                <label class="etichettaTutoratoTOP">ATTIVITA' DI TUTORATO <input type="radio" name="tipologia" value="1"></label>
                <select name="dipartimento">
                    <option value="Informatica">Informatica</option>
                    <option value="Ingegneria">Ingegneria</option>
                    <option value="Farmacia">Farmacia</option>
                    <option value="Lettere">Lettere</option>
                </select>
                <input type="text" class="inputtitle" maxlength="30" name="titolo" placeholder="Titolo" required="required">
                <textarea class="txtna" maxlength="2000" name="descrizione" contenteditable="true" required="required"></textarea>

                <input type="hidden" name="usernameUtente" value="<%=username%>">
                <%if (nannunci < 5)  {%>
                <input id="Formbutton" type="submit" name="azione" value="Crea annuncio" class="sfdbna" formaction="/BACHECAUNISA/AnnunciServlet">
                <%} else {%>
                <p style="color: red">Hai creato il numero massimo di annunci!</p>
                <%} %>

            </form>
        </div>
        <%}} %>
    </body>