<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Home</title>
        <link rel="stylesheet" type="text/css" href="CSS/BarraSopra.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
    </head>

    <body>

        <form class="form-inline">
            <input type="text" id="barraRicerca" name="cerca"
                   placeholder="Cerca annuncio o utente">
            <div class="radio">
            <label>Gruppo di studio<input type="radio" checked="checked" name="radio"></label>
            <label>Tutorato<input type="radio" name="radio"></label>
            </div>
            <div class="custom-select">
                <select>
                    <option value="0">Dipartimento:</option>
                    <option value="1">Informatica</option>
                    <option value="2">Ingegneria</option>
                    <option value="3">Farmacia</option>
                    <option value="4">Lettere</option>
                </select>
                <input type="submit" id ="cerca" value="Cerca">
            </div>
            <button class="button" id="creaAnnuncio">Crea Annuncio</button>
        </form>

    </body>
</html>
