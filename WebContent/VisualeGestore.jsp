<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Visuale Gestore</title>
<style>

#listaAnnunci{
margin-top: 20px;
margin-left:20px;
}


#listaRecensioni{

margin-top: 20px;
margin-left:20px;
display:none;

}

#tabAnnunci, #tabRecensioni {
	background-color: #ddd;
	border: none;
	color: black;
	text-decoration: none;
	cursor: pointer;
	border-radius: 16px;
	font-size: 14px;
	width: 150px;
	height: 35px;
	
margin-top: 20px;
margin-left:20px;
}

</style>
</head>
<body>
<button id="tabAnnunci" onclick="switchAnnunci()">Annunci</button>
<button id="tabRecensioni" onclick="switchRecensioni()">Recensioni</button>

<div id="listaAnnunci">

ASASDADSADASAAAAAAAAAAAAAAAA

</div>


<div id="listaRecensioni">

BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB

</div>

<script type="text/javascript">
function switchAnnunci() {
	  var x = document.getElementById("listaRecensioni");
	    x.style.display = "none";
	    x = document.getElementById("listaAnnunci");
	    x.style.display = "block";
	}

function switchRecensioni() {
	  var x = document.getElementById("listaAnnunci");
	    x.style.display = "none";
	    x = document.getElementById("listaRecensioni");
	    x.style.display = "block";
	}


</script>
</body>
</html>