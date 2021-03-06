<%@page import="gestioneutenti.Utente"%>
<%@page import="gestioneutenti.SessioneUtente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Modifica profilo</title>
        <link rel="stylesheet" type="text/css" href="CSS/ModificaProfilo.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
        <script type="text/javascript" src ="jquery.js"></script>
    </head>
    <body>
        <%
        SessioneUtente su = (SessioneUtente) request.getSession().getAttribute("Utente");
            if (su == null) {
                response.sendRedirect(request.getContextPath() + "/Login.jsp");
            } else {
                Utente u = (Utente) request.getSession().getAttribute("utenteTrovato");
                if (u == null) {
                    response.sendRedirect("/BACHECAUNISA/UtenteServlet?azione=prelevaUtente&luogo=mod&username=" + su.getUsername());
                } else {
                    request.getSession().removeAttribute("utenteTrovato");  
        %>
        <div id="barraleft"><%@ include file="barraLEFTv2.jsp" %></div>
        <div id=modProfilo>
            <img class="avatarProf" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png"> <br/>		
            <form accept-charset="utf-8" id="modifyUser" name = "modify" class="modify" method="Post" action="/BACHECAUNISA/UtenteServlet?azione=modificaUtente" onsubmit="return conferma()">
                <fieldset class="fieldset">
                    <legend class="legend">   MODIFICA PROFILO   </legend>
                    <input type="text"  placeholder="Nome" maxlength="20" pattern="[a-zA-Z\s]{1,}" class="campo" name="nome" id = "nome" title="Solo lettere" value="<%= u.getNome()%>">
                    <input type="text" placeholder="Cognome" maxlength="20" pattern="[a-zA-Z\s]{1,}" class="campo" name="cognome" title="Solo lettere" value="<%= u.getCognome()%>">
                    <textarea rows="4" cols="50" id="textareaProfilo" name="descrizione" placeholder="Descrizione" maxlength="2000"><%= u.getDescrizione()%></textarea>
                    <input type="submit" value="Modifica Dati" id="modbutton">   
                    <script>
                        function conferma() {
                            var r = confirm("Conferma");
                            if (r === true) {
                                return true;
                            } else {
                                return false;
                            }
                        }
                    </script>
                </fieldset>
            </form>
                    
            <form accept-charset="utf-8" id="modifyPassword" name = "modify" class="modify" method="Post" action="/BACHECAUNISA/UtenteServlet?azione=modificaPassword" onsubmit="return conferma()">
                <fieldset class="fieldset">
                    <legend class="legend">   MODIFICA PASSWORD   </legend>
                    <input id="password" name="newPassword" type="password" placeholder="Password" maxlength="20" pattern=".{8,}" class="campo" name="password" required="required" title="Almeno 8 caratteri">
                    <input type="password" placeholder="Conferma password" maxlength="20" pattern=".{8,}" class="campo" name="confpassword" required="required" title="Almeno 8 caratteri" oninput="check(this)">
                    <script>
                        function check(input) {
                            if (input.value !== $("#password").val()) {
                                input.setCustomValidity("Le password non coincidono");
                            } else {
                                input.setCustomValidity("");
                            }
                        }
                    </script>      
                    <input type="submit" value="Modifica Password" id="modbutton">
                </fieldset>
            </form>

        </div>
                    <%}
      }%>
    </body>
</html>
