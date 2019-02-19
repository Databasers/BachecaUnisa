<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Barra side</title>
<link rel="stylesheet" type="text/css" href="CSS/BarraLeft.css">
</head>
<body>

<div id="side">
<img id="avatar" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
<br>
<br>
<button class="button" id="home"  onclick="location.href = 'Homepage.jsp';">Home</button>
<br>
<br>
<button class="button" id="profilo" onclick="location.href = 'ProfiloPersonale.jsp';">Profilo</button>
<br>
<br>
<button class="button" id="annunci" onclick="location.href = 'AnnunciPersonali.jsp';">I Miei Annunci</button>
<br>
<br>
<form action="" id = "lo">
<button class="button" id="esc" onclick="logout()">Esci</button>
</form>
<br>
<br>
<button class="button" id="faqside" onclick="location.href = 'FAQ.jsp';">Visualizza FAQ</button>
<br>
<br>
</div>

<script type="text/javascript">
function logout() {
    const elem = document.getElementById("lo");
    elem.action = "/BACHECAUNISA/UtenteServlet?azione=Logout";
    elem.submit();
}
</script>




<script>// Write JavaScript here </script></body>