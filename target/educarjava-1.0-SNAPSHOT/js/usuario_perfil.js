const BASE_URL = "http://localhost:8080/educarjava";

const obtenerUsuario = async () => {
    
    url = `${BASE_URL}/usuario`;
    
    params = '?obtener=usuario';
    
    response = await fetch(url + params);
    
    json = await response.json();
    
    if (json.status == "OK") {
        
        if (json.data.length > 0) {
            
            usuario = json.data[0];
            return usuario;
            
        }
        
    }
    return "";
}

const guardarUsuario = async () => {
    
    nombreInput = document.getElementById("nombre");
    apellidoInput = document.getElementById("apellido");
    fechaNacimientoInput = document.getElementById("fecha-nacimiento");
    fotoInput = document.getElementById("foto");
    
    url = `${BASE_URL}/usuario`;
    
    params = `?nombre=${nombreInput.value}&apellido=${apellidoInput.value}&fecha_nacimiento=${fechaNacimientoInput.value}&foto=${fotoInput.value}`;
    
    response = await fetch(url + params, {method: "PUT"});
    
    json = await response.json();
    
    M.toast({html: json.message});
    
    mostrarUsuario();
    
}

const mostrarUsuario = async () => {
    
    perfilContainer = document.getElementById("perfil");
    
    perfilContainer.innerHTML = "";
    
    usuario = await obtenerUsuario();
    
    row = document.createElement("div");
    row.classList.add("row");

    foto = "https://img.icons8.com/ios-glyphs/550/000000/login-as-user.png";
    
    if (usuario.foto) { 
        foto = usuario.foto;
    } else {
        usuario.foto = "";
    }
    
    fechaNacimiento = "Sin fecha de nacimiento";
    fechaNacimientoValor = null;
    console.log(usuario);
    if(usuario.fechaNacimiento) {
        fechaNacimiento = `Fecha de nacimiento: ${usuario.fechaNacimiento.day+1}-${usuario.fechaNacimiento.month}-${usuario.fechaNacimiento.year}`;
        fechaNacimientoValor = new Date(usuario.fechaNacimiento.year ,usuario.fechaNacimiento.month - 1, usuario.fechaNacimiento.day+1);
    }
    
    row.innerHTML = `<div class="col s12 m6 offset-m3">
                        <div class="card teal lighten-2">
                            <div class="right-align">
                                <a class="btn-floating btn-large waves-effect waves-light blue btn modal-trigger" href="#modal-user" id="editar-btn"><i class="material-icons">edit</i></a>
                            </div>
                            <div class="foto center-align">
                                <img src="${foto}" alt="Foto de ${usuario.nombre}">
                            </div>
                            <div class="card-content white-text center-align">
                                <span class="card-title">${usuario.nombre} ${usuario.apellido}</span>
                                <p>${usuario.email}</p>
                                <p>${fechaNacimiento}</p> 
                            </div>
                        </div>
                    </div>`;
    
    h1 = document.createElement("h1");
    h1.classList.add("center-align");
    
    modal = document.createElement("div");
    modal.classList.add("modal");
    modal.id = "modal-user";
    modal.innerHTML = `<div class="modal-content">
                            <h4>Editar Datos Personales</h4>
                            <form id="actualizar-form">
                                <div class="row">
                                    <div class="input-field col s6">
                                        <input value="${usuario.nombre}" id="nombre" type="text" class="validate">
                                        <label for="nombre">Nombre</label>
                                    </div>
                                    <div class="input-field col s6">
                                        <input value="${usuario.apellido}" id="apellido" type="text" class="validate">
                                        <label for="apellido">Apellido</label>
                                    </div>
                                    <div class="input-field col s6">
                                        <input disabled value="${usuario.email}" id="email" type="text" class="validate">
                                        <label for="email">Email</label>
                                    </div>
                                    <div class="input-field col s6">
                                        <input type="text" class="datepicker" id="fecha-nacimiento">
                                        <label for="fecha-nacimiento">Fecha de Nacimiento</label>
                                    </div>
                                    <div class="input-field col s6">
                                        <input value="${usuario.foto}" id="foto" type="text" class="validate">
                                        <label for="foto">Foto</label>
                                    </div>
                                </div>
                                <div class="right-align">
                                    <a class="modal-close waves-effect waves-light btn red">Cancelar</a>
                                    <button class="btn waves-effect waves-light" type="submit" name="action">Guardar Cambios</button>
                                </div>
                            </form>
                        </div>`;
                        
    
    perfilContainer.appendChild(h1);
    perfilContainer.appendChild(row);
    perfilContainer.appendChild(modal);
    
    editarBtn = document.getElementById("editar-btn");
    
    var elems = document.querySelector('.modal');
    var instance = M.Modal.init(elems);
    M.updateTextFields();
    var elems = document.querySelector('.datepicker');
    var instances = M.Datepicker.init(elems, {
        defaultDate: fechaNacimientoValor,
        setDefaultDate: true,
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
        }});
    
    actualizarForm = document.getElementById("actualizar-form");
    
    actualizarForm.addEventListener("submit", event => {
        
        event.preventDefault();
        guardarUsuario();
        
    });
    
    fechaNacimientoInput = document.getElementById("fecha-nacimiento");
    fechaNacimientoInput.addEventListener("change", () => {
        console.log(fechaNacimientoInput.value);
    })
    
}



document.addEventListener("DOMContentLoaded", mostrarUsuario);