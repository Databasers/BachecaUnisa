<!DOCTYPE html>
<%@page import="gestioneannunci.Annuncio"%>
<html>
<head><style type="text/css">.lw { font-size: 60px; }</style>

<style type="text/css">.lw { font-size: 60px; }</style>



<title>Visualizza Annuncio</title>

  <style> 


h1 { font-size: 1.5em; margin: 10px; }


    
    #avatarannuncio{
border-radius: 50%;
width: 150px;
height:150px;
margin-left: 12px;
      margin-bottom: 10px;
      
}
    
    
 .titleannuncio{
   border-style: solid;
   text-align: left;
      margin: 1%;
    }
    .txtannuncio{
    margin: 1%;
    border-style: solid;
    height: 50%;
    min-height: 250px;
    overflow-y: auto;
      font-size: 150%;
}
   
     #segnalaannuncio{
      text-align: right;
      margin: 1%;
    }
       #modificannuncio{
      float: right;
      margin-top: 30px;
  
    }
    
    #nomeutente{
      text-align: center;
      position: absolute;
      font-size: 150%;
      font-weight: bolder;
      margin-left: 50px;
      margin-top: 30px;
    }
  </style>
  
</head>
<body>

<%
Annuncio x = (Annuncio) request.getSession().getAttribute("annuncioTrovato");

if (x == null){
  response.sendRedirect("BACHECAUNISA/AnnunciServlet?id=" + request.getParameter("id"));
} else {
  request.getSession().removeAttribute("annuncioTrovato");
}
%>
  
  
  
  
  <img id="avatarannuncio" alt="avatar" src="https://www.w3schools.com/howto/img_avatar.png">
  <span id="nomeutente"><%=x.getUsernameUtente()%></span>
   <div id="modificannuncio"><input class="valuebutton" type="submit" value="Modifica Annuncio" formaction="/modificaAnnuncio.jsp"></div>
   
  
  <div class="titleannuncio">
    <h1 id="Utenteinfo">TITOLO:<%=x.getTitolo()%><br>
      DIPARTIMENTO:<%=x.getDipartimento() %></h1>
  </div>

  <p class="txtannuncio"><%=x.getDescrizione()%></p>
  
    
  
  <div id="segnalaannuncio"><input class="valuebutton" type="submit" value="Segnala Annuncio"></div>
  
  </body>
  
  