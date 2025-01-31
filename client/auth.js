console.log("auth.js script loaded");

const API_URL = "http://localhost:8082/user/login"; 

document.addEventListener("DOMContentLoaded", () => {
    if (window.location.pathname.includes("home.html") && !localStorage.getItem("token")) {
        window.location.href = "login.html"; 
    }
   
});

document.getElementById("loginForm")?.addEventListener("submit", async function(event) {
    event.preventDefault(); 

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    console.log("Form submitted!");
    console.log("username:", username);
    console.log("password:", password);

    try {
        const response = await fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ username, password })
        });

       
        console.log("Response status:", response.status);

        if (!response.ok) { 
            throw new Error("Invalid credentials");
        }

        const data = await response.json();  
        console.log("Response data:", data);

        if (data && data.token) {
            localStorage.setItem("token", data.token); 
            window.location.href = "home.html"; 

        } else {
            throw new Error("Token not found in response");
        }  
       
    } catch (error) {
        console.error("Login error:", error);
        document.getElementById("error-message").style.display = "block";
    }
});

function logout() {
    localStorage.removeItem("token");
    window.location.href = "login.html";
}