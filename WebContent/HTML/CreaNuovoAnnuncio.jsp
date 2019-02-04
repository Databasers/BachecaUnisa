<!DOCTYPE html>
<html>
<head>
</head><style type="text/css">
</style>


<style>

.contna{
 border-style: dashed;
  height: 80%;
  width: 100%;
  
}
.sfdbna{
  margin-left: auto;
 margin-right: auto;
  display: block;
  margin-bottom: 5%;
}

.titolo{
  font-weight: 800;
  font-size: larger;
}

.txtna{
  margin: 2%;
  border-style: solid;
  height: 50%;
  min-height: 250px;
  overflow-y: auto;
}



.titlena{
  text-align: center;
}

.togna{
  margin-left: 7%;
}

.selectna{
  margin-left: 7%;
  margin-top: 20px;
}


.txtna:focus {outline:0;}

</style>
<meta charset="ISO-8859-1">
<title>login</title>
</head>
<body>
  <div class="contna"> <!-- na vicino ai nomi delle classi sta per nuovo annuncio -->
    
    <h1 class="titlena">Crea Nuovo Annuncio</h1>
    <form action="" class="togna">
  <input type="radio" name="tipologia" value="Gruppo"> Gruppo di studio
      <br>
      <br>
  <input type="radio" name="tipologia" value="Tutorato"> Tutorato <!-- Da far diventare una form (propotipo) -->
</form>
    <form class="selectna">
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
  
    <div class="txtna" contenteditable="true"><div><br></div></div>
    <input type="submit" value="Crea annuncio" class="sfdbna">
        </form>
  </div>


<script>// Write JavaScript here </script></body>