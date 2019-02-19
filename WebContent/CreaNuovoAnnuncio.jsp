<!DOCTYPE html>
<%@page import="gestioneutenti.Utente"%>
<%@page import="gestioneutenti.SessioneUtente"%>
<html>
<head>
<title>Nuovo Annuncio</title>
<link rel="stylesheet" type="text/css" href="CSS/CreaNuovoAnnuncio.css">
<meta charset="ISO-8859-1">
<script type="text/javascript" src ="jquery.js"></script>
</head>
<body>

  <div class="contna"> <!-- na vicino ai nomi delle classi sta per nuovo annuncio -->
    <%
      Utente h = (Utente) request.getSession().getAttribute("utenteTrovato");
      String username = null;
      if (h == null) {
        SessioneUtente u = (SessioneUtente) request.getSession().getAttribute("Utente");
        response.sendRedirect("/BACHECAUNISA/UtenteServlet?azione=prelevaUtente&luogo=crea&username=" + u.getUsername());
      } else if (h.getNumAnnunci() > 4) {
     %>
    <script type="text/javascript">
     $("input[name=azione]").prop("disabled", true);
    </script>
    <%} else {
      username = h.getUsername();
    }%>
    
    <h1 class="titlena">Crea Nuovo Annuncio</h1>
    <form class="form-inline" id="AnnuncioForm">
  <label class="container">Gruppo di studio<input type="radio" checked="checked" name="radio">
		<span class="checkmark"></span>
		</label>
                <label class="container">Tutorato<input type="radio"
			name="radio"> <span class="checkmark"></span>
		</label>
     
<div class="custom-select">
  <select>
     <option value="0">Dipartimento:</option>
     <option value="1">Informatica</option>
     <option value="2">Ingegneria</option>
     <option value="3">Farmacia</option>
     <option value="4">Lettere</option>
   </select>
</div>

   <label for="titolo" class="titolo" >Titolo:</label> 
       <input type="text" name="titolo" placeholder="Titolo annuncio" required="required">
       
  
    <textarea class="txtna" maxlength="2000" name="descrizione" contenteditable="true" required="required"></textarea>
    
    <input type="hidden" name="usernameUtente" value="<%=username%>">
    
    <input type="submit" name="azione" value="Crea annuncio" class="sfdbna" formaction="/BACHECAUNISA/AnnunciServlet">
</form>

 
  </div>
</body>