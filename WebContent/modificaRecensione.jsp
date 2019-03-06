<!DOCTYPE html>
<%@page import="gestioneutenti.SessioneUtente"%>
<%@page import="gestionerecensioni.Recensione"%>
<html>
    <head>
        <title>Modifica Annuncio</title>
        <link rel="stylesheet" type="text/css" href="CSS/ModificaRecensione.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">       
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
        <meta charset="UTF-8">
    </head>
    <body>
        <%
            System.out.println("Entrati in modifica recensione pagina");
            Recensione x = (Recensione) request.getSession().getAttribute("recensioneTrovata");
            SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
            if (su == null) {
              response.sendRedirect(request.getContextPath() + "/Login.jsp");
            } else  if (x == null) {
              System.out.println("Recensione non trovata");
              response.sendRedirect("/BACHECAUNISA/RecensioniServlet?azione=visualizzarecensione&luogo=mo&id=" + request.getParameter("id"));
            } else {
              request.getSession().removeAttribute("recensioneTrovata");
              
        %>
        <div id="barraleft"><%@ include file="barraLEFTv2.jsp" %></div>
        <div class="contna" > <!-- na vicino ai nomi delle classi sta per nuovo annuncio -->
            <p id="oldAnnuncio"> </p>
            <form accept-charset="utf-8" action="/BACHECAUNISA/RecensioniServlet" id = "form">
                <input type="hidden" name = "azione" value="modificaRecensione">
                <input type="hidden" name="mittente" value="<%=x.getMittente()%>">
                <input type="hidden" name="id" value="<%=x.getId()%>">
                <input type="hidden" name="destinatario" value="<%=x.getDestinatario()%>">
                <textarea id="textarea" name="descrizione" form="form" class="txtna"><%= x.getDescrizione()%></textarea>
                <div class="star-rating">
                    <input id="star-5" type="radio" name="rating" value="5" <%if(x.getValutazione()==5){%> checked="checked" <%}%>>
                    <label for="star-5" title="5 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-4" type="radio" name="rating" value="4" <%if(x.getValutazione()==4){%> checked="checked" <%}%>>
                    <label for="star-4" title="4 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-3" type="radio" name="rating" value="3" <%if(x.getValutazione()==3){%> checked="checked" <%}%>>
                    <label for="star-3" title="3 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-2" type="radio" name="rating" value="2" <%if(x.getValutazione()==2){%> checked="checked" <%}%>>
                    <label for="star-2" title="2 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-1" type="radio" name="rating" value="1" <%if(x.getValutazione()==1){%> checked="checked" <%}%>>
                    <label for="star-1" title="1 star">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                </div>
                <input id="feedButton" type="submit" value="Modifica recensione" class="sfdbna">
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