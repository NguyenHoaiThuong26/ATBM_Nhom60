const policyReturn = document.querySelector('.return');
const policyConfidentiality = document.querySelector('.confidentiality');

const returnContent = document.querySelector('.policy-return');
const confidentialityContent = document.querySelector('.policy-confidentiality');

policyReturn.addEventListener('click', function() {
    returnContent.classList.add('active');
    confidentialityContent.classList.remove('active');
});

policyConfidentiality.addEventListener('click', function() {
    confidentialityContent.classList.add('active');
    returnContent.classList.remove('active');
});