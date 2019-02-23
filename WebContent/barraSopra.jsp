<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Home</title>
        <link rel="stylesheet" type="text/css" href="CSS/BarraSopra.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
    </head>

    <body>

        <form class="form-inline" action="/BACHECAUNISA/AnnunciServlet">
            <input type="hidden" name="azione" value="stampaAnnunci">
            <input type="hidden" name="numPagina" value="0">
            <input type="text" id="barraRicerca" name="descrizione"
                   placeholder="Cerca">
            <div class="radio">
            <label class="etichettaGruppo">GRUPPO  <input type="radio" name="filtro" value="gruppo"></label>
            <label class="etichettaTutorato">TUTORATO  <input type="radio" checked="checked" name="filtro" value="tutorato"></label>
            </div>
            <div class="custom-select">
                <select name="dipartimento">
                    <option value="Informatica">Informatica</option>
                    <option value="Ingegneria">Ingegneria</option>
                    <option value="Farmacia">Farmacia</option>
                    <option value="Lettere">Lettere</option>
                </select>
                <input type="submit" id ="cerca" value="Cerca">
            </div>
            <button class="button" id="creaAnnuncio" formaction="/BACHECAUNISA/CreaNuovoAnnuncio.jsp">Crea Annuncio</button>
        </form>

    </body>
</html>
