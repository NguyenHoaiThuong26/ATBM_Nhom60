// Lấy tham chiếu đến button Sign Up
const signUpButton = document.getElementById('sign-up-link');

// Lấy tham chiếu đến button Log In
const logInButton = document.getElementById('log-in-link');

// Lấy tham chiếu đến form đăng nhập
const logInForm = document.getElementById('log-in');

// Lấy tham chiếu đến form đăng ký
const signUpForm = document.getElementById('sign-up');

// Lấy tham chiếu đến form đăng nhập button quên mật khẩu
const forgotPW = document.querySelector('.forget-pw');

// Lấy tham chiếu đến form quên mật khẩu
const forgotPWForm = document.getElementById('forgot-pw__form');

const loginPW = document.querySelector('.login-in-pw');

// Thêm sự kiện click cho button Sign Up
signUpButton.addEventListener('click', function() {
    logInForm.classList.remove('open');
    logInForm.classList.add('hidden');
    signUpForm.classList.add('open');
    signUpForm.classList.remove('hidden');
});

// Thêm sự kiện click cho button Log In
logInButton.addEventListener('click', function() {
    signUpForm.classList.remove('open');
    signUpForm.classList.add('hidden');
    logInForm.classList.add('open');
    logInForm.classList.remove('hidden');
});

// Thêm sự kiện click cho button quên mật khẩu
forgotPW.addEventListener('click', function() {
    logInForm.classList.add('hidden');
    logInForm.classList.remove('open');
    forgotPWForm.classList.add('open');
    forgotPWForm.classList.remove('hidden');
});

loginPW.addEventListener('click', function() {
    logInForm.classList.add('open');
    logInForm.classList.remove('hidden');
    forgotPWForm.classList.add('hidden');
    forgotPWForm.classList.remove('open');
});


