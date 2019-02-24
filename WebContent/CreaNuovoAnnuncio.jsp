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
                
                <label>Gruppo di studio<input type="radio" checked="checked" name="radio"></label>
                <label>Tutorato<input type="radio"name="radio"></label>
                <select>
                        <option value="0">Dipartimento:</option>
                        <option value="1">Informatica</option>
                        <option value="2">Ingegneria</option>
                        <option value="3">Farmacia</option>
                        <option value="4">Lettere</option>
                </select>
                <input type="text" class="inputtitle" name="titolo" placeholder="Titolo" pattern="[a-zA-Z\s]{1,}" required="required" title="Solo lettere">
                <textarea class="txtna" maxlength="2000" name="descrizione" contenteditable="true" required="required"></textarea>
                <input type="hidden" name="usernameUtente" value="<%=username%>">
                <input type="submit" name="azione" value="Crea annuncio" class="sfdbna" formaction="/BACHECAUNISA/AnnunciServlet">
            </form>
        </div>
        <%} %>
    </body>