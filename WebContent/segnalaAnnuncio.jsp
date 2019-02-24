<!DOCTYPE html>
<%@page import="gestioneannunci.Annuncio"%>
<%@page import="gestioneutenti.SessioneUtente"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Segnala Annuncio</title>
        <link rel="stylesheet" type="text/css" href="CSS/SegnalaAnnuncio.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
    </head>
    <body>
        <div id="barraleft"><%@ include file="barraLEFTv2.jsp" %></div>
        <%
            SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
            if (su == null) {
                response.sendRedirect(request.getContextPath() + "/Login.jsp");
            } else {
                Annuncio x = (Annuncio) request.getSession().getAttribute("annuncioTrovato");
                if (x == null) {
                    System.out.println("X non esiste");
                    response.sendRedirect("/BACHECAUNISA/AnnunciServlet?azione=visualizzannuncio&luogo=se&id=" + request.getParameter("id"));
                } else {
                    request.getSession().removeAttribute("annuncioTrovato");
        %>

        <div class="container">
            <fieldset class="fieldset">
                    <legend class="legend">Stai segnalando : </legend>
                    <img id="avatarProf" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
                    <div class="annuncio">
                        <%if (x.isTipologia()) {%> <p class="etichettaTutorato">ATTIVITA' DI TUTORATO</p>
                        <%} else {%> <p class="etichettaGruppo">GRUPPO DI STUDIO</p> <%}%>
                        <h1 id="titoloAnnuncio"><%=x.getTitolo()%></h1>
                        <textarea id="textareaAnnuncio" disabled="disabled"><%=x.getDescrizione()%></textarea>
                    </div>               
            </fieldset>    

        <form id="formSegnalaannuncio" method="Post" action="/BACHECAUNISA/SegnalazioniServlet">
            <input type="hidden" name="azione" value="creaSegnalazione">
            <input type="hidden" name="recensione" value= ""> 
            <input type="hidden" name="annuncio" value="<%=x.getId()%>">

            <input type="hidden" name="luogo" value="no">

            <div class="checkboxes">
                <input type="radio" name="motivazione" value="0" checked="checked"> L'annuncio contiene un linguaggio volgare o inappropriato.<br>
                <input type="radio" name="motivazione" value="1"> L'annuncio non e' pertinente alla tipologia.<br>
                <input type="radio" name="motivazione" value="2"> Non e' possibile contattare l'utente che ha fatto l'annuncio.<br>  
                <input type="radio" name="motivazione" value="3"> Altro.<br> 
            </div>
            <textarea rows="4" cols="50" id="textareaSeg" name="descrizione" placeholder="Inserire la motivazione della segnalazione..." maxlength="2000"></textarea>
            <input type="submit" value="Segnala" id="segButton">
        </form>
        </div>
        <%}
    }%>
    </body>
