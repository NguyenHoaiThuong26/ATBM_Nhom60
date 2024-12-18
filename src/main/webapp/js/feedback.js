// Cấu trúc dữ liệu để lưu trữ phản hồi
let feedbacks = [];

// Khởi tạo Owl Carousel
$(document).ready(function(){
    $('#feedbackCarousel').owlCarousel({
        items: 1,
        loop: true,
        margin: 10,
        autoplay: true,
        autoplayTimeout: 3000,
        autoplayHoverPause: true
    });
});


// Hàm để xử lý việc gửi phản hồi
function submitFeedBack() {
    // Lấy dữ liệu từ các trường Input
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let email = document.getElementById("email").value;
    let phone = document.getElementById("phone").value;
    let message = document.getElementById("message").value;

    // Kiểm tra nếu có ít nhất Tên và Message
    if (firstName && message) {
        // Tạo phản hồi mới nhất
        let newFeedback = {
        firstName: firstName,
        lastName: lastName,
        email: email,
        phone: phone,
        message: message
    };

    // Thêm phản hồi mới nhất  vào đầu mảng
    feedbacks.unshift(newFeedback);

    // Giới hạn số lượng phản hồi tối đa la 5
    if(feedbacks.length > 5) {
        feedbacks.pop(); // Loại bỏ phản hồi cũ nhất
    }

    // Cập nhật giao diện
    updateFeedBackUI();

    } else {
        alert("Vui lòng nhập ít nhất Tên và Message.");
    }
}

    // Hàm để cập nhật giao diện với danh sách phản hồi
    function updateFeedBackUI() {
        let feedbackCarousel = $("#feedbackCarousel");
        feedbackCarousel.trigger('destroy.owl.carousel'); // Xóa owl carousel cũ
        
        // Hiển thị tối đa 5 phản hồi
        for (let i = 0; i < array.length && i < 5; i++) {
            let feedback = array[i];
            let feedbackElement = $("<div class='item'></div>");
            feedbackElement.html(`
            <div class="item">
                <p><i class="fa fa-quote-left"></i> ${feedback.message} <i class="fa fa-quote-right"></i></p>
                <p class="feedback__name">${feedback.firstName}</p>
            </div>
        `
        );
        feedbackCarousel.append(feedbackElement);
        }

        // Khởi tạo lại Owl Carousel
        feedbackCarousel.owlCarousel({
            items: 1,
            loop: true,
            margin: 10,
            autoplay: true,
            autoplayTimeout: 3000,
            autoplayHoverPause: true
        });

    }
