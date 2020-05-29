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
    <title>Tus Alumnos</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/navbar.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/profesor/profesor-alumnos.css">
    <link rel="stylesheet" href="css/general.css">
</head>
<body>
    <div class="container">
        
        <%@include file="resources/header.jsp" %>

        <article id="alumnos">
            <h1>Alumnos del curso (ID)</h1>
            <h2>Nombre del Curso</h2>

            <table class="striped">
                <thead>
                  <tr>
                      <th>Nombre</th>
                      <th>Apellido</th>
                      <th>Email</th>
                  </tr>
                </thead>
        
                <tbody>
                  <tr>
                    <td>Alvin</td>
                    <td>Eclair</td>
                    <td>alvin@alvin.com</td>
                  </tr>
                  <tr>
                    <td>Alan</td>
                    <td>Jellybean</td>
                    <td>alan@alan.com</td>
                  </tr>
                  <tr>
                    <td>Jonathan</td>
                    <td>Lollipop</td>
                    <td>jonathan@jonathan.com</td>
                  </tr>
                </tbody>
              </table>

        </article>
        
        <%@include file="resources/footer.jsp" %>

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="https://kit.fontawesome.com/3981d882ec.js" crossorigin="anonymous"></script>
</body>
</html>