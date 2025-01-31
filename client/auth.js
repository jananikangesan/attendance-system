console.log("auth.js script loaded");

const API_URL = "http://localhost:8082/user/login"; 

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

       
       
    } catch (error) {
        console.error("Login error:", error);
        document.getElementById("error-message").style.display = "block";
    }
});