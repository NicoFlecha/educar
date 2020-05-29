document.addEventListener('DOMContentLoaded', function() {
    var elem = document.querySelector('.carousel');
    var instances = M.Carousel.init(elem);
    let instance = M.Carousel.getInstance(elem);
    setInterval(() => {
        instance.next();
    }, 3000)
  });