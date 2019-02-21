<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="CSS/Login.css">
        <meta charset="ISO-8859-1">
        <script type="text/javascript" src ="jquery.js"></script>
        <title>Login</title>
    </head>
    <body>
        <div id="logo">
            <img alt="Login" src="https://nextindustry.net/wp-content/uploads/2018/01/Logo_TV_2015.png">
        </div>
        <div class="container">
            <form name ="Login" method="Post" action="/BACHECAUNISA/UtenteServlet?azione=Login">
                <input type="text" placeholder="Username" name="username" maxlength="20" pattern="[a-zA-Z0-9]{1,}" required="required" title="Solo lettere e numeri">
                <input type="password" placeholder="Password" name="password" maxlength="20" pattern=".{8,}" required="required" title="Almeno 8 caratteri">
                <input type="submit" value="Login" id="logbutton" class="btn"> 
            </form>

            <form action="registrazione.jsp" method="get" class="container">
                <input type="submit" class="btnReg" value ="Registrati"
                       name="Submit" />
            </form>
        </div>
    </body>