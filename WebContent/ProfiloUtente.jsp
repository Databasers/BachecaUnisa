<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="CSS/ProfiloUtente.css">

<title>Profilo Utente</title>

</head>
<body>
  <%@ include file="barraLEFTv2.jsp" %>
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
 
  

  <div class="utentetxt">
    <img id="avatar" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
    <h1 id="Utenteinfo">Nome Utente<br>
Indirizzo<br>
Provincia</h1>
  </div>
  
<textarea id="textarea" disabled="disabled">Qui andrà la descrizione dell'utente.</textarea>
  
 <span style="width: 49%; text-align:left">
 <button id="visAnn" onclick="location.href = 'VisualizzaAnnunci.jsp';">Visualizza Annunci</button>
 </span>
 <span style="width:50%; text-align: right">
 <button id="riFeed" onclick="location.href = 'RilasciaFeedback.jsp';">Rilascia Feedback</button>
</span>

<script>// Write JavaScript here </script></body>