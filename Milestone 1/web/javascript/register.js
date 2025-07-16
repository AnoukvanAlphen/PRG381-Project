const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
const phoneRegex = /^0[6-8][0-9]{8}$/;
const passwordRegex = /^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\S+$).{8,20}$/;

document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".error-text").forEach(el => {
        el.style.display = "none";
    });

    const setupValidation = (id, regex, message) => {
        const input = document.getElementById(id);
        const error = document.getElementById(`${id}-error`);
        input.addEventListener("input", () => {
            if (input.value.trim() === "") {
                error.style.display = "none";
            } else if (!regex.test(input.value)) {
                error.textContent = message;
                error.style.display = "block";
            } else {
                error.style.display = "none";
            }
        });
    };

    setupValidation("email", emailRegex, "Please enter a valid email.");
    setupValidation("phone", phoneRegex, "Enter a valid SA phone number (e.g., 0812345678).");
    setupValidation("password", passwordRegex, "Password must be 8–20 characters with uppercase, lowercase, number, and symbol.");

    ["name", "surname", "student_number"].forEach(id => {
        const input = document.getElementById(id);
        const error = document.getElementById(`${id}-error`);
        input.addEventListener("input", () => {
            if (input.value.trim() === "") {
                error.textContent = "This field is required.";
                error.style.display = "block";
            } else {
                error.style.display = "none";
            }
        });
    });
});
