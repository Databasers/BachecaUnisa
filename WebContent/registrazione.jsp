<!DOCTYPE html>
<html>
    <head>

        <title>Registrazione</title>
        <link rel="stylesheet" type="text/css" href="CSS/Registrazione.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src ="jquery.js"></script>
    </head>
    <body>
        <div id="logo">
            <img alt="Logo" src="CSS/IMAGES/logo.png">
        </div>
        <div id="container">   

            <% String registered = (String) request.getParameter("ar");
                System.out.println(registered);
                if (registered != null && registered.equalsIgnoreCase("t")) {%>
            <h3><font color="red">Questo utente esiste già</font></h3>
                <%}%>
            <div id="field">   
                <form accept-charset="utf-8" id="register" name = "register" class="register" method="Post" action="/BACHECAUNISA/UtenteServlet?azione=creaUtente">
                    <input type="text" placeholder="Nome" maxlength="20" pattern="[a-zA-Z\s]{1,}" class="campo" name="nome" id = "nome" required="required" title="Solo lettere">
                    <input type="text" placeholder="Cognome" maxlength="20" pattern="[a-zA-Z\s]{1,}" class="campo" name="cognome" required="required" title="Solo lettere">
                    <div id="sextext">
                        <select name="sesso" class="sex">
                            <option value="maschio">Maschio</option>
                            <option value="femmina">Femmina</option>
                            <option value="altro">Altro</option>
                        </select>
                    </div>
                    <input id="nomefield" placeholder="Username" maxlength="20" type="text" pattern="[a-zA-Z0-9]{1,}" class="campo" name="username" required="required" title="Solo lettere e numeri">
                    <input id="password" type="password" placeholder="Password" maxlength="20" pattern=".{8,}" class="campo" name="password" required="required" title="Almeno 8 caratteri">
                    <input type="password" placeholder="Conferma password" maxlength="20" pattern=".{8,}" class="campo" name="confpassword" required="required" title="Almeno 8 caratteri" oninput="check(this)">
                    <script>
                        function check(input) {
                            if (input.value !== $("#password").val()) {
                                input.setCustomValidity("Le password non coincidono");
                            } else {
                                input.setCustomValidity("");
                            }
                        }
                    </script>
                    <div class="regolamento">
                        <label for="checkbox" class="confermaReg">Conferma regolamento</label>
                        <input type="checkbox" id= "checkbox" class="checkbox" name="ceckbox" required="required">    
                    </div>
                    <input type="submit" value="Registrati" id="regbutton"> 

                </form>
            </div> 
            <form accept-charset="utf-8" action="FAQ.jsp" id="faq">
                <input type="submit" value="Visualizza FAQ" class="faqbutton">
            </form>

        </div>
    </body>