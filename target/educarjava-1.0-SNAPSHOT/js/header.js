document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.sidenav');
    var instances = M.Sidenav.init(elems);
  });
  
document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.dropdown-usuario-trigger');
    var instances = M.Dropdown.init(elems, {coverTrigger: false});
});