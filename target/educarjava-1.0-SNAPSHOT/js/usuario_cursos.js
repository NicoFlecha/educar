const BASE_URL = "http://localhost:8080/educarjava";

const mostrarCursos = async () => {
    
    url = `${BASE_URL}/usuario`;
    
    params = '?obtener=cursos';
    
    response = await fetch(url + params);
    
    json = await response.json();
    
    if (json.status == "OK") {
        
        cursosContainer = document.getElementById("cursos-container");
        
        if (json.data.length > 0) {
            
            cursosContainer.innerHTML = "";
            
            row = document.createElement("div");
            row.classList.add("row");
            
            json.data.forEach(curso => {
                
                row.innerHTML +=     '<div class="curso-container col s12 m6 l4">' +
                                        `<a href="cursos?ver=curso&curso=${curso.id}">` +
                                            '<div class="card">' +
                                              '<div class="title-container row">' +
                                                '<div class="title-img col s4 m12">' +
                                                  `<img src="${curso.foto}" alt="${curso.nombre}">` +
                                                '</div>' +
                                                '<div class="title-text col s8 m12">' +
                                                  `<span>${curso.nombre}</span>` +
                                                '</div>' +
                                              '</div>' +
                                              '<div class="details-container row">' +
                                                '<div class="price col s4">' +
                                                    `<span>$${curso.precio}</span>` +
                                                '</div>' +
                                                '<div class="duration col s4">' +
                                                    `<i class="material-icons">watch_later</i><span> ${curso.duracion}h</span>` +
                                                '</div>' +
                                                '<div class="rating col s4">' +
                                                  `<i class="material-icons">star</i><span>8</span>` +
                                                '</div>' +
                                              '</div>' +
                                            '</div>' +
                                        '</a>' +
                                    '</div>';
                
            });
            
            h1 = document.createElement("h1");
            h1.innerText = "Tus cursos";
            h1.classList.add("center-align");
            
            cursosContainer.appendChild(h1);
            cursosContainer.appendChild(row);
            
        } else {
            message = document.createElement("h2");
            message.innerText = json.message;
            message.classList.add("center.align");
            cursosContainer.appendChild(message);
        }
        
    }
    
}

document.addEventListener("DOMContentLoaded", mostrarCursos);