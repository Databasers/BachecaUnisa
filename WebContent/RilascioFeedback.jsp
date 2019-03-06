<!DOCTYPE html>
<%@page import="gestioneutenti.SessioneUtente"%>
<%@page import="gestioneutenti.Utente"%>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="CSS/RilascioFeedback.css">
        <script type="text/javascript" src ="jquery.js"></script>
        <title>Rilascio Feedback</title>
    </head>
    <body>
        <div id="barraleft"><%@ include file="barraLEFTv2.jsp" %></div>
        <%
            SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
            if (su == null) {
                response.sendRedirect(request.getContextPath() + "/Login.jsp");
            } else {
                Utente u = (Utente) request.getSession().getAttribute("utenteTrovato");
                if (u == null) {
                    System.out.println(request.getContextPath() + "/UtenteServlet?azione=prelevaUtente&luogo=feed&username=" + request.getParameter("username"));
                    response.sendRedirect(request.getContextPath() + "/UtenteServlet?azione=prelevaUtente&luogo=feed&username=" + request.getParameter("username"));
                } else {
                    Boolean es = Boolean.valueOf(request.getParameter("es"));
                    request.getSession().removeAttribute("utenteTrovato");

        %>
        <%if (es) {%>
        <div>Mammt</div>
        <%} %>
        <div class="container">
            <fieldset class="fieldset">
                    <legend class="legend">   STAI RILASCIANDO FEEDBACK A : </legend>
                    <img class="avatarProf" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
                    <div class="utentetxt">
                        <h1 id="nomecognome"><%=u.getNome()%> <%=u.getCognome()%></h1>
                        <h3 id="Utenteinfo"><%=u.getUsername()%></h3>
                    </div>               
            </fieldset>    
            <form accept-charset="utf-8" class="togna" name = "FeedForm">
                <textarea id="textarea" maxlength="2000" name="descrizione" placeholder="Inserisci qui la tua recensione..."></textarea>
                <p class="tit">Valutazione</p>
                <div class="star-rating">
                    <input id="star-5" type="radio" name="rating" value="5">
                    <label for="star-5" title="5 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-4" type="radio" name="rating" value="4">
                    <label for="star-4" title="4 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-3" type="radio" name="rating" value="3" checked="checked">
                    <label for="star-3" title="3 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-2" type="radio" name="rating" value="2">
                    <label for="star-2" title="2 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-1" type="radio" name="rating" value="1">
                    <label for="star-1" title="1 star">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                </div>
                <input type="hidden" name = "mittente" value = "<%=((SessioneUtente) request.getSession().getAttribute("Utente")).getUsername()%>">
                <input type="hidden" name = "destinatario" value = "<%=u.getUsername()%>">
                <input type="hidden" name="azione" value="creaRecensione">
                <div id="visualizzafeed"><input id="feedButton" type="submit" value="Rilascia Feedback" formaction="/BACHECAUNISA/RecensioniServlet"></div>
            </form>
            <%}
            }%>
        </div>
    </body>