const BASE_URL = "http://localhost:8080/educarjava";

document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('select');
    var instances = M.FormSelect.init(elems);
  });
  
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelector('.datepicker');
    console.log(elems);
    var instances = M.Datepicker.init(elems, {
        format: "yyyy-mm-dd",
        firstDay: 1,
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
});

let formCurso = document.getElementById("guardar-curso-form");
let nombre = document.getElementById("nombre");
let foto = document.getElementById("foto");
let descripcion = document.getElementById("descripcion");
let fechaInicio = document.getElementById("fecha_inicio");
let duracion = document.getElementById("duracion");
let categoria = document.getElementById("categoria");
let precio = document.getElementById("precio");
let cursoId = document.getElementById("curso_id");
let btnGuardar = document.getElementById("guardar-curso-btn");

formCurso.addEventListener("submit", event => {
    guardarCurso(event);
});

const guardarCurso = async event => {
    
    event.preventDefault();
    
    url = `${BASE_URL}/cursos`;
    params = `?accion=guardar&nombre=${nombre.value}&foto=${foto.value}&descripcion=${descripcion.value}&fecha_inicio=${fechaInicio.value}&duracion=${duracion.value}&categoria=${categoria.value}&precio=${precio.value}&curso_id=${cursoId.value}`;
    
    
    response = await fetch(url + params, {method: "POST"});
    
    json = await response.json();
    
    if (json.status = "OK") {
        M.toast({html: json.message});
        if (cursoId.value == 0) {
            cursoId.value = json.data[0];
        }
    }
    
}