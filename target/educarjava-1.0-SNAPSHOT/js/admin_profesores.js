const BASE_URL = "http://localhost:8080/educarjava";
let profesoresContainer = document.getElementById("profesores-container");
let profesorForm = document.getElementById("profesor-form");

document.addEventListener("DOMContentLoaded", () => {
    M.updateTextFields();
    var elems = document.querySelector('.datepicker');
    var instances = M.Datepicker.init(elems, {
        autoClose: true,
        format: "yyyy-mm-dd",
        firstDay: 1,
        minDate: new Date(1900, 1, 1),
        disableWeekends: false,
        i18n: {
                months: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
                monthsShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"],
                weekdays: ["Domingo","Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"],
                weekdaysShort: ["Dom","Lun", "Mar", "Mie", "Jue", "Vie", "Sab"],
                weekdaysAbbrev: ["D","L", "M", "M", "J", "V", "S"],
                cancel: "Cancelar",
                clear: "Volver a empezar"
        }
    });
    mostrarProfesores(obtenerProfesores());
});
const obtenerProfesores = async () => {
    
    let url = `${BASE_URL}/profesor`;
    
    let params = '?obtener=profesores';
    
    let response = await fetch(url + params);
    
    let json = await response.json();
    
    return json;
    
}

const obtenerProfesor = async (profesorId) => {
    
    let url = `${BASE_URL}/profesor`;
    
    let params = `?obtener=profesor&profesor=${profesorId}`;
    
    let response = await fetch(url + params);
    
    let json = await response.json();
    
    return json.data[0];
    
}

const mostrarProfesores = async json => {
    
    json = await json;
    
    if (json.status == "OK") {
        
        if (json.data.length > 0) {
            
            profesoresContainer.innerHTML = "";
            
            let tabla = document.createElement("table");
            tabla.classList.add("striped");
            
            let encabezadoTabla = document.createElement("thead");
            encabezadoTabla.innerHTML = "<tr>" +
                                            "<th>ID</th>" +
                                            "<th>Nombre</th>" +
                                            "<th>Apellido</th>" +
                                            "<th>Email</th>" +
                                            "<th>Fecha de Nacimiento</th>" +
                                            "<th>Acciones</th>" +
                                        "</tr>" ;
            let cuerpoTabla = document.createElement("tbody");
            
            json.data.forEach(profesor => {
               
                cuerpoTabla.innerHTML +=    "<tr>" +
                                                `<td>${profesor.id}</td>` +
                                                `<td>${profesor.nombre}</td>` +
                                                `<td>${profesor.apellido}</td>` +
                                                `<td>${profesor.email}</td>` +
                                                `<td>Fecha</td>` +
                                                `<td>   <a class="editar-profesor waves-effect waves-light btn blue tooltipped" data-position="bottom" data-tooltip="Editar" data-profesor-id=${profesor.id}><i class="material-icons">edit</i></a>
                                                        <a class="agregar-curso waves-effect waves-light btn tooltipped" data-position="bottom" data-tooltip="Administrar cursos" data-profesor-id=${profesor.id}><i class="material-icons">book</i></a>
                                                        <a class="eliminar-profesor waves-effect waves-light btn red tooltipped" data-position="bottom" data-tooltip="Eliminar" data-profesor-id=${profesor.id}><i class="material-icons">delete</i></a>
                                                </td>` +
                                            "</tr>"
                
            });
            
            tabla.appendChild(encabezadoTabla);
            tabla.appendChild(cuerpoTabla);
            
            let h1 = document.createElement("h1");
            h1.classList.add("center-align");
            h1.innerText = "Listado de Profesores";
            
            profesoresContainer.appendChild(h1);
            profesoresContainer.appendChild(tabla);
            
            var elems = document.querySelectorAll('.tooltipped');
            var instances = M.Tooltip.init(elems);
            
            acciones();
            
        } else {
            
            profesoresContainer.innerHTML = "";
            
            let message = document.createElement("h1");
            message.classList.add("center-align");
            message.innerText = json.message;
            
            profesoresContainer.appendChild(message)
            
        }
        
    }
    
}

const guardarProfesor = async () => {
    
    let idInput = document.getElementById("profesor-id");
    let nombreInput = document.getElementById("nombre");
    let apellidoInput = document.getElementById("apellido");
    let fechaNacimientoInput = document.getElementById("fecha-nacimiento");
    let emailInput = document.getElementById("email");
    let passwordInput = document.getElementById("password");
    let fotoInput = document.getElementById("foto");
    
    let inputs = [nombreInput, apellidoInput, fechaNacimientoInput, emailInput, passwordInput, fotoInput];
    
    url = `${BASE_URL}/profesor`;
    
    let params = `?nombre=${nombreInput.value}`;
    params += `&apellido=${apellidoInput.value}`;
    params += `&fecha_nacimiento=${fechaNacimientoInput.value}`;
    params += `&email=${emailInput.value}`;
    params += `&password=${passwordInput.value}`;
    params += `&foto=${fotoInput.value}`;
    
    let method;
    if (idInput.value == "0") {
        method = "POST"; 
    } else {
        method = "PUT";
        params += `&id=${idInput.value}`;
    }
    
    let response = await fetch(url + params, {method: method});
    
    let json = await response.json();
    
    if (json.status == "OK") {
        
        M.toast({html: json.message});
        inputs.forEach(input => {
           
            input.value = "";
            
        });
        
        elem = document.querySelector(".modal");
        instance = M.Modal.getInstance(elem);
        instance.close();
        mostrarProfesores(obtenerProfesores());
        
    }
    
}

const eliminarProfesor = async (profesorId) => {
    
    let url = `${BASE_URL}/profesor`;
    
    let params = `?profesor=${profesorId}`;
    
    let response = await fetch(url + params, {method: "DELETE"});
    
    let json = await response.json();
    
    if (json.status == "OK") {
        
        M.toast({html: json.message});
        
        mostrarProfesores(obtenerProfesores());
        
    }

    
}

profesorForm.addEventListener("submit", event => {
    
    event.preventDefault();
    guardarProfesor();
    
});

const acciones = async () => {
    
    let idInput = document.getElementById("profesor-id");
    let nombreInput = document.getElementById("nombre");
    let apellidoInput = document.getElementById("apellido");
    let fechaNacimientoInput = document.getElementById("fecha-nacimiento");
    let emailInput = document.getElementById("email");
    let passwordInput = document.getElementById("password");
    let fotoInput = document.getElementById("foto");
    
    let botonesEditar = document.querySelectorAll(".editar-profesor");
    let botonesAgregarCurso = document.querySelectorAll(".agregar-curso");
    let botonesEliminar = document.querySelectorAll(".eliminar-profesor");
    let btnAgregarProfesor = document.getElementById("agregar-profesor");
    
    let tituloModal = document.getElementById("modal-title");
    
    botonesEditar.forEach(botonEditar => {
        
        elem = document.querySelector(".modal");
        instance = M.Modal.getInstance(elem);
        
        botonEditar.addEventListener("click", (event) => {
            let profesorId;
            if(event.target.attributes[3]) {
                profesorId = event.target.attributes[3].value;
            } else {
                profesorId = event.target.parentElement.getAttribute("data-profesor-id");
            }
            
            obtenerProfesor(profesorId).then(profesor => {
                tituloModal.innerText = "Editar Profesor";
                idInput.value = profesor.id;
                nombreInput.value = profesor.nombre;
                apellidoInput.value = profesor.apellido;
                emailInput.value = profesor.email;
                fotoInput.value = profesor.foto;
                instance.open();
            })
            
        })
        
    });
    
    botonesAgregarCurso.forEach(botonAgregarCurso => {
        
        botonAgregarCurso.addEventListener("click", (event) => {
            let elem = document.getElementById("modal-cursos");
            let instance = M.Modal.init(elem);
            instance.open();
            let profesorId;
            if(event.target.attributes[3]) {
                profesorId = event.target.attributes[3].value;
            } else {
                profesorId = event.target.parentElement.getAttribute("data-profesor-id");
            }
            mostrarCursosModal(obtenerCursos(), profesorId);
        })
        
    });
    
    botonesEliminar.forEach(botonEliminar => {
        
        botonEliminar.addEventListener("click", (event) => {
            let profesorId;
            if(event.target.attributes[3]) {
                profesorId = event.target.attributes[3].value;
            } else {
                profesorId = event.target.parentElement.getAttribute("data-profesor-id");
            }
            eliminarProfesor(profesorId);
            
        })
        
    });
    
    btnAgregarProfesor.addEventListener("click", () => {
        tituloModal.innerText = "Agregar un profesor";
        idInput.value = 0;
        nombreInput.value = "";
        apellidoInput.value = "";
        emailInput.value = "";
        fotoInput.value = "";
    });
    
}

document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.modal');
    var instances = M.Modal.init(elems);
});

const obtenerCursos = async () => {
    
    let url = `${BASE_URL}/cursos`;
    
    let params = "?obtener=cursos";
    
    let response = await fetch(url + params);
    
    let json = await response.json();
    
    return json;
    
}

const agregarProfesorCurso = async (profesorId, cursoId) => {
    
    let url = `${BASE_URL}/cursos`;
    
    let params = "?accion=agregarProfesor";
    params += `&profesor=${profesorId}`;
    params += `&curso=${cursoId}`;
    
    let response = await fetch(url + params, {method: "POST"});
    
    let json = await response.json();
    
    if (json.status == "OK") {
        
        M.toast({html: json.message});
        
    }
    
}

const eliminarProfesorCurso = async (profesorId, cursoId) => {
    
    let url = `${BASE_URL}/cursos`;
    
    let params = "?accion=profesor";
    params += `&curso=${cursoId}`;
    params += `&profesor=${profesorId}`;
    
    let response = await fetch(url + params, {method: "DELETE"});
    
    let json = await response.json();
    
    if (json.status == "OK") {
        
        M.toast({html: json.message});
        
    }
    
}

const esProfesor = async (profesorId, cursoId) => {
    
    let url = `${BASE_URL}/cursos`;
    
    let params = `?obtener=esProfesor`;
    params += `&profesor=${profesorId}`;
    params += `&curso=${cursoId}`;
    
    let response = await fetch(url + params);
    
    let json = await response.json();
    
    return json.data[0];
    
}

const mostrarCursosModal = async (json, profesorId) => {
    
    json = await json;
    
    let cursosModalContent = document.getElementById("modal-cursos-content");
    cursosModalContent.innerHTML = "";
    
    let ul = document.createElement("ul");
    ul.classList.add("collection");
    ul.classList.add("with-header");
    
    ul.innerHTML = '<li class="collection-header"><h4>Cursos</h4></li>';
    
    json.data.forEach(curso => {
        
        esProfesor(profesorId, curso.id).then(esProfesorDelCurso => {
            
            ul.innerHTML += `<li class="collection-item avatar valign-wrapper">
                            <img src="${curso.foto}" alt="${curso.nombre}" class="circle">
                            <div>${curso.nombre} <br>
                                <span class="grey-text text-lighten-1">${curso.categoria.nombre}</span>
                                <form class="secondary-content">
                                    <input type="hidden" name="profesor" value="${profesorId}">
                                    <div class="switch">
                                        <label><input type="checkbox" value="${curso.id}" ${esProfesorDelCurso ? "checked" : ""} class="switchInput"><span class="lever"></span></label>
                                    </div>
                                </form>
                            </div>
                        </li>`
        }).then(() => {
            
            let switchInputs = document.querySelectorAll(".switchInput");

            switchInputs.forEach(switchInput => {

                switchInput.addEventListener("change", () => {
                    if (switchInput.checked) {
                        agregarProfesorCurso(profesorId, switchInput.value);
                    } else {
                        eliminarProfesorCurso(profesorId, switchInput.value);
                    }
                });

            })
            
        })
        
    });
    
    cursosModalContent.appendChild(ul);
    
}


