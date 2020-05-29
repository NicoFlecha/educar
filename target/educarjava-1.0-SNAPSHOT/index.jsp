<%-- 
    Document   : index
    Created on : 12-may-2020, 14:44:10
    Author     : Nico
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>EducAR</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/general.css">
    <link rel="stylesheet" href="css/navbar.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/index.css">
</head>
<body>
    
    <div class="container">

        <%@include file="/resources/header.jsp" %>
        
        <div class="parallax-container">
          <h2>Profesores</h2>
          <div class="parallax"><img src="https://www.etitulo.com/wp-content/uploads/2018/12/profesor.jpg"></div>
        </div>

        <article>
            <div class="row">
              <div id="novedades">
                <h3><a href="noticias.html">Novedades</a></h3>
                <a id="mas-btn" class="waves-effect waves-light btn" href="noticias"><i class="material-icons right">cloud</i>Ver más</a>
              </div>
                <div class="col s12 m6">
                    <div class="card">
                        <div class="card-image waves-effect waves-block waves-light">
                          <img class="activator" src="https://www.etitulo.com/wp-content/uploads/2018/12/profesor.jpg">
                        </div>
                        <div class="card-content">
                          <span class="card-title activator grey-text text-darken-4">Los Profesores<i class="material-icons right">more_vert</i></span>
                        </div>
                        <div class="card-reveal">
                          <span class="card-title grey-text text-darken-4">Card Title<i class="material-icons right">close</i></span>
                          <p>Here is some more information about this product that is only revealed once clicked on.</p>
                        </div>
                      </div>                    
                </div>
                <div class="col s12 m6">
                    <div class="card">
                        <div class="card-image waves-effect waves-block waves-light">
                          <img class="activator" src="https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/UKCLibraryOutside.jpg/1200px-UKCLibraryOutside.jpg">
                        </div>
                        <div class="card-content">
                          <span class="card-title activator grey-text text-darken-4">El Instituto<i class="material-icons right">more_vert</i></span>
                        </div>
                        <div class="card-reveal">
                          <span class="card-title grey-text text-darken-4">Card Title<i class="material-icons right">close</i></span>
                          <p>Here is some more information about this product that is only revealed once clicked on.</p>
                        </div>
                      </div> 
                </div>
            </div>
        </article>

        <div class="parallax-container">
          <h2>Instituto</h2>
          <div class="parallax"><img src="https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/UKCLibraryOutside.jpg/1200px-UKCLibraryOutside.jpg"></div>
        </div>
    
        <%@include file="/resources/footer.jsp" %>
        
    </div>

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="https://kit.fontawesome.com/3981d882ec.js" crossorigin="anonymous"></script>

    <script src="./js/parallax.js"></script>
</body>
</html>
