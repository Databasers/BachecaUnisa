<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" type="text/css" href="CSS/Login.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto:100,400,700" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.css" />
        <meta charset="UTF-8">
        <script type="text/javascript" src ="jquery.js"></script>
    </head>
    <body>
        <div id="logo">
            <img alt="Login" src="https://nextindustry.net/wp-content/uploads/2018/01/Logo_TV_2015.png">
        </div>
        <div class="container">
            <% String registered = (String) request.getParameter("error");
                System.out.println(registered);
                if (registered != null && registered.equalsIgnoreCase("true")) {%>
            <h3 align="center"><font color="red">Username o password non corretti.</font></h3>
                <%}%>
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