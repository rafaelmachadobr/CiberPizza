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
});