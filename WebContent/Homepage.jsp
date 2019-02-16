<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>
<style>

#listaAnnunci{

margin-top: 20px;
margin-left:20px;

}


#listaProfili{

margin-top: 20px;
margin-left:20px;
display:none;

}

#tabAnnunci, #tabProfilo{
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
<button id="tabProfilo" onclick="switchProfilo()">Profilo</button>

<div id="listaAnnunci">

ASASDADSADASAAAAAAAAAAAAAAAA

</div>


<div id="listaProfili">

BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB

</div>

<script type="text/javascript">
function switchAnnunci() {
	  var x = document.getElementById("listaProfili");
	    x.style.display = "none";
	    x = document.getElementById("listaAnnunci");
	    x.style.display = "block";
	}

function switchProfilo() {
	  var x = document.getElementById("listaAnnunci");
	    x.style.display = "none";
	    x = document.getElementById("listaProfili");
	    x.style.display = "block";
	}


</script>
</body>
</html>