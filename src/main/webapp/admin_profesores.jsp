<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profesores</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/navbar.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/general.css">
</head>
<body>
    <div class="container">
        
        <%@include file="resources/header.jsp" %>

        <article id="profesores">
            <a class="waves-effect waves-light btn modal-trigger" id="agregar-profesor" href="#modal-profesor">Agregar Profesor</a>
            <div id="profesores-container"></div>
        </article>
        
        <%@include file="resources/footer.jsp" %>
        
    </div>
        
        
    <!-- MODAL PROFESOR -->    
    <div id="modal-profesor" class="modal">
        <div class="modal-content">
            <h4 id="modal-title"></h4>
            <form id="profesor-form">
                <div class="row">
                    <input type="hidden" value="0" id="profesor-id">
                    <div class="input-field col s12 m6">
                        <input id="nombre" type="text" class="validate">
                        <label for="nombre">Nombre</label>
                    </div>
                    <div class="input-field col s12 m6">
                        <input id="apellido" type="text" class="validate">
                        <label for="apellido">Apellido</label>
                    </div>
                    <div class="input-field col s12 m6">
                        <input id="email" type="text" class="validate">
                        <label for="email">Email</label>
                    </div>
                    <div class="input-field col s12 m6">
                        <input type="text" class="datepicker" id="fecha-nacimiento">
                        <label for="fecha-nacimiento">Fecha de Nacimiento</label>
                    </div>
                    <div class="input-field col s12 m6">
                        <input id="password" type="password" class="validate">
                        <label for="password">Password</label>
                    </div>
                    <div class="input-field col s12 m6">
                        <input id="foto" type="text" class="validate">
                        <label for="foto">Foto</label>
                    </div>
                </div>
                <div class="right-align">
                    <a class="modal-close waves-effect waves-light btn red">Cancelar</a>
                    <button class="btn waves-effect waves-light" type="submit">Guardar Profesor</button>
                </div>
            </form>
        </div>
    </div>
    
    <!-- MODAL CURSOS -->
    <div id="modal-cursos" class="modal">
        <div class="modal-content" id="modal-cursos-content"></div>
    </div>

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="https://kit.fontawesome.com/3981d882ec.js" crossorigin="anonymous"></script>
    
    <script src="js/admin_profesores.js"></script>
</body>
</html>