<%

    String loginError = (String) request.getAttribute("loginError");
    if(loginError != null) {
        System.out.println("No es null");
    } else {
        System.out.println("Es null");
    }

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/navbar.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/login.css">
    <link rel="stylesheet" href="css/general.css">
</head>
<body>
    <div class="container">
        
        <%@include file="resources/header.jsp" %>
        
        <% if (loginError != null) { %>
        <div class="error-container">
                 <div class="row">
                    <div class="col s12 m4 offset-m4">
                        <div class="card red darken-4">
                            <div class="card-content white-text center-align">
                                <span class="card-title">Error</span>
                                <p><%= loginError %></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div> 
        <% } %>

        <article id="formulario" class="valign-wrapper">
            
            <div id="login-form" class="form-container">
                <div class="row">
                    <div class="col s8 l6 offset-s2 offset-l3">
                        <div class="card horizontal">
                            <div class="card-stacked">
                                <span class="card-title">Iniciar Sesión</span>
                                <form action="login" method="POST">
                                    <div class="input-field col s8 offset-s2">
                                        <input id="email" type="text" name="email" class="validate">
                                        <label for="email">Email</label>
                                    </div>
                                    <div class="input-field col s8 offset-s2">
                                        <input id="password" type="password" name="password" class="validate">
                                        <label for="password">Contraseña</label>
                                    </div>
                                    <div class="submit col s12">
                                        <button class="btn waves-effect waves-light" type="submit" name="action">Ingresar</button>
                                    </div>
                                </form>
                                <div class="register-form-btn">
                                    <a href="#">No tengo cuenta</a>
                                </div>
                                <div class="login-profesor-form-btn">
                                    <a href="#">Soy Profesor</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div id="register-form" class="form-container hide">
                <div class="row">
                    <div class="col s8 l6 offset-s2 offset-l3">
                        <div class="card horizontal">
                            <div class="card-stacked">
                                <span class="card-title">Registrarme</span>
                                <form action="registro" method="POST">
                                    <div class="input-field col s8 m4 offset-s2 offset-m2">
                                        <input id="nombre" type="text" name="nombre" class="validate">
                                        <label for="nombre">Nombre</label>
                                    </div>
                                    <div class="input-field col s8 m4 offset-s2">
                                        <input id="apellido" type="text" name="apellido" class="validate">
                                        <label for="apellido">Apellido</label>
                                    </div>
                                    <div class="input-field col s8 offset-s2">
                                        <input id="email" type="text" name="email" class="validate">
                                        <label for="email">Email</label>
                                    </div>
                                    <div class="input-field col s8 offset-s2">
                                        <input id="password" type="password" name="password" class="validate">
                                        <label for="password">Contraseña</label>
                                    </div>
                                    <div class="submit col s12">
                                        <button class="btn waves-effect waves-light" type="submit">Registrame</button>
                                    </div>
                                </form>
                                <div class="login-form-btn">
                                    <a href="#">Tengo una cuenta</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            
            <div id="login-profesor-form" class="form-container hide">
                <div class="row">
                    <div class="col s8 l6 offset-s2 offset-l3">
                        <div class="card horizontal">
                            <div class="card-stacked">
                                <span class="card-title">Iniciar Sesión</span>
                                <form action="login.html" method="get">
                                    <div class="input-field col s8 offset-s2">
                                        <input id="last_name" type="text" name="email" class="validate">
                                        <label for="last_name">Email</label>
                                    </div>
                                    <div class="input-field col s8 offset-s2">
                                        <input id="password" type="password" name="password" class="validate">
                                        <label for="password">Contraseña</label>
                                    </div>
                                    <div class="submit col s12">
                                        <button class="btn waves-effect waves-light" type="submit" name="action">Ingresar</button>
                                    </div>
                                </form>
                                <div class="login-alumno-form-btn">
                                    <a href="#">Soy Alumno</a>
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
    
    <script src="js/login.js"></script>
</body>
</html>