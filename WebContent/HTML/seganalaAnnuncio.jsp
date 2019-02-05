<!DOCTYPE html>
<html>
<head><style type="text/css">.lw { font-size: 60px; }</style>



<title>HTML, CSS and JavaScript demo</title>

  <style> 


h1 { font-size: 1.5em; margin: 10px; }


    
    #avatarannuncio{
border-radius: 50%;
width: 150px;
height:150px;
margin-left: 12px;
  
}
    
    
 .titleannuncio{
   border-style: solid;
   text-align: left;
      margin: 1%;
    }
    .txtannuncio{
    margin: 1%;
    border-style: solid;
    height: 50%;
    min-height: 250px;
    overflow-y: auto;
      font-size: 150%;
}
   
     #segnala{
     margin-right: auto;
     margin-left: auto;
     display: block;
     margin-top: 2%;
      height: 8%;
      width: 25%;
      min-height: 70px;
    }
    .avatarcont{
      border-style: solid;
      margin: 1%;
      padding: 0.5%;
      
    }
    
    #textannunciosegn {
     float: right;
      border-style: solid;
      margin-right: 1%;
      height: 14%;
      width: 78%;
      min-height: 123px;
    }
    
    
    #textarea {
    width: 100%;
    height: 40%;
    overflow: auto;
    height: 300px;
    
}
   
  </style>
  
</head>
<body>
  
  
  
  <h3>Stai segnalando l'annuncio:</h3>
 <div class="avatarcont"> <img id="avatarannuncio" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
  
  <p id="textannunciosegn">Offro lezioni di tutorato per l'esame...disponibile giorni...contattare via e-mail</p>
  
  </div>
   <div class="checkboxes">
<input type="checkbox" name="segnala1" value="Bike"> L'annuncio contiene un linguaggio volgare o inappropriato.<br>
<input type="checkbox" name="segnala2" value="Car"> L'annuncio non e' pertinente alla tipologia.<br>
<input type="checkbox" name="segnala3" value="Boat"> Non e' possibile contattare l'utente che ha fatto l'annuncio.<br>  
     <input type="checkbox" name="segnala3" value="Boat"> Altro.<br> 
 </div>
  
 
    <textarea id="textarea">Qui andra'  la descrizione della segnalazione.</textarea>
  
  

  <button id="segnala">Segnala</button>
 
  
  

<script>// Write JavaScript here </script></body>