<!DOCTYPE html>
<html>
<head><style type="text/css">.lw { font-size: 60px; }</style>

<style type="text/css">.lw { font-size: 60px; }</style>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">


<title>HTML, CSS and JavaScript demo</title>

  <style>

fieldset, label { margin: 0; padding: 0; }
body{ margin: 20px; }
h1 { font-size: 1.5em; margin: 10px; }

    .tit{
      margin-top:23px ;
      
      width: 123px;
      position: absolute;
    }
    
    #avatar{
  display: block;
border-radius: 50%;
width: 150px;
height:150px;
float: left;
      margin-bottom: 100px;
}
    
    #textarea{
    width: 100%;
      min-height: 300px;
      overflow: auto;
      
    }
    
    
 .utentetxt{
   text-align: center;
  
    }
    .txtpresent{
    margin: 2%;
    border-style: solid;
    height: 50%;
    min-height: 250px;
    overflow-y: auto;
}
    #visualizzafeed{
    margin-top: 42px;
      float: right;
    }
    
    .star-rating {
    direction: rtl;
    display: inline-block;
    padding: 20px;
        margin-top: 32px;
}

.star-rating input[type=radio] {
    display: none
}

.star-rating label {
    color: #bbb;
    font-size: 18px;
    padding: 0;
    cursor: pointer;
    -webkit-transition: all .3s ease-in-out;
    transition: all .3s ease-in-out
}

.star-rating label:hover,
.star-rating label:hover ~ label,
.star-rating input[type=radio]:checked ~ label {
    color: #f2b600
}
  </style>
  
</head>
<body>
  <img id="avatar" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
  <p style="text-align:center">Stai rilasciando feedback a:
   </p> <br>
  <div class="utentetxt">
    <h1 id="Utenteinfo">Nome Utente<br></h1>
  </div>
  
  <form>
  <textarea id="textarea" maxlength="2000">Inserisci qui la tua recensione!</textarea>
    <h1 class="tit">Valutazione:</h1>

  	<div class="star-rating">
			<input id="star-5" type="radio" name="rating" value="star-5">
			<label for="star-5" title="5 stars">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
			<input id="star-4" type="radio" name="rating" value="star-4">
			<label for="star-4" title="4 stars">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
			<input id="star-3" type="radio" name="rating" value="star-3">
			<label for="star-3" title="3 stars">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
			<input id="star-2" type="radio" name="rating" value="star-2">
			<label for="star-2" title="2 stars">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
			<input id="star-1" type="radio" name="rating" value="star-1">
			<label for="star-1" title="1 star">
					<i class="active fa fa-star" aria-hidden="true"></i>
			</label>
		</div>
    
    
  <div id="visualizzafeed"><input type="submit" value="Rilascia Feedback"></div>
  </form>
  
  

<script>// Write JavaScript here </script><script>// Write JavaScript here </script></body>