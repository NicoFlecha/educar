<%@page import="java.util.ArrayList"%>
<%@page import="com.nico.educarjava.model.entities.Categoria"%>
<%
    ArrayList<Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("categorias");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nueva Categoría</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/navbar.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/general.css">
    <link rel="stylesheet" href="css/categorias_formulario.css">
</head>
<body>
    <div class="container">
        
        <%@include file="/resources/header.jsp" %>

        <article id="formulario">
            
            <h1>Crear Categoria</h1>
            
            <form action="categorias?accion=guardar" method="POST">
                <label for="nombre">Nombre:</label><br>
                <input type="text" name="nombre" id="nombre"><br>
                <input type="submit">
            </form>
            
            <div id="categoria-nueva" class="hide">
                <h2>Categoria a crear</h2>
                <div class="row">
                    
                    <div class="categoria-container col s12 m3 offset-m4">
                        <div class="card">
                            <div class="card-image" style="background-image: url('https://miro.medium.com/max/1024/1*vxjAHkrXbGG6gOiPZgjeZA.jpeg');"></div>
                            <div class="card-text">
                                <h5 id="nombre_categoria"></h5>
                            </div>
                        </div>
                    </div>
                    
                </div>
                   
            </div>
            
            <div class="categorias">
                
                <h3>Categorias</h3>
                
                <div class="row">
                    
                    <% if (categorias.size() != 0) { %>
                    
                        <% for(Categoria categoria: categorias) { %>
                            <div class="categoria-container col s12 m3">
                                <div class="card">
                                    <div class="card-image" style="background-image: url('https://miro.medium.com/max/1024/1*vxjAHkrXbGG6gOiPZgjeZA.jpeg');"></div>
                                    <div class="card-text">
                                        <h5><%= categoria.getNombre() %></h5>
                                    </div>
                                    <form action="categorias?accion=eliminar" method="POST">
                                        <input name="id" type="hidden" value="<%=categoria.getId()%>">
                                        <button type="submit">Eliminar categoria</button>
                                    </form>
                                </div>
                            </div>
                        <% } %>
                    
                    <% } else { %>
                        
                        <h4>No hay Categorias</h4>
                        
                    <% } %>
                    
                </div>
                
            </div>
            
        </article>

        <%@include file="/resources/footer.jsp" %>

    </div>



    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="https://kit.fontawesome.com/3981d882ec.js" crossorigin="anonymous"></script>

    <script src="js/categorias_formulario.js"></script>
</body>
</html>