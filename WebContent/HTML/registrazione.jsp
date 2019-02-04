<!DOCTYPE html>
<html>
<head><style type="text/css">.lw { font-size: 60px; }</style>


<style>
#container, #logo{
margin-top: 30px;
}
#container{
padding-top:12px;
padding-left: 12px;
display: block;
  margin-left: auto;
  margin-right: 56px;
  float:right;
  border-style: solid;
  width: 30%; 
  height: auto;
  padding-bottom: 7%;
}
    
.campo{
  float: right;
margin-right: 10%;
width: 50%;
}
  
#logo img{
display: block;
margin-left: auto;
margin-right: auto;
}
  
#logo{
border-style:solid;
  width: 50%;
  height:auto;
  float: left;
  margin-left: 3%;
}
  
#faq{

}

#sextext{
    float: left;
}
  
 
#sex{
float: right;
margin-right: 10%;
width: 50%;
  }
  
#field{
margin-bottom: 5px;
}

#sextext,#sex{
position:relative;
margin-top:-25px;
}

#regbutton{
    float:left;

}
</style>
<meta charset="ISO-8859-1">
<title>Registrazione</title>
</head>
<body>
<div id="logo">
<img alt="Logo" src="https://nextindustry.net/wp-content/uploads/2018/01/Logo_TV_2015.png">
</div>
<div id="container">   
	<div id="field">   
	<form>
		<label for="nome">Nome:</label>
		<input type="text" class="campo" name="Nome">
		<br>
		<br>
		<label for="cognome">Cognome:</label>
		<input type="text" class="campo" name="cognome">
			
	</form>
	</div> 
	<br>
	<br>
	
	<div id="sextext">Sesso:</div>
		<form id="sex" action="Seleziona sesso" enctype="text/plain">
	 <select name="sesso">
	<option>maschio
	</option><option>femmina
	</option><option>altro
	</option></select>
		</form>
		
		<form>
		<label for="username">Username:</label>
		<input id="nomefield" type="text" class="campo" name="Nome">
		<br>
		<br>
		<label for="password">Password:</label>
		<input type="text" class="campo" name="password">
		<br>
		<br>
		<label for="password">Conferma <br>Password:</label>
		<input type="text" class="campo" name="confpassword">
		<br>
		<br>
		<label for="checkbox">Conferma <br>Regolamento</label>
		<input type="checkbox" class="campo" name="ceckbox">
		<br>
		<br>
		<input type="submit" value="Registrati" id="regbutton">
		<br>
		<br>
		<input type="submit" value="Visualizza FAQ" id="faq">
		
	</form>
		
	
</div>


<script>// Write JavaScript here </script></body>