const BASE_URL = "http://localhost:8080/educarjava";

let alumnosArticle = document.getElementById("alumnos");

let alumnosContainer = document.createElement("div");

const obtenerAlumnos = async () => {
    
    let url = `${BASE_URL}/admin`;
    
    let params = '?obtener=alumnos';
    
    let response = await fetch(url + params);
    
    let json = await response.json();
    
    return json;
    
}

const buscarAlumnos = async (busqueda) => {
    
    let url = `${BASE_URL}/admin`;
    
    let params = `?obtener=busqueda&buscar=${busqueda}`;
    
    let response = await fetch(url + params);
    
    let json = await response.json();
    
    return json;
    
}

const mostrarTabla = async (titulos, json, container, excepciones) => {

    json = await json;
    
    let tabla = document.createElement("table");
    tabla.classList.add("striped");
    
    let encabezado = document.createElement("thead");
    let filaTitulos = document.createElement("tr");
    
    titulos.forEach(titulo => {
        
        filaTitulos.innerHTML +=    `<th>${titulo}</th>`;
        
    });
    
    encabezado.appendChild(filaTitulos);
    tabla.appendChild(encabezado);
    
    let cuerpo = document.createElement("tbody");

    
    if (json.status == "OK") {
        
        json.data.forEach(dato => {
            
            for (excepcion of excepciones) {
                
                for (parte in dato) {
                    
                    if(parte == excepcion) {
                        
                        delete dato[parte];
                        
                    }
                    
                }
                
            }
            
        })
        
        json.data.forEach(dato => {
            
            let filaCuerpo = document.createElement("tr");
            
            for (parte in dato) {
                
                if(typeof dato[parte] != "object") {
                    filaCuerpo.innerHTML += `<td>${dato[parte]}</td>`;
                }
                
            }
            
            cuerpo.appendChild(filaCuerpo);
            
        });
        
        tabla.appendChild(cuerpo);
        
    }
    
    container.innerHTML = "";
    
    container.appendChild(tabla);
    
}

let titulos = ["ID", "Nombre", "Apellido", "Email"];

mostrarTabla(titulos, obtenerAlumnos(), alumnosContainer, ["foto"]);

alumnosArticle.appendChild(alumnosContainer);

let buscarInput = document.getElementById("buscar-alumnos");

buscarInput.addEventListener("keyup", () => {
    mostrarTabla(titulos, buscarAlumnos(buscarInput.value), alumnosContainer, ["foto"]);
});