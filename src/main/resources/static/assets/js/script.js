document.addEventListener("DOMContentLoaded", () => {
  "use strict";

  const preloader = document.querySelector("#preloader");
  if (preloader) {
    window.addEventListener("load", () => {
      preloader.remove();
    });
  }

  const scrollTopButton = document.querySelector(".scroll-top");

  if (scrollTopButton) {
    const toggleScrollTop = function () {
      window.scrollY > 100
        ? scrollTopButton.classList.add("active")
        : scrollTopButton.classList.remove("active");
    };

    window.addEventListener("load", toggleScrollTop);
    document.addEventListener("scroll", toggleScrollTop);

    scrollTopButton.addEventListener("click", function () {
      window.scrollTo({
        top: 0,
        behavior: "smooth",
      });
    });
  }

  (() => {
    "use strict";

    const forms = document.querySelectorAll(".needs-validation");

    Array.from(forms).forEach((form) => {
      form.addEventListener(
        "submit",
        (event) => {
          if (!form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
          }

          form.classList.add("was-validated");
        },
        false
      );
    });
  })();

  new Swiper(".slides-1", {
    speed: 600,
    loop: true,
    autoplay: {
      delay: 5000,
      disableOnInteraction: false,
    },
    slidesPerView: "auto",
    pagination: {
      el: ".swiper-pagination",
      type: "bullets",
      clickable: true,
    },
    navigation: {
      nextEl: ".swiper-button-next",
      prevEl: ".swiper-button-prev",
    },
  });

  new Swiper(".slides-3", {
    speed: 600,
    loop: true,
    autoplay: {
      delay: 5000,
      disableOnInteraction: false,
    },
    slidesPerView: "auto",
    pagination: {
      el: ".swiper-pagination",
      type: "bullets",
      clickable: true,
    },
    navigation: {
      nextEl: ".swiper-button-next",
      prevEl: ".swiper-button-prev",
    },
    breakpoints: {
      320: {
        slidesPerView: 1,
        spaceBetween: 40,
      },

      1200: {
        slidesPerView: 3,
      },
    },
  });

  document.addEventListener("DOMContentLoaded", function () {
    const myForm = document.querySelector(".email-form");
    const liveToast = new bootstrap.Toast(document.getElementById("liveToast"));

    myForm.addEventListener("submit", function (event) {
      event.preventDefault();

      if (!myForm.checkValidity()) {
        event.stopPropagation();
      } else {
        liveToast.show();

        setTimeout(function () {
          myForm.reset();
          myForm.classList.remove("was-validated");
        }, 3000);
      }
    });
  });

  $(document).ready(function () {
    $(".cpf").mask("000.000.000-00", { reverse: true });
    $("#telefone").mask("(00) 00000-0000");
  });

  $(document).ready(function () {
    $("#mostrarSenha").click(function () {
      var senhaInput = $("#senha");
      var icon = $("#mostrarSenha i");

      if (senhaInput.prop("type") == "password") {
        senhaInput.prop("type", "text");
        icon.removeClass("bi-eye-slash").addClass("bi-eye");
      } else {
        senhaInput.prop("type", "password");
        icon.removeClass("bi-eye").addClass("bi-eye-slash");
      }
    });
  });

  $(document).ready(function () {
    $("#mostrarConfirmarSenha").click(function () {
      var confirmarSenhaInput = $("#confirmarSenha");

      if (confirmarSenhaInput.attr("type") === "password") {
        confirmarSenhaInput.attr("type", "text");
        $("#mostrarConfirmarSenha i")
          .removeClass("bi-eye-slash")
          .addClass("bi-eye");
      } else {
        confirmarSenhaInput.attr("type", "password");
        $("#mostrarConfirmarSenha i")
          .removeClass("bi-eye")
          .addClass("bi-eye-slash");
      }
    });
  });
});
