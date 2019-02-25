<!DOCTYPE html>
<%@page import="gestioneannunci.Annuncio"%>

<%@page import="gestioneutenti.SessioneUtente"%>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Visualizza Annuncio</title>
        <link rel="stylesheet" type="text/css" href="CSS/VisualizzaAnnuncio.css">
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
            System.out.println("Siamo in visualizza annuncio");
            if (x == null) {

                System.out.println("X non esiste");
                String checazzo = "/BACHECAUNISA/AnnunciServlet?azione=visualizzannuncio&luogo=vi&id=" + request.getParameter("id");
                System.out.println(checazzo);
                response.sendRedirect(checazzo);
            } else {
                request.getSession().removeAttribute("annuncioTrovato");
        %>
        <div class="annuncio">
            <img id="avatarannuncio" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
            <br>
            <span id="nomeutente"><%=x.getUsernameUtente()%></span>
            <div class="annuncioInfo">
                <h2 id="annuncioTitle"><%=x.getTitolo()%><br></h2>
                <span class="dipartimento"><%=x.getDipartimento()%></span>
                <%if (x.isTipologia()) {%> <p class="etichettaTutorato">ATTIVITA' DI TUTORATO</p>
                <%} else {%> <p class="etichettaGruppo">GRUPPO DI STUDIO</p> <%}%>
            </div>
            <p class="txtannuncio"><%=x.getDescrizione()%></p>
            <div id="segnalaannuncio"><form action="">
                    <input type="hidden" name = "id" value = "<%= x.getId()%>">
                    <input class="valuebutton" type="submit" value="Segnala Annuncio" formaction="/BACHECAUNISA/segnalaAnnuncio.jsp"></form>
            </div>
        </div>



        <%}}%>
    </body>

