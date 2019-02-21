<!DOCTYPE html>
<html>
    <head>

        <title>Registrazione</title>
        <link rel="stylesheet" type="text/css" href="CSS/Registrazione.css">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src ="jquery.js"></script>
    </head>
    <body>
        <div id="logo">
            <img alt="Logo" src="https://nextindustry.net/wp-content/uploads/2018/01/Logo_TV_2015.png">
        </div>
        <div id="container">   

            <% String registered = (String) request.getParameter("ar");
    System.out.println(registered);
    if (registered != null && registered.equalsIgnoreCase("t")) {%>
            <h3><font color="red">Questo utente esiste già</font></h3>
                <%}%>
            <div id="field">   
                <form id="register" name = "register" class="register" method="Post">
                    <input type="text" placeholder="Nome" maxlength="20" pattern="[a-zA-Z\s]{1,}" class="campo" name="Nome" id = "nome" required="required" title="Solo lettere">
                    <input type="text" placeholder="Cognome" maxlength="20" pattern="[a-zA-Z\s]{1,}" class="campo" name="cognome" required="required" title="Solo lettere">
                    <div id="sextext">
                        <select name="sesso" class="sex">
                            <option value="maschio">Maschio</option>
                            <option value="femmina">Femmina</option>
                            <option value="altro">Altro</option>
                        </select>
                    </div>
                        <input id="nomefield" placeholder="Username" maxlength="20" type="text" pattern="[a-zA-Z0-9]{1,}" class="campo" name="username" required="required" title="Solo lettere e numeri">
                        <input type="password" placeholder="Password" maxlength="20" pattern=".{8,}" class="campo" name="password" required="required" title="Almeno 8 caratteri">
                        <input type="password" placeholder="Conferma password" maxlength="20" pattern=".{8,}" class="campo" name="confpassword" required="required" title="Almeno 8 caratteri">
                        <div class="regolamento">
                            <label for="checkbox" class="confermaReg">Conferma regolamento</label>
                            <input type="checkbox" id= "checkbox" class="checkbox" name="ceckbox" required="required">    
                        </div>
                        <script>
                        function validateString(stringa){
                        	if ((stringa.match(/[^a-z0-9A-Z]/))) {
                                return true;
                            } else return false;
                        }
                        function validateReal(stringa){
                        	if ((stringa.match(/[^a-zA-Z]/))) {
                                return true;
                            } else return false;
                        }
                        
                            function registra() {
                                console.log("Entrati");
                                var nom = $("input[name = Nome]").val();
                                var cog = $("input[name = cognome]").val();
                                var ss = $("select[name = sesso]").val();
                                var user = $("input[name = username]").val();
                                var pas = $("input[name = password]").val();
                                var cpass = $("input[name = confpassword]").val();
                                var rego = $("input[name = ceckbox]").prop("checked");

                                var azione = "/BACHECAUNISA/UtenteServlet?azione=creaUtente&username=" + user +
                                            "&cognome=" + cog + "&sesso=" + ss + "&nome=" + nom + "&password=" + pas;

                                    console.log(azione + "");
                                    const elem = document.getElementById("register");
                                    elem.action = azione;
                                    console.log(elem.action + " è l'azione");
                                    elem.submit();
                                }
                                return false;
                            }

                        </script>
                        <input type="submit" value="Registrati" id="regbutton" onclick="registra()"> 

                </form>
            </div> 

            <form action="FAQ.jsp" id="faq">
                <input type="submit" value="Visualizza FAQ" class="faqbutton">
            </form>

        </div>




    </body>