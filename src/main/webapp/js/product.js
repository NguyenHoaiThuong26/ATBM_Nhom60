
$('.sort_pricess').click(function() {
    $('#sort_pricess').toggleClass('hidden');
    $('#sort_feature').addClass('hidden');
    $('#sort_manufacturer').addClass('hidden');
    $('#sort_order').addClass('hidden');
});
$('.sort_feature').click(function() {
    $('#sort_feature').toggleClass('hidden');
    $('#sort_pricess').addClass('hidden');
    $('#sort_manufacturer').addClass('hidden');
    $('#sort_order').addClass('hidden');

});
$('.sort_manufacturer').click(function() {
    $('#sort_manufacturer').toggleClass('hidden');
    $('#sort_pricess').addClass('hidden');
    $('#sort_feature').addClass('hidden');
    $('#sort_order').addClass('hidden');
});
$('.sort_order').click(function() {
    $('#sort_order').toggleClass('hidden');
    $('#sort_pricess').addClass('hidden');
    $('#sort_feature').addClass('hidden');
    $('#sort_manufacturer').addClass('hidden');
});
