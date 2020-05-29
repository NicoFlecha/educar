const BASE_URL = "http://localhost:8080/educarjava";

let btnInscribir = document.getElementById("btn-inscribir");

const obtenerValorParametro = sParametroNombre => {
    let sPaginaURL = window.location.search.substring(1);
    let sURLVariables = sPaginaURL.split('&');
    for (var i = 0; i < sURLVariables.length; i++) {
        var sParametro = sURLVariables[i].split('=');
        if (sParametro[0] == sParametroNombre) {
          return sParametro[1];
        }
    }
    return null;
}

if(btnInscribir != null) {
    
    btnInscribir.addEventListener("click", () => {
    let cursoId = obtenerValorParametro("curso");
    inscribirUsuario(cursoId);
    
});
    
}

const inscribirUsuario = async (cursoId) => {
    
    let url = `${BASE_URL}/cursos?accion=inscribir&idCurso=${cursoId}`;
    let response = await fetch(url, {
        method: "POST"
    });
    let json = await response.json();
    
    M.toast({html: json.message});
    
    mostrarTemas(cargarTemas(cursoId));
    
    btnInscribirPadre = btnInscribir.parentElement;
    
    btnInscribirPadre.removeChild(btnInscribir);
}

let comentariosContainer = document.getElementById("comentarios");

document.addEventListener("DOMContentLoaded", () => {
    let cursoId = obtenerValorParametro("curso");
    traerComentarios(cursoId);
    temasContainer = document.getElementById("temas-container");
    if(temasContainer) {
        mostrarTemas(cargarTemas(cursoId));
    }
})

const traerComentarios = async (cursoId) => {
    
    let url = `${BASE_URL}/cursos?ver=comentarios&curso=${cursoId}`;
    let response = await fetch(url);
    let json = await response.json();
    
    let datos = json.data;
    
    let comentarios = [];
    
    datos.forEach(dato => {
       
       if (dato.comentario != null) {
        
            let comentario = [];
            
            comentario["mensaje"] = dato.comentario;
            comentario["usuario"] = dato.usuario.nombre;
            
            comentarios.push(comentario);
           
       }
        
    });
    
    if (comentarios.length > 0) {
        
        comentariosContainer.innerHTML = "";
        
        let row = document.createElement("div");
        row.classList.add("row");
        
        comentarios.forEach(comentario => {
            
            row.innerHTML +=    '<div class="col s12 m4">' +
                                    '<div class="card blue-grey darken-1">' +
                                        '<div class="card-content white-text center-align">' +
                                            `<p>${comentario.mensaje}</p>` +
                                            `<p>${comentario.usuario}</p>`
                                        '</div>' +
                                    '</div>' +
                                '</div>';
            
        })
        
        let titulo = document.createElement("h3");
        titulo.innerText = "Comentarios";
        
        comentariosContainer.appendChild(titulo);
        comentariosContainer.appendChild(row);
    }
    
}

let btnEliminar = document.getElementById("btn-eliminar");

if (btnEliminar) {
    btnEliminar.addEventListener("click", () => {
        let cursoId = obtenerValorParametro("curso");
        eliminarCurso(cursoId);
    });
}

const eliminarCurso = async cursoId => {
    
    let url = `${BASE_URL}/cursos?accion=curso&curso=${cursoId}`;
    let response = await fetch(url, {method: "DELETE"});
    let json = await response.json();
    
    M.toast({html: json.message});
    
    setTimeout(() => {
        window.location = "/educarjava/cursos";
    }, 2000);
    
}

const cargarTemas = async (cursoId) => {
    
    let url = `${BASE_URL}/temas?ver=temas&curso=${cursoId}`;
    
    let response = await fetch(url);
    
    let json = await response.json();
    
    return json;
    
}

const mostrarTemas = async json => {
    
    json = await json; 
    
    let editar = obtenerValorParametro('editar');
    let botonBorrar = "";
    
    if(editar == "curso") {
        
        botonBorrar =   '<div class="right-align">' +
                            '<a class="btn-floating red btn-borrar">' +
                                '<i class="large material-icons">delete</i>' +
                            '</a>' +
                        '</div>';
        
    }
    
    if (json.status == "OK") {
        
        if (json.data.length > 0) {
            
            temasContainer.innerHTML = "";
            
            let temas = json.data;
            let listaTemas = document.createElement("ul");
            listaTemas.classList.add("collapsible");
            
            temas.forEach(tema => {
                
                listaTemas.innerHTML += `<li data-tema-id=${tema.id}>` +
                                            `<div class="collapsible-header">${tema.numero_tema} - ${tema.nombre}</div>` +
                                            `<div class="collapsible-body">
                                                ${botonBorrar}
                                                <span>${tema.descripcion}</span>
                                            </div>` +
                                        '</li>';
                
            });
            
            h3 = document.createElement("h3");
            h3.innerText = "Temas";
            h3.classList.add("center-align");
            
            temasContainer.appendChild(h3);
            temasContainer.appendChild(listaTemas);
            
            var elem = document.querySelector('.collapsible');
            var instance = M.Collapsible.init(elem);
            
        }
        
        btnBorrarArray = document.getElementsByClassName("btn-borrar");
        for (i = 0; i < btnBorrarArray.length; i++) {
            let btnBorrar = btnBorrarArray.item(i);
            btnBorrar.addEventListener("click", eliminarTema);
        }
        
    }
    
}

let formNuevoComentario = document.getElementById("nuevo-comentario-form");

if(formNuevoComentario) {
   formNuevoComentario.addEventListener("submit", event => {
        event.preventDefault();
        guardarComentario();
    }); 
}


const guardarComentario = async () => {
    
    url = `${BASE_URL}/cursos`;
    
    params = `?accion=comentario`;
    
    cursoId = obtenerValorParametro("curso");
    params += `&curso=${cursoId}`;
    
    comentarioInput = document.getElementById("nuevo-comentario");
    params += `&comentario=${comentarioInput.value}`;
    
    response = await fetch(url+params, {method: "PUT"});
    
    json = await response.json();
    
    comentarioInput.value = "";
    
    M.toast({html: json.message});
    
    traerComentarios(cursoId);
    
}