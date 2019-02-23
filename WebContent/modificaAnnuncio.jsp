<!DOCTYPE html>
<%@page import="gestioneannunci.AnnuncioManager"%>
<%@page import="gestioneannunci.Annuncio"%>
<html>
    <head>
        <title>Modifica Annuncio</title>
        <link rel="stylesheet" type="text/css" href="CSS/ModificaAnnuncio.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
        <meta charset="ISO-8859-1">
    </head>
    <body>
        <div id="barraleft"><%@ include file="barraLEFTv2.jsp" %></div>
        <div class="contna"> <!-- na vicino ai nomi delle classi sta per nuovo annuncio -->
        <%
            AnnuncioManager manager = new AnnuncioManager();
            int id = Integer.valueOf(request.getParameter("id"));
            Annuncio x = (Annuncio) manager.recuperaPerId(id);
        %>
        
            <p id="oldAnnuncio"> </p>
            <form action="">
                <div class="togna">
                    <label class="etichettaGruppo">GRUPPO DI STUDIO   <input type="radio" name="filtro" value="gruppo"></label>
                    <label class="etichettaTutorato">ATTIVITA' DI TUTORATO   <input type="radio" checked="checked" name="filtro" value="tutorato"></label>
                        <select name="Dipartimento">
                            <option>Informatica</option>
                            <option>Ingegneria</option>
                            <option>Farmacia</option>
                            <option>Lettere</option>
                        </select>
                </div>             
                    <input type="text" class="title" name="titolo" placeholder="Titolo" value="<%= x.getTitolo()%>">
                    <textarea class="txtna"><%= x.getDescrizione()%></textarea>
                    <input type="submit" value="Modifica annuncio" class="sfdbna" onclick="check()">
            </form>
        </div>


        <script>
            function ceck() {
                var tipologia = $("input[name=tipologia]:selected").val()
                );
                        var dipartimento;
                if (tipologia == "Tutorato") {
                    dipartimento = $(select[name = "Dipartimento"]).val();
                } else {
                    dipartimento = "Informatica";
                }
                var titolo = $("label[name=titolo]").val();
                var descrizione = $(div[name = "descrizione"]).val();

                var azione = "/BACHECAUNISA/gestioneannunci/AnnunciServlet?action=modificaAnnuncio&usernameUtente=" + session.getAttribute("username")
                        + "&dipartimento=" + dipartimento + "&titolo=" + titolo + "&descrizione=" + descrizione + "&tipologia=" + tipologia;
                document.togna.action = azione;
                document.togna.submit();

            }
        </script>
    </body>