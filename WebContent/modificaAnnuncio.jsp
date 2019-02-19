<!DOCTYPE html>
<html>
<head>
<title>Modifica Annuncio</title>
<link rel="stylesheet" type="text/css" href="CSS/ModificaAnnuncio.css">
<meta charset="ISO-8859-1">
</head>
<body>
  <h3 class="titlena">Stai modificando l'annuncio:</h3>
     <p id="oldAnnuncio"> </p>
  <div class="contna"> <!-- na vicino ai nomi delle classi sta per nuovo annuncio -->
    
  
 <form action="">
    <div class="togna">
  <input type="radio" name="tipologia" value="Gruppo"> Gruppo di studio
      <br/>
      <br/>
  <input type="radio" name="tipologia" value="Tutorato"> Tutorato <!-- Da far diventare una form (propotipo) -->
</div>
    <div class="selectna">
      <select name="Dipartimento">
        <option>Dipartimento1</option>
        <option>Dipartimento2</option>
        <option>Dipartimento3</option>
        <option>Dipartimento4</option>
      </select>
      <br>
      <br>
      <label for="titolo" class="titolo">Titolo:</label> 
       <input type="text" name="titolo" placeholder="Titolo annuncio">
       <br/>
  <br/>
    <textarea class="txtna"></textarea>
  
     <br/> <br/>
    <input type="submit" value="Modifica annuncio" class="sfdbna" onclick="check()">
    </div>
    
        </form>
  </div>


<script>
function ceck() {
	   var tipologia = $("input[name=tipologia]:selected").val());
	   var dipartimento;
	   if(tipologia == "Tutorato"){
	      dipartimento = $(select[name="Dipartimento"]).val();
	      } else {
	        dipartimento = "Informatica";
	      }
	   var titolo = $("label[name=titolo]").val();
	   var descrizione = $(div[name="descrizione"]).val();
	   
	   var azione = "/BACHECAUNISA/gestioneannunci/AnnunciServlet?action=modificaAnnuncio&usernameUtente=" + session.getAttribute("username") 
	       + "&dipartimento=" + dipartimento + "&titolo=" + titolo + "&descrizione=" + descrizione + "&tipologia=" + tipologia;
	   document.togna.action = azione;
	   document.togna.submit();
	   
	  }
</script>
</body>