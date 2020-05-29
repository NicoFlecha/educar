let nombreInput = document.getElementById("nombre");
let categoriaNueva = document.getElementById("categoria-nueva");
let nombreCategoria = document.getElementById("nombre_categoria");

nombreInput.addEventListener("keyup", function () {
    
    if (categoriaNueva.classList.contains("hide")) {
        categoriaNueva.classList.remove("hide");
    }
    
    nombreCategoria.innerHTML = nombreInput.value;
    
    if (nombreInput.value.length === 0) {
        categoriaNueva.classList.add("hide");
    }
    
});