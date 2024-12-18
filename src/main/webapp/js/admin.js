const manageUser = document.querySelector('.manage-user');
const manageUserContent = document.getElementById('manage-user');

const manageProduct = document.querySelector('.manage-product');
const manageProductContent = document.getElementById('manage-product');

const manageBill = document.querySelector('.manage-bill');
const manageBillContent = document.getElementById('manage-bill');

manageUser.addEventListener('click', function() {
    manageUserContent.classList.add('active');
    manageProductContent.classList.remove('active');
    manageBillContent.classList.remove('active');
});

manageProduct.addEventListener('click', function() {
    manageUserContent.classList.remove('active');
    manageProductContent.classList.add('active');
    manageBillContent.classList.remove('active');
});

manageBill.addEventListener('click', function() {
    manageUserContent.classList.remove('active');
    manageProductContent.classList.remove('active');
    manageBillContent.classList.add('active');
});