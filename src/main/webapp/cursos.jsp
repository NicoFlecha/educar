<%@page import="java.util.ArrayList"%>
<%@page import="com.nico.educarjava.model.entities.Curso"%>
<%@page import="com.nico.educarjava.model.entities.Categoria"%>
<%
    ArrayList<Curso> cursos = (ArrayList<Curso>) request.getAttribute("cursos");
    ArrayList<Categoria> categorias = (ArrayList<Categoria>) request.getAttribute("categorias");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cursos</title>
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

        <article id="cursos">
          <h1>Cursos</h1>
          <% if(middleware.esAdmin(request, response)) { %>
          <a href="cursos?ver=nuevo">Agregar cursos</a>
          <% } %>
          <div class="option-container">
            <div id="categorias-btn">
              <a class="waves-effect waves-light btn modal-trigger" href="#modal-categorias"><i class="material-icons left">cloud</i>Categorias</a>
            </div>
  
            <!-- <div id="filtros-btn">
              <a class='dropdown-trigger btn' href='#' data-target='dropdown-filtros'>Filtros</a>
            </div> -->
          </div>

          <div class="new row">
            <h2 class="flow-text">Cursos Recientes</h2>
            
            <%if(cursos.size() > 0 ) { %>
                <% for(Curso curso: cursos) { %>
                    <div class="curso-container col s12 m6 l4">
                        <a href="cursos?ver=curso&curso=<%=curso.getId()%>">
                            <div class="card">
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

          <!--<div class="Populares row">
            <h2 class="flow-text">Los más Populares</h2>
            <div class="curso-container col s12 m6 l4">
              <div class="card">
                <div class="title-container row">
                  <div class="title-img col s4 m12">
                    <img src="./images/logo.png" alt="curso">
                  </div>
                  <div class="title-text col s8 m12">
                    <span>Acá va el Título del Curso</span>
                  </div>
                </div>
                <div class="details-container row">
                  <div class="price col s4">
                    <span>6000</span>
                  </div>
                  <div class="duration col s4">
                    <i class="material-icons">watch_later</i><span>&nbsp3h</span>
                  </div>
                  <div class="rating col s4">
                    <i class="material-icons">star</i><span>8</span>
                  </div>
                </div>
              </div>
            </div>
            <div class="curso-container col s12 m6 l4">
              <div class="card">
                <div class="title-container row">
                  <div class="title-img col s4 m12">
                    <img src="./images/logo.png" alt="curso">
                  </div>
                  <div class="title-text col s8 m12">
                    <span>Acá va el Título del Curso</span>
                  </div>
                </div>
                <div class="details-container row">
                  <div class="price col s4">
                    <span>6000</span>
                  </div>
                  <div class="duration col s4">
                    <i class="material-icons">watch_later</i><span>3h</span>
                  </div>
                  <div class="rating col s4">
                    <i class="material-icons">star</i><span>8</span>
                  </div>
                </div>
              </div>
            </div>
            <div class="curso-container col s12 m6 l4">
              <div class="card">
                <div class="title-container row">
                  <div class="title-img col s4 m12">
                    <img src="./images/logo.png" alt="curso">
                  </div>
                  <div class="title-text col s8 m12">
                    <span>Acá va el Título del Curso</span>
                  </div>
                </div>
                <div class="details-container row">
                  <div class="price col s4">
                    <span>6000</span>
                  </div>
                  <div class="duration col s4">
                    <i class="material-icons">watch_later</i><span>3h</span>
                  </div>
                  <div class="rating col s4">
                    <i class="material-icons">star</i><span>8</span>
                  </div>
                </div>
              </div>
            </div>
          </div>-->
        </article>

        <%@include file="/resources/footer.jsp" %>

    </div>
    

    <!-- ------- MODAL CATEGORIAS ------- -->
    <div id="modal-categorias" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <h4>Categorias</h4>
                <!-- <button class="modal-close">
                  <i class="material-icons">close</i>
                </button> -->
            </div>
            <div class="categorias row">

                <% for(Categoria categoria: categorias) { %>
                    <div class="categoria-container col s12 m4">
                      <div class="card">
                        <div class="card-image" style="background-image: url('https://miro.medium.com/max/1024/1*vxjAHkrXbGG6gOiPZgjeZA.jpeg');"></div>
                        <div class="card-text">
                            <h5><%= categoria.getNombre()%></h5>
                        </div>
                      </div>
                    </div>
                <% } %>

            </div>
            <% if (middleware.esAdmin(request, response)) { %>
                <a href="categorias?ver=nuevo">Agregar categorias</a>
            <% } %>
        </div>
    </div>


    <!-- ------- DROPDOWN FILTROS ------- -->
    <ul id='dropdown-filtros' class='dropdown-content'>
      <form action="" method="get">
        <p>
          <label>
            <input type="checkbox" />
            <span>Red</span>
          </label>
        </p>
        <p>
          <label>
            <input type="checkbox" checked="checked" />
            <span>Yellow</span>
          </label>
        </p>
        <p class="range-field">
          <input type="range" id="test5" min="0" max="10" />
        </p>
      </form>
    </ul>



    <!-- Compiled and minified JavaScript -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js"></script>
    <script src="https://kit.fontawesome.com/3981d882ec.js" crossorigin="anonymous"></script>

    <script src="js/cursos.js"></script>
</body>
</html>