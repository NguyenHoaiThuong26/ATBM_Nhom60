function Validator(option) {

    let selectorRules = {}

    // Hàm validate
        function validate(inputElement, rule) {
            // dùng closet để tìm phần tử cha có class là 'form-grp'.
            let parentElement = inputElement.closest(option.parentError);
            // lấy phần tử con của parentElement có class là errorMessage
            let errorElement = parentElement.querySelector(option.errorSelector);

            let errorMessage;

            // Lấy ra các rule của selector
            let rules = selectorRules[rule.selector];

            for (let i = 0; i < rules.length; i++) {
                errorMessage = rules[i](inputElement.value);
                if(errorMessage) break;
            }

            // Nếu không có phần tử con class = 'errorMessage'
            if (!errorElement) {
                // thì tạo thêm thẻ div và thêm vào trong phần tử cha (parentElement)
                // thẻ div có tên là errorMessage
                errorElement = document.createElement('div');
                errorElement.classList.add('errorMessage');
                parentElement.appendChild(errorElement);
            }

            if (errorMessage) {
                errorElement.innerText = errorMessage;
                parentElement.classList.add('invalid');
            } else {
                errorElement.innerText = '';
                parentElement.classList.remove('invalid');
            }
            return !!errorMessage;
        }


    // lấy element của form cần validate
    let formElement = document.querySelector(option.form);

    if (formElement) {

        option.rules.forEach(function (rule) {

            // Lưu các rules lại cho mỗi input
            if(Array.isArray(selectorRules[rule.selector])) {
                selectorRules[rule.selector].push(rule.check);
            } else {
                selectorRules[rule.selector] = [rule.check]
            }

            let inputElement = formElement.querySelector(rule.selector);

            if (inputElement) {
                // xử lý trường hợp blur ra ngoài
                inputElement.onblur = function () {
                    validate(inputElement, rule);
                };
                // xử lý trường hợp user đang nhập
                inputElement.oninput = function () {
                    let parentElement = inputElement.closest(option.parentError);
                    let errorElement = parentElement.querySelector(option.errorSelector);

                    errorElement.innerText = '';
                    parentElement.classList.remove('invalid');
                };
            }
        });

    }
}

Validator.isRequired = function (selector, message) {
    return {
        selector: selector,
        check: function (value) {
            return value.trim() ? undefined : message || 'Vui lòng nhập trường này';
        }
    };
};

Validator.isEmail = function (selector, message) {
    return {
        selector: selector,
        check: function (value) {
            let regex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            return value.match(regex) ? undefined : message ||'Vui lòng nhập đúng định dạng email \n' + 'abc@gmail.com OR abc@edu.com ...';
        }
    }
}

Validator.minLength = function (selector, min, message) {
    return {
        selector: selector,
        check: function (value) {
            return value.length >= min ? undefined : message || 'Vui lòng nhập tối thiểu ' + min +' ký tự';
        }
    }
}

Validator.confirmPassword = function (selector, getConfirmPassword, message) {
    return {
        selector: selector,
        check: function (value) {
            const confirmPassword = getConfirmPassword();

            return value === confirmPassword ? undefined : message || 'Xác thực mật khẩu chưa đúng, vui lòng nhập lại';
        }
    }
}
