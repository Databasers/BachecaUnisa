<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<style>
body {font-family: Arial, Helvetica, sans-serif;}
* {box-sizing: border-box;}

.form-inline {  
  display: flex;
  flex-flow: row wrap;
  align-items: center;
}

.form-inline label {
  margin: 5px 10px 5px 0;
}


/*DIPARTIMENTO*/
/*the container must be positioned relative:*/
.custom-select {
	width: 200px;
	position: relative;
}

.custom-select select {
	display: none; /*hide original SELECT element:*/
}

.select-selected {
	background-color: #ddd;
}

/*style the arrow inside the select element:*/
.select-selected:after {
	position: absolute;
	content: "";
	top: 14px;
	right: 10px;
	width: 0;
	height: 0;
	border: 6px solid transparent;
	border-color: #fff transparent transparent transparent;
}

/*point the arrow upwards when the select box is open (active):*/
.select-selected.select-arrow-active:after {
	border-color: transparent transparent #fff transparent;
	top: 7px;
}

/*style the items (options), including the selected item:*/
.select-items div, .select-selected {
	color: black;
	padding: 8px 16px;
	border: 1px solid transparent;
	border-radius: 4px;
	border-color: transparent transparent rgba(0, 0, 0, 0.1) transparent;
	cursor: pointer;
	user-select: none;
}

/*style items (options):*/
.select-items {
	position: absolute;
	background-color: #ddd;
	top: 100%;
	left: 0;
	right: 0;
	z-index: 99;
}

/*hide the items when the select box is closed:*/
.select-hide {
	display: none;
}

.select-items div:hover, .same-as-selected {
	background-color: rgba(0, 0, 0, 0.1);
}

/*BARRA DI RICERCA*/
#barraRicerca {
	display: inline-block;
	font-size: 12px;
	width: 150px;
	box-sizing: border-box;
	border: 2px solid #ccc;
	border-radius: 4px;
	font-size: 16px;
	background-color: white;
	padding: 6px 10px;
	-webkit-transition: width 0.4s ease-in-out;
	transition: width 0.4s ease-in-out;
}

#barraRicerca:focus {
	width: 20%;
}

/*PULSANTI RADIO*/
/* The container */
.container {
	display: inline-block;
	position: relative;
	padding-left: 35px;
	margin-bottom: 12px;
	cursor: pointer;
	font-size: 16px;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}

/* Hide the browser's default radio button */
.container input {
	position: absolute;
	opacity: 0;
	cursor: pointer;
}

/* Create a custom radio button */
.checkmark {
	position: absolute;
	top: 0;
	left: 8px;
	height: 20px;
	width: 20px;
	background-color: #ddd;
	border-radius: 50%;
}

/* On mouse-over, add a grey background color */
.container:hover input ~ .checkmark {
	background-color: #ccc;
}

/* When the radio button is checked, add a blue background */
.container input:checked ~ .checkmark {
	background-color: #2196F3;
}

/* Create the indicator (the dot/circle - hidden when not checked) */
.checkmark:after {
	content: "";
	position: absolute;
	display: none;
}

/* Show the indicator (dot/circle) when checked */
.container input:checked ~ .checkmark:after {
	display: inline-block;
}

/* Style the indicator (dot/circle) */
.container .checkmark:after {
	top: 5px;
	left: 5px;
	width: 10px;
	height: 10px;
	border-radius: 50%;
	background: white;
}

.button {
	background-color: #ddd;
	border: none;
	color: black;
	padding: 10px 20px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	margin: 15px;
	cursor: pointer;
	border-radius: 4px;
	font-size: 16px;
	width: 170px;
}

.button:hover {
	background-color: #f1f1f1;
}
</style>
</head>

<body>

	<form class="form-inline">
		<input type="text" id="barraRicerca" name="cerca"
			placeholder="Cerca annuncio"> <label class="container">Gruppo
			di studio<input type="radio" checked="checked" name="radio">
			<span class="checkmark"></span>
		</label> <label class="container">Tutorato<input type="radio"
			name="radio"> <span class="checkmark"></span>
		</label>


		<!--surround the select box with a "custom-select" DIV element. Remember to set the width:-->
		<div class="custom-select">
			<select>
				<option value="0">Dipartimento:</option>
				<option value="1">Informatica</option>
				<option value="2">Ingegneria</option>
				<option value="3">Farmacia</option>
				<option value="4">Lettere</option>
			</select>
		</div>

		<button class="button" id="creaAnnuncio">Crea Annuncio</button>
	</form>

	<script>
var x, i, j, selElmnt, a, b, c;
/*look for any elements with the class "custom-select":*/
x = document.getElementsByClassName("custom-select");
for (i = 0; i < x.length; i++) {
  selElmnt = x[i].getElementsByTagName("select")[0];
  /*for each element, create a new DIV that will act as the selected item:*/
  a = document.createElement("DIV");
  a.setAttribute("class", "select-selected");
  a.innerHTML = selElmnt.options[selElmnt.selectedIndex].innerHTML;
  x[i].appendChild(a);
  /*for each element, create a new DIV that will contain the option list:*/
  b = document.createElement("DIV");
  b.setAttribute("class", "select-items select-hide");
  for (j = 1; j < selElmnt.length; j++) {
    /*for each option in the original select element,
    create a new DIV that will act as an option item:*/
    c = document.createElement("DIV");
    c.innerHTML = selElmnt.options[j].innerHTML;
    c.addEventListener("click", function(e) {
        /*when an item is clicked, update the original select box,
        and the selected item:*/
        var y, i, k, s, h;
        s = this.parentNode.parentNode.getElementsByTagName("select")[0];
        h = this.parentNode.previousSibling;
        for (i = 0; i < s.length; i++) {
          if (s.options[i].innerHTML == this.innerHTML) {
            s.selectedIndex = i;
            h.innerHTML = this.innerHTML;
            y = this.parentNode.getElementsByClassName("same-as-selected");
            for (k = 0; k < y.length; k++) {
              y[k].removeAttribute("class");
            }
            this.setAttribute("class", "same-as-selected");
            break;
          }
        }
        h.click();
    });
    b.appendChild(c);
  }
  x[i].appendChild(b);
  a.addEventListener("click", function(e) {
      /*when the select box is clicked, close any other select boxes,
      and open/close the current select box:*/
      e.stopPropagation();
      closeAllSelect(this);
      this.nextSibling.classList.toggle("select-hide");
      this.classList.toggle("select-arrow-active");
    });
}
function closeAllSelect(elmnt) {
  /*a function that will close all select boxes in the document,
  except the current select box:*/
  var x, y, i, arrNo = [];
  x = document.getElementsByClassName("select-items");
  y = document.getElementsByClassName("select-selected");
  for (i = 0; i < y.length; i++) {
    if (elmnt == y[i]) {
      arrNo.push(i)
    } else {
      y[i].classList.remove("select-arrow-active");
    }
  }
  for (i = 0; i < x.length; i++) {
    if (arrNo.indexOf(i)) {
      x[i].classList.add("select-hide");
    }
  }
}
/*if the user clicks anywhere outside the select box,
then close all select boxes:*/
document.addEventListener("click", closeAllSelect);
</script>

</body>
</html>
