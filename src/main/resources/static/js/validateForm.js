function validateForm() {
    const username = document.getElementById("username");
    const email = document.getElementById("email");
    const password = document.getElementById("password");
    const confirmPassword = document.getElementById("confirmPassword");



    email.addEventListener("submit", event => {

        if (email.validity.typeMismatch) {
            email.setCustomValidity("Please enter a valid e-mail address!");
        } else {
            email.setCustomValidity("");
        }

    });

    password.addEventListener("submit", event => {

        var passwordValue = document.getElementById("password").value;

        if (passwordValue.length < 3 || passwordValue.length > 15) {
            event.preventDefault();
            password.setCustomValidity("Password must be between 3 and 15 characters!");
        } else {
            password.setCustomValidity("");
        }

    });


    confirmPassword.addEventListener("submit", event => {

        var passwordValue = document.getElementById("password").value;
        var confirmPasswordValue = document.getElementById("confirmPassword").value;

        if (confirmPasswordValue.length < 3 || confirmPasswordValue.length > 15) {
            event.preventDefault();
            password.setCustomValidity("Password must be between 3 and 15 characters!");
        } else {
            password.setCustomValidity("");
        }

        if (passwordValue !== confirmPasswordValue) {
            event.preventDefault();
            confirmPassword.setCustomValidity("Passwords should match!")
        } else {
            confirmPassword.setCustomValidity("");
        }

    });



}

function validateUsername(username) {

    username.addEventListener("submit", (event) => {

        var usernameValue = document.getElementById("username").value;

        if (usernameValue.length < 3 || usernameValue.length > 15) {
            event.preventDefault();
            username.setCustomValidity("Username should be between 3 and 15 characters!")
        } else {
            username.setCustomValidity("");
        }

    });
}

