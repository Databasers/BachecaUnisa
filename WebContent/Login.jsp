<!DOCTYPE html>
<html>
    <head>
        <title>
            Pagina di login
        </title>
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
            <form name ="Login">
                <input type="text" placeholder="Username" name="username" required="required">
                <input type="password" placeholder="Password" name="password" required="required">
                <div id="logbutton">
                    <button value="Login" class="btn" name ="azione" formaction="/BACHECAUNISA/UtenteServlet">Login</button>
                </div>
            </form>

            <form action="registrazione.jsp" method="get" class="container">
                <input type="submit" class="btnReg" value ="Registrati"
                       name="Submit" />
            </form>
        </div>
    </body>