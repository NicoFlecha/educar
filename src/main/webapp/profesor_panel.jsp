<%-- 
    Document   : cursos
    Created on : 12-may-2020, 15:08:41
    Author     : Nico
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tu Panel</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/navbar.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/profesor/profesor-panel.css">
    <link rel="stylesheet" href="css/general.css">
</head>
<body>
    <div class="container">
        
        <%@include file="resources/header.jsp" %>

        <article id="panel">
            <h1>Tu Panel</h1>

            <div id="panel-container">

                <div class="row">

                    <div class="col s12 m6">
                        <div class="card blue-grey darken-1">
                            <div class="card-content white-text center-align waves-effect waves-light">
                                <a href="profesor?ver=cursos"><span class="card-title">Cursos</span></a>
                            </div>
                        </div>
                    </div>

                    <div class="col s12 m6">
                        <div class="card blue-grey darken-1">
                            <div class="card-content white-text center-align waves-effect waves-teal">
                                <a href="profesor?ver=alumnos"><span class="card-title">Alumnos</span></a>
                            </div>
                        </div>
                    </div>
                    
                </div>

            </div>

        </article>
        
        <%@include file="resources/footer.jsp" %>

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="https://kit.fontawesome.com/3981d882ec.js" crossorigin="anonymous"></script>
</body>
</html>