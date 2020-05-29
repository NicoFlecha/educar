<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tu perfil</title>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="css/footer.css">
        <link rel="stylesheet" href="css/general.css">
        <link rel="stylesheet" href="css/usuario.css">
    </head>
    <body>
        
        <div class="container">
        
            <%@include file="/resources/header.jsp" %>
            
            <article id="perfil"></article>
                
            <%@include file="/resources/footer.jsp" %>

        </div>
            
        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <script src="https://kit.fontawesome.com/3981d882ec.js" crossorigin="anonymous"></script>
        
        <script src="js/usuario_perfil.js"></script>
    </body>
</html>
