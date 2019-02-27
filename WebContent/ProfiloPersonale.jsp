<%@page import="gestioneutenti.Utente"%>
<%@page import="gestioneutenti.SessioneUtente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <title>Profilo Personale</title>
        <link rel="stylesheet" type="text/css" href="CSS/ProfiloPersonale.css">
    </head>
    <body>
        <div id="barraleft"><%@ include file="barraLEFTv2.jsp" %></div>

        <%
            SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
            System.out.println(su.getDescrizione());
            if (su == null) {
                response.sendRedirect(request.getContextPath() + "/Login.jsp");
            } else {
                Utente u = (Utente) request.getSession().getAttribute("utenteTrovato");

                if (u == null) {
                    response.sendRedirect("/BACHECAUNISA/UtenteServlet?azione=prelevaUtente&luogo=pro&username=" + su.getUsername());
                } else {
                    request.getSession().removeAttribute("utenteTrovato");
                                    System.out.println(u.getDescrizione());
                    int media= u.getMedia();
        %>


        <div class="utentetxt">
            <img id="avatarProf" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
            <div class="star-rating" >
                    <input id="star-5" type="radio" name="rating" value="5" disabled="disabled" <%if(media==5){%> checked="checked" <%}%>>
                    <label for="star-5" title="5 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-4" type="radio" name="rating" value="4" disabled="disabled" <%if(media==4){%> checked="checked" <%}%>>
                    <label for="star-4" title="4 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-3" type="radio" name="rating" value="3" disabled="disabled" <%if(media==3){%> checked="checked" <%}%>>
                    <label for="star-3" title="3 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-2" type="radio" name="rating" value="2" disabled="disabled" <%if(media==2){%> checked="checked" <%}%>>
                    <label for="star-2" title="2 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-1" type="radio" name="rating" value="1" disabled="disabled" <%if(media==1){%> checked="checked" <%}%>>
                    <label for="star-1" title="1 star">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
            </div>
            
            <h1 id="nomecognome"><%=u.getNome()%> <%=u.getCognome()%></h1>
            <h3 id="Utenteinfo"><%=u.getUsername()%></h3>
            <button id="modPro" onclick="location.href = 'modificaProfilo.jsp'">Modifica Profilo</button> 
            <textarea id="textarea" disabled="disabled"><%=u.getDescrizione()%></textarea>
            <button id="visFeed" onclick="location.href = 'VisualizzaFeedback.jsp'">Visualizza Feedback</button>
        </div>
        <%}
      }%>
    </body>
</html>




