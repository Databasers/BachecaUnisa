<!DOCTYPE html>
<html>
<head>
<title> Nuovo Annuncio</title>
<style type="text/css">

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
  width: 100%;
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
<script type="text/javascript" src ="jquery.js"></script>
<title>login</title>
</head>
<body>
  <div class="contna"> <!-- na vicino ai nomi delle classi sta per nuovo annuncio -->
    
    <h1 class="titlena">Crea Nuovo Annuncio</h1>
    <form class="togna" name="AnnuncioForm">
  <input type="radio" name="tipologia" value="Gruppo" checked="checked"> Gruppo di studio
      <br>
      <br>
  <input type="radio" name="tipologia" value="Tutorato"> Tutorato <!-- Da far diventare una form (propotipo) -->


  <select name="Dipartimento">
     <option>Dipartimento1</option>
     <option>Dipartimento2</option>
     <option>Dipartimento3</option>
     <option>Dipartimento4</option>
   </select>
      <br>
      <br>
   <label for="titolo" class="titolo" >Titolo:</label> 
       <input type="text" name="titolo" placeholder="Titolo annuncio" required="required">
  
    <textarea class="txtna" maxlength="2000" name="descrizione" contenteditable="true" required="required"></textarea>
    
    <input type="submit" value="Crea annuncio" class="sfdbna" onclick="check()">
    
    <%String username = (String) request.getSession().getAttribute("Username");
      if(username == null){
                System.out.println("Username non trovato");
                username = "Default";
      }
      System.out.println(username);
    %>
    <script>
  function check() {
   var tipologia = $("input[name=tipologia]:selected").val();
   var dipartimento;
   if(tipologia === "Tutorato"){
      dipartimento = $("select[name=Dipartimento]").val();
      } else {
        dipartimento = "Informatica";
      }
   var titolo = $("input[name=titolo]").val();
   var descrizione = $("textarea[name=descrizione]").val();
   var username = $("input[name=Username]").val();
   console.log(tipologia + " " + titolo + " " + descrizione);
   
     var azione = "/BACHECAUNISA/AnnunciServlet?azione=creaAnnuncio&usernameUtente=" + username + "&dipartimento=" + dipartimento + "&titolo=" + titolo + "&descrizione=" + descrizione + "&tipologia=" + tipologia;
     console.log(azione);
     $("form[name=AnnuncioForm]").action = azione;
     $("form[name=AnnuncioForm]").submit();
   
  }
  </script>
        </form>
  </div>
</body>