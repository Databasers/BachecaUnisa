<%@page import="java.util.Iterator"%>
<%@page import="gestionerecensioni.Recensione"%>
<%@page import="java.util.ArrayList"%>
<%@page import="gestioneutenti.SessioneUtente"%>
<%@page import="gestioneutenti.Utente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="CSS/ProfiloUtente.css">

        <title>Profilo Utente</title>

    </head>
    <body>
        <div id="barraleft"><%@ include file="barraLEFTv2.jsp" %></div>

        <%
            SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
            if (su == null) {
                response.sendRedirect(request.getContextPath() + "/Login.jsp");
            } else {
                Utente u = (Utente) request.getSession().getAttribute("utenteTrovato");
                ArrayList<Recensione> lista = (ArrayList<Recensione>) request.getSession().getAttribute("recensioni");
                if (u == null) {
                    response.sendRedirect("/BACHECAUNISA/UtenteServlet?azione=prelevaUtente&luogo=ut&username=" + request.getParameter("username"));
                } else if (lista == null){
                  System.out.println("Lista non trovata");
                  response.sendRedirect("/BACHECAUNISA/RecensioniServlet?azione=recensioniUtente&luogo=no&username=" + request.getParameter("username"));
                } else {
                    if (su.getUsername().equals(u.getUsername()))response.sendRedirect("ProfiloPersonale.jsp");
                    request.getSession().removeAttribute("utenteTrovato");
                    request.getSession().removeAttribute("recensioni");
                    int media = u.getMedia();
                    System.out.println("Media: " + media);
                    System.out.println("Dimensione lista " + lista.size()       );
        %>
        <div class="utentetxt">
            <img id="avatarProf" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
            <div class="star-rating" >
                <span class="numRec">(<%=lista.size()%>)</span>
                    <input id="star-5" type="radio" name="valutazione" value="5" disabled="disabled" <%if(media==5){%> checked="checked" <%}%>>
                    <label for="star-5" title="5 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-4" type="radio" name="valutazione" value="4" disabled="disabled" <%if(media==4){%> checked="checked" <%}%>>
                    <label for="star-4" title="4 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-3" type="radio" name="valutazione" value="3" disabled="disabled" <%if(media==3){%> checked="checked" <%}%>>
                    <label for="star-3" title="3 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-2" type="radio" name="valutazione" value="2" disabled="disabled" <%if(media==2){%> checked="checked" <%}%>>
                    <label for="star-2" title="2 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-1" type="radio" name="valutazione" value="1" disabled="disabled" <%if(media==1){%> checked="checked" <%}%>>
                    <label for="star-1" title="1 star">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>    
            </div>  
            <h1 id="nomecognome"><%=u.getNome()%> <%=u.getCognome()%></h1>
            <h3 id="Utenteinfo"><%=u.getUsername()%></h3>
            <textarea id="textarea" disabled="disabled"><%=u.getDescrizione()%></textarea>
            <form accept-charset="utf-8" action="">
            <input type="hidden" name="username" value="<%=request.getParameter("username")%>">
            <% if (!su.getUsername().equals(u.getUsername())) {%><button id="riFeed" type="submit" formaction="/BACHECAUNISA/RilascioFeedback.jsp?es=false">Rilascia Feedback</button><%}%>
        </form>
            <fieldset class="fieldset">
                <legend class="legend">   FEEDBACK DI <%=u.getUsername()%>   </legend>
                <%if (lista.isEmpty()) {%>
                <div class="legend">Non ci sono ancora recensioni per questo utente</div>
                <%} else {
                    Recensione[] a = lista.toArray(new Recensione[0]);
                    int index= 0;
                    for (Recensione x : a) { index++;%>
                <div id="Recensione">
                    <div id="Mittenteinfo"><a href="/BACHECAUNISA/ProfiloUtente.jsp?username=<%=x.getMittente()%>"><%=x.getMittente()%></a></div>
                    <textarea id="textareaDescrizione" disabled="disabled"><%=x.getDescrizione()%></textarea>
                    
                    <div class="star-rating" >  
                    <input id="star-5" type="radio" name="rating<%=index%>" value="5" disabled="disabled" <%if(x.getValutazione()==5){%> checked="checked" <%}%>>
                    <label for="star-5" title="5 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-4" type="radio" name="rating<%=index%>" value="4" disabled="disabled" <%if(x.getValutazione()==4){%> checked="checked" <%}%>>
                    <label for="star-4" title="4 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-3" type="radio" name="rating<%=index%>" value="3" disabled="disabled" <%if(x.getValutazione()==3){%> checked="checked" <%}%>>
                    <label for="star-3" title="3 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-2" type="radio" name="rating<%=index%>" value="2" disabled="disabled" <%if(x.getValutazione()==2){%> checked="checked" <%}%>>
                    <label for="star-2" title="2 stars">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    <input id="star-1" type="radio" name="rating<%=index%>" value="1" disabled="disabled" <%if(x.getValutazione()==1){%> checked="checked" <%}%>>
                    <label for="star-1" title="1 star">
                        <i class="active fa fa-star" aria-hidden="true"></i>
                    </label>
                    </div>
                    <% if (x.getMittente().equals(su.getUsername())) { %>
                    <form action="modificaRecensione.jsp">
                    <input type="hidden" name = "id" value="<%=x.getId()%>">
                    <button class="modButton" type="submit">Modifica</button>
                    </form><%}%> 
                </div>
                <%}
                    } %>
            </fieldset>
        </div> 
        <%}}%>
    </body>
</html>



