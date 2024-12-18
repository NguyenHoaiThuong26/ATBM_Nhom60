function updateQuantity(index, change) {
    var quantityInput = document.getElementById('quantity' + index);
    var newQuantity = parseInt(quantityInput.value) + change;

    // Make sure the new quantity is at least 1
    newQuantity = Math.max(newQuantity, 1);

    // Update the quantity display
    quantityInput.value = newQuantity;
}

function submitQuantity(index) {
    // Retrieve the current quantity
    var quantityInput = document.getElementById('quantity' + index);
    var newQuantity = parseInt(quantityInput.value);

    // Send AJAX request to update quantity on the server
    updateQuantityOnServer(index, newQuantity);
}

function updateQuantityOnServer(index, newQuantity) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Update any other UI elements if needed
            // For example, update the total price here
            updateTotalPrice(index);
        }
    };

    // Set up the request
    xhr.open('POST', '<%= request.getContextPath() %>/updateQuantity', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    // Send the data
    var params = 'index=' + index + '&newQuantity=' + newQuantity;
    xhr.send(params);
}

function updateTotalPrice(index) {
    var quantity = submitQuantity(index)
}