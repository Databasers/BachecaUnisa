<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Homepage</title>
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