document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.modal');
    var instances = M.Modal.init(elems);
  });

document.addEventListener('DOMContentLoaded', function() {
    var elem = document.querySelector('.dropdown-trigger');
    var instances = M.Dropdown.init(elem, {
        closeOnClick: false,
        constrainWidth: false
    });
});

toastCategoriasModal = () => {
    let categoriasBtn = document.querySelector('#categorias-btn a');
    categoriasBtn.addEventListener('click', () => {
        M.toast({html: 'Tocá fuera de la ventana para salir', classes: 'rounded', displayLength: 3000})
    })
}

toastCategoriasModal();