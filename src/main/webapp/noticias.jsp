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
    <title>Noticias</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/navbar.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/noticias.css">
    <link rel="stylesheet" href="css/general.css">
</head>
<body>
    <div class="container">
        
        <%@include file="resources/header.jsp" %>

        <article id="noticias">
            <div class="carousel carousel-slider center">
                <div class="carousel-item white-text valign-wrapper" href="#one!" style="background-image: url(https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/UKCLibraryOutside.jpg/1200px-UKCLibraryOutside.jpg);">
                    <h2>Noticia Destacada</h2>
                </div>
                <div class="carousel-item white-text valign-wrapper" href="#one!" style="background-image: url(https://static.misionesonline.news/wp-content/uploads/2017/07/universidad-de-salamanca-1486637520561-5r1r914p3gj0.jpg);">
                    <h2>Noticia Destacada</h2>
                </div>
                <div class="carousel-item white-text valign-wrapper" href="#one!" style="background-image: url(https://www.elmundo.es/especiales/ranking-universidades/img/65.jpg);">
                    <h2>Noticia Destacada</h2>
                </div>
            </div>

            <h1>Noticias</h1>
            
            <div id="noticia-fijada">
                <div class="row">
                    <div class="col s12 m8 offset-m2">
                      <div class="card">
                        <div class="card-image">
                          <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/3/3f/UKCLibraryOutside.jpg/1200px-UKCLibraryOutside.jpg">
                          <span class="card-title">Título de la Noticia</span>
                        </div>
                        <div class="card-content">
                          <p class="truncate">I am a very simple card. I am good at containing small bits of information.
                          I am convenient because I require little markup to use effectively.</p>
                        </div>
                        <div class="card-action">
                          <a href="#">Leer</a>
                        </div>
                      </div>
                    </div>
                  </div>
            </div>

            <div id="noticias-container" class="row">
                <div class="noticia">
                    <div class="col s12 m6">
                        <div class="card horizontal">
                          <div class="card-image">
                            <img src="https://lorempixel.com/100/190/nature/6">
                          </div>
                          <div class="card-stacked">
                                <div class="card-content">
                                <h3>Noticia</h3>
                                <p>I am a very simple card. I am good at containing small bits of information.</p>
                            </div>
                            <div class="card-action">
                              <a href="#">Leer</a>
                            </div>
                          </div>
                        </div>
                      </div>
                </div>
                <div class="noticia">
                    <div class="col s12 m6">
                        <div class="card horizontal">
                          <div class="card-image">
                            <img src="https://lorempixel.com/100/190/nature/6">
                          </div>
                          <div class="card-stacked">
                                <div class="card-content">
                                <h3>Noticia</h3>
                                <p>I am a very simple card. I am good at containing small bits of information.</p>
                            </div>
                            <div class="card-action">
                              <a href="#">Leer</a>
                            </div>
                          </div>
                        </div>
                      </div>
                </div>
                <div class="noticia">
                    <div class="col s12 m6">
                        <div class="card horizontal">
                          <div class="card-image">
                            <img src="https://lorempixel.com/100/190/nature/6">
                          </div>
                          <div class="card-stacked">
                                <div class="card-content">
                                <h3>Noticia</h3>
                                <p>I am a very simple card. I am good at containing small bits of information.</p>
                            </div>
                            <div class="card-action">
                              <a href="#">Leer</a>
                            </div>
                          </div>
                        </div>
                      </div>
                </div>
                <div class="noticia">
                    <div class="col s12 m6">
                        <div class="card horizontal">
                          <div class="card-image">
                            <img src="https://lorempixel.com/100/190/nature/6">
                          </div>
                          <div class="card-stacked">
                                <div class="card-content">
                                <h3>Noticia</h3>
                                <p>I am a very simple card. I am good at containing small bits of information.</p>
                            </div>
                            <div class="card-action">
                              <a href="#">Leer</a>
                            </div>
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
    
    <script src="js/noticias.js"></script>
</body>
</html>