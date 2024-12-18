const btn = document.querySelectorAll("addToCart");
btn.forEach(function(addToCart, index) {
    addToCart.addEventListener("click", function(event) {
        var btnItem = event.target;
        var product = btnItem.parentElement;
        var productImg = product.querySelector("img").src;
        var productName = product.querySelector("title").innerText;
        var productDesc = product.querySelector("desc").innerText;
        var productPrice = product.querySelector("price").innerText;

        addCart(productImg,productName,productDesc,productPrice);
    }) 
});

function addCart(productImg,productName,productDesc,productPrice) {
    var addtr = document.createElement("tr")
    var trcontent = 
    '<div class="list"><div class="item"><div class="img"><img src="'+ productImg +'" alt="'+productName+'"></div><div class="item_content"><div class="title">'+ productName +'</div><div class="desc">'+ productDesc+'</div><div class="price">'+ productPrice +'</div><button class="addToCart">Thêm vào giỏ hàng</button></div></div></div>';
    addtr.innerHTML = trcontent;
    var cartTable = document.querySelector("left__content");
    cartTotal();
}