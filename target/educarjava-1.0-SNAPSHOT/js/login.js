let registerFormBtn = document.querySelector(".register-form-btn");
let loginFormBtn = document.querySelector(".login-form-btn");
let loginProfesorFormBtn = document.querySelector(".login-profesor-form-btn");
let loginAlumnoFormBtn = document.querySelector(".login-alumno-form-btn");
let loginForm = document.getElementById("login-form");
let registerForm = document.getElementById("register-form");
let loginProfesorForm = document.getElementById("login-profesor-form");

registerFormBtn.addEventListener("click", function () {
    loginForm.classList.toggle('hide');
    registerForm.classList.toggle('hide');

});

loginFormBtn.addEventListener("click", function () {
    registerForm.classList.toggle('hide');
    loginForm.classList.toggle('hide');
});

loginProfesorFormBtn.addEventListener("click", function () {
   loginForm.classList.toggle('hide');
   loginProfesorForm.classList.toggle('hide');
});

loginAlumnoFormBtn.addEventListener("click", function () {
    loginForm.classList.toggle('hide');
    loginProfesorForm.classList.toggle('hide');
})
