let form = document.getElementById("temas-form");
let cursoInput = cursoId;
let numeroTemaInput = document.getElementById("numero_tema");
let nombreInput = document.getElementById("nombre_tema");
let descripcionInput = document.getElementById("descripcion_tema");
let archivoInput = document.getElementById("archivo_tema");
let guardarBtn = document.getElementById("guardar-tema-btn");

let inputs = [numeroTemaInput, nombreInput, descripcionInput, archivoInput];

form.addEventListener("submit", event => {
    
    event.preventDefault();
    guardarTema();
    
});

function obtenerValorParametro(sParametroNombre) {
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

const guardarTema = async () => {
    
    let cursoId= cursoInput.value;
    let numeroTema = numeroTemaInput.value;
    let nombre = nombreInput.value;
    let descripcion = descripcionInput.value;
    let archivo = archivoInput.value;
    
    let url = `${BASE_URL}/temas?curso_id=${cursoId}&numero_tema=${numeroTema}&nombre_tema=${nombre}&descripcion_tema=${descripcion}&archivo_tema=${archivo}`;
    
    let response = await fetch(url, {method: "POST"});
    
    let json = await response.json();
    
    if(json.status == "OK") {
        inputs.forEach(input => {
           input.value = ""; 
        });
    }
    
    M.toast({html: json.message});
    
    mostrarTemas(cargarTemas(cursoId));
    
}

document.addEventListener("DOMContentLoaded", () => {
    let cursoId = obtenerValorParametro("curso");
    let temas = cargarTemas(cursoId);
    mostrarTemas(temas);
})

let temasContainer = document.getElementById("temas-container");

const cargarTemas = async (cursoId) => {
    
    let url = `${BASE_URL}/temas?ver=temas&curso=${cursoId}`;
    
    let response = await fetch(url);
    
    let json = await response.json();
    
    return json;
    
}

const mostrarTemas = async json => {
    
    json = await json; 
    
    let editar = obtenerValorParametro('editar');
    let ver = obtenerValorParametro('ver');
    let botonBorrar = "";
    
    if(editar == "curso" || ver == "nuevo") {
        
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

const eliminarTema = async event => {

    liTema = event.path[4];
    
    idTema = liTema.getAttribute("data-tema-id");
    
    url = `${BASE_URL}/temas`;
    
    params = `?tema=${idTema}`;
    
    response = await fetch(url+params, {method: "DELETE"});
    
    json = await response.json();
    
    cursoId = obtenerValorParametro("curso");
    
    mostrarTemas(cargarTemas(cursoId));
    
    M.toast({html: json.message});

}
