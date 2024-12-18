let categoryButton = document.querySelector('.add-category');
let brandButton = document.querySelector('.add-brand');
let supplierButton = document.querySelector('.add-supplier');

let dialogCate = document.querySelector('.dialog-category');
let dialogBrand = document.querySelector('.dialog-brand');
let dialogSupplier = document.querySelector('.dialog-supplier');

let closeBtnCate = dialogCate.querySelector('.close-btn-cate');
let closeBtnBrand = dialogBrand.querySelector('.close-btn-brand');
let closeBtnSupplier = dialogSupplier.querySelector('.close-btn-supplier');

categoryButton.addEventListener('click', function (event) {
    event.preventDefault();
    toggleDialogVisibility(dialogCate);
});

brandButton.addEventListener('click', function (event) {
    event.preventDefault();
    toggleDialogVisibility(dialogBrand);
});

supplierButton.addEventListener('click', function (event) {
    event.preventDefault();
    toggleDialogVisibility(dialogSupplier);
});

closeBtnCate.addEventListener('click', function () {
    toggleDialogVisibility(dialogCate);
});

closeBtnBrand.addEventListener('click', function () {
    toggleDialogVisibility(dialogBrand);
});

closeBtnSupplier.addEventListener('click', function () {
    toggleDialogVisibility(dialogSupplier);
});

function toggleDialogVisibility(dialog) {
    // Hiển thị hoặc ẩn dialog tùy thuộc vào trạng thái hiện tại
    if (dialog.style.display === 'none' || dialog.style.display === '') {
        dialog.style.display = 'block';
    } else {
        dialog.style.display = 'none';
    }

}