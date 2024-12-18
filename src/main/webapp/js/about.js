
const aboutProBackground = document.getElementById("aboutProBackground");

window.addEventListener("scroll", function () {
    const scrollPosition = window.scrollY;
    const speed = 0.5; // Điều chỉnh tốc độ thay đổi

    aboutProBackground.style.backgroundPosition = `center ${-scrollPosition * speed}px`;
});



document.addEventListener("DOMContentLoaded", function () {
    // Lấy các phần tử cần thiết
    var sliderItems = document.querySelectorAll(".images__slider__item");
    var currentIndex = 0;
  
    // Hàm chuyển đổi ảnh
    function changeImage(index) {
      // Ẩn tất cả các ảnh
      sliderItems.forEach(function (item) {
        item.classList.remove("img-active");
      });
  
      // Hiển thị ảnh được chọn
      sliderItems[index].classList.add("img-active");
    }
  
    // Hàm chuyển đổi tự động
    function autoChange() {
      currentIndex = (currentIndex + 1) % sliderItems.length;
      changeImage(currentIndex);
    }
  
    // Gọi hàm chuyển đổi tự động sau mỗi khoảng thời gian
    var intervalId = setInterval(autoChange, 3000); // 5000 milliseconds = 5 seconds
  
  });

  
  document.addEventListener("DOMContentLoaded", function () {
    // Lấy các phần tử cần thiết
    var itemContents = document.querySelectorAll(".item__content");
    var currentIndex = 0;

    // Hàm chuyển đổi nội dung
    function changeContent(index) {
        // Ẩn tất cả các nội dung
        itemContents.forEach(function (itemContent) {
            itemContent.classList.remove("content-active");
        });

        // Hiển thị nội dung được chọn
        itemContents[index].classList.add("content-active");
    }

    // Hàm chuyển đổi tự động
    function autoChange() {
        currentIndex = (currentIndex + 1) % itemContents.length;
        changeContent(currentIndex);
    }

    // Gọi hàm chuyển đổi tự động sau mỗi khoảng thời gian
    var intervalId = setInterval(autoChange, 3000); // 3000 milliseconds = 3 seconds
});

// ReStorage Page
  const aboutReturn = document.querySelector('.return');
  const aboutConfidentiality = document.querySelector('.confidentiality');

  const returnContent = document.querySelector('.about__return');
  const confidentialityContent = document.querySelector('.about__confidentiality');

  policyReturn.addEventListener('click', function() {
    returnContent.classList.add('active');
    confidentialityContent.classList.add('active');
  });

  policyConfidentiality.addEventListener('click', function() {
    confidentialityContent.classList.add('active');
    returnContent.classList.remove('active');
});

