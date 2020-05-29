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
    <title>Tus Cursos</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/navbar.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/profesor/profesor-cursos.css">
    <link rel="stylesheet" href="css/general.css">
</head>
<body>
    <div class="container">
        
        <%@include file="resources/header.jsp" %>

        <article id="cursos">
            <h1>Tus Cursos</h1>

            <h2>Alumnos por curso</h2>
            <div class="cursos-container">

                <div class="row">

                    <div class="curso">
                        <div class="col s12 m6 l4">
                            <div class="card horizontal">
                                <div class="card-image">
                                    <img src="https://lorempixel.com/100/190/nature/6">
                                </div>
                                <div class="card-stacked">
                                    <div class="card-content">
                                        <span class="card-title center-align">3 (ID del Curso)</span>
                                        <p class="center center-align">Acá va el nombre del curso</p>
                                    </div>
                                    <div class="card-action center-align">
                                        <a href="#">Ver alumnos</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="curso">
                        <div class="col s12 m6 l4">
                            <div class="card horizontal">
                                <div class="card-image">
                                    <img src="https://lorempixel.com/100/190/nature/6">
                                </div>
                                <div class="card-stacked">
                                    <div class="card-content">
                                        <span class="card-title center-align">3 (ID del Curso)</span>
                                        <p class="center center-align">Acá va el nombre del curso</p>
                                    </div>
                                    <div class="card-action center-align">
                                        <a href="#">Ver alumnos</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="curso">
                        <div class="col s12 m6 l4">
                            <div class="card horizontal">
                                <div class="card-image">
                                    <img src="https://lorempixel.com/100/190/nature/6">
                                </div>
                                <div class="card-stacked">
                                    <div class="card-content">
                                        <span class="card-title center-align">3 (ID del Curso)</span>
                                        <p class="center center-align">Acá va el nombre del curso</p>
                                    </div>
                                    <div class="card-action center-align">
                                        <a href="#">Ver alumnos</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div>

            <!-- <h2>Temas de tus cursos</h2>
            <div class="cursos-container">

                <div class="row">

                    <div class="curso">
                        <div class="col s12 m6 l4">
                            <div class="card horizontal">
                                <div class="card-image">
                                    <img src="https://lorempixel.com/100/190/nature/6">
                                </div>
                                <div class="card-stacked">
                                    <div class="card-content">
                                        <span class="card-title center-align">3 (ID del Curso)</span>
                                        <p class="center center-align">Acá va el nombre del curso</p>
                                    </div>
                                    <div class="card-action center-align">
                                        <a href="#">Ver Temas</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>

            </div> -->

        </article>
        
        <%@include file="resources/footer.jsp" %>

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="https://kit.fontawesome.com/3981d882ec.js" crossorigin="anonymous"></script>
</body>
</html>