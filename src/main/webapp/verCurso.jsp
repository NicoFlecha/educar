<%@page import="com.nico.educarjava.model.dao.CursosDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.nico.educarjava.model.entities.UsuarioCurso"%>
<%@page import="com.nico.educarjava.model.entities.Curso"%>
<%
    Curso curso = (Curso) request.getAttribute("curso");
    Boolean inscripto = ((Boolean) request.getAttribute("inscripto")).booleanValue();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title><%= curso.getNombre() %></title>
        <!--Import Google Icon Font-->
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        <!-- Compiled and minified CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
        <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="css/navbar.css">
        <link rel="stylesheet" href="css/footer.css">
        <link rel="stylesheet" href="css/general.css">
        <link rel="stylesheet" href="css/curso.css">
    </head>
    <body>
        
        <div class="container">
        
            <%@include file="/resources/header.jsp" %>
            
            <article id="curso">
                
                <div class="row">
                    
                    <div class="col s12 m6 foto-container">
                        <img src="<%= curso.getFoto() %>">
                    </div>
                    
                    <div class="col s12 m6">
                        <h1><%= curso.getNombre() %></h1>
                        <h2><%= curso.getCategoria().getNombre() %></h2>
                        <p><%= curso.getDescripcion() %></p>

                        <% if (!inscripto) { %>
                            <% if (usuario == null) { %>
                                <a class="waves-effect waves-light btn" href="/educarjava/login">Inscribirme</a>
                            <% } else { %>
                                <a class="waves-effect waves-light btn" id="btn-inscribir">Inscribirme</a>
                            <% } %>
                        <% } %>
                            
                        <% if (middleware.esAdmin(request, response)) { %>
                            <a class="waves-effect waves-light btn red" id="btn-eliminar"><i class="material-icons">delete</i></a>
                        <% } %>
                    </div>

                </div>

                <% if (middleware.estaLogeado(request, response) && inscripto) { %>
                    <div class="row">
                        <form id="nuevo-comentario-form" class="col s12">
                            <div class="input-field col s12 m10">
                                <input id="nuevo-comentario" name="nuevo-comentario" type="text">
                                <label for="nuevo-comentario">Comentario</label>
                            </div>
                            <button class="btn waves-effect waves-light" type="submit" name="action">Comentar</button>
                        </form>
                    </div>
                <% } %>
                <div id="comentarios"></div>
                <div id="temas-container"></div>
            </article>
                
            <%@include file="/resources/footer.jsp" %>

        </div>
            
        <!-- Compiled and minified JavaScript -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
        <script src="https://kit.fontawesome.com/3981d882ec.js" crossorigin="anonymous"></script>
        
        <script src="js/curso.js"></script>
    </body>
</html>
