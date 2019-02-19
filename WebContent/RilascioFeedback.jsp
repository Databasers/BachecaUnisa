<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="CSS/RilascioFeedback.css">

<title>Rilascio Feedback</title>
</head>
<body>
  <img id="avatar" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
  <p style="text-align:center">Stai rilasciando feedback a:
   </p> <br>
  <div class="utentetxt">
    <h1 id="Utenteinfo">Nome Utente<br></h1>
  </div>
  
  <form class="togna">
  <textarea id="textarea" maxlength="2000" name="descrizione">Inserisci qui la tua recensione!</textarea>
    <h1 class="tit">Valutazione:</h1>

  	<div class="star-rating">
			<input id="star-5" type="radio" name="rating" value="star-5">
			<label for="star-5" title="5 stars">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
			<input id="star-4" type="radio" name="rating" value="star-4">
			<label for="star-4" title="4 stars">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
			<input id="star-3" type="radio" name="rating" value="star-3">
			<label for="star-3" title="3 stars">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
			<input id="star-2" type="radio" name="rating" value="star-2">
			<label for="star-2" title="2 stars">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
			<input id="star-1" type="radio" name="rating" value="star-1">
			<label for="star-1" title="1 star">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
		</div>
    
    
  <div id="visualizzafeed"><input type="submit" value="Rilascia Feedback" onClick="ceck()"></div>
  </form>
  
  

<script>
function ceck() {
	var stelle;
	var rate = $("rating");
	if (rating[0].checked){
		stelle = rating[0].value;
	} else if (rating[1].checked){
		stelle = rating[1].value;
	} else if (rating[2].checked){
		stelle = rating[2].value;
	} else if (rating[3].checked){
	stelle = rating[3].value;
	} else if (rating[4].checked){
	stelle = rating[4].value;
	}
	
	var descrizione = $(div[name="descrizione"]).val();
	
	var azione = "/BACHECAUNISA/gestioneannunci/RecensioniServlet?action=creaRecensione&usernameUtente=" + session.getAttribute("username") 
    + "&stelle=" + stelle + "&descrizione=" + descrizione;
	document.togna.action = azione;
	document.togna.submit();
	
</script>
</body>