<!DOCTYPE html>
<html>
<head>
<title>Nuovo Annuncio</title>
<link rel="stylesheet" type="text/css" href="CSS/CreaNuovoAnnuncio.css">
<meta charset="ISO-8859-1">
<script type="text/javascript" src ="jquery.js"></script>
</head>
<body>

    <%String username = (String) request.getSession().getAttribute("Username");
      if(username == null){
                System.out.println("Username non trovato");
                username = "Default";
      }
      System.out.println(username);
    %>
  <div class="contna"> <!-- na vicino ai nomi delle classi sta per nuovo annuncio -->
    
    <h1 class="titlena">Crea Nuovo Annuncio</h1>
    <form class="togna" id="AnnuncioForm">
  <input type="radio" name="tipologia" value="Gruppo" checked="checked"> Gruppo di studio
      <br>
      <br>
  <input type="radio" name="tipologia" value="Tutorato"> Tutorato <!-- Da far diventare una form (propotipo) -->


  <select name="Dipartimento">
     <option>Informatica</option>
     <option>Farmacia</option>
     <option>Chimica</option>
     <option>Matematica</option>
   </select>
      <br>
      <br>
   <label for="titolo" class="titolo" >Titolo:</label> 
       <input type="text" name="titolo" placeholder="Titolo annuncio" required="required">
  
    <textarea class="txtna" maxlength="2000" name="descrizione" contenteditable="true" required="required"></textarea>
    
    <input type="hidden" name="usernameUtente" value="<%=username%>">
    
    <input type="submit" name="azione" value="creaAnnuncio" class="sfdbna" formaction="/BACHECAUNISA/AnnunciServlet">
  </script>
        </form>
  </div>
</body>