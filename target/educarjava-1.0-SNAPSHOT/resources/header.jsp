<%@page import="com.nico.educarjava.utils.Middleware"%>
<%@page import="com.nico.educarjava.model.entities.Usuario"%>
<%

    Usuario usuario = null;
    
    try {
        usuario = (Usuario) session.getAttribute("user");
        System.out.println(usuario.getApellido());
    } catch (NullPointerException ex) {
        System.out.println("No hay usuario");
    }
  

    Middleware middleware = new Middleware();
    
%>

<header>
    <nav class="teal lighten-2">
      <div class="nav-wrapper">
        <a href="/educarjava" class="brand-logo">
            <div class="navbar-logo">
              <img src="https://img.icons8.com/ios-glyphs/100/ffffff/open-book.png"/>&nbspEducAR
            </div>
          </a>
        <a href="#" data-target="mobile-demo" class="sidenav-trigger"><i class="material-icons">menu</i></a>
        <ul class="right hide-on-med-and-down">
            <li><a href="cursos"><i class="material-icons right">school</i>Cursos</a></li>
            <li><a href="noticias"><i class="material-icons right">info_outline</i>Noticias</a></li>
            <!--<li><a href="nosotros.html"><i class="material-icons right">people</i>Nosotros</a></li>-->
            <% if (usuario == null) { %>
              <li><a href="login"><i class="material-icons right">account_circle</i>Iniciar Sesión</a></li>
            <% } else { %>
              <li><a class="dropdown-usuario-trigger" href="#!" data-target="dropdown-usuario"><%= usuario.getNombre() + " " + usuario.getApellido() %><i class="material-icons right">arrow_drop_down</i></a></li>
            <% } %>
            <% if (middleware.esAdmin(request, response)) { %>
              <li>ADMIN</li>
            <% } %>
        </ul>
      </div>
    </nav>
</header>

<!-- ------- SIDENAV ------- -->
<div class="sidenav collection teal " id="mobile-demo">
    <a href="cursos" class="collection-item waves-effect waves-light"><i class="material-icons left">school</i>Cursos</a>
    <a href="noticias" class="collection-item waves-effect waves-light"><i class="material-icons left">info_outline</i>Noticias</a>
    <!--<a href="nosotros.html" class="collection-item waves-effect waves-light"><i class="material-icons left">people</i>Nosotros</a></a>-->
    <% if (usuario == null) { %>
        <a href="login" class="collection-item waves-effect waves-light"><i class="material-icons left">account_circle</i>Iniciar Sesión</a>
    <% } else { %>
        <a href="#!" class="collection-item waves-effect waves-light dropdown-usuario-trigger" data-target="dropdown-usuario-mobile"><%= usuario.getNombre() + " " + usuario.getApellido() %><i class="material-icons right">arrow_drop_down</i></a>
    <% } %>
</div>

<!-- ------- DROPDOWN USUARIO ------- -->
<!-- --- Desktop --- -->
<ul id="dropdown-usuario" class="dropdown-content">
    <% if (middleware.esAdmin(request, response)) { %>
        <li><a href="admin?ver=panel">Ir al Panel</a></li>
    <% } %>
    <li><a href="usuario?ver=cursos">Mis cursos</a></li>
    <li><a href="usuario?ver=perfil">Mi Perfil</a></li>
    <li><a href="logout">Cerrar Sesión</a></li>
</ul>
    
<!-- --- Mobile --- -->
<ul id="dropdown-usuario-mobile" class="dropdown-content">
    <% if (middleware.esAdmin(request, response)) { %>
        <li><a href="admin?ver=panel">Ir al Panel</a></li>
    <% } %>
    <li><a href="usuario?ver=cursos">Mis cursos</a></li>
    <li><a href="usuario?ver=perfil">Mi Perfil</a></li>
    <li><a href="logout">Cerrar Sesión</a></li>
</ul>

<script src="js/header.js"></script>