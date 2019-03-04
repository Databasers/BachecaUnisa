<!DOCTYPE html>
<%@page import="gestionerecensioni.Recensione"%>
<%@page import="gestioneutenti.SessioneUtente"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Segnala Annuncio</title>
        <link rel="stylesheet" type="text/css" href="CSS/SegnalaRecensione.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    </head>
    <body>
        <div id="barraleft"><%@ include file="barraLEFTv2.jsp" %></div>
        <%
            SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
            if (su == null) {
                response.sendRedirect(request.getContextPath() + "/Login.jsp");
            } else {
                Recensione x = (Recensione) request.getSession().getAttribute("recensioneTrovata");
                if (x == null) {
                    System.out.println("X non esiste");
                    response.sendRedirect("/BACHECAUNISA/RecensioniServlet?azione=visualizzarecensione&luogo=se&id=" + request.getParameter("id"));
                } else {
                    request.getSession().removeAttribute("recensioneTrovata");
        %>

        <div class="container">
            <fieldset class="fieldset">
                    <legend class="legend">   STAI SEGNALANDO: </legend>
                    <img id="avatarProf" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
                    <div class="annuncio">
                        <h1 id="titoloAnnuncio"><%=x.getMittente()%></h1>
                        <textarea id="textareaAnnuncio" disabled="disabled"><%=x.getDescrizione()%></textarea>
                        <div class="star-rating" >  
                    <input id="star-5" type="radio" name="rating" value="5" disabled="disabled" <%if(x.getValutazione()==5){%> checked="checked" <%}%>>
                    <label for="star-5" title="5 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-4" type="radio" name="rating" value="4" disabled="disabled" <%if(x.getValutazione()==4){%> checked="checked" <%}%>>
                    <label for="star-4" title="4 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-3" type="radio" name="rating" value="3" disabled="disabled" <%if(x.getValutazione()==3){%> checked="checked" <%}%>>
                    <label for="star-3" title="3 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-2" type="radio" name="rating" value="2" disabled="disabled" <%if(x.getValutazione()==2){%> checked="checked" <%}%>>
                    <label for="star-2" title="2 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-1" type="radio" name="rating" value="1" disabled="disabled" <%if(x.getValutazione()==1){%> checked="checked" <%}%>>
                    <label for="star-1" title="1 star">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    </div>
                    </div>
                    
            </fieldset>    

        <form accept-charset="utf-8" id="formSegnalaannuncio" method="Post" action="/BACHECAUNISA/SegnalazioniServlet">
            <input type="hidden" name="azione" value="creaSegnalazione">
            <input type="hidden" name="recensione" value= "<%=x.getId()%>"> 
            <input type="hidden" name="annuncio" value="">
            <input type="hidden" name="luogo" value="no">

            <div class="checkboxes">
                <input type="radio" name="motivazione" value="0" checked="checked"> La recensione contiene un linguaggio volgare o inappropriato.<br>
                <input type="radio" name="motivazione" value="1"> La recensione non e' veritiera, non ho mai interagito con il recensore.<br>
                <input type="radio" name="motivazione" value="2"> Non e' possibile contattare il recensore per chiarimenti.<br>  
                <input type="radio" name="motivazione" value="3"> Altro.<br> 
            </div>
            <textarea  class="textarea" maxlength="2000" name="descrizione"></textarea>
            <div style="text-align:center">
                <input id="segnala" type="submit" value="Segnala" class="segButton">
            </div>

        </form>
        </div>
        <%}
    }%>
    </body>
