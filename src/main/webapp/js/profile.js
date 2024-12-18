

document.getElementById("updatePasswordLink").addEventListener("click", function(event) {
    // Ngăn chặn hành vi mặc định của liên kết
    event.preventDefault();

    // Thực hiện các hành động khác nếu cần
    // ...

    // Chuyển hướng đến trang resetPassword.jsp bằng JavaScript
    window.location.href = "./resetPassword.jsp";
});

//JS email error
const emailField = document.getElementById("email");
const emailError = document.getElementById("email-error");


function validateEmail() {
    if (!emailField.value.match(/^[A-Za-z\._\-0-9]*[@][A-Za-z]*[\.][a-z]{2,4}$/)) {
        emailError.innerHTML = "Vui lòng nhập địa chỉ email hợp lệ";
        emailField.style.border = "1px solid red";
        emailError.style.top = "48%";
        return false;
    }

    emailError.innerHTML = "";
    emailField.style.border = "1px solid rgb(196, 196, 207)";
    emailError.style.top = "40%";
    return true;
}

//JS phone error
const phoneField = document.getElementById("phone");
const phoneError = document.getElementById("phone-error");

function validatePhone() {
    if (!phoneField.value.match(/^(0|\+84)(3[2-9]|5[689]|7[06-9]|8[1-9]|9\d)\d{7}$/)) {
        phoneError.innerHTML = "Vui lòng nhập số điện thoại hợp lệ";
        phoneField.style.border = "1px solid red";
        phoneError.style.top = "32%";
        return false;
    }

    phoneError.innerHTML = "";
    phoneField.style.border = "1px solid rgb(196, 196, 207)";
    phoneError.style.top = "40%";
    return true;
}



