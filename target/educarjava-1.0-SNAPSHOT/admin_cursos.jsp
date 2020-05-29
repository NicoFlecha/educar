<%@page import="java.util.ArrayList"%>
<%@page import="com.nico.educarjava.model.entities.Curso"%>
<%@page import="com.nico.educarjava.model.entities.Categoria"%>
<%
    ArrayList<Curso> cursos = (ArrayList<Curso>) request.getAttribute("cursos");
    ArrayList<Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("categorias");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Administrar Cursos</title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/navbar.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/general.css">
    <link rel="stylesheet" href="css/cursos.css">
</head>
<body>
    <div class="container">
        
        <%@include file="/resources/header.jsp" %>

        <article id="cursos">
            
            <div class="row">
            <%if(cursos.size() > 0 ) { %>
                <% for(Curso curso: cursos) { %>
                    <div class="curso-container col s12 m6 l4">
                        <a href="admin?editar=curso&curso=<%=curso.getId()%>">
                            <div class="card center-align">
                              <div class="title-container row">
                                <div class="title-img col s4 m12">
                                  <img src="<%= curso.getFoto() %>" alt="curso">
                                </div>
                                <div class="title-text col s8 m12">
                                  <span><%=curso.getNombre()%></span>
                                </div>
                              </div>
                              <div class="details-container row">
                                <div class="price col s4">
                                    <span><%=curso.getPrecio()%></span>
                                </div>
                                <div class="duration col s4">
                                    <i class="material-icons">watch_later</i><span>&nbsp<%=curso.getDuracion()%>h</span>
                                </div>
                                <div class="rating col s4">
                                  <i class="material-icons">star</i><span>8</span>
                                </div>
                              </div>
                            </div>
                        </a>
                    </div>
                <% } %>
            <% } else { %>
                <h3>No hay Cursos</h3>
            <% } %>
            </div>
            
        </article>

        <%@include file="/resources/footer.jsp" %>

    </div>

    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="https://kit.fontawesome.com/3981d882ec.js" crossorigin="anonymous"></script>
</body>
</html>
