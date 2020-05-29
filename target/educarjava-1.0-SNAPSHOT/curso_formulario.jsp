<%@page import="com.nico.educarjava.model.entities.Curso"%>
<%@page import="com.nico.educarjava.model.entities.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("listadoCategorias");
    
    Curso curso = (Curso) request.getAttribute("curso");
    
    String h1 = "Nuevo Curso";
    int cursoId = 0;
    String titulo = "Nuevo Curso";
    String foto = "";
    String descripcion = "";
    String fecha_inicio = "";
    String duracion = "";
    int categoriaId = 1;
    int precio = 0;
    
    if (curso != null) {
        
        h1 = "Modificar Curso";
        cursoId = curso.getId();
        titulo  = curso.getNombre();
        foto = curso.getFoto();
        descripcion = curso.getDescripcion();
        fecha_inicio = String.valueOf(curso.getFechaInicio());
        duracion = curso.getDuracion();
        categoriaId = curso.getCategoria().getId();
        precio = curso.getPrecio();
       
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= titulo %></title>
    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <!-- Compiled and minified CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/navbar.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/cursos.css">
    <link rel="stylesheet" href="css/general.css">
</head>
<body>
    <div class="container">
        
        <%@include file="/resources/header.jsp" %>

        <article id="formulario">
            
            <div class="row">
                <div class="col s12 m8 offset-m2">
                    <h1><%= h1 %></h1>
                    <form action="cursos?accion=guardar" method="POST" id="guardar-curso-form">
                        <input type="hidden" name="curso_id" id="curso_id" value="<%= cursoId %>">
                        <label for="nombre">Nombre:</label><br>
                        <input type="text" name="nombre" id="nombre" value="<%= titulo %>"><br>
                        <label for="foto">Foto</label><br>
                        <input type="text" name="foto" id="foto" value="<%= foto %>"><br>
                        <label for="descripcion">Descripcion:</label><br>
                        <input type="text" name="descripcion" id="descripcion" value="<%= descripcion%>"><br>
                        <div class="col s12 m6">
                            <label for="fecha_inicio">Fecha de Inicio:</label>
                            <input type="text" class="datepicker" name="fecha_inicio" id="fecha_inicio" value="<%=fecha_inicio%>">
                        </div>
                        <div class="col s12 m6">
                            <label for="duracion">Duración:</label>
                            <input type="text" name="duracion" id="duracion" value="<%= duracion%>">
                        </div>
                        <div class="col s12 m6">
                            <label for="categoria">Categoria:</label>
                            <div class="input-field">
                                <select name="categoria" id="categoria">
                                    <% for(Categoria categoria: categorias) { %>
                                        <option value="<%= categoria.getId() %>" <% if (categoria.getId() == categoriaId) { %> selected <% } %> ><%= categoria.getNombre() %></option>
                                    <%}%>
                                </select>
                            </div>
                        </div>
                        <div class="col s12 m6">
                            <label for="precio">Precio:</label>
                            <input type="number" name="precio" id="precio" value="<%=precio%>">
                        </div>
                        <div class="col s12 right-align">
                            <button class="btn waves-effect waves-light" type="submit" id="guardar-curso-btn">
                                Guardar
                            </button>
                        </div>
                    </form>
                        
                        <h2>Temas</h2>
                        
                        <div id="temas-container"></div>
                        
                        <form action="temas" method="POST" id="temas-form">
                        <input type="hidden" id="curso_id_tema" name="curso_id" value="<%= cursoId %>">
                        <div class="col s12 m4">
                            <label for="numero_tema">Numero de Tema</label>
                            <input type="text" name="numero_tema" id="numero_tema">
                        </div>
                        <div class="col s12 m8">
                            <label for="nombre_tema">Nombre:</label>
                            <input type="text" name="nombre_tema" id="nombre_tema">
                        </div>
                        <label for="descripcion_tema">Descripción:</label><br>
                        <input type="text" name="descripcion_tema" id="descripcion_tema"><br>
                        <label for="archivo_tema">Archivo:</label><br>
                        <input type="text" name="archivo_tema" id="archivo_tema"><br>
                        <div class="col s12 right-align">
                            <button class="btn waves-effect waves-light" type="submit" name="action" id="guardar-tema-btn">
                                Guardar
                            </button>
                        </div>
                    </form>
                </div>
            </div>

        </article>

        <%@include file="/resources/footer.jsp" %>

    </div>



    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="https://kit.fontawesome.com/3981d882ec.js" crossorigin="anonymous"></script>

    <script src="js/cursos_formulario.js"></script>
    <script src="js/temas_formulario.js"></script>
</body>
</html>