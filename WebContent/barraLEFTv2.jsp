<!DOCTYPE html>
<html>
<head><style type="text/css">.lw { font-size: 60px; }</style>


<meta charset="ISO-8859-1">
<title>Barra side</title>
<style>
#side {
	float: left;
	padding: 2%;
}

#avatar {
	display: block;
	border-radius: 50%;
	width: 150px;
	height: 150px;
	margin: 2px auto 0;
}

.button {
	background-color: #ddd;
	border: none;
	color: black;
	padding: 15px 30px;
	text-align: center;
	text-decoration: none;
	display: block;
	margin: 0px auto 0;
	cursor: pointer;
	border-radius: 16px;
	font-size: 14px;
	width: 150px;
}

.button:hover {
	background-color: #f1f1f1;
}
</style>
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
<button class="button" id="esc" onclick="logout()";>Esci</button>
<br>
<br>
<button class="button" id="faqside" onclick="location.href = 'FAQ.jsp';">Visualizza FAQ</button>
<br>
<br>
</div>




<script>// Write JavaScript here </script></body>