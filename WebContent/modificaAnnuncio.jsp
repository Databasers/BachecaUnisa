<!DOCTYPE html>
<html>
<head>
</head><style type="text/css">
</style>


<style>

.contna{
 border-style: solid;
  height: 80%;
  width: 100%;
  text-align:left;
  
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
  border-style: solid;
  height: 50%;
  min-height: 250px;
  overflow-y: auto;
  width: 90%;
  margin-right: 10%;
}



.titlena{
  text-align: left;
}

.togna{
  margin-left: 7%;
}

.selectna{
  margin-left: 7%;
  margin-top: 20px;
}

#oldAnnuncio{
border-style:solid;
min-height:100px;
height: 30%;
width: 100%;
}

.txtna:focus {outline:0;}

</style>
<meta charset="ISO-8859-1">
<title>login</title>
</head>
<body>
  <h3 class="titlena">Stai modificando l'annuncio:</h3>
     <p id="oldAnnuncio"> </p>
  <div class="contna"> <!-- na vicino ai nomi delle classi sta per nuovo annuncio -->
    
  
 <form action="">
    <div action="" class="togna">
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
    <input type="submit" value="Modifica annuncio" class="sfdbna">
    </div>
    
        </form>
  </div>


<script>// Write JavaScript here </script></body>